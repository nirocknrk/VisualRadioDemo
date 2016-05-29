package vrdemo.mi.com.visualradiodemo.programminglineup;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import vrdemo.mi.com.visualradiodemo.R;
import vrdemo.mi.com.visualradiodemo.models.Programme;

/**
 * Created by Niroshan on 5/26/2016.
 */
public class ProgramLineupAdapter extends ArrayAdapter<Programme> {



    public ProgramLineupAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ProgramLineupAdapter(Context context, int resource, List<Programme> programme) {
        super(context, resource, programme);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;


        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.song_list_item, null);
        }

        Programme programme = getItem(position);

        if (programme != null) {

            // normal view
            TextView programeTitle = (TextView) v.findViewById(R.id.songTitles_SongBar);
            TextView songNumber = (TextView) v.findViewById(R.id.tvSongNumber_SongBar);
            TextView programDJs = (TextView) v.findViewById(R.id.artist_songBar);
            ImageView albumArt = (ImageView) v.findViewById(R.id.albumArt_imageView_SongBar);
            ImageView nowPlayingIcon = (ImageView) v.findViewById(R.id.nowPlayingIconView_songBar);

            //ext view
//            TextView extSongTitle = (TextView) v.findViewById(R.id.songTitles_extSongBar);
//            TextView extSongNumber = (TextView) v.findViewById(R.id.tvSongNumber_extSongBar);
//            TextView extArtistName = (TextView) v.findViewById(R.id.artist_extSongBar);
//            ImageView extAlbumArt = (ImageView) v.findViewById(R.id.albumArt_imageView_extSongBar);
//            ImageView extNowPlayingIcon = (ImageView) v.findViewById(R.id.nowPlayingIconView_extSongBar);
//            TextView extAlbum = (TextView) v.findViewById(R.id.album_extSongBar);
//            TextView extSongGender = (TextView) v.findViewById(R.id.gender_extSongBar);

            songNumber.setVisibility(View.GONE);


            if (programeTitle != null) {
                programeTitle.setText(programme.getChannelProgramName());

            }

            if (songNumber != null) {
                int trackNumber = position+1;
                songNumber.setText(""+trackNumber);
            }

            if (programDJs != null) {
                if(programme.getDjName()!=null){
                    programDJs.setText(programme.getDjName());
                }
            }

            if (albumArt != null) {
                //albumArt.setImageBitmap(song.getVisualFileName());
                //extAlbumArt.setImageBitmap(song.getVisualFileName());
            }

            RelativeLayout extSongBarView = (RelativeLayout) v.findViewById(R.id.expandedSong);
            extSongBarView.setVisibility(View.GONE);

        }



        return v;
    }

}