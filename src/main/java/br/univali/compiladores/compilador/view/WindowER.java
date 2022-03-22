package br.univali.compiladores.compilador.view;

import br.univali.compiladores.compilador.controller.MenuController;
import br.univali.compiladores.compilador.controller.WindowERController;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe de configurações da janela
 */
public class WindowER extends JFrame implements ActionListener, DocumentListener {

    private JTextArea tf, ta;
    private JLabel teste;
    private JScrollPane scroll1, scroll2;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, compMenu;
    private JMenuItem newAction, openAction, saveAction, saveAsAction, exitAction, cutMenuItem, copyMenuItem, pasteMenuItem, compAction, runAction;
    private JToolBar toolBar;
    private ImageIcon newFileIcon, openFileIcon, saveFileIcon, cutIcon, copyIcon, pasteIcon, hammerIcon, playIcon;
    private JButton newFileButton, openFileButton, saveFileButton, cutButton, copyButton, pasteButton, compileButton, executeButton;
    private final MenuController menuController = new MenuController(this);
    private JPanel jpanel;
    private boolean changedDocument;

    public WindowER() {
        super("Compilador");
        initComponents();
        changedDocument = false;
    }

    private void setWindow() {
        Dimension size = getPreferredSize();
        size.width = 800;
        size.height = 770;
        setPreferredSize(size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Dispose_on_close
        setSize(800, 770);
        jpanel = new JPanel();
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
        jpanel.setBorder(BorderFactory.createEmptyBorder(5,2,10,2));
        setContentPane(jpanel);
    }

    private void createMenuBar(){
        //Cria barra de menu
        menuBar = new JMenuBar();
        //Define e adiciona tres menus drop down na barra de menus
        fileMenu = new JMenu("Arquivo");
        editMenu = new JMenu("Edição");
        compMenu = new JMenu("Compilação");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(compMenu);

        setJMenuBar(menuBar);
    }

    private void createFileMenu(){
        //Cria e adiciona um item simples para o menu
        newAction = new JMenuItem("Novo");
        openAction = new JMenuItem("Abrir");
        saveAction = new JMenuItem("Salvar");
        saveAsAction = new JMenuItem("Salvar como");
        exitAction = new JMenuItem("Sair");
        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
    }

    private void createEditMenu(){
        cutMenuItem = new JMenuItem("Recortar");
        copyMenuItem = new JMenuItem("Copiar");
        pasteMenuItem = new JMenuItem("Colar");

        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

    }

    private void createCompilateMenu(){
        compAction = new JMenuItem("Compilar");
        runAction = new JMenuItem("Executar");

        compMenu.add(compAction);
        compMenu.add(runAction);
    }

    private void createIconsToolBar(){
        //Icons
        newFileIcon = new ImageIcon("src/resources/newFile.png");
        openFileIcon = new ImageIcon("src/resources/openFile.png");
        saveFileIcon = new ImageIcon("src/resources/saveFile.png");
        cutIcon = new ImageIcon("src/resources/cut.png");
        copyIcon = new ImageIcon("src/resources/copy.png");
        pasteIcon = new ImageIcon("src/resources/paste.png");
        hammerIcon = new ImageIcon("src/resources/hammer.png");
        playIcon = new ImageIcon("src/resources/play.png");
    }

    private void createToolBar(){
        //Cria toolbar
        toolBar = new JToolBar();
        toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpanel.add(toolBar);
        jpanel.add(Box.createVerticalStrut(5));
    }

    private void createFileToolBar(){
        //Botão Novo
        newFileButton = new JButton(newFileIcon);
        newFileButton.setToolTipText("Novo");
        //Botão Abrir
        openFileButton = new JButton(openFileIcon);
        openFileButton.setToolTipText("Abrir");
        //Botão Salvar
        saveFileButton = new JButton(saveFileIcon);
        saveFileButton.setToolTipText("Salvar");

        toolBar.add(newFileButton);
        toolBar.add(openFileButton);
        toolBar.add(saveFileButton);
        toolBar.addSeparator();
    }

    private void createEditToolBar(){
        //Botão Recortar
        cutButton = new JButton(cutIcon);
        cutButton.setToolTipText("Recortar");

        //Botão Copiar
        copyButton = new JButton(copyIcon);
        copyButton.setToolTipText("Copiar");

        //Botão Colar
        pasteButton = new JButton(pasteIcon);
        pasteButton.setToolTipText("Colar");

        toolBar.add(cutButton);
        toolBar.add(copyButton);
        toolBar.add(pasteButton);
        toolBar.addSeparator();

    }

    private void createCompileToolBar(){
        //botão Compilar
        compileButton = new JButton(hammerIcon);
        compileButton.setToolTipText("Compilar");
        //Botão Executar
        executeButton = new JButton(playIcon);
        executeButton.setToolTipText("Executar");

        toolBar.add(compileButton);
        toolBar.add(executeButton);
    }

    private void createEditionArea(){
        //Área de texto 1
        ta = new JTextArea();
        ta.getDocument().addDocumentListener(this);
        //Scroll texto 1
        scroll1 = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setPreferredSize(new Dimension(750, 350));
        scroll1.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Área para edição"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                        scroll1.getBorder()));
        scroll1.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpanel.add(scroll1);
        jpanel.add(Box.createVerticalStrut(5));
    }

    private void createMessagesArea(){
        //Área de texto 2
        tf = new JTextArea();
        tf.setLineWrap(true);
        tf.setEditable(false);
        //Scroll texto 2
        scroll2 = new JScrollPane(tf,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll2.setPreferredSize(new Dimension(750, 200));
        scroll2.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Área para mensagens:"),
                                BorderFactory.createEmptyBorder(5,5,5,5)),
                        scroll2.getBorder()));
        scroll2.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpanel.add(scroll2);
        jpanel.add(Box.createVerticalStrut(10));
    }

    private void createInfosText(){
        teste = new JLabel("Linha: 1, Coluna: 1 ", JLabel.LEFT);
        teste.setAlignmentX(Component.LEFT_ALIGNMENT);
        jpanel.add(teste);

        ta.addCaretListener(
                new CaretListener() {
                    @Override
                    public void caretUpdate(CaretEvent e) {
                        int lineNumber = 0, column = 0, pos = 0;
                        try {

                            pos = ta.getCaretPosition();
                            System.out.println(pos);
                            lineNumber = ta.getLineOfOffset(pos);
                            column = pos - ta.getLineOfOffset(lineNumber);
                        } catch (Exception e1) {}
                        if (ta.getText().length()==0) {
                            lineNumber = 0;
                            column = 0;
                        }
                        teste.setText("Linha: " + (lineNumber+1) + ", Coluna: " + (column+1));
                    }
                }
        );
    }

    private void initComponents() {
        setWindow();
        createMenuBar();
        createFileMenu();
        createEditMenu();
        createCompilateMenu();
        createIconsToolBar();
        createToolBar();
        createFileToolBar();
        createEditToolBar();
        createCompileToolBar();
        createEditionArea();
        createMessagesArea();
        createInfosText();
        addActions();
     }

    public void addActions(){
        newAction.addActionListener(this);
        newAction.setActionCommand("New");

        newFileButton.addActionListener(this);
        newFileButton.setActionCommand("New");

        openAction.addActionListener(this);
        openAction.setActionCommand("Open");

        openFileButton.addActionListener(this);
        openFileButton.setActionCommand("Open");

        saveAction.addActionListener(this);
        saveAction.setActionCommand("Save");

        saveAsAction.addActionListener(this);
        saveAsAction.setActionCommand("SaveAs");

        saveFileButton.addActionListener(this);
        saveFileButton.setActionCommand("Save");

        exitAction.addActionListener(this);
        exitAction.setActionCommand("Exit");
        cutMenuItem.addActionListener(this);
        cutMenuItem.setActionCommand("Cut");
        cutButton.addActionListener(this);
        cutButton.setActionCommand("Cut");

        pasteMenuItem.addActionListener(this);
        pasteMenuItem.setActionCommand("Paste");
        pasteButton.addActionListener(this);
        pasteButton.setActionCommand("Paste");

        copyMenuItem.addActionListener(this);
        copyMenuItem.setActionCommand("Copy");
        copyButton.addActionListener(this);
        copyButton.setActionCommand("Copy");

        compileButton.addActionListener(this);
        compileButton.setActionCommand("Compile");
        compAction.addActionListener(this);
        compAction.setActionCommand("Compile");

        executeButton.addActionListener(this);
        executeButton.setActionCommand("Execute");
        runAction.addActionListener(this);
        runAction.setActionCommand("Execute");

    }

    public JTextArea getTf() {
        return tf;
    }

    public JTextArea getTa() {
        return ta;
    }

    public void setChangedDocument(boolean changedDocument) {
        this.changedDocument = changedDocument;
    }

    public boolean isChangedDocument() {
        return changedDocument;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "New":
            case "Open":
            case "Exit":
                menuController.verifyEdition(command);
                break;
            case "SaveAs":
            case "Save":
                menuController.save();
                break;
            case "Cut":
                menuController.cut(e);
                break;
            case "Copy":
                menuController.copy(e);
                break;
            case "Paste":
                menuController.paste(e);
                break;
            case "Compile":
                menuController.compile();
                break;
            default:
                System.out.println(command);
                break;
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        changedDocument = true;
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        changedDocument = true;
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        changedDocument = true;
    }
}