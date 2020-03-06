package com.company.listeners.viewMenuListeners.zoomMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class zoomInListener implements ActionListener {

    EditorWindow window;

    public zoomInListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        window.setZoomLevel(window.getZoomLevel() + 10);

    }
}
