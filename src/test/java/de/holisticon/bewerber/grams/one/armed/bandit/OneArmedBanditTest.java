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

    public static final Credit NOT_ENOUGH_CREDITS_TO_PLAY = new Credit(OneArmedBandit.REGULAR_GAME_PRICE).subtract(new Credit(1));

    public static final Credit CREDITS_TO_PLAY = new Credit(10);

    /**
     * Test to lose a game when different wheels states arise from the game.
     */
    @Test
    public void shouldLoseAGame() {
        //given
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), CREDITS_TO_PLAY);
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
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), CREDITS_TO_PLAY);
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
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), CREDITS_TO_PLAY);
        cut.setBanditStrategy(WIN_STRATEGY_WITH_APPLE_STATE);
        //when
        GameResult gameResult = cut.pullingHandel();
        //then
        assertThat(gameResult.isGameWon()).isTrue();
        assertThat(gameResult.getCreditsRemained())
                .isEqualTo(CREDITS_TO_PLAY
                        .subtract(OneArmedBandit.REGULAR_GAME_PRICE)
                        .addition(Wheel.APPLE.getBenefitAsCredit()));
    }

    /**
     * Check the game only started when enough credits exists. Expected a CreditException is thrown.
     */
    @Test
    public void shouldNotStartAGameWhenCreditsNotEnough() {
        //given
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), NOT_ENOUGH_CREDITS_TO_PLAY);
        //when
        try {
            cut.pullingHandel();
            //then
        } catch (CreditException e) {
            assertThat(e).hasMessageContaining("Not enough credits to play. One game costs 3 credits.");
        }
    }

    @Test
    public void shouldIncreaseCredits() {
        //given
        OneArmedBandit cut = new OneArmedBandit(new Player("Player One"), NOT_ENOUGH_CREDITS_TO_PLAY);
        //when
        Credit remainingCredits = cut.increaseCredits(CREDITS_TO_PLAY);
        //then
        assertThat(new Credit(12)).isEqualTo(remainingCredits);
        assertThat(cut.getCredits()).isEqualTo(remainingCredits);
    }

}
