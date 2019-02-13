package de.holisticon.bewerber.grams.one.armed.bandit;

/**
 * Represents the http session service for a player and save the one armed bandit state.
 */
public interface OneArmedBanditService {

    /**
     * Initialized a game checkin with a player name and credits to play with.
     *
     * @param credit credit to pay in
     */
    void checkin(Credit credit);

    /**
     * Challenge your luck
     */
    GameResult pullingHandle();


}
