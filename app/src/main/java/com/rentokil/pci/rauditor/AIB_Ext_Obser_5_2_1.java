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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class AIB_Ext_Obser_5_2_1 extends AppCompatActivity {


    Button aibtitlebck, aibtitlesub,aib_extrnobs2_finish1;

    private FirebaseAnalytics mFirebaseAnalytics;

    ConnectivityManager cManager;
    NetworkInfo nInfo;


    //HEAD9
    RadioGroup aib_ex2_headrd9, aib_ex2_prio9, aib_ex2_obser9, aib_ex2_ass9;
    TextView aib_ex2_s9;
    EditText aib_ex2_rpci9, aib_ex2_rcus9;
    public static TextView aib_ex2_disp9;

    RadioButton headbtn9, priobtn9, obserbtn9, assbtn9;

    String gethead9, getprio9, getobser9, getass9, gets9, rpci9, rcus9, rdisp9;

    //HEAD10
    RadioGroup aib_ex2_headrd10, aib_ex2_prio10, aib_ex2_obser10, aib_ex2_ass10;
    TextView aib_ex2_s10;
    EditText aib_ex2_rpci10, aib_ex2_rcus10;
    public static TextView aib_ex2_disp10;

    RadioButton headbtn10, priobtn10, obserbtn10, assbtn10;

    String gethead10, getprio10, getobser10, getass10, gets10, rpci10, rcus10, rdisp10;


    //HEAD11
    RadioGroup aib_ex2_headrd11, aib_ex2_prio11, aib_ex2_obser11, aib_ex2_ass11;
    TextView aib_ex2_s11;
    EditText aib_ex2_rpci11, aib_ex2_rcus11;
    public static TextView aib_ex2_disp11;

    RadioButton headbtn11, priobtn11, obserbtn11, assbtn11;

    String gethead11, getprio11, getobser11, getass11, gets11, rpci11, rcus11, rdisp11;


    //HEAD12
    RadioGroup aib_ex2_headrd12, aib_ex2_prio12, aib_ex2_obser12, aib_ex2_ass12;
    TextView aib_ex2_s12;
    EditText aib_ex2_rpci12, aib_ex2_rcus12;
    public static TextView aib_ex2_disp12;

    RadioButton headbtn12, priobtn12, obserbtn12, assbtn12;

    String gethead12, getprio12, getobser12, getass12, gets12, rpci12, rcus12, rdisp12;


    ArrayList<String> header_arr = new ArrayList<String>();
    ArrayList<String> pestcover_arr = new ArrayList<String>();
    ArrayList<String> recomm_pci_arr = new ArrayList<String>();
    ArrayList<String> recomm_cus_arr = new ArrayList<String>();
    ArrayList<String> pri_arr = new ArrayList<String>();
    ArrayList<String> obs_status_arr = new ArrayList<String>();
    ArrayList<String> assign_to_arr = new ArrayList<String>();

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    private android.app.AlertDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aib__ext__obser_5_2_1);

        aibtitlesub = (Button) findViewById(R.id.aibtitlesub);
        aibtitlebck = (Button) findViewById(R.id.aibtitlebck);
        aib_extrnobs2_finish1 = (Button) findViewById(R.id.aib_extrnobs2_finish1);


        aib_extrnobs2_finish1.setVisibility(View.GONE);

        aib_extrnobs2_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

  pd.show();

                aib_extr_2_button_next();

                Intent i = new Intent(AIB_Ext_Obser_5_2_1.this,AIB_Interl_Obser_6.class);
                startActivity(i);

            }
        });

        db = new DatabaseHelper(AIB_Ext_Obser_5_2_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //HEAD9

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        aib_ex2_headrd9 = (RadioGroup) findViewById(R.id.aib_ex2_headrd9);
        aib_ex2_prio9 = (RadioGroup) findViewById(R.id.aib_ex2_prio9);
        aib_ex2_obser9 = (RadioGroup) findViewById(R.id.aib_ex2_obser9);
        aib_ex2_ass9 = (RadioGroup) findViewById(R.id.aib_ex2_ass9);
        aib_ex2_s9 = (TextView) findViewById(R.id.aib_ex2_s9);
        aib_ex2_rpci9 = (EditText) findViewById(R.id.aib_ex2_rpci9);
        aib_ex2_rcus9 = (EditText) findViewById(R.id.aib_ex2_rcus9);
        aib_ex2_disp9 = (TextView) findViewById(R.id.aib_ex2_disp9);


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Ext_Obser_5_2_1.this);
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
                        Toast.makeText(AIB_Ext_Obser_5_2_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Ext_Obser_5_2_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        pd = new SpotsDialog(AIB_Ext_Obser_5_2_1.this, R.style.Custom);
        aib_ex2_headrd9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_headrd9.getCheckedRadioButtonId();

                headbtn9 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_prio9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_prio9.getCheckedRadioButtonId();

                priobtn9 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_obser9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_obser9.getCheckedRadioButtonId();

                obserbtn9 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_ass9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_ass9.getCheckedRadioButtonId();

                assbtn9 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_s9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                aib_extrob_2(2230);
            }
        });


        //HEAD10

        aib_ex2_headrd10 = (RadioGroup) findViewById(R.id.aib_ex2_headrd10);
        aib_ex2_prio10 = (RadioGroup) findViewById(R.id.aib_ex2_prio10);
        aib_ex2_obser10 = (RadioGroup) findViewById(R.id.aib_ex2_obser10);
        aib_ex2_ass10 = (RadioGroup) findViewById(R.id.aib_ex2_ass10);
        aib_ex2_s10 = (TextView) findViewById(R.id.aib_ex2_s10);
        aib_ex2_rpci10 = (EditText) findViewById(R.id.aib_ex2_rpci10);
        aib_ex2_rcus10 = (EditText) findViewById(R.id.aib_ex2_rcus10);
        aib_ex2_disp10 = (TextView) findViewById(R.id.aib_ex2_disp10);

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


        aib_ex2_headrd10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_headrd10.getCheckedRadioButtonId();

                headbtn10 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_prio10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_prio10.getCheckedRadioButtonId();

                priobtn10 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_obser10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_obser10.getCheckedRadioButtonId();

                obserbtn10 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_ass10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_ass10.getCheckedRadioButtonId();

                assbtn10 = (RadioButton) findViewById(selectedId);


            }
        });

        aib_ex2_s10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                aib_extrob_2(2231);
            }
        });


        //HEAD11

        aib_ex2_headrd11 = (RadioGroup) findViewById(R.id.aib_ex2_headrd11);
        aib_ex2_prio11 = (RadioGroup) findViewById(R.id.aib_ex2_prio11);
        aib_ex2_obser11 = (RadioGroup) findViewById(R.id.aib_ex2_obser11);
        aib_ex2_ass11 = (RadioGroup) findViewById(R.id.aib_ex2_ass11);
        aib_ex2_s11 = (TextView) findViewById(R.id.aib_ex2_s11);
        aib_ex2_rpci11 = (EditText) findViewById(R.id.aib_ex2_rpci11);
        aib_ex2_rcus11 = (EditText) findViewById(R.id.aib_ex2_rcus11);
        aib_ex2_disp11 = (TextView) findViewById(R.id.aib_ex2_disp11);


        aib_ex2_headrd11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_headrd11.getCheckedRadioButtonId();

                headbtn11 = (RadioButton) findViewById(selectedId);


            }
        });

        aib_ex2_prio11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_prio11.getCheckedRadioButtonId();

                priobtn11 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_obser11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_obser11.getCheckedRadioButtonId();

                obserbtn11 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_ass11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_ass11.getCheckedRadioButtonId();

                assbtn11 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_s11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                aib_extrob_2(2232);
            }
        });


        //HEAD12

        aib_ex2_headrd12 = (RadioGroup) findViewById(R.id.aib_ex2_headrd12);
        aib_ex2_prio12 = (RadioGroup) findViewById(R.id.aib_ex2_prio12);
        aib_ex2_obser12 = (RadioGroup) findViewById(R.id.aib_ex2_obser12);
        aib_ex2_ass12 = (RadioGroup) findViewById(R.id.aib_ex2_ass12);
        aib_ex2_s12 = (TextView) findViewById(R.id.aib_ex2_s12);
        aib_ex2_rpci12 = (EditText) findViewById(R.id.aib_ex2_rpci12);
        aib_ex2_rcus12 = (EditText) findViewById(R.id.aib_ex2_rcus12);
        aib_ex2_disp12 = (TextView) findViewById(R.id.aib_ex2_disp12);


        aib_ex2_headrd12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_headrd12.getCheckedRadioButtonId();

                headbtn12 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_prio12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_prio12.getCheckedRadioButtonId();

                priobtn12 = (RadioButton) findViewById(selectedId);


            }
        });

        aib_ex2_obser12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_obser12.getCheckedRadioButtonId();

                obserbtn12 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_ass12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId = aib_ex2_ass12.getCheckedRadioButtonId();

                assbtn12 = (RadioButton) findViewById(selectedId);



            }
        });

        aib_ex2_s12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                aib_extrob_2(2233);
            }
        });



        aibtitlesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(AIB_Ext_Obser_5_2_1.this);
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
                                Toast.makeText(AIB_Ext_Obser_5_2_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Ext_Obser_5_2_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }





                if (validation()) {

                    aib_extr_2_button_next();
                    post_aibexobs2();
                } else {
                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }




            }
        });

        aibtitlebck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_extr_2_button_back();

                Intent i = new Intent(AIB_Ext_Obser_5_2_1.this, AIB_Ext_Obser_5_1.class);
                startActivity(i);

            }
        });

    }

    public  boolean validation(){

        try {
            rpci9=aib_ex2_rpci9.getText().toString();
            rcus9=aib_ex2_rcus9.getText().toString();
            rdisp9=aib_ex2_disp9.getText().toString();

            rpci10=aib_ex2_rpci10.getText().toString();
            rcus10=aib_ex2_rcus10.getText().toString();
            rdisp10=aib_ex2_disp10.getText().toString();

            rpci11=aib_ex2_rpci11.getText().toString();
            rcus11=aib_ex2_rcus11.getText().toString();
            rdisp11=aib_ex2_disp11.getText().toString();

            rpci12=aib_ex2_rpci12.getText().toString();
            rcus12=aib_ex2_rcus12.getText().toString();
            rdisp12=aib_ex2_disp12.getText().toString();



            if(TextUtils.isEmpty(rpci9 ) || TextUtils.isEmpty(rcus9)
                    ||TextUtils.isEmpty(rpci10) ||TextUtils.isEmpty(rcus10)
                    ||TextUtils.isEmpty(rpci11) ||TextUtils.isEmpty(rcus11)
                    ||TextUtils.isEmpty(rpci12) ||TextUtils.isEmpty(rcus12)


                    ) {

                if(TextUtils.isEmpty(rpci9 )){
                    aib_ex2_rpci9.setError("Required");
                }

                if(TextUtils.isEmpty(rcus9)) {
                    aib_ex2_rcus9.setError("Required");


                }
//                if(TextUtils.isEmpty(rdisp9)) {
//                    aib_ex2_disp9.setError("Required");
//
//                }
                if(TextUtils.isEmpty(rpci10)) {
                    aib_ex2_rpci10.setError("Required");

                }
                if(TextUtils.isEmpty(rcus10)) {
                    aib_ex2_rcus10.setError("Required");

                }

//                if(TextUtils.isEmpty(rdisp10)) {
//                    aib_ex2_disp10.setError("Required");
//
//                }


                if(TextUtils.isEmpty(rpci11)) {
                    aib_ex2_rpci11.setError("Required");

                }
                if(TextUtils.isEmpty(rcus11)) {
                    aib_ex2_rcus11.setError("Required");

                }
//                if(TextUtils.isEmpty(rdisp11)) {
//                    aib_ex2_disp11.setError("Required");
//
//                }

                if(TextUtils.isEmpty(rpci12)) {
                    aib_ex2_rpci12.setError("Required");

                }
                if(TextUtils.isEmpty(rcus12)) {
                    aib_ex2_rcus12.setError("Required");

                }
//                if(TextUtils.isEmpty(rdisp12)) {
//                    aib_ex2_disp12.setError("Required");
//
//                }
                return false;
            }



            gethead9 = ((RadioButton)findViewById(aib_ex2_headrd9.getCheckedRadioButtonId())).getText().toString();
            getprio9 = ((RadioButton)findViewById(aib_ex2_prio9.getCheckedRadioButtonId())).getText().toString();
            getobser9 = ((RadioButton)findViewById(aib_ex2_obser9.getCheckedRadioButtonId())).getText().toString();
            getass9 = ((RadioButton)findViewById(aib_ex2_ass9.getCheckedRadioButtonId())).getText().toString();
            gethead10 = ((RadioButton)findViewById(aib_ex2_headrd10.getCheckedRadioButtonId())).getText().toString();
            getprio10 = ((RadioButton)findViewById(aib_ex2_prio10.getCheckedRadioButtonId())).getText().toString();
            getobser10 = ((RadioButton)findViewById(aib_ex2_obser10.getCheckedRadioButtonId())).getText().toString();
            getass10 = ((RadioButton)findViewById(aib_ex2_ass10.getCheckedRadioButtonId())).getText().toString();
            gethead11 = ((RadioButton)findViewById(aib_ex2_headrd11.getCheckedRadioButtonId())).getText().toString();
            getprio11 = ((RadioButton)findViewById(aib_ex2_prio11.getCheckedRadioButtonId())).getText().toString();
            getobser11 = ((RadioButton)findViewById(aib_ex2_obser11.getCheckedRadioButtonId())).getText().toString();
            getass11 = ((RadioButton)findViewById(aib_ex2_ass11.getCheckedRadioButtonId())).getText().toString();
            gethead12 = ((RadioButton)findViewById(aib_ex2_headrd12.getCheckedRadioButtonId())).getText().toString();
            getprio12 = ((RadioButton)findViewById(aib_ex2_prio12.getCheckedRadioButtonId())).getText().toString();
            getobser12 = ((RadioButton)findViewById(aib_ex2_obser12.getCheckedRadioButtonId())).getText().toString();
            getass12 = ((RadioButton)findViewById(aib_ex2_ass12.getCheckedRadioButtonId())).getText().toString();


            header_arr.add(gethead9);
            header_arr.add(gethead10);
            header_arr.add(gethead11);
            header_arr.add(gethead12);


            pestcover_arr.add(rdisp9);
            pestcover_arr.add(rdisp10);
            pestcover_arr.add(rdisp11);
            pestcover_arr.add(rdisp12);

            recomm_pci_arr.add(rpci9);
            recomm_pci_arr.add(rpci10);
            recomm_pci_arr.add(rpci11);
            recomm_pci_arr.add(rpci12);

            recomm_cus_arr.add(rcus9);
            recomm_cus_arr.add(rcus10);
            recomm_cus_arr.add(rcus11);
            recomm_cus_arr.add(rcus12);

            pri_arr.add(getprio9);
            pri_arr.add(getprio10);
            pri_arr.add(getprio11);
            pri_arr.add(getprio12);

            obs_status_arr.add(getobser9);
            obs_status_arr.add(getobser10);
            obs_status_arr.add(getobser11);
            obs_status_arr.add(getobser12);

            assign_to_arr.add(getass9);
            assign_to_arr.add(getass10);
            assign_to_arr.add(getass11);
            assign_to_arr.add(getass12);


            if ((rpci9.length()==0||rcus9.length()==0||rpci10.length()==0||rcus10.length()==0
                    ||rpci11.length()==0||rcus11.length()==0||rpci12.length()==0||rcus12.length()==0||gethead9.length()==0
                    ||gethead10.length()==0||gethead11.length()==0||gethead12.length()==0||getprio9.length()==0||getprio10.length()==0||getprio11.length()==0||getprio12.length()==0
                    ||getobser9.length()==0||getobser10.length()==0||getobser11.length()==0||getobser12.length()==0||getass9.length()==0||getass10.length()==0||getass11.length()==0||getass12.length()==0



            )){
                return  false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }


    }



    public void aib_extrob_2(int status) {

        Bundle bundle = new Bundle();
        if (status == 2230) {
            bundle.putString("status", "2230");
        }

        if (status == 2231) {
            bundle.putString("status", "2231");
        }

        if (status == 2232) {
            bundle.putString("status", "2232");
        }
        if (status == 2233) {
            bundle.putString("status", "2233");
        }


        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(), "Example");
        btnwork.setArguments(bundle);
    }

    private void post_aibexobs2() {

        pd.show();
        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("count",""+header_arr.size());
        Log.e("FFFFF","aibex5.2 = "+AIB_Title_1.Main_ID);
        params.put("main_id",AIB_Title_1.Main_ID);


        for (int i=0;i<header_arr.size();i++) {

            params.put("headid"+i, ""+(i+8));
            params.put("headi"+i,header_arr.get(i));
            params.put("pestcvri"+i,pestcover_arr.get(i));
            params.put("repci"+i,recomm_pci_arr.get(i));
            params.put("recus"+i,recomm_cus_arr.get(i));
            params.put("priq"+i,pri_arr.get(i));
            params.put("obsq"+i,obs_status_arr.get(i));
            params.put("assq"+i,assign_to_arr.get(i));

        }


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_extr_obs_2.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        pd.dismiss();

                        try {
                            Log.e("SSASA"," res = "+response);

                            Intent i = new Intent(AIB_Ext_Obser_5_2_1.this, AIB_Interl_Obser_6.class);
                            startActivity(i);
                            //  Toast.makeText( AIB_Ext_Obser_5_2_1.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e("SSASA","NMNM ERRR"+error.getMessage());

                        if(error.getMessage().equalsIgnoreCase("null")){

                            Intent i = new Intent(AIB_Ext_Obser_5_2_1.this,AIB_Interl_Obser_6.class);
                            startActivity(i);
                        }
                        else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                        {
                            Intent i = new Intent(AIB_Ext_Obser_5_2_1.this,AIB_Interl_Obser_6.class);
                            startActivity(i);

                        }
                        //    Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

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


        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_aib_extr_obs_5_2.php?main_id=" + AIB_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            Log.e("FDFDF","HGHG res"+response);


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString("main_id");
                                String getpest = c.getString("pest_cover");
                                String getcus = c.getString("recomm_cus");
                                String getpci = c.getString("recomm_rpci");
                                String get_header = c.getString("header");
                                String get_pri = c.getString("pri");
                                String get_obser_status = c.getString("obser_status");
                                String get_assign_to = c.getString("assign_to");


                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDF123DS","update value = "+c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS)));

                                String getsircuscomp = c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS));

                                if(getsircuscomp.equalsIgnoreCase("2")) {



                                    Log.e("IIIO","entry1");

                                    aibtitlebck.setVisibility(View.GONE);
                                    aibtitlesub.setVisibility(View.GONE);
                                    aib_extrnobs2_finish1.setVisibility(View.VISIBLE);

                                    aib_ex2_headrd9.getChildAt(0).setEnabled(false);
                                    aib_ex2_headrd9.getChildAt(1).setEnabled(false);

                                    aib_ex2_s9.setEnabled(false);
                                    aib_ex2_rcus9.setEnabled(false);
                                    aib_ex2_rpci9.setEnabled(false);

                                    aib_ex2_prio9.getChildAt(0).setEnabled(false);
                                    aib_ex2_prio9.getChildAt(1).setEnabled(false);
                                    aib_ex2_prio9.getChildAt(2).setEnabled(false);
                                    aib_ex2_prio9.getChildAt(3).setEnabled(false);

                                    aib_ex2_obser9.getChildAt(0).setEnabled(false);
                                    aib_ex2_obser9.getChildAt(1).setEnabled(false);
                                    aib_ex2_obser9.getChildAt(2).setEnabled(false);
                                    aib_ex2_obser9.getChildAt(3).setEnabled(false);
                                    aib_ex2_obser9.getChildAt(4).setEnabled(false);

                                    aib_ex2_ass9.getChildAt(0).setEnabled(false);
                                    aib_ex2_ass9.getChildAt(1).setEnabled(false);
                                    aib_ex2_ass9.getChildAt(2).setEnabled(false);


                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////


                                    aib_ex2_headrd10.getChildAt(0).setEnabled(false);
                                    aib_ex2_headrd10.getChildAt(1).setEnabled(false);

                                    aib_ex2_s10.setEnabled(false);
                                    aib_ex2_rcus10.setEnabled(false);
                                    aib_ex2_rpci10.setEnabled(false);

                                    aib_ex2_prio10.getChildAt(0).setEnabled(false);
                                    aib_ex2_prio10.getChildAt(1).setEnabled(false);
                                    aib_ex2_prio10.getChildAt(2).setEnabled(false);
                                    aib_ex2_prio10.getChildAt(3).setEnabled(false);

                                    aib_ex2_obser10.getChildAt(0).setEnabled(false);
                                    aib_ex2_obser10.getChildAt(1).setEnabled(false);
                                    aib_ex2_obser10.getChildAt(2).setEnabled(false);
                                    aib_ex2_obser10.getChildAt(3).setEnabled(false);
                                    aib_ex2_obser10.getChildAt(4).setEnabled(false);

                                    aib_ex2_ass10.getChildAt(0).setEnabled(false);
                                    aib_ex2_ass10.getChildAt(1).setEnabled(false);
                                    aib_ex2_ass10.getChildAt(2).setEnabled(false);

                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////


                                    aib_ex2_headrd11.getChildAt(0).setEnabled(false);
                                    aib_ex2_headrd11.getChildAt(1).setEnabled(false);

                                    aib_ex2_s11.setEnabled(false);
                                    aib_ex2_rcus11.setEnabled(false);
                                    aib_ex2_rpci11.setEnabled(false);

                                    aib_ex2_prio11.getChildAt(0).setEnabled(false);
                                    aib_ex2_prio11.getChildAt(1).setEnabled(false);
                                    aib_ex2_prio11.getChildAt(2).setEnabled(false);
                                    aib_ex2_prio11.getChildAt(3).setEnabled(false);

                                    aib_ex2_obser11.getChildAt(0).setEnabled(false);
                                    aib_ex2_obser11.getChildAt(1).setEnabled(false);
                                    aib_ex2_obser11.getChildAt(2).setEnabled(false);
                                    aib_ex2_obser11.getChildAt(3).setEnabled(false);
                                    aib_ex2_obser11.getChildAt(4).setEnabled(false);

                                    aib_ex2_ass11.getChildAt(0).setEnabled(false);
                                    aib_ex2_ass11.getChildAt(1).setEnabled(false);
                                    aib_ex2_ass11.getChildAt(2).setEnabled(false);


                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////


                                    aib_ex2_headrd12.getChildAt(0).setEnabled(false);
                                    aib_ex2_headrd12.getChildAt(1).setEnabled(false);

                                    aib_ex2_s12.setEnabled(false);
                                    aib_ex2_rcus12.setEnabled(false);
                                    aib_ex2_rpci12.setEnabled(false);

                                    aib_ex2_prio12.getChildAt(0).setEnabled(false);
                                    aib_ex2_prio12.getChildAt(1).setEnabled(false);
                                    aib_ex2_prio12.getChildAt(2).setEnabled(false);
                                    aib_ex2_prio12.getChildAt(3).setEnabled(false);

                                    aib_ex2_obser12.getChildAt(0).setEnabled(false);
                                    aib_ex2_obser12.getChildAt(1).setEnabled(false);
                                    aib_ex2_obser12.getChildAt(2).setEnabled(false);
                                    aib_ex2_obser12.getChildAt(3).setEnabled(false);
                                    aib_ex2_obser12.getChildAt(4).setEnabled(false);

                                    aib_ex2_ass12.getChildAt(0).setEnabled(false);
                                    aib_ex2_ass12.getChildAt(1).setEnabled(false);
                                    aib_ex2_ass12.getChildAt(2).setEnabled(false);


                                    if (i == 0) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd9.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd9.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio9.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio9.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio9.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio9.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser9.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass9.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass9.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass9.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex2_disp9.setText(getpest);
                                        aib_ex2_rcus9.setText(getcus);
                                        aib_ex2_rpci9.setText(getpci);


                                    }


                                    if (i == 1) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd10.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd10.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio10.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio10.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio10.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio10.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser10.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass10.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass10.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass10.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex2_disp10.setText(getpest);
                                        aib_ex2_rcus10.setText(getcus);
                                        aib_ex2_rpci10.setText(getpci);

                                    }

                                    if (i == 2) {





                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd11.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd11.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio11.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio11.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio11.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio11.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser11.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass11.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass11.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass11.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex2_disp11.setText(getpest);
                                        aib_ex2_rcus11.setText(getcus);
                                        aib_ex2_rpci11.setText(getpci);
                                    }

                                    if (i == 3) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd12.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd12.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio12.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio12.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio12.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio12.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser12.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass12.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass12.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass12.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex2_disp12.setText(getpest);
                                        aib_ex2_rcus12.setText(getcus);
                                        aib_ex2_rpci12.setText(getpci);
                                    }

                                    pd.dismiss();



//                                    Update_Status = "2";
                                }
                                else if(getsircuscomp.equalsIgnoreCase("1")) {

                                    Log.e("IIIO","entry2");


                                    if (i == 0) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd9.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd9.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio9.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio9.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio9.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio9.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser9.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser9.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass9.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass9.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass9.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex2_disp9.setText(getpest);
                                        aib_ex2_rcus9.setText(getcus);
                                        aib_ex2_rpci9.setText(getpci);


                                    }


                                    if (i == 1) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd10.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd10.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio10.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio10.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio10.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio10.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser10.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser10.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass10.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass10.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass10.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex2_disp10.setText(getpest);
                                        aib_ex2_rcus10.setText(getcus);
                                        aib_ex2_rpci10.setText(getpci);

                                    }

                                    if (i == 2) {





                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd11.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd11.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio11.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio11.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio11.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio11.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser11.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser11.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass11.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass11.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass11.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex2_disp11.setText(getpest);
                                        aib_ex2_rcus11.setText(getcus);
                                        aib_ex2_rpci11.setText(getpci);
                                    }

                                    if (i == 3) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex2_headrd12.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex2_headrd12.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex2_prio12.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex2_prio12.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex2_prio12.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_prio12.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex2_obser12.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_obser12.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex2_ass12.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex2_ass12.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex2_ass12.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex2_disp12.setText(getpest);
                                        aib_ex2_rcus12.setText(getcus);
                                        aib_ex2_rpci12.setText(getpci);
                                    }


                                }

                            }

                            // Toast.makeText( AIB_Ext_Obser_5_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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
                        Log.e("JJJJ","NMNM ERRR"+error.getMessage());
                    }
                } )
                .requestJson();
    }

    @Override

    public void onBackPressed(){

    }



    private void aib_extr_2_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_extr_2_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }



}

