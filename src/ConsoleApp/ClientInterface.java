package ConsoleApp;

import Database.*;

import java.io.IOException;

// w ClientInterface logujemy sie lub rejestrujemy
public class ClientInterface extends ConsoleApp {
    Integer ticketNumber = 1;
    public ClientInterface(Database dataAccess) {
        super(dataAccess, new Printer(), new DataReader());
        //wczytanie ostatniego zajetego id biletu
        //if(dataAccess.getTicket().size() > 0)
        ticketNumber = dataAccess.getTicket().get(dataAccess.getTicket().size() - 1).getTicket_id() + 1;
    }

    void clientInterface() throws IOException {
        int cases = 0;
        do {
            printWelcomeInterface();
            try {
                cases = dataReader.readCase();
            } catch (Exception e) {
                printer.printMessage("Only 1, 2, 3 are accepted");
            }

            switch (cases) {
                case 1:
                    if(tryLogg(false)){
                        printer.printMessage("You have successfully logged in.");
                        LoggedClient loggedClient = new LoggedClient(database);
                        loggedClient.client();
                    }
                    break;
                case 2:
                    signIn();
                    break;
                case 3: {
                    printer.printMessage("End of session");
                    cases = 3;
                    break;
                }
                default:
                    printer.separate();
            }
        } while(cases != 3);
    }

    void printWelcomeInterface() throws IOException {
        printer.printInterface();
        printer.printMessage("Welcome to the client page!" );
        printer.printMessage("Actions: ");
        printer.printMessage("1. Log in");
        printer.printMessage("2. Sign in");
        printer.printMessage("3. End session and log out");
    }

    void signIn() throws IOException {
        printer.printMessage("Please follow next requests:" );

        printer.printMessage("Creating new client account");
        printer.printMessage("Please, enter: login, password, first name, last name:");
        String[] data = dataReader.readData(4);
        createClient(data);
    }

}
