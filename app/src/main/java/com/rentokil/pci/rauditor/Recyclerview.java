

package com.rentokil.pci.rauditor;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.chootdev.recycleclick.RecycleClick;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_Main;

import java.util.ArrayList;
import java.util.List;

public class Recyclerview extends Fragment {

    //a list to store all the products
    List<recyclercons> productList;

    //the recyclerview
    RecyclerView recyclerView;

    private View view;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_recyclerview, container, false);

        //getting the recyclerview from xml

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();

        cManager = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        if (nInfo != null && nInfo.isConnected()) {


        } else {

            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.alertbox);
            dialog.setCancelable(false);


            Button btnreff =(Button) dialog.findViewById(R.id.btnrefresh);

            btnreff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    nInfo=cManager.getActiveNetworkInfo();
                    if (nInfo != null && nInfo.isConnected()) {

                        Intent intent = getActivity().getIntent();
                        getActivity().finish();
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getActivity(), "No Internet Access ", Toast.LENGTH_SHORT).show();
                    }

                }
            });



            //Toast.makeText(getActivity(), "No Internet Access ", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                sd.delete(db.CHECK_STATUS_TABLE,null,null);


                Cursor c7;
                c7 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
                c7.moveToFirst();

                String get_country = c7.getString(c7.getColumnIndex(db.COUNTRY));
                Log.e("VVVVVVV",""+productList.get(position).getTitle());
                if(get_country.equalsIgnoreCase("India")) {
                     String Name=productList.get(position).getTitle();
                    if(Name.equalsIgnoreCase("Site Inspection Report")){


                        Intent i = new Intent(getActivity(),SIR_Title_1.class);
                        startActivity(i);

                    }
                    if(Name.equalsIgnoreCase("Pest Management Complaint Audit Report")){

                        Intent i = new Intent(getActivity(),PMCA_Title_1.class);
                        startActivity(i);

                    }
                    if(Name.equalsIgnoreCase("AIB Audit")){

                        Intent i = new Intent(getActivity(),AIB_Title_1.class);
                        startActivity(i);

                    }
                    if(Name.equalsIgnoreCase("QSR-Pest Management Audit")){

                        cv1.put(db.STATUS, "100");
                        sd.insert(db.CHECK_STATUS_TABLE, null, cv1);

                        Intent i = new Intent(getActivity(),QSR_Title_1.class);
                        startActivity(i);

                    }
                    if(Name.equalsIgnoreCase("IPM Audit")){

                        Intent i = new Intent(getActivity(),IPM_Title_1.class);
                        startActivity(i);

                    }

                    if(position==5){

                        sd.delete(db.INSERTED_MSOT_MAIN,null,null);

                        Intent i = new Intent(getActivity(), MSOT_Main.class);
                        startActivity(i);

                    }


                }else {


                    if(position==0){


                        Intent i = new Intent(getActivity(),MSOT_Main.class);
                        startActivity(i);

                    }

                }
            }
        });





        //initializing the productlist
        productList = new ArrayList<>();

        Cursor c7;
        c7 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
        c7.moveToFirst();

        String get_country = c7.getString(c7.getColumnIndex(db.COUNTRY));

        if(get_country.equalsIgnoreCase("India")) {


            //adding some items to our list
            if (db.get_check_audit(sd,"SIR")!=0) {
                productList.add(
                        new recyclercons(

                                "Site Inspection Report",
                                "All Customers",
                                R.drawable.rentokilpest));
            }
if (db.get_check_audit(sd,"PMCA")!=0) {
    productList.add(
            new recyclercons(

                    "Pest Management Complaint Audit Report",
                    "All Customers",

                    R.drawable.rentokilpest));
}
            if (db.get_check_audit(sd,"AIB")!=0) {

                productList.add(
                        new recyclercons(

                                "AIB Audit",
                                "All Customers",
                                R.drawable.rentokilpest));
            }
            if (db.get_check_audit(sd,"QSR")!=0) {
                productList.add(
                        new recyclercons(

                                "QSR-Pest Management Audit",
                                "All Customers",

                                R.drawable.rentokilpest));

            }
            Log.e("UUUUUUU MC",""+db.get_audit_count(sd));
            if (db.get_check_audit(sd,"IPM")!=0) {
                productList.add(
                        new recyclercons(

                                "IPM Audit",
                                "All Customers",

                                R.drawable.rentokilpest));
            }
            if(db.get_check_audit(sd,"MSOT")!=0) {
                productList.add(
                        new recyclercons(

                                "MSOT Audit",
                                "All Customers",

                                R.drawable.rentokilpest));

            }
        }


        //creating recyclerview adapter
        recyclerAdapter adapter = new recyclerAdapter(getActivity(), productList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        return view;

    }


}