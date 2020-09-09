package timeBot.Utils.exception;

public class BotAllException extends RuntimeException {

    public BotAllException(String message) {
        super(message);
    }

    public BotAllException(String message, Throwable cause) {
        super(message, cause);
    }

}
