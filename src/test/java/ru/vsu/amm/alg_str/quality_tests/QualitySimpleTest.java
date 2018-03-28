package ru.vsu.amm.alg_str.quality_tests;

public class QualitySimpleTest {
    public String text;
    public String pattern;
    public int answer;

    public QualitySimpleTest(String text, String pattern, int answer) {
        this.text = text;
        this.pattern = pattern;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "\nText: " + text + "\npattern: " + pattern;
    }
}
