package app.davecstillo.com.schoolapp;


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Entity;

public class httpHandler {

    public  String post(String posturl){

        Log.d("path:",posturl);
        try{

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(posturl);

            HttpResponse resp = httpClient.execute(httpPost);
            HttpEntity entity = resp.getEntity();

            String text = EntityUtils.toString(entity);

            return text;

        }catch(Exception e){return "error";}
    }
}
