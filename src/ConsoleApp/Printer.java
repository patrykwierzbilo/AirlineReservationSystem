package ConsoleApp;

import java.io.IOException;

public class Printer {
    public Printer() {

    }

    public void printMessage(String message) {
        int size = 57;
        int spaceSize = 0;
        spaceSize = size - message.length();
        //dla zerowej dlugosci blad, zmniejszajac przesuniecie ostatniej * w prawo
        if(spaceSize == 0)
            spaceSize--;
        String format = "%" + spaceSize + "s";
        System.out.printf("%10s*  " + message + format + "*\n", " ", " ");
    }

    public void separate() {
        printMessage("*******************************************************");
    }

    public void printInterface() {
        String space = " ";
        printMessage("*******************************************************");
        printMessage("");
        printMessage("------------!! Airline Reservation System !!-----------");
        printMessage("");
        printMessage("*******************************************************");
    }

    public void printChooseInterface() {
        String space = " ";
        printMessage("Please, choose your interface");
        printMessage("1. Client");
        printMessage("2. Admin");
        printMessage("3. End session");
        printMessage("Your choose:");
    }

    public void clearConsole() throws IOException {
        try
        {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
        }
        catch (Exception e)
        {
            System.out.println("Can't clear console.");
        }
    }

}
