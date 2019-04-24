package com.rentokil.pci.rauditor;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
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

public class PMCA_Cus_Info_2 extends AppCompatActivity {

    Button pestcusinsub,pestcusinbck,pestman_cusinfo_sub;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    private FirebaseAnalytics mFirebaseAnalytics;


    EditText pestcusinfoarea,pestcusinfotherservice;

    TextView pestcusresponsebtn2,pestcusresponsebtn3;

    String pestcusinfoareaq,pestcusinfotherserviceq,pestcusdisp2q,pestcusdisp3q;

    public  static TextView pestcusdisp2,pestcusdisp3;

    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    private android.app.AlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pestman_cusinfo);

        db = new DatabaseHelper(PMCA_Cus_Info_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();
        pd = new SpotsDialog(PMCA_Cus_Info_2.this, R.style.Custom);

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(PMCA_Cus_Info_2.this);
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
                        Toast.makeText(PMCA_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(PMCA_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }



        pestcusinsub=(Button) findViewById(R.id.pestancusinfsub);
        pestcusinbck=(Button) findViewById(R.id.pestancusinfbck);
        pestman_cusinfo_sub=(Button) findViewById(R.id.pestman_cusinfo_sub);

        pestman_cusinfo_sub.setVisibility(View.GONE);

        pestman_cusinfo_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                pmca_cus_info_button_next();

                Intent i = new Intent(PMCA_Cus_Info_2.this,PMCA_Obser_3.class);
                startActivity(i);
                finish();
            }
        });


        pestcusresponsebtn2=(TextView) findViewById(R.id.pestcusbtn2);
        pestcusresponsebtn3=(TextView) findViewById(R.id.pestcusbtn3);

        pestcusdisp2=(TextView) findViewById(R.id.pestcusdis2);
        pestcusdisp3=(TextView) findViewById(R.id.pestcusdis3);

        pestcusinfoarea=(EditText) findViewById(R.id.pestcusinfoarea);
        pestcusinfotherservice=(EditText) findViewById(R.id.pestcusinfotherservice);
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        pestcusinsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {

                } else {
                    final Dialog dialog = new Dialog(PMCA_Cus_Info_2.this);
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
                                Toast.makeText(PMCA_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(PMCA_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }



                if (validation()) {

                    pmca_cus_info_button_next();
                    post_pest_cus_js();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory",Toast.LENGTH_SHORT ).show();
                }


            }
        });

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){

            Log.e("VVVCC","pmca main id = "+PMCA_Title_1.Main_ID);
            PMCA_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!PMCA_Title_1.Main_ID.equals("0")) {
                // String keyid=null;

                Log.e("VVVCC","pmca main id else = "+PMCA_Title_1.Main_ID);
                getData(PMCA_Title_1.Main_ID);
            }

        }

        pestcusinbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pmca_cus_info_button_back();

                Intent i = new Intent(PMCA_Cus_Info_2.this,PMCA_Title_1.class);
                startActivity(i);


            }
        });

        pestcusresponsebtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pescusresponse(777);
            }
        });
        pestcusresponsebtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pescusresponse(888);


            }
        });
    }

    public void pescusresponse(int status) {

        Bundle bundle = new Bundle();
        if(status==777) {
            bundle.putString("status", "777");
        }if(status==888){

            bundle.putString("status", "888");
        }


        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }
    @Override

    public void onBackPressed(){

    }
    public boolean validation(){


        pestcusinfoareaq= pestcusinfoarea.getText().toString();
        pestcusinfotherserviceq= pestcusinfotherservice.getText().toString();
        pestcusdisp2q= pestcusdisp2.getText().toString();
        pestcusdisp3q= pestcusdisp3.getText().toString();


        if(TextUtils.isEmpty(pestcusinfoareaq ) || TextUtils.isEmpty(pestcusinfotherserviceq) ||TextUtils.isEmpty(pestcusdisp2q)
                ||TextUtils.isEmpty(pestcusdisp3q)) {

            if(TextUtils.isEmpty(pestcusinfoareaq )){
                pestcusinfoarea.setError("Required");
            }

            if(TextUtils.isEmpty(pestcusinfotherserviceq)) {
                pestcusinfotherservice.setError("Required");


            }
            if(TextUtils.isEmpty(pestcusdisp2q)) {
                pestcusdisp2.setError("Required");

            }
            if(TextUtils.isEmpty(pestcusdisp3q)) {
                pestcusdisp3.setError("Required");

            }

            return false;
        }



        if(pestcusinfoareaq.length()==0||pestcusinfotherserviceq.length()==0||pestcusdisp2q.length()==0||pestcusdisp3q.length()==0){
            return false;
        }else {
            return  true;
        }

    }

    private void post_pest_cus_js() {

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("pestcusareai",pestcusinfoareaq);
        params.put("pestotherservicei",pestcusinfotherserviceq);
        params.put("pestdisp2i",pestcusdisp2q);
        params.put("pestdisp3i",pestcusdisp3q);
        params.put("main_id",PMCA_Title_1.Main_ID);
        params.put("update_status",Update_Status);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/PMCAR/in_pmca_cus_2.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Intent i = new Intent(PMCA_Cus_Info_2.this,PMCA_Obser_3.class);
                            startActivity(i);
                            finish();

                            Log.e("JJJJJJJ","PMCA Cus Rs\t"+response);

                            //  Toast.makeText( PMCA_Cus_Info_2.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }


    public void getData(String key_id){


        Log.e("VVVCC","getdata keyid cus2 ="+key_id);
        if(key_id!=null){
            PMCA_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/PMCAR/get_pmca_cus_2.php?main_id="+PMCA_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e( "LKLKP","pmca cus 2 res="+response );

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");

                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                Log.e("BBBB","en1");


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                Log.e("BBBB","en2");
                                String id = c.getString( "main_id" );

                                Log.e("BBBB","en3");
                                String getlocation = c.getString( "location" );

                                Log.e("BBBB","en4");
                                String getservicelocation = c.getString( "service_location" );
                                Log.e("BBBB","en5");
                                String getpestcover = c.getString( "pest_cover" );
                                Log.e("BBBB","en6");
                                String getother = c.getString( "other_service" );
                                Log.e("BBBB","en7");

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.PMCA_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("BBBB","en8");
                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.PMCA_COMPLETE_STATUS));

                                Log.e("BBBB","en10");

                                if(get_pmca_complete.equalsIgnoreCase("2")){




                                    pestcusinbck.setVisibility(View.GONE);
                                    pestcusinsub.setVisibility(View.GONE);
                                    pestman_cusinfo_sub.setVisibility(View.VISIBLE);

                                    pestcusinfoarea.setText(getlocation);
                                    pestcusdisp2.setText(getservicelocation);
                                    pestcusdisp3.setText(getpestcover);
                                    pestcusinfotherservice.setText(getother);
                                    Update_Status = "2";

                                    pestcusinfoarea.setTypeface(pestcusinfoarea.getTypeface(), Typeface.BOLD_ITALIC);
                                    pestcusdisp2.setTypeface(pestcusdisp2.getTypeface(), Typeface.BOLD_ITALIC);
                                    pestcusdisp3.setTypeface(pestcusdisp3.getTypeface(), Typeface.BOLD_ITALIC);
                                    pestcusinfotherservice.setTypeface(pestcusinfotherservice.getTypeface(), Typeface.BOLD_ITALIC);

                                    pestcusinfoarea.setEnabled(false);
                                    pestcusdisp2.setEnabled(false);
                                    pestcusdisp3.setEnabled(false);
                                    pestcusinfotherservice.setEnabled(false);
                                    pestcusresponsebtn2.setEnabled(false);
                                    pestcusresponsebtn3.setEnabled(false);

                                    pd.dismiss();

                                }else if(get_pmca_complete.equalsIgnoreCase("1")){


                                pestcusinfoarea.setText(getlocation);
                                pestcusdisp2.setText(getservicelocation);
                                pestcusdisp3.setText(getpestcover);
                                pestcusinfotherservice.setText(getother);



                                Update_Status="1";
                                }



                            }

//                            Toast.makeText( PMCA_Cus_Info_2.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );

                        Log.e( "LKLKP","error message = "+error.getMessage() );
                    }
                } )
                .requestJson();
    }


    private void pmca_cus_info_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "pmca_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "pmca_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void pmca_cus_info_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "pmca_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "pmca_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }


}
