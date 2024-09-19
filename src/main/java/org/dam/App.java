package org.dam;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.dam.controllers.FormDialogController;
import org.dam.controllers.MainFrameController;
import org.dam.dao.DinoDAO;
import org.dam.services.WindowsService;
import org.dam.views.FormDialog;
import org.dam.views.MainFrame;

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

        // DAOS
        DinoDAO dinoDAO = new DinoDAO();


        // Controladores
        MainFrameController mainFrameController = new MainFrameController(windowsService);
        FormDialogController formDialogController =
                new FormDialogController(windowsService, dinoDAO);

        // Listeners
        frame.addListener(mainFrameController);
        formDialog.addListener(formDialogController);

        frame.showWindow();

    }
}
