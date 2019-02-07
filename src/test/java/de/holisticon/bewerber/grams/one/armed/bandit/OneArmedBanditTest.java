package de.holisticon.bewerber.grams.one.armed.bandit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OneArmedBanditTest {

    /**
     * Test to start a regular priced game. Expected the remaining credits minimized by 3 credits.
     */
    @Test
    public void shouldStartARegularPricedGame() {
        //given
        final Credit initialCredits = new Credit(10);
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), initialCredits);
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult.getCreditsRemained())
                .isEqualTo(initialCredits.subtract(OneArmedBandit.REGULAR_GAME_PRICE));
    }

}
