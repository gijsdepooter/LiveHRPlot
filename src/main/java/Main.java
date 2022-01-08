import org.vaadin.example.HR_data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Main {
    public static void main(String[] args){
        try {
            HR_data HR = new HR_data();
            List<Integer> hr = HR.GetHR();
            List<Time> ts = HR.getTime();
            System.out.println(hr);
            System.out.println(ts);
        }catch (Exception e){System.out.println(e.getMessage());}
    }
}
