package br.univali.compiladores.compilador.view;

import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

public class WindowConsole{

    private JPanel rootPanel;
    private JPanel panelTerminal;
    private JPanel panelCO;
    private JTable table;
    private JTextArea outputArea;
    private final ArrayList<HelpInstructionTable> instructionList;

    public WindowConsole(ArrayList<HelpInstructionTable> instructionList) {
        this.instructionList = instructionList;
        createUIComponents();
        createTable();
        createFunctionGui();
    }

    private void createFunctionGui() {
        outputArea.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
            }

            @Override
            public void focusGained(FocusEvent e) {
                outputArea.getCaret().setVisible(true);
            }
        });
        outputArea.getCaret().setVisible(true);
    }

    public JTable getTable() {
        return table;
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createTable() {
        Object[][] rec = new String[this.instructionList.size()][3];
        for(int i=0; i<this.instructionList.size(); i++){
            rec[i][0] = String.valueOf(this.instructionList.get(i).getInstruction());
            rec[i][1] = this.instructionList.get(i).getCode();
            rec[i][2] = String.valueOf(this.instructionList.get(i).getParameter());
        }
        String[] header = { "Instrução", "Código", "Parâmetro" };
        table.setModel(new DefaultTableModel(rec, header));
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        Dimension size = getRootPanel().getPreferredSize();
        size.width = 1200;
        size.height = 700;
        getRootPanel().setPreferredSize(size);
        getRootPanel().setSize(size);

        Dimension size1 = panelTerminal.getPreferredSize();
        size1.width = 900;
        size1.height = 700;
        panelTerminal.setPreferredSize(size1);
        panelTerminal.setSize(size1);

        Dimension size2 = panelCO.getPreferredSize();
        size2.width = 300;
        size2.height = 700;
        panelCO.setPreferredSize(size2);
        panelCO.setSize(size2);

        outputArea.setEditable(false);
        outputArea.setBackground(Color.BLACK);
        outputArea.setFont(new Font("Arial Black", Font.PLAIN, 14));
        outputArea.setForeground(Color.green);
        outputArea.setCaretColor(Color.lightGray);
        outputArea.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
    }

    public void print(String str) {
        outputArea.append(str);
        outputArea.setCaretPosition(outputArea.getText().length());
    }

    public void println(String str) {
        print(str + "\n");
    }

}
