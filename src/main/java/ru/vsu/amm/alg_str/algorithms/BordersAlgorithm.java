package ru.vsu.amm.alg_str.algorithms;

public class BordersAlgorithm implements Algorithm {
    @Override
    public int method(String text, String target) {
        final String DELIMITER = "#";
        String helpString = new StringBuilder(target).append(DELIMITER)
                .append(text)
                .toString();
        int[] maxBorderArray = maxBorderArray(helpString);
        int targetLen = target.length();
        int countInputs = 0;
        for(int elem: maxBorderArray){
            if(elem == targetLen)
                countInputs++;
        }
        return countInputs;
    }

    private static int[] maxBorderArray(String str){
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

}
