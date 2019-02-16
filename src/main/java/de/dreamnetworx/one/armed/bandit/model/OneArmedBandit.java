package de.dreamnetworx.one.armed.bandit.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.IntSupplier;
import java.util.stream.Stream;

public class OneArmedBandit {

    public static Credit REGULAR_GAME_PRICE = new Credit(3);

    private Credit creditState;
    private IntSupplier banditStrategy;

    private Logger log = LoggerFactory.getLogger(OneArmedBandit.class);

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
    public GameResult pullingHandle() throws CreditException {
        return pullingHandle(Optional.empty());
    }

    GameResult pullingHandle(AdditionalInput additionalInput) throws CreditException {
        return pullingHandle(Optional.ofNullable(additionalInput));
    }

    /**
     * Start the game by pulling the handel.
     *
     * @param additionalInput optional risk input for one game.
     * @return Returns the game result after pulling the handle.
     * @throws throws a CreditException when not enough credits available for this game.
     */
    public GameResult pullingHandle(Optional<AdditionalInput> additionalInput) throws CreditException {
        final TemporaryGameResult temporaryGameResult = buildTempGameResult(this.banditStrategy);
        this.creditState = calculateNewCreditState(this.creditState, temporaryGameResult, additionalInput);
        final GameResult gameResult = new GameResult(this.creditState, temporaryGameResult, additionalInput.isPresent());
        log.info("game result {}", gameResult);
        return gameResult;
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
     * Calculates the new Credits and use the game won indicator and players additional input
     *
     * @param currentState        current credits
     * @param temporaryGameResult indicates game is won and selected wheels
     * @param additionalInput     risk input
     * @return
     */
    private Credit calculateNewCreditState(final Credit currentState, final TemporaryGameResult temporaryGameResult, final Optional<AdditionalInput> additionalInput) {
        Credit newState = currentState.subtract(REGULAR_GAME_PRICE);
        if(temporaryGameResult.isGameWon()) {
            final Credit profitByWheel = temporaryGameResult.getFirstWheelProfit();
            if(additionalInput.isPresent()) {
                Credit rateRisk = getProfitMultiplicator(additionalInput.get());
                final Credit ratedProfitByWheel = profitByWheel.multiplied(rateRisk);
                newState = newState.addition(ratedProfitByWheel);
            } else {
                newState = newState.addition(profitByWheel);
            }
        } else {
            if(additionalInput.isPresent()) {
                newState = newState.subtract(additionalInput.get());
            }
        }
        return newState;
    }

    private TemporaryGameResult buildTempGameResult(final IntSupplier banditStrategy) {
        final List<Wheel> result = getRandomWheelStates(banditStrategy);
        final boolean isGameWon = isGameWon(result);
        return new TemporaryGameResult(result, isGameWon);
    }

    private Credit getProfitMultiplicator(final AdditionalInput additionalInput) {
        return new Credit((REGULAR_GAME_PRICE.getValue() + additionalInput.getValue()) / REGULAR_GAME_PRICE.getValue());
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
