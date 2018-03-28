package ru.vsu.amm.alg_str;

import org.junit.Before;
import org.junit.Test;
import ru.vsu.amm.alg_str.algorithms.Algorithm;
import ru.vsu.amm.alg_str.algorithms.KNPAlgorithm;
import ru.vsu.amm.alg_str.algorithms.MKNPAlgorithm;

import static org.junit.Assert.assertEquals;

public class KNPAlgorithmTest {

    Algorithm knpAlgorinthm;

    @Before
    public void beforeTest(){
        knpAlgorinthm = new KNPAlgorithm();
    }

    @Test
    public void test(){
        String text = "abababaabababaababaaba";
        String pattern =  "abaababaaba";
        MKNPAlgorithm alg = new MKNPAlgorithm(26);
        int result = alg.method(text,pattern);
        assertEquals(result, 1);

        text = "aaaaaaa";
        pattern = "aaa";

        result = alg.method(text,pattern);
        assertEquals(result, 5);

        text = "aabaabaa";
        pattern = "aabaa";

        result = alg.method(text,pattern);
        assertEquals(result, 2);

        text = "aabaabaa";
        pattern = "aabbaa";

        result = alg.method(text,pattern);
        assertEquals(result, 0);

        text = "aabaabaa";
        pattern = "aabbaa";

        result = alg.method(text,pattern);
        assertEquals(result, 0);
    }
}
