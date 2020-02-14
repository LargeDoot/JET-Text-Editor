package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileSaveAsListener extends JFrame implements ActionListener {

    EditorWindow editorWindow;

    public fileSaveAsListener(EditorWindow editorWindow) {

        this.editorWindow = editorWindow;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.out.println("File > Open");

        String text = editorWindow.getText();
        new ReaderWriter(new FileBrowser().getURI(), text);

    }
}
