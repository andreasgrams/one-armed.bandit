package de.dreamnetworx.one.armed.bandit.model;

import org.junit.jupiter.api.Test;

import static de.dreamnetworx.one.armed.bandit.BanditExceptionAssertion.verifyBanditException;
import static org.assertj.core.api.Assertions.assertThat;

class PositiveValueTest {

    @Test
    void shouldNotInitializeWithNegativeValue() {
        //given
        //when
        verifyBanditException(() -> new TestablePositiveValue(-1), "The value must be a positive. Given value: -1");
        //then
    }

    @Test
    void shouldInitializePositiveValue() {
        //given
        //when
        final TestablePositiveValue cut = new TestablePositiveValue(2);
        //then
        assertThat(cut.getValue()).isEqualTo(2);
    }

    class TestablePositiveValue extends PositiveValue {

        public TestablePositiveValue(final int value) {
            super(value);
        }
    }
}