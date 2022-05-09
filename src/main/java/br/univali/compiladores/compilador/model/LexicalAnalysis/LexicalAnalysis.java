package br.univali.compiladores.compilador.model.LexicalAnalysis;

import br.univali.compiladores.compilador.model.parser.LParser;
import br.univali.compiladores.compilador.model.parser.ParseException;
import br.univali.compiladores.compilador.model.parser.TokenMgrError;
import br.univali.compiladores.compilador.view.WindowER;

import java.io.BufferedReader;

public class LexicalAnalysis {

    private final WindowER gui;

    public LexicalAnalysis(WindowER gui){
        this.gui = gui;
        gui.getTf().setText("");
    }


    public void printRecognizedToken(String token, String msg, int kind, int line, int column) {
        gui.getTf().append("----------------------------------LEXEMA RECONHECIDO---------------------------------\n\n" +
                "Lexema: " + token + "\n" +
                "Linha: " + line + "\n" +
                "Coluna: " + column + "\n" +
                "Categoria do token: " + msg + "\n" +
                "Número da catergoria do token: " + kind + "\n\n");
    }

    public void printNotRecognizedToken(String token, String msg, int kind, int line, int column) {
        gui.getTf().append("----------------------------------------ERRO-----------------------------------------\n\n" +
                "Lexema: " + token + "\n" +
                "Linha: " + line + "\n" +
                "Coluna: " + column + "\n" +
                "Categoria do token: " + msg + "\n" +
                "Número da catergoria do token: " + kind + "\n\n");
    }


    public LParser runLexicalVerification(BufferedReader textToParser) {
        LParser parser = new LParser(textToParser);
        try {
            parser.setLexicalAnalysis(this.gui);
            try {
                parser.parseLexical();
                if (parser.getLexErrorCount() > 0) {
                    gui.getTf().append("--------------------------------CONCLUSÃO--------------------------------\n\n" +
                            "Análise Léxica concluída. Erro(s) encontrados: " + parser.getLexErrorCount() + "\n");
                } else {
                    gui.getTf().append("--------------------------------CONCLUSÃO--------------------------------\n\n" +
                            "Análise Léxica concluída. Nenhum erro encontrado" + "\n");
                }

            } catch (ParseException ex) {
                System.out.println("Erro no parser");
                gui.getTf().append("----------------------------------ERRO NO PARSER-----------------------------\n\n" +
                        "Mensagem: " + ex.getMessage() + "\n");
            }
        } catch (TokenMgrError ex) {
            gui.getTf().append("------------------------------------ERRO NO TOKEN--------------------------------\n\n" +
                    "Mensagem: " + ex.getMessage() + "\n");
        }
        return parser;
    }
}
