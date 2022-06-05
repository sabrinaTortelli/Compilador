package br.univali.compiladores.compilador.view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.JTextComponent;
import javax.swing.text.StyledDocument;
import javax.swing.text.Utilities;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class WindowCO extends JFrame {

    private JPanel jpanel;
    JTable table;
    JScrollPane scrollPane;

    Object [][] dados = {
            {" ", " ", " "},
            {" ", " ", " "},
            {" ", " ", " "}
    };

    String [] colunas = {"N° Instrução", "Código", "Parâmetro"};


    public WindowCO() {
        super("Código Objeto");
        initComponents();
    }

    private void initComponents() {
        setWindow();
        createEditionArea();
    }

    private void setWindow() {
        Dimension size = getPreferredSize();
        size.width = 500;
        size.height = 400;
        setPreferredSize(size);
        setSize(size);
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(1, 1));
        getContentPane().add(jpanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private void createEditionArea(){
        table = new JTable(dados, colunas);
        scrollPane = new JScrollPane(table);
        jpanel.add(scrollPane);
    }
}
