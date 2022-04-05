package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.helper.finder.PatientFinder;
import seedu.duke.status.Status;

public class AddAppointmentCommand extends Command {
    public AddAppointmentCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List appointmentList) throws DuplicateEntryException {
        appointmentList.add(parameterArray);
        return Status.ADD_APPOINTMENT_SUCCESS;
    }

    private boolean checkIfPatientExists(String inputPatientName) {
        PatientFinder patientFinder = new PatientFinder();
        return true;
    }
}
