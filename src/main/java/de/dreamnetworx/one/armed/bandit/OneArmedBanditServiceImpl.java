package de.dreamnetworx.one.armed.bandit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OneArmedBanditServiceImpl implements OneArmedBanditService {

    private Logger log = LoggerFactory.getLogger(OneArmedBanditServiceImpl.class);
    private OneArmedBandit oneArmedBandit;

    @Override
    public void checkin(final Credit credit) {
        if(oneArmedBandit == null) {
            log.info("new one armed bandit session started with initial credits {}.", credit.getValue());
            this.oneArmedBandit = new OneArmedBandit(credit);
        } else {
            log.info("existing one armed bandit session increased by {} credits.", credit.getValue());
            this.oneArmedBandit.increaseCredits(credit);
        }
    }

    @Override
    public GameResult pullingHandle() {
        if(this.oneArmedBandit == null) {
            throw new OneArmedBanditException("Do check in before pulling the handle");
        }
        log.info("try to pulling handling to challenge player luck.");
        return this.oneArmedBandit.pullingHandel();
    }

    @Override
    public Credit checkout() {
        final Credit reamingCredits = oneArmedBandit.getCredits();
        this.oneArmedBandit = null;
        return reamingCredits;
    }

    @Override
    public Credit getCredits() {
        if(this.oneArmedBandit == null) {
            return new Credit(0);
        }
        return this.oneArmedBandit.getCredits();
    }
}
