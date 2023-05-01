import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Movie {
    private String[][] movieDetails;
    private int count;

    public Movie() {
        movieDetails = new String[100][4]; // Initialize with space for 100 movies
        count = 0;
    }

    public void addMovie() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter movie name: ");
        String name = scanner.nextLine();
        System.out.print("Enter theater name: ");
        String theater = scanner.nextLine();
        System.out.print("Enter number of tickets: ");
        int numTickets = scanner.nextInt();
        System.out.print("Enter cost per ticket: ");
        double costPerTicket = scanner.nextDouble();

        movieDetails[count][0] = name;
        movieDetails[count][1] = theater;
        movieDetails[count][2] = Integer.toString(numTickets);
        movieDetails[count][3] = Double.toString(costPerTicket);

        count++;

        // Write to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("movie_details.txt", true));
            writer.write(name + "," + theater + "," + numTickets + "," + costPerTicket + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }

    public void displayMovies() {
        System.out.println("Movie\tTheater\tTickets\tCost per ticket");
        for (int i = 0; i < count; i++) {
            System.out.println(movieDetails[i][0] + "\t" + movieDetails[i][1] + "\t" + movieDetails[i][2] + "\t$" + movieDetails[i][3]);
        }
    }
}


public class moviespro {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean c=true;
        while(c==true){
            Movie movie = new Movie();
            movie.addMovie();
            movie.displayMovies();
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
