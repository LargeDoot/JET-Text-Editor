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

        new ReaderWriter(new FileBrowser().getURI(), editorWindow);


    }

}
