package org.ulearn;


import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.StandardChartTheme;

import org.jfree.chart.title.TextTitle;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.HorizontalAlignment;

import org.jfree.data.general.PieDataset;

public class PieChart extends ApplicationFrame
{

    static {
        ChartFactory.setChartTheme(new StandardChartTheme("JFree/Shadow", true));
    }
    public PieChart(PieDataset dataset) {
        super("Процентное соотношение пользователей в интернете от всего населения по субрегионам.");
        setContentPane(createChartPanel(dataset));
        pack();
        setVisible(true);
    }
    public JPanel createChartPanel(PieDataset dataset)
    {
        JFreeChart chart = createChart(dataset);
        chart.setPadding(new RectangleInsets(15, 15, 15, 15));
        ChartPanel panel = new ChartPanel(chart);
        return panel;
    }

    private JFreeChart createChart(PieDataset dataset)
    {
        var chart = ChartFactory.createPieChart(
                "Пользователи в интернете от всего населения по субрегионам",
                dataset
        );

        TextTitle title = chart.getTitle();
        title.setHorizontalAlignment(HorizontalAlignment.LEFT);
        title.setPaint(Color.DARK_GRAY);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(null);
        plot.setOutlineVisible(false);
        return chart;
    }
}