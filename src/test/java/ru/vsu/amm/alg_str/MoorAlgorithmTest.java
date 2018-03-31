package ru.vsu.amm.alg_str;

import org.junit.Before;
import org.junit.Test;
import ru.vsu.amm.alg_str.algorithms.Algorithm;
import ru.vsu.amm.alg_str.algorithms.MoorBadSymbolAlgorithm;
import ru.vsu.amm.alg_str.algorithms.MoorGoodSuffixAlgorithm;

import static org.junit.Assert.assertEquals;

public class MoorAlgorithmTest {

    Algorithm moor;

    @Before
    public void beforeTest(){
        moor = new MoorGoodSuffixAlgorithm();
    }

    @Test
    public void test(){
        String text = "abaabaabaababaabaaba";
        String pattern = "abaaba";
        assertEquals(moor.method(text, pattern),1);
    }
}
