package ru.vsu.amm.alg_str.algorithms;



public class KNPAlgorithm implements Algorithm {
    @Override
    public int method(String text, String target) {
        int countInputs = 0;
        int textLen = text.length();
        int targetLen = target.length();
        int[] maxBorderArray = maxBorderArray(target);
        int targetIndex = 0;
        for(int i = 0; i < textLen; i++){
            while( targetIndex > 0 && target.charAt(targetIndex) != text.charAt(i))
                targetIndex = maxBorderArray[targetIndex - 1];

            if(target.charAt(targetIndex) == text.charAt(i))
                targetIndex++;
            if(targetIndex == targetLen){
                countInputs++;
                targetIndex = maxBorderArray[targetLen - 1];
            }

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
