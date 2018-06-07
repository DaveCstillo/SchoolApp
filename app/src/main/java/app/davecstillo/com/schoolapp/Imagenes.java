package app.davecstillo.com.schoolapp;

import android.graphics.Bitmap;
import android.util.JsonReader;
import android.util.Log;

import com.google.gson.JsonElement;

import java.util.HashMap;

public class Imagenes
    {
        public static httpHandler imagesApi;
        public static HashMap<String, Bitmap> images = new HashMap<>();

        public static void init()
        {
            Imagenes.imagesApi = new httpHandler("https://dacastest.000webhostapp.com/uploads/");
        }

        public static Bitmap get(String name) throws Exception
        {
            if (images.containsKey(name))
                return images.get(name);

            Bitmap b = imagesApi.getImage(name);
            images.put(name, b);
            return b;
        }
    }

