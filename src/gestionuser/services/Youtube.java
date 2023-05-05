/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser.services;

/**
 *
 * @author asus
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;


public class Youtube {
 
    private String URL = "https://youtube.googleapis.com/youtube/v3/search?part=snippet&maxResults=10&q=%s&type=video&key=%s";
    private String youtube_key = "AIzaSyA4FwiZlJGmQlC8YQ16-zWGu78ifVJhfMw";
    private String attr = "title";
    private String fURL;
    private URL url;
    private HttpURLConnection httpURLConnection;
    private BufferedReader in;
    private StringBuffer response;
    private String inputLine;

    public Youtube() {
        this.fURL = String.format(URL, "%s", youtube_key);
    }

   public List<String> fetchResult(String query) throws IOException {
    List<String> resultList = new ArrayList<>();
    try {
        if (query.equals("")) {
            return resultList;
        }
        query = query.replaceAll(" ", "%20");

        url = new URL(String.format(fURL, query));
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray items = jsonResponse.getJSONArray("items");
        for(int i=0;i<items.length();i++)
        {
            JSONObject item = items.getJSONObject(i);
            JSONObject id = item.getJSONObject("id");
            String videoId = id.getString("videoId");
            String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
            resultList.add(videoUrl);
        }
    } catch (MalformedURLException ex) {
        System.out.println(ex.getMessage());
    }
    return resultList;
}
   public String fetchRandomVideo(String query) throws IOException {
    try {
        if (query.equals("")) {
            return "";
        }
        query = query.replaceAll(" ", "%20");

        url = new URL(String.format(fURL, query));
        httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        JSONObject jsonResponse = new JSONObject(response.toString());
        JSONArray items = jsonResponse.getJSONArray("items");
        int numItems = items.length();
        if (numItems == 0) {
            return "";
        }
        int randomIndex = (int) (Math.random() * numItems);
        JSONObject item = items.getJSONObject(randomIndex);
        JSONObject id = item.getJSONObject("id");
        String videoId = id.getString("videoId");
        String videoUrl = "https://www.youtube.com/watch?v=" + videoId;
        return videoUrl;
    } catch (MalformedURLException ex) {
        System.out.println(ex.getMessage());
    }
    return "";
}


}
  

