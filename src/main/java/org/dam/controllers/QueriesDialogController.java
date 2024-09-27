package org.dam.controllers;

import org.dam.dao.DinoDAO;
import org.dam.models.DinoModels;
import org.dam.services.WindowsService;
import org.dam.views.FormDialog;
import org.dam.views.QueriesDialog;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.dam.controllers.FormDialogController.EDIT_MODE;

public class QueriesDialogController implements ActionListener,
        WindowListener, ItemListener, MouseListener {

    //public static final String
    private WindowsService windowsService;
    private QueriesDialog queriesDialog;
    private DinoDAO dinoDAO;

    // COMANDOS
    public static final String GET_DINO_BY_ID = "GET_DINO_BY_ID";
    public static final String GET_DINOS_BY_NAME_AND_FLY = "GET_DINOS_BY_NAME_AND_FLY";
    public static final String GET_DINOS_BY_DATES = "GET_DINOS_BY_DATES";
    public static final String CLEAN_DIALOG = "CLEAN_DIALOG";


    public QueriesDialogController(WindowsService windowsService, DinoDAO dinoDAO) {
        this.windowsService = windowsService;
        this.dinoDAO = dinoDAO;
        this.queriesDialog = (QueriesDialog) windowsService.getWindow("QueriesDialog");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case GET_DINO_BY_ID:
                handleGetDinoByID();
                break;
            case GET_DINOS_BY_NAME_AND_FLY:
                handleGetDinosByNameAndFly();
                break;
            case GET_DINOS_BY_DATES:
                handleGetDinosByDates();
                break;
            case CLEAN_DIALOG:
                    handleCleanDialog();
                    handleGetFeedingList();
                    break;
                default:
                    break;

        }

    }

    private void handleCleanDialog() {
        queriesDialog.initComponents();

    }

    private void handleGetFeedingList() {
        try {
            queriesDialog.loadComboFeeadings(dinoDAO.getFeedings());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    private void handleShowDinos(){
        try {
            ArrayList<DinoModels> dinoList = dinoDAO.getDinos();
            queriesDialog.loadDinoTable(dinoList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleGetDinosByDates(){
        try {
            ArrayList<DinoModels> dinoList = dinoDAO.getDinoByDates(queriesDialog.getDate1(),
                    queriesDialog.getDate2());
            queriesDialog.loadDinoTable(dinoList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleGetDinoByID(){

        try {
            ArrayList<DinoModels> dinoList = dinoDAO.getDinoByID(queriesDialog.getDinoID());
            queriesDialog.loadDinoTable(dinoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void handleGetDinosByNameAndFly(){
        try {
            ArrayList<DinoModels> dinoList = dinoDAO.getDinoByNameAndFly(
                    queriesDialog.getDinoName(), queriesDialog.getFly()
            );
            queriesDialog.loadDinoTable(dinoList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {
        handleShowDinos();
        handleGetFeedingList();
        queriesDialog.initComponents();
    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        queriesDialog.removeFirtsComboItem();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            int dinoID = queriesDialog.getSelectedDinoID();
            System.out.println("LA ID SELECCIONADA ES: " +dinoID);
            try {
                ArrayList<DinoModels> dinoList = dinoDAO.getDinoByID(dinoID);
                DinoModels dino = dinoList.get(0);
                FormDialog formDialog = (FormDialog) windowsService.getWindow("FormDialog");
                formDialog.setMode(EDIT_MODE);
                formDialog.setDino(dino);
                formDialog.showWindow();


            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
