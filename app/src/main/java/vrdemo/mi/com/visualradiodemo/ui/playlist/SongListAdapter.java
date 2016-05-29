package vrdemo.mi.com.visualradiodemo.ui.playlist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import vrdemo.mi.com.visualradiodemo.R;
import vrdemo.mi.com.visualradiodemo.models.Song;

/**
 * Created by Niroshan on 5/24/2016.
 */
public class SongListAdapter extends ArrayAdapter<Song> {

        View lastExtView=null;

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

                // normal view
                TextView songTitle = (TextView) v.findViewById(R.id.songTitles_SongBar);
                TextView songNumber = (TextView) v.findViewById(R.id.tvSongNumber_SongBar);
                TextView artistName = (TextView) v.findViewById(R.id.artist_songBar);
                ImageView albumArt = (ImageView) v.findViewById(R.id.albumArt_imageView_SongBar);
                ImageView nowPlayingIcon = (ImageView) v.findViewById(R.id.nowPlayingIconView_songBar);

                //ext view
                TextView extSongTitle = (TextView) v.findViewById(R.id.songTitles_extSongBar);
                TextView extSongNumber = (TextView) v.findViewById(R.id.tvSongNumber_extSongBar);
                TextView extArtistName = (TextView) v.findViewById(R.id.artist_extSongBar);
                ImageView extAlbumArt = (ImageView) v.findViewById(R.id.albumArt_imageView_extSongBar);
                ImageView extNowPlayingIcon = (ImageView) v.findViewById(R.id.nowPlayingIconView_extSongBar);
                TextView extAlbum = (TextView) v.findViewById(R.id.album_extSongBar);
                TextView extSongGender = (TextView) v.findViewById(R.id.gender_extSongBar);


                if(position==2){
                        nowPlayingIcon.setVisibility(View.VISIBLE);
                        songNumber.setVisibility(View.GONE);

                        extNowPlayingIcon.setVisibility(View.VISIBLE);
                        extSongNumber.setVisibility(View.GONE);
                }
                else{
                        nowPlayingIcon.setVisibility(View.GONE);
                        songNumber.setVisibility(View.VISIBLE);

                        extNowPlayingIcon.setVisibility(View.GONE);
                        extSongNumber.setVisibility(View.VISIBLE);
                }

                if (songTitle != null) {
                        songTitle.setText(song.getTitle());
                        extSongTitle.setText(song.getTitle());
                }

                if (songNumber != null) {
                        int trackNumber = position+1;
                        songNumber.setText(""+trackNumber);
                        extSongNumber.setText(""+trackNumber);
                }

                if (artistName != null) {
                        artistName.setText(song.getArtistClient());
                        extArtistName.setText(song.getArtistClient());
                }
                if (extAlbum != null) {
                        extAlbum.setText(song.getAlbum());
                }
                if (extSongGender != null) {
                        extSongGender.setText(song.getSongGender());
                }

                if (albumArt != null) {
                        //albumArt.setImageBitmap(song.getSongAlbumArt());
                        //extAlbumArt.setImageBitmap(song.getSongAlbumArt());
                }

                RelativeLayout extSongBarView = (RelativeLayout) v.findViewById(R.id.expandedSong);
                extSongBarView.setVisibility(View.GONE);

        }

        return v;
        }

        }