package br.univali.compiladores.compilador.view;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

public class WindowVM extends JFrame {

    private JTextPane textPane;
    private JPanel jpanel;
    private StyledDocument doc;

    public WindowVM() {
        super("Virtual Machine");
        initComponents();
    }

    private void initComponents() {
        setWindow();
        createEditionArea();
    }

    private void setWindow() {
        Dimension size = getPreferredSize();
        size.width = 1000;
        size.height = 700;
        setPreferredSize(size);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(size);
        jpanel = new JPanel();
        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
        jpanel.setBorder(BorderFactory.createEmptyBorder(5,2,10,2));
        setContentPane(jpanel);
    }

    private void createEditionArea(){
        textPane = new JTextPane();
        textPane.setForeground(Color.WHITE);
        textPane.setBackground(Color.BLACK);
        textPane.setEditable(false);
        textPane.setFont(textPane.getFont().deriveFont(16.0f));
        jpanel.add(textPane, BorderLayout.CENTER);
        doc = textPane.getStyledDocument();
        textPane.setText("Profº Alessandro nos dê nota 10 ❤💻");
    }

    public void printText(String text){
        textPane.setText(text);
    }

    public void setEditable(boolean status){
        textPane.setEditable(status);
    }

    public JTextPane getTextPane() {
        return textPane;
    }
}
