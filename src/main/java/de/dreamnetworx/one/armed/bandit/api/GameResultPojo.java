package de.dreamnetworx.one.armed.bandit.api;

import java.util.List;

public class GameResultPojo {

    private CreditPojo credit;
    private List<String> wheels;
    private boolean won;

    public GameResultPojo() {
    }

    public GameResultPojo(final CreditPojo credit, final List<String> wheels, final boolean won) {
        this.credit = credit;
        this.wheels = wheels;
        this.won = won;
    }

    public CreditPojo getCredit() {
        return credit;
    }

    public void setCredit(final CreditPojo credit) {
        this.credit = credit;
    }

    public List<String> getWheels() {
        return wheels;
    }

    public void setWheels(final List<String> wheels) {
        this.wheels = wheels;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(final boolean won) {
        this.won = won;
    }
}
