package org.vaadin.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class GET {
    static Patient makeGETrequest (String request) {
        Patient p = new Patient();
        try {
            URL myURL = new URL("http://localhost:8080/DATAserver/Login/"+request);

            HttpURLConnection conn = (HttpURLConnection) myURL.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("charset", "utf-8");

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(myURL.openStream()));
            String inputLine;
            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();


            // Read the body of the response
            while ((inputLine = in.readLine()) != null) { System.out.println(inputLine);
                p = gson.fromJson(inputLine, Patient.class);
            }
            in.close();

        }catch(MalformedURLException e){System.out.println(e);}catch (IOException e){System.out.println(e);}
        return p;

    }

}
