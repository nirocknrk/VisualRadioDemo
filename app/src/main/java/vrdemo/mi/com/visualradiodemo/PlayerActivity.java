package vrdemo.mi.com.visualradiodemo;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.plus.PlusShare;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vrdemo.mi.com.visualradiodemo.models.ChennelProgramList;
import vrdemo.mi.com.visualradiodemo.models.Programme;
import vrdemo.mi.com.visualradiodemo.models.Song;
import vrdemo.mi.com.visualradiodemo.programminglineup.ProgramLineUpListFragment;
import vrdemo.mi.com.visualradiodemo.requestsong.RequestSongFragment;
import vrdemo.mi.com.visualradiodemo.ui.playlist.PlaylistFragment;
import vrdemo.mi.com.visualradiodemo.ui.playlist.ProgramDetailsFragment;

public class PlayerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String PLAYLISTFRAGMENT="playlistFragment";
    private static final String PROGRAMMLINEUPFRAGMENT="ProgramLineFragment";
    TextView mTextViewListHeader_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            initialisePlayList();
        }



        mTextViewListHeader_title = (TextView)findViewById(R.id.listHeader_title);
    }

    private void initialisePlayList() {
        showPlayList();
    }

    private void showPlayList() {
        Fragment playlistFragment = new PlaylistFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.mainListArea_playerView, playlistFragment).commit();
        //transaction.addToBackStack(null); is needed only need to navigate old fragment back using back button (b4 commit)
    }

    private void showProgramLineUpList() {

            Fragment ProgramLineFragment = new ProgramLineUpListFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainListArea_playerView, ProgramLineFragment);
            ft.addToBackStack(null).commit();
            //transaction.addToBackStack(null); is needed only need to navigate old fragment back using back button (b4 commit)
    }
    private void showSongRequest() {

            Fragment requestSongFragment = new RequestSongFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainListArea_playerView, requestSongFragment).commit();
            //transaction.addToBackStack(null);
            // is needed only need to navigate old fragment back using back button (b4 commit)
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Fragment pr = (ProgramDetailsFragment)getFragmentManager().findFragmentByTag(getString(R.string.PROGRAME_DETAILS_FRAGMENT_TAG));
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        else if (pr != null && pr.isVisible()) {
            showProgramLineUpList();
        }else {
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
        else if(id==android.R.id.home){
            getSupportFragmentManager().popBackStack();
            return true;
        }
        else if (id == R.id.action_share_fb) {

        }else if (id == R.id.action_share_gp) {
            shareToGoogleP();
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
            showPlayList();

        } else if (id == R.id.nav_proramelineup) {
            mTextViewListHeader_title.setText(getString(R.string.nav_proramelineup));
            showProgramLineUpList();

        }else if (id == R.id.nav_request) {
//            mTextViewListHeader_title.setText(getString(R.string.nav_requestsong));
//            showSongRequest();
            Intent i = new Intent(PlayerActivity.this,RequestSong.class);
            startActivity(i);

        } else if (id == R.id.nav_about) {
            Intent i = new Intent(PlayerActivity.this,About.class);
            startActivity(i);
//            Fragment fragment = new AboutFragment();
//            FragmentTransaction transaction = getFragmentManager().beginTransaction();
//            transaction.add(R.id.container_playerView, fragment);
//            transaction.addToBackStack(null);
//            transaction.commit();

        } else if (id == R.id.nav_setting) {

        }else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void shareToGoogleP() {

        Intent shareIntent = new PlusShare.Builder(this)
                .setType("text/plain")
                .setText("Your listening to VisualRadio!")
                .setContentUrl(Uri.parse("https://mimobimedia.com"))
                .getIntent();

        startActivityForResult(shareIntent, 0);


//        Intent shareIntent = ShareCompat.IntentBuilder.from(this)
//                .setText("Your listening to VisualRadio!")
//                //.setType("image/jpeg")
//                .setType("text/plain")
//                //.setStream(pictureUri)
//                //.setContentUrl(Uri.parse("https://developers.google.com/+/web/snippet/examples/restaurant"))
//                .getIntent()
//                .setPackage("com.google.android.apps.plus");
//        startActivity(shareIntent);

    }

    public List<Song> getPlaylist0(){
        List<Song> mSongs = new ArrayList<Song>();
        //dummy songs
        mSongs.add(new Song("Justin Bieber","What Do You Mean" ,1, "pic1",false,"JB", "PoP","02:09"));
        mSongs.add(new Song("Taylor Swift","Wildest Dreams", 2, "pic2",false,"TaSw", "R&D","04:15"));
        mSongs.add(new Song("Thomas Rhett","Die A Happy Man", 3, "pic3",true,"Die Happy", "Classic","01:17"));
        mSongs.add(new Song("Ed Sheeran","Thinking Out Loud", 4, "pic4",false,"TOB", "Rock","04:22"));
        mSongs.add(new Song("5th song","title 5", 5, "pic5",false,"JB", "PoP","02:54"));
        mSongs.add(new Song("6th song","title 6", 6, "pic6",false,"JB", "PoP","01:24"));

        return mSongs;
    }

    public List<Song> getPlaylist(){
        //dummy songs
        List<Song> mSongs = new ArrayList<Song>();
        Gson gson = new GsonBuilder().create();
        String demoJsonStrem= getJsonStringOfPlayList();
        Song[] p = gson.fromJson(demoJsonStrem, Song[].class);
        String u="ChennelProgramList";

        mSongs = new ArrayList<Song>(Arrays.asList(p));

        return mSongs;
    }

    public List<Programme> getProgrammeList(){
        List<Programme> mProgramme = new ArrayList<Programme>();
        //dummy Programme
        mProgramme.add(new Programme("Sun Rise", 2,"Dj demo","8.00am","10.00am"));
        mProgramme.add(new Programme("Sun Rise2",2,"Dj demo2","8.00am","10.00am"));
        mProgramme.add(new Programme("Sun Rise2",2,"Dj demo4","8.00am","10.00am"));

        return mProgramme;
    }
//    public List<ChennelProgramList> ChennelProgramList0(){
//        List<ChennelProgramList> mChennelProgramLists = new ArrayList<ChennelProgramList>();
//        //dummy Programme
//        List<Programme> mProgramme = new ArrayList<Programme>();
//        //dummy Programme
//        mProgramme.add(new Programme("2Sun Rise",2,"Dj test","8.00am","1210.00am"));
//        mProgramme.add(new Programme("S2 Rise",2,"Dj test2","18.00am","1120.00am"));
//        mProgramme.add(new Programme("give Rise",2,"Dj test3","118.00am","1210.00am"));
//
//        mChennelProgramLists.add(new ChennelProgramList("Monday",mProgramme));
//        //mProgramme.clear();
//        mProgramme.add(new Programme("test show",2,"Dj talker","8.00am","1210.00am"));
//        mProgramme.add(new Programme("exam show",2,"Dj talker2","18.00am","1120.00am"));
//        mProgramme.add(new Programme("S22 Rise",2,"Dj talker4","118.00am","1210.00am"));
//
//        mChennelProgramLists.add(new ChennelProgramList("Tueday",mProgramme));
//        //mProgramme.clear();
//
//        mProgramme.add(new Programme("game show",2,"Dj gold","8.00am","1210.00am"));
//        mProgramme.add(new Programme("live show",2,"Dj gold4","18.00am","1120.00am"));
//        mProgramme.add(new Programme("tool Rise",2,"Dj gold5","118.00am","1210.00am"));
//
//        mChennelProgramLists.add(new ChennelProgramList("Wednesday",mProgramme));
//
//        //mProgramme.clear();
//        mChennelProgramLists.add(new ChennelProgramList("Wednesday",mProgramme));
//        mChennelProgramLists.add(new ChennelProgramList("Thursday",mProgramme));
//        mChennelProgramLists.add(new ChennelProgramList("Friday",mProgramme));
//        mChennelProgramLists.add(new ChennelProgramList("Saturday",mProgramme));
//        mChennelProgramLists.add(new ChennelProgramList("Sunday",mProgramme));
//
//        return mChennelProgramLists;
//    }
    public List<ChennelProgramList> ChennelProgramList(String tes){
        List<ChennelProgramList> mChennelProgramLists = new ArrayList<ChennelProgramList>();
        Gson gson = new GsonBuilder().create();
        String demoJsonStrem= getJsonStringOfProgramList();
        ChennelProgramList[] p = gson.fromJson(demoJsonStrem, ChennelProgramList[].class);
        //List<ChennelProgramList> p1 = gson.fromJson(demoJsonStrem, new TypeToken<ArrayList<ArrayList<ChennelProgramList>>>() {}.getType());
        //List<ChennelProgramList> p1 = gson.fromJson(demoJsonStrem, new TypeToken<ArrayList<ArrayList<ChennelProgramList>>>() {}.getType());
        String u="ChennelProgramList";

        mChennelProgramLists = new ArrayList<ChennelProgramList>(Arrays.asList(p));
        return mChennelProgramLists;
    }


    private String getJsonStringOfProgramList(){
    return
            "[{\"programlist\":[{\"channelProgramAutoID\":1123,\"channelProgramID\":110,\"channelProgramDay\":1,\"channelProgramName\":\"KRISTAL Klassik\",\"channelProgramStartHour\":20,\"channelProgramEndHour\":21,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1207,\"channelProgramID\":112,\"channelProgramDay\":1,\"channelProgramName\":\"Dakwah Hour\",\"channelProgramStartHour\":19,\"channelProgramEndHour\":19,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1152,\"channelProgramID\":132,\"channelProgramDay\":1,\"channelProgramName\":\"Suria Pagi\",\"channelProgramStartHour\":7,\"channelProgramEndHour\":9,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1153,\"channelProgramID\":133,\"channelProgramDay\":1,\"channelProgramName\":\"Renjis\",\"channelProgramStartHour\":10,\"channelProgramEndHour\":12,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1237,\"channelProgramID\":134,\"channelProgramDay\":1,\"channelProgramName\":\"Kickback Sundays\",\"channelProgramStartHour\":13,\"channelProgramEndHour\":14,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1155,\"channelProgramID\":135,\"channelProgramDay\":1,\"channelProgramName\":\"Carta Hits 20 KFM\",\"channelProgramStartHour\":15,\"channelProgramEndHour\":16,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1217,\"channelProgramID\":136,\"channelProgramDay\":1,\"channelProgramName\":\"KFM Hot Hitz\",\"channelProgramStartHour\":17,\"channelProgramEndHour\":18,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1238,\"channelProgramID\":137,\"channelProgramDay\":1,\"channelProgramName\":\"Santai Hujung Minggu\",\"channelProgramStartHour\":22,\"channelProgramEndHour\":24,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1242,\"channelProgramID\":139,\"channelProgramDay\":1,\"channelProgramName\":\"Dakwah Hour (Subuh) 2\",\"channelProgramStartHour\":6,\"channelProgramEndHour\":6,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1192,\"channelProgramID\":146,\"channelProgramDay\":1,\"channelProgramName\":\"Spinners Nite - Club Mix\",\"channelProgramStartHour\":2,\"channelProgramEndHour\":2,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1200,\"channelProgramID\":147,\"channelProgramDay\":1,\"channelProgramName\":\"Dakwah Hour (Subuh)\",\"channelProgramStartHour\":5,\"channelProgramEndHour\":5,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1216,\"channelProgramID\":148,\"channelProgramDay\":1,\"channelProgramName\":\"Global Dance Session - Hour 2\",\"channelProgramStartHour\":1,\"channelProgramEndHour\":1,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1250,\"channelProgramID\":138,\"channelProgramDay\":1,\"channelProgramName\":\"80s and 90s\",\"channelProgramStartHour\":3,\"channelProgramEndHour\":4,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"}],\"day\":\"Sunday\"},{\"programlist\":[{\"channelProgramAutoID\":1102,\"channelProgramID\":105,\"channelProgramDay\":2,\"channelProgramName\":\"The KFM Breakfast Show\",\"channelProgramStartHour\":7,\"channelProgramEndHour\":9,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1228,\"channelProgramID\":106,\"channelProgramDay\":2,\"channelProgramName\":\"InBox KFM\",\"channelProgramStartHour\":10,\"channelProgramEndHour\":11,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1224,\"channelProgramID\":107,\"channelProgramDay\":2,\"channelProgramName\":\"KFM DriveTime\",\"channelProgramStartHour\":12,\"channelProgramEndHour\":14,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1115,\"channelProgramID\":108,\"channelProgramDay\":2,\"channelProgramName\":\"Kopi dan Pulut Panggang\",\"channelProgramStartHour\":15,\"channelProgramEndHour\":16,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1218,\"channelProgramID\":109,\"channelProgramDay\":2,\"channelProgramName\":\"Kickin It Old Skool\",\"channelProgramStartHour\":17,\"channelProgramEndHour\":18,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1124,\"channelProgramID\":110,\"channelProgramDay\":2,\"channelProgramName\":\"KRISTAL Klassik\",\"channelProgramStartHour\":20,\"channelProgramEndHour\":21,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1125,\"channelProgramID\":111,\"channelProgramDay\":2,\"channelProgramName\":\"Chill Out Zone\",\"channelProgramStartHour\":22,\"channelProgramEndHour\":24,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1208,\"channelProgramID\":112,\"channelProgramDay\":2,\"channelProgramName\":\"Dakwah Hour\",\"channelProgramStartHour\":19,\"channelProgramEndHour\":19,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1243,\"channelProgramID\":139,\"channelProgramDay\":2,\"channelProgramName\":\"Dakwah Hour (Subuh) 2\",\"channelProgramStartHour\":6,\"channelProgramEndHour\":6,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1190,\"channelProgramID\":140,\"channelProgramDay\":2,\"channelProgramName\":\"Billboard Rock Chart\",\"channelProgramStartHour\":1,\"channelProgramEndHour\":2,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1201,\"channelProgramID\":147,\"channelProgramDay\":2,\"channelProgramName\":\"Dakwah Hour (Subuh)\",\"channelProgramStartHour\":5,\"channelProgramEndHour\":5,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1251,\"channelProgramID\":138,\"channelProgramDay\":2,\"channelProgramName\":\"80s and 90s\",\"channelProgramStartHour\":3,\"channelProgramEndHour\":4,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"}],\"day\":\"Monday\"},{\"programlist\":[{\"channelProgramAutoID\":1103,\"channelProgramID\":105,\"channelProgramDay\":3,\"channelProgramName\":\"The KFM Breakfast Show\",\"channelProgramStartHour\":7,\"channelProgramEndHour\":9,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1229,\"channelProgramID\":106,\"channelProgramDay\":3,\"channelProgramName\":\"InBox KFM\",\"channelProgramStartHour\":10,\"channelProgramEndHour\":11,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1225,\"channelProgramID\":107,\"channelProgramDay\":3,\"channelProgramName\":\"KFM DriveTime\",\"channelProgramStartHour\":12,\"channelProgramEndHour\":14,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1116,\"channelProgramID\":108,\"channelProgramDay\":3,\"channelProgramName\":\"Kopi dan Pulut Panggang\",\"channelProgramStartHour\":15,\"channelProgramEndHour\":16,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1219,\"channelProgramID\":109,\"channelProgramDay\":3,\"channelProgramName\":\"Kickin It Old Skool\",\"channelProgramStartHour\":17,\"channelProgramEndHour\":18,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1209,\"channelProgramID\":112,\"channelProgramDay\":3,\"channelProgramName\":\"Dakwah Hour\",\"channelProgramStartHour\":19,\"channelProgramEndHour\":19,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1235,\"channelProgramID\":113,\"channelProgramDay\":3,\"channelProgramName\":\"Rockalicious\",\"channelProgramStartHour\":20,\"channelProgramEndHour\":21,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1188,\"channelProgramID\":114,\"channelProgramDay\":3,\"channelProgramName\":\"The Big Love Show\",\"channelProgramStartHour\":22,\"channelProgramEndHour\":24,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1244,\"channelProgramID\":139,\"channelProgramDay\":3,\"channelProgramName\":\"Dakwah Hour (Subuh) 2\",\"channelProgramStartHour\":6,\"channelProgramEndHour\":6,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1222,\"channelProgramID\":141,\"channelProgramDay\":3,\"channelProgramName\":\"KFM Hot Hitz Repeat\",\"channelProgramStartHour\":1,\"channelProgramEndHour\":2,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1202,\"channelProgramID\":147,\"channelProgramDay\":3,\"channelProgramName\":\"Dakwah Hour (Subuh)\",\"channelProgramStartHour\":5,\"channelProgramEndHour\":5,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1252,\"channelProgramID\":138,\"channelProgramDay\":3,\"channelProgramName\":\"80s and 90s\",\"channelProgramStartHour\":3,\"channelProgramEndHour\":4,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"}],\"day\":\"Tuesday\"},{\"programlist\":[{\"channelProgramAutoID\":1104,\"channelProgramID\":105,\"channelProgramDay\":4,\"channelProgramName\":\"The KFM Breakfast Show\",\"channelProgramStartHour\":7,\"channelProgramEndHour\":9,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1230,\"channelProgramID\":106,\"channelProgramDay\":4,\"channelProgramName\":\"InBox KFM\",\"channelProgramStartHour\":10,\"channelProgramEndHour\":11,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1226,\"channelProgramID\":107,\"channelProgramDay\":4,\"channelProgramName\":\"KFM DriveTime\",\"channelProgramStartHour\":12,\"channelProgramEndHour\":14,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1117,\"channelProgramID\":108,\"channelProgramDay\":4,\"channelProgramName\":\"Kopi dan Pulut Panggang\",\"channelProgramStartHour\":15,\"channelProgramEndHour\":16,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1220,\"channelProgramID\":109,\"channelProgramDay\":4,\"channelProgramName\":\"Kickin It Old Skool\",\"channelProgramStartHour\":17,\"channelProgramEndHour\":18,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1210,\"channelProgramID\":112,\"channelProgramDay\":4,\"channelProgramName\":\"Dakwah Hour\",\"channelProgramStartHour\":19,\"channelProgramEndHour\":19,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1187,\"channelProgramID\":115,\"channelProgramDay\":4,\"channelProgramName\":\"Relaksi Halwa\",\"channelProgramStartHour\":20,\"channelProgramEndHour\":21,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1234,\"channelProgramID\":116,\"channelProgramDay\":4,\"channelProgramName\":\"KFM Cypher\",\"channelProgramStartHour\":22,\"channelProgramEndHour\":24,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1245,\"channelProgramID\":139,\"channelProgramDay\":4,\"channelProgramName\":\"Dakwah Hour (Subuh) 2\",\"channelProgramStartHour\":6,\"channelProgramEndHour\":6,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1203,\"channelProgramID\":147,\"channelProgramDay\":4,\"channelProgramName\":\"Dakwah Hour (Subuh)\",\"channelProgramStartHour\":5,\"channelProgramEndHour\":5,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1249,\"channelProgramID\":142,\"channelProgramDay\":4,\"channelProgramName\":\"Carta Hits Indonesia Repeat\",\"channelProgramStartHour\":1,\"channelProgramEndHour\":2,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1253,\"channelProgramID\":138,\"channelProgramDay\":4,\"channelProgramName\":\"80s and 90s\",\"channelProgramStartHour\":3,\"channelProgramEndHour\":4,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"}],\"day\":\"Wednesday\"},{\"programlist\":[{\"channelProgramAutoID\":1105,\"channelProgramID\":105,\"channelProgramDay\":5,\"channelProgramName\":\"The KFM Breakfast Show\",\"channelProgramStartHour\":7,\"channelProgramEndHour\":9,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1231,\"channelProgramID\":106,\"channelProgramDay\":5,\"channelProgramName\":\"InBox KFM\",\"channelProgramStartHour\":10,\"channelProgramEndHour\":11,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1227,\"channelProgramID\":107,\"channelProgramDay\":5,\"channelProgramName\":\"KFM DriveTime\",\"channelProgramStartHour\":12,\"channelProgramEndHour\":14,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1118,\"channelProgramID\":108,\"channelProgramDay\":5,\"channelProgramName\":\"Kopi dan Pulut Panggang\",\"channelProgramStartHour\":15,\"channelProgramEndHour\":16,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1221,\"channelProgramID\":109,\"channelProgramDay\":5,\"channelProgramName\":\"Kickin It Old Skool\",\"channelProgramStartHour\":17,\"channelProgramEndHour\":18,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1211,\"channelProgramID\":112,\"channelProgramDay\":5,\"channelProgramName\":\"Dakwah Hour\",\"channelProgramStartHour\":19,\"channelProgramEndHour\":19,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1241,\"channelProgramID\":117,\"channelProgramDay\":5,\"channelProgramName\":\"Bingkisan Melodi\",\"channelProgramStartHour\":20,\"channelProgramEndHour\":21,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1138,\"channelProgramID\":118,\"channelProgramDay\":5,\"channelProgramName\":\"Gelak Geli Kristal\",\"channelProgramStartHour\":22,\"channelProgramEndHour\":24,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1246,\"channelProgramID\":139,\"channelProgramDay\":5,\"channelProgramName\":\"Dakwah Hour (Subuh) 2\",\"channelProgramStartHour\":6,\"channelProgramEndHour\":6,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1189,\"channelProgramID\":143,\"channelProgramDay\":5,\"channelProgramName\":\"Billboard Hot 100 Singles\",\"channelProgramStartHour\":1,\"channelProgramEndHour\":2,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1204,\"channelProgramID\":147,\"channelProgramDay\":5,\"channelProgramName\":\"Dakwah Hour (Subuh)\",\"channelProgramStartHour\":5,\"channelProgramEndHour\":5,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1254,\"channelProgramID\":138,\"channelProgramDay\":5,\"channelProgramName\":\"80s and 90s\",\"channelProgramStartHour\":3,\"channelProgramEndHour\":4,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"}],\"day\":\"Thursday\"},{\"programlist\":[{\"channelProgramAutoID\":1106,\"channelProgramID\":105,\"channelProgramDay\":6,\"channelProgramName\":\"The KFM Breakfast Show\",\"channelProgramStartHour\":7,\"channelProgramEndHour\":9,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1212,\"channelProgramID\":112,\"channelProgramDay\":6,\"channelProgramName\":\"Dakwah Hour\",\"channelProgramStartHour\":19,\"channelProgramEndHour\":19,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1239,\"channelProgramID\":119,\"channelProgramDay\":6,\"channelProgramName\":\"Warung Kopi Zureen\",\"channelProgramStartHour\":10,\"channelProgramEndHour\":12,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1214,\"channelProgramID\":120,\"channelProgramDay\":6,\"channelProgramName\":\"Dakwah Solat Jumaat\",\"channelProgramStartHour\":13,\"channelProgramEndHour\":14,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1141,\"channelProgramID\":121,\"channelProgramDay\":6,\"channelProgramName\":\"Teladan Sepanjang Jalan\",\"channelProgramStartHour\":15,\"channelProgramEndHour\":16,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1142,\"channelProgramID\":122,\"channelProgramDay\":6,\"channelProgramName\":\"KFM Fresh\",\"channelProgramStartHour\":17,\"channelProgramEndHour\":18,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1223,\"channelProgramID\":123,\"channelProgramDay\":6,\"channelProgramName\":\"Fresh 2.5\",\"channelProgramStartHour\":20,\"channelProgramEndHour\":21,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1233,\"channelProgramID\":124,\"channelProgramDay\":6,\"channelProgramName\":\"The Big Bluff Show\",\"channelProgramStartHour\":22,\"channelProgramEndHour\":24,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1247,\"channelProgramID\":139,\"channelProgramDay\":6,\"channelProgramName\":\"Dakwah Hour (Subuh) 2\",\"channelProgramStartHour\":6,\"channelProgramEndHour\":6,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1176,\"channelProgramID\":144,\"channelProgramDay\":6,\"channelProgramName\":\"Carta Hits 20 KFM Repeat\",\"channelProgramStartHour\":1,\"channelProgramEndHour\":2,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1205,\"channelProgramID\":147,\"channelProgramDay\":6,\"channelProgramName\":\"Dakwah Hour (Subuh)\",\"channelProgramStartHour\":5,\"channelProgramEndHour\":5,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1255,\"channelProgramID\":138,\"channelProgramDay\":6,\"channelProgramName\":\"80s and 90s\",\"channelProgramStartHour\":3,\"channelProgramEndHour\":4,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"}],\"day\":\"Friday\"},{\"programlist\":[{\"channelProgramAutoID\":1213,\"channelProgramID\":112,\"channelProgramDay\":7,\"channelProgramName\":\"Dakwah Hour\",\"channelProgramStartHour\":19,\"channelProgramEndHour\":19,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1240,\"channelProgramID\":125,\"channelProgramDay\":7,\"channelProgramName\":\"The Weekend Breakfast Show\",\"channelProgramStartHour\":7,\"channelProgramEndHour\":9,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1191,\"channelProgramID\":126,\"channelProgramDay\":7,\"channelProgramName\":\"KFM Top Hitz Indonesia\",\"channelProgramStartHour\":10,\"channelProgramEndHour\":11,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1147,\"channelProgramID\":127,\"channelProgramDay\":7,\"channelProgramName\":\"KFM Gempaq\",\"channelProgramStartHour\":12,\"channelProgramEndHour\":14,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1148,\"channelProgramID\":128,\"channelProgramDay\":7,\"channelProgramName\":\"AT40 With Ryan Seacrest\",\"channelProgramStartHour\":15,\"channelProgramEndHour\":18,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1232,\"channelProgramID\":129,\"channelProgramDay\":7,\"channelProgramName\":\"Supa Saturdays\",\"channelProgramStartHour\":20,\"channelProgramEndHour\":21,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1236,\"channelProgramID\":130,\"channelProgramDay\":7,\"channelProgramName\":\"Melodi Absolusi\",\"channelProgramStartHour\":22,\"channelProgramEndHour\":23,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1215,\"channelProgramID\":131,\"channelProgramDay\":7,\"channelProgramName\":\"Global Dance Session - Hour 1\",\"channelProgramStartHour\":24,\"channelProgramEndHour\":24,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1248,\"channelProgramID\":139,\"channelProgramDay\":7,\"channelProgramName\":\"Dakwah Hour (Subuh) 2\",\"channelProgramStartHour\":6,\"channelProgramEndHour\":6,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1177,\"channelProgramID\":145,\"channelProgramDay\":7,\"channelProgramName\":\"UK Top 20\",\"channelProgramStartHour\":1,\"channelProgramEndHour\":2,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"},{\"channelProgramAutoID\":1206,\"channelProgramID\":147,\"channelProgramDay\":7,\"channelProgramName\":\"Dakwah Hour (Subuh)\",\"channelProgramStartHour\":5,\"channelProgramEndHour\":5,\"channelProgramType\":3,\"DjName\":\"Auto DJ\",\"DjShowName\":\"System Auto\",\"DjAddress\":\"Brunei\",\"DjOtherName\":\"Fully Auto Mode\",\"DjHobbies\":\"N/A\"}],\"day\":\"Saturday\"}]";
    }
    private String getJsonStringOfPlayList(){
    return
            "[{\"id\":663506,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:03:56\",\"EndTime\":\"1900-01-01T19:08:13\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27732,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":2.0,\"BeltSequence\":0.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663511,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:22:41\",\"EndTime\":\"1900-01-01T19:28:24\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27716,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":11.0,\"BeltSequence\":10.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663512,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:28:25\",\"EndTime\":\"1900-01-01T19:32:26\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":22.0,\"BeltSequence\":22.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663513,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:32:26\",\"EndTime\":\"1900-01-01T19:36:39\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":23.0,\"BeltSequence\":25.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663514,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:36:39\",\"EndTime\":\"1900-01-01T19:39:34\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":24.0,\"BeltSequence\":25.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663515,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:39:35\",\"EndTime\":\"1900-01-01T19:43:10\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":26.0,\"BeltSequence\":26.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663516,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:43:10\",\"EndTime\":\"1900-01-01T19:48:24\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":32.0,\"BeltSequence\":27.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663517,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:48:25\",\"EndTime\":\"1900-01-01T19:52:20\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":34.0,\"BeltSequence\":30.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663518,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:52:20\",\"EndTime\":\"1900-01-01T19:55:46\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":35.0,\"BeltSequence\":32.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663519,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:55:46\",\"EndTime\":\"1900-01-01T20:00:20\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":36.0,\"BeltSequence\":32.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0}]";
    }
}
