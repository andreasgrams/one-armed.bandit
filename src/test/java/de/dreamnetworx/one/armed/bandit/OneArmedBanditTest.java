package de.dreamnetworx.one.armed.bandit;

import org.junit.jupiter.api.Test;

import java.util.function.IntSupplier;

import static org.assertj.core.api.Assertions.assertThat;

class OneArmedBanditTest {

    /**
     * Injectable Bandit strategy to win each game
     */
    static final IntSupplier WIN_STRATEGY_WITH_APPLE_STATE = () -> 0;
    static final Credit NOT_ENOUGH_CREDITS_TO_PLAY = new Credit(OneArmedBandit.REGULAR_GAME_PRICE)
            .subtract(new Credit(1));
    static final Credit CREDITS_TO_PLAY = new Credit(10);

    /**
     * Test to lose a game when different wheels states arise from the game.
     */
    @Test
    void shouldLoseAGame() {
        //given
        OneArmedBandit cut = new OneArmedBandit(CREDITS_TO_PLAY);
        cut.setBanditStrategy(getLoseStrategy());
        //when
        GameResult gameResult = cut.pullingHandle();
        //then
        assertThat(gameResult.isGameWon()).isFalse();
    }

    /**
     * Test to start a regular game with the default bandit strategy. The game result is not interested. Expected after
     * pulling the handle a GameResult returned with any values.
     */
    @Test
    void shouldStartARegularGame() {
        //given
        OneArmedBandit cut = new OneArmedBandit(CREDITS_TO_PLAY);
        //when
        GameResult gameResult = cut.pullingHandle();
        //then
        assertThat(gameResult.getCreditsRemained()).isNotNull();
        assertThat(gameResult.getWheels()).hasSize(3);
    }

    /**
     * Test to win a game with the injected bandit strategy 'WIN_STRATEGY_WITH_APPLE_STATE'. The strategy returns
     * each time the same apple state.
     * <p>
     * Expected the game is won and the credits minimized by 3 credits.
     */
    @Test
    void shouldWinAGame() {
        //given
        OneArmedBandit cut = new OneArmedBandit(CREDITS_TO_PLAY);
        cut.setBanditStrategy(WIN_STRATEGY_WITH_APPLE_STATE);
        //when
        GameResult gameResult = cut.pullingHandle();
        //then
        assertThat(gameResult.isGameWon()).isTrue();
        assertThat(gameResult.getCreditsRemained())
                .isEqualTo(CREDITS_TO_PLAY
                        .subtract(OneArmedBandit.REGULAR_GAME_PRICE)
                        .addition(Wheel.APPLE.getProfitAsCredit()));
    }

    /**
     * Check the game only started when enough credits exists. Expected a CreditException is thrown.
     */
    @Test
    void shouldNotStartAGameWhenCreditsNotEnough() {
        //given
        OneArmedBandit cut = new OneArmedBandit(NOT_ENOUGH_CREDITS_TO_PLAY);
        //when
        try {
            cut.pullingHandle();
            //then
        } catch (CreditException e) {
            assertThat(e).hasMessageContaining("Not enough credits to play. One game costs 3 credits.");
        }
    }

    /**
     * Test to increase the given credit count.
     */
    @Test
    void shouldIncreaseCredits() {
        //given
        OneArmedBandit cut = new OneArmedBandit(NOT_ENOUGH_CREDITS_TO_PLAY);
        //when
        Credit remainingCredits = cut.increaseCredits(CREDITS_TO_PLAY);
        //then
        assertThat(new Credit(12)).isEqualTo(remainingCredits);
        assertThat(cut.getCredits()).isEqualTo(remainingCredits);
    }

    /**
     * Test to play with abnormal Risk. The player has 10 Credits to play with and
     * set 6 Credits as risk input. Expected the player win the game with three
     * apples (10 Credits profit).
     * <p>
     * The Credit calculation in detail:
     * Step 1 # 10  - 3 =  7  | remaining Credits after pay game cost
     * Step 2 # 6 / 3   =  2  | rate Risk Factor
     * Step 3 # 10 * 2  = 20  | Apple profit * rate Risk profit
     * Step 4 # 7 + 20  = 27  | remaining credits
     */
    @Test
    void shouldWinAGameWithAbnormalRisk() {
        //given
        OneArmedBandit cut = new OneArmedBandit(CREDITS_TO_PLAY);
        cut.setBanditStrategy(WIN_STRATEGY_WITH_APPLE_STATE);
        //when
        GameResult gameResult = cut.pullingHandle(new AdditionalInput(6));
        //then
        assertThat(gameResult.isGameWon()).isTrue();
        assertThat(gameResult.isRiskGame()).isTrue();
        assertThat(gameResult.getCreditsRemained())
                .isEqualTo(new Credit(27));
    }

    /**
     * Test to play with abnormal Risk. The player has 10 Credits to play with and
     * set 6 Credits as risk input. Expected the player lose the game and has only 1 credit.
     * <p>
     * The Credit calculation in detail:
     * Step 1 # 10 - 3 = 7   | remaining Credits after pay game cost
     * Step 2 # 7 - 6 = 1    | remaining credits
     */
    @Test
    public void shouldLoseAGameWithAbnormalRisk() {
        //given
        OneArmedBandit cut = new OneArmedBandit(CREDITS_TO_PLAY);
        cut.setBanditStrategy(getLoseStrategy());
        //when
        GameResult gameResult = cut.pullingHandle(new AdditionalInput(6));
        //then
        assertThat(gameResult.isGameWon()).isFalse();
        assertThat(gameResult.isRiskGame()).isTrue();
        assertThat(gameResult.getCreditsRemained())
                .isEqualTo(new Credit(1));
    }

    /**
     * Try to play with 4 Credits a game with additional input of 6 credits.
     * Expected the game ended with a CreditException because minimal 10 Credits required.
     */
    @Test
    void shouldPullingHandleWithWithAbnormalRiskAndNotEnoughCredits() {
        //given
        OneArmedBandit cut = new OneArmedBandit(new Credit(4));
        //when
        try {
            cut.pullingHandle(new AdditionalInput(6));
            //then
        } catch (CreditException e) {
            assertThat(e).hasMessageContaining("costs 9 credits (with additional input)");
        }
    }

    /**
     * Injectable Bandit strategy to lose each game
     */
    private IntSupplier getLoseStrategy() {
        return new IntSupplier() {
            int state;

            @Override
            public int getAsInt() {
                return state++;
            }
        };
    }
}
