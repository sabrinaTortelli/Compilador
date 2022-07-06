package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;
import br.univali.compiladores.compilador.model.virtualMachine.VirtualMachine;
import br.univali.compiladores.compilador.view.WindowCO;
import br.univali.compiladores.compilador.view.WindowConsole;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WindowVMController {

    private WindowConsole window;
    private ArrayList<HelpInstructionTable> instructionList;

    public WindowVMController(ArrayList<HelpInstructionTable> instructionList){
        this.instructionList = instructionList;
        initComponents();
    }

    private void initComponents() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGui(instructionList);
            }
        });
    }

    private void createGui(ArrayList<HelpInstructionTable> instructionList){
        window = new WindowConsole(instructionList);
        JPanel root = window.getRootPanel();
        JFrame frame = new JFrame("Máquina Virtual");
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        VirtualMachine vm = new VirtualMachine(instructionList, window);
        vm.executeCode();
    }

}
