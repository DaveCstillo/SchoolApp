package app.davecstillo.com.schoolapp;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import java.text.SimpleDateFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link schedule_frame.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link schedule_frame#newInstance} factory method to
 * create an instance of this fragment.
 */
public class schedule_frame extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match

    // TODO: Rename and change types of parameters
    private static CalendarView calendar;
    static long selectedDate;
    private OnFragmentInteractionListener mListener;
    private View actividades;
    private activities act;


    public static SimpleDateFormat DayNoformat = new SimpleDateFormat("dd-MM");

    public schedule_frame() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment schedule_frame.
     */
    // TODO: Rename and change types and number of parameters
    public static schedule_frame newInstance() {
        schedule_frame fragment = new schedule_frame();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_schedule_frame, container, false);
        actividades = view.findViewById(R.id.activitiesFragment);

        calendar = (CalendarView) view.findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Log.w("i1","variable: "+i1);
                i1+=1;
                Log.w("i1","variable: "+i1);

                Log.w("CAL-DATE","variable: "+calendarView.getDate());
                Log.w("CAL-DATE2","variable format: "+ DayNoformat.format(calendarView.getDate()));
                if(i1<10){
                    Log.w("mes","variable: "+i1);
                    activities.chgTxt(calendarView,i2,i1,i);
                }
                else{
                    activities.chgTxt(calendarView,i2,i1,i);
                }
                Log.d("Cambio la fecha","Nueva Fecha año " + String.valueOf(i));
                Log.d("Cambio la fecha","Nueva Fecha mes " + String.valueOf(i1));
                Log.d("Cambio la fecha","Nueva Fecha dia " + String.valueOf(i2));

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

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
        void onFragmentInteraction(Uri uri);
    }
}
