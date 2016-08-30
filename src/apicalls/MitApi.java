/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apicalls;
 
import apicalls.Feelings.Feel;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random; 

/**
 *
 * @author ina
 */
public class MitApi {

    final static String BASE_URL = "https://www.qnt.io/api/";
    final static String PUBLICKEY = "54a309ac1c61be23aba0da3f";
    final static String METRIC = "0.7";
    final static String MODE_RANDOM = "random";
    final static String MODE_ALL = "all";
    final static public String LIMIT = "7000";
    //mode for mode_random or mode_all
    final static String QUERY = "search?pID=gifgif&mID=%s&metric_score=%s&limit=%s&skip=%d&key="; 
    final static String HAPPINESS_ID = "54a309ae1c61be23aba0da5c";
    final static String SADNESS_ID = "54a309ae1c61be23aba0da60";
    final static String AMUSEMENT_ID = "54a309ae1c61be23aba0da53";
    final static String ANGER_ID = "54a309ae1c61be23aba0da54";
    final static String CONTEMPT_ID = "54a309ae1c61be23aba0da55";
    final static String CONTENTMENT_ID = "54a309ae1c61be23aba0da56";
    final static String DISGUST_ID = "54a309ae1c61be23aba0da57";
    final static String EMBARRASSMENT_ID = "54a309ae1c61be23aba0da58";
    final static String EXCITEMENT_ID = "54a309ae1c61be23aba0da59";
    final static String FEAR_ID = "54a309ae1c61be23aba0da5a";
    final static String GUILT_ID = "54a309ae1c61be23aba0da5b";
    final static String PLEASURE_ID = "54a309ae1c61be23aba0da5d";
    final static String PRIDE_ID = "54a309ae1c61be23aba0da5e";
    final static String RELIEF_ID = "54a309ae1c61be23aba0da5f";
    final static String SATISFACTION_ID = "54a309ae1c61be23aba0da61";
    Feel activeFeel;
    public int count=0;

    public MitApi() {
        this.count=0;
    }

    private String formatUrl(String type) {  
        String url = String.format(BASE_URL + QUERY + PUBLICKEY, type, METRIC, LIMIT, count);
        System.out.println(url);
        return url;
    } 
    
    public String getIdFromEnum(Feelings.Feel randomFeel) {
        switch (randomFeel) {
            case HAPPINESS:
                System.out.println("HAPPINESS_ID.");
                activeFeel = Feel.HAPPINESS;
                return HAPPINESS_ID + "";
            case SADNESS:
                System.out.println("SADNESS_ID.");
                activeFeel = Feel.SADNESS;
                return SADNESS_ID + "";
            case AMUSEMENT:
                System.out.println("AMUSEMENT_ID.");
                activeFeel = Feel.AMUSEMENT;
                return AMUSEMENT_ID + "";
            case ANGER:
                System.out.println("ANGER_ID.");
                activeFeel = Feel.ANGER;
                return ANGER_ID + "";
            case PRIDE:
                System.out.println("PRIDE_ID.");
                activeFeel = Feel.PRIDE;
                return PRIDE_ID + "";
            case SATISFACTION:
                System.out.println("SATISFACTION_ID.");
                activeFeel = Feel.SATISFACTION;
                return SATISFACTION_ID + "";
            case RELIEF:
                System.out.println("RELIEF_ID.");
                activeFeel = Feel.RELIEF;
                return RELIEF_ID + "";
            case PLEASURE:
                System.out.println("PLEASURE_ID.");
                activeFeel = Feel.PLEASURE;
                return PLEASURE_ID + "";
            case GUILT:
                System.out.println("GUILT_ID.");
                activeFeel = Feel.GUILT;
                return GUILT_ID + "";
            case FEAR:
                System.out.println("FEAR_ID.");
                activeFeel = Feel.FEAR;
                return FEAR_ID + "";
            case EXCITEMENT:
                System.out.println("EXCITEMENT_ID.");
                activeFeel = Feel.EXCITEMENT;
                return EXCITEMENT_ID + "";
            case EMBARRASSMENT:
                System.out.println("EMBARRASSMENT_ID.");
                activeFeel = Feel.EMBARRASSMENT;
                return EMBARRASSMENT_ID + "";
            case DISGUST:
                System.out.println("DISGUST_ID.");
                activeFeel = Feel.DISGUST;
                return DISGUST_ID + "";
            case CONTENTMENT:
                System.out.println("CONTENTMENT_ID.");
                activeFeel = Feel.CONTENTMENT;
                return CONTENTMENT_ID + "";
            case CONTEMPT:
                System.out.println("CONTEMPT_ID.");
                activeFeel = Feel.CONTEMPT;
                return CONTEMPT_ID + "";

        }
        return "";
    }

    public String getQueryFromID(String id) {
        return getQuery(id);
    } 

    public String getQuery(String value) {
        return formatUrl(value);
    }

    public String sadQuery() {
        activeFeel = Feel.SADNESS;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String contemptQuery() {
        activeFeel = Feel.CONTEMPT;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String angetQuery() {
        activeFeel = Feel.ANGER;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String amusementQuery() {
        activeFeel = Feel.AMUSEMENT;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String satisfactionQuery() {
        activeFeel = Feel.SATISFACTION;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String reliefQuery() {
        activeFeel = Feel.RELIEF;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String prideQuery() {
        activeFeel = Feel.PRIDE;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String pleasureQuery() {
        activeFeel = Feel.PLEASURE;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String guiltQuery() {
        activeFeel = Feel.GUILT;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String fearQuery() {
        activeFeel = Feel.FEAR;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String excitementQuery() {
        activeFeel = Feel.EXCITEMENT;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String embarrassmentQuery() {
        activeFeel = Feel.EMBARRASSMENT;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String disgustQuery() {
        activeFeel = Feel.DISGUST;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String contentmentQuery() {
        activeFeel = Feel.CONTEMPT;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public String happyQuery() {
        activeFeel = Feel.HAPPINESS;
        return formatUrl(getIdFromEnum(activeFeel));
    }

    public JsonArray getJsonFrom(String query) throws MalformedURLException, IOException {
        URL url = new URL(query);
        JsonObject jsonMainObject = readJsonFromUrl(query);
        Random rand = new Random();
        JsonArray jsonResults = jsonMainObject.getAsJsonArray("results");
        System.out.println("Number of objecs:"+ jsonResults.size());
        System.out.println("count: "+count);
        return jsonResults;
    }

    JsonObject readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JsonObject jsonObject = new JsonParser().parse(jsonText).getAsJsonObject(); 
            return jsonObject;
        } finally {
            is.close();
        }
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    } 
}
