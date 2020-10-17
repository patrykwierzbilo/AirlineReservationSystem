package Tests;

import Database.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Database.*;
import java.util.ArrayList;
import java.util.List;

class DatabaseTest {
    Database db;
    List<Admin> admins;
    List<Client> clients;
    List<Flight> flights;
    List<Ticket> tickets;
    @BeforeEach
    void setUp() {
        admins = new ArrayList<Admin>();
        clients = new ArrayList<Client>();
        flights = new ArrayList<Flight>();
        tickets = new ArrayList<Ticket>();
        admins.add(new Admin("admin", "admin", "admin", "admin"));
        admins.add(new Admin("z", "z", "z", "z"));
        clients.add(new Client("client", "client", "client", "client"));
        flights.add(new Flight(1, "Krakow", "Nowy Jork",
                "12/07/2020","10/07/2020","20:00", "05:00",
                100, 100));
        flights.add(new Flight(2, "Krakow", "Zanzibar",
                "12/07/2020","10/07/2020","21:00", "15:00",
                100, 100));
        tickets.add(new Ticket(1, 1, "client"));
        tickets.add(new Ticket(5, 1, "client"));
        db = new Database(admins, clients, flights, tickets);
    }

    @Test
    void testAddingFlightToDb() {
        Flight flight = new Flight(3, "Smocza Wyspa", "BarCelOna",
                "22/07/2021","23/07/2021","12:00", "15:00",
                100, 100);

        db.addFlight(flight);

        List<Flight> result = db.getFlight();
        Assertions.assertTrue(result.contains(flight), "Failed to add flight to database.");
    }

    @Test
    void testAddNullFlightToDb() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> db.addFlight(null),
                "AddFlight method didnt throw an IllegalArgumentException when adding null to Db");
    }

    @Test
    void testToDeleteFlightFromDb() {
        db.deleteFlight(2);

        List<Flight> result = db.getFlight();

        Assertions.assertFalse(result.stream().anyMatch(o -> o.getFlight_id().equals(2)));
    }

    @Test
    void isExistsFlightReturnTrueForExistingFlight() {
        Boolean result = db.existsFlight(1);

        Assertions.assertTrue(result, "The existsFlight method returns false for existing flight");
    }

    @Test
    void isExistsFlightReturnFalseForNonexistentFlight() {
        Boolean result = db.existsFlight(10);

        Assertions.assertFalse(result, "The existsFlight method returns true for a non-existent flight");
    }

    @Test
    void testAddingAddingTicketToDb() {
        Ticket ticket = new Ticket(2, 1, "client");

        db.addTicket(ticket);

        List<Ticket> result = db.getTicket();
        Assertions.assertTrue(result.contains(ticket), "Failed to add ticket to database.");
    }

    @Test
    void tryAddNullTicketToDb() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> db.addTicket(null),
                "CreateTicket method didnt throw an IllegalArgumentException when adding null to Db");
    }

    @Test
    void testToDeleteTicketFromDb() {
        db.deleteTicket(1);

        List<Ticket> result = db.getTicket();

        Assertions.assertFalse(result.stream().anyMatch(o -> o.getTicket_id().equals(1)));
    }

    @Test
    void isExistsTicketReturnTrueForExistingTicket() {
        Boolean result = db.existsTicket(5);

        Assertions.assertTrue(result, "The existsTicket method returns false for existing ticket");
    }

    @Test
    void isExistsTicketReturnFalseForNonexistentTicket() {
        Boolean result = db.existsTicket(10);

        Assertions.assertFalse(result, "The existsTicket method returns true for a non-existent ticket");
    }

    @Test
    void testIdCorrectenssInBuyTicket() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> db.buyTicket(13, "client", 1),
                "BuyTicket method didnt throw an IllegalArgumentException when buyingTicket with wrong flight_id");
    }

    @Test
    void testLoginCorrectenssInBuyTicket() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> db.buyTicket(1, "cant", 2),
                "BuyTicket method didnt throw an IllegalArgumentException when buyingTicket with wrong login");
    }

    @Test
    void testIdCorrectenssInCancelTicket() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> db.cancelTicket(13),
                "CancelTicket method didnt throw an IllegalArgumentException when canceling non-existent Ticket");
    }

    @Test
    void testAddingAdminAccountToDb() {
        Admin admin = new Admin("x", "x", "x", "x");

        db.addAdminAccount(admin);

        List<Admin> result = db.getAdmin();
        Assertions.assertTrue(result.contains(admin), "Failed to add admin account to database.");
    }

    @Test
    void testToDeleteAdminFromDb() {
        db.deleteAdminAccount("z");

        List<Admin> result = db.getAdmin();

        Assertions.assertFalse(result.stream().anyMatch(o -> o.getLogin().equals("z")), "Failed to delete admin account from database.");
    }

    @Test
    void testAddingClientAccountToDb() {
        Client client = new Client("c", "c", "c", "c");

        db.addClientAccount(client);

        List<Client> result = db.getClient();
        Assertions.assertTrue(result.contains(client), "Failed to add client account to database.");
    }

    @Test
    void testToDeleteClientFromDb() {
        db.deleteClientAccount("client");

        List<Client> result = db.getClient();

        Assertions.assertFalse(result.stream().anyMatch(o -> o.getLogin().equals("client")), "Failed to delete client account from database.");
    }

    @Test
    void testDataCorrectness() {
        Assertions.assertTrue(db.isDataCorrect("qwert", "qwert", true) ||
                        db.isDataCorrect("qwert", "qwert", false),
                "IsDataCorrect return true for non-existent login or password.");
    }

}