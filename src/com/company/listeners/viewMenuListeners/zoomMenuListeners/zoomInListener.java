package com.company.listeners.viewMenuListeners.zoomMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class zoomInListener implements ActionListener {

    public zoomInListener() {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        new EditorWindow().setVisible(true);

    }
}
