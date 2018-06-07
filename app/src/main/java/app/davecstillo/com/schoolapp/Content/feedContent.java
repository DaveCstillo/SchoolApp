package app.davecstillo.com.schoolapp.Content;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class feedContent {

    public static final List<feedItem> ITEMS = new ArrayList<feedItem>();

    public static final HashMap<String, feedItem> ITEM_MAP = new HashMap<String, feedItem>();

    static{

    }

    public void addItem(feedItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.ID, item);
    }



    public feedItem createFeedItem(String ID, String titulo, String descripcion, String imagen){
        return new feedItem(ID,titulo,descripcion,imagen);
    }



    public class feedItem{
        public final String ID, Titulo, Descripcion, imgName;
        Bitmap Imagen;

        public feedItem(String ID, String titulo, String descripcion, String imagen) {
            this.ID = ID;
            Titulo = titulo;
            Descripcion = descripcion;
            imgName = imagen;
        }

        @Override
        public String toString() {
            return super.toString();
        }

        public String getimgName(){
            return imgName;
        }

        public Bitmap getImage(){
            return Imagen;
        }
    }

}

