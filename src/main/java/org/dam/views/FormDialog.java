package org.dam.views;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.dam.controllers.FormDialogController.CLOSE_FORM_DIALOG;

public class FormDialog extends JDialog {
    private JPanel mainPanel;
    private JButton CREARButton;
    private JButton btn_cancel;
    private JTextField tx_name;
    private JTextField tx_feeding;
    private JSpinner sp_date;
    private JCheckBox ck_flyer;
    private JSlider sl_weight;
    private JComboBox cb_attack;
    private JLabel lb_warning;

    public FormDialog(JFrame parent, Boolean modal) {
        super(parent, modal);
    }

    public void addListener(ActionListener listener) {
        btn_cancel.addActionListener(listener);
    }

    public void addCommand(){
        btn_cancel.setActionCommand(CLOSE_FORM_DIALOG);
    }

    public void initWindow(){
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addCommand();
    }

}
