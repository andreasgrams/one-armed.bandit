package de.dreamnetworx.one.armed.bandit;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Stream;

public class OneArmedBandit {

    public static Credit REGULAR_GAME_PRICE = new Credit(3);

    private Credit creditState;
    private IntSupplier banditStrategy;

    /**
     * Initialized the one armed bandit with the given credits.
     *
     * @param credit credits to play with
     */
    public OneArmedBandit(final Credit credit) {
        this.creditState = new Credit(credit);
        this.banditStrategy = () -> new Random().nextInt(Wheel.values().length);
    }

    /**
     * Start the game by pulling the handel.
     *
     * @return Returns the game result after pulling the handle.
     * @throws throws a CreditException when not enough credits available for this game.
     */
    public GameResult pullingHandel() throws CreditException {
        return pullingHandel(new Credit(0));
    }

    /**
     * Start the game by pulling the handel.
     *
     * @param additionalInput risk input for one game.
     * @return Returns the game result after pulling the handle.
     * @throws throws a CreditException when not enough credits available for this game.
     */
    public GameResult pullingHandel(Credit additionalInput) throws CreditException {
        final TemporaryGameResult temporaryGameResult = buildTempGameResult(this.banditStrategy);

        if(temporaryGameResult.isGameWon()) {
            final Credit profitByWheel = temporaryGameResult.getFirstWheelProfit();
            this.creditState = creditState.subtract(REGULAR_GAME_PRICE);

            if(additionalInput.hasPositiveValue()) {
                int rateRisk = getProfitMultiplicator(additionalInput);
                final Credit ratedProfitByWheel = profitByWheel.multiplied(rateRisk);
                this.creditState = creditState.addition(ratedProfitByWheel);
            } else {
                this.creditState = creditState.addition(profitByWheel);
            }
        } else {
            this.creditState = creditState.subtract(REGULAR_GAME_PRICE);
            this.creditState = creditState.subtract(additionalInput);

        }
        return new GameResult(this.creditState, temporaryGameResult);
    }

    private TemporaryGameResult buildTempGameResult(final IntSupplier banditStrategy) {
        final List<Wheel> result = getRandomWheelStates(banditStrategy);
        final boolean isGameWon = isGameWon(result);
        return new TemporaryGameResult(result, isGameWon);
    }

    private int getProfitMultiplicator(final Credit additionalInput) {
        return additionalInput.getValue() / REGULAR_GAME_PRICE.getValue();
    }

    /**
     * Increase the given credits
     *
     * @param credit increased by value
     * @return the given creditState
     */
    public Credit increaseCredits(final Credit credit) {
        this.creditState = this.creditState.addition(credit);
        return this.creditState;
    }

    /**
     * Determine the game is won by comparing the wheels.
     *
     * @param wheelsToCompare
     * @return is game won
     */
    private boolean isGameWon(final Collection<Wheel> wheelsToCompare) {
        return new HashSet<>(wheelsToCompare).size() == 1;
    }

    /**
     * @param banditStrategy
     * @return Returns a list of randomized wheels.
     */
    private List<Wheel> getRandomWheelStates(final IntSupplier banditStrategy) {
        List<Wheel> result = new ArrayList<>();
        Stream.of(Wheel.values())
                .forEach(c -> result.add(getWheel(banditStrategy)));
        return result;
    }

    /**
     * Select Wheel randomized by invoking the banditStrategy.
     *
     * @param banditStrategy
     * @return
     */
    private Wheel getWheel(final IntSupplier banditStrategy) {
        return Wheel.valueByIndex(banditStrategy.getAsInt());
    }

    /**
     * @return the given creditState
     */
    public Credit getCredits() {
        return new Credit(this.creditState);
    }

    /**
     * The bandit strategy is used for selecting the wheel state. The given index must between 0 - {@link Wheel}.length.
     *
     * @param banditStrategy
     */
    void setBanditStrategy(IntSupplier banditStrategy) {
        this.banditStrategy = banditStrategy;
    }
}
