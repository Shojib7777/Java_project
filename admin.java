import java.io.*;
import java.util.*;

class Admin {
    private String username;
    private String password;
    private String filename;

    public Admin(String username, String password, String filename) {
        this.username = username;
        this.password = password;
        this.filename = filename;
    }

    public boolean login(String username, String password) {
        return (username.equals(this.username) && password.equals(this.password));
    }

    public boolean updateRecord(String movieName, String theaterName, int numOfTickets, double ticketCost) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            List<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts[0].equals(movieName) && parts[1].equals(theaterName)) {
                    line = movieName + "," + theaterName + "," + numOfTickets + "," + ticketCost;
                }
                lines.add(line);
            }
            scanner.close();
            FileWriter writer = new FileWriter(file);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteRecord(String movieName, String theaterName) {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            List<String> lines = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (!parts[0].equals(movieName) || !parts[1].equals(theaterName)) {
                    lines.add(line);
                }
            }
            scanner.close();
            FileWriter writer = new FileWriter(file);
            for (String line : lines) {
                writer.write(line + "\n");
            }
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
public class admin{


    public static void main(String[] args) {
        Admin admin = new Admin("admin", "password", "movie_details.txt");
        if (admin.login("admin", "password")) {
            if (admin.updateRecord("movie1", "the1", 10, 8.0)) {
                System.out.println("Record updated successfully");
            } else {
                System.out.println("Failed to update record");
            }
            if (admin.deleteRecord("movie2", "the2")) {
                System.out.println("Record deleted successfully");
            } else {
                System.out.println("Failed to delete record");
            }
        } else {
            System.out.println("Invalid login credentials");
        }
    }
}
