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

public class QSR_Facility_4 extends AppCompatActivity {

    Button qsrfacilitysub,qsrfacilitybck;

    TextView txt1,txt2,txt3,txt4,txt5,txt5_1,txt6,txt7,txt8,txt9,txt10,txt11,txt12;

    TextView Q1_Imageview,Q2_Imageview;
    ImageView Image_layout_Q1,Image_layout_Q2;
    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    String Image_1_qsr,Image_2_qsr;
    Bitmap bitmapImage1,bitmapImage2;

    byte[] img_by1;
    ByteArrayInputStream imageStream1;
    String ImageCheck = "";
    Uri uri;
    //   String bitmapst;
    String mCurrentPhotoPath;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    private android.app.AlertDialog pd;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    String bitmapsts1,bitmapsts2;


    private FirebaseAnalytics mFirebaseAnalytics;

    public static final int RequestPermissionCode = 1;

    public  static TextView dis1,dis2,dis3,dis4,dis5,dis5_1,dis6,dis7,dis8,dis9,dis10,dis11,dis12;

    String getrd1,getrd2,getrd3,getrd4,getrd5,getrd6,getrd7,getrd8,getrd9,getrd10,getrd11,getrd12,etget,displs2;

    RadioGroup rdg1,rdg2,rdg3,rdg4,rdg5,rdg6,rdg7,rdg8,rdg9,rdg10,rdg11,rdg12;

    RadioButton rdbtn1,rdbtn2,rdbtn3,rdbtn4,rdbtn5,rdbtn6,rdbtn7,rdbtn8,rdbtn9,rdbtn10,rdbtn11,rdbtn12;

    EditText et1;

    Button qsrfacilitysub1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qsr_facilityinfo);


        Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (ImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        Q2_Imageview = (TextView) findViewById(R.id.image_Q2);
        Image_layout_Q2 = (ImageView) findViewById(R.id.image_layout_Q2);
        Image_layout_Q2.setVisibility(View.GONE);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


        db = new DatabaseHelper(QSR_Facility_4.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

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
                    ImageCheck = "8888";
                    selectImage();
                }
            }
        });


        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );


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
                    ImageCheck = "7777";
                    selectImage();
                }
            }
        });



        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(QSR_Facility_4.this);
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
                        Toast.makeText(QSR_Facility_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(QSR_Facility_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }



        rdg1=(RadioGroup) findViewById(R.id.qsr_faci_radgroup1);
        rdg2=(RadioGroup) findViewById(R.id.qsr_faci_radgroup2);
        rdg3=(RadioGroup) findViewById(R.id.qsr_faci_radgroup3);
        rdg4=(RadioGroup) findViewById(R.id.qsr_faci_radgroup4);
        rdg5=(RadioGroup) findViewById(R.id.qsr_faci_radgroup5);
        rdg6=(RadioGroup) findViewById(R.id.qsr_faci_radgroup6);
        rdg7=(RadioGroup) findViewById(R.id.qsr_faci_radgroup7);
        rdg8=(RadioGroup) findViewById(R.id.qsr_faci_radgroup8);
        rdg9=(RadioGroup) findViewById(R.id.qsr_faci_radgroup9);
        rdg10=(RadioGroup) findViewById(R.id.qsr_faci_radgroup10);
        rdg11=(RadioGroup) findViewById(R.id.qsr_faci_radgroup11);
        rdg12=(RadioGroup) findViewById(R.id.qsr_faci_radgroup12);


        final Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            QSR_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!QSR_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(QSR_Title_1.Main_ID);
            }

        }






        qsrfacilitysub =(Button) findViewById(R.id.qsrfacilitysub);
        qsrfacilitybck=(Button) findViewById(R.id.qsrfacilitybck);
        qsrfacilitysub1=(Button) findViewById(R.id.qsrfacilitysub1);

        qsrfacilitysub1.setVisibility(View.GONE);
        pd = new SpotsDialog(QSR_Facility_4.this, R.style.Custom);

        qsrfacilitysub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();
                Intent i = new Intent(QSR_Facility_4.this,QSR_Obser_5.class);
                startActivity(i);

            }
        });


        et1=(EditText) findViewById(R.id.qsr_facility_et1) ;


        txt2=(TextView) findViewById(R.id.qsr_facility_selectrespon2);

        dis2=(TextView) findViewById(R.id.qsr_facility_disp2);


        Cursor c101;
        c101 = sd.rawQuery("Select * from " + db.CHECK_STATUS_TABLE, null);
        c101.moveToFirst();

        Log.e("UUUUUUU","status ="+c101.getString(c101.getColumnIndex(db.STATUS)));

        if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("101")){
            qsrfacilitysub.setVisibility(View.GONE);
            qsrfacilitybck.setVisibility(View.GONE);

            qsrfacilitysub1.setVisibility(View.VISIBLE);

        }
        else  if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("100")){

            qsrfacilitysub.setVisibility(View.VISIBLE);
            qsrfacilitybck.setVisibility(View.VISIBLE);

            qsrfacilitysub1.setVisibility(View.GONE);

        }




        rdg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg1.getCheckedRadioButtonId();

                rdbtn1=(RadioButton)findViewById(selectedId);

                getrd1 = ((RadioButton)findViewById(rdg1.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg2.getCheckedRadioButtonId();

                rdbtn2=(RadioButton)findViewById(selectedId);

                getrd2 = ((RadioButton)findViewById(rdg2.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg3.getCheckedRadioButtonId();

                rdbtn3=(RadioButton)findViewById(selectedId);

                getrd3 = ((RadioButton)findViewById(rdg3.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg4.getCheckedRadioButtonId();

                rdbtn4=(RadioButton)findViewById(selectedId);

                getrd4 = ((RadioButton)findViewById(rdg4.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg5.getCheckedRadioButtonId();

                rdbtn5=(RadioButton)findViewById(selectedId);

                getrd5 = ((RadioButton)findViewById(rdg5.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg6.getCheckedRadioButtonId();

                rdbtn6=(RadioButton)findViewById(selectedId);

                getrd6 = ((RadioButton)findViewById(rdg6.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg7.getCheckedRadioButtonId();

                rdbtn7=(RadioButton)findViewById(selectedId);

                getrd7 = ((RadioButton)findViewById(rdg7.getCheckedRadioButtonId())).getText().toString();

            }
        });


        rdg8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg8.getCheckedRadioButtonId();

                rdbtn8=(RadioButton)findViewById(selectedId);

                getrd8 = ((RadioButton)findViewById(rdg8.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg9.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg9.getCheckedRadioButtonId();

                rdbtn9=(RadioButton)findViewById(selectedId);

                getrd9 = ((RadioButton)findViewById(rdg9.getCheckedRadioButtonId())).getText().toString();

            }
        });


        rdg10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg10.getCheckedRadioButtonId();

                rdbtn10=(RadioButton)findViewById(selectedId);

                getrd10 = ((RadioButton)findViewById(rdg10.getCheckedRadioButtonId())).getText().toString();

            }
        });

        rdg11.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg11.getCheckedRadioButtonId();

                rdbtn11=(RadioButton)findViewById(selectedId);

                getrd11 = ((RadioButton)findViewById(rdg11.getCheckedRadioButtonId())).getText().toString();

            }
        });


        rdg12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=rdg12.getCheckedRadioButtonId();

                rdbtn12=(RadioButton)findViewById(selectedId);

                getrd12 = ((RadioButton)findViewById(rdg12.getCheckedRadioButtonId())).getText().toString();

            }
        });






        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsrfacilityselect(117);

            }
        });





        qsrfacilitysub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(QSR_Facility_4.this);
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
                                Toast.makeText(QSR_Facility_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(QSR_Facility_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {

                    qsr_facility_button_next();
                    post_facility_js();
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }


            }
        });

        qsrfacilitybck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsr_facility_button_back();
                Intent i = new Intent(QSR_Facility_4.this,QSR_Cus_Pest_3.class);
                startActivity(i);
            }
        });



    }
    public  boolean validation(){


        try {
            displs2=dis2.getText().toString();


            etget=et1.getText().toString();

            bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
            bitmapImage2=((BitmapDrawable) Image_layout_Q2.getDrawable()).getBitmap();


            if(TextUtils.isEmpty(displs2 ) || TextUtils.isEmpty(etget )) {

                if (TextUtils.isEmpty(displs2)) {
                    dis2.setError("Required");
                }

                if (TextUtils.isEmpty(etget)) {
                    et1.setError("Required");
                }

                return false;
            }


            if (bitmapImage1!=null) {
                bitmapsts1=getStringImage(bitmapImage1);
            }else {
                bitmapsts1="";
            }

            if (bitmapImage2!=null) {
                bitmapsts2=getStringImage(bitmapImage2);
            }else {
                bitmapsts2="";
            }
            if(displs2.length()==0||etget.length()==0){
                return  false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
        }

    }

    public void qsrfacilityselect(int status) {

        Bundle bundle = new Bundle();
       if(status==117){

            bundle.putString("status", "117");

        }


        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
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

        AlertDialog.Builder builder = new AlertDialog.Builder(QSR_Facility_4.this);
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
                if (ImageCheck.equals("7777")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("7777"));
                    if (ImageCheck.equals("7777")) {
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
                if (ImageCheck.equals("8888")) {
                    Log.e("ImageCheck11", "" + ImageCheck.equals("8888"));
                    if (ImageCheck.equals("8888")) {
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
                    if (ImageCheck.equals("7777")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("7777"));
                        if (ImageCheck.equals("7777")) {
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

                    if (ImageCheck.equals("8888")) {
                        Log.e("ImageCheck11", "" + ImageCheck.equals("8888"));
                        if (ImageCheck.equals("8888")) {

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

        if (ActivityCompat.shouldShowRequestPermissionRationale(QSR_Facility_4.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(QSR_Facility_4.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(QSR_Facility_4.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(QSR_Facility_4.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(QSR_Facility_4.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }

    private void post_facility_js() {

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("getdisp1",getrd1);
        params.put("getdisp3",getrd2);
        params.put("getdisp4",getrd3);
        params.put("getdisp5",getrd4);
        params.put("getdisp5_1",getrd5);
        params.put("getdisp6",getrd6);
        params.put("getdisp7",getrd7);
        params.put("getdisp8",getrd8);
        params.put("getdisp9",getrd9);
        params.put("getdisp10",getrd10);
        params.put("getdisp11",getrd11);
        params.put("getdisp12",getrd12);
        params.put("getdisp2",displs2);
        params.put("getet1",etget);
        params.put("gtimage1",bitmapsts1);
        params.put("gtimage2",bitmapsts2);
        params.put("main_id",QSR_Title_1.Main_ID);
        params.put("update_status",Update_Status);

        Log.e("VCVGH","params" +params);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/QSR/in_qsr_facilityinfo_4.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e( "JJJJJ","QSR Fac"+response);
                            Intent i = new Intent(QSR_Facility_4.this,QSR_Obser_5.class);
                            startActivity(i);

//                            Toast.makeText( QSR_Facility_4.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("DDDFG","error qsr fac4= "+error.getMessage());

                        if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"));

                        {

                            Intent i = new Intent(QSR_Facility_4.this,QSR_Obser_5.class);
                            startActivity(i);
                        }

                    }
                } )
                .requestString();




    }



    public void onBackPressed(){

    }

    private void getData(String key_id){

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            QSR_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/QSR/get_qsr_facilityinfo_4.php?main_id="+QSR_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("RTYTR","res = "+response);


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "main_id" );
                                String getpro = c.getString( "pro_info" );
                                String getrodent = c.getString( "rodent_sign" );


                                String get_rodent_infe = c.getString( "rodent_infe" );
                                String get_qsr_image1 = c.getString( "image1" );
                                String get_qsr_image2 = c.getString( "image2" );
                                String get_main_door = c.getString( "main_door" );
                                String get_sliding_door = c.getString( "sliding_door" );
                                String get_gaps = c.getString( "gaps" );
                                String get_auto_door = c.getString( "auto_door" );
                                String get_air_curt = c.getString( "air_curt" );
                                String get_air_func = c.getString( "air_func" );
                                String get_air_present = c.getString( "air_present" );
                                String get_air_align = c.getString( "air_align" );
                                String get_type_ilt = c.getString( "type_ilt" );
                                String get_ilt_instal = c.getString( "ilt_instal" );
                                String get_control_board = c.getString( "control_board" );

                                rdg1=(RadioGroup) findViewById(R.id.qsr_faci_radgroup1);
                                rdg2=(RadioGroup) findViewById(R.id.qsr_faci_radgroup2);
                                rdg3=(RadioGroup) findViewById(R.id.qsr_faci_radgroup3);
                                rdg4=(RadioGroup) findViewById(R.id.qsr_faci_radgroup4);
                                rdg5=(RadioGroup) findViewById(R.id.qsr_faci_radgroup5);
                                rdg6=(RadioGroup) findViewById(R.id.qsr_faci_radgroup6);
                                rdg7=(RadioGroup) findViewById(R.id.qsr_faci_radgroup7);
                                rdg8=(RadioGroup) findViewById(R.id.qsr_faci_radgroup8);
                                rdg9=(RadioGroup) findViewById(R.id.qsr_faci_radgroup9);
                                rdg10=(RadioGroup) findViewById(R.id.qsr_faci_radgroup10);
                                rdg11=(RadioGroup) findViewById(R.id.qsr_faci_radgroup11);
                                rdg12=(RadioGroup) findViewById(R.id.qsr_faci_radgroup12);




                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.QSR_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("RTYTR","update value = "+c5.getString(c5.getColumnIndex(db.QSR_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.QSR_COMPLETE_STATUS));

                                if(get_pmca_complete.equalsIgnoreCase("2")) {



                                    qsrfacilitysub.setVisibility(View.GONE);
                                    qsrfacilitybck.setVisibility(View.GONE);

                                    qsrfacilitysub1.setVisibility(View.VISIBLE);

                                    Log.e("EEEVCXS", "qsr entry1");

                                    et1.setText(getpro);
                                    dis2.setText(getrodent);

                                    et1.setEnabled(false);
                                    txt2.setEnabled(false);


                                    rdg1.getChildAt(0).setEnabled(false);
                                    rdg1.getChildAt(1).setEnabled(false);
                                    rdg1.getChildAt(2).setEnabled(false);


                                    rdg2.getChildAt(0).setEnabled(false);
                                    rdg2.getChildAt(1).setEnabled(false);

                                    rdg3.getChildAt(0).setEnabled(false);
                                    rdg3.getChildAt(1).setEnabled(false);
                                    rdg3.getChildAt(2).setEnabled(false);

                                    rdg4.getChildAt(0).setEnabled(false);
                                    rdg4.getChildAt(1).setEnabled(false);
                                    rdg4.getChildAt(2).setEnabled(false);

                                    rdg5.getChildAt(0).setEnabled(false);
                                    rdg5.getChildAt(1).setEnabled(false);
                                    rdg5.getChildAt(2).setEnabled(false);


                                    rdg6.getChildAt(0).setEnabled(false);
                                    rdg6.getChildAt(1).setEnabled(false);

                                    rdg7.getChildAt(0).setEnabled(false);
                                    rdg7.getChildAt(1).setEnabled(false);

                                    rdg8.getChildAt(0).setEnabled(false);
                                    rdg8.getChildAt(1).setEnabled(false);

                                    rdg9.getChildAt(0).setEnabled(false);
                                    rdg9.getChildAt(1).setEnabled(false);

                                    rdg10.getChildAt(0).setEnabled(false);
                                    rdg10.getChildAt(1).setEnabled(false);

                                    rdg11.getChildAt(0).setEnabled(false);
                                    rdg11.getChildAt(1).setEnabled(false);
                                    rdg11.getChildAt(2).setEnabled(false);

                                    rdg12.getChildAt(0).setEnabled(false);
                                    rdg12.getChildAt(1).setEnabled(false);
                                    rdg12.getChildAt(2).setEnabled(false);






                                    if(get_rodent_infe.equalsIgnoreCase("LIVE INFESTATION")){
                                        ((RadioButton) rdg1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_infe.equalsIgnoreCase("OLD INFESTATION")){

                                        ((RadioButton) rdg1.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_rodent_infe.equalsIgnoreCase("NO INFESTATION")){

                                        ((RadioButton) rdg1.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_main_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_main_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg2.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_sliding_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_sliding_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_sliding_door.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg3.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_gaps.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_gaps.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg4.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_gaps.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg4.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_auto_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg5.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_auto_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg5.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_auto_door.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg5.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_air_curt.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg6.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_curt.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg6.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_air_func.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_func.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg7.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_air_present.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg8.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_present.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg8.getChildAt(1)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_air_align.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_align.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg9.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_type_ilt.equalsIgnoreCase("INSECTICUTOR(EFK / PDF)")){
                                        ((RadioButton) rdg10.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_type_ilt.equalsIgnoreCase("INSECT LIGHT TRAP(EFC/SPIDER)")){

                                        ((RadioButton) rdg10.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_ilt_instal.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg11.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_ilt_instal.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg11.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_ilt_instal.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg11.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_control_board.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg12.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_control_board.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg12.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_control_board.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg12.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_qsr_image1.length()!=0){
                                        Image_1_qsr=get_qsr_image1;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_qsr_image1, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));

                                    if(get_qsr_image2.length()!=0){
                                        Image_2_qsr=get_qsr_image2;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c2 = Base64.decode(get_qsr_image2, Base64.DEFAULT);
                                    Bitmap decodedByte_c2 = BitmapFactory.decodeByteArray(decodedString_c2, 0, decodedString_c2.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c2, 400, 400, false));


                                    Update_Status="2";

                                    pd.dismiss();


                                }
                                else if(get_pmca_complete.equalsIgnoreCase("1")) {



                                    Log.e("EFEFFE","entry2");
                                    et1.setText(getpro);
                                    dis2.setText(getrodent);



                                    if(get_rodent_infe.equalsIgnoreCase("LIVE INFESTATION")){
                                        ((RadioButton) rdg1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_rodent_infe.equalsIgnoreCase("OLD INFESTATION")){

                                        ((RadioButton) rdg1.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_rodent_infe.equalsIgnoreCase("NO INFESTATION")){

                                        ((RadioButton) rdg1.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_main_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_main_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg2.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_sliding_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_sliding_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_sliding_door.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg3.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_gaps.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_gaps.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg4.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_gaps.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg4.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_auto_door.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg5.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_auto_door.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg5.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_auto_door.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg5.getChildAt(2)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_air_curt.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg6.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_curt.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg6.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_air_func.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg7.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_func.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg7.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_air_present.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg8.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_present.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg8.getChildAt(1)).setChecked(true);

                                    }
                                    //////////////
                                    if(get_air_align.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg9.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_air_align.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg9.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_type_ilt.equalsIgnoreCase("INSECTICUTOR(EFK / PDF)")){
                                        ((RadioButton) rdg10.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_type_ilt.equalsIgnoreCase("INSECT LIGHT TRAP(EFC/SPIDER)")){

                                        ((RadioButton) rdg10.getChildAt(1)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_ilt_instal.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg11.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_ilt_instal.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg11.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_ilt_instal.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg11.getChildAt(2)).setChecked(true);

                                    }

                                    //////////////
                                    if(get_control_board.equalsIgnoreCase("YES")){
                                        ((RadioButton) rdg12.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_control_board.equalsIgnoreCase("NO")){

                                        ((RadioButton) rdg12.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_control_board.equalsIgnoreCase("N.A")){

                                        ((RadioButton) rdg12.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_qsr_image1.length()!=0){
                                        Image_1_qsr=get_qsr_image1;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_qsr_image1, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));

                                    if(get_qsr_image2.length()!=0){
                                        Image_2_qsr=get_qsr_image2;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c2 = Base64.decode(get_qsr_image2, Base64.DEFAULT);
                                    Bitmap decodedByte_c2 = BitmapFactory.decodeByteArray(decodedString_c2, 0, decodedString_c2.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q2.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c2, 400, 400, false));


                                           Update_Status="1";




                                }



                            }

                            // Toast.makeText( QSR_Facility_4.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }


    private void qsr_facility_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_sub_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void qsr_facility_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_bck_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }




}
