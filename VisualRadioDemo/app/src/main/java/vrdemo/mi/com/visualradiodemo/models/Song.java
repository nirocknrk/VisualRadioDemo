package vrdemo.mi.com.visualradiodemo.models;

/**
 * Created by Niroshan on 5/24/2016.
 */
public class Song {

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public int getSongNumber() {
        return songNumber;
    }

    public void setSongNumber(int songNumber) {
        this.songNumber = songNumber;
    }

    public String getSongAlbumArt() {
        return songAlbumArt;
    }

    public void setSongAlbumArt(String songAlbumArt) {
        this.songAlbumArt = songAlbumArt;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Song(String songArtist, String songTitle, int songNumber, String songAlbumArt, boolean playing,String songAlbum, String songGender, String songLength) {
        this.songArtist = songArtist;
        this.songTitle = songTitle;
        this.songNumber = songNumber;
        this.songAlbumArt = songAlbumArt;
        this.playing = playing;
        this.songAlbum = songAlbum;
        this.songGender = songGender;
        this.songLength = songLength;
    }

    private String songArtist;

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongGender() {
        return songGender;
    }

    public void setSongGender(String songGender) {
        this.songGender = songGender;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }

    private String songAlbum;
    private String songGender;
    private String songLength;
    private String songTitle;
    private int songNumber;
    private String songAlbumArt;
    private boolean playing;


}
