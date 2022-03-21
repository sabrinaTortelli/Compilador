package br.univali.compiladores.compilador.view;

import br.univali.compiladores.compilador.controller.MenuController;
import br.univali.compiladores.compilador.controller.WindowERController;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe de configurações da janela
 */
public class WindowER extends JFrame implements ActionListener {

    private JTextArea tf, ta;
    private JLabel l1, l2, teste;
    private JScrollPane scroll1, scroll2;
    private JMenuBar menuBar;
    private JMenu fileMenu, editMenu, compMenu;
    private JMenuItem newAction, openAction, saveAction, saveAsAction, exitAction, cutMenuItem, copyMenuItem, pasteMenuItem, compAction, runAction;
    private JToolBar toolBar;
    private ImageIcon newFileIcon, openFileIcon, saveFileIcon, cutIcon, copyIcon, pasteIcon, hammerIcon, playIcon;
    private JButton newFileButton, openFileButton, saveFileButton, cutButton, copyButton, pasteButton, compileButton, executeButton;
    private MenuController menuController = new MenuController(this);

    public WindowER() {
        super("Compilador - Novo arquivo");
        initComponents();
    }

    private void setWindow() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 770);
        setLayout(null);
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
        copyMenuItem = new JMenuItem("Copiar");
        pasteMenuItem = new JMenuItem("Colar");
        cutMenuItem = new JMenuItem("Recortar");

        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(cutMenuItem);
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
        toolBar.setBounds(0,0, 800, (hammerIcon.getIconHeight()) + 20);
        add(toolBar, BorderLayout.NORTH);
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
        //Label texto 1
        l1 = new JLabel("Área para edição:");
        l1.setBounds(19, 45, 150, 30);
        //Área de texto 1
        ta = new JTextArea();
        //Scroll texto 1
        scroll1 = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll1.setBounds(18, 75, 750, 250);

        add(l1);
        add(scroll1);
    }

    private void createMessagesArea(){
        //Label texto 2
        l2 = new JLabel("Área para mensagens:");
        l2.setBounds(19, 360, 250, 30);
        //Área de texto 2
        tf = new JTextArea();
        tf.setLineWrap(true);
        tf.setEditable(false);
        //Scroll texto 2
        scroll2 = new JScrollPane(tf,  JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll2.setBounds(18, 390, 750, 250);

        add(l2);
        add(scroll2);
    }

    private void createInfosText(){
        teste = new JLabel("Linha: 1, Coluna: 1 ", JLabel.RIGHT);
        teste.setBounds(0,680, 120,15);
        add(teste, BorderLayout.SOUTH);

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
        saveFileButton.setActionCommand("SaveAs");

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command){
            case "New":
                menuController.newFile();
                break;
            case "Open":
                menuController.openFile();
                break;
            case "SaveAs":
                menuController.saveAs();
                break;
            case "Save":
                menuController.save();
                break;
            case "Exit":
                menuController.exit();
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
}