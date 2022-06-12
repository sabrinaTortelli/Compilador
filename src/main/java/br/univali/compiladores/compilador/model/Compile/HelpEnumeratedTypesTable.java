package br.univali.compiladores.compilador.model.Compile;

import br.univali.compiladores.compilador.model.parser.Token;

import java.util.ArrayList;

public class HelpEnumeratedTypesTable {

    private Token id;
    private ArrayList<Token> constantsIds;

    public HelpEnumeratedTypesTable(){
        constantsIds = new ArrayList<>();
    }

    public void addList(Token constantId){
        constantsIds.add(constantId);
    }

    public ArrayList<Token> getConstantsIds() {
        return constantsIds;
    }

    public Token getId() {
        return id;
    }

    public void setId(Token id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "HelpEnumeratedTypesTable{" +
                "id=" + id +
                ", constantsIds=" + constantsIds +
                '}';
    }
}
