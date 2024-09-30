package org.dam.views;

import com.github.lgooddatepicker.components.DatePicker;
import org.dam.models.DinoModels;
import org.dam.models.FeedingModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.dam.controllers.QueriesDialogController.*;

public class QueriesDialog extends JDialog implements InterfaceView {
    private JPanel mainPanel;
    private JTable tb_dinos;
    private JTextField tx_id;
    private JButton bt_id;
    private JButton bt_name_fly;
    private JTextField tx_name;
    private JCheckBox ck_fly;
    private JButton bt_dates;
    private DatePicker dp_date1;
    private DatePicker dp_date2;
    private JComboBox cb_feeding;
    private JButton BUSCARButton;
    private JButton bt_back;
    private JButton bt_next;
    private JComboBox cb_results;
    private JButton bt_clean;
    private JLabel lb_pages;
    private boolean isFeedingSelected = false;
    private ArrayList<FeedingModel> feedingList;
    private ActionListener listener;
    private int totalPages;
    private int page;
    private int totalElements;


    public QueriesDialog(JFrame frame, boolean modal) {
        super(frame, modal);
        initWindow();
        initComponents();
    }

    public void setTotalElements(int totalElements) {
       this.totalElements = totalElements;
    }

    public int getTotalElements(){
        return totalElements;
    }


    public void setTotalPages(int value) {

        totalPages = (int)Math.ceil((double) getTotalElements() /
                getSelectedResults());
        if(value == 0){
            page = 1;
        }
        else if((value + page) <= totalPages && value + page >0){
            page = value + page;


        }
        lb_pages.setText("Página " + page + " de " + totalPages);

    }



    public void loadComboResults(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for(int i = 10; i <= 50 ; i+=10){
            model.addElement(i);
        }
        cb_results.setModel(model);
        cb_results.setSelectedIndex(0);
    }

    public int getSelectedResults(){
        return (int) cb_results.getSelectedItem();
    }

    public int getSelectedDinoID(){
        TableModel model = tb_dinos.getModel();
        return Integer.valueOf(model.getValueAt(tb_dinos.getSelectedRow(), 0).toString());
    }

    public void loadComboFeeadings(ArrayList<FeedingModel> feedingList){
        this.feedingList = feedingList;
        DefaultComboBoxModel<FeedingModel> model =
                new DefaultComboBoxModel();

        // AÑADIR UN ITEM AL COMBO
        FeedingModel fm = new FeedingModel();
        fm.setName("Elige una opción...");
        fm.setId(0);
        feedingList.add(0, fm);

        for (FeedingModel feedingModel : feedingList) {
            model.addElement(feedingModel);
        }
        cb_feeding.setModel(model);
    }

    public void removeFirtsComboItem(){
        if(!isFeedingSelected){
            isFeedingSelected = true;
            cb_feeding.removeItemListener((ItemListener) listener);
            cb_feeding.removeItemAt(0);
        }

    }

    @Override
    public void initWindow() {
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setCommands();
    }

    @Override
    public void showWindow() {
        setVisible(true);
    }

    @Override
    public void closeWindow() {
        dispose();
    }

    @Override
    public void setCommands() {
        bt_id.setActionCommand(GET_DINO_BY_ID);
        bt_name_fly.setActionCommand(GET_DINOS_BY_NAME_AND_FLY);
        bt_dates.setActionCommand(GET_DINOS_BY_DATES);
        bt_clean.setActionCommand(CLEAN_DIALOG);
    }

    @Override
    public void addListener(ActionListener listener) {
        this.listener = listener;
        bt_id.addActionListener(listener);
        bt_name_fly.addActionListener(listener);
        bt_dates.addActionListener(listener);
        this.addWindowListener((WindowListener) listener);
        bt_clean.addActionListener(listener);
        cb_feeding.addItemListener((ItemListener) listener);
        tb_dinos.addMouseListener((MouseListener) listener);
        cb_results.addItemListener((ItemListener) listener);
        bt_back.addActionListener(listener);
        bt_next.addActionListener(listener);
    }

    public LocalDate getDate1(){
        return dp_date1.getDate();
    }

    public LocalDate getDate2(){
        return dp_date2.getDate();
    }

    public void loadDinoTable(ArrayList<DinoModels> dinoList) {
        String[] encabezado = new String[]{"ID","NOMBRE","ATAQUE",
                "VOLADOR","PESO", "FECHA", "ALIMENTACIÓN"};

        DefaultTableModel model = new DefaultTableModel(null, encabezado){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        for (DinoModels dino : dinoList) {
            model.addRow(dino.getArray());
        }
        tb_dinos.setModel(model);
    }

    public int getDinoID(){
        return Integer.parseInt(tx_id.getText());
    }

    public String getDinoName(){
        return tx_name.getText();
    }

    public boolean getFly(){
        return ck_fly.isSelected();
    }

    @Override
    public void initComponents() {
        tx_id.setText("");
        if(cb_feeding.getItemCount()>0){
            cb_feeding.setSelectedIndex(0);
        }
        isFeedingSelected = false;
        cb_feeding.addItemListener((ItemListener) listener);
        tx_name.setText("");
        dp_date1.setDate(null);
        dp_date2.setDate(null);
        ck_fly.setSelected(false);

    }
}
