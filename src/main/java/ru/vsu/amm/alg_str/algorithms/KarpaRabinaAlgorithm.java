package ru.vsu.amm.alg_str.algorithms;

import java.util.Calendar;

public class KarpaRabinaAlgorithm implements Algorithm {
    private int denominator;

    public KarpaRabinaAlgorithm(int denominator) {
        this.denominator = denominator;
    }

    @Override
    public int method(String text, String pattern) {
        //long start = Calendar.getInstance().getTimeInMillis();
        int[] patternArray = toIntArray(pattern);
        int[] textArray = toIntArray(text);
        //long end = Calendar.getInstance().getTimeInMillis();
        //System.out.println("prep: " + (end-start));
        int patternLen = patternArray.length;
        int textLen = textArray.length;
        int countInputs = 0;

        int p2m = 1;//  2^(m-1) mod q
        for (int i = 0; i < patternLen - 1; ++i)
            p2m = (p2m * 2) % denominator;

        int hashPattern = getGorner2Mod(patternArray, patternLen);
        int hashText = getGorner2Mod(textArray, patternLen);
        for (int textIndex = 0; textIndex <= textLen - patternLen; ++textIndex) // ищем вхождений
        {
            if (hashText == hashPattern) { // предохраняемся от коллиции
                //int patternIndex = 0;
                //while (patternIndex < patternLen && patternArray[patternIndex] == textArray[textIndex + patternIndex]) ++patternIndex;
                //if (patternIndex == patternLen)
                    countInputs++;
            }
            if(textIndex != textLen - patternLen) {
                hashText = ((hashText - p2m * textArray[textIndex]) * 2 + textArray[textIndex + patternLen]) % denominator;
                if (hashText < 0)
                    hashText += denominator; // Модулярная арифметика
            }
        }
        return countInputs;
    }

    private int getGorner2Mod(int[] str, int m) {
        int res = 0;
        for (int i = 0; i < m; ++i) {
            res = (res  * 2 + str[i]) % denominator;
        }
        return res;
    }

    private int[] toIntArray(String text) {
        int n = text.length();
        int[] array = new int[n];
        for( int i=0; i< n; i++){
            array[i] = text.charAt(i) - 'a';
        }
        return array;
    }
}
