package vrdemo.mi.com.visualradiodemo.models;

/**
 * Created by Niroshan on 5/26/2016.
 */
public class Programme {
    public String getChannelProgramName() {
        return channelProgramName;
    }

    public void setChannelProgramName(String channelProgramName) {
        this.channelProgramName = channelProgramName;
    }

    public int getChannelProgramDay() {
        return channelProgramDay;
    }

    public void setChannelProgramDay(int channelProgramDay) {
        this.channelProgramDay = channelProgramDay;
    }

    public String getDjName() {
        return DjName;
    }

    public void setDjName(String djName) {
        this.DjName = djName;
    }

    public String getChannelProgramStartHour() {
        return channelProgramStartHour;
    }

    public void setChannelProgramStartHour(String channelProgramStartHour) {
        this.channelProgramStartHour = channelProgramStartHour;
    }

    public String getChannelProgramEndHour() {
        return channelProgramEndHour;
    }

    public void setChannelProgramEndHour(String channelProgramEndHour) {
        this.channelProgramEndHour = channelProgramEndHour;
    }

    public Programme(String name, int channelProgramDay, String DJs, String startingTime, String endingTime) {
        this.channelProgramName = name;
        this.channelProgramDay = channelProgramDay;
        this.DjName = DJs;
        this.channelProgramStartHour = startingTime;
        this.channelProgramEndHour = endingTime;
    }

    String channelProgramName;
    int channelProgramDay;
    String DjName;
    String channelProgramStartHour;
    String channelProgramEndHour;
}
