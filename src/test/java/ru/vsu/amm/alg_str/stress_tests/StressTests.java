package ru.vsu.amm.alg_str.stress_tests;


import org.junit.Before;
import org.junit.Test;
import ru.vsu.amm.alg_str.algorithms.*;



import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class StressTests {

    List<Algorithm> listAlg;
    List<StressSimpleTest> listTests;

    private long simpleTest(String text, String target, Algorithm algorithm) {

        long start = Calendar.getInstance().getTimeInMillis();
        algorithm.method(text, target);
        long end = Calendar.getInstance().getTimeInMillis();

        return end - start;
    }

    @Before
    public void beforeTest(){
        listAlg = new LinkedList<Algorithm>();
        listAlg.add(new BlockAlgorithm());
        listAlg.add(new BorderAlgorithm());
        listAlg.add(new KNPAlgorithm());//hellelle...h elle
        listAlg.add(new MKNPAlgorithm(4));
        listAlg.add(new MoorAlgorithm());

        listTests = new LinkedList<StressSimpleTest>();
        listTests.add(new StressSimpleTest("src\\test\\resources\\ab_100kk","src\\test\\resources\\a_5kk"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\abcd_100kk","src\\test\\resources\\a_5kk"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\abc_100kk","abcabcabc"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\abcd_100kk","src\\test\\resources\\abc_5kk"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\abc_100kk","src\\test\\resources\\ab_5kk"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\abcd_100kk","src\\test\\resources\\abcd_5kk"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\ab_50kk","abbbaabbbaabbbaabbbaabbbaabbbaabbbaabbbaabbba"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\abc_100kk","src\\test\\resources\\pattern_mknp1"));
        listTests.add(new StressSimpleTest("src\\test\\resources\\text_mknp","src\\test\\resources\\pattern_mknp"));

    }
    @Test
    public void testing() throws Exception {
        for(StressSimpleTest test: listTests ){
            System.out.println(test.toString());
            for(Algorithm alg: listAlg){
                System.out.println("\n"+alg.getClass().toString() + "\t--->\t" + simpleTest(test.getText(), test.getPattern(), alg));
            }
        }
    }
}
