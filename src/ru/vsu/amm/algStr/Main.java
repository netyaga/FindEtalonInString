package ru.vsu.amm.algStr;

import ru.vsu.amm.algStr.algorithms.Algorithm;
import ru.vsu.amm.algStr.algorithms.BlocksAlgorithm;
import ru.vsu.amm.algStr.algorithms.BorderAlgorithm;
import ru.vsu.amm.algStr.testing.AlgorithmTest;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
//
// AlgorithmTest.putInFile("resources\\aaa_1kkk", 100000000L);

        System.out.println("----------blocks----------");
        try {
            AlgorithmTest.testing(new BlocksAlgorithm(),new BorderAlgorithm());
        } catch (Exception e) {
            System.err.println("Сломал блоки");
        }

        System.out.println("----------blocks--custom--tests------");
        Algorithm blocks = new BlocksAlgorithm();
        AlgorithmTest.customTest(blocks);
        AlgorithmTest.customTest(blocks);
        AlgorithmTest.customTest(blocks);


        System.out.println("----------border--custom--tests------");
        Algorithm borders = new BorderAlgorithm();
        AlgorithmTest.customTest(borders);
        AlgorithmTest.customTest(borders);
        AlgorithmTest.customTest(borders);
    }

}
