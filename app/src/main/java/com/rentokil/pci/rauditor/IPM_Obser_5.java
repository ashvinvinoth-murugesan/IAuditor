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

public class IPM_Obser_5 extends AppCompatActivity {

    Button sirobsub,sirobbck,ipm_obser_finish_1;
    TextView IPM_Submit,sirobsercounter,sirobsbtn2;
    LinearLayout laytest;
    ImageView Image_layout_Q1;
    TextView Q1_Imageview;
    Bitmap bitmapImage1;
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


    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    public static final int RequestPermissionCode = 1;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    ConnectivityManager cManager;
    NetworkInfo nInfo;

    private FirebaseAnalytics mFirebaseAnalytics;


    private RadioButton radiovaxButton,radiovaxButton2,radiovaxButton3,radiovaxButton4,radiovaxButton5,radiovaxButton6,radiovaxButton7;

    private RadioGroup radiovaxGroup,radiovaxGroup2,radiovaxGroup3,sirobsradiogroupnew1,ipm_obser_radiogroupnew2,ipm_obser_radiogroupnew3,ipm_obser_radiogroupnew4;

    EditText IPM_Loc,sirobsrecopci,sirobsrecocus,ipm_obser_et_specify,ipm_obser_et_observations;

    private  int counter=1;

    String sirobslocation,sirobsrecpci,sirobsrecus,sirobssdisp,radiogets1,radiogets2,radiogets3,getobser;

    public  static TextView ipm_obser_txt_disp1 ;

    static String getradio1,getradio2,getradio3,getradio4,getradio5,getradio6,getradio7;



    ArrayList<String> loc_arr = new ArrayList<String>();
    ArrayList<String> pest_cover_arr = new ArrayList<String>();
    ArrayList<String> image_arr = new ArrayList<String>();
    ArrayList<String> recomm_rpci_arr = new ArrayList<String>();
    ArrayList<String> cust_arr = new ArrayList<String>();
    ArrayList<String> status_arr = new ArrayList<String>();
    ArrayList<String> priority_arr = new ArrayList<String>();
    ArrayList<String> ass_to_arr = new ArrayList<String>();
    ArrayList<String> bitmapst = new ArrayList<String>();
    ArrayList<String> obse_arr = new ArrayList<String>();

    ArrayList<String> spcfy_arr = new ArrayList<String>();
    ArrayList<String> inspctd_arr = new ArrayList<String>();
    ArrayList<String> typearea_arr = new ArrayList<String>();
    ArrayList<String> mode_arr = new ArrayList<String>();
    ArrayList<String> insectiside_arr = new ArrayList<String>();
    private android.app.AlertDialog pd;

    String Image_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipm__obser);


        EnableRuntimePermission();

        ipm_obser_et_observations=(EditText) findViewById(R.id.ipm_obser_et_observations);

        Q1_Imageview = (TextView) findViewById(R.id.image_Q1);
        Image_layout_Q1 = (ImageView) findViewById(R.id.image_layout_Q1);
        Image_layout_Q1.setVisibility(View.GONE);

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

        db = new DatabaseHelper(IPM_Obser_5.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        pd = new SpotsDialog(IPM_Obser_5.this, R.style.Custom);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(IPM_Obser_5.this);
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
                        Toast.makeText(IPM_Obser_5.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(IPM_Obser_5.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }


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
        ipm_obser_et_specify=(EditText) findViewById(R.id.ipm_obser_et_specify);
        sirobsub =(Button) findViewById(R.id.sirobsub);
        sirobbck=(Button) findViewById(R.id.sirobbck);
        ipm_obser_finish_1=(Button) findViewById(R.id.ipm_obser_finish_1);


        ipm_obser_finish_1.setVisibility(View.GONE);

        ipm_obser_finish_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                ipm_obser_next();

                Intent i = new Intent(IPM_Obser_5.this,IPM_Summary_6.class);
                startActivity(i);

            }
        });
        sirobsercounter=(TextView) findViewById(R.id.sirobsercounter);
        sirobsbtn2=(TextView) findViewById(R.id.sirobsbtn2);

        laytest=(LinearLayout) findViewById(R.id.ipmobslayout);



        sirobsrecopci =(EditText) findViewById(R.id.sirobserrecorentopci);
        sirobsrecocus =(EditText) findViewById(R.id.sirobserrecorentocus);

        IPM_Loc =(EditText) findViewById(R.id.sirobslocation);

        IPM_Submit=(TextView) findViewById(R.id.sirobserresponse);

        ipm_obser_txt_disp1=(TextView) findViewById(R.id.ipm_obser_txt_disp1);

//        sirradio1=(RadioGroup)findViewById(R.id.sirobsradiogroup1);
        radiovaxGroup2=(RadioGroup)findViewById(R.id.sirobradgroup2);
        radiovaxGroup3=(RadioGroup)findViewById(R.id.sirobradgroup3);

//        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
//        nInfo=cManager.getActiveNetworkInfo();

        radiovaxGroup=(RadioGroup) findViewById(R.id.sirobsradiogroup1);

        sirobsradiogroupnew1=(RadioGroup) findViewById(R.id.sirobsradiogroupnew1);
        ipm_obser_radiogroupnew2=(RadioGroup) findViewById(R.id.ipm_obser_radiogroupnew2);
        ipm_obser_radiogroupnew3=(RadioGroup) findViewById(R.id.ipm_obser_radiogroupnew3);
        ipm_obser_radiogroupnew4=(RadioGroup) findViewById(R.id.ipm_obser_radiogroupnew4);

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

        ipm_obser_radiogroupnew2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_obser_radiogroupnew2.getCheckedRadioButtonId();

                radiovaxButton4=(RadioButton)findViewById(selectedId);

            }
        });

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            IPM_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!IPM_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(IPM_Title_1.Main_ID);
            }

        }

        sirobsradiogroupnew1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=sirobsradiogroupnew1.getCheckedRadioButtonId();

                radiovaxButton5=(RadioButton)findViewById(selectedId);

            }
        });

        ipm_obser_radiogroupnew3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_obser_radiogroupnew3.getCheckedRadioButtonId();

                radiovaxButton6=(RadioButton)findViewById(selectedId);

            }
        });
        ipm_obser_radiogroupnew4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipm_obser_radiogroupnew4.getCheckedRadioButtonId();

                radiovaxButton7=(RadioButton)findViewById(selectedId);

            }
        });



        IPM_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ipm_obser_next();

                Log.e("MMMMM count ",""+counter);

                if (IPM_Loc.length() == 0 && sirobsrecopci.length()==0 && sirobsrecocus.length()==0) {
//                    Toast.makeText(getApplicationContext(),
//                            "All fields are mandatory to fill", Toast.LENGTH_SHORT)
//                            .show();
                }if (IPM_Loc.length() == 0) {
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

                    ipm_obser_next();
                    Log.e("MMMMM else","else"+counter);
                    Log.e("MMMMM loc",""+IPM_Loc.getText().toString());
                    if (counter!=0) {
                        getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                        getradio2 = ((RadioButton)findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                        getradio3 = ((RadioButton)findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                        getradio4 = ((RadioButton)findViewById(ipm_obser_radiogroupnew2.getCheckedRadioButtonId())).getText().toString();
                        getradio5 = ((RadioButton)findViewById(sirobsradiogroupnew1.getCheckedRadioButtonId())).getText().toString();
                        getradio6 = ((RadioButton)findViewById(ipm_obser_radiogroupnew3.getCheckedRadioButtonId())).getText().toString();
                        getradio7= ((RadioButton)findViewById(ipm_obser_radiogroupnew4.getCheckedRadioButtonId())).getText().toString();
                        loc_arr.add(IPM_Loc.getText().toString());
                        pest_cover_arr.add(ipm_obser_txt_disp1.getText().toString());
                        recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                        cust_arr.add(sirobsrecocus.getText().toString());
                        obse_arr.add(ipm_obser_et_observations.getText().toString());
                        spcfy_arr.add(ipm_obser_et_specify.getText().toString());
                        status_arr.add(getradio1);
                        priority_arr.add(getradio2);
                        ass_to_arr.add(getradio3);
                        inspctd_arr.add(getradio5);
                        typearea_arr.add(getradio4);
                        mode_arr.add(getradio6);
                        insectiside_arr.add(getradio7);





                        bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                        if (bitmapImage1!=null) {
                            bitmapst.add(getStringImage(bitmapImage1));
                        }

                    }


                    counter++;
                    sirobsercounter.setText("" + counter);

                    if(counter>1){

                        IPM_Loc.getText().clear();
                        sirobsrecopci.getText().clear();
                        sirobsrecocus.getText().clear();
                        radiovaxGroup.clearCheck();
                        radiovaxGroup2.clearCheck();
                        radiovaxGroup3.clearCheck();
                        ipm_obser_radiogroupnew2.clearCheck();
                        sirobsradiogroupnew1.clearCheck();
                        ipm_obser_radiogroupnew3.clearCheck();
                        ipm_obser_radiogroupnew4.clearCheck();
                        ipm_obser_et_specify.getText().clear();
                        ipm_obser_et_observations.getText().clear();

                        Image_layout_Q1.setImageDrawable(null);
                        ipm_obser_txt_disp1.setText("");

                    }
                }
            }
        });

        sirobsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_obser_next();

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(IPM_Obser_5.this);
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
                                Toast.makeText(IPM_Obser_5.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(IPM_Obser_5.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }


                if (validation()) {
                        try {
                            if (counter != 0) {
                                getradio1 = ((RadioButton) findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                                getradio2 = ((RadioButton) findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                                getradio3 = ((RadioButton) findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                                getradio4 = ((RadioButton) findViewById(ipm_obser_radiogroupnew2.getCheckedRadioButtonId())).getText().toString();
                                getradio5 = ((RadioButton) findViewById(sirobsradiogroupnew1.getCheckedRadioButtonId())).getText().toString();
                                getradio6 = ((RadioButton) findViewById(ipm_obser_radiogroupnew3.getCheckedRadioButtonId())).getText().toString();
                                getradio7 = ((RadioButton) findViewById(ipm_obser_radiogroupnew4.getCheckedRadioButtonId())).getText().toString();
                                loc_arr.add(IPM_Loc.getText().toString());
                                pest_cover_arr.add(ipm_obser_txt_disp1.getText().toString());
                                recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                                cust_arr.add(sirobsrecocus.getText().toString());
                                spcfy_arr.add(ipm_obser_et_specify.getText().toString());
                                obse_arr.add(ipm_obser_et_observations.getText().toString());
                                bitmapImage1 = ((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                                //   bitmapst[0]=getStringImage(bitmapImage1);
                                if (bitmapImage1 != null) {
                                    bitmapst.add(getStringImage(bitmapImage1));
                                }

                                status_arr.add(getradio1);
                                priority_arr.add(getradio2);
                                ass_to_arr.add(getradio3);
                                inspctd_arr.add(getradio5);
                                typearea_arr.add(getradio4);
                                mode_arr.add(getradio6);
                                insectiside_arr.add(getradio7);
                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();

                        }
                        post_ipm_obsjs();
                    } else {
                        Toast.makeText(getApplicationContext(), "Please fill all mandatory fields", Toast.LENGTH_SHORT).show();
                    }

            }
        });

        sirobbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_obser_back();
                Intent i = new Intent(IPM_Obser_5.this,IPM_Facility_4.class);
                startActivity(i);
            }
        });

        sirobsbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sirobs(110);

            }
        });

    }
    public boolean validation(){
        Log.e("MMMMM else","submit");

            sirobslocation=IPM_Loc.getText().toString();
            sirobsrecpci=sirobsrecopci.getText().toString();
            sirobsrecus=sirobsrecocus.getText().toString();
            sirobssdisp=ipm_obser_txt_disp1.getText().toString();


        if(TextUtils.isEmpty(sirobslocation ) || TextUtils.isEmpty(sirobsrecpci) ||TextUtils.isEmpty(sirobsrecus)
                ||TextUtils.isEmpty(sirobssdisp) ) {

            if(TextUtils.isEmpty(sirobslocation )){
                IPM_Loc.setError("Required");
            }

            if(TextUtils.isEmpty(sirobsrecpci)) {
                sirobsrecopci.setError("Required");


            }
            if(TextUtils.isEmpty(sirobsrecus)) {
                sirobsrecocus.setError("Required");

            }
            if(TextUtils.isEmpty(sirobssdisp)) {
                ipm_obser_txt_disp1.setError("Required");

            }

            return false;
        }



        if(loc_arr.size()!=0){
                if(sirobslocation.length()==0||sirobsrecpci.length()==0||sirobsrecus.length()==0||sirobssdisp.length()==0){
                    return false;
                }else {
                    return true;
                }
            }else {
                return true;
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

        AlertDialog.Builder builder = new AlertDialog.Builder(IPM_Obser_5.this);
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

        if (ActivityCompat.shouldShowRequestPermissionRationale(IPM_Obser_5.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(IPM_Obser_5.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(IPM_Obser_5.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(IPM_Obser_5.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(IPM_Obser_5.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }


    public void sirobs(int status) {

        Bundle bundle = new Bundle();
        if(status==110) {
            bundle.putString("status", "110");
        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }

    private void post_ipm_obsjs() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("count",""+loc_arr.size());
        Log.e("MMMMM size",""+loc_arr.size());
        params.put("main_id",IPM_Title_1.Main_ID);
        for (int i=0;i<loc_arr.size();i++) {
            params.put("sirobslocationq"+i,loc_arr.get(i));
            params.put("sirobsrecpciq"+i,recomm_rpci_arr.get(i));
            params.put("sirobsrecusq"+i,cust_arr.get(i));
            params.put("sirobssdispq"+i,pest_cover_arr.get(i));
            params.put("getradio1q"+i,status_arr.get(i));
            params.put("getradio2q"+i,priority_arr.get(i));
            params.put("getradio3q"+i,ass_to_arr.get(i));
            params.put("addmediai"+i,bitmapst.get(i));
            params.put("specifyi"+i,spcfy_arr.get(i));
            params.put("inspcdi"+i,inspctd_arr.get(i));
            params.put("typeari"+i,typearea_arr.get(i));
            params.put("modeii"+i,mode_arr.get(i));
            params.put("insectii"+i,insectiside_arr.get(i));
            params.put("obseri"+i,obse_arr.get(i));
            params.put("update_status",Update_Status);


            Log.e("FFFFR","PPP"+loc_arr.get(i));
            Log.e("SSSGT",""+recomm_rpci_arr.get(i));
            Log.e("SSQWE",""+cust_arr.get(i));
            Log.e("TYUIY",""+pest_cover_arr.get(i));
            Log.e("XXXSS",""+status_arr.get(i));
            Log.e("BBBGG",""+priority_arr.get(i));
            Log.e("VVVCC",""+ass_to_arr.get(i));
            Log.e("DDDDS",""+bitmapst.get(i));
            Log.e("EEEES",""+spcfy_arr.get(i));
            Log.e("QQQSS",""+inspctd_arr.get(i));
            Log.e("LLLOO",""+typearea_arr.get(i));
            Log.e("HHHHY",""+mode_arr.get(i));
            Log.e("LLLLS",""+insectiside_arr.get(i));
            Log.e("MMMNN",""+obse_arr.get(i));


        }


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/IPM/in_ipm_obser_5.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();
                            Log.e( "JJJJ IPM 5","\t"+response );
                            Intent i=new Intent( IPM_Obser_5.this,IPM_Summary_6.class);
                            startActivity( i );
                            //   Toast.makeText(SIR_Cus_2.this,response,Toast.LENGTH_LONG).show();
                            Log.e( "JJJJJJJ","Res\t"+response );

                            //    Toast.makeText( IPM_Obser_5.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e("ERERTY","err ipm obs sub"+error.getMessage());


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
            IPM_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/IPM/get_ipm_obser_5.php?main_id="+IPM_Title_1.Main_ID ;

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
                                String getlocation = c.getString("location");
                                String getpest = c.getString("pest_obser");
                                String getspecify = c.getString("specify");
                                String getobser = c.getString("obser");
                                String getpci = c.getString("ri_recomm");
                                String getcust = c.getString("cust_recomm");

                                String get_inspt_area = c.getString("inspt_area");
                                String get_ipm_image = c.getString("image");
                                String get_type_area = c.getString("type_area");
                                String get_mode_pest = c.getString("mode_pest");
                                String get_insect_used = c.getString("insect_used");
                                String get_obser_status = c.getString("obser_status");
                                String get_priority = c.getString("priority");
                                String get_assign_to = c.getString("assign_to");

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDFCSGFS","update value = "+c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS));



                                if(get_pmca_complete.equalsIgnoreCase("2")) {




                                    sirobbck.setVisibility(View.GONE);
                                    sirobsub.setVisibility(View.GONE);
                                    ipm_obser_finish_1.setVisibility(View.VISIBLE);

                                    IPM_Loc.setText(getlocation);
                                    ipm_obser_txt_disp1.setText(getpest);
                                    ipm_obser_et_specify.setText(getspecify);
                                    ipm_obser_et_observations.setText(getobser);
                                    sirobsrecopci.setText(getpci);
                                    sirobsrecocus.setText(getcust);

                                    IPM_Loc.setEnabled(false);
                                    IPM_Submit.setEnabled(false);
                                    ipm_obser_et_specify.setEnabled(false);
                                    ipm_obser_et_observations.setEnabled(false);
                                    sirobsrecopci.setEnabled(false);
                                    sirobsrecocus.setEnabled(false);

                                    sirobsradiogroupnew1.getChildAt(0).setEnabled(false);
                                    sirobsradiogroupnew1.getChildAt(1).setEnabled(false);

                                    ipm_obser_radiogroupnew2.getChildAt(0).setEnabled(false);
                                    ipm_obser_radiogroupnew2.getChildAt(1).setEnabled(false);

                                    ipm_obser_radiogroupnew3.getChildAt(0).setEnabled(false);
                                    ipm_obser_radiogroupnew3.getChildAt(1).setEnabled(false);


                                    ipm_obser_radiogroupnew4.getChildAt(0).setEnabled(false);
                                    ipm_obser_radiogroupnew4.getChildAt(1).setEnabled(false);
                                    ipm_obser_radiogroupnew4.getChildAt(2).setEnabled(false);

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



                                    if(get_inspt_area.equalsIgnoreCase("EXTERNAL")){
                                        ((RadioButton) sirobsradiogroupnew1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_inspt_area.equalsIgnoreCase("INTERNAL")){

                                        ((RadioButton) sirobsradiogroupnew1.getChildAt(1)).setChecked(true);

                                    }

                                    if(get_type_area.equalsIgnoreCase("SENSITIVE")){
                                        ((RadioButton) ipm_obser_radiogroupnew2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_type_area.equalsIgnoreCase("NON-SENSITIVE")){

                                        ((RadioButton) ipm_obser_radiogroupnew2.getChildAt(1)).setChecked(true);

                                    }

                                    if(get_mode_pest.equalsIgnoreCase("PREVENTIVE")){
                                        ((RadioButton) ipm_obser_radiogroupnew3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_mode_pest.equalsIgnoreCase("CURATIVE")){

                                        ((RadioButton) ipm_obser_radiogroupnew3.getChildAt(1)).setChecked(true);

                                    }


                                    if(get_insect_used.equalsIgnoreCase("YES")){
                                        ((RadioButton) ipm_obser_radiogroupnew4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_insect_used.equalsIgnoreCase("NO")){

                                        ((RadioButton) ipm_obser_radiogroupnew4.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_insect_used.equalsIgnoreCase("NA")){

                                        ((RadioButton) ipm_obser_radiogroupnew4.getChildAt(2)).setChecked(true);

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


                                    if(get_ipm_image.length()!=0){
                                        Image_1=get_ipm_image;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_ipm_image, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));








                                    Update_Status = "2";

                                    pd.dismiss();

                                }
                                else if(get_pmca_complete.equalsIgnoreCase("1")){


                                    IPM_Loc.setText(getlocation);
                                    ipm_obser_txt_disp1.setText(getpest);
                                    ipm_obser_et_specify.setText(getspecify);
                                    ipm_obser_et_observations.setText(getobser);
                                    sirobsrecopci.setText(getpci);
                                    sirobsrecocus.setText(getcust);

                                    if(get_inspt_area.equalsIgnoreCase("EXTERNAL")){
                                        ((RadioButton) sirobsradiogroupnew1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_inspt_area.equalsIgnoreCase("INTERNAL")){

                                        ((RadioButton) sirobsradiogroupnew1.getChildAt(1)).setChecked(true);

                                    }

                                    if(get_type_area.equalsIgnoreCase("SENSITIVE")){
                                        ((RadioButton) ipm_obser_radiogroupnew2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_type_area.equalsIgnoreCase("NON-SENSITIVE")){

                                        ((RadioButton) ipm_obser_radiogroupnew2.getChildAt(1)).setChecked(true);

                                    }

                                    if(get_mode_pest.equalsIgnoreCase("PREVENTIVE")){
                                        ((RadioButton) ipm_obser_radiogroupnew3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_mode_pest.equalsIgnoreCase("CURATIVE")){

                                        ((RadioButton) ipm_obser_radiogroupnew3.getChildAt(1)).setChecked(true);

                                    }


                                    if(get_insect_used.equalsIgnoreCase("YES")){
                                        ((RadioButton) ipm_obser_radiogroupnew4.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_insect_used.equalsIgnoreCase("NO")){

                                        ((RadioButton) ipm_obser_radiogroupnew4.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_insect_used.equalsIgnoreCase("NA")){

                                        ((RadioButton) ipm_obser_radiogroupnew4.getChildAt(2)).setChecked(true);

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


                                    if(get_ipm_image.length()!=0){
                                        Image_1=get_ipm_image;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_ipm_image, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));








                                    Update_Status = "1";




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
                        //mobile.setText( error.getMessage() );

                        pd.dismiss();

                        Log.e("ERERTY","err ipm obs get"+error.getMessage());
                    }
                } )
                .requestJson();
    }


    private void ipm_obser_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void  ipm_obser_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }







}






