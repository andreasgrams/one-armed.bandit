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
     * Test to win a game with the injected bandit strategy 'WIN_STRATEGY'. The strategy returns each time the same wheel state.
     *
     * Expected the game is won and the credits minimized by 3 credits.
     */
    @Test
    public void shouldWinAGame() {
        //given
        final Credit initialCredits = new Credit(10);
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), initialCredits);
        cut.setBanditStrategy(WIN_STRATEGY);
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
        cut.setBanditStrategy(LOSE_STRATEGY);
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult.isGameWon()).isFalse();
    }

}
