import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.net.*;
//import com.google.gson.*;

public class HackerRank_MovieTitles {
  
	 /*
     * Base url: https://jsonmock.hackerrank.com/api/movies/search/?Title=
     */

	// passed
    public static String[] getMovieTitles(String substr) {
        String response;
        int pageNumber = 1;
        int total = Integer.MAX_VALUE;
        List<String> titles = new ArrayList<String>();

        while (pageNumber <= total) {
            try {
                URL url = new URL("https://jsonmock.hackerrank.com/api/movies/search/?Title=" + substr + "&page=" + pageNumber);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                InputStreamReader in = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(in);

                while ((response = br.readLine()) != null) {
                    JsonObject jObj = new Gson().fromJson(response, JsonObject.class);
                    total = jObj.get("total_pages").getAsInt();
                    JsonArray data = jObj.getAsJsonArray("data");

                    for (int i =0; i < data.size(); i++){
                        String title = data.get(i).getAsJsonObject().get("Title").getAsString();
                        titles.add(title);
                    }
                } 
                br.close();
                pageNumber++;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }

        Collections.sort(titles);
        return titles.toArray(new String[0]);
    }

    public static void main(String[] args) throws IOException{
        Scanner in = new Scanner(System.in);
        final String fileName = System.getenv("OUTPUT_PATH");
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        String[] res;
        String _substr;
        try {
            _substr = in.nextLine();
        } catch (Exception e) {
            _substr = null;
        }
        
        res = getMovieTitles(_substr);
        for(int res_i=0; res_i < res.length; res_i++) {
            bw.write(String.valueOf(res[res_i]));
            bw.newLine();
        }
        
        bw.close();
    }
}
