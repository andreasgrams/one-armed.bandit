package de.holisticon.bewerber.grams.one.armed.bandit;

public enum Wheel {
    A(10), B(20), C(30);

    private final int credis;

    Wheel(int credits) {
        this.credis = credits;
    }

    public int getCredit() {
        return credis;
    }
}
