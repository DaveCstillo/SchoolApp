package app.davecstillo.com.schoolapp.Content;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import app.davecstillo.com.schoolapp.BackgroundTask;
import app.davecstillo.com.schoolapp.httpHandler;

/**
 * Created by DELL on 28/02/2018.
 */

public class alumnosContent {

    public static List<alumno> ITEM = new ArrayList<alumno>();

    public static final Map<String, alumno> ITEM_MAP = new HashMap<String, alumno>();
    private static final int COUNT = 5;

    static {
        String user = httpHandler.instance.user;
        user = user.replace("\"","");
        user = user.replace(" ","%20");
        StringBuilder path = new StringBuilder("app.php?usser=");
        path.append(user);

        String url = path.toString();

        callList(url);
        // Add some sample items.
//        for (int i = 1; i <= COUNT; i++) {
//            addItem(createAlumno(i));
//        }
    }

    private static void addItem(alumno item) {
        ITEM.add(item);
        ITEM_MAP.put(String.valueOf(item.ID), item);
    }

    private static alumno createAlumno(int ID, int codigoAlumno, String nombres, String apellidos, String grado, String edad) {
        return new alumno(ID, codigoAlumno, nombres, apellidos, grado, edad);
    }


    public static void callList(String path){
        new BackgroundTask<JsonElement>(() -> httpHandler.instance.getJson(path), (json, exception)->
        {
            if(exception!=null){
                Log.d("Error",exception.getMessage());
            }
            if(json!=null){
                Log.d("Exito",json.toString());
                for(JsonElement res : json.getAsJsonObject().get("Alumnos").getAsJsonArray()) {
                    JsonElement obj;
                    int ID, codigoAlumno;
                    String nombres, apellidos, grado, edad;
                    obj = res.getAsJsonObject().get("ID");
                    ID = obj.getAsInt();
                    obj = res.getAsJsonObject().get("Codigo_alumno");
                    codigoAlumno = obj.getAsInt();
                    obj = res.getAsJsonObject().get("Nombres");
                    nombres = obj.getAsString();
                    obj = res.getAsJsonObject().get("Apellidos");
                    apellidos = obj.getAsString();
                    obj = res.getAsJsonObject().get("Grado");
                    grado = obj.getAsString();
                    obj = res.getAsJsonObject().get("Edad");
                    edad = obj.getAsString();

                    addItem(createAlumno(ID,codigoAlumno,nombres,apellidos,grado,edad));

                    Log.d("RES", res.toString());
                    Log.d("obj",obj.toString());

                    JsonObject obj2 = res.getAsJsonObject();
                    Log.d("obj2",obj2.toString());
                }
            }
        }).execute();
    }


    public alumnosContent() {
    }

    public static class alumno{
        public int ID, codigoAlumno;
        public String nombres, apellidos;
        public String grado;
        public String edad;


//        public alumno(int idEstudiante) {
//            this.idEstudiante = idEstudiante;
//        }


        public alumno(int ID, int codigoAlumno, String nombres, String apellidos, String grado, String edad) {
            this.ID = ID;
            this.codigoAlumno = codigoAlumno;
            this.nombres = nombres;
            this.apellidos = apellidos;
            this.grado = grado;
            this.edad = edad;
        }

        public String getDesc(){
            return nombres+" "+apellidos+" Grado: "+grado;
        }

        public String getGrade(){
            return grado;
        }

        @Override
        public String toString() {
            return codigoAlumno+": "+nombres;
        }

    }
}
