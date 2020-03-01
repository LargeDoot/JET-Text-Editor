package com.company;

import com.company.fileMenuListeners.*;
import com.company.formatMenuListeners.formatFontListener;
import com.company.formatMenuListeners.formatWrapListener;
import com.company.preferences.TextPrefs;
import com.company.preferences.listeners.prefsBoldListener;
import javafx.scene.text.FontSmoothingType;

import javax.swing.*;
import java.awt.*;

public class EditorWindow extends JFrame {

    private ImageIcon jetIcon;

    JPanel statusBar;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    private JToolBar toolBar;

    private JMenuBar menuBar;

    private JLabel wordCount, lineColumn, zoomLevel, charSet;

    private String currentWorkingDirectory;

    private JETFile currentFile;

    private TextPrefs windowPreferences;

    private Font windowFont;

    public EditorWindow() {

        super("Jet Editor");

        //Add the system's look anf feel to make the window look normal in the current OS
        //Set the font size to the system's default "TextField" font size (otherwise it is very small on some screens)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            windowFont = UIManager.getFont("TextField.font");
            UIManager.getDefaults().put("TextArea.font", windowFont);
        } catch (Exception ignored) {
        }

        //Create objects for the file, prefs and style
        currentFile = new JETFile();
        windowPreferences = new TextPrefs(this);

        //Set the icon for the editor window
        jetIcon = new ImageIcon("images/JET Logo.png");
        setIconImage(jetIcon.getImage());

        //Set the layout to border layout
        BorderLayout layout = new BorderLayout(50, 0);
        setLayout(layout);

        //Initialize the menu bar
        initMenuBar();

        add(menuBar);
        setJMenuBar(menuBar);

        //Initialize the tool bar
        initToolbar();

        add(toolBar, BorderLayout.NORTH);

        //Add status bar to bottom of the window
        statusBar = new JPanel();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));

        wordCount = new JLabel(" Words: x \t Chars: x ");
        lineColumn = new JLabel(" Ln: x \t Col: x ");
        zoomLevel = new JLabel(" 100% ");
        charSet = new JLabel(" UTF-8 ");

        statusBar.add(wordCount);
        statusBar.add(Box.createHorizontalGlue());
        statusBar.add(lineColumn);
        statusBar.add(Box.createRigidArea(new Dimension(20, 0)));
        statusBar.add(zoomLevel);
        statusBar.add(Box.createRigidArea(new Dimension(20, 0)));
        statusBar.add(charSet);

        add(statusBar, BorderLayout.SOUTH);

        //Initialise the text area
        initTextArea();

        //Apply the font that is stored in the preferences file
        applyInitialFont();

        //Initialise the scroll area
        initScrollPane();

        //Add two empty JPanels to the east and west to provide padding to the edges of the TextArea todo
//        add(new JPanel(), BorderLayout.WEST);
//        add(new JPanel(), BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);

        //Set the close operation to dispose of the window when closed, allowing for multiple windows
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Align the formatting to the preferences
        setPrefFormatting();

        //Pack the window
        pack();

        //Make the window open in the center of the screen (not the top left)
        setLocationRelativeTo(null);
    }

    private void setPrefFormatting() {
        if (windowPreferences.isBold()) {

            textArea.setFont( new Font( windowFont.getFontName(), Font.BOLD, windowFont.getSize() ) );

        }
    }

    private void initToolbar() {

        JButton toolbarButton2, toolbarButton3;
        JToggleButton toolbarButton1;

        //Setup toolbar and buttons for toolbar
        toolbarButton1 = new JToggleButton("Button 1");
        toolbarButton2 = new JButton("Button 2");
        toolbarButton3 = new JButton("Button 3");

        toolbarButton1.addItemListener( new prefsBoldListener(this) );

        toolBar = new JToolBar();
        toolBar.add(toolbarButton1);
        toolBar.add(toolbarButton2);
        toolBar.add(toolbarButton3);
    }

    private void initMenuBar() {

        JMenu menuFile, menuEdit, menuFormat, menuView, menuHelp;
        JMenuItem fileNew, fileSave, fileSaveAs, fileOpen, fileExit;
        JMenuItem editCopy, editPaste, editFind, editReplace;
        JMenuItem formatFont;
        JCheckBoxMenuItem formatWordWrap;
        JMenuItem viewZoom;
        JMenuItem helpHelp;

        JSeparator fileSeparator1, editSeparator1;

        //Setup menus and menu buttons for a menubar
        menuBar = new JMenuBar();

        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");
        menuFormat = new JMenu("Format");
        menuView = new JMenu("View");
        menuHelp = new JMenu("Help");

        fileNew = new JMenuItem("New");
        fileSave = new JMenuItem("Save");
        fileSaveAs = new JMenuItem("Save As");
        fileOpen = new JMenuItem("Open");
        fileExit = new JMenuItem("Exit");

        fileNew.addActionListener(new fileNewListener());
        fileOpen.addActionListener(new fileOpenListener(this));
        fileSave.addActionListener(new fileSaveListener(this));
        fileSaveAs.addActionListener(new fileSaveAsListener(this));
        fileExit.addActionListener(new fileExitListener(this));

        editCopy = new JMenuItem("Copy");
        editPaste = new JMenuItem("Paste");
        editFind = new JMenuItem("Find");
        editReplace = new JMenuItem("Replace");

        formatWordWrap = new JCheckBoxMenuItem("Word Wrap", true);
        formatFont = new JMenuItem("Font...");

        formatWordWrap.addItemListener(new formatWrapListener(this));
        formatFont.addActionListener(new formatFontListener(this));

        viewZoom = new JMenuItem("Zoom");

        helpHelp = new JMenuItem("Help");

        fileSeparator1 = new JSeparator();
        editSeparator1 = new JSeparator();

        menuFile.add(fileNew);
        menuFile.add(fileSave);
        menuFile.add(fileSaveAs);
        menuFile.add(fileOpen);
        menuFile.add(fileSeparator1);
        menuFile.add(fileExit);

        menuEdit.add(editCopy);
        menuEdit.add(editPaste);
        menuEdit.add(editSeparator1);
        menuEdit.add(editFind);
        menuEdit.add(editReplace);

        menuFormat.add(formatWordWrap);
        menuFormat.add(formatFont);

        menuView.add(viewZoom);

        menuHelp.add(helpHelp);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);
        menuBar.add(menuFormat);
        menuBar.add(menuView);
        menuBar.add(menuHelp);

    }

    private void initScrollPane() {

        scrollPane = new JScrollPane(textArea);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setPreferredSize(new Dimension(1000, 550));

    }

    private void applyInitialFont() {

        String fontName = windowPreferences.getFontName();
        int fontSize = windowPreferences.getFontSize();

        textArea.setFont( new Font( fontName, Font.PLAIN, fontSize) );

    }

    private void initTextArea() {
        textArea = new JTextArea(50, 50);
        textArea.setLineWrap(true);
    }

    public String getText() {
        return textArea.getText();
    }

    public void setFile(JETFile file) {
        currentFile = file;
        textArea.setText(file.getTextContents());
    }

    public String getCurrentWorkingDirectory() {
        return currentWorkingDirectory;
    }

    public void setCurrentWorkingDirectory(String currentWorkingDirectory) {
        this.currentWorkingDirectory = currentWorkingDirectory;
    }

    public JETFile getCurrentFile() {
        return currentFile;
    }

    public TextPrefs getPrefs() {
        return windowPreferences;
    }

    public Font getWindowFont() {
        return windowFont;
    }

    public void setWindowFont(Font windowFont) {
        this.windowFont = windowFont;
        this.textArea.setFont(windowFont);
    }

    public void setTextWrap(Boolean wrap) {

        this.textArea.setLineWrap(wrap);

    }
}
