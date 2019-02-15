package de.dreamnetworx.one.armed.bandit.endpoint;

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

    @Override
    public String toString() {
        return "Checkin as player='" + player + '\'' + " with credit=" + credit;
    }
}
