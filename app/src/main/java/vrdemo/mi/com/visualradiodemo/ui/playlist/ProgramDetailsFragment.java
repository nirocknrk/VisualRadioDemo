package vrdemo.mi.com.visualradiodemo.ui.playlist;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vrdemo.mi.com.visualradiodemo.R;
import vrdemo.mi.com.visualradiodemo.models.Programme;

/**
 * Created by Niroshan on 5/30/2016.
 */
public class ProgramDetailsFragment extends Fragment {

    Context context;
    Programme programme;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.program_detail_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context=getActivity().getApplicationContext();

    }


}
