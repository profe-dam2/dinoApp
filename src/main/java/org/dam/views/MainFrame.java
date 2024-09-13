package org.dam.views;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.dam.controllers.MainFrameController.CLOSE_MAIN_FRAME;
import static org.dam.controllers.MainFrameController.SHOW_FORM_DIALOG;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JButton btn_create;
    private JButton btn_queries;
    private JButton btn_exit;

    public MainFrame() {
        initWindow();
    }

    public void addListener(ActionListener listener) {
        btn_create.addActionListener(listener);

        btn_exit.addActionListener(listener);
    }

    private void setCommands(){
        btn_create.setActionCommand(SHOW_FORM_DIALOG);

        btn_exit.setActionCommand(CLOSE_MAIN_FRAME);
    }

    private void initWindow() {
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ventana centrada
        setVisible(true);
        setCommands();
    }


}
