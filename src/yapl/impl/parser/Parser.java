/* Generated By:JavaCC: Do not edit this line. Parser.java */
package yapl.impl.parser;
import java.io.*;
import yapl.lib.CompilerMessage;

public class Parser implements ParserConstants {
  public static String programName;

  public static void main(String args []) throws ParseException, FileNotFoundException, TokenMgrError
  {
    try
    {
      Parser parser = new Parser(new FileInputStream(args [0]));
      parser.Program();
      CompilerMessage.printOK(Parser.programName);
    }
    catch (TokenMgrError e)
    {
      CompilerMessage.printError(e, Parser.programName);
    }
    catch (ParseException e)
    {
      CompilerMessage.printError(e, Parser.programName);
    }
  }

  static final public void Literal() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUTHVALUE:
      jj_consume_token(TRUTHVALUE);
      break;
    case number:
      jj_consume_token(number);
      break;
    default:
      jj_la1[0] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Selector() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case openingBrackets:
      jj_consume_token(openingBrackets);
      Expr();
      jj_consume_token(closingBrackets);
      break;
    case dot:
      jj_consume_token(dot);
      jj_consume_token(ident);
      break;
    default:
      jj_la1[1] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case openingBrackets:
    case dot:
      Selector();
      break;
    default:
      jj_la1[2] = jj_gen;
      ;
    }
  }

  static final public void ArrayLen() throws ParseException {
    jj_consume_token(hash);
    jj_consume_token(ident);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case openingBrackets:
    case dot:
      Selector();
      break;
    default:
      jj_la1[3] = jj_gen;
      ;
    }
  }

  static final public void PrimaryExpr() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case TRUTHVALUE:
    case number:
      Literal();
      break;
    case openingParenthesis:
      jj_consume_token(openingParenthesis);
      Expr();
      jj_consume_token(closingParenthesis);
      break;
    default:
      jj_la1[6] = jj_gen;
      if (jj_2_2(1)) {
        if (jj_2_1(2147483647) && (getToken(2).kind == openingParenthesis)) {
          ProcedureCall();
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case ident:
            jj_consume_token(ident);
            switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
            case openingBrackets:
            case dot:
              Selector();
              break;
            default:
              jj_la1[4] = jj_gen;
              ;
            }
            break;
          default:
            jj_la1[5] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case hash:
          ArrayLen();
          break;
        default:
          jj_la1[7] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  static final public void UnaryExpr() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case plus:
    case minus:
      AddOp();
      break;
    default:
      jj_la1[8] = jj_gen;
      ;
    }
    PrimaryExpr();
  }

  static final public void MulExpr() throws ParseException {
    UnaryExpr();
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case multiply:
      case divide:
      case modulo:
        ;
        break;
      default:
        jj_la1[9] = jj_gen;
        break label_1;
      }
      MulOp();
      UnaryExpr();
    }
  }

  static final public void AddExpr() throws ParseException {
    MulExpr();
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case plus:
      case minus:
        ;
        break;
      default:
        jj_la1[10] = jj_gen;
        break label_2;
      }
      AddOp();
      MulExpr();
    }
  }

  static final public void RelExpr() throws ParseException {
    AddExpr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case less:
    case lessequal:
    case greater:
    case greaterequal:
      RelOp();
      AddExpr();
      break;
    default:
      jj_la1[11] = jj_gen;
      ;
    }
  }

  static final public void EqualExpr() throws ParseException {
    RelExpr();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case equals:
    case nequals:
      EqualOp();
      RelExpr();
      break;
    default:
      jj_la1[12] = jj_gen;
      ;
    }
  }

  static final public void CondAndExpr() throws ParseException {
    EqualExpr();
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ANDOP:
        ;
        break;
      default:
        jj_la1[13] = jj_gen;
        break label_3;
      }
      jj_consume_token(ANDOP);
      EqualExpr();
    }
  }

  static final public void CreationExpr() throws ParseException {
    jj_consume_token(NEWOP);
    NonArrayType();
    label_4:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case openingBrackets:
        ;
        break;
      default:
        jj_la1[14] = jj_gen;
        break label_4;
      }
      jj_consume_token(openingBrackets);
      Expr();
      jj_consume_token(closingBrackets);
    }
  }

  static final public void Expr() throws ParseException {
    if (jj_2_3(1)) {
      CondAndExpr();
      label_5:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case OROP:
          ;
          break;
        default:
          jj_la1[15] = jj_gen;
          break label_5;
        }
        jj_consume_token(OROP);
        CondAndExpr();
      }
    } else {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case NEWOP:
        CreationExpr();
        break;
      default:
        jj_la1[16] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void ArgumentList() throws ParseException {
    Expr();
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case colon:
        ;
        break;
      default:
        jj_la1[17] = jj_gen;
        break label_6;
      }
      jj_consume_token(colon);
      Expr();
    }
  }

  static final public void ProcedureCall() throws ParseException {
    jj_consume_token(ident);
    jj_consume_token(openingParenthesis);
    if (jj_2_4(1)) {
      ArgumentList();
    } else {
      ;
    }
    jj_consume_token(closingParenthesis);
  }

  static final public void Assignment() throws ParseException {
    jj_consume_token(ident);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case openingBrackets:
    case dot:
      Selector();
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
    jj_consume_token(assignOp);
    Expr();
  }

  static final public void IfStatement() throws ParseException {
    jj_consume_token(IF);
    Expr();
    jj_consume_token(THEN);
    StatementList();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      StatementList();
      break;
    default:
      jj_la1[19] = jj_gen;
      ;
    }
    jj_consume_token(ENDIF);
  }

  static final public void WhileStatement() throws ParseException {
    jj_consume_token(WHILE);
    Expr();
    jj_consume_token(DO);
    StatementList();
    jj_consume_token(ENDWHILE);
  }

  static final public void ReturnStatement() throws ParseException {
    jj_consume_token(RETURN);
    if (jj_2_5(1)) {
      Expr();
    } else {
      ;
    }
  }

  static final public void WriteStatement() throws ParseException {
    jj_consume_token(WRITE);
    jj_consume_token(string);
  }

  static final public void Statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case IF:
      IfStatement();
      break;
    case WHILE:
      WhileStatement();
      break;
    case RETURN:
      ReturnStatement();
      break;
    case WRITE:
      WriteStatement();
      break;
    default:
      jj_la1[21] = jj_gen;
      if (jj_2_7(1)) {
        if (jj_2_6(2147483647) && (getToken(2).kind != openingParenthesis)) {
          Assignment();
        } else {
          switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
          case ident:
            ProcedureCall();
            break;
          default:
            jj_la1[20] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
          }
        }
      } else {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case BEGIN:
        case DECLARE:
          Block();
          break;
        default:
          jj_la1[22] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
  }

  static final public void StatementList() throws ParseException {
    label_7:
    while (true) {
      if (jj_2_8(1)) {
        ;
      } else {
        break label_7;
      }
      Statement();
      jj_consume_token(DELIMITER);
    }
  }

  static final public void Block() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case DECLARE:
      Decl();
      break;
    default:
      jj_la1[23] = jj_gen;
      ;
    }
    jj_consume_token(BEGIN);
    StatementList();
    jj_consume_token(END);
  }

  static final public void NonArrayType() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
      jj_consume_token(INT);
      break;
    case BOOL:
      jj_consume_token(BOOL);
      break;
    case ident:
      jj_consume_token(ident);
      break;
    default:
      jj_la1[24] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Type() throws ParseException {
    NonArrayType();
    label_8:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case openingBrackets:
        ;
        break;
      default:
        jj_la1[25] = jj_gen;
        break label_8;
      }
      jj_consume_token(openingBrackets);
      jj_consume_token(closingBrackets);
    }
  }

  static final public void ReturnType() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case VOID:
      jj_consume_token(VOID);
      break;
    case INT:
    case BOOL:
    case ident:
      Type();
      break;
    default:
      jj_la1[26] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void ConstDecl() throws ParseException {
    jj_consume_token(CONSTANT);
    jj_consume_token(ident);
    jj_consume_token(constAssignOp);
    Literal();
    jj_consume_token(DELIMITER);
  }

  static final public void VarDecl() throws ParseException {
    Type();
    jj_consume_token(ident);
    label_9:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case colon:
        ;
        break;
      default:
        jj_la1[27] = jj_gen;
        break label_9;
      }
      jj_consume_token(colon);
      jj_consume_token(ident);
    }
    jj_consume_token(DELIMITER);
  }

  static final public void TypeDecl() throws ParseException {
    jj_consume_token(RECORD);
    jj_consume_token(ident);
    VarDecl();
    label_10:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
      case BOOL:
      case ident:
        ;
        break;
      default:
        jj_la1[28] = jj_gen;
        break label_10;
      }
      VarDecl();
    }
    jj_consume_token(ENDRECORD);
    jj_consume_token(DELIMITER);
  }

  static final public void Decl() throws ParseException {
    jj_consume_token(DECLARE);
    label_11:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
      case BOOL:
      case CONSTANT:
      case RECORD:
      case ident:
        ;
        break;
      default:
        jj_la1[29] = jj_gen;
        break label_11;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case CONSTANT:
        ConstDecl();
        break;
      case INT:
      case BOOL:
      case ident:
        VarDecl();
        break;
      case RECORD:
        TypeDecl();
        break;
      default:
        jj_la1[30] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
  }

  static final public void FormalParam() throws ParseException {
    Type();
    jj_consume_token(ident);
  }

  static final public void FormalParamList() throws ParseException {
    FormalParam();
    label_12:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case colon:
        ;
        break;
      default:
        jj_la1[31] = jj_gen;
        break label_12;
      }
      jj_consume_token(colon);
      FormalParam();
    }
  }

  static final public void Procedure() throws ParseException {
    jj_consume_token(PROCEDURE);
    ReturnType();
    jj_consume_token(ident);
    jj_consume_token(openingParenthesis);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case INT:
    case BOOL:
    case ident:
      FormalParamList();
      break;
    default:
      jj_la1[32] = jj_gen;
      ;
    }
    jj_consume_token(closingParenthesis);
    Block();
    jj_consume_token(ident);
    jj_consume_token(DELIMITER);
  }

  static final public void Program() throws ParseException {
  Token t;
    jj_consume_token(PROGRAM);
    t = jj_consume_token(ident);
    Parser.programName = t.toString();
    label_13:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DECLARE:
      case PROCEDURE:
        ;
        break;
      default:
        jj_la1[33] = jj_gen;
        break label_13;
      }
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case DECLARE:
        Decl();
        break;
      case PROCEDURE:
        Procedure();
        break;
      default:
        jj_la1[34] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(BEGIN);
    StatementList();
    jj_consume_token(END);
    jj_consume_token(ident);
    jj_consume_token(dot);
  }

  static final public void RelOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case less:
      jj_consume_token(less);
      break;
    case lessequal:
      jj_consume_token(lessequal);
      break;
    case greaterequal:
      jj_consume_token(greaterequal);
      break;
    case greater:
      jj_consume_token(greater);
      break;
    default:
      jj_la1[35] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void EqualOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case equals:
      jj_consume_token(equals);
      break;
    case nequals:
      jj_consume_token(nequals);
      break;
    default:
      jj_la1[36] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void AddOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case plus:
      jj_consume_token(plus);
      break;
    case minus:
      jj_consume_token(minus);
      break;
    default:
      jj_la1[37] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void MulOp() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case multiply:
      jj_consume_token(multiply);
      break;
    case divide:
      jj_consume_token(divide);
      break;
    case modulo:
      jj_consume_token(modulo);
      break;
    default:
      jj_la1[38] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static private boolean jj_2_1(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_2(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_3(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_4(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_5(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_6(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_2_7(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_7(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(6, xla); }
  }

  static private boolean jj_2_8(int xla) {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_8(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(7, xla); }
  }

  static private boolean jj_3R_17() {
    if (jj_3R_18()) return true;
    return false;
  }

  static private boolean jj_3R_50() {
    if (jj_scan_token(hash)) return true;
    return false;
  }

  static private boolean jj_3R_24() {
    if (jj_3R_32()) return true;
    return false;
  }

  static private boolean jj_3R_39() {
    if (jj_3R_41()) return true;
    return false;
  }

  static private boolean jj_3R_18() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3_3()) {
    jj_scanpos = xsp;
    if (jj_3R_24()) return true;
    }
    return false;
  }

  static private boolean jj_3_3() {
    if (jj_3R_16()) return true;
    return false;
  }

  static private boolean jj_3R_37() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_39()) jj_scanpos = xsp;
    if (jj_scan_token(BEGIN)) return true;
    return false;
  }

  static private boolean jj_3_8() {
    if (jj_3R_21()) return true;
    return false;
  }

  static private boolean jj_3_6() {
    if (jj_scan_token(ident)) return true;
    return false;
  }

  static private boolean jj_3R_32() {
    if (jj_scan_token(NEWOP)) return true;
    return false;
  }

  static private boolean jj_3R_49() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(6)) {
    jj_scanpos = xsp;
    if (jj_scan_token(51)) return true;
    }
    return false;
  }

  static private boolean jj_3R_20() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_30() {
    if (jj_3R_37()) return true;
    return false;
  }

  static private boolean jj_3R_19() {
    if (jj_3R_25()) return true;
    return false;
  }

  static private boolean jj_3R_16() {
    if (jj_3R_23()) return true;
    return false;
  }

  static private boolean jj_3_7() {
    Token xsp;
    xsp = jj_scanpos;
    jj_lookingAhead = true;
    jj_semLA = getToken(2).kind != openingParenthesis;
    jj_lookingAhead = false;
    if (!jj_semLA || jj_3R_19()) {
    jj_scanpos = xsp;
    if (jj_3R_20()) return true;
    }
    return false;
  }

  static private boolean jj_3R_29() {
    if (jj_3R_36()) return true;
    return false;
  }

  static private boolean jj_3R_28() {
    if (jj_3R_35()) return true;
    return false;
  }

  static private boolean jj_3R_27() {
    if (jj_3R_34()) return true;
    return false;
  }

  static private boolean jj_3R_21() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_26()) {
    jj_scanpos = xsp;
    if (jj_3R_27()) {
    jj_scanpos = xsp;
    if (jj_3R_28()) {
    jj_scanpos = xsp;
    if (jj_3R_29()) {
    jj_scanpos = xsp;
    if (jj_3_7()) {
    jj_scanpos = xsp;
    if (jj_3R_30()) return true;
    }
    }
    }
    }
    }
    return false;
  }

  static private boolean jj_3R_26() {
    if (jj_3R_33()) return true;
    return false;
  }

  static private boolean jj_3R_23() {
    if (jj_3R_31()) return true;
    return false;
  }

  static private boolean jj_3R_36() {
    if (jj_scan_token(WRITE)) return true;
    return false;
  }

  static private boolean jj_3_5() {
    if (jj_3R_18()) return true;
    return false;
  }

  static private boolean jj_3R_31() {
    if (jj_3R_38()) return true;
    return false;
  }

  static private boolean jj_3R_35() {
    if (jj_scan_token(RETURN)) return true;
    return false;
  }

  static private boolean jj_3R_41() {
    if (jj_scan_token(DECLARE)) return true;
    return false;
  }

  static private boolean jj_3R_38() {
    if (jj_3R_40()) return true;
    return false;
  }

  static private boolean jj_3R_34() {
    if (jj_scan_token(WHILE)) return true;
    return false;
  }

  static private boolean jj_3R_40() {
    if (jj_3R_42()) return true;
    return false;
  }

  static private boolean jj_3R_33() {
    if (jj_scan_token(IF)) return true;
    return false;
  }

  static private boolean jj_3R_43() {
    if (jj_3R_45()) return true;
    return false;
  }

  static private boolean jj_3R_42() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_43()) jj_scanpos = xsp;
    if (jj_3R_44()) return true;
    return false;
  }

  static private boolean jj_3_1() {
    if (jj_scan_token(ident)) return true;
    return false;
  }

  static private boolean jj_3R_25() {
    if (jj_scan_token(ident)) return true;
    return false;
  }

  static private boolean jj_3R_48() {
    if (jj_3R_50()) return true;
    return false;
  }

  static private boolean jj_3R_45() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_scan_token(43)) {
    jj_scanpos = xsp;
    if (jj_scan_token(44)) return true;
    }
    return false;
  }

  static private boolean jj_3R_15() {
    if (jj_scan_token(ident)) return true;
    return false;
  }

  static private boolean jj_3_4() {
    if (jj_3R_17()) return true;
    return false;
  }

  static private boolean jj_3R_14() {
    if (jj_3R_22()) return true;
    return false;
  }

  static private boolean jj_3R_22() {
    if (jj_scan_token(ident)) return true;
    return false;
  }

  static private boolean jj_3_2() {
    Token xsp;
    xsp = jj_scanpos;
    jj_lookingAhead = true;
    jj_semLA = getToken(2).kind == openingParenthesis;
    jj_lookingAhead = false;
    if (!jj_semLA || jj_3R_14()) {
    jj_scanpos = xsp;
    if (jj_3R_15()) return true;
    }
    return false;
  }

  static private boolean jj_3R_47() {
    if (jj_scan_token(openingParenthesis)) return true;
    return false;
  }

  static private boolean jj_3R_46() {
    if (jj_3R_49()) return true;
    return false;
  }

  static private boolean jj_3R_44() {
    Token xsp;
    xsp = jj_scanpos;
    if (jj_3R_46()) {
    jj_scanpos = xsp;
    if (jj_3R_47()) {
    jj_scanpos = xsp;
    if (jj_3_2()) {
    jj_scanpos = xsp;
    if (jj_3R_48()) return true;
    }
    }
    }
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  /** Whether we are looking ahead. */
  static private boolean jj_lookingAhead = false;
  static private boolean jj_semLA;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[39];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x40,0x0,0x0,0x0,0x0,0x0,0x40000040,0x0,0x0,0x0,0x0,0x0,0x0,0x80,0x0,0x100,0x200,0x0,0x0,0x1000,0x0,0x64400,0x4080000,0x4000000,0x600000,0x0,0x10600000,0x0,0x600000,0x1e00000,0x1e00000,0x0,0x600000,0xc000000,0xc000000,0x0,0x0,0x0,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x80000,0x9,0x9,0x9,0x9,0x40000,0x80000,0x4,0x1800,0xe000,0x1800,0x780,0x30000,0x0,0x1,0x0,0x0,0x10,0x9,0x0,0x40000,0x0,0x0,0x0,0x40000,0x1,0x40000,0x10,0x40000,0x40000,0x40000,0x10,0x40000,0x0,0x0,0x780,0x30000,0x1800,0xe000,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[8];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 39; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 39; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new ParserTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 39; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 39; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 39; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 39; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    if (token.kind == kind) {
      jj_gen++;
      if (++jj_gc > 100) {
        jj_gc = 0;
        for (int i = 0; i < jj_2_rtns.length; i++) {
          JJCalls c = jj_2_rtns[i];
          while (c != null) {
            if (c.gen < jj_gen) c.first = null;
            c = c.next;
          }
        }
      }
      return token;
    }
    token = oldToken;
    jj_kind = kind;
    throw generateParseException();
  }

  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
    if (jj_scanpos == jj_lastpos) {
      jj_la--;
      if (jj_scanpos.next == null) {
        jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
      } else {
        jj_lastpos = jj_scanpos = jj_scanpos.next;
      }
    } else {
      jj_scanpos = jj_scanpos.next;
    }
    if (jj_rescan) {
      int i = 0; Token tok = token;
      while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
      if (tok != null) jj_add_error_token(kind, i);
    }
    if (jj_scanpos.kind != kind) return true;
    if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
    return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
    Token t = jj_lookingAhead ? jj_scanpos : token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  static private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[57];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 39; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 57; i++) {
      if (la1tokens[i]) {
        jj_expentry = new int[1];
        jj_expentry[0] = i;
        jj_expentries.add(jj_expentry);
      }
    }
    jj_endpos = 0;
    jj_rescan_token();
    jj_add_error_token(0, 0);
    int[][] exptokseq = new int[jj_expentries.size()][];
    for (int i = 0; i < jj_expentries.size(); i++) {
      exptokseq[i] = jj_expentries.get(i);
    }
    return new ParseException(token, exptokseq, tokenImage);
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 8; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
            case 1: jj_3_2(); break;
            case 2: jj_3_3(); break;
            case 3: jj_3_4(); break;
            case 4: jj_3_5(); break;
            case 5: jj_3_6(); break;
            case 6: jj_3_7(); break;
            case 7: jj_3_8(); break;
          }
        }
        p = p.next;
      } while (p != null);
      } catch(LookaheadSuccess ls) { }
    }
    jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
    JJCalls p = jj_2_rtns[index];
    while (p.gen > jj_gen) {
      if (p.next == null) { p = p.next = new JJCalls(); break; }
      p = p.next;
    }
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}