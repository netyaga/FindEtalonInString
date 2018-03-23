package ru.vsu.amm.alg_str.algorithms;

public class MoorAlgorithm implements Algorithm {
    @Override
    public int method(String text, String pattern) {
        int patternLen = pattern.length();
        int textLen = text.length();
        int[][] shiftBadSymbol = getShiftBadSymbol(pattern);
        int textIndex = 0;
        int countInputs = 0;
        while (textIndex <= textLen - patternLen){
            int patternIndex = patternLen - 1;
            while(patternIndex>=0 && pattern.charAt(patternIndex) == text.charAt(textIndex + patternIndex))
                patternIndex--;
            if(patternIndex == -1){
                countInputs++;
                textIndex++;
            }
            else{
                int indexLastInputBadSymbol = 1;
                int indexBadSymbol = text.charAt(textIndex + patternIndex) - 'a';
                while(indexLastInputBadSymbol <= shiftBadSymbol[indexBadSymbol][0] && shiftBadSymbol[indexBadSymbol][indexLastInputBadSymbol] > patternIndex)
                    indexLastInputBadSymbol++;
                textIndex += Math.max(1, patternIndex - shiftBadSymbol[indexBadSymbol][indexLastInputBadSymbol]);
            }
        }
        return countInputs;
    }

    private int[][] getShiftBadSymbol(String pattern) {
        int patternLen = pattern.length();
        char currentSymbol;
        int[][] matrixSymbolsInputs = new int[26][patternLen + 1];
        for( int i = patternLen - 1; i >= 0; i--){
            int currentCharIndex = pattern.charAt(i) - 'a';
            matrixSymbolsInputs[currentCharIndex][0] += + 1;  //в первом столбце количество вхождений
            matrixSymbolsInputs[currentCharIndex] [matrixSymbolsInputs[currentCharIndex][0]] = i;
        }
        return matrixSymbolsInputs;
    }
}
