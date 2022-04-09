package seedu.duke.helper.command;

import seedu.duke.assets.List;
import seedu.duke.assets.MedicineList;
import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.status.Status;

import java.util.Arrays;

public class CheckForMedicineStockCommand extends Command {

    public CheckForMedicineStockCommand(String[] parameterArray) {
        super(parameterArray);
    }

    @Override
    public Status execute(List medicineList) throws DuplicateEntryException, NotFoundException, UserInputErrorException {
        String[] medicineArray = Arrays.copyOfRange(parameterArray,1,parameterArray.length);
        if (medicineList instanceof MedicineList) {
            ((MedicineList) medicineList).checkStock(medicineArray);
            return Status.MEDICINE_STOCK_FOUND_SUCCESS;
        }
        assert false;
        throw new UserInputErrorException("Error with code! Approach Developer.");
    }
}
