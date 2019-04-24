package com.rentokil.pci.rauditor.MSOT_NEW;

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
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
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
import android.widget.CheckBox;
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
import java.util.HashMap;
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

public class Page_I extends AppCompatActivity implements BaseSliderView.OnSliderClickListener , View.OnClickListener{


    SliderLayout mDemoSlider;


    public static ExpandableHeightListView listview;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off,cv_off_img;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    String Main_ID="";
    EditText edt1;

    CheckBox checkbox_1,checkbox_2,checkbox_3,checkbox_4,checkbox_5;

    String[] section_1={"j","k","l","m","n","o"};
    String[] section_2={"h","g","f"};

    EditText input1,input2,input3,input4,input5;
    String get_input1,get_input2,get_input3,get_input4,get_input5;

    TextView img_toast1;
    RadioGroup rd1,rd2,rd3,rd4,rd5;
    String get_rd1="",get_rd2="",get_rd3="",get_rd4="",get_rd5="";
    TextView addcmt1,addcmt2,addcmt3,addcmt4,addcmt5;
    String get_cmt1,get_cmt2,get_cmt3,get_cmt4,get_cmt5;

    String bitmapget1, bitmapget2,bitmapget3,bitmapget5;
    Bitmap bitmapImage1, bitmapImage2, bitmapImage3, bitmapImage5;
    ImageView Image_layout_Q1, Image_layout_Q2, Image_layout_Q3, Image_layout_Q5;
    public String Update_Status = "0";
    String ImageCheck = "";
    Uri uri;
    //   String bitmapst;
    String mCurrentPhotoPath;
    public static final int RequestPermissionCode = 1;
    LinearLayout lay_i_1,lay_i_2,lay_i_3,lay_i_4,lay_i_5,lay_i_6;
    TextView cmt1,cmt2,cmt3,cmt4,cmt5;
    CoordinatorLayout coordinatorLayout;

    Snackbar snackbar;
    ImageView draft;

    SpannableStringBuilder ssb;

    Boolean isInternetPresent = false;

    String Image_1,Image_2,Image_3,Image_4,Image_5,Image_6;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    MyTextViewMSOT sub_1,sub_2;
    private android.app.AlertDialog pd;
    String get_check1,get_check2,get_check3,get_check4,get_check5,get_et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_i);


        db = new DatabaseHelper(Page_I.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();
        cv_off_img = new ContentValues();


        pd = new SpotsDialog(Page_I.this, R.style.Custom);

        sub_1  = (MyTextViewMSOT)findViewById(R.id.sub_1);
        sub_2  = (MyTextViewMSOT)findViewById(R.id.sub_2);


        sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_I.this);
                if (isInternetPresent) {

                    pd.show();

                    if (validation()) {
                        insert_page_i();

                    }
                    else{
                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_I.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    pd.dismiss();
                    final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


                    final Dialog dialog = new Dialog(Page_I.this);
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
                                Toast.makeText(Page_I.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_I.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();


                }
            }
        });

        sub_1.setOnClickListener(this);

        edt1 =(EditText) findViewById(R.id.et1);
        checkbox_1=(CheckBox)findViewById(R.id.checkbox_1);
        checkbox_2=(CheckBox)findViewById(R.id.checkbox_2);
        checkbox_3=(CheckBox)findViewById(R.id.checkbox_3);
        checkbox_4=(CheckBox)findViewById(R.id.checkbox_4);
        checkbox_5=(CheckBox)findViewById(R.id.checkbox_5);

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




        checkbox_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkbox_1.isChecked()){
                    get_check1 = "Methyl Bromide";
                }
                else{
                    get_check1 = "";
                }
            }
        });

        checkbox_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkbox_2.isChecked()){
                    get_check2 = "Phosphide";
                }
                else{
                    get_check2 = "";
                }
            }
        });

        checkbox_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkbox_3.isChecked()){
                    get_check3 ="Hydrogen Cyanide";
                }
                else{
                    get_check3 ="";
                }
            }
        });

        checkbox_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkbox_4.isChecked()){

                   get_check4="Carbon Dioxide";
                }
                else{

                    get_check4="";
                }
            }
        });


        checkbox_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(checkbox_5.isChecked()){

                    get_check5="Ethylene Oxide";
                }
                else{

                    get_check5="";
                }
            }
        });

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        rd1=(RadioGroup) findViewById(R.id.rd1);
        rd2=(RadioGroup) findViewById(R.id.rd2);
        rd3=(RadioGroup) findViewById(R.id.rd3);
        rd4=(RadioGroup) findViewById(R.id.rd4);
        rd5=(RadioGroup) findViewById(R.id.rd5);





        lay_i_1=(LinearLayout) findViewById(R.id.lay_i_1);
        lay_i_2=(LinearLayout) findViewById(R.id.lay_i_2);
        lay_i_3=(LinearLayout) findViewById(R.id.lay_i_3);
        lay_i_4=(LinearLayout) findViewById(R.id.lay_i_4);
        lay_i_5=(LinearLayout) findViewById(R.id.lay_i_5);
        lay_i_6=(LinearLayout) findViewById(R.id.lay_i_6);

        lay_i_1.setVisibility(View.GONE);
        lay_i_2.setVisibility(View.GONE);
        lay_i_3.setVisibility(View.GONE);
        lay_i_4.setVisibility(View.GONE);
        lay_i_5.setVisibility(View.GONE);
        lay_i_6.setVisibility(View.GONE);


        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {




                get_rd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();

                if  (get_rd1.equalsIgnoreCase("YES")){
                    sub_1.setVisibility(View.VISIBLE);
                    sub_2.setVisibility(View.VISIBLE);
                    lay_i_1.setVisibility(View.VISIBLE);
                    lay_i_2.setVisibility(View.VISIBLE);
                    lay_i_3.setVisibility(View.VISIBLE);
                    lay_i_4.setVisibility(View.VISIBLE);
                    lay_i_5.setVisibility(View.VISIBLE);
                    lay_i_6.setVisibility(View.VISIBLE);

                }
                else{
                    sub_1.setVisibility(View.VISIBLE);


                    lay_i_1.setVisibility(View.GONE);
                    lay_i_2.setVisibility(View.GONE);
                    lay_i_3.setVisibility(View.GONE);
                    lay_i_4.setVisibility(View.GONE);
                    lay_i_5.setVisibility(View.GONE);
                    lay_i_6.setVisibility(View.GONE);



                }

            }
        });






        addcmt1=(TextView) findViewById(R.id.addcmt1);
        addcmt2=(TextView) findViewById(R.id.addcmt2);
        addcmt3=(TextView) findViewById(R.id.addcmt3);
        addcmt4=(TextView) findViewById(R.id.addcmt4);
        addcmt5=(TextView) findViewById(R.id.addcmt5);


        cmt1=(TextView) findViewById(R.id.cmt1);
        cmt2=(TextView) findViewById(R.id.cmt2);
        cmt3=(TextView) findViewById(R.id.cmt3);
        cmt4=(TextView) findViewById(R.id.cmt4);
        cmt5=(TextView) findViewById(R.id.cmt5);
        img_toast1=(TextView) findViewById(R.id.img_toast2);

        addcmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


                input1 = new EditText(Page_I.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input2 = new EditText(Page_I.this);
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



        addcmt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input3 = new EditText(Page_I.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input3.setLayoutParams(lp);
                builder.setView(input3);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt3.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input3.getText().toString()+"</font></font> "));


                        String get_comment3 = cmt3.getText().toString();

                        if(get_comment3.equalsIgnoreCase("Remarks: ")){

                            cmt3.setText("");

                        }
                    }
                });
                builder.show();
            }
        });

        addcmt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input4 = new EditText(Page_I.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input4.setLayoutParams(lp);
                builder.setView(input4);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt4.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input4.getText().toString()+"</font></font> "));

                        String get_comment4 = cmt4.getText().toString();

                        if(get_comment4.equalsIgnoreCase("Remarks: ")){

                            cmt4.setText("");

                        }
                    }
                });
                builder.show();
            }
        });

        addcmt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input5 = new EditText(Page_I.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input5.setLayoutParams(lp);
                builder.setView(input5);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt5.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input5.getText().toString()+"</font></font> "));


                        String get_comment5 = cmt5.getText().toString();

                        if(get_comment5.equalsIgnoreCase("Remarks: ")){

                            cmt5.setText("");

                        }
                    }
                });
                builder.show();
            }
        });

//        img_toast1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "1.\tAlways read and follow fumigant label instructions before each and every use.\n" +
//                                "2.\tAlways protect yourself from exposure to fumigants by using suitable respiratory protective equipment (RPE).\n" +
//                                "3.\tTasks designated to be carried out by two people must never be carried out by one.\n" +
//                                "4.\tOnly confirm an environment is 'gas free' after verified by a calibrated digital equipment.\n" +
//                                "5.\tNever try to deal with any emergency where it could endanger your life.  Raise the alarm, evacuate to a safe area and allow emergency services to make the situation safe.\n", Snackbar.LENGTH_LONG);
//                View snackbarView = snackbar.getView();
//                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
//                textView.setMaxLines(16);
//                snackbar.setDuration(10000);
//
//
//                snackbar.show();
//
//            }
//        });

        String longDescription = "1.\tAlways read and follow fumigant label\n     instructions before each and every use.\n\t" +
                "2.\tAlways protect yourself from exposure to \n     fumigants by using suitable respiratory \n     protective equipment (RPE).\n\t" +
                "3.\tTasks designated to be carried out by two \n     people must never be carried out by one.\n\t" +
                "4.\tOnly confirm an environment is 'gas free' \n     after verified by a calibrated digital \n     equipment.\n\t" +
                "5.\tNever try to deal with any emergency \n     where it could endanger your life.  Raise \n     the alarm,evacuate to a safe area and \n     allow emergency services to make the \n     situation safe.\n\t";

//        String longDescription = "Enhanced bass performance.\n" +
//                "Lightweight headband enhances comfort and adds durability\n" +
//                "Easy to adjust headband ensures optimum fit and comfort\n" +
//                "2 metre-long cable";


        String arr[] = longDescription.split("\n\t");

        int bulletGap = (int) dp(10);

        ssb  = new SpannableStringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String line = arr[i];
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan(bulletGap), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);

            //avoid last "\n"
            if(i+1<arr.length)
                ssb.append("\n");

        }



        img_toast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              snackbar = Snackbar
                        .make(coordinatorLayout, ssb, Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(16);
                snackbar.setDuration(10000);

                snackbar.show();

            }
        });




        TextView Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);


        TextView Q2_Imageview = (TextView) findViewById(R.id.image_Q3);
        Image_layout_Q2 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q3);
        Image_layout_Q2.setVisibility(View.GONE);

        TextView Q3_Imageview = (TextView) findViewById(R.id.image_Q4);
        Image_layout_Q3 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q4);
        Image_layout_Q3.setVisibility(View.GONE);

        TextView Q5_Imageview = (TextView) findViewById(R.id.image_Q5);
        Image_layout_Q5 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q5);
        Image_layout_Q5.setVisibility(View.GONE);

        Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
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

        Image_layout_Q3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q3.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q3.getDrawable() == null) {
                                    Image_layout_Q3.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q3.setVisibility(View.VISIBLE);
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

        Q3_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q3.getDrawable() == null) {
                    ImageCheck = "3333";
                    selectImage();
                }
            }
        });


        Image_layout_Q5.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q5.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q5.getDrawable() == null) {
                                    Image_layout_Q5.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q5.setVisibility(View.VISIBLE);
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

        Q5_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q5.getDrawable() == null) {
                    ImageCheck = "5555";
                    selectImage();
                }
            }
        });



        isInternetPresent = haveNetworkConnection(Page_I.this);


        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        draft=(ImageView) findViewById(R.id.draft);

        draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_I.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        insert_page_i();
                    }
                    else{

                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_I.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_I.this);
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
                                Toast.makeText(Page_I.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_I.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




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

    private float dp(int dp) {
        return getResources().getDisplayMetrics().density * dp;
    }

    public boolean validation() {

        boolean check_point=false;


        try {
            get_rd1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
            get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
            get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();

            if(get_rd1.equalsIgnoreCase("Yes")){


                if(get_rd5.length()!=0) {
                    try {

                        if (get_rd1.length() != 0 && get_rd2.length() != 0 && get_rd3.length() != 0 && get_rd4.length() != 0) {
                            check_point = true;
                        } else {
                            check_point = false;
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                        return false;
                    }



                }

            }
            else if(get_rd1.equalsIgnoreCase("No")){

                check_point = true;
            }


        } catch (Exception e) {
            e.printStackTrace();
         return false;
        }


        return check_point;
    }


    @Override
    public void onSliderClick(BaseSliderView slider) {




    }

    private void submitres(final String saveid) {

        Log.e("GGGG","sub");
        isInternetPresent = haveNetworkConnection(Page_I.this);
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

            if (rd3 != null) {
                get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
            } else {
                get_rd3 = "";
            }


            if (rd4 != null) {
                get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
            } else {
                get_rd4 = "";
            }

            if (rd5 != null) {
                get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();
            } else {
                get_rd5 = "";
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

            if (bitmapImage3!=null) {
                bitmapget3=getStringImage(bitmapImage3);
            }else {
                bitmapget3="";
            }


            if (bitmapImage5!=null) {
                bitmapget5=getStringImage(bitmapImage5);
            }else {
                bitmapget5="";
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
            if (input3!=null) {
                get_input3 = input3.getText().toString();
            }else {
                get_input3="";
            }
            if (input4!=null) {
                get_input4 = input4.getText().toString();
            }else {
                get_input4="";
            }
            if (input5!=null) {
                get_input5 = input5.getText().toString();
            }else {
                get_input5="";
            }

            if (edt1!=null) {
                get_et1=edt1.getText().toString();
            }else {
                get_et1="";
            }

            if (get_check1==null) {

                get_check1="";
            }

            if (get_check2==null) {

                get_check2="";
            }

            if (get_check3==null) {

                get_check3="";
            }

            if (get_check4==null) {

                get_check4="";
            }

            if (get_check5==null) {

                get_check5="";
            }




            Map<String, String> params = new HashMap<String, String>();
            params.put("pos_et1",get_rd1);
            params.put("pos_et2",get_rd2);
            params.put("pos_et3",get_rd3);
            params.put("pos_et4",get_rd4);
            params.put("pos_et5",get_rd5);

            params.put("add_com1", get_input1);
            params.put("add_com2", get_input2);
            params.put("add_com3", get_input3);
            params.put("add_com4", get_input4);
            params.put("add_com5", get_input5);

            params.put("getimage1", bitmapget1);
            params.put("getimage2", bitmapget2);
            params.put("getimage3", bitmapget3);
            params.put("getimage4", bitmapget5);


            params.put("get_check1", get_check1);
            params.put("get_check2", get_check2);
            params.put("get_check3", get_check3);
            params.put("get_check4", get_check4);
            params.put("get_check5", get_check5);
            params.put("get_et1", get_et1);
            params.put("main_id", MSOT_Main.Main_ID);

            Log.e("GGGHH","params"+params);
            Log.e("VVVFDS","params"+get_check1);
            Log.e("VVVFDS","params"+get_check2);
            Log.e("VVVFDS","params"+get_check3);
            Log.e("VVVFDS","params"+get_check4);
            Log.e("VVVFDS","params"+get_check5);
            Log.e("VVVFDS","params"+get_et1);



            VolleyDataRequester.withDefaultHttps( this )
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_9.php")
                    .setBody( params )
                    .setMethod( VolleyDataRequester.Method.POST )
                    .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            try {


                                if(saveid.equalsIgnoreCase("save")){


                                    final AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
                                    builder.setMessage("Are You Sure Want to Save & Exit");
                                    builder.setPositiveButton("YES",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    Intent intent=new Intent(Page_I.this, Category_Type_Activity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {

                                            pd.dismiss();
                                            dialog.dismiss();

                                            String name = "";

                                            for (int i = 0; i < section_1.length; i++) {
                                                name = get_question("" + section_1[i]);
                                                Log.e("DDDDHHHS", "question=" + name);


                                                if (name.startsWith("j")) {

                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_I.this, Page_J.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("k")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_I.this, Page_K.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("l")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_I.this, Page_L.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("m")) {

                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_I.this, Page_M.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("n")) {

                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_I.this, Page_N.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("o")) {

                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_I.this, Page_O.class);
                                                    startActivity(intent);
                                                    break;

                                                } else {

                                                    pd.dismiss();
                                                    Log.e("DDDDHHHS", "JJJJJ\t" + i);
                                                    if (i == 5) {
                                                        Intent intent = new Intent(Page_I.this, Page_J.class);
                                                        startActivity(intent);
                                                    }
                                                }
                                            }



                                        }
                                    });
                                    builder.setCancelable(false);
                                    builder.show();

                                }
                                else if(saveid.equalsIgnoreCase("pos")) {


                                    String name = "";

                                    for (int i = 0; i < section_1.length; i++) {
                                        name = get_question("" + section_1[i]);
                                        Log.e("DDDDHHHS", "question=" + name);


                                        if (name.startsWith("j")) {

                                            pd.dismiss();
                                            Intent intent = new Intent(Page_I.this, Page_J.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("k")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_I.this, Page_K.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("l")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_I.this, Page_L.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("m")) {

                                            pd.dismiss();
                                            Intent intent = new Intent(Page_I.this, Page_M.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("n")) {

                                            pd.dismiss();
                                            Intent intent = new Intent(Page_I.this, Page_N.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("o")) {

                                            pd.dismiss();
                                            Intent intent = new Intent(Page_I.this, Page_O.class);
                                            startActivity(intent);
                                            break;

                                        } else {

                                            pd.dismiss();
                                            Log.e("DDDDHHHS", "JJJJJ\t" + i);
                                            if (i == 5) {
                                                Intent intent = new Intent(Page_I.this, Page_J.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }
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

                                    String name="";

                                    for (int i=0;i<section_1.length;i++) {
                                        name=get_question(""+section_1[i]);
                                        Log.e("DDDDHHHS","question="+name);


                                        if(name.startsWith("j")){
                                            Intent intent=new Intent(Page_I.this,Page_J.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("k")){
                                            Intent intent=new Intent(Page_I.this,Page_K.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("l")){
                                            Intent intent=new Intent(Page_I.this,Page_L.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("m")){
                                            Intent intent=new Intent(Page_I.this,Page_M.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("n")){
                                            Intent intent=new Intent(Page_I.this,Page_N.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("o")){
                                            Intent intent=new Intent(Page_I.this,Page_O.class);
                                            startActivity(intent);
                                            break;

                                        }else {
                                            Log.e("DDDDHHHS","JJJJJ\t"+i);
                                            if (i==5) {
                                                Intent intent=new Intent(Page_I.this,Page_J.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }


                                }
                            } catch (Exception e) {

                                String name="";

                                for (int i=0;i<section_1.length;i++) {
                                    name=get_question(""+section_1[i]);
                                    Log.e("DDDDHHHS","question="+name);


                                    if(name.startsWith("j")){
                                        Intent intent=new Intent(Page_I.this,Page_J.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("k")){
                                        Intent intent=new Intent(Page_I.this,Page_K.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("l")){
                                        Intent intent=new Intent(Page_I.this,Page_L.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("m")){
                                        Intent intent=new Intent(Page_I.this,Page_M.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("n")){
                                        Intent intent=new Intent(Page_I.this,Page_N.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("o")){
                                        Intent intent=new Intent(Page_I.this,Page_O.class);
                                        startActivity(intent);
                                        break;

                                    }else {
                                        Log.e("DDDDHHHS","JJJJJ\t"+i);
                                        if (i==5) {
                                            Intent intent=new Intent(Page_I.this,Page_J.class);
                                            startActivity(intent);
                                        }
                                    }
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

            final Dialog dialog = new Dialog(Page_I.this);
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
                        Toast.makeText(Page_I.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Page_I.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();


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
    public void onBackPressed(){

        String name="";

        for (int i=0;i<section_2.length;i++) {
            name=get_question(""+section_2[i]);
            Log.e("DDDDHHHS","question="+name);


            if(name.startsWith("h")){

                Intent intent=new Intent(Page_I.this,Page_H.class);
                startActivity(intent);
                break;

            }else if(name.startsWith("g")){

                Intent intent=new Intent(Page_I.this,Page_G.class);
                startActivity(intent);
                break;
            }else {

                    Intent intent=new Intent(Page_I.this,Page_F.class);
                    startActivity(intent);
                }

        }
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

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sub_1){

            isInternetPresent = haveNetworkConnection(Page_I.this);
                if (isInternetPresent) {

                    pd.show();

                    if (validation()) {
                        insert_page_i();
                    }
                    else{
                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_I.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    pd.dismiss();
                    final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                    

                    final Dialog dialog = new Dialog(Page_I.this);
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
                                Toast.makeText(Page_I.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_I.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();


                }
            }
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

        AlertDialog.Builder builder = new AlertDialog.Builder(Page_I.this);
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

                if (ImageCheck.equals("2222")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("2222"));
                    if (ImageCheck.equals("2222")) {
                        Image_layout_Q2.setVisibility(View.VISIBLE);

                        try {
                            Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            bitmapImage2 = ((BitmapDrawable) Image_layout_Q2.getDrawable()).getBitmap();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q2);
                    } else {
                        Image_layout_Q2.setVisibility(View.GONE);
                        Image_layout_Q2.setImageBitmap(null);


                    }

                }

                if (ImageCheck.equals("3333")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("3333"));
                    if (ImageCheck.equals("3333")) {
                        Image_layout_Q3.setVisibility(View.VISIBLE);

                        try {
                            Image_layout_Q3.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            bitmapImage3 = ((BitmapDrawable) Image_layout_Q3.getDrawable()).getBitmap();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q3);
                    } else {
                        Image_layout_Q3.setVisibility(View.GONE);
                        Image_layout_Q3.setImageBitmap(null);


                    }

                }

                if (ImageCheck.equals("5555")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("5555"));
                    if (ImageCheck.equals("5555")) {
                        Image_layout_Q5.setVisibility(View.VISIBLE);

                        try {
                            Image_layout_Q5.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            bitmapImage5 = ((BitmapDrawable) Image_layout_Q5.getDrawable()).getBitmap();
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q5);
                    } else {
                        Image_layout_Q5.setVisibility(View.GONE);
                        Image_layout_Q5.setImageBitmap(null);
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

                    if (ImageCheck.equals("2222")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("2222"));
                        if (ImageCheck.equals("2222")) {


                            try {
                                Image_layout_Q2.setVisibility(View.VISIBLE);
                                Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage2 = ((BitmapDrawable) Image_layout_Q2.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {
                                Image_layout_Q2.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q2);
                        } else {


                            Image_layout_Q2.setVisibility(View.GONE);
                            Image_layout_Q2.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("3333")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("3333"));
                        if (ImageCheck.equals("3333")) {


                            try {
                                Image_layout_Q3.setVisibility(View.VISIBLE);
                                Image_layout_Q3.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage3 = ((BitmapDrawable) Image_layout_Q3.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {
                                Image_layout_Q3.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q3);
                        } else {


                            Image_layout_Q3.setVisibility(View.GONE);
                            Image_layout_Q3.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("5555")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("5555"));
                        if (ImageCheck.equals("5555")) {


                            try {
                                Image_layout_Q5.setVisibility(View.VISIBLE);
                                Image_layout_Q5.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage5 = ((BitmapDrawable) Image_layout_Q5.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {
                                Image_layout_Q5.setVisibility(View.GONE);
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q5);
                        } else {


                            Image_layout_Q5.setVisibility(View.GONE);
                            Image_layout_Q5.setImageBitmap(null);
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

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_9.php?main_id="+MSOT_Main.Main_ID ;

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
                                String et3 = c.getString( "et3" );
                                String et4 = c.getString( "et4" );
                                String et5 = c.getString( "et5" );
                                String pos_area = c.getString( "area" );
                                String pos_check1 = c.getString( "check1" );
                                String pos_check2 = c.getString( "check2" );
                                String pos_check3 = c.getString( "check3" );
                                String pos_check4 = c.getString( "check4" );
                                String pos_check5 = c.getString( "check5" );


                                if(pos_area.equalsIgnoreCase("")){


                                }else{
                                    edt1.setText(pos_area);
                                }

                                if(pos_check1.equalsIgnoreCase("")){


                                }else{
                                    checkbox_1.setChecked(true);
                                }

                                if(pos_check2.equalsIgnoreCase("")){


                                }else{
                                    checkbox_2.setChecked(true);
                                }

                                if(pos_check3.equalsIgnoreCase("")){


                                }else{
                                    checkbox_3.setChecked(true);
                                }


                                if(pos_check4.equalsIgnoreCase("")){


                                }else{
                                    checkbox_4.setChecked(true);
                                }

                                if(pos_check5.equalsIgnoreCase("")){


                                }else{
                                    checkbox_5.setChecked(true);
                                }


                                Log.e("PPOEEE","E2");

                                String cv_cmt1 = c.getString( "cmt1" );
                                String cv_cmt2 = c.getString( "cmt2" );
                                String cv_cmt3 = c.getString( "cmt3" );
                                String cv_cmt4 = c.getString( "cmt4" );
                                String cv_cmt5 = c.getString( "cmt5" );



                                Log.e("PPOEEE","E3");

                                String image_1 = c.getString( "image1" );
                                String image_2 = c.getString( "image3" );
                                String image_3 = c.getString( "image4" );
                                String image_5 = c.getString( "image5" );

                                Log.e("PPOEEE","E4");



                                if(et1.equalsIgnoreCase("Yes")) {
                                    ((RadioButton) rd1.getChildAt(0)).setChecked(true);

                                }
                                else if(et1.equalsIgnoreCase("No")){

                                    ((RadioButton) rd1.getChildAt(2)).setChecked(true);
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


                                if(et3.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd3.getChildAt(0)).setChecked(true);


                                }

                                else if(et3.equalsIgnoreCase("Partial")){

                                    ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                }
                                else if(et3.equalsIgnoreCase("No")){

                                    ((RadioButton) rd3.getChildAt(2)).setChecked(true);

                                }

                                else if(et3.equalsIgnoreCase("N/A")){

                                    ((RadioButton) rd3.getChildAt(3)).setChecked(true);

                                }

                                if(et4.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd4.getChildAt(0)).setChecked(true);


                                }
                                else if(et4.equalsIgnoreCase("Partial")){

                                    ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                }
                                else if(et4.equalsIgnoreCase("No")){

                                    ((RadioButton) rd4.getChildAt(2)).setChecked(true);

                                }

                                else if(et4.equalsIgnoreCase("N/A")){

                                    ((RadioButton) rd4.getChildAt(3)).setChecked(true);

                                }


                                if(et5.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd5.getChildAt(0)).setChecked(true);


                                }

                                else if(et5.equalsIgnoreCase("Partial")){

                                    ((RadioButton) rd5.getChildAt(1)).setChecked(true);

                                }
                                else if(et5.equalsIgnoreCase("No")){

                                    ((RadioButton) rd5.getChildAt(2)).setChecked(true);

                                }

                                else if(et5.equalsIgnoreCase("N/A")){

                                    ((RadioButton) rd5.getChildAt(3)).setChecked(true);

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
                                if(cv_cmt3.equalsIgnoreCase("null")){
                                    cmt3.setText("");
                                }else{
                                    cmt3.setText(cv_cmt3);
                                }

                                if(cv_cmt4.equalsIgnoreCase("null")){
                                    cmt4.setText("");
                                }else{
                                    cmt4.setText(cv_cmt4);
                                }
                                if(cv_cmt5.equalsIgnoreCase("null")){
                                    cmt5.setText("");
                                }else{
                                    cmt5.setText(cv_cmt5);
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


                                Log.e("PPOEEE","E10");

                                if(image_2.length()!=0){
                                    Image_2=image_2;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c1 = Base64.decode(image_2, Base64.DEFAULT);
                                Bitmap decodedByte_c1 = BitmapFactory.decodeByteArray(decodedString_c1, 0, decodedString_c1.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c1, 400, 400, false));
                                Image_layout_Q2.setVisibility(View.VISIBLE);

                                Log.e("PPOEEE","E11");
                                if(image_3.length()!=0){
                                    Image_3=image_3;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c3 = Base64.decode(image_3, Base64.DEFAULT);
                                Bitmap decodedByte_c3 = BitmapFactory.decodeByteArray(decodedString_c3, 0, decodedString_c3.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q3.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c3, 400, 400, false));
                                Image_layout_Q3.setVisibility(View.VISIBLE);

                                if(image_5.length()!=0){
                                    Image_5=image_5;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c4 = Base64.decode(image_5, Base64.DEFAULT);
                                Bitmap decodedByte_c4 = BitmapFactory.decodeByteArray(decodedString_c4, 0, decodedString_c4.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q5.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c4, 400, 400, false));
                                Image_layout_Q5.setVisibility(View.VISIBLE);

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

    public void insert_page_i(){
        cv_off.put(db.MAIN_ID, ""+Main_ID);
        cv_off.put(db.et1, ""+get_rd1);
        cv_off.put(db.et2, ""+get_rd2);
        cv_off.put(db.et3, ""+get_rd3);
        cv_off.put(db.et4, ""+get_rd4);
        cv_off.put(db.et5, ""+get_rd5);
        sd.insert(db.MSOT_PAGE_I_DB,null,cv_off);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"9");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"I1");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"9");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"I3");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"9");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"I4");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"9");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"I5");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);


    }

}

