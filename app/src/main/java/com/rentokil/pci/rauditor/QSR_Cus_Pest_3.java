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
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class QSR_Cus_Pest_3 extends AppCompatActivity {

    ImageView Image_layout_Q1,Image_layout_Q2,Image_layout_Q3,Image_layout_Q4;

    TextView Q1_Imageview,Q2_Imageview,Q3_Imageview,Q4_Imageview;
    Bitmap bitmapImage1,bitmapImage2,bitmapImage3,bitmapImage4;
    byte[] img_by1;
    ByteArrayInputStream imageStream1;
    String ImageCheck = "";

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    Uri uri;
    //   String bitmapst;
    String mCurrentPhotoPath;
    String bitmapst,bitmapst2,bitmapst3,bitmapst4;
    public static final int RequestPermissionCode = 1;


    Button qsrcuspestsub,qsrcuspestbck,qsrcuspestsub1;

    RadioGroup rd1,rd2,rd3,rd4,rd5,rd6,rd7,rd8,rd9,rd10,rd11,rd12,rd13,rd14,rd15,rd16,rd17,rd18;

    RadioButton rbtn1,rbtn2,rbtn3,rbtn4,rbtn5,rbtn6,rbtn7,rbtn8,rbtn9,rbtn10,rbtn11,rbtn12,rbtn13,rbtn14,rbtn15,rbtn16,rbtn17,rbtn18;

    EditText et1,et2;

    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    private android.app.AlertDialog pd;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    String Image_1,Image_2,Image_3,Image_4;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    private FirebaseAnalytics mFirebaseAnalytics;

    String getet1,getet2,getrbtn1,getrbtn2,getrbtn3,getrbtn4,getrbtn5,getrbtn6,getrbtn7,getrbtn8,getrbtn9,getrbtn10,getrbtn11,getrbtn12,getrbtn13,getrbtn14,getrbtn15,getrbtn16,getrbtn17,getrbtn18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qsr_cuspest);

        EnableRuntimePermission();

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        db = new DatabaseHelper(QSR_Cus_Pest_3.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        pd = new SpotsDialog(QSR_Cus_Pest_3.this, R.style.Custom);
        Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (ImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);

        Q2_Imageview = (TextView) findViewById(R.id.image_Q2);
        Image_layout_Q2 = (ImageView) findViewById(R.id.image_layout_Q2);
        Image_layout_Q2.setVisibility(View.GONE);

        Q3_Imageview = (TextView) findViewById(R.id.image_Q3);
        Image_layout_Q3 = (ImageView) findViewById(R.id.image_layout_Q3);
        Image_layout_Q3.setVisibility(View.GONE);

        Q4_Imageview = (TextView) findViewById(R.id.image_Q4);
        Image_layout_Q4 = (ImageView) findViewById(R.id.image_layout_Q4);
        Image_layout_Q4.setVisibility(View.GONE);


        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        if(id!=null){
            QSR_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!QSR_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(QSR_Title_1.Main_ID);
            }

        }




        Image_layout_Q4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Image_layout_Q4.setImageResource(0);

                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                if (Image_layout_Q4.getDrawable() == null) {

                    Image_layout_Q4.setVisibility(View.GONE);
                } else {
                    Image_layout_Q4.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        Q4_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image_layout_Q4.setVisibility(View.VISIBLE);


                if (Image_layout_Q4.getDrawable() == null) {
                    ImageCheck = "4444";
                    selectImage();
                }
            }
        });


        Image_layout_Q3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Image_layout_Q3.setImageResource(0);

                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                if (Image_layout_Q3.getDrawable() == null) {

                    Image_layout_Q3.setVisibility(View.GONE);
                } else {
                    Image_layout_Q3.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        Q3_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image_layout_Q3.setVisibility(View.VISIBLE);


                if (Image_layout_Q3.getDrawable() == null) {
                    ImageCheck = "3333";
                    selectImage();
                }
            }
        });

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(QSR_Cus_Pest_3.this);
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
                        Toast.makeText(QSR_Cus_Pest_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(QSR_Cus_Pest_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }








        Image_layout_Q2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Image_layout_Q2.setImageResource(0);

                Toast.makeText(getApplicationContext(), "Image Deleted", Toast.LENGTH_SHORT).show();
                if (Image_layout_Q2.getDrawable() == null) {

                    Image_layout_Q2.setVisibility(View.GONE);
                } else {
                    Image_layout_Q2.setVisibility(View.VISIBLE);
                }
                return false;
            }
        });

        Q2_Imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Image_layout_Q2.setVisibility(View.VISIBLE);


                if (Image_layout_Q2.getDrawable() == null) {
                    ImageCheck = "2222";
                    selectImage();
                }
            }
        });





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
                    ImageCheck = "1818";
                    selectImage();
                }
            }
        });

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        qsrcuspestsub =(Button) findViewById(R.id.qsrcuspestsub);
        qsrcuspestbck=(Button) findViewById(R.id.qsrcuspestbck);
        qsrcuspestsub1=(Button) findViewById(R.id.qsrcuspestsub1);

        qsrcuspestsub1.setVisibility(View.GONE);

        qsrcuspestsub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                Intent i = new Intent(QSR_Cus_Pest_3.this,QSR_Facility_4.class);
                startActivity(i);
            }
        });

        et1=(EditText) findViewById(R.id.qsr_cuspest_et_exoplaced);
        et2=(EditText) findViewById(R.id.qsr_cuspest_et_imtplaced);

        rd1=(RadioGroup) findViewById(R.id.qsr_cuspest_rd1);
        rd2=(RadioGroup) findViewById(R.id.qsr_cuspest_rd2);
        rd3=(RadioGroup) findViewById(R.id.qsr_cuspest_rd3);
        rd4=(RadioGroup) findViewById(R.id.qsr_cuspest_rd4);
        rd5=(RadioGroup) findViewById(R.id.qsr_cuspest_rd5);
        rd6=(RadioGroup) findViewById(R.id.qsr_cuspest_rd6);
        rd7=(RadioGroup) findViewById(R.id.qsr_cuspest_rd7);
        rd8=(RadioGroup) findViewById(R.id.qsr_cuspest_rd8);
        rd9=(RadioGroup) findViewById(R.id.qsr_cuspest_rd9);
        rd10=(RadioGroup) findViewById(R.id.qsr_cuspest_rd10);
        rd11=(RadioGroup) findViewById(R.id.qsr_cuspest_rd11);
        rd12=(RadioGroup) findViewById(R.id.qsr_cuspest_rd12);
        rd13=(RadioGroup) findViewById(R.id.qsr_cuspest_rd13);
        rd14=(RadioGroup) findViewById(R.id.qsr_cuspest_rd14);
        rd15=(RadioGroup) findViewById(R.id.qsr_cuspest_rd15);
        rd16=(RadioGroup) findViewById(R.id.qsr_cuspest_rd16);
        rd17=(RadioGroup) findViewById(R.id.qsr_cuspest_rd17);
        rd18=(RadioGroup) findViewById(R.id.qsr_cuspest_rd18);

        Cursor c101;
        c101 = sd.rawQuery("Select * from " + db.CHECK_STATUS_TABLE, null);
        c101.moveToFirst();

        Log.e("UUUUUUU","status ="+c101.getString(c101.getColumnIndex(db.STATUS)));

        if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("101")){
            qsrcuspestsub.setVisibility(View.GONE);
            qsrcuspestbck.setVisibility(View.GONE);
            qsrcuspestsub1.setVisibility(View.VISIBLE);

        }
        else  if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("100")){

            qsrcuspestsub.setVisibility(View.VISIBLE);
            qsrcuspestbck.setVisibility(View.VISIBLE);
            qsrcuspestsub1.setVisibility(View.GONE);

        }


        rd1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd1.getCheckedRadioButtonId();

                rbtn1=(RadioButton)findViewById(selectedId);

                getrbtn1 = ((RadioButton)findViewById(rd1.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd2.getCheckedRadioButtonId();

                rbtn2=(RadioButton)findViewById(selectedId);

                getrbtn2 = ((RadioButton)findViewById(rd2.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd3.getCheckedRadioButtonId();

                rbtn3=(RadioButton)findViewById(selectedId);

                getrbtn3 = ((RadioButton)findViewById(rd3.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd4.getCheckedRadioButtonId();

                rbtn4=(RadioButton)findViewById(selectedId);

                getrbtn4 = ((RadioButton)findViewById(rd4.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd5.getCheckedRadioButtonId();

                rbtn5=(RadioButton)findViewById(selectedId);

                getrbtn5 = ((RadioButton)findViewById(rd5.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd6.getCheckedRadioButtonId();

                rbtn6=(RadioButton)findViewById(selectedId);

                getrbtn6 = ((RadioButton)findViewById(rd6.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd7.getCheckedRadioButtonId();

                rbtn7=(RadioButton)findViewById(selectedId);

                getrbtn7 = ((RadioButton)findViewById(rd7.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd8.getCheckedRadioButtonId();

                rbtn8=(RadioButton)findViewById(selectedId);

                getrbtn8 = ((RadioButton)findViewById(rd8.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd9.getCheckedRadioButtonId();

                rbtn9=(RadioButton)findViewById(selectedId);

                getrbtn9 = ((RadioButton)findViewById(rd9.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd10.getCheckedRadioButtonId();

                rbtn10=(RadioButton)findViewById(selectedId);

                getrbtn10 = ((RadioButton)findViewById(rd10.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd11.getCheckedRadioButtonId();

                rbtn11=(RadioButton)findViewById(selectedId);

                getrbtn11 = ((RadioButton)findViewById(rd11.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd12.getCheckedRadioButtonId();

                rbtn12=(RadioButton)findViewById(selectedId);

                getrbtn12 = ((RadioButton)findViewById(rd12.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd13.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd13.getCheckedRadioButtonId();

                rbtn13=(RadioButton)findViewById(selectedId);

                getrbtn13 = ((RadioButton)findViewById(rd13.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd14.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd14.getCheckedRadioButtonId();

                rbtn14=(RadioButton)findViewById(selectedId);

                getrbtn14 = ((RadioButton)findViewById(rd14.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd15.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd15.getCheckedRadioButtonId();

                rbtn15=(RadioButton)findViewById(selectedId);

                getrbtn15 = ((RadioButton)findViewById(rd15.getCheckedRadioButtonId())).getText().toString();

            }
        });
        rd16.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd16.getCheckedRadioButtonId();

                rbtn16=(RadioButton)findViewById(selectedId);

                getrbtn16 = ((RadioButton)findViewById(rd16.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd17.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd17.getCheckedRadioButtonId();

                rbtn17=(RadioButton)findViewById(selectedId);

                getrbtn17 = ((RadioButton)findViewById(rd17.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rd18.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rd18.getCheckedRadioButtonId();

                rbtn18=(RadioButton)findViewById(selectedId);

                getrbtn18 = ((RadioButton)findViewById(rd18.getCheckedRadioButtonId())).getText().toString();

            }
        });






        qsrcuspestsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(QSR_Cus_Pest_3.this);
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
                                Toast.makeText(QSR_Cus_Pest_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(QSR_Cus_Pest_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }



                if (validation()) {

                    qsr_cus_pest_button_next();
                    post_cust_pest_js();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fileds",Toast.LENGTH_SHORT ).show();
                }


            }
        });

        qsrcuspestbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qsr_cus_pest_button_back();

                Intent i = new Intent(QSR_Cus_Pest_3.this,QSR_Cus_Info_2.class);
                startActivity(i);
            }
        });
    }
      public boolean validation (){
          getet1=et1.getText().toString();
          getet2=et2.getText().toString();
          bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
          bitmapImage2=((BitmapDrawable) Image_layout_Q2.getDrawable()).getBitmap();
          bitmapImage3=((BitmapDrawable) Image_layout_Q3.getDrawable()).getBitmap();
          bitmapImage4=((BitmapDrawable) Image_layout_Q4.getDrawable()).getBitmap();



          if(TextUtils.isEmpty(getet1 ) || TextUtils.isEmpty(getet2 )) {

              if (TextUtils.isEmpty(getet1)) {
                  et1.setError("Required");
              }

              if (TextUtils.isEmpty(getet2)) {
                  et2.setError("Required");
              }

              return false;
          }


          if (bitmapImage1!=null) {
              bitmapst=getStringImage(bitmapImage1);
          }else {
              bitmapst="";
          }

          if (bitmapImage2!=null) {
              bitmapst2=getStringImage(bitmapImage2);
          }else {
              bitmapst2="";
          }
          if (bitmapImage3!=null) {
              bitmapst3=getStringImage(bitmapImage3);
          }else {
              bitmapst3="";
          }
          if (bitmapImage4!=null) {
              bitmapst4=getStringImage(bitmapImage4);
          }else {
              bitmapst4="";
          }
          if(getet1.length()==0||getet2.length()==0||getrbtn1==null||getrbtn2==null||getrbtn7==null ||getrbtn3==null||getrbtn8==null
                  ||getrbtn4==null||getrbtn9==null ||getrbtn5==null||getrbtn10==null ||getrbtn6==null||getrbtn11==null ||getrbtn12==null||getrbtn17==null ||getrbtn13==null||getrbtn18==null ||getrbtn14==null ||getrbtn15==null ||getrbtn16==null                  ){
              return false;

          }else {
              return  true;
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

        AlertDialog.Builder builder = new AlertDialog.Builder(QSR_Cus_Pest_3.this);
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
                if (ImageCheck.equals("1818")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("1818"));
                    if (ImageCheck.equals("1818")) {
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
                if (ImageCheck.equals("2222")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("2222"));
                    if (ImageCheck.equals("2222")) {
                        Image_layout_Q2.setVisibility(View.VISIBLE);

                        try {
                            Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));

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

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q3);
                    } else {
                        Image_layout_Q3.setVisibility(View.GONE);
                        Image_layout_Q3.setImageBitmap(null);


                    }

                }

                if (ImageCheck.equals("4444")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("4444"));
                    if (ImageCheck.equals("4444")) {
                        Image_layout_Q4.setVisibility(View.VISIBLE);

                        try {
                            Image_layout_Q4.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));

                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                        Log.e("IMAGEEEE", "" + Image_layout_Q4);
                    } else {
                        Image_layout_Q4.setVisibility(View.GONE);
                        Image_layout_Q4.setImageBitmap(null);


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
                    if (ImageCheck.equals("1818")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("1818"));
                        if (ImageCheck.equals("1818")) {
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

                    if (ImageCheck.equals("2222")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("2222"));
                        if (ImageCheck.equals("2222")) {

                            Image_layout_Q2.setVisibility(View.VISIBLE);
                            try {

                                Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
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
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q3);
                        } else {


                            Image_layout_Q3.setVisibility(View.GONE);
                            Image_layout_Q3.setImageBitmap(null);
                        }
                    }

                    if (ImageCheck.equals("4444")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("4444"));
                        if (ImageCheck.equals("4444")) {

                            Image_layout_Q4.setVisibility(View.VISIBLE);
                            try {

                                Image_layout_Q4.setImageBitmap(Bitmap.createScaledBitmap(bitmap, 250, 250, false));
                            } catch (NullPointerException e) {
                                e.printStackTrace();
                            }
                            Log.e("IMAGEEEE", "" + Image_layout_Q4);
                        } else {


                            Image_layout_Q4.setVisibility(View.GONE);
                            Image_layout_Q4.setImageBitmap(null);
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

        if (ActivityCompat.shouldShowRequestPermissionRationale(QSR_Cus_Pest_3.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(QSR_Cus_Pest_3.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(QSR_Cus_Pest_3.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(QSR_Cus_Pest_3.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(QSR_Cus_Pest_3.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }

    private void post_cust_pest_js() {


        Map<String, String> params = new HashMap<String, String>();
        params.put("radio1w",getrbtn1);
        params.put("radio2w",getrbtn2);
        params.put("radio3w",getrbtn3);
        params.put("radio4w",getrbtn4);
        params.put("radio5w",getrbtn5);
        params.put("radio6w",getrbtn6);
        params.put("radio7w",getrbtn7);
        params.put("radio8w",getrbtn8);
        params.put("radio9w",getrbtn9);
        params.put("radio10w",getrbtn10);
        params.put("radio11w",getrbtn11);
        params.put("radio12w",getrbtn12);
        params.put("radio13w",getrbtn13);
        params.put("radio14w",getrbtn14);
        params.put("radio15w",getrbtn15);
        params.put("radio16w",getrbtn16);
        params.put("radio17w",getrbtn17);
        params.put("radio18w",getrbtn18);
        params.put("getexow",getet1);
        params.put("getimow",getet2);
        params.put("getimage1",bitmapst);
        params.put("getimage2",bitmapst2);
        params.put("getimage3",bitmapst3);
        params.put("getimage4",bitmapst4);
        params.put("main_id",QSR_Title_1.Main_ID);
        params.put("update_status",Update_Status);
        Log.e("FFFFF","getParams"+params);




        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/QSR/in_qsr_cus_pests_3.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e( "JJJJJJJ","QSR cus 3"+response );
                            Intent i = new Intent(QSR_Cus_Pest_3.this,QSR_Facility_4.class);
                            startActivity(i);
                            pd.dismiss();

                            //  Toast.makeText( QSR_Cus_Pest_3.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
Log.e("EEEGF","error = "+error.getMessage());


                        pd.dismiss();

if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference")){

    Intent i = new Intent(QSR_Cus_Pest_3.this,QSR_Facility_4.class);
    startActivity(i);

}

                        //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }

    public void onBackPressed(){

    }

//    private void post_cust_pest_js() {
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://rauditor.riflows.com/rauditor/Android/QSR/in_qsr_cus_pests_3.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
//                     //   Toast.makeText(QSR_Cus_Pest_3.this,response,Toast.LENGTH_LONG).show();
//                        Log.e( "JJJJJJJ","QSR cus 3"+response );
//                        Intent i = new Intent(QSR_Cus_Pest_3.this,QSR_Facility_4.class);
//                        startActivity(i);
//
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
//
//                    }
//                }){
//            @Override
//            protected Map<String,String> getParams(){
//                Map<String,String> params = new HashMap<String, String>();
//
//                params.put("radio1w",getrbtn1);
//                params.put("radio2w",getrbtn2);
//                params.put("radio3w",getrbtn3);
//                params.put("radio4w",getrbtn4);
//                params.put("radio5w",getrbtn5);
//                params.put("radio6w",getrbtn6);
//                params.put("radio7w",getrbtn7);
//                params.put("radio8w",getrbtn8);
//                params.put("radio9w",getrbtn9);
//                params.put("radio10w",getrbtn10);
//                params.put("radio11w",getrbtn11);
//                params.put("radio12w",getrbtn12);
//                params.put("radio13w",getrbtn13);
//                params.put("radio14w",getrbtn14);
//                params.put("radio15w",getrbtn15);
//                params.put("radio16w",getrbtn16);
//                params.put("radio17w",getrbtn17);
//                params.put("radio18w",getrbtn18);
//                params.put("getexow",getet1);
//                params.put("getimow",getet2);
//                params.put("getimage1",bitmapst);
//                params.put("getimage2",bitmapst2);
//                params.put("getimage3",bitmapst3);
//                params.put("getimage4",bitmapst4);
//                params.put("main_id",QSR_Title_1.Main_ID);
//                params.put("update_status",Update_Status);
//
//
//
//
//
//
//                Log.e("MMMMM 444",""+params);
//
//                return params;
//            }
//
//        };
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                30000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        requestQueue.add(stringRequest);
//    }


    private void getData(String key_id){


        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            QSR_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/QSR/get_qsr_cus_pests_3.php?main_id="+QSR_Title_1.Main_ID  ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("BNBN","qsr 3 res ="+response);


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {

                       Log.e("VBVB","FGFG");
                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "main_id" );
                                String getexo = c.getString( "no_exo" );
                                String getimt = c.getString( "no_imt" );
                                String get_cu_pes = c.getString( "cu_pes" );
                                String get_occ = c.getString( "occ" );
                                String get_po_cus = c.getString( "po_cus" );
                                String get_branch_licen = c.getString( "branch_licen" );
                                String get_adv_ser = c.getString( "adv_ser" );
                                String get_list_apl = c.getString( "list_apl" );
                                String get_msds= c.getString( "msds" );
                                String get_app_pre= c.getString( "app_pre" );
                                String get_list_anti= c.getString( "list_anti" );
                                String get_tech_certi= c.getString( "tech_certi" );
                                String get_scs_pms= c.getString( "scs_pms" );
                                String get_floor_plan_rodent= c.getString( "floor_plan_rodent" );
                                String get_rodent_grouted= c.getString( "rodent_grouted" );
                                String get_rodent_glue= c.getString( "rodent_glue" );
                                String get_floor_plan_cock= c.getString( "floor_plan_cock" );
                                String get_floor_plan_insect= c.getString( "floor_plan_insect" );
                                String get_audit_rep= c.getString( "audit_rep" );
                                String get_pest_count= c.getString( "pest_count" );
                                String get_image_123= c.getString( "image1" );
                                String get_image_qsr_2= c.getString( "image2" );
                                String get_image_qsr_3= c.getString( "image3" );
                                String get_image_qsr_4= c.getString( "image4" );
                                Log.e("EEED","before db");

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.QSR_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDFCSGFS","update value = "+c5.getString(c5.getColumnIndex(db.QSR_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.QSR_COMPLETE_STATUS));

                                Log.e("EEED","before else");

                                if(get_pmca_complete.equalsIgnoreCase("2")) {


                                    qsrcuspestsub.setVisibility(View.GONE);
                                    qsrcuspestbck.setVisibility(View.GONE);
                                    qsrcuspestsub1.setVisibility(View.VISIBLE);


                                    Log.e("EEE", "qsr entry1");

                                    et1.setText(getexo);
                                    et2.setText(getimt);


                                    et1.setEnabled(false);
                                    et2.setEnabled(false);

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
                                    rd16.getChildAt(3).setEnabled(false);

                                    rd17.getChildAt(0).setEnabled(false);
                                    rd17.getChildAt(1).setEnabled(false);
                                    rd17.getChildAt(2).setEnabled(false);

                                    rd18.getChildAt(0).setEnabled(false);
                                    rd18.getChildAt(1).setEnabled(false);
                                    rd18.getChildAt(2).setEnabled(false);

                                    if(get_cu_pes.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_cu_pes.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd1.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_cu_pes.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd1.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_occ.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_occ.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_occ.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd2.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_po_cus.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_po_cus.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_po_cus.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd3.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_branch_licen.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_branch_licen.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_branch_licen.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd4.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_adv_ser.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd5.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_adv_ser.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd5.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_adv_ser.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd5.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_list_apl.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd6.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_list_apl.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd6.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_list_apl.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd6.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_msds.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_msds.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd7.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_msds.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd7.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_app_pre.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_app_pre.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd7.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_app_pre.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd7.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_list_anti.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd8.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_list_anti.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd8.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_list_anti.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd8.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_tech_certi.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_tech_certi.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_tech_certi.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_scs_pms.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_scs_pms.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_scs_pms.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_floor_plan_rodent.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_rodent.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_tech_certi.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd10.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_tech_certi.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd10.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_tech_certi.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd10.getChildAt(2)).setChecked(true);

                                    }



                                    if(get_scs_pms.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd11.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_scs_pms.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd11.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_scs_pms.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd11.getChildAt(2)).setChecked(true);

                                    }




                                    if(get_floor_plan_rodent.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd12.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd12.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_rodent.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd12.getChildAt(2)).setChecked(true);

                                    }



                                    if(get_rodent_grouted.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd13.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_grouted.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd13.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_rodent_grouted.equalsIgnoreCase("DAMAGED")){

                                        ((RadioButton) rd13.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_rodent_glue.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd14.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_glue.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd14.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_rodent_glue.equalsIgnoreCase("NA")){

                                        ((RadioButton) rd14.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_floor_plan_cock.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd15.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_cock.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd15.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_cock.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd15.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_floor_plan_insect.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd16.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_insect.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd16.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_insect.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd16.getChildAt(2)).setChecked(true);

                                    }
                                    else if(get_floor_plan_insect.equalsIgnoreCase("NOT APPLICABLE")){

                                        ((RadioButton) rd16.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_audit_rep.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd17.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_audit_rep.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd17.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_audit_rep.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd17.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_pest_count.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd18.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pest_count.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd18.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pest_count.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd18.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_image_123.length()!=0){
                                        Image_1=get_image_123;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_image_123, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));


                                    if(get_image_qsr_2.length()!=0){
                                        Image_2=get_image_qsr_2;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    byte[] decodedString_c2 = Base64.decode(get_image_qsr_2, Base64.DEFAULT);
                                    Bitmap decodedByte_c2 = BitmapFactory.decodeByteArray(decodedString_c2, 0, decodedString_c2.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c2, 400, 400, false));


                                                                   if(get_image_qsr_3.length()!=0){
                                        Image_3=get_image_qsr_3;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    byte[] decodedString_c3 = Base64.decode(get_image_qsr_3, Base64.DEFAULT);
                                    Bitmap decodedByte_c3 = BitmapFactory.decodeByteArray(decodedString_c3, 0, decodedString_c3.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q3.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c3, 400, 400, false));


                                    if(get_image_qsr_4.length()!=0){
                                        Image_4=get_image_qsr_4;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    byte[] decodedString_c4 = Base64.decode(get_image_qsr_4, Base64.DEFAULT);
                                    Bitmap decodedByte_c4 = BitmapFactory.decodeByteArray(decodedString_c4, 0, decodedString_c4.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q4.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c4, 400, 400, false));





                                    Update_Status="2";

                                    pd.dismiss();


                                }
                                else if(get_pmca_complete.equalsIgnoreCase("1")) {




                                    Log.e("EEE", "qsr entry1");

                                    if(get_cu_pes.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_cu_pes.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd1.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_cu_pes.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd1.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_occ.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_occ.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_occ.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd2.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_po_cus.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_po_cus.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd3.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_po_cus.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd3.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_branch_licen.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_branch_licen.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd4.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_branch_licen.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd4.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_adv_ser.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd5.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_adv_ser.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd5.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_adv_ser.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd5.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_list_apl.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd6.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_list_apl.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd6.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_list_apl.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rd6.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_msds.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_msds.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd7.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_msds.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd7.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_app_pre.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_app_pre.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd7.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_app_pre.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd7.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_list_anti.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd8.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_list_anti.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd8.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_list_anti.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd8.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_tech_certi.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_tech_certi.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_tech_certi.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_scs_pms.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_scs_pms.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_scs_pms.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_floor_plan_rodent.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd9.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_rodent.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd9.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_tech_certi.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd10.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_tech_certi.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd10.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_tech_certi.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd10.getChildAt(2)).setChecked(true);

                                    }



                                    if(get_scs_pms.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd11.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_scs_pms.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd11.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_scs_pms.equalsIgnoreCase("NOT AVAILABLE")) {

                                        ((RadioButton) rd11.getChildAt(2)).setChecked(true);

                                    }




                                    if(get_floor_plan_rodent.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd12.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_rodent.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd12.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_rodent.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd12.getChildAt(2)).setChecked(true);

                                    }



                                    if(get_rodent_grouted.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd13.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_grouted.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd13.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_rodent_grouted.equalsIgnoreCase("DAMAGED")){

                                        ((RadioButton) rd13.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_rodent_glue.equalsIgnoreCase("YES")){
                                        ((RadioButton) rd14.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_glue.equalsIgnoreCase("NO")){

                                        ((RadioButton) rd14.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_rodent_glue.equalsIgnoreCase("NA")){

                                        ((RadioButton) rd14.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_floor_plan_cock.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd15.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_cock.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd15.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_cock.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd15.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_floor_plan_insect.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd16.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_floor_plan_insect.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd16.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_floor_plan_insect.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd16.getChildAt(2)).setChecked(true);

                                    }
                                    else if(get_floor_plan_insect.equalsIgnoreCase("NOT APPLICABLE")){

                                        ((RadioButton) rd16.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_audit_rep.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd17.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_audit_rep.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd17.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_audit_rep.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd17.getChildAt(2)).setChecked(true);

                                    }


                                    if(get_pest_count.equalsIgnoreCase("YES,UPDATED")){
                                        ((RadioButton) rd18.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pest_count.equalsIgnoreCase("YES,BUT NOT UPDATED")){

                                        ((RadioButton) rd18.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pest_count.equalsIgnoreCase("NOT AVAILABLE")){

                                        ((RadioButton) rd18.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_image_123.length()!=0){
                                        Image_1=get_image_123;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_image_123, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));


                                    if(get_image_qsr_2.length()!=0){
                                        Image_2=get_image_qsr_2;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    byte[] decodedString_c2 = Base64.decode(get_image_qsr_2, Base64.DEFAULT);
                                    Bitmap decodedByte_c2 = BitmapFactory.decodeByteArray(decodedString_c2, 0, decodedString_c2.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c2, 400, 400, false));


                                    if(get_image_qsr_3.length()!=0){
                                        Image_3=get_image_qsr_3;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    byte[] decodedString_c3 = Base64.decode(get_image_qsr_3, Base64.DEFAULT);
                                    Bitmap decodedByte_c3 = BitmapFactory.decodeByteArray(decodedString_c3, 0, decodedString_c3.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q3.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c3, 400, 400, false));


                                    if(get_image_qsr_4.length()!=0){
                                        Image_4=get_image_qsr_4;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    byte[] decodedString_c4 = Base64.decode(get_image_qsr_4, Base64.DEFAULT);
                                    Bitmap decodedByte_c4 = BitmapFactory.decodeByteArray(decodedString_c4, 0, decodedString_c4.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q4.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c4, 400, 400, false));


                                    et1.setText(getexo);
                                    et2.setText(getimt);

                                    Update_Status="1";

pd.dismiss();

                                }




                                                         }

                            //   Toast.makeText( QSR_Cus_Pest_3.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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
                    }
                } )
                .requestJson();
    }
    private void qsr_cus_pest_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_sub_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void qsr_cus_pest_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_bck_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }



}
