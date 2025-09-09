import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.io.File;

public class Database {
   private static final String DB_URL = "jdbc:sqlite:A:/coding/java/ExpenseTracker/main/expenses.db";


    public static Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC"); // force load driver
            Connection conn = DriverManager.getConnection(DB_URL);

            //path of the DB file
           File dbFile = new File("A:/coding/java/ExpenseTracker/main/expenses.db");
System.out.println("Using DB file: " + dbFile.getAbsolutePath());


          
            conn.setAutoCommit(true);

            return conn;
        } catch (Exception e) {
            System.out.println("atabase connection failed.");
            e.printStackTrace();
            return null;
        }
    }

    public static void init() {
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS expenses (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "name TEXT," +
                         "amount REAL," +
                         "category TEXT," +
                         "date TEXT)");
            System.out.println("Table ready.");
        } catch (Exception e) {
            System.out.println("Table creation failed.");
            e.printStackTrace();
        }
    }
}
