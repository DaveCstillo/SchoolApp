package app.davecstillo.com.schoolapp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;

import app.davecstillo.com.schoolapp.Content.WeekContent;
import app.davecstillo.com.schoolapp.dummy.DummyContent;


public class MainActivity extends AppCompatActivity implements feedFragment.OnListFragmentInteractionListener, weekFragment.OnListFragmentInteractionListener, schedule_frame.OnFragmentInteractionListener {



    schedule_frame Schedule = new schedule_frame();
    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
