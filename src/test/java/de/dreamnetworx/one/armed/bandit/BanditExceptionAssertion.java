package de.dreamnetworx.one.armed.bandit;

import de.dreamnetworx.one.armed.bandit.model.OneArmedBanditException;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class BanditExceptionAssertion {

    /**
     * Check a OneArmedBanditException is thrown with the containing message
     *
     * @param supplier          supplier that produced a OneArmedBanditException
     * @param containingMessage message to verify
     */
    public static void verifyBanditException(Supplier supplier, String containingMessage) {
        try {
            supplier.get();
            fail("supplier must throw a OneArmedBanditException");
        } catch (OneArmedBanditException e) {
            assertThat(e).hasMessageContaining(containingMessage);
        }
    }
}
