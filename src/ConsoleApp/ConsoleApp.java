package ConsoleApp;

import Database.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

// ConsoleApp to glowne menu uzytkownika
// odpowiada za wywolanie czesci aplikacji admina - AdminInterface lub klienta - ClientInterface
// oraz zawiera kilka metod dziedziczonych przez te "interfejsy" (rozszerzaja one ConsoleApp) np. logowanie lub wyswietlanie
// wlasnego konta poniewaz sa one niezbedne nawet w przypadku dodawania nowych interfejsow
// zawiera rowniez printer i dataReader wspolny dla calej aplikacji

public class ConsoleApp {
    Database database;
    Printer printer;
    DataReader dataReader;
    public ConsoleApp(Database dataAccess, Printer printer, DataReader dataReader) {
        this.database = dataAccess;
        this.printer = printer;
        this.dataReader = dataReader;
    }

    public void mainExecution () throws IOException {
        printer.printInterface();
        int actions = 0;
        do {
            printer.printChooseInterface();
            try {
                actions = dataReader.readCase();
            } catch (Exception e) {
                printer.separate();
                printer.printMessage("Only 1, 2, 3 are accepted");
            }

            switch (actions) {
                case 1:
                    printer.clearConsole();
                    ClientInterface clientInterface = new ClientInterface(database);
                    clientInterface.clientInterface();
                    break;
                case 2:
                    printer.clearConsole();
                    AdminInterface adminInterface = new AdminInterface(database);
                    adminInterface.adminInterface();
                    break;
                case 3:
                    printer.printMessage("End of user session");
                    actions = 3;
                    break;
            }
        } while (actions != 3);
    }

    Boolean tryLogg (Boolean type) throws IOException {
        printer.printMessage("Please, log in");
        String login = "";
        String password = "";
        boolean control = false;
        while(database.isDataCorrect(login, password, type)) {
            printer.clearConsole();
            if(control) {

                if (!dataReader.readAnswer("Do you want to try once more time?"))
                    return false;
            } else
                control = true;
            printer.printMessage("Login: ");
            login = dataReader.readDataFromUser();
            printer.printMessage("Password: ");
            password = dataReader.readDataFromUser();
        }
        return true;
    }

    void displayFlights() throws IOException {
        printer.printMessage("Avaliable flights:");
        printer.printMessage("flight_id;departure_place;arrival_place;departure_date;departure_time");
        printer.printMessage("arrival_date;arrival_time;seats_number;seats_booked;price");
        for(Flight f: database.getFlight()) {
            printer.printMessage(f.FlighttoString());
        }
        //aplikacja czeka az uzytkownik kliknie dowolny przycisk
        //zanim przejdzie do kolejnego okna
        //identycznie w pozostalych funkcjach 'display'
        System.in.read();
    }

    void displayMyAcount() throws IOException {
        List<Admin> admins = database.getAdmin();
        List<Client> clients = database.getClient();
        printer.printMessage("Please, enter your login:");
        String login = dataReader.readDataFromUser();
        printer.printMessage("login;password;first_name;last_name");
        for(Admin a: admins)
            if(a.getLogin().equals(login))
                printer.printMessage(a.UsertoString());
        for(Client c: clients)
            if(c.getLogin().equals(login))
                printer.printMessage(c.UsertoString());
        System.in.read();
    }

    public void createClient(String[] data) throws IOException {

        Client client = new Client(data);
        //data[0]-login, data[1]-password
        if(database.isDataCorrect(data[0], data[1], false) &&
           database.isDataCorrect(data[0], data[1], true)) {
            try {
                database.addClientAccount(client);
            } catch (Exception e) {
                printer.printMessage("Can't add client account");
            }
            printer.printMessage("Successfully added client accout");
        }
        else
            printer.printMessage("Wrong data to new client account");
    }

}