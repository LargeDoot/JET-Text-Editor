package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileNewListener implements ActionListener {

    /**
     * A method to open a new EditorWindow
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        //Simply opens a new Editor Window
        new EditorWindow().setVisible(true);

    }
}
