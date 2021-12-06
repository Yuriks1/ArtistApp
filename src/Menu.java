import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void menu() throws SQLException, IOException {

        ArtistDAO dao = new ArtistDAOImpalement();

        System.out.println("What would you like to do?\n"
                + "1 Add an artist.\n"
                + "2 Delete an artist.\n"
                + "3 Update an artist.\n"
                + "4 Show all artists.\n"
                + "5 Find an artist by ID.\n"
                + "6 Find an artist by Age\n"
                + "7 Find an artist by Name\n"
                + "0 Exit ");


        String choice = scanner.nextLine();

        while (true) {

            switch (choice) {

                case "1":
                    System.out.print("Insert first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Insert last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Insert age: ");
                    int age = Integer.parseInt(scanner.nextLine());
                    Artist artist = new Artist(firstName, lastName, age);
                    dao.create(artist);
                    break;

                case "2":
                    System.out.print("Type what ID of the Artist you want to DELETE: ");
                    int idDelete = scanner.nextInt();
                    scanner.nextLine();
                    dao.deleteArtist(idDelete);
                    break;

                case "3":
                    System.out.print("Type the ID of the artist you want to update: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    dao.updateArtist(idUpdate);
                    break;

                case "4":
                    dao.findAll();
                    break;

                case "5":
                    System.out.print("Type what ID of the Artist you want to search for: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    dao.findById(id);
                    break;

                case "6":
                    System.out.print("Type the age of the artist you want to search for: ");
                    int ageSearch = scanner.nextInt();
                    scanner.nextLine();
                    dao.findByAge(ageSearch);
                    break;

                case "7":
                    System.out.print("Type the name of the artist you want to search for: ");
                    String nameSearch = scanner.nextLine();
                    dao.findByName(nameSearch);
                    break;

                case "0":
                    System.out.println("Program is exiting");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Something went wrong. Try again please!");
                    break;
            }
        }
    }
}