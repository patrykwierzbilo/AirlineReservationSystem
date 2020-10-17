package Tests;

import Database.Flight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FlightTest {

    @ParameterizedTest
    @CsvSource({"'12/07/2020', '11/07/2020', '20:00', '05:00'", "'12/07/2020','10/07/2020','21:00', '15:00'"})
    void checkCorrectDateTime(String departure_date, String arrival_date, String departure_time, String arrival_time) {

        Flight flight = new Flight(1, "Krakow", "Nowy Jork",
                departure_date, arrival_date, departure_time, arrival_time,
                100, 100);

        Boolean correctness = flight.isDateTimeCorrect();

        Assertions.assertFalse(correctness, "Arrival must be after departure.");
    }



    @org.junit.jupiter.api.Test
    void checkFlightWithFreeSeats() {
        Flight flight = new Flight(1, "Krakow", "Nowy Jork",
                "12/07/2020","10/07/2020","20:00", "05:00",
                100, 100);

        flight.setSeats_booked(10);
        Boolean isFreeSeats = flight.isFreeSeats();

        Assertions.assertTrue(isFreeSeats, "The isFreeSeats method returned false when free seats were available.");

    }

    @org.junit.jupiter.api.Test
    void checkFlightWithoutFreeSeats() {
        Flight flight = new Flight(1, "Krakow", "Nowy Jork",
                "12/07/2020","10/07/2020","20:00", "05:00",
                100, 100);

        flight.setSeats_booked(100);
        Boolean isFreeSeats = flight.isFreeSeats();

        Assertions.assertFalse(isFreeSeats, "The isFreeSeats method returned true when free seats weren't available.");
    }
}