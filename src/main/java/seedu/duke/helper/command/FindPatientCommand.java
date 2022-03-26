package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

public class FindPatientCommand extends Command {
    public FindPatientCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List patientList) throws NotFoundException {
        patientList.find(parameterArray);
        return Status.FIND_PATIENT_SUCCESS;
    }
}