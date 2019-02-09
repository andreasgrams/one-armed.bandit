package de.holisticon.bewerber.grams.one.armed.bandit;

public class CreditException extends IllegalStateException {

    /**
     * Constructs an IllegalStateException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param message the String that contains a detailed message
     */
    public CreditException(final String message) {
        super(message);
    }
}
