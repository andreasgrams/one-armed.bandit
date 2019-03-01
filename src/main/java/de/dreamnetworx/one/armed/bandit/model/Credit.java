package de.dreamnetworx.one.armed.bandit.model;

import java.util.Objects;

public class Credit extends PositiveValue {

    /**
     * Construct a positive Credit Instance if the given value is negative, PositiveValueException is thrown.
     *
     * @param value
     * @throws PositiveValueException When the given value is negative
     */
    public Credit(final int value) {
        super(value);
    }

    /**
     * Construct a positive Credit when the given value is negative, PositiveValueException is thrown.
     *
     * @param credit
     * @throws PositiveValueException When the given value is negative
     */
    public Credit(final PositiveValue credit) {
        super(credit);
    }

    /**
     * Subtract the given credits. If the credit value is negative a PositiveValueException is thrown.
     *
     * @param creditsToSubtract
     * @return the credit result
     * @throws PositiveValueException
     */
    public Credit subtract(final PositiveValue creditsToSubtract) {
        try {
            return new Credit(value - creditsToSubtract.getValue());
        } catch (PositiveValueException e) {
            String hint = "";
            int costs = OneArmedBandit.REGULAR_GAME_PRICE.getValue();
            if(creditsToSubtract instanceof AdditionalInput) {
                hint = " (with additional input)";
                costs += creditsToSubtract.getValue();
            }
            throw new PositiveValueException(String.format(
                    "Not enough credits to play. This game costs %d credits%s. Remaining credits: %d",
                    costs, hint, value));
        }
    }

    /**
     * Added the given credit value
     *
     * @param credit
     * @return
     */
    public Credit addition(final PositiveValue credit) {
        return new Credit(value + credit.getValue());
    }

    /**
     * Multiplied the credit By given parameter
     *
     * @param multipliedBy
     * @return
     */
    public Credit multiplied(final PositiveValue multipliedBy) {
        return new Credit(value * multipliedBy.getValue());
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


    /**
     * @return the credits
     */
    public int getValue() {
        return value;
    }
}
