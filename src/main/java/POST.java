import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class POST {
    static void RequestWriter(URL myURL, byte[] body){
        try {
            HttpURLConnection conn;
            conn = (HttpURLConnection) myURL.openConnection();
            // Set up the header
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(body.length));
            conn.setDoOutput(true);
            // Write the body of the request
            try (OutputStream outputStream = conn.getOutputStream()) {
                outputStream.write(body, 0, body.length);
            }
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream(), "utf-8"));
            String inputLine;
            // Read the body of the response
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
            }
            bufferedReader.close();
        }catch (IOException e){System.out.println(e);}

    }
    static void makePOSTrequest(Doctor Doc){
        try{
            URL myURL = new URL("http://localhost:8080/DATAserver/Login");
            Gson gson = new Gson();
            String jsonString = gson.toJson(Doc);
            byte[] body = jsonString.getBytes(StandardCharsets.UTF_8);
            RequestWriter(myURL,body);

        }catch(MalformedURLException e){System.out.println(e);}

    }

}
