package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.List;

import app.davecstillo.com.schoolapp.Content.activitiesContent;
import app.davecstillo.com.schoolapp.Content.activitiesContent.*;
import app.davecstillo.com.schoolapp.activities.*;

/**
 * Created by DELL on 8/02/2018.
 */

/**
 * {@link RecyclerView.Adapter} that can display a {@link activitiesContent} and makes a call to the
 * specified {@link OnFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */

public class activitiesRecyclerView extends RecyclerView.Adapter<activitiesRecyclerView.ViewHolder> {


    private dayItem mValues;
    private final OnFragmentInteractionListener mListener;

    ViewHolder holder;

    public activitiesRecyclerView(dayItem mValues, OnFragmentInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
        Log.d("mValues",String.valueOf(mValues.getFechaDia()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activities,parent,false);
        mValues.showData();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues;
            Log.e("MITEM","Item" + holder.mItem);
            holder.mDayName.setText(mValues.getFechaDia());
            holder.mDayDate.setText(mValues.getNombreDia());
            holder.Content.setText(mValues.getContenido());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(null!= mListener){
                        mListener.onFragmentInteraction(holder.mItem);
                    }
                }
            });

        this.holder = holder;
    }


    public void changeTexts(){
        holder.mDayName.setText(mValues.getFechaDia());
        holder.mDayDate.setText(mValues.getNombreDia());
        holder.Content.setText(mValues.getContenido());
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        public final TextView mDayDate;
        public final TextView mDayName;
        public final TextView Content;
        public dayItem mItem;

        public ViewHolder(View view){
            super(view);
            mView=view;
            mDayDate = (TextView) view.findViewById(R.id.fechadia);
            mDayName = (TextView) view.findViewById(R.id.nombredia);
            Content = (TextView) view.findViewById(R.id.contenido);
        }

        @Override
        public String toString() {
            return super.toString() + " '"+mDayName.getText()+"' ";
        }
    }

}
