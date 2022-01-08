package org.vaadin.example;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.*;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Route("")
public class LiveDataPlot extends AbstractChartExample {

    @Override
    public void initDemo() {
        try {
            HR_data HR = new HR_data();
            List<Integer> hr = HR.GetHR();
            List<Time> ts = HR.getTime();

            Chart chart = new Chart(ChartType.SPLINE);
            Configuration conf = chart.getConfiguration();
            conf.setTitle("Live hr");
            conf.setSubTitle("patiendID:23232");

            XAxis xaxis = new XAxis();

            xaxis.setType(AxisType.DATETIME);
            xaxis.setTickPixelInterval(150);
            xaxis.setTitle("Time");
            conf.addxAxis(xaxis);

            conf.getTooltip().setEnabled(false);
            conf.getLegend().setEnabled(false);

            YAxis yAxis = new YAxis();
            yAxis.setTitle("HR");

            DataSeries series = new DataSeries();
            series.setPlotOptions(new PlotOptionsSpline());
            series.setName("HR over time");

            for (int i = 0; i < 10; i++) {
                series.add(new DataSeriesItem(ts.get(i), hr.get(i)));
            }
            conf.setSeries(series);

            runWhileAttached(chart, () -> {
                try {
                final Time x = HR.LatestTime();
                final int y = HR.LatestHR();
                series.add(new DataSeriesItem(x, y), true, true);
                } catch (Exception e) {e.printStackTrace();}
            }, 3000, 3000);

            add(chart);

        } catch (Exception e) {}
    }
}
