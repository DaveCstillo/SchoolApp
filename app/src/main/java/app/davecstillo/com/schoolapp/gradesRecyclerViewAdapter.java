package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import app.davecstillo.com.schoolapp.Content.GradesContent;
import app.davecstillo.com.schoolapp.Content.GradesContent.GradeItem;
import app.davecstillo.com.schoolapp.gradesFragment.OnListFragmentInteractionListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link GradesContent} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class gradesRecyclerViewAdapter extends RecyclerView.Adapter<gradesRecyclerViewAdapter.ViewHolder> {

    private final List<GradeItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    public gradesRecyclerViewAdapter(List<GradeItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_grades, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("mValues",String.valueOf(mValues.get(position)));
        holder.mItem = mValues.get(position);
        holder.mClaseBtn.setText(mValues.get(position).clase);
        holder.mNota1Pts.setText(Integer.toString(mValues.get(position).nota1));
        holder.mNota2Pts.setText(Integer.toString(mValues.get(position).nota2));
        holder.mNota3Pts.setText(Integer.toString(mValues.get(position).nota3));
        holder.mNota4Pts.setText(Integer.toString(mValues.get(position).nota4));
        holder.mParcial1Pts.setText(Integer.toString(mValues.get(position).parcial1));
        holder.mParcial2Pts.setText(Integer.toString(mValues.get(position).parcial2));
        holder.mZonaPts.setText(Integer.toString(mValues.get(position).zona));
        holder.mFinalPts.setText(Integer.toString(mValues.get(position).exfinl));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final Button mClaseBtn;
        public final LinearLayout mGradesLayout;
        public final TextView mNota1Pts, mNota2Pts, mNota3Pts, mNota4Pts, mParcial1Pts, mParcial2Pts, mZonaPts, mFinalPts;
        public GradeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mClaseBtn = (Button) view.findViewById(R.id.classBtn);
            mGradesLayout = (LinearLayout) view.findViewById(R.id.classLayout);
            mNota1Pts = (TextView) view.findViewById(R.id.nota1Pts);
            mNota2Pts = (TextView) view.findViewById(R.id.nota2Pts);
            mNota3Pts = (TextView) view.findViewById(R.id.nota3Pts);
            mNota4Pts = (TextView) view.findViewById(R.id.nota4Pts);
            mParcial1Pts= (TextView) view.findViewById(R.id.parcial1Pts);
            mParcial2Pts= (TextView) view.findViewById(R.id.parcial2Pts);
            mZonaPts= (TextView) view.findViewById(R.id.zonaPts);
            mFinalPts= (TextView) view.findViewById(R.id.finalPts);



            mClaseBtn.setOnClickListener((view1 -> {

                mGradesLayout.setVisibility(toggleVisibility());

            }));
        }

        public int toggleVisibility(){
            if(mGradesLayout.getVisibility()==View.GONE)
                return View.VISIBLE;
            else
                return View.GONE;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mClaseBtn.getText() + "'";
        }


    }
}
