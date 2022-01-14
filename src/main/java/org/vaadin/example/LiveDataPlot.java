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
public class LiveDataPlot extends AbstractChart {

    @Override
    public void initDemo(String plt) {

        Chart chart = new Chart(ChartType.SPLINE);
        Configuration conf = chart.getConfiguration();

        conf.setSubTitle("patiendID:23232");
        XAxis xaxis = new XAxis();


        xaxis.setType(AxisType.DATETIME);
        xaxis.setTickPixelInterval(150);
        xaxis.setTitle("Time");
        conf.addxAxis(xaxis);

        YAxis yAxis = new YAxis();

        conf.getTooltip().setEnabled(false);
        conf.getLegend().setEnabled(false);

        DataSeries series = new DataSeries();
        series.setPlotOptions(new PlotOptionsSpline());

        if(plt.equals("HR")){
            try {
            Patient p;

            conf.setTitle("Live Heart Rate");
            conf.setSubTitle("patiendID:23232");

            yAxis.setTitle("HR");

            series.setName("HR over time");

            for (int i = 0; i < 10; i++) {
                p = GET.makeGETrequest("HR");
                series.add(new DataSeriesItem(p.getlastTimeRec(), p.getlastHR()));
            }
            conf.setSeries(series);

            runWhileAttached(chart, () -> {
                try {
                    final Patient p1 = GET.makeGETrequest("HR");
                    series.add(new DataSeriesItem(p1.getlastTimeRec(), p1.getlastHR()), true, true);
                } catch (Exception e) {e.printStackTrace();}
            }, 3000, 3000);

            add(chart);

        } catch (Exception e) {}
        }
        else if(plt.equals("ECG")){
            try {
                Patient p;

                conf.setTitle("Live ECG");

                yAxis.setTitle("ECG");

                series.setName("ECG over time");
                p = GET.makeGETrequest("ECG");
                for (int i = 0; i < 500; i++) {
                    series.add(new DataSeriesItem(p.getTimeRec().get(i), p.getECG().get(i)));
                }
                conf.setSeries(series);

                runWhileAttached(chart, () -> {
                    try {
                        final Patient p1 = GET.makeGETrequest("ECGlast");

                        series.add(new DataSeriesItem(p1.getlastTimeRec(), p1.getlastECG()), true, true);

                    } catch (Exception e) {e.printStackTrace();}
                }, 30, 30);

                add(chart);

            } catch (Exception e) {System.out.println(e);}
        }

    }
}
