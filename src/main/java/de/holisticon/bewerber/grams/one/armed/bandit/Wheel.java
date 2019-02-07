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

    /**
     * Return the item from index. The index started by 0.
     *
     * @param index
     * @return
     * @throws IndexOutOfBoundsException when index outside existing items.
     */
    public static Wheel valueByIndex(int index) {
        return values()[index];
    }
}
