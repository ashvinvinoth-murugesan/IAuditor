package com.rentokil.pci.rauditor.MSOT_NEW;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.VolleyError;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import com.rentokil.pci.rauditor.Category_Type_Activity;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.MSOT.ChildAnimationExample;
import com.rentokil.pci.rauditor.MSOT.ExpandableHeightListView;
import com.rentokil.pci.rauditor.MSOT.SliderLayout;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import customfonts.MyTextViewMSOT;
import dmax.dialog.SpotsDialog;

public class Page_J extends AppCompatActivity implements BaseSliderView.OnSliderClickListener ,View.OnClickListener{


    SliderLayout mDemoSlider;
   String Main_ID="";

    public static ExpandableHeightListView listview;


    String[] section_1={"k","l","m","n","o"};


    EditText edt_spcify_feed;

    String get_feed;

    String[] section_2={"i","h","g","f"};

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    String Image_1;

    ImageView draft;

    int follow = 0;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private long mLastClickTime = 0;
    Calendar cal = Calendar.getInstance();
    int startYear = cal.get(Calendar.YEAR);
    int startMonth = cal.get(Calendar.MONTH);
    int startDay = cal.get(Calendar.DAY_OF_MONTH);
    private SimpleDateFormat dateFormatter;
    public static final int RequestPermissionCode = 1;

    ConnectivityManager cManager;
    NetworkInfo nInfo;


    MyTextViewMSOT sub_1,sub_2;

    Boolean isInternetPresent = false;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off,cv_off_img;

    RadioGroup rd1,rd2;
    String get_rd1="",get_rd2="";
    TextView addcmt1,addcmt2;
    String get_cmt1,get_cmt2;
    String bitmapget1;
    Bitmap bitmapImage1;
    ImageView Image_layout_Q1;
    public String Update_Status = "0";
    String ImageCheck = "";
    Uri uri;
    //   String bitmapst;
    String mCurrentPhotoPath;


    EditText input1,input2;
    String get_input1,get_input2;

    TextView cmt1,cmt2;
    CoordinatorLayout coordinatorLayout;

    private android.app.AlertDialog pd;
    TextView input_validaiton;
    int final_input_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_j);

        db = new DatabaseHelper(Page_J.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();
        cv_off_img = new ContentValues();
        pd = new SpotsDialog(Page_J.this, R.style.Custom);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        rd1=(RadioGroup) findViewById(R.id.rd1);
        rd2=(RadioGroup) findViewById(R.id.rd2);

        addcmt1=(TextView) findViewById(R.id.addcmt1);
        addcmt2=(TextView) findViewById(R.id.addcmt2);

        cmt1=(TextView) findViewById(R.id.cmt1);
        cmt2=(TextView) findViewById(R.id.cmt2);
        edt_spcify_feed=(EditText) findViewById(R.id.edt_spcify_feed);

        addcmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_J.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


                input1 = new EditText(Page_J.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input1.setLayoutParams(lp);
                builder.setView(input1);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {



                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt1.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input1.getText().toString()+"</font></font> "));


                        String get_comment1 = cmt1.getText().toString();

                        if(get_comment1.equalsIgnoreCase("Remarks: ")){

                            cmt1.setText("");

                        }


//                        Toast.makeText(getApplicationContext(), "Text entered is " + input.getText().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();


            }
        });


        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("LLLMM",""+id);
        if(id!=null){

            Log.e("SSSSWE","page I = "+MSOT_Main.Main_ID);
            MSOT_Main.Main_ID=id;
            get_LIST_Data(id);
        }else {
            if (!MSOT_Main.Main_ID.equals("0")) {
                // String keyid=null;
                Log.e("CCCCX","I = "+MSOT_Main.Main_ID);
                get_LIST_Data(MSOT_Main.Main_ID);
            }

        }

        addcmt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_J.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input2 = new EditText(Page_J.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input2.setLayoutParams(lp);
                builder.setView(input2);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt2.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input2.getText().toString()+"</font></font> "));


                        String get_comment2 = cmt2.getText().toString();

                        if(get_comment2.equalsIgnoreCase("Remarks: ")){

                            cmt2.setText("");

                        }
                    }
                });
                builder.show();
            }
        });


        TextView Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);


        Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_J.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q1.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q1.getDrawable() == null) {
                                    Image_layout_Q1.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q1.setVisibility(View.VISIBLE);
                                }
                            }
                        });
                builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });


                builder.setCancelable(false);
                builder.show();


                return false;
            }
        });

        Q1_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q1.getDrawable() == null) {
                    ImageCheck = "1111";
                    selectImage();
                }
            }
        });



        isInternetPresent = haveNetworkConnection(Page_J.this);


        sub_1  = (MyTextViewMSOT)findViewById(R.id.sub_1);
        sub_2  = (MyTextViewMSOT)findViewById(R.id.sub_2);
        sub_1.setText("Next");


        sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isInternetPresent = haveNetworkConnection(Page_J.this);
                if (isInternetPresent) {
//                    get_et1 = et1.getText().toString();
//                    get_et2 = et2.getText().toString();
                    pd.show();
                    if (validation()) {
                        insert_page_j();
                    }
                    else{
                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_J.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    pd.dismiss();

                    final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

                    final Dialog dialog = new Dialog(Page_J.this);
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
                                Toast.makeText(Page_J.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_J.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }

            }
        });



        sub_1.setOnClickListener(this);
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};



        draft=(ImageView) findViewById(R.id.draft);

        draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_J.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        Log.e("GGGG","validation");
                        insert_page_j();
                    }
                    else{

                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_J.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_J.this);
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
                                Toast.makeText(Page_J.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_J.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




            }
        });


//        sub_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                isInternetPresent = haveNetworkConnection(Page_J.this);
//                if (isInternetPresent) {
////                    get_et1 = et1.getText().toString();
////                    get_et2 = et2.getText().toString();
//
//                    if (validation()) {
//                        Log.e("GGGG","validation");
//                        if (isInternetPresent) {
//                            submitres();
//                        }
//                        else{
//
//                            Toast.makeText(Page_J.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                    else{
//
//                        Log.e("GGGG","else");
//
//                        Toast.makeText(Page_J.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else{
//
//                    final Dialog dialog = new Dialog(Page_J.this);
//                    dialog.setContentView(R.layout.alertbox);
//                    dialog.setTitle("Ashvin");
//                    dialog.setCancelable(false);
//                    Button btnreff = (Button) dialog.findViewById(R.id.btnrefresh);
//                    btnreff.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            nInfo[0] = cManager.getActiveNetworkInfo();
//                            if (nInfo[0] != null && nInfo[0].isConnected()) {
//
//                                Intent intent = getIntent();
//                                finish();
//                                startActivity(intent);
//                            } else {
//                                Toast.makeText(Page_J.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    Toast.makeText(Page_J.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
//                    dialog.show();
//                }
//
////
//            }
//        });



        listview = (ExpandableHeightListView)findViewById(R.id.haircut_list);

        mDemoSlider = (SliderLayout)findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1", R.drawable.pcislider);
        file_maps.put("2",R.drawable.pci1);
        file_maps.put("3",R.drawable.pci4);


        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    //  .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);


            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            textSliderView.getBundle().putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Default);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new ChildAnimationExample());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {




    }

    public boolean validation() {
        boolean check_point=false;


        try {
            get_rd1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            get_feed=edt_spcify_feed.getText().toString();

                try {

                    if (get_rd1.length() != 0 && get_rd2.length() != 0 && get_feed.length() != 0) {
                        check_point = true;
                    } else {
                        check_point = false;
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                    return false;
                }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return check_point;


    }

    private void submitres(final String saveid) {

        Log.e("GGGG","sub");
        isInternetPresent = haveNetworkConnection(Page_J.this);
        if (isInternetPresent) {

            if (rd1 != null) {
                get_rd1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            } else {
                get_rd1 = "";
            }

            if (rd2 != null) {
                get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            } else {
                get_rd2 = "";
            }

            if (bitmapImage1!=null) {
                bitmapget1=getStringImage(bitmapImage1);
            }else {
                bitmapget1="";
            }

            if (input1!=null) {
                get_input1 = input1.getText().toString();
            }else {
                get_input1="";
            }

            if (input2!=null) {
                get_input2 = input2.getText().toString();
            }else {
                get_input2="";
            }

            if (edt_spcify_feed!=null) {
                get_feed=edt_spcify_feed.getText().toString();
            }else {
                get_feed="";
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate  = dateFormat.format(new Date());

            Map<String, String> params = new HashMap<String, String>();
            params.put("pos_et1",get_rd1);
            params.put("pos_et2",get_rd2);

            params.put("add_com1", get_input1);
            params.put("add_com2", get_input2);
            params.put("feedback", get_feed);

            params.put("getimage1", bitmapget1);
            params.put("main_id", MSOT_Main.Main_ID);
            params.put("pos_date", formattedDate);

            Log.e("GGGHH","params"+params);


            VolleyDataRequester.withDefaultHttps( this )
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_10.php")
                    .setBody( params )
                    .setMethod( VolleyDataRequester.Method.POST )
                    .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(final String response) {

                            try {

                                Log.e("GGGG","e1"+response);

                                pd.dismiss();

                                JSONObject jsonRootObject = new JSONObject(response);
                                JSONArray jsonArray = jsonRootObject.optJSONArray("result");
                                Log.e("MMMM", "KKKKK 11" + jsonArray);
                                JSONObject jsonObject = jsonArray.getJSONObject(0);
                                Log.e("MMMM", "KKKKK 12" + jsonArray);
                                String message = jsonObject.optString("message");
                                String main_id = jsonObject.optString("main_id");
                                final String status = jsonObject.optString("status");


                                if(saveid.equalsIgnoreCase("save")){


                                    final AlertDialog.Builder builder = new AlertDialog.Builder(Page_J.this);
                                    builder.setMessage("Are You Sure Want to Save & Exit");
                                    builder.setPositiveButton("YES",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    Intent intent=new Intent(Page_J.this, Category_Type_Activity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            pd.dismiss();
                                            dialog.dismiss();
                                            
                                            alertmove(status);



                                        }
                                    });
                                    builder.setCancelable(false);
                                    builder.show();

                                }
                                else if(saveid.equalsIgnoreCase("pos")) {


                         

                                    alertmove(status);
                                /*if(status.equalsIgnoreCase("Yes")){

                                    Intent intent=new Intent(Page_J.this, Signature_Activity.class);
                                    startActivity(intent);
                                }else {
                                    Intent intent=new Intent(Page_J.this, Page_K.class);
                                    intent.putExtra("main_id",main_id);
                                    startActivity(intent);

                                }
                                Log.e("MMMM","KKKKK 13"+message);
*/


//
//                                Log.e("MMMM","KKKKK"+check);


//                                for (int i = 0; i < check.length(); i++) {
//
//                                    Log.e("PPOEEE", "E1");
//
//
//
//
//                                    String g_main_id = ch;
//                                    String g_status = c.getString("status");
//
//
//                                    Log.e("VVVVDDQ","mes = "+g_message);
//                                    Log.e("VVVVDDQ","main id = "+g_main_id);
//                                    Log.e("VVVVDDQ","status = "+g_status);
//
//
//                                }






                                   /* Intent intent=new Intent(Page_J.this, Signature2.class);
                                    startActivity(intent);*/

                                    pd.dismiss();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } )
                    .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("DDDDDD","error  = "+error.getMessage());

                            pd.dismiss();
                            try {
                                if(error.getMessage().equalsIgnoreCase("null")){

                                    Intent intent=new Intent(Page_J.this, Signature2.class);
                                    startActivity(intent);

                                }
                            } catch (Exception e) {

                                Intent intent=new Intent(Page_J.this, Signature2.class);
                                startActivity(intent);
                                e.printStackTrace();
                            }

                        }
                    } )
                    .requestString();




        }
        else{

            final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

            final Dialog dialog = new Dialog(Page_J.this);
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
                        Toast.makeText(Page_J.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Page_J.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();

        }
    }



    public  void alertmove(final String status){

        AlertDialog.Builder builder = new AlertDialog.Builder(Page_J.this);
        if (status.equalsIgnoreCase("Yes")) {
            builder.setMessage("Do you Want to complete MSOT Now ?");

        }else {
            builder.setMessage("Do you Want to continue Branch MSOT ?");
        }
        builder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        if (status.equalsIgnoreCase("Yes")) {
                            Intent intent=new Intent(Page_J.this, Signature_Activity.class);
                            startActivity(intent);
                        } else {
                            Intent intent=new Intent(Page_J.this, Page_K.class);
                            startActivity(intent);
                        }


                    }
                });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {


                Intent intent=new Intent(Page_J.this, Category_Type_Activity.class);
                startActivity(intent);
            }
        });
        builder.setCancelable(false);
        builder.show();


    }

    @Override
    public void onBackPressed(){


        String name="";

        for (int i=0;i<section_2.length;i++) {
            name=get_question(""+section_2[i]);
            Log.e("DDDDHHHS","question="+name);


            if(name.startsWith("i")){

                Intent intent=new Intent(Page_J.this,Page_I.class);
                startActivity(intent);
                break;

            }else if(name.startsWith("h")){

                Intent intent=new Intent(Page_J.this,Page_H.class);
                startActivity(intent);
                break;

            }else if(name.startsWith("g")){

                Intent intent=new Intent(Page_J.this,Page_G.class);
                startActivity(intent);
                break;

            }else {

                Intent intent=new Intent(Page_J.this,Page_F.class);
                startActivity(intent);
            }

        }


    }

    private boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            Log.e("Available ", "" + ni.getTypeName());
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                    Log.i("WIFI CONNECTION ", "AVAILABLE");
                } else {
                    Log.i("WIFI CONNECTION ", "NOT AVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("MOBILE NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("MOBILE NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("USBNET")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("USBNET NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("USBNET NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile_internet")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile_net CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile_net CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile NET CONNECTION", "NOTAVAILABLE");
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sub_1){


            isInternetPresent = haveNetworkConnection(Page_J.this);
                if (isInternetPresent) {
//                    get_et1 = et1.getText().toString();
//                    get_et2 = et2.getText().toString();
pd.show();
                    if (validation()) {
                        insert_page_j();
                    }
                    else{
                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_J.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    pd.dismiss();

                    final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

                    final Dialog dialog = new Dialog(Page_J.this);
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
                                Toast.makeText(Page_J.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_J.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }

//
            }








//            for (int i=0;i<section_1.length;i++) {
//                name=get_question(""+section_1[i]);
//                Log.e("DDDDHHHSD","question="+name);
//
//                  if(name.startsWith("k")){
//                    Intent intent=new Intent(Page_J.this,Page_K.class);
//                    startActivity(intent);
//                    break;
//
//                }else if(name.startsWith("l")){
//                    Intent intent=new Intent(Page_J.this,Page_L.class);
//                    startActivity(intent);
//                    break;
//
//                }else if(name.startsWith("m")){
//                    Intent intent=new Intent(Page_J.this,Page_M.class);
//                    startActivity(intent);
//                    break;
//
//                }else if(name.startsWith("n")){
//                    Intent intent=new Intent(Page_J.this,Page_N.class);
//                    startActivity(intent);
//                    break;
//
//                }else if(name.startsWith("o")){
//                    Intent intent=new Intent(Page_J.this,Page_O.class);
//                    startActivity(intent);
//                    break;
//
//                }else {
//                    Log.e("DDDDHHHS","JJJJJ\t"+i);
//                    if (i==8) {
//                        Intent intent=new Intent(Page_J.this,Page_J.class);
//                        startActivity(intent);
//                    }
//                }
//            }
        }



    public String getStringImage (Bitmap bmp){
        String encodedImage = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp.setPixel(20, 100, Color.BLUE);
            byte[] imageBytes = baos.toByteArray();
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return encodedImage;
    }

    private void selectImage () {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Page_J.this);
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

    public void GetImageFromGallery () {

        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        // If you call startActivityForResult() using an intent that no app can handle, your app will crash.
        // So as long as the result is not null, it's safe to use the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            // Bring up gallery to select a photo
            startActivityForResult(intent, 1);
        }

    }

    public void ClickImageFromCamera () {

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


    private File createImageFile () throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
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
    protected void onActivityResult ( int requestCode, int resultCode, Intent data){
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
                            bitmapImage1 = ((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
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
                catch (NullPointerException e1){


                    Log.e("EEETT ERRss","error = "+e1.getMessage());
                }

                try {
                    if (ImageCheck.equals("1111")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1111"));
                        if (ImageCheck.equals("1111")) {

                            try {
                                Image_layout_Q1.setVisibility(View.VISIBLE);
                                Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage1 = ((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {
                                Image_layout_Q1.setVisibility(View.GONE);
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

    private void get_LIST_Data(String key_id){

        Log.e("VVVCS","h"+key_id);
        Log.e("VVVCC",key_id);
        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("EEEEQ",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_10.php?main_id="+MSOT_Main.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("MMMHHH","response = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                Log.e("PPOEEE","E1");

                                JSONObject c = jsonArray_get.getJSONObject(i);

                                String et1 = c.getString( "et1" );
                                String et2 = c.getString( "et2" );

                                Log.e("PPOEEE","E2");

                                String cv_cmt1 = c.getString( "cmt1" );
                                String cv_cmt2 = c.getString( "cmt2" );
                                String cv_feed = c.getString( "feedback" );

                                Log.e("PPOEEE","E3");

                                String image_1 = c.getString( "image1" );


                                Log.e("PPOEEE","E4");



                                if(et1.equalsIgnoreCase("Yes")) {
                                    ((RadioButton) rd1.getChildAt(0)).setChecked(true);

                                }
                                else if(et1.equalsIgnoreCase("Partial")){

                                    ((RadioButton) rd1.getChildAt(1)).setChecked(true);

                                }
                                else if(et1.equalsIgnoreCase("No")){

                                    ((RadioButton) rd1.getChildAt(2)).setChecked(true);

                                }

                                else if(et1.equalsIgnoreCase("N/A")){

                                    ((RadioButton) rd1.getChildAt(3)).setChecked(true);

                                }



                                if(et2.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd2.getChildAt(0)).setChecked(true);


                                }

                                else if(et2.equalsIgnoreCase("Partial")){

                                    ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                }
                                else if(et2.equalsIgnoreCase("No")){

                                    ((RadioButton) rd2.getChildAt(2)).setChecked(true);

                                }

                                else if(et2.equalsIgnoreCase("N/A")){

                                    ((RadioButton) rd2.getChildAt(3)).setChecked(true);

                                }



                                Log.e("PPOEEE","E8");

                                if(cv_cmt1.equalsIgnoreCase("null")){
                                    cmt1.setText("");
                                }else{
                                    cmt1.setText(cv_cmt1);
                                }
                                if(cv_cmt2.equalsIgnoreCase("null")){
                                    cmt2.setText("");
                                }else{
                                    cmt2.setText(cv_cmt2);
                                }

                                if(cv_feed.equalsIgnoreCase("null")){
                                    edt_spcify_feed.setText("");
                                }else{
                                    edt_spcify_feed.setText(cv_feed);
                                }



                                Log.e("PPOEEE","E9");

                                if(image_1.length()!=0){
                                    Image_1=image_1;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c = Base64.decode(image_1, Base64.DEFAULT);
                                Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));
                                Image_layout_Q1.setVisibility(View.VISIBLE);



                            }

//                            Toast.makeText( MSOT_Main.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e("FFFFF err HH",e.getMessage());
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );


                        Log.e("FFFFF err",error.getMessage());
                    }
                } )
                .requestJson();
    }

    public String get_question(String ques){


        String Question_Name="";
        Cursor c6;
        c6 = sd.rawQuery("Select * from " + db.MSOT_QUESTION_ID_TABLE +" where MAIN_ID = '"+db.get_main_id(sd)+"' AND QUESTION_ID like '%"+ques+"%'", null);
        c6.moveToFirst();


        if(c6.getCount()!=0){

            Question_Name=c6.getString(c6.getColumnIndex(db.QUESTION_ID));

        }

        return Question_Name;
    }


    private void get_tech_complete_date() {


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        mLastClickTime = SystemClock.elapsedRealtime();



            Map<String, String> params = new HashMap<String, String>();

            params.put("pos_date",formattedDate);
            params.put("main_id",MSOT_Main.Main_ID);

            Log.e("MMMUUY","params"+params);



            VolleyDataRequester.withDefaultHttps( this )
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/get_tech_date.php")
                    .setBody( params )
                    .setMethod( VolleyDataRequester.Method.POST )
                    .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                pd.dismiss();
                                Log.e("GGGG","e1"+response);




                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } )
                    .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("DDDDDD","error  = "+error.getMessage());
                            pd.dismiss();

                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } )
                    .requestString();




    }
    public void insert_page_j(){
        cv_off.put(db.MAIN_ID, ""+Main_ID);
        cv_off.put(db.et1, ""+get_rd1);
        cv_off.put(db.et2, ""+get_rd2);
        sd.insert(db.MSOT_PAGE_J_DB,null,cv_off);


        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"10");
        cv_off_img.put(db.IMAGE_1,bitmapget1);
        cv_off_img.put(db.COMMANDS,get_input1);
        cv_off_img.put(db.QUESTION_ID,"J2");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);



    }


}
