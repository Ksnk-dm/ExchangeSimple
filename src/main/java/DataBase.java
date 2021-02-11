import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;


public class DataBase {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
        } catch (InstantiationException |IllegalAccessException |InvocationTargetException | NoSuchMethodException|ClassNotFoundException  e) {
            e.printStackTrace();
        } 

    }

    private Connection getConnection() throws SQLException, IOException {
        Properties property = new Properties();
        try (InputStream fis = DataBase.class.getResourceAsStream("config.properties");
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {
            property.load(isr);
            String url = property.getProperty("db.url");
            String username = property.getProperty("db.login");
            String password = property.getProperty("db.password");
            fis.close();
            isr.close();
            return DriverManager.getConnection(url, username, password);
        }
    }


    public int insert(Exchange curs) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO exche (r030, txt, rate, cc, exchangedate) VALUES(?,?,?,?,?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
                preparedStatement.setInt(1, curs.getR030());
                preparedStatement.setString(2, curs.getTxt());
                preparedStatement.setDouble(3, curs.getRate());
                preparedStatement.setString(4, curs.getCc());
                preparedStatement.setString(5, curs.getExchangedate());
                return preparedStatement.executeUpdate();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public String max(String name) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("select MAX(rate) from exche where cc=?;");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (IllegalArgumentException | SecurityException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean delDb() {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE exche;");
            return ps.execute();
        } catch (IllegalArgumentException | SecurityException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String avg(String name) {
        try (Connection conn = getConnection()) {
            PreparedStatement ps = conn.prepareStatement("SELECT AVG(rate) FROM Exche WHERE cc=?");
            ps.setString(1, name);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            return resultSet.getString(1);
        } catch (IllegalArgumentException | SecurityException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}



