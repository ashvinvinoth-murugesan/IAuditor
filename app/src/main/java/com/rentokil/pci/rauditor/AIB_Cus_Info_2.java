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

public class AIB_Cus_Info_2 extends AppCompatActivity {

    Button aibcusinforsub,aibcusinforbck,aib_cusinfo_finish1;
    EditText qsr_cusinfo_et_area,qsr_cusinfo_0ther;

    RadioGroup rd1,rd2,rd3,rd4;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    private FirebaseAnalytics mFirebaseAnalytics;




    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";
    private RadioButton radiobtn1,radiobtn2,radiobtn3,radiobtn4;
    TextView qsr_cusinfo_selectrs1,qsr_cusinfo_selectrs2;
    public  static TextView disp1,disp2 ;

    String et1,et2,getrad1,getrad2,getrad3,getrad4,dis1,dis2;
    ConnectivityManager cManager;
    NetworkInfo nInfo;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    private android.app.AlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aib_cusinfo);

        aibcusinforsub=(Button) findViewById(R.id.aibcusinfosub);
        aibcusinforbck=(Button) findViewById(R.id.aibcusinfobck);
        aib_cusinfo_finish1=(Button) findViewById(R.id.aib_cusinfo_finish1);

        rd1=(RadioGroup) findViewById(R.id.qsr_cusinfo_radiob1);
        rd2=(RadioGroup) findViewById(R.id.qsr_cusinfo_radiob2);
        rd3=(RadioGroup) findViewById(R.id.qsr_cusinfo_radiob3);
        rd4=(RadioGroup) findViewById(R.id.qsr_cusinfo_radiob4);
        qsr_cusinfo_et_area=(EditText) findViewById(R.id.qsr_cusinfo_et_area);

        pd = new SpotsDialog(AIB_Cus_Info_2.this, R.style.Custom);

        aib_cusinfo_finish1.setVisibility(View.GONE);

        aib_cusinfo_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                aib_cus_info_button_next();

                Intent i = new Intent(AIB_Cus_Info_2.this,AIB_Cus_Pest_3.class);
                startActivity(i);

            }
        });

        qsr_cusinfo_selectrs1=(TextView) findViewById(R.id.qsr_cusinfo_selectrs1);
        disp1=(TextView) findViewById(R.id.qsr_cusinfo_disp1);
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        qsr_cusinfo_selectrs2=(TextView) findViewById(R.id.qsr_cusinfo_selectrs2);
        disp2=(TextView) findViewById(R.id.qsr_cusinfo_disp2);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Cus_Info_2.this);
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
                        Toast.makeText(AIB_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }


        qsr_cusinfo_selectrs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aibcus(1919);
            }
        });

        qsr_cusinfo_selectrs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aibcus(2020);
            }
        });

        qsr_cusinfo_0ther=(EditText) findViewById(R.id.qsr_cusinfo_0ther);


        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd1.getCheckedRadioButtonId();

                radiobtn1=(RadioButton)findViewById(selectedId);


//                getrad1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();



            }
        });

        rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd2.getCheckedRadioButtonId();

                radiobtn2=(RadioButton)findViewById(selectedId);


//                getrad2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();



            }
        });
        rd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd3.getCheckedRadioButtonId();

                radiobtn3=(RadioButton)findViewById(selectedId);


//                getrad3 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();



            }
        });
        rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd4.getCheckedRadioButtonId();

                radiobtn4=(RadioButton)findViewById(selectedId);


//                getrad4 = ((RadioButton)findViewById(rd4.getCheckedRadioButtonId())).getText().toString();



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

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        db = new DatabaseHelper(AIB_Cus_Info_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();


        aibcusinforsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_cus_info_button_next();
                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(AIB_Cus_Info_2.this);
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
                                Toast.makeText(AIB_Cus_Info_2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Cus_Info_2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }



                if (validation()) {

                    aib_cus_info_button_next();

                    post_aibcus_js();
                } else {
                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }








            }
        });

        aibcusinforbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_cus_info_button_back();

                Intent i = new Intent(AIB_Cus_Info_2.this,AIB_Title_1.class);
                startActivity(i);


            }
        });
    }


    public  boolean validation(){


        try {
            et1 =qsr_cusinfo_et_area.getText().toString();
            et2 =qsr_cusinfo_0ther.getText().toString();
            getrad1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            getrad2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            getrad3 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
            getrad4 = ((RadioButton)findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
            dis1=disp1.getText().toString();
            dis2=disp2.getText().toString();

            if(TextUtils.isEmpty(et1 ) || TextUtils.isEmpty(et2) ||TextUtils.isEmpty(dis1)
                    ||TextUtils.isEmpty(dis2) ) {

                if(TextUtils.isEmpty(et1 )){
                    qsr_cusinfo_et_area.setError("Required");
                }

                if(TextUtils.isEmpty(et2)) {
                    qsr_cusinfo_0ther.setError("Required");


                }
                if(TextUtils.isEmpty(dis1)) {
                    disp1.setError("Required");

                }
                if(TextUtils.isEmpty(dis2)) {
                    disp2.setError("Required");
                }

                return false;
            }


            if ((et1.length()==0||et2.length()==0||getrad1.length()==0||getrad2.length()==0||getrad3.length()==0
                    ||getrad4.length()==0||dis1.length()==0||dis2.length()==0)){
                return  false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }


    }

    public void aibcus(int status) {

        Bundle bundle = new Bundle();
        if(status==1919) {
            bundle.putString("status", "1919");
        }

        if(status==2020) {
            bundle.putString("status", "2020");
        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    private void post_aibcus_js() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("gtr1",getrad1);
        params.put("edit1",et1);
        params.put("show1",dis1);
        params.put("show2",dis2);
        params.put("edit2",et2);
        params.put("gtr2",getrad2);
        params.put("gtr3",getrad3);
        params.put("gtr4",getrad4);
        params.put("update_status",Update_Status);

        params.put("main_id",AIB_Title_1.Main_ID);




        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_cus_info.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();
                            Log.e("HJHJ","sub res = "+response);
                            Intent i = new Intent(AIB_Cus_Info_2.this,AIB_Cus_Pest_3.class);
                            startActivity(i);
                            //       Toast.makeText( AIB_Cus_Info_2.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e("EDED","ERRRR sub = "+error.getMessage());

                        if(error.getMessage().equalsIgnoreCase("null")){

                            Intent i = new Intent(AIB_Cus_Info_2.this,AIB_Cus_Pest_3.class);
                            startActivity(i);
                        }
                        else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                        {
                            Intent i = new Intent(AIB_Cus_Info_2.this,AIB_Cus_Pest_3.class);
                            startActivity(i);

                        }
                        // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

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
            AIB_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_cus_info_2.php?main_id="+AIB_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();


                            Log.e("EDED","res ="+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "main_id" );
                                String getlocation = c.getString( "location" );
                                String getpestcover = c.getString( "pest_cover" );
                                String getother = c.getString( "other_service" );
                                String getservice = c.getString( "service_contract" );

                                String get_contract = c.getString( "contract" );
                                String get_q1 = c.getString( "q1" );
                                String get_q2 = c.getString( "q2" );
                                String get_q3 = c.getString( "q3" );

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDF123","update value = "+c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS)));

                                String getsircuscomp = c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS));


                                if(getsircuscomp.equalsIgnoreCase("2")) {




                                    aibcusinforbck.setVisibility(View.GONE);
                                    aibcusinforsub.setVisibility(View.GONE);
                                    aib_cusinfo_finish1.setVisibility(View.VISIBLE);

                                    qsr_cusinfo_et_area.setEnabled(false);
                                    qsr_cusinfo_0ther.setEnabled(false);
                                    qsr_cusinfo_selectrs1.setEnabled(false);
                                    qsr_cusinfo_selectrs2.setEnabled(false);

                                    rd1.getChildAt(0).setEnabled(false);
                                    rd1.getChildAt(1).setEnabled(false);

                                    rd2.getChildAt(0).setEnabled(false);
                                    rd2.getChildAt(1).setEnabled(false);
                                    rd2.getChildAt(2).setEnabled(false);

                                    rd3.getChildAt(0).setEnabled(false);
                                    rd3.getChildAt(1).setEnabled(false);
                                    rd3.getChildAt(2).setEnabled(false);

                                    rd4.getChildAt(0).setEnabled(false);
                                    rd4.getChildAt(1).setEnabled(false);
                                    rd4.getChildAt(2).setEnabled(false);

                                    Log.e("EDED","en1 if");


                                    if (get_contract.equalsIgnoreCase("RENTOKIL PCI")) {
                                        ((RadioButton) rd1.getChildAt(0)).setChecked(true);


                                    } else if (get_contract.equalsIgnoreCase("CUSTOMER")) {

                                        ((RadioButton) rd1.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_q1.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd2.getChildAt(0)).setChecked(true);


                                    } else if (get_q1.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                    } else if (get_q1.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_q2.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd3.getChildAt(0)).setChecked(true);


                                    } else if (get_q2.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                    } else if (get_q2.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_q3.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd4.getChildAt(0)).setChecked(true);


                                    } else if (get_q3.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                    } else if (get_q3.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                    }







                                    qsr_cusinfo_et_area.setText(getlocation);
                                    disp2.setText(getpestcover);
                                    disp1.setText(getservice);
                                    qsr_cusinfo_0ther.setText(getother);








                                    Update_Status="2";
                                    pd.dismiss();


                                }
                                else if(getsircuscomp.equalsIgnoreCase("1")) {

                                    Log.e("EDED","en2 else");


                                    if (get_contract.equalsIgnoreCase("RENTOKIL PCI")) {
                                        ((RadioButton) rd1.getChildAt(0)).setChecked(true);


                                    } else if (get_contract.equalsIgnoreCase("CUSTOMER")) {

                                        ((RadioButton) rd1.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_q1.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd2.getChildAt(0)).setChecked(true);


                                    } else if (get_q1.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                    } else if (get_q1.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_q2.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd3.getChildAt(0)).setChecked(true);


                                    } else if (get_q2.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                    } else if (get_q2.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                    }

                                    if (get_q3.equalsIgnoreCase("YES")) {
                                        ((RadioButton) rd4.getChildAt(0)).setChecked(true);


                                    } else if (get_q3.equalsIgnoreCase("NO")) {

                                        ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                    } else if (get_q3.equalsIgnoreCase("N.A")) {

                                        ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                    }
                                    qsr_cusinfo_et_area.setText(getlocation);
                                    disp2.setText(getpestcover);
                                    disp1.setText(getservice);
                                    qsr_cusinfo_0ther.setText(getother);
                                    Update_Status="1";

                                }


                            }

                            //      Toast.makeText( AIB_Cus_Info_2.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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
                        Log.e("EDED","ERRRR = "+error.getMessage());
                    }
                } )
                .requestJson();
    }


    private void aib_cus_info_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_cus_info_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }





}
