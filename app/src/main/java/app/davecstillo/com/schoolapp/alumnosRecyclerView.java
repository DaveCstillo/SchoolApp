package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import app.davecstillo.com.schoolapp.Content.alumnosContent;
import app.davecstillo.com.schoolapp.Content.alumnosContent.alumno;
import app.davecstillo.com.schoolapp.alumnosListado.OnListFragmentInteractionListener;

/**
 * Created by DELL on 8/02/2018.
 */

/**
 * {@link RecyclerView.Adapter} that can display a {@link alumnosContent} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */

public class alumnosRecyclerView extends RecyclerView.Adapter<alumnosRecyclerView.ViewHolder> {


    private List<alumno> mValues;
    private final OnListFragmentInteractionListener mListener;

    ViewHolder holder;

    public alumnosRecyclerView(List<alumno> mValues, OnListFragmentInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
        Log.d("mValues",String.valueOf(mValues));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.alumnoslistado,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            Log.e("MITEM","Item" + holder.mItem);
            holder.button.setText(mValues.get(position).toString());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(null!= mListener){
                        mListener.onListFragmentInteraction(holder.mItem);
                    }
                }
            });

        this.holder = holder;
    }


    public void changeTexts(){
        holder.button.setText(mValues.toString());

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Button button;
        public alumno mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            button = (Button) view.findViewById(R.id.alumnoBtn);
        }

    }

}
