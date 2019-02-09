package de.holisticon.bewerber.grams.one.armed.bandit;

public enum Wheel {
    APPLE(10),
    B(20),
    C(30);

    private final int benefit;

    /**
     * Defined a wheel state with benefit.
     *
     * @param benefit The benefit for player when the game is won.
     */
    Wheel(int benefit) {
        this.benefit = benefit;
    }

    /**
     * @param index started by 0.
     * @return the item on index
     * @throws IndexOutOfBoundsException when index outside existing items.
     */
    public static Wheel valueByIndex(int index) {
        return values()[index];
    }

    /**
     * @return the defined benefit of wheel state.
     */
    public int getBenefit() {
        return benefit;
    }

    /**
     * @return the defined benefit of wheel state as Credit.
     */
    public Credit getBenefitAsCredit() {
        return new Credit(benefit);
    }
}
