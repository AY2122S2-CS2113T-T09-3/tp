package seedu.duke.exception;

public class InputException extends HalpmiException {
    public InputException(String message) {
        super(message);
    }

    public InputException() {
        super("There is one or more parameters missing! Please type help for a full list of commands.");
    }

}
