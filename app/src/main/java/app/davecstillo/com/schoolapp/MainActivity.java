package app.davecstillo.com.schoolapp;

import android.app.Fragment;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;

import app.davecstillo.com.schoolapp.Content.WeekContent;
import app.davecstillo.com.schoolapp.dummy.DummyContent;


public class MainActivity extends AppCompatActivity implements feedFragment.OnListFragmentInteractionListener, weekFragment.OnListFragmentInteractionListener, schedule_frame.OnFragmentInteractionListener {


    View schedule;
    Fragment feed;
    TabHost tabHost;
    LinearLayout tab1, tab2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LayoutInflater inflater = LayoutInflater.from(this);

        tab1 = (LinearLayout) findViewById(R.id.tab1);
        tab2 = (LinearLayout) findViewById(R.id.tab2);

        tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("FEED");
        spec.setContent(R.id.tab1);
        spec.setIndicator("FEED");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("SCHEDULE");
        spec.setContent(R.id.tab2);
        spec.setIndicator("SCHEDULE");
        tabHost.addTab(spec);

        schedule = findViewById(R.id.schedule_fragment);

        View laView = inflater.inflate(R.layout.fragment_schedule_frame,tab1,true);



    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }

    @Override
    public void onListFragmentInteraction(WeekContent.WeekItem item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
