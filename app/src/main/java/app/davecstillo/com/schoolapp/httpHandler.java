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

    private String url;


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

                    text = EntityUtils.toString(entity);


                } catch (Exception e) {e.printStackTrace();}
            }
        });

        thread.start();

    }
}
