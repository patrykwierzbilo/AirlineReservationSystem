package Database;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Data {

    public List<String> readDataFromDB(String name) throws IOException {
        String filePath = "src/Database/"+name+"_data.txt";
        BufferedReader fileReader = null;
        List<String> data = new ArrayList<>();

        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            String record;

            while ((record = fileReader.readLine()) != null)
                data.add(record);
            System.out.println("Read " + name + " data from database succeed.");
        } catch (Exception e) {
            System.out.println("Read " + name + " data from database went wrong.");
        }  finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }
        return data;
    }

    public void saveData(List<Admin> admins, List<Client> clients,
                         List<Flight> flights, List<Ticket> tickets) throws IOException {
        writeDataToDB(convertAdminsToString(admins), "admin");
        writeDataToDB(convertClientsToString(clients), "client");
        writeDataToDB(convertTicketsToString(tickets), "ticket");
        writeDataToDB(convertFlightsToString(flights), "flight");
    }

    public void writeDataToDB(List<String> data, String name) {
        String filePath = "src/Database/" + name + "_data.txt";

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath))) {
            for (String object : data)
                fileWriter.write(object + "\n");
            System.out.println("Write " + name + " data to database succeed.");
        } catch (Exception e) {
            System.out.println("Write " + name + " data from database went wrong.");
        }
    }

    //4 kolejne metody sa redundantne poniewaz nie potrafilem znalezc rozwiazania
    //dzialajacego niezaleznie od typu obiektu
    public List<Admin> convertStringToAdmins(List<String> data) {
        List<Admin> result = new ArrayList<>();
        for(String s: data)
            result.add(new Admin(s.split(";")));
        return result;
    }

    public List<Client> convertStringToClients(List<String> data) {
        List<Client> result = new ArrayList<>();
        for(String s: data)
            result.add(new Client(s.split(";")));
        return result;
    }

    public List<Flight> convertStringToFlights(List<String> data) {
        List<Flight> result = new ArrayList<>();
        for(String s: data)
            result.add(new Flight(s.split(";")));
        return result;
    }

    public List<Ticket> convertStringToTickets(List<String> data) {
        List<Ticket> result = new ArrayList<>();
        for(String s: data)
            result.add(new Ticket(s.split(";")));
        return result;
    }

    //tak samo jak przy convertStringToObject
    public List<String> convertAdminsToString(List<Admin> admins) {
        List<String> result = new ArrayList<>();
        for(Admin a: admins)
            result.add(a.UsertoString());
        return result;
    }

    public List<String> convertClientsToString(List<Client> clients) {
        List<String> result = new ArrayList<>();
        for(Client c: clients)
            result.add(c.UsertoString());
        return result;
    }

    public List<String> convertFlightsToString(List<Flight> flights) {
        List<String> result = new ArrayList<>();
        for(Flight f: flights)
            result.add(f.FlighttoString());
        return result;
    }

    public List<String> convertTicketsToString(List<Ticket> tickets) {
        List<String> result = new ArrayList<>();
        for(Ticket t: tickets)
            result.add(t.TickettoString());
        return result;
    }

}
