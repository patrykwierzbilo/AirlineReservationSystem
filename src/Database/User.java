package Database;

public abstract class User {
    protected String login;
    protected String password;
    protected String f_name;
    protected String l_name;

    public User(String login, String password, String f_name, String l_name) {
        this.login = login;
        this.password = password;
        this.f_name = f_name;
        this.l_name = l_name;
    }

    @Override
    public String toString () {
        return login + ";" + password
                + ";" + f_name  + ";" + l_name;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getF_name() {
        return f_name;
    }

    public String getL_name() {
        return l_name;
    }
}
