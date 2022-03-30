package seedu.duke.exception;

public class UserInputErrorException extends Exception {
    public UserInputErrorException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
