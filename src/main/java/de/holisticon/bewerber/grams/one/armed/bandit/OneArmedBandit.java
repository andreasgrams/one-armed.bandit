package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.Random;
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
        for (int i = 0; i < Wheel.values().length; i++) {
            gameResult.addWheel(Wheel.valueByIndex(banditStrategy.getAsInt()));
        }
        return gameResult;
    }

    void setBanditStrategy(IntSupplier banditStrategy) {
        this.banditStrategy = banditStrategy;
    }
}
