package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.view.WindowER;

import javax.swing.*;
import java.awt.*;

/**
 * Classe de controle da janela
 */
public class WindowERController {
    private WindowER window;

    public WindowERController(){
        initComponents();
    }

    private void initComponents() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new WindowER();
                window.setVisible(true);
            }
        });
    }
}


