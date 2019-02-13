package de.dreamnetworx.one.armed.bandit;

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
     * Leave the game. The remaining credits returned as benefit.
     *
     * @return remaining credits
     */
    Credit checkout();

    /**
     * Return the remaining credits.
     *
     * @return remaining credits
     */
    Credit getCredits();
}
