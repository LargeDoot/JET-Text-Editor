package com.company.listeners.viewMenuListeners.zoomMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class zoomOutListener implements ActionListener {

    public zoomOutListener() {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        new EditorWindow().setVisible(true);

    }
}
