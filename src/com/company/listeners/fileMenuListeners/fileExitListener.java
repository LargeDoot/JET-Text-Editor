package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.SaveDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileExitListener implements ActionListener {

    final EditorWindow window;
    final boolean checkSave;

    public fileExitListener(EditorWindow editorWindow, boolean checkSave) {

        this.window = editorWindow;
        this.checkSave = checkSave;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String currentText = window.getText();
        String savedText = window.getCurrentFile().getTextContents();

        if (!currentText.equals(savedText) && checkSave) {

            new SaveDialog(window);

        } else {
            window.dispose();
        }

    }
}
