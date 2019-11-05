package org.themunthedude.customreportviewer.visualisation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.FixedMillisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.themunthedude.customreportviewer.json.ReportJson;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class ChartBuilder {
    public static JFreeChart createChart(String chartTitle, List<ReportJson> reports) throws ParseException {
        Map<Date, Integer> highs = new HashMap<>();
        Map<Date, Integer> mediums = new HashMap<>();
        Map<Date, Integer> lows = new HashMap<>();

        for(ReportJson report : reports) {
            Date reportDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(report.getProjectInfo().getReportDate());
            highs.put(reportDate, report.getNumHigh());
            mediums.put(reportDate, report.getNumMedium());
            lows.put(reportDate, report.getNumLow());
        }

        TimeSeries highSeries = createSeries("High", highs);
        TimeSeries mediumSeries = createSeries("Mediums", mediums);
        TimeSeries lowSeries = createSeries("Lows", lows);

        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(highSeries);
        dataset.addSeries(mediumSeries);
        dataset.addSeries(lowSeries);

        JFreeChart chart = ChartFactory.createTimeSeriesChart(chartTitle, "Date", "Vulnerabilities", dataset, true, false, false);

        XYPlot plot = chart.getXYPlot();
        XYItemRenderer rend = plot.getRenderer();
        DateAxis axis = (DateAxis)plot.getDomainAxis();
        rend.setSeriesPaint(0, new Color(200,55,55));
        rend.setSeriesPaint(1, new Color(245,189,73));
        rend.setSeriesPaint(2, new Color(86,168,36));
        plot.setBackgroundPaint(new Color(97, 97, 97));

        rend.setSeriesStroke(0, new BasicStroke(5.0f));
        rend.setSeriesStroke(1, new BasicStroke(5.0f));
        rend.setSeriesStroke(2, new BasicStroke(5.0f));

        axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));

        return chart;
    }

    public static BufferedImage extractImage(JFreeChart chart, int width, int height) {
        BufferedImage img =
                new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = img.createGraphics();
        chart.draw(g2, new Rectangle2D.Double(0, 0, width, height));

        g2.dispose();
        return img;

    }

    private static TimeSeries createSeries(String name, Map<Date, Integer> data) {
        TimeSeries series = new TimeSeries(name);

        for(Map.Entry<Date, Integer> entry : data.entrySet()) {
            FixedMillisecond milli = new FixedMillisecond(entry.getKey());
            RegularTimePeriod period = (RegularTimePeriod)milli;
            series.add(period, entry.getValue());
        }

        return series;
    }
}
