package ru.vsu.amm.alg_str.quality_tests;

import org.junit.Before;
import org.junit.Test;
import ru.vsu.amm.alg_str.algorithms.*;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

public class QualityTests {

    List<Algorithm> algorithms;
    List<QualitySimpleTest> tests;

    @Before
    public void beforeTest(){
        algorithms = new LinkedList<Algorithm>();
        algorithms.add(new BlockAlgorithm());
        algorithms.add(new BorderAlgorithm());
        algorithms.add(new KNPAlgorithm());
        algorithms.add(new MKNPAlgorithm(26));
        algorithms.add(new MoorBadSymbolAlgorithm());
        algorithms.add(new MoorGoodSuffixAlgorithm());
        algorithms.add(new KarpaRabinaAlgorithm((int)Math.pow(2, 61) - 1));
        algorithms.add(new ShiftAndAlgorithm());

        tests = new LinkedList<QualitySimpleTest>();
        tests.add(new QualitySimpleTest("abaabaaaaba","aba", 3));
        tests.add(new QualitySimpleTest("aabaabaaaaba","abaabaaaa", 1));
        tests.add(new QualitySimpleTest("aaaaaaaa","a", 8));
        tests.add(new QualitySimpleTest("aaaaaaaa","aa", 7));
        tests.add(new QualitySimpleTest("aaaaaaaa","b", 0));
        //tests.add(new QualitySimpleTest("ababcxcdedeaxaabcxabcde","abcxabcde", 1));
    }

    @Test
    public void testing() throws Exception {
        for(QualitySimpleTest test: tests){
            System.out.println(test.toString());
            for(Algorithm alg: algorithms){
                int result = alg.method(test.text, test.pattern);
                if( result != test.answer){
                    System.err.println(alg.getClass().toString());
                }
                assertEquals(result, test.answer);

            }
        }
    }
}
