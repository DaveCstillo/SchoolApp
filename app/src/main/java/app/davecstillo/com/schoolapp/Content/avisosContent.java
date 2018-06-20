package app.davecstillo.com.schoolapp.Content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class avisosContent{

    public List<avisos> ITEM = new ArrayList<avisos>();

    public Map<String, avisos> ITEM_MAP = new HashMap<String, avisos>();



    static {

    }

    public void addItem(avisos aviso){
        ITEM.add(aviso);
        ITEM_MAP.put(String.valueOf(aviso.ID),aviso);
    }

    public avisos createAviso(int ID, String Titulo, String Descripcion){
        return new avisos(ID,Titulo,Descripcion);
    }


    public class avisos{
        public final int ID;
        public final String Titulo, Descripcion;

        public avisos(int ID, String titulo, String descripcion) {
            this.ID = ID;
            this.Titulo = titulo;
            this.Descripcion = descripcion;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
