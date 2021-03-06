package de.dreamnetworx.one.armed.bandit.model;

public class AdditionalInput extends PositiveValue {

    /**
     * Construct a positive AdditionalInput Instance if the given value is negative, PositiveValueException is thrown.
     *
     * @param value
     * @throws PositiveValueException When the given value is negative
     */
    public AdditionalInput(final int value) {
        super(value);
        final int price = OneArmedBandit.REGULAR_GAME_PRICE.getValue();
        if(value == 0 || value % price != 0) {
            throw new OneArmedBanditException(
                    String.format("Only additional inputs divisible by %s allowed!", price));
        }
    }

}
