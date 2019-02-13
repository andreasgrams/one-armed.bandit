package de.dreamnetworx.one.armed.bandit;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OneArmedBanditException extends IllegalStateException {

    /**
     * Constructs an OneArmedBanditException with the specified detail
     * message.  A detail message is a String that describes this particular
     * exception.
     *
     * @param s the String that contains a detailed message
     */
    OneArmedBanditException(final String s) {
        super(s);
    }
}
