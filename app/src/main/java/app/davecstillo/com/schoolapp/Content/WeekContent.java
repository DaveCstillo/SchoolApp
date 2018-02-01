package app.davecstillo.com.schoolapp.Content;

import java.util.ArrayList;
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

    static {
        //To add some items
        for(int i=1;i<=COUNT;i++){
            addItem(createWeekItem(i));
        }
    }

    private static void addItem(WeekItem item){
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static WeekItem createWeekItem(int position){
        return new WeekItem(String.valueOf(position),selectDay(position),String.valueOf(position));
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


    /*
     *WeekItem Class
     *
     */

    public static class WeekItem{
        public final String id;
        public final String day;
        public final String dayNo;

        public WeekItem(String id, String day, String dayNo){
            this.id = id;
            this.day = day;
            this.dayNo = dayNo;
        }

        @Override
        public String toString() { return day; }
    }


}
