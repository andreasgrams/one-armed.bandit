package de.dreamnetworx.one.armed.bandit.model;

import java.util.Objects;

public abstract class PositiveValue {

    protected int value;

    /**
     * Construct a positive value Object. If the given value negative, PositiveValueException is thrown.
     *
     * @param value
     * @throws PositiveValueException When the given value is negative
     */
    public PositiveValue(int value) {
        if(isNegative(value)) {
            throw new PositiveValueException(String.format("The value must be a positive. Given value: %d", value));
        }
        this.value = value;
    }

    /**
     * Construct a positive value Object clone. If the given value negative, PositiveValueException is thrown.
     *
     * @param credit
     * @throws PositiveValueException When the given value is negative
     */
    public PositiveValue(PositiveValue credit) {
        this(credit.getValue());
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PositiveValue playMoney = (PositiveValue) o;
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
