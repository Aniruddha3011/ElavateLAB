

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class ChartTest {
    public static void main(String[] args) {
        //dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(5, "Expenses", "Jan");
        dataset.addValue(8, "Expenses", "Feb");
        dataset.addValue(6, "Expenses", "Mar");

        //chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Monthly Expenses",
                "Month",
                "Amount",
                dataset
        );

        //chart in JFrame
        JFrame frame = new JFrame("JFreeChart Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChartPanel(chart));
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}
