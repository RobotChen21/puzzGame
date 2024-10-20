package com.xidian.util;

import java.util.Random;

public class CodeUtil {
    private CodeUtil(){}
    public static String getCode(){
       char[] charArr = creatArr();
       Random rd = new Random();
       StringBuilder code = new StringBuilder();
       code.append(rd.nextInt(10));
       code.append(rd.nextInt(10));
       for (int i = 0; i < 4; i++) {
           code.append(charArr[rd.nextInt(26*2)]);
       }
       char[] charCode = code.toString().toCharArray();
       for (int i = 0; i < charCode.length; i++) {
            int random = rd.nextInt(charCode.length);
            char temp = charCode[random];
            charCode[random] = charCode[0];
            charCode[0] = temp;
       }
       return new String(charCode);
    }

    private static char[] creatArr() {
        char[] charArr = new char[26*2];
        for (int i = 0; i < 26; i++) {
            charArr[i] = (char)('a' + i);
            charArr[i+26] = (char)('A' + i);
        }
        return charArr;
    }
}
