package app.davecstillo.com.schoolapp;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.davecstillo.com.schoolapp.Content.feedContent;


/**
 * A simple {@link Fragment} subclass.
 */
public class feedNotice extends BaseFragment {


    private feedContent.feedItem Noticia;

    public ImageView img;
    public TextView titulo, descripcion;


    public feedNotice() {
        // Required empty public constructor
    }

    public void setItem(feedContent.feedItem item){
        this.Noticia = item;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed_notice, container, false);

        img = (ImageView) view.findViewById(R.id.cuadroNIMG);
        titulo = (TextView) view.findViewById(R.id.Ntitulo);
        descripcion = (TextView) view.findViewById(R.id.Ndescripcion);

        llenarNoticia();
        return view;
    }

    public void llenarNoticia(){

       ponerImagen(Noticia.getimgName());
        img.setContentDescription(Noticia.getimgName());
        titulo.setText(Noticia.Titulo);

        descripcion.setText(Noticia.Descripcion);


    }

    void ponerImagen(String imgurl){
        new BackgroundTask<Bitmap>(() -> Imagenes.get(imgurl), (b, e) ->
        {
            if (e != null) {
                Log.d("Exception",e.getMessage()); throw new RuntimeException(e);}

            if(b!=null) {
                Log.d("Imagen","puesta");
                img.setImageBitmap(b);
            }


        }).execute();
    }


}
