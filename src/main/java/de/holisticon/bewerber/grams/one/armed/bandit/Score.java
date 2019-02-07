package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.Objects;

public class Score {

    private int value;

    public Score(final int value) {
        this.value = value;
    }

    @Override
    public boolean equals(final Object o) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
