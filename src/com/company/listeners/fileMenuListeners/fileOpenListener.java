package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.FileBrowser;
import com.company.ReaderWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileOpenListener implements ActionListener {

    final EditorWindow editorWindow;

    public fileOpenListener(EditorWindow editorWindow) {

        this.editorWindow = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String readLocation = new FileBrowser().getURI();
        new ReaderWriter(readLocation, editorWindow);

        if (readLocation != null) {
            editorWindow.setTitle(String.format(" JET \t | \t %s", readLocation));

        }

    }

}
