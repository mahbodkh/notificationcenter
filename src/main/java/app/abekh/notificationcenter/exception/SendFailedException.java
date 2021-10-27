package app.abekh.notificationcenter.exception;

/**
 * Created by Ebrahim Kh.
 */


public class SendFailedException extends Exception {
    public SendFailedException(String message) {
        super(message);
    }

    public SendFailedException(String message, Exception e) {
        super(message, e);
    }

    @Override
    public String getMessage() {
        var message = super.getMessage();
        if (getCause() != null && getCause() != this) {
            message += ": " + getCause().getMessage();
        }
        return message;
    }
}
