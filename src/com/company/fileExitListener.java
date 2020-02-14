package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileExitListener extends JFrame implements ActionListener {
    public fileExitListener(EditorWindow editorWindow) {
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        System.exit(0);

    }
}
