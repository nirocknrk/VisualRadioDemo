package vrdemo.mi.com.visualradiodemo.models;

/**
 * Created by Niroshan on 5/24/2016.
 */
public class Song {

    public String getArtistClient() {
        return ArtistClient;
    }

    public void setArtistClient(String artistClient) {
        this.ArtistClient = artistClient;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public int getSequence() {
        return Sequence;
    }

    public void setSequence(int sequence) {
        this.Sequence = sequence;
    }

    public String getVisualFileName() {
        return VisualFileName;
    }

    public void setVisualFileName(String visualFileName) {
        this.VisualFileName = visualFileName;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public Song(String songArtist, String songTitle, int songNumber, String songAlbumArt, boolean playing,String songAlbum, String songGender, String songLength) {
        this.ArtistClient = songArtist;
        this.Title = songTitle;
        this.Sequence = songNumber;
        this.VisualFileName = songAlbumArt;
        this.playing = playing;
        this.Album = songAlbum;
        this.songGender = songGender;
        this.songLength = songLength;
    }



    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        this.Album = album;
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

    public String getVisualMediaPath() {
        return VisualMediaPath;
    }

    public void setVisualMediaPath(String visualMediaPath) {
        VisualMediaPath = visualMediaPath;
    }

    public String getVisualTypeID() {
        return VisualTypeID;
    }

    public void setVisualTypeID(String visualTypeID) {
        VisualTypeID = visualTypeID;
    }

    public String getProgramID() {
        return ProgramID;
    }

    public void setProgramID(String programID) {
        ProgramID = programID;
    }

    public String getChannelID() {
        return ChannelID;
    }

    public void setChannelID(String channelID) {
        ChannelID = channelID;
    }

    public String getSliceID() {
        return SliceID;
    }

    public void setSliceID(String sliceID) {
        SliceID = sliceID;
    }

    public int getMediapath() {
        return Mediapath;
    }

    public void setMediapath(int mediapath) {
        Mediapath = mediapath;
    }

    public String getBeltID() {
        return BeltID;
    }

    public void setBeltID(String beltID) {
        BeltID = beltID;
    }

    public String getMediaType() {
        return MediaType;
    }

    public void setMediaType(String mediaType) {
        MediaType = mediaType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String ArtistClient;
    private String Album;
    private String songGender;
    private String songLength;
    private String Title;
    private int Mediapath;
    private String VisualFileName;
    private String VisualMediaPath;
    private String VisualTypeID;
    private String ProgramID;
    private String ChannelID;
    private String SliceID;
    private String BeltID;
    private String MediaType;
    private int Sequence;
    private String id;
    private boolean playing;



}
