package com.rentokil.pci.rauditor;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class AIB_Interl_Obser_6 extends AppCompatActivity {

    Button aibintrnobssub,aibintrnobsbck,aib_interobs_finish1;

    TextView sirobserres,sirobsercounter,sirobsbtn2;
    LinearLayout laytest;
    ImageView Image_layout_Q1;
    TextView Q1_Imageview;
    Bitmap bitmapImage1;
    byte[] img_by1;
    ByteArrayInputStream imageStream1;
    String ImageCheck = "";
    Uri uri;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    private FirebaseAnalytics mFirebaseAnalytics;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    //   String bitmapst;
    String mCurrentPhotoPath;

    public static final int RequestPermissionCode = 1;



    private RadioButton radiovaxButton,radiovaxButton2,radiovaxButton3,rdbtn1,rdbtn2,rdbtn3,rdbtn4,rdbtn5,rdbtn6,rdbtn7,rdbtn8,rdbtn9,rdbtn10,rdbtn11,rdbtn12;

    private RadioGroup radiovaxGroup,radiovaxGroup2,radiovaxGroup3,aib_obs_rd1,aib_obs_rd2,aib_obs_rd3,aib_obs_rd4,aib_obs_rd5,aib_obs_rd6,aib_obs_rd7,aib_obs_rd8,aib_obs_rd9,aib_obs_rd10,aib_obs_rd11,aib_obs_rd12;

    EditText sirobsloc,sirobsrecopci,sirobsrecocus,aib_obs_et_detail;

    private  int counter=1;

    String sirobslocation,sirobsrecpci,sirobsrecus,sirobssdisp,getdetails,radiogets1,radiogets2,radiogets3,getrd1,getrd2,getrd3,getrd4,getrd5,getrd6,getrd7,getrd8,getrd9,getrd10,getrd11,getrd12;

    public  static TextView sirobsdisplay ;

    static String getradio1,getradio2,getradio3;



    ArrayList<String> loc_arr = new ArrayList<String>();
    ArrayList<String> pest_cover_arr = new ArrayList<String>();
    ArrayList<String> image_arr = new ArrayList<String>();
    ArrayList<String> recomm_rpci_arr = new ArrayList<String>();
    ArrayList<String> cust_arr = new ArrayList<String>();
    ArrayList<String> status_arr = new ArrayList<String>();
    ArrayList<String> priority_arr = new ArrayList<String>();
    ArrayList<String> ass_to_arr = new ArrayList<String>();
    ArrayList<String> bitmapst = new ArrayList<String>();

    ArrayList<String> gtrd1_arr = new ArrayList<String>();
    ArrayList<String> gtrd2_arr = new ArrayList<String>();
    ArrayList<String> gtrd3_arr = new ArrayList<String>();
    ArrayList<String> gtrd4_arr = new ArrayList<String>();
    ArrayList<String> gtrd5_arr = new ArrayList<String>();
    ArrayList<String> gtrd6_arr = new ArrayList<String>();
    ArrayList<String> gtrd7_arr = new ArrayList<String>();
    ArrayList<String> gtrd8_arr = new ArrayList<String>();
    ArrayList<String> gtrd9_arr = new ArrayList<String>();
    ArrayList<String> gtrd10_arr = new ArrayList<String>();
    ArrayList<String> gtrd11_arr = new ArrayList<String>();

    ArrayList<String> detail_arr = new ArrayList<String>();
    ConnectivityManager cManager;
    NetworkInfo nInfo;


    private android.app.AlertDialog pd;


    String Image_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aib__interlobser);

        EnableRuntimePermission();

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (ImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Interl_Obser_6.this);
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
                        Toast.makeText(AIB_Interl_Obser_6.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Interl_Obser_6.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Image_layout_Q1.setImageResource(0);

                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                if (Image_layout_Q1.getDrawable() == null) {
                    Image_layout_Q1.setVisibility(View.GONE);
                } else {
                    Image_layout_Q1.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        Q1_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image_layout_Q1.setVisibility(View.VISIBLE);


                if (Image_layout_Q1.getDrawable() == null) {
                    ImageCheck = "1111";
                    selectImage();
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
        pd = new SpotsDialog(AIB_Interl_Obser_6.this, R.style.Custom);
        db = new DatabaseHelper(AIB_Interl_Obser_6.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        aibintrnobssub=(Button) findViewById(R.id.aibintrobsub);
        aibintrnobsbck=(Button) findViewById(R.id.aibintrobck);
        aib_interobs_finish1=(Button) findViewById(R.id.aib_interobs_finish1);

        aib_interobs_finish1.setVisibility(View.GONE);

        aib_interobs_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                aib_inter_obser_button_next();

                Intent i = new Intent(AIB_Interl_Obser_6.this,AIB_Summary_7.class);
                startActivity(i);

            }
        });

        aib_obs_rd1=(RadioGroup) findViewById(R.id.aib_obs_rd1);
        aib_obs_rd2=(RadioGroup) findViewById(R.id.aib_obs_rd2);
        aib_obs_rd3=(RadioGroup) findViewById(R.id.aib_obs_rd3);
        aib_obs_rd4=(RadioGroup) findViewById(R.id.aib_obs_rd4);
        aib_obs_rd5=(RadioGroup) findViewById(R.id.aib_obs_rd5);
        aib_obs_rd6=(RadioGroup) findViewById(R.id.aib_obs_rd6);
        aib_obs_rd7=(RadioGroup) findViewById(R.id.aib_obs_rd7);
        aib_obs_rd8=(RadioGroup) findViewById(R.id.aib_obs_rd8);
        aib_obs_rd9=(RadioGroup) findViewById(R.id.aib_obs_rd9);
        aib_obs_rd10=(RadioGroup) findViewById(R.id.aib_obs_rd10);
        aib_obs_rd11=(RadioGroup) findViewById(R.id.aib_obs_rd11);








        aib_obs_et_detail=(EditText) findViewById(R.id.aib_obs_et_detail);



        aibintrnobsbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_inter_obser_button_back();

                Intent i = new Intent(AIB_Interl_Obser_6.this,AIB_Ext_Obser_5_2_1.class);
                startActivity(i);


            }
        });

        sirobsercounter=(TextView) findViewById(R.id.sirobsercounter);
        sirobsbtn2=(TextView) findViewById(R.id.sirobsbtn2);

        laytest=(LinearLayout) findViewById(R.id.sirobserlayout);

//        laytest.setVisibility(View.GONE);

        sirobsrecopci =(EditText) findViewById(R.id.sirobserrecorentopci);
        sirobsrecocus =(EditText) findViewById(R.id.sirobserrecorentocus);

        sirobsloc =(EditText) findViewById(R.id.sirobslocation);

        sirobserres=(TextView) findViewById(R.id.sirobserresponse);

        sirobsdisplay=(TextView) findViewById(R.id.sirobsdisplay);


//        sirradio1=(RadioGroup)findViewById(R.id.sirobsradiogroup1);
        radiovaxGroup2=(RadioGroup)findViewById(R.id.sirobradgroup2);
        radiovaxGroup3=(RadioGroup)findViewById(R.id.sirobradgroup3);

        radiovaxGroup=(RadioGroup) findViewById(R.id.sirobsradiogroup1);

        radiovaxGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=radiovaxGroup.getCheckedRadioButtonId();

                radiovaxButton=(RadioButton)findViewById(selectedId);



            }
        });



        radiovaxGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                int selectedId=radiovaxGroup2.getCheckedRadioButtonId();

                radiovaxButton2=(RadioButton)findViewById(selectedId);

            }
        });

        radiovaxGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=radiovaxGroup3.getCheckedRadioButtonId();

                radiovaxButton3=(RadioButton)findViewById(selectedId);

            }
        });


        aib_obs_rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd1.getCheckedRadioButtonId();

                rdbtn1=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd2.getCheckedRadioButtonId();

                rdbtn2=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd3.getCheckedRadioButtonId();

                rdbtn3=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd4.getCheckedRadioButtonId();

                rdbtn4=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd5.getCheckedRadioButtonId();

                rdbtn5=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd6.getCheckedRadioButtonId();

                rdbtn6=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd7.getCheckedRadioButtonId();

                rdbtn7=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd8.getCheckedRadioButtonId();

                rdbtn8=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd9.getCheckedRadioButtonId();

                rdbtn9=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd10.getCheckedRadioButtonId();

                rdbtn10=(RadioButton)findViewById(selectedId);

            }
        });

        aib_obs_rd11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=aib_obs_rd11.getCheckedRadioButtonId();

                rdbtn11=(RadioButton)findViewById(selectedId);

            }
        });





        sirobserres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_inter_obser_button_next();



//                laytest.setVisibility(View.VISIBLE);
                Log.e("MMMMM count ",""+counter);

                if (sirobsloc.length() == 0 && sirobsrecopci.length()==0 && sirobsrecocus.length()==0) {
//                    Toast.makeText(getApplicationContext(),
//                            "All fields are mandatory to fill", Toast.LENGTH_SHORT)
//                            .show();
                }if (sirobsloc.length() == 0) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please Fill Location Details", Toast.LENGTH_SHORT)
//                            .show();
                }

                if (sirobsrecopci.length()==0) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please Fill Recommendations- Rentokil PCI", Toast.LENGTH_SHORT)
//                            .show();
                }

                if (sirobsrecocus.length()==0) {
//                    Toast.makeText(getApplicationContext(),
//                            "Please Fill Recommendations- CUSTOMER ", Toast.LENGTH_SHORT)
//                            .show();
                }

                else {
                    Log.e("MMMMM else","else"+counter);
                    Log.e("MMMMM loc",""+sirobsloc.getText().toString());
                    if (counter!=0) {
                        getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                        getradio2 = ((RadioButton)findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                        getradio3 = ((RadioButton)findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                        getrd1 = ((RadioButton)findViewById(aib_obs_rd1.getCheckedRadioButtonId())).getText().toString();
                        getrd2 = ((RadioButton)findViewById(aib_obs_rd2.getCheckedRadioButtonId())).getText().toString();
                        getrd3 = ((RadioButton)findViewById(aib_obs_rd3.getCheckedRadioButtonId())).getText().toString();
                        getrd4 = ((RadioButton)findViewById(aib_obs_rd4.getCheckedRadioButtonId())).getText().toString();
                        getrd5 = ((RadioButton)findViewById(aib_obs_rd5.getCheckedRadioButtonId())).getText().toString();
                        getrd6 = ((RadioButton)findViewById(aib_obs_rd6.getCheckedRadioButtonId())).getText().toString();
                        getrd7 = ((RadioButton)findViewById(aib_obs_rd7.getCheckedRadioButtonId())).getText().toString();
                        getrd8 = ((RadioButton)findViewById(aib_obs_rd8.getCheckedRadioButtonId())).getText().toString();
                        getrd9 = ((RadioButton)findViewById(aib_obs_rd9.getCheckedRadioButtonId())).getText().toString();
                        getrd10 = ((RadioButton)findViewById(aib_obs_rd10.getCheckedRadioButtonId())).getText().toString();
                        getrd11 = ((RadioButton)findViewById(aib_obs_rd11.getCheckedRadioButtonId())).getText().toString();


                        gtrd1_arr.add(getrd1);
                        gtrd2_arr.add(getrd2);
                        gtrd3_arr.add(getrd3);
                        gtrd4_arr.add(getrd4);
                        gtrd5_arr.add(getrd5);
                        gtrd6_arr.add(getrd6);
                        gtrd7_arr.add(getrd7);
                        gtrd8_arr.add(getrd8);
                        gtrd9_arr.add(getrd9);
                        gtrd10_arr.add(getrd10);
                        gtrd11_arr.add(getrd11);

                        detail_arr.add(aib_obs_et_detail.getText().toString());


                        loc_arr.add(sirobsloc.getText().toString());
                        pest_cover_arr.add(sirobsdisplay.getText().toString());
                        recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                        cust_arr.add(sirobsrecocus.getText().toString());
                        status_arr.add(getradio1);
                        priority_arr.add(getradio2);
                        ass_to_arr.add(getradio3);
                        bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                        if (bitmapImage1!=null) {
                            bitmapst.add(getStringImage(bitmapImage1));
                        }

                    }


                    counter++;
                    sirobsercounter.setText("" + counter);

                    if(counter>1){

                        sirobsloc.getText().clear();
                        sirobsrecopci.getText().clear();
                        sirobsrecocus.getText().clear();
                        radiovaxGroup.clearCheck();
                        radiovaxGroup2.clearCheck();
                        radiovaxGroup3.clearCheck();
                        sirobsdisplay.setText("");

                        aib_obs_rd1.clearCheck();
                        aib_obs_rd2.clearCheck();
                        aib_obs_rd3.clearCheck();
                        aib_obs_rd4.clearCheck();
                        aib_obs_rd5.clearCheck();
                        aib_obs_rd6.clearCheck();
                        aib_obs_rd7.clearCheck();
                        aib_obs_rd8.clearCheck();
                        aib_obs_rd9.clearCheck();
                        aib_obs_rd10.clearCheck();
                        aib_obs_rd11.clearCheck();

                        aib_obs_et_detail.getText().clear();
                        Image_layout_Q1.setImageDrawable(null);




//                        for (int i = 0; i <sirradio1.getChildCount(); i++)
//                        {
//                            sirradio1.clearCheck();
//                        }
                    }
                }
            }
        });

        aibintrnobssub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_inter_obser_button_next();
                Log.e("MMMMM else","submit");
//
//                sirobslocation=sirobsloc.getText().toString();
//                sirobsrecpci=sirobsrecopci.getText().toString();
//                sirobsrecus=sirobsrecocus.getText().toString();
//                sirobssdisp=sirobsdisplay.getText().toString();

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(AIB_Interl_Obser_6.this);
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
                                Toast.makeText(AIB_Interl_Obser_6.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Interl_Obser_6.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




//







                if (validation()) {


                        try {


                            if (counter != 0) {
                                getradio1 = ((RadioButton) findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                                getradio2 = ((RadioButton) findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                                getradio3 = ((RadioButton) findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                                getrd1 = ((RadioButton) findViewById(aib_obs_rd1.getCheckedRadioButtonId())).getText().toString();
                                getrd2 = ((RadioButton) findViewById(aib_obs_rd2.getCheckedRadioButtonId())).getText().toString();
                                getrd3 = ((RadioButton) findViewById(aib_obs_rd3.getCheckedRadioButtonId())).getText().toString();
                                getrd4 = ((RadioButton) findViewById(aib_obs_rd4.getCheckedRadioButtonId())).getText().toString();
                                getrd5 = ((RadioButton) findViewById(aib_obs_rd5.getCheckedRadioButtonId())).getText().toString();
                                getrd6 = ((RadioButton) findViewById(aib_obs_rd6.getCheckedRadioButtonId())).getText().toString();
                                getrd7 = ((RadioButton) findViewById(aib_obs_rd7.getCheckedRadioButtonId())).getText().toString();
                                getrd8 = ((RadioButton) findViewById(aib_obs_rd8.getCheckedRadioButtonId())).getText().toString();
                                getrd9 = ((RadioButton) findViewById(aib_obs_rd9.getCheckedRadioButtonId())).getText().toString();
                                getrd10 = ((RadioButton) findViewById(aib_obs_rd10.getCheckedRadioButtonId())).getText().toString();
                                getrd11 = ((RadioButton) findViewById(aib_obs_rd11.getCheckedRadioButtonId())).getText().toString();


                                detail_arr.add(aib_obs_et_detail.getText().toString());

                                loc_arr.add(sirobsloc.getText().toString());
                                pest_cover_arr.add(sirobsdisplay.getText().toString());
                                recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                                cust_arr.add(sirobsrecocus.getText().toString());


                                bitmapImage1 = ((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                                //   bitmapst[0]=getStringImage(bitmapImage1);
                                if (bitmapImage1 != null) {
                                    bitmapst.add(getStringImage(bitmapImage1));
                                }

                                status_arr.add(getradio1);
                                priority_arr.add(getradio2);
                                ass_to_arr.add(getradio3);

                                gtrd1_arr.add(getrd1);
                                gtrd2_arr.add(getrd2);
                                gtrd3_arr.add(getrd3);
                                gtrd4_arr.add(getrd4);
                                gtrd5_arr.add(getrd5);
                                gtrd6_arr.add(getrd6);
                                gtrd7_arr.add(getrd7);
                                gtrd8_arr.add(getrd8);
                                gtrd9_arr.add(getrd9);
                                gtrd10_arr.add(getrd10);
                                gtrd11_arr.add(getrd11);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        post_aib_obsjs();
                    } else {

                        Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                    }



            }
        });



        sirobsbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sirobs(2235);


            }
        });

    }

    public  boolean validation() {


        try {
            sirobslocation=sirobsloc.getText().toString();
            sirobssdisp=sirobsdisplay.getText().toString();
            sirobsrecpci=sirobsrecopci.getText().toString();
            sirobsrecus=sirobsrecocus.getText().toString();
            getdetails=aib_obs_et_detail.getText().toString();



            if(TextUtils.isEmpty(sirobslocation ) || TextUtils.isEmpty(sirobssdisp) ||TextUtils.isEmpty(sirobsrecpci)
                    ||TextUtils.isEmpty(sirobsrecus) ||TextUtils.isEmpty(getdetails) ) {

                if(TextUtils.isEmpty(sirobslocation )){
                    sirobsloc.setError("Required");
                }

                if(TextUtils.isEmpty(sirobssdisp)) {
                    sirobsdisplay.setError("Required");


                }
                if(TextUtils.isEmpty(sirobsrecpci)) {
                    sirobsrecopci.setError("Required");

                }
                if(TextUtils.isEmpty(sirobsrecus)) {
                    sirobsrecocus.setError("Required");

                }
                if(TextUtils.isEmpty(getdetails)) {
                    aib_obs_et_detail.setError("Required");

                }
                return false;
            }



            if (loc_arr.size()!=0) {

                if (sirobslocation.length()==0||sirobssdisp.length()==0||sirobsrecpci.length()==0||sirobsrecus.length()==0||getdetails.length()==0){

                    return false;

                }else {
                    return  true;
                }
            }else {
                return  true;

            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }








    public String getStringImage(Bitmap bmp){
        String encodedImage = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp.setPixel(20,100, Color.BLUE);
            byte[] imageBytes = baos.toByteArray();
            encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return encodedImage;
    }

    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(AIB_Interl_Obser_6.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    ClickImageFromCamera();
                } else if (options[item].equals("Choose from Gallery")) {
                    GetImageFromGallery();
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void GetImageFromGallery() {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, 1);
        }

    }

    public void ClickImageFromCamera() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.rentokil.pci.rauditor.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, 1);
            }
        }

    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new java.util.Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("onActivityResultC1", "" + data);
        Log.e("onActivityResultC2", "" + resultCode);
        Log.e("onActivityResultC3", "" + requestCode);

        if (requestCode == 0 && resultCode == RESULT_OK) {

            //  ImageCropFunction();

        } else if (requestCode == 2) {

            if (data != null) {
                uri = data.getData();
                //     ImageCropFunction();

            }
        } else if (requestCode == 1) {

            if (data == null) {

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);


                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                if (ImageCheck.equals("1111")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("1111"));
                    if (ImageCheck.equals("1111")) {
                        Image_layout_Q1.setVisibility(View.VISIBLE);
                        try {
                            Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q1);
                    } else {
                        Image_layout_Q1.setVisibility(View.GONE);
                        Image_layout_Q1.setImageBitmap(null);
                    }

                }
            }
            if (data != null) {

                Uri photoUri = data.getData();
                // Do something with the photo based on Uri\
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), photoUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    if (ImageCheck.equals("1111")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1111"));
                        if (ImageCheck.equals("1111")) {
                            Image_layout_Q1.setVisibility(View.VISIBLE);
                            try {
                                Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q1);
                        } else {
                            Image_layout_Q1.setVisibility(View.GONE);
                            Image_layout_Q1.setImageBitmap(null);
                        }
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {


                } else {

                }
                break;
        }
    }

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(AIB_Interl_Obser_6.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(AIB_Interl_Obser_6.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(AIB_Interl_Obser_6.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(AIB_Interl_Obser_6.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(AIB_Interl_Obser_6.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }


    public void sirobs(int status) {

        Bundle bundle = new Bundle();
        if(status==2235) {
            bundle.putString("status", "2235");
        }



        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    private void post_aib_obsjs() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("count",""+loc_arr.size());
        Log.e("MMMMM size",""+loc_arr.size());
        params.put("main_id",AIB_Title_1.Main_ID);
        for (int i=0;i<loc_arr.size();i++) {
            params.put("sirobslocationq"+i,loc_arr.get(i));
            params.put("sirobsrecpciq"+i,recomm_rpci_arr.get(i));
            params.put("sirobsrecusq"+i,cust_arr.get(i));
            params.put("sirobssdispq"+i,pest_cover_arr.get(i));
            params.put("getradio1q"+i,status_arr.get(i));
            params.put("getradio2q"+i,priority_arr.get(i));
            params.put("getradio3q"+i,ass_to_arr.get(i));
            params.put("addmediai"+i,bitmapst.get(i));
            params.put("getrad1"+i,gtrd1_arr.get(i));
            params.put("getrad2"+i,gtrd2_arr.get(i));
            params.put("getrad3"+i,gtrd3_arr.get(i));
            params.put("getrad4"+i,gtrd4_arr.get(i));
            params.put("getrad5"+i,gtrd5_arr.get(i));
            params.put("getrad6"+i,gtrd6_arr.get(i));
            params.put("getrad7"+i,gtrd7_arr.get(i));
            params.put("getrad8"+i,gtrd8_arr.get(i));
            params.put("getrad9"+i,gtrd9_arr.get(i));
            params.put("getrad10"+i,gtrd10_arr.get(i));
            params.put("getrad11"+i,gtrd11_arr.get(i));
            params.put("detail"+i,detail_arr.get(i));

            Log.e("DSDS","params"+params);
        }

        Log.e("DSDS","params 1 ="+params);


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_inter_obs.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();

                            Log.e( "JJJJJJJ","Res\t"+response );
                            Intent i = new Intent(AIB_Interl_Obser_6.this,AIB_Summary_7.class);
                            startActivity(i);
                            //   Toast.makeText(SIR_Cus_2.this,response,Toast.LENGTH_LONG).show();


                            //  Toast.makeText( AIB_Interl_Obser_6.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e( "JJJJJJJ","ERRRR RRRR = "+error.getMessage() );

                        if(error.getMessage().equalsIgnoreCase("null")){

                            Intent i = new Intent(AIB_Interl_Obser_6.this,AIB_Summary_7.class);
                            startActivity(i);
                        }
                        else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                        {
                            Intent i = new Intent(AIB_Interl_Obser_6.this,AIB_Summary_7.class);
                            startActivity(i);

                        }
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
            AIB_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_aib_obs_6.php?main_id="+AIB_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            Log.e("GGGG","res ipm obs="+response);


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];

                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString("main_id");
                                String get_location = c.getString("location");
                                String get_aib_image= c.getString("image");
                                String get_pest_obser = c.getString("pest_obser");
                                String get_doors_window = c.getString("doors_window");
                                String get_auto_door = c.getString("auto_door");
                                String get_air_curtain = c.getString("air_curtain");
                                String get_air_direct = c.getString("air_direct");
                                String get_air_roof = c.getString("air_roof");
                                String get_all_sliding = c.getString("all_sliding");
                                String get_open_hole = c.getString("open_hole");
                                String get_obser_resid = c.getString("obser_resid");
                                String get_rodent_sta = c.getString("rodent_sta");
                                String get_insect_light = c.getString("insect_light");
                                String get_floor_drain = c.getString("floor_drain");
                                String get_detail_obser = c.getString("detail_obser");
                                String get_ri_recomm = c.getString("ri_recomm");
                                String get_cust_recomm = c.getString("cust_recomm");
                                String get_obser_status = c.getString("obser_status");
                                String get_priority = c.getString("priority");
                                String get_assign_to = c.getString("assign_to");

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("KIKIL","update value = "+c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS));



                                if(get_pmca_complete.equalsIgnoreCase("2")) {

                                    Log.e("DDVCX","en1");




                                    aibintrnobsbck.setVisibility(View.GONE);
                                    aibintrnobssub.setVisibility(View.GONE);
                                    aib_interobs_finish1.setVisibility(View.VISIBLE);

                                    sirobsloc.setText(get_location);
                                    sirobsdisplay.setText(get_pest_obser);
                                    aib_obs_et_detail.setText(get_detail_obser);
                                    sirobsrecopci.setText(get_ri_recomm);
                                    sirobsrecocus.setText(get_cust_recomm);

                                    sirobsloc.setEnabled(false);
                                    aib_obs_et_detail.setEnabled(false);
                                    sirobsrecopci.setEnabled(false);
                                    sirobsrecocus.setEnabled(false);


                                    aib_obs_rd1.getChildAt(0).setEnabled(false);
                                    aib_obs_rd1.getChildAt(1).setEnabled(false);
                                    aib_obs_rd1.getChildAt(2).setEnabled(false);


                                    aib_obs_rd2.getChildAt(0).setEnabled(false);
                                    aib_obs_rd2.getChildAt(1).setEnabled(false);
                                    aib_obs_rd2.getChildAt(2).setEnabled(false);

                                    aib_obs_rd3.getChildAt(0).setEnabled(false);
                                    aib_obs_rd3.getChildAt(1).setEnabled(false);
                                    aib_obs_rd3.getChildAt(2).setEnabled(false);

                                    aib_obs_rd4.getChildAt(0).setEnabled(false);
                                    aib_obs_rd4.getChildAt(1).setEnabled(false);
                                    aib_obs_rd4.getChildAt(2).setEnabled(false);

                                    aib_obs_rd5.getChildAt(0).setEnabled(false);
                                    aib_obs_rd5.getChildAt(1).setEnabled(false);
                                    aib_obs_rd5.getChildAt(2).setEnabled(false);

                                    aib_obs_rd6.getChildAt(0).setEnabled(false);
                                    aib_obs_rd6.getChildAt(1).setEnabled(false);
                                    aib_obs_rd6.getChildAt(2).setEnabled(false);

                                    aib_obs_rd7.getChildAt(0).setEnabled(false);
                                    aib_obs_rd7.getChildAt(1).setEnabled(false);
                                    aib_obs_rd7.getChildAt(2).setEnabled(false);

                                    aib_obs_rd8.getChildAt(0).setEnabled(false);
                                    aib_obs_rd8.getChildAt(1).setEnabled(false);
                                    aib_obs_rd8.getChildAt(2).setEnabled(false);

                                    aib_obs_rd9.getChildAt(0).setEnabled(false);
                                    aib_obs_rd9.getChildAt(1).setEnabled(false);
                                    aib_obs_rd9.getChildAt(2).setEnabled(false);

                                    aib_obs_rd10.getChildAt(0).setEnabled(false);
                                    aib_obs_rd10.getChildAt(1).setEnabled(false);
                                    aib_obs_rd10.getChildAt(2).setEnabled(false);

                                    aib_obs_rd11.getChildAt(0).setEnabled(false);
                                    aib_obs_rd11.getChildAt(1).setEnabled(false);
                                    aib_obs_rd11.getChildAt(2).setEnabled(false);


                                    radiovaxGroup.getChildAt(0).setEnabled(false);
                                    radiovaxGroup.getChildAt(1).setEnabled(false);
                                    radiovaxGroup.getChildAt(2).setEnabled(false);
                                    radiovaxGroup.getChildAt(3).setEnabled(false);

                                    radiovaxGroup2.getChildAt(0).setEnabled(false);
                                    radiovaxGroup2.getChildAt(1).setEnabled(false);
                                    radiovaxGroup2.getChildAt(2).setEnabled(false);

                                    radiovaxGroup3.getChildAt(0).setEnabled(false);
                                    radiovaxGroup3.getChildAt(1).setEnabled(false);
                                    radiovaxGroup3.getChildAt(2).setEnabled(false);





                                    if(get_doors_window.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) aib_obs_rd1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_doors_window.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) aib_obs_rd1.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_doors_window.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) aib_obs_rd1.getChildAt(2)).setChecked(true);

                                    }
//////////////////////////NEXT/////////////////////////////////////////
                                    if(get_auto_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_auto_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd2.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_auto_door.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd2.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_air_curtain.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_curtain.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_air_curtain.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd3.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_air_direct.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_direct.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd4.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_air_direct.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd4.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_air_roof.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd5.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_roof.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd5.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_air_roof.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd5.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_all_sliding.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd6.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_all_sliding.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd6.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_all_sliding.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd6.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_open_hole.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_open_hole.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd7.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_open_hole.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd7.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_obser_resid.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd8.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_obser_resid.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd8.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_obser_resid.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd8.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_rodent_sta.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_sta.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd9.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_rodent_sta.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd9.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_insect_light.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd10.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_insect_light.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd10.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_insect_light.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd10.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_floor_drain.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd11.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_drain.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd11.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_floor_drain.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd11.getChildAt(2)).setChecked(true);

                                    }





                                    if(get_obser_status.equalsIgnoreCase("NEW")){
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_obser_status.equalsIgnoreCase("REPEAT")){

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    }

                                    else if(get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }

                                    if(get_priority.equalsIgnoreCase("HIGH")){
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_priority.equalsIgnoreCase("MEDIUM")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_priority.equalsIgnoreCase("LOW")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_assign_to.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_assign_to.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_assign_to.equalsIgnoreCase("BOTH")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_aib_image.length()!=0){
                                        Image_1=get_aib_image;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_aib_image, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));


pd.dismiss();





//                                    Update_Status = "2";

                                }
                                else if(get_pmca_complete.equalsIgnoreCase("1")) {

                                    Log.e("DDVCX","en2");

                                    sirobsloc.setText(get_location);
                                    sirobsdisplay.setText(get_pest_obser);
                                    aib_obs_et_detail.setText(get_detail_obser);
                                    sirobsrecopci.setText(get_ri_recomm);
                                    sirobsrecocus.setText(get_cust_recomm);


                                    if(get_doors_window.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) aib_obs_rd1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_doors_window.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) aib_obs_rd1.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_doors_window.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) aib_obs_rd1.getChildAt(2)).setChecked(true);

                                    }
//////////////////////////NEXT/////////////////////////////////////////
                                    if(get_auto_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_auto_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd2.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_auto_door.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd2.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_air_curtain.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_curtain.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_air_curtain.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd3.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_air_direct.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_direct.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd4.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_air_direct.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd4.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_air_roof.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd5.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_roof.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd5.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_air_roof.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd5.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_all_sliding.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd6.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_all_sliding.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd6.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_all_sliding.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd6.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_open_hole.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_open_hole.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd7.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_open_hole.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd7.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_obser_resid.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd8.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_obser_resid.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd8.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_obser_resid.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd8.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_rodent_sta.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_sta.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd9.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_rodent_sta.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd9.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_insect_light.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd10.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_insect_light.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd10.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_insect_light.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd10.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////////////////NEXT/////////////////////////////////////////
                                    if(get_floor_drain.equalsIgnoreCase("YES")){
                                        ((RadioButton) aib_obs_rd11.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_drain.equalsIgnoreCase("NO")){

                                        ((RadioButton) aib_obs_rd11.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_floor_drain.equalsIgnoreCase("NA")){

                                        ((RadioButton) aib_obs_rd11.getChildAt(2)).setChecked(true);

                                    }





                                    if(get_obser_status.equalsIgnoreCase("NEW")){
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_obser_status.equalsIgnoreCase("REPEAT")){

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    }

                                    else if(get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }

                                    if(get_priority.equalsIgnoreCase("HIGH")){
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_priority.equalsIgnoreCase("MEDIUM")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_priority.equalsIgnoreCase("LOW")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_assign_to.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_assign_to.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_assign_to.equalsIgnoreCase("BOTH")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_aib_image.length()!=0){
                                        Image_1=get_aib_image;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_aib_image, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));







                                }

                            }

                            //   Toast.makeText( IPM_Obser_5.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        //mobile.setText( error.getMessage() );

                        Log.e("ERERTY","err ipm obs get"+error.getMessage());
                    }
                } )
                .requestJson();
    }


    private void aib_inter_obser_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_inter_obser_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }





}
