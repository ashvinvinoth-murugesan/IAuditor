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

public class QSR_Obser_5 extends AppCompatActivity {



    TextView sirobserres,sirobsercounter,qsrobsbtn2,qsr_obser_selectrespon1,qsr_obser_selectrespon2;
    LinearLayout laytest;
    ImageView Image_layout_Q1;
    TextView Q1_Imageview;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";


    Bitmap bitmapImage1;
    byte[] img_by1;
    ByteArrayInputStream imageStream1;
    String ImageCheck = "";
    Uri uri;
    //   String bitmapst;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    String mCurrentPhotoPath;

    public static final int RequestPermissionCode = 1;

    private RadioButton radiovaxButton,radiovaxButton2,radiovaxButton3,radiovaxButton4;

    private RadioGroup radiovaxGroup,radiovaxGroup2,radiovaxGroup3,qsr_obs_rd4;

    EditText sirobsloc,sirobsrecopci,sirobsrecocus,qsr_obser_et_specify,qsr_obser_et_obser;

    private  int counter=1;

    String sirobslocation,sirobsrecpci,sirobsrecus,sirobssdisp,sirobsgetspecify,getobser;

    public  static TextView qsrobsdisp,qsr_obser_disp1,qsr_obser_disp2 ;

    static String getradio1,getradio2,getradio3,getradio4;

    Button qsrobsersub1;

    private FirebaseAnalytics mFirebaseAnalytics;

    private android.app.AlertDialog pd;
    ArrayList<String> inspect_arr = new ArrayList<String>();
    ArrayList<String> loc_name_arr = new ArrayList<String>();
    ArrayList<String> specific_others = new ArrayList<String>();
    ArrayList<String> observations_arr = new ArrayList<String>();
    ArrayList<String> qsr_disp2 = new ArrayList<String>();




    ArrayList<String> loc_arr = new ArrayList<String>();
    ArrayList<String> pest_cover_arr = new ArrayList<String>();
    ArrayList<String> image_arr = new ArrayList<String>();
    ArrayList<String> recomm_rpci_arr = new ArrayList<String>();
    ArrayList<String> cust_arr = new ArrayList<String>();
    ArrayList<String> status_arr = new ArrayList<String>();
    ArrayList<String> priority_arr = new ArrayList<String>();
    ArrayList<String> ass_to_arr = new ArrayList<String>();
    ArrayList<String> bitmapst = new ArrayList<String>();

    String Image_1_obs_qsr;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    Button qsrobsersub,qsrobserbck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qsr_obser);

        EnableRuntimePermission();

        Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (ImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

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


        db = new DatabaseHelper(QSR_Obser_5.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

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

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);
        pd = new SpotsDialog(QSR_Obser_5.this, R.style.Custom);
        if(id!=null){
            QSR_Title_1.Main_ID=id;

            qsr_obser_button_next();
            getData(id);
        }else {
            if (!QSR_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                qsr_obser_button_next();
                getData(QSR_Title_1.Main_ID);
            }

        }

        qsr_obser_selectrespon2=(TextView) findViewById(R.id.qsr_obser_selectrespon2);
        qsr_obser_selectrespon1=(TextView) findViewById(R.id.qsr_obser_selectrespon1);

        qsr_obser_disp1=(TextView) findViewById(R.id.qsr_obser_disp1);

        qsr_obser_disp2 =(TextView) findViewById(R.id.qsr_obser_disp2);

        sirobsercounter=(TextView) findViewById(R.id.sirobsercounter);
        qsrobsbtn2=(TextView) findViewById(R.id.qsrobsbtn2);

        laytest=(LinearLayout) findViewById(R.id.sirobserlayout);

//        laytest.setVisibility(View.GONE);
        qsr_obser_et_obser  =(EditText) findViewById(R.id.qsr_obser_et_obser);
        sirobsrecopci =(EditText) findViewById(R.id.sirobserrecorentopci);
        sirobsrecocus =(EditText) findViewById(R.id.sirobserrecorentocus);
        qsr_obser_et_specify =(EditText) findViewById(R.id.qsr_obser_et_specify);

        sirobsloc =(EditText) findViewById(R.id.sirobslocation);

        sirobserres=(TextView) findViewById(R.id.sirobserresponse);

        qsrobsdisp=(TextView) findViewById(R.id.qsrobsdisplay);


//        sirradio1=(RadioGroup)findViewById(R.id.sirobsradiogroup1);
        radiovaxGroup2=(RadioGroup)findViewById(R.id.sirobradgroup2);
        radiovaxGroup3=(RadioGroup)findViewById(R.id.sirobradgroup3);

        qsr_obs_rd4=(RadioGroup) findViewById(R.id.qsr_obs_rd4);

        radiovaxGroup=(RadioGroup) findViewById(R.id.sirobsradiogroup1);





        qsr_obs_rd4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=qsr_obs_rd4.getCheckedRadioButtonId();

                radiovaxButton4=(RadioButton)findViewById(selectedId);

            }
        });

        radiovaxGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=radiovaxGroup.getCheckedRadioButtonId();

                radiovaxButton=(RadioButton)findViewById(selectedId);



            }
        });


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(QSR_Obser_5.this);
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
                        Toast.makeText(QSR_Obser_5.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(QSR_Obser_5.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }




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



        sirobserres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                qsr_obser_button_next();

                Log.e("MMMMM In 1 ",""+counter);


                    Log.e("MMMMM loc",""+sirobsloc.getText().toString());
                if (sirobsrecocus.length()==0) {
                }else {


                    if (counter!=0) {

                        getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                        getradio2 = ((RadioButton)findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                        getradio3 = ((RadioButton)findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                        getradio4 = ((RadioButton)findViewById(qsr_obs_rd4.getCheckedRadioButtonId())).getText().toString();


                        loc_arr.add(sirobsloc.getText().toString());
                        pest_cover_arr.add(qsrobsdisp.getText().toString());
                        recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                        cust_arr.add(sirobsrecocus.getText().toString());
                        status_arr.add(getradio1);
                        priority_arr.add(getradio2);
                        ass_to_arr.add(getradio3);
                        inspect_arr.add(getradio4);
                        specific_others.add(qsr_obser_et_specify.getText().toString());
                        loc_name_arr.add(qsr_obser_disp1.getText().toString());
                        observations_arr.add(qsr_obser_et_obser.getText().toString());
                        qsr_disp2.add(qsr_obser_disp2.getText().toString());



                        bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                        if (bitmapImage1!=null) {
                            bitmapst.add(getStringImage(bitmapImage1));
                        }

                    }


                    counter++;
                    sirobsercounter.setText("" + (counter));

                    if(counter>1){

                        sirobsloc.getText().clear();
                        sirobsrecopci.getText().clear();
                        sirobsrecocus.getText().clear();
                        radiovaxGroup.clearCheck();
                        radiovaxGroup2.clearCheck();
                        radiovaxGroup3.clearCheck();
                        qsr_obs_rd4.clearCheck();
                        qsrobsdisp.setText("");
                        qsr_obser_disp1.setText("");
                        qsr_obser_disp2.setText("");
                        qsr_obser_et_specify.getText().clear();
                        qsr_obser_et_obser.getText().clear();
                        Image_layout_Q1.setImageDrawable(null);
                }
                }
            }
        });

        qsrobsbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsrobs(1616);


            }
        });

        qsr_obser_selectrespon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsrobs(1717);
            }
        });

        qsr_obser_selectrespon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsrobs(1818);
            }
        });




        qsrobsersub =(Button) findViewById(R.id.qsrobsersub);
        qsrobserbck=(Button) findViewById(R.id.qsrobserbck);
        qsrobsersub1=(Button) findViewById(R.id.qsrobsersub1);

        qsrobsersub1.setVisibility(View.GONE);


        qsrobsersub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsr_obser_button_next();


pd.show();
                Intent i = new Intent(QSR_Obser_5.this,QSR_summary_6.class);
                startActivity(i);
            }
        });

        Cursor c101;
        c101 = sd.rawQuery("Select * from " + db.CHECK_STATUS_TABLE, null);
        c101.moveToFirst();

        Log.e("UUUUUUU","status ="+c101.getString(c101.getColumnIndex(db.STATUS)));

        if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("101")){
            qsrobsersub.setVisibility(View.GONE);
            qsrobserbck.setVisibility(View.GONE);
            qsrobsersub1.setVisibility(View.VISIBLE);

        }
        else  if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("100")){

            qsrobsersub.setVisibility(View.VISIBLE);
            qsrobserbck.setVisibility(View.VISIBLE);
            qsrobsersub1.setVisibility(View.GONE);

        }


//        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//        nInfo=cManager.getActiveNetworkInfo();

        qsrobsersub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {

                } else {
                    final Dialog dialog = new Dialog(QSR_Obser_5.this);
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
                                Toast.makeText(QSR_Obser_5.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(QSR_Obser_5.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }

                if (validation()) {

                    qsr_obser_button_next();
                    Log.e("MMMMM else", "submit" + counter);
                    try {
                        if (counter!=0) {
                            getradio1 = ((RadioButton) findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                            getradio2 = ((RadioButton) findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                            getradio3 = ((RadioButton) findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                            getradio4 = ((RadioButton) findViewById(qsr_obs_rd4.getCheckedRadioButtonId())).getText().toString();
                            loc_arr.add(sirobsloc.getText().toString());
                            pest_cover_arr.add(qsrobsdisp.getText().toString());
                            recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                            cust_arr.add(sirobsrecocus.getText().toString());
                            specific_others.add(qsr_obser_et_specify.getText().toString());
                            loc_name_arr.add(qsr_obser_disp1.getText().toString());
                            observations_arr.add(qsr_obser_et_obser.getText().toString());
                            qsr_disp2.add(qsr_obser_disp2.getText().toString());


                            bitmapImage1 = ((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                            //   bitmapst[0]=getStringImage(bitmapImage1);
                            if (bitmapImage1 != null) {
                                bitmapst.add(getStringImage(bitmapImage1));
                            }

                            Log.e("VVVRR","image = "+bitmapImage1);

                            status_arr.add(getradio1);
                            priority_arr.add(getradio2);
                            ass_to_arr.add(getradio3);
                            inspect_arr.add(getradio4);

                        }
                    } catch (NullPointerException e) {
                        Log.e("MMMMM ERR Null",""+e.getMessage());
                        e.printStackTrace();
                    }

                    qsr_obser_button_next();

                        post_qsrobs_js();

                }  else {
                Toast.makeText(getApplicationContext(), "Please fill all mandatory fields", Toast.LENGTH_SHORT).show();
            }

                }
        });

        qsrobserbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsr_obser_button_back();
                Intent i = new Intent(QSR_Obser_5.this,QSR_Facility_4.class);
                startActivity(i);
            }
        });
    }
public  boolean validation(){


    try {
        sirobslocation=sirobsloc.getText().toString();
        sirobsrecpci=sirobsrecopci.getText().toString();
        sirobsrecus=sirobsrecocus.getText().toString();
        sirobssdisp=qsrobsdisp.getText().toString();
        sirobsgetspecify=qsr_obser_et_specify.getText().toString();
        Log.e("MMMMM Size","\t"+loc_arr.size());
        if (loc_arr.size()!=0) {

            if (sirobslocation.length()==0||sirobssdisp.length()==0||sirobsrecpci.length()==0||sirobsrecus.length()==0||sirobsgetspecify.length()==0){

                return false;

            }else {
                return  true;
            }
        }else {
            return  true;

        }
    } catch (Exception e) {
        e.printStackTrace();
        Log.e("MMMMMM Errr Vali",""+e.getMessage());
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

        AlertDialog.Builder builder = new AlertDialog.Builder(QSR_Obser_5.this);
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

        if (ActivityCompat.shouldShowRequestPermissionRationale(QSR_Obser_5.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(QSR_Obser_5.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(QSR_Obser_5.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(QSR_Obser_5.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(QSR_Obser_5.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }


    public void qsrobs(int status) {

        Bundle bundle = new Bundle();
        if(status==1616) {
            bundle.putString("status", "1616");
        }
        if(status==1717) {
            bundle.putString("status", "1717");
        }
        if(status==1818) {
            bundle.putString("status", "1818");
        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    public void onBackPressed(){

    }

    private void post_qsrobs_js() {

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("count",""+loc_arr.size());
        Log.e("MMMMM loca siz",""+loc_arr.size());
        params.put("main_id",QSR_Title_1.Main_ID);
        for (int i=0;i<loc_arr.size();i++) {


            Log.e("LOOOI","image = "+bitmapst.get(i));

            params.put("sirobslocationq"+i,loc_arr.get(i));
            params.put("sirobsrecpciq"+i,recomm_rpci_arr.get(i));
            params.put("sirobsrecusq"+i,cust_arr.get(i));
            params.put("sirobssdispq"+i,pest_cover_arr.get(i));
            params.put("getradio1q"+i,status_arr.get(i));
            params.put("getradio2q"+i,priority_arr.get(i));
            params.put("getradio3q"+i,ass_to_arr.get(i));

            if(bitmapst.get(i)==null){
                params.put("addmediai" + i,"");
            }else {
                params.put("addmediai"+i,bitmapst.get(i));
            }

//            params.put("addmediai"+i,bitmapst.get(i));
            params.put("inspcta"+i,inspect_arr.get(i));
            params.put("loc_name"+i,loc_name_arr.get(i));
            params.put("spec_loc"+i,specific_others.get(i));
            params.put("obser_vation"+i,observations_arr.get(i));
            params.put("mode_pest"+i,qsr_disp2.get(i));
            params.put("update_status",Update_Status);

            Log.e("FFFFFCX","getParams = "+params);

        }

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/QSR/in_qsr_obser_5.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e( "JJJJJJ"," QSR Obser = "+response );
                            Intent i = new Intent(QSR_Obser_5.this,QSR_summary_6.class);
                            startActivity(i);

//                            Toast.makeText( QSR_Obser_5.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("EEEXZX","error obs res = "+error.getMessage());
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }




    private void getData(String key_id){

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            QSR_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/QSR/get_qsr_obser_5.php?main_id="+QSR_Title_1.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("EEETR","qsr obs res = "+response);


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString("main_id");
                                String getloc = c.getString("loc_name");
                                String getspeci = c.getString("speci_loc");
                                String getpest = c.getString("pest_obser");
                                String getotherspeci = c.getString("other_speci");

                                String getobser = c.getString("obser");
                                String getmodepest = c.getString("mode_pest");
                                String getrecommpci = c.getString("recomm_pci");
                                String getrecommcus = c.getString("recomm_cus");


                                String get_qsr_obs_image1 = c.getString("image1");
                                String get_inpect_area = c.getString("inpect_area");
                                String get_obser_status= c.getString("obser_status");
                                String get_priority= c.getString("priority");
                                String get_assign_to= c.getString("assign_to");

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.QSR_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDFCS","update value = "+c5.getString(c5.getColumnIndex(db.QSR_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.QSR_COMPLETE_STATUS));

                                if(get_pmca_complete.equalsIgnoreCase("2")) {



                                    qsrobsersub.setVisibility(View.GONE);
                                    qsrobserbck.setVisibility(View.GONE);
                                    qsrobsersub1.setVisibility(View.VISIBLE);

                                    qsr_obser_disp1.setText(getloc);
                                    sirobsloc.setText(getspeci);
                                    qsrobsdisp.setText(getpest);
                                    qsr_obser_et_specify.setText(getotherspeci);
                                    qsr_obser_et_obser.setText(getobser);
                                    qsr_obser_disp2.setText(getmodepest);
                                    sirobsrecopci.setText(getrecommpci);
                                    sirobsrecocus.setText(getrecommcus);

                                    sirobsloc.setEnabled(false);
                                    qsr_obser_et_specify.setEnabled(false);
                                    qsr_obser_et_obser.setEnabled(false);
                                    sirobsrecopci.setEnabled(false);
                                    sirobsrecocus.setEnabled(false);
                                    qsr_obser_selectrespon1.setEnabled(false);
                                    qsrobsbtn2.setEnabled(false);
                                    qsr_obser_selectrespon2.setEnabled(false);


                                    qsr_obs_rd4.getChildAt(0).setEnabled(false);
                                    qsr_obs_rd4.getChildAt(1).setEnabled(false);


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



                                    if (get_inpect_area.equalsIgnoreCase("EXTERNAL")) {
                                        ((RadioButton) qsr_obs_rd4.getChildAt(0)).setChecked(true);


                                    } else if (get_inpect_area.equalsIgnoreCase("INTERNAL")) {

                                        ((RadioButton) qsr_obs_rd4.getChildAt(1)).setChecked(true);

                                    }


                                    if (get_obser_status.equalsIgnoreCase("NEW")) {
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }

                                    if (get_priority.equalsIgnoreCase("HIGH")) {
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    } else if (get_priority.equalsIgnoreCase("MEDIUM")) {

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    } else if (get_priority.equalsIgnoreCase("LOW")) {

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }


                                    if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }
                                    else if (get_assign_to.equalsIgnoreCase("BOTH")) {

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_qsr_obs_image1.length() != 0) {
                                        Image_1_obs_qsr = get_qsr_obs_image1;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_qsr_obs_image1, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));





                                    Update_Status = "2";

                                    pd.dismiss();
                                }

                                else if(get_pmca_complete.equalsIgnoreCase("1")) {


                                    if (get_inpect_area.equalsIgnoreCase("EXTERNAL")) {
                                        ((RadioButton) qsr_obs_rd4.getChildAt(0)).setChecked(true);


                                    } else if (get_inpect_area.equalsIgnoreCase("INTERNAL")) {

                                        ((RadioButton) qsr_obs_rd4.getChildAt(1)).setChecked(true);

                                    }


                                    if (get_obser_status.equalsIgnoreCase("NEW")) {
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    } else if (get_obser_status.equalsIgnoreCase("REPEAT")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    } else if (get_obser_status.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }

                                    if (get_priority.equalsIgnoreCase("HIGH")) {
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    } else if (get_priority.equalsIgnoreCase("MEDIUM")) {

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    } else if (get_priority.equalsIgnoreCase("LOW")) {

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }


                                    if (get_assign_to.equalsIgnoreCase("RENTOKIL PCI")) {
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    } else if (get_assign_to.equalsIgnoreCase("CUSTOMER")) {

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }

                                    else if (get_assign_to.equalsIgnoreCase("BOTH")) {

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }

                                    if (get_qsr_obs_image1.length() != 0) {
                                        Image_1_obs_qsr = get_qsr_obs_image1;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_qsr_obs_image1, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));


                                    qsr_obser_disp1.setText(getloc);
                                    sirobsloc.setText(getspeci);
                                    qsrobsdisp.setText(getpest);
                                    qsr_obser_et_specify.setText(getotherspeci);
                                    qsr_obser_et_obser.setText(getobser);
                                    qsr_obser_disp2.setText(getmodepest);
                                    sirobsrecopci.setText(getrecommpci);
                                    sirobsrecocus.setText(getrecommcus);


                                    Update_Status = "1";

                                }

                            }

                            // Toast.makeText( QSR_Obser_5.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Log.e("EEETSS","error obs res = "+error.getMessage());
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }


    private void qsr_obser_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_sub_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void qsr_obser_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_bck_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }


}
