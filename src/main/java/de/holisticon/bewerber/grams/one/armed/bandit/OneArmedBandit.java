package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.Random;
import java.util.function.IntSupplier;

public class OneArmedBandit {

    public static Credit REGULAR_GAME_PRICE = new Credit(3);

    private final Player player;
    private Credit creditState;
    private IntSupplier luckStrategy;

    public OneArmedBandit(final Player player, final Credit credit) {
        this.creditState = credit;
        this.player = player;
        this.luckStrategy = () -> new Random().nextInt(Wheel.values().length);
    }

    public GameResult pullingHandel() {
        this.creditState = creditState.subtract(REGULAR_GAME_PRICE);
        final GameResult gameResult = new GameResult(this.creditState);
        for (int i = 0; i < Wheel.values().length; i++) {
            gameResult.addWheel(Wheel.valueByIndex(luckStrategy.getAsInt()));
        }
        return gameResult;
    }

    public void setLuckStrategy(IntSupplier luckStrategy) {
        this.luckStrategy = luckStrategy;
    }
}
