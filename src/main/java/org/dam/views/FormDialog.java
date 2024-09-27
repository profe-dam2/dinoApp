package org.dam.views;

import com.github.lgooddatepicker.components.DatePicker;
import org.dam.models.DinoModels;
import org.dam.models.FeedingModel;
import org.dam.utils.RoundedBorder;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;

import static org.dam.controllers.FormDialogController.*;

public class FormDialog extends JDialog implements InterfaceView {
    private JPanel mainPanel;
    private JButton btn_create;
    private JButton btn_cancel;
    private JTextField tx_name;

    private JCheckBox ck_flyer;
    private JSlider sl_weight;
    private JComboBox cb_attack;
    private JLabel lb_warning;
    private DatePicker dp_date;
    private JComboBox cb_feeding;
    private JLabel lb_weighValue;

    public FormDialog(JFrame parent, Boolean modal) {
        super(parent, modal);
        initWindow();
        initComponents();
    }

    public void setWarningMessage(String warningMessage) {
        lb_warning.setText(warningMessage);
    }

    public void loadComboFeeadings(ArrayList<FeedingModel> feedingList){
        DefaultComboBoxModel<FeedingModel> model =
                new DefaultComboBoxModel();

        for (FeedingModel feedingModel : feedingList) {
            model.addElement(feedingModel);
        }
        cb_feeding.setModel(model);
    }


    private void loadComboAttacks(ArrayList<String> attacks) {
        DefaultComboBoxModel model =
                new DefaultComboBoxModel();
        for(String attack : attacks) {
            model.addElement(attack);
        }
        cb_attack.setModel(model);
    }

    @Override
    public void initComponents() {
        setWarningMessage("");
        lb_weighValue.setText("Selecciona un valor");
        ArrayList<String> attacks = new ArrayList<>();
        attacks.add("Mordisco");
        attacks.add("Silver");
        attacks.add("Gold");
        attacks.add("Diamond");
        attacks.add("Platinum");
        attacks.add("Quarta");
        loadComboAttacks(attacks);

        btn_create.setBorder(new RoundedBorder(50));
        btn_create.setForeground(Color.BLUE);

        btn_cancel.setBorder(new RoundedBorder(50));
        btn_cancel.setForeground(Color.RED);


    }

    public void addListener(ActionListener listener) {
        btn_cancel.addActionListener(listener);
        btn_create.addActionListener(listener);

    }

    public void addChangeListener(ChangeListener changeListener){
        sl_weight.addChangeListener(changeListener);
    }

    public void setWeigth(String weigth) {
        lb_weighValue.setText(weigth+" Toneladas");
    }

    @Override
    public void setCommands() {
        btn_cancel.setActionCommand(CLOSE_FORM_DIALOG);
        btn_create.setActionCommand(CREATE_DINO);
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
        FeedingModel feeding =
                (FeedingModel) cb_feeding.getSelectedItem();
        dino.setFeeding_id(feeding.getId());
        dino.setAttack((String) cb_attack.getSelectedItem());
        dino.setWeigth(sl_weight.getValue());
        dino.setFlying(ck_flyer.isSelected());
        dino.setDate(dp_date.getDate());
        return dino;
    }

    public void setDino(DinoModels dino){
        tx_name.setText(dino.getName());
        dp_date.setDate(dino.getDate());
        ck_flyer.setSelected(dino.isFlying());
        sl_weight.setValue((int)dino.getWeigth());
    }

    public void setMode(String mode){
        if(mode.equals(EDIT_MODE)){
            btn_create.setText("EDITAR");

        }else if(mode.equals(CREATE_MODE)){
            btn_create.setText("CREAR");
            btn_create.setActionCommand(CREATE_DINO);
        }
    }

}
