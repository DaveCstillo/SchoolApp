package app.davecstillo.com.schoolapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.JsonElement;

import app.davecstillo.com.schoolapp.Content.alumnosContent;
import app.davecstillo.com.schoolapp.Content.reportContent;
import app.davecstillo.com.schoolapp.dummy.DummyContent;
import app.davecstillo.com.schoolapp.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class reportfragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    private alumnosContent.alumno alumno;
    private reportContent content = new reportContent();

    private ProgressDialog progressBar;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public reportfragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static reportfragment newInstance(int columnCount) {
        reportfragment fragment = new reportfragment();
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
        View view = inflater.inflate(R.layout.fragment_report_list, container, false);

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
            StringBuilder url = new StringBuilder("reportados.php?codAlm=");
            url.append(alumno.codigoAlumno);
            callList(url.toString(), recyclerView);
        }
        return view;
    }


    public void callList(String path, RecyclerView recyclerView){
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, exception)->{


            if(exception!=null){

            }
            if(json!=null){
                if(json.getAsJsonObject().get("Reporte_Alumno")!=null){
                    Toast toast = Toast.makeText(getContext(), "No hay notas disponibles", Toast.LENGTH_LONG);
                    toast.show();
                    progressBar.hide();
                }else {
                    for (JsonElement res : json.getAsJsonObject().get("Reporte").getAsJsonArray()) {
                        int ID;
                        String Causal, Descripcion, Fecha;
                        JsonElement elm;

                        elm = res.getAsJsonObject().get("ID");ID = elm.getAsInt();
                        elm = res.getAsJsonObject().get("Causal");Causal = elm.getAsString();
                        elm = res.getAsJsonObject().get("Descripcion");Descripcion = elm.getAsString();
                        elm = res.getAsJsonObject().get("Fecha_reporte");Fecha = elm.getAsString();

                        Log.d("RES","Reportes result: " + res.toString());

                        content.addItem(content.createReporte(ID,Causal,Descripcion, Fecha));

                    }
                    onInfoFetched(content, recyclerView);
                }
            }



        }).execute();

    }


    public void onInfoFetched(reportContent contenido, RecyclerView recyclerView){
        progressBar.hide();
        recyclerView.setAdapter(new reportItemRecyclerViewAdapter(contenido.ITEM, mListener));
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
        void onListFragmentInteraction(reportContent.reporte item);
    }
}
