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
import android.graphics.Typeface;
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

public class PMCA_Title_1 extends AppCompatActivity {

    Button pesttitlesub,pesttitlebck;

   EditText pesttitlauditno,pesttitlcusname,pesttitlcntdetails,pesttitlphne,pesttitlemail,pesttitlbranch,pesttitlauditedby;
    Button getLL;
    TextView getlocations;
    String lati,longi;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    String pesttitlauditnos,pesttitlcusnames,pesttitlcntdetailss,pesttitlphnes,pesttitlemails,pesttitlbranchs,pesttitlauditedbys,pesttitleadress,pesttitleconducton;

    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    EditText results;
    Geocoder geocoder;
    List<Address> addressList;
    GPSTracker gps;
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

    private FirebaseAnalytics mFirebaseAnalytics;

    static String Main_ID="0";

    TextView calendardemo;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    // GPSTracker class    GPSTracker gps;

    private android.app.AlertDialog pd;

    Context mContext;
    LinearLayout relsnack;

    double latitude,longitude;
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;

    protected static final String TAG = "LocationOnOff";
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    Button pestman_comp_sub;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pestman_title);


        this.setFinishOnTouchOutside(true);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) PMCA_Title_1.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(PMCA_Title_1.this)) {
            //    Toast.makeText(PMCA_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(PMCA_Title_1.this)){
            Toast.makeText(PMCA_Title_1.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(PMCA_Title_1.this)) {
            Log.e("keshav","Gps already enabled");
            Toast.makeText(PMCA_Title_1.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{
            Log.e("keshav","Gps already enabled");
            //  Toast.makeText(PMCA_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
        }

        if (!Main_ID.equals("0")) {
            // String keyid=null;

            getData(Main_ID);
        }

        db = new DatabaseHelper(PMCA_Title_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        pd = new SpotsDialog(PMCA_Title_1.this, R.style.Custom);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {

        } else {
            final Dialog dialog = new Dialog(PMCA_Title_1.this);
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
                        Toast.makeText(PMCA_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(PMCA_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        relsnack=(LinearLayout) findViewById(R.id.relsnack);
        pesttitlesub=(Button) findViewById(R.id.pestmantitlesub);

        pesttitlebck=(Button) findViewById(R.id.pestmantitlebck);
        pesttitlauditno=(EditText) findViewById(R.id.pesttitlauditno);
        pesttitlcusname=(EditText) findViewById(R.id.pesttitlcusname);
        pesttitlcntdetails=(EditText) findViewById(R.id.pesttitlcntdetails);
        pesttitlphne=(EditText) findViewById(R.id.pesttitlphne);
        pesttitlemail=(EditText) findViewById(R.id.pesttitlemail);
        pesttitlbranch=(EditText) findViewById(R.id.pesttitlbranch);
        pesttitlauditedby=(EditText) findViewById(R.id.pesttitlauditedby);
        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        pestman_comp_sub=(Button) findViewById(R.id.pestman_comp_sub);

        pestman_comp_sub.setVisibility(View.GONE);

        pestman_comp_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                Intent i = new Intent(PMCA_Title_1.this,PMCA_Cus_Info_2.class);
                startActivity(i);

            }
        });

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        mLastClickTime = SystemClock.elapsedRealtime();

        calendardemo=(TextView) findViewById(R.id.date_calendar1);

        calendardemo.setText(formattedDate);

        setDateTimeField();

        calendardemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                follow = 1;
                fromDatePickerDialog.show();
            }
        });



        pesttitlesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {

                } else {
                    final Dialog dialog = new Dialog(PMCA_Title_1.this);
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
                                Toast.makeText(PMCA_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(PMCA_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }





                if (validation()) {
                    if (isValidMail(pesttitlemail.getText().toString())) {

                        if (isValidPhone(pesttitlphne.getText().toString())) {

                            pmca_title_button_next();
                            post_pesttitle_js();

                        }else
                        {
                            Toast.makeText( getApplicationContext(),"Invalid Mobile Number",Toast.LENGTH_SHORT ).show();
                        }
                    }else{

                        Toast.makeText( getApplicationContext(),"Invalid Email id",Toast.LENGTH_SHORT ).show();
                    }
                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
                }
            }
        });
        get_username_data();
        pesttitlebck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pmca_title_button_back();

                Intent i = new Intent(PMCA_Title_1.this,Category_Type_Activity.class);
                startActivity(i);



            }
        });

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            getData(id);
        }

        results = (EditText) findViewById(R.id.pestmantitleresult);
        geocoder = new Geocoder(this, Locale.getDefault());



        getlocations = (TextView) findViewById(R.id.pestmantitlegetlocation);

        mContext = this;


        getlocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED

                            && ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(PMCA_Title_1.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                    } else {
                  /*      Toast.makeText(mContext, "You need have granted permission",

                                Toast.LENGTH_SHORT).show();*/
                        gps = new GPSTracker(mContext, PMCA_Title_1.this);

                        // Check if GPS enabled

                        if (gps.canGetLocation()) {

                            latitude = gps.getLatitude();
                            longitude = gps.getLongitude();

                            // \n is for new line

                         /*   Toast.makeText(getApplicationContext(),
                                    "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                       */     lati = Double.toString(latitude);
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
                        Toast.makeText(getApplicationContext(),"Press Again To Get Address",Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


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
            pesttitlemail.setError("Not Valid Email");

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

                pesttitlphne.setError("Invalid Mobile No");
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
            googleApiClient = new GoogleApiClient.Builder(PMCA_Title_1.this)
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
                                status.startResolutionForResult(PMCA_Title_1.this, REQUEST_LOCATION);

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

                                String getpmcausername = c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME));
                                String getpmcabranch = c5.getString(c5.getColumnIndex(db.REP_BRANCH));
                                pesttitlauditedby.setText(getpmcausername);
                                pesttitlbranch.setText(getpmcabranch);

                                pesttitlauditedby.setEnabled(false);
                                pesttitlbranch.setEnabled(false);


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
                    gps = new GPSTracker(mContext, PMCA_Title_1.this);

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line

                 /*       Toast.makeText(getApplicationContext(),
                                "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
               */     } else {
                        // Can't get location.

                        // GPS or network is not enabled.

                        // Ask user to enable GPS/network in settings.

                        gps.showSettingsAlert();
                    }

                } else {

                    // permission denied, boo! Disable the

                    // functionality that depends on this permission.
                    Toast.makeText(mContext, "You need to grant permission",

                            Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    public boolean validation(){


        pesttitleconducton=calendardemo.getText().toString();
        pesttitlauditnos = pesttitlauditno.getText().toString();
        pesttitlcusnames = pesttitlcusname.getText().toString();
        pesttitlcntdetailss = pesttitlcntdetails.getText().toString();
        pesttitlphnes = pesttitlphne.getText().toString();
        pesttitlemails = pesttitlemail.getText().toString();
        pesttitlbranchs = pesttitlbranch.getText().toString();
        pesttitlauditedbys = pesttitlauditedby.getText().toString();
        pesttitleadress=results.getText().toString();

        if(TextUtils.isEmpty(pesttitlauditnos) ||TextUtils.isEmpty(pesttitlcusnames)
                ||TextUtils.isEmpty(pesttitlcntdetailss)|| TextUtils.isEmpty(pesttitlphnes)
                || TextUtils.isEmpty(pesttitlemails) || TextUtils.isEmpty(pesttitlbranchs) || TextUtils.isEmpty(pesttitlauditedbys)  ) {



            if(TextUtils.isEmpty(pesttitlauditnos)) {
                pesttitlauditno.setError("Required");


            }
            if(TextUtils.isEmpty(pesttitlcusnames)) {
                pesttitlcusname.setError("Required");

            }
            if(TextUtils.isEmpty(pesttitlcntdetailss)) {
                pesttitlcntdetails.setError("Required");

            }
            if(TextUtils.isEmpty(pesttitlphnes)) {
                pesttitlphne.setError("Required");

            }
            if(TextUtils.isEmpty(pesttitlemails)) {
                pesttitlemail.setError("Required");

            }
            if(TextUtils.isEmpty(pesttitlbranchs)) {
                pesttitlbranch.setError("Required");

            }
            if(TextUtils.isEmpty(pesttitlauditedbys)) {
                pesttitlauditedby.setError("Required");

            }


            return false;
        }






        if(pesttitleconducton.length()==0||pesttitlauditnos.length()==0|pesttitlcntdetailss.length()==0||
                pesttitlcusnames.length()==0||pesttitlphnes.length()==0||pesttitlemails.length()==0||
                pesttitlbranchs.length()==0||pesttitlauditedbys.length()==0||pesttitleadress.length()==0){
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
                            calendardemo.setText

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
                            calendardemo.setText


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

    private void post_pesttitle_js() {

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("pesttitlauditnosi",pesttitlauditnos);
        params.put("pesttitlcusnamesi",pesttitlcusnames);
        params.put("pesttitlcntdetailssi",pesttitlcntdetailss);
        params.put("pesttitlphnesi",pesttitlphnes);
        params.put("pesttitlemailsi",pesttitlemails);
        params.put("pesttitlbranchsi",pesttitlbranchs);
        params.put("pesttitlauditedbysi",pesttitlauditedbys);
        params.put("pesttitleadressi",pesttitleadress);
        params.put("pesttitleconductoni",pesttitleconducton);
        params.put("ver_no",versionName);
        params.put("update_status",Update_Status);
        params.put("main_id",Main_ID);
        params.put("mail_id",Category_Type_Activity.User_Login_Mail);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/PMCAR/in_pmca_title_1.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(response);
                            Main_ID=jsonObject.getString("id");
                            Log.e("FFFFF Main_ID",""+Main_ID);
                            Intent i=new Intent( PMCA_Title_1.this,PMCA_Cus_Info_2.class );
                            startActivity(i);

                            cv1.put(db.PMCA_COMPLETE_STATUS, "1");
                            sd.insert(db.PMCA_COMPLETE_STATUS_TABLE, null, cv1);


                            //  Toast.makeText( PMCA_Title_1.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }

    private void pmca_title_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "pmca_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "pmca_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void pmca_title_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "pmca_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "pmca_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }


    private void getData(String key_id){

        Log.e("FFFFF","getpmca 1 "+key_id);
        if(key_id!=null){
            Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/PMCAR/get_pmca_title_1.php?user_mail=" + Category_Type_Activity.User_Login_Mail +"&main_id="+Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e( "JJJJJJ","Profile_Fragment\t"+myJSON );
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");

                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
//                                String id = c.getString( "user_id" );
                                String getreport = c.getString( "report_no" );
                                String customer_name = c.getString( "cust_name" );
                                String contactperson = c.getString( "cont_person" );
                                String getaddress = c.getString( "address" );
                                String getphone = c.getString( "phone" );
                                String getemail = c.getString( "email" );
                                String getdate = c.getString( "cond_date" );
                                String get_pmca_status = c.getString( "status" );

                                if(get_pmca_status.equalsIgnoreCase("Pending")){

                                    sd.delete(db.PMCA_COMPLETE_STATUS_TABLE,null,null);

                                    Log.e("GHGH","pending");

                                    pesttitlauditno.setText(getreport);
                                    pesttitlcusname.setText(customer_name);
                                    pesttitlcntdetails.setText(contactperson);
                                    pesttitlemail.setText(getemail);
                                    results.setText(getaddress);
                                    pesttitlphne.setText(getphone);
                                    calendardemo.setText(getdate);

                                    cv1.put(db.PMCA_COMPLETE_STATUS, "1");
                                    sd.insert(db.PMCA_COMPLETE_STATUS_TABLE, null, cv1);

                                    Update_Status="1";


                                }
                                else if(get_pmca_status.equalsIgnoreCase("Completed")){


                                    pd.show();

                                    sd.delete(db.PMCA_COMPLETE_STATUS_TABLE,null,null);

                                    Log.e("GHGH","completed");

                                    pesttitlesub.setVisibility(View.GONE);
                                    pesttitlebck.setVisibility(View.GONE);
                                    pestman_comp_sub.setVisibility(View.VISIBLE);


                                    pesttitlauditno.setText(getreport);
                                    pesttitlcusname.setText(customer_name);
                                    pesttitlcntdetails.setText(contactperson);
                                    pesttitlemail.setText(getemail);
                                    results.setText(getaddress);
                                    pesttitlphne.setText(getphone);
                                    calendardemo.setText(getdate);

                                    pesttitlauditno.setTypeface(pesttitlauditno.getTypeface(), Typeface.BOLD_ITALIC);
                                    pesttitlcusname.setTypeface(pesttitlcusname.getTypeface(), Typeface.BOLD_ITALIC);
                                    pesttitlcntdetails.setTypeface(pesttitlcntdetails.getTypeface(), Typeface.BOLD_ITALIC);
                                    pesttitlemail.setTypeface(pesttitlemail.getTypeface(), Typeface.BOLD_ITALIC);
                                    results.setTypeface(results.getTypeface(), Typeface.BOLD_ITALIC);
                                    pesttitlphne.setTypeface(pesttitlphne.getTypeface(), Typeface.BOLD_ITALIC);
                                    calendardemo.setTypeface(calendardemo.getTypeface(), Typeface.BOLD_ITALIC);

                                    pesttitlauditno.setEnabled(false);
                                    pesttitlcusname.setEnabled(false);
                                    pesttitlcntdetails.setEnabled(false);
                                    pesttitlemail.setEnabled(false);
                                    results.setEnabled(false);
                                    pesttitlphne.setEnabled(false);
                                    calendardemo.setEnabled(false);
                                    pesttitlbranch.setEnabled(false);
                                    pesttitlauditedby.setEnabled(false);

                                    Cursor c5;
                                    c5 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
                                    c5.moveToFirst();

                                    Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME)));

                                    String getusername = c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME));
                                    String getbranch = c5.getString(c5.getColumnIndex(db.REP_BRANCH));

                                    pesttitlbranch.setText(getbranch);
                                    pesttitlauditedby.setText(getusername);
                                    Update_Status="2";


                                    cv1.put(db.PMCA_COMPLETE_STATUS, "2");
                                    sd.insert(db.PMCA_COMPLETE_STATUS_TABLE, null, cv1);
                                    Cursor c6;
                                    c6 = sd.rawQuery("Select * from " + db.PMCA_COMPLETE_STATUS_TABLE, null);
                                    c6.moveToFirst();

                                    Log.e("DFDF","db = "+c6.getString(c6.getColumnIndex(db.PMCA_COMPLETE_STATUS)));
pd.dismiss();

                                }




                            }

                            //  Toast.makeText( PMCA_Title_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );

                        Log.e("GHGH","error mes = "+error.getMessage());
                    }
                } )
                .requestJson();
    }



}
