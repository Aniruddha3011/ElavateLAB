import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAO {
    public static void addExpense(String name, double amount, String category, String date) {
        try (Connection conn = Database.connect();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO expenses (name, amount, category, date) VALUES (?, ?, ?, ?)")) {

            ps.setString(1, name);
            ps.setDouble(2, amount);
            ps.setString(3, category);
            ps.setString(4, date);

            int rows = ps.executeUpdate();
            System.out.println("Inserted rows: " + rows);

        } catch (Exception e) {
            System.out.println("Insert failed.");
            e.printStackTrace();
        }
    }

    public static List<Expense> getAllExpenses() {
        List<Expense> list = new ArrayList<>();
        try (Connection conn = Database.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM expenses")) {

            while (rs.next()) {
                list.add(new Expense(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("amount"),
                        rs.getString("category"),
                        rs.getString("date")
                ));
            }
            System.out.println("Loaded " + list.size() + " expenses from DB.");
        } catch (Exception e) {
            System.out.println("Query failed.");
            e.printStackTrace();
        }
        return list;
    }
}
