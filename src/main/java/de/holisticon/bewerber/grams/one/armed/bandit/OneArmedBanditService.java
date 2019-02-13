package de.holisticon.bewerber.grams.one.armed.bandit;

/**
 * Represents the http session service for a player.
 */
public interface OneArmedBanditService {

    /**
     * Starts a game checkin with a player name and credits to play with.
     *
     * @param credit credit to pay in
     */
    void checkin(Credit credit);

}
