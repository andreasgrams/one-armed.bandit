package de.dreamnetworx.one.armed.bandit.model;

public enum Wheel {
    APPLE(10),
    BANANA(15),
    CLEMENTINE(20),
    CHEERY(25);

    private final int profit;

    /**
     * Defined a wheel state with amount of profit.
     *
     * @param profit The profit for player when the game is won.
     */
    Wheel(int profit) {
        this.profit = profit;
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
     * @return the defined profit of wheel state as {@link Credit}.
     */
    public Credit getProfitAsCredit() {
        return new Credit(profit);
    }
}
