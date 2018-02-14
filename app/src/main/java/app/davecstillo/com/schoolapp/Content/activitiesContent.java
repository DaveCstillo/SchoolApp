package app.davecstillo.com.schoolapp.Content;


import android.util.Log;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 8/02/2018.
 */

public class activitiesContent {

    public static final List<dayItem> ITEMS = new ArrayList<dayItem>();
    public static final Map<String, dayItem> ITEM_MAP = new HashMap<String, dayItem>();

    public static SimpleDateFormat DayNoformat = new SimpleDateFormat("dd-MM");
    public static SimpleDateFormat DayNameformat = new SimpleDateFormat("E");



   static {
       Calendar cal;
       cal = Calendar.getInstance();

       Log.e("DayNoFormat","Format: "+DayNoformat.format(cal.getTime()));
       Log.e("DayNameFormat","Format: "+DayNameformat.format(cal.getTime()));

       String diaNo = DayNoformat.format(cal.getTime());
       String diaName = DayNameformat.format(cal.getTime());
       addItem(createDayItem(diaNo,diaName,"Este dia esta deawebo xD"));
       Log.d("Create Item","Item creado");
   }



    public static void addItem(dayItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.nombreDia,item);
        Log.d("AddItem","Item añadido");
    }



    public static dayItem createDayItem(String day, String nombre, String contenido){
        return new dayItem(1,day,nombre,contenido);
    }


    public static class dayItem{
        private final int id;
        public final String fechaDia;
        public final String nombreDia;
        public final String contenido;

        public dayItem(int id, String fechaDia, String nombreDia, String contenido) {
            this.id = id;
            this.fechaDia = fechaDia;
            this.nombreDia = nombreDia;
            this.contenido = contenido;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }
}
