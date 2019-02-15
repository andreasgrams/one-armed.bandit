package de.dreamnetworx.one.armed.bandit.model;

import java.util.Objects;

public abstract class PlayMoneyImpl implements PlayMoney {

    protected int value;

    /**
     * Construct a positive PlayMoneyImpl if the given value is negative, CreditException is thrown.
     *
     * @param value
     * @throws CreditException When the given value is negative
     */
    public PlayMoneyImpl(int value) {
        if(isNegative(value)) {
            throw new CreditException(String.format("The credits must be a positive value. Given value: %d", value));
        }
        this.value = value;
    }

    /**
     * Construct a positive Credit when the given value is negative, CreditException is thrown.
     *
     * @param credit
     * @throws CreditException When the given value is negative
     */
    public PlayMoneyImpl(PlayMoney credit) {
        this(credit.getValue());
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PlayMoneyImpl playMoney = (PlayMoneyImpl) o;
        return value == playMoney.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private boolean isNegative(int value) {
        return value < 0;
    }

    public int getValue() {
        return value;
    }
}
