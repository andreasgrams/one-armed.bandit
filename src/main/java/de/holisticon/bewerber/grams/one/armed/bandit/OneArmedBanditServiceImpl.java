package de.holisticon.bewerber.grams.one.armed.bandit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class OneArmedBanditServiceImpl implements OneArmedBanditService {

    private OneArmedBandit oneArmedBandit;

    Logger log = LoggerFactory.getLogger(OneArmedBanditServiceImpl.class);

    @Override
    public void checkin(final Credit credit) {
        this.oneArmedBandit = new OneArmedBandit(credit);
        log.info("checkin with credits: " + credit + " this " + this);
    }

    @Override
    public GameResult pullingHandle() {
        log.info("pullingHandle::  credits: " + oneArmedBandit.getCredits() + " this " + this);
        return this.oneArmedBandit.pullingHandel();
    }

    /**
     * Leave the game with the one armed bandit. The reaming credits return as benefit.
     *
     * @return reaming credits
     */
    @Override
    public Credit checkout() {
        return null;
    }
}
