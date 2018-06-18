package app.davecstillo.com.schoolapp.Content;

import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.davecstillo.com.schoolapp.BackgroundTask;
import app.davecstillo.com.schoolapp.httpHandler;

public class reportContent {

    public static List<reporte> ITEM = new ArrayList<reporte>();

    public static final Map<String, reporte> ITEM_MAP = new HashMap<String, reporte>();

    static {

    }



    public static void addItem(reporte item){
        ITEM.add(item);
        ITEM_MAP.put(String.valueOf(item.ID),item);
    }

    public static reporte createReporte(int ID, String Causal, String Descripcion, String fecha){
        return new reporte(ID,Causal,Descripcion, fecha);
    }







    public static class reporte{

        public int ID;
        public String causal, descripcion, fecha;

        public reporte(int ID, String causal, String descripcion, String fecha) {
            this.ID = ID;
            this.causal = causal;
            this.descripcion = descripcion;
            this.fecha = fecha;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }



}
