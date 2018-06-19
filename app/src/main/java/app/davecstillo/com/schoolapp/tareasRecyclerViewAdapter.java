package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.davecstillo.com.schoolapp.Content.tareasContent;
import app.davecstillo.com.schoolapp.tareasFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.schoolapp.Content.tareasContent.tarea;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link tarea} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class tareasRecyclerViewAdapter extends RecyclerView.Adapter<tareasRecyclerViewAdapter.ViewHolder> {

    private final List<tarea> mValues;
    private final OnListFragmentInteractionListener mListener;

    public tareasRecyclerViewAdapter(List<tarea> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_tareas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mClaseView.setText(mValues.get(position).Materia);
        holder.mFechaEntrega.setText(mValues.get(position).FechaEn);
        holder.mTareaView.setText(mValues.get(position).Tarea);

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
        public final TextView mClaseView, mFechaEntrega;
        public final TextView mTareaView;
        public tarea mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mClaseView = (TextView) view.findViewById(R.id.claseTxt);
            mFechaEntrega = (TextView) view.findViewById(R.id.fechaEntrega);
            mTareaView = (TextView) view.findViewById(R.id.tareaTxt);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTareaView.getText() + "'";
        }
    }
}
