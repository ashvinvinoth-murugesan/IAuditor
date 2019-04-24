package com.rentokil.pci.rauditor.Adapter;

/**
 * Created by Murugan Kuppusamy on 23-Oct-17.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rentokil.pci.rauditor.MSOT_NEW.Pdf_Viewer;
import com.rentokil.pci.rauditor.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class ListViewAdapter_Completed extends BaseAdapter {

    // Declare Variables


    Context mContext;
    static int half_visible;
    ArrayList<Integer> poss;
    LayoutInflater inflater;
    private List<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes> List_Item_Methodes = null;
    private ArrayList<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes> arraylist;
    public ListViewAdapter_Completed(Context context, ArrayList<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes> List_Item_Methodes) {
          mContext = context;
        this.List_Item_Methodes = List_Item_Methodes;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<com.rentokil.pci.rauditor.Adapter.List_Item_Methodes>();
        this.arraylist.addAll(List_Item_Methodes);


        //poss = new ArrayList<Integer>();
    }


/* //   public int getCount() {
        return customer_name.length;
    }*/

    @Override
    public int getCount() {
        return List_Item_Methodes.size();
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
        TextView Tx_status;
        TextView View_Bt;

    }
    @SuppressLint("ResourceAsColor")
    public View getView(final int position, View view, final ViewGroup parent) {
        // Declare Variables
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.list_item_completed, null);

                holder.Tx_Audi_Name = (TextView) view.findViewById(R.id.audi_name);
                holder.Tx_Cus_Name = (TextView) view.findViewById(R.id.cus_name);
                holder.Tx_Audi_Date = (TextView) view.findViewById(R.id.audi_date);
                holder.Tx_Doc_No = (TextView) view.findViewById(R.id.doc_no);
                holder.Tx_status = (TextView) view.findViewById(R.id.complet);
                holder.View_Bt = (Button) view.findViewById(R.id.view_bt);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Locate the TextViews in listview_item.xml
        // Capture position and set to the TextViews
        holder.Tx_Cus_Name.setText(List_Item_Methodes.get(position).getCus_name());
        holder.Tx_Audi_Name.setText(List_Item_Methodes.get(position).getAudi_name());
        holder.Tx_Audi_Date.setText(List_Item_Methodes.get(position).getAudi_date());
        holder.Tx_Doc_No.setText(List_Item_Methodes.get(position).getDoc_no());
        holder.Tx_status.setText(List_Item_Methodes.get(position).getStatus());
        holder.View_Bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("JJJJSS","E1");

                if(List_Item_Methodes.get(position).getAudi_name().equalsIgnoreCase("MSOT")){



                    Log.e("KKKKKSS","PPPP = "+List_Item_Methodes.get(position).getId());



                    Intent i = new Intent(mContext, Pdf_Viewer.class);
                    i.putExtra("main_id",""+""+List_Item_Methodes.get(position).getId());
                    mContext.startActivity(i);


//                    Intent intent=new Intent(mContext, Msot_View_pdf.class);
//                    intent.putExtra("main_id",List_Item_Methodes.get(position).getId());
//
//                    mContext.startActivity(intent);



                }else {

                    Log.e("JJJJSS","E3");

                    Toast.makeText(mContext,"Under Development",Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        List_Item_Methodes.clear();
        if (charText.length() == 0) {
            List_Item_Methodes.addAll(arraylist);
        } else {
            for (com.rentokil.pci.rauditor.Adapter.List_Item_Methodes wp : arraylist) {
                if (wp.getCus_name().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    List_Item_Methodes.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }






}