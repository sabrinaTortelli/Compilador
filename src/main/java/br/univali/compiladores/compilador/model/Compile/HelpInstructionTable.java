package br.univali.compiladores.compilador.model.Compile;

public class HelpInstructionTable {

    private int instruction;
    private String code;
    private Object parameter;

    public HelpInstructionTable(int instruction, String code, Object parameter){
        this.instruction = instruction;
        this.code = code;
        this.parameter = parameter;
    }

    public Object getParameter() {
        return parameter;
    }

    public int getInstruction() {
        return instruction;
    }

    public String getCode() {
        return code;
    }

    public void setParameter(Object parameter) {
        this.parameter = parameter;
    }
}
