package com.example.artem.phrasebook.Model;

public class Phrase {
    private String engPhrase;
    private String ukrPhrase;

    public Phrase() {
    }

    public Phrase(String engPhrase, String ukrPhrase) {
        this.engPhrase = engPhrase;
        this.ukrPhrase = ukrPhrase;
    }

    public String getEngPhrase() {
        return engPhrase;
    }

    public void setEngPhrase(String engPhrase) {
        this.engPhrase = engPhrase;
    }

    public String getUkrPhrase() {
        return ukrPhrase;
    }

    public void setUkrPhrase(String ukrPhrase) {
        this.ukrPhrase = ukrPhrase;
    }
}
