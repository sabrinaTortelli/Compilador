package br.univali.compiladores.compilador.model.virtualMachine;

import br.univali.compiladores.compilador.view.WindowConsole;

public class DataTypesAndOperations<T>{
    //valores definidos por causa do REA INT =1, REAL/FLOAT = 2, LITERAL/STRING = 3
    static final int FLOAT = 2;
    static final int INT = 1;
    static final int LOGIC = 4;
    static final int LITERAL = 3;


    final private T value;
    final private int type;

    public DataTypesAndOperations(T value, int type) {
        this.value = value;
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public int getType() {
        return type;
    }

    //operacoes logicas
    public DataTypesAndOperations checkAnd(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.LOGIC && secondValue.getType() == DataTypesAndOperations.LOGIC) {
            boolean and = (Boolean) this.getValue() & (boolean) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(and, DataTypesAndOperations.LOGIC);
            return result;
        } else {
            gui.println("Erro: operação 'E' esperava valores lógicos");
            throw new Exception("Erro: operacao 'E' esperava valores logicos");
        }
    }

    public DataTypesAndOperations checkOr(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.LOGIC && secondValue.getType() == DataTypesAndOperations.LOGIC) {
            boolean or = (Boolean) this.getValue() | (boolean) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(or, DataTypesAndOperations.LOGIC);
            return result;
        } else {
            gui.println("Erro: operação 'OU' esperava valores lógicos");
            throw new Exception("Erro: operacao 'OU' esperava valores logicos");
        }
    }

    public DataTypesAndOperations checkNot(WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.LOGIC) {
            DataTypesAndOperations result = new DataTypesAndOperations<>(!(Boolean) this.getValue(), DataTypesAndOperations.LOGIC);
            return result;
        } else {
            gui.println("Erro: negação esperava um valor lógico");
            throw new Exception("Erro: negacao esperava um valor logico");
        }
    }

    //operacoes aritimeticas
    public DataTypesAndOperations checkSum(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float sum = (Float) this.getValue() + (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(sum, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            int sum = (Integer) this.getValue() + (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations(sum, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            float sum = (Float) this.getValue() + (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(sum, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float sum = (Integer) this.getValue() + (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(sum, DataTypesAndOperations.FLOAT);
            return result;
        }
        gui.println("Erro: operação adição esperava inteiro ou real");
        throw new Exception("Erro: operacao adicao esperava inteiro ou real");
    }

    public DataTypesAndOperations checkSubtraction(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float subtraction = (Float) this.getValue() - (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(subtraction, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            int subtraction = (Integer) this.getValue() - (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(subtraction, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            float subtraction = (Float) this.getValue() - (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(subtraction, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float subtraction = (Integer) this.getValue() - (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(subtraction, DataTypesAndOperations.FLOAT);
            return result;
        }
        gui.println("Erro: operação subtração esperava inteiro ou real");
        throw new Exception("Erro: operacao subtracao esperava inteiro ou real");
    }

    public DataTypesAndOperations checkMultiplication(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float multiplication = (Float) this.getValue() * (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(multiplication, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            int multiplication = (Integer) this.getValue() * (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(multiplication, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            float multiplication = (Float) this.getValue() * (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(multiplication, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float multiplication = (Integer) this.getValue() * (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(multiplication, DataTypesAndOperations.FLOAT);
            return result;
        }
        gui.println("Erro: operação multiplicação esperava inteiro ou real");
        throw new Exception("Erro: operacao multiplicacao esperava inteiro ou real");
    }

    public DataTypesAndOperations checkFloatDivision(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float division = (Float) this.getValue() / (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            float division = ((Integer) this.getValue()) / ((float) (int) secondValue.getValue());
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            float division = (Float) this.getValue() / (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float division = (Integer) this.getValue() / (float) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.FLOAT);
            return result;
        }
        gui.println("Erro: operação divisão esperava inteiro ou real");
        throw new Exception("Erro: operacao divisao esperava inteiro ou real");
    }

    public DataTypesAndOperations checkIntDivision(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            int division = (int) ((Float) this.getValue()/ (float) secondValue.getValue());
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            int division = (Integer) this.getValue() / (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            float auxCast = (Float) this.getValue();
            int division = ((int) auxCast) / (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            int division = (Integer) this.getValue() / (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(division, DataTypesAndOperations.INT);
            return result;
        }
        gui.println("Erro: operação divisão esperava inteiro ou real");
        throw new Exception("Erro: operacao divisao esperava inteiro ou real");
    }

    public DataTypesAndOperations checkPowerOf(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float floatResult = (float) Math.pow((Float) this.getValue(), (float) secondValue.getValue());
            DataTypesAndOperations result = new DataTypesAndOperations<>(floatResult, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            int intResult = (int) Math.pow((Integer) this.getValue(), (int) secondValue.getValue());
            DataTypesAndOperations result = new DataTypesAndOperations<>(intResult, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            float floatResult = (float) Math.pow((Float) this.getValue(), (int) secondValue.getValue());
            DataTypesAndOperations result = new DataTypesAndOperations<>(floatResult, DataTypesAndOperations.FLOAT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float floatResult = (float) Math.pow((Integer) this.getValue(), (float) secondValue.getValue());
            DataTypesAndOperations result = new DataTypesAndOperations<>(floatResult, DataTypesAndOperations.FLOAT);
            return result;
        }
        gui.println("Erro: potenciação esperava inteiro ou real");
        throw new Exception("Erro: potenciacao esperava inteiro ou real");
    }

    public DataTypesAndOperations checkMod(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            float auxCast1 = (Float) this.getValue();
            float auxCast2 = (float) secondValue.getValue();
            int intMod = ((int) auxCast1) % ((int) auxCast2);
            DataTypesAndOperations result = new DataTypesAndOperations<>(intMod, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            int intMod = (Integer) this.getValue() % (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(intMod, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            float auxCast = (Float) this.getValue();
            int intMod = ((int) auxCast) % (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(intMod, DataTypesAndOperations.INT);
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            int intMod = (Integer) this.getValue() % (int) secondValue.getValue();
            DataTypesAndOperations result = new DataTypesAndOperations<>(intMod, DataTypesAndOperations.INT);
            return result;
        }
        gui.println("Erro: resto esperava inteiro ou real");
        throw new Exception("Erro: resto esperava inteiro ou real");
    }

    //comparacoes
    public boolean checkEqual(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {

        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Float) this.getValue() == (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Integer) this.getValue() == (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Float) this.getValue() == (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Integer) this.getValue() == (float) secondValue.getValue();
            return result;
        }
        gui.println("Erro: comparação de igualdade esperava inteiro ou real");
        throw new Exception("Erro: comparacao de igualdade esperava inteiro ou real");
    }

    public boolean checkDifferent(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Float) this.getValue() != (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Integer) this.getValue() != (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Float) this.getValue() != (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Integer) this.getValue() != (float) secondValue.getValue();
            return result;
        }
        gui.println("Erro: diferenciação esperava inteiro ou real");
        throw new Exception("Erro: diferenciacao esperava inteiro ou real");
    }

    public boolean checkBiggerValue(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Float) this.getValue() > (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Integer) this.getValue() > (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Float) this.getValue() > (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Integer) this.getValue() > (float) secondValue.getValue();
            return result;
        }
        gui.println("Erro: comparação de valor maior esperava inteiro ou real");
        throw new Exception("Erro: comparacao de valor maior esperava inteiro ou real");
    }

    public boolean checkLesserValue(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Float) this.getValue() < (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Integer) this.getValue() < (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Float) this.getValue() < (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Integer) this.getValue() < (float) secondValue.getValue();
            return result;
        }
        gui.println("Erro: comparação de valor menor esperava inteiro ou real");
        throw new Exception("Erro: comparacao de valor menor esperava inteiro ou real");
    }

    public boolean checkBiggerOrEqualValue(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Float) this.getValue() >= (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Integer) this.getValue() >= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Float) this.getValue() >= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Integer) this.getValue() >= (float) secondValue.getValue();
            return result;
        }
        gui.println("Erro: comparação de valor igual ou maior esperava inteiro ou real");
        throw new Exception("Erro: comparacao de valor igual ou maior esperava inteiro ou real");
    }

    public boolean checkLesserOrEqualValue(DataTypesAndOperations secondValue, WindowConsole gui) throws Exception {
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Float) this.getValue() <= (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Integer) this.getValue() <= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.FLOAT && secondValue.getType() == DataTypesAndOperations.INT) {
            boolean result = (Float) this.getValue() <= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypesAndOperations.INT && secondValue.getType() == DataTypesAndOperations.FLOAT) {
            boolean result = (Integer) this.getValue() <= (float) secondValue.getValue();
            return result;
        }
        gui.println("Erro: comparação de valor igual ou mmenor esperava inteiro ou real");
        throw new Exception("Erro: comparacao de valor igual ou mmenor esperava inteiro ou real");
    }
}

