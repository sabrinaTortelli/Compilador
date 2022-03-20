package br.univali.compiladores.compilador.model;

/**
 * Classe dos dados passados para as funções de análise da sentença
 */
public class Loader {

    int initialState = 0;

    public String VectorLoader(){
        String vSample = "000010010001";
        return vSample;
    }
    public int[][] TableLoader(){
        //7x12
        //99 representa movimentos não permitidos pela tabela
        int[][] tSample = {{1,99,4,6,99,11,10},{99,2,99,99,99,11,10},{3,99,99,99,99,11,10},
                {99,0,99,99,99,11,10},{99,99,5,8,99,11,10},{99,99,4,6,99,11,10},{99,99,99,99,7,11,10},
                {99,99,99,8,99,11,10},{99,99,99,99,9,11,10},{99,99,99,6,99,11,10},{99,99,99,99,99,11,10},
                {99,99,99,99,99,99,99}};
        return tSample;
    }
    public String AlphabetLoader(){
        String aSample = "abcde";
        return aSample;
    }
}
