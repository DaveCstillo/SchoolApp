package app.davecstillo.com.schoolapp.Content;


import android.util.Log;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by DELL on 8/02/2018.
 */

public class activitiesContent {

    public static dayItem ITEM;

    public static SimpleDateFormat DayNoformat = new SimpleDateFormat("dd-MM");
    public static SimpleDateFormat DayNameformat = new SimpleDateFormat("EE");



   static {
       Calendar cal;
       cal = Calendar.getInstance();
        ITEM = new dayItem();
       Log.e("DayNoFormat", "Format: " + DayNoformat.format(cal.getTime()));
       Log.e("DayNameFormat", "Format: " + DayNameformat.format(cal.getTime()));

       String diaNo = DayNoformat.format(cal.getTime());
       String diaName = DayNameformat.format(cal.getTime());
       createDayItem(diaNo, diaName, "Este dia esta deawebo xD");
       Log.d("Create Item", "Item creado");
   }

    public static void createDayItem(String day, String nombre, String contenido){
       ITEM.addItem(day,nombre,contenido);
    }

    public static class dayItem{
        public String fechaDia = "";
        public String nombreDia = "";
        public String contenido = "";

        public dayItem(){

        }

        public void addItem(String fechaDia, String nombreDia, String contenido) {
            this.fechaDia = fechaDia;
            this.nombreDia = nombreDia;
            this.contenido = contenido;
            showData();
        }

        public void newItem(int dia, int mes){
            String date = String.valueOf(dia)+"0"+String.valueOf(mes);
            long fecha = Integer.parseInt(date);
            Log.w("fecha","variable: "+fecha);
            Date dat = new Date();
            Log.w("DAT","variable: "+dat);
            Log.d("DATOS: ","Dia: "+dia);
            Log.d("DATOS: ","Mes: "+mes);
            Log.d("DATOS: ","Date: "+date);
            String day = DayNoformat.format(dat);
            String name = DayNameformat.format(dat);
            Log.d("DATOS: ",day+name);
            String content = "Informacion del dia: "+day+"/"+name;
            addItem(day,name,content);
        }
        public void showData(){
            Log.d("ADDITEM: ", "FechaDia: "+this.fechaDia);
            Log.d("ADDITEM: ", "NombreDia: "+this.nombreDia);
            Log.d("ADDITEM: ", "Contenido: "+this.contenido);
        }

        public String getFechaDia() {
            return fechaDia;
        }

        public String getNombreDia() {
            return nombreDia;
        }

        public String getContenido() {
            return contenido;
        }

        public dayItem getItem(){
            return this;
        }

        @Override
        public String toString() {
            return fechaDia + nombreDia;
        }
    }
}
