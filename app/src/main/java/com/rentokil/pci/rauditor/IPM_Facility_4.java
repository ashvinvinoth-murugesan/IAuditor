package com.rentokil.pci.rauditor;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class IPM_Facility_4 extends AppCompatActivity {

    Button ipmfacilityinfosub,ipmfacilityinfobck,ipm_facilityinfo_finish_1;


    private FirebaseAnalytics mFirebaseAnalytics;

    TextView selectrespond1;

    EditText et_product_info_1,et_raw_material_2,et_regulatory_standards_3,et_certification_standarad4;

    public  static TextView disp1;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    private android.app.AlertDialog pd;

    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    String ipm_et_product_info_1,ipm_et_raw_material_2,ipm_et_regulatory_standards_3,ipm_et_certification_standarad4,displytxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipm__facilityinfo);

        et_product_info_1 =(EditText) findViewById(R.id.ipm_facility_et_product_info);
        et_raw_material_2 =(EditText) findViewById(R.id.ipm_facility_et_raw_material);
        et_regulatory_standards_3 =(EditText) findViewById(R.id.ipm_facility_et_regulatory_standards);
        et_certification_standarad4 =(EditText) findViewById(R.id.ipm_facility_et_certification_standarad);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        db = new DatabaseHelper(IPM_Facility_4.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        pd = new SpotsDialog(IPM_Facility_4.this, R.style.Custom);

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        selectrespond1 =(TextView) findViewById(R.id.ipm_facility_selectrespon);
        disp1 =(TextView) findViewById(R.id.ipm_facility_txt_disp);


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(IPM_Facility_4.this);
            dialog.setContentView(R.layout.alertbox);
            dialog.setTitle("Ashvin");
            dialog.setCancelable(false);
            Button btnreff = (Button) dialog.findViewById(R.id.btnrefresh);
            btnreff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nInfo[0] = cManager.getActiveNetworkInfo();
                    if (nInfo[0] != null && nInfo[0].isConnected()) {

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    } else {
                        Toast.makeText(IPM_Facility_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(IPM_Facility_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }


        selectrespond1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ipm_facility_next();
                newdemos(108);
            }
        });
        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            IPM_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!IPM_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(IPM_Title_1.Main_ID);
            }

        }

        ipmfacilityinfosub =(Button) findViewById(R.id.ipmfacilityinfosub);
        ipmfacilityinfobck=(Button) findViewById(R.id.ipmfacilityinfobck);
        ipm_facilityinfo_finish_1=(Button) findViewById(R.id.ipm_facilityinfo_finish_1);


        ipm_facilityinfo_finish_1.setVisibility(View.GONE);

        ipm_facilityinfo_finish_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();
                ipm_facility_next();
                Intent i = new Intent(IPM_Facility_4.this,IPM_Obser_5.class);
                startActivity(i);

            }
        });

        ipmfacilityinfosub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(IPM_Facility_4.this);
                    dialog.setContentView(R.layout.alertbox);
                    dialog.setTitle("Ashvin");
                    dialog.setCancelable(false);
                    Button btnreff = (Button) dialog.findViewById(R.id.btnrefresh);
                    btnreff.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            nInfo[0] = cManager.getActiveNetworkInfo();
                            if (nInfo[0] != null && nInfo[0].isConnected()) {

                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            } else {
                                Toast.makeText(IPM_Facility_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(IPM_Facility_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {

                    ipm_facility_next();
                    post_ipm_facility();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();

                }
            }
        });

        ipmfacilityinfobck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_facility_back();
                Intent i = new Intent(IPM_Facility_4.this,IPM_Cus_Pest_3.class);
                startActivity(i);
            }
        });
    }

    public void newdemos(int status) {

        Bundle bundle = new Bundle();
        if (status == 108) {
            bundle.putString("status", "108");
        }
        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);


}
public  boolean validation() {
  /*  Intent i = new Intent( IPM_Facility_4.this, IPM_Obser_5.class );
    startActivity( i );*/

    try {
        ipm_et_product_info_1 = et_product_info_1.getText().toString();
        ipm_et_raw_material_2 = et_raw_material_2.getText().toString();
        ipm_et_regulatory_standards_3 = et_regulatory_standards_3.getText().toString();
        ipm_et_certification_standarad4 = et_certification_standarad4.getText().toString();
        displytxt = disp1.getText().toString();

        if (TextUtils.isEmpty(ipm_et_product_info_1) || TextUtils.isEmpty(ipm_et_raw_material_2) || TextUtils.isEmpty(ipm_et_regulatory_standards_3)
                || TextUtils.isEmpty(ipm_et_certification_standarad4) || TextUtils.isEmpty(displytxt)) {

            if (TextUtils.isEmpty(ipm_et_product_info_1)) {
                et_product_info_1.setError("Required");
            }

            if (TextUtils.isEmpty(ipm_et_raw_material_2)) {
                et_raw_material_2.setError("Required");


            }
            if (TextUtils.isEmpty(ipm_et_regulatory_standards_3)) {
                et_regulatory_standards_3.setError("Required");

            }
            if (TextUtils.isEmpty(ipm_et_certification_standarad4)) {
                et_certification_standarad4.setError("Required");

            }
            if (TextUtils.isEmpty(displytxt)) {
                disp1.setError("Required");

            }
            return false;
        }


        if (ipm_et_product_info_1.length() == 0 || ipm_et_raw_material_2.length() == 0
                || ipm_et_regulatory_standards_3.length() == 0 || ipm_et_certification_standarad4.length() == 0 ||
                displytxt.length() == 0) {
            return false;

        } else {
            return true;
        }
    } catch (Exception e) {

        e.printStackTrace();
        return false;
    }
}

    private void post_ipm_facility() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("producti",ipm_et_product_info_1);
        params.put("rawmateriali",ipm_et_raw_material_2);
        params.put("regulatoryi",ipm_et_regulatory_standards_3);
        params.put("certificationi",ipm_et_certification_standarad4);
        params.put("displaytxti",displytxt);
        params.put("main_id",IPM_Title_1.Main_ID);
        params.put("update_status",Update_Status);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/IPM/in_ipm_facility_4.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            pd.dismiss();
                            Log.e("JJJJJJ",""+response);

                            Intent i = new Intent( IPM_Facility_4.this, IPM_Obser_5.class );
                            startActivity( i );

                            //  Toast.makeText( IPM_Facility_4.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();

                        //   Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();



    }


    @Override

    public void onBackPressed(){

    }




    private void getData(String key_id){


        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            IPM_Title_1.Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/IPM/get_ipm_facility_4.php?main_id="+IPM_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            pd.dismiss();

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString("main_id");
                                String gettype = c.getString("type_faci");
                                String getproduct = c.getString("prod_info");
                                String getraw = c.getString("raw_mat");
                                String getregul = c.getString("regul_stand");
                                String getcerti = c.getString("certi_stand");

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDFCSGFS","update value = "+c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS));

                                if(get_pmca_complete.equalsIgnoreCase("2")) {

                                    Log.e("EEEE","entry 1..");

                                    ipmfacilityinfobck.setVisibility(View.GONE);
                                    ipmfacilityinfosub.setVisibility(View.GONE);
                                    ipm_facilityinfo_finish_1.setVisibility(View.VISIBLE);

                                    disp1.setText(gettype);
                                    et_product_info_1.setText(getproduct);
                                    et_raw_material_2.setText(getraw);
                                    et_regulatory_standards_3.setText(getregul);
                                    et_certification_standarad4.setText(getcerti);

                                    selectrespond1.setEnabled(false);
                                    et_product_info_1.setEnabled(false);
                                    et_raw_material_2.setEnabled(false);
                                    et_regulatory_standards_3.setEnabled(false);
                                    et_certification_standarad4.setEnabled(false);

                                    Update_Status = "2";

                                    pd.dismiss();
                                }

                                else if(get_pmca_complete.equalsIgnoreCase("1")) {

                                    Log.e("EEEE","entry 2..");


                                    disp1.setText(gettype);
                                    et_product_info_1.setText(getproduct);
                                    et_raw_material_2.setText(getraw);
                                    et_regulatory_standards_3.setText(getregul);
                                    et_certification_standarad4.setText(getcerti);


                                    Update_Status = "1";





                                }





                            }

                            //    Toast.makeText( IPM_Facility_4.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }


    private void ipm_facility_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void ipm_facility_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }


    }
