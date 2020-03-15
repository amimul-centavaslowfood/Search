import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;


public class Main {

    // http://localhost:8080/RESTfulExample/json/product/get
    public static void main(String[] args) throws IOException {


        String wholeObject = "";

        try {

            URL url = new URL("https://www.googleapis.com/customsearch/v1?key=AIzaSyBgoB6M3SkUjOWZpRftNBFOtdLH1cLZkKs&cx=006923662362818103712:a6sklh2bdpf&q=%22Slow%20Food%20&%20Sous%20Vide%22");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());

            }


            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            String output;


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                //if(output.equals("title"))
                wholeObject += output;

                //System.out.println(output);

            }

            JSONParser jsonParser = new JSONParser();

            JSONObject jsonObject = (JSONObject) jsonParser.parse(wholeObject);


            JSONArray jsonarr_1 = (JSONArray) jsonObject.get("items");


            for (int i = 0; i < jsonarr_1.size(); i++) {


                JSONObject jsonobj_1 = (JSONObject) jsonarr_1.get(i);

                System.out.println("Position " + (i + 1));

                System.out.println("Title: " + jsonobj_1.get("title"));

                System.out.println("URL: " + jsonobj_1.get("link"));

                System.out.println();
                System.out.println();


            }

            conn.disconnect();
        } catch (MalformedURLException | ParseException e) {

            e.printStackTrace();
        }


    }


}



