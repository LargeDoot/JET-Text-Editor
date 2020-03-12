package com.company.listeners.viewMenuListeners.zoomMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class zoomResetListener implements ActionListener {

    final EditorWindow window;

    public zoomResetListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        window.setZoomLevel(0);
        window.getPrefs().setZoomAmount(0);

    }
}
