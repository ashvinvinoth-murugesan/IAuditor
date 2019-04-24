package com.rentokil.pci.rauditor.Adapter;

/**
 * Created by Murugan Kuppusamy on 23-Oct-17.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_LIST_1;
import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_Main;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_A;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_K;
import com.rentokil.pci.rauditor.MSOT_NEW.Signature_Activity;
import com.rentokil.pci.rauditor.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Locale;




public class ListViewAdapter_Inprogress extends BaseAdapter {

    // Declare Variables


    Context mContext;
    static int half_visible;
    ArrayList<Integer> poss;
    LayoutInflater inflater;
    private List<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes_incomplete> List_Item_Methodes_incomplete = null;
    private ArrayList<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes_incomplete> arraylist;
    public ListViewAdapter_Inprogress(Context context, ArrayList<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes_incomplete> List_Item_Methodes_incomplete) {
        mContext = context;
        this.List_Item_Methodes_incomplete = List_Item_Methodes_incomplete;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes_incomplete>();
        this.arraylist.addAll(List_Item_Methodes_incomplete);


        //poss = new ArrayList<Integer>();
    }


/* //   public int getCount() {
        return customer_name.length;
    }*/

    @Override
    public int getCount() {
        return List_Item_Methodes_incomplete.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder {
        TextView Tx_Audi_Name;
        TextView Tx_Cus_Name;
        TextView Tx_Audi_Date;
        TextView Tx_Doc_No;
        TextView count_disp;
        Button Bt_Tech,Bt_Branch;


    }
    @SuppressLint("ResourceAsColor")
    public View getView(final int position, View view, final ViewGroup parent) {
        // Declare Variables
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_incomplete, null);

            holder.Tx_Audi_Name = (TextView) view.findViewById(R.id.audi_name);
            holder.Tx_Cus_Name = (TextView) view.findViewById(R.id.cus_name);
            holder.Tx_Audi_Date = (TextView) view.findViewById(R.id.audi_date);
            holder.Tx_Doc_No = (TextView) view.findViewById(R.id.doc_no);
            holder.Bt_Tech = (Button) view.findViewById(R.id.tech_id);
            holder.Bt_Branch = (Button) view.findViewById(R.id.branch_id);
            holder.count_disp = (TextView) view.findViewById(R.id.count_disp);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Locate the TextViews in listview_item.xml
        // Capture position and set to the TextViews
        holder.Tx_Cus_Name.setText(List_Item_Methodes_incomplete.get(position).getCus_name());
        holder.Tx_Audi_Name.setText(List_Item_Methodes_incomplete.get(position).getAudi_name());
        holder.Tx_Audi_Date.setText(List_Item_Methodes_incomplete.get(position).getAudi_date());
        holder.Tx_Doc_No.setText(List_Item_Methodes_incomplete.get(position).getDoc_no());
        holder.count_disp.setText(List_Item_Methodes_incomplete.get(position).getcount_disp());
        holder.Bt_Branch.setVisibility(View.GONE);
        Log.e("KKKKK","in"+List_Item_Methodes_incomplete.get(position).getAudi_name());
        Log.e("KKKKK","in"+List_Item_Methodes_incomplete.get(position).getAudi_name().length());
        if(List_Item_Methodes_incomplete.get(position).getAudi_name().equalsIgnoreCase("MSOT")){
            holder.Bt_Branch.setVisibility(View.VISIBLE);
            holder.Bt_Tech.setText("Tech");
            if(List_Item_Methodes_incomplete.get(position).getAudit_tech().equalsIgnoreCase("Completed")){
                holder.Bt_Tech.setTextColor(Color.parseColor("#39A308"));
            }else {
                holder.Bt_Tech.setTextColor(Color.parseColor("#F02905"));
            }if(List_Item_Methodes_incomplete.get(position).getAudit_branch().equalsIgnoreCase("Completed")){
                holder.Bt_Branch.setTextColor(Color.parseColor("#39A308"));
            }else {
                holder.Bt_Branch.setTextColor(Color.parseColor("#F02905"));
            }



        }else {
            Log.e("KKKKK","in");
            holder.Bt_Tech.setText(List_Item_Methodes_incomplete.get(position).getcount_disp());
            holder.Bt_Branch.setVisibility(View.GONE);
        }

        holder.Bt_Branch.setOnClickListener(new
                                                    View.OnClickListener() {
                                                        @Override
                                                        public void onClick(View v) {
                                                            if(List_Item_Methodes_incomplete.get(position).getAudit_tech().equalsIgnoreCase("Completed")&&List_Item_Methodes_incomplete.get(position).getAudit_branch().equalsIgnoreCase("Completed")){
                                                                Intent i = new Intent(mContext, Signature_Activity.class);
                                                                i.putExtra("key_id",""+List_Item_Methodes_incomplete.get(position).getId());
                                                                i.putExtra("check","1111");
                                                                mContext.startActivity(i);
                                                            }else {
                                                                Log.e("KKKKK", "in Br" + List_Item_Methodes_incomplete.get(position).getId());
                                                                MSOT_Main.Main_ID = "" + List_Item_Methodes_incomplete.get(position).getId();
                                                                Intent i = new Intent(mContext, Page_K.class);
                                                                i.putExtra("key_id", "" + List_Item_Methodes_incomplete.get(position).getId());
                                                                mContext.startActivity(i);
                                                            }

                                                        }
                                                    });
        holder.Bt_Tech.setOnClickListener(new



                                                  View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          Log.e("KKKKK","in Te"+List_Item_Methodes_incomplete.get(position).getId());
                                                          MSOT_Main.Main_ID=""+List_Item_Methodes_incomplete.get(position).getId();
                                                          if(List_Item_Methodes_incomplete.get(position).getAudit_tech().equalsIgnoreCase("Completed")&&List_Item_Methodes_incomplete.get(position).getAudit_branch().equalsIgnoreCase("Completed")){
                                                              Intent i = new Intent(mContext, Signature_Activity.class);
                                                              i.putExtra("key_id",""+List_Item_Methodes_incomplete.get(position).getId());
                                                              i.putExtra("check","1111");
                                                              mContext.startActivity(i);
                                                          } else if (!List_Item_Methodes_incomplete.get(position).getAudit_tech().equalsIgnoreCase("Completed")) {
                                                              Intent i = new Intent(mContext, MSOT_LIST_1.class);
                                                              i.putExtra("key_id",""+List_Item_Methodes_incomplete.get(position).getId());
                                                              i.putExtra("check","1111");
                                                              mContext.startActivity(i);
                                                          }
                                                          else {
                                                              Intent i = new Intent(mContext, Page_A.class);
                                                              i.putExtra("key_id",""+List_Item_Methodes_incomplete.get(position).getId());
                                                              i.putExtra("check","1111");
                                                              mContext.startActivity(i);
                                                          }


                                                      }
                                                  });

        return view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List_Item_Methodes_incomplete.clear();
        if (charText.length() == 0) {
            List_Item_Methodes_incomplete.addAll(arraylist);
        } else {
            for (com.rentokil.pci.rauditor.Adapter.List_Item_Methodes_incomplete wp : arraylist) {
                if (wp.getCus_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    List_Item_Methodes_incomplete.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }



}