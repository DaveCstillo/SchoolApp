package app.davecstillo.com.schoolapp;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by DELL on 26/02/2018.
 */

public class BaseActivity extends AppCompatActivity{

    public void changeFragment(BaseFragment f){
        changeFragment(f,true);
    }

    public void changeFragment(BaseFragment f, boolean backstack){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction trans = manager.beginTransaction().replace(R.id.fragmentContenido,f);
        if(backstack)
            trans.addToBackStack(null);
        else
            manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);

        trans.commit();
        this.setTitle(f.getTitle());
    }
}
