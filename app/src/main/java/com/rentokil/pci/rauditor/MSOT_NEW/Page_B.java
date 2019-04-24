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
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
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

public class Page_B extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {


    SliderLayout mDemoSlider;
    public static ExpandableHeightListView listview;
    ImageView img_tool1;
    EditText et2, et3, et4;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off,cv_off_img;
    String get_et2, get_et3, get_et4;
    Boolean isInternetPresent = false;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    String getradio1;
    LinearLayout layout1, layout2, layout3;
    ArrayList<String> bitmapst = new ArrayList<String>();
    ImageView Image_layout_Q1, Image_layout_Q2, Image_layout_Q3;
    public static final int RequestPermissionCode = 1;
    String bitmapget1, bitmapget2,bitmapget3;
    Bitmap bitmapImage1, bitmapImage2, bitmapImage3;
    public String Update_Status = "0";
    String ImageCheck = "";
    Uri uri;
    //   String bitmapst;
    String mCurrentPhotoPath;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    Snackbar snackbar;
  String Main_ID="0";
    SpannableStringBuilder ssb;
    String Image_1,Image_2,Image_3;

    RadioGroup rd1,rd2,rd3,rd4;
    String get_rd2,get_rd3,get_rd4;
    TextView addcmt1,addcmt2,addcmt3,cmt1,cmt2,cmt3,img_toast1;

    String get_cmt1,get_cmt2,get_cmt3;


    EditText input1,input2,input3;
    String get_input1,get_input2,get_input3;

    CoordinatorLayout coordinatorLayout;
    TextView submit_page2;

    MyTextViewMSOT sub_nxt,sub_2;
    
    ImageView draft;

    private android.app.AlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_b);


        sub_nxt = (MyTextViewMSOT) findViewById(R.id.sub_nxt);
        sub_2 = (MyTextViewMSOT) findViewById(R.id.sub_2);


        submit_page2 = (TextView) findViewById(R.id.submit_page2);

        pd = new SpotsDialog(Page_B.this, R.style.Custom);

        rd1 = (RadioGroup) findViewById(R.id.qsr_cuspest_rd1);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout3 = (LinearLayout) findViewById(R.id.layout3);

        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

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
        

        sub_nxt.setVisibility(View.GONE);


        sub_nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Page_B.this,Page_C.class);
                startActivity(i);
            }
        });

        rd2 = (RadioGroup) findViewById(R.id.e1_rd1);
        rd3 = (RadioGroup) findViewById(R.id.e2_rd1);
        rd4 = (RadioGroup) findViewById(R.id.e2_rd3);
        addcmt1=(TextView) findViewById(R.id.e1_addcmt1);
        addcmt2=(TextView) findViewById(R.id.e2_addcmt);
        addcmt3=(TextView) findViewById(R.id.e3_addcmt);

        cmt1=(TextView) findViewById(R.id.e1_cmt1);
        cmt2=(TextView) findViewById(R.id.e2_cmt2);
        cmt3=(TextView) findViewById(R.id.e3_cmt3);

        img_toast1=(TextView) findViewById(R.id.img_toast1);

        rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


//                int selectedId=rd1.getCheckedRadioButtonId();
                get_rd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


//                int selectedId=rd1.getCheckedRadioButtonId();

                get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();


            }
        });

        rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


//                int selectedId=rd1.getCheckedRadioButtonId();

                get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();


            }
        });
        
        
        

        addcmt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


            input1 = new EditText(Page_B.this);
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


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


               input2= new EditText(Page_B.this);
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


                AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
                builder.setTitle("Actions/Comments");
                builder.setIcon(R.drawable.commenticon);


                input3 = new EditText(Page_B.this);
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

                        String get_comment2 = cmt3.getText().toString();

                        if(get_comment2.equalsIgnoreCase("Remarks: ")){

                            cmt3.setText("");

                        }



                    }
                });
                builder.show();


            }
        });

        img_toast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG);

                Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

                TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
                textView.setVisibility(View.INVISIBLE);
                textView.setMaxLines(14);


                View snackView = getLayoutInflater().inflate(R.layout.page_b_snackbar, null);

                layout.setPadding(0,0,0,0);

                layout.addView(snackView, 0);
                snackbar.setDuration(10000);
                snackbar.show();

            }
        });

        submit_page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (db.get_country(sd).equalsIgnoreCase("India")) {
                    Intent intent =new Intent(Page_B.this, Page_D.class);
                    startActivity(intent);
                }else {
                    Intent intent =new Intent(Page_B.this, Page_C.class);
                    startActivity(intent);

                }
            }
        });





        TextView Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.Image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);


        TextView Q2_Imageview = (TextView) findViewById(R.id.image_Q2);
        Image_layout_Q2 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.Image_layout_Q2);
        Image_layout_Q2.setVisibility(View.GONE);

        TextView Q3_Imageview = (TextView) findViewById(R.id.image_Q3);
        Image_layout_Q3 = (de.hdodenhof.circleimageview.CircleImageView) findViewById(R.id.Image_layout_Q3);
        Image_layout_Q3.setVisibility(View.GONE);

        Image_layout_Q1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
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
                AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
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


        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


//                int selectedId=rd1.getCheckedRadioButtonId();

                getradio1 = ((RadioButton) findViewById(rd1.getCheckedRadioButtonId())).getText().toString();

                if (getradio1.equalsIgnoreCase("YES")) {
                    submit_page2.setVisibility(View.VISIBLE);
                    sub_2.setVisibility(View.VISIBLE);
                    layout1.setVisibility(View.VISIBLE);
                    layout2.setVisibility(View.VISIBLE);
                    layout3.setVisibility(View.VISIBLE);


                } else {
                    submit_page2.setVisibility(View.VISIBLE);
                    sub_2.setVisibility(View.GONE);

                    layout1.setVisibility(View.GONE);
                    layout2.setVisibility(View.GONE);
                    layout3.setVisibility(View.GONE);


                }

            }
        });

        isInternetPresent = haveNetworkConnection(Page_B.this);

        db = new DatabaseHelper(this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();


        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        et2 = (EditText) findViewById(R.id.et2);
        et3 = (EditText) findViewById(R.id.et3);
        et4 = (EditText) findViewById(R.id.et4);




        listview = (ExpandableHeightListView) findViewById(R.id.haircut_list);

        mDemoSlider = (SliderLayout) findViewById(R.id.slider);


        submit_page2.setVisibility(View.INVISIBLE);
        sub_2.setVisibility(View.INVISIBLE);


        draft=(ImageView) findViewById(R.id.draft);

        draft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_B.this);
                if (isInternetPresent) {
                    pd.show();

                    if (validation()) {
                        Log.e("GGGG","validation");
                        if (isInternetPresent) {

                            String enterid="save";

                            submitres(enterid);

                        }
                        else{
                            pd.dismiss();
                            Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else{

                        pd.dismiss();
                        Log.e("GGGG","else");

                        Toast.makeText(Page_B.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_B.this);
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
                                Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_B.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




            }
        });


        submit_page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                isInternetPresent = haveNetworkConnection(Page_B.this);
                if (isInternetPresent) {

                    pd.show();

                    if (getradio1.equalsIgnoreCase("Yes")) {

                        pd.show();

                        if (validation()) {
                            Log.e("GGGG", "validation");
                            if (isInternetPresent) {
                                String sub ="pos";
                                submitres(sub);
                            } else {
                                pd.dismiss();

                                Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            pd.dismiss();
                            Log.e("GGGG", "else");

                            Toast.makeText(Page_B.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                        }
                    } else if (getradio1.equalsIgnoreCase("No")) {


                        isInternetPresent = haveNetworkConnection(Page_B.this);
                        if (isInternetPresent) {

                            String sub ="pos";
                            submitres(sub);
                        } else {
                            pd.dismiss();
                            Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else {
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_B.this);
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
                                Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_B.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();


                }
            }
        });

        sub_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                isInternetPresent = haveNetworkConnection(Page_B.this);
                if (isInternetPresent) {

                    pd.show();

                    if (getradio1.equalsIgnoreCase("YES")) {

                        pd.show();

                        if (validation()) {
                           insert_page_b();

                        } else {
                            pd.dismiss();
                            Log.e("GGGG", "else");

                            Toast.makeText(Page_B.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                        }
                    } else if (getradio1.equalsIgnoreCase("NO")) {


                        isInternetPresent = haveNetworkConnection(Page_B.this);
                        if (isInternetPresent) {

                            String sub ="pos";
                            submitres(sub);
                        } else {
                            pd.dismiss();
                            Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                        }

                    }
                } else {
                    pd.dismiss();
                    final Dialog dialog = new Dialog(Page_B.this);
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
                                Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(Page_B.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();


                }
            }
        });

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1", R.drawable.pcislider);
        file_maps.put("2", R.drawable.pci1);
        file_maps.put("3", R.drawable.pci4);


        for (String name : file_maps.keySet()) {
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


        try {
            get_rd2 = ((RadioButton) findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
            get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
            get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();

            if(get_rd2.length()==0||get_rd3.length()==0||get_rd4.length()==0){
                return false;

            }
            else {
                return  true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public void onSliderClick(BaseSliderView slider) {


    }

    private void submitres(final String saveid) {

        sd.delete(db.MSOT_TABLE, null, null);


        Log.e("GGGG", "sub");
        isInternetPresent = haveNetworkConnection(Page_B.this);
        if (isInternetPresent) {

            if (getradio1.equalsIgnoreCase("YES")) {

                if (rd2!=null) {
                    get_rd2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();
                }else {
                    get_rd2="";
                }

                if (rd3!=null) {
                    get_rd3 = ((RadioButton) findViewById(rd3.getCheckedRadioButtonId())).getText().toString();
                }else {
                    get_rd3="";
                }

                if (rd4!=null) {
                    get_rd4 = ((RadioButton) findViewById(rd4.getCheckedRadioButtonId())).getText().toString();
                }else {
                    get_rd4="";
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




            } else if (getradio1.equalsIgnoreCase("NO")) {

                get_rd2 = "";
                get_rd3 = "";
                get_rd4 = "";
                get_input1="";
                get_input2="";
                get_input3="";
                bitmapget1="";
                bitmapget2="";
                bitmapget3="";

            }





            Map<String, String> params = new HashMap<String, String>();
            params.put("pos_et1", getradio1);
            params.put("pos_et2", get_rd2);
            params.put("pos_et3", get_rd3);
            params.put("pos_et4", get_rd4);
            params.put("add_c1", get_input1);
            params.put("add_c2", get_input2);
            params.put("add_c3", get_input3);
            params.put("getimage1", bitmapget1);
            params.put("getimage2", bitmapget2);
            params.put("getimage3", bitmapget3);
            params.put("main_id", MSOT_Main.Main_ID);

Log.e("AAA1","param = "+getradio1);
Log.e("AAA2","param = "+get_rd2);
Log.e("AAA3","param = "+get_rd3);
Log.e("AAA4","param = "+get_rd4);
Log.e("AAA5","param = "+get_cmt1);
Log.e("AAA6","param = "+get_cmt2);
Log.e("AAA7","param = "+get_cmt3);
Log.e("AAA8","param = "+bitmapget1);
Log.e("AAA9","param = "+bitmapget2);
Log.e("AAA10","param = "+bitmapget3);
Log.e("AAA11","param = "+MSOT_Main.Main_ID);

            VolleyDataRequester.withDefaultHttps(this)
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_2.php")
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


                                    if(saveid.equalsIgnoreCase("save")){


                                        final AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
                                        builder.setMessage("Are You Sure Want to Save & Exit");
                                        builder.setPositiveButton("YES",
                                                new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {

                                                        Intent intent=new Intent(Page_B.this, Category_Type_Activity.class);
                                                        startActivity(intent);
                                                    }
                                                });
                                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {

                                                pd.dismiss();
                                                dialog.dismiss();

                                                if (db.get_country(sd).equalsIgnoreCase("India")) {

                                                    Intent intent =new Intent(Page_B.this, Page_D.class);
                                                    startActivity(intent);

                                                    pd.dismiss();
                                                }
                                                else{

                                                    Intent intent =new Intent(Page_B.this, Page_C.class);
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

                                            Intent intent =new Intent(Page_B.this, Page_D.class);
                                            startActivity(intent);

                                            pd.dismiss();
                                        }
                                        else{

                                            Intent intent =new Intent(Page_B.this, Page_C.class);
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


                            try {

                                pd.dismiss();

                                if(error.getMessage().equalsIgnoreCase("null")){

                                    if (db.get_country(sd).equalsIgnoreCase("India")) {

                                        Intent intent =new Intent(Page_B.this, Page_D.class);
                                        startActivity(intent);
                                    }
                                    else{

                                        Intent intent =new Intent(Page_B.this, Page_C.class);
                                        startActivity(intent);

                                    }

                                }
                            } catch (Exception e) {

                                pd.dismiss();

                                if (db.get_country(sd).equalsIgnoreCase("India")) {

                                    Intent intent =new Intent(Page_B.this, Page_D.class);
                                    startActivity(intent);
                                }
                                else{

                                    Intent intent =new Intent(Page_B.this, Page_C.class);
                                    startActivity(intent);

                                }
                                e.printStackTrace();
                            }

                        }
                    })
                    .requestString();


        } else {


            pd.dismiss();

            final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

            final Dialog dialog = new Dialog(Page_B.this);
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
                        Toast.makeText(Page_B.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Page_B.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
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

    public String getStringImage(Bitmap bmp) {
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

    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(Page_B.this);
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


    private float dp(int dp) {
        return getResources().getDisplayMetrics().density * dp;
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

    public void next_page_f(String page_name,int position){

        if(page_name.equalsIgnoreCase("INCIDENT REPORTING")){

            Intent intent = new Intent(Page_B.this, Page_A.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }

        if(page_name.equalsIgnoreCase("SINA CARD")){

            Intent intent = new Intent(Page_B.this, Page_B.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("ZERO TOLERANCE POLICY")){

            Intent intent = new Intent(Page_B.this, Page_C.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }

        if(page_name.equalsIgnoreCase("SHE GOLDEN RULES")){

            Intent intent = new Intent(Page_B.this, Page_D.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }

        if(page_name.equalsIgnoreCase("COMPLIANCE TO BASIC SAFETY RULES")){

            Intent intent = new Intent(Page_B.this, Page_K.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }
        if(page_name.equalsIgnoreCase("SHE GOLDEN RULES TRAINING AT THE BRANCH")){

            Intent intent = new Intent(Page_B.this, Page_L.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }
        if(page_name.equalsIgnoreCase("SRA IMPLEMENTATION AT THE BRANCH")){

            Intent intent = new Intent(Page_B.this, Page_M.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }
        if(page_name.equalsIgnoreCase("PERSONAL PROTECTIVE EQUIPMENT")){

            Intent intent = new Intent(Page_B.this, Page_N.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }

        if(page_name.equalsIgnoreCase("DRIVING & MOTORBIKE RIDING SAFETY")){

            Intent intent = new Intent(Page_B.this, Page_O.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }


        if(page_name.equalsIgnoreCase("Fly Killer Dispenser installation / servicing")){

            Intent intent = new Intent(Page_B.this, Page_B.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }


        if (page_name.equalsIgnoreCase("Bait Stations Installation / Servicing")) {

            Intent intent = new Intent(Page_B.this, Page_B.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);


        }


        if(page_name.equalsIgnoreCase("5.WORKING AT HEIGHT")){

            Intent intent = new Intent(Page_B.this, Page_E.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("6.SLIP, TRIP & FALL, MOVING VEHICLE & OTHER RISKS")){

            Intent intent = new Intent(Page_B.this, Page_F.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("7.DRIVING & PEDESTRIAN SAFETY")){

            Intent intent = new Intent(Page_B.this, Page_G.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("8.ELECTRICAL SAFETY")){

            Intent intent = new Intent(Page_B.this, Page_H.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("9.FUMIGATION SAFETY")){

            Intent intent = new Intent(Page_B.this, Page_I.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("10.EMPLOYEE INDUCTION & CONSULTATION")){

            Intent intent = new Intent(Page_B.this, Page_J.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("11.COMPLIANCE TO BASIC SAFETY RULES")){

            Intent intent = new Intent(Page_B.this, Page_K.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("12.SHE GOLDEN RULES TRAINING AT THE BRANCH")){

            Intent intent = new Intent(Page_B.this, Page_L.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("13.SRA IMPLEMENTATION AT THE BRANCH")){

            Intent intent = new Intent(Page_B.this, Page_M.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("14.PERSONAL PROTECTIVE EQUIPMENT")){

            Intent intent = new Intent(Page_B.this, Page_N.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }
        if(page_name.equalsIgnoreCase("15.DRIVING & MOTORBIKE RIDING SAFETY")){

            Intent intent = new Intent(Page_B.this, Page_O.class);
            intent.putExtra("page_name",""+position);
            startActivity(intent);

        }

    }

    private void get_LIST_Data(String key_id){

        Log.e("XXXXV","3rd ="+key_id);
        Log.e("KKKJJ",key_id);
        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("LLLLKI",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_2.php?main_id="+MSOT_Main.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("DDSSEEE","response = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String et1 = c.getString( "et1" );
                                String et2 = c.getString( "et2" );
                                String et3 = c.getString( "et3" );
                                String et4 = c.getString( "et4" );
                                String image_1 = c.getString( "image1" );
                                String image_2 = c.getString( "image2" );
                                String image_3 = c.getString( "image3" );
                                String cv_cmt1 = c.getString( "cmt1" );
                                String cv_cmt2 = c.getString( "cmt2" );
                                String cv_cmt3 = c.getString( "cmt3" );




                                sub_nxt.setVisibility(View.VISIBLE);

                                if(et1.equalsIgnoreCase("YES")){
                                    ((RadioButton) rd1.getChildAt(0)).setChecked(true);


                                }

                                else if(et1.equalsIgnoreCase("NO")){

                                    ((RadioButton) rd1.getChildAt(1)).setChecked(true);

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

    @Override
    public void onBackPressed(){

        Intent i = new Intent(Page_B.this,Page_A.class);
        startActivity(i);
    }

    public void insert_page_b(){

        cv_off.put(db.MAIN_ID, ""+Main_ID);
        cv_off.put(db.et1, ""+getradio1);
        cv_off.put(db.et2, ""+get_rd2);
        cv_off.put(db.et3, ""+get_rd3);
        cv_off.put(db.et4, ""+get_rd4);
        sd.insert(db.MSOT_PAGE_B_DB,null,cv_off);

        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"2");
        cv_off_img.put(db.IMAGE_1,bitmapget1);
        cv_off_img.put(db.COMMANDS,get_input1);
        cv_off_img.put(db.QUESTION_ID,"B1");

        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);
        cv_off_img.clear();

        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"2");
        cv_off_img.put(db.IMAGE_1,bitmapget2);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"B2");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);

        cv_off_img.clear();

        cv_off_img.put(db.MAIN_ID,Main_ID);
        cv_off_img.put(db.PAGE_ID,"2");
        cv_off_img.put(db.IMAGE_1,bitmapget3);
        cv_off_img.put(db.COMMANDS,get_input2);
        cv_off_img.put(db.QUESTION_ID,"B3");
        sd.insert(db.MSOT_IMAGE_TB,null,cv_off_img);



    }
    public void get_off_b(String Main_ID ){
        String et1,et2,et3,et4;
        String Query="select * from "+db.MSOT_PAGE_B_DB +" where MAIN_ID = '" +Main_ID+"'";
        Cursor cursor = sd.rawQuery(Query, null);
        cursor.moveToFirst();

        et1=cursor.getString(cursor.getColumnIndex(db.et1));
        et2=cursor.getString(cursor.getColumnIndex(db.et2));
        et3=cursor.getString(cursor.getColumnIndex(db.et3));
        et4=cursor.getString(cursor.getColumnIndex(db.et4));

        if(et1.equalsIgnoreCase("YES")){
            ((RadioButton) rd1.getChildAt(0)).setChecked(true);


        }

        else if(et1.equalsIgnoreCase("NO")){

            ((RadioButton) rd1.getChildAt(1)).setChecked(true);

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




    }

}
