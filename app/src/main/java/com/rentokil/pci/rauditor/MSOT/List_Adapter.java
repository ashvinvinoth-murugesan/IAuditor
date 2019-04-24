package com.rentokil.pci.rauditor.MSOT;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.R;

import java.util.ArrayList;

/**
 * Created by hardik on 9/1/17.
 */
public class List_Adapter  extends BaseAdapter {

    private Context context;
    ArrayList<String> poss;
    public static ArrayList<DataModel> modelArrayList;
    private LinearLayout lay_size;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;


    public List_Adapter(Context context, ArrayList<DataModel> modelArrayList) {

        this.context = context;
        this.modelArrayList = modelArrayList;

        poss = new ArrayList<String>();

    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
         ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);
            holder.tvAnimal = (TextView) convertView.findViewById(R.id.animal);
            lay_size = (LinearLayout) convertView.findViewById(R.id.lay_size);

            convertView.setTag(holder);

        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }



//        holder.checkBox.setText(""+position);
        holder.tvAnimal.setText(modelArrayList.get(position).getAnimal());

    //    holder.checkBox.setChecked(modelArrayList.get(position).getSelected());



    //    holder.checkBox.setTag(R.integer.btnplusview, convertView);
       // holder.checkBox.setTag( position);

        //

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                try {
                    Log.e("HHHHHHHHSQD IN",position+"\t"+isChecked+"\t"+poss.size());
                    if(isChecked){


                        poss.add(""+position);
//                        modelArrayList.get(position).setSelected(true);
                    }else {
                        Log.e("HHHHHHHHSQ EL",position+"\t"+poss.size());
                        poss.remove(""+position);


                    }
                } catch (Exception e) {
                    Log.e("HHHHHHHHSQ ER",""+e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }

    private class ViewHolder {

        protected CheckBox checkBox;
        private TextView tvAnimal;


    }
    public ArrayList<String> getIndex(){

        return poss;
    }
}
