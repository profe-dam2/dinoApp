package org.dam.views;

import com.github.lgooddatepicker.components.DatePicker;
import org.dam.models.DinoModels;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.dam.controllers.FormDialogController.CLOSE_FORM_DIALOG;

public class FormDialog extends JDialog implements InterfaceView {
    private JPanel mainPanel;
    private JButton CREARButton;
    private JButton btn_cancel;
    private JTextField tx_name;
    private JTextField tx_feeding;
    private JCheckBox ck_flyer;
    private JSlider sl_weight;
    private JComboBox cb_attack;
    private JLabel lb_warning;
    private DatePicker dp_date;

    public FormDialog(JFrame parent, Boolean modal) {
        super(parent, modal);
        initWindow();
    }

    public void addListener(ActionListener listener) {
        btn_cancel.addActionListener(listener);
    }

    @Override
    public void initComponents() {

    }
    @Override
    public void setCommands() {
        btn_cancel.setActionCommand(CLOSE_FORM_DIALOG);
    }

    public void initWindow(){
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

    public DinoModels getDino(){
        DinoModels dino = new DinoModels();
        dino.setName(tx_name.getText());
        dino.setFeeding(tx_feeding.getText());
        dino.setAttack((String) cb_attack.getSelectedItem());
        dino.setWeigth(sl_weight.getValue());
        dino.setFlying(ck_flyer.isSelected());
        dino.setDate(dp_date.getDate());
        return dino;
    }
    public void setDino(DinoModels dino){
        tx_name.setText(dino.getName());
    }

}
