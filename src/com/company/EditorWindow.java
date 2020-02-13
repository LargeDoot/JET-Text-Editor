package com.company;

import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends JFrame {

    private final BorderLayout layout;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    private JToolBar toolBar;
    private JButton toolbarButton1, toolbarButton2, toolbarButton3;

    private JMenuBar menuBar;
    private JMenuItem menuFile, menuEdit, menuView, menuHelp;

    public EditorWindow() {

        super("Jet Editor");

        layout = new BorderLayout(50, 0);
        setLayout(layout);

        //Setup menubar and menu buttons for menubar
        menuFile = new JMenuItem("File");

        //Setup toolbar and buttons for toolbar
        toolbarButton1 = new JButton("Button 1");
        toolbarButton2 = new JButton("Button 2");
        toolbarButton3 = new JButton("Button 3");

        toolBar = new JToolBar();
        toolBar.add(toolbarButton1);
        toolBar.add(toolbarButton2);
        toolBar.add(toolbarButton3);

        add(toolBar, BorderLayout.NORTH);

        JButton button = new JButton("TestButton");
        add(button, layout.SOUTH);

        //Initialise the text area
        initTextArea();

        //Initialise the scroll area
        initScrollPane();

        //Add two empty JPanels to the east and west to provide padding to the edges of the TextArea
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.EAST);

        add(scrollPane, layout.CENTER);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) { }
        SwingUtilities.updateComponentTreeUI(this);


        pack();

    }

    private void initScrollPane() {

        scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setPreferredSize(new Dimension(800, 800));

    }

    private void initTextArea() {

        textArea = new JTextArea(50, 50);
        textArea.setLineWrap(true);
    }



}
