package ru.vsu.amm.alg_str.algorithms;

public class ShiftAndAlgorithm implements Algorithm {
    @Override
    public int method(String text, String pattern) {
        int countInputs = 0;
        int m = pattern.length();
        int n = text.length();
        char chBeg = '0';
        char chEnd = 'z'; // Алфавит: от цифр до букв латиницы
        int nA = chEnd - chBeg + 1; // Длина алфавита
        // Подготовка массива вхождений
        long[] B=new long[nA];
        for (int k = 0; k < nA; ++k){
            B[k] = 0;
        }
        for (int j = 0; j < m; ++j){
            B[pattern.charAt(j) - chBeg] |= 1 << (m-1-j);
        }
        int uHigh = 1 << (m-1); // Константа для установки 1 в старший разряд
        long M = 0;
        // Вычисление «строк матрицы» и фиксация вхождений
        for (int i = 0; i < n; ++i){
            M = (M >> 1 | uHigh) & B[text.charAt(i) - chBeg];
            if ((M & 1) > 0)
                countInputs++;
        }
        return countInputs;
    }
}
