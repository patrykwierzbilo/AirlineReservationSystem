package ConsoleApp;

import java.io.IOException;
import java.util.Scanner;

public class DataReader {
    static DataReader dataReader = null;
    Printer printer;
    public DataReader() {
        printer = new Printer();
    }

    public static DataReader getInstance() {
        if(dataReader == null) {
            dataReader = new DataReader();
            return dataReader;
        }
        System.out.println("Can't create second dataReader!");
        return dataReader;
    }

    public String[] readData(int n) throws IOException {
        String[] data = new String[n];
        for(int i = 0; i < n; i++)
            try {
                Scanner in = new Scanner(System.in);
                data[i] = in.nextLine();
            } catch (NumberFormatException e) {
                printer.printMessage("Not a string!");
            }
        return data;
    }

    public int readCase() {
        int number = 1;
        try {
            Scanner in = new Scanner(System.in);

            number = in.nextInt();
        } catch (NumberFormatException e) {
            printer.printMessage("Not a number!");
        }
        return  number;
    }

    public Boolean readAnswer(String message) throws IOException {
        printer.printMessage(message+"[Yes/No]: ");
        String answer = "";
        while (true) {
            try {
                Scanner in = new Scanner(System.in);
                answer = in.nextLine();
            } catch (NumberFormatException e) {
                printer.printMessage("Not a string!");
            }
            if (answer.equals("Yes") || answer.equals("yes"))
                return true;
            else if (answer.equals("No") || answer.equals("no"))
                return false;
            else
                printer.printMessage("Incorrect data, try to enter the answer again");
        }
    }


    public String readDataFromUser() {
        String data ="";
        try {
            Scanner in = new Scanner(System.in);
            data = in.nextLine();
        } catch (NumberFormatException e) {
            printer.printMessage("Can't read data.");
        }
        return data;
    }

}
