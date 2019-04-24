package com.rentokil.pci.rauditor.Fragments;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rentokil.pci.rauditor.CustomExpandableListAdapter;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.ExpandableListDataPump;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.SIR_Cus_2;
import com.rentokil.pci.rauditor.SIR_Obser_3;
import com.rentokil.pci.rauditor.SIR_Summary_4;
import com.rentokil.pci.rauditor.SIR_Title_1;
import com.rentokil.pci.rauditor.Splash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Dashboard extends Fragment {
    android.widget.ExpandableListView expandableListView;
    Spinner search_audit;
    List<String> expandableListTitle;
    HashMap<String, List<String>> expandableListDetail;

    private View view;
    TextView Header;
    TextView Y_INC_T,Y_COM_T,M_INC_T,M_COM_T,W_INC_T,W_COM_T;
    ArrayAdapter Adapter_branch, Adapter_riss;

    String [] values =
            {"SIR","PMCA","AIB","QSR","IPM","MSOT"};
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_dashboard, container, false);

        search_audit = (Spinner) view.findViewById(R.id.search_audit);
        Y_COM_T = (TextView) view.findViewById(R.id.y_com_text);
        Y_INC_T = (TextView) view.findViewById(R.id.y_inc_text);
        M_INC_T = (TextView) view.findViewById(R.id.m_inc_text);
        M_COM_T = (TextView) view.findViewById(R.id.m_com_text);
        W_INC_T = (TextView) view.findViewById(R.id.w_inc_text);
        W_COM_T = (TextView) view.findViewById(R.id.w_com_text);
        Header=(TextView) view.findViewById(R.id.header);
        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv=new ContentValues(  );
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        search_audit.setAdapter(adapter);

          search_audit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
              @Override
              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                  Header.setText(search_audit.getSelectedItem().toString());
                  String Audit=search_audit.getSelectedItem().toString();
                  get_audit_count_offline(Audit);

              }

              @Override
              public void onNothingSelected(AdapterView<?> parent) {

              }
          });





        return view;
    }
    public void get_audit_count_offline(String audit_name){
        String selectQuery = "SELECT  * FROM " + db.DASHBOARD_TB +" where AUDIT_NAME = '" +audit_name+"'";
        Cursor cursor = sd.rawQuery(selectQuery, null);

        if(cursor.getCount()!=0) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                String audit_type=cursor.getString(cursor.getColumnIndex(db.AUDIT_NAME));
                String type=cursor.getString(cursor.getColumnIndex(db.STATE));
                if(audit_type.equalsIgnoreCase("SIR")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                } if(audit_type.equalsIgnoreCase("PMCA")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }if(audit_type.equalsIgnoreCase("AIB")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }if(audit_type.equalsIgnoreCase("QSR")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }if(audit_type.equalsIgnoreCase("IPM")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }if(audit_type.equalsIgnoreCase("MSOT")){
                    if(type.equalsIgnoreCase("Month")){
                        M_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        M_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));

                    }else if(type.equalsIgnoreCase("Week")) {
                        W_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        W_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }else if(type.equalsIgnoreCase("Year")) {
                        Y_COM_T.setText(cursor.getString(cursor.getColumnIndex(db.COMPLETED)));
                        Y_INC_T.setText(cursor.getString(cursor.getColumnIndex(db.IN_PROGRESS)));
                    }

                }
              cursor.moveToNext();
            }

        }

    }

}
