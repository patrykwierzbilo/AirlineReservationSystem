import ConsoleApp.*;
import Database.*;
import java.io.IOException;
import java.util.List;

// S: klasy wykonuja jedna czynnosc np. do kolejnych czynnosci sa wywolywane klasy rozszerzajace
// O: np. by rozbudowywac aplikacje potrzebne sa tylko metody np do obslugi nowych obiektow
// L: podczas dziedziczenia przez klasy pochodne rozszerzaja one tylko klasy bazowe
// I: klasy nie uzywaja niepotrzebnych im metod
// D: wyskopoziomowe klasy dostaja wstrzykniete zaleznosci - tworzymy klasy niskopoziomowe
//    i przekazujemy do konstruktora wysokopoziomowej

//wzorce projektowe:
// - Template method - np. abstrakcyjna klasa User implementuje wspolna metode UsertoString
//   dla konkretnych klas pochodnych - klienta i administratora
// - Singleton - dla bazy danych by zablokowac mozliwosc duplikowania danych i dla czytnika danych

//przytworzeniu obiektu database dane wczytywane z plikow
//wlaczenie czesci aplikacyjnej
//zapisanie danych i koniec dzialania aplikacji
public class Main {
    public static void main (String[] args) throws IOException {
        System.out.println("App is starting work");
        Data data = new Data();
        List<Admin> admins = data.convertStringToAdmins(data.readDataFromDB("admin"));
        List<Client> clients = data.convertStringToClients(data.readDataFromDB("client"));
        List<Flight> flights = data.convertStringToFlights(data.readDataFromDB("flight"));
        List<Ticket> tickets = data.convertStringToTickets(data.readDataFromDB("ticket"));

        Database database = Database.getInstance(admins, clients, flights, tickets);

        Printer printer = new Printer();
        DataReader dataReader = DataReader.getInstance();
        ConsoleApp consoleApp = new ConsoleApp(database, printer, dataReader);
        consoleApp.mainExecution();

        data.saveData(database.getAdmin(), database.getClient(),
                database.getFlight(), database.getTicket());
    }
}
