package vrdemo.mi.com.visualradiodemo.ui.playlist;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import vrdemo.mi.com.visualradiodemo.PlayerActivity;
import vrdemo.mi.com.visualradiodemo.R;
import vrdemo.mi.com.visualradiodemo.models.Song;

/**
 * Created by Niroshan on 5/25/2016.
 */
public class PlaylistFragment extends ListFragment implements ListView.OnItemClickListener  {

    Context context;
    List<Song> songs;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.playlist_area_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        context = getActivity().getApplicationContext();
        songs = getPlaylist(jsonStringForPlayList);
        SongListAdapter songAdapter = new SongListAdapter(context, R.layout.song_list_item, songs);

        setListAdapter(songAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                RelativeLayout regularSogListView = (RelativeLayout) view.findViewById(R.id.regularSogListView);
                RelativeLayout expandedSong = (RelativeLayout) view.findViewById(R.id.expandedSong);

                if(regularSogListView.getVisibility()==View.GONE){
                    regularSogListView.setVisibility(View.VISIBLE);
                    expandedSong.setVisibility(View.GONE);
                }
                else{
                    regularSogListView.setVisibility(View.GONE);
                    expandedSong.setVisibility(View.VISIBLE);
                }



    }


    String jsonStringForPlayList = "[{\"id\":663506,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:03:56\",\"EndTime\":\"1900-01-01T19:08:13\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27732,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":2.0,\"BeltSequence\":0.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663511,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:22:41\",\"EndTime\":\"1900-01-01T19:28:24\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27716,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":11.0,\"BeltSequence\":10.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663512,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:28:25\",\"EndTime\":\"1900-01-01T19:32:26\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":22.0,\"BeltSequence\":22.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663513,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:32:26\",\"EndTime\":\"1900-01-01T19:36:39\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":23.0,\"BeltSequence\":25.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663514,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:36:39\",\"EndTime\":\"1900-01-01T19:39:34\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":24.0,\"BeltSequence\":25.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663515,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:39:35\",\"EndTime\":\"1900-01-01T19:43:10\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27720,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":26.0,\"BeltSequence\":26.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663516,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:43:10\",\"EndTime\":\"1900-01-01T19:48:24\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":32.0,\"BeltSequence\":27.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663517,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:48:25\",\"EndTime\":\"1900-01-01T19:52:20\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":34.0,\"BeltSequence\":30.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663518,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:52:20\",\"EndTime\":\"1900-01-01T19:55:46\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":35.0,\"BeltSequence\":32.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0},{\"id\":663519,\"Date\":\"2011-10-05T00:00:00\",\"StartTime\":\"1900-01-01T19:55:46\",\"EndTime\":\"1900-01-01T20:00:20\",\"ProgramID\":115,\"ChannelID\":9,\"SliceID\":27722,\"BeltID\":286,\"BeltType\":1,\"MediaType\":1,\"Sequence\":36.0,\"BeltSequence\":32.0,\"ProgramNumber\":0,\"BeltNumber\":0,\"TypeSequenceno\":0,\"CutID\":0,\"ACutID\":0,\"Skip\":false,\"TalkBefore\":false,\"Comment\":null,\"Status\":null,\"SHour\":0,\"IntroExtro\":0,\"CatText\":null,\"LevelCode\":0,\"CutNumber\":null,\"Year\":null,\"Title\":null,\"Intro\":0,\"Duration\":0.0,\"ExtroType\":null,\"CueInTime\":0.0,\"CueOutTime\":0.0,\"DurationPlayed\":0.0,\"Mediapath\":null,\"ArtistClient\":null,\"VisualFileName\":null,\"VisualMediaPath\":null,\"VisualTypeID\":0}]";;

    public List<Song> getPlaylist(String jsonStringForPlayList){
        //dummy songs
        List<Song> mSongs = new ArrayList<Song>();

        if(jsonStringForPlayList!=null && jsonStringForPlayList.trim().length()>0) {
            try {
                Gson gson = new GsonBuilder().create();
                Song[] p = gson.fromJson(jsonStringForPlayList, Song[].class);
                String u = "ChennelProgramList";

                mSongs = new ArrayList<Song>(Arrays.asList(p));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mSongs;
    }

}
