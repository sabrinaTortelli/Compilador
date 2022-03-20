package br.univali.compiladores.compilador.model;

/**
 * Classe que analisa toda a sentença
 */
public class SentenceAnalyser {
    Loader loader = new Loader();
    int initialState = loader.initialState;
    int [][] transitionMatrix = loader.TableLoader();
    String alphabet = loader.AlphabetLoader();
    String finalStatesVector = loader.VectorLoader();
    String respUser = "";

    private boolean isFinal(int state){
        if (finalStatesVector.charAt(state)!='1') return false;
        return true;
    }

    private boolean isOperator(char test){
        if (test == '+' || test == '-' || test == '/' || test == '*') return true;
        return false;
    }

    private boolean isSymbol(char test){
        for (int i = 0; i<alphabet.length();i++){
            if (test == alphabet.charAt(i)) return true;
        }
        return false;
    }

    private boolean isSpace(char test){
        if (test == ' ')return true;
        return false;
    }

    private boolean isControlChar(String sample, char test, int i){
        if (test == '\n'|| test == '\b'|| test == '\r'|| test == '\t'||
                test == '\f'||test == '\"'|| test == '\\') return true;
        return false;
    }

    private String hasOnlyValidSymbols(String sample){
        String resp = sample;
        for (int i=0; i<sample.length();i++){
            char test = sample.charAt(i);
            if (i==0 && !isSymbol(test)) {
                respUser += "ERRO: simbolo(s) invalido(s): " + sample + "\n";
                resp = "ERRO: simbolo(s) invalido(s): " + sample + "\n";
                return resp;
            }
            if (i>0 && !isSymbol(test)){
                resp = "ERRO: sentenca invalida: " + sample + "\n";
                respUser += "ERRO: sentenca invalida: " + sample + "\n";
                return resp;
            }
        }
        return resp;

    }

    public void tokenChecker(String sample){
        String sentence="";
        for (int i=0; i< sample.length(); i++){
            char test = sample.charAt(i);

            for(int j = 0; j<1; j++){
                if (isOperator(test)){
                    if(!sentence.isEmpty() && hasOnlyValidSymbols(sentence).equals(sentence)){
                        isValidSentence(sentence);
                    }
                    respUser += "Operador Aritimetico: " + test + "\n";
                    sentence="";
                    break;
                }
                if (isSpace(test)){
                    if (!sentence.isEmpty() && hasOnlyValidSymbols(sentence).equals(sentence)) {
                        isValidSentence(sentence);
                        sentence="";
                        break;
                    }else{
                        sentence="";
                        break;
                    }
                }
                if(isControlChar(sample,test,i)){
                    if(!sentence.isEmpty()&& hasOnlyValidSymbols(sentence).equals(sentence)){
                        isValidSentence(sentence);
                        sentence="";
                        break;
                    }else {
                        sentence="";
                        break;
                    }
                }
                else {
                    sentence = sentence+test;
                }
            }
        }
        if (!sentence.isEmpty()&&hasOnlyValidSymbols(sentence).equals(sentence)){
            isValidSentence(sentence);
            sentence = "";
        }else sentence="";
    }

    private String isValidSentence (String sentence){
        int state = initialState;

        for (int i=0; i< sentence.length(); i++){
            char test = sentence.charAt(i);
            int symbol;

            symbol =alphabet.indexOf(test);
            int newState = transitionMatrix[state][symbol];
            state = newState;
            //99 - move not allowed by transitionMatrix
            if(state == 99) break;
            if (isFinal(state) && (i+1==sentence.length())){
                respUser += "Sentenca Valida: " + sentence + "\n";
                return "Sentenca Valida: " + sentence;
            }
        }
        respUser += "ERRO: sentenca invalida: " + sentence + "\n";
        return "ERRO: sentenca invalida: " + sentence;
    }

    public String getRespUser() {
        return respUser;
    }
}
