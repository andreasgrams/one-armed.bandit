package de.dreamnetworx.one.armed.bandit.model;

public class PositiveValueException extends OneArmedBanditException {

    /**
     * Constructs an PositiveValueException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param message the String that contains a detailed message
     */
    public PositiveValueException(final String message) {
        super(message);
    }
}
