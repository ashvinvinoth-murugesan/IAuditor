package com.rentokil.pci.rauditor.Fragments;


import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.Toast;

import com.rentokil.pci.rauditor.SIR_Title_1;
import com.rentokil.pci.rauditor.SIR_Obser_3;
import com.rentokil.pci.rauditor.SIR_Summary_4;
import com.rentokil.pci.rauditor.SIR_Cus_2;
import com.rentokil.pci.rauditor.CustomExpandableListAdapter;
import com.rentokil.pci.rauditor.ExpandableListDataPump;
import com.rentokil.pci.rauditor.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListView extends Fragment {

    android.widget.ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

     //   super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_expandable_list_view);
      //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_expandable_list_view, container, false);

        expandableListView = (android.widget.ExpandableListView)view.findViewById(R.id.expandableListView);
        expandableListDetail = ExpandableListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        expandableListAdapter = new CustomExpandableListAdapter(container.getContext(), expandableListTitle, expandableListDetail);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new android.widget.ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });


        expandableListView.setOnGroupCollapseListener(new android.widget.ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();



            }
        });

        expandableListView.setOnChildClickListener(new android.widget.ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(android.widget.ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT
                 ).show();

                if (groupPosition == 0 && childPosition==0){
                    Intent myIntent = new Intent(v.getContext(), SIR_Title_1.class);
                    startActivity(myIntent);
                }
                if (groupPosition == 0 && childPosition==1){
                    Intent myIntent = new Intent(v.getContext(), SIR_Cus_2.class);
                    startActivity(myIntent);
                }
                if (groupPosition == 0 && childPosition==2){
                    Intent myIntent = new Intent(v.getContext(), SIR_Obser_3.class);
                    startActivity(myIntent);
                }
                if (groupPosition == 0 && childPosition==3){
                    Intent myIntent = new Intent(v.getContext(), SIR_Summary_4.class);
                    startActivity(myIntent);
                }


                return false;

            }
        });

        return view;
    }
}