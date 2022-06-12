package br.univali.compiladores.compilador.model.Compile;

import br.univali.compiladores.compilador.controller.WindowCOController;
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
    private SemanticActions semanticActions;

    public Compile(WindowER gui){
        this.gui = gui;
        gui.getTf().setText("");
        semanticActions = new SemanticActions();
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
        gui.getTf().append("---------------------------------------ERRO LÉXICO----------------------------------------\n\n" +
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

                    gui.getTf().append("---------------------------CONCLUSÃO ANÁLISE LÉXICA----------------------------\n\n" +
                            "Análise Léxica concluída. Erro(s) encontrados: " + parser.getLexErrorCount() + "\n");
                    gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
                    gui.getTf().append("------------------------------------FIM ANÁLISE LÉXICA----------------------------------\n");
                    gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                } else {
                    gui.getTf().append("---------------------------CONCLUSÃO ANÁLISE LÉXICA-----------------------------\n\n" +
                            "Análise Léxica concluída. Nenhum erro encontrado" + "\n");
                    gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
                    gui.getTf().append("------------------------------------FIM ANÁLISE LÉXICA----------------------------------\n");
                    gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                    gui.getTf().append("\n---------------------------------------------------------------------------------------------------\n");
                    gui.getTf().append("-------------------------------INÍCIO ANÁLISE SINTÁTICA------------------------------\n");
                    gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                    runSintaticVerification(text, parser);
                }
            } catch (ParseException ex) {
                System.out.println("ERRO NA ANÁLISE LÉXICA: "+ ex.getMessage());
                gui.getTf().append("----------------------------------ERRO NO PARSER-----------------------------\n\n" +
                        "Mensagem: " + ex.getMessage() + "\n");
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
                gui.getTf().append("--------------------------CONCLUSÃO ANÁLISE SINTÁTICA--------------------------\n\n" +
                        "Análise Sintática concluída. Erro(s) encontrados: " + parser.getSynErrorCount() + "\n");
                gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
                gui.getTf().append("----------------------------------FIM ANÁLISE SINTÁTICA-------------------------------\n");
                gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                //mostrar uma mensagem ao executar
            } else {
                gui.getTf().append("--------------------------CONCLUSÃO ANÁLISE SINTÁTICA--------------------------\n\n" +
                        "Análise Sintática concluída. Nenhum erro encontrado" + "\n");
                gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
                gui.getTf().append("----------------------------------FIM ANÁLISE SINTÁTICA-------------------------------\n");
                gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                //runSemanticVerification();
                System.out.println(parser.getSemanticActions().getInstructionList());
                new WindowCOController();
                //mostrar erros semânticos
            }
        } catch (ParseException | ParseEOFException ex) {
            System.out.println("ERRO NA ANÁLISE SINTÁTICA: "+ ex.getMessage());
            gui.getTf().append("----------------------------------ERRO NO PARSER-----------------------------\n\n" +
                    "Mensagem: " + ex.getMessage() + "\n");

        }
    }

    public void printNotRecognized(RecoverySet g, ParseException e, String met) {
        String type = msgErrorSintatic(e);
        gui.getTf().append("----------------------------------------ERRO SINTÁTICO----------------------------------\n\n" +
                "Erro sintático encontrado " + met + "\n" +
                "Encontrado \"" + e.currentToken.next + "\", mas era esperado " + type + "\n" +
                "Linha: " + e.currentToken.next.beginLine + "\n" +
                "Coluna: " + e.currentToken.next.beginColumn + "\n\n");
    }

    public void runSemanticVerification() {
        if (semanticActions.getSemErrorCount() > 0) {
            gui.getTf().append("--------------------------CONCLUSÃO ANÁLISE SINTÁTICA--------------------------\n\n" +
                    "Análise Sintática concluída. Erro(s) encontrados: " + semanticActions.getSemErrorCount() + "\n");
            gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
            gui.getTf().append("----------------------------------FIM ANÁLISE SINTÁTICA-------------------------------\n");
            gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
        } else {
            gui.getTf().append("--------------------------CONCLUSÃO ANÁLISE SINTÁTICA--------------------------\n\n" +
                    "Análise Sintática concluída. Nenhum erro encontrado" + "\n");
            gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
            gui.getTf().append("----------------------------------FIM ANÁLISE SINTÁTICA-------------------------------\n");
            gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
            //mostra o código objeto
        }
    }

    public String msgErrorSintatic(ParseException e){
//        for (int i = 0; i < e.tokenImage[e.expectedTokenSequences.length]; i++) {
//            for (int j = 0; j < e.tokenImage[e.expectedTokenSequences[i]].length(); j++) {
//                expected.append(tokenImage[expectedTokenSequences[i][j]]).append(' ');
//            }
//        }
        String expected = e.tokenImage[e.expectedTokenSequences[0][0]];
        String type = "";
        //expected += getExpectedType(tokenImage[tok.kind]);
        if(expected.startsWith("<RESERVED_WORD")){
            type = "a palavra reservada " + expected.substring(15,expected.length()-1);
        } else {
            switch (expected) {
                case "<CONST_INT>":
                    type = "um valor inteiro";
                    break;
                case "<CONST_REAL>":
                    type = "um valor real";
                    break;
                case "<CONST_LITERAL>":
                    type = "uma letra, palavra ou frase";
                    break;
                case "<IDENTIFIER>":
                    type = "um identificador";
                    break;
                case "<EOF>":
                    type = "o final do programa";
                    break;
                default:
                    System.out.println("Token esperado" + expected);
                    type = expected;
                    break;
            }
        }
        return type;
    }
}
