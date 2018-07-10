package app.davecstillo.com.schoolapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import app.davecstillo.com.schoolapp.Content.feedContent;
import app.davecstillo.com.schoolapp.Content.feedContent.feedItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class feedFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    public feedContent content = new feedContent();

    String ID, Title, Desc, imgName;
    Bitmap Img;

    ProgressDialog progressBar;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public feedFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static feedFragment newInstance(int columnCount) {
        feedFragment fragment = new feedFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_list, container, false);

        progressBar = new ProgressDialog(getContext());
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
        progressBar.setCancelable(false);
        progressBar.setMessage("Cargando....");
        progressBar.show();


        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            content.cleanList();
            callList("downloader.php", recyclerView);

        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }


    void callList(String path, RecyclerView recyclerView){

        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json,exception)-> {

        if(exception!=null){
            //Log.e("Error",exception.getMessage());
        }
        if(json!=null){
            JsonArray feed = json.getAsJsonObject().get("feed").getAsJsonArray();

            for(JsonElement res : feed.getAsJsonArray()){
                String ID, Titulo, Descripcion, imgName;



                ID = res.getAsJsonObject().get("ID").getAsString();
                Titulo = res.getAsJsonObject().get("Titulo").getAsString();
                Descripcion = res.getAsJsonObject().get("Descripcion").getAsString();
                imgName = res.getAsJsonObject().get("Imagen").getAsString();

                setVars(ID, Titulo, Descripcion, imgName);


                onInfoFetched(recyclerView);
            }

        }else{
            progressBar.hide();
        }


        }).execute();




    }

    void setImagen2(String imgName){

        new BackgroundTask<Bitmap>(()-> httpHandler.instance.getImage("uploads/"+imgName), (image,exc)->{
            if(exc!=null){
                Log.e("Error"," al traer la imagen "+ exc.getMessage());
            }
            if(image!=null) {
                Log.d("Imagen","recogida");
                Bitmap map = image;
                loadImage(map);
            }
        }).execute();

    }

    void loadImage(Bitmap bitmap){
            this.Img = bitmap;
    }

    void setVars(String ID, String Titulo,String Descripcion, String imgName){
        this.ID = ID;
        this.Title = Titulo;
        this.Desc = Descripcion;
        this.imgName = imgName;
    }

    void onInfoFetched(RecyclerView recyclerView){
        progressBar.hide();
        content.addItem(content.createFeedItem(this.ID,this.Title,this.Desc, this.imgName));

        recyclerView.setAdapter(new MyfeedRecyclerViewAdapter(feedContent.ITEMS, mListener));
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(feedItem item);
    }
}
