public class Expense {
    private int id;
    private String name;
    private double amount;
    private String category;
    private String date;

    public Expense(int id, String name, double amount, String category, String date) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public String getCategory() { return category; }
    public String getDate() { return date; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + amount + " | " + category + " | " + date;
    }
}
