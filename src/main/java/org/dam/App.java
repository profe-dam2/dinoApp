package org.dam;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.dam.controllers.FormDialogController;
import org.dam.controllers.MainFrameController;
import org.dam.controllers.QueriesDialogController;
import org.dam.dao.DinoDAO;
import org.dam.services.WindowsService;
import org.dam.views.FormDialog;
import org.dam.views.MainFrame;
import org.dam.views.QueriesDialog;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //FlatDarkLaf.setup();
        //FlatDarculaLaf.setup();
        //FlatIntelliJLaf.setup();
        // FlatDarkLaf.setup();

        // TEMA DE LA APP
        FlatLightLaf.setup();

        // Servicio de ventanas
        WindowsService windowsService = new WindowsService();

        // Ventana principal
        MainFrame frame = new MainFrame();
        windowsService.registerWindow("MainFrame", frame);

        // Ventana formulario
        FormDialog formDialog = new FormDialog(frame, true);
        windowsService.registerWindow("FormDialog", formDialog);

        // Ventana consultas
        QueriesDialog queriesDialog = new QueriesDialog(frame, true);
        windowsService.registerWindow("QueriesDialog", queriesDialog);

        // DAOS
        DinoDAO dinoDAO = new DinoDAO();


        // Controladores
        MainFrameController mainFrameController =
                new MainFrameController(windowsService);

        FormDialogController formDialogController =
                new FormDialogController(windowsService, dinoDAO);

        QueriesDialogController queriesDialogController =
                new QueriesDialogController(windowsService, dinoDAO);

        // Listeners
        frame.addListener(mainFrameController);
        formDialog.addListener(formDialogController);
        formDialog.addChangeListener(formDialogController);
        queriesDialog.addListener(queriesDialogController);

        // MOSTRAR LA VENTANA PRINCIPAL
        frame.showWindow();

    }
}
