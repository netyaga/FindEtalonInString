package ru.vsu.amm.algStr;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String T = "abcabdabcabeabcabdabcabc";
        String P = "abaaba";
//        System.out.println("Target: " + T);
//        System.out.println("Patter: " + P);
//        List inputsList = borderMethod(T,P);
//        showResult(T, inputsList);

        int[] bl = block(T);
    }

    private static void showResult(String t, List<Integer> inputsList) {
        int len = t.length();
        int indexPos = 0;
        for( int i = 0; i < len; i++){
            if(indexPos < inputsList.size() && i == inputsList.get(indexPos)){
                System.out.print("|");
                indexPos++;
            }
            System.out.print(t.charAt(i));

        }
    }

    public static List<Integer> borderMethod(String target, String etalon){
        final String DELIMITER = "#";
        String helpString = new StringBuilder(etalon).append(DELIMITER)
                .append(target)
                .toString();
        int[] maxBorderArray = maxBorderArray(helpString);
        List resultPositionsList = new LinkedList<Integer>();
        int etalonLen = etalon.length();
        int targetLen = target.length();
        for(int endPosition = 0; endPosition < targetLen; endPosition++ ){
            if(maxBorderArray[endPosition + etalonLen + 1] == etalonLen)
                resultPositionsList.add(endPosition - etalonLen + 1);
        }
        return  resultPositionsList;
    }

    public static int[] maxBorderArray(String str){
        int n = str.length(); //длина строки
        int[] br = new int[n]; // массив с длинами b[i] максимальных граней строк str[0..i]
        int t; // вспомогательная переменная что-то типа: длина максимальной грани предыдущей строки
        for (int i = 1; i < n; i++){
            t = br[i-1];
            while( (t > 0) && (str.charAt(i) !=  str.charAt(t)))
                t = br[t-1]; // тип если не можем удлиннить максимальную грань,
            // пытаемся удлиннить вторую по максимальности грань
            if(str.charAt(i) ==  str.charAt(t))
                br[i] = t + 1;
            else
                br[i] = 0;
        }
        return br;
    }

    public static List<Integer> blockMethod(String target, String etalon) {
        final String DELIMITER = "#";
        String helpString = new StringBuilder(etalon).append(DELIMITER)
                .append(target)
                .toString();
        int[] maxBorderArray = block(helpString);
        List resultPositionsList = new LinkedList<Integer>();
        int etalonLen = etalon.length();
        int targetLen = target.length();
        for(int endPosition = 0; endPosition < targetLen; endPosition++ ){
            if(maxBorderArray[endPosition + etalonLen + 1] == etalonLen)
                resultPositionsList.add(endPosition - etalonLen + 1);
        }
        return  resultPositionsList;
    }

    private static int[] block(String str) {
        int n  = str.length();
        int r = 0; // правая граница блока
        int l = 0; // левая граница блока
        int[] bl = new int[n];
        bl[0] = 0;
        for(int i = 1; i < n; i++ ){
            bl[i] = 0;
            if(i > r){
                bl[i] = comp(0, i, str);
                if( bl[i] > 0){
                    r = i + bl[i] - 1;
                    l = i;
                }
            }
            else{
                int k = i - l;
                if(bl[k] < r - i + 1)
                    bl[i] = bl[k];
                else{
                    bl[i] = r - i + 1;
                    l = i;
                    int q = comp(r - i + 1, r + 1, str);
                    if(q > 0){
                        bl[i] = bl[i] + q;
                        r = i + bl[i] - 1;
                    }
                }
            }
        }
        return bl;
    }

    private static int comp(int p1, int p2, String str){
        int n = str.length();
        if(p1 >= n || p2 >= n)
            return 0;
        else{
            int t ; // минимальная длина до конца строки
            if ( n - 1 - p1 < n - 1 - p2)
                t = n - p1 - 1;
            else
                t = n - p2 - 1;

            int j = 0; // количество совпавших элементов
            while ( j <= t && str.charAt(p1 + j) == str.charAt(p2 + j))
                j++;
            return j;
        }
    }

}
