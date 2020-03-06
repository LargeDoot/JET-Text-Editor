package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileExitListener implements ActionListener {

    EditorWindow window;

    public fileExitListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        window.dispose();

    }
}
