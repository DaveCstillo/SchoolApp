package app.davecstillo.com.schoolapp;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.JsonElement;


/**
 * A simple {@link Fragment} subclass.
 */
public class cafeteria extends BaseFragment {


    ImageView cartel;
    public cafeteria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_cafeteria, container, false);

        cartel = (ImageView) view.findViewById(R.id.cafeteriaMenu);

        callList("getMenu.php");

        return view;
    }


    public void callList(String path){
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, ex)->{
            if(ex!=null){

            }
            if(json!=null){
                for(JsonElement res : json.getAsJsonObject().get("Menu").getAsJsonArray()){
                    JsonElement elm;

                    elm = res.getAsJsonObject().get("Imagen");

                    putImg(elm.getAsString());
                }
            }



        }).execute();
    }


    public void putImg(String imgName){

        new BackgroundTask<Bitmap>(()->Imagenes.get(imgName), (b, ex)->{
            if(ex!=null){

            }
            if(b!=null){
                cartel.setImageBitmap(b);
            }



        }).execute();


    }

}
