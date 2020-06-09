package Concurrency;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        	System.out.println("Exception: " + e.getMessage() );
            e.printStackTrace();
        } finally {
            watch.stop();
        }

        System.out.println(String.format("%s %s %d: %s", method.getName(), method.getURI(), method.getStatusCode(), watch.toString()));

    }
}    
    */

public class ResponseTime {
	//creat static class 
   static class IThread implements Runnable{

        static double timing(String url) {
            try {
                URL myUrl = new URL(url);
                long l = System.currentTimeMillis();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(myUrl.openStream()));
                
                while ((in.readLine()) != null);
                in.close();
                double spentMS = System.currentTimeMillis()  - l;
                return spentMS/1000;
                
            }catch (Throwable t){
                return Double.MAX_VALUE ;
            }
        }

        static List<IThread> makePool(int times, String url){
            List<IThread> pool = new ArrayList<>(times);
            
            for ( int i = 0 ; i < times; i++ ){
                IThread it = new IThread(url);
                Thread t = new Thread( it);
                pool.add(it);
                t.start();
            }
            return pool;
        }

        double timing;

        final String url;

        Thread myThread;

        IThread(String url){
            this.url = url;
            timing = Double.MAX_VALUE ;

        }

        @Override
        public void run() {
            this.myThread = Thread.currentThread();
            timing = timing(url);
        }
    }

    public static void analyze(int times, String url){
        List<IThread> pool = IThread.makePool(times,url);
        // wait for all to complete execution :: poll()
        while ( true ){
            boolean oneAlive = false ;
            for ( IThread t : pool ){
                oneAlive |= t.myThread.isAlive();
            }
            if ( !oneAlive ){ break; }
        }
        List<Double> data = new ArrayList<>();
        for ( IThread t : pool ){
            data.add(t.timing);
        }
        // now we have the data, do mean, sd, what not...
        Collections.sort(data);
        int _90 = (int)Math.floor(times * 0.9);
        System.out.printf("90th Percentile is : %f", data.get(_90 ) );
    }

    public static void main(String[] args){
        analyze(20, "http://en.wikipedia.org/wiki/Main_Page" );
    }
}
