package com.company.fileMenuListeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileExitListener extends JFrame implements ActionListener {

    EditorWindow window;

    public fileExitListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        window.dispose();

    }
}
