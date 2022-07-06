package br.univali.compiladores.compilador.model.Compile;

import br.univali.compiladores.compilador.model.parser.Token;

import java.util.*;

public class SemanticActions {

    private int semErrorCount;
    private HelpTableSymbol helpSymbol;
    private HelpInstructionTable helpInstruction;
    private ArrayList<HelpTableSymbol> symbolTable;
    private int vt;
    private int vp;
    private int vi;
    private String context;
    private String output;
    private int tvi;
    private int type;
    private int pointer;
    private ArrayList<HelpInstructionTable> instructionList;
    private String printSemanticError;
    private String error;
    private Hashtable<String, HelpEnumeratedTypesTable> enumeratedTypesTables;
    private HelpEnumeratedTypesTable helpEnumerated;
    private Token value;
    private int constInt;
    private Token tokenConstInt;
    private boolean indexVariable;
    private Token identifier;
    private ArrayList<Integer> attributesList;
    private Stack<Integer> stackDeviations;

    private ArrayList<String> constantsList;

    private boolean tokenAlredyDeclared;

    public String getPrintSemanticError() {
        return printSemanticError;
    }

    public ArrayList<HelpInstructionTable> getInstructionList() {
        return instructionList;
    }

    public int getSemErrorCount() {
        return semErrorCount;
    }

    public void initSemantic(){
        symbolTable = new ArrayList<>();
        semErrorCount = 0;
        vt = 0;
        vp = 0;
        vi = 0;
        tvi = 0;
        pointer = 1;
        context = "";
        instructionList = new ArrayList<>();
        printSemanticError = "";
        error = "";
        helpEnumerated = new HelpEnumeratedTypesTable();
        enumeratedTypesTables = new Hashtable<>();
        attributesList = new ArrayList<>();
        stackDeviations = new Stack<>();
        indexVariable = false;
        tokenAlredyDeclared = false;
        constantsList = new ArrayList<>();
    }

    public int getPointer() {
        return pointer;
    }

    //Reconhecimento do identificador do programa
    public void trigger1(Token t1){
        initSemantic();
        System.out.println("Entrou no trigger 1:" + t1);
        helpSymbol = new HelpTableSymbol(t1,0, -1, -1);
        symbolTable.add(helpSymbol);
        System.out.println("SymbolTable trigger1 : " + symbolTable);
    }

    //Reconhecimento de fim do programa
    public void trigger2(){
        System.out.println("Entrou no trigger 2 - fim do programa");
        helpInstruction = new HelpInstructionTable(getPointer(), "STP", 0);
        instructionList.add(helpInstruction);
        System.out.println(instructionList);
    }

    //Reconhecimento de identificador de tipo enumerado
    public void trigger3(Token t1){
        System.out.println("Entrou no trigger 3:" + t1);
        boolean found = false;
        for(int i=0; i< symbolTable.size(); i++) {
            if (symbolTable.get(i).getToken().image.equals(t1.image) || enumeratedTypesTables.containsKey(t1.image)) {
                semErrorCount++;
                error = "identificador já declarado";
                buildPrintSemanticError(error, t1);
                tokenAlredyDeclared = true;
                found = true;
                break;
            }
        }
        if(!found){
            helpEnumerated = new HelpEnumeratedTypesTable();
            helpEnumerated.setId(t1.image);
            enumeratedTypesTables.put(t1.image, helpEnumerated);
            tokenAlredyDeclared = false;
            System.out.println("Table Enumerated Type trigger3: " + enumeratedTypesTables);
        }
    }

    //Reconhecimento do identificador de constante de tipo enumerado
    public void trigger4(Token t1){
        System.out.println("Entrou no trigger 4:" + t1);
        boolean found = false;
        for(int i=0; i< symbolTable.size(); i++) {
            if (symbolTable.get(i).getToken().image.equals(t1.image) || enumeratedTypesTables.containsKey(t1.image)
                    || constantsList.contains(t1.image)) {
                semErrorCount++;
                error = "identificador já declarado";
                buildPrintSemanticError(error, t1);
                found = true;
                break;
            }
        }
        if(!found && !tokenAlredyDeclared){
            helpEnumerated.addList(t1.image);
            enumeratedTypesTables.put(helpEnumerated.getId(), helpEnumerated);
            constantsList.add(t1.image);
            System.out.println("Table Enumerated Type trigger4: " + enumeratedTypesTables);
        }
    }

    //Reconhecimento das palavras reservadas as constant
    public void trigger5(String s1){
        System.out.println("Entrou no trigger 5:" + s1);
        context = s1;
        vi = 0;
        tvi = 0;
    }

    //Reconhecimento do término da declaração de constantes ou variáveis de um determinado tipo
    public void trigger6(){
        System.out.println("Entrou no trigger 6");
        int n= vp + vi;
        //int cont = 1;
        System.out.println("N: " + n);
        for(int i=symbolTable.size()-1; i>=symbolTable.size()-n ; i--){
            System.out.println(symbolTable.get(i).getToken());
            symbolTable.get(i).setCategory(type);
        }
        vp = vp + tvi;
        switch (type) {
            case 1:
            case 5:
                helpInstruction = new HelpInstructionTable(getPointer(), "ALI", vp);
                instructionList.add(helpInstruction);
                pointer++;
                break;
            case 2:
            case 6:
                helpInstruction = new HelpInstructionTable(getPointer(), "ALR", vp);
                instructionList.add(helpInstruction);
                pointer++;
                break;
            case 3:
            case 7:
                helpInstruction = new HelpInstructionTable(getPointer(), "ALS", vp);
                instructionList.add(helpInstruction);
                pointer++;
                break;
            case 4:
                helpInstruction = new HelpInstructionTable(getPointer(), "ALB", vp);
                instructionList.add(helpInstruction);
                pointer++;
                break;
        }
        if((type == 1) || (type == 2) || (type == 3) || (type == 4)){
            vp = 0;
            vi = 0;
            tvi = 0;
        }
    }

    //Reconhecimento de valor na declaração de constante
    public void trigger7(){
        System.out.println("Entrou no trigger 7");
        switch (type){
            case 5:
                helpInstruction = new HelpInstructionTable(getPointer(), "LDI", value);
                instructionList.add(helpInstruction);
                pointer++;
                break;
            case 6:
                helpInstruction = new HelpInstructionTable(getPointer(), "LDR", value);
                instructionList.add(helpInstruction);
                pointer++;
                break;
            case 7:
                helpInstruction = new HelpInstructionTable(getPointer(), "LDS", value);
                instructionList.add(helpInstruction);
                pointer++;
                break;
        }
        //STC tá aqui
        helpInstruction = new HelpInstructionTable(getPointer(), "STC", vp);
        instructionList.add(helpInstruction);
        pointer++;
        vp = 0;
    }

    //Reconhecimento das palavras reservadas as variable
    public void trigger8(String s1){
        System.out.println("Entrou no trigger 8:" + s1);
        context = s1;
    }

    //Reconhecimento de identificador de constante
    public void trigger9(Token t1){
        System.out.println("Entrou no trigger 9:" + t1);
        boolean found = false;
        for(int i=0; i< symbolTable.size(); i++) {
            if (symbolTable.get(i).getToken().image.equals(t1.image) || enumeratedTypesTables.containsKey(t1.image)
                    || constantsList.contains(t1.image)) {
                semErrorCount++;
                error = "identificador já declarado";
                buildPrintSemanticError(error, t1);
                found = true;
                break;
            }
        }
        if(!found){
            vt = vt + 1;
            vp = vp + 1;
            helpSymbol = new HelpTableSymbol(t1, -1, vt, -1);
            symbolTable.add(helpSymbol);
            System.out.println("SymbolTable trigger9 : " + symbolTable);
        }
    }

    //Reconhecimento de identificador de variável
    public void trigger10(Token t1){
        System.out.println("Entrou no trigger 10:" + t1);
        switch (context){
            case "as variable":
                boolean found = false;
                for(int i=0; i< symbolTable.size(); i++) {
                    if (symbolTable.get(i).getToken().image.equals(t1.image) || enumeratedTypesTables.containsKey(t1)
                            || helpEnumerated.getConstantsIds().contains(t1)) {
                        semErrorCount++;
                        error = "identificador já declarado";
                        buildPrintSemanticError(error, t1);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    indexVariable = false;
                    identifier = t1;
                }
                break;
            case "atribuicao":
            case "entrada dados":
                indexVariable = false;
                identifier = t1;
                break;
        }
    }

    //Reconhecimento de identificador de variável e tamanho da variável indexada
    public void trigger11(){
        System.out.println("Entrou no trigger 11");
        int attribute = 0;
        int sizeVector = -1;
        int category = 0;
        boolean found = false;
        switch (context){
            case "as variable":
                if(!indexVariable) {
                    vt = vt + 1;
                    vp = vp + 1;
                    helpSymbol = new HelpTableSymbol(identifier,-1, vt, -1);
                    symbolTable.add(helpSymbol);
                    System.out.println("SymbolTable trigger11 : " + symbolTable);
                } else {
                    vi = vi + 1;
                    tvi = tvi + constInt;
                    helpSymbol = new HelpTableSymbol(identifier,-1, vt + 1, constInt);
                    symbolTable.add(helpSymbol);
                    vt = vt + constInt;
                    System.out.println("SymbolTable trigger11 : " + symbolTable);
                }
                break;
            case "atribuicao":
                for(int i=0; i< symbolTable.size(); i++) {
                    if (symbolTable.get(i).getToken().image.equals(identifier.image) &&
                            (symbolTable.get(i).getCategory() == 1 ||
                                    symbolTable.get(i).getCategory() == 2 ||
                                    symbolTable.get(i).getCategory() == 3 ||
                                    symbolTable.get(i).getCategory() == 4)) {
                        attribute = symbolTable.get(i).getAttribute();
                        sizeVector = symbolTable.get(i).getSizeVector();
                        found = true;
                        break;
                    }
                }
                if (sizeVector == -1 && found) {
                    if (!indexVariable) {
                        attributesList.add(attribute);
                    } else {
                        semErrorCount++;
                        error = "identificador de variável não indexada";
                        buildPrintSemanticError(error, tokenConstInt);
                    }
                } else if (sizeVector != -1 && found) {
                    if (indexVariable) {
                        attributesList.add(attribute + constInt - 1);
                    } else {
                        semErrorCount++;
                        error = "identificador de variável exige índice";
                        buildPrintSemanticError(error, tokenConstInt);
                    }
                }
                else if(!found){
                    semErrorCount++;
                    error = "identificador não declarado ou identificador de programa, de constante ou de tipo enumerado";
                    buildPrintSemanticError(error, identifier);
                }
                break;
            case "entrada dados":
                System.out.println(symbolTable.size());
                System.out.println(found);
                for(int i=0; i< symbolTable.size(); i++) {
                    System.out.println(symbolTable.get(i).getToken().image);
                    System.out.println(symbolTable.get(i).getCategory());
                    System.out.println("Identifier: " + identifier);
                    if (symbolTable.get(i).getToken().image.equals(identifier.image) &&
                            (symbolTable.get(i).getCategory() == 1 ||
                                    symbolTable.get(i).getCategory() == 2 ||
                                    symbolTable.get(i).getCategory() == 3 ||
                                    symbolTable.get(i).getCategory() == 4)) {
                        attribute = symbolTable.get(i).getAttribute();
                        sizeVector = symbolTable.get(i).getSizeVector();
                        category = symbolTable.get(i).getCategory();
                        found = true;
                        break;
                    } else {
                        System.out.println("Passou por aqui?");
                    }
                }
                System.out.println("SizeVector: " + sizeVector);
                if (sizeVector == -1 && found) {
                    System.out.println("Entrou no size vector");
                    System.out.println("IndexVariable: " + indexVariable);
                    if (!indexVariable) {
                        System.out.println("Entrou no if");
                        helpInstruction = new HelpInstructionTable(getPointer(), "REA", category);
                        instructionList.add(helpInstruction);
                        pointer++;
                        helpInstruction = new HelpInstructionTable(getPointer(), "STR", attribute);
                        instructionList.add(helpInstruction);
                        pointer++;
                    } else {
                        System.out.println("Entrou no else");
                        semErrorCount++;
                        error = "identificador de variável não indexada";
                        buildPrintSemanticError(error, tokenConstInt);
                    }
                } else if (sizeVector != -1 && found) {
                    System.out.println("IndexVariable: " + indexVariable);
                    if (indexVariable) {
                        helpInstruction = new HelpInstructionTable(getPointer(), "REA", category);
                        instructionList.add(helpInstruction);
                        pointer++;
                        helpInstruction = new HelpInstructionTable(getPointer(), "STR", attribute + constInt -1);
                        instructionList.add(helpInstruction);
                        pointer++;
                    } else {
                        semErrorCount++;
                        System.out.println();
                        error = "identificador de variável exige índice";
                        buildPrintSemanticError(error, tokenConstInt);
                    }
                }
                else if(!found){
                    semErrorCount++;
                    System.out.println(semErrorCount);
                    error = "identificador não declarado ou identificador de programa, de constante ou de tipo enumerado";
                    System.out.println(error);
                    buildPrintSemanticError(error, identifier);
                }
                break;
        }
    }

    //Reconhecimento de constante inteira como tamanho da variável indexada
    public void trigger12(Token t1){
        System.out.println("Entrou no trigger 12:" + t1);
        tokenConstInt = t1;
        Object temp = t1.getValue();
        constInt = (int) temp;
        System.out.println("Constante inteira lida em trigger12: " + constInt);
        indexVariable = true;
    }

    //Reconhecimento da palavra reservada integer
    public void trigger13(){
        System.out.println("Entrou no trigger 13");
        if(context.equals("as variable")){
            type = 1;
        } else {
            type = 5;
        }
        System.out.println("type: " + type);
    }

    //Reconhecimento da palavra reservada float
    public void trigger14(){
        System.out.println("Entrou no trigger 14");
        if(context.equals("as variable")){
            type = 2;
        } else {
            type = 6;
        }
        System.out.println("type: " + type);
    }

    //Reconhecimento da palavra reservada string
    public void trigger15(){
        System.out.println("Entrou no trigger 15");
        if(context.equals("as variable")){
            type = 3;
        } else {
            type = 7;
        }
        System.out.println("type: " + type);
    }

    //Reconhecimento da palavra reservada boolean
    //Incluir Token no trigger 16 -> apontar para a palavra LOGIC
    public void trigger16(Token t1){
        System.out.println("Entrou no trigger 16");
        if(context.equals("as variable")){
            type = 4;
        } else {
            semErrorCount++;
            error = "tipo inválido para constante";
            buildPrintSemanticError(error, t1);
        }
        System.out.println("type: " + type);
    }

    //Reconhecimento de identificador de tipo enumerado
    //Incluir Token no trigger 17 -> apontar para a palavra identificador
    public void trigger17(Token t1){
        System.out.println("Entrou no trigger 17");
        if(context.equals("as variable")){
            type = 1;
        } else {
            semErrorCount++;
            error = "tipo inválido para constante";
            buildPrintSemanticError(error, t1);
        }
        System.out.println("type: " + type);
    }

    //Reconhecimento do comando de atribuição
    public void trigger18(String s1){
        System.out.println("Entrou no trigger 18: " + s1);
        context = s1;
    }

    //Reconhecimento do fim do comando de atribuição
    public void trigger19(){
        System.out.println("Entrou no trigger 19");
        for(int i=0; i<attributesList.size(); i++){
            helpInstruction = new HelpInstructionTable(getPointer(), "STR", attributesList.get(i));
            instructionList.add(helpInstruction);
            pointer++;
            //valor do topo não deverá ser decrementado para cada instrução STR gerada, exceto para a última
            //Alterar na máquina virtual
        }

    }

    //Reconhecimento do comando de entrada de dados
    public void trigger20(String s1){
        System.out.println("Entrou no trigger 20: " + s1);
        context = s1;
    }

    //Reconhecimento das palavras reservadas "write all this"
    public void trigger21(String s1){
        System.out.println("Entrou no trigger 21: " + s1);
        output = s1;
    }

    //Reconhecimento das palavras reservadas "write this"
    public void trigger22(String s1){
        System.out.println("Entrou no trigger 22: " + s1);
        output = s1;
    }

    //Reconhecimento de mensagem em comando de saída de dados
    public void trigger23(){
        System.out.println("Entrou no trigger 23");
        helpInstruction = new HelpInstructionTable(getPointer(), "WRT", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de identificador em comando de saída ou em expressão
    public void trigger24(Token t1){
        System.out.println("Entrou no trigger 24:" + t1);
        boolean found = false;
        for(int i=0; i< symbolTable.size(); i++) {
            if (symbolTable.get(i).getToken().image.equals(t1.image) && symbolTable.get(i).getCategory() > 0) {
                indexVariable = false;
                identifier = t1;
                found = true;
                break;
            }
        }
        if(!found) {
            semErrorCount++;
            error = "identificador não declarado, identificador de programa ou de tipo enumerado";
            buildPrintSemanticError(error, t1);
        }
    }

    //Reconhecimento de identificador de constante ou de variável e tamanho de variável indexada em comando de saída
    public void trigger25(Token t1){
        System.out.println("Entrou no trigger 25:" + t1);
        int attribute = 0;
        int sizeVector = -1;
        for(int i=0; i< symbolTable.size(); i++) {
            if (symbolTable.get(i).getToken().image.equals(t1.image)) {
                attribute = symbolTable.get(i).getAttribute();
                sizeVector = symbolTable.get(i).getSizeVector();
                break;
            }
        }
        System.out.println("IndexVariable: " +indexVariable);
        System.out.println("sizeVector: " +sizeVector );
        if(!indexVariable){
            if(sizeVector == -1){
                if(output.equals("write all this")){
                    System.out.println("Entrou no if do sizeVector == -1");
                    helpInstruction = new HelpInstructionTable(getPointer(), "LDS", ("\"" + identifier + "=\""));
                    instructionList.add(helpInstruction);
                    pointer++;
                    helpInstruction = new HelpInstructionTable(getPointer(), "WRT", 0);
                    instructionList.add(helpInstruction);
                    pointer++;
                }
                helpInstruction = new HelpInstructionTable(getPointer(), "LDV", attribute);
                instructionList.add(helpInstruction);
                pointer++;
            } else {
                semErrorCount++;
                error = "identificador de variável indexada exige índice";
                buildPrintSemanticError(error, t1);
            }
        } else {
            if(sizeVector != -1){
                if(output.equals("write all this")){
                    System.out.println("Entrou no else e if do sizeVector != -1");
                    helpInstruction = new HelpInstructionTable(getPointer(), "LDS", ("\"" + identifier + "=\""));
                    instructionList.add(helpInstruction);
                    pointer++;
                    helpInstruction = new HelpInstructionTable(getPointer(), "WRT", 0);
                    instructionList.add(helpInstruction);
                    pointer++;
                }
                helpInstruction = new HelpInstructionTable(getPointer(), "LDV", attribute + constInt -1);
                instructionList.add(helpInstruction);
                pointer++;
            } else {
                semErrorCount++;
                error = "identificador de constante ou de variável não indexada";
                buildPrintSemanticError(error, t1);
            }
        }
    }

    //Reconhecimento de constante inteira em comando de saída ou em expressão
    public void trigger26(Token t1){
        System.out.println("Entrou no trigger 26:" + t1);
        helpInstruction = new HelpInstructionTable(getPointer(), "LDI", t1);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de constante real em comando de saída ou em expressão
    public void trigger27(Token t1){
        System.out.println("Entrou no trigger 27:" + t1);
        helpInstruction = new HelpInstructionTable(getPointer(), "LDR", t1);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de constante literal em comando de saída ou em expressão
    public void trigger28(Token t1){
        System.out.println("Entrou no trigger 28:" + t1);
        helpInstruction = new HelpInstructionTable(getPointer(), "LDS", t1);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de fim de comando de seleção
    public void trigger29(){
        System.out.println("Entrou no trigger 29");
        int address = stackDeviations.pop();
        for(int i=0; i<instructionList.size(); i++){
            if(instructionList.get(i).getInstruction() == address){
                instructionList.get(i).setParameter(getPointer());
                break;
            }
        }
    }

    //Reconhecimento da palavra reservada true
    public void trigger30(){
        System.out.println("Entrou no trigger 30");
        helpInstruction = new HelpInstructionTable(getPointer(), "JMF", -1);
        instructionList.add(helpInstruction);
        pointer++;
        stackDeviations.push(getPointer()-1);
    }

    //Reconhecimento da palavra reservada untrue
    public void trigger31(){
        System.out.println("Entrou no trigger 31");
        helpInstruction = new HelpInstructionTable(getPointer(), "JMT", -1);
        instructionList.add(helpInstruction);
        pointer++;
        stackDeviations.push(getPointer()-1);
    }

    //Reconhecimento da palavra reservada untrue (ou true)
    public void trigger32(){
        System.out.println("Entrou no trigger 32");
        int address = stackDeviations.pop();
        for(int i=0; i<instructionList.size(); i++){
            if(instructionList.get(i).getInstruction() == address){
                instructionList.get(i).setParameter(getPointer()+1);
                break;
            }
        }
        helpInstruction = new HelpInstructionTable(getPointer(), "JMP", -1);
        instructionList.add(helpInstruction);
        pointer++;
        stackDeviations.push(getPointer()-1);
    }

    //Reconhecimento do início de expressão em comando de repetição
    public void trigger33(){
        System.out.println("Entrou no trigger 33");
        stackDeviations.push(getPointer());
    }

    //Reconhecimento de expressão em comando de repetição
    public void trigger34(){
        System.out.println("Entrou no trigger 34");
        helpInstruction = new HelpInstructionTable(getPointer(), "JMF", -1);
        instructionList.add(helpInstruction);
        pointer++;
        stackDeviations.push(getPointer()-1);
    }

    //Reconhecimento do fim do comando de repetição
    public void trigger35(){
        System.out.println("Entrou no trigger 35");
        int address = stackDeviations.pop();
        for(int i=0; i<instructionList.size(); i++){
            if(instructionList.get(i).getInstruction() == address){
                instructionList.get(i).setParameter(getPointer()+1);
            }
        }
        address = stackDeviations.pop();
        helpInstruction = new HelpInstructionTable(getPointer(), "JMP", address);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação relacional igual
    public void trigger36(){
        System.out.println("Entrou no trigger 36:");
        helpInstruction = new HelpInstructionTable(getPointer(), "EQL", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação relacional diferente
    public void trigger37(){
        System.out.println("Entrou no trigger 37:");
        helpInstruction = new HelpInstructionTable(getPointer(), "DIF", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação relacional menor que
    public void trigger38(){
        System.out.println("Entrou no trigger 38:");
        helpInstruction = new HelpInstructionTable(getPointer(), "SMR", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação relacional maior que
    public void trigger39(){
        System.out.println("Entrou no trigger 39:");
        helpInstruction = new HelpInstructionTable(getPointer(), "BGR", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação relacional menor ou igual que
    public void trigger40(){
        System.out.println("Entrou no trigger 40:");
        helpInstruction = new HelpInstructionTable(getPointer(), "SME", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação relacional maior ou igual que
    public void trigger41(){
        System.out.println("Entrou no trigger 41:");
        helpInstruction = new HelpInstructionTable(getPointer(), "BGE", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação adição
    //Tirar token
    public void trigger42(){
        System.out.println("Entrou no trigger 42:");
        helpInstruction = new HelpInstructionTable(getPointer(), "ADD", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação subtração
    public void trigger43(){
        System.out.println("Entrou no trigger 43:");
        helpInstruction = new HelpInstructionTable(getPointer(), "SUB", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação |
    public void trigger44(){
        System.out.println("Entrou no trigger 44:");
        helpInstruction = new HelpInstructionTable(getPointer(), "OR", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação *
    public void trigger45(){
        System.out.println("Entrou no trigger 45:");
        helpInstruction = new HelpInstructionTable(getPointer(), "MUL", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação /
    public void trigger46(){
        System.out.println("Entrou no trigger 46:");
        helpInstruction = new HelpInstructionTable(getPointer(), "DIV", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação % - divisão inteira
    //Não implementada - Implementar dividão inteira em máquina virtual --> incluir operação DVI
    public void trigger47(){
        System.out.println("Entrou no trigger 47:");
        helpInstruction = new HelpInstructionTable(getPointer(), "DVI", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação %% - mod
    //Não implementada - Implementar dividão inteira em máquina virtual --> incluir operação MOD
    public void trigger48(){
        System.out.println("Entrou no trigger 48:");
        helpInstruction = new HelpInstructionTable(getPointer(), "MOD", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação &
    public void trigger49(){
        System.out.println("Entrou no trigger 49:");
        helpInstruction = new HelpInstructionTable(getPointer(), "AND", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação ** - power
    //Não implementada - Implementar dividão inteira em máquina virtual --> incluir operação POW
    public void trigger50(){
        System.out.println("Entrou no trigger 50");
        helpInstruction = new HelpInstructionTable(getPointer(), "POW", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de identificador de constante ou de variável e tamanho de variável indexada em expressão
    public void trigger51(Token t1){
        System.out.println("Entrou no trigger 51");
        int attribute = 0;
        int sizeVector = 0;
        for(int i=0; i< symbolTable.size(); i++) {
            if (symbolTable.get(i).getToken().image.equals(t1.image)) {
                attribute = symbolTable.get(i).getAttribute();
                sizeVector = symbolTable.get(i).getSizeVector();
                break;
            }
        }
        if(!indexVariable){
            if(sizeVector == -1){
                helpInstruction = new HelpInstructionTable(getPointer(), "LDV", attribute);
                instructionList.add(helpInstruction);
                pointer++;
            } else{
                semErrorCount++;
                error = "identificador de variável indexada exige índice";
                buildPrintSemanticError(error, identifier);
            }
        } else {
            if(sizeVector != -1){
                helpInstruction = new HelpInstructionTable(getPointer(), "LDV", attribute + constInt -1);
                instructionList.add(helpInstruction);
                pointer++;
            } else{
                semErrorCount++;
                error = "identificador de constante ou de variável não indexada";
                buildPrintSemanticError(error, identifier);
            }
        }

    }

    //Reconhecimento de constante lógica true
    public void trigger52(){
        System.out.println("Entrou no trigger 52");
        helpInstruction = new HelpInstructionTable(getPointer(), "LDB", true);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de constante lógica untrue
    public void trigger53(){
        System.out.println("Entrou no trigger 53");
        helpInstruction = new HelpInstructionTable(getPointer(), "LDB", false);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecimento de operação lógica NÃO ( ! )
    public void trigger54(){
        System.out.println("Entrou no trigger 54");
        helpInstruction = new HelpInstructionTable(getPointer(), "NOT", 0);
        instructionList.add(helpInstruction);
        pointer++;
    }

    //Reconhecer o valor da constante inteira
    public void trigger55(Token t1){
        System.out.println("Entrou no trigger 55" + t1);
        value = t1;
    }

    //Reconhecer o valor da constante real
    public void trigger56(Token t1){
        System.out.println("Entrou no trigger 56" + t1);
        value = t1;
    }

    //Reconhecer o valor da constante literal
    public void trigger57(Token t1){
        System.out.println("Entrou no trigger 57" + t1);
        value = t1;
    }

    public void buildPrintSemanticError(String error, Token t) {
        printSemanticError += "----------------------------------------ERRO SEMÂNTICO----------------------------------\n\n" +
                "Erro: " + error + "\n" +
                "Linha: " + t.beginLine + "\n" +
                "Coluna: " + t.beginColumn + "\n\n";
    }
}
