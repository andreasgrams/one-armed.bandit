package de.holisticon.bewerber.grams.one.armed.bandit;

public class GameResult {

    private Credit credit;

    GameResult(Credit remainCredits) {
        this.credit = remainCredits;
    }

    public Credit getCreditsRemained() {
        return credit;
    }
}
