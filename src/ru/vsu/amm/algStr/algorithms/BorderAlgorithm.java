package ru.vsu.amm.algStr.algorithms;

import java.util.LinkedList;
import java.util.List;

public class BorderAlgorithm implements Algorithm {
    @Override
    public int method(String text, String target) {
        final String DELIMITER = "#";
        String helpString = new StringBuilder(target).append(DELIMITER)
                .append(text)
                .toString();
        int[] maxBorderArray = block(helpString);
        int targetLen = target.length();
        int countInputs = 0;
        for(int elem: maxBorderArray){
            if(elem == targetLen)
                countInputs++;
        }
        return countInputs;
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
