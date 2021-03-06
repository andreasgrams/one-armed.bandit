package de.dreamnetworx.one.armed.bandit.endpoint;

import java.util.List;

public class GameResultPojo {

    private CreditPojo credit;
    private List<String> wheels;
    private boolean won;
    private boolean riskGame;

    public GameResultPojo() {
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

    public boolean isRiskGame() {
        return riskGame;
    }

    public void setRiskGame(final boolean riskGame) {
        this.riskGame = riskGame;
    }
}
