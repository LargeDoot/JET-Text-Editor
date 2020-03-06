package com.company.listeners.windowCloseListeners;

import com.company.EditorWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class windowCloseCancelListener implements ActionListener {

    JDialog dialog;

    public windowCloseCancelListener(JDialog dialog) {

        this.dialog = dialog;

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        dialog.dispose();

    }
}
