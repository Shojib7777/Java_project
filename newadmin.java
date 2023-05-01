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

public class newadmin {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Admin admin = new Admin("admin", "password", "movie_details.txt");
        if (admin.login("admin", "password")) {
            System.out.println("enter 1 to update and 2 to delete movie records ");
            int x1=sc.nextInt();
            sc.nextLine();
            if(x1==1){
                System.out.print("Enter movie name: ");
                String name = sc.nextLine();
                System.out.print("Enter theater name: ");
                String theater = sc.nextLine();
                System.out.print("Enter number of tickets: ");
                int numTickets = sc.nextInt();
                System.out.print("Enter cost per ticket: ");
                double costPerTicket = sc.nextDouble();
                if (admin.updateRecord(name, theater, numTickets, costPerTicket)) {
                    System.out.println("Record updated successfully");
                } else {
                    System.out.println("Failed to update record");
                }
            }
            else if(x1==2){
                System.out.print("Enter movie name: ");
                String name = sc.nextLine();
                System.out.print("Enter theater name: ");
                String theater = sc.nextLine();
                if (admin.deleteRecord(name,theater )) {
                    System.out.println("Record deleted successfully");
                } else {
                    System.out.println("Failed to delete record");
                }
            }
            else{
                System.out.println("wrong choice");
            }
            
        } else {
            System.out.println("Invalid login credentials");
        }
    }
}



