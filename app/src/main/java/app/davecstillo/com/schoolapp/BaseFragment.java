package app.davecstillo.com.schoolapp;

import android.support.v4.app.Fragment;

/**
 * Created by DELL on 26/02/2018.
 */

public class BaseFragment extends Fragment {

    public BaseActivity getBaseActivity(){
        return (BaseActivity)this.getActivity();
    }

    public String getTitle(){
        return "";
    }
}
