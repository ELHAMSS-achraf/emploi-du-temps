package com.company;

public class Utils {
    public static String rightPad (String str,char c,int i){
        String car = String.valueOf(c);
        String chaine = "";
        for (int j = 0; j < i-str.length(); j++) {
            chaine+=car ;

        }
        return str+chaine;
    }
}
