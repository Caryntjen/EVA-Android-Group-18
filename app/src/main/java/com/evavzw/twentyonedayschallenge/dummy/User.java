package com.evavzw.twentyonedayschallenge.dummy;

public enum User {
    EMAIL("hogent@evavzw.be"),
    PASSWORD("EvaVZW123"),
    BIRTHDAY("05-11-1985"),
    CHILDREN("2"),
    SEX("Female"),
    LANGUAGE("English"),
    STUDENT("Yes"),
    DIET("Veganism"),
    DAY("7"),
    NEWSLETTER("Yes"),
    POINTS("125"),
    COMPLETEDCHALLENGES("7");

    private final String text;

    private User(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public boolean toBool() {
        if (text.equals("Yes")||text.equals("English")||text.equals("Female")||text.equals("Veganism")) return true;
        else return false;
    }
}
