package ConsoleApp;

import java.io.IOException;

import Database.Database;
import Database.*;

// AdminInterface wykonuje zadania admina
public class AdminInterface extends ConsoleApp {

    public AdminInterface(Database dataAccess) {
        super(dataAccess, new Printer(), new DataReader());
    }

    void adminInterface() throws IOException {
        printer.printInterface();
        if(tryLogg(true)) {
            printer.printMessage("You have successfully logged in.");
            LoggedAdmin loggedAdmin = new LoggedAdmin(database);
            loggedAdmin.admin();
        }
    }


}
