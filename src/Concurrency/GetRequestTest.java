package Concurrency;

/*import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.HeadMethod;
import org.apache.commons.lang.time.StopWatch;

public class Main {

*   public static void main(String[] args) throws URIException {
        StopWatch watch = new StopWatch();
        HttpClient client = new HttpClient();
        HttpMethod method = new HeadMethod("http://stackoverflow.com/");

        try {
            watch.start();
            client.executeMethod(method);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            watch.stop();
        }

        System.out.println(String.format("%s %s %d: %s", method.getName(), method.getURI(), method.getStatusCode(), watch.toString()));

    }
}    
    */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.management.RuntimeErrorException;

/*
 *  Retrieves a web page with HTTP GET request. uses HttpURLConnection to create a GET request.
 *  http://zetcode.com/java/getpostrequest/
 */

public class GetRequestTest {

    private static HttpURLConnection con;

    public static void main(String[] args) throws IOException {

       // String url = "http://webcode.me";
        String url = "https://en.wikipedia.org/wiki/Main_Page";

        try {

            URL myurl = new URL(url);
            con = (HttpURLConnection) myurl.openConnection();

            con.setRequestMethod("GET"); // request method type 
            System.out.println( "Resonse code: " + con.getResponseCode());
            
        /*    if (con.getResponseCode() != 200) {
            	throw new RuntimeException (con.getResponseCode(), "GET operation failed.");
            }*/
          //  Stopwatch requestTimer = Stopwatch.createStarted();

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) { //input stream is used to read the returned data.

                String line;
                content = new StringBuilder(); //use StringBuilder to build the content string.

                while ((line = in.readLine()) != null) {

                    content.append(line);   // read the data from the input stream line by line with readLine(). Each line is added to StringBuilder.
                    content.append(System.lineSeparator()); //After each line we append a system-dependent line separator.
                }
            }

            System.out.println(content.toString()); //print the content to the terminal.

        } finally {

            con.disconnect();
        }
    }
    
    /** NOTE: he openStream() method opens a connection to the specified url and returns an InputStream for reading from that connection. 
     * The InputStreamReader is a bridge from byte streams to character streams. It reads bytes and decodes them into characters using a specified charset.
     * In addition, BufferedReader is used for better performance.
     */
}

