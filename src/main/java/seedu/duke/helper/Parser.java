package seedu.duke.helper;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }

    public static String[] parseAddPatient(String parameters) {
        String[] addPatientParameters = parameters.split(",");
        for (int i = 0; i < addPatientParameters.length; i++) {
            addPatientParameters[i] = addPatientParameters[i].trim();
        }
        if (addPatientParameters.length != 7) {
            UI.printParagraph("There is one or more parameters missing.");
            return null;
        }
        if (validateAddPatient(addPatientParameters)) {
            return addPatientParameters;
        } else {
            return null;
        }
    }

    public static String[] parseAddDoctor(String parameters) {
        String[] addDoctorParameters = parameters.split(",");
        for (int i = 0; i < addDoctorParameters.length; i++) {
            addDoctorParameters[i] = addDoctorParameters[i].trim();
        }
        if (addDoctorParameters.length != 7) {
            UI.printParagraph("There is one or more parameters missing.");
            return null;
        }
        if (validateAddDoctor(addDoctorParameters)) {
            return addDoctorParameters;
        } else {
            return null;
        }
    }

    public static String[] parseAddMedicine(String parameters) {
        String[] medicineParameters = parameters.trim().split(",");
        for (int i = 0; i < medicineParameters.length; i++) {
            medicineParameters[i] = medicineParameters[i].trim();
        }
        if (medicineParameters.length == 5 && validateMedicine(medicineParameters)) {
            return medicineParameters;
        } else {
            return null;
        }
    }

    public static String[] parseAddAppointment(String parameters) {
        String[] addAppointmentParameters = parameters.trim().split(",");
        for (int i = 0; i < addAppointmentParameters.length; i++) {
            addAppointmentParameters[i] = addAppointmentParameters[i].trim();
        }
        if (addAppointmentParameters.length != 6) {
            UI.printParagraph("There is one or more parameters missing.");
            return null;
        }
        if (validateAddAppointment(addAppointmentParameters)) {
            return addAppointmentParameters;
        } else {
            return null;
        }
    }

    private static boolean validateAddDoctor(String[] parameters) {
        boolean isValid = validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        //validate full name cause specialization is also j a name
        if (!validateFullName(parameters[6])) {
            UI.printParagraph("Specialization must be a name");
            isValid = false;
        }
        return isValid;
    }

    private static boolean validateAddPerson(String[] parameters) {
        boolean isValid = true;
        if (!validateNric(parameters[0])) {
            UI.printParagraph("NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
            isValid = false;
        }
        if (!validateFullName(parameters[1])) {
            UI.printParagraph("Full name must contain only alphabets and no special characters.");
            isValid = false;
        }
        if (!validateAge(parameters[2])) {
            UI.printParagraph("Age must be between 1 and 120 inclusive.");
            isValid = false;
        }
        if (!validateGender(parameters[3])) {
            UI.printParagraph("Gender must be a single character: M or F.");
            isValid = false;
        }
        if (!validateAddress(parameters[4])) {
            UI.printParagraph("Address must be alphanumeric. "
                    + "Only these specific special characters are allowed: ' ( ) #");
            isValid = false;
        }
        if (!validateDob(parameters[5])) {
            UI.printParagraph("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1900-01-01 or be today and after.");
            isValid = false;
        }
        return isValid;
    }

    private static boolean validateAddPatient(String[] parameters) {
        boolean isValid = validateAddPerson(Arrays.copyOfRange(parameters, 0, 6));
        if (!validateDate(parameters[6], "patient")) {
            UI.printParagraph("Date of birth must be in YYYY-MM-DD format. "
                    + "It cannot be before 1980-01-01 or be today and after.");
            isValid = false;
        }
        return isValid;
    }

    private static boolean validateAddAppointment(String[] parameters) {
        boolean isValid = true;
        if (!validateNric(parameters[0])) {
            UI.printParagraph("Patient NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
            isValid = false;
        }
        if (!validateFullName(parameters[1])) {
            UI.printParagraph("Patient name must contain only alphabets and no special characters.");
            isValid = false;
        }
        if (!validateNric(parameters[2])) {
            UI.printParagraph("Doctor NRIC must start with a capital letter, "
                    + "followed by 7 digits and end with a capital letter.");
            isValid = false;
        }
        if (!validateFullName(parameters[3])) {
            UI.printParagraph("Doctor name must contain only alphabets and no special characters.");
            isValid = false;
        }
        if (!validateDate(parameters[4], "appointment")) {
            UI.printParagraph("Date of birth must be in YYYY-MM-DD format."
                    + "It cannot be today or before.");
            isValid = false;
        }
        if (!validateAppointmentDetails(parameters[5])) {
            UI.printParagraph("Appointment details cannot be empty. Please indicate some details.");
            isValid = false;
        }
        return isValid;
    }

    public static boolean validateMedicine(String[] parameters) {
        boolean check = true;
        for (int i = 0; i < 5; i++) {
            switch (i) {
            case 0:
                check = validateMedicineName(parameters[i]);
                break;
            case 1:
                check = check && validateDosage(parameters[i]);
                break;
            case 2:
                check = check && validateExpiry(parameters[i]);
                break;
            case 4:
                check = check && validateQuantity(parameters[i]);
                break;
            default:
                break;
            }
        }
        return check;
    }

    private static boolean validateNric(String nric) {
        Pattern nricPattern = Pattern.compile("[A-Z][0-9]{7}[A-Z]");
        Matcher nricMatcher = nricPattern.matcher(nric);
        return nricMatcher.matches();
    }

    private static boolean validateFullName(String fullName) {
        Pattern fullNamePattern = Pattern.compile("[a-zA-Z ]*");
        Matcher fullNameMatcher = fullNamePattern.matcher(fullName);
        return fullNameMatcher.matches();
    }

    private static boolean validateAge(String ageString) {
        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
        if (1 <= age && age <= 120) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateGender(String gender) {
        Pattern genderPattern = Pattern.compile("M|F");
        Matcher genderMatcher = genderPattern.matcher(gender);
        return genderMatcher.matches();
    }

    private static boolean validateAddress(String address) {
        Pattern addressPattern = Pattern.compile("[\\w\\-\\s'()#]*");
        Matcher addressMatcher = addressPattern.matcher(address);
        return addressMatcher.matches();
    }

    private static boolean validateDob(String dobString) {
        LocalDate dob;
        try {
            dob = LocalDate.parse(dobString);
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate dobLimit = LocalDate.parse("1900-01-01");
        if (dob.isAfter(dobLimit) && dob.isBefore(today)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateDate(String inputDate, String type) {
        LocalDate newDate;
        try {
            newDate = LocalDate.parse(inputDate);
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
        LocalDate today = LocalDate.now();
        LocalDate admissionDateLimit = LocalDate.parse("1980-01-01");
        if (type.equals("appointment") && newDate.isAfter(today)) {
            return true;
        } else if (type.equals("patient") && newDate.isAfter(admissionDateLimit) && newDate.isBefore(today)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean validateMedicineName(String medicineName) {
        return medicineName.matches("[a-zA-z]+");
    }

    private static boolean validateDosage(String dosage) {
        try {
            int dosageInt = Integer.parseInt(dosage);
            return dosageInt > 0;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private static boolean validateExpiry(String expiry) {
        try {
            LocalDate expiryDate = LocalDate.parse(expiry);
            LocalDate minimumDate = LocalDate.now().plusMonths(6);
            if (expiryDate.isBefore(minimumDate)) {
                return false;
            }
            return true;
        } catch (DateTimeParseException dateTimeParseException) {
            return false;
        }
    }

    private static boolean validateQuantity(String quantity) {
        try {
            int quantityInt = Integer.parseInt(quantity);
            return quantityInt > 0;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private static boolean validateAppointmentDetails(String appointmentDetails) {
        if (appointmentDetails.isBlank() || appointmentDetails.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
