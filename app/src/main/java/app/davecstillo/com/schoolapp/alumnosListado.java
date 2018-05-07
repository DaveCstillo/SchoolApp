package app.davecstillo.com.schoolapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.davecstillo.com.schoolapp.Content.alumnosContent;

/**
 * Created by DELL on 28/02/2018.
 */

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link alumnosListado.OnListFragmentInteractionListener}
 * interface.
 */

public class alumnosListado extends BaseFragment {

    private OnListFragmentInteractionListener mListener;
    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    private httpHandler handler = new httpHandler();

    private int fragmentID;


    public static alumnosListado newInstance(int columnCount) {
        alumnosListado fragment = new alumnosListado();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public String getTitle() {
        return "Calificaciones";
    }

    public void setFID(int id){
        fragmentID=id;
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        String txt = handler.post("https://dacastest.000webhostapp.com/app.php");
        Log.d("Metodo Post", txt);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_alumnos_content,container,false);
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new alumnosRecyclerView(alumnosContent.ITEM, mListener));
        }
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(alumnosContent.alumno item);
    }
}
