//package br.univali.compiladores.compilador.controller;
//
//import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;
//import br.univali.compiladores.compilador.model.virtualMachine.VirtualMachine;
//import br.univali.compiladores.compilador.view.WindowVM;
//
//import java.awt.*;
//import java.util.ArrayList;
//
//public class WindowVMController {
//
//    private WindowVM window;
//    private ArrayList<HelpInstructionTable> instructionList;
//
//    public WindowVMController(ArrayList<HelpInstructionTable> instructionList){
//        this.instructionList = instructionList;
//        initComponents();
//    }
//
//    private void initComponents() {
//        EventQueue.invokeLater(() -> {
//            window = new WindowVM();
//            window.setVisible(true);
//            window.printText("Executando programa...");
//            VirtualMachine vm = new VirtualMachine(instructionList, window);
//            vm.executeCode();
//        });
//    }
//}
