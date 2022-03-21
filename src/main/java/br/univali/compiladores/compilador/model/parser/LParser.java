package br.univali.compiladores.compilador.model.parser;

import br.univali.compiladores.compilador.model.LexicalAnalysis.LexicalAnalysis;
import br.univali.compiladores.compilador.view.WindowER;

/* Generated By:JavaCC: Do not edit this line. LParser.java */
public class LParser implements LParserConstants {

    private LexicalAnalysis lexicalAnalysis;
    private int errorCount;
    public int getErrorCount() {
        return errorCount;
    }

  final public void parseLexical() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SYMBOL:
      case RESERVED_WORD:
      case CONST_INT:
      case CONST_REAL:
      case CONST_LITERAL:
      case IDENTIFIER:
      case NONSUPPORTED_GRAMMAR:
      case NONSUPPORTED_CONST_LITERAL:
      case NONSUPPORTED_BLOCK_COMMENT:
        ;
        break;
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NONSUPPORTED_BLOCK_COMMENT:
        nonsupportedBlockComment();
        break;
      case SYMBOL:
        symbol();
        break;
      case RESERVED_WORD:
        reservedWord();
        break;
      case CONST_INT:
        constInt();
        break;
      case CONST_REAL:
        constReal();
        break;
      case CONST_LITERAL:
        constLiteral();
        break;
      case IDENTIFIER:
        identifier();
        break;
      case NONSUPPORTED_GRAMMAR:
        nonsupportedGrammar();
        break;
      case NONSUPPORTED_CONST_LITERAL:
        nonsupportedConstLiteral();
        break;
      default:
        jj_la1[1] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  final public void symbol() throws ParseException {
    jj_consume_token(SYMBOL);
    lexicalAnalysis.printRecognizedToken(token.image, "Símbolo", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Símbolo: " + token.image);
  }

  final public void reservedWord() throws ParseException {
    jj_consume_token(RESERVED_WORD);
    lexicalAnalysis.printRecognizedToken(token.image, "Palavra Reservada", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Palavra Reservada: " + token.image);

  }

  final public void constInt() throws ParseException {
    jj_consume_token(CONST_INT);
    lexicalAnalysis.printRecognizedToken(token.image, "Constante Inteira", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Constante Inteira: " + token.image);

  }

  final public void constReal() throws ParseException {
    jj_consume_token(CONST_REAL);
    lexicalAnalysis.printRecognizedToken(token.image, "Constante Real", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Constante Real: " + token.image);

  }

  final public void constLiteral() throws ParseException {
    jj_consume_token(CONST_LITERAL);
    lexicalAnalysis.printRecognizedToken(token.image, "Constante Literal", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Constante Literal: " + token.image);

  }

  final public void identifier() throws ParseException {
    jj_consume_token(IDENTIFIER);
    lexicalAnalysis.printRecognizedToken(token.image, "Identificador", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Identificador: " + token.image);

  }

  final public void nonsupportedGrammar() throws ParseException {
      errorCount++;
    jj_consume_token(NONSUPPORTED_GRAMMAR);
    lexicalAnalysis.printNotRecognizedToken(token.image, "Gramática não suportada", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Gramática não suportada: " + token.image);

  }

  final public void nonsupportedConstLiteral() throws ParseException {
                                    errorCount++;
    jj_consume_token(NONSUPPORTED_CONST_LITERAL);
    lexicalAnalysis.printNotRecognizedToken(token.image, "Constante literal não suportada", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Constante literal não suportada: " + token.image);

  }

  final public void nonsupportedBlockComment() throws ParseException {
                                    errorCount++;
    jj_consume_token(NONSUPPORTED_BLOCK_COMMENT);
    lexicalAnalysis.printNotRecognizedToken(token.image, "Não contém o fechamento do bloco de comentário", token.kind, token.beginLine, token.beginColumn);
    System.out.println("Fechamento do Bloco: " + token.image);

  }

  /** Generated Token Manager. */
  public LParserTokenManager token_source;
  SimpleCharStream jj_input_stream;
  /** Current token. */
  public Token token;
  /** Next token. */
  public Token jj_nt;
  private int jj_ntk;
  private int jj_gen;
  final private int[] jj_la1 = new int[2];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0xfe180,0xfe180,};
   }

  /** Constructor with InputStream. */
  public LParser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public LParser(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new LParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public LParser(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new LParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public LParser(LParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(LParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 2; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }


/** Get the next Token. */
  final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List jj_expentries = new java.util.ArrayList();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[20];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 2; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 20; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = (int[])jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  final public void enable_tracing() {
  }

  /** Disable tracing. */
  final public void disable_tracing() {
  }

//  public void setWindowGui(WindowER gui){
//    this.gui = gui;
//  }

  public void setLexicalAnalysis(WindowER gui){
    lexicalAnalysis = new LexicalAnalysis(gui);
  }

}
