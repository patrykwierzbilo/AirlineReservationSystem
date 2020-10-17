package ConsoleApp;

import Database.*;

import java.io.IOException;

public class LoggedAdmin extends AdminInterface {
    public LoggedAdmin(Database dataAccess) {
        super(dataAccess);
    }

    void admin() throws IOException {
        int cases = 0;
        do {
            printAdminInterface();
            try {
                cases = dataReader.readCase();
            } catch (Exception e) {
                printer.printMessage("Enter value between 1 and 9");
            }

            switch (cases) {
                case 1: {
                    printer.printMessage("Creating new administrator account");
                    printer.printMessage("Please, enter: login, password, first name, last name:(every data in different line)");
                    String[] data = null;
                    try {
                        data = dataReader.readData(4);
                    } catch (Exception e) {
                        printer.printMessage("Wrong data");
                    }
                    createAdmin(data);
                    break;
                } case 2:{
                    printer.printMessage("Deleting administrator account");
                    printer.printMessage("Please, enter login to delete");
                    String[] loginToDelete = dataReader.readData(1);
                    deleteAdmin(loginToDelete);
                    break;
                } case 3:{
                    printer.printMessage("Adding flight");
                    printer.printMessage("Please, enter flight_id, departure,arrival,");
                    printer.printMessage("departure_date,arrival_time,departure_date,");
                    printer.printMessage("arrival_time,seats_number,price(every data in different line)");
                    String[] data = null;
                    try {
                        data = dataReader.readData(9);
                    } catch (Exception e) {
                        printer.printMessage("Wrong data");
                    }
                    addFlight(data);
                    break;
                } case 4:
                    displayFlights();
                    break;
                case 5:{
                    printer.printMessage("Deleting flight");
                    printer.printMessage("Please, enter flight_id to delete");
                    String[] idToDelete = dataReader.readData(1);
                    deleteFlight(idToDelete);
                    break;
                } case 6:{
                    printer.printMessage("Creating new client account");
                    printer.printMessage("Please, enter: login, password, first name, last name:");
                    String[] data = dataReader.readData(4);
                    createClient(data);
                    break;
                } case 7:{
                    printer.printMessage("Deleting client account");
                    printer.printMessage("Please, enter login to delete");
                    String[] loginToDelete = dataReader.readData(1);
                    deleteClient(loginToDelete);
                    break;
                } case 8:{
                    displayMyAcount();
                    break;
                } case 9: {
                    printer.printMessage("End of session");
                    break;
                }
            }
        } while(cases != 9);
    }

    public void createAdmin(String[] data) throws IOException {

        assert data != null;
        Admin admin = new Admin(data);
        if(database.isDataCorrect(data[0], data[1], true) &&
                database.isDataCorrect(data[0], data[1], false)) {
            try {
                database.addAdminAccount(admin);
            } catch (Exception e) {
                printer.printMessage("Can't add admin account");
            }
            printer.printMessage("Successfully added");
        }
        else
            printer.printMessage("Failed attempt:");
    }

    public void deleteAdmin(String[] loginToDelete) throws IOException {

        try {
            database.deleteAdminAccount(loginToDelete[0]);
        } catch (Exception e) {
            printer.printMessage("Can't delete admin account");
        }
    }

    public void addFlight(String[] data) throws IOException {

        Flight flight = new Flight(data);
        try {
            if (!database.existsFlight(Integer.parseInt(data[0]))
                    && flight.isDateTimeCorrect()
                    && flight.isFreeSeats()) {
                database.addFlight(flight);
                System.out.println("Succeed add flight");
            }
        } catch (Exception e) {
            printer.printMessage("Can't add flight");
        }
        //System.in.read();
    }

    public void deleteFlight(String[] idToDelete) throws IOException {

        try {
            database.deleteFlight(Integer.parseInt(idToDelete[0]));

        } catch (Exception e) {
            printer.printMessage("Can't delete flight");
        }
    }

    public void deleteClient(String[] loginToDelete) throws IOException {

        try {
            database.deleteClientAccount(loginToDelete[0]);
        } catch (Exception e) {
            printer.printMessage("Can't delete client account");
        }
    }

    void printAdminInterface () throws IOException {
        printer.clearConsole();
        printer.printMessage("Welcome to admins page!");
        printer.printMessage("Options: ");
        printer.printMessage("1. Create new admin account");
        printer.printMessage("2. Delete admin account");
        printer.printMessage("3. Add new flight");
        printer.printMessage("4. Display flights");
        printer.printMessage("5. Remove flight");
        printer.printMessage("6. Create new client account");
        printer.printMessage("7. Delete client account");
        printer.printMessage("8. Display my account");
        printer.printMessage("9. End session and log out");
    }

}
