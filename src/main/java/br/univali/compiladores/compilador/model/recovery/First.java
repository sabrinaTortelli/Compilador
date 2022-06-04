package br.univali.compiladores.compilador.model.recovery;

import br.univali.compiladores.compilador.model.*;
import br.univali.compiladores.compilador.model.parser.LParserConstants;

import java.util.*;

public class First {

    static public final RecoverySet Comment = new RecoverySet();
    static public final RecoverySet ProgramBody = new RecoverySet();
    static public final RecoverySet ConstantsLL = new RecoverySet();
    static public final RecoverySet VariablesLL = new RecoverySet();
    static public final RecoverySet VariablesIdentifiersListL = new RecoverySet();
    static public final RecoverySet CommandListL = new RecoverySet();
    static public final RecoverySet IndentifierAndOrContantListL = new RecoverySet();
    static public final RecoverySet CmdSelectionL = new RecoverySet();
    static public final RecoverySet ExpressionL = new RecoverySet();
    static public final RecoverySet LowestPriority = new RecoverySet();
    static public final RecoverySet MediumPriority = new RecoverySet();
    static public final RecoverySet HighestPriority = new RecoverySet();

    static {

    }

}

