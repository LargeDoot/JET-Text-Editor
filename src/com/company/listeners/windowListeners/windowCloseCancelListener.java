package com.company.listeners.windowListeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class windowCloseCancelListener implements ActionListener {

    final JDialog dialog;

    /**
     * Constructor to create the listener.
     *
     * @param dialog the parent dialog
     */
    public windowCloseCancelListener(JDialog dialog) {

        this.dialog = dialog;

    }

    /**
     * Dispose of the dialog
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        dialog.dispose();

    }
}
