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

public class Page_H extends AppCompatActivity implements BaseSliderView.OnSliderClickListener , View.OnClickListener{


    SliderLayout mDemoSlider;

    Snackbar snackbar;

    ImageView draft;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    String Image_1,Image_2,Image_3,Image_4,Image_5,Image_6,Image_7,Image_8,Image_9,Image_10;

    public static ExpandableHeightListView listview;

    String[] section_1={"i","j","k","l","m","n","o"};
    String[] section_2={"g","f"};

    LinearLayout get_layout1,get_layout2,get_layout3,get_layout4;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off,cv_off_img;

    EditText input1,input2,input3,input4,input5,input6,input7,input8,input9,input10;
    String get_input1,get_input2,get_input3,get_input4,get_input5,get_input6,get_input7,get_input8,get_input9,get_input10;

    public static final int RequestPermissionCode = 1;
    ImageView Image_layout_Q1, Image_layout_Q2, Image_layout_Q3, Image_layout_Q5, Image_layout_Q6, Image_layout_Q7
            , Image_layout_Q8, Image_layout_Q10;
    String bitmapget1, bitmapget2,bitmapget3,bitmapget5,bitmapget6,bitmapget7,bitmapget8,bitmapget10;
    Bitmap bitmapImage1, bitmapImage2, bitmapImage3, bitmapImage5, bitmapImage6, bitmapImage7, bitmapImage8, bitmapImage10;
    public String Update_Status = "0";
    String ImageCheck = "";
    Uri uri;
    //   String bitmapst;
    String mCurrentPhotoPath;
    Boolean isInternetPresent = false;
    String Main_ID;

    TextView img_toast1,img_toast2,img_toast3,img_toast4;
    RadioGroup rd1,rd2,rd3,rd4,rd5,rd6,rd7,rd8,rd9,rd10;
    String get_rd1="",get_rd2="",get_rd3="",get_rd4="",get_rd5="",get_rd6="",get_rd7="",get_rd8="",get_rd9="",get_rd10="";
    TextView addcmt1,addcmt2,addcmt3,addcmt4,addcmt5,addcmt6,addcmt7,addcmt8,addcmt9,addcmt10;
    String get_cmt1,get_cmt2,get_cmt3,get_cmt4,get_cmt5,get_cmt6,get_cmt7,get_cmt8,get_cmt9,get_cmt10;

    TextView cmt1,cmt2,cmt3,cmt4,cmt5,cmt6,cmt7,cmt8,cmt9,cmt10;
    CoordinatorLayout coordinatorLayout;



    MyTextViewMSOT sub_1,sub_2;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    private android.app.AlertDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_h);

        db = new DatabaseHelper(Page_H.this);
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
        pd = new SpotsDialog(Page_H.this, R.style.Custom);

        addcmt1=(TextView) findViewById(R.id.addcmt1);
        addcmt2=(TextView) findViewById(R.id.addcmt2);
        addcmt3=(TextView) findViewById(R.id.addcmt3);
        addcmt4=(TextView) findViewById(R.id.addcmt4);
        addcmt5=(TextView) findViewById(R.id.addcmt5);
        addcmt6=(TextView) findViewById(R.id.addcmt6);
        addcmt7=(TextView) findViewById(R.id.addcmt7);
        addcmt8=(TextView) findViewById(R.id.addcmt8);
        addcmt9=(TextView) findViewById(R.id.addcmt9);
        addcmt10=(TextView) findViewById(R.id.addcmt10);

        get_layout1=(LinearLayout) findViewById(R.id.layout1);
        get_layout2=(LinearLayout) findViewById(R.id.layout2);
        get_layout3=(LinearLayout) findViewById(R.id.layout3);
        get_layout4=(LinearLayout) findViewById(R.id.layout4);


        get_layout1.setVisibility(View.GONE);
        get_layout2.setVisibility(View.GONE);
        get_layout3.setVisibility(View.GONE);

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("LLLMM",""+id);
        if(id!=null){

            Log.e("SSSSWE","page h = "+MSOT_Main.Main_ID);
            MSOT_Main.Main_ID=id;
            get_LIST_Data(id);
        }else {
            if (!MSOT_Main.Main_ID.equals("0")) {
                // String keyid=null;
                Log.e("CCCCX",""+MSOT_Main.Main_ID);
                get_LIST_Data(MSOT_Main.Main_ID);
            }

        }


        if(get_question_int("h1")!=0){

            get_layout1.setVisibility(View.VISIBLE);

        }if(get_question_int("h5")!=0){

            get_layout2.setVisibility(View.VISIBLE);

        }if(get_question_int("h6")!=0){

            get_layout3.setVisibility(View.VISIBLE);

        }

        TextView Q1_Imageview = (TextView) findViewById(R.id.image_Q2);
        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q2);
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

        TextView Q6_Imageview = (TextView) findViewById(R.id.image_Q6);
        Image_layout_Q6 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q6);
        Image_layout_Q6.setVisibility(View.GONE);

        TextView Q7_Imageview = (TextView) findViewById(R.id.image_Q8);
        Image_layout_Q7 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q8);
        Image_layout_Q7.setVisibility(View.GONE);

        TextView Q8_Imageview = (TextView) findViewById(R.id.image_Q9);
        Image_layout_Q8 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q9);
        Image_layout_Q8.setVisibility(View.GONE);

        TextView Q10_Imageview = (TextView) findViewById(R.id.image_Q10);
        Image_layout_Q10 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.image_layout_Q10);
        Image_layout_Q10.setVisibility(View.GONE);

        Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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

        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
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
            }
        });

        rd10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                get_rd10 = ((RadioButton) findViewById(rd10.getCheckedRadioButtonId())).getText().toString();
            }
        });




        cmt1=(TextView) findViewById(R.id.cmt1);
        cmt2=(TextView) findViewById(R.id.cmt2);
        cmt3=(TextView) findViewById(R.id.cmt3);
        cmt4=(TextView) findViewById(R.id.cmt4);
        cmt5=(TextView) findViewById(R.id.cmt5);
        cmt6=(TextView) findViewById(R.id.cmt6);
        cmt7=(TextView) findViewById(R.id.cmt7);
        cmt8=(TextView) findViewById(R.id.cmt8);
        cmt9=(TextView) findViewById(R.id.cmt9);
        cmt10=(TextView) findViewById(R.id.cmt10);

        img_toast1=(TextView) findViewById(R.id.img_toast1);
        img_toast2=(TextView) findViewById(R.id.img_toast2);
        img_toast3=(TextView) findViewById(R.id.img_toast3);
        img_toast4=(TextView) findViewById(R.id.img_toast4);


        isInternetPresent = haveNetworkConnection(Page_H.this);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        draft=(ImageView) findViewById(R.id.draft);

        draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_H.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        insert_page_h();
                    }
                    else{

                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_H.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_H.this);
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
                                Toast.makeText(Page_H.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_H.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




            }
        });


        addcmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


                input1 = new EditText(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input2 = new EditText(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input3 = new EditText(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input4 = new EditText(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input5 = new EditText(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input6 = new EditText(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input7 = new EditText(Page_H.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input8 = new EditText(Page_H.this);
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


        addcmt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
               input9 = new EditText(Page_H.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input9.setLayoutParams(lp);
                builder.setView(input9);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CharSequence contentTitle7 = getString(R.string.Activity_remarks);
                        cmt9.setText(Html.fromHtml( "" + "<font> <font color='#65096E'><b>"+contentTitle7+"</font></font> "+"<font> <font color='#00898B'><i>"+input9.getText().toString()+"</font></font> "));
                       
                        
                        String get_comment9 = cmt9.getText().toString();

                        if(get_comment9.equalsIgnoreCase("Remarks: ")){

                            cmt9.setText("");

                        }
                
                    }
                });
                builder.show();
            }
        });
        addcmt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);
                input10 = new EditText(Page_H.this);
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
        img_toast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar
                        .make(coordinatorLayout, "Some other alternatives: Identify other safer access point to the ceiling/ roof void. Avoid working near these areas. Check the voltage, wear electrical insulation gloves where appropriate (only for low voltage environment: below 500V).", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);

                snackbar.setDuration(10000);
                snackbar.show();

            }
        });

        img_toast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar
                        .make(coordinatorLayout, "Exemption: Rentokil Lumnia Fly Killing Units are specifically designed to be safely serviced whilst remaining plugged in and switched on – lowering the unit out of the chassis or opening the front louvre effectively isolates units from mains powered electricity", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);
                snackbar.setDuration(10000);
                snackbar.show();
            }
        });

        img_toast3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar
                        .make(coordinatorLayout, "As per Group Electrical Safety Guidance: High Voltage = greater than 1000V AC, or 1500V DC.", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);
                snackbar.setDuration(10000);
                snackbar.show();
            }
        });



        img_toast4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 snackbar = Snackbar
                        .make(coordinatorLayout, "As per Group Electrical Safety Guidance: Low Voltage = 50V-1000V AC, or 120V-1500V DC.", Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
                textView.setMaxLines(6);
                snackbar.setDuration(10000);

                snackbar.show();
            }
        });


        sub_1=(MyTextViewMSOT) findViewById(R.id.sub_1);
        sub_2=(MyTextViewMSOT) findViewById(R.id.sub_2);


        sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                isInternetPresent = haveNetworkConnection(Page_H.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        Log.e("GGGG","validation");
                        insert_page_h();

                    }
                    else{
                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_H.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    pd.dismiss();
                    final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                    final Dialog dialog = new Dialog(Page_H.this);
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
                                Toast.makeText(Page_H.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_H.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();


                }
        }
        });

        sub_1.setOnClickListener(this);

//        sub_page3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                isInternetPresent = haveNetworkConnection(Page_H.this);
//                if (isInternetPresent) {
//
//
//                    if (validation()) {
//                        Log.e("GGGG","validation");
//                        if (isInternetPresent) {
//                            submitres();
//                        }
//                        else{
//
//                            Toast.makeText(Page_H.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
//                        }
//
//
//                    }
//                    else{
//
//                        Log.e("GGGG","else");
//
//                        Toast.makeText(Page_H.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
//                    }
//                }
//                else {
//
//                    final Dialog dialog = new Dialog(Page_H.this);
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
//                                Toast.makeText(Page_H.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                    Toast.makeText(Page_H.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
//                    dialog.show();
//
//
//                }
//
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

    public boolean validation() {
        boolean check_point=false;

        try {
//            get_rd1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
//            get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
//            get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
//            get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
//            get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();
//            get_rd6 = ((RadioButton) findViewById(rd6.getCheckedRadioButtonId())).getText().toString();
//            get_rd7 = ((RadioButton) findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
//            get_rd8 = ((RadioButton) findViewById(rd8.getCheckedRadioButtonId())).getText().toString();
//            get_rd9 = ((RadioButton) findViewById(rd9.getCheckedRadioButtonId())).getText().toString();
            get_rd10 = ((RadioButton) findViewById(rd10.getCheckedRadioButtonId())).getText().toString();

            if(get_question_int("h1")!=0) {
                    get_rd1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();
                    get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
                    get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
                    get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
            }

            if(get_question_int("h5")!=0) {
                get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();
            }
            if(get_question_int("h6")!=0) {

                get_rd6 = ((RadioButton) findViewById(rd6.getCheckedRadioButtonId())).getText().toString();
                get_rd7 = ((RadioButton) findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
                get_rd8 = ((RadioButton) findViewById(rd8.getCheckedRadioButtonId())).getText().toString();
                get_rd9 = ((RadioButton) findViewById(rd9.getCheckedRadioButtonId())).getText().toString();
            }


            if(get_rd10.length()!=0) {
                try {
                    if (get_question_int("h1") == 0) {
                        check_point = true;

                    } else {
                            if (get_rd1.length() != 0 && get_rd2.length() != 0 && get_rd3.length() != 0 && get_rd4.length() != 0) {
                                check_point = true;
                            } else {
                                check_point = false;
                            }
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                    return false;
                }


                try {
                    if( get_question_int("h5")==0){
                        check_point=true;

                    }else {

                            if (get_rd5.length()!=0){
                                check_point=true;
                            }else {
                                check_point=false;
                            }

                    }
                } catch (Exception e) {

                    e.printStackTrace();
                    return  false;
                }


                try {
                    if( get_question_int("h6")==0){
                        check_point=true;

                    }else {

                        if (get_rd6.length() != 0 && get_rd7.length() != 0 && get_rd8.length() != 0 && get_rd9.length() != 0) {
                            check_point=true;
                        }else {
                            check_point=false;
                        }

                    }
                } catch (Exception e) {

                    e.printStackTrace();
                    return  false;
                }
            }
            else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return check_point;
    }

    @Override
    public void onBackPressed(){

        String name="";
        for (int i=0;i<section_2.length;i++) {
            name=get_question(""+section_2[i]);
            Log.e("DDDDHHHS","question="+name);

            if(name.startsWith("g")){
                Intent intent=new Intent(Page_H.this,Page_G.class);
                startActivity(intent);
                break;

            }else if(name.startsWith("e")){
                Intent intent=new Intent(Page_H.this,Page_E.class);
                startActivity(intent);
                break;
            }
            else{

                Intent intent=new Intent(Page_H.this,Page_F.class);
                startActivity(intent);
                break;
            }

        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {




    }

    private void submitres(final String saveid) {

        Log.e("GGGG","sub");
        isInternetPresent = haveNetworkConnection(Page_H.this);
        if (isInternetPresent) {

            if(get_question_int("h1")!=0) {
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

            }
            if(get_question_int("h5")!=0) {
                if (rd5 != null) {
                    get_rd5 = ((RadioButton) findViewById(rd5.getCheckedRadioButtonId())).getText().toString();
                } else {
                    get_rd5 = "";
                }
            }

            if(get_question_int("h6")!=0) {
                if (rd6 != null) {
                    get_rd6 = ((RadioButton) findViewById(rd6.getCheckedRadioButtonId())).getText().toString();
                } else {
                    get_rd6 = "";
                }

                if (rd7 != null) {
                    get_rd7 = ((RadioButton) findViewById(rd7.getCheckedRadioButtonId())).getText().toString();
                } else {
                    get_rd7 = "";
                }

                if (rd8 != null) {
                    get_rd8 = ((RadioButton) findViewById(rd8.getCheckedRadioButtonId())).getText().toString();
                } else {
                    get_rd8 = "";
                }


                if (rd9 != null) {
                    get_rd9 = ((RadioButton) findViewById(rd9.getCheckedRadioButtonId())).getText().toString();
                } else {
                    get_rd9 = "";
                }

            }
                if (rd10 != null) {
                    get_rd10 = ((RadioButton) findViewById(rd10.getCheckedRadioButtonId())).getText().toString();
                } else {
                    get_rd10 = "";
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

            if (input9!=null) {
                get_input9 = input9.getText().toString();
            }else {
                get_input9="";
            }

            if (input10!=null) {
                get_input10 = input10.getText().toString();
            }else {
                get_input10="";
            }






        Map<String, String> params = new HashMap<String, String>();
            params.put("pos_et1",get_rd1);
            params.put("pos_et2",get_rd2);
            params.put("pos_et3",get_rd3);
            params.put("pos_et4",get_rd4);
            params.put("pos_et5",get_rd5);
            params.put("pos_et6",get_rd6);
            params.put("pos_et7",get_rd7);
            params.put("pos_et8",get_rd8);
            params.put("pos_et9",get_rd9);
            params.put("pos_et10",get_rd10);

        params.put("add_com1", get_input1);
        params.put("add_com2", get_input2);
        params.put("add_com3", get_input3);
        params.put("add_com4", get_input4);
        params.put("add_com5", get_input5);
        params.put("add_com6", get_input6);
        params.put("add_com7", get_input7);
        params.put("add_com8", get_input8);
        params.put("add_com9", get_input9);
        params.put("add_com10", get_input10);

        params.put("getimage1", bitmapget1);
        params.put("getimage2", bitmapget2);
        params.put("getimage3", bitmapget3);
        params.put("getimage4", bitmapget5);
        params.put("getimage5", bitmapget6);
        params.put("getimage6", bitmapget7);
        params.put("getimage7", bitmapget8);
        params.put("getimage8", bitmapget10);

            params.put("main_id", MSOT_Main.Main_ID);

            Log.e("GGGHH","params"+params);
            Log.e("RRREEE","BBBB1 = "+get_input1);
            Log.e("RRREEE","BBBB2 = "+get_input2);


            VolleyDataRequester.withDefaultHttps( this )
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_8.php")
                    .setBody( params )
                    .setMethod( VolleyDataRequester.Method.POST )
                    .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                Log.e("GGGG","e1"+response);


                                if(saveid.equalsIgnoreCase("save")){


                                    final AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
                                    builder.setMessage("Are You Sure Want to Save & Exit");
                                    builder.setPositiveButton("YES",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {

                                                    Intent intent=new Intent(Page_H.this, Category_Type_Activity.class);
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

                                                if (name.startsWith("i")) {

                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_H.this, Page_I.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("j")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_H.this, Page_J.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("k")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_H.this, Page_K.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("l")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_H.this, Page_L.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("m")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_H.this, Page_M.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("n")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_H.this, Page_N.class);
                                                    startActivity(intent);
                                                    break;

                                                } else if (name.startsWith("o")) {
                                                    pd.dismiss();
                                                    Intent intent = new Intent(Page_H.this, Page_O.class);
                                                    startActivity(intent);
                                                    break;

                                                } else {
                                                    pd.dismiss();
                                                    Log.e("DDDDHHHS", "JJJJJ\t" + i);
                                                    if (i == 6) {
                                                        Intent intent = new Intent(Page_H.this, Page_J.class);
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

                                        if (name.startsWith("i")) {

                                            pd.dismiss();
                                            Intent intent = new Intent(Page_H.this, Page_I.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("j")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_H.this, Page_J.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("k")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_H.this, Page_K.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("l")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_H.this, Page_L.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("m")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_H.this, Page_M.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("n")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_H.this, Page_N.class);
                                            startActivity(intent);
                                            break;

                                        } else if (name.startsWith("o")) {
                                            pd.dismiss();
                                            Intent intent = new Intent(Page_H.this, Page_O.class);
                                            startActivity(intent);
                                            break;

                                        } else {
                                            pd.dismiss();
                                            Log.e("DDDDHHHS", "JJJJJ\t" + i);
                                            if (i == 6) {
                                                Intent intent = new Intent(Page_H.this, Page_J.class);
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

                                        if(name.startsWith("i")){
                                            Intent intent=new Intent(Page_H.this,Page_I.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("j")){
                                            Intent intent=new Intent(Page_H.this,Page_J.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("k")){
                                            Intent intent=new Intent(Page_H.this,Page_K.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("l")){
                                            Intent intent=new Intent(Page_H.this,Page_L.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("m")){
                                            Intent intent=new Intent(Page_H.this,Page_M.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("n")){
                                            Intent intent=new Intent(Page_H.this,Page_N.class);
                                            startActivity(intent);
                                            break;

                                        }else if(name.startsWith("o")){
                                            Intent intent=new Intent(Page_H.this,Page_O.class);
                                            startActivity(intent);
                                            break;

                                        }else {
                                            Log.e("DDDDHHHS","JJJJJ\t"+i);
                                            if (i==6) {
                                                Intent intent=new Intent(Page_H.this,Page_J.class);
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

                                    if(name.startsWith("i")){
                                        Intent intent=new Intent(Page_H.this,Page_I.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("j")){
                                        Intent intent=new Intent(Page_H.this,Page_J.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("k")){
                                        Intent intent=new Intent(Page_H.this,Page_K.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("l")){
                                        Intent intent=new Intent(Page_H.this,Page_L.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("m")){
                                        Intent intent=new Intent(Page_H.this,Page_M.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("n")){
                                        Intent intent=new Intent(Page_H.this,Page_N.class);
                                        startActivity(intent);
                                        break;

                                    }else if(name.startsWith("o")){
                                        Intent intent=new Intent(Page_H.this,Page_O.class);
                                        startActivity(intent);
                                        break;

                                    }else {
                                        Log.e("DDDDHHHS","JJJJJ\t"+i);
                                        if (i==6) {
                                            Intent intent=new Intent(Page_H.this,Page_J.class);
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

            final Dialog dialog = new Dialog(Page_H.this);
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
                        Toast.makeText(Page_H.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Page_H.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
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

        AlertDialog.Builder builder = new AlertDialog.Builder(Page_H.this);
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


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sub_1){


            isInternetPresent = haveNetworkConnection(Page_H.this);
                if (isInternetPresent) {
pd.show();

                    if (validation()) {
                        insert_page_h();
                    }
                    else{
                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_H.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    pd.dismiss();
                    final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                    final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                    final Dialog dialog = new Dialog(Page_H.this);
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
                                Toast.makeText(Page_H.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_H.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();


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

    private void get_LIST_Data(String key_id){

        Log.e("VVVCS","h"+key_id);
        Log.e("VVVCC",key_id);
        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("EEEEQ",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_8.php?main_id="+MSOT_Main.Main_ID ;

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
                                String et6 = c.getString( "et6" );
                                String et7 = c.getString( "et7" );
                                String et8 = c.getString( "et8" );
                                String et9 = c.getString( "et9" );
                                String et10 = c.getString( "et10" );


                                Log.e("PPOEEE","E2");

                                String cv_cmt1 = c.getString( "cmt1" );
                                String cv_cmt2 = c.getString( "cmt2" );
                                String cv_cmt3 = c.getString( "cmt3" );
                                String cv_cmt4 = c.getString( "cmt4" );
                                String cv_cmt5 = c.getString( "cmt5" );
                                String cv_cmt6 = c.getString( "cmt6" );
                                String cv_cmt7 = c.getString( "cmt7" );
                                String cv_cmt8 = c.getString( "cmt8" );
                                String cv_cmt9 = c.getString( "cmt9" );
                                String cv_cmt10 = c.getString( "cmt10" );


                                Log.e("PPOEEE","E3");

                                String image_1 = c.getString( "image1" );
                                String image_2 = c.getString( "image2" );
                                String image_3 = c.getString( "image3" );
                                String image_5 = c.getString( "image4" );
                                String image_6 = c.getString( "image5" );
                                String image_7 = c.getString( "image6" );
                                String image_8 = c.getString( "image7" );
                                String image_10 = c.getString( "image8" );


                                Log.e("PPOEEE","E4");



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
                                else if(et9.equalsIgnoreCase("Partial")){
                                    ((RadioButton) rd9.getChildAt(1)).setChecked(true);
                                }
                                else if(et9.equalsIgnoreCase("No")){
                                    ((RadioButton) rd9.getChildAt(2)).setChecked(true);
                                }
                                else if(et9.equalsIgnoreCase("N/A")){
                                    ((RadioButton) rd9.getChildAt(3)).setChecked(true);
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
                                    cmt9.setText("");
                                }else{
                                    cmt9.setText(cv_cmt9);
                                }

                                if(cv_cmt10.equalsIgnoreCase("null")){
                                    cmt10.setText("");
                                }else{
                                    cmt10.setText(cv_cmt10);
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
    public void insert_page_h(){
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
        sd.insert(db.MSOT_PAGE_H_DB,null,cv_off);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H2");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H3");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H4");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H5");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H6");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H8");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H9");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();
        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"8");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input4);
        cv_off_img.put(db.QUESTION_ID,"H10");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);


    }



}
