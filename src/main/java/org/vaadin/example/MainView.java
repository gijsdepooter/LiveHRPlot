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
public class MainView extends VerticalLayout {

    public MainView() {
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

            SimpleDateFormat outputFormat = new SimpleDateFormat("HH:mm:ss.SS");

            for(int i=0; i< hr.size();i++) {
                //String t = outputFormat.format(ts.get(i));
                //System.out.println(t);
                series.add(new DataSeriesItem(ts.get(i), hr.get(i)));


            }
            conf.setSeries(series);


            add(chart);



        }catch (Exception e){}
    }
}