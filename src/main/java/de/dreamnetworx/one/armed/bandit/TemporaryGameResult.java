package de.dreamnetworx.one.armed.bandit;

import java.util.Collections;
import java.util.List;

public class TemporaryGameResult {

    private List<Wheel> wheels;
    private boolean won;

    /**
     * Instantiate a temporary game result with wheels they selected and a indicator has the
     * player won the game.
     *
     * @param wheels
     * @param isGameWon
     */
    public TemporaryGameResult(List<Wheel> wheels, boolean isGameWon) {
        this.wheels = Collections.unmodifiableList(wheels);
        this.won = isGameWon;
    }

    /**
     * @return Returns the selected wheels.
     */
    public List<Wheel> getWheels() {
        return wheels;
    }

    /**
     * Indicator is game won or not.
     *
     * @return indicator is the game won
     */
    public boolean isGameWon() {
        return won;
    }

    /**
     * Is the game won, the profit from first wheel represents the profit of this round.
     *
     * @return define profit by wheel
     */
    public Credit getFirstWheelProfit() {
        return wheels.get(0).getProfitAsCredit();
    }
}
