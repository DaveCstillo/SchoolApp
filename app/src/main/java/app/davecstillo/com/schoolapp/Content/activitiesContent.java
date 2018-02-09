package app.davecstillo.com.schoolapp.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 8/02/2018.
 */

public class activitiesContent {

    public static final List<dayItem> ITEMS = new ArrayList<dayItem>();
    public static final Map<String, dayItem> ITEM_MAP = new HashMap<String, dayItem>();




    public static void addItem(dayItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.nombreDia,item);
    }


/*
    public static dayItem createDayItem(int day, String nombre, String contenido){


        return 0;
    }
*/

    public static class dayItem{
        public final int fechaDia;
        public final String nombreDia;
        public final String contenido;

        public dayItem( int fechaDia, String nombreDia, String contenido) {
            this.fechaDia = fechaDia;
            this.nombreDia = nombreDia;
            this.contenido = contenido;
        }

        @Override
        public String toString() {
            return nombreDia;
        }
    }
}
