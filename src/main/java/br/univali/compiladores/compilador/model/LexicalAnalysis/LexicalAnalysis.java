package br.univali.compiladores.compilador.model.LexicalAnalysis;

import br.univali.compiladores.compilador.model.parser.LParser;
import br.univali.compiladores.compilador.model.parser.ParseException;
import br.univali.compiladores.compilador.model.parser.TokenMgrError;
import br.univali.compiladores.compilador.view.WindowER;

import java.awt.*;
import java.io.BufferedReader;

public class LexicalAnalysis {

    private WindowER gui;

    public LexicalAnalysis(WindowER gui){
        this.gui = gui;
        gui.getTf().setText("");
    }


    public void printRecognizedToken(String token, String msg, int kind, int line, int column) {
        gui.getTf().append("-----------------------------------------------------\n");
        gui.getTf().append("Lexema: " + token + "\n");
        gui.getTf().append("Linha: " + line + "\n");
        gui.getTf().append("Coluna: " + column + "\n");
        gui.getTf().append("Categoria do token: " + msg + "\n");
        gui.getTf().append("Número da catergoria do token: " + kind + "\n");
        gui.getTf().append("-----------------------------------------------------\n");
        gui.getTf().setForeground(Color.black);
    }

    public void printNotRecognizedToken(String token, String msg, int kind, int line, int column) {
        gui.getTf().append("---------------------ERRO----------------------------\n");
        gui.getTf().append("Lexema: " + token + "\n");
        gui.getTf().append("Linha: " + line + "\n");
        gui.getTf().append("Coluna: " + column + "\n");
        gui.getTf().append("Categoria do token: " + msg + "\n");
        gui.getTf().append("Número da catergoria do token: " + kind + "\n");
        gui.getTf().append("-----------------------------------------------------\n");
        gui.getTf().setForeground(Color.red);
    }


    public void runLexicalVerification(BufferedReader textToParser) {
        try {
            LParser parser = new LParser(textToParser);
            parser.setLexicalAnalysis(this.gui);

            try {

                parser.parseLexical();
                if (parser.getErrorCount() > 0) {
                    gui.getTf().append("Análise Léxica concluída. Erro(s) encontrados: " + parser.getErrorCount() + "\n");
                    //gui.getTf().setForeground(Color.red);
                } else {
                    gui.getTf().append("Análise Léxica concluída. Nenhum erro encontrado" + "\n");
                    //gui.getTf().setForeground(Color.green);
                }

            } catch (ParseException ex) {
                System.out.println("Erro no parser");
                gui.getTf().append("---------------------ERRO NO PARSER----------------------\n");
                gui.getTf().append("Mensagem: " + ex.getMessage() + "\n");
                gui.getTf().append("-----------------------------------------------------\n");
                gui.getTf().setForeground(Color.red);
            }
        } catch (TokenMgrError ex) {
            gui.getTf().append("---------------------ERRO NO TOKEN----------------------\n");
            gui.getTf().append("Mensagem: " + ex.getMessage() + "\n");
            gui.getTf().append("-----------------------------------------------------\n");
            gui.getTf().setForeground(Color.red);
            System.out.println("Erro no token: " + ex.getMessage());
        }
        System.out.println("Feito!");
    }
}
