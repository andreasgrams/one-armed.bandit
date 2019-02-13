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
     * Challenge your luck.
     *
     * Works like {@link OneArmedBandit#pullingHandel()}
     *
     * @throws OneArmedBanditException when player not checked in
     */
    GameResult pullingHandle();

    /**
     * Leave the game with the one armed bandit. The reaming credits returned as benefit.
     *
     * @return reaming credits
     */
    Credit checkout();

    /**
     * Return the reaming credits.
     *
     * @return reaming credits
     */
    Credit getCredits();
}
