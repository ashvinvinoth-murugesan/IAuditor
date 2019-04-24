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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class AIB_Facility_4 extends AppCompatActivity {

    Button aibfacilityinfosub, aibfacilityinfobck,aib_facility_finish1;
    RadioGroup radi1, radi2, radi3;
    RadioButton radbtn1, radbtn2, radbtn3;
    EditText reg_et1, certi_et2;
    String getradio1, getradio2, getradio3, getet1, gete2;
    ConnectivityManager cManager;
    NetworkInfo nInfo;


    private FirebaseAnalytics mFirebaseAnalytics;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status = "0";


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    private android.app.AlertDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aib__facilityinfo);


        aibfacilityinfosub = (Button) findViewById(R.id.aibfacilitysub);
        aibfacilityinfobck = (Button) findViewById(R.id.aibfacilitybck);
        aib_facility_finish1 = (Button) findViewById(R.id.aib_facility_finish1);


        aib_facility_finish1.setVisibility(View.GONE);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        aib_facility_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                aib_facility_button_next();

                Intent i = new Intent(AIB_Facility_4.this,AIB_Ext_Obser_5_1.class);
                startActivity(i);

            }
        });

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();


        reg_et1 = (EditText) findViewById(R.id.aib_faci_et1);
        certi_et2 = (EditText) findViewById(R.id.aib_faci_et2);
        pd = new SpotsDialog(AIB_Facility_4.this, R.style.Custom);

        radi1 = (RadioGroup) findViewById(R.id.aib_faci_rd1);
        radi2 = (RadioGroup) findViewById(R.id.aib_faci_rd2);
        radi3 = (RadioGroup) findViewById(R.id.aib_faci_rd3);

        db = new DatabaseHelper(AIB_Facility_4.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Facility_4.this);
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
                        Toast.makeText(AIB_Facility_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Facility_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }



        radi1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = radi1.getCheckedRadioButtonId();

                radbtn1 = (RadioButton) findViewById(selectedId);

                getradio1 = ((RadioButton) findViewById(radi1.getCheckedRadioButtonId())).getText().toString();

            }
        });

        radi2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = radi2.getCheckedRadioButtonId();

                radbtn2 = (RadioButton) findViewById(selectedId);

                getradio2 = ((RadioButton) findViewById(radi2.getCheckedRadioButtonId())).getText().toString();

            }
        });

        radi3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = radi3.getCheckedRadioButtonId();

                radbtn3 = (RadioButton) findViewById(selectedId);

                getradio3 = ((RadioButton) findViewById(radi3.getCheckedRadioButtonId())).getText().toString();

            }
        });

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            AIB_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!AIB_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(AIB_Title_1.Main_ID);
            }

        }


        aibfacilityinfosub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {


                } else {
                    final Dialog dialog = new Dialog(AIB_Facility_4.this);
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
                                Toast.makeText(AIB_Facility_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Facility_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }


                if (validation()) {

                    aib_facility_button_next();
                    post_aibfaci_js();
                } else {

                    Toast.makeText(getApplicationContext(), "Please fill all mandatory fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        aibfacilityinfobck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_facility_button_back();

                Intent i = new Intent(AIB_Facility_4.this, AIB_Cus_Pest_3.class);
                startActivity(i);


            }
        });
    }

    public boolean validation() {


        try {
            getradio1 = ((RadioButton) findViewById(radi1.getCheckedRadioButtonId())).getText().toString();
            getradio2 = ((RadioButton) findViewById(radi2.getCheckedRadioButtonId())).getText().toString();
            getradio3 = ((RadioButton) findViewById(radi3.getCheckedRadioButtonId())).getText().toString();
            getet1 = reg_et1.getText().toString();
            gete2 = certi_et2.getText().toString();

            if (TextUtils.isEmpty(getet1) || TextUtils.isEmpty(gete2)) {

                if (TextUtils.isEmpty(getet1)) {
                    reg_et1.setError("Required");
                }

                if (TextUtils.isEmpty(gete2)) {
                    certi_et2.setError("Required");


                }

                return false;
            }


            if ((getradio1.length() == 0 || getradio2.length() == 0 || getradio3.length() == 0 || getet1.length() == 0 || gete2.length() == 0)) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }


    }


    private void post_aibfaci_js() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("rad1", getradio1);
        params.put("rad2", getradio2);
        params.put("rad3", getradio3);
        params.put("et1", getet1);
        params.put("et2", gete2);
        params.put("update_status", Update_Status);

        params.put("main_id", AIB_Title_1.Main_ID);

        Log.e("FDFD","params= "+params);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_faci.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();

                            Log.e("EEE","aib4 res ="+response);
                            Intent i = new Intent(AIB_Facility_4.this, AIB_Ext_Obser_5_1.class);
                            startActivity(i);


                            //      Toast.makeText( AIB_Facility_4.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e("EEESF","ERRRRRr"+error.getMessage());

                        if(error.getMessage().equalsIgnoreCase("null")){

                            Intent i = new Intent(AIB_Facility_4.this,Category_Type_Activity.class);
                            startActivity(i);
                        }
                        else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                        {
                            Intent i = new Intent(AIB_Facility_4.this,Category_Type_Activity.class);
                            startActivity(i);

                        }

                        //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }


    private void getData(String key_id){

pd.show();

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            AIB_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_aib_faci_4.php?main_id=" + AIB_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            Log.e("GFGFS","get res = "+response);
                            pd.dismiss();

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString("main_id");
                                String getregu = c.getString("regu_stan");
                                String getcerti = c.getString("certi_stan");
                                String get_written_daily = c.getString("written_daily");
                                String get_type_faci = c.getString("type_faci");
                                String get_ind_vacum = c.getString("ind_vacum");

                                Log.e("EEED","before entry00");
                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDF123SS","update value = "+c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS)));

                                String getsircuscomp = c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS));

                                Log.e("EEED","before entry11");

                                if(getsircuscomp.equalsIgnoreCase("2")) {



                                    Log.e("EEGFG","e1");

                                    aibfacilityinfobck.setVisibility(View.GONE);
                                    aibfacilityinfosub.setVisibility(View.GONE);
                                    aib_facility_finish1.setVisibility(View.VISIBLE);

                                    reg_et1.setText(getregu);
                                    certi_et2.setText(getcerti);

                                    reg_et1.setEnabled(false);
                                    certi_et2.setEnabled(false);


                                    radi1.getChildAt(0).setEnabled(false);
                                    radi1.getChildAt(1).setEnabled(false);
                                    radi1.getChildAt(2).setEnabled(false);

                                    radi2.getChildAt(0).setEnabled(false);
                                    radi2.getChildAt(1).setEnabled(false);


                                    radi3.getChildAt(0).setEnabled(false);
                                    radi3.getChildAt(1).setEnabled(false);




                                    if (get_type_faci.equalsIgnoreCase("FOOD & BEVERAGE MANUFACTURING")) {
                                        ((RadioButton) radi1.getChildAt(0)).setChecked(true);


                                    } else if (get_type_faci.equalsIgnoreCase("FOOD PACKAGING")) {

                                        ((RadioButton) radi1.getChildAt(1)).setChecked(true);

                                    } else if (get_type_faci.equalsIgnoreCase("PHARMA")) {

                                        ((RadioButton) radi1.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_written_daily.equalsIgnoreCase("YES")) {
                                        ((RadioButton) radi2.getChildAt(0)).setChecked(true);


                                    } else if (get_written_daily.equalsIgnoreCase("NO")) {

                                        ((RadioButton) radi2.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_ind_vacum.equalsIgnoreCase("YES")) {
                                        ((RadioButton) radi3.getChildAt(0)).setChecked(true);


                                    } else if (get_ind_vacum.equalsIgnoreCase("NO")) {

                                        ((RadioButton) radi3.getChildAt(1)).setChecked(true);

                                    }

                                    Update_Status="2";

                                    pd.dismiss();


                                }
                                else if(getsircuscomp.equalsIgnoreCase("1")){

                                    Log.e("EEGFG","e2");

                                    reg_et1.setText(getregu);
                                    certi_et2.setText(getcerti);

                                    if (get_type_faci.equalsIgnoreCase("FOOD & BEVERAGE MANUFACTURING")) {
                                        ((RadioButton) radi1.getChildAt(0)).setChecked(true);


                                    } else if (get_type_faci.equalsIgnoreCase("FOOD PACKAGING")) {

                                        ((RadioButton) radi1.getChildAt(1)).setChecked(true);

                                    } else if (get_type_faci.equalsIgnoreCase("PHARMA")) {

                                        ((RadioButton) radi1.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_written_daily.equalsIgnoreCase("YES")) {
                                        ((RadioButton) radi2.getChildAt(0)).setChecked(true);


                                    } else if (get_written_daily.equalsIgnoreCase("NO")) {

                                        ((RadioButton) radi2.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_ind_vacum.equalsIgnoreCase("YES")) {
                                        ((RadioButton) radi3.getChildAt(0)).setChecked(true);


                                    } else if (get_ind_vacum.equalsIgnoreCase("NO")) {

                                        ((RadioButton) radi3.getChildAt(1)).setChecked(true);

                                    }

                                    Update_Status="1";


                                }









                            }

                            // Toast.makeText( AIB_Facility_4.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("FDFD","ERRRRRr"+error.getMessage());

                        pd.dismiss();
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }


    @Override

    public void onBackPressed(){

    }



    private void aib_facility_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_facility_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }




}
