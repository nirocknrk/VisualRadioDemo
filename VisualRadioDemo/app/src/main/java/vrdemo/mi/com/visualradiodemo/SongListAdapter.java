package vrdemo.mi.com.visualradiodemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import vrdemo.mi.com.visualradiodemo.models.Song;

/**
 * Created by Niroshan on 5/24/2016.
 */
public class SongListAdapter extends ArrayAdapter<Song> {

public SongListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        }

public SongListAdapter(Context context, int resource, List<Song> songs) {
        super(context, resource, songs);
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
        LayoutInflater vi;
        vi = LayoutInflater.from(getContext());
        v = vi.inflate(R.layout.song_list_item, null);
        }

        Song song = getItem(position);

        if (song != null) {
                TextView songTitle = (TextView) v.findViewById(R.id.songTitles_songBar);
                TextView songNumber = (TextView) v.findViewById(R.id.tvSongNumber_songBar);
                TextView artistName = (TextView) v.findViewById(R.id.artist_songBar);
                ImageView albumArt = (ImageView) v.findViewById(R.id.albumArt_imageView_songBar);
                ImageView nowPlayingIcon = (ImageView) v.findViewById(R.id.nowPlayingIconView_songBar);

                if(song.isPlaying()){
                        nowPlayingIcon.setVisibility(View.VISIBLE);
                        songNumber.setVisibility(View.GONE);
                }
                else{
                        nowPlayingIcon.setVisibility(View.GONE);
                        songNumber.setVisibility(View.VISIBLE);
                }

                if (songTitle != null) {
                        songTitle.setText(song.getSongTitle());
                }

                if (songNumber != null) {
                        int trackNumber = position+1;
                        songNumber.setText(""+trackNumber);
                }

                if (artistName != null) {
                        artistName.setText(song.getSongArtist());
                }

                if (albumArt != null) {
                        //albumArt.setImageBitmap(song.getSongAlbumArt());
                }



        }

        return v;
        }

        }