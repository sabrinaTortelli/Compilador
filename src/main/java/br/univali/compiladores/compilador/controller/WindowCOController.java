package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;
import br.univali.compiladores.compilador.view.WindowCO;

import javax.swing.*;
import java.util.ArrayList;

public class WindowCOController {

    private WindowCO window;

    private JFrame frame;

    public WindowCOController(ArrayList<HelpInstructionTable> instructionList){
        initComponents(instructionList);
    }

    private void initComponents(ArrayList<HelpInstructionTable> instructionList) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGui(instructionList);
            }
        });
    }

    private void createGui(ArrayList<HelpInstructionTable> instructionList){
        window = new WindowCO(instructionList);
        JPanel root = window.getRootPanel();
        frame = new JFrame("Código Objeto");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
