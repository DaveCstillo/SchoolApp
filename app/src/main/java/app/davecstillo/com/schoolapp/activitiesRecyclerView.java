package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import app.davecstillo.com.schoolapp.Content.activitiesContent.*;
import app.davecstillo.com.schoolapp.activities.*;

/**
 * Created by DELL on 8/02/2018.
 */

public class activitiesRecyclerView extends RecyclerView.Adapter<activitiesRecyclerView.ViewHolder> {


    private final List<dayItem> mValues;
    private final OnFragmentInteractionListener mListener;

    public activitiesRecyclerView(List<dayItem> mValues, OnFragmentInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activities,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mDayName.setText("Que pedo wee");
            holder.mDayDate.setText("Ahhh pos mira");
            holder.Content.setText("LooooooooL");

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(null!= mListener){
                        mListener.onFragmentInteraction(holder.mItem);
                    }
                }
            });

    }

    @Override
    public int getItemCount() {
        return 0;
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
