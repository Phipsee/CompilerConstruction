/* Generated By:JavaCC: Do not edit this line. ParserConstants.java */
package yapl.impl.parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface ParserConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int PROGRAM = 5;
  /** RegularExpression Id. */
  int TRUTHVALUE = 6;
  /** RegularExpression Id. */
  int ANDOP = 7;
  /** RegularExpression Id. */
  int OROP = 8;
  /** RegularExpression Id. */
  int NEWOP = 9;
  /** RegularExpression Id. */
  int IF = 10;
  /** RegularExpression Id. */
  int THEN = 11;
  /** RegularExpression Id. */
  int ELSE = 12;
  /** RegularExpression Id. */
  int ENDIF = 13;
  /** RegularExpression Id. */
  int WHILE = 14;
  /** RegularExpression Id. */
  int DO = 15;
  /** RegularExpression Id. */
  int ENDWHILE = 16;
  /** RegularExpression Id. */
  int RETURN = 17;
  /** RegularExpression Id. */
  int WRITE = 18;
  /** RegularExpression Id. */
  int BEGIN = 19;
  /** RegularExpression Id. */
  int END = 20;
  /** RegularExpression Id. */
  int INT = 21;
  /** RegularExpression Id. */
  int BOOL = 22;
  /** RegularExpression Id. */
  int CONSTANT = 23;
  /** RegularExpression Id. */
  int RECORD = 24;
  /** RegularExpression Id. */
  int ENDRECORD = 25;
  /** RegularExpression Id. */
  int DECLARE = 26;
  /** RegularExpression Id. */
  int PROCEDURE = 27;
  /** RegularExpression Id. */
  int VOID = 28;
  /** RegularExpression Id. */
  int DELIMITER = 29;
  /** RegularExpression Id. */
  int openingParenthesis = 30;
  /** RegularExpression Id. */
  int closingParenthesis = 31;
  /** RegularExpression Id. */
  int openingBrackets = 32;
  /** RegularExpression Id. */
  int closingBrackets = 33;
  /** RegularExpression Id. */
  int hash = 34;
  /** RegularExpression Id. */
  int dot = 35;
  /** RegularExpression Id. */
  int colon = 36;
  /** RegularExpression Id. */
  int assignOp = 37;
  /** RegularExpression Id. */
  int constAssignOp = 38;
  /** RegularExpression Id. */
  int less = 39;
  /** RegularExpression Id. */
  int lessequal = 40;
  /** RegularExpression Id. */
  int greater = 41;
  /** RegularExpression Id. */
  int greaterequal = 42;
  /** RegularExpression Id. */
  int plus = 43;
  /** RegularExpression Id. */
  int minus = 44;
  /** RegularExpression Id. */
  int multiply = 45;
  /** RegularExpression Id. */
  int divide = 46;
  /** RegularExpression Id. */
  int modulo = 47;
  /** RegularExpression Id. */
  int equals = 48;
  /** RegularExpression Id. */
  int nequals = 49;
  /** RegularExpression Id. */
  int ident = 50;
  /** RegularExpression Id. */
  int number = 51;
  /** RegularExpression Id. */
  int letter = 52;
  /** RegularExpression Id. */
  int digit = 53;
  /** RegularExpression Id. */
  int otherchar = 54;
  /** RegularExpression Id. */
  int string = 55;
  /** RegularExpression Id. */
  int comment = 56;

  /** Lexical state. */
  int DEFAULT = 0;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "\"Program\"",
    "<TRUTHVALUE>",
    "\"And\"",
    "\"Or\"",
    "\"new\"",
    "\"If\"",
    "\"Then\"",
    "\"Else\"",
    "\"EndIf\"",
    "\"While\"",
    "\"Do\"",
    "\"EndWhile\"",
    "\"Return\"",
    "\"Write\"",
    "\"Begin\"",
    "\"End\"",
    "\"int\"",
    "\"bool\"",
    "\"Const\"",
    "\"Record\"",
    "\"EndRecord\"",
    "\"Declare\"",
    "\"Procedure\"",
    "\"void\"",
    "\";\"",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "\"#\"",
    "\".\"",
    "\",\"",
    "\":=\"",
    "\"=\"",
    "\"<\"",
    "\"<=\"",
    "\">\"",
    "\">=\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"==\"",
    "\"!=\"",
    "<ident>",
    "<number>",
    "<letter>",
    "<digit>",
    "<otherchar>",
    "<string>",
    "<comment>",
  };

}
