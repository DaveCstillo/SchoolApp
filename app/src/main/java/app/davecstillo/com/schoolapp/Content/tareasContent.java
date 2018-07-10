package app.davecstillo.com.schoolapp.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tareasContent {

    public static List<tarea> ITEM = new ArrayList<tarea>();
    public static final Map<String, tarea> ITEM_MAP = new HashMap<String, tarea>();

    static {

    }

    public void cleanList(){
        ITEM.clear();
        ITEM_MAP.clear();
    }

    public void addItem(tarea hmwk){
        ITEM.add(hmwk);
        ITEM_MAP.put(String.valueOf(hmwk.id), hmwk);
    }

    public tarea createTarea(int ID, int codMaestro, String Materia, String Tarea, String fEm, String fEn){
        return  new tarea(ID,codMaestro,Materia,Tarea,fEm,fEn);
    }



    public class tarea {
        public final int id;
        public int codMaestro;
        public String Materia, Tarea, FechaEm, FechaEn;

        public tarea(int id, int codMaestro, String materia, String tarea, String fechaEm, String fechaEn) {
            this.id = id;
            this.codMaestro = codMaestro;
            Materia = materia;
            Tarea = tarea;
            FechaEm = fechaEm;
            FechaEn = fechaEn;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
