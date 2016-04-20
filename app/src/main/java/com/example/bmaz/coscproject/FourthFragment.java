package com.example.bmaz.coscproject;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.widget.ExpandableListView;
import android.widget.TextView;
import java.util.HashMap;



/**
 * A simple {@link Fragment} subclass.
 */
public class FourthFragment extends Fragment
{
    ExpandableListView expandListView;
    public FourthFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        expandListView = (ExpandableListView) view.findViewById(R.id.expandListView);
        List<String> parents = new ArrayList<String>();
        List<String> child_h = new ArrayList<String>();
        List<String> child_r = new ArrayList<String>();
        List<String> child_a = new ArrayList<String>();
        HashMap<String,List<String>> childList = new HashMap<String, List<String>>();
        String parent_items[] = getResources().getStringArray(R.array.parent);
        String child_hotel[] = getResources().getStringArray(R.array.child_hotel);
        String child_rest[] = getResources().getStringArray(R.array.child_rest);
        String child_attract[] = getResources().getStringArray(R.array.child_attract);
        for(String title : parent_items)
        {
            parents.add(title);
        }
        for(String title : child_hotel)
        {
            child_h.add(title);
        }
        for(String title : child_rest)
        {
            child_r.add(title);
        }
        for(String title : child_attract)
        {
            child_a.add(title);
        }
        childList.put(parents.get(0),child_h);
        childList.put(parents.get(1),child_r);
        childList.put(parents.get(2),child_a);
        ExpandableLayout expand = new ExpandableLayout(getContext(),parents,childList);
        expandListView.setAdapter(expand);

    }

    public class ExpandableLayout extends BaseExpandableListAdapter
    {
        private List<String> parent_strings;
        private HashMap<String,List<String>> child_strings;
        private Context ctx;
        ExpandableLayout(Context ctx, List<String> parent_strings, HashMap<String,List<String>> child_strings)
        {
            this.ctx = ctx;
            this.parent_strings = parent_strings;
            this.child_strings = child_strings;
        }

        @Override
        public int getGroupCount()
        {
            return parent_strings.size();
        }

        @Override
        public int getChildrenCount(int groupPosition)
        {
            return child_strings.get(parent_strings.get(groupPosition)).size();
        }

        @Override
        public Object getGroup(int groupPosition)
        {
            return parent_strings.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition)
        {
            return child_strings.get(parent_strings.get(groupPosition)).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition)
        {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition)
        {
            return childPosition;
        }

        @Override
        public boolean hasStableIds()
        {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
        {
            String title = (String)this.getGroup(groupPosition);
            if(convertView == null)
            {
                LayoutInflater layoutInflater = (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.acc_expand_parent, null);
            }
            TextView textView = (TextView)convertView.findViewById(R.id.expand_parent);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setText(title);

            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
        {
            String title = (String)this.getChild(groupPosition, childPosition);
            if(convertView == null)
            {
                LayoutInflater layoutInflater = (LayoutInflater)this.ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = layoutInflater.inflate(R.layout.acc_expand_child,null);
            }
            TextView textView = (TextView)convertView.findViewById(R.id.expand_child);
            textView.setText(title);

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition)
        {
            return true;
        }
    }
}
