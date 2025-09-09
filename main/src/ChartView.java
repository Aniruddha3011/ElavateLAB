import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class ChartView {
    public static <JFreeChart> void showChart() {
        List<Expense> expenses = ExpenseDAO.getAllExpenses();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Expense e : expenses) {
            dataset.addValue(e.getAmount(), "Expenses", e.getDate());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Expenses Over Time",
                "Date",
                "Amount",
                dataset
        );

        JFrame frame = new JFrame("Expense Chart");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(null, new ChartPanel(chart), 0);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
