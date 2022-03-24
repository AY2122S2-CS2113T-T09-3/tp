package seedu.duke.status;

public enum Status {
    ADD_PATIENT_SUCCESS ("The specified patient has been added successfully."),
    ADD_DOCTOR_SUCCESS ("The specified doctor has been added successfully."),
    ADD_MEDICINE_SUCCESS ("The specified medicine has been added successfully."),
    DELETE_PATIENT_SUCCESS ("The specified patient has been deleted successfully."),
    DELETE_DOCTOR_SUCCESS ("The specified doctor has been deleted successfully."),
    DELETE_MEDICINE_SUCCESS ("The specified medicine has been deleted successfully."),
    EDIT_PATIENT_SUCCESS ("The specified patient has been edited successfully."),
    EDIT_DOCTOR_SUCCESS ("The specified doctor has been edited successfully."),
    EDIT_MEDICINE_SUCCESS ("The specified medicine has been edited successfully."),
    VIEW_SUCCESS ("Here you go!"),
    PRINT_HELP ("Here are the commands and examples:\n"
            + "1. add patient\n"
            + "Example: add patient /info S1234567A, John Doe, 23, M, 10 Baker Street, 1999-12-31, 2021-02-15\n"
            + "2. delete patient\n"
            + "Example: delete patient /info S1234567J\n"
            + "3. view patient\nExample: view patient [/info NRIC]\n"
            + "4. add doctor\n"
            + "Example: add doctor /info S1234567A, Shirley Tan, 40, F, 1 Baker Road, 1980-12-31, Dermatology\n"
            + "5. delete doctor\nExample: delete doctor /info S1234567J\n"
            + "6. view doctor\nExample: view doctor [/info NRIC]\n"
            + "7. add medicine\nExample: add medicine /info Paracetamol, 500, 2023-06-11, Slight headache, 10\n"
            + "8. delete medicine\nExample: delete medicine /info 1\n"
            + "9. view medicine\nExample: view medicine [/info id]\n"
            + "10. bye\nExample: bye"),
    END_APP ("Goodbye!"),
    NOT_RECOGNISED ("Sorry, HALPMI please I don't understand your command! " +
            "Please type help for a full list of commands.\"");

    private final String successMessage;

    Status(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }
}
