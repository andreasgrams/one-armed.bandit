package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.Collections;
import java.util.List;

public class GameResult {

    private Credit credit;
    private List<Wheel> wheels;
    private boolean won;

    GameResult(Credit remainCredits, List<Wheel> wheels, boolean isGameWon) {
        this.credit = new Credit(remainCredits);
        this.wheels = Collections.unmodifiableList(wheels);
        this.won = isGameWon;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public boolean isGameWon() {
        return won;
    }

    public Credit getCreditsRemained() {
        return credit;
    }
}
