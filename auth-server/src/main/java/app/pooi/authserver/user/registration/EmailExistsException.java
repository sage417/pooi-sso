package app.pooi.authserver.user.registration;

public class EmailExistsException extends Exception {

    public EmailExistsException(String message) {
        super(message);
    }
}
