package app.davecstillo.com.schoolapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import app.davecstillo.com.schoolapp.Content.GradesContent;
import app.davecstillo.com.schoolapp.Content.alumnosContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class gradesFragment extends BaseFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;


    public alumnosContent.alumno alumno;
    public  GradesContent content = new GradesContent();



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public gradesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static gradesFragment newInstance(int columnCount) {
        gradesFragment fragment = new gradesFragment();
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
        View view = inflater.inflate(R.layout.fragment_grades_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            StringBuilder path = new StringBuilder("grades.php?alumno=");
            path.append(alumno.codigoAlumno);
            path.append("&grado=");
            path.append(alumno.grado);

            String url = path.toString();
            callList(url, recyclerView);
        }
        return view;
    }

    public void callList(String path,RecyclerView recyclerView){
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, exception)->{
            if (exception!=null){
                Log.e("Error",exception.getMessage());
            }
            if(json!=null) {

                for (int i = 1; i < 3; i++) {
                    if(getClase(i)==""){
                        //Error!!!TODO: Agregar mensaje de error
                    }else {
                        JsonArray clase = json.getAsJsonObject().get(getClase(i)).getAsJsonArray();
                        Log.d("Clase", getClase(i));


                        for (JsonElement res : clase.getAsJsonArray()) {
                            int id, nota1, nota2, nota3, nota4, parcial1, parcial2, zona, exfinl;
                            String classe, grade;
                            JsonElement elm;

                            elm = res.getAsJsonObject().get("ID");id = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Nota1");nota1 = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Nota2");nota2 = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Nota3");nota3 = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Nota4");nota4 = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Parcial1");parcial1 = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Parcial2");parcial2 = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Zona");zona = elm.getAsInt();
                            elm = res.getAsJsonObject().get("Final");exfinl = elm.getAsInt();
                            classe = getClase(i);
                            grade = alumno.getGrade();


                            Log.d("Res", res.toString());
                            Log.d("Elm", elm.toString());
                            content.addItem(content.createGradeItem(id,classe,nota1,nota2,nota3,nota4,parcial1,parcial2,zona,exfinl,grade));
                        }
                    }
                }
                onInfoFetched(content, recyclerView);
            }

        }).execute();

    }

    public void onInfoFetched(GradesContent cont, RecyclerView recyclerView){
        recyclerView.setAdapter(new gradesRecyclerViewAdapter(GradesContent.ITEMS, mListener));
    }

    public String getClase(int x){
        switch (x){
            case 1:
                return "matematicas";
            case 2:
                return "lenguaje";
                default:
                    return "";
        }
    }


    public void setAlumno(alumnosContent.alumno alumno) {
        this.alumno = alumno;
    }

    @Override
    public String getTitle() {
        return alumno.toString();
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
        void onListFragmentInteraction(GradesContent.GradeItem item);
    }
}
