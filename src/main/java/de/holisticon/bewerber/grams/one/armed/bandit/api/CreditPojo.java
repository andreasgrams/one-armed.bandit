package de.holisticon.bewerber.grams.one.armed.bandit.api;

public class CreditPojo {

    private int value;

    public CreditPojo(final int value) {
        this.value = value;
    }

    public CreditPojo() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(final int value) {
        this.value = value;
    }

}
