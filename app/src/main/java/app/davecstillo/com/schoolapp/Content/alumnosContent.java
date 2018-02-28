package app.davecstillo.com.schoolapp.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by DELL on 28/02/2018.
 */

public class alumnosContent {

    public static List<alumno> ITEM = new ArrayList<alumno>();

    public static final Map<String, alumno> ITEM_MAP = new HashMap<String, alumno>();
    private static final int COUNT = 5;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createAlumno(i));
        }
    }

    private static void addItem(alumno item) {
        ITEM.add(item);
        ITEM_MAP.put(String.valueOf(item.idEstudiante), item);
    }

    private static alumno createAlumno(int position) {
        return new alumno(position,generateName(position),generateLastName(),generateGrade(position),generateSec());
    }

    private static String generateName(int position){
        switch (position){
            case 1:
                return "Carlos";
            case 2:
                return "Aida";
            case 3:
                return "Juan";
            case 4:
                return "Andrea";
            case 5:
            default:
                return "Andy";

        }
    }

    private static String generateLastName(){
      return "Escobar";
    }

    private static String generateGrade(int position){
        switch (position){
            case 1:
                return "PP";
            case 2:
                return "1P";
            case 3:
                return "3P";
            case 4:
                return "2S";
            case 5:
            default:
                return "4B";

        }
    }

    private static String generateSec(){
        Random rand = new Random();
        int sec = rand.nextInt(4);
        switch (sec){
            case 1:
                default:
                return "A";
            case 2:
                return "B";
            case 3:
                return "C";
        }
    }


    public alumnosContent() {
    }

    public static class alumno{
        public int idEstudiante;
        public String nombre, apellido;
        public String grado;
        public String seccion;
//
//        public alumno(int idEstudiante) {
//            this.idEstudiante = idEstudiante;
//        }

        public alumno(int idEstudiante, String nombre, String apellido, String grado, String seccion) {
            this.idEstudiante = idEstudiante;
            this.nombre = nombre;
            this.apellido = apellido;
            this.grado = grado;
            this.seccion = seccion;
        }

        public String getDesc(){
            return nombre+" "+apellido+" Grado: "+grado+"-"+seccion;
        }

        @Override
        public String toString() {
            return apellido+", "+nombre;
        }
    }
}
