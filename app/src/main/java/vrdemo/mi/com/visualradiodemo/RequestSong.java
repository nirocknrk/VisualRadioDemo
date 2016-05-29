package vrdemo.mi.com.visualradiodemo;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by Niroshan on 5/29/2016.
 */
public class RequestSong extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_song_fragment);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] data = {"program 01", "program 02", "program", "program 04", "program 05"};

        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.spinner_item_selected, data);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner_requestsong_prograList);
        spinner.setAdapter(adapter);
//        spinner.setSpinnerEventsListener(new Spinner.OnSpinnerEventsListener() {
//            public void onSpinnerOpened() {
//                spinner.setSelected(true);
//            }
//            public void onSpinnerClosed() {
//                spinner.setSelected(false);
//            }
//        });
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void requestSongSubmitButtonClick(View v){
        Toast.makeText(this,"clicked submit",Toast.LENGTH_SHORT).show();
    }
}
