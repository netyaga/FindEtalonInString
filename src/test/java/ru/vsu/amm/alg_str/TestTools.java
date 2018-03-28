package ru.vsu.amm.alg_str;

import org.junit.Test;

import java.io.*;
import java.util.Random;

public class TestTools {

    Random rand = new Random();


    public void putInFile(String path, long count, int range){
        try {
            FileWriter fw = new FileWriter(path);
            for( int i =0 ; i< count; i++){
                char symbol= getRandomCharacters(range);
                fw.append(symbol);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private char getRandomCharacters(int range) {
        return (char)('a'+rand.nextInt(range));
    }


    @Test
    public void createFile(){
        putInFile("src\\test\\resources\\a_100kk",100000000L, 1);
        putInFile("src\\test\\resources\\ab_100kk",100000000L, 2);
        putInFile("src\\test\\resources\\abc_100kk",100000000L, 3);
        putInFile("src\\test\\resources\\abcd_100kk",100000000L, 4);
//        putInFile("src\\test\\resources\\a_50kk",50000000L, 1);
//        putInFile("src\\test\\resources\\ab_50kk",50000000L, 2);
//        putInFile("src\\test\\resources\\abc_50kk",50000000L, 3);
//        putInFile("src\\test\\resources\\abcd_50kk",50000000L, 4);
//        putInFile("src\\test\\resources\\a_5kk",5000000L, 1);
//        putInFile("src\\test\\resources\\ab_5kk",5000000L, 2);
//        putInFile("src\\test\\resources\\abc_5kk",5000000L, 3);
//        putInFile("src\\test\\resources\\abcd_5kk",5000000L, 4);
    }
}
