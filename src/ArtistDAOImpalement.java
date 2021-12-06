import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class ArtistDAOImpalement implements ArtistDAO {

    static Scanner sc = new Scanner(System.in);
    private final String url = "jdbc:mysql://localhost:3306/laboration3";
    private final String user = "root";
    private final String password = "1234567@";
    private final Connection connection;
    private Statement statement;
    private ResultSet rs;
    private final PreparedStatement insert;
    private final PreparedStatement findById;
    private final PreparedStatement findByName;
    private final PreparedStatement findByAge;
    private final PreparedStatement update;
    private final PreparedStatement delete;


    public ArtistDAOImpalement() throws SQLException {

        connection = DriverManager.getConnection(url, user, password);
        insert = connection.prepareStatement("insert into artist(first_name, last_name, age) values (?,?,?)");
        findById = connection.prepareStatement("SELECT * from artist where id=?");
        findByName = connection.prepareStatement("SELECT * from artist WHERE first_name like ?");
        findByAge = connection.prepareStatement("SELECT * from artist WHERE age =?");
        update = connection.prepareStatement("UPDATE artist set first_name =?, last_name = ?, age = ? where id = ?");
        delete = connection.prepareStatement("delete from artist where id =?");

    }


    @Override
    public void create(Artist artist) throws IOException, SQLException {
        try {
            insert.setString(1, artist.getFirst_name());
            insert.setString(2, artist.getLast_name());
            insert.setString(3, String.valueOf(artist.getAge()));
            insert.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Returning to main menu");
        Menu.menu();
    }

    @Override
    public void findAll() throws IOException, SQLException {
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery("Select * from artist");
            System.out.println("Table [ArtistInfo]");
            while (rs.next()) {
                System.out.println("[ID: " + rs.getString("id") + " Name: " + rs.getString("first_name") + " Surname: " +
                        rs.getString("last_name") + " Age: " + rs.getInt("age")
                        + "]");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Returning to main menu");
        Menu.menu();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void findById(int id) throws IOException, SQLException {
        try {
            findById.setInt(1, id);
            rs = findById.executeQuery();
            System.out.println("This person was found:");
            while (rs.next()) {
                System.out.println("[ID: " + rs.getString("id") + " Name: " + rs.getString("first_name")
                        + " Surname: " + rs.getString("last_name") + " Age: " + rs.getInt("age"));
            }
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        System.out.println("Returning to main menu");
        Menu.menu();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void findByName(String firstName) throws IOException, SQLException {
        try {
            findByName.setString(1, firstName);
            rs = findByName.executeQuery();
            System.out.println("This person was found:");
            while (rs.next()) {
                System.out.println("[ID: " + rs.getString("id") + " Name: " + rs.getString("first_name")
                        + " Surname: " + rs.getString("last_name") + " Age: " + rs.getInt("age") + "]");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.out.println("Returning to main menu");
        Menu.menu();
    }

    @Override
    @SuppressWarnings("Duplicates")
    public void findByAge(int age) throws IOException, SQLException {
        try {
            findByAge.setInt(1, age);
            rs = findByAge.executeQuery();
            System.out.println("This person was found:");
            while (rs.next()) {
                System.out.println("[ID: " + rs.getString("id") + " Name: " + rs.getString("first_name")
                        + " Surname: " + rs.getString("last_name") + " Age: " + rs.getInt("age"));
            }
        } catch (SQLException exp) {
            System.out.println(exp);
        }
        System.out.println("Returning to main menu");
        Menu.menu();
    }

    @Override
    public void updateArtist(int id) throws IOException, SQLException {

        System.out.print("Enter new name: ");
        String updateFirstName = sc.nextLine();
        System.out.print("Enter new last name: ");
        String updateLastName = sc.nextLine();
        System.out.print("Enter new age: ");
        int updateAge = sc.nextInt();
        try {

            update.setString(1, updateFirstName);
            update.setString(2, updateLastName);
            update.setInt(3, updateAge);
            update.setInt(4, id);
            update.executeUpdate();

            System.out.println("The person with the ID " + id + " have been updated to ");
            findById(id);

        } catch (SQLException | IOException ex2) {
            System.out.println(ex2);
        }
        System.out.println("Returning to main menu");
        Menu.menu();
    }

    @Override
    public void deleteArtist(int id) throws IOException, SQLException {
        try {
            delete.setInt(1, id);
            delete.execute();
            System.out.println("The person with the id " + id + " have been deleted from the database.");
        } catch (SQLException ex3) {
            System.out.println(ex3);
        }
        System.out.println("Returning to main menu");
        Menu.menu();
    }
}
