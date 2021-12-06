import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) throws SQLException, IOException {

        tableCreation();
        Menu.menu();

    }


    public static void tableCreation() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/laboration3",
                "root", "1234567@");
             Statement statement = connection.createStatement()
        ) {
            String sql = "CREATE TABLE Artist " +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " first_name VARCHAR(255) not NULL, " +
                    " last_name VARCHAR(255)not NULL, " +
                    " age INTEGER not NULL, " +
                    " PRIMARY KEY ( id ))";

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Connection successful and database Artist created");
        System.out.println("Welcome to Artist app");

    }
}