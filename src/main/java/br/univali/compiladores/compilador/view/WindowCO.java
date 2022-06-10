package br.univali.compiladores.compilador.view;

import javax.swing.*;
import javax.swing.border.Border;
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
import java.util.ArrayList;

public class WindowCO extends JFrame {

    private JPanel jpanel;
    private JTextArea tf, cn;

    public WindowCO() {
        super("Código Objeto");
        initComponents();
    }

    private void initComponents() {
        setWindow();
    }

    private void setWindow() {
        Dimension size = getPreferredSize();
        size.width = 500;
        size.height = 400;
        setPreferredSize(size);
        setSize(size);
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(5,3,5,5));
        for (int i = 1; i <= 15; i++) {
            jpanel.add(createMessagesArea());
        }
        getContentPane().add(jpanel);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    private JTextArea createMessagesArea(){
        //Área de texto 2
        tf = new JTextArea();
        tf.setLineWrap(true);
        tf.setEditable(false);
        tf.setForeground(Color.BLACK);
        Border border = BorderFactory.createTitledBorder("Teste");
        tf.setBorder(border);
        return tf;
    }
}
