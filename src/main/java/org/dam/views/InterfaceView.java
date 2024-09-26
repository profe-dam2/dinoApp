package org.dam.views;

import java.awt.event.ActionListener;
import java.util.EventListener;

public interface InterfaceView {
    void initWindow();
    void showWindow();
    void closeWindow();
    void setCommands();
    void addListener(ActionListener listener);
    void initComponents();

}
