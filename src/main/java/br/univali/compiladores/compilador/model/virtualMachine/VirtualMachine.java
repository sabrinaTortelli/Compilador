package br.univali.compiladores.compilador.model.virtualMachine;
import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;
import br.univali.compiladores.compilador.view.WindowVM;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Stack;

public class VirtualMachine{
    private final Stack<DataTypes> stack; //stack comeca do zero e o array list tambem
                                        // por isso todos os enderecos levam -1
    private final ArrayList<HelpInstructionTable> program;
    private boolean halt;
    private int counter; // program counter comeca em zero para pegar as posicoes do array list
    private WindowVM gui;

    public VirtualMachine(ArrayList<HelpInstructionTable> program, WindowVM gui) {
        this.gui = gui;
        stack = new Stack<>();
        this.program = program;
        halt = false;
        counter = 0;
    }
     public void executeCode() {

        while (!halt) {
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
                    ALB((int)program.get(counter).getParameter());
                    break;
                case "ALI":
                    ALI((int)program.get(counter).getParameter());
                    break;
                case "ALR":
                    ALR((int)program.get(counter).getParameter());
                    break;
                case "ALS":
                    ALS((int)program.get(counter).getParameter());
                    break;
                case "LDB":
                    LDB((boolean)program.get(counter).getParameter());
                    break;
                case "LDI":
                    LDI((int)program.get(counter).getParameter());
                    break;
                case "LDR":
                    LDR(castToFloat(program.get(counter).getParameter()));
                    break;
                case "LDS":
                    LDS((String)program.get(counter).getParameter());
                    break;
                case "LDV":
                    LDV((int)program.get(counter).getParameter());
                    break;
                case "STR":
                    STR((int)program.get(counter).getParameter());
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
                    JMF((int)program.get(counter).getParameter());
                    break;
                case "JMP":
                    JMP((int)program.get(counter).getParameter());
                    break;
                case "JMT":
                    JMT((int)program.get(counter).getParameter());
                    break;
                case "STP":
                    STP();
                    break;
                //entrada/saida dados
                case "REA":
                    REA((int)program.get(counter).getParameter());
                    break;
                case "WRT":
                    WRT();
                    break;
                //??
                case "STC":
                    STC((int)program.get(counter).getParameter());
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
        gui.printText("HALT");
        System.out.println("HALT");
    }

    private Float castToFloat(Object value){
        double d = (double) value;
        float f = (float)d;
        return f;
    }

    private void ADD() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultSum;
        try {
            resultSum = firstValue.checkSum(secondValue);
            stack.push(resultSum);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }

    }

    private void DIV() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultDivision;
        if (secondValue.getType() == DataTypes.INT) {
            if ((int) secondValue.getValue() == 0) {
                gui.printText("Divisor e zero");
                System.out.println("Divisor e zero");
                halt();
                return;
            }
        } else if (secondValue.getType() == DataTypes.FLOAT) {
            if ((float) secondValue.getValue() == 0.0) {
                gui.printText("Divisor e zero");
                System.out.println("Divisor e zero");
                halt();
                return;
            }
        }
        try {
            resultDivision = firstValue.checkFloatDivision(secondValue);
            stack.push(resultDivision);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void MUL() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultMultiplication;
        try {
            resultMultiplication = firstValue.checkMultiplication(secondValue);
            stack.push(resultMultiplication);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void SUB() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultSubtraction;
        try {
            resultSubtraction = firstValue.checkSubtraction(secondValue);
            stack.push(resultSubtraction);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void ALB(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypes<>(false, DataTypes.LOGIC));
        }
        counter++;
    }
    private void ALI(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypes<>(0, DataTypes.INT));
        }
        counter++;
    }
    private void ALR(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypes<>(0.0f, DataTypes.FLOAT));
        }
        counter++;
    }

    private void ALS(int deslocamento) {
        for (int i = 0; i < deslocamento; i++) {
            stack.push(new DataTypes("", DataTypes.LITERAL)); //string vazia para literal
        }
        counter++;
    }

    private void LDB(boolean constBool) {
        DataTypes boolValue = new DataTypes<>(constBool, DataTypes.LOGIC);
        stack.push(boolValue);
        counter++;
    }

    private void LDI(int constInt) {
        DataTypes intValue = new DataTypes<>(constInt, DataTypes.INT);
        stack.push(intValue);
        counter++;
    }

    private void LDR(float consFloat) {
        DataTypes realValue = new DataTypes<>(consFloat, DataTypes.FLOAT);
        stack.push(realValue);
        counter++;
    }
    private void LDS(String constLiteral) {
        DataTypes litValue = new DataTypes<>(constLiteral, DataTypes.LITERAL);
        stack.push(litValue);
        counter++;
    }

    private void LDV(int endereco) {
        stack.push(stack.elementAt(endereco - 1)); //primeiro indice da stack é zero
        counter++;
    }

    private void STR(int endereco) {
        DataTypes destination = stack.get(endereco - 1);
        DataTypes stackTop = stack.pop();
        DataTypes attribution = null;

        switch (printType(destination.getType())){
            case "Real":
                if (stackTop.getType() == DataTypes.INT) {
                    float auxCast = (Integer) stackTop.getValue();
                    attribution = new DataTypes(auxCast, DataTypes.FLOAT);
                } else if (stackTop.getType() == DataTypes.FLOAT) {
                    attribution = new DataTypes(stackTop.getValue(), DataTypes.FLOAT);
                } else {
                    attribution = stackTop;
                    gui.printText("Tipo: " + printType(stackTop.getType()) + " não pode ser atribuido a um real. " +
                            "Atribuição inválida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuido a um real. " +
                            "Atribuicao invalida.");
                    halt();
                }
                break;
            case "Inteiro":
                if (stackTop.getType() == DataTypes.INT) {
                    attribution = new DataTypes(stackTop.getValue(), DataTypes.INT);
                } else {
                    gui.printText("Tipo: " + printType(stackTop.getType()) + " não pode ser atribuido a um inteiro. " +
                            "Atribuição inválida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuido a um inteiro. " +
                            "Atribuicao invalida.");
                    halt();
                }
                break;
            case "Literal":
                if (stackTop.getType() == DataTypes.LITERAL) {
                    attribution = new DataTypes((String) stackTop.getValue(), DataTypes.LITERAL);
                } else {
                    gui.printText("Tipo: " + printType(stackTop.getType()) + " não pode ser atribuido a um literal. " +
                            "Atribuição inválida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuido a um literal. " +
                            "Atribuicao invalida.");
                    halt();
                }
                break;
            case "Logico":
                if (stackTop.getType() == DataTypes.LOGIC) {
                    attribution = new DataTypes((Boolean) stackTop.getValue(), DataTypes.LOGIC);
                } else {
                    gui.printText("Tipo: " + printType(stackTop.getType()) + " não pode ser atribuido a um lógico. " +
                            "Atribuição inválida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuido a um logico. " +
                            "Atribuicao invalida.");
                    halt();
                }
                break;
        }
        stack.set(endereco - 1, attribution);
        counter++;
    }
    private void AND() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultAnd;

        try {
            resultAnd = firstValue.checkAnd(secondValue);
            stack.push(resultAnd);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void NOT() {
        DataTypes topNot;

        try {
            topNot = stack.pop().checkNot();
            stack.push(topNot);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void OR() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultOr;
        try {
            resultOr = firstValue.checkOr(secondValue);
            stack.push(resultOr);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void BGE() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        boolean resultBiggerOrEqualValue;
        try {
            resultBiggerOrEqualValue = firstValue.checkBiggerOrEqualValue(secondValue);
            stack.push(new DataTypes<>(resultBiggerOrEqualValue, DataTypes.LOGIC));
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void BGR() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        boolean resultBiggerValue;
        try {
            resultBiggerValue = firstValue.checkBiggerValue(secondValue);
            stack.push(new DataTypes<>(resultBiggerValue, DataTypes.LOGIC));
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void EQL() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        boolean resultEquals;
        try {
            resultEquals = firstValue.checkEqual(secondValue);
            stack.push(new DataTypes<>(resultEquals, DataTypes.LOGIC));
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }

    private void DIF() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        boolean resultDifferent;
        try {
            resultDifferent = firstValue.checkDifferent(secondValue);
            stack.push(new DataTypes<>(resultDifferent, DataTypes.LOGIC));
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void SME() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        boolean resultLesserOrEqualValue;
        try {
            resultLesserOrEqualValue = firstValue.checkLesserOrEqualValue(secondValue);
            stack.push(new DataTypes<>(resultLesserOrEqualValue, DataTypes.LOGIC));
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void SMR() {
        DataTypes b = stack.pop();
        DataTypes a = stack.pop();
        boolean resultLesserValue;
        try {
            resultLesserValue = a.checkLesserValue(b);
            stack.push(new DataTypes<>(resultLesserValue, DataTypes.LOGIC));
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }
    }
    private void JMF(int endereco) {
        DataTypes falseCheckJump = stack.pop();
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
        DataTypes trueCheckJump = stack.pop();
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
            gui.setEditable(true);
//            userInput = gui.getTextPane().getText();
//            System.out.println(userInput);

            //userInput = metodo.read(); //metodo que vai ler a entrada do usuário
            userInput = "2"; //metodo que vai ler a entrada do usuário
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
        }

        switch (valueType) {
            case DataTypes.FLOAT:
                try {
                    Float.parseFloat(userInput);
                    stack.push(new DataTypes<>(Float.parseFloat(userInput), valueType));
                } catch (NumberFormatException e) {
                    gui.printText("Tipo informado incorreto. Esperava tipo Real");
                    System.out.println("Tipo informado incorreto. Esperava tipo Real");
                    System.out.println(e.getMessage());
                    halt();
                }
                break;
            case DataTypes.INT:
                try {
                    Integer.parseInt(userInput);
                    stack.push(new DataTypes<>(Integer.parseInt(userInput), valueType));
                } catch (NumberFormatException e) {
                    gui.printText("Tipo informado incorreto. Esperava tipo Inteiro");
                    System.out.println("Tipo informado incorreto. Esperava tipo Inteiro");
                    System.out.println(e.getMessage());
                    halt();
                }
                break;
            case DataTypes.LITERAL:
                stack.push(new DataTypes<>(userInput, valueType));
                break;
        }

        counter++;
    }

    private void WRT() {
        DataTypes writeStackTop = stack.pop();

        if (writeStackTop.getType() == DataTypes.FLOAT) {
            String value = String.format("%.4f", (Float) writeStackTop.getValue());
            gui.printText("" + value + "\r");
            System.out.println("" + value + "\r");
        } else {
            gui.printText("" + writeStackTop.getValue() + "\r");
            System.out.println("" + writeStackTop.getValue() + "\r");
        }
        counter++;
    }

    private void STC(int deslocamento) {
        DataTypes stackTop = stack.pop();
        for (int i = stack.size() - deslocamento; i < stack.size(); i++) {
            stack.set(i, stackTop);
        }

        counter++;
    }
    private void DVI() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultIntDivision;

        if (secondValue.getType() == DataTypes.INT) {
            if ((int) secondValue.getValue() == 0) {
                gui.printText("Divisor e zero");
                System.out.println("Divisor e zero");
                halt();
                return;
            }
        } else if (secondValue.getType() == DataTypes.FLOAT) {
            if ((float) secondValue.getValue() == 0.0) {
                gui.printText("Divisor e zero");
                System.out.println("Divisor e zero");
                halt();
                return;
            }
        }

        try {
            resultIntDivision = firstValue.checkIntDivision(secondValue);
            stack.push(resultIntDivision);
            counter++;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            halt();
        }

    }
    private void MOD() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultMod;
        try {
            resultMod = firstValue.checkMod(secondValue);
            stack.push(resultMod);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }

    }

    private void POW() {
        DataTypes secondValue = stack.pop();
        DataTypes firstValue = stack.pop();
        DataTypes resultPowerOf;
        try {
            resultPowerOf = firstValue.checkPowerOf(secondValue);
            stack.push(resultPowerOf);
            counter++;
        } catch (Exception e) {
            gui.printText(e.getMessage());
            System.out.println(e.getMessage());
            halt();
        }

    }
    private String printType(int type) {
        String printableTypeName;
        switch (type) {
            case DataTypes.FLOAT:
                printableTypeName = "Real";
                break;
            case DataTypes.INT:
                printableTypeName = "Inteiro";
                break;
            case DataTypes.LITERAL:
                printableTypeName = "Literal";
                break;
            case DataTypes.LOGIC:
                printableTypeName = "Logico";
                break;
            default:
                printableTypeName = "Não Reconhecido";
        }
        return printableTypeName;
    }
}
