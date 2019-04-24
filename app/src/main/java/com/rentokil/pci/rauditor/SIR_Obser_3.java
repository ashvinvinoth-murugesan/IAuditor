package com.rentokil.pci.rauditor;

import android.Manifest;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class SIR_Obser_3 extends AppCompatActivity {

    Button sirobsub,sirobbck,sirobsub1;
   TextView sirobserres,sirobsercounter,sirobsbtn2;

    ImageView Image_layout_Q1;
    TextView Q1_Imageview;
    Bitmap bitmapImage1;
    byte[] img_by1;
    ByteArrayInputStream imageStream1;
    String ImageCheck = "";
    Uri uri;
    String myJSON;
    JSONArray json_arr_cus_result;
    String Image_1;
    public String Update_Status="0";
 //   String bitmapst;
    String mCurrentPhotoPath;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    public static final int RequestPermissionCode = 1;

    private RadioButton radiovaxButton,radiovaxButton2,radiovaxButton3;

    private RadioGroup radiovaxGroup,radiovaxGroup2,radiovaxGroup3;

    EditText sirobsloc,sirobsrecopci,sirobsrecocus;

    private  int counter=1;

    String sirobslocation,sirobsrecpci,sirobsrecus,sirobssdisp,radiogets1,radiogets2,radiogets3;

    public  static TextView sirobsdisplay ;

    static String getradio1,getradio2,getradio3;


    private android.app.AlertDialog pd;
    String sirobslocst,sirobsdisplayst,sirobsrecopcist,sirobsrecocusst;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;

    private FirebaseAnalytics mFirebaseAnalytics;

    ArrayList<String> loc_arr = new ArrayList<String>();
    ArrayList<String> pest_cover_arr = new ArrayList<String>();
    ArrayList<String> image_arr = new ArrayList<String>();
    ArrayList<String> recomm_rpci_arr = new ArrayList<String>();
    ArrayList<String> cust_arr = new ArrayList<String>();
    ArrayList<String> status_arr = new ArrayList<String>();
    ArrayList<String> priority_arr = new ArrayList<String>();
    ArrayList<String> ass_to_arr = new ArrayList<String>();
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    ArrayList<String> bitmapst = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sir_obser);

        //image initialize

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();
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
        db = new DatabaseHelper(SIR_Obser_3.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();


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
//        getData();
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        sirobsub =(Button) findViewById(R.id.sirobsub);
        sirobbck=(Button) findViewById(R.id.sirobbck);
        sirobsercounter=(TextView) findViewById(R.id.sirobsercounter);
        sirobsbtn2=(TextView) findViewById(R.id.sirobsbtn2);

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(SIR_Obser_3.this);
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
                        Toast.makeText(SIR_Obser_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(SIR_Obser_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        pd = new SpotsDialog(SIR_Obser_3.this, R.style.Custom);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        sirobsrecopci =(EditText) findViewById(R.id.sirobserrecorentopci);
        sirobsrecocus =(EditText) findViewById(R.id.sirobserrecorentocus);

        sirobsloc =(EditText) findViewById(R.id.sirobslocation);

        sirobserres=(TextView) findViewById(R.id.sirobserresponse);

        sirobsdisplay=(TextView) findViewById(R.id.sirobsdisplay);

        sirobsub1=(Button) findViewById(R.id.sirobsub1);

        sirobsub1.setVisibility(View.GONE);

        sirobsub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                Intent i = new Intent(SIR_Obser_3.this,SIR_Summary_4.class);
                startActivity(i);
            }
        });


//        sirradio1=(RadioGroup)findViewById(R.id.sirobsradiogroup1);
        radiovaxGroup2=(RadioGroup)findViewById(R.id.sirobradgroup2);
        radiovaxGroup3=(RadioGroup)findViewById(R.id.sirobradgroup3);



        radiovaxGroup=(RadioGroup) findViewById(R.id.sirobsradiogroup1);

        radiovaxGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=radiovaxGroup.getCheckedRadioButtonId();

                radiovaxButton=(RadioButton)findViewById(selectedId);



            }
        });

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            SIR_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!SIR_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(SIR_Title_1.Main_ID);
            }

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


// Add multi observation
        sirobserres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Log.e("MMMMM count ",""+counter);
                if (sirobsrecocus.length()==0) {
                }

                else {
                     Log.e("MMMMM else","else"+counter);
                     Log.e("MMMMM loc",""+sirobsloc.getText().toString());
                    try {
                        if (counter!=0) {
                            getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                            getradio2 = ((RadioButton)findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                            getradio3 = ((RadioButton)findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                            loc_arr.add(sirobsloc.getText().toString());
                            pest_cover_arr.add(sirobsdisplay.getText().toString());
                            recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                            cust_arr.add(sirobsrecocus.getText().toString());
                            status_arr.add(getradio1);
                            priority_arr.add(getradio2);
                            ass_to_arr.add(getradio3);
                            bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                            if (bitmapImage1!=null) {
                                bitmapst.add(getStringImage(bitmapImage1));
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    counter++;
                    sirobsercounter.setText("" + counter);

                    if(counter>1){

                        sirobsloc.getText().clear();
                        sirobsrecopci.getText().clear();
                        sirobsrecocus.getText().clear();
                        radiovaxGroup.clearCheck();
                        radiovaxGroup2.clearCheck();
                        radiovaxGroup3.clearCheck();
                        sirobsdisplay.setText("");
                        Image_layout_Q1.setImageDrawable(null);
    }
                }
            }
        });
// Post Button (Final submission)
        sirobsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isNetworkAvailable()) {

                 if(validation()){
                     try {
                         if (counter!=0) {
                             getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                             getradio2 = ((RadioButton)findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                             getradio3 = ((RadioButton)findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                             loc_arr.add(sirobsloc.getText().toString());
                             pest_cover_arr.add(sirobsdisplay.getText().toString());
                             recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                             cust_arr.add(sirobsrecocus.getText().toString());
                             bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                             //   bitmapst[0]=getStringImage(bitmapImage1);


                             sirobslocst=sirobsloc.getText().toString();
                             sirobsdisplayst=sirobsdisplay.getText().toString();
                             sirobsrecopcist=sirobsrecopci.getText().toString();
                             sirobsrecocusst=sirobsrecocus.getText().toString();

                             if(TextUtils.isEmpty(sirobslocst)|| TextUtils.isEmpty(sirobsdisplayst)
                                     || TextUtils.isEmpty(sirobsrecopcist) || TextUtils.isEmpty(sirobsrecocusst)) {



                                 if(TextUtils.isEmpty(sirobslocst)) {
                                     sirobsloc.setError("Required");


                                 }
                                 if(TextUtils.isEmpty(sirobsdisplayst)) {
                                     sirobsdisplay.setError("Required");

                                 }
                                 if(TextUtils.isEmpty(sirobsrecopcist)) {
                                     sirobsrecopci.setError("Required");

                                 }
                                 if(TextUtils.isEmpty(sirobsrecocusst)) {
                                     sirobsrecocus.setError("Required");

                                 }


                             }


                             if (bitmapImage1!=null){
                                 bitmapst.add(getStringImage(bitmapImage1));
                             }

                             status_arr.add(getradio1);
                             priority_arr.add(getradio2);
                             ass_to_arr.add(getradio3);
                         }else {

                         }
                     } catch (NullPointerException e) {
                         e.printStackTrace();
                     }
                     SIR_Obj_Post();
                     sir_obs_button_next();
                 }


                } else {

                    Toast.makeText(SIR_Obser_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();

                }



            }
        });

        sirobbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sir_obs_button_back();
                Intent i = new Intent(SIR_Obser_3.this,SIR_Cus_2.class);
                startActivity(i);
            }
        });

        sirobsbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sirobs(666);


            }
        });

    }

    @Override

    public void onBackPressed(){

    }

    public  boolean validation(){
        Log.e("MMMMM else","submit");


        sirobslocation=sirobsloc.getText().toString();
        sirobsrecpci=sirobsrecopci.getText().toString();
        sirobsrecus=sirobsrecocus.getText().toString();
        sirobssdisp=sirobsdisplay.getText().toString();
        if (loc_arr.size()!=0) {
            if (sirobslocation.length()==0||sirobsrecpci.length()==0||sirobsrecus.length()==0||sirobssdisp.length()==0){
                return false;

            }else {
                return  true;
            }
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

        AlertDialog.Builder builder = new AlertDialog.Builder(SIR_Obser_3.this);
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


    private void sir_obs_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_3");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_3_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

    private void sir_obs_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_3");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_3_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

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
                        Image_layout_Q1.setVisibility(View.GONE);
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

        if (ActivityCompat.shouldShowRequestPermissionRationale(SIR_Obser_3.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(SIR_Obser_3.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(SIR_Obser_3.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(SIR_Obser_3.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(SIR_Obser_3.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }


    public void sirobs(int status) {

        Bundle bundle = new Bundle();
        if(status==666) {
            bundle.putString("status", "666");
        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }


    private void SIR_Obj_Post() {
        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();

        for (int i=0;i<loc_arr.size();i++) {
            params.put("count", "" + loc_arr.size());
            Log.e("MMMMM size loc_arr", "" + loc_arr.size());
            Log.e("MMMMM size recomm_r", "" + recomm_rpci_arr.size());
            Log.e("MMMMM size cust_arr", "" + cust_arr.size());
            Log.e("MMMMM size cover_arr", "" + pest_cover_arr.size());
            Log.e("MMMMM size status_arr", "" + status_arr.size());
            Log.e("MMMMM size priority_arr", "" + priority_arr.size());
            Log.e("MMMMM size ass_to_arr", "" + ass_to_arr.size());
            Log.e("MMMMM size bitmapst", "" + bitmapst.size());
            Log.e("MMMMM size bitmapst", "" + Update_Status);
            Log.e("MMMMM size bitmapst", "" + SIR_Title_1.Main_ID);

            params.put("main_id", SIR_Title_1.Main_ID);
            params.put("sirobslocationq" + i, loc_arr.get(i));
            params.put("sirobsrecpciq" + i, recomm_rpci_arr.get(i));
            params.put("sirobsrecusq" + i, cust_arr.get(i));
            params.put("sirobssdispq" + i, pest_cover_arr.get(i));
            params.put("getradio1q" + i, status_arr.get(i));
            params.put("getradio2q" + i, priority_arr.get(i));
            params.put("getradio3q" + i, ass_to_arr.get(i));

            if(bitmapst.get(i)==null){
                params.put("addmediai" + i,"");
            }else {
                params.put("addmediai" + i, bitmapst.get(i));
            }

            params.put("update_status", Update_Status);
            Log.e("MMMMM size params", "" +params);
        }



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/SIR/in_sir_obs_3.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            pd.dismiss();
                            Log.e("DDDDDD",""+response);
//                            String data = response.getString( "message" );
                            Intent i = new Intent(SIR_Obser_3.this,SIR_Summary_4.class);
                            startActivity(i);
                            //   Toast.makeText(SIR_Cus_2.this,response,Toast.LENGTH_LONG).show();
                            Log.e( "JJJJJJJ","Res\t"+response );
//
//                            Toast.makeText( SIR_Obser_3.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("DDDDDD","error obser3  = "+error.getMessage());

                        pd.dismiss();

                        try {
                            if(error.getMessage().equalsIgnoreCase("null")){

                                Intent i = new Intent(SIR_Obser_3.this,SIR_Summary_4.class);
                                startActivity(i);

                            }
                        } catch (Exception e) {
                            Intent i = new Intent(SIR_Obser_3.this,SIR_Summary_4.class);
                            startActivity(i);
                            e.printStackTrace();
                        }


//
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }





    private void getData(String key_id){

        pd.show();

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            SIR_Title_1.Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/SIR/get_sir_obs_3.php?main_id="+SIR_Title_1.Main_ID;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            Log.e( "JJJJJJ","sirobs res = "+response );

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");

                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String getlocation = c.getString("location");
                                String getpestobserr = c.getString("pest_obser");
                                String getrecomm = c.getString("ri_recomm");
                                String getcusrecomm = c.getString("cust_recomm");
                                String getimage_ri = c.getString( "image_ri" );
                                String getsirobsers = c.getString( "obser_status" );
                                String getsirprir = c.getString( "priority" );
                                String getsirassign = c.getString( "assign_to" );


//                                radiovaxGroup,radiovaxGroup2,radiovaxGroup3

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.USER_COMPLETE_STATUS, null);
                                c5.moveToFirst();

                                Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.COMPLETE_STATUS)));

                                String getsircuscomp = c5.getString(c5.getColumnIndex(db.COMPLETE_STATUS));

                                Log.e("EEEEDS","value"+c5.getString(c5.getColumnIndex(db.COMPLETE_STATUS)));
                                if(getsircuscomp.equalsIgnoreCase("2")) {



                                    Log.e("EEEEDS","entry12");
                                    sirobsub.setVisibility(View.GONE);
                                    sirobbck.setVisibility(View.GONE);
                                    sirobsub1.setVisibility(View.VISIBLE);
                                    sirobsloc.setEnabled(false);
                                    sirobsdisplay.setEnabled(false);
                                    sirobsrecopci.setEnabled(false);
                                    sirobsrecocus.setEnabled(false);
                                    sirobserres.setEnabled(false);
                                    sirobsbtn2.setEnabled(false);

                                    sirobsloc.setText(getlocation);
                                    sirobsdisplay.setText(getpestobserr);
                                    sirobsrecopci.setText(getrecomm);
                                    sirobsrecocus.setText(getcusrecomm);

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

                                    Update_Status = "2";

                                    if(getsirobsers.equalsIgnoreCase("NEW")){
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    }
                                    else if(getsirobsers.equalsIgnoreCase("REPEAT")){

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    }

                                    else if(getsirobsers.equalsIgnoreCase("PREVIOUS AUDIT CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    }

                                    else if(getsirobsers.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }



                                    if(getsirprir.equalsIgnoreCase("HIGH")){
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(getsirprir.equalsIgnoreCase("MEDIUM")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(getsirprir.equalsIgnoreCase("LOW")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }

                                    if(getsirassign.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(getsirassign.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(getsirassign.equalsIgnoreCase("BOTH")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }



                                    if(getimage_ri.length()!=0){
                                        Image_1=getimage_ri;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(getimage_ri, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));
                                     pd.dismiss(); }
                                else if(getsircuscomp.equalsIgnoreCase("1")) {
                                    if (getimage_ri.length() != 0) {
                                        Image_1 = getimage_ri;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    if(getsirobsers.equalsIgnoreCase("NEW")){
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    }
                                    else if(getsirobsers.equalsIgnoreCase("REPEAT")){

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    }

                                    else if(getsirobsers.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    }

                                    else if(getsirobsers.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }



                                    if(getsirprir.equalsIgnoreCase("HIGH")){
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(getsirprir.equalsIgnoreCase("MEDIUM")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(getsirprir.equalsIgnoreCase("LOW")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }

                                    if(getsirassign.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(getsirassign.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(getsirassign.equalsIgnoreCase("BOTH")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }
                                    sirobsloc.setText(getlocation);
                                    sirobsdisplay.setText(getpestobserr);
                                    sirobsrecopci.setText(getrecomm);
                                    sirobsrecocus.setText(getcusrecomm);


                                    byte[] decodedString_c = Base64.decode(getimage_ri, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));



                                    Update_Status = "1";


                                }

                            }

//                            Toast.makeText( SIR_Obser_3.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        //mobile.setText( error.getMessage() );
                        Log.e("FFFFF err","SIR obser = "+error.getMessage());
                    }
                } )
                .requestJson();
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
