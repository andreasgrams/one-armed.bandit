package de.holisticon.bewerber.grams.one.armed.bandit;

public class OneArmedBandit {

    public static Credit REGULAR_GAME_PRICE = new Credit(3);

    private final Player player;
    private Credit creditState;

    public OneArmedBandit(final Player player, final Credit credit) {
        this.creditState = credit;
        this.player = player;
    }

    public GameResult pullingHandel() {
        creditState = creditState.subtract(REGULAR_GAME_PRICE);
        return new GameResult(creditState);
    }
}
