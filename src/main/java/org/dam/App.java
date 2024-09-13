package org.dam;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;
import org.dam.controllers.MainFrameController;
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
        FlatLightLaf.setup();

        MainFrame frame = new MainFrame();
        MainFrameController mainFrameController = new MainFrameController(frame);
        frame.addListener(mainFrameController);



    }
}
