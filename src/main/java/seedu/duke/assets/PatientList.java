package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;

import java.util.ArrayList;

public class PatientList extends List {

    private ArrayList<Patient> patients = new ArrayList<>();

    public Patient getPatient(String nric) {
        for (Patient patient : patients) {
            if (patient.getNric().equals(nric)) {
                return patient;
            }
        }
        return null;
    }

    public void view(String nric) {
        Patient patient = getPatient(nric);
        if (patient == null) {
            UI.printParagraph("There is no such patient");
            return;
        }
        UI.printParagraph(getPatient(nric).toString());
    }

    public void view() {
        UI.printParagraph(toString());
    }

    @Override
    public void edit(String[] parameters) throws NotFoundException {

    }

    public void add(String[] addPatientParameters) throws DuplicateEntryException {
        int numberOfPatientsBefore = patients.size();
        if (getPatient(addPatientParameters[0]) != null) {
            throw new DuplicateEntryException("Patient with given NRIC already exists!");
        }
        Patient newPatient = new Patient(addPatientParameters[0],addPatientParameters[1],
                Integer.parseInt(addPatientParameters[2]), addPatientParameters[3].charAt(0),
                addPatientParameters[4],addPatientParameters[5], addPatientParameters[6]);
        patients.add(newPatient);
        assert patients.size() == numberOfPatientsBefore + 1;
    }

    public int getSize() {
        return patients.size();
    }

    public void remove(String nric) throws NotFoundException {
        int numberOfPatientsBefore = patients.size();
        for (int i = 0; i < getSize(); i++) {
            if (patients.get(i).getNric().equals(nric)) {
                patients.remove(i);
                assert patients.size() == numberOfPatientsBefore - 1;
                return;
            }
        }
        throw new NotFoundException("There are no patients with given NRIC!");
    }

    public ArrayList<Patient> getList() {
        return patients;
    }

    @Override
    public String toString() {
        if (getSize() == 0) {
            return "There are no patients currently.";
        }
        String output = "";
        int number = 1;
        for (Patient patient : this.patients) {
            output += String.format("%d. %s", number, patient.toString());
            if (number != this.getSize()) {
                output += "\n";
            }
            number++;
        }
        assert !output.isEmpty();
        return output;
    }
}
