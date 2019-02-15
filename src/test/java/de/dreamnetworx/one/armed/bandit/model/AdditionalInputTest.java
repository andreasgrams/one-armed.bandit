package de.dreamnetworx.one.armed.bandit.model;

import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class AdditionalInputTest {

    @Test
    public void shouldInitializeValidAdditionalInput() {
        //given
        //when
        final AdditionalInput additionalInput = new AdditionalInput(3);
        //then
        assertThat(additionalInput.getValue()).isEqualTo(3);
    }

    @Test
    public void shouldInitializeInValidAdditionalInput() {
        //given
        final String containingMessage = "divisible by 3 allowed";
        //when
        verifyBanditException(() -> new AdditionalInput(-1), containingMessage);
        verifyBanditException(() -> new AdditionalInput(0), containingMessage);
        verifyBanditException(() -> new AdditionalInput(2), containingMessage);
        //then
    }

    /**
     * Check a OneArmedBanditException is thrown with the containingMessage
     *
     * @param supplier          supplier that produced a OneArmedBanditException
     * @param containingMessage message to verify
     */
    private void verifyBanditException(Supplier supplier, String containingMessage) {
        try {
            supplier.get();
            fail("supplier must throw a OneArmedBanditException");
        } catch (OneArmedBanditException e) {
            assertThat(e).hasMessageContaining(containingMessage);
        }
    }
}