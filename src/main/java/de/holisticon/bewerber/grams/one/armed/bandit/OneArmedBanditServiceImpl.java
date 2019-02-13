package de.holisticon.bewerber.grams.one.armed.bandit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;


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

    @PostConstruct
    public void lifecycle() {
        log.info("PostConstruct OneArmedBanditServiceImpl " + this);
    }
}
