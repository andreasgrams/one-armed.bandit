package de.dreamnetworx.one.armed.bandit;

import java.util.*;
import java.util.function.IntSupplier;

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
        this.creditState = creditState.subtract(REGULAR_GAME_PRICE);
        final List<Wheel> result = getRandomWheelStates(this.banditStrategy);
        final boolean isGameWon = isGameWon(result);
        if(isGameWon) {
            this.creditState = creditState.addition(result.get(0).getBenefitAsCredit());
        }
        return new GameResult(this.creditState, result, isGameWon);
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
     * @return the given creditState
     */
    public Credit getCredits() {
        return new Credit(this.creditState);
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
     * @return Returns a list of wheels. They selected randomized by invoking the banditStrategy.
     */
    private List<Wheel> getRandomWheelStates(final IntSupplier banditStrategy) {
        List<Wheel> result = new ArrayList<>();
        for (int i = 0; i < Wheel.values().length; i++) {
            result.add(Wheel.valueByIndex(banditStrategy.getAsInt()));
        }
        return result;
    }

    /**
     * The bandit strategy is used for selecting the wheel state. The given index must between 0 - {@link Wheel}.length.
     * @param banditStrategy
     */
    void setBanditStrategy(IntSupplier banditStrategy) {
        this.banditStrategy = banditStrategy;
    }
}
