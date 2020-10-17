package Database;

public class Admin extends User {
    public Admin(String login, String password, String f_name, String l_name) {
        super(login, password, f_name, l_name);
    }

    public Admin(String[] data) {
        super(data[0], data[1], data[2], data[3]);
    }

}