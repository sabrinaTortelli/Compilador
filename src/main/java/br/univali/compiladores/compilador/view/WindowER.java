package br.univali.compiladores.compilador.view;

import br.univali.compiladores.compilador.controller.WindowERController;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import java.awt.*;

/**
 * Classe de configurações da janela
 */
public class WindowER extends JFrame {

    private JButton analisar;
    private JButton limpar;
    private JTextArea tf;
    private JTextArea ta;
    private JLabel l1;
    private JLabel l2;
    private JScrollPane scroll1;
    private JScrollPane scroll2;
    private Color primaryColor;
    private Color secondColor;
    private Font font;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu compMenu;
    private JMenuItem newAction;
    private JMenuItem openAction;
    private JMenuItem saveAction;
    private JMenuItem saveAsAction;
    private JMenuItem exitAction;
    private JMenuItem cutAction;
    private JMenuItem copyAction;
    private JMenuItem pasteAction;
    private JMenuItem compAction;
    private JMenuItem runAction;
    private JToolBar toolBar;
    private ImageIcon hammerIcon;
    private ImageIcon playIcon;
    private JButton hammerButton;
    private JButton playButton;

    public WindowER() {
        super("Compilador");
        initComponents();
    }

    private void initComponents() {
        //Janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 770);

        //Cria barra de menu
        menuBar = new JMenuBar();

        //Define e adiciona tres menus drop down na barra de menus
        fileMenu = new JMenu("Arquivo");
        editMenu = new JMenu("Edição");
        compMenu = new JMenu("Compilação");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(compMenu);

        //Cria e adiciona um item simples para o menu
        newAction = new JMenuItem("Novo");
        openAction = new JMenuItem("Abrir");
        saveAction = new JMenuItem("Salvar");
        saveAsAction = new JMenuItem("Salvar como");
        exitAction = new JMenuItem("Sair");
        copyAction = new JMenuItem("Copiar");
        pasteAction = new JMenuItem("Colar");
        cutAction = new JMenuItem("Recortar");
        compAction = new JMenuItem("Compilar");
        runAction = new JMenuItem("Executar");

        fileMenu.add(newAction);
        fileMenu.add(openAction);
        fileMenu.add(saveAction);
        fileMenu.add(saveAsAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        editMenu.add(copyAction);
        editMenu.add(pasteAction);
        editMenu.add(cutAction);
        compMenu.add(compAction);
        compMenu.add(runAction);

        //Cria toolbar
        toolBar = new JToolBar();

        //Icons
        hammerIcon = new ImageIcon("src/resources/hammer.png");
        playIcon = new ImageIcon("src/resources/play.png");

        toolBar.setBounds(0,0, 800, (hammerIcon.getIconHeight()) + 20);

        //Cria botão
        hammerButton = new JButton(hammerIcon);
        playButton = new JButton(playIcon);

        toolBar.add(hammerButton);
        toolBar.add(playButton);

        //Label texto 1
        l1 = new JLabel("Área para edição:");
        l1.setBounds(19, 45, 150, 30);

        //Label texto 2
        l2 = new JLabel("Área para mensagens:");
        l2.setBounds(19, 360, 250, 30);

        //Área de texto 1
        ta = new JTextArea();


        //Área de texto 2
        tf = new JTextArea();
        tf.setLineWrap(true);
        tf.setEditable(false);

        //Scroll texto 1
        scroll1 = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll1.setBounds(18, 75, 750, 250);

        //Scroll texto 2
        scroll2 = new JScrollPane(tf, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll2.setBounds(18, 390, 750, 250);

        setJMenuBar(menuBar);
        add(toolBar, BorderLayout.NORTH);
        add(l1);
        add(l2);
        add(scroll1);
        add(scroll2);
        setLayout(null);

        JLabel teste = new JLabel("Linha: 1, Coluna: 1 ", JLabel.RIGHT);
        teste.setBounds(0,680, 120,15);
        add(teste, BorderLayout.SOUTH);

        ta.addCaretListener(
                new CaretListener() {
                    @Override
                    public void caretUpdate(CaretEvent e) {
                        int lineNumber = 0, column = 0, pos = 0;
                        try {
                            pos = ta.getCaretPosition();
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

//        //Cores
//        primaryColor = new Color(65,130,190);
//        secondColor = new Color(217,230,242);
//
//        //Fontes
//        font = new Font("SansSerif", 1, 15);
//
//        //Botão analisar
//        analisar = new JButton("Analisar");
//        analisar.setBounds(500, 310, 100, 40);
//        analisar.setFont(font);
//        analisar.setForeground(primaryColor);
//        analisar.setBackground(secondColor);
//        analisar.setBorder(javax.swing.BorderFactory.createLineBorder(primaryColor, 2));
//        analisar.setRequestFocusEnabled(false);
//        analisar.setRolloverEnabled(false);
//        analisar.setMargin(new Insets(2, 1000, 2, 14));
//
//        //Botão limpar
//        limpar = new JButton("Limpar");
//        limpar.setBounds(650, 310, 100, 40);
//        limpar.setFont(font);
//        limpar.setForeground(primaryColor);
//        limpar.setBackground(secondColor);
//        limpar.setBorder(javax.swing.BorderFactory.createLineBorder(primaryColor, 2));
//        limpar.setRequestFocusEnabled(false);
//        limpar.setRolloverEnabled(false);
//        limpar.setMargin(new Insets(2, 1000, 2, 14));
//
//        add(analisar);
//        add(limpar);
     }

//    public void addActions(WindowERController.Actions actions){
//        analisar.addActionListener(actions);
//        limpar.addActionListener(actions);
//    }

    public JTextArea getTf() {
        return tf;
    }

    public JTextArea getTa() {
        return ta;
    }

}