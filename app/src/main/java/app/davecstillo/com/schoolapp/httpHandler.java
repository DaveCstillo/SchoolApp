package app.davecstillo.com.schoolapp;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

    public String user;

    private String url;
    JsonParser parser;

    public static httpHandler instance;

    public httpHandler(String url){
        this.url = url;
        parser = new JsonParser();
    }


    public JsonElement getJson(String path) throws Exception
    {
        Log.i("path", path);
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
        Log.i("Json", chaine.toString());
        Log.i("Json2", String.valueOf(parser.parse(chaine.toString())));
        return parser.parse(chaine.toString());
    }

    public Bitmap getImage(String name) throws Exception
    {
        return getImage(name, true);
    }

    public Bitmap getImage(String path, boolean useBaseUrl) throws Exception
    {
        String address = useBaseUrl ? this.url + path : path;
        URL url = new URL(address);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;
    }

}
