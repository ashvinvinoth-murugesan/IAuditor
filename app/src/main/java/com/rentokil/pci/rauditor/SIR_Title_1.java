package com.rentokil.pci.rauditor;


import com.google.firebase.analytics.FirebaseAnalytics;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dmax.dialog.SpotsDialog;

public class SIR_Title_1 extends AppCompatActivity {


    Button sirba,sirsub,sirsubcomplete;
    EditText sirtitleetreportno,sirtitleetcustname,sirtitleetcontctperson,sirtitleetemail,sirtitleetphone,sirtitleetbranch,sirtitleetauditedby;

    TextView getlocations;
    String lati,longi;

    LinearLayout SIR_lin_lay;

    private android.app.AlertDialog pd;

    EditText results;
    String myJSON;
    JSONArray json_arr_cus_result;
    Geocoder geocoder;
    List<Address> addressList;
    GPSTracker gps;
    static String Main_ID="0";


    protected static final String TAG = "LocationOnOff";
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;


    private FirebaseAnalytics mFirebaseAnalytics;

    // GPSTracker class    GPSTracker gps;

    Context mContext;

    double latitude,longitude;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;




    String sirtitlereportd,sirtitlmaild,sirtitleetcontctd,sirtitlemobd,sirtitleconductd,sirtitlearead,sirtitleauditd,sirtitlenamed,sirtitleadressd;

    TextView date_tv,date,txt45,sirtitleetconducted;
    int follow = 0;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private long mLastClickTime = 0;
    Calendar cal = Calendar.getInstance();
    int startYear = cal.get(Calendar.YEAR);
    int startMonth = cal.get(Calendar.MONTH);
    int startDay = cal.get(Calendar.DAY_OF_MONTH);
    private SimpleDateFormat dateFormatter;
    public static final int RequestPermissionCode = 1;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    public String Update_Status="0";
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;



    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    LinearLayout relsnack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sir_title);

        this.setFinishOnTouchOutside(true);

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) SIR_Title_1.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(SIR_Title_1.this)) {
//            Toast.makeText(SIR_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(SIR_Title_1.this)){
            Toast.makeText(SIR_Title_1.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(SIR_Title_1.this)) {
            Log.e("keshav","Gps already enabled");
            Toast.makeText(SIR_Title_1.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{
            Log.e("keshav","Gps already enabled");
//            Toast.makeText(SIR_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
        }
        pd = new SpotsDialog(SIR_Title_1.this, R.style.Custom);
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(SIR_Title_1.this);
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
                        Toast.makeText(SIR_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(SIR_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        sirsub =(Button) findViewById(R.id.sirsubmit);
        sirba=(Button) findViewById(R.id.sirback);
        sirtitleetreportno=(EditText) findViewById(R.id.sirtitleetreportno);


        db = new DatabaseHelper(SIR_Title_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        SIR_lin_lay=(LinearLayout) findViewById(R.id.SIR_lin_lay);
        sirtitleetcustname=(EditText) findViewById(R.id.sirtitleetcustname);
        relsnack=(LinearLayout) findViewById(R.id.relsnack);
        sirtitleetemail=(EditText) findViewById(R.id.sirtitleetemail);
        sirtitleetcontctperson=(EditText) findViewById(R.id.sirtitleetcontctperson);
        sirtitleetphone=(EditText) findViewById(R.id.sirtitleetphone);
        sirtitleetconducted=(TextView) findViewById(R.id.date_calendar);
        sirtitleetbranch=(EditText) findViewById(R.id.sirtitleetbranch);
        sirtitleetauditedby=(EditText) findViewById(R.id.sirtitauditedby);

        sirsubcomplete=(Button) findViewById(R.id.sirsubmitcomplete);

        sirsubcomplete.setVisibility(View.GONE);

        sirsubcomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("DDD","Success comp");
                Intent i = new Intent(SIR_Title_1.this,SIR_Cus_2.class);
                startActivity(i);

            }
        });



        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        mLastClickTime = SystemClock.elapsedRealtime();

        date = (TextView) findViewById(R.id.date_calendar);
        date.setText(formattedDate);
        setDateTimeField();
        if (!Main_ID.equals("0")) {
           // String keyid=null;
            getData(Main_ID);
        }


        //  setDateTimeField();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                follow = 1;
                fromDatePickerDialog.show();
            }
        });


        get_username_data();




        sirsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(SIR_Title_1.this);
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
                                Toast.makeText(SIR_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(SIR_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }



    Log.e("DDDSDF","entry3");
    if(validation()) {

        if (isValidMail(sirtitleetemail.getText().toString())) {
            if (isValidPhone(sirtitleetphone.getText().toString())) {

                sir_title_button_next();
                submitres();
            } else {

                Toast.makeText( getApplicationContext(),"Invalid Mobile Number",Toast.LENGTH_SHORT ).show();

            }


        }
        else {
            Toast.makeText( getApplicationContext(),"Invalid Email id",Toast.LENGTH_SHORT ).show();

        }


    }else {



        Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
    }


}
        });
          Intent intent1=getIntent();
          String id=intent1.getStringExtra("key_id");
          Log.e("FFFFFF Sir main",""+id);
          Log.e("FFFFFF Sir main",""+Main_ID);

        if(id!=null){
            getData(id);
          }
        sirba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sir_title_button_back();

                Intent i = new Intent(SIR_Title_1.this,Category_Type_Activity.class);
                startActivity(i);
            }
        });

        results = (EditText) findViewById(R.id.sirtitleresults);

        mContext = this;
        getlocations = (TextView) findViewById(R.id.sirtitlegetLocation);

        getlocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geocoder = new Geocoder(SIR_Title_1.this, Locale.getDefault());





                try {

                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED

                            && ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED) {
                             ActivityCompat.requestPermissions(SIR_Title_1.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                    } else {

                        gps = new GPSTracker(mContext, SIR_Title_1.this);

                        // Check if GPS enabled

                        if (gps.canGetLocation()) {

                            latitude = gps.getLatitude();
                            longitude = gps.getLongitude();

                            // \n is for new line


                            lati = Double.toString(latitude);
                            longi = Double.toString(longitude);

                        } else {
                            // Can't get location.

                            // GPS or network is not enabled.

                            // Ask user to enable GPS/network in settings.

                            gps.showSettingsAlert();
                        }
                    }

                        addressList = geocoder.getFromLocation(latitude,longitude,1);
                    try {
                        String addressStr = addressList.get(0).getAddressLine(0);
                        String areaStr = addressList.get(0).getLocality();
                        String cityStr = addressList.get(0).getAdminArea();
                        String countryStr = addressList.get(0).getCountryName();
                        String postalcodeStr = addressList.get(0).getPostalCode();

                        String fullAddress = addressStr+", "+areaStr+", "+cityStr+", "+countryStr+", "+postalcodeStr;

                        results.setText(fullAddress);
                    } catch (Exception e) {
                        Log.e("DDDDD",""+e.getMessage());
                        Toast.makeText(getApplicationContext(),"Press Again To Get Address",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        EnableRuntimePermission();


    }


    private void sir_title_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void sir_title_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

    private boolean isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();

        if(!check) {
            sirtitleetemail.setError("Not Valid Email");

            Snackbar snack;
            snack = Snackbar.make(relsnack, "Invalid Email", Snackbar.LENGTH_LONG);
            View view = snack.getView();
            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
            params.gravity = Gravity.BOTTOM;
            view.setLayoutParams(params);
            snack.show();
        }
        return check;
    }

    private boolean isValidPhone(String phone)
    {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone))
        {
            if(phone.length() < 10 || phone.length() > 11)
            {
                sirtitleetphone.setError("Invalid Mobile No");
                check = false;

            }
            else
            {
                check = true;

            }
        }
        else
        {
            check=false;
        }
        return check;
    }



    private boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context
                .getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null)
            return false;
        final List<String> providers = mgr.getAllProviders();
        if (providers == null)
            return false;
        return providers.contains(LocationManager.GPS_PROVIDER);
    }

    private void enableLoc() {

        if (googleApiClient == null) {
            googleApiClient = new GoogleApiClient.Builder(SIR_Title_1.this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                        @Override
                        public void onConnected(Bundle bundle) {

                        }

                        @Override
                        public void onConnectionSuspended(int i) {
                            googleApiClient.connect();
                        }
                    })
                    .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                        @Override
                        public void onConnectionFailed(ConnectionResult connectionResult) {

                            Log.d("Location error","Location error " + connectionResult.getErrorCode());
                        }
                    }).build();
            googleApiClient.connect();

            LocationRequest locationRequest = LocationRequest.create();
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            locationRequest.setInterval(30 * 1000);
            locationRequest.setFastestInterval(5 * 1000);
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(locationRequest);

            builder.setAlwaysShow(true);

            PendingResult<LocationSettingsResult> result =
                    LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
            result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status = result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                // Show the dialog by calling startResolutionForResult(),
                                // and check the result in onActivityResult().
                                status.startResolutionForResult(SIR_Title_1.this, REQUEST_LOCATION);

                                finish();
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                    }
                }
            });
        }
    }


    private void get_username_data(){

        String  url="https://rauditor.riflows.com/rauditor/Android/ia_profile.php?mail_id=" + Category_Type_Activity.User_Login_Mail + "" ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String user_name = c.getString( "user_name" );
                                String branch = c.getString( "branch" );
                                String manager_mail = c.getString( "manager_mail" );


                                cv2.put(db.AUDIT_BY_NAME, user_name);
                                cv2.put(db.REP_BRANCH, branch);
                                sd.insert(db.BRANCH_AUDITBY_TABLE, null, cv2);

                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME)));
                                Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.REP_BRANCH)));

                                String getusername = c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME));
                                String getbranch = c5.getString(c5.getColumnIndex(db.REP_BRANCH));
                                sirtitleetauditedby.setText(getusername);
                                sirtitleetbranch.setText(getbranch);




                                sirtitleetauditedby.setEnabled(false);
                                sirtitleetbranch.setEnabled(false);


                            }

//                            Toast.makeText( getActivity(), "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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



    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(SIR_Title_1.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(SIR_Title_1.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(SIR_Title_1.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(SIR_Title_1.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(SIR_Title_1.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE},RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,

                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.

                if (grantResults.length > 0

                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    gps = new GPSTracker(mContext, SIR_Title_1.this);

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line


                    } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the

                    // functionality that depends on this permission.

                }
                return;
            }
        }
    }
    public boolean validation(){
        sirtitlereportd = sirtitleetreportno.getText().toString();
        sirtitlmaild = sirtitleetemail.getText().toString();
        sirtitleetcontctd = sirtitleetcontctperson.getText().toString();
        sirtitlemobd = sirtitleetphone.getText().toString();
        sirtitleconductd = sirtitleetconducted.getText().toString();
        sirtitlearead= sirtitleetbranch.getText().toString();
        sirtitleauditd = sirtitleetauditedby.getText().toString();
        sirtitlenamed = sirtitleetcustname.getText().toString();
        sirtitleadressd = results.getText().toString();




        if(TextUtils.isEmpty(sirtitlmaild ) || TextUtils.isEmpty(sirtitlereportd) ||TextUtils.isEmpty(sirtitleetcontctd)
                ||TextUtils.isEmpty(sirtitlemobd)|| TextUtils.isEmpty(sirtitlearead)|| TextUtils.isEmpty(sirtitleconductd)
                || TextUtils.isEmpty(sirtitleauditd) || TextUtils.isEmpty(sirtitlenamed) ) {

            if(TextUtils.isEmpty(sirtitlmaild )){
                sirtitleetemail.setError("Required");
            }

            if(TextUtils.isEmpty(sirtitlereportd)) {
                sirtitleetreportno.setError("Required");


            }
            if(TextUtils.isEmpty(sirtitleetcontctd)) {
                sirtitleetcontctperson.setError("Required");

            }
            if(TextUtils.isEmpty(sirtitlemobd)) {
                sirtitleetphone.setError("Required");

            }
            if(TextUtils.isEmpty(sirtitleconductd)) {
                sirtitleetconducted.setError("Required");

            }
            if(TextUtils.isEmpty(sirtitlearead)) {
                sirtitleetbranch.setError("Required");

            }
            if(TextUtils.isEmpty(sirtitleauditd)) {
                sirtitleetauditedby.setError("Required");

            }
            if(TextUtils.isEmpty(sirtitlenamed)) {
                sirtitleetcustname.setError("Required");

            }

//
//            sirtitleetreportno.setError("Required");
//            sirtitleetcontctperson.setError("Required");
//            sirtitleetphone.setError("Required");
//
//            sirtitleetbranch.setError("Required");
//            sirtitleetauditedby.setError("Required");
//            sirtitleetcustname.setError("Required");
//            results.setError("Required");






            return false;
        }



        if(sirtitlereportd.length()==0||sirtitlmaild.length()==0||sirtitleetcontctd.length()==0||
                sirtitlemobd.length()==0||sirtitleconductd.length()==0||sirtitlearead.length()==0||
                sirtitleauditd.length()==0||sirtitlenamed.length()==0||sirtitleadressd.length()==0){
            return false;
        }else {
            return  true;
        }
    }

    private void setDateTimeField() {

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        monthOfYear = c.get(Calendar.MONTH);
        dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
        fromDatePickerDialog = new DatePickerDialog



                (this,R.style.Theme_AppCompat_DayNight_DarkActionBar, new DatePickerDialog.OnDateSetListener() {

                    public void onDateSet(DatePicker view, int

                            year, int monthOfYear, int dayOfMonth) {
                        if (year == 0 || monthOfYear == 0 || dayOfMonth == 0) {

                        }
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, monthOfYear, dayOfMonth);
                        if (true) {
                            date.setText

                                    (dateFormatter.format(newDate.getTime()));
                        }

                    }

                }, year, monthOfYear, dayOfMonth);





        toDatePickerDialog = new DatePickerDialog(this,R.style.Theme_AppCompat_DayNight_DarkActionBar,

                new DatePickerDialog.OnDateSetListener() {


                    public void onDateSet(DatePicker view, int

                            year, int monthOfYear, int dayOfMonth) {
                        Log.i("calender", "20\t" + startDay);
                        startYear = year;
                        startMonth = monthOfYear;
                        startDay = dayOfMonth;

                        cal.set(startYear, startMonth,

                                startDay);
                        if (follow == 1) {
                            date.setText


                                    (dateFormatter.format(cal.getTime()));


                        }
                        if (true) {

                        }

                    }

                }, year, monthOfYear, dayOfMonth);






        toDatePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                });
    }

    private void submitres() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("sirtitlereportnoi",sirtitlereportd);
        params.put("user_mail",Category_Type_Activity.User_Login_Mail);
        params.put("sirtitlenamei",sirtitlenamed);
        params.put("sirtitleadressi",sirtitleadressd);
        params.put("sirtitleetcontcti",sirtitleetcontctd);
        params.put("sirtitlmaili",sirtitlmaild);
        params.put("sirtitlemobi",sirtitlemobd);
        params.put("sirtitleconducti",sirtitleconductd);
        params.put("sirtitleareai",sirtitlearead);
        params.put("sirtitleaudioni",sirtitleauditd);
        params.put("version",versionName);
        params.put("update_status",Update_Status);
        params.put("main_id",Main_ID);

Log.e("CCCCDDS","param = "+params);
Log.e("CCCCDDSW","main id="+Main_ID);

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/SIR/in_sir_title_1.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {



                            pd.dismiss();
                            Log.e("DDDDDD","resds =  "+response);
//                            String data = response.getString( "message" );

                          JSONObject jsonObject = new JSONObject(response);

                            Main_ID=jsonObject.getString("id");

                            Log.e("FFFFF Main_ID",""+Main_ID);
                            Intent i = new Intent(SIR_Title_1.this,SIR_Cus_2.class);
                            startActivity(i);




//                            Toast.makeText( SIR_Title_1.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
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

                        if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference")){

                         submitres();

                         Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }

                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }

    private void getData(String key_id){

        pd.show();
        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/SIR/get_sir_title_1.php?main_id="+Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("RRRR","response = "+response);

                            pd.dismiss();
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                         //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "user_id" );
                                String customer_name = c.getString( "cust_name" );
                                String contactperson = c.getString( "cont_person" );
                                String getreport = c.getString( "report_no" );
                                String getaddress = c.getString( "address" );
                                String getphone = c.getString( "Phone" );
                                String getemail = c.getString( "email" );
                                String getdate = c.getString( "cond_date" );
                                String sirstatus = c.getString( "status" );


                                Log.e("DDD","wel 1 \t"+sirstatus);

                                if(sirstatus.equalsIgnoreCase("Pending")){

                                    sd.delete(db.USER_COMPLETE_STATUS,null,null);

                                    Log.e("DDD","wel 1 ");


                                sirtitleetcustname.setText(customer_name);
                                sirtitleetcontctperson.setText(contactperson);
                                sirtitleetemail.setText(getemail);
                                sirtitleetreportno.setText(getreport);
                                results.setText(getaddress);
                                sirtitleetphone.setText(getphone);
                                sirtitleetconducted.setText(getdate);

                                    cv1.put(db.COMPLETE_STATUS, "1");
                                    sd.insert(db.USER_COMPLETE_STATUS, null, cv1);


                                Update_Status="1";

                                }
                                else if(sirstatus.equalsIgnoreCase("Completed")){


                                   pd.show();

                                    SIR_lin_lay.setEnabled(false);
                                    Log.e("DDD","wel 2 ");
                                    sd.delete(db.USER_COMPLETE_STATUS,null,null);
                                    sirsub.setVisibility(View.GONE);

                                    sirba.setVisibility(View.GONE);
                                    sirsubcomplete.setVisibility(View.VISIBLE);



                                    sirtitleetcustname.setEnabled(false);
                                    sirtitleetcontctperson.setEnabled(false);
                                    sirtitleetemail.setEnabled(false);
                                    sirtitleetreportno.setEnabled(false);
                                    sirtitleetauditedby.setEnabled(false);
                                    sirtitleetbranch.setEnabled(false);
                                    results.setEnabled(false);
                                    sirtitleetphone.setEnabled(false);
                                    sirtitleetconducted.setEnabled(false);

                                    sirtitleetcustname.setTypeface(sirtitleetcustname.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirtitleetcontctperson.setTypeface(sirtitleetcontctperson.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirtitleetemail.setTypeface(sirtitleetemail.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirtitleetreportno.setTypeface(sirtitleetreportno.getTypeface(), Typeface.BOLD_ITALIC);
                                    results.setTypeface(results.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirtitleetphone.setTypeface(sirtitleetphone.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirtitleetconducted.setTypeface(sirtitleetconducted.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirtitleetbranch.setTypeface(sirtitleetbranch.getTypeface(), Typeface.BOLD_ITALIC);
                                    sirtitleetauditedby.setTypeface(sirtitleetauditedby.getTypeface(), Typeface.BOLD_ITALIC);

                                    sirtitleetcustname.setText(customer_name);
                                    sirtitleetcontctperson.setText(contactperson);
                                    sirtitleetemail.setText(getemail);
                                    sirtitleetreportno.setText(getreport);
                                    results.setText(getaddress);
                                    sirtitleetphone.setText(getphone);
                                    sirtitleetconducted.setText(getdate);

                                    Cursor c5;
                                    c5 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
                                    c5.moveToFirst();

                                    Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME)));

                                    String getusername = c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME));
                                    String getbranch = c5.getString(c5.getColumnIndex(db.REP_BRANCH));
                                    sirtitleetauditedby.setText(getusername);
                                    sirtitleetbranch.setText(getbranch);
                                    Update_Status="2";

                                    cv1.put(db.COMPLETE_STATUS, "2");
                                    sd.insert(db.USER_COMPLETE_STATUS, null, cv1);

pd.dismiss();


                                }




                            }

//                            Toast.makeText( SIR_Title_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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

                        pd.dismiss();

                        Log.e("CCCC","error = "+error.getMessage());


                    }
                } )
                .requestJson();
    }



}
