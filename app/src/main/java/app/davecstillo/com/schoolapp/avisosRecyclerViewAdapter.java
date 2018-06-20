package app.davecstillo.com.schoolapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.davecstillo.com.schoolapp.avisosFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.schoolapp.Content.avisosContent.avisos;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link avisos} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class avisosRecyclerViewAdapter extends RecyclerView.Adapter<avisosRecyclerViewAdapter.ViewHolder> {

    private final List<avisos> mValues;
    private final OnListFragmentInteractionListener mListener;

    public avisosRecyclerViewAdapter(List<avisos> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_avisos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String texto;
        holder.mItem = mValues.get(position);
        holder.mIDAviso.setText(String.valueOf(position+1)+".");
        holder.mTituloAviso.setText(mValues.get(position).Titulo);
        holder.mDescriptionAviso.setText(mValues.get(position).Descripcion);

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
        public final TextView mIDAviso;
        public final TextView mTituloAviso;
        public final TextView mDescriptionAviso;
        public avisos mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIDAviso = (TextView) view.findViewById(R.id.avisoID);
            mTituloAviso = (TextView) view.findViewById(R.id.tituloAviso);
            mDescriptionAviso = (TextView) view.findViewById(R.id.descripcionAviso);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mDescriptionAviso.getText() + "'";
        }
    }
}
