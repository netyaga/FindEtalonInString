package ru.vsu.amm.alg_str;

import org.junit.Test;
import ru.vsu.amm.alg_str.algorithms.online.FinderThread;
import ru.vsu.amm.alg_str.algorithms.online.WriterThread;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadAlgorithmTest {
    @Test
    public void test(){
        Queue<String> resources = new LinkedList<String>();
        FinderThread finder = new FinderThread(resources,"ABCDABCDABCD", "DAB");
        WriterThread writer = new WriterThread(resources);
       // writer.run();
        finder.run();
    }
}
