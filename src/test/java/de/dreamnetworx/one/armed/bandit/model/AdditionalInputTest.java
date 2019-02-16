package de.dreamnetworx.one.armed.bandit.model;

import org.junit.jupiter.api.Test;

import static de.dreamnetworx.one.armed.bandit.BanditExceptionAssertion.verifyBanditException;
import static org.assertj.core.api.Assertions.assertThat;

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
        verifyBanditException(() -> new AdditionalInput(0), containingMessage);
        verifyBanditException(() -> new AdditionalInput(2), containingMessage);
        //then
    }

}