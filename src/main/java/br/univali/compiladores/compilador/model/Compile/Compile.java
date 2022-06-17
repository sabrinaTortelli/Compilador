package br.univali.compiladores.compilador.model.Compile;

import br.univali.compiladores.compilador.controller.WindowCOController;
import br.univali.compiladores.compilador.model.parser.LParser;
import br.univali.compiladores.compilador.model.parser.ParseException;
import br.univali.compiladores.compilador.model.parser.Token;
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
                gui.getTf().append("\n---------------------------------------------------------------------------------------------------\n");
                gui.getTf().append("-------------------------------INÍCIO ANÁLISE SEMÂNTICA-----------------------------\n");
                gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
                runSemanticVerification(parser);
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
                type + "\n" +
                "Linha: " + e.currentToken.next.beginLine + "\n" +
                "Coluna: " + e.currentToken.next.beginColumn + "\n\n");
    }

    public void printRecognizedEOF() {
        gui.getTf().append("----------------------------------------------------------------------------------------------------\n\n" +
                "OBS: Final do arquivo encontrado prematuramente\n\n");
    }

    public void runSemanticVerification(LParser parser) {
        System.out.println(parser.getSemanticActions().getSemErrorCount());
        if (parser.getSemanticActions().getSemErrorCount() > 0) {
            gui.getTf().append(parser.getSemanticActions().getPrintSemanticError());
            gui.getTf().append("--------------------------CONCLUSÃO ANÁLISE SEMÂNTICA--------------------------\n\n" +
                    "Análise Semântica concluída. Erro(s) encontrados: " + parser.getSemanticActions().getSemErrorCount() + "\n");
            gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
            gui.getTf().append("----------------------------------FIM ANÁLISE SEMÂNTICA-------------------------------\n");
            gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
        } else {
            gui.getTf().append("--------------------------CONCLUSÃO ANÁLISE SEMÂNTICA------------------------\n\n" +
                    "Análise Semântica concluída. Nenhum erro encontrado" + "\n");
            gui.getTf().append("\n\n---------------------------------------------------------------------------------------------------\n");
            gui.getTf().append("---------------------------------FIM ANÁLISE SEMÂNTICA------------------------------\n");
            gui.getTf().append("---------------------------------------------------------------------------------------------------\n\n");
            System.out.println(parser.getSemanticActions().getInstructionList());
            new WindowCOController(parser.getSemanticActions().getInstructionList());
            //mostra o código objeto
        }
    }

    private String eol = System.getProperty("line.separator", "\n");

    public String msgErrorSintatic(ParseException e) {
        StringBuffer expected = new StringBuffer();
        int maxSize = 0;
        for (int i = 0; i < e.expectedTokenSequences.length; i++) {
            if (maxSize < e.expectedTokenSequences[i].length) {
                maxSize = e.expectedTokenSequences[i].length;
            }
            for (int j = 0; j < e.expectedTokenSequences[i].length; j++) {
                String tokenString = swicthTokens(e.tokenImage[e.expectedTokenSequences[i][j]]);
                expected.append(tokenString);
            }
            if(e.expectedTokenSequences.length == 1 || i == e.expectedTokenSequences.length-1){
                expected.append(".");
            } else {
                expected.append(" ou \n");
            }
        }
        String retval = "Encontrado ";
        Token tok = e.currentToken.next;
        for (int i = 0; i < maxSize; i++) {
            if (i != 0) retval += " ";
            if (tok.kind == 0) {
                String tokenString = swicthTokens(e.tokenImage[0]);
                retval += tokenString;
                break;
            }
            String tokenString = swicthTokens(e.tokenImage[tok.kind]);
            retval += " " + tokenString;
            tok = tok.next;
        }
        retval += "." + eol;
        if (e.expectedTokenSequences.length == 1) {
            retval += "Mas era esperado: ";
        } else {
            retval += "Mas era esperado um destes tokens:" + eol;
        }
        retval += expected.toString();
        return retval;
    }

    public String swicthTokens(String token){
        String type = "";
        switch (token) {
            case "<RESERVED_WORD_DO>":
                type = "a palavra reservada \"do\"";
                break;
            case "<RESERVED_WORD_THIS>":
                type = "a palavra reservada \"this\"";
                break;
            case "<RESERVED_WORD_BODY>":
                type = "a palavra reservada \"body\"";
                break;
            case "<RESERVED_WORD_DESCRIPTION>":
                type = "a palavra reservada \"description\"";
                break;
            case "<RESERVED_WORD_DECLARATION>":
                type = "a palavra reservada \"declaration\"";
                break;
            case "<RESERVED_WORD_TYPE>":
                type = "a palavra reservada \"type\"";
                break;
            case "<RESERVED_WORD_IS>":
                type = "a palavra reservada \"is\"";
                break;
            case "<RESERVED_WORD_CONSTANT>":
                type = "a palavra reservada \"constant\"";
                break;
            case "<RESERVED_WORD_AND>":
                type = "a palavra reservada \"and\"";
                break;
            case "<RESERVED_WORD_VARIABLE>":
                type = "a palavra reservada \"variable\"";
                break;
            case "<RESERVED_WORD_AS>":
                type = "a palavra reservada \"as\"";
                break;
            case "<RESERVED_WORD_INTEGER>":
                type = "a palavra reservada \"integer\"";
                break;
            case "<RESERVED_WORD_REAL>":
                type = "a palavra reservada \"real\"";
                break;
            case "<RESERVED_WORD_STRING>":
                type = "a palavra reservada \"string\"";
                break;
            case "<RESERVED_WORD_LOGIC>":
                type = "a palavra reservada \"logic\"";
                break;
            case "<RESERVED_WORD_DESIGNATE>":
                type = "a palavra reservada \"designate\"";
                break;
            case "<RESERVED_WORD_READ>":
                type = "a palavra reservada \"read\"";
                break;
            case "<RESERVED_WORD_WRITE>":
                type = "a palavra reservada \"write\"";
                break;
            case "<RESERVED_WORD_ALL>":
                type = "a palavra reservada \"write all\"";
                break;
            case "<RESERVED_WORD_REPEAT>":
                type = "a palavra reservada \"repeat\"";
                break;
            case "<RESERVED_WORD_AVALIATE>":
                type = "a palavra reservada \"avaliate\"";
                break;
            case "<RESERVED_WORD_RESULT>":
                type = "a palavra reservada \"result\"";
                break;
            case "<RESERVED_WORD_TRUE>":
                type = "a palavra reservada \"true\"";
                break;
            case "<RESERVED_WORD_UNTRUE>":
                type = "a palavra reservada \"untrue\"";
                break;
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
                System.out.println("Token esperado" + token);
                type = token;
                break;
        }
        return type;
    }
}
