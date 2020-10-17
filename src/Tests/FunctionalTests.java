package Tests;

import ConsoleApp.*;
import Database.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FunctionalTests {
    Database db = null;

    @BeforeEach
    void setUp() {
        List<Admin> admins = new ArrayList<Admin>();
        admins.add(new Admin("admin","admin","Linus", "Torvalds"));
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client("client","client","David", "Beckham"));
        List<Flight> flights = new ArrayList<Flight>();
        flights.add(new Flight(1, "Krakow", "Nowy Jork",
                "12/07/2020","10/07/2020","20:00", "05:00",
                100, 100));
        List<Ticket> tickets = new ArrayList<Ticket>();
        tickets.add(new Ticket(1,1,"client"));
        db = new Database(admins, clients, flights, tickets);

        //Printer printer = new Printer();
        //DataReader dataReader = DataReader.getInstance();
        //ConsoleApp consoleApp = new ConsoleApp(database, printer, dataReader);
    }

    @Test
    void FTCreateAdminWithCorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data[] = {"x","x","x", "x"};
        loggedAdmin.createAdmin(data);

        Assertions.assertTrue(db.getAdmin().stream().anyMatch(o -> o.getLogin().equals("x")) &&
                db.getAdmin().stream().anyMatch(o -> o.getPassword().equals("x")) &&
                db.getAdmin().stream().anyMatch(o -> o.getF_name().equals("x")) &&
                db.getAdmin().stream().anyMatch(o -> o.getL_name().equals("x")), "Failed to create admin account with correct data.");
    }

    @Test
    void FTCreateAdminWithIncorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data[] = {"admin","admin","Linus", "Torvalds"};
        int sizeBefore = db.getAdmin().size();
        loggedAdmin.createAdmin(data);
        int sizeAfter = db.getAdmin().size();
        Assertions.assertEquals(sizeBefore, sizeAfter,"Succeed to create admin account with incorrect data.");
    }

    @Test
    void FTCreateAdminWithClientData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data[] = {"client","client","David", "Beckham"};
        int sizeBefore = db.getAdmin().size();
        loggedAdmin.createAdmin(data);
        int sizeAfter = db.getAdmin().size();

        Assertions.assertEquals(sizeBefore, sizeAfter, "Failed to create admin account.");
    }

    @Test
    void FTCreateClientWithCorrectData() throws IOException {
        ConsoleApp consoleApp = new ConsoleApp(db, new Printer(), new DataReader());
        String data[] = {"z","z","z", "z"};
       consoleApp.createClient(data);

        Assertions.assertTrue(db.getClient().stream().anyMatch(o -> o.getLogin().equals("z")) &&
                db.getClient().stream().anyMatch(o -> o.getPassword().equals("z")) &&
                db.getClient().stream().anyMatch(o -> o.getF_name().equals("z")) &&
                db.getClient().stream().anyMatch(o -> o.getL_name().equals("z")) , "Failed to create client account.");
    }

    @Test
    void FTCreateClientWithIncorrectData() throws IOException {
        ConsoleApp consoleApp = new ConsoleApp(db, new Printer(), new DataReader());
        String data[] = {"client","client","David", "Beckham"};
        int sizeBefore = db.getClient().size();
        consoleApp.createClient(data);
        int sizeAfter = db.getClient().size();
        Assertions.assertEquals(sizeBefore, sizeAfter,"Succeed to create client account with incorrect data.");
    }

    @Test
    void FTCreateClientWithAdminData() throws IOException {
        ConsoleApp consoleApp = new ConsoleApp(db, new Printer(), new DataReader());
        String data[] = {"admin","admin","Linus", "Torvalds"};
        int sizeBefore = db.getClient().size();
        consoleApp.createClient(data);
        int sizeAfter = db.getClient().size();

        Assertions.assertEquals(sizeBefore, sizeAfter, "Succeed to create client account with admin data.");
    }

    @Test
    void FTAddFlightWithCorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String[] data = {"2", "Krakow", "Nowy Jork",
                "12/07/2020","10:00","13/07/2020", "15:00",
                "100", "100"};
        loggedAdmin.addFlight(data);

        Assertions.assertTrue(db.getFlight().stream().anyMatch(o -> o.getFlight_id().equals(2)) &&
                db.getFlight().stream().anyMatch(o -> o.getDeparture().equals("Krakow")) &&
                db.getFlight().stream().anyMatch(o -> o.getArrival().equals("Nowy Jork")) &&
                db.getFlight().stream().anyMatch(o -> o.getDeparture_date().equals("12/07/2020")) &&
                db.getFlight().stream().anyMatch(o -> o.getArrival_date().equals("13/07/2020")) &&
                db.getFlight().stream().anyMatch(o -> o.getDeparture_time().equals("10:00")) &&
                db.getFlight().stream().anyMatch(o -> o.getArrival_time().equals("15:00")) &&
                db.getFlight().stream().anyMatch(o -> o.getSeats_number().equals(100)) &&
                db.getFlight().stream().anyMatch(o -> o.getPrice().equals(100))
                , "Failed to create flight.");
    }

    @Test
    void FTAddFlightWithIncorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        //daty sie nie zgadzaja
        String data[] = {"3", "Krakow", "Nowy Jork",
                "12/07/2020","10/07/2020","20:00", "05:00",
                "100", "100"};
        int sizeBefore = db.getClient().size();
        loggedAdmin.addFlight(data);
        int sizeAfter = db.getClient().size();

        Assertions.assertEquals(sizeBefore, sizeAfter, "Succeed to add flight with incorrect data.");
    }

    @Test
    void FTDeleteFlightWithCorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String[] data = {"1"};
        int sizeBefore = db.getFlight().size();
        loggedAdmin.deleteFlight(data);
        int sizeAfter = db.getFlight().size();
        Assertions.assertNotEquals(sizeBefore, sizeAfter, "Failed to delete flight.");
    }

    @Test
    void FTDeleteFlightWithIncorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data[] = {"3"};
        int sizeBefore = db.getFlight().size();
        loggedAdmin.deleteFlight(data);
        int sizeAfter = db.getFlight().size();

        Assertions.assertEquals(sizeBefore, sizeAfter, "Succeed to create client account with admin data.");
    }

    @Test
    void FTCorrectFlightModification() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data1[] = {"1"};
        String[] data2 = {"1", "Belchatow", "Nowy Jork",
                "13/07/2020","11:00","14/07/2020", "12:00",
                "20", "20"};
        loggedAdmin.deleteFlight(data1);
        loggedAdmin.addFlight(data2);

        Assertions.assertTrue(db.getFlight().stream().anyMatch(o -> o.getFlight_id().equals(1)) &&
                        db.getFlight().stream().anyMatch(o -> o.getDeparture().equals("Belchatow")) &&
                        db.getFlight().stream().anyMatch(o -> o.getArrival().equals("Nowy Jork")) &&
                        db.getFlight().stream().anyMatch(o -> o.getDeparture_date().equals("13/07/2020")) &&
                        db.getFlight().stream().anyMatch(o -> o.getArrival_date().equals("14/07/2020")) &&
                        db.getFlight().stream().anyMatch(o -> o.getDeparture_time().equals("11:00")) &&
                        db.getFlight().stream().anyMatch(o -> o.getArrival_time().equals("12:00")) &&
                        db.getFlight().stream().anyMatch(o -> o.getSeats_number().equals(20)) &&
                        db.getFlight().stream().anyMatch(o -> o.getPrice().equals(20))
                , "Failed to modificate flight.");
    }

    @Test
    void FTIncorrectFlightModification() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data1[] = {"3"};
        String[] data2 = {"1", "Belchatow", "Nowy Jork",
                "13/07/2020","11:00","14/07/2020", "12:00",
                "20", "20"};
        loggedAdmin.deleteFlight(data1);
        loggedAdmin.addFlight(data2);

        Assertions.assertFalse(db.getFlight().stream().anyMatch(o -> o.getFlight_id().equals(1)) &&
                        db.getFlight().stream().anyMatch(o -> o.getDeparture().equals("Belchatow")) &&
                        db.getFlight().stream().anyMatch(o -> o.getArrival().equals("Nowy Jork")) &&
                        db.getFlight().stream().anyMatch(o -> o.getDeparture_date().equals("13/07/2020")) &&
                        db.getFlight().stream().anyMatch(o -> o.getArrival_date().equals("14/07/2020")) &&
                        db.getFlight().stream().anyMatch(o -> o.getDeparture_time().equals("11:00")) &&
                        db.getFlight().stream().anyMatch(o -> o.getArrival_time().equals("12:00")) &&
                        db.getFlight().stream().anyMatch(o -> o.getSeats_number().equals(20)) &&
                        db.getFlight().stream().anyMatch(o -> o.getPrice().equals(20))
                , "Failed to modificate flight.");
    }

    @Test
    void FTCorrectTicketReservation () throws IOException {
        LoggedClient loggedClient = new LoggedClient(db);System.out.println(4);
        String[] data = {"1", "client"};
        loggedClient.buyTicket(data);

        Assertions.assertTrue(db.getTicket().stream().anyMatch(o -> o.getFlight_id().equals(1)) &&
                db.getTicket().stream().anyMatch(o -> o.getLogin().equals("client")),"Failed buy correct ticket");
    }

    @Test
    void FTIncorrectTicketReservation () throws IOException {
        LoggedClient loggedClient = new LoggedClient(db);
        String[] data = {"3", "client"};
        loggedClient.buyTicket(data);

        Assertions.assertFalse(db.getTicket().stream().anyMatch(o -> o.getFlight_id().equals(3)) &&
                db.getTicket().stream().anyMatch(o -> o.getLogin().equals("client")),"Succeed buy incorrect ticket");
    }

    @Test
    void FTCorrectTicketResignation () throws IOException {
        LoggedClient loggedClient = new LoggedClient(db);
        String[] data = {"1"};
        loggedClient.cancelTicket(data);

        Assertions.assertFalse(db.getTicket().stream().anyMatch(o -> o.getFlight_id().equals(1)),"Failed resignation correct ticket");
    }

    @Test
    void FTIncorrectTicketResignation () throws IOException {
        LoggedClient loggedClient = new LoggedClient(db);
        String[] data = {"3"};
        int sizeBefore = db.getFlight().size();
        loggedClient.cancelTicket(data);
        int sizeAfter = db.getFlight().size();

        Assertions.assertEquals(sizeBefore, sizeAfter, "Failed resignation correct ticket.");
    }

    @Test
    void FTDeleteClientAccountWithCorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String[] data = {"client"};
        int sizeBefore = db.getClient().size();
        loggedAdmin.deleteClient(data);
        int sizeAfter = db.getClient().size();
        Assertions.assertNotEquals(sizeBefore, sizeAfter, "Failed to delete client account with correct data.");
    }

    @Test
    void FTDeleteClientAccountWithIncorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data[] = {"ack"};
        int sizeBefore = db.getClient().size();
        loggedAdmin.deleteClient(data);
        int sizeAfter = db.getClient().size();
        Assertions.assertEquals(sizeBefore, sizeAfter, "Succeed to delete client account with incorrect data.");
    }

    @Test
    void FTCorrectClientAccountModification() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data1[] = {"admin"};
        String[] data2 = {"adminq", "adminq", "a", "a"};
        loggedAdmin.deleteClient(data1);
        loggedAdmin.createClient(data2);

        Assertions.assertTrue(!db.getClient().stream().anyMatch(o -> o.getLogin().equals("admin")) &&
                        db.getClient().stream().anyMatch(o -> o.getLogin().equals("adminq")) &&
                        db.getClient().stream().anyMatch(o -> o.getPassword().equals("adminq")) &&
                        db.getClient().stream().anyMatch(o -> o.getF_name().equals("a")) &&
                        db.getClient().stream().anyMatch(o -> o.getL_name().equals("a"))
                        , "Failed to modify client account.");
    }

    @Test
    void FTIncorrectClientAccountModification() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data1[] = {"admingo"};
        String[] data2 = {"admin", "admin", "aa", "aa"};
        loggedAdmin.deleteClient(data1);
        loggedAdmin.createClient(data2);

        Assertions.assertFalse(//db.getClient().stream().noneMatch(o -> o.getLogin().equals("admingo")) &&
                        db.getClient().stream().anyMatch(o -> o.getLogin().equals("admin")) &&
                        db.getClient().stream().anyMatch(o -> o.getPassword().equals("admin")) &&
                        db.getClient().stream().anyMatch(o -> o.getF_name().equals("aa")) &&
                        db.getClient().stream().anyMatch(o -> o.getL_name().equals("aa"))
                , "Failed to modify client account.");
    }

    @Test
    void FTCreateClientByAdminWithCorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data[] = {"z","z","z", "z"};
        loggedAdmin.createClient(data);

        Assertions.assertTrue(db.getClient().stream().anyMatch(o -> o.getLogin().equals("z")) &&
                db.getClient().stream().anyMatch(o -> o.getPassword().equals("z")) &&
                db.getClient().stream().anyMatch(o -> o.getF_name().equals("z")) &&
                db.getClient().stream().anyMatch(o -> o.getL_name().equals("z")) , "Failed to create client account.");
    }

    @Test
    void FTCreateClientByAdminWithIncorrectData() throws IOException {
        LoggedAdmin loggedAdmin = new LoggedAdmin(db);
        String data[] = {"client","client","David", "Beckham"};
        int sizeBefore = db.getClient().size();
        loggedAdmin.createClient(data);
        int sizeAfter = db.getClient().size();
        Assertions.assertEquals(sizeBefore, sizeAfter,"Succeed to create client account with incorrect data.");
    }

}
