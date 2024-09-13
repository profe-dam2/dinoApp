package org.dam.controllers;

import org.dam.views.FormDialog;
import org.dam.views.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController implements ActionListener {
    public static final String SHOW_FORM_DIALOG = "SHOW_FORM_DIALOG";

    public static final String CLOSE_MAIN_FRAME = "CLOSE_MAIN_FRAME";

    private MainFrame mainFrame;
    public MainFrameController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    private void handleShowFormDialog() {
        FormDialog formDialog = new FormDialog(mainFrame,true);
        FormDialogController controller = new FormDialogController(formDialog);
        formDialog.addListener(controller);
        formDialog.addCommand();
        formDialog.initWindow();
    }

    private void handleCloseMainFrame() {
        int response = JOptionPane.showConfirmDialog(null,
                "¿Quieres salir?");
        if (response == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null,
                    "Adios");
            mainFrame.dispose();
        }else{
            JOptionPane.showMessageDialog(null,
                    "Gracias por quedarte");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case SHOW_FORM_DIALOG:
                handleShowFormDialog();
                break;
            case CLOSE_MAIN_FRAME:
                handleCloseMainFrame();
                break;

                default:
                    System.out.println("Unknown action command: " + command);
                    break;
        }
    }
}
