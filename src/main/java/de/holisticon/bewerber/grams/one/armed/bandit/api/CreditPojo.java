package de.holisticon.bewerber.grams.one.armed.bandit.api;

public class CreditPojo {

    private int credits;

    public CreditPojo(final int credits) {
        this.credits = credits;
    }

    public CreditPojo() {
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(final int credits) {
        this.credits = credits;
    }

}