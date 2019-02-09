package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.*;
import java.util.function.IntSupplier;

public class OneArmedBandit {

    public static Credit REGULAR_GAME_PRICE = new Credit(3);

    private Credit creditState;
    private IntSupplier banditStrategy;

    public OneArmedBandit(final Credit credit) {
        this.creditState = new Credit(credit);
        this.banditStrategy = () -> new Random().nextInt(Wheel.values().length);
    }

    /**
     * Start the game  by pulling the handel.
     *
     * @return
     */
    public GameResult pullingHandel() {
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
     * @return
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
     * Inject the banditStrategy
     *
     * @param banditStrategy
     */
    void setBanditStrategy(IntSupplier banditStrategy) {
        this.banditStrategy = banditStrategy;
    }
}
