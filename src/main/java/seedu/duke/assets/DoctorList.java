package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.UI;
import seedu.duke.helper.command.CommandLineTable;

import java.util.ArrayList;


public class DoctorList extends List {

    private ArrayList<Doctor> doctors = new ArrayList<>();

    public Doctor getDoctor(String nric) {
        for (Doctor doctor : doctors) {
            if (doctor.getNric().equals(nric)) {
                return doctor;
            }
        }
        return null;
    }

    public void find(String[] command){
    }

    public void add(String[] addDoctorParameters) throws DuplicateEntryException {
        if (getDoctor(addDoctorParameters[0]) != null) {
            throw new DuplicateEntryException("Doctor with given NRIC already exists!");
        }
        Doctor newDoctor = new Doctor(addDoctorParameters[0],addDoctorParameters[1],
                Integer.parseInt(addDoctorParameters[2]),
                addDoctorParameters[3], addDoctorParameters[4].charAt(0),addDoctorParameters[5],
                addDoctorParameters[6]);
        doctors.add(newDoctor);
    }

    //view particular doctor
    public void view(String nric) {
        Doctor doctor = getDoctor(nric);
        if (doctor == null) {
            UI.printParagraph("Doctor doesn't exist please try again!");
            return;
        }
        CommandLineTable doctorTable = new CommandLineTable();
        doctorTable.setShowVerticalLines(true);
        doctorTable.setHeaders("Nric", "FullName","Age", "Address", "Gender", "Dob",
                "Specialization");
        doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                doctor.getSpecialization());
        doctorTable.print();
    }

    //view all doctor
    public void view() {
        CommandLineTable doctorTable = new CommandLineTable();
        //st.setRightAlign(true);//if true then cell text is right aligned
        doctorTable.setShowVerticalLines(true);//if false (default) then no vertical lines are shown
        doctorTable.setHeaders("Nric", "FullName","Age", "Address", "Gender", "Dob",
                "Specialization");
        for (Doctor doctor: doctors) {
            doctorTable.addRow(doctor.getNric(), doctor.getFullName(), String.valueOf(doctor.getAge()),
                    doctor.getAddress(), String.valueOf(doctor.getGender()), doctor.getDob(),
                    doctor.getSpecialization());
        }
        doctorTable.print();
    }


    @Override
    public void edit(String[] parameters) throws NotFoundException {

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
}
