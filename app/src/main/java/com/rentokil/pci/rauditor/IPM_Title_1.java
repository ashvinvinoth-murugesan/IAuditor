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

public class IPM_Title_1 extends AppCompatActivity {


    Button ipmtitlesub,ipmtitlebck,ipm_title_finish_1;
    TextView ipmgetlocations;
    String lati,longi;

    private FirebaseAnalytics mFirebaseAnalytics;


    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    private android.app.AlertDialog pd;

    EditText ipm_title_et_docu,ipm_title_et_cusname,ipm_title_et_contactperson,ipm_title_et_email,ipm_title_et_phone,ipm_title_et_branch,ipm_title_et_retopci_customer,ipm_title_et_auditedby;

    String ipm_title_et_docuq,ipm_title_et_cusnameq,ipm_title_et_contactpersonq,ipm_title_et_emailq,ipm_title_et_phoneq,ipm_title_et_branchq,ipm_title_et_retopci_customerq,ipm_title_et_auditedbyq,ipm_title_txt_Displyq,ipm_title_txt_Disply2q,ipmresultsq,ipmcalendar;

    TextView ipm_title_txt_selecRespon2;

    public  static TextView ipm_title_txt_disply2;

    ConnectivityManager cManager;
    NetworkInfo nInfo;


    EditText ipmresults;
    Geocoder geocoder;
    List<Address> addressList;
    GPSTracker gps;
    private int year;
    private int monthOfYear;
    private int dayOfMonth;
    private DatePickerDialog fromDatePickerDialog;
    private DatePickerDialog toDatePickerDialog;
    Calendar newCalendar = Calendar.getInstance();
    Calendar cal = Calendar.getInstance();
    int startYear = cal.get(Calendar.YEAR);
    int startMonth = cal.get(Calendar.MONTH);
    int startDay = cal.get(Calendar.DAY_OF_MONTH);
    private SimpleDateFormat dateFormatter;
    TextView calendardemo;
    int follow = 0;
    private long mLastClickTime = 0;

    RadioGroup ipmtitleradiogroupnew1;

    RadioButton getipmtitlerg;

    String getipmrdbtn;
    static String Main_ID="0";

    // GPSTracker class    GPSTracker gps;

    Context mContext;

    LinearLayout relsnack;

    double latitude,longitude;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;


    protected static final String TAG = "LocationOnOff";
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipm__title);


        final LocationManager manager = (LocationManager) IPM_Title_1.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(IPM_Title_1.this)) {
//            Toast.makeText(IPM_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(IPM_Title_1.this)){
            Toast.makeText(IPM_Title_1.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(IPM_Title_1.this)) {
//            Log.e("keshav","Gps already enabled");
//            Toast.makeText(IPM_Title_1.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{
//            Log.e("keshav","Gps already enabled");
//            Toast.makeText(IPM_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
        }
        relsnack=(LinearLayout) findViewById(R.id.relsnack);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);



        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(IPM_Title_1.this);
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
                        Toast.makeText(IPM_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(IPM_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        get_username_data();

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

        db = new DatabaseHelper(IPM_Title_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();


        pd = new SpotsDialog(IPM_Title_1.this, R.style.Custom);

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        mLastClickTime = SystemClock.elapsedRealtime();

        calendardemo=(TextView) findViewById(R.id.date_calendar1);
        calendardemo.setText(formattedDate);

        ipmtitleradiogroupnew1=(RadioGroup) findViewById(R.id.ipmtitleradiogroupnew1);

        ipmtitleradiogroupnew1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=ipmtitleradiogroupnew1.getCheckedRadioButtonId();

                getipmtitlerg=(RadioButton)findViewById(selectedId);

                getipmrdbtn = ((RadioButton)findViewById(ipmtitleradiogroupnew1.getCheckedRadioButtonId())).getText().toString();






            }
        });


        setDateTimeField();

        calendardemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                follow = 1;
                fromDatePickerDialog.show();
            }
        });

        ipmtitlesub =(Button) findViewById(R.id.ipmtitlesub);
        ipmtitlebck=(Button) findViewById(R.id.ipmtitlebck);

        ipm_title_txt_selecRespon2=(TextView) findViewById(R.id.ipm_title_txt_selecRespon2);

        ipm_title_txt_disply2=(TextView) findViewById(R.id.ipm_title_txt_disply2);



        ipm_title_et_docu=(EditText) findViewById(R.id.ipm_title_et_docu);
        ipm_title_et_cusname=(EditText) findViewById(R.id.ipm_title_et_cusname);
        ipm_title_et_contactperson=(EditText) findViewById(R.id.ipm_title_et_contactperson);
        ipm_title_et_email=(EditText) findViewById(R.id.ipm_title_et_email);
        ipm_title_et_phone=(EditText) findViewById(R.id.ipm_title_et_phone);
        ipm_title_et_branch=(EditText) findViewById(R.id.ipm_title_et_branch);
        ipm_title_et_retopci_customer=(EditText) findViewById(R.id.ipm_title_et_retopci_customer);
        ipm_title_et_auditedby=(EditText) findViewById(R.id.ipm_title_et_auditedby);
        ipm_title_finish_1=(Button) findViewById(R.id.ipm_title_finish_1);

        ipm_title_finish_1.setVisibility(View.GONE);

        ipm_title_finish_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();

                ipm_title_button_next();

                Intent i = new Intent(IPM_Title_1.this,IPM_Cus_Info_2.class);
                startActivity(i);

            }
        });




        ipm_title_txt_selecRespon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipmtitleselect(102);
            }
        });


        ipmtitlesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(IPM_Title_1.this);
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
                                Toast.makeText(IPM_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(IPM_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {

                    if (isValidMail(ipm_title_et_email.getText().toString())) {
                        if (isValidPhone(ipm_title_et_phone.getText().toString())) {

                            ipm_title_button_next();
                            post_ipm_title_js();
                        } else {

                            Toast.makeText( getApplicationContext(),"Invalid Mobile Number",Toast.LENGTH_SHORT ).show();

                        }


                    }
                    else {
                        Toast.makeText( getApplicationContext(),"Invalid Email id",Toast.LENGTH_SHORT ).show();

                    }


                } else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }
            }
        });

        ipmtitlebck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_title_button_back();
                Intent i = new Intent(IPM_Title_1.this,Category_Type_Activity.class);
                startActivity(i);
            }
        });

        ipmresults = (EditText) findViewById(R.id.ipm_title_et_address);
        geocoder = new Geocoder(this, Locale.getDefault());



        ipmgetlocations = (TextView) findViewById(R.id.ipmtitlegetlocation);

        mContext = this;


        ipmgetlocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED

                            && ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(IPM_Title_1.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                    } else {

                        gps = new GPSTracker(mContext, IPM_Title_1.this);

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

                        ipmresults.setText(fullAddress);
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
            ipm_title_et_email.setError("Not Valid Email");

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
                ipm_title_et_phone.setError("Invalid Mobile No");
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
            googleApiClient = new GoogleApiClient.Builder(IPM_Title_1.this)
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
                                status.startResolutionForResult(IPM_Title_1.this, REQUEST_LOCATION);

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






    public  boolean validation() {


            ipm_title_et_docuq= ipm_title_et_docu.getText().toString();
            ipm_title_et_cusnameq= ipm_title_et_cusname.getText().toString();
            ipm_title_et_contactpersonq= ipm_title_et_contactperson.getText().toString();
            ipm_title_et_emailq= ipm_title_et_email.getText().toString();
            ipm_title_et_phoneq= ipm_title_et_phone.getText().toString();
            ipm_title_et_branchq= ipm_title_et_branch.getText().toString();
            ipm_title_et_retopci_customerq= ipm_title_et_retopci_customer.getText().toString();
            ipm_title_et_auditedbyq= ipm_title_et_auditedby.getText().toString();

            ipm_title_txt_Disply2q = ipm_title_txt_disply2.getText().toString();
            ipmresultsq = ipmresults.getText().toString();
            ipmcalendar=calendardemo.getText().toString();

            if(TextUtils.isEmpty(ipm_title_et_docuq ) || TextUtils.isEmpty(ipm_title_et_cusnameq) ||TextUtils.isEmpty(ipm_title_et_contactpersonq)
                    ||TextUtils.isEmpty(ipm_title_et_emailq)|| TextUtils.isEmpty(ipm_title_et_phoneq)|| TextUtils.isEmpty(ipm_title_et_branchq)
                    || TextUtils.isEmpty(ipm_title_et_retopci_customerq) || TextUtils.isEmpty(ipm_title_et_auditedbyq)|| TextUtils.isEmpty(ipm_title_txt_Disply2q)|| TextUtils.isEmpty(ipmresultsq)|| TextUtils.isEmpty(ipmcalendar) ) {

                if(TextUtils.isEmpty(ipm_title_et_docuq )){
                    ipm_title_et_docu.setError("Required");
                }

                if(TextUtils.isEmpty(ipm_title_et_cusnameq)) {
                    ipm_title_et_cusname.setError("Required");


                }
                if(TextUtils.isEmpty(ipm_title_et_contactpersonq)) {
                    ipm_title_et_contactperson.setError("Required");

                }
                if(TextUtils.isEmpty(ipm_title_et_emailq)) {
                    ipm_title_et_email.setError("Required");

                }
                if(TextUtils.isEmpty(ipm_title_et_phoneq)) {
                    ipm_title_et_phone.setError("Required");

                }
                if(TextUtils.isEmpty(ipm_title_et_branchq)) {
                    ipm_title_et_branch.setError("Required");

                }
                if(TextUtils.isEmpty(ipm_title_et_retopci_customerq)) {
                    ipm_title_et_retopci_customer.setError("Required");

                }
                if(TextUtils.isEmpty(ipm_title_et_auditedbyq)) {
                    ipm_title_et_auditedby.setError("Required");

                }
                if(TextUtils.isEmpty(ipm_title_txt_Disply2q)) {
                    ipm_title_txt_disply2.setError("Required");

                }
                if(TextUtils.isEmpty(ipmresultsq)) {
                    ipmresults.setError("Required");

                }
                if(TextUtils.isEmpty(ipmcalendar)) {
                    calendardemo.setError("Required");

                }



                return false;
            }



            if(ipm_title_et_docuq.length()==0||ipm_title_et_cusnameq.length()==0||ipm_title_et_contactpersonq.length()==0
                    ||ipm_title_et_emailq.length()==0||ipm_title_et_branchq.length()==0||
                    ipm_title_et_retopci_customerq.length()==0||getipmrdbtn.length()==0||ipmresultsq.length()==0||
                    ipm_title_txt_Disply2q.length()==0
                    ||ipmcalendar.length()==0){
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


    public void ipmtitleselect(int status) {

        Bundle bundle = new Bundle();
       if(status==102){

            bundle.putString("status", "102");
        }


        ButtonAdapter btnwork = new ButtonAdapter();
        btnwork.show(getSupportFragmentManager(),"Example");
        btnwork.setArguments(bundle);
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
                    gps = new GPSTracker(mContext, IPM_Title_1.this);

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

    private void post_ipm_title_js() {

        pd.show();

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("ipm_title_et_docui",ipm_title_et_docuq);
        params.put("user_mail",Category_Type_Activity.User_Login_Mail);
        params.put("ipm_title_et_cusnamei",ipm_title_et_cusnameq);
        params.put("ipm_title_et_contactpersoni",ipm_title_et_contactpersonq);
        params.put("ipm_title_et_emaili",ipm_title_et_emailq);
        params.put("ipm_title_et_phonei",ipm_title_et_phoneq);
        params.put("ipm_title_et_branchi",ipm_title_et_branchq);
        params.put("ipm_title_et_retopci_customeri",ipm_title_et_retopci_customerq);
        params.put("ipm_title_et_auditedbyi",ipm_title_et_auditedbyq);
        params.put("ipm_title_txt_Disply2i",ipm_title_txt_Disply2q);
        params.put("addressi",ipmresultsq);
        params.put("calendari",ipmcalendar);
        params.put("ver_no",versionName);
        params.put("getradio1",getipmrdbtn);
        params.put("update_status",Update_Status);
        params.put("main_id",Main_ID);


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/IPM/in_ipm_titile_1.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();
                            Intent i = new Intent(IPM_Title_1.this,IPM_Cus_Info_2.class);
                            startActivity(i);
                            JSONObject jsonObject;
                            jsonObject = new JSONObject(response);
                            Main_ID=jsonObject.getString("id");
                            Log.e("FFFFF Main_ID",""+Main_ID);

                            cv3.put(db.IPM_COMPLETE_STATUS, "1");
                            sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv3);

                            //   Toast.makeText( IPM_Title_1.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        //     Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




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
                                    ipm_title_et_auditedby.setText(getusername);
                                    ipm_title_et_branch.setText(getbranch);

                                    ipm_title_et_auditedby.setEnabled(false);
                                    ipm_title_et_branch.setEnabled(false);


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



    private void getData(String key_id){



        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            IPM_Title_1.Main_ID=key_id;
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/IPM/get_ipm_title_1.php?user_mail=" + Category_Type_Activity.User_Login_Mail +"&main_id="+Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");

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
                                String gettype = c.getString( "typ_faci" );
                                String get_ipm_status = c.getString( "status" );
                                String get_pci_pest = c.getString( "pci_pest" );

                                String get_pci_cus = c.getString( "pci_cus" );


                                if(get_ipm_status.equalsIgnoreCase("Pending")){

                                    sd.delete(db.IPM_COMPLETE_STATUS_TABLE,null,null);


                                    ipm_title_et_cusname.setText(customer_name);
                                    ipm_title_et_contactperson.setText(contactperson);
                                    ipm_title_et_email.setText(getemail);
                                    ipm_title_et_docu.setText(getreport);
                                    ipmresults.setText(getaddress);
                                    ipm_title_et_phone.setText(getphone);
                                    calendardemo.setText(getdates);
                                    ipm_title_txt_disply2.setText(gettype);
                                    ipm_title_et_retopci_customer.setText(get_pci_cus);

                                    Cursor c5;
                                    c5 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
                                    c5.moveToFirst();

                                    Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME)));

                                    String getusername = c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME));
                                    String getbranch = c5.getString(c5.getColumnIndex(db.REP_BRANCH));

                                    ipm_title_et_auditedby.setText(getusername);
                                    ipm_title_et_branch.setText(getbranch);


                                    if(get_pci_pest.equalsIgnoreCase("MONTHLY")){
                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pci_pest.equalsIgnoreCase("Bi-Monthly")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("Six-Monthly")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(2)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("Yearly")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(3)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("As per Requirement")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(4)).setChecked(true);

                                    }



                                    Update_Status="1";

                                    cv3.put(db.IPM_COMPLETE_STATUS, "1");
                                    sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv3);
                                    Cursor c10;
                                    c10 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                    c10.moveToFirst();

                                    Log.e("DFDFSD","db = "+c10.getString(c10.getColumnIndex(db.IPM_COMPLETE_STATUS)));





                                }
                                else if(get_ipm_status.equalsIgnoreCase("Completed")) {



                                    pd.show();
                                    sd.delete(db.IPM_COMPLETE_STATUS_TABLE,null,null);


                                    ipmtitlebck.setVisibility(View.GONE);
                                    ipmtitlesub.setVisibility(View.GONE);
                                    ipm_title_finish_1.setVisibility(View.VISIBLE);


                                    ipm_title_et_cusname.setText(customer_name);
                                    ipm_title_et_contactperson.setText(contactperson);
                                    ipm_title_et_email.setText(getemail);
                                    ipm_title_et_docu.setText(getreport);
                                    ipmresults.setText(getaddress);
                                    ipm_title_et_phone.setText(getphone);
                                    calendardemo.setText(getdates);
                                    ipm_title_txt_disply2.setText(gettype);
                                    ipm_title_et_retopci_customer.setText(get_pci_cus);

                                    ipm_title_et_cusname.setEnabled(false);
                                    ipm_title_et_contactperson.setEnabled(false);
                                    ipm_title_et_email.setEnabled(false);
                                    ipm_title_et_docu.setEnabled(false);
                                    ipmresults.setEnabled(false);
                                    ipm_title_et_phone.setEnabled(false);
                                    calendardemo.setEnabled(false);
                                    calendardemo.setEnabled(false);
                                    ipm_title_et_retopci_customer.setEnabled(false);
                                    ipm_title_txt_selecRespon2.setEnabled(false);



                                    ipmtitleradiogroupnew1.getChildAt(0).setEnabled(false);
                                    ipmtitleradiogroupnew1.getChildAt(1).setEnabled(false);
                                    ipmtitleradiogroupnew1.getChildAt(2).setEnabled(false);
                                    ipmtitleradiogroupnew1.getChildAt(3).setEnabled(false);
                                    ipmtitleradiogroupnew1.getChildAt(4).setEnabled(false);



                                    Cursor c5;
                                    c5 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
                                    c5.moveToFirst();

                                    Log.e("DFDF","update value = "+c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME)));

                                    String getusername = c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME));
                                    String getbranch = c5.getString(c5.getColumnIndex(db.REP_BRANCH));

                                    ipm_title_et_auditedby.setText(getusername);
                                    ipm_title_et_branch.setText(getbranch);


                                    if(get_pci_pest.equalsIgnoreCase("MONTHLY")){
                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pci_pest.equalsIgnoreCase("Bi-Monthly")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("Six-Monthly")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(2)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("Yearly")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(3)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("As per Requirement")){

                                        ((RadioButton) ipmtitleradiogroupnew1.getChildAt(4)).setChecked(true);

                                    }





                                    cv3.put(db.IPM_COMPLETE_STATUS, "2");
                                    sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv3);
                                    Cursor c10;
                                    c10 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                    c10.moveToFirst();

                                    Log.e("DFDFSD","db = "+c10.getString(c10.getColumnIndex(db.IPM_COMPLETE_STATUS)));
                                    Update_Status="2";

                                    pd.dismiss();
                                }
                                }




                            //     Toast.makeText( IPM_Title_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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


    private void ipm_title_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void ipm_title_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "ipm_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "ipm_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

}
