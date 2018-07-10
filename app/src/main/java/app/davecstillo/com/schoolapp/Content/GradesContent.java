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
import app.davecstillo.com.schoolapp.httpHandler;

/**
 * Created by David on 2/1/2018.
 */

public class GradesContent extends BaseFragment {


    public static final List<GradeItem> ITEMS = new ArrayList<GradeItem>();

    public static final Map<String, GradeItem> ITEM_MAP = new HashMap<String, GradeItem>();

    private static final int COUNT = 5;


    static {
//
//        //To add some items
//        for(int i=1;i<=COUNT;i++){
//            addItem(createGradeItem(i));
//        }
    }

    public void cleanList(){
        ITEMS.clear();
        ITEM_MAP.clear();
    }


    public void addItem(GradeItem item){
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.id), item);
    }

    public GradeItem createGradeItem(int position, String clase, int nota1,int nota2, int nota3, int nota4, int parcial1, int parcial2, int zona, int exfinal, String grade){
        return new GradeItem(position,clase,nota1,nota2,nota3,nota4,parcial1,parcial2,zona,exfinal,grade);
    }



    /*
     *GradeItem Class
     *
     */

    public static class GradeItem{
        public final int id;
        public final String clase;
        public int nota1, nota2,nota3,nota4;
        public int parcial1, parcial2, zona, exfinl;
        public final String grade;

        public GradeItem(int id, String clase, int nota1, int nota2, int nota3, int nota4, int parcial1, int parcial2, int zona, int exfinl, String grade) {
            this.id = id;
            this.clase = clase;
            this.nota1 = nota1;
            this.nota2 = nota2;
            this.nota3 = nota3;
            this.nota4 = nota4;
            this.parcial1 = parcial1;
            this.parcial2 = parcial2;
            this.zona = zona;
            this.exfinl = exfinl;
            this.grade = grade;
        }

        @Override
        public String toString() { return clase; }
    }


}
