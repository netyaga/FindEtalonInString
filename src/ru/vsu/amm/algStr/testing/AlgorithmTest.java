package ru.vsu.amm.algStr.testing;

import javafx.util.Pair;
import ru.vsu.amm.algStr.algorithms.Algorithm;

import java.io.*;
import java.nio.file.Path;
import java.util.Calendar;
import java.util.Scanner;


public class AlgorithmTest {

    private static long simpleTest(String text, String target, Algorithm algorithm) {

        long start = Calendar.getInstance().getTimeInMillis();
        int result = algorithm.method(text, target);
        long end = Calendar.getInstance().getTimeInMillis();

        return end - start;
    }

    private static long assertedTest(String text, String target, int realResult, Algorithm algorithm) throws Exception {

        long start = Calendar.getInstance().getTimeInMillis();
        int result = algorithm.method(text, target);
        long end = Calendar.getInstance().getTimeInMillis();

        if (result != realResult )
            throw new Exception();
        return end - start;
    }

    public static void customTest(Algorithm alg){
        String text;
        String target;
        System.out.println("Ipt text:");
        Scanner sc = new Scanner(System.in);
        text = sc.next();
        System.out.println("Ipt target:");
        target = sc.next();
        System.out.println("count Input = " + alg.method(text, target));

    }


    private static String getStringFromFile(String path)  {
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

    public static void putInFile(String path, long count){
        try {
            FileWriter fw = new FileWriter(path);
            for( int i =0 ; i< count; i++){
                fw.append("a");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testing(Algorithm algorithm1, Algorithm algorithm2) throws Exception {
        String text;
        String target;
        String pathToTarget;
        String pathToText;
        long time;

        text = "abaabaaaaba";
        target = "aba";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", text, target,
                assertedTest(text,target,3, algorithm1),
                assertedTest(text,target,3, algorithm2));

        text = "abaabaaaaba";
        target = "abaabaaaa";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", text, target,
                assertedTest(text,target,1, algorithm1),
                assertedTest(text,target,1, algorithm2));


        text = "aaaaaaaa";
        target = "a";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", text, target,
                assertedTest(text,target,8, algorithm1),
                assertedTest(text,target,8, algorithm2));

        text = "aaaaaaaa";
        target = "aa";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", text, target,
                assertedTest(text,target,7, algorithm1),
                assertedTest(text,target,7, algorithm2));

        text = "aaaaaaaa";
        target = "b";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", text, target,
                assertedTest(text,target,0, algorithm1),
                assertedTest(text,target,0, algorithm2));



        pathToText = "resources\\war";
        text = getStringFromFile(pathToText);
        target = "война";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, target,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));

        target = "мир";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, target,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));



        pathToText = "resources\\aaa_100k";
        pathToTarget = "resources\\aaa_10k";
        text = getStringFromFile(pathToText);
        target = getStringFromFile(pathToTarget);
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, pathToTarget,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));

        pathToText = "resources\\aaa_1kkk";
        pathToTarget = "resources\\aaa_10k";
        text = getStringFromFile(pathToText);
        target = getStringFromFile(pathToTarget);
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, pathToTarget,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));

        pathToText = "resources\\aaa_1kkk";
        pathToTarget = "resources\\aaa_100k";
        text = getStringFromFile(pathToText);
        target = getStringFromFile(pathToTarget);
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, pathToTarget,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));

        pathToText = "resources\\aaa_1kkk";
        text = getStringFromFile(pathToText);
        target = "b";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, target,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));


        pathToText = "resources\\aaa_100k";
        text = getStringFromFile(pathToText);
        target = "b";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, target,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));

        pathToText = "resources\\aaa_10k";
        text = getStringFromFile(pathToText);
        target = "b";
        System.out.printf("\ntext = %s \n find = %s \n bl_time = %s\n br_time = %s\n", pathToText, target,
                simpleTest(text,target, algorithm1),
                simpleTest(text,target, algorithm2));
    }
}
