package org.dam.controllers;

import org.dam.dao.DinoDAO;
import org.dam.models.DinoModels;
import org.dam.services.WindowsService;
import org.dam.views.FormDialog;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FormDialogController implements ActionListener, ChangeListener {
    // COMANDOS
    public static final String CLOSE_FORM_DIALOG ="CLOSE_FORM_DIALOG";
    public static final String CREATE_DINO ="CREATE_DINO";

    private FormDialog formDialog;
    private WindowsService windowsService;
    private DinoDAO dinoDAO;

    public FormDialogController(WindowsService windowsService, DinoDAO dinoDAO) {
        this.windowsService = windowsService;
        this.dinoDAO = dinoDAO;
        this.formDialog =
                (FormDialog) windowsService.getWindow("FormDialog");
        handleGetFeedingList();
    }

    private void handleCloseFormDialog() {
        formDialog.closeWindow();
    }

    private void handleGetFeedingList() {
        try {
            formDialog.loadComboFeeadings(dinoDAO.getFeedings());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleCreateDino(){
        try {
            boolean okCreate = dinoDAO.createDino(formDialog.getDino());
            if (okCreate) {
                JOptionPane.showMessageDialog(null,
                        "DINO CREADO");
                formDialog.setDino(new DinoModels());
                return;
            }
        } catch (SQLException e) {
            formDialog.setWarningMessage(e.getMessage());
        }
        JOptionPane.showMessageDialog(null,
                "ERROR AL CREAR DINO");
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case CLOSE_FORM_DIALOG:
                handleCloseFormDialog();
                break;
            case CREATE_DINO:
                handleCreateDino();
                break;
                default:
                    System.out.println("Unknown action command: " + command);
                    break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider)e.getSource();
        int value = source.getValue();
        handleSetWeightValue(value);
    }

    private void handleSetWeightValue(int value) {
        formDialog.setWeigth(String.valueOf(value));
    }
}
