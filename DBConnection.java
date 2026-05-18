import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:bmi.db");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}