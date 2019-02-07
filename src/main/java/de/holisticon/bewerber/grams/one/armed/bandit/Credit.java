package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.Objects;

public class Credit {

    private int value;

    public Credit(final int credits) {
        this.value = credits;
    }

    public Credit subtract(final Credit regularGamePrice) {
        return new Credit(value - regularGamePrice.getValue());
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

    public int getValue() {
        return value;
    }
}
