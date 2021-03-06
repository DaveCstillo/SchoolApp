package app.davecstillo.com.schoolapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import app.davecstillo.com.schoolapp.Content.activitiesContent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link activities.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link activities#newInstance} factory method to
 * create an instance of this fragment.
 */
public class activities extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static activitiesContent ContentItem;
    static activitiesContent.dayItem ITEM;
    static TextView fechaDia, nombreDia, contenido;

    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;


    public activities() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment activities.
     */
    // TODO: Rename and change types and number of parameters
    public static activities newInstance() {
        activities fragment = new activities();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        Log.e("NOTIF", "Esta chiva va por aqui xD");
        ContentItem = new activitiesContent();
        fechaDia = (TextView) view.findViewById(R.id.fechadia);
        nombreDia = (TextView) view.findViewById(R.id.nombredia);
        contenido = (TextView) view.findViewById(R.id.contenido);
        ITEM = ContentItem.ITEM.getItem();

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onFragmentInteraction(ITEM);
                }
            });
        changeTexts();
        return view;
    }


    public static void chgTxt(CalendarView cal,int dia, int mes,int yy){
        ITEM.newItem(cal,dia,mes,yy);
        changeTexts();
    }

    public static void changeTexts(){
        fechaDia.setText(ITEM.getFechaDia());
        nombreDia.setText(ITEM.getNombreDia());
        contenido.setText(ITEM.getContenido());
    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(activitiesContent.dayItem item);
    }
}
