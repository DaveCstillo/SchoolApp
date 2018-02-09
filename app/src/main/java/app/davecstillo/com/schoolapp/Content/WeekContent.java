package app.davecstillo.com.schoolapp.Content;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by David on 2/1/2018.
 */

public class WeekContent {


    public static final List<WeekItem> ITEMS = new ArrayList<WeekItem>();

    public static final Map<String, WeekItem> ITEM_MAP = new HashMap<String, WeekItem>();

    private static final int COUNT = 5;

    static SimpleDateFormat sDayFormat = new SimpleDateFormat("dd");

    private Calendar calendar;

    static {
        //To add some items
        for(int i=1;i<=COUNT;i++){
            addItem(createWeekItem(i));
        }
    }

    private static void addItem(WeekItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    private static WeekItem createWeekItem(int position){
        return new WeekItem(position,selectDay(position),setDay(position));
    }

    private static String selectDay(int position){
        String dia = "Sabado";
        switch (position){
            case 1: dia = "Lunes"; break;
            case 2: dia = "Martes"; break;
            case 3: dia = "Miercoles"; break;
            case 4: dia = "Jueves"; break;
            case 5: dia = "Viernes"; break;
        }

        return dia;
    }

    private static String setDay(int position){
        Date elDia = new Date();
        Calendar calendar = Calendar.getInstance();
        String dia;
        int day;
        dia = sDayFormat.format(elDia.getDate());
        Log.d("Dia Fecha: ", String.valueOf(elDia.getDate()));
        day = elDia.getDate();
        switch (position){

            case 1:
                break;
            case 2:
                dia = sDayFormat.format(day+1);
                break;
            case 3:
                dia = sDayFormat.format(day+2);
                break;
            case 4:
                dia = sDayFormat.format(day+3);
                break;
            case 5:
                dia = sDayFormat.format(day+4);
                break;


        }

        return dia;
    }

    private static String makeDetails(int position){
        StringBuilder builder = new StringBuilder();
        builder.append("Details: ").append(position);
        for(int i=0;i<position;i++){
            builder.append("\nMore details info here.");
        }
        return builder.toString();

    }


    /*
     *WeekItem Class
     *
     */

    public static class WeekItem{
        public final int id;
        public final String day;
        public final String details;

        public WeekItem(int id, String day, String details){
            this.id = id;
            this.day = day;
            this.details = details;
        }

        @Override
        public String toString() { return day; }
    }


}
