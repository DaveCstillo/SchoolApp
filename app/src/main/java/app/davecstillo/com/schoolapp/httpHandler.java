package app.davecstillo.com.schoolapp;


import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Entity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class httpHandler {

    private String url;
    JsonParser parser;

    public static httpHandler instance;

    public httpHandler(String url){
        this.url = url;
    }

    public  String post(final String posturl) throws Exception{
        final String text;
        Log.d("path:",posturl);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {

                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(posturl);

                    HttpResponse resp = httpClient.execute(httpPost);
                    HttpEntity entity = resp.getEntity();




                } catch (Exception e) {e.printStackTrace();}
            }
        });

        thread.start();
        return "Nada we";
    }

    public JsonElement getJson(String path) throws Exception
    {
        Log.d("path", path);
        StringBuffer chaine = new StringBuffer("");
        URL url = new URL(this.url + path);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestProperty("User-Agent", "");
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while ((line = rd.readLine()) != null) {
            chaine.append(line);
        }
        Log.d("Json", chaine.toString());
        Log.d("Json2", String.valueOf(parser.parse(chaine.toString())));
        return parser.parse(chaine.toString());
    }
}
