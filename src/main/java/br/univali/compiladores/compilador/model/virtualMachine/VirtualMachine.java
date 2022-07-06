package br.univali.compiladores.compilador.model.virtualMachine;

import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;
import br.univali.compiladores.compilador.view.WindowConsole;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class VirtualMachine extends Thread{
    private final Stack<DataTypesAndOperations> stack; //stack comeca do zero por isso endereco -1 onde envolve a stack
    private final ArrayList<HelpInstructionTable> program;
    private boolean halt;
    private int counter; // program counter
    private final WindowConsole terminal;

    public VirtualMachine(ArrayList<HelpInstructionTable> program, WindowConsole gui) {
        stack = new Stack<>();
        this.program = program;
        halt = false;
        counter = 0;
        this.terminal = gui;
    }

    public WindowConsole getTerminal() {
        return terminal;
    }

    public void executeCode() {
        while (!halt) {
            Object s;
            switch (program.get(counter).getCode()) {
                //aritimeticas
                case "ADD":
                    ADD();
                    break;
                case "DIV":
                    DIV();
                    break;
                case "MUL":
                    MUL();
                    break;
                case "SUB":
                    SUB();
                    break;
                //memoria(var/consts)
                case "ALB":
                    ALB(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "ALI":
                    ALI(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "ALR":
                    ALR(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "ALS":
                    ALS(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "LDB":
                    LDB(Boolean.parseBoolean(program.get(counter).getParameter().toString()));
                    break;
                case "LDI":
                    LDI(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "LDR":
                    LDR(Float.parseFloat(program.get(counter).getParameter().toString()));
                    break;
                case "LDS":
                    LDS(program.get(counter).getParameter().toString());
                    break;
                case "LDV":
                    LDV(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "STR":
                    STR(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                //logica
                case "AND":
                    AND();
                    break;
                case "NOT":
                    NOT();
                    break;
                case "OR":
                    OR();
                    break;
                //relacional
                case "BGE":
                    BGE();
                    break;
                case "BGR":
                    BGR();
                    break;
                case "DIF":
                    DIF();
                    break;
                case "EQL":
                    EQL();
                    break;
                case "SME":
                    SME();
                    break;
                case "SMR":
                    SMR();
                    break;
                //desvio/controle
                case "JMF":
                    JMF(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "JMP":
                    JMP(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "JMT":
                    JMT(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "STP":
                    STP();
                    break;
                //entrada/saida dados
                case "REA":
                    REA(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                case "WRT":
                    WRT();
                    break;
                //??
                case "STC":
                    STC(Integer.parseInt(program.get(counter).getParameter().toString()));
                    break;
                // Ver com a sabrina:
                case "DVI":
                    DVI();
                    break;
                case "MOD":
                    MOD();
                    break;
                case "POW":
                    POW();
            }
        }
    }

    private void halt() {
        halt = true;
        terminal.println("HALT");
//        System.out.println("HALT");
    }

    private void ADD() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultSum;
        try {
            resultSum = firstValue.checkSum(secondValue, terminal);
            stack.push(resultSum);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }

    }

    private void DIV() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultDivision;
        if (secondValue.getType() == DataTypesAndOperations.INT) {
            if ((int) secondValue.getValue() == 0) {
                terminal.println("Erro: Divisor e zero");
                //System.out.println("Divisor e zero");
                halt();
                return;
            }
        } else if (secondValue.getType() == DataTypesAndOperations.FLOAT) {
            if ((float) secondValue.getValue() == 0.0) {
                terminal.println("Erro: Divisor e zero");
                //System.out.println("Divisor e zero");
                halt();
                return;
            }
        }
        try {
            resultDivision = firstValue.checkFloatDivision(secondValue, terminal);//float
            stack.push(resultDivision);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void MUL() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultMultiplication;
        try {
            resultMultiplication = firstValue.checkMultiplication(secondValue, terminal);
            stack.push(resultMultiplication);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void SUB() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultSubtraction;
        try {
            resultSubtraction = firstValue.checkSubtraction(secondValue, terminal);
            stack.push(resultSubtraction);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void ALB(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypesAndOperations<>(false, DataTypesAndOperations.LOGIC));
        }
        counter++;
    }
    private void ALI(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypesAndOperations<>(0, DataTypesAndOperations.INT));
        }
        counter++;
    }
    private void ALR(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypesAndOperations<>(0.0f, DataTypesAndOperations.FLOAT));
        }
        counter++;
    }

    private void ALS(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypesAndOperations("", DataTypesAndOperations.LITERAL)); //string vazia para literal
        }
        counter++;
    }

    private void LDB(boolean constBool) {
        DataTypesAndOperations boolValue = new DataTypesAndOperations<>(constBool, DataTypesAndOperations.LOGIC);
        stack.push(boolValue);
        counter++;
    }

    private void LDI(int constInt) {
        DataTypesAndOperations intValue = new DataTypesAndOperations<>(constInt, DataTypesAndOperations.INT);
        stack.push(intValue);
        counter++;
    }

    private void LDR(float consFloat) {
        DataTypesAndOperations realValue = new DataTypesAndOperations<>(consFloat, DataTypesAndOperations.FLOAT);
        stack.push(realValue);
        counter++;
    }
    private void LDS(String constLiteral) {
        DataTypesAndOperations litValue = new DataTypesAndOperations<>(constLiteral, DataTypesAndOperations.LITERAL);
        stack.push(litValue);
        counter++;
    }

    private void LDV(int endereco) {
        stack.push(stack.elementAt(endereco - 1)); //primeiro indice da stack é zero
        counter++;
    }

    private void STR(int endereco) {
        DataTypesAndOperations destination = stack.get(endereco - 1);
        DataTypesAndOperations stackTop = stack.pop();
        DataTypesAndOperations attribution = null;

        switch (printType(destination.getType())){
            case "Real":
                if (stackTop.getType() == DataTypesAndOperations.INT) {
                    float auxCast = (Integer) stackTop.getValue();
                    attribution = new DataTypesAndOperations(auxCast, DataTypesAndOperations.FLOAT);
                } else if (stackTop.getType() == DataTypesAndOperations.FLOAT) {
                    attribution = new DataTypesAndOperations(stackTop.getValue(), DataTypesAndOperations.FLOAT);
                } else {
                    attribution = stackTop;
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " não pode ser atribuído a um real. " +
                            "Atribuição inválida.");
//                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuído a um real. " +
//                            "Atribuição inválida.");
                    halt();
                }
                break;
            case "Inteiro":
                if (stackTop.getType() == DataTypesAndOperations.INT) {
                    attribution = new DataTypesAndOperations(stackTop.getValue(), DataTypesAndOperations.INT);
                } else {
                    attribution = stackTop;
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuído a um inteiro. " +
                            "Atribuição inválida.");
//                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuído a um inteiro. " +
//                            "Atribuição inválida.");
                    halt();
                }
                break;
            case "Literal":
                if (stackTop.getType() == DataTypesAndOperations.LITERAL) {
                    attribution = new DataTypesAndOperations((String) stackTop.getValue(), DataTypesAndOperations.LITERAL);
                } else {
                    attribution = stackTop;
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuído a um literal. " +
                            "Atribuição inválida.");
//                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuído a um literal. " +
//                            "Atribuicao invalida.");
                    halt();
                }
                break;
            case "Logico":
                if (stackTop.getType() == DataTypesAndOperations.LOGIC) {
                    attribution = new DataTypesAndOperations((Boolean) stackTop.getValue(), DataTypesAndOperations.LOGIC);
                } else {
                    attribution = stackTop;
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuído a um lógico. " +
                            "Atribuição inválida.");
//                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um logico. " +
//                            "Atribuicao invalida.");
                    halt();
                }
                break;
        }
        stack.set(endereco - 1, attribution);
        counter++;
    }
    private void AND() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultAnd;

        try {
            resultAnd = firstValue.checkAnd(secondValue, terminal);
            stack.push(resultAnd);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void NOT() {
        DataTypesAndOperations topNot;

        try {
            topNot = stack.pop().checkNot(terminal);
            stack.push(topNot);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void OR() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultOr;
        try {
            resultOr = firstValue.checkOr(secondValue, terminal);
            stack.push(resultOr);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void BGE() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        boolean resultBiggerOrEqualValue;
        try {
            resultBiggerOrEqualValue = firstValue.checkBiggerOrEqualValue(secondValue, terminal);
            stack.push(new DataTypesAndOperations<>(resultBiggerOrEqualValue, DataTypesAndOperations.LOGIC));
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void BGR() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        boolean resultBiggerValue;
        try {
            resultBiggerValue = firstValue.checkBiggerValue(secondValue, terminal);
            stack.push(new DataTypesAndOperations<>(resultBiggerValue, DataTypesAndOperations.LOGIC));
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void EQL() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        boolean resultEquals;
        try {
            resultEquals = firstValue.checkEqual(secondValue, terminal);
            stack.push(new DataTypesAndOperations<>(resultEquals, DataTypesAndOperations.LOGIC));
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void DIF() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        boolean resultDifferent;
        try {
            resultDifferent = firstValue.checkDifferent(secondValue, terminal);
            stack.push(new DataTypesAndOperations<>(resultDifferent, DataTypesAndOperations.LOGIC));
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void SME() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        boolean resultLesserOrEqualValue;
        try {
            resultLesserOrEqualValue = firstValue.checkLesserOrEqualValue(secondValue, terminal);
            stack.push(new DataTypesAndOperations<>(resultLesserOrEqualValue, DataTypesAndOperations.LOGIC));
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void SMR() {
        DataTypesAndOperations b = stack.pop();
        DataTypesAndOperations a = stack.pop();
        boolean resultLesserValue;
        try {
            resultLesserValue = a.checkLesserValue(b, terminal);
            stack.push(new DataTypesAndOperations<>(resultLesserValue, DataTypesAndOperations.LOGIC));
            counter++;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            halt();
        }
    }
    private void JMF(int endereco) {
        DataTypesAndOperations falseCheckJump = stack.pop();
        if (!(boolean) falseCheckJump.getValue()) {
            counter = endereco -1;
        } else {
            counter++;
        }
    }

    private void JMP(int endereco) {
        counter = endereco -1;
    }

    private void JMT(int endereco) {
        DataTypesAndOperations trueCheckJump = stack.pop();
        if ((boolean) trueCheckJump.getValue()) {
            counter = endereco -1;
        } else {
            counter++;
        }
    }

    private void STP() {
        halt();
    }
    private void REA(int valueType) {
        String userInput = "";
        try {
            userInput = JOptionPane.showInputDialog("Insira um valor esperado");
            terminal.println(userInput);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        switch (valueType) {
            case DataTypesAndOperations.FLOAT:
                try {
                    Float.parseFloat(userInput);
                    stack.push(new DataTypesAndOperations<>(Float.parseFloat(userInput), valueType));
                } catch (NumberFormatException e) {
                    terminal.println("Tipo informado incorreto. Esperava tipo Real");
//                    System.out.println("Tipo informado incorreto. Esperava tipo Real");
                    System.out.println(e.getMessage());
                    halt();
                }
                break;
            case DataTypesAndOperations.INT:
                try {
                    Integer.parseInt(userInput);
                    stack.push(new DataTypesAndOperations<>(Integer.parseInt(userInput), valueType));
                } catch (NumberFormatException e) {
                    terminal.println("Tipo informado incorreto. Esperava tipo Inteiro");
//                    System.out.println("Tipo informado incorreto. Esperava tipo Inteiro");
                    System.out.println(e.getMessage());
                    halt();
                }
                break;
            case DataTypesAndOperations.LITERAL:
                stack.push(new DataTypesAndOperations<>(userInput, valueType));
                break;
        }

        counter++;
    }

    private void WRT() {
        DataTypesAndOperations writeStackTop = stack.pop();

        if (writeStackTop.getType() == DataTypesAndOperations.FLOAT) {
            String value = String.format("%.4f", (Float) writeStackTop.getValue());
//            System.out.println("" + value + "\r");
            terminal.println("" + value + "\r");
        } else {
//            System.out.println("" + writeStackTop.getValue() + "\r");
            terminal.println("" + writeStackTop.getValue() + "\r");
        }
        counter++;
    }

    private void STC(int deslocamento) {
        DataTypesAndOperations stackTop = stack.pop();
        for (int i = stack.size() - deslocamento; i < stack.size(); i++) {
            stack.set(i, stackTop);
        }
        counter++;
    }
    private void DVI() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultIntDivision;

        if (secondValue.getType() == DataTypesAndOperations.INT) {
            if ((int) secondValue.getValue() == 0) {
                terminal.println("Erro: Divisor é zero");
//                System.out.println("Divisor e zero");
                halt();
                return;
            }
        } else if (secondValue.getType() == DataTypesAndOperations.FLOAT) {
            if ((float) secondValue.getValue() == 0.0) {
                terminal.println("Erro: Divisor é zero");
//                System.out.println("Divisor e zero");
                halt();
                return;
            }
        }

        try {
            resultIntDivision = firstValue.checkIntDivision(secondValue, terminal);
            stack.push(resultIntDivision);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }

    }
    private void MOD() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultMod;
        try {
            resultMod = firstValue.checkMod(secondValue, terminal);
            stack.push(resultMod);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }

    }

    private void POW() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultPowerOf;
        try {
            resultPowerOf = firstValue.checkPowerOf(secondValue, terminal);
            stack.push(resultPowerOf);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }

    }
    private String printType(int type) {
        String printableTypeName;
        switch (type) {
            case DataTypesAndOperations.FLOAT:
                printableTypeName = "Real";
                break;
            case DataTypesAndOperations.INT:
                printableTypeName = "Inteiro";
                break;
            case DataTypesAndOperations.LITERAL:
                printableTypeName = "Literal";
                break;
            case DataTypesAndOperations.LOGIC:
                printableTypeName = "Lógico";
                break;
            default:
                printableTypeName = "Não Reconhecido";
        }
        return printableTypeName;
    }
}
