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

public class AIB_Ext_Obser_5_1 extends AppCompatActivity {

    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status = "0";

    private FirebaseAnalytics mFirebaseAnalytics;

    Button aibextrnlobsub,aibextrnlobbck,aib_extrobs1_finish1;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    //HEAD!
    RadioGroup aib_ex1_headrd1,aib_ex1_prio1,aib_ex1_obser1,aib_ex1_ass1;
    TextView aib_ex1_s1;
    EditText aib_ex1_rpci1,aib_ex1_rcus1;
    public static TextView aib_ex1_disp1;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    RadioButton headbtn1,priobtn1,obserbtn1,assbtn1;

    String gethead1,getprio1,getobser1,getass1,gets1,getrpci1,getrcus1,getrdisp1;

    //HEAD2
    RadioGroup aib_ex1_headrd2,aib_ex1_prio2,aib_ex1_obser2,aib_ex1_ass2;
    TextView aib_ex1_s2;
    EditText aib_ex1_rpci2,aib_ex1_rcus2;
    public static TextView aib_ex1_disp2;

    RadioButton headbtn2,priobtn2,obserbtn2,assbtn2;

    String gethead2,getprio2,getobser2,getass2,gets2,getrpci2,getrcus2,getrdisp2;

    //HEAD3
    RadioGroup aib_ex1_headrd3,aib_ex1_prio3,aib_ex1_obser3,aib_ex1_ass3;
    TextView aib_ex1_s3;
    EditText aib_ex1_rpci3,aib_ex1_rcus3;
    public static TextView aib_ex1_disp3;


    RadioButton headbtn3,priobtn3,obserbtn3,assbtn3;
    String gethead3,getprio3,getobser3,getass3,gets3,getrpci3,getrcus3,getrdisp3;

    //HEAD4
    RadioGroup aib_ex1_headrd4,aib_ex1_prio4,aib_ex1_obser4,aib_ex1_ass4;
    TextView aib_ex1_s4;
    EditText aib_ex1_rpci4,aib_ex1_rcus4;
    public static TextView aib_ex1_disp4;

    RadioButton headbtn4,priobtn4,obserbtn4,assbtn4;
    String gethead4,getprio4,getobser4,getass4,gets4,getrpci4,getrcus4,getrdisp4;

    //HEAD5
    RadioGroup aib_ex1_headrd5,aib_ex1_prio5,aib_ex1_obser5,aib_ex1_ass5;
    TextView aib_ex1_s5;
    EditText aib_ex1_rpci5,aib_ex1_rcus5;
    public static TextView aib_ex1_disp5;

    RadioButton headbtn5,priobtn5,obserbtn5,assbtn5;
    String gethead5,getprio5,getobser5,getass5,gets5,getrpci5,getrcus5,getrdisp5;

    //HEAD6
    RadioGroup aib_ex1_headrd6,aib_ex1_prio6,aib_ex1_obser6,aib_ex1_ass6;
    TextView aib_ex1_s6;
    EditText aib_ex1_rpci6,aib_ex1_rcus6;
    public static TextView aib_ex1_disp6;

    RadioButton headbtn6,priobtn6,obserbtn6,assbtn6;
    String gethead6,getprio6,getobser6,getass6,gets6,getrpci6,getrcus6,getrdisp6;

    //HEAD7
    RadioGroup aib_ex1_headrd7,aib_ex1_prio7,aib_ex1_obser7,aib_ex1_ass7;
    TextView aib_ex1_s7;
    EditText aib_ex1_rpci7,aib_ex1_rcus7;
    public static TextView aib_ex1_disp7;

    RadioButton headbtn7,priobtn7,obserbtn7,assbtn7;
    String gethead7,getprio7,getobser7,getass7,gets7,getrpci7,getrcus7,getrdisp7;

    //HEAD8
    RadioGroup aib_ex1_headrd8,aib_ex1_headrd8_1,aib_ex1_prio8,aib_ex1_obser8,aib_ex1_ass8;
    TextView aib_ex1_s8;
    EditText aib_ex1_rpci8,aib_ex1_rcus8;
    public static TextView aib_ex1_disp8;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    private android.app.AlertDialog pd;


    RadioButton headbtn8,priobtn8,obserbtn8,assbtn8;

    String gethead8,getprio8,getobser8,getass8,gets8,getrpci8,getrcus8,getrdisp8;

    ArrayList<String> header_arr = new ArrayList<String>();
    ArrayList<String> pestcover_arr = new ArrayList<String>();
    ArrayList<String> recomm_pci_arr = new ArrayList<String>();
    ArrayList<String> recomm_cus_arr = new ArrayList<String>();
    ArrayList<String> pri_arr = new ArrayList<String>();
    ArrayList<String> obs_status_arr = new ArrayList<String>();
    ArrayList<String> assign_to_arr = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aib__extrnlobser);

        db = new DatabaseHelper(AIB_Ext_Obser_5_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        aibextrnlobsub=(Button) findViewById(R.id.aibtitlesub12);
        aibextrnlobbck=(Button) findViewById(R.id.aibtitlebck12);
        aib_extrobs1_finish1=(Button) findViewById(R.id.aib_extrobs1_finish1);

        aib_extrobs1_finish1.setVisibility(View.GONE);

        aib_extrobs1_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                aib_extr_1_button_next();
                Intent i = new Intent(AIB_Ext_Obser_5_1.this,AIB_Ext_Obser_5_2_1.class);
                startActivity(i);

            }
        });

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();
        pd = new SpotsDialog(AIB_Ext_Obser_5_1.this, R.style.Custom);

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
 //HEAD1
        aib_ex1_headrd1=(RadioGroup) findViewById(R.id.aib_ex1_headrd1);
        aib_ex1_prio1=(RadioGroup) findViewById(R.id.aib_ex1_prio1);
        aib_ex1_obser1=(RadioGroup) findViewById(R.id.aib_ex1_obser1);
        aib_ex1_ass1=(RadioGroup) findViewById(R.id.aib_ex1_ass1);
        aib_ex1_s1=(TextView) findViewById(R.id.aib_ex1_s1);
        aib_ex1_rpci1=(EditText) findViewById(R.id.aib_ex1_rpci1);
        aib_ex1_rcus1=(EditText) findViewById(R.id.aib_ex1_rcus1);
        aib_ex1_disp1=(TextView) findViewById(R.id.aib_ex1_disp1);


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Ext_Obser_5_1.this);
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
                        Toast.makeText(AIB_Ext_Obser_5_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Ext_Obser_5_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        aib_ex1_headrd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd1.getCheckedRadioButtonId();

                headbtn1=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_prio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio1.getCheckedRadioButtonId();

                priobtn1=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_obser1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser1.getCheckedRadioButtonId();

                obserbtn1 =(RadioButton)findViewById(selectedId);


            }
        });

        aib_ex1_ass1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass1.getCheckedRadioButtonId();

                assbtn1=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2222);
            }
        });






        //HEAD2

        aib_ex1_headrd2=(RadioGroup) findViewById(R.id.aib_ex1_headrd2);
        aib_ex1_prio2=(RadioGroup) findViewById(R.id.aib_ex1_prio2);
        aib_ex1_obser2=(RadioGroup) findViewById(R.id.aib_ex1_obser2);
        aib_ex1_ass2=(RadioGroup) findViewById(R.id.aib_ex1_ass2);
        aib_ex1_s2=(TextView) findViewById(R.id.aib_ex1_s2);
        aib_ex1_rpci2=(EditText) findViewById(R.id.aib_ex1_rpci2);
        aib_ex1_rcus2=(EditText) findViewById(R.id.aib_ex1_rcus2);
        aib_ex1_disp2=(TextView) findViewById(R.id.aib_ex1_disp2);


        aib_ex1_headrd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd2.getCheckedRadioButtonId();

                headbtn2=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_prio2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio2.getCheckedRadioButtonId();

                priobtn2=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_obser2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser2.getCheckedRadioButtonId();

                obserbtn2 =(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_ass2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass2.getCheckedRadioButtonId();

                assbtn2=(RadioButton)findViewById(selectedId);


            }
        });

        aib_ex1_s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2223);
            }
        });




        //HEAD3

        aib_ex1_headrd3=(RadioGroup) findViewById(R.id.aib_ex1_headrd3);
        aib_ex1_prio3=(RadioGroup) findViewById(R.id.aib_ex1_prio3);
        aib_ex1_obser3=(RadioGroup) findViewById(R.id.aib_ex1_obser3);
        aib_ex1_ass3=(RadioGroup) findViewById(R.id.aib_ex1_ass3);
        aib_ex1_s3=(TextView) findViewById(R.id.aib_ex1_s3);
        aib_ex1_rpci3=(EditText) findViewById(R.id.aib_ex1_rpci3);
        aib_ex1_rcus3=(EditText) findViewById(R.id.aib_ex1_rcus3);
        aib_ex1_disp3=(TextView) findViewById(R.id.aib_ex1_disp3);


        aib_ex1_headrd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd3.getCheckedRadioButtonId();

                headbtn3=(RadioButton)findViewById(selectedId);


            }
        });

        aib_ex1_prio3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio3.getCheckedRadioButtonId();

                priobtn3=(RadioButton)findViewById(selectedId);

            }
        });

        aib_ex1_obser3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser3.getCheckedRadioButtonId();

                obserbtn3 =(RadioButton)findViewById(selectedId);





            }
        });

        aib_ex1_ass3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass3.getCheckedRadioButtonId();

                assbtn3=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2224);
            }
        });



        //HEAD4

        aib_ex1_headrd4=(RadioGroup) findViewById(R.id.aib_ex1_headrd4);
        aib_ex1_prio4=(RadioGroup) findViewById(R.id.aib_ex1_prio4);
        aib_ex1_obser4=(RadioGroup) findViewById(R.id.aib_ex1_obser4);
        aib_ex1_ass4=(RadioGroup) findViewById(R.id.aib_ex1_ass4);
        aib_ex1_s4=(TextView) findViewById(R.id.aib_ex1_s4);
        aib_ex1_rpci4=(EditText) findViewById(R.id.aib_ex1_rpci4);
        aib_ex1_rcus4=(EditText) findViewById(R.id.aib_ex1_rcus4);
        aib_ex1_disp4=(TextView) findViewById(R.id.aib_ex1_disp4);


        aib_ex1_headrd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd4.getCheckedRadioButtonId();
                headbtn4=(RadioButton)findViewById(selectedId);



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

        aib_ex1_prio4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio4.getCheckedRadioButtonId();

                priobtn4=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_obser4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser4.getCheckedRadioButtonId();

                obserbtn4 =(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_ass4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass4.getCheckedRadioButtonId();

                assbtn4=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_s4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2225);
            }
        });



        //HEAD5

        aib_ex1_headrd5=(RadioGroup) findViewById(R.id.aib_ex1_headrd5);
        aib_ex1_prio5=(RadioGroup) findViewById(R.id.aib_ex1_prio5);
        aib_ex1_obser5=(RadioGroup) findViewById(R.id.aib_ex1_obser5);
        aib_ex1_ass5=(RadioGroup) findViewById(R.id.aib_ex1_ass5);
        aib_ex1_s5=(TextView) findViewById(R.id.aib_ex1_s5);
        aib_ex1_rpci5=(EditText) findViewById(R.id.aib_ex1_rpci5);
        aib_ex1_rcus5=(EditText) findViewById(R.id.aib_ex1_rcus5);
        aib_ex1_disp5=(TextView) findViewById(R.id.aib_ex1_disp5);


        aib_ex1_headrd5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd5.getCheckedRadioButtonId();

                headbtn5=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_prio5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio5.getCheckedRadioButtonId();

                priobtn5=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_obser5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser5.getCheckedRadioButtonId();

                obserbtn5 =(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_ass5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass5.getCheckedRadioButtonId();

                assbtn5=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_s5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2226);
            }
        });




        //HEAD6

        aib_ex1_headrd6=(RadioGroup) findViewById(R.id.aib_ex1_headrd6);
        aib_ex1_prio6=(RadioGroup) findViewById(R.id.aib_ex1_prio6);
        aib_ex1_obser6=(RadioGroup) findViewById(R.id.aib_ex1_obser6);
        aib_ex1_ass6=(RadioGroup) findViewById(R.id.aib_ex1_ass6);
        aib_ex1_s6=(TextView) findViewById(R.id.aib_ex1_s6);
        aib_ex1_rpci6=(EditText) findViewById(R.id.aib_ex1_rpci6);
        aib_ex1_rcus6=(EditText) findViewById(R.id.aib_ex1_rcus6);
        aib_ex1_disp6=(TextView) findViewById(R.id.aib_ex1_disp6);



        aib_ex1_headrd6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd6.getCheckedRadioButtonId();

                headbtn6=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_prio6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio6.getCheckedRadioButtonId();

                priobtn6=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_obser6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser6.getCheckedRadioButtonId();

                obserbtn6 =(RadioButton)findViewById(selectedId);


            }
        });

        aib_ex1_ass6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass6.getCheckedRadioButtonId();

                assbtn6=(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_s6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2227);
            }
        });



        //HEAD7

        aib_ex1_headrd7=(RadioGroup) findViewById(R.id.aib_ex1_headrd7);
        aib_ex1_prio7=(RadioGroup) findViewById(R.id.aib_ex1_prio7);
        aib_ex1_obser7=(RadioGroup) findViewById(R.id.aib_ex1_obser7);
        aib_ex1_ass7=(RadioGroup) findViewById(R.id.aib_ex1_ass7);
        aib_ex1_s7=(TextView) findViewById(R.id.aib_ex1_s7);
        aib_ex1_rpci7=(EditText) findViewById(R.id.aib_ex1_rpci7);
        aib_ex1_rcus7=(EditText) findViewById(R.id.aib_ex1_rcus7);
        aib_ex1_disp7=(TextView) findViewById(R.id.aib_ex1_disp7);


        aib_ex1_headrd7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd7.getCheckedRadioButtonId();

                headbtn7 =(RadioButton)findViewById(selectedId);





            }
        });

        aib_ex1_prio7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio7.getCheckedRadioButtonId();

                priobtn7=(RadioButton)findViewById(selectedId);


            }
        });

        aib_ex1_obser7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser7.getCheckedRadioButtonId();

                obserbtn7 =(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_ass7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass7.getCheckedRadioButtonId();

                assbtn7=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_s7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2228);
            }
        });

        //HEAD8

        aib_ex1_headrd8=(RadioGroup) findViewById(R.id.aib_ex1_headrd8);
        aib_ex1_prio8=(RadioGroup) findViewById(R.id.aib_ex1_prio8);
        aib_ex1_obser8=(RadioGroup) findViewById(R.id.aib_ex1_obser8);
        aib_ex1_ass8=(RadioGroup) findViewById(R.id.aib_ex1_ass8);
        aib_ex1_s8=(TextView) findViewById(R.id.aib_ex1_s8);
        aib_ex1_rpci8=(EditText) findViewById(R.id.aib_ex1_rpci8);
        aib_ex1_rcus8=(EditText) findViewById(R.id.aib_ex1_rcus8);
        aib_ex1_disp8=(TextView) findViewById(R.id.aib_ex1_disp8);


        aib_ex1_headrd8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_headrd8.getCheckedRadioButtonId();

                headbtn8 =(RadioButton)findViewById(selectedId);


            }
        });

        aib_ex1_prio8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_prio8.getCheckedRadioButtonId();

                priobtn8=(RadioButton)findViewById(selectedId);


            }
        });

        aib_ex1_obser8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_obser8.getCheckedRadioButtonId();

                obserbtn8 =(RadioButton)findViewById(selectedId);



            }
        });

        aib_ex1_ass8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_ex1_ass8.getCheckedRadioButtonId();

                assbtn8=(RadioButton)findViewById(selectedId);




            }
        });

        aib_ex1_s8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_extrob_1(2229);
            }
        });












        aibextrnlobsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(AIB_Ext_Obser_5_1.this);
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
                                Toast.makeText(AIB_Ext_Obser_5_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Ext_Obser_5_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {

                    aib_extr_1_button_next();
                    post_aibex1();
                } else {
                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }





            }
        });

        aibextrnlobbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_extr_1_button_back();

                Intent i = new Intent(AIB_Ext_Obser_5_1.this,AIB_Facility_4.class);
                startActivity(i);


            }
        });
    }

    public  boolean validation(){


        try {
            getrpci1=aib_ex1_rpci1.getText().toString();
            getrcus1=aib_ex1_rcus1.getText().toString();
            getrdisp1=aib_ex1_disp1.getText().toString();

            getrpci2=aib_ex1_rpci2.getText().toString();
            getrcus2=aib_ex1_rcus2.getText().toString();
            getrdisp2=aib_ex1_disp2.getText().toString();

            getrpci3=aib_ex1_rpci3.getText().toString();
            getrcus3=aib_ex1_rcus3.getText().toString();
            getrdisp3=aib_ex1_disp3.getText().toString();

            getrpci4=aib_ex1_rpci4.getText().toString();
            getrcus4=aib_ex1_rcus4.getText().toString();
            getrdisp4=aib_ex1_disp4.getText().toString();

            getrpci5=aib_ex1_rpci5.getText().toString();
            getrcus5=aib_ex1_rcus5.getText().toString();
            getrdisp5=aib_ex1_disp5.getText().toString();

            getrpci6=aib_ex1_rpci6.getText().toString();
            getrcus6=aib_ex1_rcus6.getText().toString();
            getrdisp6=aib_ex1_disp6.getText().toString();


            getrpci7=aib_ex1_rpci7.getText().toString();
            getrcus7=aib_ex1_rcus7.getText().toString();
            getrdisp7=aib_ex1_disp7.getText().toString();

            getrpci8=aib_ex1_rpci8.getText().toString();
            getrcus8=aib_ex1_rcus8.getText().toString();
            getrdisp8=aib_ex1_disp8.getText().toString();

            if(TextUtils.isEmpty(getrpci1 ) || TextUtils.isEmpty(getrcus1) ||TextUtils.isEmpty(getrdisp1)
                    ||TextUtils.isEmpty(getrpci2) ||TextUtils.isEmpty(getrcus2)
                    ||TextUtils.isEmpty(getrpci3) ||TextUtils.isEmpty(getrcus3)
                    ||TextUtils.isEmpty(getrpci4) ||TextUtils.isEmpty(getrcus4)
                    ||TextUtils.isEmpty(getrpci5) ||TextUtils.isEmpty(getrcus5)
                    ||TextUtils.isEmpty(getrpci6) ||TextUtils.isEmpty(getrcus6)
                    ||TextUtils.isEmpty(getrpci7) ||TextUtils.isEmpty(getrcus7)
                    ||TextUtils.isEmpty(getrpci8) ||TextUtils.isEmpty(getrcus8)


                    ) {

                if(TextUtils.isEmpty(getrpci1 )){
                    aib_ex1_rpci1.setError("Required");
                }

                if(TextUtils.isEmpty(getrcus1)) {
                    aib_ex1_rcus1.setError("Required");


                }
//                if(TextUtils.isEmpty(getrdisp1)) {
//                    aib_ex1_disp1.setError("Required");
//
//                }

                if(TextUtils.isEmpty(getrpci2 )){
                    aib_ex1_rpci2.setError("Required");
                }
                if(TextUtils.isEmpty(getrcus2)) {
                    aib_ex1_rcus2.setError("Required");

                }
//                if(TextUtils.isEmpty(getrdisp2)) {
//                    aib_ex1_disp2.setError("Required");
//
//                }
                if(TextUtils.isEmpty(getrpci3 )){
                    aib_ex1_rpci3.setError("Required");
                }

                if(TextUtils.isEmpty(getrcus3)) {
                    aib_ex1_rcus3.setError("Required");


                }
//                if(TextUtils.isEmpty(getrdisp3)) {
//                    aib_ex1_disp3.setError("Required");
//
//                }
                if(TextUtils.isEmpty(getrpci4 )){
                    aib_ex1_rpci4.setError("Required");
                }

                if(TextUtils.isEmpty(getrcus4)) {
                    aib_ex1_rcus4.setError("Required");
                }
//                if(TextUtils.isEmpty(getrdisp4)) {
//                    aib_ex1_disp4.setError("Required");
//
//                }



                if(TextUtils.isEmpty(getrpci5 )){
                    aib_ex1_rpci5.setError("Required");
                }

                if(TextUtils.isEmpty(getrcus5)) {
                    aib_ex1_rcus5.setError("Required");
                }
//                if(TextUtils.isEmpty(getrdisp5)) {
//                    aib_ex1_disp5.setError("Required");
//
//                }


                if(TextUtils.isEmpty(getrpci6 )){
                    aib_ex1_rpci6.setError("Required");
                }

                if(TextUtils.isEmpty(getrcus6)) {
                    aib_ex1_rcus6.setError("Required");
                }
//                if(TextUtils.isEmpty(getrdisp6)) {
//                    aib_ex1_disp6.setError("Required");
//
//                }


                if(TextUtils.isEmpty(getrpci7 )){
                    aib_ex1_rpci7.setError("Required");
                }

                if(TextUtils.isEmpty(getrcus7)) {
                    aib_ex1_rcus7.setError("Required");
                }
//                if(TextUtils.isEmpty(getrdisp7)) {
//                    aib_ex1_disp7.setError("Required");
//
//                }


                if(TextUtils.isEmpty(getrpci8 )){
                    aib_ex1_rpci8.setError("Required");
                }

                if(TextUtils.isEmpty(getrcus8)) {
                    aib_ex1_rcus8.setError("Required");
                }
//                if(TextUtils.isEmpty(getrdisp8)) {
//                    aib_ex1_disp8.setError("Required");
//
//                }

                return false;
            }


            gethead1 = ((RadioButton)findViewById(aib_ex1_headrd1.getCheckedRadioButtonId())).getText().toString();
            getprio1 = ((RadioButton)findViewById(aib_ex1_prio1.getCheckedRadioButtonId())).getText().toString();
            getobser1 = ((RadioButton)findViewById(aib_ex1_obser1.getCheckedRadioButtonId())).getText().toString();
            getass1 = ((RadioButton)findViewById(aib_ex1_ass1.getCheckedRadioButtonId())).getText().toString();
            gethead2 = ((RadioButton)findViewById(aib_ex1_headrd2.getCheckedRadioButtonId())).getText().toString();
            getprio2 = ((RadioButton)findViewById(aib_ex1_prio2.getCheckedRadioButtonId())).getText().toString();
            getobser2 = ((RadioButton)findViewById(aib_ex1_obser2.getCheckedRadioButtonId())).getText().toString();
            getass2 = ((RadioButton)findViewById(aib_ex1_ass2.getCheckedRadioButtonId())).getText().toString();
            gethead3 = ((RadioButton)findViewById(aib_ex1_headrd3.getCheckedRadioButtonId())).getText().toString();
            getprio3 = ((RadioButton)findViewById(aib_ex1_prio3.getCheckedRadioButtonId())).getText().toString();
            getobser3 = ((RadioButton)findViewById(aib_ex1_obser3.getCheckedRadioButtonId())).getText().toString();
            getass3 = ((RadioButton)findViewById(aib_ex1_ass3.getCheckedRadioButtonId())).getText().toString();
            gethead4 = ((RadioButton)findViewById(aib_ex1_headrd4.getCheckedRadioButtonId())).getText().toString();
            getprio4 = ((RadioButton)findViewById(aib_ex1_prio4.getCheckedRadioButtonId())).getText().toString();
            getobser4 = ((RadioButton)findViewById(aib_ex1_obser4.getCheckedRadioButtonId())).getText().toString();
            getass4 = ((RadioButton)findViewById(aib_ex1_ass4.getCheckedRadioButtonId())).getText().toString();
            gethead5 = ((RadioButton)findViewById(aib_ex1_headrd5.getCheckedRadioButtonId())).getText().toString();
            getprio5= ((RadioButton)findViewById(aib_ex1_prio5.getCheckedRadioButtonId())).getText().toString();
            getobser5 = ((RadioButton)findViewById(aib_ex1_obser5.getCheckedRadioButtonId())).getText().toString();
            getass5 = ((RadioButton)findViewById(aib_ex1_ass5.getCheckedRadioButtonId())).getText().toString();
            gethead6 = ((RadioButton)findViewById(aib_ex1_headrd6.getCheckedRadioButtonId())).getText().toString();
            getprio6= ((RadioButton)findViewById(aib_ex1_prio6.getCheckedRadioButtonId())).getText().toString();
            getobser6 = ((RadioButton)findViewById(aib_ex1_obser6.getCheckedRadioButtonId())).getText().toString();
            getass6 = ((RadioButton)findViewById(aib_ex1_ass6.getCheckedRadioButtonId())).getText().toString();
            gethead7 = ((RadioButton)findViewById(aib_ex1_headrd7.getCheckedRadioButtonId())).getText().toString();
            getprio7 = ((RadioButton)findViewById(aib_ex1_prio7.getCheckedRadioButtonId())).getText().toString();
            getobser7 = ((RadioButton)findViewById(aib_ex1_obser7.getCheckedRadioButtonId())).getText().toString();
            getass7 = ((RadioButton)findViewById(aib_ex1_ass7.getCheckedRadioButtonId())).getText().toString();
            gethead8 = ((RadioButton)findViewById(aib_ex1_headrd8.getCheckedRadioButtonId())).getText().toString();
            getprio8 = ((RadioButton)findViewById(aib_ex1_prio8.getCheckedRadioButtonId())).getText().toString();
            getobser8 = ((RadioButton)findViewById(aib_ex1_obser8.getCheckedRadioButtonId())).getText().toString();
            getass8 = ((RadioButton)findViewById(aib_ex1_ass8.getCheckedRadioButtonId())).getText().toString();






            header_arr.add(gethead1);
            header_arr.add(gethead2);
            header_arr.add(gethead3);
            header_arr.add(gethead4);
            header_arr.add(gethead5);
            header_arr.add(gethead6);
            header_arr.add(gethead7);
            header_arr.add(gethead8);

            pestcover_arr.add(getrdisp1);
            pestcover_arr.add(getrdisp2);
            pestcover_arr.add(getrdisp3);
            pestcover_arr.add(getrdisp4);
            pestcover_arr.add(getrdisp5);
            pestcover_arr.add(getrdisp6);
            pestcover_arr.add(getrdisp7);
            pestcover_arr.add(getrdisp8);


            recomm_pci_arr.add(getrpci1);
            recomm_pci_arr.add(getrpci2);
            recomm_pci_arr.add(getrpci3);
            recomm_pci_arr.add(getrpci4);
            recomm_pci_arr.add(getrpci5);
            recomm_pci_arr.add(getrpci6);
            recomm_pci_arr.add(getrpci7);
            recomm_pci_arr.add(getrpci8);


            recomm_cus_arr.add(getrcus1);
            recomm_cus_arr.add(getrcus2);
            recomm_cus_arr.add(getrcus3);
            recomm_cus_arr.add(getrcus4);
            recomm_cus_arr.add(getrcus5);
            recomm_cus_arr.add(getrcus6);
            recomm_cus_arr.add(getrcus7);
            recomm_cus_arr.add(getrcus8);


            pri_arr.add(getprio1);
            pri_arr.add(getprio2);
            pri_arr.add(getprio3);
            pri_arr.add(getprio4);
            pri_arr.add(getprio5);
            pri_arr.add(getprio6);
            pri_arr.add(getprio7);
            pri_arr.add(getprio8);


            obs_status_arr.add(getobser1);
            obs_status_arr.add(getobser2);
            obs_status_arr.add(getobser3);
            obs_status_arr.add(getobser4);
            obs_status_arr.add(getobser5);
            obs_status_arr.add(getobser6);
            obs_status_arr.add(getobser7);
            obs_status_arr.add(getobser8);


            assign_to_arr.add(getass1);
            assign_to_arr.add(getass2);
            assign_to_arr.add(getass3);
            assign_to_arr.add(getass4);
            assign_to_arr.add(getass5);
            assign_to_arr.add(getass6);
            assign_to_arr.add(getass7);
            assign_to_arr.add(getass8);


            if ((getrpci1.length()==0||getrcus1.length()==0||getrpci2.length()==0||getrcus2.length()==0
                    ||getrpci3.length()==0||getrcus3.length()==0||getrpci4.length()==0||getrcus4.length()==0
                    ||getrpci5.length()==0||getrcus5.length()==0||getrpci6.length()==0||getrcus6.length()==0||getrpci7.length()==0
                    ||getrcus7.length()==0||getrpci8.length()==0||getrcus8.length()==0||gethead1.length()==0||gethead2.length()==0
                    ||gethead3.length()==0||gethead4.length()==0||gethead5.length()==0||gethead6.length()==0||gethead7.length()==0||gethead8.length()==0||getprio1.length()==0||getprio2.length()==0
                    ||getprio3.length()==0||getprio4.length()==0||getprio5.length()==0||getprio6.length()==0||getprio7.length()==0||getprio8.length()==0||getobser1.length()==0||getobser2.length()==0
                    ||getobser3.length()==0||getobser4.length()==0||getobser5.length()==0||getobser6.length()==0||getobser7.length()==0||getobser8.length()==0||getass1.length()==0||getass2.length()==0
                    ||getass3.length()==0||getass4.length()==0||getass5.length()==0||getass6.length()==0||getass7.length()==0||getass8.length()==0


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



    public void aib_extrob_1(int status) {

        Bundle bundle = new Bundle();
        if(status==2222) {
            bundle.putString("status", "2222");
        }

        if(status==2223) {
            bundle.putString("status", "2223");
        }

        if(status==2224) {
            bundle.putString("status", "2224");
        }
        if(status==2225) {
        Log.e("RRRR","main");
            bundle.putString("status", "2225");
        }

        if(status==2226) {
            bundle.putString("status", "2226");
        }
        if(status==2227) {
            bundle.putString("status", "2227");
        }
        if(status==2228) {
            bundle.putString("status", "2228");
        }
        if(status==2229) {
            bundle.putString("status", "2229");
        }



        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    private void post_aibex1() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("count",""+header_arr.size());
        Log.e("MMMMM size",""+header_arr.size());
        Log.e("FFFFF","aibex5.1 = "+AIB_Title_1.Main_ID);
        params.put("main_id",AIB_Title_1.Main_ID);
        for (int i=0;i<header_arr.size();i++) {

            params.put("headid"+i, String.valueOf(i));
            params.put("headi"+i,header_arr.get(i));
            params.put("pestcvri"+i,pestcover_arr.get(i));
            params.put("repci"+i,recomm_pci_arr.get(i));
            params.put("recus"+i,recomm_cus_arr.get(i));
            params.put("priq"+i,pri_arr.get(i));
            params.put("obsq"+i,obs_status_arr.get(i));
            params.put("assq"+i,assign_to_arr.get(i));
            params.put("update_status", Update_Status);
            Log.e("DDDDDD","getParams");
            Log.e("DSDS","params"+recomm_pci_arr.get(i));
        }

        Log.e("DSDS","params 1 ="+params);


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_extr_obs_1.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();

                            Log.e("CDCF","res = "+response);
                            Intent i = new Intent(AIB_Ext_Obser_5_1.this,AIB_Ext_Obser_5_2_1.class);
                            startActivity(i);

                            //   Toast.makeText( AIB_Ext_Obser_5_1.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
Log.e("CDCF","ERRRR"+error.getMessage());

                        if(error.getMessage().equalsIgnoreCase("null")){

                            Intent i = new Intent(AIB_Ext_Obser_5_1.this,AIB_Ext_Obser_5_2_1.class);
                            startActivity(i);
                        }
                        else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                        {
                            Intent i = new Intent(AIB_Ext_Obser_5_1.this,AIB_Ext_Obser_5_2_1.class);
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


        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_aib_extr_obs_5_1.php?main_id=" + AIB_Title_1.Main_ID ;

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

                                    aibextrnlobbck.setVisibility(View.GONE);
                                    aibextrnlobsub.setVisibility(View.GONE);
                                    aib_extrobs1_finish1.setVisibility(View.VISIBLE);

                                    aib_ex1_headrd1.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd1.getChildAt(1).setEnabled(false);

                                    aib_ex1_s1.setEnabled(false);
                                    aib_ex1_rcus1.setEnabled(false);
                                    aib_ex1_rpci1.setEnabled(false);

                                    aib_ex1_prio1.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio1.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio1.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio1.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser1.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser1.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser1.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser1.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser1.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass1.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass1.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass1.getChildAt(2).setEnabled(false);

///////////////////////////////////////NEXT//////////////////////////////////////////////////////////
                                    aib_ex1_headrd2.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd2.getChildAt(1).setEnabled(false);

                                    aib_ex1_s2.setEnabled(false);
                                    aib_ex1_rcus2.setEnabled(false);
                                    aib_ex1_rpci2.setEnabled(false);

                                    aib_ex1_prio2.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio2.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio2.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio2.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser2.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser2.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser2.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser2.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser2.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass2.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass2.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass2.getChildAt(2).setEnabled(false);

                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////
                                    aib_ex1_headrd3.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd3.getChildAt(1).setEnabled(false);

                                    aib_ex1_s3.setEnabled(false);
                                    aib_ex1_rcus3.setEnabled(false);
                                    aib_ex1_rpci3.setEnabled(false);

                                    aib_ex1_prio3.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio3.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio3.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio3.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser3.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser3.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser3.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser3.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser3.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass3.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass3.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass3.getChildAt(2).setEnabled(false);

                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////
                                    aib_ex1_headrd4.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd4.getChildAt(1).setEnabled(false);

                                    aib_ex1_s4.setEnabled(false);
                                    aib_ex1_rcus4.setEnabled(false);
                                    aib_ex1_rpci4.setEnabled(false);

                                    aib_ex1_prio4.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio4.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio4.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio4.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser4.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser4.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser4.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser4.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser4.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass4.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass4.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass4.getChildAt(2).setEnabled(false);

                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////
                                    aib_ex1_headrd5.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd5.getChildAt(1).setEnabled(false);

                                    aib_ex1_s5.setEnabled(false);
                                    aib_ex1_rcus5.setEnabled(false);
                                    aib_ex1_rpci5.setEnabled(false);

                                    aib_ex1_prio5.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio5.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio5.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio5.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser5.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser5.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser5.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser5.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser5.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass5.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass5.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass5.getChildAt(2).setEnabled(false);

                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////
                                    aib_ex1_headrd6.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd6.getChildAt(1).setEnabled(false);

                                    aib_ex1_s6.setEnabled(false);
                                    aib_ex1_rcus6.setEnabled(false);
                                    aib_ex1_rpci6.setEnabled(false);

                                    aib_ex1_prio6.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio6.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio6.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio6.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser6.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser6.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser6.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser6.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser6.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass6.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass6.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass6.getChildAt(2).setEnabled(false);

                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////
                                    aib_ex1_headrd7.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd7.getChildAt(1).setEnabled(false);

                                    aib_ex1_s7.setEnabled(false);
                                    aib_ex1_rcus7.setEnabled(false);
                                    aib_ex1_rpci7.setEnabled(false);

                                    aib_ex1_prio7.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio7.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio7.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio7.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser7.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser7.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser7.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser7.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser7.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass7.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass7.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass7.getChildAt(2).setEnabled(false);

                                    ///////////////////////////////////////NEXT//////////////////////////////////////////////////////////
                                    aib_ex1_headrd8.getChildAt(0).setEnabled(false);
                                    aib_ex1_headrd8.getChildAt(1).setEnabled(false);

                                    aib_ex1_s8.setEnabled(false);
                                    aib_ex1_rcus8.setEnabled(false);
                                    aib_ex1_rpci8.setEnabled(false);

                                    aib_ex1_prio8.getChildAt(0).setEnabled(false);
                                    aib_ex1_prio8.getChildAt(1).setEnabled(false);
                                    aib_ex1_prio8.getChildAt(2).setEnabled(false);
                                    aib_ex1_prio8.getChildAt(3).setEnabled(false);

                                    aib_ex1_obser8.getChildAt(0).setEnabled(false);
                                    aib_ex1_obser8.getChildAt(1).setEnabled(false);
                                    aib_ex1_obser8.getChildAt(2).setEnabled(false);
                                    aib_ex1_obser8.getChildAt(3).setEnabled(false);
                                    aib_ex1_obser8.getChildAt(4).setEnabled(false);

                                    aib_ex1_ass8.getChildAt(0).setEnabled(false);
                                    aib_ex1_ass8.getChildAt(1).setEnabled(false);
                                    aib_ex1_ass8.getChildAt(2).setEnabled(false);






                                    if (i == 0) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd1.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd1.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio1.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio1.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio1.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio1.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser1.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass1.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass1.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass1.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp1.setText(getpest);
                                        aib_ex1_rcus1.setText(getcus);
                                        aib_ex1_rpci1.setText(getpci);


                                    }

                                    if (i == 1) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd2.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd2.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio2.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio2.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio2.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio2.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser2.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass2.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass2.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass2.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp2.setText(getpest);
                                        aib_ex1_rcus2.setText(getcus);
                                        aib_ex1_rpci2.setText(getpci);

                                    }

                                    if (i == 2) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd3.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd3.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio3.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio3.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio3.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio3.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser3.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass3.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass3.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass3.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp3.setText(getpest);
                                        aib_ex1_rcus3.setText(getcus);
                                        aib_ex1_rpci3.setText(getpci);
                                    }

                                    if (i == 3) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd4.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd4.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio4.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio4.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio4.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio4.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser4.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass4.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass4.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass4.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp4.setText(getpest);
                                        aib_ex1_rcus4.setText(getcus);
                                        aib_ex1_rpci4.setText(getpci);
                                    }

                                    if (i == 4) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd5.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd5.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio5.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio5.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio5.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio5.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser5.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass5.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass5.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass5.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp5.setText(getpest);
                                        aib_ex1_rcus5.setText(getcus);
                                        aib_ex1_rpci5.setText(getpci);
                                    }

                                    if (i == 5) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd6.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd6.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio6.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio6.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio6.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio6.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser6.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass6.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass6.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass6.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp6.setText(getpest);
                                        aib_ex1_rcus6.setText(getcus);
                                        aib_ex1_rpci6.setText(getpci);
                                    }

                                    if (i == 6) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd7.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd7.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio7.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio7.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio7.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio7.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser7.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass7.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass7.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass7.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp7.setText(getpest);
                                        aib_ex1_rcus7.setText(getcus);
                                        aib_ex1_rpci7.setText(getpci);
                                    }

                                    if (i == 7) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd8.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd8.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio8.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio8.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio8.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio8.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser8.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass8.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass8.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass8.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp8.setText(getpest);
                                        aib_ex1_rcus8.setText(getcus);
                                        aib_ex1_rpci8.setText(getpci);
                                    }


                                    Update_Status = "2";

                                    pd.dismiss();
                                }
                                else if(getsircuscomp.equalsIgnoreCase("1")) {

                                    Log.e("IIIO","entry2");


                                    if (i == 0) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd1.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd1.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio1.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio1.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio1.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio1.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser1.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser1.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass1.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass1.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass1.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp1.setText(getpest);
                                        aib_ex1_rcus1.setText(getcus);
                                        aib_ex1_rpci1.setText(getpci);


                                    }

                                    if (i == 1) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd2.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd2.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio2.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio2.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio2.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio2.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser2.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser2.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass2.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass2.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass2.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp2.setText(getpest);
                                        aib_ex1_rcus2.setText(getcus);
                                        aib_ex1_rpci2.setText(getpci);

                                    }

                                    if (i == 2) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd3.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd3.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio3.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio3.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio3.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio3.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser3.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser3.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass3.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass3.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass3.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp3.setText(getpest);
                                        aib_ex1_rcus3.setText(getcus);
                                        aib_ex1_rpci3.setText(getpci);
                                    }

                                    if (i == 3) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd4.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd4.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio4.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio4.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio4.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio4.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser4.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser4.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass4.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass4.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass4.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp4.setText(getpest);
                                        aib_ex1_rcus4.setText(getcus);
                                        aib_ex1_rpci4.setText(getpci);
                                    }

                                    if (i == 4) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd5.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd5.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio5.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio5.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio5.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio5.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser5.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser5.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass5.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass5.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass5.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp5.setText(getpest);
                                        aib_ex1_rcus5.setText(getcus);
                                        aib_ex1_rpci5.setText(getpci);
                                    }

                                    if (i == 5) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd6.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd6.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio6.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio6.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio6.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio6.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser6.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser6.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass6.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass6.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass6.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp6.setText(getpest);
                                        aib_ex1_rcus6.setText(getcus);
                                        aib_ex1_rpci6.setText(getpci);
                                    }

                                    if (i == 6) {


                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd7.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd7.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio7.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio7.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio7.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio7.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser7.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser7.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass7.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass7.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass7.getChildAt(2)).setChecked(true);

                                        }


                                        aib_ex1_disp7.setText(getpest);
                                        aib_ex1_rcus7.setText(getcus);
                                        aib_ex1_rpci7.setText(getpci);
                                    }

                                    if (i == 7) {

                                        if (get_header.equalsIgnoreCase("YES")) {
                                            ((RadioButton) aib_ex1_headrd8.getChildAt(0)).setChecked(true);


                                        } else if (get_header.equalsIgnoreCase("NO")) {

                                            ((RadioButton) aib_ex1_headrd8.getChildAt(1)).setChecked(true);

                                        }

                                        if (get_pri.equalsIgnoreCase("HIGH")) {
                                            ((RadioButton) aib_ex1_prio8.getChildAt(0)).setChecked(true);


                                        } else if (get_pri.equalsIgnoreCase("MEDIUM")) {

                                            ((RadioButton) aib_ex1_prio8.getChildAt(1)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("LOW")) {

                                            ((RadioButton) aib_ex1_prio8.getChildAt(2)).setChecked(true);

                                        } else if (get_pri.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_prio8.getChildAt(3)).setChecked(true);

                                        }


                                        if (get_obser_status.equalsIgnoreCase("NEW")) {
                                            ((RadioButton) aib_ex1_obser8.getChildAt(0)).setChecked(true);


                                        } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(1)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(2)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(3)).setChecked(true);

                                        } else if (get_obser_status.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_obser8.getChildAt(4)).setChecked(true);

                                        }

                                        if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                            ((RadioButton) aib_ex1_ass8.getChildAt(0)).setChecked(true);


                                        } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                            ((RadioButton) aib_ex1_ass8.getChildAt(1)).setChecked(true);

                                        } else if (get_assign_to.equalsIgnoreCase("NA")) {

                                            ((RadioButton) aib_ex1_ass8.getChildAt(2)).setChecked(true);

                                        }

                                        aib_ex1_disp8.setText(getpest);
                                        aib_ex1_rcus8.setText(getcus);
                                        aib_ex1_rpci8.setText(getpci);
                                    }


                                    Update_Status = "1";





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
                        Log.e("FGHFGH","ERRRR = "+error.getMessage());
                    }
                } )
                .requestJson();
    }

    @Override

    public void onBackPressed(){

    }

    private void aib_extr_1_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_extr_1_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }







}
