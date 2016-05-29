package vrdemo.mi.com.visualradiodemo.ui.playlist;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import vrdemo.mi.com.visualradiodemo.R;

/**
 * Created by Niroshan on 5/25/2016.
 */
public class AboutFragment extends Fragment{

    Context con;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle b)
    {
        View view = inflater.inflate(R.layout.about,container,false);

        return view;

    }


}
