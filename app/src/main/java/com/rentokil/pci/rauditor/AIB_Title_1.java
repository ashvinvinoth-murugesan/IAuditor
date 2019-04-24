package com.rentokil.pci.rauditor;

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
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.firebase.analytics.FirebaseAnalytics;
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

public class AIB_Title_1 extends AppCompatActivity {

    Button aibtitlesub,aibtitlebck,aib_title_finish1;

    TextView aibgetlocations;
    String lati,longi;

    EditText resultsaib;
    Geocoder geocoder;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    List<Address> addressList;
    GPSTracker gps;
    static String Main_ID="0";

    // GPSTracker class    GPSTracker gps;

    Context mContext;

    double latitude,longitude;

    EditText aib_title_et1,aib_title_et2,aib_title_et3,aib_title_et4,aib_title_et5,aib_title_et6,aib_title_et7;

    String getett1,getett2,getett3,getett4,getett5,getett6,getett7,aibtitleaddress,getdate,getradio1;

    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";


    TextView date_tv,date,txt45,sirtitleetconducted;
    int follow = 0;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    private long mLastClickTime = 0;
    Calendar newCalendar = Calendar.getInstance();
    Calendar cal = Calendar.getInstance();
    int startYear = cal.get(Calendar.YEAR);
    int startMonth = cal.get(Calendar.MONTH);
    int startDay = cal.get(Calendar.DAY_OF_MONTH);
    private SimpleDateFormat dateFormatter;
    static String Cover_Date = "2017-10-24";
    Locale myLocale;
    String language_str;

    protected static final int REQUEST_CHECK_SETTINGS = 0x1;

    private RadioButton radiovaxButton;


    private FirebaseAnalytics mFirebaseAnalytics;


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    private RadioGroup radiovaxGroup;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;

    protected static final String TAG = "LocationOnOff";
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;

    LinearLayout relsnack;

    private android.app.AlertDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aib_title);

        this.setFinishOnTouchOutside(true);

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) AIB_Title_1.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(AIB_Title_1.this)) {
            //   Toast.makeText(AIB_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(AIB_Title_1.this)){
            Toast.makeText(AIB_Title_1.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(AIB_Title_1.this)) {
            Log.e("keshav","Gps already enabled");
            Toast.makeText(AIB_Title_1.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{
            Log.e("keshav","Gps already enabled");
            //    Toast.makeText(AIB_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
        }


        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        if (!Main_ID.equals("0")) {
            // String keyid=null;
            getData(Main_ID);
        }


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        db = new DatabaseHelper(AIB_Title_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();

        pd = new SpotsDialog(AIB_Title_1.this, R.style.Custom);


        relsnack=(LinearLayout) findViewById(R.id.relsnack);
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Title_1.this);
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
                        Toast.makeText(AIB_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }
        get_username_data();
        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            getData(id);
        }



        aibtitlesub=(Button) findViewById(R.id.aibtitlesub);
        aibtitlebck=(Button) findViewById(R.id.aibtitlebck);
        aib_title_finish1=(Button) findViewById(R.id.aib_title_finish1);

        aib_title_finish1.setVisibility(View.GONE);

        aib_title_finish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                aib_title_button_next();

                Intent i = new Intent(AIB_Title_1.this,AIB_Cus_Info_2.class);
                startActivity(i);

            }
        });

        aib_title_et1=(EditText) findViewById(R.id.aib_title_et1);
        aib_title_et2=(EditText) findViewById(R.id.aib_title_et2);
        aib_title_et3=(EditText) findViewById(R.id.aib_title_et3);
        aib_title_et4=(EditText) findViewById(R.id.aib_title_et4);
        aib_title_et5=(EditText) findViewById(R.id.aib_title_et5);
        aib_title_et6=(EditText) findViewById(R.id.aib_title_et6);
        aib_title_et7=(EditText) findViewById(R.id.aib_title_et7);

        radiovaxGroup=(RadioGroup) findViewById(R.id.aib_title_rd1);





        sirtitleetconducted=(TextView) findViewById(R.id.date_calendar);


        radiovaxGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=radiovaxGroup.getCheckedRadioButtonId();

                radiovaxButton=(RadioButton)findViewById(selectedId);

            }
        });


        aibtitlesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(AIB_Title_1.this);
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
                                Toast.makeText(AIB_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {


                    if (isValidMail(aib_title_et4.getText().toString())) {
                        if (isValidPhone(aib_title_et5.getText().toString())) {

                            aib_title_button_next();
                            post_submitres();
                        } else {

                            Toast.makeText( getApplicationContext(),"Invalid Mobile Number",Toast.LENGTH_SHORT ).show();

                        }


                    }
                    else {
                        Toast.makeText( getApplicationContext(),"Invalid Email id",Toast.LENGTH_SHORT ).show();

                    }


                } else {
                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }

            }
        });

        aibtitlebck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_title_button_back();

                Intent i = new Intent(AIB_Title_1.this,Category_Type_Activity.class);
                startActivity(i);

            }
        });

        resultsaib = (EditText) findViewById(R.id.aibtitleresult);
        geocoder = new Geocoder(this, Locale.getDefault());



        aibgetlocations = (TextView) findViewById(R.id.aibtitlegetLocation);

        mContext = this;


        aibgetlocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED

                            && ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(AIB_Title_1.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                    } else {

                        gps = new GPSTracker(mContext, AIB_Title_1.this);

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

                        resultsaib.setText(fullAddress);
                    } catch (Exception e) {

                        Toast.makeText(getApplicationContext(),"Press Again To Get Address",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                } catch (IOException e) {


                    e.printStackTrace();
                }
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
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                follow = 1;
                fromDatePickerDialog.show();
            }
        });



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

                                String getusername = c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME));
                                String getbranch = c5.getString(c5.getColumnIndex(db.REP_BRANCH));
                                aib_title_et7.setText(getusername);
                                aib_title_et6.setText(getbranch);

                                aib_title_et7.setEnabled(false);
                                aib_title_et6.setEnabled(false);


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
            aib_title_et4.setError("Not Valid Email");

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
            googleApiClient = new GoogleApiClient.Builder(AIB_Title_1.this)
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
                                status.startResolutionForResult(AIB_Title_1.this, REQUEST_LOCATION);

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






    public  boolean validation(){


        try {
            getett1=aib_title_et1.getText().toString();
            getett2=aib_title_et2.getText().toString();
            getett3=aib_title_et3.getText().toString();
            getett4=aib_title_et4.getText().toString();
            getett5=aib_title_et5.getText().toString();
            getett6=aib_title_et6.getText().toString();
            getett7=aib_title_et7.getText().toString();
            aibtitleaddress=resultsaib.getText().toString();
            getdate=sirtitleetconducted.getText().toString();
            getradio1 = ((RadioButton)findViewById(radiovaxGroup.getCheckedRadioButtonId())).getText().toString();



            if(TextUtils.isEmpty(getett1) || TextUtils.isEmpty(getett2) ||TextUtils.isEmpty(getett3)
                    ||TextUtils.isEmpty(getett4) ||TextUtils.isEmpty(getett5) || TextUtils.isEmpty(getett6) || TextUtils.isEmpty(getett7)
                     || TextUtils.isEmpty(getdate)
                    ) {

                if(TextUtils.isEmpty(getett1 )){
                    aib_title_et1.setError("Required");
                }

                if(TextUtils.isEmpty(getett2)) {
                    aib_title_et2.setError("Required");


                }
                if(TextUtils.isEmpty(getett3)) {
                    aib_title_et3.setError("Required");

                }
                if(TextUtils.isEmpty(getett4)) {
                    aib_title_et4.setError("Required");

                }
                if(TextUtils.isEmpty(getett5)) {
                    aib_title_et5.setError("Required");

                }
                if(TextUtils.isEmpty(getett6)) {
                    aib_title_et6.setError("Required");

                }
                if(TextUtils.isEmpty(getett7)) {
                    aib_title_et7.setError("Required");

                }
                if(TextUtils.isEmpty(getdate)) {
                    sirtitleetconducted.setError("Required");

                }
                return false;
            }


            if ((getett1.length()==0||getett2.length()==0||getett3.length()==0||getett4.length()==0||getett5.length()==0
            ||getett6.length()==0||getett7.length()==0||aibtitleaddress.length()==0||getdate.length()==0||getradio1.length()==0
            )){
                return  false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  false;
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
                    gps = new GPSTracker(mContext, AIB_Title_1.this);

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

    private void post_submitres() {

        pd.show();


        Map<String, String> params = new HashMap<String, String>();
        params.put("getet1",getett1);
        params.put("gtradio1",getradio1);
        params.put("getet2",getett2);
        params.put("getadd",aibtitleaddress);
        params.put("getet3",getett3);
        params.put("getet4",getett4);
        params.put("getet5",getett5);
        params.put("getdate",getdate);
        params.put("getet6",getett6);
        params.put("getet7",getett7);
        params.put("ver_no",versionName);
        params.put("mail_id",Category_Type_Activity.User_Login_Mail);
        params.put("update_status",Update_Status);
        params.put("main_id",Main_ID);

        Log.e("RTRTRTR","getParams"+params);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_title.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        JSONObject jsonObject;
                        try {

                            pd.dismiss();
                            Intent i = new Intent(AIB_Title_1.this,AIB_Cus_Info_2.class);
                            startActivity(i);
                            jsonObject = new JSONObject(response);





                            Main_ID=jsonObject.getString("id");
                            Log.e("FFFFF Main_ID",""+Main_ID);

                            // Toast.makeText( AIB_Title_1.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();

                        Log.e("FDFDF","aib error"+error.getMessage());

                        if(error.getMessage().equalsIgnoreCase("null")){

                            Intent i = new Intent(AIB_Title_1.this,AIB_Cus_Info_2.class);
                            startActivity(i);
                        }
                        else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                        {
                            Intent i = new Intent(AIB_Title_1.this,AIB_Cus_Info_2.class);
                            startActivity(i);

                        }


                        // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }



    private void getData(String key_id){



        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_aib_title_1.php?user_mail=" + Category_Type_Activity.User_Login_Mail +"&main_id="+Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

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
                                String getphone = c.getString( "phone" );
                                String getemail = c.getString( "email" );
                                String getdates = c.getString( "cond_date" );


                                String get_freq= c.getString( "freq" );
                                String get_status= c.getString( "status" );

                                if(get_status.equalsIgnoreCase("Pending")){


                                    aib_title_et2.setText(customer_name);
                                    aib_title_et3.setText(contactperson);
                                    aib_title_et4.setText(getemail);
                                    aib_title_et1.setText(getreport);
                                    resultsaib.setText(getaddress);
                                    sirtitleetconducted.setText(getdates);
                                    aib_title_et5.setText(getphone);


                                    Update_Status="1";

                                    if (get_freq.equalsIgnoreCase("NEW")) {
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    } else if (get_freq.equalsIgnoreCase("REPEAT")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    } else if (get_freq.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    } else if (get_freq.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }


                                    cv3.put(db.AIB_COMPLETE_STATUS, "1");
                                    sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);
                                    Cursor c10;
                                    c10 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                    c10.moveToFirst();

                                    Log.e("DFDFSD1","db = "+c10.getString(c10.getColumnIndex(db.AIB_COMPLETE_STATUS)));




                                }
                                else if(get_status.equalsIgnoreCase("Completed")) {

                                    pd.show();

                                    sd.delete(db.AIB_COMPLETE_STATUS_TABLE,null,null);

                                    aibtitlebck.setVisibility(View.GONE);
                                    aibtitlesub.setVisibility(View.GONE);
                                    aib_title_finish1.setVisibility(View.VISIBLE);

                                    aib_title_et2.setText(customer_name);
                                    aib_title_et3.setText(contactperson);
                                    aib_title_et4.setText(getemail);
                                    sirtitleetconducted.setText(getdates);
                                    aib_title_et1.setText(getreport);
                                    resultsaib.setText(getaddress);
                                    aib_title_et5.setText(getphone);

                                    aib_title_et2.setEnabled(false);
                                    aib_title_et3.setEnabled(false);
                                    aib_title_et4.setEnabled(false);
                                    sirtitleetconducted.setEnabled(false);
                                    aib_title_et1.setEnabled(false);
                                    resultsaib.setEnabled(false);
                                    aib_title_et5.setEnabled(false);

                                    radiovaxGroup.getChildAt(0).setEnabled(false);
                                    radiovaxGroup.getChildAt(1).setEnabled(false);
                                    radiovaxGroup.getChildAt(2).setEnabled(false);
                                    radiovaxGroup.getChildAt(3).setEnabled(false);





                                    if (get_freq.equalsIgnoreCase("NEW")) {
                                        ((RadioButton) radiovaxGroup.getChildAt(0)).setChecked(true);


                                    } else if (get_freq.equalsIgnoreCase("REPEAT")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(1)).setChecked(true);

                                    } else if (get_freq.equalsIgnoreCase("PREVIOUS AUDIT POINT CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(2)).setChecked(true);

                                    } else if (get_freq.equalsIgnoreCase("PREVIOUS AUDIT POINT PARTIALLY CLOSED")) {

                                        ((RadioButton) radiovaxGroup.getChildAt(3)).setChecked(true);

                                    }


                                    Update_Status="2";

                                    cv3.put(db.AIB_COMPLETE_STATUS, "2");
                                    sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);
                                    Cursor c10;
                                    c10 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                    c10.moveToFirst();

                                    Log.e("DFDFSD2","db = "+c10.getString(c10.getColumnIndex(db.AIB_COMPLETE_STATUS)));

                                    pd.dismiss();


                                }


                            }

                            //  Toast.makeText( AIB_Title_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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
                    }
                } )
                .requestJson();
    }


    private void aib_title_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_title_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }



}
