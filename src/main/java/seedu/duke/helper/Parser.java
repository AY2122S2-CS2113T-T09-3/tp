package seedu.duke.helper;

import seedu.duke.exception.InputException;
import seedu.duke.helper.command.AddDoctorCommand;
import seedu.duke.helper.command.AddMedicineCommand;
import seedu.duke.helper.command.AddPatientCommand;
import seedu.duke.helper.command.Command;
import seedu.duke.helper.command.DeleteDoctorCommand;
import seedu.duke.helper.command.DeletePatientCommand;
import seedu.duke.helper.command.EditMedicineCommand;
import seedu.duke.helper.command.ViewDoctorCommand;
import seedu.duke.helper.command.ViewMedicineCommand;
import seedu.duke.helper.command.ViewPatientCommand;


public class Parser {
    public static String[] commandParser(String userInput) {
        return userInput.trim().split("/info");
    }

    private static String[] minParameterCheck(String parameters, int length) throws InputException {
        try {
            String[] parametersArray = parameters.split(",");
            if (parametersArray.length != length) {
                throw new InputException("There is one or more parameters missing");
            }
            for (int i = 0; i < parametersArray.length; i++) {
                parametersArray[i] = parametersArray[i].trim();
            }
            return parametersArray;
        } catch (Exception e) {
            throw new InputException("There is one or more parameters missing");
        }
    }

    private static boolean isNull(String string) {
        return string == null;
    }

    public static Command parseAddPatient(String parameters) throws InputException {
        String[] addPatientParameters = minParameterCheck(parameters, 7);
        Validator.validateAddPatient(addPatientParameters);
        return new AddPatientCommand(addPatientParameters);
    }

    public static Command parseDeletePatient(String parameters) throws InputException {
        String[] deletePatientParameters = minParameterCheck(parameters,1);
        Validator.validateNric(parameters);
        return new DeletePatientCommand(deletePatientParameters);
    }

    public static Command parseViewPatient(String parameters) throws InputException {
        if (isNull(parameters)) {
            return new ViewPatientCommand(null);
        }
        String[] viewPatientParameters = minParameterCheck(parameters,1);
        return new ViewPatientCommand(viewPatientParameters);
    }

    public static Command parseEditPatient(String parameters) throws InputException {
        return null;
    }

    public static Command parseAddDoctor(String parameters) throws InputException {
        String[] addDoctorParameters = minParameterCheck(parameters, 7);
        Validator.validateAddDoctor(addDoctorParameters);
        return  new AddDoctorCommand(addDoctorParameters);
    }

    public static Command parseDeleteDoctor(String parameters) throws InputException {
        String[] deleteDoctorParameters = minParameterCheck(parameters,1);
        Validator.validateNric(parameters);
        return new DeleteDoctorCommand(deleteDoctorParameters);
    }

    public static Command parseViewDoctor(String parameters) throws InputException {
        if (isNull(parameters)) {
            return new ViewDoctorCommand(null);
        }
        String[] viewDoctorParameters = minParameterCheck(parameters,1);
        return new ViewDoctorCommand(viewDoctorParameters);
    }

    public static Command parseEditDoctor(String parameters) throws InputException {
        return null;
    }


    public static Command parseAddMedicine(String parameters) throws InputException {
        String[] medicineParameters = minParameterCheck(parameters, 6);
        Validator.validateMedicine(medicineParameters);
        return new AddMedicineCommand(medicineParameters);
    }

    public static Command parseDeleteMedicine(String parameters) throws InputException {
        String[] deleteMedicineParameters = minParameterCheck(parameters,1);
        return new DeletePatientCommand(deleteMedicineParameters);
    }

    public static Command parseViewMedicine(String parameters) throws InputException {
        if (isNull(parameters)) {
            return new ViewMedicineCommand(null);
        }
        String[] viewMedicineParameters = minParameterCheck(parameters,1);
        return new ViewMedicineCommand(viewMedicineParameters);
    }

    public static Command parseEditMedicine(String parameters) throws InputException {
        String[] medicineParameters = minParameterCheck(parameters, 6);
        Validator.validateMedicine(medicineParameters);
        return new EditMedicineCommand(medicineParameters);
    }

    public static String[] parseAddAppointment(String parameters) throws InputException {
        String[] addAppointmentParameters = minParameterCheck(parameters, 6);
        Validator.validateAddAppointment(addAppointmentParameters);
        return addAppointmentParameters;
    }

    public static String[] parseFindAppointment(String parameters) throws InputException {
        String[] findAppointmentParameters = minParameterCheck(parameters, 2);
        return findAppointmentParameters;
    }
}
