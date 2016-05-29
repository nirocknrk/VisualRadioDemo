package vrdemo.mi.com.visualradiodemo.requestsong;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import vrdemo.mi.com.visualradiodemo.PlayerActivity;
import vrdemo.mi.com.visualradiodemo.R;
import vrdemo.mi.com.visualradiodemo.adapter.ExpandableRecyclerAdapter;
import vrdemo.mi.com.visualradiodemo.models.Programme;
import vrdemo.mi.com.visualradiodemo.programminglineup.ProgramListAdapter;

/**
 * Created by Niroshan on 5/28/2016.
 */
public class RequestSongFragment extends Fragment{

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.request_song_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getActivity().getApplicationContext();

    }



}
