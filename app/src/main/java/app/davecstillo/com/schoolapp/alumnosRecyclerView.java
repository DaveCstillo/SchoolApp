package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeoutException;

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
    private int fragmentID;

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

       // if(position<mValues.size()) {
            holder.mItem = mValues.get(position);
            Log.e("MITEM", "Item" + holder.mItem);
            holder.name.setText(mValues.get(position).toString());
            holder.grade.setText(mValues.get(position).getGrade());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (null != mListener) {
                        mListener.onListFragmentInteraction(holder.mItem);
                    }
                }
            });

            this.holder = holder;
      //  }
    }


    public void changeTexts(){
        holder.name.setText(mValues.toString());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView name;
        public TextView grade;
        public alumno mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.nameAlm);
            grade = (TextView) view.findViewById(R.id.gradeTxt);
        }

    }

}
