package de.dreamnetworx.one.armed.bandit;

public class AdditionalInput {

    private int value;

    public AdditionalInput(final int value) {
        final int price = OneArmedBandit.REGULAR_GAME_PRICE.getValue();
        if(value == 0 || value % price != 0) {
            throw new OneArmedBanditException(
                    String.format("Only additional inputs divisible by %s allowed!", price));
        }
        this.value = value;
    }

    public Credit asCredit() {
        return new Credit(value);
    }

    public int getValue() {
        return value;
    }
}
