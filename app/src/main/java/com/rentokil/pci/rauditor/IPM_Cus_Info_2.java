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

public class IPM_Cus_Info_2 extends AppCompatActivity {

    Button ipmcusinfosub,ipmcusinfobck,ipm_cusinfo_finish_1;

    EditText qsr_cusinfo_et_area,qsr_cusinfo_et_contract,qsr_cusinfo_0ther;

    RadioGroup qsr_cusinfo_radiob1,ipm_cusinfo_r2;

    private FirebaseAnalytics mFirebaseAnalytics;


    private RadioButton radiobtn,radiobtn2;

    TextView qsr_cusinfo_selectrs1,qsr_cusinfo_selectrs2;

    public  static TextView  ipm_cusinfo_disp1,ipm_cusinfo_disp2;

    String getarea,getcontract,getother,getdisp1,getdisp2,getrad1,getrad2;

    ConnectivityManager cManager;
    NetworkInfo nInfo;
    private android.app.AlertDialog pd;
    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipm__cusinfo);

        ipmcusinfosub =(Button) findViewById(R.id.ipmcusinfosub);
        ipmcusinfobck=(Button) findViewById(R.id.ipmcusinfobck);
        ipm_cusinfo_finish_1=(Button) findViewById(R.id.ipm_cusinfo_finish_1);

        qsr_cusinfo_et_area=(EditText) findViewById(R.id.qsr_cusinfo_et_area);
        qsr_cusinfo_et_contract=(EditText) findViewById(R.id.qsr_cusinfo_et_contract);
        qsr_cusinfo_0ther=(EditText) findViewById(R.id.qsr_cusinfo_0ther);
        qsr_cusinfo_selectrs1=(TextView) findViewById(R.id.qsr_cusinfo_selectrs1);
        qsr_cusinfo_selectrs2=(TextView) findViewById(R.id.qsr_cusinfo_selectrs2);

        ipm_cusinfo_disp1=(TextView) findViewById(R.id.qsr_cusinfo_disp1);
        ipm_cusinfo_disp2=(TextView) findViewById(R.id.qsr_cusinfo_disp2);

        db = new DatabaseHelper(IPM_Cus_Info_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        ipm_cusinfo_finish_1.setVisibility(View.GONE);

        ipm_cusinfo_finish_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                ipm_cus_info_next();

                Intent i = new Intent(IPM_Cus_Info_2.this,IPM_Cus_Pest_3.class);
                startActivity(i);

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
        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(IPM_Cus_Info_2.this);
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
                        Toast.makeText(IPM_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(IPM_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }


        pd = new SpotsDialog(IPM_Cus_Info_2.this, R.style.Custom);
        qsr_cusinfo_radiob1=(RadioGroup) findViewById(R.id.qsr_cusinfo_radiob1);

        ipm_cusinfo_r2=(RadioGroup) findViewById(R.id.ipm_cusinfo_r2);


        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        qsr_cusinfo_selectrs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                newdemoselect(103);
            }
        });

        qsr_cusinfo_selectrs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                newdemoselect(104);
            }
        });


        qsr_cusinfo_radiob1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=qsr_cusinfo_radiob1.getCheckedRadioButtonId();

                radiobtn=(RadioButton)findViewById(selectedId);


                getrad1 = ((RadioButton)findViewById(qsr_cusinfo_radiob1.getCheckedRadioButtonId())).getText().toString();



            }
        });

        qsr_cusinfo_radiob1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=qsr_cusinfo_radiob1.getCheckedRadioButtonId();

                radiobtn=(RadioButton)findViewById(selectedId);


                getrad1 = ((RadioButton)findViewById(qsr_cusinfo_radiob1.getCheckedRadioButtonId())).getText().toString();



            }
        });

        ipm_cusinfo_r2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cusinfo_r2.getCheckedRadioButtonId();

                radiobtn2=(RadioButton)findViewById(selectedId);


                getrad2 = ((RadioButton)findViewById(ipm_cusinfo_r2.getCheckedRadioButtonId())).getText().toString();



            }
        });








        ipmcusinfosub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(IPM_Cus_Info_2.this);
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
                                Toast.makeText(IPM_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(IPM_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }


                if (validation()) {

                    ipm_cus_info_next();
                    post_ipm_cus_js();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fileds",Toast.LENGTH_SHORT ).show();
                }

            }
        });

        ipmcusinfobck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_cus_info_next_back();
                Intent i = new Intent(IPM_Cus_Info_2.this,IPM_Title_1.class);
                startActivity(i);
            }
        });
    }
    public  boolean validation(){






        getarea=qsr_cusinfo_et_area.getText().toString();
        getcontract=qsr_cusinfo_et_contract.getText().toString();
        getother=qsr_cusinfo_0ther.getText().toString();
        getdisp1=ipm_cusinfo_disp1.getText().toString();
        getdisp2=ipm_cusinfo_disp2.getText().toString();

        if(TextUtils.isEmpty(getarea ) || TextUtils.isEmpty(getcontract) ||TextUtils.isEmpty(getother)
                ||TextUtils.isEmpty(getdisp1) ||TextUtils.isEmpty(getdisp2) ) {

            if(TextUtils.isEmpty(getarea )){
                qsr_cusinfo_et_area.setError("Required");
            }

            if(TextUtils.isEmpty(getcontract)) {
                qsr_cusinfo_et_contract.setError("Required");


            }
            if(TextUtils.isEmpty(getother)) {
                qsr_cusinfo_0ther.setError("Required");

            }
            if(TextUtils.isEmpty(getdisp1)) {
                ipm_cusinfo_disp1.setError("Required");

            }
            if(TextUtils.isEmpty(getdisp2)) {
                ipm_cusinfo_disp2.setError("Required");

            }
            return false;
        }


        if(getarea.length()==0||getcontract.length()==0||getdisp1.length()==0||getdisp2.length()==0){
            return false;
        }else {
            return true;
        }
    }

    public void newdemoselect(int status) {

        Bundle bundle = new Bundle();
        if(status==103) {
            bundle.putString("status", "103");
        }if(status==104){

            bundle.putString("status", "104");

        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    @Override

    public void onBackPressed(){

    }

    private void post_ipm_cus_js() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("areaz",getarea);
        params.put("main_id",IPM_Title_1.Main_ID);
        params.put("contractz",getcontract);
        params.put("otherz",getother);
        params.put("disp1z",getdisp1);
        params.put("disp2z",getdisp2);
        params.put("gradio1z",getrad1);
        params.put("gradio2z",getrad2);
        params.put("update_status",Update_Status);


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/IPM/in_ipm_cus_2.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();

                            //    Toast.makeText(IPM_Cus_Info_2.this,response,Toast.LENGTH_LONG).show();
                            Log.e("JJJJJ IPM","222\t"+response);
                            Intent i = new Intent(IPM_Cus_Info_2.this,IPM_Cus_Pest_3.class);
                            startActivity(i);



                            // Toast.makeText( IPM_Cus_Info_2.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
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



    private void getData(String key_id){



        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            IPM_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/IPM/get_ipm_cus_2.php?main_id="+IPM_Title_1.Main_ID;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            pd.dismiss();

                            Log.e( "JJJJJJ","ipm cus 2 res ="+response );
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "main_id" );
                                String getarea = c.getString( "area" );
                                String getcontract = c.getString( "contract_value" );
                                String getpest = c.getString( "pest_cover" );
                                String getother = c.getString( "other_service" );
                                String getservice = c.getString( "service_contract" );
                                String get_contract = c.getString( "contract" );
                                String get_cus_sensitive = c.getString( "cus_sensi" );

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDF123","update value = "+c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS)));

                                String getsircuscomp = c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS));

                                Log.e( "JJJJJJ","entry0"+getsircuscomp );

                                if(getsircuscomp.equalsIgnoreCase("2")) {



                                    Log.e( "JJJJJJ","entry1" );


                                    ipmcusinfosub.setVisibility(View.GONE);
                                    ipmcusinfobck.setVisibility(View.GONE);
                                    ipm_cusinfo_finish_1.setVisibility(View.VISIBLE);


                                    qsr_cusinfo_et_area.setText(getarea);
                                    qsr_cusinfo_et_contract.setText(getcontract);
                                    ipm_cusinfo_disp2.setText(getpest);
                                    qsr_cusinfo_0ther.setText(getother);
                                    ipm_cusinfo_disp1.setText(getservice);

                                    qsr_cusinfo_et_area.setEnabled(false);
                                    qsr_cusinfo_et_contract.setEnabled(false);
                                    qsr_cusinfo_selectrs1.setEnabled(false);
                                    qsr_cusinfo_0ther.setEnabled(false);
                                    qsr_cusinfo_selectrs2.setEnabled(false);


                                    qsr_cusinfo_radiob1.getChildAt(0).setEnabled(false);
                                    qsr_cusinfo_radiob1.getChildAt(1).setEnabled(false);

                                    ipm_cusinfo_r2.getChildAt(0).setEnabled(false);
                                    ipm_cusinfo_r2.getChildAt(1).setEnabled(false);



                                    if(get_contract.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_contract.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(1)).setChecked(true);

                                    }

                                    if(get_cus_sensitive.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) ipm_cusinfo_r2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_cus_sensitive.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) ipm_cusinfo_r2.getChildAt(1)).setChecked(true);

                                    }


                                    Update_Status = "2";

                                    pd.dismiss();

                                } else  if(getsircuscomp.equalsIgnoreCase("1")) {

                                    Log.e( "JJJJJJ","entry2" );


                                    qsr_cusinfo_et_area.setText(getarea);
                                    qsr_cusinfo_et_contract.setText(getcontract);
                                    ipm_cusinfo_disp2.setText(getpest);
                                    qsr_cusinfo_0ther.setText(getother);
                                    ipm_cusinfo_disp1.setText(getservice);

                                    if(get_contract.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_contract.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(1)).setChecked(true);

                                    }

                                    if(get_cus_sensitive.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) ipm_cusinfo_r2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_cus_sensitive.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) ipm_cusinfo_r2.getChildAt(1)).setChecked(true);

                                    }


                                    Update_Status = "1";
                                }
                            }

                            //  Toast.makeText( IPM_Cus_Info_2.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );
                        pd.dismiss();
                        Log.e( "JJJJJJ","ipm cus 2 error"+error.getMessage() );
                    }
                } )
                .requestJson();
    }


    private void ipm_cus_info_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void ipm_cus_info_next_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }





}
