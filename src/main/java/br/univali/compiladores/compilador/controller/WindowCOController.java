package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.view.WindowCO;

import java.awt.*;

public class WindowCOController {

    private WindowCO window;

    public WindowCOController(){
        initComponents();
    }

    private void initComponents() {
        EventQueue.invokeLater(() -> {
            window = new WindowCO();
            window.setVisible(true);
        });
    }
}
