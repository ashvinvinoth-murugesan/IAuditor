package com.rentokil.pci.rauditor.MSOT_NEW;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

public class Page_E extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, View.OnClickListener {


    SliderLayout mDemoSlider;

    String[] section_1={"f","g","h","i","j","k","l","m","n","o"};
    public static ExpandableHeightListView listview;
    String Image_1,Image_2,Image_3,Image_4,Image_5,Image_6,Image_7,Image_8,Image_9,Image_10,Image_11,Image_12,Image_13,Image_14,Image_16,Image_17,Image_18;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off,cv_off_img;

    Snackbar snackbar;

    EditText edt_type_wah,edt_height;

    LinearLayout lay_i_1;

    MyTextViewMSOT sub_page5,sub_2;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    ImageView draft;


    ArrayList<String> bitmapst = new ArrayList<String>();
    ImageView Image_layout_Q1, Image_layout_Q2, Image_layout_Q3, Image_layout_Q5, Image_layout_Q6, Image_layout_Q7
            , Image_layout_Q8, Image_layout_Q10, Image_layout_Q11, Image_layout_Q12, Image_layout_Q14,
            Image_layout_Q16,Image_layout_Q17,Image_layout_Q18;

    public static final int RequestPermissionCode = 1;
    String bitmapget1, bitmapget2,bitmapget3,bitmapget5,bitmapget6,bitmapget7,bitmapget8,bitmapget10,bitmapget11,bitmapget12,bitmapget14,bitmapget16,bitmapget17,bitmapget18;
    Bitmap bitmapImage1, bitmapImage2, bitmapImage3, bitmapImage5, bitmapImage6, bitmapImage7, bitmapImage8, bitmapImage10, bitmapImage11, bitmapImage12, bitmapImage14,bitmapImage16,bitmapImage17,bitmapImage18;
    public String Update_Status = "0";
    String ImageCheck = "";
    Uri uri;
    //   String bitmapst;
    String mCurrentPhotoPath,get_type,get_height;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    EditText input1,input2,input3,input4,input5,input6,input7,input8,input9,input10,input11,input12,input13,input14,input15,input16,input17,input18;
    String get_input1,get_input2,get_input3,get_input4,get_input5,get_input6,get_input7,get_input8,get_input9,get_input10,get_input11,get_input12,get_input13,get_input14,get_input15,get_input16,get_input17,get_input18;

    Boolean isInternetPresent = false;

    LinearLayout get_layout1,get_layout2,get_layout3,get_layout4,get_layout5;

    LinearLayout inlay_4a,inlay_4b,inlay_4c,inlay_4d,inlay_5a,inlay_5b,inlay_5c,inlay_6a,inlay_7a,inlay_7b,inlay_7c;

    TextView img_toast1,img_toast2,img_toast3,img_toast5,img_toast7,img_toast8,img_toast10,img_toast12;

    RadioGroup rd1,rd2,rd3,rd4,rd5,rd6,rd7,rd8,rd9,rd10,rd11,rd12,rd13,rd14,rd15,rd16,rd17,rd18;

    String get_rd1="",get_rd2="",get_rd3="",get_rd4="",get_rd5="",get_rd6="",get_rd7="",get_rd8="",get_rd9="",get_rd10="",get_rd11="",get_rd12="",get_rd13="",get_rd14="",get_rd15="",get_rd16="",get_rd17="",get_rd18="";

    TextView addcmt1,addcmt2,addcmt3,addcmt5,addcmt6,addcmt7,addcmt8,addcmt10,addcmt11,addcmt12,addcmt14,addcmt16,addcmt17,addcmt18
            ,cmt1,cmt2,cmt3,cmt5,cmt6,cmt7,cmt8,cmt10,cmt11,cmt12,cmt14,cmt16,cmt17,cmt18;

    String get_cmt1,get_cmt2,get_cmt3,get_cmt5,get_cmt6,get_cmt7,get_cmt8,get_cmt10,get_cmt11,get_cmt12,get_cmt14,get_cmt16,get_cmt17,get_cmt18;


    CoordinatorLayout coordinatorLayout;

    private android.app.AlertDialog pd;
    String Main_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_e);

        db = new DatabaseHelper(Page_E.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();
        cv_off_img = new ContentValues();


        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        rd1=(RadioGroup) findViewById(R.id.rd1);
        rd2=(RadioGroup) findViewById(R.id.rd2);
        rd3=(RadioGroup) findViewById(R.id.rd3);
        rd4=(RadioGroup) findViewById(R.id.rd4);
        rd5=(RadioGroup) findViewById(R.id.rd5);
        rd6=(RadioGroup) findViewById(R.id.rd6);
        rd7=(RadioGroup) findViewById(R.id.rd7);
        rd8=(RadioGroup) findViewById(R.id.rd8);
        rd9=(RadioGroup) findViewById(R.id.rd9);
        rd10=(RadioGroup) findViewById(R.id.rd10);
        rd11=(RadioGroup) findViewById(R.id.rd11);
        rd12=(RadioGroup) findViewById(R.id.rd12);
        rd13=(RadioGroup) findViewById(R.id.rd13);
        rd14=(RadioGroup) findViewById(R.id.rd14);
        rd15=(RadioGroup) findViewById(R.id.rd15);
        rd16=(RadioGroup) findViewById(R.id.rd16);
        rd17=(RadioGroup) findViewById(R.id.rd17);
        rd18=(RadioGroup) findViewById(R.id.rd18);

        edt_type_wah=(EditText) findViewById(R.id.edt_type_wah);
        edt_height=(EditText) findViewById(R.id.edt_height);

        lay_i_1=(LinearLayout) findViewById(R.id.lay_i_1);

        lay_i_1.setVisibility(View.GONE);


        addcmt1=(TextView) findViewById(R.id.addcmt1);
        addcmt2=(TextView) findViewById(R.id.addcmt2);
        addcmt3=(TextView) findViewById(R.id.addcmt3);
        addcmt5=(TextView) findViewById(R.id.addcmt5);
        addcmt6=(TextView) findViewById(R.id.addcmt6);
        addcmt7=(TextView) findViewById(R.id.addcmt7);
        addcmt8=(TextView) findViewById(R.id.addcmt8);
        addcmt10=(TextView) findViewById(R.id.addcmt10);
        addcmt11=(TextView) findViewById(R.id.addcmt11);
        addcmt12=(TextView) findViewById(R.id.addcmt12);
        addcmt14=(TextView) findViewById(R.id.addcmt14);
        addcmt16=(TextView) findViewById(R.id.addcmt16);
        addcmt17=(TextView) findViewById(R.id.addcmt17);
        addcmt18=(TextView) findViewById(R.id.addcmt18);

        cmt1=(TextView) findViewById(R.id.cmt1);
        cmt2=(TextView) findViewById(R.id.cmt2);
        cmt3=(TextView) findViewById(R.id.cmt3);
        cmt5=(TextView) findViewById(R.id.cmt5);
        cmt6=(TextView) findViewById(R.id.cmt6);
        cmt7=(TextView) findViewById(R.id.cmt7);
        cmt8=(TextView) findViewById(R.id.cmt8);
        cmt10=(TextView) findViewById(R.id.cmt10);
        cmt11=(TextView) findViewById(R.id.cmt11);
        cmt12=(TextView) findViewById(R.id.cmt12);
        cmt14=(TextView) findViewById(R.id.cmt14);
        cmt16=(TextView) findViewById(R.id.cmt16);
        cmt17=(TextView) findViewById(R.id.cmt17);
        cmt18=(TextView) findViewById(R.id.cmt18);




        img_toast1=(TextView) findViewById(R.id.img_toast1);
        img_toast2=(TextView) findViewById(R.id.img_toast2);
        img_toast3=(TextView) findViewById(R.id.img_toast3);
        img_toast5=(TextView) findViewById(R.id.img_toast5);
        img_toast7=(TextView) findViewById(R.id.img_toast7);
        img_toast8=(TextView) findViewById(R.id.img_toast8);
        img_toast10=(TextView) findViewById(R.id.img_toast10);
        img_toast12=(TextView) findViewById(R.id.img_toast12);



        pd = new SpotsDialog(Page_E.this, R.style.Custom);


        get_layout1=(LinearLayout) findViewById(R.id.get_layout1);
        get_layout2=(LinearLayout) findViewById(R.id.get_layout2);
        get_layout3=(LinearLayout) findViewById(R.id.get_layout3);
        get_layout4=(LinearLayout) findViewById(R.id.get_layout4);
        get_layout5=(LinearLayout) findViewById(R.id.get_layout5);


        inlay_4a=(LinearLayout) findViewById(R.id.inlay_4a);
        inlay_4b=(LinearLayout) findViewById(R.id.inlay_4b);
        inlay_4c=(LinearLayout) findViewById(R.id.inlay_4c);
        inlay_4d=(LinearLayout) findViewById(R.id.inlay_4d);

        inlay_5a=(LinearLayout) findViewById(R.id.inlay_5a);
        inlay_5b=(LinearLayout) findViewById(R.id.inlay_5b);
        inlay_5c=(LinearLayout) findViewById(R.id.inlay_5c);

        inlay_6a=(LinearLayout) findViewById(R.id.inlay_6a);

        inlay_7a=(LinearLayout) findViewById(R.id.inlay_7a);
        inlay_7b=(LinearLayout) findViewById(R.id.inlay_7b);
        inlay_7c=(LinearLayout) findViewById(R.id.inlay_7c);




        inlay_4a.setVisibility(View.GONE);
        inlay_4b.setVisibility(View.GONE);
        inlay_4c.setVisibility(View.GONE);
        inlay_4d.setVisibility(View.GONE);
        inlay_5a.setVisibility(View.GONE);
        inlay_5b.setVisibility(View.GONE);
        inlay_5c.setVisibility(View.GONE);
        inlay_6a.setVisibility(View.GONE);
        inlay_7a.setVisibility(View.GONE);
        inlay_7b.setVisibility(View.GONE);
        inlay_7c.setVisibility(View.GONE);





        get_layout2.setVisibility(View.GONE);
        get_layout3.setVisibility(View.GONE);
        get_layout4.setVisibility(View.GONE);
        get_layout5.setVisibility(View.GONE);


        Intent intent1=getIntent();
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

        Log.e("CCCCQW","e4");

        if(get_question_int("e4")!=0){

            get_layout2.setVisibility(View.VISIBLE);



        }if(get_question_int("e5")!=0){

            get_layout3.setVisibility(View.VISIBLE);

        }if(get_question_int("e6")!=0){

            get_layout4.setVisibility(View.VISIBLE);

        }if(get_question_int("e7")!=0){

            get_layout5.setVisibility(View.VISIBLE);
        }

        TextView Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);


        TextView Q2_Imageview = (TextView) findViewById(R.id.image_Q2);
        Image_layout_Q2 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q2);
        Image_layout_Q2.setVisibility(View.GONE);

        TextView Q3_Imageview = (TextView) findViewById(R.id.image_Q3);
        Image_layout_Q3 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q3);
        Image_layout_Q3.setVisibility(View.GONE);

        TextView Q5_Imageview = (TextView) findViewById(R.id.image_Q5);
        Image_layout_Q5 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q5);
        Image_layout_Q5.setVisibility(View.GONE);

        TextView Q6_Imageview = (TextView) findViewById(R.id.image_Q6);
        Image_layout_Q6 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q6);
        Image_layout_Q6.setVisibility(View.GONE);

        TextView Q7_Imageview = (TextView) findViewById(R.id.image_Q7);
        Image_layout_Q7 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q7);
        Image_layout_Q7.setVisibility(View.GONE);

        TextView Q8_Imageview = (TextView) findViewById(R.id.image_Q8);
        Image_layout_Q8 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q8);
        Image_layout_Q8.setVisibility(View.GONE);

        TextView Q10_Imageview = (TextView) findViewById(R.id.image_Q10);
        Image_layout_Q10 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q10);
        Image_layout_Q10.setVisibility(View.GONE);

        TextView Q11_Imageview = (TextView) findViewById(R.id.image_Q11);
        Image_layout_Q11 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q11);
        Image_layout_Q11.setVisibility(View.GONE);

        TextView Q12_Imageview = (TextView) findViewById(R.id.image_Q12);
        Image_layout_Q12 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q12);
        Image_layout_Q12.setVisibility(View.GONE);

        TextView Q14_Imageview = (TextView) findViewById(R.id.image_Q14);
        Image_layout_Q14 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q14);
        Image_layout_Q14.setVisibility(View.GONE);

        TextView Q16_Imageview = (TextView) findViewById(R.id.image_Q16);
        Image_layout_Q16 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q16);
        Image_layout_Q16.setVisibility(View.GONE);

        TextView Q17_Imageview = (TextView) findViewById(R.id.image_Q17);
        Image_layout_Q17 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q17);
        Image_layout_Q17.setVisibility(View.GONE);

        TextView Q18_Imageview = (TextView) findViewById(R.id.image_Q18);
        Image_layout_Q18 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q18);
        Image_layout_Q18.setVisibility(View.GONE);


        Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
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

        Image_layout_Q6.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q6.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q6.getDrawable() == null) {
                                    Image_layout_Q6.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q6.setVisibility(View.VISIBLE);
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

        Q6_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Image_layout_Q6.getDrawable() == null) {
                    ImageCheck = "6666";
                    selectImage();
                }
            }
        });


        Image_layout_Q7.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q7.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q7.getDrawable() == null) {
                                    Image_layout_Q7.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q7.setVisibility(View.VISIBLE);
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

        Q7_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Image_layout_Q7.getDrawable() == null) {
                    ImageCheck = "7777";
                    selectImage();
                }
            }
        });

        Image_layout_Q8.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q8.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q8.getDrawable() == null) {
                                    Image_layout_Q8.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q8.setVisibility(View.VISIBLE);
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

        Q8_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Image_layout_Q8.getDrawable() == null) {
                    ImageCheck = "8888";
                    selectImage();
                }
            }
        });

        Image_layout_Q10.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q10.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q10.getDrawable() == null) {
                                    Image_layout_Q10.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q10.setVisibility(View.VISIBLE);
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

        Q10_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Image_layout_Q10.getDrawable() == null) {
                    ImageCheck = "1010";
                    selectImage();
                }
            }
        });

        Image_layout_Q11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q11.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q11.getDrawable() == null) {
                                    Image_layout_Q11.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q11.setVisibility(View.VISIBLE);
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

        Q11_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (Image_layout_Q11.getDrawable() == null) {
                    ImageCheck = "1011";
                    selectImage();
                }
            }
        });

        Image_layout_Q12.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q12.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q12.getDrawable() == null) {
                                    Image_layout_Q12.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q12.setVisibility(View.VISIBLE);
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

        Q12_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q12.getDrawable() == null) {
                    ImageCheck = "1012";
                    selectImage();
                }
            }
        });

        Image_layout_Q14.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q14.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q14.getDrawable() == null) {
                                    Image_layout_Q14.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q14.setVisibility(View.VISIBLE);
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

        Q14_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q14.getDrawable() == null) {
                    ImageCheck = "1014";
                    selectImage();
                }
            }
        });

        Image_layout_Q16.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q16.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q16.getDrawable() == null) {
                                    Image_layout_Q16.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q16.setVisibility(View.VISIBLE);
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

        Q16_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q16.getDrawable() == null) {
                    ImageCheck = "1016";
                    selectImage();
                }
            }
        });

        Image_layout_Q17.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q17.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q17.getDrawable() == null) {
                                    Image_layout_Q17.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q17.setVisibility(View.VISIBLE);
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

        Q17_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q17.getDrawable() == null) {
                    ImageCheck = "1017";
                    selectImage();
                }
            }
        });

        Image_layout_Q18.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setMessage("Are You Sure Want to Delete this Image?");
                builder.setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Image_layout_Q18.setImageResource(0);

                                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                                if (Image_layout_Q18.getDrawable() == null) {
                                    Image_layout_Q18.setVisibility(View.GONE);
                                } else {
                                    Image_layout_Q18.setVisibility(View.VISIBLE);
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

        Q18_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (Image_layout_Q18.getDrawable() == null) {
                    ImageCheck = "1018";
                    selectImage();
                }
            }
        });


        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();

                if(get_rd1.equalsIgnoreCase("Yes")){

                    lay_i_1.setVisibility(View.VISIBLE);
                }
                else{

                    lay_i_1.setVisibility(View.GONE);
                }


            }
        });


        rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();


            }
        });

        rd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();


               if (get_rd4.equalsIgnoreCase("Yes")){

                   inlay_4a.setVisibility(View.VISIBLE);
                   inlay_4b.setVisibility(View.VISIBLE);
                   inlay_4c.setVisibility(View.VISIBLE);
                   inlay_4d.setVisibility(View.VISIBLE);

               }

               else if(get_rd4.equalsIgnoreCase("No")){
                   inlay_4a.setVisibility(View.GONE);
                   inlay_4b.setVisibility(View.GONE);
                   inlay_4c.setVisibility(View.GONE);
                   inlay_4d.setVisibility(View.GONE);
               }

            }
        });

        rd5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd6 = ((RadioButton) findViewById(rd6.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd7 = ((RadioButton) findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd8 = ((RadioButton) findViewById(rd8.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd9 = ((RadioButton) findViewById(rd9.getCheckedRadioButtonId())).getText().toString();


                if (get_rd9.equalsIgnoreCase("Yes")){

                    inlay_5a.setVisibility(View.VISIBLE);
                    inlay_5b.setVisibility(View.VISIBLE);
                    inlay_5c.setVisibility(View.VISIBLE);

                }

                else if(get_rd9.equalsIgnoreCase("No")){

                    inlay_5a.setVisibility(View.GONE);
                    inlay_5b.setVisibility(View.GONE);
                    inlay_5c.setVisibility(View.GONE);
                }
            }
        });

        rd10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd10 = ((RadioButton) findViewById(rd10.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd11 = ((RadioButton) findViewById(rd11.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd12 = ((RadioButton) findViewById(rd12.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd13 = ((RadioButton) findViewById(rd13.getCheckedRadioButtonId())).getText().toString();

                if (get_rd13.equalsIgnoreCase("Yes")){

                    inlay_6a.setVisibility(View.VISIBLE);

                }

                else if(get_rd13.equalsIgnoreCase("No")){

                    inlay_6a.setVisibility(View.GONE);

                }
            }
        });

        rd14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd14 = ((RadioButton) findViewById(rd14.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd15 = ((RadioButton) findViewById(rd15.getCheckedRadioButtonId())).getText().toString();

                if (get_rd15.equalsIgnoreCase("Yes")){

                    inlay_7a.setVisibility(View.VISIBLE);
                    inlay_7b.setVisibility(View.VISIBLE);
                    inlay_7c.setVisibility(View.VISIBLE);

                }

                else if(get_rd15.equalsIgnoreCase("No")){

                    inlay_7a.setVisibility(View.GONE);
                    inlay_7b.setVisibility(View.GONE);
                    inlay_7c.setVisibility(View.GONE);
                }
            }
        });

        rd16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd16 = ((RadioButton) findViewById(rd16.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd17 = ((RadioButton) findViewById(rd17.getCheckedRadioButtonId())).getText().toString();
            }
        });

        rd18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd18 = ((RadioButton) findViewById(rd18.getCheckedRadioButtonId())).getText().toString();
            }
        });

        addcmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


                input1 = new EditText(Page_E.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input2 = new EditText(Page_E.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input3 = new EditText(Page_E.this);
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
        addcmt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input5 = new EditText(Page_E.this);
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
        addcmt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input6 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input6.setLayoutParams(lp);
                builder.setView(input6);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt6.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input6.getText().toString()+"</font></font> "));


                        String get_comment6 = cmt6.getText().toString();

                        if(get_comment6.equalsIgnoreCase("Remarks: ")){

                            cmt6.setText("");

                        }
                    }
                });
                builder.show();
            }
        });

        addcmt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input7 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input7.setLayoutParams(lp);
                builder.setView(input7);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt7.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input7.getText().toString()+"</font></font> "));


                        String get_comment7 = cmt7.getText().toString();

                        if(get_comment7.equalsIgnoreCase("Remarks: ")){

                            cmt7.setText("");

                        }
                    }
                });
                builder.show();
            }
        });
        addcmt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input8 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input8.setLayoutParams(lp);
                builder.setView(input8);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt8.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input8.getText().toString()+"</font></font> "));


                        String get_comment8 = cmt8.getText().toString();

                        if(get_comment8.equalsIgnoreCase("Remarks: ")){

                            cmt8.setText("");

                        }
                    }
                });
                builder.show();
            }
        });

        addcmt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input10 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input10.setLayoutParams(lp);
                builder.setView(input10);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt10.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input10.getText().toString()+"</font></font> "));

                        String get_comment10 = cmt10.getText().toString();

                        if(get_comment10.equalsIgnoreCase("Remarks: ")){

                            cmt10.setText("");

                        }
                    }
                });
                builder.show();
            }
        });
        addcmt11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input11 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input11.setLayoutParams(lp);
                builder.setView(input11);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt11.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input11.getText().toString()+"</font></font> "));


                        String get_comment11 = cmt11.getText().toString();

                        if(get_comment11.equalsIgnoreCase("Remarks: ")){

                            cmt11.setText("");

                        }
                    }
                });
                builder.show();
            }
        });

        addcmt12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input12 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input12.setLayoutParams(lp);
                builder.setView(input12);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt12.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input12.getText().toString()+"</font></font> "));


                        String get_comment12 = cmt12.getText().toString();

                        if(get_comment12.equalsIgnoreCase("Remarks: ")){

                            cmt12.setText("");

                        }
                    }
                });
                builder.show();
            }
        });
        addcmt14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input14 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input14.setLayoutParams(lp);
                builder.setView(input14);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt14.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input14.getText().toString()+"</font></font> "));


                        String get_comment14 = cmt14.getText().toString();

                        if(get_comment14.equalsIgnoreCase("Remarks: ")){

                            cmt14.setText("");

                        }
                    }
                });
                builder.show();
            }
        });
        addcmt16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input16 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input16.setLayoutParams(lp);
                builder.setView(input16);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt16.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input16.getText().toString()+"</font></font> "));


                        String get_comment16 = cmt16.getText().toString();

                        if(get_comment16.equalsIgnoreCase("Remarks: ")){

                            cmt16.setText("");

                        }
                    }
                });
                builder.show();
            }
        });
        addcmt17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input17 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input17.setLayoutParams(lp);
                builder.setView(input17);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt17.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input17.getText().toString()+"</font></font> "));


                        String get_comment17 = cmt17.getText().toString();

                        if(get_comment17.equalsIgnoreCase("Remarks: ")){

                            cmt17.setText("");

                        }
                    }
                });
                builder.show();
            }
        });
        addcmt18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input18 = new EditText(Page_E.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input18.setLayoutParams(lp);
                builder.setView(input18);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt18.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input18.getText().toString()+"</font></font> "));


                        String get_comment18 = cmt18.getText().toString();

                        if(get_comment18.equalsIgnoreCase("Remarks: ")){

                            cmt18.setText("");

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
                        .make(coordinatorLayout, "Examples of Working At Height: termite/ rodent/ bait inspections at roof void or ceiling from a ladder, servicing air freshener or insect light trap from a ladder, bird proofing work on boom lift/ scaffold/ cherry lift/ man lift, bird control work via abseiling/ rope access, WAH during fumigation of stack or silo", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(12);
                snackbar.setDuration(10000);

                snackbar.show();

            }
        });

        img_toast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar
                        .make(coordinatorLayout, "For examples: training on safe ladder use, roof void safety, crawl board use, operations of boom lift, scissor lift, use of harness & lifeline, rope access/ abseiling, WAH during fumigation of stack or silo.", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(8);
                snackbar.setDuration(10000);

                snackbar.show();

            }
        });

        img_toast3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG);

                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

                TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                textView.setVisibility(View.INVISIBLE);
                textView.setMaxLines(14);


                View snackView = getLayoutInflater().inflate(R.layout.page_e_snackbar, null);

                layout.setPadding(0,0,0,0);

                layout.addView(snackView, 0);
                snackbar.setDuration(10000);
                snackbar.show();

            }
        });

        img_toast5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG);

                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

                TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                textView.setVisibility(View.INVISIBLE);
                textView.setMaxLines(14);


                View snackView = getLayoutInflater().inflate(R.layout.my_snackbar, null);

                layout.setPadding(0,0,0,0);

                layout.addView(snackView, 0);
                snackbar.setDuration(10000);
                snackbar.show();

            }
        });



        img_toast7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar
                        .make(coordinatorLayout, "No placing of ladder on staircase/ uneven floor. Obstructions which prevent A-ladder from being fully spread out should be removed, or other equipment should be considered (such as single ladder, pole cam, grab & reach tool).", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);

                snackbar.setDuration(10000);
                snackbar.show();
            }
        });

        img_toast8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG);
                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
                TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                textView.setVisibility(View.INVISIBLE);
                textView.setMaxLines(14);
                View snackView = getLayoutInflater().inflate(R.layout.page_e_1_snackbar, null);
                layout.setPadding(0,0,0,0);
                layout.addView(snackView, 0);
                snackbar.setDuration(10000);
                snackbar.show();
            }
        });

        img_toast10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar
                        .make(coordinatorLayout, "Guideline: Installation at not more than 2m above ground is recommended. This is to minimize the risks of falling from height and allow staff to use the standard, approved ladder from RI", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);
                snackbar.setDuration(10000);

                snackbar.show();
            }
        });

        img_toast12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar
                        .make(coordinatorLayout, "Look out for any installation above doorway, seating area, above toilet seat, diaper changing area, work station etc.", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);

                snackbar.setDuration(10000);
                snackbar.show();
            }
        });






        isInternetPresent = haveNetworkConnection(Page_E.this);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        draft=(ImageView) findViewById(R.id.draft);

        draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_E.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        insert_page_e();
                    }
                    else{

                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_E.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_E.this);
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
                                Toast.makeText(Page_E.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_E.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




            }
        });


        sub_page5=(MyTextViewMSOT) findViewById(R.id.sub_1);
        sub_2=(MyTextViewMSOT) findViewById(R.id.sub_2);

        sub_page5.setOnClickListener(this);

        sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isInternetPresent = haveNetworkConnection(Page_E.this);
                if (isInternetPresent) {


                    pd.show();


                    Log.e("EERRS","get_rd1 = "+get_rd1);
                    Log.e("EERRS","get_rd2 = "+get_rd2);
                    Log.e("EERRS","get_rd3 = "+get_rd3);
                    Log.e("EERRS","get_rd4 = "+get_rd4);
                    Log.e("EERRS","get_rd5 = "+get_rd5);
                    Log.e("EERRS","get_rd6 = "+get_rd6);
                    Log.e("EERRS","get_rd7 = "+get_rd7);
                    Log.e("EERRS","get_rd8 = "+get_rd8);
                    Log.e("EERRS","get_rd9 = "+get_rd9);
                    Log.e("EERRS","get_rd10 = "+get_rd10);
                    Log.e("EERRS","get_rd11 = "+get_rd11);
                    Log.e("EERRS","get_rd12 = "+get_rd12);
                    Log.e("EERRS","get_rd13 = "+get_rd13);
                    Log.e("EERRS","get_rd14 = "+get_rd14);
                    Log.e("EERRS","get_rd15 = "+get_rd15);
                    Log.e("EERRS","get_rd16 = "+get_rd16);
                    Log.e("EERRS","get_rd17 = "+get_rd17);
                    Log.e("EERRS","get_rd18 = "+get_rd18);


                    if (validation()) {
                        Log.e("GGGG", "validation");
                        insert_page_e();
                    } else {
                        pd.dismiss();
                        Log.e("GGGG", "else");

                        Toast.makeText(Page_E.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    pd.dismiss();
                    final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                    final Dialog dialog = new Dialog(Page_E.this);
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
                                Toast.makeText(Page_E.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_E.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
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
    public void onSliderClick(BaseSliderView slider) {

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




    private void submitres(final String saveid) {

        sd.delete(db.MSOT_TABLE, null, null);


        Log.e("GGGG", "sub");
        isInternetPresent = haveNetworkConnection(Page_E.this);
        if (isInternetPresent) {


//            get_rd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
//            get_rd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
//            get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
//            get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
//            get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();
//            get_rd6 = ((RadioButton) findViewById(rd6.getCheckedRadioButtonId())).getText().toString();
//            get_rd7 = ((RadioButton) findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
//            get_rd8 = ((RadioButton) findViewById(rd8.getCheckedRadioButtonId())).getText().toString();
//            get_rd9 = ((RadioButton) findViewById(rd9.getCheckedRadioButtonId())).getText().toString();
//            get_rd10 = ((RadioButton) findViewById(rd10.getCheckedRadioButtonId())).getText().toString();
//            get_rd11 = ((RadioButton) findViewById(rd11.getCheckedRadioButtonId())).getText().toString();
//            get_rd12 = ((RadioButton) findViewById(rd12.getCheckedRadioButtonId())).getText().toString();
//            get_rd13 = ((RadioButton) findViewById(rd13.getCheckedRadioButtonId())).getText().toString();
//            get_rd14 = ((RadioButton) findViewById(rd14.getCheckedRadioButtonId())).getText().toString();
//            get_rd15 = ((RadioButton) findViewById(rd15.getCheckedRadioButtonId())).getText().toString();
//            get_rd16 = ((RadioButton) findViewById(rd16.getCheckedRadioButtonId())).getText().toString();
//            get_rd17 = ((RadioButton) findViewById(rd17.getCheckedRadioButtonId())).getText().toString();
//            get_rd18 = ((RadioButton) findViewById(rd18.getCheckedRadioButtonId())).getText().toString();
//
//            get_cmt1=cmt1.getText().toString();
//            get_cmt2=cmt2.getText().toString();
//            get_cmt3=cmt3.getText().toString();
//            get_cmt5=cmt5.getText().toString();
//            get_cmt6=cmt6.getText().toString();
//            get_cmt7=cmt7.getText().toString();
//            get_cmt8=cmt8.getText().toString();
//            get_cmt10=cmt10.getText().toString();
//            get_cmt11=cmt11.getText().toString();
//            get_cmt12=cmt12.getText().toString();
//            get_cmt14=cmt14.getText().toString();
//            get_cmt16=cmt16.getText().toString();
//            get_cmt17=cmt17.getText().toString();
//            get_cmt18=cmt18.getText().toString();


            get_rd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            get_rd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            get_rd3 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();

            if(get_question_int("e4")!=0) {

                get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();

                if(get_rd4.equalsIgnoreCase("Yes")) {


                    get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();

                    get_rd6 = ((RadioButton) findViewById(rd6.getCheckedRadioButtonId())).getText().toString();

                    get_rd7 = ((RadioButton) findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
                    get_rd8 = ((RadioButton) findViewById(rd8.getCheckedRadioButtonId())).getText().toString();

                }
                else if(get_rd4.equalsIgnoreCase("No")){

                    get_rd5="";
                    get_rd6="";
                    get_rd7="";
                    get_rd8="";


                }
            }


            if(get_question_int("e5")!=0) {

                get_rd9 = ((RadioButton) findViewById(rd9.getCheckedRadioButtonId())).getText().toString();

                if(get_rd9.equalsIgnoreCase("Yes")) {

                    get_rd10 = ((RadioButton) findViewById(rd10.getCheckedRadioButtonId())).getText().toString();

                    get_rd11 = ((RadioButton) findViewById(rd11.getCheckedRadioButtonId())).getText().toString();

                    get_rd12 = ((RadioButton) findViewById(rd12.getCheckedRadioButtonId())).getText().toString();

                }

                else  if(get_rd9.equalsIgnoreCase("No")) {
                    get_rd10="";
                    get_rd11="";
                    get_rd12="";


                }
            }


            if(get_question_int("e6")!=0) {


                get_rd13 = ((RadioButton) findViewById(rd13.getCheckedRadioButtonId())).getText().toString();


                if(get_rd13.equalsIgnoreCase("Yes")) {


                    get_rd14 = ((RadioButton) findViewById(rd14.getCheckedRadioButtonId())).getText().toString();

                }
                else{
                    get_rd14="";
                }
            }

            if(get_question_int("e7")!=0) {


                get_rd15 = ((RadioButton) findViewById(rd15.getCheckedRadioButtonId())).getText().toString();


                if(get_rd15.equalsIgnoreCase("Yes")){

                    get_rd16 = ((RadioButton) findViewById(rd16.getCheckedRadioButtonId())).getText().toString();

                    get_rd17 = ((RadioButton) findViewById(rd17.getCheckedRadioButtonId())).getText().toString();

                    get_rd18 = ((RadioButton) findViewById(rd18.getCheckedRadioButtonId())).getText().toString();

                }
                else if (get_rd15.equalsIgnoreCase("No")){

                    get_rd16="";
                    get_rd17="";
                    get_rd18="";

                }
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

            if (input5!=null) {
                get_input5 = input5.getText().toString();
            }else {
                get_input5="";
            }
            if (input6!=null) {
                get_input6 = input6.getText().toString();
            }else {
                get_input6="";
            }
            if (input7!=null) {
                get_input7 = input7.getText().toString();
            }else {
                get_input7="";
            }
            if (input8!=null) {
                get_input8 = input8.getText().toString();
            }else {
                get_input8="";
            }

            if (input10!=null) {
                get_input10 = input10.getText().toString();
            }else {
                get_input10="";
            }
            if (input11!=null) {
                get_input11 = input11.getText().toString();
            }else {
                get_input11="";
            }

            if (input12!=null) {
                get_input12 = input12.getText().toString();
            }else {
                get_input12="";
            }


            if (input14!=null) {
                get_input14 = input14.getText().toString();
            }else {
                get_input14="";
            }

            if (input16!=null) {
                get_input16 = input16.getText().toString();
            }else {
                get_input16="";
            }

            if (input17!=null) {
                get_input17 = input17.getText().toString();
            }else {
                get_input17="";
            }


            if (input18!=null) {
                get_input18 = input18.getText().toString();
            }else {
                get_input18="";
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

            if (bitmapImage6!=null) {
                bitmapget6=getStringImage(bitmapImage6);
            }else {
                bitmapget6="";
            }

            if (bitmapImage7!=null) {
                bitmapget7=getStringImage(bitmapImage7);
            }else {
                bitmapget7="";
            }

            if (bitmapImage8!=null) {
                bitmapget8=getStringImage(bitmapImage8);
            }else {
                bitmapget8="";
            }

            if (bitmapImage10!=null) {
                bitmapget10=getStringImage(bitmapImage10);
            }else {
                bitmapget10="";
            }

            if (bitmapImage11!=null) {
                bitmapget11=getStringImage(bitmapImage11);
            }else {
                bitmapget11="";
            }

            if (bitmapImage12!=null) {
                bitmapget12=getStringImage(bitmapImage12);
            }else {
                bitmapget12="";
            }


            if (bitmapImage14!=null) {
                bitmapget14=getStringImage(bitmapImage14);
            }else {
                bitmapget14="";
            }

            if (bitmapImage16!=null) {
                bitmapget16=getStringImage(bitmapImage16);
            }else {
                bitmapget16="";
            }

            if (bitmapImage17!=null) {
                bitmapget17=getStringImage(bitmapImage17);
            }else {
                bitmapget17="";
            }

            if (bitmapImage18!=null) {
                bitmapget18=getStringImage(bitmapImage18);
            }else {
                bitmapget18="";
            }


            if (edt_type_wah!=null) {
                get_type = edt_type_wah.getText().toString();
            }else {
                get_type="";
            }


            if (edt_height!=null) {
                get_height = edt_height.getText().toString();
            }else {
                get_height="";
            }





            Map<String, String> params = new HashMap<String, String>();
            params.put("pos_et1", get_rd1);
            params.put("pos_et2", get_rd2);
            params.put("pos_et3", get_rd3);
            params.put("pos_et4", get_rd4);
            params.put("pos_et5", get_rd5);
            params.put("pos_et6", get_rd6);
            params.put("pos_et7", get_rd7);
            params.put("pos_et8", get_rd8);
            params.put("pos_et9", get_rd9);
            params.put("pos_et10", get_rd10);
            params.put("pos_et11", get_rd11);
            params.put("pos_et12", get_rd12);
            params.put("pos_et13", get_rd13);
            params.put("pos_et14", get_rd14);
            params.put("pos_et15", get_rd15);
            params.put("pos_et16", get_rd16);
            params.put("pos_et17", get_rd17);
            params.put("pos_et18", get_rd18);
            params.put("pos_type", get_type);
            params.put("pos_height", get_height);


            params.put("add_com1", get_input1);
            params.put("add_com2", get_input2);
            params.put("add_com3", get_input3);
            params.put("add_com4", get_input5);
            params.put("add_com5", get_input6);
            params.put("add_com6", get_input7);
            params.put("add_com7", get_input8);
            params.put("add_com8", get_input10);
            params.put("add_com9", get_input11);
            params.put("add_com10", get_input12);
            params.put("add_com11", get_input14);
            params.put("add_com12", get_input16);
            params.put("add_com13", get_input17);
            params.put("add_com14", get_input18);

            params.put("getimage1", bitmapget1);
            params.put("getimage2", bitmapget2);
            params.put("getimage3", bitmapget3);
            params.put("getimage4", bitmapget5);
            params.put("getimage5", bitmapget6);
            params.put("getimage6", bitmapget7);
            params.put("getimage7", bitmapget8);
            params.put("getimage8", bitmapget10);
            params.put("getimage9", bitmapget11);
            params.put("getimage10", bitmapget12);
            params.put("getimage11", bitmapget14);
            params.put("getimage12", bitmapget16);
            params.put("getimage13", bitmapget17);
            params.put("getimage14", bitmapget18);
            params.put("main_id", MSOT_Main.Main_ID);

            Log.e("CCDD","param = "+params);

            VolleyDataRequester.withDefaultHttps(this)
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_5.php")
                    .setBody(params)
                    .setMethod(VolleyDataRequester.Method.POST)
                    .setStringResponseListener(new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                Log.e("GGGG", "e1");

                                cv1.put(db.STATUS, "2");
                                sd.insert(db.MSOT_TABLE, null, cv1);

                                try {

                                    pd.dismiss();


                                    if(saveid.equalsIgnoreCase("save")){


                                        final AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
                                        builder.setMessage("Are You Sure Want to Save & Exit");
                                        builder.setPositiveButton("YES",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        Intent intent=new Intent(Page_E.this, Category_Type_Activity.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                pd.dismiss();
                                                dialog.dismiss();

                                                Intent intent = new Intent(Page_E.this, Page_F.class);
                                                startActivity(intent);




                                            }
                                        });
                                        builder.setCancelable(false);
                                        builder.show();

                                    }
                                    else if(saveid.equalsIgnoreCase("pos")) {

                                        pd.dismiss();
                                        Intent intent = new Intent(Page_E.this, Page_F.class);
                                        startActivity(intent);
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

                                    Log.e("YYYYYYY index er",""+e.getMessage());
                                    e.printStackTrace();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .setResponseErrorListener(new VolleyDataRequester.ResponseErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("DDDDDD", "error  = " + error.getMessage());
                            pd.dismiss();
                            try {
                                if(error.getMessage().equalsIgnoreCase("null")){
                                    Intent intent = new Intent(Page_E.this, Page_F.class);
                                    startActivity(intent);

                                }
                            } catch (Exception e) {
                                Intent intent = new Intent(Page_E.this, Page_F.class);
                                startActivity(intent);
                                e.printStackTrace();
                            }

                        }
                    })
                    .requestString();


        } else {

            final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

            final Dialog dialog = new Dialog(Page_E.this);
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
                        Toast.makeText(Page_E.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Page_E.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();


        }


    }

    public boolean validation() {
         boolean check_point=false;





        try {

            get_rd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
            get_rd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            get_rd3 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();

            if(get_question_int("e4")!=0) {

                get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();

                if(get_rd4.equalsIgnoreCase("Yes")) {


                    get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();

                    get_rd6 = ((RadioButton) findViewById(rd6.getCheckedRadioButtonId())).getText().toString();

                    get_rd7 = ((RadioButton) findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
                    get_rd8 = ((RadioButton) findViewById(rd8.getCheckedRadioButtonId())).getText().toString();

                }
                else if(get_rd4.equalsIgnoreCase("No")){

                    get_rd5="";
                    get_rd6="";
                    get_rd7="";
                    get_rd8="";


                }
            }


            if(get_question_int("e5")!=0) {

                get_rd9 = ((RadioButton) findViewById(rd9.getCheckedRadioButtonId())).getText().toString();

                if(get_rd9.equalsIgnoreCase("Yes")) {

                    get_rd10 = ((RadioButton) findViewById(rd10.getCheckedRadioButtonId())).getText().toString();

                    get_rd11 = ((RadioButton) findViewById(rd11.getCheckedRadioButtonId())).getText().toString();

                    get_rd12 = ((RadioButton) findViewById(rd12.getCheckedRadioButtonId())).getText().toString();

                }

                else  if(get_rd9.equalsIgnoreCase("No")) {
                    get_rd10="";
                    get_rd11="";
                    get_rd12="";


                }
            }


            if(get_question_int("e6")!=0) {


                get_rd13 = ((RadioButton) findViewById(rd13.getCheckedRadioButtonId())).getText().toString();


                if(get_rd13.equalsIgnoreCase("Yes")) {


                    get_rd14 = ((RadioButton) findViewById(rd14.getCheckedRadioButtonId())).getText().toString();

                }
                else{

                    get_rd14="";
                }
            }

            if(get_question_int("e7")!=0) {


                get_rd15 = ((RadioButton) findViewById(rd15.getCheckedRadioButtonId())).getText().toString();


                if(get_rd15.equalsIgnoreCase("Yes")){

                    get_rd16 = ((RadioButton) findViewById(rd16.getCheckedRadioButtonId())).getText().toString();

                    get_rd17 = ((RadioButton) findViewById(rd17.getCheckedRadioButtonId())).getText().toString();

                    get_rd18 = ((RadioButton) findViewById(rd18.getCheckedRadioButtonId())).getText().toString();

                }
                else if (get_rd15.equalsIgnoreCase("No")){

                    get_rd16="";
                    get_rd17="";
                    get_rd18="";

                }
            }

            Log.e("BBBBP","get_rd1 = "+get_rd1);
            Log.e("BBBBP","get_rd2 = "+get_rd2);
            Log.e("BBBBP","get_rd3 = "+get_rd3);
            Log.e("BBBBP","get_rd4 = "+get_rd4);
            Log.e("BBBBP","get_rd5 = "+get_rd5);
            Log.e("BBBBP","get_rd6 = "+get_rd6);
            Log.e("BBBBP","get_rd7 = "+get_rd7);
            Log.e("BBBBP","get_rd8 = "+get_rd8);
            Log.e("BBBBP","get_rd9 = "+get_rd9);
            Log.e("BBBBP","get_rd10 = "+get_rd10);
            Log.e("BBBBP","get_rd11 = "+get_rd11);
            Log.e("BBBBP","get_rd12 = "+get_rd12);
            Log.e("BBBBP","get_rd13 = "+get_rd13);
            Log.e("BBBBP","get_rd14 = "+get_rd14);
            Log.e("BBBBP","get_rd15 = "+get_rd15);
            Log.e("BBBBP","get_rd16 = "+get_rd16);
            Log.e("BBBBP","get_rd17 = "+get_rd17);
            Log.e("BBBBP","get_rd18 = "+get_rd18);


           if(get_rd1.length()!=0&&get_rd2.length()!=0&&get_rd3.length()!=0){
               try {
                   if( get_question_int("e4")==0){
                        check_point=true;

                   }else {
                       if(get_rd4.equalsIgnoreCase("Yes")){
                           if (get_rd5.length()!=0&&get_rd6.length()!=0&&get_rd7.length()!=0&&get_rd8.length()!=0){
                               check_point=true;
                           }else {
                               check_point=false;
                           }

                       }else {
                           check_point=true;
                       }

                   }
               } catch (Exception e) {

                   e.printStackTrace();
                   return false;
               }
               try {
                   if( get_question_int("e5")==0){
                       check_point=true;

                   }else {
                       if(get_rd9.equalsIgnoreCase("Yes")){
                           if (get_rd10.length()!=0&&get_rd11.length()!=0&&get_rd12.length()!=0){
                               check_point=true;
                           }else {
                               check_point=false;
                           }

                       }else {
                           check_point=true;
                       }

                   }
               } catch (Exception e) {

                   e.printStackTrace();
                   return  false;
               }
               try {
                   if( get_question_int("e6")==0){
                       check_point=true;

                   }else {
                       if(get_rd13.equalsIgnoreCase("Yes")){
                           if (get_rd14.length()!=0){
                               check_point=true;
                           }else {
                               check_point=false;
                           }

                       }else {
                           check_point=true;
                       }

                   }
               } catch (Exception e) {

                   e.printStackTrace();
                   return  false;
               }
               try {
                   if( get_question_int("e7")==0){
                       check_point=true;

                   }else {
                       if(get_rd15.equalsIgnoreCase("Yes")){
                           if (get_rd16.length()!=0&&get_rd17.length()!=0&&get_rd18.length()!=0){
                               check_point=true;
                           }else {
                               check_point=false;
                           }

                       }else {
                           check_point=true;
                       }

                   }
               } catch (Exception e) {

                   e.printStackTrace();
                   return  false;
               }
           }else {
               return false;
           }


        } catch (Exception e) {
            Log.e("ERRRRRR",""+e.getMessage());
            e.printStackTrace();
            return false;
        }
         return check_point;
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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sub_1) {


            isInternetPresent = haveNetworkConnection(Page_E.this);
            if (isInternetPresent) {


                pd.show();

                if (validation()) {
                    insert_page_e();
                } else {
                    pd.dismiss();
                    Log.e("GGGG", "else");

                    Toast.makeText(Page_E.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }


            } else {
                pd.dismiss();
                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                final Dialog dialog = new Dialog(Page_E.this);
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
                            Toast.makeText(Page_E.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Toast.makeText(Page_E.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                dialog.show();


            }


        }
    }


        public String get_question (String ques){


            String Question_Name = "";
            Cursor c6;
            c6 = sd.rawQuery("Select * from " + db.MSOT_QUESTION_ID_TABLE + " where MAIN_ID = '" + db.get_main_id(sd) + "' AND QUESTION_ID like '%" + ques + "%'", null);
            c6.moveToFirst();


            if (c6.getCount() != 0) {

                Question_Name = c6.getString(c6.getColumnIndex(db.QUESTION_ID));

            }
            Log.e("SDSDSDS", ques + "\tLLLLLL\t" + c6.getCount());
            return Question_Name;
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

            AlertDialog.Builder builder = new AlertDialog.Builder(Page_E.this);
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

                    if (ImageCheck.equals("6666")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("6666"));
                        if (ImageCheck.equals("6666")) {
                            Image_layout_Q6.setVisibility(View.VISIBLE);

                            try {

                                Image_layout_Q6.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage6 = ((BitmapDrawable) Image_layout_Q6.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q6);
                        } else {
                            Image_layout_Q6.setVisibility(View.GONE);
                            Image_layout_Q6.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("7777")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("7777"));
                        if (ImageCheck.equals("7777")) {

                            Image_layout_Q7.setVisibility(View.VISIBLE);
                            try {

                                Image_layout_Q7.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage7 = ((BitmapDrawable) Image_layout_Q7.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q7);
                        } else {
                            Image_layout_Q7.setVisibility(View.GONE);
                            Image_layout_Q7.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("8888")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("8888"));
                        if (ImageCheck.equals("8888")) {

                            Image_layout_Q8.setVisibility(View.VISIBLE);
                            try {

                                Image_layout_Q8.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage8 = ((BitmapDrawable) Image_layout_Q8.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q8);
                        } else {
                            Image_layout_Q8.setVisibility(View.GONE);
                            Image_layout_Q8.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("1010")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1010"));
                        if (ImageCheck.equals("1010")) {
                            Image_layout_Q10.setVisibility(View.VISIBLE);

                            try {

                                Image_layout_Q10.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage10 = ((BitmapDrawable) Image_layout_Q10.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q10);
                        } else {
                            Image_layout_Q10.setVisibility(View.GONE);
                            Image_layout_Q10.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("1011")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1011"));
                        if (ImageCheck.equals("1011")) {
                            Image_layout_Q11.setVisibility(View.VISIBLE);

                            try {
                                Image_layout_Q11.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage11 = ((BitmapDrawable) Image_layout_Q11.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q11);
                        } else {
                            Image_layout_Q11.setVisibility(View.GONE);
                            Image_layout_Q11.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("1012")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1012"));
                        if (ImageCheck.equals("1012")) {
                            Image_layout_Q12.setVisibility(View.VISIBLE);

                            try {
                                Image_layout_Q12.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage12 = ((BitmapDrawable) Image_layout_Q12.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q12);
                        } else {
                            Image_layout_Q12.setVisibility(View.GONE);
                            Image_layout_Q12.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("1014")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1014"));
                        if (ImageCheck.equals("1014")) {
                            Image_layout_Q14.setVisibility(View.VISIBLE);

                            try {
                                Image_layout_Q14.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage14 = ((BitmapDrawable) Image_layout_Q14.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q14);
                        } else {
                            Image_layout_Q14.setVisibility(View.GONE);
                            Image_layout_Q14.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("1016")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1016"));
                        if (ImageCheck.equals("1016")) {
                            Image_layout_Q16.setVisibility(View.VISIBLE);

                            try {
                                Image_layout_Q16.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage16 = ((BitmapDrawable) Image_layout_Q16.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q16);
                        } else {
                            Image_layout_Q16.setVisibility(View.GONE);
                            Image_layout_Q16.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("1017")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1017"));
                        if (ImageCheck.equals("1017")) {
                            Image_layout_Q17.setVisibility(View.VISIBLE);

                            try {
                                Image_layout_Q17.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage17 = ((BitmapDrawable) Image_layout_Q17.getDrawable()).getBitmap();
                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q17);
                        } else {
                            Image_layout_Q17.setVisibility(View.GONE);
                            Image_layout_Q17.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("1018")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1018"));
                        if (ImageCheck.equals("1018")) {
                            Image_layout_Q18.setVisibility(View.VISIBLE);

                            try {

                                Image_layout_Q18.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                bitmapImage18 = ((BitmapDrawable) Image_layout_Q18.getDrawable()).getBitmap();

                            } catch (NullPointerException e) {

                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q18);
                        } else {
                            Image_layout_Q18.setVisibility(View.GONE);
                            Image_layout_Q18.setImageBitmap(null);
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
                        Log.e("EEETT ERR","error = "+e1.getMessage());
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

                        if (ImageCheck.equals("6666")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("6666"));
                            if (ImageCheck.equals("6666")) {


                                try {
                                    Image_layout_Q6.setVisibility(View.VISIBLE);
                                    Image_layout_Q6.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage6 = ((BitmapDrawable) Image_layout_Q6.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q6.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q6);
                            } else {


                                Image_layout_Q6.setVisibility(View.GONE);
                                Image_layout_Q6.setImageBitmap(null);
                            }
                        }


                        if (ImageCheck.equals("7777")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("7777"));
                            if (ImageCheck.equals("7777")) {


                                try {
                                    Image_layout_Q7.setVisibility(View.VISIBLE);
                                    Image_layout_Q7.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage7 = ((BitmapDrawable) Image_layout_Q7.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q7.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q7);
                            } else {


                                Image_layout_Q7.setVisibility(View.GONE);
                                Image_layout_Q7.setImageBitmap(null);
                            }
                        }

                        if (ImageCheck.equals("8888")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("8888"));
                            if (ImageCheck.equals("8888")) {


                                try {
                                    Image_layout_Q8.setVisibility(View.VISIBLE);
                                    Image_layout_Q8.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage8 = ((BitmapDrawable) Image_layout_Q8.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q8.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q8);
                            } else {


                                Image_layout_Q8.setVisibility(View.GONE);
                                Image_layout_Q8.setImageBitmap(null);
                            }
                        }

                        if (ImageCheck.equals("1010")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("1010"));
                            if (ImageCheck.equals("1010")) {


                                try {
                                    Image_layout_Q10.setVisibility(View.VISIBLE);
                                    Image_layout_Q10.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage10 = ((BitmapDrawable) Image_layout_Q10.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q10.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q10);
                            } else {


                                Image_layout_Q10.setVisibility(View.GONE);
                                Image_layout_Q10.setImageBitmap(null);
                            }
                        }


                        if (ImageCheck.equals("1011")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("1011"));
                            if (ImageCheck.equals("1011")) {


                                try {
                                    Image_layout_Q11.setVisibility(View.VISIBLE);
                                    Image_layout_Q11.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage11 = ((BitmapDrawable) Image_layout_Q11.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q11.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q11);
                            } else {


                                Image_layout_Q11.setVisibility(View.GONE);
                                Image_layout_Q11.setImageBitmap(null);
                            }
                        }

                        if (ImageCheck.equals("1012")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("1012"));
                            if (ImageCheck.equals("1012")) {


                                try {
                                    Image_layout_Q12.setVisibility(View.VISIBLE);
                                    Image_layout_Q12.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage12 = ((BitmapDrawable) Image_layout_Q12.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q12.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q12);
                            } else {


                                Image_layout_Q12.setVisibility(View.GONE);
                                Image_layout_Q12.setImageBitmap(null);
                            }
                        }

                        if (ImageCheck.equals("1014")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("1014"));
                            if (ImageCheck.equals("1014")) {


                                try {
                                    Image_layout_Q14.setVisibility(View.VISIBLE);
                                    Image_layout_Q14.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage14 = ((BitmapDrawable) Image_layout_Q14.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q14.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q14);
                            } else {


                                Image_layout_Q14.setVisibility(View.GONE);
                                Image_layout_Q14.setImageBitmap(null);
                            }
                        }


                        if (ImageCheck.equals("1016")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("1016"));
                            if (ImageCheck.equals("1016")) {


                                try {
                                    Image_layout_Q16.setVisibility(View.VISIBLE);
                                    Image_layout_Q16.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage16 = ((BitmapDrawable) Image_layout_Q16.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q16.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q14);
                            } else {


                                Image_layout_Q16.setVisibility(View.GONE);
                                Image_layout_Q16.setImageBitmap(null);
                            }
                        }

                        if (ImageCheck.equals("1017")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("1017"));
                            if (ImageCheck.equals("1017")) {


                                try {
                                    Image_layout_Q17.setVisibility(View.VISIBLE);
                                    Image_layout_Q17.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage17 = ((BitmapDrawable) Image_layout_Q17.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {
                                    Image_layout_Q17.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q17);
                            } else {


                                Image_layout_Q17.setVisibility(View.GONE);
                                Image_layout_Q17.setImageBitmap(null);
                            }
                        }

                        if (ImageCheck.equals("1018")) {
                            Log.e("ImageCheck11", "" + ImageCheck.equals("1018"));
                            if (ImageCheck.equals("1018")) {


                                try {
                                    Image_layout_Q18.setVisibility(View.VISIBLE);
                                    Image_layout_Q18.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                                    bitmapImage18 = ((BitmapDrawable) Image_layout_Q18.getDrawable()).getBitmap();
                                } catch (NullPointerException e) {

                                    Image_layout_Q18.setVisibility(View.GONE);
                                    e.printStackTrace();
                                }
                                Log.e("IMAGEEEE", "" + Image_layout_Q18);
                            } else {


                                Image_layout_Q18.setVisibility(View.GONE);
                                Image_layout_Q18.setImageBitmap(null);
                            }
                        }
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }


            }
        }

        @Override
        public void onRequestPermissionsResult ( int RC, String per[],int[] PResult){

            switch (RC) {

                case RequestPermissionCode:

                    if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {


                    } else {

                    }
                    break;
            }

        }

        public int get_question_int (String ques){


            String Question_Name = "";
            Cursor c6;
            c6 = sd.rawQuery("Select * from " + db.MSOT_QUESTION_ID_TABLE + " where MAIN_ID = '" + db.get_main_id(sd) + "' AND QUESTION_ID like '%" + ques + "%'", null);
            c6.moveToFirst();


            if (c6.getCount() != 0) {

                Question_Name = c6.getString(c6.getColumnIndex(db.QUESTION_ID));

            }
            Log.e("SDSDSDS", ques + "\tLLLLLL\t" + c6.getCount());
            return c6.getCount();
        }

    @Override
    public void onBackPressed(){

        Intent intent = new Intent(Page_E.this, Page_D.class);
        startActivity(intent);

    }


    private void get_LIST_Data(String key_id){

        Log.e("SSSXX","3rd ="+key_id);
        Log.e("MMBVC",key_id);
        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("PPPQQQ",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_5.php?main_id="+MSOT_Main.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("DDSSEEE","response = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String et1 = c.getString( "et1" );

                                String et2 = c.getString( "et2" );
                                String et3 = c.getString( "et3" );
                                String et4 = c.getString( "et4" );

                                String et5 = c.getString( "et5" );


                                String et6 = c.getString( "et6" );


                                String et7 = c.getString( "et7" );
                                String et8 = c.getString( "et8" );
                                String et9 = c.getString( "et9" );
                                String et10 = c.getString( "et10" );
                                String et11 = c.getString( "et11" );
                                String et12 = c.getString( "et12" );
                                String et13 = c.getString( "et13" );


                                String et14 = c.getString( "et14" );
                                String et15 = c.getString( "et15" );
                                String et16 = c.getString( "et16" );
                                String et17 = c.getString( "et17" );
                                String et18 = c.getString( "et18" );

                                String type = c.getString( "type" );
                                String height = c.getString( "height" );


                                String cv_cmt1 = c.getString( "cmt1" );
                                String cv_cmt2 = c.getString( "cmt2" );
                                String cv_cmt3 = c.getString( "cmt3" );
                                String cv_cmt5 = c.getString( "cmt4" );
                                String cv_cmt6 = c.getString( "cmt5" );
                                String cv_cmt7 = c.getString( "cmt6" );
                                String cv_cmt8 = c.getString( "cmt7" );
                                String cv_cmt9 = c.getString( "cmt8" );
                                String cv_cmt10 = c.getString( "cmt9" );
                                String cv_cmt11 = c.getString( "cmt10" );
                                String cv_cmt12 = c.getString( "cmt11" );
                                String cv_cmt13 = c.getString( "cmt12" );
                                String cv_cmt14 = c.getString( "cmt13" );
                                String cv_cmt15 = c.getString( "cmt14" );




                                String image_1 = c.getString( "image1" );
                                String image_2 = c.getString( "image2" );
                                String image_3 = c.getString( "image3" );
                                String image_5 = c.getString( "image4" );
                                String image_6 = c.getString( "image5" );
                                String image_7 = c.getString( "image6" );
                                String image_8 = c.getString( "image7" );
                                String image_10 = c.getString( "image8" );
                                String image_11 = c.getString( "image9" );
                                String image_12 = c.getString( "image10" );
                                String image_14 = c.getString( "image11" );
                                String image_16 = c.getString( "image12" );
                                String image_17 = c.getString( "image13" );
                                String image_18 = c.getString( "image14" );





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
                                else if(et4.equalsIgnoreCase("No")){

                                    ((RadioButton) rd4.getChildAt(2)).setChecked(true);

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




                                if(et6.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd6.getChildAt(0)).setChecked(true);
                                }
                                else if(et6.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd6.getChildAt(1)).setChecked(true);
                                }
                                else if(et6.equalsIgnoreCase("No")){
                                    ((RadioButton) rd6.getChildAt(2)).setChecked(true);
                                }
                                else if(et6.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd6.getChildAt(3)).setChecked(true);
                                }


                                if(et7.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd7.getChildAt(0)).setChecked(true);
                                }
                                else if(et7.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd7.getChildAt(1)).setChecked(true);
                                }
                                else if(et7.equalsIgnoreCase("No")){
                                    ((RadioButton) rd7.getChildAt(2)).setChecked(true);
                                }
                                else if(et7.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd7.getChildAt(3)).setChecked(true);
                                }

                                if(et8.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd8.getChildAt(0)).setChecked(true);
                                }
                                else if(et8.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd8.getChildAt(1)).setChecked(true);
                                }
                                else if(et8.equalsIgnoreCase("No")){
                                    ((RadioButton) rd8.getChildAt(2)).setChecked(true);
                                }
                                else if(et8.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd8.getChildAt(3)).setChecked(true);
                                }


                                if(et9.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd9.getChildAt(0)).setChecked(true);
                                }
                                else if(et9.equalsIgnoreCase("No")){
                                    ((RadioButton) rd9.getChildAt(2)).setChecked(true);
                                }



                                if(et10.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd10.getChildAt(0)).setChecked(true);
                                }
                                else if(et10.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd10.getChildAt(1)).setChecked(true);
                                }
                                else if(et10.equalsIgnoreCase("No")){
                                    ((RadioButton) rd10.getChildAt(2)).setChecked(true);
                                }
                                else if(et10.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd10.getChildAt(3)).setChecked(true);
                                }


                                if(et11.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd11.getChildAt(0)).setChecked(true);
                                }
                                else if(et11.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd11.getChildAt(1)).setChecked(true);
                                }
                                else if(et11.equalsIgnoreCase("No")){
                                    ((RadioButton) rd11.getChildAt(2)).setChecked(true);
                                }
                                else if(et11.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd11.getChildAt(3)).setChecked(true);
                                }

                                if(et12.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd12.getChildAt(0)).setChecked(true);
                                }
                                else if(et12.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd12.getChildAt(1)).setChecked(true);
                                }
                                else if(et12.equalsIgnoreCase("No")){
                                    ((RadioButton) rd12.getChildAt(2)).setChecked(true);
                                }
                                else if(et12.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd12.getChildAt(3)).setChecked(true);
                                }


                                if(et13.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd13.getChildAt(0)).setChecked(true);
                                }

                                else if(et13.equalsIgnoreCase("No")){
                                    ((RadioButton) rd13.getChildAt(2)).setChecked(true);
                                }


                                if(et14.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd14.getChildAt(0)).setChecked(true);
                                }
                                else if(et14.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd14.getChildAt(1)).setChecked(true);
                                }
                                else if(et14.equalsIgnoreCase("No")){
                                    ((RadioButton) rd14.getChildAt(2)).setChecked(true);
                                }
                                else if(et14.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd14.getChildAt(3)).setChecked(true);
                                }

                                if(et15.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd15.getChildAt(0)).setChecked(true);
                                }
                                else if(et15.equalsIgnoreCase("No")){
                                    ((RadioButton) rd15.getChildAt(2)).setChecked(true);
                                }


                                if(et16.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd16.getChildAt(0)).setChecked(true);
                                }
                                else if(et16.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd16.getChildAt(1)).setChecked(true);
                                }
                                else if(et16.equalsIgnoreCase("No")){
                                    ((RadioButton) rd16.getChildAt(2)).setChecked(true);
                                }
                                else if(et16.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd16.getChildAt(3)).setChecked(true);
                                }

                                if(et17.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd17.getChildAt(0)).setChecked(true);
                                }
                                else if(et17.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd17.getChildAt(1)).setChecked(true);
                                }
                                else if(et17.equalsIgnoreCase("No")){
                                    ((RadioButton) rd17.getChildAt(2)).setChecked(true);
                                }
                                else if(et17.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd17.getChildAt(3)).setChecked(true);
                                }

                                if(et18.equalsIgnoreCase("Yes")){
                                    ((RadioButton) rd18.getChildAt(0)).setChecked(true);
                                }
                                else if(et18.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd18.getChildAt(1)).setChecked(true);
                                }
                                else if(et18.equalsIgnoreCase("No")){
                                    ((RadioButton) rd18.getChildAt(2)).setChecked(true);
                                }
                                else if(et18.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd18.getChildAt(3)).setChecked(true);
                                }


                                if(type.equalsIgnoreCase("null")){
                                    edt_type_wah.setText("");
                                }else{
                                    edt_type_wah.setText(type);
                                }

                                if(height.equalsIgnoreCase("null")){
                                    edt_height.setText("");
                                }else{
                                    edt_height.setText(height);
                                }



                                if(cv_cmt1.equalsIgnoreCase("null")){
                                    cmt1.setText("");
                                }else{
                                    cmt1.setText(cv_cmt1);
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
                                if(cv_cmt3.equalsIgnoreCase("null")){
                                    cmt3.setText("");
                                }else{
                                    cmt3.setText(cv_cmt3);
                                }
                                if(cv_cmt5.equalsIgnoreCase("null")){
                                    cmt5.setText("");
                                }else{
                                    cmt5.setText(cv_cmt5);
                                }
                                if(cv_cmt6.equalsIgnoreCase("null")){
                                    cmt6.setText("");
                                }else{
                                    cmt6.setText(cv_cmt6);
                                }
                                if(cv_cmt7.equalsIgnoreCase("null")){
                                    cmt7.setText("");
                                }else{
                                    cmt7.setText(cv_cmt7);
                                }

                                if(cv_cmt8.equalsIgnoreCase("null")){
                                    cmt8.setText("");
                                }else{
                                    cmt8.setText(cv_cmt8);
                                }

                                if(cv_cmt9.equalsIgnoreCase("null")){
                                    cmt10.setText("");
                                }else{
                                    cmt10.setText(cv_cmt9);
                                }

                                if(cv_cmt10.equalsIgnoreCase("null")){
                                    cmt11.setText("");
                                }else{
                                    cmt11.setText(cv_cmt10);
                                }

                                if(cv_cmt11.equalsIgnoreCase("null")){
                                    cmt12.setText("");
                                }else{
                                    cmt12.setText(cv_cmt11);
                                }

                                if(cv_cmt12.equalsIgnoreCase("null")){
                                    cmt14.setText("");
                                }else{
                                    cmt14.setText(cv_cmt12);
                                }

                                if(cv_cmt13.equalsIgnoreCase("null")){
                                    cmt16.setText("");
                                }else{
                                    cmt16.setText(cv_cmt13);
                                }

                                if(cv_cmt14.equalsIgnoreCase("null")){
                                    cmt17.setText("");
                                }else{
                                    cmt17.setText(cv_cmt14);
                                }
                                if(cv_cmt15.equalsIgnoreCase("null")){
                                    cmt18.setText("");
                                }else{
                                    cmt18.setText(cv_cmt15);
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


                                if(image_6.length()!=0){
                                    Image_6=image_6;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c5 = Base64.decode(image_6, Base64.DEFAULT);
                                Bitmap decodedByte_c5 = BitmapFactory.decodeByteArray(decodedString_c5, 0, decodedString_c5.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q6.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c5, 400, 400, false));
                                Image_layout_Q6.setVisibility(View.VISIBLE);



                                if(image_7.length()!=0){
                                    Image_7=image_7;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c6 = Base64.decode(image_7, Base64.DEFAULT);
                                Bitmap decodedByte_c6 = BitmapFactory.decodeByteArray(decodedString_c6, 0, decodedString_c6.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q7.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c6, 400, 400, false));
                                Image_layout_Q7.setVisibility(View.VISIBLE);



                                if(image_8.length()!=0){
                                    Image_8=image_7;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c7 = Base64.decode(image_8, Base64.DEFAULT);
                                Bitmap decodedByte_c7 = BitmapFactory.decodeByteArray(decodedString_c7, 0, decodedString_c7.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q8.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c7, 400, 400, false));
                                Image_layout_Q8.setVisibility(View.VISIBLE);



                                if(image_10.length()!=0){
                                    Image_10=image_10;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c10 = Base64.decode(image_10, Base64.DEFAULT);
                                Bitmap decodedByte_c10 = BitmapFactory.decodeByteArray(decodedString_c10, 0, decodedString_c10.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q10.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c10, 400, 400, false));
                                Image_layout_Q10.setVisibility(View.VISIBLE);


                                if(image_11.length()!=0){
                                    Image_11=image_11;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c11 = Base64.decode(image_11, Base64.DEFAULT);
                                Bitmap decodedByte_c11 = BitmapFactory.decodeByteArray(decodedString_c11, 0, decodedString_c11.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q11.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c11, 400, 400, false));
                                Image_layout_Q11.setVisibility(View.VISIBLE);

                                if(image_12.length()!=0){
                                    Image_12=image_12;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c12 = Base64.decode(image_12, Base64.DEFAULT);
                                Bitmap decodedByte_c12 = BitmapFactory.decodeByteArray(decodedString_c12, 0, decodedString_c12.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q12.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c12, 400, 400, false));
                                Image_layout_Q12.setVisibility(View.VISIBLE);

                                if(image_14.length()!=0){
                                    Image_14=image_14;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c14 = Base64.decode(image_14, Base64.DEFAULT);
                                Bitmap decodedByte_c14 = BitmapFactory.decodeByteArray(decodedString_c14, 0, decodedString_c14.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q14.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c14, 400, 400, false));
                                Image_layout_Q14.setVisibility(View.VISIBLE);


                                if(image_16.length()!=0){
                                    Image_16=image_16;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c16 = Base64.decode(image_16, Base64.DEFAULT);
                                Bitmap decodedByte_c16 = BitmapFactory.decodeByteArray(decodedString_c16, 0, decodedString_c14.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q16.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c16, 400, 400, false));
                                Image_layout_Q16.setVisibility(View.VISIBLE);

                                if(image_17.length()!=0){
                                    Image_17=image_17;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }


                                byte[] decodedString_c17 = Base64.decode(image_17, Base64.DEFAULT);
                                Bitmap decodedByte_c17 = BitmapFactory.decodeByteArray(decodedString_c17, 0, decodedString_c17.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q17.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c17, 400, 400, false));
                                Image_layout_Q17.setVisibility(View.VISIBLE);


                                if(image_18.length()!=0){
                                    Image_18=image_18;
                                    //Customer_Signature_IM.setImageDrawable(null);
                                }

                                byte[] decodedString_c18 = Base64.decode(image_18, Base64.DEFAULT);
                                Bitmap decodedByte_c18 = BitmapFactory.decodeByteArray(decodedString_c18, 0, decodedString_c18.length);
                                //   imageView1.setImageBitmap(decodedByte_c);
                                Image_layout_Q18.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c18, 400, 400, false));
                                Image_layout_Q18.setVisibility(View.VISIBLE);


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
    public void insert_page_e(){

        cv_off.put(db.MAIN_ID, ""+Main_ID);
        cv_off.put(db.et1, ""+get_rd1);
        cv_off.put(db.et2, ""+get_rd2);
        cv_off.put(db.et3, ""+get_rd3);
        cv_off.put(db.et4, ""+get_rd4);
        cv_off.put(db.et5, ""+get_rd5);
        cv_off.put(db.et6, ""+get_rd6);
        cv_off.put(db.et7, ""+get_rd7);
        cv_off.put(db.et8, ""+get_rd8);
        cv_off.put(db.et9, ""+get_rd9);
        cv_off.put(db.et10, ""+get_rd10);
        cv_off.put(db.et11, ""+get_rd11);
        cv_off.put(db.et12, ""+get_rd12);
        cv_off.put(db.et13, ""+get_rd13);
        cv_off.put(db.et14, ""+get_rd14);
        cv_off.put(db.et15, ""+get_rd15);
        cv_off.put(db.et16, ""+get_rd16);
        cv_off.put(db.et17, ""+get_rd17);
        cv_off.put(db.et18, ""+get_rd18);
        sd.insert(db.MSOT_PAGE_E_DB,null,cv_off);
        cv_off_img.clear();

        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E1");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);
        cv_off_img.clear();

        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E2");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E3");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E4-A");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E4-B");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E4-C");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E4-D");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E5-A");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E5-B");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E5-C");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E6-A");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);


        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E7-A");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E7-B");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"5");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"E7-C");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);


    }
    public void get_off_b(String Main_ID ){
        String et1,et2,et3,et4,et5,et6,et7,et8,et9,et10,et11,et12,et13,et14,et15,et16,et17;
        String Query="select * from "+db.MSOT_PAGE_E_DB +" where MAIN_ID = '" +Main_ID+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        et1=cursor.getString(cursor.getColumnIndex(db.et1));
        et2=cursor.getString(cursor.getColumnIndex(db.et2));
        et3=cursor.getString(cursor.getColumnIndex(db.et3));
        et4=cursor.getString(cursor.getColumnIndex(db.et4));
        et5=cursor.getString(cursor.getColumnIndex(db.et5));
        et6=cursor.getString(cursor.getColumnIndex(db.et6));
        et7=cursor.getString(cursor.getColumnIndex(db.et7));
        et8=cursor.getString(cursor.getColumnIndex(db.et4));
        et9=cursor.getString(cursor.getColumnIndex(db.et4));
        et10=cursor.getString(cursor.getColumnIndex(db.et4));
        et11=cursor.getString(cursor.getColumnIndex(db.et4));
        et12=cursor.getString(cursor.getColumnIndex(db.et4));
        et13=cursor.getString(cursor.getColumnIndex(db.et4));
        et14=cursor.getString(cursor.getColumnIndex(db.et4));

    }


}
