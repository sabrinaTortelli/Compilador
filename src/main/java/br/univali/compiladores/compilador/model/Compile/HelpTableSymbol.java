package br.univali.compiladores.compilador.model.Compile;

import br.univali.compiladores.compilador.model.parser.Token;

public class HelpTableSymbol {

    private Token token;
    private int category;
    private int attribute;
    private int sizeVector;

    public HelpTableSymbol(Token token, int category, int attribute, int sizeVector){
        this.token = token;
        this.category = category;
        this.attribute = attribute;
        this.sizeVector = sizeVector;
    }

//    public HelpTableSymbol(int category){
//        this.category = category;
//    }
//
//    public HelpTableSymbol(int category, int attribute){
//        this.category = category;
//        this.attribute = attribute;
//    }
//
//    public HelpTableSymbol(int category, int attribute, int sizeVector){
//        this.category = category;
//        this.attribute = attribute;
//        this.sizeVector = sizeVector;
//    }

    public Token getToken() {
        return token;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getAttribute() {
        return attribute;
    }

    public int getCategory() {
        return category;
    }

    public int getSizeVector() {
        return sizeVector;
    }

    @Override
    public String toString() {
        return "HelpTableSymbol{" +
                "token=" + token.image +
                ", category=" + category +
                ", attribute=" + attribute +
                ", sizeVector=" + sizeVector +
                '}';
    }
}
