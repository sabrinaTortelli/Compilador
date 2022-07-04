package br.univali.compiladores.compilador.model.virtualMachine;

import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;

import java.util.ArrayList;
import java.util.Stack;

public class VirtualMachine {
    private final Stack<DataTypesAndOperations> stack; //stack comeca do zero por isso endereco -1 onde envolve a stack
    private final ArrayList<HelpInstructionTable> program;
    private boolean halt;
    private int counter; // program counter
    private TerminalEmulator terminal;

    public VirtualMachine(ArrayList program) {
        stack = new Stack<>();
        this.program = program;
        halt = false;
        counter = 0;
        TerminalEmulator terminator = new TerminalEmulator();
        this.terminal = terminator;
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
        terminal.println("HALT");
        System.out.println("HALT");
    }

    private Float castToFloat(Object value){
        double d = (double) value;
        float f = (float)d;
        return f;
    }

    private void ADD() {
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultSum;
        try {
            resultSum = firstValue.checkSum(secondValue);
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
                System.out.println("Divisor e zero");
                halt();
                return;
            }
        } else if (secondValue.getType() == DataTypesAndOperations.FLOAT) {
            if ((float) secondValue.getValue() == 0.0) {
                terminal.println("Erro: Divisor e zero");
                System.out.println("Divisor e zero");
                halt();
                return;
            }
        }
        try {
            resultDivision = firstValue.checkFloatDivision(secondValue);//float
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
            resultMultiplication = firstValue.checkMultiplication(secondValue);
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
            resultSubtraction = firstValue.checkSubtraction(secondValue);
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
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um real. " +
                            "Atribuicao invalida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um real. " +
                            "Atribuicao invalida.");
                    halt();
                }
                break;
            case "Inteiro":
                if (stackTop.getType() == DataTypesAndOperations.INT) {
                    attribution = new DataTypesAndOperations(stackTop.getValue(), DataTypesAndOperations.INT);
                } else {
                    attribution = stackTop;
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um inteiro. " +
                            "Atribuicao invalida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um inteiro. " +
                            "Atribuicao invalida.");
                    halt();
                }
                break;
            case "Literal":
                if (stackTop.getType() == DataTypesAndOperations.LITERAL) {
                    attribution = new DataTypesAndOperations((String) stackTop.getValue(), DataTypesAndOperations.LITERAL);
                } else {
                    attribution = stackTop;
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um literal. " +
                            "Atribuicao invalida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um literal. " +
                            "Atribuicao invalida.");
                    halt();
                }
                break;
            case "Logico":
                if (stackTop.getType() == DataTypesAndOperations.LOGIC) {
                    attribution = new DataTypesAndOperations((Boolean) stackTop.getValue(), DataTypesAndOperations.LOGIC);
                } else {
                    attribution = stackTop;
                    terminal.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um logico. " +
                            "Atribuicao invalida.");
                    System.out.println( "Tipo: " + printType(stackTop.getType()) + " nao pode ser atribuito a um logico. " +
                            "Atribuicao invalida.");
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
            resultAnd = firstValue.checkAnd(secondValue);
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
            topNot = stack.pop().checkNot();
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
            resultOr = firstValue.checkOr(secondValue);
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
            resultBiggerOrEqualValue = firstValue.checkBiggerOrEqualValue(secondValue);
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
            resultBiggerValue = firstValue.checkBiggerValue(secondValue);
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
            resultEquals = firstValue.checkEqual(secondValue);
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
            resultDifferent = firstValue.checkDifferent(secondValue);
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
            resultLesserOrEqualValue = firstValue.checkLesserOrEqualValue(secondValue);
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
            resultLesserValue = a.checkLesserValue(b);
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
//        Scanner scan = new Scanner(System.in);
        try {
            terminal.println("Inserir valor esperado: ");
            userInput = terminal.read(); //metodo que vai ler a entrada do usuário
//            userInput = scan.next();
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
                    System.out.println("Tipo informado incorreto. Esperava tipo Real");
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
                    System.out.println("Tipo informado incorreto. Esperava tipo Inteiro");
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
            System.out.println("" + value + "\r");
            terminal.println("" + value + "\r");
        } else {
            System.out.println("" + writeStackTop.getValue() + "\r");
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
                terminal.println("Erro: Divisor e zero");
                System.out.println("Divisor e zero");
                halt();
                return;
            }
        } else if (secondValue.getType() == DataTypesAndOperations.FLOAT) {
            if ((float) secondValue.getValue() == 0.0) {
                terminal.println("Erro: Divisor e zero");
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
        DataTypesAndOperations secondValue = stack.pop();
        DataTypesAndOperations firstValue = stack.pop();
        DataTypesAndOperations resultMod;
        try {
            resultMod = firstValue.checkMod(secondValue);
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
            resultPowerOf = firstValue.checkPowerOf(secondValue);
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
                printableTypeName = "Logico";
                break;
            default:
                printableTypeName = "Não Reconhecido";
        }
        return printableTypeName;
    }
}
