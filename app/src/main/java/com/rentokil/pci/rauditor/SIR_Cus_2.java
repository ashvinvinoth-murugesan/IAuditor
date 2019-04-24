package com.rentokil.pci.rauditor;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class SIR_Cus_2 extends AppCompatActivity {


    Button cusbck,cussub,sir_cus_complete;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    TextView sircusbtn1,sircusbtn2;
     public  static TextView sircustxt1,sircustxt2;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    private FirebaseAnalytics mFirebaseAnalytics;

    String restxt1,restxt2,edtother;

    EditText sircusothrpst;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;

    private android.app.AlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sir_cusinfo);

        cussub =(Button) findViewById(R.id.sircussub);
        cusbck=(Button) findViewById(R.id.sircusback);
        sir_cus_complete=(Button) findViewById(R.id.sir_cus_complete);

        sir_cus_complete.setVisibility(View.GONE);

        sir_cus_complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                Intent i = new Intent(SIR_Cus_2.this,SIR_Obser_3.class);
                startActivity(i);
            }
        });
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        sircusbtn1=(TextView) findViewById(R.id.sircusbtn1);
        sircusbtn2=(TextView) findViewById(R.id.sircusbtn2);
        sircustxt1=(TextView) findViewById(R.id.sircustxt1);
        sircustxt2=(TextView) findViewById(R.id.sircustxt2);
        sircusothrpst= (EditText) findViewById(R.id.sircusothrpst);
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();
        db = new DatabaseHelper(SIR_Cus_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
//        getData();

        pd = new SpotsDialog(SIR_Cus_2.this, R.style.Custom);


        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        Log.e("FFFFF Main_ID 222",""+SIR_Title_1.Main_ID);
        cussub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(SIR_Cus_2.this);
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
                                Toast.makeText(SIR_Cus_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(SIR_Cus_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {

                    sir_cus_button_next();
                    sircusjson();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
                }

            }
        });

        cusbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sir_cus_button_back();

                Log.e("BBBB","back pressed");
                Intent i = new Intent(SIR_Cus_2.this,SIR_Title_1.class);
                startActivity(i);
            }
        });

        sircusbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                newdemo(111);
            }
        });

        sircusbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                newdemo(222);

            }
        });
        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            SIR_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!SIR_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(SIR_Title_1.Main_ID);
            }

        }
    }

    public void newdemo(int status) {

        Bundle bundle = new Bundle();
        if(status==111) {
            bundle.putString("status", "111");
        }if(status==222){
            bundle.putString("status", "222");
        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }
    public boolean validation(){

        restxt1=sircustxt1.getText().toString();
        restxt2=sircustxt2.getText().toString();
        edtother=sircusothrpst.getText().toString();

        if(TextUtils.isEmpty(restxt1) || TextUtils.isEmpty(restxt2) ||TextUtils.isEmpty(edtother)) {


            if(TextUtils.isEmpty(restxt1 )){
                sircustxt1.setError("Required");
            }

            if(TextUtils.isEmpty(restxt2)) {
                sircustxt2.setError("Required");


            }
            if(TextUtils.isEmpty(edtother)) {
                sircusothrpst.setError("Required");

            }



            return false;
        }
        if(restxt1.length()==0||restxt2.length()==0||edtother.length()==0){
            return false;

        }else {
            return  true;
        }
    }


    private void sir_cus_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_2");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_2_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

    private void sir_cus_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_2");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_2_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }



    private void sircusjson() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("sircustext1",restxt1);
        params.put("sircustext2",restxt2);
        params.put("sircusedtother",edtother);
        params.put("main_id",SIR_Title_1.Main_ID);
        params.put("update_status",Update_Status);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/SIR/in_sir_cus_2.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener(new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();
                            Log.e("DDDDDD","sircus2 res"+response);
//                            String data = response.getString( "message" );
                            Intent i = new Intent(SIR_Cus_2.this,SIR_Obser_3.class);
                            startActivity(i);
                            //   Toast.makeText(SIR_Cus_2.this,response,Toast.LENGTH_LONG).show();
                            Log.e( "JJJJJJJ","Res\t"+response );
//
//                            Toast.makeText( SIR_Cus_2.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        Log.e("DDDDDD","error cus2  = "+error.getMessage());
                        Intent i = new Intent(SIR_Cus_2.this,SIR_Obser_3.class);
                        startActivity(i);

//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }


    @Override

    public void onBackPressed(){

     }



    private void getData(String key_id){

        pd.show();

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            SIR_Title_1.Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/SIR/get_sir_cus_2.php?main_id="+SIR_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("CVFCV"," sir cus 2 getres "+response);

                            pd.dismiss();
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            if (jsonArray_get.length()!=0) {
                                for (int i = 0; i < jsonArray_get.length(); i++) {

                                    Log.e("CVFCV","e1");


                                    JSONObject c = jsonArray_get.getJSONObject(i);
                                    String id = c.getString( "main_id" );
                                    String getlocation = c.getString( "location" );
                                    String getpestcover = c.getString( "pest_cover" );
                                    String getother = c.getString( "other" );
                                    Log.e("CVFCV","e2");
                                    Cursor c5;
                                    c5 = sd.rawQuery("Select * from " + db.USER_COMPLETE_STATUS, null);
                                    c5.moveToFirst();

                                    Log.e("SSSSS","update value = "+c5.getString(c5.getColumnIndex(db.COMPLETE_STATUS)));
                                    Log.e("CVFCV","e3");
                                    String getsircuscomp = c5.getString(c5.getColumnIndex(db.COMPLETE_STATUS));
                               if(getsircuscomp.equalsIgnoreCase("2")){



                                   cusbck.setVisibility(View.GONE);

                                   cussub.setVisibility(View.GONE);

                                   sir_cus_complete.setVisibility(View.VISIBLE);


                                   sircusbtn1.setEnabled(false);
                                   sircusbtn2.setEnabled(false);
                                   sircustxt2.setEnabled(false);
                                   sircusothrpst.setEnabled(false);
                                   sircustxt1.setText(getlocation);
                                   sircustxt2.setText(getpestcover);
                                   sircusothrpst.setText(getother);
                                   Update_Status="2";

                                   pd.dismiss();



                               }
                               else if(getsircuscomp.equalsIgnoreCase("1")){

                                   Log.e("DFDFTYU","sircus else");
                                   sircustxt1.setText(getlocation);
                                   sircustxt2.setText(getpestcover);
                                   sircusothrpst.setText(getother);
                                   Update_Status = "1";
                               }

                                }
                            }

//                            Toast.makeText( SIR_Cus_2.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e("FFFFF err HH",e.getMessage());
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Log.e("FFFFF err","sir cus2"+error.getMessage());
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }




}
