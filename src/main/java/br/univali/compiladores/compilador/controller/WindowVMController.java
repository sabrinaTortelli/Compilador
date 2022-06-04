package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.view.WindowVM;

import java.awt.*;

public class WindowVMController {

    private WindowVM window;

    public WindowVMController(){
        initComponents();
    }

    private void initComponents() {
        EventQueue.invokeLater(() -> {
            window = new WindowVM();
            window.setVisible(true);
        });
    }
}
