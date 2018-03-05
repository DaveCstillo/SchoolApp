package app.davecstillo.com.schoolapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import app.davecstillo.com.schoolapp.Content.GradesContent;
import app.davecstillo.com.schoolapp.Content.activitiesContent;
import app.davecstillo.com.schoolapp.Content.alumnosContent;
import app.davecstillo.com.schoolapp.dummy.DummyContent;

public class menuActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        feedFragment.OnListFragmentInteractionListener,
        schedule_frame.OnFragmentInteractionListener,
        activities.OnFragmentInteractionListener,
        initFragment.OnFragmentInteractionListener,
        gradesFragment.OnListFragmentInteractionListener,
        alumnosListado.OnListFragmentInteractionListener{

    private int fragmentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       fragmentID=0;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        changeFragment(new initFragment());

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        BaseFragment f = null;

        if(id==R.id.homePage){
          f=new initFragment();
        } else if (id == R.id.notas) {
            fragmentID=R.id.notas;
            f=new alumnosListado();
        } else if (id == R.id.reportes) {
            fragmentID=R.id.reportes;
            f=new alumnosListado();
        } else if (id == R.id.Horarios) {
            fragmentID=R.id.Horarios;
            f=new alumnosListado();
        } else if (id == R.id.tareas) {
            fragmentID=R.id.tareas;
            f=new alumnosListado();
        } else if (id == R.id.teachers_chat) {
            fragmentID=R.id.teachers_chat;
            f=new alumnosListado();
        } else if (id == R.id.principal_chat) {
            fragmentID=R.id.principal_chat;
            f=new alumnosListado();
        }

        changeFragment(f);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onFragmentInteraction(activitiesContent.dayItem item) {

    }

    @Override
    public void onListFragmentInteraction(GradesContent.GradeItem item) {

    }


    @Override
    public void onListFragmentInteraction(alumnosContent.alumno item) {
        BaseFragment f;
        switch (fragmentID) {
            case R.id.notas:
                gradesFragment fragment = new gradesFragment();
                fragment.setAlumno(item);
                f= fragment;
                break;
            case R.id.reportes:
                f = new initFragment();
                break;
            case R.id.Horarios:
                f = new initFragment();
                break;
            case R.id.tareas:
                f = new initFragment();
                break;
            default:
                f = new initFragment();
                break;
        }

        changeFragment(f);

    }
}
