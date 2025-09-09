import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.time.format.DateTimeFormatter;

public class ExpenseApp extends Application {
    private TextArea output;

    @Override
    public void start(Stage stage) {
        Database.init();

        // Input fields
        TextField nameField = new TextField();
        nameField.setPromptText("Expense name");

        TextField amountField = new TextField();
        amountField.setPromptText("Amount");

        // ✅ Dropdown for Category
        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.getItems().addAll(
                "Food",
                "Travel",
                "Shopping",
                "Bills",
                "Entertainment",
                "Health",
                "Other"
        );
        categoryBox.setPromptText("Select Category");

        // ✅ Date Picker for Date
        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Pick a Date");

        // Add Expense Button
        Button addBtn = new Button("Add Expense");
        addBtn.setOnAction(e -> {
            try {
                String name = nameField.getText();
                double amount = Double.parseDouble(amountField.getText());
                String category = categoryBox.getValue();
                String date = (datePicker.getValue() != null)
                        ? datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        : "";

                if (category == null || category.isEmpty()) {
                    showAlert("Error", "Please select a category!");
                    return;
                }
                if (date.isEmpty()) {
                    showAlert("Error", "Please pick a date!");
                    return;
                }

                System.out.println("Adding: " + name + " | " + amount + " | " + category + " | " + date);

                ExpenseDAO.addExpense(name, amount, category, date);
                refresh();

                // Clear input fields after adding
                nameField.clear();
                amountField.clear();
                categoryBox.setValue(null);
                datePicker.setValue(null);

            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Error", "Please check your input. Amount must be a number.");
            }
        });

        // Show Chart Button
        Button chartBtn = new Button("Show Chart");
        chartBtn.setOnAction(e -> ChartView.showChart());

        // Output area to display expenses
        output = new TextArea();
        output.setEditable(false);
        refresh();

        // Layout setup
        VBox root = new VBox(10,
                new HBox(10, nameField, amountField, categoryBox, datePicker, addBtn, chartBtn),
                output);
        root.setPadding(new Insets(10));

        Scene scene = new Scene(root, 800, 500);
        stage.setScene(scene);
        stage.setTitle("Expense Tracker");
        stage.show();
    }

    // Method to refresh the output area with current expenses
    private void refresh() {
        output.clear();
        var list = ExpenseDAO.getAllExpenses();
        for (Expense exp : list) {
            output.appendText(exp.toString() + "\n");
        }
        System.out.println("Loaded " + list.size() + " expenses.");
    }

    // Show an alert dialog with a given title and message
    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    // Main method to launch the JavaFX application
    public static void main(String[] args) {
        launch(args);
    }
}
