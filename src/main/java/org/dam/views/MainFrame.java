package org.dam.views;

import javax.swing.*;
import java.awt.event.ActionListener;

import static org.dam.controllers.MainFrameController.CLOSE_MAIN_FRAME;
import static org.dam.controllers.MainFrameController.SHOW_FORM_DIALOG;

public class MainFrame extends JFrame implements InterfaceView {
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

    @Override
    public void initComponents() {

    }

    public void setCommands(){
        btn_create.setActionCommand(SHOW_FORM_DIALOG);

        btn_exit.setActionCommand(CLOSE_MAIN_FRAME);
    }

    public void initWindow() {
        setContentPane(mainPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ventana centrada
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


}
