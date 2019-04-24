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
import android.graphics.Typeface;
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

public class PMCA_Obser_3 extends AppCompatActivity {

    Button sirobsub,sirobbck;
    TextView sirobserres,sirobsercounter,sirobsbtn2;
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
    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    private android.app.AlertDialog pd;

    public static final int RequestPermissionCode = 1;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    private RadioButton radiovaxButton,radiovaxButton2,radiovaxButton3;

    private RadioGroup radiovaxGroup,radiovaxGroup2,radiovaxGroup3;

    EditText sirobsloc,sirobsrecopci,sirobsrecocus;

    private  int counter=1;

    String sirobslocation,sirobsrecpci,sirobsrecus,sirobssdisp,radiogets1,radiogets2,radiogets3;

    public  static TextView pestobsdisp ;

    static String getradio1,getradio2,getradio3;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    Button pmca_obs_sub;


    private FirebaseAnalytics mFirebaseAnalytics;

    String Image_1;


    ArrayList<String> loc_arr = new ArrayList<String>();
    ArrayList<String> pest_cover_arr = new ArrayList<String>();
    ArrayList<String> image_arr = new ArrayList<String>();
    ArrayList<String> recomm_rpci_arr = new ArrayList<String>();
    ArrayList<String> cust_arr = new ArrayList<String>();
    ArrayList<String> status_arr = new ArrayList<String>();
    ArrayList<String> priority_arr = new ArrayList<String>();
    ArrayList<String> ass_to_arr = new ArrayList<String>();
    ArrayList<String> bitmapst = new ArrayList<String>();
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pestman_obser);

        db = new DatabaseHelper(PMCA_Obser_3.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        EnableRuntimePermission();
        pd = new SpotsDialog(PMCA_Obser_3.this, R.style.Custom);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

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


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(PMCA_Obser_3.this);
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
                        Toast.makeText(PMCA_Obser_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(PMCA_Obser_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }



        pmca_obs_sub =(Button) findViewById(R.id.pmca_obs_sub);
        sirobsub =(Button) findViewById(R.id.sirobsub);
        sirobbck=(Button) findViewById(R.id.sirobbck);
        sirobsercounter=(TextView) findViewById(R.id.sirobsercounter);
        sirobsbtn2=(TextView) findViewById(R.id.sirobsbtn2);

        laytest=(LinearLayout) findViewById(R.id.sirobserlayout);

//        laytest.setVisibility(View.GONE);

        sirobsrecopci =(EditText) findViewById(R.id.sirobserrecorentopci);
        sirobsrecocus =(EditText) findViewById(R.id.sirobserrecorentocus);

        pmca_obs_sub.setVisibility(View.GONE);
        pmca_obs_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();
                pmca_obser_button_next();
                Intent i = new Intent(PMCA_Obser_3.this,PMCA_Summary_4.class);
                startActivity(i);
                finish();
            }
        });

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            PMCA_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!PMCA_Title_1.Main_ID.equals("0")) {
                // String keyid=null;
                getData(PMCA_Title_1.Main_ID);
            }

        }
        sirobsloc =(EditText) findViewById(R.id.sirobslocation);

        sirobserres=(TextView) findViewById(R.id.sirobserresponse);

        pestobsdisp=(TextView) findViewById(R.id.sirobsdisplay);


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

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

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

                pmca_obser_button_next();

//                laytest.setVisibility(View.VISIBLE);
                Log.e("MMMMM count ",""+counter);

                if (sirobsloc.length() == 0 && sirobsrecopci.length()==0 && sirobsrecocus.length()==0) {
//                    Toast.makeText(getApplicationContext(),
//                            "All fields are mandatory to fill", Toast.LENGTH_SHORT)
//                            .show();
                }if (sirobsloc.length() == 0) {
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
                    Log.e("MMMMM else","else"+counter);
                    Log.e("MMMMM loc",""+sirobsloc.getText().toString());
                    if (counter!=0) {
                        pmca_obser_button_next();
                        getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                        getradio2 = ((RadioButton)findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                        getradio3 = ((RadioButton)findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                        loc_arr.add(sirobsloc.getText().toString());
                        pest_cover_arr.add(pestobsdisp.getText().toString());
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


                    counter++;
                    sirobsercounter.setText("" + counter);

                    if(counter>1){

                        pmca_obser_button_next();

                        sirobsloc.getText().clear();
                        sirobsrecopci.getText().clear();
                        sirobsrecocus.getText().clear();
                        radiovaxGroup.clearCheck();
                        radiovaxGroup2.clearCheck();
                        radiovaxGroup3.clearCheck();
                        pestobsdisp.setText("");
                        Image_layout_Q1.setImageDrawable(null);


//                        for (int i = 0; i <sirradio1.getChildCount(); i++)
//                        {
//                            sirradio1.clearCheck();
//                        }
                    }
                }
            }
        });

        sirobsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("MMMMM else","submit");

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {

                } else {
                    final Dialog dialog = new Dialog(PMCA_Obser_3.this);
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
                                Toast.makeText(PMCA_Obser_3.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(PMCA_Obser_3.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }






                if (validation()) {
                        try {
                            if (counter!=0) {
                                getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();
                                getradio2 = ((RadioButton)findViewById(radiovaxGroup2.getCheckedRadioButtonId())).getText().toString();
                                getradio3 = ((RadioButton)findViewById(radiovaxGroup3.getCheckedRadioButtonId())).getText().toString();
                                loc_arr.add(sirobsloc.getText().toString());
                                pest_cover_arr.add(pestobsdisp.getText().toString());
                                recomm_rpci_arr.add(sirobsrecopci.getText().toString());
                                cust_arr.add(sirobsrecocus.getText().toString());
                                bitmapImage1=((BitmapDrawable) Image_layout_Q1.getDrawable()).getBitmap();
                                //   bitmapst[0]=getStringImage(bitmapImage1);
                                if (bitmapImage1!=null){
                                    bitmapst.add(getStringImage(bitmapImage1));
                                }

                                status_arr.add(getradio1);
                                priority_arr.add(getradio2);
                                ass_to_arr.add(getradio3);

                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        }
                    pmca_obser_button_next();
                        post_obser_js();
                    } else {
                        Toast.makeText( getApplicationContext(),"Please fill all mandatory fileds",Toast.LENGTH_SHORT ).show();
                    }



            }
        });

        sirobbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pmca_obser_button_back();

                Intent i = new Intent(PMCA_Obser_3.this, PMCA_Cus_Info_2.class);
                startActivity(i);

            }
        });

        sirobsbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sirobs(999);


            }
        });

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

        AlertDialog.Builder builder = new AlertDialog.Builder(PMCA_Obser_3.this);
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
    public  boolean validation(){

        sirobslocation=sirobsloc.getText().toString();
        sirobsrecpci=sirobsrecopci.getText().toString();
        sirobsrecus=sirobsrecocus.getText().toString();
        sirobssdisp=pestobsdisp.getText().toString();


        if(TextUtils.isEmpty(sirobslocation ) || TextUtils.isEmpty(sirobsrecpci) ||TextUtils.isEmpty(sirobsrecus)
                ||TextUtils.isEmpty(sirobssdisp)) {

            if(TextUtils.isEmpty(sirobslocation )){
                sirobsloc.setError("Required");
            }

            if(TextUtils.isEmpty(sirobsrecpci)) {
                sirobsrecopci.setError("Required");


            }
            if(TextUtils.isEmpty(sirobsrecus)) {
                sirobsrecocus.setError("Required");

            }
            if(TextUtils.isEmpty(sirobssdisp)) {
                pestobsdisp.setError("Required");

            }




            return false;
        }



        if (loc_arr.size()!=0) {
            if(sirobslocation.length()==0||sirobsrecpci.length()==0||sirobsrecus.length()==0||sirobssdisp.length()==0){
                return  false;
            }else {
                return  true;
            }


        }else {
            return true;
        }


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

        if (ActivityCompat.shouldShowRequestPermissionRationale(PMCA_Obser_3.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(PMCA_Obser_3.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(PMCA_Obser_3.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(PMCA_Obser_3.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(PMCA_Obser_3.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }


    public void sirobs(int status) {

        Bundle bundle = new Bundle();
        if(status==999) {
            bundle.putString("status", "999");
        }

        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
    }


    private void post_obser_js() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();

        params.put("count",""+loc_arr.size());
        Log.e("MMMMM size",""+loc_arr.size());
        params.put("main_id",PMCA_Title_1.Main_ID);

        for (int i=0;i<loc_arr.size();i++) {
            params.put("sirobslocationq" + i, loc_arr.get(i));
            params.put("sirobsrecpciq" + i, recomm_rpci_arr.get(i));
            params.put("sirobsrecusq" + i, cust_arr.get(i));
            params.put("sirobssdispq" + i, pest_cover_arr.get(i));
            params.put("getradio1q" + i, status_arr.get(i));
            params.put("getradio2q" + i, priority_arr.get(i));
            params.put("getradio3q" + i, ass_to_arr.get(i));
            params.put("addmediai" + i, bitmapst.get(i));
            params.put("update_status", Update_Status);

        }


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/PMCAR/in_pmca_obser_3.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();
                            Log.e( "JJJJJJ","PMCA Obser 3"+response );
                            Intent i = new Intent(PMCA_Obser_3.this,PMCA_Summary_4.class);
                            startActivity(i);


                            //   Toast.makeText( PMCA_Obser_3.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e( "ERRR eror","RRRR"+error.getMessage() );
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }

//    private void post_obser_js() {
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://rauditor.riflows.com/rauditor/Android/PMCAR/in_pmca_obser_3.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//
//                       // Toast.makeText(PMCA_Obser_3.this,response,Toast.LENGTH_LONG).show();
//                        Log.e( "JJJJJJ","PMCA Obser 3"+response );
//                        Intent i = new Intent(PMCA_Obser_3.this,PMCA_Summary_4.class);
//                        startActivity(i);
//
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
//                params.put("count",""+loc_arr.size());
//                Log.e("MMMMM size",""+loc_arr.size());
//                params.put("main_id",PMCA_Title_1.Main_ID);
//                for (int i=0;i<loc_arr.size();i++) {
//                    params.put("sirobslocationq"+i,loc_arr.get(i));
//                    params.put("sirobsrecpciq"+i,recomm_rpci_arr.get(i));
//                    params.put("sirobsrecusq"+i,cust_arr.get(i));
//                    params.put("sirobssdispq"+i,pest_cover_arr.get(i));
//                    params.put("getradio1q"+i,status_arr.get(i));
//                    params.put("getradio2q"+i,priority_arr.get(i));
//                    params.put("getradio3q"+i,ass_to_arr.get(i));
//                    params.put("addmediai"+i,bitmapst.get(i));
//                    params.put("update_status",Update_Status);
//
//                }
//                Log.e("MMMMM params",""+params);
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


    public void getData(String key_id){
        pd.show();

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            PMCA_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/PMCAR/get_pmca_obs_3.php?main_id="+PMCA_Title_1.Main_ID;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            Log.e( "couneeee","getcount\t"+counter );
                            Log.e( "JJJJJJ","Profile_Fragment\t"+myJSON );

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");

                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString("main_id");
                                String getlocation = c.getString("location");
                                String getpestobserr = c.getString("pest_obser");
                                String getrecomm = c.getString("ri_recomm");
                                String getcusrecomm = c.getString("cust_recomm");
                                String get_pmca_obser_stat = c.getString("obser_status");
                                String get_pmca_prior = c.getString("priority");
                                String get_pmca_assign_to = c.getString("assign_to");
                                String get_pmca_image_ri = c.getString( "image_ri" );

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.PMCA_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.PMCA_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.PMCA_COMPLETE_STATUS));

                                if(get_pmca_complete.equalsIgnoreCase("2")) {


                                    sirobsub.setVisibility(View.GONE);
                                    sirobbck.setVisibility(View.GONE);
                                    pmca_obs_sub.setVisibility(View.VISIBLE);


                                    sirobsloc.setEnabled(false);
                                    pestobsdisp.setEnabled(false);
                                    sirobsrecopci.setEnabled(false);
                                    sirobsrecocus.setEnabled(false);
                                    sirobsbtn2.setEnabled(false);
                                    sirobserres.setEnabled(false);

                                    sirobsloc.setTypeface(sirobsloc.getTypeface(), Typeface.BOLD_ITALIC);
                                    pestobsdisp.setTypeface(pestobsdisp.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirobsrecopci.setTypeface(sirobsrecopci.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirobsrecocus.setTypeface(sirobsrecocus.getTypeface(), Typeface.BOLD_ITALIC);

                                    sirobsloc.setText(getlocation);
                                    pestobsdisp.setText(getpestobserr);
                                    sirobsrecopci.setText(getrecomm);
                                    sirobsrecocus.setText(getcusrecomm);
                                    Update_Status = "2";

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

                                    if(get_pmca_obser_stat.equalsIgnoreCase("NEW")){
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pmca_obser_stat.equalsIgnoreCase("REPEAT")){

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pmca_obser_stat.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    }

                                    else if(get_pmca_obser_stat.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }



                                    if(get_pmca_prior.equalsIgnoreCase("HIGH")){
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pmca_prior.equalsIgnoreCase("MEDIUM")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pmca_prior.equalsIgnoreCase("LOW")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_pmca_assign_to.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pmca_assign_to.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_pmca_assign_to.equalsIgnoreCase("BOTH")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_pmca_image_ri.length()!=0){
                                        Image_1=get_pmca_image_ri;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_pmca_image_ri, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));


pd.dismiss();

                                }

                                else if (get_pmca_complete.equalsIgnoreCase("1")) {


                                    sirobsloc.setText(getlocation);
                                    pestobsdisp.setText(getpestobserr);
                                    sirobsrecopci.setText(getrecomm);
                                    sirobsrecocus.setText(getcusrecomm);

                                    Update_Status = "1";

                                    if(get_pmca_image_ri.length()!=0){
                                        Image_1=get_pmca_image_ri;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(get_pmca_image_ri, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Image_layout_Q1.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));


                                    if(get_pmca_obser_stat.equalsIgnoreCase("NEW")){
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pmca_obser_stat.equalsIgnoreCase("REPEAT")){

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pmca_obser_stat.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    }

                                    else if(get_pmca_obser_stat.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")){

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }



                                    if(get_pmca_prior.equalsIgnoreCase("HIGH")){
                                        ((RadioButton) radiovaxGroup2.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pmca_prior.equalsIgnoreCase("MEDIUM")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pmca_prior.equalsIgnoreCase("LOW")){

                                        ((RadioButton) radiovaxGroup2.getChildAt(2)).setChecked(true);

                                    }

                                    if(get_pmca_assign_to.equalsIgnoreCase("RENTOKIL PCI")){
                                        ((RadioButton) radiovaxGroup3.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pmca_assign_to.equalsIgnoreCase("CUSTOMER")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(1)).setChecked(true);

                                    }
                                    else if(get_pmca_assign_to.equalsIgnoreCase("BOTH")){

                                        ((RadioButton) radiovaxGroup3.getChildAt(2)).setChecked(true);

                                    }

                                }
                            }

                            // Toast.makeText( PMCA_Obser_3.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e("VCVC","error obs 3"+error.getMessage());
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }

    @Override

    public void onBackPressed(){

    }


    private void pmca_obser_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "pmca_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "pmca_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void pmca_obser_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "pmca_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "pmca_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }




}
