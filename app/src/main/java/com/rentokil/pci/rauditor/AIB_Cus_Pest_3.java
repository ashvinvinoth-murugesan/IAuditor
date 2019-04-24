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

public class AIB_Cus_Pest_3 extends AppCompatActivity {

    Button aibcuspestsub,aibcuspestbck,aib_cuspest_finish1;
    JSONObject jsonObject_get;


    private FirebaseAnalytics mFirebaseAnalytics;

    JSONArray jsonArray_get;

       RadioGroup rd1,rd2,rd3,rd4,rd5,rd6,rd7,rd8,rd9,rd10,rd11,rd12,rd13,rd14,rd15,rd16,rd17,rd18,rd19,rd20,rd21,rd22,rd23,rd24,rd25,rd26
    ,rd27,rd28,rd29,rd30,rd31,rd32,rd33,rd34,rd35,rd36;

    RadioButton rbd1,rbd2,rbd3,rbd4,rbd5,rbd6,rbd7,rbd8,rbd9,rbd10,rbd11,rbd12,rbd13,rbd14,rbd15,rbd16,rbd17,rbd18,rbd19,rbd20,rbd21,rbd22,rbd23,rbd24,rbd25,rbd26
            ,rbd27,rbd28,rbd29,rbd30,rbd31,rbd32,rbd33,rbd34,rbd35,rbd36;

    String getrd1,getrd2,getrd3,getrd4,getrd5,getrd6,getrd7,getrd8,getrd9,getrd10,getrd11,getrd12,getrd13,getrd14,getrd15,getrd16,getrd17,getrd18,getrd19,getrd20,getrd21,getrd22,getrd23,getrd24,getrd25,getrd26
            ,getrd27,getrd28,getrd29,getrd30,getrd31,getrd32,getrd33,getrd34,getrd35,getrd36,getdisp1;

    TextView selectrespon1;

    public  static TextView disp1;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    private android.app.AlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aib_cuspest);


        aibcuspestsub=(Button) findViewById(R.id.aibcuspestsub);
        aibcuspestbck=(Button) findViewById(R.id.aibcuspestbck);
        aib_cuspest_finish1=(Button) findViewById(R.id.aib_cuspest_finish1);

        selectrespon1=(TextView) findViewById(R.id.aib_cuspest_selectrespon1);
        disp1=(TextView) findViewById(R.id.aib_cuspest_disp1);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        aib_cuspest_finish1.setVisibility(View.GONE);

        aib_cuspest_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                aib_cus_pest_button_next();

                Intent i = new Intent(AIB_Cus_Pest_3.this,AIB_Facility_4.class);
                startActivity(i);

            }
        });


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Cus_Pest_3.this);
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
                        Toast.makeText(AIB_Cus_Pest_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Cus_Pest_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        pd = new SpotsDialog(AIB_Cus_Pest_3.this, R.style.Custom);
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        selectrespon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                aib_cuspest(2121);
            }
        });

        db = new DatabaseHelper(AIB_Cus_Pest_3.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();


        rd1=(RadioGroup) findViewById(R.id.aib_cuspest_rd1);
        rd2=(RadioGroup) findViewById(R.id.aib_cuspest_rd2);
        rd3=(RadioGroup) findViewById(R.id.aib_cuspest_rd3);
        rd4=(RadioGroup) findViewById(R.id.aib_cuspest_rd4);
        rd5=(RadioGroup) findViewById(R.id.aib_cuspest_rd5);
        rd6=(RadioGroup) findViewById(R.id.aib_cuspest_rd6);
        rd7=(RadioGroup) findViewById(R.id.aib_cuspest_rd7);
        rd8=(RadioGroup) findViewById(R.id.aib_cuspest_rd8);
        rd9=(RadioGroup) findViewById(R.id.aib_cuspest_rd9);
        rd10=(RadioGroup) findViewById(R.id.aib_cuspest_rd10);
        rd11=(RadioGroup) findViewById(R.id.aib_cuspest_rd11);
        rd12=(RadioGroup) findViewById(R.id.aib_cuspest_rd12);
        rd13=(RadioGroup) findViewById(R.id.aib_cuspest_rd13);
        rd14=(RadioGroup) findViewById(R.id.aib_cuspest_rd14);
        rd15=(RadioGroup) findViewById(R.id.aib_cuspest_rd15);
        rd16=(RadioGroup) findViewById(R.id.aib_cuspest_rd16);
        rd17=(RadioGroup) findViewById(R.id.aib_cuspest_rd17);
        rd18=(RadioGroup) findViewById(R.id.aib_cuspest_rd18);
        rd19=(RadioGroup) findViewById(R.id.aib_cuspest_rd19);
        rd20=(RadioGroup) findViewById(R.id.aib_cuspest_rd20);
        rd21=(RadioGroup) findViewById(R.id.aib_cuspest_rd21);
        rd22=(RadioGroup) findViewById(R.id.aib_cuspest_rd22);
        rd23=(RadioGroup) findViewById(R.id.aib_cuspest_rd23);
        rd24=(RadioGroup) findViewById(R.id.aib_cuspest_rd24);
        rd25=(RadioGroup) findViewById(R.id.aib_cuspest_rd25);
        rd26=(RadioGroup) findViewById(R.id.aib_cuspest_rd26);
        rd27=(RadioGroup) findViewById(R.id.aib_cuspest_rd27);
        rd28=(RadioGroup) findViewById(R.id.aib_cuspest_rd28);
        rd29=(RadioGroup) findViewById(R.id.aib_cuspest_rd29);
        rd30=(RadioGroup) findViewById(R.id.aib_cuspest_rd30);
        rd31=(RadioGroup) findViewById(R.id.aib_cuspest_rd31);
        rd32=(RadioGroup) findViewById(R.id.aib_cuspest_rd32);
        rd33=(RadioGroup) findViewById(R.id.aib_cuspest_rd33);
        rd34=(RadioGroup) findViewById(R.id.aib_cuspest_rd34);
        rd35=(RadioGroup) findViewById(R.id.aib_cuspest_rd35);
        rd36=(RadioGroup) findViewById(R.id.aib_cuspest_rd36);




        aibcuspestsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(AIB_Cus_Pest_3.this);
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
                                Toast.makeText(AIB_Cus_Pest_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Cus_Pest_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }





                if (validation()) {

                    aib_cus_pest_button_next();
                    post_aib_cuspest();
                } else {
                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }







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


        aibcuspestbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_cus_pest_button_back();

                Intent i = new Intent(AIB_Cus_Pest_3.this,AIB_Cus_Info_2.class);
                startActivity(i);


            }
        });

        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd1.getCheckedRadioButtonId();

                rbd1=(RadioButton)findViewById(selectedId);

                getrd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();

            }
        });



        rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd2.getCheckedRadioButtonId();

                rbd2=(RadioButton)findViewById(selectedId);

                getrd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd3.getCheckedRadioButtonId();

                rbd3=(RadioButton)findViewById(selectedId);

                getrd3 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd4.getCheckedRadioButtonId();

                rbd4=(RadioButton)findViewById(selectedId);

                getrd4 = ((RadioButton)findViewById(rd4.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd5.getCheckedRadioButtonId();

                rbd5=(RadioButton)findViewById(selectedId);

                getrd5 = ((RadioButton)findViewById(rd5.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd6.getCheckedRadioButtonId();

                rbd6=(RadioButton)findViewById(selectedId);

                getrd6 = ((RadioButton)findViewById(rd6.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd7.getCheckedRadioButtonId();

                rbd7=(RadioButton)findViewById(selectedId);

                getrd7= ((RadioButton)findViewById(rd7.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd8.getCheckedRadioButtonId();

                rbd8=(RadioButton)findViewById(selectedId);

                getrd8 = ((RadioButton)findViewById(rd8.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd9.getCheckedRadioButtonId();

                rbd9=(RadioButton)findViewById(selectedId);

                getrd9 = ((RadioButton)findViewById(rd9.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd10.getCheckedRadioButtonId();

                rbd10=(RadioButton)findViewById(selectedId);

                getrd10 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd11.getCheckedRadioButtonId();

                rbd11=(RadioButton)findViewById(selectedId);

                getrd11 = ((RadioButton)findViewById(rd11.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd12.getCheckedRadioButtonId();

                rbd12=(RadioButton)findViewById(selectedId);

                getrd12 = ((RadioButton)findViewById(rd12.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd13.getCheckedRadioButtonId();

                rbd13=(RadioButton)findViewById(selectedId);

                getrd13 = ((RadioButton)findViewById(rd13.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd14.getCheckedRadioButtonId();

                rbd14=(RadioButton)findViewById(selectedId);

                getrd14 = ((RadioButton)findViewById(rd14.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd15.getCheckedRadioButtonId();

                rbd15=(RadioButton)findViewById(selectedId);

                getrd15 = ((RadioButton)findViewById(rd15.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd16.getCheckedRadioButtonId();

                rbd16=(RadioButton)findViewById(selectedId);

                getrd16 = ((RadioButton)findViewById(rd16.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd17.getCheckedRadioButtonId();

                rbd17=(RadioButton)findViewById(selectedId);

                getrd17 = ((RadioButton)findViewById(rd17.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd18.getCheckedRadioButtonId();

                rbd18=(RadioButton)findViewById(selectedId);

                getrd18 = ((RadioButton)findViewById(rd18.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd19.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd19.getCheckedRadioButtonId();

                rbd19=(RadioButton)findViewById(selectedId);

                getrd19 = ((RadioButton)findViewById(rd19.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd20.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd20.getCheckedRadioButtonId();

                rbd20=(RadioButton)findViewById(selectedId);

                getrd20 = ((RadioButton)findViewById(rd20.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd21.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd21.getCheckedRadioButtonId();

                rbd21=(RadioButton)findViewById(selectedId);

                getrd21 = ((RadioButton)findViewById(rd21.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd22.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd22.getCheckedRadioButtonId();

                rbd22=(RadioButton)findViewById(selectedId);

                getrd22 = ((RadioButton)findViewById(rd22.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd23.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd23.getCheckedRadioButtonId();

                rbd23=(RadioButton)findViewById(selectedId);

                getrd23 = ((RadioButton)findViewById(rd23.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd24.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd24.getCheckedRadioButtonId();

                rbd24=(RadioButton)findViewById(selectedId);

                getrd24 = ((RadioButton)findViewById(rd24.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd25.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd25.getCheckedRadioButtonId();

                rbd25=(RadioButton)findViewById(selectedId);

                getrd25 = ((RadioButton)findViewById(rd25.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd26.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd26.getCheckedRadioButtonId();

                rbd26=(RadioButton)findViewById(selectedId);

                getrd26 = ((RadioButton)findViewById(rd26.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd27.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd27.getCheckedRadioButtonId();

                rbd27=(RadioButton)findViewById(selectedId);

                getrd27 = ((RadioButton)findViewById(rd27.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd28.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd28.getCheckedRadioButtonId();

                rbd28=(RadioButton)findViewById(selectedId);

                getrd28 = ((RadioButton)findViewById(rd28.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd29.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd29.getCheckedRadioButtonId();

                rbd29=(RadioButton)findViewById(selectedId);

                getrd29 = ((RadioButton)findViewById(rd29.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd30.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd30.getCheckedRadioButtonId();

                rbd30=(RadioButton)findViewById(selectedId);

                getrd30 = ((RadioButton)findViewById(rd30.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd31.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd31.getCheckedRadioButtonId();

                rbd31=(RadioButton)findViewById(selectedId);

                getrd31 = ((RadioButton)findViewById(rd31.getCheckedRadioButtonId())).getText().toString();

            }
        });


        rd32.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd32.getCheckedRadioButtonId();

                rbd32=(RadioButton)findViewById(selectedId);

                getrd32 = ((RadioButton)findViewById(rd32.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd33.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd33.getCheckedRadioButtonId();

                rbd33=(RadioButton)findViewById(selectedId);

                getrd33 = ((RadioButton)findViewById(rd33.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd34.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd34.getCheckedRadioButtonId();

                rbd34=(RadioButton)findViewById(selectedId);

                getrd34 = ((RadioButton)findViewById(rd34.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd35.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd35.getCheckedRadioButtonId();

                rbd35=(RadioButton)findViewById(selectedId);

                getrd35 = ((RadioButton)findViewById(rd35.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd36.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd36.getCheckedRadioButtonId();

                rbd36=(RadioButton)findViewById(selectedId);

                getrd36 = ((RadioButton)findViewById(rd36.getCheckedRadioButtonId())).getText().toString();

            }
        });
            }

    @Override
    public void onBackPressed(){

    }

    public  boolean validation(){


        try {
            getdisp1=disp1.getText().toString();
            getrd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            getrd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            getrd3 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
            getrd4 = ((RadioButton)findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
            getrd5 = ((RadioButton)findViewById(rd5.getCheckedRadioButtonId())).getText().toString();
            getrd6 = ((RadioButton)findViewById(rd6.getCheckedRadioButtonId())).getText().toString();
            getrd7 = ((RadioButton)findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
            getrd8 = ((RadioButton)findViewById(rd8.getCheckedRadioButtonId())).getText().toString();
            getrd9 = ((RadioButton)findViewById(rd9.getCheckedRadioButtonId())).getText().toString();
            getrd10 = ((RadioButton)findViewById(rd10.getCheckedRadioButtonId())).getText().toString();
            getrd11 = ((RadioButton)findViewById(rd11.getCheckedRadioButtonId())).getText().toString();
            getrd12 = ((RadioButton)findViewById(rd12.getCheckedRadioButtonId())).getText().toString();
            getrd13 = ((RadioButton)findViewById(rd13.getCheckedRadioButtonId())).getText().toString();
            getrd14 = ((RadioButton)findViewById(rd14.getCheckedRadioButtonId())).getText().toString();
            getrd15 = ((RadioButton)findViewById(rd15.getCheckedRadioButtonId())).getText().toString();
            getrd16 = ((RadioButton)findViewById(rd16.getCheckedRadioButtonId())).getText().toString();
            getrd17 = ((RadioButton)findViewById(rd17.getCheckedRadioButtonId())).getText().toString();
            getrd18 = ((RadioButton)findViewById(rd18.getCheckedRadioButtonId())).getText().toString();
            getrd19 = ((RadioButton)findViewById(rd19.getCheckedRadioButtonId())).getText().toString();
            getrd20 = ((RadioButton)findViewById(rd20.getCheckedRadioButtonId())).getText().toString();
            getrd21 = ((RadioButton)findViewById(rd21.getCheckedRadioButtonId())).getText().toString();
            getrd22 = ((RadioButton)findViewById(rd22.getCheckedRadioButtonId())).getText().toString();
            getrd23 = ((RadioButton)findViewById(rd23.getCheckedRadioButtonId())).getText().toString();
            getrd24 = ((RadioButton)findViewById(rd24.getCheckedRadioButtonId())).getText().toString();
            getrd25 = ((RadioButton)findViewById(rd25.getCheckedRadioButtonId())).getText().toString();
            getrd26 = ((RadioButton)findViewById(rd26.getCheckedRadioButtonId())).getText().toString();
            getrd27 = ((RadioButton)findViewById(rd27.getCheckedRadioButtonId())).getText().toString();
            getrd28 = ((RadioButton)findViewById(rd28.getCheckedRadioButtonId())).getText().toString();
            getrd29 = ((RadioButton)findViewById(rd29.getCheckedRadioButtonId())).getText().toString();
            getrd30 = ((RadioButton)findViewById(rd30.getCheckedRadioButtonId())).getText().toString();
            getrd31 = ((RadioButton)findViewById(rd31.getCheckedRadioButtonId())).getText().toString();
            getrd32 = ((RadioButton)findViewById(rd32.getCheckedRadioButtonId())).getText().toString();
            getrd33 = ((RadioButton)findViewById(rd33.getCheckedRadioButtonId())).getText().toString();
            getrd34 = ((RadioButton)findViewById(rd34.getCheckedRadioButtonId())).getText().toString();
            getrd35 = ((RadioButton)findViewById(rd35.getCheckedRadioButtonId())).getText().toString();
            getrd36 = ((RadioButton)findViewById(rd36.getCheckedRadioButtonId())).getText().toString();


            if ((getdisp1.length()==0||getrd1.length()==0||getrd2.length()==0||getrd3.length()==0||getrd4.length()==0
                    ||getrd5.length()==0||getrd6.length()==0||getrd7.length()==0||getrd8.length()==0||getrd9.length()==0||getrd10.length()==0||getrd11.length()==0||getrd12.length()==0||getrd13.length()==0||getrd14.length()==0||getrd15.length()==0||getrd16.length()==0||getrd17.length()==0
                    ||getrd18.length()==0||getrd19.length()==0||getrd20.length()==0||getrd21.length()==0||getrd22.length()==0||getrd23.length()==0||getrd24.length()==0||getrd25.length()==0||getrd26.length()==0||getrd27.length()==0||getrd28.length()==0||getrd29.length()==0||getrd30.length()==0
                    ||getrd31.length()==0||getrd32.length()==0||getrd33.length()==0||getrd34.length()==0||getrd35.length()==0||getrd36.length()==0)){
                return  false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }


    }

    public void aib_cuspest(int status) {

        Bundle bundle = new Bundle();
        if(status==2121) {
            bundle.putString("status", "2121");
        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    private void post_aib_cuspest() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("radio1",getrd1);
        params.put("radio2",getrd2);
        params.put("radio3",getrd3);
        params.put("radio4",getrd4);
        params.put("radio5",getrd5);
        params.put("radio6",getrd6);
        params.put("radio7",getrd7);
        params.put("radio8",getrd8);
        params.put("radio9",getrd9);
        params.put("radio10",getrd10);
        params.put("radio11",getrd11);
        params.put("radio12",getrd12);
        params.put("radio13",getrd13);
        params.put("radio14",getrd14);
        params.put("radio15",getrd15);
        params.put("radio16",getrd16);
        params.put("radio17",getrd17);
        params.put("radio18",getrd18);
        params.put("radio19",getrd19);
        params.put("radio20",getrd20);
        params.put("radio21",getrd21);
        params.put("radio22",getrd22);
        params.put("radio23",getrd23);
        params.put("radio24",getrd24);
        params.put("radio25",getrd25);
        params.put("radio26",getrd26);
        params.put("radio27",getrd27);
        params.put("radio28",getrd28);
        params.put("radio29",getrd29);
        params.put("radio30",getrd30);
        params.put("radio31",getrd31);
        params.put("radio32",getrd32);
        params.put("radio33",getrd33);
        params.put("radio34",getrd34);
        params.put("radio35",getrd35);
        params.put("radio36",getrd36);
        params.put("displ1",getdisp1);


        params.put("main_id",AIB_Title_1.Main_ID);


        Log.e("MMMMM 444",""+params);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_cus_pest.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
pd.dismiss();
                            Log.e("CVCV","sub response =  "+response);

                            Intent i = new Intent(AIB_Cus_Pest_3.this,AIB_Facility_4.class);
                            startActivity(i);
                            //   Toast.makeText( AIB_Cus_Pest_3.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("EEEXZ","ERRRRR  sub =  "+error.getMessage());

                        pd.dismiss();
                        if(error.getMessage().equalsIgnoreCase("null")){

                            Intent i = new Intent(AIB_Cus_Pest_3.this,AIB_Facility_4.class);
                            startActivity(i);
                        }
                        else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                        {
                            Intent i = new Intent(AIB_Cus_Pest_3.this,AIB_Facility_4.class);
                            startActivity(i);

                        }

                        //   Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

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


        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_aib_3.php?main_id="+AIB_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            pd.dismiss();
                            Log.e("EEEXZ","res = "+response);


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                Log.e("EEED","before entry00000");
                                JSONObject c = jsonArray_get.getJSONObject(i);


                                String id = c.getString( "main_id" );
                                String get_occ = c.getString( "occ" );
                                String get_po_cus = c.getString( "po_cus" );
                                String get_em_cont = c.getString( "em_cont" );
                                String get_Tech_po = c.getString( "Tech_po" );
                                String get_Tech_train = c.getString( "Tech_train" );
                                String get_branch = c.getString( "branch" );
                                String get_holi_list = c.getString( "holi_list" );
                                String get_annu_ser = c.getString( "annu_ser" );
                                String get_list_apl = c.getString( "list_apl" );
                                String get_list_anti = c.getString( "list_anti" );
                                String get_label_copy = c.getString( "label_copy" );
                                String get_msds = c.getString( "msds" );
                                String get_pes_stock = c.getString( "pes_stock" );
                                String get_pes_sight = c.getString( "pes_sight" );
                                String get_rec_pest = c.getString( "rec_pest" );
                                String get_copy_pub = c.getString( "copy_pub" );
                                String get_pms_log = c.getString( "pms_log" );
                                String get_temp_rod = c.getString( "temp_rod" );
                                String get_ser_prep = c.getString( "ser_prep" );
                                String get_sop_stor = c.getString( "sop_stor" );
                                String get_sop_dis = c.getString( "sop_dis" );
                                String get_sop_spill = c.getString( "sop_spill" );
                                String get_chem_in = c.getString( "chem_in" );
                                String get_empty_con = c.getString( "empty_con" );
                                String get_floor_sens = c.getString( "floor_sens" );
                                String get_cus_dema = c.getString( "cus_dema" );
                                String get_cus_moni = c.getString( "cus_moni" );
                                String get_floor_rodent = c.getString( "floor_rodent" );
                                String get_exte_struc = c.getString( "exte_struc" );
                                String get_inte_moni = c.getString( "inte_moni" );
                                String get_floor_insc = c.getString( "floor_insc" );
                                String get_insec_chang = c.getString( "insec_chang" );
                                String get_insec_cont = c.getString( "insec_cont" );
                                String get_floor_cock = c.getString( "floor_cock" );
                                String get_floor_sip = c.getString( "floor_sip" );
                                String get_annual_pest = c.getString( "annual_pest" );
                                String get_trend_anal = c.getString( "trend_anal" );



                                Log.e("EEED","before entry00");
                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDF123SS","update value = "+c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS)));

                                String getsircuscomp = c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS));

                                Log.e("EEED","before entry11");

                                if(getsircuscomp.equalsIgnoreCase("2")) {



                                    Log.e("EEEED", "aib cus3 en1");

                                    aibcuspestbck.setVisibility(View.GONE);
                                    aibcuspestsub.setVisibility(View.GONE);
                                    aib_cuspest_finish1.setVisibility(View.VISIBLE);

                                    disp1.setText(get_pms_log);

                                    selectrespon1.setEnabled(false);
                                    rd1.getChildAt(0).setEnabled(false);
                                    rd1.getChildAt(1).setEnabled(false);
                                    rd1.getChildAt(2).setEnabled(false);

                                    rd2.getChildAt(0).setEnabled(false);
                                    rd2.getChildAt(1).setEnabled(false);
                                    rd2.getChildAt(2).setEnabled(false);

                                    rd3.getChildAt(0).setEnabled(false);
                                    rd3.getChildAt(1).setEnabled(false);
                                    rd3.getChildAt(2).setEnabled(false);

                                    rd4.getChildAt(0).setEnabled(false);
                                    rd4.getChildAt(1).setEnabled(false);
                                    rd4.getChildAt(2).setEnabled(false);

                                    rd5.getChildAt(0).setEnabled(false);
                                    rd5.getChildAt(1).setEnabled(false);
                                    rd5.getChildAt(2).setEnabled(false);

                                    rd6.getChildAt(0).setEnabled(false);
                                    rd6.getChildAt(1).setEnabled(false);
                                    rd6.getChildAt(2).setEnabled(false);

                                    rd7.getChildAt(0).setEnabled(false);
                                    rd7.getChildAt(1).setEnabled(false);
                                    rd7.getChildAt(2).setEnabled(false);

                                    rd8.getChildAt(0).setEnabled(false);
                                    rd8.getChildAt(1).setEnabled(false);
                                    rd8.getChildAt(2).setEnabled(false);


                                    rd9.getChildAt(0).setEnabled(false);
                                    rd9.getChildAt(1).setEnabled(false);
                                    rd9.getChildAt(2).setEnabled(false);

                                    rd10.getChildAt(0).setEnabled(false);
                                    rd10.getChildAt(1).setEnabled(false);
                                    rd10.getChildAt(2).setEnabled(false);

                                    rd11.getChildAt(0).setEnabled(false);
                                    rd11.getChildAt(1).setEnabled(false);
                                    rd11.getChildAt(2).setEnabled(false);

                                    rd12.getChildAt(0).setEnabled(false);
                                    rd12.getChildAt(1).setEnabled(false);
                                    rd12.getChildAt(2).setEnabled(false);

                                    rd13.getChildAt(0).setEnabled(false);
                                    rd13.getChildAt(1).setEnabled(false);
                                    rd13.getChildAt(2).setEnabled(false);

                                    rd14.getChildAt(0).setEnabled(false);
                                    rd14.getChildAt(1).setEnabled(false);
                                    rd14.getChildAt(2).setEnabled(false);

                                    rd15.getChildAt(0).setEnabled(false);
                                    rd15.getChildAt(1).setEnabled(false);
                                    rd15.getChildAt(2).setEnabled(false);

                                    rd16.getChildAt(0).setEnabled(false);
                                    rd16.getChildAt(1).setEnabled(false);
                                    rd16.getChildAt(2).setEnabled(false);

                                    rd17.getChildAt(0).setEnabled(false);
                                    rd17.getChildAt(1).setEnabled(false);
                                    rd17.getChildAt(2).setEnabled(false);

                                    rd18.getChildAt(0).setEnabled(false);
                                    rd18.getChildAt(1).setEnabled(false);
                                    rd18.getChildAt(2).setEnabled(false);

                                    rd19.getChildAt(0).setEnabled(false);
                                    rd19.getChildAt(1).setEnabled(false);
                                    rd19.getChildAt(2).setEnabled(false);

                                    rd20.getChildAt(0).setEnabled(false);
                                    rd20.getChildAt(1).setEnabled(false);
                                    rd20.getChildAt(2).setEnabled(false);

                                    rd21.getChildAt(0).setEnabled(false);
                                    rd21.getChildAt(1).setEnabled(false);
                                    rd21.getChildAt(2).setEnabled(false);

                                    rd22.getChildAt(0).setEnabled(false);
                                    rd22.getChildAt(1).setEnabled(false);
                                    rd22.getChildAt(2).setEnabled(false);

                                    rd23.getChildAt(0).setEnabled(false);
                                    rd23.getChildAt(1).setEnabled(false);
                                    rd23.getChildAt(2).setEnabled(false);

                                    rd24.getChildAt(0).setEnabled(false);
                                    rd24.getChildAt(1).setEnabled(false);
                                    rd24.getChildAt(2).setEnabled(false);

                                    rd25.getChildAt(0).setEnabled(false);
                                    rd25.getChildAt(1).setEnabled(false);
                                    rd25.getChildAt(2).setEnabled(false);

                                    rd26.getChildAt(0).setEnabled(false);
                                    rd26.getChildAt(1).setEnabled(false);
                                    rd26.getChildAt(2).setEnabled(false);


                                    rd27.getChildAt(0).setEnabled(false);
                                    rd27.getChildAt(1).setEnabled(false);
                                    rd27.getChildAt(2).setEnabled(false);

                                    rd28.getChildAt(0).setEnabled(false);
                                    rd28.getChildAt(1).setEnabled(false);
                                    rd28.getChildAt(2).setEnabled(false);

                                    rd29.getChildAt(0).setEnabled(false);
                                    rd29.getChildAt(1).setEnabled(false);


                                    rd30.getChildAt(0).setEnabled(false);
                                    rd30.getChildAt(1).setEnabled(false);
                                    rd30.getChildAt(2).setEnabled(false);

                                    rd31.getChildAt(0).setEnabled(false);
                                    rd31.getChildAt(1).setEnabled(false);
                                    rd31.getChildAt(2).setEnabled(false);

                                    rd32.getChildAt(0).setEnabled(false);
                                    rd32.getChildAt(1).setEnabled(false);
                                    rd32.getChildAt(2).setEnabled(false);

                                    rd33.getChildAt(0).setEnabled(false);
                                    rd33.getChildAt(1).setEnabled(false);
                                    rd33.getChildAt(2).setEnabled(false);

                                    rd34.getChildAt(0).setEnabled(false);
                                    rd34.getChildAt(1).setEnabled(false);
                                    rd34.getChildAt(2).setEnabled(false);

                                    rd35.getChildAt(0).setEnabled(false);
                                    rd35.getChildAt(1).setEnabled(false);
                                    rd35.getChildAt(2).setEnabled(false);

                                    rd36.getChildAt(0).setEnabled(false);
                                    rd36.getChildAt(1).setEnabled(false);
                                    rd36.getChildAt(2).setEnabled(false);














                                    if (get_occ.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd1.getChildAt(0)).setChecked(true);


                                    } else if (get_occ.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd1.getChildAt(1)).setChecked(true);

                                    } else if (get_occ.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd1.getChildAt(2)).setChecked(true);

                                    }

///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_po_cus.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd2.getChildAt(0)).setChecked(true);


                                    } else if (get_po_cus.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                    } else if (get_po_cus.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd2.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_em_cont.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd3.getChildAt(0)).setChecked(true);


                                    } else if (get_em_cont.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                    } else if (get_em_cont.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd3.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_Tech_po.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd4.getChildAt(0)).setChecked(true);


                                    } else if (get_Tech_po.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                    } else if (get_Tech_po.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd4.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_Tech_train.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd5.getChildAt(0)).setChecked(true);


                                    } else if (get_Tech_train.equalsIgnoreCase("No")) {

                                        ((RadioButton) rd5.getChildAt(1)).setChecked(true);

                                    } else if (get_Tech_train.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd5.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_branch.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd6.getChildAt(0)).setChecked(true);


                                    } else if (get_branch.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd6.getChildAt(1)).setChecked(true);

                                    } else if (get_branch.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd6.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_holi_list.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd7.getChildAt(0)).setChecked(true);


                                    } else if (get_holi_list.equalsIgnoreCase("No")) {

                                        ((RadioButton) rd7.getChildAt(1)).setChecked(true);

                                    } else if (get_holi_list.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd7.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_annu_ser.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd8.getChildAt(0)).setChecked(true);


                                    } else if (get_annu_ser.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd8.getChildAt(1)).setChecked(true);

                                    } else if (get_annu_ser.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd8.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_list_apl.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                    } else if (get_list_apl.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                    } else if (get_list_apl.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_list_anti.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd10.getChildAt(0)).setChecked(true);


                                    } else if (get_list_anti.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd10.getChildAt(1)).setChecked(true);

                                    } else if (get_list_anti.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd10.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_label_copy.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd11.getChildAt(0)).setChecked(true);


                                    } else if (get_label_copy.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd11.getChildAt(1)).setChecked(true);

                                    } else if (get_label_copy.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd11.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_msds.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd12.getChildAt(0)).setChecked(true);


                                    } else if (get_msds.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd12.getChildAt(1)).setChecked(true);

                                    } else if (get_msds.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd12.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_pes_stock.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd13.getChildAt(0)).setChecked(true);


                                    } else if (get_pes_stock.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd13.getChildAt(1)).setChecked(true);

                                    } else if (get_pes_stock.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd13.getChildAt(2)).setChecked(true);

                                    }
                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_pes_sight.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd14.getChildAt(0)).setChecked(true);


                                    } else if (get_pes_sight.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd14.getChildAt(1)).setChecked(true);

                                    } else if (get_pes_sight.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd14.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_rec_pest.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd15.getChildAt(0)).setChecked(true);


                                    } else if (get_rec_pest.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd15.getChildAt(1)).setChecked(true);

                                    } else if (get_rec_pest.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd15.getChildAt(2)).setChecked(true);

                                    }
                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_copy_pub.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd16.getChildAt(0)).setChecked(true);


                                    } else if (get_copy_pub.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd16.getChildAt(1)).setChecked(true);

                                    } else if (get_copy_pub.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd16.getChildAt(2)).setChecked(true);

                                    }
                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_temp_rod.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd17.getChildAt(0)).setChecked(true);


                                    } else if (get_temp_rod.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd17.getChildAt(1)).setChecked(true);

                                    } else if (get_temp_rod.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd17.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_ser_prep.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd18.getChildAt(0)).setChecked(true);


                                    } else if (get_ser_prep.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd18.getChildAt(1)).setChecked(true);

                                    } else if (get_ser_prep.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd18.getChildAt(2)).setChecked(true);

                                    }
                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_sop_stor.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd19.getChildAt(0)).setChecked(true);


                                    } else if (get_sop_stor.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd19.getChildAt(1)).setChecked(true);

                                    } else if (get_sop_stor.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd19.getChildAt(2)).setChecked(true);

                                    }
                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_sop_dis.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd20.getChildAt(0)).setChecked(true);


                                    } else if (get_sop_dis.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd20.getChildAt(1)).setChecked(true);

                                    } else if (get_sop_dis.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd20.getChildAt(2)).setChecked(true);

                                    }
                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_sop_spill.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd21.getChildAt(0)).setChecked(true);


                                    } else if (get_sop_spill.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd21.getChildAt(1)).setChecked(true);

                                    } else if (get_sop_spill.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd21.getChildAt(2)).setChecked(true);

                                    }
                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_chem_in.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd22.getChildAt(0)).setChecked(true);


                                    } else if (get_chem_in.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd22.getChildAt(1)).setChecked(true);

                                    } else if (get_chem_in.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd22.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_empty_con.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd23.getChildAt(0)).setChecked(true);


                                    } else if (get_empty_con.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd23.getChildAt(1)).setChecked(true);

                                    } else if (get_empty_con.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd23.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_floor_sens.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd24.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_sens.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd24.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_sens.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd24.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_cus_dema.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd25.getChildAt(0)).setChecked(true);


                                    } else if (get_cus_dema.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd25.getChildAt(1)).setChecked(true);

                                    } else if (get_cus_dema.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd25.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_cus_moni.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd26.getChildAt(0)).setChecked(true);


                                    } else if (get_cus_moni.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd26.getChildAt(1)).setChecked(true);

                                    } else if (get_cus_moni.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd26.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_floor_rodent.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd27.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd27.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_rodent.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd27.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_exte_struc.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd28.getChildAt(0)).setChecked(true);


                                    } else if (get_exte_struc.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd28.getChildAt(1)).setChecked(true);

                                    } else if (get_exte_struc.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd28.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_inte_moni.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd29.getChildAt(0)).setChecked(true);


                                    } else if (get_inte_moni.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd29.getChildAt(1)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_floor_insc.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd30.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_insc.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd30.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_insc.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd30.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_insec_chang.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd31.getChildAt(0)).setChecked(true);


                                    } else if (get_insec_chang.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd31.getChildAt(1)).setChecked(true);

                                    } else if (get_insec_chang.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd31.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_insec_cont.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd32.getChildAt(0)).setChecked(true);


                                    } else if (get_insec_cont.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd32.getChildAt(1)).setChecked(true);

                                    } else if (get_insec_cont.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd32.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_floor_cock.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd33.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_cock.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd33.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_cock.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd33.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_floor_sip.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd34.getChildAt(0)).setChecked(true);


                                    } else if (get_floor_sip.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd34.getChildAt(1)).setChecked(true);

                                    } else if (get_floor_sip.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd34.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_annual_pest.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd35.getChildAt(0)).setChecked(true);


                                    } else if (get_annual_pest.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd35.getChildAt(1)).setChecked(true);

                                    } else if (get_annual_pest.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd35.getChildAt(2)).setChecked(true);

                                    }

                                    ///////////////////////////Next//////////////////////////////////////////////////////////
                                    if (get_trend_anal.equalsIgnoreCase("YES,UPDATED")) {
                                        ((RadioButton) rd36.getChildAt(0)).setChecked(true);


                                    } else if (get_trend_anal.equalsIgnoreCase("YES,BUT NOT UPDATED")) {

                                        ((RadioButton) rd36.getChildAt(1)).setChecked(true);

                                    } else if (get_trend_anal.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd36.getChildAt(2)).setChecked(true);


                                    }

                                    pd.dismiss();
                                }

                                    else if(getsircuscomp.equalsIgnoreCase("1")){

                    Log.e("EEEED","aib cus3 en2");


                                        disp1.setText(get_pms_log);

                                        if(get_occ.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd1.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_occ.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd1.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_occ.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd1.getChildAt(2)).setChecked(true);

                                        }

///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_po_cus.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd2.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_po_cus.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_po_cus.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd2.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_em_cont.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd3.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_em_cont.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_em_cont.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd3.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_Tech_po.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd4.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_Tech_po.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_Tech_po.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd4.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_Tech_train.equalsIgnoreCase("YES")){
                                            ((RadioButton) rd5.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_Tech_train.equalsIgnoreCase("No")){

                                            ((RadioButton) rd5.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_Tech_train.equalsIgnoreCase("N.A")){

                                            ((RadioButton) rd5.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_branch.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd6.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_branch.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd6.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_branch.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd6.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_holi_list.equalsIgnoreCase("YES")){
                                            ((RadioButton) rd7.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_holi_list.equalsIgnoreCase("No")){

                                            ((RadioButton) rd7.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_holi_list.equalsIgnoreCase("N.A")){

                                            ((RadioButton) rd7.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_annu_ser.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd8.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_annu_ser.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd8.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_annu_ser.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd8.getChildAt(2)).setChecked(true);

                                        }

                                        if(get_list_apl.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_list_apl.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_list_apl.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                        }

                                        if(get_list_anti.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd10.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_list_anti.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd10.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_list_anti.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd10.getChildAt(2)).setChecked(true);

                                        }

                                        if(get_label_copy.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd11.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_label_copy.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd11.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_label_copy.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd11.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_msds.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd12.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_msds.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd12.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_msds.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd12.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_pes_stock.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd13.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_pes_stock.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd13.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_pes_stock.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd13.getChildAt(2)).setChecked(true);

                                        }
                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_pes_sight.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd14.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_pes_sight.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd14.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_pes_sight.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd14.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_rec_pest.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd15.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_rec_pest.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd15.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_rec_pest.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd15.getChildAt(2)).setChecked(true);

                                        }
                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_copy_pub.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd16.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_copy_pub.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd16.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_copy_pub.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd16.getChildAt(2)).setChecked(true);

                                        }
                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_temp_rod.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd17.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_temp_rod.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd17.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_temp_rod.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd17.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_ser_prep.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd18.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_ser_prep.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd18.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_ser_prep.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd18.getChildAt(2)).setChecked(true);

                                        }
                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_sop_stor.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd19.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_sop_stor.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd19.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_sop_stor.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd19.getChildAt(2)).setChecked(true);

                                        }
                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_sop_dis.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd20.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_sop_dis.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd20.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_sop_dis.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd20.getChildAt(2)).setChecked(true);

                                        }
                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_sop_spill.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd21.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_sop_spill.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd21.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_sop_spill.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd21.getChildAt(2)).setChecked(true);

                                        }
                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_chem_in.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd22.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_chem_in.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd22.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_chem_in.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd22.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_empty_con.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd23.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_empty_con.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd23.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_empty_con.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd23.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_floor_sens.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd24.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_sens.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd24.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_sens.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd24.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_cus_dema.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd25.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_cus_dema.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd25.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_cus_dema.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd25.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_cus_moni.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd26.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_cus_moni.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd26.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_cus_moni.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd26.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_floor_rodent.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd27.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd27.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_rodent.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd27.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_exte_struc.equalsIgnoreCase("YES")){
                                            ((RadioButton) rd28.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_exte_struc.equalsIgnoreCase("NO")){

                                            ((RadioButton) rd28.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_exte_struc.equalsIgnoreCase("N.A")){

                                            ((RadioButton) rd28.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_inte_moni.equalsIgnoreCase("YES")){
                                            ((RadioButton) rd29.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_inte_moni.equalsIgnoreCase("NO")){

                                            ((RadioButton) rd29.getChildAt(1)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_floor_insc.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd30.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_insc.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd30.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_insc.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd30.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_insec_chang.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd31.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_insec_chang.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd31.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_insec_chang.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd31.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_insec_cont.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd32.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_insec_cont.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd32.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_insec_cont.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd32.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_floor_cock.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd33.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_cock.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd33.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_cock.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd33.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_floor_sip.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd34.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_floor_sip.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd34.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_floor_sip.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd34.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_annual_pest.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd35.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_annual_pest.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd35.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_annual_pest.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd35.getChildAt(2)).setChecked(true);

                                        }

                                        ///////////////////////////Next//////////////////////////////////////////////////////////
                                        if(get_trend_anal.equalsIgnoreCase("YES,UPDATED")){
                                            ((RadioButton) rd36.getChildAt(0)).setChecked(true);


                                        }
                                        else if(get_trend_anal.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                            ((RadioButton) rd36.getChildAt(1)).setChecked(true);

                                        }

                                        else if(get_trend_anal.equalsIgnoreCase("NOT AVAILABLE")){

                                            ((RadioButton) rd36.getChildAt(2)).setChecked(true);

                                        }



                                    }


                            }

                            //  Toast.makeText( AIB_Title_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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
                        Log.e("EEEXZ","ERRRRR =  "+error.getMessage());
                    }
                } )
                .requestJson();
    }

    private void aib_cus_pest_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_cus_pest_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }




}
