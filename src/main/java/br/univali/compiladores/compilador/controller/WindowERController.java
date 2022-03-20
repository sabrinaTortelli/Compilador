package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.model.SentenceAnalyser;
import br.univali.compiladores.compilador.view.WindowER;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classe de controle da janela
 */
public class WindowERController {
    WindowER window;
    String sSample;
    SentenceAnalyser sa;

    public WindowERController(){
        initComponents();
    }

    private void initComponents() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                window = new WindowER();
                //window.addActions(new Actions());
                window.setVisible(true);
            }
        });
    }

    /**
     * Classe que implementa as funções dos botões das janelas
     */
    public class Actions implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Analisar")) {
                sa =  new SentenceAnalyser();
                sSample = window.getTa().getText();
                sa.tokenChecker(sSample);
                String respUser = sa.getRespUser();
                window.getTf().setText(respUser);
            } else if (e.getActionCommand().equals("Limpar")) {
                window.getTf().setText("");
                window.getTa().setText("");
            }
        }
    }

}


