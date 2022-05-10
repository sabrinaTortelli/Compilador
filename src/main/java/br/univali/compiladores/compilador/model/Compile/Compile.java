package br.univali.compiladores.compilador.model.Compile;

import br.univali.compiladores.compilador.model.parser.LParser;
import br.univali.compiladores.compilador.model.parser.ParseException;
import br.univali.compiladores.compilador.model.parser.TokenMgrError;
import br.univali.compiladores.compilador.model.recovery.ParseEOFException;
import br.univali.compiladores.compilador.model.recovery.RecoverySet;
import br.univali.compiladores.compilador.view.WindowER;
import org.apache.commons.io.input.ReaderInputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;

public class Compile {

    private final WindowER gui;

    public Compile(WindowER gui){
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
        gui.getTf().append("------------------------------------ERRO LÉXICO--------------------------------------\n\n" +
                "Lexema: " + token + "\n" +
                "Linha: " + line + "\n" +
                "Coluna: " + column + "\n" +
                "Categoria do token: " + msg + "\n" +
                "Número da catergoria do token: " + kind + "\n\n");
    }


    public void runLexicalVerification(String text) {
        try {
            StringReader textReader = new StringReader(text);
            BufferedReader textToParser = new BufferedReader(new InputStreamReader(new ReaderInputStream(textReader,
                    StandardCharsets.UTF_8)));
            LParser parser = new LParser(textToParser);
            parser.setLexicalAnalysis(this.gui);
            gui.getTf().append("---------------------------------------------------------------------------------------------------\n");
            gui.getTf().append("---------------------------------INÍCIO ANÁLISE LÉXICA---------------------------------\n");
            gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
            try {
                parser.parseLexical();
                if (parser.getLexErrorCount() > 0) {

                    gui.getTf().append("-----------------------------CONCLUSÃO ANÁLISE LÉXICA-----------------------------\n\n" +
                            "Análise Léxica concluída. Erro(s) encontrados: " + parser.getLexErrorCount() + "\n");
                } else {
                    gui.getTf().append("-----------------------------CONCLUSÃO ANÁLISE LÉXICA------------------------------\n\n" +
                            "Análise Léxica concluída. Nenhum erro encontrado" + "\n");
                }
            } catch (ParseException ex) {
                System.out.println("ERRO NA ANÁLISE LÉXICA: "+ ex.getMessage());
                gui.getTf().append("----------------------------------ERRO NO PARSER-----------------------------\n\n" +
                        "Mensagem: " + ex.getMessage() + "\n");
            } finally {
                gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
                gui.getTf().append("----------------------------------FINAL ANÁLISE LÉXICA---------------------------------\n");
                gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                gui.getTf().append("\n---------------------------------------------------------------------------------------------------\n");
                gui.getTf().append("-------------------------------INÍCIO ANÁLISE SINTÁTICA------------------------------\n");
                gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                runSintaticVerification(text, parser);
            }
        } catch (TokenMgrError ex) {
            gui.getTf().append("------------------------------------ERRO NO TOKEN--------------------------------\n\n" +
                    "Mensagem: " + ex.getMessage() + "\n");
        }
    }

    public void runSintaticVerification(String text, LParser parser) {
        try {
            StringReader textReader = new StringReader(text);
            BufferedReader textToParser = new BufferedReader(new InputStreamReader(new ReaderInputStream(textReader,
                    StandardCharsets.UTF_8)));
            parser.ReInit(textToParser);
            parser.parseSyntactical();

            if (parser.getSynErrorCount() > 0) {
                gui.getTf().append("----------------------------CONCLUSÃO ANÁLISE SINTÁTICA----------------------------\n\n" +
                        "Análise Sintática concluída. Erro(s) encontrados: " + parser.getSynErrorCount() + "\n");
            } else {
                gui.getTf().append("----------------------------CONCLUSÃO ANÁLISE SINTÁTICA----------------------------\n\n" +
                        "Análise Sintática concluída. Nenhum erro encontrado" + "\n");
            }
            gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
            gui.getTf().append("--------------------------------FINAL ANÁLISE SINTÁTICA------------------------------\n");
            gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
        } catch (ParseException | ParseEOFException ex) {
            System.out.println("ERRO NA ANÁLISE SINTÁTICA: "+ ex.getMessage());
            gui.getTf().append("----------------------------------ERRO NO PARSER-----------------------------\n\n" +
                    "Mensagem: " + ex.getMessage() + "\n");

        }
    }

    public void printNotRecognized(RecoverySet g, ParseException e, String met) {
        gui.getTf().append("--------------------------------------ERRO SINTÁTICO---------------------------------\n\n" +
                "RecoverySet: " + g + "\n" +
                "ParseException: " + e.getMessage() + "\n" +
                "String: " + met + "\n\n");
    }
}
