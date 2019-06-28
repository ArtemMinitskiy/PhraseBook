package com.example.artem.phrasebook.Model;

public class StableExpression {
    private String engSE;
    private String ukrSE;

    public StableExpression() {
    }

    public StableExpression(String engSE, String ukrSE) {
        this.engSE = engSE;
        this.ukrSE = ukrSE;
    }

    public String getEngSE() {
        return engSE;
    }

    public void setEngSE(String engSE) {
        this.engSE = engSE;
    }

    public String getUkrSE() {
        return ukrSE;
    }

    public void setUkrSE(String ukrSE) {
        this.ukrSE = ukrSE;
    }
}
