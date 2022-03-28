package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.HalpmiException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;
import seedu.duke.helper.command.CommandLineTable;
import seedu.duke.helper.finder.DoctorFinder;

import java.util.ArrayList;


public class DoctorList extends List {

    private ArrayList<Doctor> doctors = new ArrayList<>();
    private ArrayList<Doctor> returnedFinderArray = new ArrayList<>();

    public Doctor getDoctor(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }

    public Doctor search(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }

    public void find(String[] command) {
    }

    public void add(String[] addDoctorParameters) throws DuplicateEntryException {
        if (getDoctor(addDoctorParameters[0]) != null) {
            throw new DuplicateEntryException("Doctor with given NRIC already exists!");
        }
        Doctor newDoctor = new Doctor(addDoctorParameters[0], addDoctorParameters[1],
                Integer.parseInt(addDoctorParameters[2]),
                addDoctorParameters[3].charAt(0), addDoctorParameters[4], addDoctorParameters[5],
                addDoctorParameters[6]);
        doctors.add(newDoctor);
        UI.printParagraph("Doctor has been added");
    }

    //view particular doctor
    public void view(String nric) throws HalpmiException {
        Doctor doctor = getDoctor(nric);
        if (doctor == null) {
            throw new HalpmiException("Doctor doesn't exist please try again!");
        }
        CommandLineTable doctorTable = new CommandLineTable();
        doctorTable.setShowVerticalLines(true);
        doctorTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                "Specialization");
        doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                doctor.getSpecialization());
        doctorTable.print();
    }

    //view all doctor
    public void view() throws HalpmiException {
        CommandLineTable doctorTable = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        doctorTable.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        doctorTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                "Specialization");
        if (doctors.size() == 0) {
            throw new HalpmiException("Doctor list is empty, please add doctor");
        }
        for (Doctor doctor : doctors) {
            doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                  doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                  doctor.getSpecialization());
        }
        doctorTable.print();
    }

    public void edit(String[] parameterArray) throws NotFoundException {
        if (search(parameterArray[0]) != null) {
            Doctor doctor = search(parameterArray[0]);
            doctor.edit(parameterArray[1], Integer.parseInt(parameterArray[2]), parameterArray[3],
                    (parameterArray[4].charAt(0)), parameterArray[5], parameterArray[6]);
            return;
        }
        throw new NotFoundException("There are no patients with given NRIC!");
    }


    //get the number of doctors
    public int getSize() {
        return doctors.size();
    }

    //remove the specific doctor
    public void remove(String nric) throws NotFoundException {
        for (int i = 0; i < getSize(); i++) {
            if (doctors.get(i).getNric().equals(nric)) {
                doctors.remove(i);
                UI.printParagraph("Doctor has been removed");
                return;
            }
        }
        throw new NotFoundException("There are no doctors with given NRIC!");
    }

    @Override
    public String toString() {
        if (getSize() == 0) {
            return "There are no doctors.";
        }
        String doctorName = "";
        int index = 1;
        for (Doctor doctor : this.doctors) {
            doctorName += String.format("%d. %s", index, doctor.toString());
            if (index != this.getSize()) {
                doctorName += "\n";
            }
            index++;
        }
        return doctorName;
    }

    public ArrayList<Doctor> getList() {
        return doctors;
    }

    public void findByNric(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByNric(doctors, parameterArray[1]);
            createArrayOfFoundDoctors();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given NRIC doesn't exist. Please try again!");
        }
    }

    public void findByName(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByName(doctors, parameterArray[1]);
            createArrayOfFoundDoctors();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given name doesn't exist. Please try again!");
        }
    }

    public void findByAge(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByAge(doctors, Integer.parseInt(parameterArray[1]));
            createArrayOfFoundDoctors();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given age doesn't exist. Please try again!");
        }
    }

    public void findByGender(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByGender(doctors, parameterArray[1].charAt(0));
            createArrayOfFoundDoctors();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given gender doesn't exist. Please try again!");
        }
    }

    public void findByAddress(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByAddress(doctors, parameterArray[1]);
            createArrayOfFoundDoctors();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given address doesn't exist. Please try again!");
        }
    }

    public void findByDob(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorByDob(doctors, parameterArray[1]);
            createArrayOfFoundDoctors();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor doesn't exist please try again!");
        }
    }

    public void findBySpecialization(String[] parameterArray) {
        try {
            this.returnedFinderArray = DoctorFinder.findDoctorBySpecialization(doctors, parameterArray[1]);
            createArrayOfFoundDoctors();
        } catch (NullPointerException e) {
            UI.printParagraph("Doctor with given specialization doesn't exist. Please try again!");
        }
    }

    private void createArrayOfFoundDoctors() {
        if (returnedFinderArray.isEmpty()) {
            UI.printParagraph("Doctor doesn't exist please try again!");
        } else {
            CommandLineTable findPatientTable = new CommandLineTable();
            for (int i = 0; i < returnedFinderArray.size(); i++) {

                findPatientTable.setShowVerticalLines(true);
                findPatientTable.setHeaders("Nric", "FullName", "Age", "Address", "Gender", "Dob",
                        "Specilization");
                findPatientTable.addRow(returnedFinderArray.get(i).getNric(),
                        returnedFinderArray.get(i).getFullName(),
                        String.valueOf(returnedFinderArray.get(i).getAge()),
                        returnedFinderArray.get(i).getAddress(),
                        String.valueOf(returnedFinderArray.get(i).getGender()),
                        returnedFinderArray.get(i).getDob(),
                        returnedFinderArray.get(i).getSpecialization());
            }
            findPatientTable.print();
        }
    }

}

