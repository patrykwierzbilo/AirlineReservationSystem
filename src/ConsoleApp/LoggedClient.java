package ConsoleApp;

import Database.*;

import java.io.IOException;
import java.util.List;

// w LoggedClient wykonywane sa zadania klienta
public class LoggedClient extends ClientInterface {

    public LoggedClient(Database dataAccess) {
        super(dataAccess);
    }

    void client() throws IOException {
        int cases = 0;
        do {
            printClientInterface();
            try {
                cases = dataReader.readCase();
            } catch (Exception e) {
                printer.printMessage("Enter value between 1 and 9");
            }

            switch (cases) {
                case 1:
                    displayFlights();
                    break;
                case 2:{
                    printer.printMessage("You are buying ticket");
                    printer.printMessage("Please, enter flight_id and login");
                    String[] data = dataReader.readData(2);
                    buyTicket(data);
                    break;
                } case 3:{
                    printer.printMessage("You are canceling ticket");
                    printer.printMessage("Please, enter ticket_id");
                    String[] data = dataReader.readData(1);
                    cancelTicket(data);
                    break;
                } case 4:
                    displayTickets();
                    break;
                case 5: {
                    printer.printMessage("Deleting client account");

                    printer.printMessage("Please, enter your login:");
                    String login = dataReader.readDataFromUser();

                    dataReader.readAnswer("Are you sure to delete your account?");
                    deleteAccount(login);
                    break;
                } case 6:
                    displayMyAcount();
                    break;
                case 9: {
                    printer.printMessage("End of session");
                    break;
                }
            }
        } while(cases != 9);
    }

    public void buyTicket(String[] data) throws IOException {

        Integer flight_id = Integer.parseInt(data[0]);
        String login = data[1];

        try {
            database.buyTicket(flight_id, login, ticketNumber++);
        } catch (Exception e) {
            printer.printMessage("Can't buy ticket");
        }
    }

    public void cancelTicket(String[] data) throws IOException {

        Integer ticket_id = Integer.parseInt(data[0]);
        try {
            database.cancelTicket(ticket_id);
        } catch (Exception e) {
            printer.printMessage("Can't cancel ticket");
        }
    }

    void displayTickets() throws IOException {
        List<Ticket> tickets = database.getTicket();
        printer.printMessage("Please, enter your login:");
        String login = dataReader.readDataFromUser();

        printer.printMessage("ticket_id;flight_id;user_login");
        for(Ticket t: tickets)
            if (t.getLogin().equals(login))
                printer.printMessage(t.toString());
        System.in.read();
    }

    void deleteAccount(String login) throws IOException {

        try {
            database.deleteClientAccount(login);
        } catch (Exception e) {
            printer.printMessage("Can't delete client account");
        }
    }

    void printClientInterface () throws IOException {
        printer.clearConsole();
        printer.printMessage("You have successfully logged in.");
        printer.printMessage("Options: ");
        printer.printMessage("1. Display flights");
        printer.printMessage("2. Buy ticket");
        printer.printMessage("3. Cancel ticket");
        printer.printMessage("4. Display tickets");
        printer.printMessage("5. Delete account");
        printer.printMessage("6. Display my account");
        printer.printMessage("9. End session and log out");
    }

}
