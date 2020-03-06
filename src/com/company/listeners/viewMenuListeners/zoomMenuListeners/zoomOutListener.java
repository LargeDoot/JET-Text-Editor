package com.company.listeners.viewMenuListeners.zoomMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class zoomOutListener implements ActionListener {

    EditorWindow window;

    public zoomOutListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println(window.getZoomLevel() - 8);
        window.setZoomLevel(window.getZoomLevel() - 8);

    }
}
