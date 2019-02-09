package de.holisticon.bewerber.grams.one.armed.bandit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WheelTest {

    /**
     * The one armed bandit should use more then one wheel definitions. Otherwise the player wins each time.
     */
    @Test
    public void shouldProvideMoreThenOneWheelState() {
        assertThat(Wheel.values().length).isGreaterThan(1);
    }

}