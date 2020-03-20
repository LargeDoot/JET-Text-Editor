package com.company.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*******************************************************************************
 * Copyright (c) Ethan Wilson 2020.
 *
 * @author Ethan Wilson
 * @since 13-02-2020
 ******************************************************************************/
public class helpListener implements ActionListener {

    /**
     * Display a new help dialog
     *
     * @param actionEvent event
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        JOptionPane.showMessageDialog(null, "This is where a help dialog would go!");

    }
}
