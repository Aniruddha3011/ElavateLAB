import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseTest {
    public static void main(String[] args) {
        try {
         
            Class.forName("org.sqlite.JDBC");

            
            Connection conn = DriverManager.getConnection("jdbc:sqlite:expenses.db");
            Statement stmt = conn.createStatement();

           
            stmt.execute("CREATE TABLE IF NOT EXISTS expenses (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT, " +
                    "amount REAL)");

            
            stmt.execute("INSERT INTO expenses (name, amount) VALUES ('Lunch', 150.0)");

         
            ResultSet rs = stmt.executeQuery("SELECT * FROM expenses");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id")
                        + ", Name: " + rs.getString("name")
                        + ", Amount: " + rs.getDouble("amount"));
            }

          
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
