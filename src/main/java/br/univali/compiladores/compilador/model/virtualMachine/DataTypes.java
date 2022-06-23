package br.univali.compiladores.compilador.model.virtualMachine;

public class DataTypes<T> {
    //valores definidos por causa do REA INT =1, REAL/FLOAT = 2, LITERAL/STRING = 3
    static final int FLOAT = 2;
    static final int INT = 1;
    static final int LOGIC = 4;
    static final int LITERAL = 3;


    final private T value;
    final private int type;

    public DataTypes(T value, int type) {
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
    public DataTypes checkAnd(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.LOGIC && secondValue.getType() == DataTypes.LOGIC) {
            boolean and = (boolean) this.getValue() & (boolean) secondValue.getValue();
            DataTypes result = new DataTypes<>(and, DataTypes.LOGIC);
            return result;
        } else {
            throw new Exception("Erro: operacao 'E' esperava valores logicos");
        }
    }

    public DataTypes checkOr(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.LOGIC && secondValue.getType() == DataTypes.LOGIC) {
            boolean or = (boolean) this.getValue() | (boolean) secondValue.getValue();
            DataTypes result = new DataTypes<>(or, DataTypes.LOGIC);
            return result;
        } else {
            throw new Exception("Erro: operacao 'OU' esperava valores logicos");
        }
    }

    public DataTypes checkNot() throws Exception {
        if (this.getType() == DataTypes.LOGIC) {
            DataTypes result = new DataTypes<>(!(boolean) this.getValue(), DataTypes.LOGIC);
            return result;
        } else {
            throw new Exception("Erro: negacao esperava um valor logico");
        }
    }

    //operacoes aritimeticas
    public DataTypes checkSum(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            float sum = (float) this.getValue() + (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(sum, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            int sum = (int) this.getValue() + (int) secondValue.getValue();
            DataTypes result = new DataTypes(sum, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            float sum = (float) this.getValue() + (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(sum, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            float sum = (int) this.getValue() + (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(sum, DataTypes.FLOAT);
            return result;
        }
        throw new Exception("Erro: operacao adicao esperava inteiro ou real");
    }

    public DataTypes checkSubtraction(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            float subtraction = (float) this.getValue() - (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(subtraction, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            int subtraction = (int) this.getValue() - (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(subtraction, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            float subtraction = (float) this.getValue() - (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(subtraction, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            float subtraction = (int) this.getValue() - (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(subtraction, DataTypes.FLOAT);
            return result;
        }
        throw new Exception("Erro: operacao subtracao esperava inteiro ou real");
    }

    public DataTypes checkMultiplication(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            float multiplication = (float) this.getValue() * (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(multiplication, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            int multiplication = (int) this.getValue() * (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(multiplication, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            float multiplication = (float) this.getValue() * (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(multiplication, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            float multiplication = (int) this.getValue() * (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(multiplication, DataTypes.FLOAT);
            return result;
        }
        throw new Exception("Erro: operacao multiplicacao esperava inteiro ou real");
    }

    public DataTypes checkFloatDivision(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            float division = (float) this.getValue() / (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(division, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            float division = ((int) this.getValue()) / ((float) (int) secondValue.getValue());
            DataTypes result = new DataTypes<>(division, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            float division = (float) this.getValue() / (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(division, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            float division = (int) this.getValue() / (float) secondValue.getValue();
            DataTypes result = new DataTypes<>(division, DataTypes.FLOAT);
            return result;
        }
        throw new Exception("Erro: operacao divisao esperava inteiro ou real");
    }

    public DataTypes checkIntDivision(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            int division = (int) ((float) this.getValue()/ (float) secondValue.getValue());
            DataTypes result = new DataTypes<>(division, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            int division = (int) this.getValue() / (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(division, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            float auxCast = (float) this.getValue();
            int division = ((int) auxCast) / (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(division, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            int division = (int) this.getValue() / (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(division, DataTypes.INT);
            return result;
        }
        throw new Exception("Erro: operacao divisao esperava inteiro ou real");
    }

    public DataTypes checkPowerOf(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            float floatResult = (float) Math.pow((float) this.getValue(), (float) secondValue.getValue());
            DataTypes result = new DataTypes<>(floatResult, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            int intResult = (int) Math.pow((int) this.getValue(), (int) secondValue.getValue());
            DataTypes result = new DataTypes<>(intResult, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            float floatResult = (float) Math.pow((float) this.getValue(), (int) secondValue.getValue());
            DataTypes result = new DataTypes<>(floatResult, DataTypes.FLOAT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            float floatResult = (float) Math.pow((int) this.getValue(), (float) secondValue.getValue());
            DataTypes result = new DataTypes<>(floatResult, DataTypes.FLOAT);
            return result;
        }
        throw new Exception("Erro: potenciacao esperava inteiro ou real");
    }

    public DataTypes checkMod(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            float auxCast1 = (float) this.getValue();
            float auxCast2 = (float) secondValue.getValue();
            int intMod = (int) (auxCast1 % auxCast2);
            DataTypes result = new DataTypes<>(intMod, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            int intMod = (int) this.getValue() % (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(intMod, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            float auxCast = (float)this.getValue();
            int intMod = (int) auxCast % (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(intMod, DataTypes.INT);
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            int intMod = (int) this.getValue() % (int) secondValue.getValue();
            DataTypes result = new DataTypes<>(intMod, DataTypes.INT);
            return result;
        }
        throw new Exception("Erro: resto esperava inteiro ou real");
    }

    //comparacoes
    public boolean checkEqual(DataTypes secondValue) throws Exception {

        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (float) this.getValue() == (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            boolean result = (int) this.getValue() == (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            boolean result = (float) this.getValue() == (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (int) this.getValue() == (float) secondValue.getValue();
            return result;
        }
        throw new Exception("Erro: comparacao de igualdade esperava inteiro ou real");
    }

    public boolean checkDifferent(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (float) this.getValue() != (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            boolean result = (int) this.getValue() != (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            boolean result = (float) this.getValue() != (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (int) this.getValue() != (float) secondValue.getValue();
            return result;
        }
        throw new Exception("Erro: diferenciacao esperava inteiro ou real");
    }

    public boolean checkBiggerValue(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (float) this.getValue() > (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            boolean result = (int) this.getValue() > (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            boolean result = (float) this.getValue() > (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (int) this.getValue() > (float) secondValue.getValue();
            return result;
        }
        throw new Exception("Erro: comparacao de valor maior esperava inteiro ou real");
    }

    public boolean checkLesserValue(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (float) this.getValue() < (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            boolean result = (int) this.getValue() < (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            boolean result = (float) this.getValue() < (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (int) this.getValue() < (float) secondValue.getValue();
            return result;
        }
        throw new Exception("Erro: comparacao de valor menor esperava inteiro ou real");
    }

    public boolean checkBiggerOrEqualValue(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (float) this.getValue() >= (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            boolean result = (int) this.getValue() >= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            boolean result = (float) this.getValue() >= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (int) this.getValue() >= (float) secondValue.getValue();
            return result;
        }
        throw new Exception("Erro: comparacao de valor igual ou maior esperava inteiro ou real");
    }

    public boolean checkLesserOrEqualValue(DataTypes secondValue) throws Exception {
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (float) this.getValue() <= (float) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.INT) {
            boolean result = (int) this.getValue() <= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.FLOAT && secondValue.getType() == DataTypes.INT) {
            boolean result = (float) this.getValue() <= (int) secondValue.getValue();
            return result;
        }
        if (this.getType() == DataTypes.INT && secondValue.getType() == DataTypes.FLOAT) {
            boolean result = (int) this.getValue() <= (float) secondValue.getValue();
            return result;
        }
        throw new Exception("Erro: comparacao de valor igual ou mmenor esperava inteiro ou real");
    }
}

