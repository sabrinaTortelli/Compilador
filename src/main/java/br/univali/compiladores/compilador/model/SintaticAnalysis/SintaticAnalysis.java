package br.univali.compiladores.compilador.model.SintaticAnalysis;

import br.univali.compiladores.compilador.model.parser.LParser;
import br.univali.compiladores.compilador.model.parser.ParseException;
import br.univali.compiladores.compilador.model.parser.TokenMgrError;
import br.univali.compiladores.compilador.model.recovery.ParseEOFException;
import br.univali.compiladores.compilador.model.recovery.RecoverySet;
import br.univali.compiladores.compilador.view.WindowER;

import java.io.BufferedReader;

public class SintaticAnalysis {

    private final WindowER gui;

    public SintaticAnalysis(WindowER gui){
        this.gui = gui;
        //gui.getTf().setText("");
    }

    public void printRecognized(String token, String msg, int kind, int line, int column) {
        gui.getTf().append("----------------------------------SINTÁTICO RECONHECIDO---------------------------------\n\n" +
                "Lexema: " + token + "\n" +
                "Linha: " + line + "\n" +
                "Coluna: " + column + "\n" +
                "Categoria do token: " + msg + "\n" +
                "Número da catergoria do token: " + kind + "\n\n");
    }

    public void printNotRecognized(RecoverySet g, ParseException e, String met) {
        gui.getTf().append("----------------------------------------ERRO SINTÁTICO----------------------------------\n\n" +
                "RecoverySet: " + g + "\n" +
                "ParseException: " + e.getMessage() + "\n" +
                "String: " + met + "\n\n");
    }




    public void runSintaticVerification(LParser parser) {
        try {
            parser.setSintaticAnalysis(this.gui);
            try {
                parser.parseSyntactical();
                if (parser.getSynErrorCount() > 0) {
                    gui.getTf().append("--------------------------------CONCLUSÃO--------------------------------\n\n" +
                            "Análise Sintática concluída. Erro(s) encontrados: " + parser.getSynErrorCount() + "\n");
                } else {
                    gui.getTf().append("--------------------------------CONCLUSÃO--------------------------------\n\n" +
                            "Análise Sintática concluída. Nenhum erro encontrado" + "\n");
                }

            } catch (ParseException ex) {
                System.out.println("Erro no parser");
                gui.getTf().append("----------------------------------ERRO NO PARSER-----------------------------\n\n" +
                        "Mensagem: " + ex.getMessage() + "\n");
            } catch (ParseEOFException e) {
                e.printStackTrace();
            }
        } catch (TokenMgrError ex) {
            gui.getTf().append("------------------------------------ERRO TokenMsgError--------------------------------\n\n" +
                    "Mensagem: " + ex.getMessage() + "\n");
        }
    }


}
