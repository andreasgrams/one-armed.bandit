package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.Collections;
import java.util.List;

/**
 * Definite the game result. Game result returned after pulling the handle  and
 * has a indicator is the game won. Additionally the new calculated credit score returned.
 */
public class GameResult {

    private Credit credit;
    private List<Wheel> wheels;
    private boolean won;

    /**
     * Instantiate a game result with calculated credits, wheels they selected and a indicator has the
     * player won the game.
     *
     * @param remainCredits
     * @param wheels
     * @param isGameWon
     */
    GameResult(Credit remainCredits, List<Wheel> wheels, boolean isGameWon) {
        this.credit = new Credit(remainCredits);
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
     * Returns the reaming credits after the game round.
     *
     * @return remaining credits
     */
    public Credit getCreditsRemained() {
        return credit;
    }
}
