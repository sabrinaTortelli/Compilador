package br.univali.compiladores.compilador.model.Compile;

import br.univali.compiladores.compilador.model.parser.Token;

import java.util.ArrayList;

public class HelpEnumeratedTypesTable {

    private String id;
    private ArrayList<String> constantsIds;

    public HelpEnumeratedTypesTable(){
        constantsIds = new ArrayList<>();
    }

    public void addList(String constantId){
        constantsIds.add(constantId);
    }

    public ArrayList<String> getConstantsIds() {
        return constantsIds;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
