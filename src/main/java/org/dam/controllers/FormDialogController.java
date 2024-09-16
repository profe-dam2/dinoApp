package org.dam.controllers;

import org.dam.services.WindowsService;
import org.dam.views.FormDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormDialogController implements ActionListener {
    public static final String CLOSE_FORM_DIALOG ="CLOSE_FORM_DIALOG";
    private FormDialog formDialog;
    private WindowsService windowsService;

    public FormDialogController(WindowsService windowsService) {
        this.windowsService = windowsService;
        this.formDialog =
                (FormDialog) windowsService.getWindow("FormDialog");
    }
    private void handleCloseFormDialog() {
        formDialog.closeWindow();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case CLOSE_FORM_DIALOG:
                handleCloseFormDialog();
                break;
                default:
                    System.out.println("Unknown action command: " + command);
                    break;
        }
    }
}
