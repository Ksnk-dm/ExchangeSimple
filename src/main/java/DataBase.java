import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.Properties;


public class DataBase {

    private static Connection getConnection() throws SQLException, IOException {
        Properties property = new Properties();
        try (InputStream fis = DataBase.class.getResourceAsStream("config.properties");
             InputStreamReader isr = new InputStreamReader(fis, "UTF-8")) {
            property.load(isr);
            String url = property.getProperty("db.url");
            String username = property.getProperty("db.login");
            String password = property.getProperty("db.password");
            fis.close();
            return DriverManager.getConnection(url, username, password);
        }
    }


    public static int Insert(Exchange curs) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
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
            }
        } catch (InstantiationException | NoSuchMethodException | SQLException | IllegalAccessException | ClassNotFoundException | InvocationTargetException | IOException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public static String Max(String name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                PreparedStatement ps = conn.prepareStatement("select MAX(rate) from exche where cc=?;");
                ps.setString(1, name);
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                String s = resultSet.getString(1);
                return s;
            }

        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static boolean delDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                PreparedStatement ps = conn.prepareStatement("TRUNCATE TABLE exche;");
                return ps.execute();
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String Avg(String name) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = getConnection()) {
                PreparedStatement ps = conn.prepareStatement("SELECT AVG(rate) FROM Exche WHERE cc=?");
                ps.setString(1, name);
                ResultSet resultSet = ps.executeQuery();
                resultSet.next();
                String s = resultSet.getString(1);
                return s;
            }
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException | ClassNotFoundException | SQLException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}



