import java.io.IOException;
import java.sql.SQLException;


public interface ArtistDAO {

    void create(Artist artist) throws IOException, SQLException;

    void deleteArtist(int id) throws IOException, SQLException;

    void updateArtist(int id) throws IOException, SQLException;

    void findAll() throws IOException, SQLException;

    void findById(int id) throws IOException, SQLException;

    void findByAge(int age) throws SQLException, IOException;

    void findByName(String first_name) throws SQLException, IOException;

}
