import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

class User {
    private List<List<String>> users;

    public User() {
        users = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                List<String> user = new ArrayList<>();
                user.add(fields[0]);
                user.add(fields[1]);
                users.add(user);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading from file");
            e.printStackTrace();
        }
    }

    public void create_user(String username, String password) {
        List<String> user = new ArrayList<>();
        user.add(username);
        user.add(password);
        users.add(user);
        try {
            FileWriter writer = new FileWriter("users.txt", true);
            writer.write(username + "," + password + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
            e.printStackTrace();
        }
    }

    public boolean authenticate_user(String username, String password) {
        for (List<String> user : users) {
            if (user.get(0).equals(username) && user.get(1).equals(password)) {
                return true;
            }
        }
        return false;
    }
}



public class project {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        User user = new User();
        boolean c=true;
        while(c==true){
            System.out.println("enter 1 to crete user and 2 to login");
            int choice=sc.nextInt();
            sc.nextLine();
            if(choice==1){
                System.out.println("enter user name and pass:");
                String newname=sc.nextLine();
                String newpass=sc.nextLine();
                user.create_user(newname, newpass);

            }
            else if(choice==2){
                System.out.println("enter user name and passward:");
                String uname=sc.nextLine();
                String pass=sc.nextLine();
                // Authenticate a user
                if (user.authenticate_user(uname, pass)) {
                    System.out.println("Authentication successful");
                } else {
                    System.out.println("Authentication failed");
                }
            }
            else{
                System.out.println("wrong choice");
            }
            System.out.println("yould you like to continue?1 for yes");
            int con=sc.nextInt();
            if(con==1){
            }
            else{
                c=false;
            }

        }
        

    }
}
