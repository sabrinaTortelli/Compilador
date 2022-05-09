package br.univali.compiladores.compilador.model.recovery;

import br.univali.compiladores.compilador.model.parser.LParserConstants;
import java.util.*;



public class RecoverySet extends HashSet {
    public RecoverySet()
    {
        super();
    }

    public RecoverySet(int t)
    {
        this.add(new Integer(t));
    }

    public boolean contains(int t)
    {
        return super.contains(new Integer(t));
    }

    public RecoverySet union(RecoverySet s)
    {
        RecoverySet t = null;

        if (s != null)
        {
            t = (RecoverySet) this.clone();
            t.addAll(s);
        }

        return t;
    }

    public RecoverySet remove(int n)
    {
        RecoverySet t = (RecoverySet) this.clone();
        t.remove(new Integer(n));

        return t; // retorna um novo conjunto, sem 1 dos elementos
    }


    @Override
    public String toString() {
        Iterator it = this.iterator();
        String s = "";
        int k;

        while (it.hasNext()) {
            k = ((Integer) it.next()).intValue();
            s += LParserConstants.tokenImage[k];
            //s += (langX.im(k) + " ");
        }

        return s;
    }
}

