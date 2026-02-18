package bd.edu.seu.background;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Customerlogin {
    public static void writenewlogin(String line)
    {
        try (RandomAccessFile raf = new RandomAccessFile("login.txt", "rw")) {
            raf.seek(raf.length());
            raf.writeBytes(line+"\n");
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: login.txt");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Failed to write to login file.");
            ex.printStackTrace();
        }
    }

    public static List<Officer> readLogInFile() {
        List<Officer> officerList = new ArrayList<>(); // List to hold officer data
        try (RandomAccessFile raf = new RandomAccessFile("login.txt", "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                String[] arr = line.split(","); // Split line into parts
                if (arr.length == 2) { // Ensure correct data format
                    Officer officer = new Officer(arr[0], arr[1]);
                    officerList.add(officer); // Add officer to list
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: login.txt");
            ex.printStackTrace(); // Exception handling
        } catch (IOException ex) {
            System.out.println("Failed to read login file.");
            ex.printStackTrace(); // Exception handling
        }
        return officerList; // Return list of officers
    }
}
