/* Generated By:JavaCC: Do not edit this line. LParserConstants.java */
package br.univali.compiladores.compilador.model.parser;


/** 
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface LParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int LINE_COMMENT = 5;
  /** RegularExpression Id. */
  int BLOCK_COMMENT = 6;
  /** RegularExpression Id. */
  int ESP_SYMBOL_L_BRACKET = 7;
  /** RegularExpression Id. */
  int ESP_SYMBOL_R_BRACKET = 8;
  /** RegularExpression Id. */
  int ESP_SYMBOL_L_PARENTHESIS = 9;
  /** RegularExpression Id. */
  int ESP_SYMBOL_R_PARENTHESIS = 10;
  /** RegularExpression Id. */
  int ESP_SYMBOL_L_BRACE = 11;
  /** RegularExpression Id. */
  int ESP_SYMBOL_R_BRACE = 12;
  /** RegularExpression Id. */
  int ESP_SYMBOL_DOT = 13;
  /** RegularExpression Id. */
  int ESP_SYMBOL_COMA = 14;
  /** RegularExpression Id. */
  int ESP_SYMBOL_ASSIGNE = 15;
  /** RegularExpression Id. */
  int ESP_SYMBOL_PLUS = 16;
  /** RegularExpression Id. */
  int ESP_SYMBOL_MINUS = 17;
  /** RegularExpression Id. */
  int ESP_SYMBOL_STAR = 18;
  /** RegularExpression Id. */
  int ESP_SYMBOL_DIVISION_REAL = 19;
  /** RegularExpression Id. */
  int ESP_SYMBOL_POWER = 20;
  /** RegularExpression Id. */
  int ESP_SYMBOL_DIVISION_INT = 21;
  /** RegularExpression Id. */
  int ESP_SYMBOL_MOD = 22;
  /** RegularExpression Id. */
  int ESP_SYMBOL_EQUAL = 23;
  /** RegularExpression Id. */
  int ESP_SYMBOL_DIFFERENT = 24;
  /** RegularExpression Id. */
  int ESP_SYMBOL_GREATER = 25;
  /** RegularExpression Id. */
  int ESP_SYMBOL_LESSER = 26;
  /** RegularExpression Id. */
  int ESP_SYMBOL_GREATEREQUAL = 27;
  /** RegularExpression Id. */
  int ESP_SYMBOL_LESSERREQUAL = 28;
  /** RegularExpression Id. */
  int ESP_SYMBOL_AND = 29;
  /** RegularExpression Id. */
  int ESP_SYMBOL_OR = 30;
  /** RegularExpression Id. */
  int ESP_SYMBOL_NOT = 31;
  /** RegularExpression Id. */
  int RESERVED_WORD_DO = 32;
  /** RegularExpression Id. */
  int RESERVED_WORD_THIS = 33;
  /** RegularExpression Id. */
  int RESERVED_WORD_BODY = 34;
  /** RegularExpression Id. */
  int RESERVED_WORD_DESCRIPTION = 35;
  /** RegularExpression Id. */
  int RESERVED_WORD_DECLARATION = 36;
  /** RegularExpression Id. */
  int RESERVED_WORD_TYPE = 37;
  /** RegularExpression Id. */
  int RESERVED_WORD_IS = 38;
  /** RegularExpression Id. */
  int RESERVED_WORD_CONSTANT = 39;
  /** RegularExpression Id. */
  int RESERVED_WORD_AND = 40;
  /** RegularExpression Id. */
  int RESERVED_WORD_VARIABLE = 41;
  /** RegularExpression Id. */
  int RESERVED_WORD_AS = 42;
  /** RegularExpression Id. */
  int RESERVED_WORD_INTEGER = 43;
  /** RegularExpression Id. */
  int RESERVED_WORD_REAL = 44;
  /** RegularExpression Id. */
  int RESERVED_WORD_STRING = 45;
  /** RegularExpression Id. */
  int RESERVED_WORD_LOGIC = 46;
  /** RegularExpression Id. */
  int RESERVED_WORD_DESIGNATE = 47;
  /** RegularExpression Id. */
  int RESERVED_WORD_READ = 48;
  /** RegularExpression Id. */
  int RESERVED_WORD_WRITE = 49;
  /** RegularExpression Id. */
  int RESERVED_WORD_ALL = 50;
  /** RegularExpression Id. */
  int RESERVED_WORD_REPEAT = 51;
  /** RegularExpression Id. */
  int RESERVED_WORD_AVALIATE = 52;
  /** RegularExpression Id. */
  int RESERVED_WORD_RESULT = 53;
  /** RegularExpression Id. */
  int RESERVED_WORD_TRUE = 54;
  /** RegularExpression Id. */
  int RESERVED_WORD_UNTRUE = 55;
  /** RegularExpression Id. */
  int DIGIT = 56;
  /** RegularExpression Id. */
  int LETTER = 57;
  /** RegularExpression Id. */
  int CAPSLETTER = 58;
  /** RegularExpression Id. */
  int LOWCAPSLETTER = 59;
  /** RegularExpression Id. */
  int UNDER = 60;
  /** RegularExpression Id. */
  int CONST_INT = 61;
  /** RegularExpression Id. */
  int CONST_REAL = 62;
  /** RegularExpression Id. */
  int CONST_LITERAL = 63;
  /** RegularExpression Id. */
  int IDENTIFIER = 64;
  /** RegularExpression Id. */
  int NONSUPPORTED_SYMBOL = 65;
  /** RegularExpression Id. */
  int NONSUPPORTED_CONST_LITERAL_SINGLE = 66;
  /** RegularExpression Id. */
  int NONSUPPORTED_CONST_LITERAL_DOUBLE = 67;
  /** RegularExpression Id. */
  int NONSUPPORTED_BLOCK_COMMENT = 68;
  /** RegularExpression Id. */
  int NONSUPPORTED_IDENTIFIER = 69;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "<LINE_COMMENT>",
    "<BLOCK_COMMENT>",
    "\"[\"",
    "\"]\"",
    "\"(\"",
    "\")\"",
    "\"{\"",
    "\"}\"",
    "\".\"",
    "\",\"",
    "\"=\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"**\"",
    "\"%\"",
    "\"%%\"",
    "\"==\"",
    "\"!=\"",
    "\">>\"",
    "\"<<\"",
    "\">>=\"",
    "\"<<=\"",
    "\"&\"",
    "\"|\"",
    "\"!\"",
    "<RESERVED_WORD_DO>",
    "<RESERVED_WORD_THIS>",
    "<RESERVED_WORD_BODY>",
    "<RESERVED_WORD_DESCRIPTION>",
    "<RESERVED_WORD_DECLARATION>",
    "<RESERVED_WORD_TYPE>",
    "<RESERVED_WORD_IS>",
    "<RESERVED_WORD_CONSTANT>",
    "<RESERVED_WORD_AND>",
    "<RESERVED_WORD_VARIABLE>",
    "<RESERVED_WORD_AS>",
    "<RESERVED_WORD_INTEGER>",
    "<RESERVED_WORD_REAL>",
    "<RESERVED_WORD_STRING>",
    "<RESERVED_WORD_LOGIC>",
    "<RESERVED_WORD_DESIGNATE>",
    "<RESERVED_WORD_READ>",
    "<RESERVED_WORD_WRITE>",
    "<RESERVED_WORD_ALL>",
    "<RESERVED_WORD_REPEAT>",
    "<RESERVED_WORD_AVALIATE>",
    "<RESERVED_WORD_RESULT>",
    "<RESERVED_WORD_TRUE>",
    "<RESERVED_WORD_UNTRUE>",
    "<DIGIT>",
    "<LETTER>",
    "<CAPSLETTER>",
    "<LOWCAPSLETTER>",
    "<UNDER>",
    "<CONST_INT>",
    "<CONST_REAL>",
    "<CONST_LITERAL>",
    "<IDENTIFIER>",
    "<NONSUPPORTED_SYMBOL>",
    "<NONSUPPORTED_CONST_LITERAL_SINGLE>",
    "<NONSUPPORTED_CONST_LITERAL_DOUBLE>",
    "<NONSUPPORTED_BLOCK_COMMENT>",
    "<NONSUPPORTED_IDENTIFIER>",
  };

}
