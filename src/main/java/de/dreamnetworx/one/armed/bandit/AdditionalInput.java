package de.dreamnetworx.one.armed.bandit;

import java.util.Objects;

public class AdditionalInput implements PlayMoney {

    private int value;

    public AdditionalInput(final int value) {
        final int price = OneArmedBandit.REGULAR_GAME_PRICE.getValue();
        if(value == 0 || value % price != 0) {
            throw new OneArmedBanditException(
                    String.format("Only additional inputs divisible by %s allowed!", price));
        }
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        AdditionalInput that = (AdditionalInput) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
