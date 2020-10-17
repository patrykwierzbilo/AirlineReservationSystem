package Database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


// klasa Database zawiera dane w postaci obiektow trzymanych w listach
// oraz metody operujace na danych
public class Database {
    static Database database = null;
    List<Admin> admins;
    List<Client> clients;
    List<Flight> flights;
    List<Ticket> tickets;
    public Database(List<Admin> admins, List<Client> clients,
                    List<Flight> flights, List<Ticket> tickets) {
        this.admins = admins;
        this.clients = clients;
        this.flights = flights;
        this.tickets = tickets;
    }

    public static Database getInstance(List<Admin> admins, List<Client> clients,
                                       List<Flight> flights, List<Ticket> tickets) {
        if(database == null) {
            database = new Database(admins, clients, flights, tickets);
            return database;
        }
        System.out.println("Can't create second database!");
        return database;
    }

    public void addFlight(Flight flight) {
        if(flight != null)
            flights.add(flight);
        else
            throw new IllegalArgumentException();
    }

    public void deleteFlight(Integer flight_id) {
        flights.removeIf(a -> a.getFlight_id().equals(flight_id));
    }

    public Boolean existsFlight(Integer flight_id) {
        return flights.stream().anyMatch(o -> o.getFlight_id().equals(flight_id));
    }

    public void addTicket(Ticket ticket) {
        if(ticket != null)
            tickets.add(ticket);
        else
            throw new IllegalArgumentException();
        getFlight(ticket.getFlight_id()).IncrementSeatsBooked();
    }

    public void deleteTicket(Integer ticket_id) {
        tickets.removeIf(a -> a.getTicket_id().equals(ticket_id));
    }

    public Boolean existsTicket(Integer ticket_id) {
        return tickets.stream().anyMatch(o -> o.getTicket_id().equals(ticket_id));
    }

    public void buyTicket(Integer flight_id, String login, Integer ticketNumber) {
        if (existsFlight(flight_id)
                && getFlight(flight_id).isFreeSeats() && !checkLogin(login) )
            addTicket(new Ticket(ticketNumber, flight_id, login));
        else {
            System.out.println("Not enough seats or wrong flight_id");
            throw new IllegalArgumentException();
        }
    }

    public void cancelTicket(Integer ticket_id) {
        Ticket cancel = null;
        for(Ticket t: tickets)
            if(t.getTicket_id().equals(ticket_id))
                cancel = t;
        if(cancel != null && existsFlight(cancel.getFlight_id())) {
            getFlight(cancel.getFlight_id()).DecrementSeatsBooked();
            tickets.remove(cancel);
        } else {
            System.out.println("Cant cancel ticket");
            throw new IllegalArgumentException();
        }
    }

    public Boolean checkLogin(String login) {
        for(Admin a: admins)
            if(a.getLogin().equals(login))
                return false;
        for(Client c: clients)
            if(c.getLogin().equals(login))
                return false;
        return true;
    }

    public void addClientAccount(Client client) {
        clients.add(client);
    }

    public void deleteClientAccount(String login) {
        clients.removeIf(a -> a.getLogin().equals(login));
    }

    public void addAdminAccount(Admin admin) {
        admins.add(admin);
    }

    public void deleteAdminAccount(String login) {
        admins.removeIf(a -> a.getLogin().equals(login));
    }

    public Boolean isDataCorrect(String login, String password, Boolean type) {
        if(type) {
            for (Admin a : admins)
                if (a.getLogin().equals(login) && a.getPassword().equals(password))
                    return false;
        } else {
            for (Client c : clients)
                if (c.getLogin().equals(login) && c.getPassword().equals(password))
                    return false;
        }
        return true;
    }

    public List<Admin> getAdmin() {
        return  admins;
    }


    public List<Client> getClient() {
        return clients;
    }

    public List<Flight> getFlight() {
        return flights;
    }

    public Flight getFlight(Integer flight_id) {
        for(Flight f: flights)
            if(f.getFlight_id().equals(flight_id))
                return f;
       return null;
    }

    public List<Ticket> getTicket() {
        return tickets;
    }

}
