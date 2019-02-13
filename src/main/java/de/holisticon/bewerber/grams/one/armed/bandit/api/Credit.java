package de.holisticon.bewerber.grams.one.armed.bandit.api;

public class Credit {

    private int value;

    public Credit(final int value) {
        this.value = value;
    }

    public Credit() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

}
