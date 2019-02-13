package de.holisticon.bewerber.grams.one.armed.bandit.api;

public class CheckinPojo {

    private String player;
    private CreditPojo credit;

    public CheckinPojo() {
    }

    public CheckinPojo(final String player, final CreditPojo credit) {
        this.player = player;
        this.credit = credit;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(final String player) {
        this.player = player;
    }

    public CreditPojo getCredit() {
        return credit;
    }

    public void setCredit(final CreditPojo credit) {
        this.credit = credit;
    }

}
