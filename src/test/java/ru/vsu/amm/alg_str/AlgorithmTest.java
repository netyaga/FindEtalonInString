package ru.vsu.amm.alg_str;

import org.junit.Before;
import org.junit.Test;
import ru.vsu.amm.alg_str.algorithms.Algorithm;
import ru.vsu.amm.alg_str.algorithms.BlockAlgorithm;
import ru.vsu.amm.alg_str.algorithms.BordersAlgorithm;
import ru.vsu.amm.alg_str.algorithms.KNPAlgorithm;

import java.io.*;
import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class AlgorithmTest {

    Algorithm borderMethod;
    Algorithm blocksMethod;
    Algorithm knpMethod;

    Random rand = new Random();

    private long simpleTest(String text, String target, Algorithm algorithm) {

        long start = Calendar.getInstance().getTimeInMillis();
        int result = algorithm.method(text, target);
        long end = Calendar.getInstance().getTimeInMillis();

        return end - start;
    }

    private long assertedTest(String text, String target, int realResult, Algorithm algorithm) throws Exception {

        long start = Calendar.getInstance().getTimeInMillis();
        int result = algorithm.method(text, target);
        long end = Calendar.getInstance().getTimeInMillis();
        assert(result == realResult);
        return end - start;
    }

    private String getStringFromFile(String path)  {
        StringBuffer buffer = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while(line != null) {
                buffer.append(line);
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("File does not exist: " + path);
        } catch (IOException e) {
            System.err.println("Some exception in read file");
        }
        return buffer.toString();
    }

    public void putInFile(String path, long count){
        try {
            FileWriter fw = new FileWriter(path);
            for( int i =0 ; i< count; i++){
                char symbol= getRandomABCD();
                fw.append(symbol);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char getRandomABCD() {
        return (char)('A'+rand.nextInt(4));
    }

    @Before
    public void beforeTest(){
        borderMethod = new BordersAlgorithm();
        blocksMethod = new BlockAlgorithm();
        knpMethod = new KNPAlgorithm();
//        putInFile("src\\test\\resources\\abcd_1kkk",1000000000L);
//        putInFile("src\\test\\resources\\abcd_1k",1000L);
    }
    @Test
    public void testing() throws Exception {
        String text;
        String target;
        String pathToTarget;
        String pathToText;

        text = "abaabaaaaba";
        target = "aba";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", text, target,
                assertedTest(text, target, 3, blocksMethod),
                assertedTest(text, target, 3, borderMethod),
                assertedTest(text, target, 3, knpMethod));

        text = "abaabaaaaba";
        target = "abaabaaaa";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", text, target,
                assertedTest(text, target, 1, blocksMethod),
                assertedTest(text, target, 1, borderMethod),
                assertedTest(text, target, 1, knpMethod));


        text = "aaaaaaaa";
        target = "a";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", text, target,
                assertedTest(text, target, 8, blocksMethod),
                assertedTest(text, target, 8, borderMethod),
                assertedTest(text, target, 8, knpMethod));

        text = "aaaaaaaa";
        target = "aa";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", text, target,
                assertedTest(text, target, 7, blocksMethod),
                assertedTest(text, target, 7, borderMethod),
                assertedTest(text, target, 7, knpMethod));

        text = "aaaaaaaa";
        target = "b";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", text, target,
                assertedTest(text, target, 0, blocksMethod),
                assertedTest(text, target, 0, borderMethod),
                assertedTest(text, target, 0, knpMethod));


        pathToText = "src\\test\\resources\\war";
        text = getStringFromFile(pathToText);
        target = "война";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", pathToText, target,
                simpleTest(text, target, blocksMethod),
                simpleTest(text, target, borderMethod),
                simpleTest(text, target, knpMethod));

        target = "мир";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", pathToText, target,
                simpleTest(text, target, blocksMethod),
                simpleTest(text, target, borderMethod),
                simpleTest(text, target, knpMethod));


        pathToText = "src\\test\\resources\\aaa_100k";
        pathToTarget = "src\\test\\resources\\aaa_10k";
        text = getStringFromFile(pathToText);
        target = getStringFromFile(pathToTarget);
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", pathToText, pathToTarget,
                simpleTest(text, target, blocksMethod),
                simpleTest(text, target, borderMethod),
                simpleTest(text, target, knpMethod));

        pathToText = "src\\test\\resources\\aaa_1kkk";
        pathToTarget = "src\\test\\resources\\aaa_10k";
        text = getStringFromFile(pathToText);
        target = getStringFromFile(pathToTarget);
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", pathToText, pathToTarget,
                simpleTest(text, target, blocksMethod),
                simpleTest(text, target, borderMethod),
                simpleTest(text, target, knpMethod));

        pathToText = "src\\test\\resources\\aaa_1kkk";
        pathToTarget = "src\\test\\resources\\aaa_100k";
        text = getStringFromFile(pathToText);
        target = getStringFromFile(pathToTarget);
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", pathToText, pathToTarget,
                simpleTest(text, target, blocksMethod),
                simpleTest(text, target, borderMethod),
                simpleTest(text, target, knpMethod));

        pathToText = "src\\test\\resources\\abcd_1kkk";
        pathToTarget = "src\\test\\resources\\abcd_1k";
        text = getStringFromFile(pathToText);
        target = getStringFromFile(pathToTarget);
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\nknp_time = %s\n", pathToText, pathToTarget,
                simpleTest(text, target, blocksMethod),
                simpleTest(text, target, borderMethod),
                simpleTest(text, target, knpMethod));
    }
}
