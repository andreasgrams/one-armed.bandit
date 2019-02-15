package de.dreamnetworx.one.armed.bandit;

/**
 * Represents the game session for a player. The service save the OneArmedBandit state for http clients.
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
     * Challenge your luck with abnormal risk.
     *
     * @param additionalInput define the additional input for one game.
     * @return
     */
    GameResult pullingHandle(int additionalInput);

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
