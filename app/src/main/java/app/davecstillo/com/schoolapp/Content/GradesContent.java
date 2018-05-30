package app.davecstillo.com.schoolapp.Content;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import app.davecstillo.com.schoolapp.BaseFragment;

/**
 * Created by David on 2/1/2018.
 */

public class GradesContent extends BaseFragment {


    public static final List<GradeItem> ITEMS = new ArrayList<GradeItem>();

    public static final Map<String, GradeItem> ITEM_MAP = new HashMap<String, GradeItem>();

    private static final int COUNT = 5;


    static {
        //To add some items
        for(int i=1;i<=COUNT;i++){
            addItem(createGradeItem(i));
        }
    }

    private static void addItem(GradeItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    private static GradeItem createGradeItem(int position){
        return new GradeItem(position,selectClass(position),setGrade());
    }

    private static String selectClass(int position){
        String clase = "Recreo";
        switch (position){
            case 1: clase = "Matematica"; break;
            case 2: clase = "Historia"; break;
            case 3: clase = "Lenguaje"; break;
            case 4: clase = "Ingles"; break;
            case 5: clase = "Ciencia"; break;
        }

        return clase;
    }

    private static String setGrade() {
        String nota = "0";
        Random rand = new Random();
        int note = rand.nextInt(100);
        nota = String.valueOf(note);
        return nota;
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

    public static class GradeItem{
        public final int id;
        public final String clase;
        public int nota1, nota2,nota3,nota4;
        public int parcial1, parcial2, zona, exfinl;
        public final String grade;

        public GradeItem(int id, String day, String details){
            this.id = id;
            this.clase = day;
            this.grade = details;
        }

        @Override
        public String toString() { return clase; }
    }


}
