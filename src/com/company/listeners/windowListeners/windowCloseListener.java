package com.company.listeners.windowListeners;

import com.company.EditorWindow;
import com.company.SaveDialog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class windowCloseListener implements WindowListener {

    EditorWindow window;

    public windowCloseListener(EditorWindow window) {

        this.window = window;

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        String currentText = window.getText();
        String savedText = window.getCurrentFile().getTextContents();

        if (!currentText.equals(savedText)) {

            System.out.println("Not equal contents");

            new SaveDialog(window);

        } else {
            window.dispose();
        }

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
