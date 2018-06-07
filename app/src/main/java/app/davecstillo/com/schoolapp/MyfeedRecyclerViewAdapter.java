package app.davecstillo.com.schoolapp;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import app.davecstillo.com.schoolapp.feedFragment.OnListFragmentInteractionListener;
import app.davecstillo.com.schoolapp.Content.feedContent;
import app.davecstillo.com.schoolapp.Content.feedContent.feedItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link feedItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyfeedRecyclerViewAdapter extends RecyclerView.Adapter<MyfeedRecyclerViewAdapter.ViewHolder> {

    private final List<feedItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyfeedRecyclerViewAdapter(List<feedItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_feed, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mImgView.setImageBitmap(mValues.get(position).getImage());
        holder.mIdView.setText(mValues.get(position).Titulo);
        holder.mContentView.setText(mValues.get(position).Descripcion);
        ponerImagen(mValues.get(position).getimgName(),holder);

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

    void ponerImagen(String imgurl, ViewHolder holder){
        Log.i("IMGURL",imgurl);
        new BackgroundTask<Bitmap>(() -> Imagenes.get(imgurl), (b, e) ->
        {
            if (e != null) {
                Log.d("Exception",e.getMessage()); throw new RuntimeException(e);}

            if(b!=null) {
                Log.d("Imagen","puesta");
                holder.mImgView.setImageBitmap(b);
            }


        }).execute();
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView mImgView;
        public feedItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mImgView = (ImageView) view.findViewById(R.id.imgFeed);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
