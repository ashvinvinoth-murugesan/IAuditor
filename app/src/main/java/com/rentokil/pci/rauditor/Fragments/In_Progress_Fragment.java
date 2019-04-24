package com.rentokil.pci.rauditor.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.AIB_Cus_Info_2;
import com.rentokil.pci.rauditor.AIB_Cus_Pest_3;
import com.rentokil.pci.rauditor.AIB_Ext_Obser_5_1;
import com.rentokil.pci.rauditor.AIB_Facility_4;
import com.rentokil.pci.rauditor.AIB_Interl_Obser_6;
import com.rentokil.pci.rauditor.AIB_Summary_7;
import com.rentokil.pci.rauditor.AIB_Title_1;
import com.rentokil.pci.rauditor.Adapter.ListViewAdapter_Inprogress;
import com.rentokil.pci.rauditor.Adapter.List_Item_Methodes_incomplete;
import com.rentokil.pci.rauditor.Category_Type_Activity;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.IPM_Cus_Info_2;
import com.rentokil.pci.rauditor.IPM_Cus_Pest_3;
import com.rentokil.pci.rauditor.IPM_Facility_4;
import com.rentokil.pci.rauditor.IPM_Obser_5;
import com.rentokil.pci.rauditor.IPM_Summary_6;
import com.rentokil.pci.rauditor.IPM_Title_1;
import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_LIST_1;
import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_LIST_2;
import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_Main;
import com.rentokil.pci.rauditor.MSOT_NEW.Page_A;
import com.rentokil.pci.rauditor.PMCA_Cus_Info_2;
import com.rentokil.pci.rauditor.PMCA_Obser_3;
import com.rentokil.pci.rauditor.PMCA_Summary_4;
import com.rentokil.pci.rauditor.PMCA_Title_1;
import com.rentokil.pci.rauditor.QSR_Cus_Info_2;
import com.rentokil.pci.rauditor.QSR_Cus_Pest_3;
import com.rentokil.pci.rauditor.QSR_Facility_4;
import com.rentokil.pci.rauditor.QSR_Obser_5;
import com.rentokil.pci.rauditor.QSR_Title_1;
import com.rentokil.pci.rauditor.QSR_summary_6;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.SIR_Cus_2;
import com.rentokil.pci.rauditor.SIR_Obser_3;
import com.rentokil.pci.rauditor.SIR_Summary_4;
import com.rentokil.pci.rauditor.SIR_Title_1;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class In_Progress_Fragment extends Fragment {
    private View view;
    String myJSON;
    JSONArray json_arr_cus_result = null;
    ArrayList<List_Item_Methodes_incomplete> arraylist;
    ListViewAdapter_Inprogress adapter;
    ListView List_View;

    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    TextView audittest;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;
    ContentValues cv4;
    ContentValues cv5;
    ContentValues cv7;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_incomplete__listview, container, false);
        List_View = (ListView) view.findViewById( R.id.listview_main );

        audittest =(TextView) view.findViewById(R.id.audi_name);

        cManager = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();
        cv4 = new ContentValues();
        cv5 = new ContentValues();
        cv7 = new ContentValues();

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


        arraylist = new ArrayList<List_Item_Methodes_incomplete>();
        try {
            getData();
        } catch (Exception e) {
            Log.e("JJJJK Erro",""+e.getMessage());
            e.printStackTrace();
        }
        return view;


    }

    private void getData(){

        Log.e("SSSWQW","DDD = "+ Category_Type_Activity.User_Login_Mail);

        String  url="https://rauditor.riflows.com/rauditor/Android/ia_inprogress.php?mail_id=" + Category_Type_Activity.User_Login_Mail + "";


        Log.e("SSSWQW","url = "+url);
        VolleyDataRequester.withDefaultHttps( getActivity() )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.e("FFFFFF inprogress",""+response);

                            List_Item_Methodes_incomplete wp;
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String cus_name = c.getString( "cus_name" );
                                String audi_name = c.getString( "audi_name" );
                                String audi_date = c.getString( "audi_date" );
                                String doc_no = c.getString( "doc_no" );
                                String getcount = c.getString( "count_t" );
                                String audit_tech = c.getString( "audit_tech" );
                                String  audit_branch = c.getString( "audit_branch" );
                                int id_1=Integer.parseInt( id );

                                Log.e("SDSDSD","count value = "+audit_tech);
                                Log.e("SDSDSD","audi_name = "+audit_branch);


                                wp = new List_Item_Methodes_incomplete( id_1, audi_name, cus_name ,audi_date,doc_no,getcount,audit_tech,audit_branch);
                                arraylist.add( wp );
                            }

                            adapter = new ListViewAdapter_Inprogress( getActivity(), arraylist );
                            // Binds the Adapter to the ListView
                            List_View.setAdapter( adapter );

                            List_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    // Get the selected item text from ListView
                                    String selectedItem = (String) parent.getItemAtPosition(position);
                                    List_Item_Methodes_incomplete methodes=arraylist.get(position);
                                    //  Toast.makeText(getContext(),"Success"+methodes.getCus_name(),Toast.LENGTH_SHORT).show();
                                    Log.e("AAAAAA","Audi name\t"+methodes.getAudi_name());
                                    String Auditor_Tite=methodes.getAudi_name();
                                    if(Auditor_Tite.equalsIgnoreCase("SIR")){
                                        sd.delete(db.USER_COMPLETE_STATUS,null,null);
                                        Intent intent=null;
                                        if(methodes.getcount_disp().equalsIgnoreCase("0")){


                                            intent=new Intent(getActivity(), SIR_Title_1.class);
                                        }else if(methodes.getcount_disp().equalsIgnoreCase("1")) {

                                            cv4.put(db.COMPLETE_STATUS, "1");
                                            sd.insert(db.USER_COMPLETE_STATUS, null, cv4);

                                            intent=new Intent(getActivity(), SIR_Cus_2.class);
                                        }else if(methodes.getcount_disp().equalsIgnoreCase("2")) {

                                            cv4.put(db.COMPLETE_STATUS, "1");
                                            sd.insert(db.USER_COMPLETE_STATUS, null, cv4);


                                            intent=new Intent(getActivity(), SIR_Obser_3.class);
                                        }else if(methodes.getcount_disp().equalsIgnoreCase("3")) {

                                            cv4.put(db.COMPLETE_STATUS, "1");
                                            sd.insert(db.USER_COMPLETE_STATUS, null, cv4);

                                            intent=new Intent(getActivity(), SIR_Summary_4.class);
                                        }
                                        intent.putExtra("key_id",""+""+methodes.getId());
                                        startActivity(intent);

                                    }else  if(Auditor_Tite.equalsIgnoreCase("AIB")){

                                        sd.delete(db.AIB_COMPLETE_STATUS_TABLE,null,null);
                                        Intent intent=null;
                                        if(methodes.getcount_disp().equalsIgnoreCase("0")){


                                            intent=new Intent(getActivity(), AIB_Title_1.class);
                                        }else  if(methodes.getcount_disp().equalsIgnoreCase("1")){

                                            cv3.put(db.AIB_COMPLETE_STATUS, "1");
                                            sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);
                                            intent=new Intent(getActivity(), AIB_Cus_Info_2.class);
                                        }else  if(methodes.getcount_disp().equalsIgnoreCase("2")){

                                            cv3.put(db.AIB_COMPLETE_STATUS, "1");
                                            sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);

                                            intent=new Intent(getActivity(), AIB_Cus_Pest_3.class);
                                        }else  if(methodes.getcount_disp().equalsIgnoreCase("3")){

                                            cv3.put(db.AIB_COMPLETE_STATUS, "1");
                                            sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);

                                            intent=new Intent(getActivity(), AIB_Facility_4.class);
                                        }else  if(methodes.getcount_disp().equalsIgnoreCase("4")){

                                            cv3.put(db.AIB_COMPLETE_STATUS, "1");
                                            sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);

                                            intent=new Intent(getActivity(), AIB_Ext_Obser_5_1.class);
                                        }else  if(methodes.getcount_disp().equalsIgnoreCase("5")){

                                            cv3.put(db.AIB_COMPLETE_STATUS, "1");
                                            sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);

                                            intent=new Intent(getActivity(), AIB_Interl_Obser_6.class);
                                        }else  if(methodes.getcount_disp().equalsIgnoreCase("6")){

                                            cv3.put(db.AIB_COMPLETE_STATUS, "1");
                                            sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);

                                            intent=new Intent(getActivity(), AIB_Summary_7.class);
                                        }
                                        intent.putExtra("key_id",""+methodes.getId());
                                        startActivity(intent);
                                    }else  if(Auditor_Tite.equalsIgnoreCase("PMCA")){

                                        sd.delete(db.PMCA_COMPLETE_STATUS_TABLE,null,null);
                                        Intent intent=null;
                                        if(methodes.getcount_disp().equalsIgnoreCase("0")){


                                            intent=new Intent(getActivity(), PMCA_Title_1.class);
                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("1")) {


                                            cv5.put(db.PMCA_COMPLETE_STATUS, "1");
                                            sd.insert(db.PMCA_COMPLETE_STATUS_TABLE, null, cv5);

                                            intent=new Intent(getActivity(), PMCA_Cus_Info_2.class);
                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("2")) {

                                            cv5.put(db.PMCA_COMPLETE_STATUS, "1");
                                            sd.insert(db.PMCA_COMPLETE_STATUS_TABLE, null, cv5);

                                            intent=new Intent(getActivity(), PMCA_Obser_3.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("3")) {

                                            cv5.put(db.PMCA_COMPLETE_STATUS, "1");
                                            sd.insert(db.PMCA_COMPLETE_STATUS_TABLE, null, cv5);
                                            intent=new Intent(getActivity(), PMCA_Summary_4.class);
                                        }
                                        intent.putExtra("key_id",""+methodes.getId());
                                        startActivity(intent);
                                    } if(Auditor_Tite.equalsIgnoreCase("IPM")){

                                        sd.delete(db.IPM_COMPLETE_STATUS_TABLE,null,null);

                                        Intent intent=null;
                                        if(methodes.getcount_disp().equalsIgnoreCase("0")){


                                            intent=new Intent(getActivity(), IPM_Title_1.class);
                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("1")) {

                                            cv2.put(db.IPM_COMPLETE_STATUS, "1");
                                            sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv2);

                                            intent=new Intent(getActivity(), IPM_Cus_Info_2.class);
                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("2")) {

                                            cv2.put(db.IPM_COMPLETE_STATUS, "1");
                                            sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv2);

                                            intent=new Intent(getActivity(), IPM_Cus_Pest_3.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("3")) {

                                            cv2.put(db.IPM_COMPLETE_STATUS, "1");
                                            sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv2);

                                            intent=new Intent(getActivity(), IPM_Facility_4.class);
                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("4")) {

                                            cv2.put(db.IPM_COMPLETE_STATUS, "1");
                                            sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv2);

                                            intent=new Intent(getActivity(), IPM_Obser_5.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("5")) {

                                            cv2.put(db.IPM_COMPLETE_STATUS, "1");
                                            sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv2);

                                            intent=new Intent(getActivity(), IPM_Summary_6.class);
                                        }
                                        intent.putExtra("key_id",""+methodes.getId());
                                        startActivity(intent);
                                    } if(Auditor_Tite.equalsIgnoreCase("QSR")){


                                        sd.delete(db.QSR_COMPLETE_STATUS_TABLE,null,null);
                                        Intent intent=null;

                                        if(methodes.getcount_disp().equalsIgnoreCase("0")){
                                            intent=new Intent(getActivity(), QSR_Title_1.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("1")) {

//
                                            cv1.put(db.QSR_COMPLETE_STATUS, "1");
                                            sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv1);

                                            intent=new Intent(getActivity(), QSR_Cus_Info_2.class);
                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("2")) {


                                            cv1.put(db.QSR_COMPLETE_STATUS, "1");
                                            sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv1);

                                            intent=new Intent(getActivity(), QSR_Cus_Pest_3.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("3")) {


                                            cv1.put(db.QSR_COMPLETE_STATUS, "1");
                                            sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv1);

                                            intent=new Intent(getActivity(), QSR_Facility_4.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("4")) {


                                            cv1.put(db.QSR_COMPLETE_STATUS, "1");
                                            sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv1);

                                            intent=new Intent(getActivity(), QSR_Obser_5.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("5")) {


                                            cv1.put(db.QSR_COMPLETE_STATUS, "1");
                                            sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv1);

                                            intent=new Intent(getActivity(), QSR_summary_6.class);
                                        }
                                        intent.putExtra("key_id",""+methodes.getId());
                                        startActivity(intent);
                                    }

                                    if(Auditor_Tite.equalsIgnoreCase("MSOT")){

                                        sd.delete(db.MSOT_TABLE,null,null);

                                        Intent intent=null;

                                        if(methodes.getcount_disp().equalsIgnoreCase("0")){

                                            intent=new Intent(getActivity(), MSOT_Main.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("1")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("2")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("3")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);
                                            intent=new Intent(getActivity(), Page_A.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("4")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);

                                        }else   if(methodes.getcount_disp().equalsIgnoreCase("5")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);
                                        }
                                        else   if(methodes.getcount_disp().equalsIgnoreCase("6")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);
                                        }
                                        else   if(methodes.getcount_disp().equalsIgnoreCase("7")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);
                                        }

                                        else   if(methodes.getcount_disp().equalsIgnoreCase("8")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);
                                        }

                                        else   if(methodes.getcount_disp().equalsIgnoreCase("9")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);
                                        }
                                        else   if(methodes.getcount_disp().equalsIgnoreCase("10")) {

                                            cv7.put(db.STATUS, "1");
                                            sd.insert(db.MSOT_TABLE, null, cv7);

                                            intent=new Intent(getActivity(), Page_A.class);
                                        }




                                        Log.e("EEERR","MSOT ID = "+methodes.getId());
                                        intent.putExtra("key_id",""+methodes.getId());
                                        startActivity(intent);
                                    }

                                }
                            });


//                                    Toast.makeText( getActivity(), "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("DDD","inprog error = "+error.getMessage());
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }

}
