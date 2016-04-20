package com.example.bmaz.coscproject;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacultyPresentersTab extends Fragment {
    View thirdView;
    ExpandableListView list;
    private String[] presentors;
    private String[][] presentorsInfo;


    public FacultyPresentersTab() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presentors = new String[] { "Student Presentor ", "Student Presentor 2", "Student Presentor 3", "Student Presentor 4" };

        presentorsInfo = new String [][] {
                { "First Info: Faculty talks..."},
                { "second Info: This is where the topic and summary of the talk will go..." +
                        "blah blah balh blha words and stuff go here..." },
                { "Type whatever stuff you want in here." +
                        "RANDOM CHARACTERS"},
                { "Fourth Info: As cloud computing becomes more popular companies are shifting from local desktop applications to cloud based software as a service (SaaS) applications. As a result, the number of companies offering these services are increasing very quickly. In order for a cloud based application provider to be competitive in the field they must maintain the best quality of service (QoS) possible. Because cloud based services’ workloads vary over time it can be difficult to maintain a high QoS while optimizing resource costs. This paper presented a model that is used to predict workloads and allocate resources accordingly."}
        };
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        thirdView = inflater.inflate(R.layout.presentations_layout, container, false);
        return thirdView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        list = (ExpandableListView) view.findViewById(R.id.expListView);
        list.setAdapter(new ExpandableListAdapter(presentors, presentorsInfo));
        list.setGroupIndicator(null);

    }

    //Used to fill the expandable list with arrays of text.
    public class ExpandableListAdapter extends BaseExpandableListAdapter {

        private final LayoutInflater inf;
        private String[] presenters;
        private String[][] presentersInformation;

        public ExpandableListAdapter(String[] groups, String[][] children) {
            this.presenters = groups;
            this.presentersInformation = children;
            inf = LayoutInflater.from(getActivity());
        }

        @Override
        public int getGroupCount() {
            return presenters.length;
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return presentersInformation[groupPosition].length;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return presenters[groupPosition];
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return presentersInformation[groupPosition][childPosition];
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = inf.inflate(R.layout.presentors_info, parent, false);
                holder = new ViewHolder();

                holder.text = (TextView) convertView.findViewById(R.id.childTxt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getChild(groupPosition, childPosition).toString());

            return convertView;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if (convertView == null) {
                convertView = inf.inflate(R.layout.presenters, parent, false);

                holder = new ViewHolder();
                holder.text = (TextView) convertView.findViewById(R.id.parentTxt);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.text.setText(getGroup(groupPosition).toString());

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        private class ViewHolder {
            TextView text;
        }
    }

}