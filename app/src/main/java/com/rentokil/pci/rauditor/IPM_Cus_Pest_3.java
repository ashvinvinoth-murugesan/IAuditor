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
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

public class IPM_Cus_Pest_3 extends AppCompatActivity {

    Button ipmcuspestsub,ipmcuspestbck,ipm_cuspest_finish_1;

    private RadioGroup ipm_cupest_radiogroup1,ipm_cupest_radiogroup2,ipm_cupest_radiogroup3,ipm_cupest_radiogroup4,ipm_cupest_radiogroup5
            ,ipm_cupest_radiogroup6,ipm_cupest_radiogroup7,ipm_cupest_radiogroup8,ipm_cupest_radiogroup9,ipm_cupest_radiogroup10,ipm_cupest_radiogroup11
            ,ipm_cupest_radiogroup12,ipm_cupest_radiogroup13,ipm_cupest_radiogroup14,ipm_cupest_radiogroup15,ipm_cupest_radiogroup16,ipm_cupest_radiogroup17
            ,ipm_cupest_radiogroup18,ipm_cupest_radiogroup19,ipm_cupest_radiogroup20,ipm_cupest_radiogroup21,ipm_cupest_radiogroup22;

    JSONObject jsonObject_get;

    JSONArray jsonArray_get;


    private FirebaseAnalytics mFirebaseAnalytics;


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    private android.app.AlertDialog pd;

    static String getrdbtn1,getrdbtn2,getrdbtn3,getrdbtn4,getrdbtn5,getrdbtn6,getrdbtn7,getrdbtn8,getrdbtn9,getrdbtn10,getrdbtn11,getrdbtn12,
            getrdbtn13,getrdbtn14,getrdbtn15,getrdbtn16,getrdbtn17,getrdbtn18,getrdbtn19,getrdbtn20,getrdbtn21,getrdbtn22;

    ConnectivityManager cManager;
    NetworkInfo nInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipm__cuspest);


        ipmcuspestsub =(Button) findViewById(R.id.ipmcuspestsub);
        ipmcuspestbck=(Button) findViewById(R.id.ipmcuspestbck);
        ipm_cuspest_finish_1=(Button) findViewById(R.id.ipm_cuspest_finish_1);
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        ipm_cupest_radiogroup1=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup1);
        ipm_cupest_radiogroup2=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup2);
        ipm_cupest_radiogroup3=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup3);
        ipm_cupest_radiogroup4=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup4);
        ipm_cupest_radiogroup5=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup5);
        ipm_cupest_radiogroup6=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup6);
        ipm_cupest_radiogroup7=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup7);
        ipm_cupest_radiogroup8=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup8);
        ipm_cupest_radiogroup9=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup9);
        ipm_cupest_radiogroup10=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup10);
        ipm_cupest_radiogroup11=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup11);
        ipm_cupest_radiogroup12=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup12);
        ipm_cupest_radiogroup13=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup13);
        ipm_cupest_radiogroup14=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup14);
        ipm_cupest_radiogroup15=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup15);
        ipm_cupest_radiogroup16=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup16);
        ipm_cupest_radiogroup17=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup17);
        ipm_cupest_radiogroup18=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup18);
        ipm_cupest_radiogroup19=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup19);
        ipm_cupest_radiogroup20=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup20);
        ipm_cupest_radiogroup21=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup21);
        ipm_cupest_radiogroup22=(RadioGroup) findViewById(R.id.ipm_cupest_radiogroup22);

        pd = new SpotsDialog(IPM_Cus_Pest_3.this, R.style.Custom);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        db = new DatabaseHelper(IPM_Cus_Pest_3.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        ipm_cuspest_finish_1.setVisibility(View.GONE);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        ipm_cuspest_finish_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                ipm_cus_pest_next();

                Intent i = new Intent(IPM_Cus_Pest_3.this,IPM_Facility_4.class);
                startActivity(i);

            }
        });


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(IPM_Cus_Pest_3.this);
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
                        Toast.makeText(IPM_Cus_Pest_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(IPM_Cus_Pest_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }


        ipm_cupest_radiogroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup1.getCheckedRadioButtonId();

            //    rdbutton1=(RadioButton)findViewById(selectedId);

                getrdbtn1 = ((RadioButton)findViewById(ipm_cupest_radiogroup1.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup2.getCheckedRadioButtonId();

              //  rdbutton2=(RadioButton)findViewById(selectedId);

                getrdbtn2 = ((RadioButton)findViewById(ipm_cupest_radiogroup2.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup3.getCheckedRadioButtonId();

                //rdbutton3=(RadioButton)findViewById(selectedId);

                getrdbtn3 = ((RadioButton)findViewById(ipm_cupest_radiogroup3.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup4.getCheckedRadioButtonId();

               // rdbutton4=(RadioButton)findViewById(selectedId);

                getrdbtn4 = ((RadioButton)findViewById(ipm_cupest_radiogroup4.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup5.getCheckedRadioButtonId();

               // rdbutton5=(RadioButton)findViewById(selectedId);

                getrdbtn5 = ((RadioButton)findViewById(ipm_cupest_radiogroup5.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup6.getCheckedRadioButtonId();

               // rdbutton6=(RadioButton)findViewById(selectedId);

                getrdbtn6 = ((RadioButton)findViewById(ipm_cupest_radiogroup6.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup7.getCheckedRadioButtonId();

               // rdbutton7=(RadioButton)findViewById(selectedId);

                getrdbtn7 = ((RadioButton)findViewById(ipm_cupest_radiogroup7.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup8.getCheckedRadioButtonId();

               // rdbutton8 =(RadioButton)findViewById(selectedId);

                getrdbtn8 = ((RadioButton)findViewById(ipm_cupest_radiogroup8.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup9.getCheckedRadioButtonId();

               // rdbutton9=(RadioButton)findViewById(selectedId);

                getrdbtn9 = ((RadioButton)findViewById(ipm_cupest_radiogroup9.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup10.getCheckedRadioButtonId();

              //  rdbutton10=(RadioButton)findViewById(selectedId);

                getrdbtn10 = ((RadioButton)findViewById(ipm_cupest_radiogroup10.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup11.getCheckedRadioButtonId();

              //  rdbutton11=(RadioButton)findViewById(selectedId);

                getrdbtn11 = ((RadioButton)findViewById(ipm_cupest_radiogroup11.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup12.getCheckedRadioButtonId();

              //  rdbutton12=(RadioButton)findViewById(selectedId);

                getrdbtn12 = ((RadioButton)findViewById(ipm_cupest_radiogroup12.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup13.getCheckedRadioButtonId();

              //  rdbutton13=(RadioButton)findViewById(selectedId);

                getrdbtn13 = ((RadioButton)findViewById(ipm_cupest_radiogroup13.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup14.getCheckedRadioButtonId();

               // rdbutton14=(RadioButton)findViewById(selectedId);

                getrdbtn14 = ((RadioButton)findViewById(ipm_cupest_radiogroup14.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup15.getCheckedRadioButtonId();

              //  rdbutton15=(RadioButton)findViewById(selectedId);

                getrdbtn15 = ((RadioButton)findViewById(ipm_cupest_radiogroup15.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup16.getCheckedRadioButtonId();

              //  rdbutton16=(RadioButton)findViewById(selectedId);

                getrdbtn16 = ((RadioButton)findViewById(ipm_cupest_radiogroup16.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup17.getCheckedRadioButtonId();

            //    rdbutton17=(RadioButton)findViewById(selectedId);

                getrdbtn17 = ((RadioButton)findViewById(ipm_cupest_radiogroup17.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup18.getCheckedRadioButtonId();

             //   rdbutton18=(RadioButton)findViewById(selectedId);

                getrdbtn18 = ((RadioButton)findViewById(ipm_cupest_radiogroup18.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup19.getCheckedRadioButtonId();

              //  rdbutton19=(RadioButton)findViewById(selectedId);

                getrdbtn19 = ((RadioButton)findViewById(ipm_cupest_radiogroup19.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup20.getCheckedRadioButtonId();

             //   rdbutton20=(RadioButton)findViewById(selectedId);

                getrdbtn20 = ((RadioButton)findViewById(ipm_cupest_radiogroup20.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup21.getCheckedRadioButtonId();

             //   rdbutton21=(RadioButton)findViewById(selectedId);

                getrdbtn21 = ((RadioButton)findViewById(ipm_cupest_radiogroup21.getCheckedRadioButtonId())).getText().toString();

            }
        });
        ipm_cupest_radiogroup22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_cupest_radiogroup22.getCheckedRadioButtonId();

             //   rdbutton22=(RadioButton)findViewById(selectedId);

                getrdbtn22 = ((RadioButton)findViewById(ipm_cupest_radiogroup22.getCheckedRadioButtonId())).getText().toString();

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







        ipmcuspestsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(IPM_Cus_Pest_3.this);
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
                                Toast.makeText(IPM_Cus_Pest_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(IPM_Cus_Pest_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }


                if (validation()) {

                    ipm_cus_pest_next();
                    post_ipm_cus_pest_js();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fileds",Toast.LENGTH_SHORT ).show();
                }
            }
        });

        ipmcuspestbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_cus_pest_back();

                Intent i = new Intent(IPM_Cus_Pest_3.this,IPM_Cus_Info_2.class);
                startActivity(i);

            }
        });
    }
    public  boolean validation(){
           try {
               getrdbtn1 = ((RadioButton)findViewById(ipm_cupest_radiogroup1.getCheckedRadioButtonId())).getText().toString();
               getrdbtn2 = ((RadioButton)findViewById(ipm_cupest_radiogroup2.getCheckedRadioButtonId())).getText().toString();
               getrdbtn3 = ((RadioButton)findViewById(ipm_cupest_radiogroup3.getCheckedRadioButtonId())).getText().toString();
               getrdbtn4 = ((RadioButton)findViewById(ipm_cupest_radiogroup4.getCheckedRadioButtonId())).getText().toString();
               getrdbtn5 = ((RadioButton)findViewById(ipm_cupest_radiogroup5.getCheckedRadioButtonId())).getText().toString();
               getrdbtn6 = ((RadioButton)findViewById(ipm_cupest_radiogroup6.getCheckedRadioButtonId())).getText().toString();
               getrdbtn7 = ((RadioButton)findViewById(ipm_cupest_radiogroup7.getCheckedRadioButtonId())).getText().toString();
               getrdbtn8 = ((RadioButton)findViewById(ipm_cupest_radiogroup8.getCheckedRadioButtonId())).getText().toString();
               getrdbtn9 = ((RadioButton)findViewById(ipm_cupest_radiogroup9.getCheckedRadioButtonId())).getText().toString();
               getrdbtn10 = ((RadioButton)findViewById(ipm_cupest_radiogroup10.getCheckedRadioButtonId())).getText().toString();
               getrdbtn11 = ((RadioButton)findViewById(ipm_cupest_radiogroup11.getCheckedRadioButtonId())).getText().toString();
               getrdbtn12 = ((RadioButton)findViewById(ipm_cupest_radiogroup12.getCheckedRadioButtonId())).getText().toString();
               getrdbtn13 = ((RadioButton)findViewById(ipm_cupest_radiogroup13.getCheckedRadioButtonId())).getText().toString();
               getrdbtn14 = ((RadioButton)findViewById(ipm_cupest_radiogroup14.getCheckedRadioButtonId())).getText().toString();
               getrdbtn15 = ((RadioButton)findViewById(ipm_cupest_radiogroup15.getCheckedRadioButtonId())).getText().toString();
               getrdbtn16 = ((RadioButton)findViewById(ipm_cupest_radiogroup16.getCheckedRadioButtonId())).getText().toString();
               getrdbtn17 = ((RadioButton)findViewById(ipm_cupest_radiogroup17.getCheckedRadioButtonId())).getText().toString();
               getrdbtn18 = ((RadioButton)findViewById(ipm_cupest_radiogroup18.getCheckedRadioButtonId())).getText().toString();
               getrdbtn19 = ((RadioButton)findViewById(ipm_cupest_radiogroup19.getCheckedRadioButtonId())).getText().toString();
               getrdbtn20 = ((RadioButton)findViewById(ipm_cupest_radiogroup20.getCheckedRadioButtonId())).getText().toString();
               getrdbtn21 = ((RadioButton)findViewById(ipm_cupest_radiogroup21.getCheckedRadioButtonId())).getText().toString();
               getrdbtn22 = ((RadioButton)findViewById(ipm_cupest_radiogroup22.getCheckedRadioButtonId())).getText().toString();

               if(
                    getrdbtn1.length()==0||getrdbtn2.length()==0||getrdbtn3.length()==0||getrdbtn4.length()==0||getrdbtn5.length()==0||getrdbtn6.length()==0||getrdbtn7.length()==0||getrdbtn8.length()==0||getrdbtn9.length()==0||getrdbtn10.length()==0||getrdbtn11.length()==0||getrdbtn12.length()==0||
                            getrdbtn13.length()==0||getrdbtn14.length()==0||getrdbtn15.length()==0||getrdbtn16.length()==0||getrdbtn17.length()==0||getrdbtn18.length()==0||getrdbtn19.length()==0||getrdbtn20.length()==0||getrdbtn21.length()==0||getrdbtn22.length()==0
                    ){
                return false;
            }else{
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }

    }

    private void post_ipm_cus_pest_js() {
        pd.show();


        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("radio1",getrdbtn1);
        params.put("radio2",getrdbtn2);
        params.put("radio3",getrdbtn3);
        params.put("radio4",getrdbtn4);
        params.put("radio5",getrdbtn5);
        params.put("radio6",getrdbtn6);
        params.put("radio7",getrdbtn7);
        params.put("radio8",getrdbtn8);
        params.put("radio9",getrdbtn9);
        params.put("radio10",getrdbtn10);
        params.put("radio11",getrdbtn11);
        params.put("radio12",getrdbtn12);
        params.put("radio13",getrdbtn13);
        params.put("radio14",getrdbtn14);
        params.put("radio15",getrdbtn15);
        params.put("radio16",getrdbtn16);
        params.put("radio17",getrdbtn17);
        params.put("radio18",getrdbtn18);
        params.put("radio19",getrdbtn19);
        params.put("radio20",getrdbtn20);
        params.put("radio21",getrdbtn21);
        params.put("radio22",getrdbtn22);
        params.put("main_id",IPM_Title_1.Main_ID);



        Log.e("MMMMM 444",""+params);


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/IPM/in_ipm_cus_pest_3.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();

                            Log.e( "JJJJ IPM 3",""+response );
                            Intent i=new Intent( IPM_Cus_Pest_3.this,IPM_Facility_4.class);
                            startActivity( i );

                            // Toast.makeText( IPM_Cus_Pest_3.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        Log.e("CCVCVC","error = "+error.getMessage());

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


        String  url="https://rauditor.riflows.com/rauditor/Android/IPM/get_ipm_cus_pes_3.php?main_id="+IPM_Title_1.Main_ID  ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            Log.e("BNBN","qsr 3 res ="+response);


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                Log.e("VBVB","FGFG");
                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "main_id" );
                                String get_occ = c.getString( "occ" );
                                String get_po_cus = c.getString( "po_cus" );
                                String get_branch_lic = c.getString( "branch_lic" );
                                String get_aproved_apl = c.getString( "aproved_apl" );
                                String get_antidote_list = c.getString( "antidote_list" );
                                String get_advance_service= c.getString( "advance_service" );
                                String get_msds_approved= c.getString( "msds_approved" );
                                String get_label_copies= c.getString( "label_copies" );
                                String get_pest_sighting= c.getString( "pest_sighting" );
                                String get_technician_certificate= c.getString( "technician_certificate" );
                                String get_technician_id= c.getString( "technician_id" );
                                String get_health_records= c.getString( "health_records" );
                                String get_public_insurance= c.getString( "public_insurance" );
                                String get_pms_log= c.getString( "pms_log" );
                                String get_prepare_logt= c.getString( "prepare_log" );
                                String get_floor_map_sensitive= c.getString( "floor_map_sensitive" );
                                String get_floor_plan_cockroach= c.getString( "floor_plan_cockroach" );
                                String get_floor_plan_rodent= c.getString( "floor_plan_rodent" );
                                String get_floor_plan_insect= c.getString( "floor_plan_insect" );
                                String get_floor_plan_sip= c.getString( "floor_plan_sip" );
                                String get_trend_analysis= c.getString( "trend_analysis" );
                                String get_audit_reports= c.getString( "audit_reports" );
                                Log.e("EEED","before db");

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDFCSGFS","update value = "+c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS));

                                Log.e("EEED","before else");

                                if(get_pmca_complete.equalsIgnoreCase("2")) {


                                    ipmcuspestbck.setVisibility(View.GONE);
                                    ipmcuspestsub.setVisibility(View.GONE);
                                    ipm_cuspest_finish_1.setVisibility(View.VISIBLE);

                                    Log.e("EEE", "qsr entry1");


                                    ipm_cupest_radiogroup1.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup1.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup1.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup2.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup2.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup2.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup3.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup3.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup3.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup4.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup4.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup4.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup5.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup5.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup5.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup6.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup6.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup6.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup7.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup7.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup7.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup8.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup8.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup8.getChildAt(2).setEnabled(false);



                                    ipm_cupest_radiogroup9.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup9.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup9.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup10.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup10.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup10.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup11.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup11.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup11.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup12.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup12.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup12.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup13.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup13.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup13.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup14.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup14.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup14.getChildAt(2).setEnabled(false);

                                    ipm_cupest_radiogroup15.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup15.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup15.getChildAt(2).setEnabled(false);

                                    ipm_cupest_radiogroup16.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup16.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup16.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup17.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup17.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup17.getChildAt(2).setEnabled(false);
                                    ipm_cupest_radiogroup17.getChildAt(3).setEnabled(false);


                                    ipm_cupest_radiogroup18.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup18.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup18.getChildAt(2).setEnabled(false);

                                    ipm_cupest_radiogroup19.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup19.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup19.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup20.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup20.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup20.getChildAt(2).setEnabled(false);


                                    ipm_cupest_radiogroup21.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup21.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup21.getChildAt(2).setEnabled(false);

                                    ipm_cupest_radiogroup22.getChildAt(0).setEnabled(false);
                                    ipm_cupest_radiogroup22.getChildAt(1).setEnabled(false);
                                    ipm_cupest_radiogroup22.getChildAt(2).setEnabled(false);





                                    if (get_occ.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup1.getChildAt(0)).setChecked(true);


                                    } else if (get_occ.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup1.getChildAt(1)).setChecked(true);

                                    } else if (get_occ.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup1.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_po_cus.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup2.getChildAt(0)).setChecked(true);


                                    } else if (get_po_cus.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup2.getChildAt(1)).setChecked(true);

                                    } else if (get_po_cus.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup2.getChildAt(2)).setChecked(true);

                                    }

                                    /////////////////////////////NEXT//////////////////////////////////////////
                                    if (get_branch_lic.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup3.getChildAt(0)).setChecked(true);


                                    } else if (get_branch_lic.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup3.getChildAt(1)).setChecked(true);

                                    } else if (get_branch_lic.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup3.getChildAt(2)).setChecked(true);

                                    }


                                    /////////////////////////////NEXT//////////////////////////////////////////
                                    if (get_aproved_apl.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup4.getChildAt(0)).setChecked(true);


                                    } else if (get_aproved_apl.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup4.getChildAt(1)).setChecked(true);

                                    } else if (get_aproved_apl.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup4.getChildAt(2)).setChecked(true);

                                    }

                                    /////////////////////////////NEXT//////////////////////////////////////////
                                    if (get_antidote_list.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup5.getChildAt(0)).setChecked(true);


                                    } else if (get_antidote_list.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup5.getChildAt(1)).setChecked(true);

                                    } else if (get_antidote_list.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup5.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    /////////////////////////////NEXT//////////////////////////////////////////
                                    if (get_advance_service.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup6.getChildAt(0)).setChecked(true);


                                    } else if (get_advance_service.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup6.getChildAt(1)).setChecked(true);

                                    } else if (get_advance_service.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup6.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    /////////////////////////////NEXT//////////////////////////////////////////
                                    if (get_msds_approved.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup7.getChildAt(0)).setChecked(true);


                                    } else if (get_msds_approved.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup7.getChildAt(1)).setChecked(true);

                                    } else if (get_msds_approved.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup7.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    /////////////////////////////NEXT//////////////////////////////////////////
                                    if (get_label_copies.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup8.getChildAt(0)).setChecked(true);


                                    } else if (get_label_copies.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup8.getChildAt(1)).setChecked(true);

                                    } else if (get_label_copies.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup8.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_pest_sighting.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup9.getChildAt(0)).setChecked(true);


                                    } else if (get_pest_sighting.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup9.getChildAt(1)).setChecked(true);

                                    } else if (get_pest_sighting.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup9.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_technician_certificate.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup10.getChildAt(0)).setChecked(true);


                                    } else if (get_technician_certificate.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup10.getChildAt(1)).setChecked(true);

                                    } else if (get_technician_certificate.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup10.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_technician_id.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup11.getChildAt(0)).setChecked(true);


                                    } else if (get_technician_id.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup11.getChildAt(1)).setChecked(true);

                                    } else if (get_technician_id.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup11.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_health_records.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup12.getChildAt(0)).setChecked(true);


                                    } else if (get_health_records.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup12.getChildAt(1)).setChecked(true);

                                    } else if (get_health_records.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup12.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_public_insurance.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup13.getChildAt(0)).setChecked(true);


                                    } else if (get_public_insurance.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup13.getChildAt(1)).setChecked(true);

                                    } else if (get_public_insurance.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup13.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_pms_log.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup14.getChildAt(0)).setChecked(true);


                                    } else if (get_pms_log.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup14.getChildAt(1)).setChecked(true);

                                    } else if (get_pms_log.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup14.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_prepare_logt.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup15.getChildAt(0)).setChecked(true);


                                    } else if (get_prepare_logt.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup15.getChildAt(1)).setChecked(true);

                                    } else if (get_prepare_logt.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup15.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_floor_map_sensitive.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup16.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_map_sensitive.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup16.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_map_sensitive.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup16.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_floor_plan_cockroach.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup17.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_plan_cockroach.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup17.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_plan_cockroach.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup17.getChildAt(2)).setChecked(true);

                                    } else if (get_floor_plan_cockroach.equalsIgnoreCase("NOT APPLICABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup17.getChildAt(3)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_floor_plan_rodent.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup18.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_plan_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup18.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_plan_rodent.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup18.getChildAt(2)).setChecked(true);

                                    }


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_floor_plan_insect.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup19.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_plan_insect.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup19.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_plan_insect.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup19.getChildAt(2)).setChecked(true);

                                    } else if (get_floor_plan_insect.equalsIgnoreCase("NOT APPLICABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup19.getChildAt(3)).setChecked(true);

                                    }


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_floor_plan_sip.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup20.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_plan_sip.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup20.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_plan_sip.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup20.getChildAt(2)).setChecked(true);

                                    } else if (get_floor_plan_sip.equalsIgnoreCase("NOT APPLICABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup20.getChildAt(3)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_trend_analysis.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup21.getChildAt(0)).setChecked(true);


                                    } else if (get_trend_analysis.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup21.getChildAt(1)).setChecked(true);

                                    } else if (get_trend_analysis.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup21.getChildAt(2)).setChecked(true);

                                    }
                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    /////////////////////////////NEXT//////////////////////////////////////////

                                    if (get_audit_reports.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) ipm_cupest_radiogroup22.getChildAt(0)).setChecked(true);


                                    } else if (get_audit_reports.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) ipm_cupest_radiogroup22.getChildAt(1)).setChecked(true);

                                    } else if (get_audit_reports.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) ipm_cupest_radiogroup22.getChildAt(2)).setChecked(true);

                                    }

                                    pd.dismiss();
                                    /////////////////////////////NEXT//////////////////////////////////////////


                                } else if(get_pmca_complete.equalsIgnoreCase("1")) {


                                    pd.dismiss();

                                    Log.e("EEE", "qsr entry1");


                                        if(get_occ.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup1.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_occ.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup1.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_occ.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup1.getChildAt(2)).setChecked(true);

                                        }

                                        if(get_po_cus.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup2.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_po_cus.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup2.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_po_cus.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup2.getChildAt(2)).setChecked(true);

                                        }

                                        /////////////////////////////NEXT//////////////////////////////////////////
                                        if(get_branch_lic.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup3.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_branch_lic.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup3.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_branch_lic.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup3.getChildAt(2)).setChecked(true);

                                        }


                                        /////////////////////////////NEXT//////////////////////////////////////////
                                        if(get_aproved_apl.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup4.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_aproved_apl.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup4.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_aproved_apl.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup4.getChildAt(2)).setChecked(true);

                                        }

                                        /////////////////////////////NEXT//////////////////////////////////////////
                                        if(get_antidote_list.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup5.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_antidote_list.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup5.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_antidote_list.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup5.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        /////////////////////////////NEXT//////////////////////////////////////////
                                        if(get_advance_service.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup6.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_advance_service.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup6.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_advance_service.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup6.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        /////////////////////////////NEXT//////////////////////////////////////////
                                        if(get_msds_approved.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup7.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_msds_approved.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup7.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_msds_approved.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup7.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        /////////////////////////////NEXT//////////////////////////////////////////
                                        if(get_label_copies.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup8.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_label_copies.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup8.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_label_copies.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup8.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_pest_sighting.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup9.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_pest_sighting.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup9.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_pest_sighting.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup9.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_technician_certificate.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup10.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_technician_certificate.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup10.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_technician_certificate.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup10.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////




                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_technician_id.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup11.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_technician_id.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup11.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_technician_id.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup11.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////


                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_health_records.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup12.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_health_records.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup12.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_health_records.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup12.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////


                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_public_insurance.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup13.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_public_insurance.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup13.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_public_insurance.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup13.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////


                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_pms_log.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup14.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_pms_log.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup14.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_pms_log.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup14.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////


                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_prepare_logt.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup15.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_prepare_logt.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup15.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_prepare_logt.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup15.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_floor_map_sensitive.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup16.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_map_sensitive.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup16.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_map_sensitive.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup16.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_floor_plan_cockroach.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup17.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_plan_cockroach.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup17.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_plan_cockroach.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup17.getChildAt(2)).setChecked(true);

                                        }

                                        else if (get_floor_plan_cockroach.equalsIgnoreCase("NOT APPLICABLE")) {

                                            ((RadioButton) ipm_cupest_radiogroup17.getChildAt(3)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////


                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_floor_plan_rodent.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup18.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_plan_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup18.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_plan_rodent.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup18.getChildAt(2)).setChecked(true);

                                        }


                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_floor_plan_insect.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup19.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_plan_insect.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup19.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_plan_insect.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup19.getChildAt(2)).setChecked(true);

                                        }
                                        else if(get_floor_plan_insect.equalsIgnoreCase("NOT APPLICABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup19.getChildAt(3)).setChecked(true);

                                        }



                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_floor_plan_sip.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup20.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_plan_sip.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup20.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_plan_sip.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup20.getChildAt(2)).setChecked(true);

                                        }

                                        else if(get_floor_plan_sip.equalsIgnoreCase("NOT APPLICABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup20.getChildAt(3)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////


                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_trend_analysis.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup21.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_trend_analysis.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup21.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_trend_analysis.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup21.getChildAt(2)).setChecked(true);

                                        }
                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        /////////////////////////////NEXT//////////////////////////////////////////

                                        if(get_audit_reports.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) ipm_cupest_radiogroup22.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_audit_reports.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) ipm_cupest_radiogroup22.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_audit_reports.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) ipm_cupest_radiogroup22.getChildAt(2)).setChecked(true);

                                        }





                                }




                            }

                            //   Toast.makeText( QSR_Cus_Pest_3.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );
                        Log.e("GFGF","error ipmcus 3"+error.getMessage());

                        pd.dismiss();
                    }
                } )
                .requestJson();
    }



    private void ipm_cus_pest_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void ipm_cus_pest_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }






}
