package seedu.duke.assets;

import seedu.duke.exception.DuplicateEntryException;
import seedu.duke.exception.UserInputErrorException;
import seedu.duke.exception.NotFoundException;
import seedu.duke.helper.CommandLineTable;
import seedu.duke.helper.UI;
import seedu.duke.helper.finder.AppointmentFinder;

import java.util.ArrayList;

public class AppointmentList extends List {
    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Appointment> returnedFinderArray = new ArrayList<>();

    public Appointment getAppointment(String appointmentId) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(appointmentId)) {
                return appointment;
            }
        }
        return null;
    }
    
    public ArrayList<Appointment> getList() {
        return appointments;
    }

    @Override
    public void add(String[] addAppointmentParameters) throws DuplicateEntryException {
        final int numberOfAppointmentsBefore = appointments.size();
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentId().equals(addAppointmentParameters[0])) {
                throw new DuplicateEntryException("Appointment with given appointment ID already exist!");
            }
        }
        Appointment newAppointment = new Appointment(addAppointmentParameters[0], addAppointmentParameters[1],
                addAppointmentParameters[2], addAppointmentParameters[3], addAppointmentParameters[4],
                addAppointmentParameters[5], addAppointmentParameters[6]);
        appointments.add(newAppointment);
        assert appointments.size() == numberOfAppointmentsBefore + 1;
    }

    @Override
    public void remove(String appointmentId) throws NotFoundException {
        int numberOfAppointmentsBefore = appointments.size();
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(appointmentId)) {
                appointments.remove(i);
                assert appointments.size() == numberOfAppointmentsBefore - 1;
                return;
            }
        }
        throw new NotFoundException("There is no appointment with the given appointment id.\n"
                + "Please search by patient's nric or doctor's nric to find out the correct id if needed.");
    }

    @Override
    public void edit(String[] parameters) throws NotFoundException {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(parameters[0])) {
                appointments.remove(i);
                Appointment editAppointment = new Appointment(parameters[0], parameters[1],
                        parameters[2], parameters[3], parameters[4],
                        parameters[5], parameters[6]);
                appointments.add(editAppointment);
                return;
            }
        }
        throw new NotFoundException("There is no appointment with the given appointment id.\n"
                + "Please search by patient's nric or doctor's nric to find out the correct id if needed.");
    }

    @Override
    public void view() throws UserInputErrorException {
        CommandLineTable appointmentTable = new CommandLineTable();
        appointmentTable.setShowVerticalLines(true);
        appointmentTable.setHeaders("Appointment Id", "Patient Name", "Patient NRIC", "Doctor Name", "Doctor NRIC",
                "Appointment Date", "Appointment Details");
        if (appointments.size() == 0) {
            throw new UserInputErrorException("Appointment list is empty, please add appointment");
        }
        for (Appointment appointment : appointments) {
            appointmentTable.addRow(appointment.getAppointmentId(), appointment.getPatientName(),
                        appointment.getPatientNric(), appointment.getDoctorName(), appointment.getDoctorNric(),
                        appointment.getAppointmentDate(), appointment.getAppointmentDetails());
        }
        appointmentTable.print();
    }

    @Override
    public void view(String appointmentId) throws UserInputErrorException {
        Appointment foundAppointment = getAppointment(appointmentId);
        if (foundAppointment == null) {
            throw new UserInputErrorException("Appointment doesn't exist please try again!");
        }
        CommandLineTable appointmentTable = new CommandLineTable();
        appointmentTable.setShowVerticalLines(true);
        appointmentTable.setHeaders("Appointment Id", "Patient Name", "Patient NRIC", "Doctor Name", "Doctor NRIC",
                "Appointment Date", "Appointment Details");
        appointmentTable.addRow(foundAppointment.getAppointmentId(), foundAppointment.getPatientName(),
                foundAppointment.getPatientNric(), foundAppointment.getDoctorName(), foundAppointment.getDoctorNric(),
                foundAppointment.getAppointmentDate(), foundAppointment.getAppointmentDetails());
        appointmentTable.print();
    }

    public void findById(String[] parameters) {
        try {
            this.returnedFinderArray = AppointmentFinder.findAppointmentById(appointments, parameters[1]);
            createArrayOfFoundAppointments();
        } catch (NullPointerException e) {
            UI.printParagraph("Appointment with given ID doesn't exist. Please try again!");
        }
    }

    public void findByPatientName(String[] parameters) {
        try {
            this.returnedFinderArray = AppointmentFinder.findAppointmentByPatientName(appointments, parameters[1]);
            createArrayOfFoundAppointments();
        } catch (NullPointerException e) {
            UI.printParagraph("Appointment with given patient name doesn't exist. Please try again!");
        }
    }

    public void findByPatientNric(String[] parameters) {
        try {
            this.returnedFinderArray = AppointmentFinder.findAppointmentByPatientNric(appointments, (parameters[1]));
            createArrayOfFoundAppointments();
        } catch (NullPointerException e) {
            UI.printParagraph("Appointment with given patient nric doesn't exist. Please try again!");
        }
    }

    public void findByDoctorName(String[] parameters) {
        try {
            this.returnedFinderArray = AppointmentFinder.findAppointmentByDoctorName(appointments, parameters[1]);
            createArrayOfFoundAppointments();
        } catch (NullPointerException e) {
            UI.printParagraph("Appointment with given doctor name doesn't exist. Please try again!");
        }
    }

    public void findByDoctorNric(String[] parameters) {
        try {
            this.returnedFinderArray = AppointmentFinder.findAppointmentByDoctorNric(appointments, parameters[1]);
            createArrayOfFoundAppointments();
        } catch (NullPointerException e) {
            UI.printParagraph("Appointment with given doctor nric doesn't exist. Please try again!");
        }
    }

    public void findByAppointmentDate(String[] parameters) {
        try {
            this.returnedFinderArray = AppointmentFinder.findAppointmentByDate(appointments, parameters[1]);
            createArrayOfFoundAppointments();
        } catch (NullPointerException e) {
            UI.printParagraph("Appointment with given date doesn't exist. Please try again!");
        }
    }

    private void createArrayOfFoundAppointments() {
        if (returnedFinderArray.isEmpty()) {
            UI.printParagraph("Appointment doesn't exist please try again!");
        } else {
            CommandLineTable findAppointmentTable = new CommandLineTable();
            findAppointmentTable.setShowVerticalLines(true);
            findAppointmentTable.setHeaders("Appointment Id", "Patient Nric", "Patient Name", "Doctor Nric",
                    "Doctor Name", "Appointment Date", "Appointment Details");
            for (int i = 0; i < returnedFinderArray.size(); i++) {
                findAppointmentTable.addRow(returnedFinderArray.get(i).getAppointmentId(),
                        returnedFinderArray.get(i).getPatientNric(),
                        returnedFinderArray.get(i).getPatientName(),
                        returnedFinderArray.get(i).getDoctorNric(),
                        returnedFinderArray.get(i).getDoctorName(),
                        returnedFinderArray.get(i).getAppointmentDate(),
                        returnedFinderArray.get(i).getAppointmentDetails());
            }
            findAppointmentTable.print();
        }
    }
}


