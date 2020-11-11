package Database;

public class Ticket {
    private Integer ticket_id;
    private Integer flight_id;
    private String login;

    public Ticket(Integer ticket_id, Integer flight_id, String login) {
        this.ticket_id = ticket_id;
        this.flight_id = flight_id;
        this.login = login;
    }

    public Ticket(String[] data) {
        this.ticket_id = Integer.parseInt(data[0]);
        this.flight_id = Integer.parseInt(data[1]);
        this.login = data[2];
    }

    @Override
    public String toString () {
        return ticket_id + ";" + flight_id
                + ";" + login;
    }

    public Integer getTicket_id() {
        return ticket_id;
    }

    public Integer getFlight_id() {
        return flight_id;
    }

    public String getLogin() {
        return login;
    }

}

