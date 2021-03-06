package au.id.eaj.activo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import org.w3c.dom.Text;

public class NavigationDrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "NavigationDrawer";

    FragmentManager fragmentManager;
    Fragment earnWaiting;
    Fragment earnActive;
    boolean earning;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Will ask user where they want to go...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab.setVisibility(FloatingActionButton.INVISIBLE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // My stuff
        // Set default layout?
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, new EarnFragment())
                .commit();

        earnActive = new EarnActiveFragment();
        earnWaiting = new EarnFragment();

        // Fixing Later Map loading Delay (code from online? try it out)
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MapView mv = new MapView(getApplicationContext());
                    mv.onCreate(null);
                    mv.onPause();
                    mv.onDestroy();
                }catch (Exception ignored){
                    Log.d(TAG, "Exception encountered.");
                }
            }
        }).start();
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
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
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

    public void earnStartListener(View view) {
        earning = true;
        fab.setVisibility(FloatingActionButton.VISIBLE);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, earnActive)
                .commit();
    }

    public void earnStopListener(View view) {
        earning = false;
        fab.setVisibility(FloatingActionButton.INVISIBLE);
        fragmentManager.beginTransaction()
                .replace(R.id.content_frame, earnWaiting)
                .commit();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_earn_layout) {
            if (!earning) {
                fab.setVisibility(FloatingActionButton.INVISIBLE);
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, earnWaiting)
                        .commit();
            } else {
                fab.setVisibility(FloatingActionButton.VISIBLE);
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, earnActive)
                        .commit();
            }
        } else if (id == R.id.nav_map_layout) {
            fab.setVisibility(FloatingActionButton.VISIBLE);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new ViewMapFragment())
                    .commit();
        } else if (id == R.id.nav_account_layout) {
            fab.setVisibility(FloatingActionButton.INVISIBLE);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new AccountFragment())
                    .commit();
        } else if (id == R.id.nav_history_layout) {
            fab.setVisibility(FloatingActionButton.INVISIBLE);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new HistoryFragment())
                    .commit();
        } else if (id == R.id.nav_social_layout) {
            fab.setVisibility(FloatingActionButton.INVISIBLE);
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, new SocialFragment())
                    .commit();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
