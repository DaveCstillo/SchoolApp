package app.davecstillo.com.schoolapp;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.JsonElement;


/**
 * A simple {@link Fragment} subclass.
 */
public class cafeteria extends BaseFragment {


    ImageView cartel;

    float scalediff;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;


    public cafeteria() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view;
        view = inflater.inflate(R.layout.fragment_cafeteria, container, false);

        cartel = (ImageView) view.findViewById(R.id.cafeteriaMenu);
//        view.setMinimumHeight(screenHeight);
//        view.setMinimumWidth(screenWidth);


        callList("getMenu.php");

        return view;
    }


    public void callList(String path){
        new BackgroundTask<JsonElement>(()-> httpHandler.instance.getJson(path), (json, ex)->{
            if(ex!=null){

            }
            if(json!=null){
                for(JsonElement res : json.getAsJsonObject().get("Menu").getAsJsonArray()){
                    JsonElement elm;

                    elm = res.getAsJsonObject().get("Imagen");

                    putImg(elm.getAsString());
                }
            }



        }).execute();
    }


    public void putImg(String imgName){

        new BackgroundTask<Bitmap>(()->Imagenes.get(imgName), (b, ex)->{
            if(ex!=null){

            }
            if(b!=null) {


                int screenWidth = this.getResources().getDisplayMetrics().widthPixels;
                int screenHeight = this.getResources().getDisplayMetrics().heightPixels;


                Log.d("Widths: ", "screenWidth: " + String.valueOf(screenWidth));
                Log.d("Heights: ", "screenHeight: " + String.valueOf(screenHeight));



                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(250, 250);
                layoutParams.leftMargin = 0;
                layoutParams.topMargin = 0;
                layoutParams.bottomMargin = 0;
                layoutParams.rightMargin = 0;
                layoutParams.width= screenWidth;
                layoutParams.height = screenHeight;


                cartel.setImageBitmap(b);
                cartel.setLayoutParams(layoutParams);
                cartel.setOnTouchListener(touchListener());

            }



        }).execute();


    }

    public OnTouchListener touchListener(){

        return new OnTouchListener() {

            RelativeLayout.LayoutParams parms;
            int startwidth;
            int startheight;
            float dx = 0, dy = 0, x = 0, y = 0;
            float angle = 0;
            int screenWidth = getResources().getDisplayMetrics().widthPixels;
            int screenHeight = getResources().getDisplayMetrics().heightPixels;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Log.d("Widths: ", "screenWidth: " + String.valueOf(screenWidth));
                Log.d("Heights: ", "screenHeight: " + String.valueOf(screenHeight));

                final ImageView view = (ImageView) v;

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:

                        parms = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        startwidth = parms.width;
                        startheight = parms.height;
                        dx = event.getRawX() - parms.leftMargin;
                        dy = event.getRawY() - parms.topMargin;
                        mode = DRAG;
                        break;

                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        if (oldDist > 10f) {
                            mode = ZOOM;
                        }

                        //d = rotation(event);

                        break;
                    case MotionEvent.ACTION_UP:

                        parms = (RelativeLayout.LayoutParams) view.getLayoutParams();

                        parms.leftMargin = 0;
                        parms.topMargin = 0;
                        parms.bottomMargin = 0;
                        parms.rightMargin = 0;
//                        parms.width= screenWidth;
//                        parms.height = screenHeight;
                        view.setAdjustViewBounds(true);
                        view.setLayoutParams(parms);
                        break;

                    case MotionEvent.ACTION_POINTER_UP:

                        mode = NONE;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == DRAG) {

                            x = event.getRawX();
                            y = event.getRawY();

                            parms.leftMargin = (int) (x - dx);
                            parms.topMargin = (int) (y - dy);

                            parms.rightMargin = 0;
                            parms.bottomMargin = 0;
                            parms.rightMargin = parms.leftMargin + (5 * parms.width);
                            parms.bottomMargin = parms.topMargin + (10 * parms.height);

                            view.setLayoutParams(parms);

                        } else if (mode == ZOOM) {

                            if (event.getPointerCount() == 2) {

                                //newRot = rotation(event);
                                float r = newRot - d;
                                angle = r;

                                x = event.getRawX();
                                y = event.getRawY();

                                float newDist = spacing(event);
                                if (newDist > 10f) {
                                    float scale = newDist / oldDist * view.getScaleX();
                                    if (scale > 0.6) {
                                        scalediff = scale;
                                        view.setScaleX(scale);
                                        view.setScaleY(scale);

                                    }
                                }

                                view.animate().rotationBy(angle).setDuration(0).setInterpolator(new LinearInterpolator()).start();

                                x = event.getRawX();
                                y = event.getRawY();

                                parms.leftMargin = (int) ((x - dx) + scalediff);
                                parms.topMargin = (int) ((y - dy) + scalediff);

                                parms.rightMargin = 0;
                                parms.bottomMargin = 0;
                                parms.rightMargin = parms.leftMargin + (5 * parms.width);
                                parms.bottomMargin = parms.topMargin + (10 * parms.height);

                                view.setLayoutParams(parms);


                            }
                        }
                        break;
                }

                return true;
            }
        };

    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

//    private float rotation(MotionEvent event) {
//        double delta_x = (event.getX(0) - event.getX(1));
//        double delta_y = (event.getY(0) - event.getY(1));
//        double radians = Math.atan2(delta_y, delta_x);
//        return (float) Math.toDegrees(radians);
//    }



}
