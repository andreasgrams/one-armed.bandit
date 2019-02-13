package de.holisticon.bewerber.grams.one.armed.bandit;

public class OneArmedBanditServiceImpl implements OneArmedBanditService {

    private OneArmedBandit oneArmedBandit;

    @Override
    public void checkin(final Credit credit) {
        this.oneArmedBandit = new OneArmedBandit(credit);
    }
}
