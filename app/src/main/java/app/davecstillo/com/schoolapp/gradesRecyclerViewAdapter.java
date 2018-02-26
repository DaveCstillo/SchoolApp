package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.davecstillo.com.schoolapp.Content.GradesContent;
import app.davecstillo.com.schoolapp.gradesFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.schoolapp.Content.GradesContent.GradeItem;

import java.util.List;

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
        holder.mClaseNombre.setText(mValues.get(position).clase);
        holder.mClasePuntos.setText(mValues.get(position).grade);

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
        public final TextView mClaseNombre;
        public final TextView mClasePuntos;
        public GradeItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mClaseNombre = (TextView) view.findViewById(R.id.className);
            mClasePuntos = (TextView) view.findViewById(R.id.pointsTxt);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mClaseNombre.getText() + "'";
        }
    }
}
