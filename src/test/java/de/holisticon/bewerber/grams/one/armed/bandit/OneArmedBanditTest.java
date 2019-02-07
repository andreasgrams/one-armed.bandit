package de.holisticon.bewerber.grams.one.armed.bandit;

import org.junit.jupiter.api.Test;

import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;

public class OneArmedBanditTest {

    public static final IntSupplier WIN_STRATEGY = () -> 1;

    public static final IntSupplier LOSE_STRATEGY = new IntSupplier() {
        int state;

        @Override
        public int getAsInt() {
            return state++;
        }
    };

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

    /**
     * Test to win a game.
     */
    @Test
    public void shouldWinAGame() {
        //given
        final Credit initialCredits = new Credit(10);
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), initialCredits);
        cut.setLuckStrategy(WIN_STRATEGY);
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult.isGameWon()).isTrue();
    }

    /**
     * Test to lose a game when different wheels states arise from the game.
     */
    @Test
    public void shouldLoseAGame() {
        //given
        final Credit initialCredits = new Credit(10);
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), initialCredits);
        cut.setLuckStrategy(LOSE_STRATEGY);
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult.isGameWon()).isFalse();
    }
}
