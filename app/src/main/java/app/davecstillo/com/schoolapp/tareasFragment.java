package app.davecstillo.com.schoolapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.JsonElement;

import app.davecstillo.com.schoolapp.Content.alumnosContent;
import app.davecstillo.com.schoolapp.Content.tareasContent;
import app.davecstillo.com.schoolapp.Content.tareasContent.tarea;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class tareasFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private alumnosContent.alumno alumno;
    tareasContent content = new tareasContent();
    ProgressDialog progressBar;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public tareasFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static tareasFragment newInstance(int columnCount) {
        tareasFragment fragment = new tareasFragment();
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
        View view = inflater.inflate(R.layout.fragment_tareas_list, container, false);

        progressBar = new ProgressDialog(getContext());
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setIndeterminate(true);
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
            StringBuilder ptch = new StringBuilder("getTareas.php?grado=");
            ptch.append(alumno.grado);

            callList(ptch.toString(), recyclerView);
        }

        return view;
    }


    public void callList(String path, RecyclerView recyclerView){
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, ex)->{
            if(ex!=null){

            }
            if(json!=null){
                for(JsonElement res : json.getAsJsonObject().get("Tarea").getAsJsonArray()){
                    int ID, codMaestro;
                    String Materia, Tarea, fechaEm, fechaEn;

                    JsonElement elm;

                    elm = res.getAsJsonObject().get("ID"); ID = elm.getAsInt();
                    elm = res.getAsJsonObject().get("codigo_Catedratico"); codMaestro = elm.getAsInt();
                    elm = res.getAsJsonObject().get("Materia"); Materia = elm.getAsString();
                    elm = res.getAsJsonObject().get("Tarea"); Tarea = elm.getAsString();
                    elm = res.getAsJsonObject().get("Fecha_Emision"); fechaEm = elm.getAsString();
                    elm = res.getAsJsonObject().get("Fecha_Entrega"); fechaEn = elm.getAsString();

                    content.addItem(content.createTarea(ID, codMaestro,Materia,Tarea,fechaEm,fechaEn));
                }
                onInfoFetched(content, recyclerView);
            }else{
                progressBar.hide();
                Toast.makeText(getContext(),"No hay tareas disponibles",Toast.LENGTH_LONG).show();
            }




        }).execute();

    }




    public void onInfoFetched(tareasContent content, RecyclerView recyclerView){
        progressBar.hide();
        recyclerView.setAdapter(new tareasRecyclerViewAdapter(content.ITEM, mListener));
    }


    public void setAlumno(alumnosContent.alumno alumno){
        this.alumno = alumno;
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
        void onListFragmentInteraction(tarea item);
    }
}
