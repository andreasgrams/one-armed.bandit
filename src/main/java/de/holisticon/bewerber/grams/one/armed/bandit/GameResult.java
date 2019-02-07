package de.holisticon.bewerber.grams.one.armed.bandit;

import java.util.ArrayList;
import java.util.List;

public class GameResult {

    private Credit credit;
    private List<Wheel> wheels;

    GameResult(Credit remainCredits) {
        this.credit = remainCredits;
        this.wheels = new ArrayList<>(Wheel.values().length);
    }

    public void addWheel(final Wheel wheel) {
        wheels.add(wheel);
    }

    public boolean isGameWon() {
        return wheels.get(0).equals(wheels.get(1)) && wheels.get(1).equals(wheels.get(2));
    }

    public Credit getCreditsRemained() {
        return credit;
    }
}
