package co.edu.escuelaing.arep.Service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class ApiResponse {

    public static String getResponse(String url) throws Exception {
         //Crear objeto URL y HttpURLConnection
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // Establecer método GET
        con.setRequestMethod("GET");

        // Leer respuesta
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        return response.toString();
    }
}
