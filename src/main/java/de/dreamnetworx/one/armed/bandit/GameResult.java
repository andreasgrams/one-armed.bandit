package de.dreamnetworx.one.armed.bandit;

/**
 * Definite the final game result. Game result returned after pulling the handle  and
 * has a indicator is the game won. Additionally the new calculated credit score returned.
 */
public class GameResult extends TemporaryGameResult {

    private Credit credit;

    /**
     * Instantiate a game result with calculated credits, wheels they selected and a indicator has the
     * player won the game.
     *
     * @param remainCredits
     * @param temporaryGameResult
     */
    GameResult(Credit remainCredits, TemporaryGameResult temporaryGameResult) {
        super(temporaryGameResult.getWheels(), temporaryGameResult.isGameWon());
        this.credit = new Credit(remainCredits);
    }

    /**
     * Returns the remaining credits after the game round.
     *
     * @return remaining credits
     */
    public Credit getCreditsRemained() {
        return credit;
    }
}
