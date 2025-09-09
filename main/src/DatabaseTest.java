import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        try {
            // Explicitly load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Connect to SQLite database (creates file if not exists)
            Connection conn = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            Statement stmt = conn.createStatement();

            // Create table if not exists
            stmt.execute("CREATE TABLE IF NOT EXISTS expenses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "amount REAL)");

            // Insert a sample row
            stmt.execute("INSERT INTO expenses (name, amount) VALUES ('Lunch', 150.0)");

            // Query rows
            ResultSet rs = stmt.executeQuery("SELECT * FROM expenses");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Amount: " + rs.getDouble("amount"));
            }

            // Close connection
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
