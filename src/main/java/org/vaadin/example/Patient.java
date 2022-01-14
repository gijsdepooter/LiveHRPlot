package org.vaadin.example;

import java.sql.*;
import java.util.Vector;

public class Patient {
    private Vector<Integer> patientID;
    private Vector<Timestamp> timeRec;
    private Vector<Integer> HR;
    private Vector<Float> ECG;

    public int getlastHR(){
        return HR.get(HR.size()-1);
    }

    public Timestamp getlastTimeRec(){
        return timeRec.get(timeRec.size()-1);
    }

    public Float getlastECG(){
        return ECG.get(ECG.size()-1);
    }

    public Vector<Timestamp> getTimeRec(){
        return timeRec;
    }

    public Vector<Float> getECG(){
        return ECG;
    }

    public void PrintPatient(){
        System.out.println(patientID);
        System.out.println(timeRec);
        System.out.println(HR);
        System.out.println(ECG);
    }

    public void AbnormalValReport(){
        try {
            String dbUrl = "jdbc:postgresql://localhost:5432/LocalData";
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, "postgres", "Surfdude04");

            for(int i = 0; i < this.patientID.size(); i++){
                System.out.println(this.timeRec.get(i));
                PreparedStatement query = conn.prepareStatement("INSERT INTO abnormalval(timerec,patientID,heartrate,ecg) values (?,?,?,0);");
                query.setTimestamp(1, this.timeRec.get(i));
                query.setInt(2,this.patientID.get(i));
                query.setInt(3,this.HR.get(i));
                query.executeUpdate();
            }
            conn.close();
        }catch (Exception e){ System.out.println("Exception while executing update: " + e.getMessage());}


    }


}
