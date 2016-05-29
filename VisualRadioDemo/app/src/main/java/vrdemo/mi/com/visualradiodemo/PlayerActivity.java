package vrdemo.mi.com.visualradiodemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vrdemo.mi.com.visualradiodemo.models.Song;

public class PlayerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    List<Song> songs;
    TextView mTextViewListHeader_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        songs = new ArrayList<Song>();
        //dummy songs
        songs.add(new Song("Justin Bieber","What Do You Mean" ,1, "pic1",false,"JB", "PoP","02:09"));
        songs.add(new Song("Taylor Swift","Wildest Dreams", 2, "pic2",false,"TaSw", "R&D","04:15"));
        songs.add(new Song("Thomas Rhett","Die A Happy Man", 3, "pic3",true,"Die Happy", "Classic","01:17"));
        songs.add(new Song("Ed Sheeran","Thinking Out Loud", 4, "pic4",false,"TOB", "Rock","04:22"));
        songs.add(new Song("5th song","title 5", 5, "pic5",false,"JB", "PoP","02:54"));
        songs.add(new Song("6th song","title 6", 6, "pic6",false,"JB", "PoP","01:24"));

        ListView mListView = (ListView) findViewById(R.id.listView);



        // get data from the table by the ListAdapter
        SongListAdapter songAdapter = new SongListAdapter(this, R.layout.song_list_item, songs);

        mListView.setAdapter(songAdapter);

        mTextViewListHeader_title = (TextView)findViewById(R.id.listHeader_title);
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
        getMenuInflater().inflate(R.menu.player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_playlist) {
            mTextViewListHeader_title.setText(getString(R.string.nav_playlist));

        } else if (id == R.id.nav_proramelineup) {
            mTextViewListHeader_title.setText(getString(R.string.nav_proramelineup));

        } else if (id == R.id.nav_about) {
            Intent i = new Intent(PlayerActivity.this,About.class);
            startActivity(i);

        } else if (id == R.id.nav_setting) {

        }else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
