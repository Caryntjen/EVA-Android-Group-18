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
    COMPLETEDCHALLENGES("7"),
    BADGE_GERM("1"),
    BADGE_FLOWER("4"),
    BADGE_TREE("19"),
    BADGE_EARTH("19"),
    BADGE_HEARTH("6"),
    BADGE_NUT("2"),
    BADGE_STAR("10"),
    BADGE_PRIZE("450"),
    BADGE_LITTLECUP("450"),
    BADGE_BIGCUP("7"),
    LEVEL("5");

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
