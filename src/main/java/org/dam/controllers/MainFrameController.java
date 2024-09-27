package org.dam.controllers;

import org.dam.services.WindowsService;
import org.dam.views.FormDialog;
import org.dam.views.MainFrame;
import org.dam.views.QueriesDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController implements ActionListener {
    public static final String SHOW_FORM_DIALOG = "SHOW_FORM_DIALOG";
    public static final String SHOW_QUERIES_DIALOG = "SHOW_QUERIES_DIALOG";
    public static final String CLOSE_MAIN_FRAME = "CLOSE_MAIN_FRAME";

    private MainFrame mainFrame;
    private WindowsService windowsService;

    public MainFrameController(WindowsService windowsService) {
        this.windowsService = windowsService;
        this.mainFrame = (MainFrame) windowsService.getWindow("MainFrame");
    }

    private void handleShowFormDialog() {
        FormDialog formDialog =
                (FormDialog) windowsService.getWindow("FormDialog");
        formDialog.showWindow();
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

    private void handleShowQueriesDialog() {
        QueriesDialog queriesDialog = (QueriesDialog) windowsService.getWindow("QueriesDialog");
        queriesDialog.showWindow();
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
            case SHOW_QUERIES_DIALOG:
                handleShowQueriesDialog();
                break;
                default:
                    System.out.println("Unknown action command: " + command);
                    break;
        }
    }
}
