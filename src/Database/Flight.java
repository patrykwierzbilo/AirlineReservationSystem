package Database;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    private Integer flight_id;
    private String departure;
    private String arrival;
    private String departure_date;
    private String arrival_date;
    private String departure_time;
    private String arrival_time;
    private Integer seats_number;
    private Integer seats_booked;
    private Integer price;

    public Flight (Integer flight_id,
                   String departure,
                   String arrival,
                   String departure_date,
                   String arrival_date,
                   String departure_time,
                   String arrival_time,
                   Integer seats_number,
                   Integer price
    )  {
        this.flight_id = flight_id;
        this.departure = departure;
        this.arrival = arrival;
        this.departure_date = departure_date;
        this.departure_time = departure_time;
        this.arrival_date = arrival_date;
        this.arrival_time = arrival_time;
        this.seats_number = seats_number;
        this.seats_booked = 0;
        this.price = price;
    }

    public Flight (String[] data)  {
        this.flight_id = Integer.parseInt(data[0]);
        this.departure = data[1];
        this.arrival = data[2];
        this.departure_date = data[3];
        this.departure_time = data[4];
        this.arrival_date = data[5];
        this.arrival_time = data[6];
        this.seats_number = Integer.parseInt(data[7]);
        this.seats_booked = 0;
        this.price = Integer.parseInt(data[8]);
    }

    public Boolean isDateTimeCorrect() {
        String departureTmp = departure_date+" "+departure_time;
        String arrivalTmp = arrival_date+" "+arrival_time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime departureDateTime = LocalDateTime.parse(departureTmp, formatter);
        LocalDateTime arrivalDateTime = LocalDateTime.parse(arrivalTmp, formatter);
        //System.out.println("time"+arrivalDateTime+" "+departureDateTime);
        return arrivalDateTime.isAfter(departureDateTime);
    }

    public Boolean isFreeSeats() {
        return (seats_booked < seats_number);
    }

    public String FlighttoString () {
        return flight_id + ";" + departure
                + ";" + arrival  + ";" + departure_date  + ";" + departure_time
                + ";" + arrival_date  + ";" + arrival_time  + ";" + seats_number
                + ";" + seats_booked  + ";" + price;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public void setSeats_booked(Integer seats_booked) {
        this.seats_booked = seats_booked;
    }

    public void IncrementSeatsBooked() {
        this.seats_booked++;
    }

    public void DecrementSeatsBooked() {
        this.seats_booked--;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public String getArrival_date() {
        return arrival_date;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public Integer getSeats_number() {
        return seats_number;
    }

    public Integer getSeats_booked() {
        return seats_booked;
    }

    public Integer getPrice() {
        return price;
    }

}
