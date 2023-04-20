package sg.edu.nus.iss;

import java.io.BufferedReader;
import java.io.Console;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        // Instantiate File object to be able to check if it exists or not
        String dirPath = args[0];
        File newDirectory = new File(dirPath);

        // Check if directory exists. Yes? print_exist : mkdir
        if (newDirectory.exists()) {
            System.out.println("Directory already exists");
        } else {
            newDirectory.mkdir();
        }

        System.out.println("Welcome to my Shopping Cart");

        // List collection to store the cart items of login user
        List<String> cartItems = new ArrayList<String>();

        // Use Console class to read from keyboard input
        Console con = System.console();
        String input = "";

        // Used to keep track of current logged in user
        // Also used to create a filename to store user cart items
        String loginUser = "";

        // Exit while loop if keyboard input "quit" is entered
        while (!input.equals("quit")) {
            input = con.readLine("What would you like to do?");

            if (input.contains("login")) {
                Scanner scan = new Scanner(input.substring(6));

                while (scan.hasNext()) {
                    loginUser = scan.next();
                }

                // Check if the file <loginuser> exists
                // If it does not exist create a new file
                // Else override

                File loginFile = new File(dirPath + File.separator + loginUser);
                if (loginFile.exists()) {
                    System.out.println("File " + loginUser + " already exists");
                } else {
                    loginFile.createNewFile();
                }
            }

            if (input.startsWith("users")) {
                File directoryPath = new File(dirPath);

                String[] directoryListing = directoryPath.list();
                System.out.println("List of files and directories in the specific folder " + dirPath);
                for (String dirList : directoryListing) {
                    System.out.println(dirList);
                }
            }

            // Add cart items logic
            if (input.startsWith("add")) {
                input = input.replace(',', ' ');

                // Use FileWrite & PrintWriter to write to a <loginuser> file
                FileWriter fw = new FileWriter(dirPath + File.separator + loginUser, false);
                PrintWriter pw = new PrintWriter(fw);

                String currentScanString = "";
                Scanner inputScanner = new Scanner(input.substring(4));

                while (inputScanner.hasNext()) {
                    currentScanString = inputScanner.next();
                    cartItems.add(currentScanString);

                    // Write to file using PrintWriter
                    pw.write(currentScanString + "\n");
                }

                // Flush and close the FileWriter & PrintWriter objects
                pw.flush();
                pw.close();
                fw.close();
            }

            // User must be logged in first
            // Must perform the following first
            // e.g. login <loginuser>
            if (input.equals("list")) {
                // Need a file class and BufferedReader class to read the cart Items form the fle (bacause data is stored line by line)
                File readFile = new File(dirPath + File.separator + loginUser);
                BufferedReader br = new BufferedReader(new FileReader(readFile));

                String readFileInput = "";

                //  Reset cartItems List collection
                cartItems = new ArrayList<String>();

                // While loop to read through all item records in file
                while ((readFileInput = br.readLine()) != null) { // REMEMBER THAT YOU HAVE TO ASSIGN THE READ LINE INTO A VARIABLE
                    System.out.println(readFileInput);
                    cartItems.add(readFileInput);
                }

                // Once while loop is exit, close BufferedReader object
                br.close();
            }

            // Start of DELETE logic
            if (input.startsWith("delete")) {
                String[] stringVal = input.split(" ");

                int givenIndex = Integer.parseInt(stringVal[1]);

                if (givenIndex <= cartItems.size() ) {
                    cartItems.remove(givenIndex);
                }
                else {
                    System.out.println("Index given is out of range.");
                }

                // Open FileWriter and BufferedWriter

                // Loop to write cartItems to file

                //  Close BufferedWriter and FileWriter
            }

        }
    }
}
