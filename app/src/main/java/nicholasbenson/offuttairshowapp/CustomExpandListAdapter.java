package nicholasbenson.offuttairshowapp;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomExpandListAdapter extends BaseExpandableListAdapter
{
    private LayoutInflater inflater;
    private ArrayList<Parent> mParent;

    public CustomExpandListAdapter(Context context, ArrayList<Parent> parent){
        mParent = parent;
        inflater = LayoutInflater.from(context);
    }


    @Override
    //counts the number of group/parent items so the list knows how many times calls getGroupView() method
    public int getGroupCount() {
        return mParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    /*@Override
    //counts the number of children items so the list knows how many times calls getChildView() method
    public int getChildrenCount(int i) {
        return mParent.get(i).getArrayChildren().size();
    }*/

    @Override
    //gets the title of each parent/group
    public Object getGroup(int i) {
        return mParent.get(i).getTitle();
    }

    //gets the name of each item
    public Object getChild(int i, int il) {
        return mParent.get(i).getChildBody();
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    //in this method you must set the text to see the parent/group on the list
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.performer_list_item_parent, viewGroup,false);
        }

        TextView textView = (TextView) view.findViewById(R.id.list_item_text_view);
        textView.setText(getGroup(groupPosition).toString());

        /*LinearLayout ll = (LinearLayout)view.findViewById(R.id.performers_image);
        ll.setBackgroundResource(PerformersActivity.getContext().getApplicationContext().getResources().getIdentifier("performer_" + groupPosition, "drawable", PerformersActivity.getContext().getApplicationInfo().packageName));*/

        ImageView imgView = (ImageView)view.findViewById(R.id.imageView);
        imgView.setImageResource(
                PerformersActivity.getContext().getApplicationContext().getResources().getIdentifier("performer_" + groupPosition, "drawable", PerformersActivity.getContext().getApplicationInfo().packageName)
        );


        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    //in this method you must set the text to see the children on the list
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();
        holder.childPosition = childPosition;
        holder.groupPosition = groupPosition;

        if (view == null) {
            view = inflater.inflate(R.layout.performer_list_item_child, viewGroup,false);
        }

        TextView tvBody = (TextView) view.findViewById(R.id.list_item_text_child_body);
        tvBody.setText(mParent.get(groupPosition).getChildBody());

        TextView tvLink = (TextView) view.findViewById(R.id.list_item_text_child_link);
        tvLink.setText(mParent.get(groupPosition).getChildLink());

        view.setTag(holder);

        //return the entire view
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {
        /* used to make the notifyDataSetChanged() method work */
        super.registerDataSetObserver(observer);
    }

// Intentionally put on comment, if you need on click deactivate it
/*  @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder)view.getTag();
        if (view.getId() == holder.button.getId()){

           // DO YOUR ACTION
        }
    }*/


    protected class ViewHolder {
        protected int childPosition;
        protected int groupPosition;
        protected Button button;
    }

    protected final static int getResourceID(final String resName, final String resType, final Context ctx)
    {
        final int ResourceID = ctx.getResources().getIdentifier(resName, resType, ctx.getApplicationInfo().packageName);
        if (ResourceID == 0)
        {
            throw new IllegalArgumentException
                    (
                            "No resource string found with name " + resName
                    );
        }
        else
        {
            return ResourceID;
        }
    }
}