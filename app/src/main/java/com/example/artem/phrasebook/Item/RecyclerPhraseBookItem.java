package com.example.artem.phrasebook.Item;

public class RecyclerPhraseBookItem {
    private int id;
    private String phrase;
    private String translate;

    public RecyclerPhraseBookItem(int id, String phrase, String translate) {
        this.id = id;
        this.phrase = phrase;
        this.translate = translate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }
}
