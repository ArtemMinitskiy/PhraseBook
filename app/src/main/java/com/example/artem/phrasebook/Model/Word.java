package com.example.artem.phrasebook.Model;

public class Word {
    private String engWord;
    private String ukrWord;

    public Word() {
    }

    public Word(String engWord, String ukrWord) {
        this.engWord = engWord;
        this.ukrWord = ukrWord;
    }

    public String getEngWord() {
        return engWord;
    }

    public void setEngWord(String engWord) {
        this.engWord = engWord;
    }

    public String getUkrWord() {
        return ukrWord;
    }

    public void setUkrWord(String ukrWord) {
        this.ukrWord = ukrWord;
    }
}
