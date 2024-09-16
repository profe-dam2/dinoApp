package org.dam.services;

import org.dam.views.InterfaceView;

import java.util.HashMap;
import java.util.Map;

public class WindowsService {
    private final Map<String, InterfaceView> windows;

    public WindowsService() {
        windows = new HashMap<String, InterfaceView>();
    }

    public void registerWindow(String name, InterfaceView window) {
        windows.put(name, window);
    }

    public InterfaceView getWindow(String name) {
        return windows.get(name);
    }
}
