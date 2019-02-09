package de.holisticon.bewerber.grams.one.armed.bandit;

import org.junit.jupiter.api.Test;

import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;

public class OneArmedBanditTest {

    /**
     * Injectable Bandit strategy to win every game
     */
    public static final IntSupplier WIN_STRATEGY_WITH_APPLE_STATE = () -> 0;
    /**
     * Injectable Bandit strategy to lose every game
     */
    public static final IntSupplier LOSE_STRATEGY = new IntSupplier() {
        int state;

        @Override
        public int getAsInt() {
            return state++;
        }
    };

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

    /**
     * Test to start a regular game with the default bandit strategy. The game can be won or lose. Expected after
     * pulling the handle a GameResult returned whatever values contains.
     */
    @Test
    public void shouldStartARegularGame() {
        //given
        final Credit initialCredits = new Credit(10);
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), initialCredits);
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult.getCreditsRemained()).isNotNull();
        assertThat(gameResult.getWheels()).hasSize(3);
    }

    /**
     * Test to win a game with the injected bandit strategy 'WIN_STRATEGY_WITH_APPLE_STATE'. The strategy returns each time the same apple state.
     * <p>
     * Expected the game is won and the credits minimized by 3 credits.
     */
    @Test
    public void shouldWinAGame() {
        //given
        final Credit initialCredits = new Credit(10);
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), initialCredits);
        cut.setBanditStrategy(WIN_STRATEGY_WITH_APPLE_STATE);
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult.isGameWon()).isTrue();
        assertThat(gameResult.getCreditsRemained())
                .isEqualTo(initialCredits
                        .subtract(OneArmedBandit.REGULAR_GAME_PRICE)
                        .addition(Wheel.APPLE.getBenefitAsCredit()));
    }

}
