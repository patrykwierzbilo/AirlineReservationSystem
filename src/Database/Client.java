package Database;


public class Client extends User {
    public Client(String login, String password, String f_name, String l_name) {
        super(login, password, f_name, l_name);
    }

    public Client(String[] data) {
        super(data[0], data[1], data[2], data[3]);
    }

}
