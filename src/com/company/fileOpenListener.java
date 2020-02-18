package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileOpenListener extends JFrame implements ActionListener {

    EditorWindow editorWindow;

    public fileOpenListener(EditorWindow editorWindow) {

        this.editorWindow = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("File > Open");

        String readLocation = new FileBrowser().getURI();
        new ReaderWriter(readLocation, editorWindow);

        if (readLocation != null) {
            editorWindow.setCurrentWorkingDirectory(readLocation);
            editorWindow.setTitle(String.format("JET \t | \t %s", readLocation));

        }

    }

}
