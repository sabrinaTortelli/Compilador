package br.univali.compiladores.compilador.view;

import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class WindowCO {

    private JPanel rootPanel;
    private JTable table;

    private ArrayList<HelpInstructionTable> instructionList;

    public WindowCO(ArrayList<HelpInstructionTable> instructionList) {
        this.instructionList = instructionList;
        createUIComponents();
        createTable();
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
        size.width = 600;
        size.height = 400;
        getRootPanel().setPreferredSize(size);
        getRootPanel().setSize(size);

    }
}
