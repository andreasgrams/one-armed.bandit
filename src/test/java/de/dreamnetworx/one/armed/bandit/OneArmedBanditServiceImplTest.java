package de.dreamnetworx.one.armed.bandit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class OneArmedBanditServiceImplTest {

    private OneArmedBanditService service;

    @BeforeEach
    void setup() {
        service = new OneArmedBanditServiceImpl();
    }

    /**
     * The internal bandit state does not override by invoking checkin twice but
     * the credits incremented by the given credits.
     */
    @Test
    void shouldNotCheckInTwice() {
        //given
        service.checkin(new Credit(1));
        //when
        service.checkin(new Credit(1));
        //then
        assertThat(service.getCredits()).isEqualTo(new Credit(2));
    }

    /**
     * Block games until player not checked in. Excepted OneArmedBanditException with
     * explanation why pulling the does not work.
     */
    @Test
    void shouldNotFailOnPullingHandleWithBanditSession() {
        //given
        //when
        try {
            service.pullingHandle();
            fail("should fail when no checkin exists");
        } catch (OneArmedBanditException e) {
            //then
            assertThat(e).hasMessageContaining("Do check in before pulling the handle");
        }
    }

    /**
     * Test to get the remaining credits.
     */
    @Test
    void shouldCheckoutRemainingCredits() {
        //given
        service.checkin(new Credit(42));
        //when
        final Credit result = service.checkout();
        //then
        assertThat(result.getValue()).isEqualTo(42);
    }

    /**
     *
     */
    @Test
    void shouldNotFailToInvokeCheckoutWithoutBanditSession() {
        //given
        //when
        final Credit result = service.checkout();
        //then
        assertThat(result).isEqualTo(new Credit(0));
    }

}