package de.dreamnetworx.one.armed.bandit;

import java.util.Objects;

public class Credit {

    private int value;

    /**
     * Construct a positive Credit when the given value is negative, CreditException is thrown.
     *
     * @param credit
     * @throws CreditException When the given value is negative
     */
    public Credit(Credit credit) {
        this(credit.getValue());
    }

    /**
     * Construct a positive Credit when the given value is negative, CreditException is thrown.
     *
     * @param value
     * @throws CreditException When the given value is negative
     */
    public Credit(final int value) {
        this.value = value;
        if(isNegative()) {
            throw new CreditException(String.format("The credits must be a positive value. Given value: %d", value));
        }
    }

    /**
     * Subtract the given credits. If the credit value is negative a CreditException is thrown.
     *
     * @param creditsToSubtract
     * @return the credit result
     * @throws CreditException
     */
    public Credit subtract(final Credit creditsToSubtract) {
        try {
            return new Credit(value - creditsToSubtract.getValue());
        } catch (CreditException e) {
            throw new CreditException(String.format(
                    "Not enough credits to play. One game costs %d credits. Remaining credits: %d",
                    OneArmedBandit.REGULAR_GAME_PRICE.getValue(), value));
        }

    }

    /**
     * Added the given credit value
     *
     * @param credit
     * @return
     */
    public Credit addition(final Credit credit) {
        return new Credit(value + credit.getValue());
    }

    private boolean isNegative() {
        return this.value < 0;
    }

    /**
     * @return the credits
     */
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return value == credit.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "Credit{" +
                "value=" + value +
                '}';
    }
}
