package ru.vsu.amm.alg_str.algorithms.online;

import java.util.Queue;
import java.util.Random;

public class WriterThread extends Thread {
    Queue<String> commonResources;
    Random rand = new Random();

    public WriterThread(Queue<String> commonResources){
        this.commonResources = commonResources;
    }
    @Override
    public void run() {
        String appendedStr;
        while(true){
            appendedStr = getString();
            commonResources.add(appendedStr);
            System.out.println("Добавил " + appendedStr);
        }
    }

    private char getRandomABCD() {
        return (char)('A'+rand.nextInt(4));
    }

    private String getString(){
        StringBuilder builder = new StringBuilder();
        builder.append(getRandomABCD())
                .append(getRandomABCD())
                .append(getRandomABCD())
                .append(getRandomABCD());
        return builder.toString();
    }
}
