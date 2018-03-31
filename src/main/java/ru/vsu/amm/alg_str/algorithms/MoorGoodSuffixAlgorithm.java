package ru.vsu.amm.alg_str.algorithms;

public class MoorGoodSuffixAlgorithm implements Algorithm {
    @Override
    public int method(String text, String pattern) {
        int patternLen = pattern.length();
        int textLen = text.length();
        //Для сдвига при совпадении паттернов на величину грани всего паттерна
        int[] maxBorderArray = maxBorderArray(pattern);
        //для сдвига по правому вхождению несовпавшего символа или символа, которого нет в паттерне
        int[][] shiftBadSymbol = getShiftBadSymbol(pattern);
        //для сдвига по правому вхождению копии суффикса до несовпавшего символа
        int[] goodSuffixArray = getGoodSuffixArray(pattern);
        //для сдвига по грани длины меньшей, чем количество совпавших символов до несовпадения
        int[] jumpBorderArray = getJumpBorderArray(pattern);
        int textIndex = 0;
        int countInputs = 0;
        int jump = 0;
        while (textIndex <= textLen - patternLen) {
            int patternIndex = patternLen - 1;
            while (patternIndex >= jump && pattern.charAt(patternIndex) == text.charAt(textIndex + patternIndex))
                patternIndex--;
            if (patternIndex == jump - 1) {
                countInputs++;
                textIndex+=patternLen - maxBorderArray[patternLen - 1]  ;
                jump = maxBorderArray[patternLen-1];
            } else {
                int shift = 1;
                if(patternIndex + 1 < patternLen) {
                    if (goodSuffixArray[patternIndex + 1] == 0)
                        shift = patternLen - jumpBorderArray[patternIndex + 1];
                    else
                        shift = patternIndex + 1 - goodSuffixArray[patternIndex + 1];
                }
                int indexLastInputBadSymbol = 1;
                int indexBadSymbol = text.charAt(textIndex + patternIndex) - 'a';
                while (indexLastInputBadSymbol <= shiftBadSymbol[indexBadSymbol][0] && shiftBadSymbol[indexBadSymbol][indexLastInputBadSymbol] > patternIndex)
                    indexLastInputBadSymbol++;
                shift = Math.max(shift, patternIndex - shiftBadSymbol[indexBadSymbol][indexLastInputBadSymbol]);
                textIndex += shift;
                if(patternIndex + 1 < patternLen && shift == patternLen - jumpBorderArray[patternIndex + 1])
                    jump = shift;
                else
                    jump = 0;
            }
        }
        return countInputs;
    }

    private int[][] getShiftBadSymbol(String pattern) {
        int patternLen = pattern.length();
        int[][] matrixSymbolsInputs = new int[26][patternLen + 1];
        for (int i = patternLen - 1; i >= 0; i--) {
            int currentCharIndex = pattern.charAt(i) - 'a';
            matrixSymbolsInputs[currentCharIndex][0] += +1;  //в первом столбце количество вхождений
            matrixSymbolsInputs[currentCharIndex][matrixSymbolsInputs[currentCharIndex][0]] = i;
        }
        return matrixSymbolsInputs;
    }

    private int[] maxBorderArray(String str) {
        int n = str.length(); //длина строки
        int[] br = new int[n]; // массив с длинами b[i] максимальных граней строк str[0..i]
        int t; // вспомогательная переменная что-то типа: длина максимальной грани предыдущей строки
        for (int i = 1; i < n; i++) {
            t = br[i - 1];
            while ((t > 0) && (str.charAt(i) != str.charAt(t)))
                t = br[t - 1]; // тип если не можем удлиннить максимальную грань,
            // пытаемся удлиннить вторую по максимальности грань
            if (str.charAt(i) == str.charAt(t))
                br[i] = t + 1;
            else
                br[i] = 0;
        }
        return br;
    }

    /*
    * Символы, предшествующие суффиксу паттерна и его соответствующей копии не совпадают
    */
    private int[] modifiedSufMaxBorderArray(String str) {
        int[] sufMaxBorderArray = getSuffixMaxBorderArray(str);
        int len = str.length();
        int[] modifiedSufMaxBorderArray = new int[len];
        for (int i = len - 2; i > 0; i--) {
            if (str.charAt(len - sufMaxBorderArray[i] - 1) != str.charAt(i - 1))
                modifiedSufMaxBorderArray[i] = sufMaxBorderArray[i];
            else
                modifiedSufMaxBorderArray[i] = modifiedSufMaxBorderArray[sufMaxBorderArray[i]];
        }
        modifiedSufMaxBorderArray[0] = sufMaxBorderArray[0];
        return modifiedSufMaxBorderArray;
    }

    private int[] getGoodSuffixArray(String str) {
        int[] modifSufMaxBorderArray = modifiedSufMaxBorderArray(str);
        int len = str.length();
        int[] goodSufArray = new int[len];
        int begginSuffBorder;
        for (int i = 0; i < len; i++) {
            if (modifSufMaxBorderArray[i] > 0) {
                begginSuffBorder = len - modifSufMaxBorderArray[i];
                goodSufArray[begginSuffBorder] = i;
            }
        }
        return goodSufArray;
    }

    private int[] getSuffixMaxBorderArray(String str) {
        int len = str.length();
        String reverseStr = new StringBuffer(str).reverse().toString();
        int[] maxBorderArray = maxBorderArray(reverseStr);
        int[] reverseMaxBorderArray = new int[len];
        for (int i = 0; i < len; i++) {
            reverseMaxBorderArray[i] = maxBorderArray[len - i - 1];
        }
        return reverseMaxBorderArray;
    }

    private int[] getJumpBorderArray(String pattern) {
        int len = pattern.length();
        int[] jumpBorderArray = new int[len];
        int[] maxBorderArray = maxBorderArray(pattern);
        int lengthMaxBorder = -1; // индексация же с нуля
        int currentBorderLen = maxBorderArray[len - 1];
        for (int q = 0; q < len; q++) {
            if((q > lengthMaxBorder) && q < len - currentBorderLen )
                jumpBorderArray[q] = currentBorderLen;
            if( q == len - currentBorderLen){
                lengthMaxBorder = len - currentBorderLen;
                currentBorderLen = maxBorderArray[currentBorderLen - 1];
            }
        }

        return  jumpBorderArray;
    }
}
