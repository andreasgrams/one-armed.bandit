package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.*;
import java.util.function.IntSupplier;

public class OneArmedBandit {

    public static Credit REGULAR_GAME_PRICE = new Credit(3);

    private Credit creditState;
    private IntSupplier banditStrategy;

    public OneArmedBandit(final Player player, final Credit credit) {
        this.creditState = credit;
        this.banditStrategy = () -> new Random().nextInt(Wheel.values().length);
    }

    public GameResult pullingHandel() {
        this.creditState = creditState.subtract(REGULAR_GAME_PRICE);
        final GameResult gameResult = new GameResult(this.creditState);
        final Collection<Wheel> result = determineWheelStates(this.banditStrategy);
        return gameResult;
    }

    private Collection<Wheel> determineWheelStates(final IntSupplier banditStrategy) {
        List<Wheel> result = new ArrayList<>();
        for (int i = 0; i < Wheel.values().length; i++) {
            result.add(Wheel.valueByIndex(banditStrategy.getAsInt()));
        }
        return Collections.unmodifiableCollection(result);
    }

    void setBanditStrategy(IntSupplier banditStrategy) {
        this.banditStrategy = banditStrategy;
    }
}
