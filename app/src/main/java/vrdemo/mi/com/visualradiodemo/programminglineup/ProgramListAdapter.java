package vrdemo.mi.com.visualradiodemo.programminglineup;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vrdemo.mi.com.visualradiodemo.R;
import vrdemo.mi.com.visualradiodemo.adapter.ExpandableRecyclerAdapter;
import vrdemo.mi.com.visualradiodemo.models.ChennelProgramList;
import vrdemo.mi.com.visualradiodemo.models.Programme;
import vrdemo.mi.com.visualradiodemo.ui.playlist.ProgramDetailsFragment;


/**
 * Created by Niroshan on 5/26/2016.
 */
public class ProgramListAdapter extends ExpandableRecyclerAdapter<ProgramListAdapter.ProgramListItem>{

    public static final int TYPE_PERSON = 1001;

    FragmentManager fragmentManager;
    Context context;
    public ProgramListAdapter(Context cont,List<ChennelProgramList> chennelProgramLists,FragmentManager fm) {
            super(cont);
            context=cont;
            List<ProgramListItem> data=getSampleItems(chennelProgramLists);
            setItems(data);
        fragmentManager=fm;
            }

    public static class ProgramListItem extends ExpandableRecyclerAdapter.ListItem {
        public String Text;
        public String djName;
        public String prograTimes;

        public ProgramListItem(String group) {
            super(TYPE_HEADER);

            Text = group;
        }

        public ProgramListItem(String progName, String djNames,String prograTime) {
            super(TYPE_PERSON);

            Text = progName;
            djName = djNames;
            prograTimes = prograTime;
        }
    }

    public class HeaderViewHolder extends ExpandableRecyclerAdapter.HeaderViewHolder {
        TextView name;

        public HeaderViewHolder(View view) {
            super(view, (ImageView) view.findViewById(R.id.item_arrow));

            name = (TextView) view.findViewById(R.id.item_header_name);
        }

        public void bind(int position) {
            super.bind(position);

            name.setText(visibleItems.get(position).Text);
        }
    }

    public class ProgramViewHolder extends ExpandableRecyclerAdapter.ViewHolder {
        TextView programName;
        TextView programDJ;
        TextView programTime;

        public ProgramViewHolder(View view) {
            super(view);

            programName = (TextView) view.findViewById(R.id.songTitles_SongBar);
            programDJ = (TextView) view.findViewById(R.id.artist_songBar);
            programTime = (TextView) view.findViewById(R.id.songDuration_songBAr);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"clicked submit",Toast.LENGTH_SHORT).show();
                    Log.i("click", "test");

                    Fragment programDetailsFragment = new ProgramDetailsFragment();

                    FragmentTransaction ft = fragmentManager.beginTransaction();

                    ft.replace(R.id.mainListArea_playerView, programDetailsFragment,context.getString(R.string.PROGRAME_DETAILS_FRAGMENT_TAG));
                    //ft.add(R.id.mainListArea_playerView, programDetailsFragment);
                    ft.commit();
                }
            });
        }

        public void bind(int position) {

            programName.setText(visibleItems.get(position).Text);
            if(visibleItems.get(position).djName!=null){
                programDJ.setText(visibleItems.get(position).djName);
            }
            if(visibleItems.get(position).prograTimes!=null){
                programTime.setText(visibleItems.get(position).prograTimes);
            }

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new HeaderViewHolder(inflate(R.layout.iteam_header, parent));
            case TYPE_PERSON:
            default:
                return new ProgramViewHolder(inflate(R.layout.programlineup_list_item, parent));
        }
    }

    @Override
    public void onBindViewHolder(ExpandableRecyclerAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                ((HeaderViewHolder) holder).bind(position);
                break;
            case TYPE_PERSON:
            default:
                ((ProgramViewHolder) holder).bind(position);
                break;
        }
    }

    private List<ProgramListItem> getSampleItems(List<ChennelProgramList> chennelProgramLists) {
        List<ProgramListItem> items = new ArrayList<>();

        List<ChennelProgramList> mChennelProgramLists=new ArrayList<>();
        mChennelProgramLists=chennelProgramLists;
        if(mChennelProgramLists!=null && mChennelProgramLists.size()>0){
            for(ChennelProgramList pList : mChennelProgramLists){ //per day
                items.add(new ProgramListItem(pList.getDay()));

                List<Programme> programmeListOfTheDay=pList.getProgramlist();
                if(programmeListOfTheDay!=null && programmeListOfTheDay.size()>0){
                    for(Programme programme : programmeListOfTheDay){ // per day
                        items.add(new ProgramListItem(""+programme.getChannelProgramName(), ""+programme.getDjName(),""+programme.getChannelProgramStartHour()+"-"+programme.getChannelProgramEndHour()));
                    }
                }
            }
        }



        return items;
    }



}
