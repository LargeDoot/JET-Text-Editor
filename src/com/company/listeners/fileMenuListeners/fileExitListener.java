package com.company.listeners.fileMenuListeners;

import com.company.EditorWindow;
import com.company.SaveDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class fileExitListener implements ActionListener {

    EditorWindow window;

    public fileExitListener(EditorWindow editorWindow) {

        this.window = editorWindow;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        String currentText = window.getText();
        String savedText = window.getCurrentFile().getTextContents();

        if (!currentText.equals(savedText)) {

            new SaveDialog(window);

        } else {
            window.dispose();
        }

    }
}
