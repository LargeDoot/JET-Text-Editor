package com.company;

import com.company.listeners.editMenuListeners.editCopyListener;
import com.company.listeners.editMenuListeners.editPasteListener;
import com.company.listeners.fileMenuListeners.*;
import com.company.listeners.formatMenuListeners.formatFontListener;
import com.company.listeners.formatMenuListeners.formatWrapListener;
import com.company.listeners.viewMenuListeners.zoomMenuListeners.zoomInListener;
import com.company.listeners.viewMenuListeners.zoomMenuListeners.zoomOutListener;
import com.company.listeners.viewMenuListeners.zoomMenuListeners.zoomResetListener;
import com.company.listeners.windowListeners.textKeyListener;
import com.company.listeners.windowListeners.textMouseListener;
import com.company.listeners.windowListeners.windowCloseListener;
import com.company.preferences.TextPrefs;
import com.company.listeners.viewMenuListeners.viewToolbarToggleListener;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("DuplicatedCode")
public class EditorWindow extends JFrame {

    JPanel statusBar;

    private JScrollPane scrollPane;
    private JTextArea textArea;

    private JToolBar toolbar;

    private JMenuBar menuBar;

    private JLabel wordCount;
    private JLabel charCount;
    private JLabel lineNum;
    private JLabel lineColumn;
    private JLabel zoomLevelLabel;

    private JETFile currentFile;

    private TextPrefs windowPreferences;

    private Font windowFont;

    private int zoomLevel, fontSize;


    public EditorWindow() {

        super(" Jet Editor");

        addWindowListener(new windowCloseListener(this));

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
        ImageIcon jetIcon = new ImageIcon("src/com/company/resources/JETLogo.png");
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

        add(toolbar, BorderLayout.NORTH);

        //Add status bar to bottom of the window
        statusBar = new JPanel();
        statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.LINE_AXIS));

        wordCount = new JLabel(" Words: 0 \t ");
        charCount = new JLabel("Chars: 0 ");
        lineNum = new JLabel(" Ln: 0 \t ");
        lineColumn = new JLabel("Col: 0 ");
        zoomLevelLabel = new JLabel(" 100% ");
        JLabel charSet = new JLabel(" UTF-8 ");

        //Create separators for use in the bar
        JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
        JSeparator separator2 = new JSeparator(SwingConstants.VERTICAL);
        JSeparator separator3 = new JSeparator(SwingConstants.VERTICAL);
        JSeparator separator4 = new JSeparator(SwingConstants.VERTICAL);
        separator.setMaximumSize(new Dimension(0, 20));
        separator2.setMaximumSize(new Dimension(0, 20));
        separator3.setMaximumSize(new Dimension(0, 20));
        separator4.setMaximumSize(new Dimension(0, 20));


        statusBar.add(wordCount);
        statusBar.add(charCount);
        statusBar.add(separator4);
        statusBar.add(Box.createHorizontalGlue());
        statusBar.add(separator3);
        statusBar.add(lineNum);
        statusBar.add(lineColumn);
        statusBar.add(Box.createRigidArea(new Dimension(30, 0)));
        statusBar.add(separator);
        statusBar.add(zoomLevelLabel);
        statusBar.add(Box.createRigidArea(new Dimension(30, 0)));
        statusBar.add(separator2);
        statusBar.add(charSet);

        add(statusBar, BorderLayout.SOUTH);

        //Initialise the text area
        initTextArea();

        //Initialise the scroll area
        initScrollPane();

        add(scrollPane, BorderLayout.CENTER);

        //Set the close operation to dispose of the window when closed, allowing for multiple windows
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        // Align the formatting to the preferences
        setPrefFormatting();

        //Pack the window
        pack();

        //Make the window open in the center of the screen (not the top left)
        setLocationRelativeTo(null);
    }

    private void setPrefFormatting() {

        String fontName = windowPreferences.getFontName();
        int fontSize = windowPreferences.getFontSize();

        setWindowFont( new Font( fontName, Font.PLAIN, fontSize) );

    }

    private void initToolbar() {

        JButton toolbarOpenButton, toolbarSaveButton, toolbarExitButton;
        JButton toolbarZoomInButton, toolbarZoomOutButton;

        //Create icons for toolbar
        Icon openIcon, saveIcon, exitIcon, zoomInIcon, zoomOutIcon;
        openIcon = new ImageIcon("src/com/company/resources/OpenIcon40x40.png");
        saveIcon = new ImageIcon("src/com/company/resources/SaveIcon40x40.png");
        exitIcon = new ImageIcon("src/com/company/resources/ExitIcon40x40.png");
        zoomInIcon = new ImageIcon("src/com/company/resources/ZoomInIcon40x40.png");
        zoomOutIcon = new ImageIcon("src/com/company/resources/ZoomOutIcon40x40.png");

        //Setup toolbar and buttons for toolbar
        toolbarOpenButton = new JButton(openIcon);
        toolbarSaveButton = new JButton(saveIcon);
        toolbarExitButton = new JButton(exitIcon);
        toolbarZoomInButton = new JButton(zoomInIcon);
        toolbarZoomOutButton = new JButton(zoomOutIcon);

        toolbarOpenButton.addActionListener(new fileOpenListener(this));
        toolbarSaveButton.addActionListener(new fileSaveListener(this));
        toolbarExitButton.addActionListener(new fileExitListener(this));
        toolbarZoomInButton.addActionListener(new zoomInListener(this));
        toolbarZoomOutButton.addActionListener(new zoomOutListener(this));

        toolbar = new JToolBar();
        toolbar.add(toolbarOpenButton);
        toolbar.add(toolbarSaveButton);
        toolbar.add(toolbarZoomInButton);
        toolbar.add(toolbarZoomOutButton);
        toolbar.add(Box.createHorizontalGlue());
        toolbar.add(toolbarExitButton);
    }

    private void initMenuBar() {

        JMenu menuFile, menuEdit, menuFormat, menuView, menuHelp;
        JMenuItem fileNew, fileSave, fileSaveAs, fileOpen, fileExit;
        JMenuItem editCopy, editPaste, editFind, editReplace;
        JMenuItem formatFont;
        JCheckBoxMenuItem formatWordWrap, viewToolbarToggle;
        JMenu viewZoom;
        JMenuItem zoomIn, zoomOut, zoomReset;
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

        editCopy.addActionListener(new editCopyListener(this));
        editPaste.addActionListener(new editPasteListener(this));
//        editFind.addActionListener(new fileExitListener(this));
//        editFind.addActionListener(new fileExitListener(this));


        formatWordWrap = new JCheckBoxMenuItem("Word Wrap", true);
        formatFont = new JMenuItem("Font...");

        formatWordWrap.addItemListener(new formatWrapListener(this));
        formatFont.addActionListener(new formatFontListener(this));

        viewZoom = new JMenu("Zoom");
        zoomIn = new JMenuItem("Zoom In");
        zoomOut = new JMenuItem("Zoom Out");
        zoomReset = new JMenuItem("Reset to default");
        viewToolbarToggle = new JCheckBoxMenuItem("Show toolbar", true);

        //Add action listeners to the zoom buttons
        zoomIn.addActionListener(new zoomInListener(this));
        zoomOut.addActionListener(new zoomOutListener(this));
        zoomReset.addActionListener(new zoomResetListener(this));

        viewZoom.add(zoomIn);
        viewZoom.add(zoomOut);
        viewZoom.add(zoomReset);

        viewToolbarToggle.addItemListener(new viewToolbarToggleListener(this));

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
        menuView.add(viewToolbarToggle);

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

    private void initTextArea() {
        textArea = new JTextArea(50, 50);
        textArea.setLineWrap(true);
        textArea.addKeyListener(new textKeyListener(this));
        textArea.addMouseListener(new textMouseListener(this));
    }

    public String getText() {
        return textArea.getText();
    }

    public void setFile(JETFile file) {
        currentFile = file;
        textArea.setText(file.getTextContents());
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
        this.fontSize = windowFont.getSize() - zoomLevel;
    }

    public void setTextWrap(Boolean wrap) {

        this.textArea.setLineWrap(wrap);

    }

    public JToolBar getToolbar() {

        return toolbar;

    }

    public int getZoomLevel() {

        return zoomLevel;

    }

    public void setZoomLevel(int zoomLevel) {

        this.zoomLevel = zoomLevel;
        setWindowFont(new Font(windowFont.getFontName(), Font.PLAIN, (fontSize + this.zoomLevel)));

        //Set label to the correct zoom level
        String zoomLevelText = String.format(" %s%% ", (100 + zoomLevel));
        zoomLevelLabel.setText(zoomLevelText);

    }

    public JLabel getWordCount() {
        return wordCount;
    }

    public JLabel getLineColumn() {
        return lineColumn;
    }

    public JLabel getCharCount() {
        return charCount;
    }

    public JLabel getLineNum() {
        return lineNum;
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}
