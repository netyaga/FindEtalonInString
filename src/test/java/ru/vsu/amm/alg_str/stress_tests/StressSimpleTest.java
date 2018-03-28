package ru.vsu.amm.alg_str.stress_tests;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StressSimpleTest {
    private String textPath;
    private String patternPath;

    public StressSimpleTest(String textPath, String patternPath) {
        this.textPath = textPath;
        this.patternPath = patternPath;
    }

    public String getText(){
        return getStringFromFile(textPath);
    }
    public String getPattern(){
        return (patternPath.contains("src\\"))?getStringFromFile(patternPath): patternPath;
    }

    public String getStringFromFile(String path)  {
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

    @Override
    public String toString() {
       return "\ntext_path: " + textPath + "\npattern_path: " + patternPath;
    }
}
