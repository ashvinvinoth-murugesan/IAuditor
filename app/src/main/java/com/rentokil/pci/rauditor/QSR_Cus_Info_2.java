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

public class QSR_Cus_Info_2 extends AppCompatActivity {


    EditText qsr_cusinfo_et_area,qsr_cusinfo_et_contract,qsr_cusinfo_0ther;

    RadioGroup qsr_cusinfo_radiob1;

    private FirebaseAnalytics mFirebaseAnalytics;

    private RadioButton radiobtn;

    String myJSON;
    JSONArray json_arr_cus_result;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    public String Update_Status="0";


    ConnectivityManager cManager;
    NetworkInfo nInfo;

    TextView qsr_cusinfo_selectrs1,qsr_cusinfo_selectrs2;

    public  static TextView  qsr_cusinfo_disp1,qsr_cusinfo_disp2;

    String getarea,getcontract,getother,getdisp1,getdisp2,getrad1;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    private android.app.AlertDialog pd;



    Button qsrcusinfosub,qsrcusinfobck,qsrcusinfosub1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qsr_cusinfo);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


        db = new DatabaseHelper(QSR_Cus_Info_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        qsrcusinfosub =(Button) findViewById(R.id.qsrcusinfosub);
        qsrcusinfobck=(Button) findViewById(R.id.qsrcusinfobck);
        qsrcusinfosub1=(Button) findViewById(R.id.qsrcusinfosub1);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        qsrcusinfosub1.setVisibility(View.GONE);
        pd = new SpotsDialog(QSR_Cus_Info_2.this, R.style.Custom);
        qsrcusinfosub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                Intent i = new Intent(QSR_Cus_Info_2.this, QSR_Cus_Pest_3.class);
                startActivity(i);
            }
        });

        qsr_cusinfo_et_area=(EditText) findViewById(R.id.qsr_cusinfo_et_area);
        qsr_cusinfo_et_contract=(EditText) findViewById(R.id.qsr_cusinfo_et_contract);
        qsr_cusinfo_0ther=(EditText) findViewById(R.id.qsr_cusinfo_0ther);
        qsr_cusinfo_selectrs1=(TextView) findViewById(R.id.qsr_cusinfo_selectrs1);
        qsr_cusinfo_selectrs2=(TextView) findViewById(R.id.qsr_cusinfo_selectrs2);

        qsr_cusinfo_disp1=(TextView) findViewById(R.id.qsr_cusinfo_disp1);
        qsr_cusinfo_disp2=(TextView) findViewById(R.id.qsr_cusinfo_disp2);

        Cursor c101;
        c101 = sd.rawQuery("Select * from " + db.CHECK_STATUS_TABLE, null);
        c101.moveToFirst();

        Log.e("UUUUUUU","status ="+c101.getString(c101.getColumnIndex(db.STATUS)));

        if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("101")){
            qsrcusinfosub.setVisibility(View.GONE);
            qsrcusinfobck.setVisibility(View.GONE);
            qsrcusinfosub1.setVisibility(View.VISIBLE);

        }
        else  if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("100")){

            qsrcusinfosub.setVisibility(View.VISIBLE);
            qsrcusinfobck.setVisibility(View.VISIBLE);
            qsrcusinfosub1.setVisibility(View.GONE);

        }



        qsr_cusinfo_radiob1=(RadioGroup) findViewById(R.id.qsr_cusinfo_radiob1);


        qsr_cusinfo_selectrs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                newdemoselect(114);
            }
        });

        qsr_cusinfo_selectrs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                newdemoselect(115);
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


        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){

            Log.e("RRRTT","qsr main ="+ QSR_Title_1.Main_ID);
            QSR_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!QSR_Title_1.Main_ID.equals("0")) {
                // String keyid=null;

                Log.e("RRRTT","qsr else = "+QSR_Title_1.Main_ID);
                getData(QSR_Title_1.Main_ID);
            }

        }

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(QSR_Cus_Info_2.this);
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
                        Toast.makeText(QSR_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(QSR_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }





        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        qsrcusinfosub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(QSR_Cus_Info_2.this);
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
                                Toast.makeText(QSR_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(QSR_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }



                if (validation()) {

                    qsr_cus_info_button_next();
                    post_cust_info_js();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
                }


            }
        });

        qsrcusinfobck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsr_cus_info_button_back();
                Intent i = new Intent(QSR_Cus_Info_2.this,QSR_Title_1.class);
                startActivity(i);
            }
        });

    }
    public boolean validation()
    {


        getarea=qsr_cusinfo_et_area.getText().toString();
        getcontract=qsr_cusinfo_et_contract.getText().toString();
        getother=qsr_cusinfo_0ther.getText().toString();
        getdisp1=qsr_cusinfo_disp1.getText().toString();
        getdisp2=qsr_cusinfo_disp2.getText().toString();


        if(TextUtils.isEmpty(getarea ) || TextUtils.isEmpty(getcontract ) || TextUtils.isEmpty(getother ) || TextUtils.isEmpty(getdisp1 )
                || TextUtils.isEmpty(getdisp2 )) {

            if (TextUtils.isEmpty(getarea)) {
                qsr_cusinfo_et_area.setError("Required");
            }

            if (TextUtils.isEmpty(getcontract)) {
                qsr_cusinfo_et_contract.setError("Required");
            }
            if (TextUtils.isEmpty(getother)) {
                qsr_cusinfo_0ther.setError("Required");
            }

            if (TextUtils.isEmpty(getdisp1)) {
                qsr_cusinfo_disp1.setError("Required");
            }

            if (TextUtils.isEmpty(getdisp2)) {
                qsr_cusinfo_disp2.setError("Required");
            }
            return false;
        }


        if(getarea.length()==0||getcontract.length()==0||getdisp1.length()==0||getdisp2.length()==0){
            return  false;
        }else {
            return true;
        }
    }

    public void newdemoselect(int status) {

        Bundle bundle = new Bundle();
        if(status==114) {
            bundle.putString("status", "114");
        }if(status==115){

            bundle.putString("status", "115");

        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    private void post_cust_info_js() {

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("areaz",getarea);
        params.put("contractz",getcontract);
        params.put("otherz",getother);
        params.put("disp1z",getdisp1);
        params.put("disp2z",getdisp2);
        params.put("gradio1z",getrad1);
        params.put("main_id",QSR_Title_1.Main_ID);
        params.put("update_status",Update_Status);


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/QSR/in_qsr_cusinfo_2.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            // Toast.makeText(QSR_Cus_Info_2.this,response,Toast.LENGTH_LONG).show();
                            Log.e("QSR Page 1",""+response);
                            Intent i = new Intent(QSR_Cus_Info_2.this,QSR_Cus_Pest_3.class);
                            startActivity(i);

                            //   Toast.makeText( QSR_Cus_Info_2.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
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

   @Override
    public void onBackPressed(){

    }

    private void getData(String key_id){


        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            QSR_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/QSR/get_qsr_cusinfo_2.php?main_id="+QSR_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("SRRRRR","res "+response);
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");
                            Log.e("LOOOOOP","R1");

                            //   id=new String[jsonArray_get.length()];

                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                Log.e("LOOOOOP","R2");
                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "main_id" );
                                String getarea = c.getString( "area" );
                                String getcontract = c.getString( "contract_value" );
                                String getpest = c.getString( "pest_cover" );
                                String getother = c.getString( "other_service" );
                                String getservice = c.getString( "service_contract" );
                                String get_radio_contract = c.getString( "contract" );
                                Log.e("LOOOOOP","R3");
                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.QSR_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("LOOOOOP","R4");

                                Log.e("LOOOOOP","R5");

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.QSR_COMPLETE_STATUS));


                                Log.e("LOOOOOP","R6");

                                if(get_pmca_complete.equalsIgnoreCase("2")){



                                    Log.e("LOOOOOP","R7");

                                    qsrcusinfosub.setVisibility(View.GONE);
                                    qsrcusinfobck.setVisibility(View.GONE);
                                    qsrcusinfosub1.setVisibility(View.VISIBLE);


   Log.e("EEE","qsr entry1");
                                    qsr_cusinfo_et_area.setText(getarea);
                                    qsr_cusinfo_et_contract.setText(getcontract);
                                    qsr_cusinfo_disp2.setText(getpest);
                                    qsr_cusinfo_0ther.setText(getother);
                                    qsr_cusinfo_disp1.setText(getservice);

                                    qsr_cusinfo_et_area.setEnabled(false);
                                    qsr_cusinfo_et_contract.setEnabled(false);
                                    qsr_cusinfo_0ther.setEnabled(false);
                                    qsr_cusinfo_selectrs1.setEnabled(false);
                                    qsr_cusinfo_selectrs2.setEnabled(false);

                                    qsr_cusinfo_radiob1.getChildAt(0).setEnabled(false);
                                    qsr_cusinfo_radiob1.getChildAt(1).setEnabled(false);


                                    if(get_radio_contract.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_radio_contract.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(1)).setChecked(true);

                                    }

                                    qsr_cusinfo_radiob1.getChildAt(0).setEnabled(false);
                                    qsr_cusinfo_radiob1.getChildAt(1).setEnabled(false);


                                    Update_Status="2";

                                    pd.dismiss();
                                }
                          else  if(get_pmca_complete.equalsIgnoreCase("1")) {

                                    Log.e("EEE","qsr else");
                                    qsr_cusinfo_et_area.setText(getarea);
                                    qsr_cusinfo_et_contract.setText(getcontract);
                                    qsr_cusinfo_disp2.setText(getpest);
                                    qsr_cusinfo_0ther.setText(getother);
                                    qsr_cusinfo_disp1.setText(getservice);

                                    if(get_radio_contract.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_radio_contract.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) qsr_cusinfo_radiob1.getChildAt(1)).setChecked(true);

                                    }




                                    Update_Status="1";
                                }




                            }

                            //   Toast.makeText( QSR_Cus_Info_2.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );

                        Log.e("EEE","error qsr cus2 "+error.getMessage());
                    }
                } )
                .requestJson();
    }

    private void qsr_cus_info_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_sub_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void qsr_cus_info_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_bck_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }









}
