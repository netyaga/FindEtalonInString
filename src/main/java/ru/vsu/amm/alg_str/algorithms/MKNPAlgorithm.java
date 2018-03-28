package ru.vsu.amm.alg_str.algorithms;

public class MKNPAlgorithm implements Algorithm {
    int alphabetSize;

    public MKNPAlgorithm(int countSymbols){
        alphabetSize = countSymbols;
    }


    public int method(String text, String pattern) {
        int countInputs = 0;
        int textLen = text.length();
        int targetLen = pattern.length();
        int[][] maxBorderMap = maxBorderMatrix(pattern);
        int targetIndex = 0;
        for(int i = 0; i < textLen; i++){
            if(targetIndex>0 && pattern.charAt(targetIndex) != text.charAt(i))
                targetIndex = maxBorderMap[targetIndex - 1][text.charAt(i) - 'a'];

            if(pattern.charAt(targetIndex) == text.charAt(i))
                targetIndex++;
            if(targetIndex == targetLen){
                countInputs++;
                if(i + 1 < textLen)
                    targetIndex = maxBorderMap[targetLen - 1][text.charAt(i+1) - 'a'];
            }

        }
        return countInputs;
    }

    private int[][] maxBorderMatrix(String target) {
        int[] maxBorderArray = modifiedMaxBorderArray(target);
        int patternLen = target.length();
        int[][] map = new int[patternLen][alphabetSize];
        for(int i = 0; i < patternLen; i++){
            char nextSymbol = target.charAt(maxBorderArray[i]);
            if(nextSymbol - 'a' < alphabetSize)
                map[i][nextSymbol - 'a'] = maxBorderArray[i];
            else
                System.err.println("symbol not in alphabet: " + nextSymbol);
        }
        return map;
    }

    private int[] maxBorderArray(String str){
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

    private int[] modifiedMaxBorderArray(String str){
        int n = str.length();
        int[] maxBorderArray = maxBorderArray(str);
        int[] brs = new int[n];
        brs[0] = 0;
        brs[n-1] = maxBorderArray[n-1];
        for( int i = 1; i < n - 1; i++){
            if(maxBorderArray[i] > 0 && str.charAt(maxBorderArray[i]) == str.charAt(i+1))
                brs[i] = brs[maxBorderArray[i] - 1];
            else
                brs[i] = maxBorderArray[i];

        }
        return brs;
    }
}
