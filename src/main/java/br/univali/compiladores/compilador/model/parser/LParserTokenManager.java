package br.univali.compiladores.compilador.model.parser;/* Generated By:JavaCC: Do not edit this line. LParserTokenManager.java */

/** Token Manager. */
public class LParserTokenManager implements LParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      default :
         return jjMoveNfa_0(0, 0);
   }
}
static final long[] jjbitVec0 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   //int[] nextStates; // not used
   int startsAt = 0;
   jjnewStateCnt = 173;
   int i = 1;
   jjstateSet[0] = startState;
   //int j; // not used
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0xac000040ffffc9ffL & l) != 0L)
                  {
                     if (kind > 17)
                        kind = 17;
                     jjCheckNAdd(71);
                  }
                  else if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 13)
                        kind = 13;
                     jjCheckNAddStates(0, 6);
                  }
                  else if (curChar == 39)
                  {
                     if (kind > 18)
                        kind = 18;
                     jjCheckNAddStates(7, 9);
                  }
                  else if (curChar == 34)
                  {
                     if (kind > 18)
                        kind = 18;
                     jjCheckNAddStates(10, 12);
                  }
                  else if (curChar == 60)
                     jjAddStates(13, 14);
                  else if (curChar == 62)
                     jjAddStates(15, 16);
                  else if (curChar == 33)
                     jjCheckNAdd(10);
                  else if (curChar == 37)
                     jjstateSet[jjnewStateCnt++] = 8;
                  else if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 6;
                  else if (curChar == 35)
                     jjCheckNAddStates(17, 19);
                  if ((0x2000ff6200000000L & l) != 0L)
                  {
                     if (kind > 7)
                        kind = 7;
                  }
                  if (curChar == 61)
                     jjCheckNAdd(10);
                  break;
               case 1:
                  if ((0xffffffffffffdbffL & l) != 0L)
                     jjCheckNAddStates(17, 19);
                  break;
               case 2:
                  if ((0x2400L & l) != 0L && kind > 5)
                     kind = 5;
                  break;
               case 3:
                  if (curChar == 10 && kind > 5)
                     kind = 5;
                  break;
               case 4:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 3;
                  break;
               case 5:
                  if ((0x2000ff6200000000L & l) != 0L && kind > 7)
                     kind = 7;
                  break;
               case 6:
                  if (curChar == 42 && kind > 7)
                     kind = 7;
                  break;
               case 7:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 6;
                  break;
               case 8:
                  if (curChar == 37 && kind > 7)
                     kind = 7;
                  break;
               case 9:
                  if (curChar == 37)
                     jjstateSet[jjnewStateCnt++] = 8;
                  break;
               case 10:
                  if (curChar == 61 && kind > 7)
                     kind = 7;
                  break;
               case 11:
                  if (curChar == 61)
                     jjCheckNAdd(10);
                  break;
               case 12:
                  if (curChar == 33)
                     jjCheckNAdd(10);
                  break;
               case 54:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(20, 22);
                  break;
               case 55:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(56, 57);
                  break;
               case 62:
               case 67:
               case 69:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(23, 25);
                  break;
               case 63:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(60, 64);
                  break;
               case 71:
                  if ((0xac000040ffffc9ffL & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAdd(71);
                  break;
               case 135:
                  if (curChar == 62)
                     jjAddStates(15, 16);
                  break;
               case 136:
                  if (curChar == 62 && kind > 7)
                     kind = 7;
                  break;
               case 137:
                  if (curChar == 62)
                     jjCheckNAdd(10);
                  break;
               case 138:
                  if (curChar == 60)
                     jjAddStates(13, 14);
                  break;
               case 139:
                  if (curChar == 60 && kind > 7)
                     kind = 7;
                  break;
               case 140:
                  if (curChar == 60)
                     jjCheckNAdd(10);
                  break;
               case 142:
                  jjCheckNAddStates(26, 29);
                  break;
               case 143:
                  if ((0x2400L & l) != 0L)
                     jjCheckNAddStates(26, 29);
                  break;
               case 144:
                  if (curChar == 10)
                     jjCheckNAddStates(26, 29);
                  break;
               case 145:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 144;
                  break;
               case 147:
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAddStates(30, 32);
                  break;
               case 148:
                  if ((0x2400L & l) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAddStates(30, 32);
                  break;
               case 149:
                  if (curChar != 10)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAddStates(30, 32);
                  break;
               case 150:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 149;
                  break;
               case 151:
                  if (curChar != 34)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjCheckNAddStates(10, 12);
                  break;
               case 152:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(152, 153);
                  break;
               case 153:
                  if (curChar == 34 && kind > 15)
                     kind = 15;
                  break;
               case 154:
                  if ((0xfffffffbffffdbffL & l) == 0L)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjCheckNAdd(154);
                  break;
               case 155:
                  if (curChar != 39)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjCheckNAddStates(7, 9);
                  break;
               case 156:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAddTwoStates(156, 157);
                  break;
               case 157:
                  if (curChar == 39 && kind > 15)
                     kind = 15;
                  break;
               case 158:
                  if ((0xffffff7fffffdbffL & l) == 0L)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjCheckNAdd(158);
                  break;
               case 159:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 13)
                     kind = 13;
                  jjCheckNAddStates(0, 6);
                  break;
               case 160:
                  if ((0x3ff000000000000L & l) != 0L && kind > 13)
                     kind = 13;
                  break;
               case 161:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAdd(160);
                  break;
               case 162:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAdd(163);
                  break;
               case 163:
                  if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 164;
                  break;
               case 164:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 14)
                     kind = 14;
                  jjCheckNAddTwoStates(165, 166);
                  break;
               case 165:
                  if ((0x3ff000000000000L & l) != 0L && kind > 14)
                     kind = 14;
                  break;
               case 166:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAdd(165);
                  break;
               case 167:
               case 169:
               case 172:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAdd(162);
                  break;
               case 168:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 169;
                  break;
               case 170:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 171;
                  break;
               case 171:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 172;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffeL & l) != 0L)
                  {
                     if (kind > 16)
                        kind = 16;
                     jjCheckNAddStates(33, 35);
                  }
                  else if ((0xd0000001d0000000L & l) != 0L)
                  {
                     if (kind > 17)
                        kind = 17;
                     jjCheckNAdd(71);
                  }
                  else if (curChar == 64)
                  {
                     if (kind > 19)
                        kind = 19;
                     jjCheckNAddStates(36, 42);
                  }
                  else if (curChar == 114)
                     jjAddStates(43, 45);
                  else if (curChar == 97)
                     jjCheckNAddStates(46, 49);
                  else if (curChar == 105)
                     jjCheckNAddTwoStates(101, 114);
                  else if (curChar == 116)
                     jjAddStates(50, 52);
                  else if (curChar == 100)
                     jjAddStates(53, 56);
                  else if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 51;
                  else if (curChar == 119)
                     jjstateSet[jjnewStateCnt++] = 46;
                  else if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 42;
                  else if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 37;
                  else if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 31;
                  else if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 23;
                  else if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 15;
                  if ((0x3800000028000000L & l) != 0L)
                  {
                     if (kind > 7)
                        kind = 7;
                  }
                  break;
               case 1:
                  jjAddStates(17, 19);
                  break;
               case 5:
                  if ((0x3800000028000000L & l) != 0L && kind > 7)
                     kind = 7;
                  break;
               case 13:
                  if (curChar == 121 && kind > 8)
                     kind = 8;
                  break;
               case 14:
                  if (curChar == 100)
                     jjstateSet[jjnewStateCnt++] = 13;
                  break;
               case 15:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 14;
                  break;
               case 16:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 17:
                  if (curChar == 116 && kind > 8)
                     kind = 8;
                  break;
               case 18:
                  if (curChar == 110)
                     jjCheckNAdd(17);
                  break;
               case 19:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 20:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 21:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 22:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 21;
                  break;
               case 23:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 22;
                  break;
               case 24:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 23;
                  break;
               case 25:
                  if (curChar == 101 && kind > 8)
                     kind = 8;
                  break;
               case 26:
                  if (curChar == 108)
                     jjCheckNAdd(25);
                  break;
               case 27:
                  if (curChar == 98)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 28:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 29:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 30:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 31:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 32:
                  if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 31;
                  break;
               case 33:
                  if (curChar == 103 && kind > 8)
                     kind = 8;
                  break;
               case 34:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 33;
                  break;
               case 35:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 34;
                  break;
               case 36:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 35;
                  break;
               case 37:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 38:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 37;
                  break;
               case 39:
                  if (curChar == 99 && kind > 8)
                     kind = 8;
                  break;
               case 40:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 39;
                  break;
               case 41:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 40;
                  break;
               case 42:
                  if (curChar == 111)
                     jjstateSet[jjnewStateCnt++] = 41;
                  break;
               case 43:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 42;
                  break;
               case 44:
               case 93:
               case 120:
                  if (curChar == 116)
                     jjCheckNAdd(25);
                  break;
               case 45:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 44;
                  break;
               case 46:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 45;
                  break;
               case 47:
                  if (curChar == 119)
                     jjstateSet[jjnewStateCnt++] = 46;
                  break;
               case 48:
               case 106:
                  if (curChar == 117)
                     jjCheckNAdd(25);
                  break;
               case 49:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 48;
                  break;
               case 50:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 49;
                  break;
               case 51:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 50;
                  break;
               case 52:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 51;
                  break;
               case 53:
                  if ((0x7fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(33, 35);
                  break;
               case 56:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(57, 59);
                  break;
               case 57:
                  if (curChar != 95)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(54, 58);
                  break;
               case 58:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(60, 62);
                  break;
               case 59:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(63, 67);
                  break;
               case 60:
                  if (curChar != 95)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjAddStates(68, 69);
                  break;
               case 61:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                     jjCheckNAddStates(70, 72);
                  break;
               case 64:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(73, 77);
                  break;
               case 65:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddTwoStates(65, 66);
                  break;
               case 66:
                  if (curChar == 95 && kind > 16)
                     kind = 16;
                  break;
               case 68:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(78, 84);
                  break;
               case 70:
                  if ((0x7fffffe07fffffeL & l) == 0L)
                     break;
                  if (kind > 16)
                     kind = 16;
                  jjCheckNAddStates(85, 92);
                  break;
               case 71:
                  if ((0xd0000001d0000000L & l) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAdd(71);
                  break;
               case 72:
                  if (curChar == 100)
                     jjAddStates(53, 56);
                  break;
               case 73:
                  if (curChar == 111 && kind > 8)
                     kind = 8;
                  break;
               case 74:
                  if (curChar == 110 && kind > 8)
                     kind = 8;
                  break;
               case 75:
               case 84:
                  if (curChar == 111)
                     jjCheckNAdd(74);
                  break;
               case 76:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 75;
                  break;
               case 77:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 76;
                  break;
               case 78:
                  if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 77;
                  break;
               case 79:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 78;
                  break;
               case 80:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 79;
                  break;
               case 81:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 80;
                  break;
               case 82:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 81;
                  break;
               case 83:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 82;
                  break;
               case 85:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 84;
                  break;
               case 86:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 85;
                  break;
               case 87:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 86;
                  break;
               case 88:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 87;
                  break;
               case 89:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 88;
                  break;
               case 90:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 89;
                  break;
               case 91:
                  if (curChar == 99)
                     jjstateSet[jjnewStateCnt++] = 90;
                  break;
               case 92:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 91;
                  break;
               case 94:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 93;
                  break;
               case 95:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 94;
                  break;
               case 96:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 95;
                  break;
               case 97:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 96;
                  break;
               case 98:
                  if (curChar == 115)
                     jjstateSet[jjnewStateCnt++] = 97;
                  break;
               case 99:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 98;
                  break;
               case 100:
                  if (curChar == 116)
                     jjAddStates(50, 52);
                  break;
               case 101:
                  if (curChar == 115 && kind > 8)
                     kind = 8;
                  break;
               case 102:
                  if (curChar == 105)
                     jjCheckNAdd(101);
                  break;
               case 103:
                  if (curChar == 104)
                     jjstateSet[jjnewStateCnt++] = 102;
                  break;
               case 104:
                  if (curChar == 112)
                     jjCheckNAdd(25);
                  break;
               case 105:
                  if (curChar == 121)
                     jjstateSet[jjnewStateCnt++] = 104;
                  break;
               case 107:
                  if (curChar == 114)
                     jjstateSet[jjnewStateCnt++] = 106;
                  break;
               case 108:
                  if (curChar == 105)
                     jjCheckNAddTwoStates(101, 114);
                  break;
               case 109:
                  if (curChar == 114 && kind > 8)
                     kind = 8;
                  break;
               case 110:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 109;
                  break;
               case 111:
                  if (curChar == 103)
                     jjstateSet[jjnewStateCnt++] = 110;
                  break;
               case 112:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 111;
                  break;
               case 113:
                  if (curChar == 116)
                     jjstateSet[jjnewStateCnt++] = 112;
                  break;
               case 114:
                  if (curChar == 110)
                     jjstateSet[jjnewStateCnt++] = 113;
                  break;
               case 115:
                  if (curChar == 97)
                     jjCheckNAddStates(46, 49);
                  break;
               case 116:
                  if (curChar == 100 && kind > 8)
                     kind = 8;
                  break;
               case 117:
                  if (curChar == 110)
                     jjCheckNAdd(116);
                  break;
               case 118:
                  if (curChar == 108 && kind > 8)
                     kind = 8;
                  break;
               case 119:
                  if (curChar == 108)
                     jjCheckNAdd(118);
                  break;
               case 121:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 120;
                  break;
               case 122:
                  if (curChar == 105)
                     jjstateSet[jjnewStateCnt++] = 121;
                  break;
               case 123:
                  if (curChar == 108)
                     jjstateSet[jjnewStateCnt++] = 122;
                  break;
               case 124:
                  if (curChar == 97)
                     jjstateSet[jjnewStateCnt++] = 123;
                  break;
               case 125:
                  if (curChar == 118)
                     jjstateSet[jjnewStateCnt++] = 124;
                  break;
               case 126:
                  if (curChar == 114)
                     jjAddStates(43, 45);
                  break;
               case 127:
                  if (curChar == 97)
                     jjCheckNAdd(118);
                  break;
               case 128:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 127;
                  break;
               case 129:
                  if (curChar == 97)
                     jjCheckNAdd(116);
                  break;
               case 130:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 129;
                  break;
               case 131:
                  if (curChar == 97)
                     jjCheckNAdd(17);
                  break;
               case 132:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 131;
                  break;
               case 133:
                  if (curChar == 112)
                     jjstateSet[jjnewStateCnt++] = 132;
                  break;
               case 134:
                  if (curChar == 101)
                     jjstateSet[jjnewStateCnt++] = 133;
                  break;
               case 141:
                  if (curChar != 64)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAddStates(36, 42);
                  break;
               case 142:
                  if ((0xfffffffffffffffeL & l) != 0L)
                     jjCheckNAddStates(26, 29);
                  break;
               case 146:
                  if (curChar == 64 && kind > 6)
                     kind = 6;
                  break;
               case 147:
                  if ((0xfffffffffffffffeL & l) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjCheckNAddStates(30, 32);
                  break;
               case 152:
                  jjAddStates(93, 94);
                  break;
               case 154:
                  if (kind > 18)
                     kind = 18;
                  jjstateSet[jjnewStateCnt++] = 154;
                  break;
               case 156:
                  jjAddStates(95, 96);
                  break;
               case 158:
                  if (kind > 18)
                     kind = 18;
                  jjstateSet[jjnewStateCnt++] = 158;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
               case 71:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 17)
                     kind = 17;
                  jjCheckNAdd(71);
                  break;
               case 1:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(17, 19);
                  break;
               case 142:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(26, 29);
                  break;
               case 147:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 19)
                     kind = 19;
                  jjAddStates(30, 32);
                  break;
               case 152:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(93, 94);
                  break;
               case 154:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjstateSet[jjnewStateCnt++] = 154;
                  break;
               case 156:
                  if ((jjbitVec0[i2] & l2) != 0L)
                     jjAddStates(95, 96);
                  break;
               case 158:
                  if ((jjbitVec0[i2] & l2) == 0L)
                     break;
                  if (kind > 18)
                     kind = 18;
                  jjstateSet[jjnewStateCnt++] = 158;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 173 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   160, 161, 162, 167, 168, 170, 163, 156, 157, 158, 152, 153, 154, 139, 140, 136, 
   137, 1, 2, 4, 55, 56, 57, 63, 60, 64, 142, 143, 145, 146, 147, 148, 
   150, 54, 60, 70, 142, 143, 145, 146, 147, 148, 150, 128, 130, 134, 117, 101, 
   119, 125, 103, 105, 107, 73, 83, 92, 99, 54, 57, 59, 54, 58, 57, 54, 
   57, 58, 57, 59, 61, 69, 60, 62, 68, 65, 66, 60, 67, 64, 65, 66, 
   60, 67, 64, 62, 68, 54, 58, 57, 65, 66, 60, 67, 64, 152, 153, 156, 
   157, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT", 
};
static final long[] jjtoToken = {
   0xfe181L, 
};
static final long[] jjtoSkip = {
   0x7eL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[173];
private final int[] jjstateSet = new int[346];
protected char curChar;
/** Constructor. */
public LParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public LParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 173; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String tokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   tokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, tokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  //int kind;
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {   
   try   
   {     
      curChar = input_stream.BeginToken();
   }     
   catch(java.io.IOException e)
   {        
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
