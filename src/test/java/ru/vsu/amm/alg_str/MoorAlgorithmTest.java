package ru.vsu.amm.alg_str;

import org.junit.Before;
import org.junit.Test;
import ru.vsu.amm.alg_str.algorithms.Algorithm;
import ru.vsu.amm.alg_str.algorithms.MoorAlgorithm;

import static org.junit.Assert.assertEquals;

public class MoorAlgorithmTest {

    Algorithm moor;

    @Before
    public void beforeTest(){
        moor = new MoorAlgorithm();
    }

    @Test
    public void test(){
        String text = "aaaaaaaa";
        String pattern = "a";
        assertEquals(moor.method(text, pattern), 1);
    }
}
