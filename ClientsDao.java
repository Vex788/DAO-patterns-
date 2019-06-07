import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ClientsDao {
    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Collection<Clients> findAll() {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM clients;");
            ResultSet result = statement.executeQuery();
            Collection<Clients> collection = new ArrayList<Clients>();

            while (result.next()) {
                Clients obj = new Clients();

                obj.setId(result.getInt(1));
                obj.setName(result.getString(2));
                obj.setAge(result.getInt(3));

                //System.out.println(obj.toString());
                collection.add(obj);
            }

            return collection;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Clients find(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT FROM clients WHERE id = ?");

            statement.setInt(1, id);
            statement.executeUpdate();

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean create(Clients client) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO clients (name, age) VALUES(?, ?)");

            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Clients client) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE clients SET name = ?, age = ? WHERE id = ?");

            statement.setString(1, client.getName());
            statement.setInt(2, client.getAge());
            statement.setInt(3, client.getId());

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM clients WHERE id = ?");

            statement.setInt(1, id);

            return statement.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
