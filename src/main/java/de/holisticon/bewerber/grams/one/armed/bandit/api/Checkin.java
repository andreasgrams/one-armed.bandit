package de.holisticon.bewerber.grams.one.armed.bandit.api;

public class Checkin {

    private String player;
    private Credit credit;

    public String getPlayer() {
        return player;
    }

    public void setPlayer(final String player) {
        this.player = player;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(final Credit credit) {
        this.credit = credit;
    }
}
