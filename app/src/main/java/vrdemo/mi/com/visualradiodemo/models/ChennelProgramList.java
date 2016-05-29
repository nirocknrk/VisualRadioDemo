package vrdemo.mi.com.visualradiodemo.models;

import java.util.List;

/**
 * Created by Niroshan on 5/26/2016.
 */
public class ChennelProgramList {
    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Programme> getProgramlist() {
        return programlist;
    }

    public void setProgramlist(List<Programme> programlist) {
        this.programlist = programlist;
    }

    public ChennelProgramList(String day, List<Programme> programlist) {
        this.day = day;
        this.programlist = programlist;
    }

    String day;
    List<Programme> programlist;
}
