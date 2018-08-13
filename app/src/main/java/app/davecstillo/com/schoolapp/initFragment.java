package app.davecstillo.com.schoolapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TabHost;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link initFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link initFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class initFragment extends BaseFragment {
    // TODO: Rename parameter arguments, choose names that match

    // TODO: Rename and change types of parameters
    private OnFragmentInteractionListener mListener;



    TabHost tabHost;
    LinearLayout tab1, tab2, tab3;



    public initFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment initFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static initFragment newInstance(String param1, String param2) {
        initFragment fragment = new initFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Api web
        //httpHandler.instance = new httpHandler("https://dacastest.000webhostapp.com/");


        if (getArguments() != null) {
        }
    }

    @Override
    public String getTitle() {
        return "Inicio";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_init, container, false);



        tab1 = (LinearLayout) view.findViewById(R.id.tab1);
        tab2 = (LinearLayout) view.findViewById(R.id.tab2);
        tab3 = (LinearLayout) view.findViewById(R.id.tab3);

        tabHost = (TabHost) view.findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("BOLETIN");
        spec.setContent(R.id.tab1);
        spec.setIndicator("BOLETIN");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("AVISOS");
        spec.setContent(R.id.tab2);
        spec.setIndicator("AVISOS");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("MENU");
        spec.setContent(R.id.tab3);
        spec.setIndicator("MENU");
        tabHost.addTab(spec);



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
