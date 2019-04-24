package com.rentokil.pci.rauditor.MSOT_NEW;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.BulletSpan;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.rentokil.pci.rauditor.Category_Type_Activity;

import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.MSOT.ChildAnimationExample;
import com.rentokil.pci.rauditor.MSOT.ExpandableHeightListView;
import com.rentokil.pci.rauditor.MSOT.SliderLayout;
import com.rentokil.pci.rauditor.QSR_Title_1;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import customfonts.MyTextViewMSOT;
import dmax.dialog.SpotsDialog;

public class Page_A extends AppCompatActivity implements BaseSliderView.OnSliderClickListener{


    SliderLayout mDemoSlider;


    public static ExpandableHeightListView listview;

    TextView img_toast;
    public static TextView sub_inc1;

    ImageView img_tool1,draft;

    String Image_1,Image_2;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    String get_et1,get_et2;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;

    private android.app.AlertDialog pd;

    ArrayList<String> bitmapst = new ArrayList<String>();


    public static final int RequestPermissionCode = 1;
    String bitmapget1,bitmapget2;

    Bitmap bitmapImage1,bitmapImage2;

    byte byte_ar_1[];
    byte byte_ar_2[];
    Bitmap bitmap_1,bitmap_2;
    ByteArrayInputStream imageStream1;
    String ImageCheck = "";
    Uri uri;
    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";
    //   String bitmapst;
    String mCurrentPhotoPath;
    SpannableStringBuilder ssb;
    EditText input1,input2;
    String get_input1,get_input2;
    MyTextViewMSOT sub_1,sub_nxt,sub_2;
    Snackbar snackbar;
    Boolean isInternetPresent = false;
    RadioGroup rd1,rd2;
    String get_rd1,get_rd2;
    TextView addcmt1,addcmt2,cmt1,cmt2,img_toast1;
    String get_cmt1,get_cmt2;
    de.hdodenhof.circleimageview.CircleImageView Image_layout_Q1,Image_layout_Q2;
    CoordinatorLayout coordinatorLayout;
    ContentValues cv_off,cv_off_image;
    TextView Q1_Imageview,Q2_Imageview;
    String Main_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_a);

        cv_off = new ContentValues();
        cv_off_image = new ContentValues();
        db = new DatabaseHelper(this);

        isInternetPresent = haveNetworkConnection(Page_A.this);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

        sub_1  = (MyTextViewMSOT)findViewById(R.id.sub_1);
        sub_2  = (MyTextViewMSOT)findViewById(R.id.sub_2);
        sub_nxt  = (MyTextViewMSOT)findViewById(R.id.sub_nxt);
        pd = new SpotsDialog(Page_A.this, R.style.Custom);

        rd1 = (RadioGroup) findViewById(R.id.e1_rd1);
        rd2 = (RadioGroup) findViewById(R.id.e2_rd1);
        addcmt1=(TextView) findViewById(R.id.e1_addcmt1);
        addcmt2=(TextView) findViewById(R.id.e2_addcmt);
        cmt1=(TextView) findViewById(R.id.e1_cmt1);
        cmt2=(TextView) findViewById(R.id.e2_cmt2);
        draft=(ImageView) findViewById(R.id.draft);


        sub_nxt.setVisibility(View.GONE);

        Intent intent1=getIntent();
        Main_id=intent1.getStringExtra("key_id");

        sub_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (db.get_country(sd).equalsIgnoreCase("India")) {

                    Intent intent =new Intent(Page_A.this, Page_D.class);
                    startActivity(intent);
                    pd.dismiss();
                }
                else{
                    Intent intent =new Intent(Page_A.this, Page_B.class);
                    startActivity(intent);
                    pd.dismiss();

                }
            }
        });

        draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_A.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        Log.e("GGGG","validation");
                       insert_page_a();
                    }
                    else{
                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_A.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_A.this);
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
                                Toast.makeText(Page_A.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_A.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




            }
        });



        img_toast1=(TextView) findViewById(R.id.img_toast1);

        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);
        if(id!=null){

            Log.e("RRRTT","qsr main ="+MSOT_Main.Main_ID);
            MSOT_Main.Main_ID=id;
            get_LIST_Data(id);
        }else {
            if (!MSOT_Main.Main_ID.equals("0")) {
                // String keyid=null;

                Log.e("RRRTT","qsr else = "+MSOT_Main.Main_ID);
                get_LIST_Data(MSOT_Main.Main_ID);
            }

        }



        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


//                int selectedId=rd1.getCheckedRadioButtonId();
                get_rd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


//                int selectedId=rd1.getCheckedRadioButtonId();

                get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();


            }
        });

        addcmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_A.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


              input1 = new EditText(Page_A.this);
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

        addcmt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_A.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


                input2 = new EditText(Page_A.this);
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

        img_toast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 snackbar = Snackbar
                        .make(coordinatorLayout, "All big and small incidents must be reported immediately to manager/ supervisor, including near miss, minor injury, serious injury, non-injury case e.g. vehicle damage, fire", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);

                snackbar.setDuration(10000);
                snackbar.show();

            }
        });




         Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.Image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);


         Q2_Imageview = (TextView) findViewById(R.id.image_Q2);
        Image_layout_Q2 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.Image_layout_Q2);
        Image_layout_Q2.setVisibility(View.GONE);

        EnableRuntimePermission();



        Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_A.this);
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

        Image_layout_Q2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_A.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q2.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q2.getDrawable() == null) {
                                    Image_layout_Q2.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q2.setVisibility(View.VISIBLE);
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

        Q2_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Image_layout_Q2.getDrawable() == null) {
                    ImageCheck = "2222";
                    selectImage();
                }
            }
        });









        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);




      //  db = new DatabaseHelper(this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();


        sub_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                isInternetPresent = haveNetworkConnection(Page_A.this);
                if (isInternetPresent) {
                    pd.show();

                        if (validation()) {
                            Log.e("GGGG","validation");
                            insert_page_a();
                         }
                        else{

                            pd.dismiss();
                            Log.e("GGGG","else");

                            Toast.makeText(Page_A.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                        }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_A.this);
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
                                Toast.makeText(Page_A.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_A.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }

//
            }
        });

        sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isInternetPresent = haveNetworkConnection(Page_A.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        Log.e("GGGG","validation");
                        if (isInternetPresent) {
                            String sub ="pos";
                            submitres(sub);


                        }
                        else{
                            pd.dismiss();
                            Toast.makeText(Page_A.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{

                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_A.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_A.this);
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
                                Toast.makeText(Page_A.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_A.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }

//
            }
        });













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

    private float dp(int dp) {
        return getResources().getDisplayMetrics().density * dp;
    }





    public boolean validation() {

        try {
            get_rd1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();

            if(get_rd1.length()==0||get_rd2.length()==0){
                return false;
            }
            else {
                return  true;
            }
            try {
                bitmap_1 = ((BitmapDrawable)Image_layout_Q1.getDrawable()).getBitmap();
                ByteArrayOutputStream stream_1 = new ByteArrayOutputStream();
                bitmap_1.compress(Bitmap.CompressFormat.PNG, 50, stream_1);
                byte_ar_1 = stream_1.toByteArray();

                bitmap_2 = ((BitmapDrawable)Image_layout_Q2.getDrawable()).getBitmap();
                ByteArrayOutputStream stream_2 = new ByteArrayOutputStream();
                bitmap_2.compress(Bitmap.CompressFormat.PNG, 50, stream_2);
                byte_ar_2 = stream_2.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("LLLLLL",""+e.getMessage());
            return false;
        }

    }

    private void submitres(final String saveid) {

        sd.delete(db.MSOT_TABLE,null,null);

        Log.e("GGGG","sub");
        isInternetPresent = haveNetworkConnection(Page_A.this);
        if (isInternetPresent) {






            if (rd1!=null) {
                get_rd1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            }else {
                get_rd1="";
            }
            if (rd2!=null) {
                get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            }else {
                get_rd2="";
            }

            if (bitmapImage1!=null) {
                bitmapget1=getStringImage(bitmapImage1);
            }else {
                bitmapget1="";
            }

            if (bitmapImage2!=null) {
                bitmapget2=getStringImage(bitmapImage2);
            }else {
                bitmapget2="";
            }

            if (input2!=null) {
                get_input2 = input2.getText().toString();
            }else {
                get_input2="";
            }

            if (input1!=null) {
                get_input1 = input1.getText().toString();
            }else {
                get_input1="";
            }



            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedDate  = dateFormat.format(new Date());



            Map<String, String> params = new HashMap<String, String>();
            params.put("pos_et1",get_rd1);
            params.put("pos_et2",get_rd2);
            params.put("getimage1",bitmapget1);
            params.put("getimage2",bitmapget2);
            params.put("add_c1",get_input1);
            params.put("add_c2",get_input2);
            params.put("pos_date",formattedDate);
            params.put("main_id", MSOT_Main.Main_ID);

            Log.e("FFFDD","params"+params);
            Log.e("HHHTY","et1"+get_rd1);
            Log.e("EEEEP","et2"+get_rd2);
            Log.e("VVVVA","image1"+bitmapget1);
            Log.e("XXXXS","image2"+bitmapget2);



            VolleyDataRequester.withDefaultHttps( this )
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_1.php")
                    .setBody( params )
                    .setMethod( VolleyDataRequester.Method.POST )
                    .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            try {


                                Log.e("GGGG","e1"+response);




                                cv1.put(db.STATUS, "1");
                                sd.insert(db.MSOT_TABLE, null, cv1);


                                try {
                                    Log.e("CCCSSSWW","save id = "+saveid);

                                    if(saveid.equalsIgnoreCase("save")){


                                    final AlertDialog.Builder builder = new AlertDialog.Builder(Page_A.this);
                                    builder.setMessage("Are You Sure Want to Save & Exit");
                                    builder.setPositiveButton("YES",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    Intent intent=new Intent(Page_A.this, Category_Type_Activity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            pd.dismiss();
                                            dialog.dismiss();

                                            if (db.get_country(sd).equalsIgnoreCase("India")) {

                                                Intent intent =new Intent(Page_A.this, Page_D.class);
                                                startActivity(intent);
                                                pd.dismiss();
                                            }
                                            else{
                                                Intent intent =new Intent(Page_A.this, Page_B.class);
                                                startActivity(intent);
                                                pd.dismiss();

                                            }



                                        }
                                    });
                                    builder.setCancelable(false);
                                    builder.show();

                                    }
                                    else if(saveid.equalsIgnoreCase("pos")){


                                        if (db.get_country(sd).equalsIgnoreCase("India")) {

                                            Intent intent =new Intent(Page_A.this, Page_D.class);
                                            startActivity(intent);
                                            pd.dismiss();
                                        }
                                        else{
                                            Intent intent =new Intent(Page_A.this, Page_B.class);
                                            startActivity(intent);
                                            pd.dismiss();

                                        }


                                    }





//                                    Intent intent=getIntent();
//                                    String poss=intent.getStringExtra("page_name");
//
//                                    Log.e("YYYYYYY index",""+poss);
//                                    int poss1=Integer.parseInt(poss);
//                                    ++poss1;
//                                    String next_page= MSOT_LIST_2.movieList.get(poss1).getTitle();
//                                    next_page_f(next_page,poss1);

                                } catch (Exception e) {
//                                    Intent intent=new Intent(Page_A.this, MSOT_LIST_2.class);
//                                    startActivity(intent);
//                                    finish();
                                    Log.e("YYYYYYY index er",""+e.getMessage());
                                    e.printStackTrace();
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

                            try {
                                pd.dismiss();
                                if(error.getMessage().equalsIgnoreCase("null")){

                                    if (db.get_country(sd).equalsIgnoreCase("India")) {
                                        pd.dismiss();

                                        Intent intent =new Intent(Page_A.this, Page_D.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        pd.dismiss();
                                        Intent intent =new Intent(Page_A.this, Page_B.class);
                                        startActivity(intent);

                                    }

                                }
                            } catch (Exception e) {
                                if (db.get_country(sd).equalsIgnoreCase("India")) {

                                    Intent intent =new Intent(Page_A.this, Page_D.class);
                                    startActivity(intent);
                                }
                                else{
                                    Intent intent =new Intent(Page_A.this, Page_B.class);
                                    startActivity(intent);

                                }
                                e.printStackTrace();
                            }

                        }
                    } )
                    .requestString();




        }
        else{

            final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

            final Dialog dialog = new Dialog(Page_A.this);
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
                        Toast.makeText(Page_A.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Page_A.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();


        }



    }

    @Override
    public void onBackPressed(){

        Intent i = new Intent(Page_A.this,MSOT_LIST_1.class);
        startActivity(i);


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
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (snackbar != null && snackbar.isShown()) {

                Rect sRect = new Rect();
                snackbar.getView().getHitRect(sRect);

                //This way the snackbar will only be dismissed if
                //the user clicks outside it.
                if (!sRect.contains((int)ev.getX(), (int)ev.getY())) {
                    snackbar.dismiss();
                }
            }
        }

        return super.dispatchTouchEvent(ev);
    }

    public String getStringImage(Bitmap bmp){
        String encodedImage = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp.setPixel(20,100, Color.BLUE);
            byte[] imageBytes = baos.toByteArray();
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return encodedImage;
    }

    private void selectImage() {



        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Page_A.this);
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

    private void captureImage(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        /* create instance of File with name img.jpg */
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "img.jpg");
        /* put uri as extra in intent object */
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        intent.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        startActivityForResult(intent, 1);
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


    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(Page_A.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(Page_A.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(Page_A.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(Page_A.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Page_A.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
     } else if (requestCode == 2) {
            if (data != null) {
                uri = data.getData();
            }
        } else if (requestCode == 1) {

            if (data == null) {

                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inPurgeable = true;
                Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                if (ImageCheck.equals("1111")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("1111"));
                    if (ImageCheck.equals("1111")) {

                        try {
                            Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();


                            Image_layout_Q1.setVisibility(View.VISIBLE);
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q1);
                    } else {
                        Q1_Imageview.setVisibility(View.VISIBLE);
                        Image_layout_Q1.setVisibility(View.GONE);
                        Image_layout_Q1.setImageBitmap(null);
                    }

                }

                if (ImageCheck.equals("2222")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("2222"));
                    if (ImageCheck.equals("2222")) {

                        Log.e("PPAAQ","GGF1");
                        Image_layout_Q2.setVisibility(View.VISIBLE);

                        try {
                            Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            bitmapImage2=((BitmapDrawable) Image_layout_Q2.getDrawable()).getBitmap();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("DDDREWS", "" + bitmapImage2);
                    } else {

                        Log.e("PPAAQ","GGF2");
                        Image_layout_Q2.setVisibility(View.GONE);
                        Image_layout_Q2.setImageBitmap(null);


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
                    Log.e("EEETT ERR","error = "+e.getMessage());
                }catch (NullPointerException e1){


                    Log.e("EEETT ERRss","error = "+e1.getMessage());
                }

                try {
                    if (ImageCheck.equals("1111")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1111"));
                        if (ImageCheck.equals("1111")) {

                            try {
                                Image_layout_Q1.setVisibility(View.VISIBLE);
                                Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {
                                Image_layout_Q1.setVisibility(View.GONE);
                                Log.e("EEETT BBBV","error = "+e.getMessage());
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q1);
                        } else {
                            Image_layout_Q1.setVisibility(View.GONE);
                            Image_layout_Q1.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("2222")) {

                        Log.e("DDDSX","FFF1");
                        Log.e("ImageCheck11", "" + ImageCheck.equals("2222"));
                        if (ImageCheck.equals("2222")) {

                            Log.e("DDDSX","FFF2");

                            try {
                                Image_layout_Q2.setVisibility(View.VISIBLE);
                                Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage2=((BitmapDrawable) Image_layout_Q2.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                Image_layout_Q2.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q2);
                        } else {

                            Log.e("DDDSX","FFF3");
                            Image_layout_Q2.setVisibility(View.GONE);
                            Image_layout_Q2.setImageBitmap(null);
                        }
                    }
                } catch (NullPointerException e) {

                    Log.e("DDDSX","catch"+e.getMessage());
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

    private void get_LIST_Data(String key_id){

        Log.e("XXXXV","3rd ="+key_id);
        Log.e("KKKJJ",key_id);
        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("LLLLKI",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_1.php?main_id="+MSOT_Main.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("GGGGG","response = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String et1 = c.getString( "et1" );
                                String et2 = c.getString( "et2" );
                                String image_1 = c.getString( "image1" );
                                String image_2 = c.getString( "image2" );
                                String cv_cmt1 = c.getString( "cmt1" );
                                String cv_cmt2 = c.getString( "cmt2" );



                                sub_nxt.setVisibility(View.VISIBLE);

                                if(et1.equalsIgnoreCase("Yes")){
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


                                if(image_1.length()!=0){
                                    Image_1=image_1;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c = Base64.decode(image_1, Base64.DEFAULT);
                                Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));
                                Image_layout_Q1.setVisibility(View.VISIBLE);

                                if(image_2.length()!=0){
                                    Image_2=image_2;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c1 = Base64.decode(image_2, Base64.DEFAULT);
                                Bitmap decodedByte_c1 = BitmapFactory.decodeByteArray(decodedString_c1, 0, decodedString_c1.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c1, 400, 400, false));
                                Image_layout_Q2.setVisibility(View.VISIBLE);


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




public void insert_page_a(){

    cv_off.put(db.MAIN_ID, ""+Main_id);
    cv_off.put(db.et1, ""+get_rd1);
    cv_off.put(db.et2, ""+get_rd2);
    sd.insert(db.MSOT_PAGE_A_DB,null,cv_off);

    cv_off_image.put(db.MAIN_ID,Main_id);
    cv_off_image.put(db.PAGE_ID,"1");
    cv_off_image.put(db.IMAGE_1,bitmapget1);
    cv_off_image.put(db.COMMANDS,get_input1);
    cv_off_image.put(db.QUESTION_ID,"A1");

    sd.insert(db.MSOT_IMAGE_TB,null,cv_off_image);
    cv_off_image.clear();

     cv_off_image.put(db.MAIN_ID,Main_id);
    cv_off_image.put(db.PAGE_ID,"1");
    cv_off_image.put(db.IMAGE_1,bitmapget2);
    cv_off_image.put(db.COMMANDS,get_input2);
    cv_off_image.put(db.QUESTION_ID,"A2");
    sd.insert(db.MSOT_IMAGE_TB,null,cv_off_image);

}
public void get_off_a(){
    String et1,et2;
    String Query="select * from "+db.MSOT_PAGE_A_DB +" where MAIN_ID = '" +Main_id+"'";
    Cursor cursor = sd.rawQuery(Query, null);
    cursor.moveToFirst();

    et1=cursor.getString(cursor.getColumnIndex(db.et1));
    et2=cursor.getString(cursor.getColumnIndex(db.et2));

    if(et1.equalsIgnoreCase("Yes")){
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


}
}
