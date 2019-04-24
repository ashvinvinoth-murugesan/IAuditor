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

public class QSR_Title_1 extends AppCompatActivity {

    Button qsrtitlesub,qsrtitlebck;
    TextView qsrgetlocations;
    String lati,longi;

    private android.app.AlertDialog pd;

    RadioGroup qsrtitleradiogroupnew12;

    RadioButton getipmtitlerg;
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    String getipmrdbtn;

    EditText qsrresults;
    Geocoder geocoder;
    List<Address> addressList;
    GPSTracker gps;

    EditText ipm_title_et_docu,ipm_title_et_cusname,ipm_title_et_contactperson,ipm_title_et_email,ipm_title_et_phone,ipm_title_et_branch,ipm_title_et_retopci_customer,ipm_title_et_auditedby;

    String ipm_title_et_docuq,ipm_title_et_cusnameq,ipm_title_et_contactpersonq,ipm_title_et_emailq,ipm_title_et_phoneq,ipm_title_et_branchq,ipm_title_et_retopci_customerq,ipm_title_et_auditedbyq,ipm_title_txt_Displyq,ipm_title_txt_Disply2q,ipmresultsq,ipmcalendar;

    TextView ipm_title_txt_selecRespon2;

    public  static TextView qsr_title_txt_disply2;

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

    static String Main_ID="0";

Button qsrtitlesub1;


    // GPSTracker class    GPSTracker gps;

    Context mContext;

    double latitude,longitude;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;
    String myJSON;
    JSONArray json_arr_cus_result;
    public String Update_Status="0";

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;

    private FirebaseAnalytics mFirebaseAnalytics;

    protected static final String TAG = "LocationOnOff";
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;


    LinearLayout relsnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qsr_title);

        this.setFinishOnTouchOutside(true);

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) QSR_Title_1.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(QSR_Title_1.this)) {
//            Toast.makeText(QSR_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(QSR_Title_1.this)){
            Toast.makeText(QSR_Title_1.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(QSR_Title_1.this)) {
            Log.e("keshav","Gps already enabled");
            Toast.makeText(QSR_Title_1.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{
            Log.e("keshav","Gps already enabled");
//            Toast.makeText(QSR_Title_1.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
        }

        Log.e("BBBN","main id = "+Main_ID);
        if (!Main_ID.equals("0")) {
            // String keyid=null;
            getData(Main_ID);
        }


        db = new DatabaseHelper(QSR_Title_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();





        pd = new SpotsDialog(QSR_Title_1.this, R.style.Custom);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(QSR_Title_1.this);
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
                        Toast.makeText(QSR_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(QSR_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        get_username_data();

        relsnack=(LinearLayout) findViewById(R.id.relsnack);
        qsrtitleradiogroupnew12=(RadioGroup) findViewById(R.id.qsrtitleradiogroupnew1);

        qsrtitleradiogroupnew12.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                int selectedId=qsrtitleradiogroupnew12.getCheckedRadioButtonId();

                getipmtitlerg=(RadioButton)findViewById(selectedId);

                getipmrdbtn = ((RadioButton)findViewById(qsrtitleradiogroupnew12.getCheckedRadioButtonId())).getText().toString();



            }
        });

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){
            getData(id);
        }


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

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



        ipm_title_txt_selecRespon2=(TextView) findViewById(R.id.ipm_title_txt_selecRespon2);

        qsr_title_txt_disply2=(TextView) findViewById(R.id.ipm_title_txt_disply2);


        ipm_title_et_docu=(EditText) findViewById(R.id.ipm_title_et_docu);
        ipm_title_et_cusname=(EditText) findViewById(R.id.ipm_title_et_cusname);
        ipm_title_et_contactperson=(EditText) findViewById(R.id.ipm_title_et_contactperson);
        ipm_title_et_email=(EditText) findViewById(R.id.ipm_title_et_email);
        ipm_title_et_phone=(EditText) findViewById(R.id.ipm_title_et_phone);
        ipm_title_et_branch=(EditText) findViewById(R.id.ipm_title_et_branch);
        ipm_title_et_retopci_customer=(EditText) findViewById(R.id.ipm_title_et_retopci_customer);
        ipm_title_et_auditedby=(EditText) findViewById(R.id.ipm_title_et_auditedby);





        ipm_title_txt_selecRespon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipmtitleselect(113);
            }
        });

        qsrtitlesub =(Button) findViewById(R.id.qsrtitlesub);
        qsrtitlebck=(Button) findViewById(R.id.qsrtitlebck);
        qsrtitlesub1=(Button) findViewById(R.id.qsrtitlesub1);

        qsrtitlesub1.setVisibility(View.GONE);


        Cursor c101;
        c101 = sd.rawQuery("Select * from " + db.CHECK_STATUS_TABLE, null);
        c101.moveToFirst();

        Log.e("UUUUUUU","status ="+c101.getString(c101.getColumnIndex(db.STATUS)));

        if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("101")){
            qsrtitlesub.setVisibility(View.GONE);
            qsrtitlebck.setVisibility(View.GONE);
            qsrtitlesub1.setVisibility(View.VISIBLE);

        }
        else  if(c101.getString(c101.getColumnIndex(db.STATUS)).equalsIgnoreCase("100")){

            qsrtitlesub.setVisibility(View.VISIBLE);
            qsrtitlebck.setVisibility(View.VISIBLE);
            qsrtitlesub1.setVisibility(View.GONE);

        }



        qsrtitlesub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();

                Intent i = new Intent(QSR_Title_1.this,QSR_Cus_Info_2.class);
                startActivity(i);

            }
        });

        qsrtitlesub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(QSR_Title_1.this);
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
                                Toast.makeText(QSR_Title_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(QSR_Title_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




//                Log.e( "HHHH" ,""+validation());
                if (validation()) {

                    if (isValidMail(ipm_title_et_email.getText().toString())) {
                        if (isValidPhone(ipm_title_et_phone.getText().toString())) {

                            qsr_title_button_next();
                            post_qsr_info_js();
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

        qsrtitlebck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                qsr_title_button_back();
                Intent i = new Intent(QSR_Title_1.this,Category_Type_Activity.class);
                startActivity(i);
            }
        });

        qsrresults = (EditText) findViewById(R.id.ipm_title_et_address);
        geocoder = new Geocoder(this, Locale.getDefault());



        qsrgetlocations = (TextView) findViewById(R.id.ipmtitlegetlocation);

        mContext = this;


        qsrgetlocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED

                            && ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(QSR_Title_1.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                    } else {
                    /*    Toast.makeText(mContext, "You need have granted permission",

                                Toast.LENGTH_SHORT).show();*/
                        gps = new GPSTracker(mContext, QSR_Title_1.this);

                        // Check if GPS enabled

                        if (gps.canGetLocation()) {

                            latitude = gps.getLatitude();
                            longitude = gps.getLongitude();

                            // \n is for new line

                        /*    Toast.makeText(getApplicationContext(),
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

                        qsrresults.setText(fullAddress);
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

                                Log.e("UUUUUUU","nameeeee="+c5.getString(c5.getColumnIndex(db.AUDIT_BY_NAME)));

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
            googleApiClient = new GoogleApiClient.Builder(QSR_Title_1.this)
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
                                status.startResolutionForResult(QSR_Title_1.this, REQUEST_LOCATION);

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





    public boolean validation(){



        ipm_title_et_docuq= ipm_title_et_docu.getText().toString();
        ipm_title_et_cusnameq= ipm_title_et_cusname.getText().toString();
        ipm_title_et_contactpersonq= ipm_title_et_contactperson.getText().toString();
        ipm_title_et_emailq= ipm_title_et_email.getText().toString();
        ipm_title_et_phoneq= ipm_title_et_phone.getText().toString();
        ipm_title_et_branchq= ipm_title_et_branch.getText().toString();
        ipm_title_et_retopci_customerq= ipm_title_et_retopci_customer.getText().toString();
        ipm_title_et_auditedbyq= ipm_title_et_auditedby.getText().toString();
        ipm_title_txt_Disply2q = qsr_title_txt_disply2.getText().toString();
        ipmresultsq = qsrresults.getText().toString();
        ipmcalendar=calendardemo.getText().toString();

        if(TextUtils.isEmpty(ipm_title_et_docuq ) || TextUtils.isEmpty(ipm_title_et_cusnameq) ||TextUtils.isEmpty(ipm_title_et_contactpersonq)
                ||TextUtils.isEmpty(ipm_title_et_emailq)|| TextUtils.isEmpty(ipm_title_et_phoneq)|| TextUtils.isEmpty(ipm_title_et_branchq)
                || TextUtils.isEmpty(ipm_title_et_retopci_customerq)|| TextUtils.isEmpty(ipm_title_et_auditedbyq)
                || TextUtils.isEmpty(ipm_title_txt_Disply2q)|| TextUtils.isEmpty(ipmresultsq)


                ) {

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
                qsr_title_txt_disply2.setError("Required");

            }
            if(TextUtils.isEmpty(ipmresultsq)) {
                qsrresults.setError("Required");

            }



            return false;
        }



        if(ipm_title_et_docuq.length()==0||ipm_title_et_cusnameq.length()==0||ipm_title_et_contactpersonq.length()==0||
                ipm_title_et_emailq.length()==0||ipm_title_et_phoneq.length()==0||ipm_title_et_retopci_customerq.length()==0||
                ipm_title_et_auditedbyq.length()==0||ipm_title_txt_Disply2q.length()==0||
                ipmresultsq.length()==0||ipmcalendar.length()==0  )
        {
            return false;
        }else {
            return true;
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

    private void qsr_title_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_sub_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void qsr_title_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "qsr_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me_bck_qsr_1");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "qsr_1_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }




    public void ipmtitleselect(int status) {

        Bundle bundle = new Bundle();
        if(status==112) {
            bundle.putString("status", "112");
        }if(status==113){

            bundle.putString("status", "113");
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
                    gps = new GPSTracker(mContext, QSR_Title_1.this);

                    // Check if GPS enabled

                    if (gps.canGetLocation()) {

                        double latitude = gps.getLatitude();
                        double longitude = gps.getLongitude();

                        // \n is for new line

                    /*    Toast.makeText(getApplicationContext(),
                                "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
                  */  } else {
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

    private void post_qsr_info_js() {

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("ipm_title_et_docui",ipm_title_et_docuq);
        params.put("ipm_title_et_cusnamei",ipm_title_et_cusnameq);
        params.put("ipm_title_et_contactpersoni",ipm_title_et_contactpersonq);
        params.put("ipm_title_et_emaili",ipm_title_et_emailq);
        params.put("ipm_title_et_phonei",ipm_title_et_phoneq);
        params.put("ipm_title_et_branchi",ipm_title_et_branchq);
        params.put("ipm_title_et_retopci_customeri",ipm_title_et_retopci_customerq);
        params.put("ipm_title_et_auditedbyi",ipm_title_et_auditedbyq);
        params.put("ipm_title_txt_Displyi",getipmrdbtn);
        params.put("ipm_title_txt_Disply2i",ipm_title_txt_Disply2q);
        params.put("addressi",ipmresultsq);
        params.put("ver_no",versionName);
        params.put("calendari",ipmcalendar);
        params.put("user_mail",Category_Type_Activity.User_Login_Mail);
        params.put("update_status",Update_Status);
        params.put("main_id",Main_ID);




        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/QSR/in_qsr_title_1.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("DCDC"," qsr res = "+response);

                            JSONObject jsonObject;
                            jsonObject = new JSONObject(response);
                            Main_ID=jsonObject.getString("id");
                            Log.e("FFFFF Main_ID",""+Main_ID);
                            Intent i = new Intent(QSR_Title_1.this,QSR_Cus_Info_2.class);
                            startActivity(i);

                            cv1.put(db.QSR_COMPLETE_STATUS, "1");
                            sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv1);

//                            Toast.makeText( QSR_Title_1.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

Log.e("DCDC"," qsr 1 err in submit"+error.getMessage());
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }


    private void getData(String key_id){

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/QSR/get_qsr_title_1.php?user_mail=" + Category_Type_Activity.User_Login_Mail +"&main_id="+Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            sd.delete(db.QSR_COMPLETE_STATUS_TABLE,null,null);
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "user_id" );
                                String customer_name = c.getString( "cust_name" );
                                String get_pci_pest = c.getString( "pci_pest" );
                                String contactperson = c.getString( "cont_person" );
                                String getreport = c.getString( "report_no" );
                                String getaddress = c.getString( "address" );
                                String getphone = c.getString( "phone" );
                                String getemail = c.getString( "email" );
                                String getdates = c.getString( "cond_date" );
                                String gettype = c.getString( "typ_faci" );
                                String get_qsr_status = c.getString( "status" );
                                String get_pci_cus = c.getString( "pci_cus" );

                                if(get_qsr_status.equalsIgnoreCase("Pending")){

                                    Log.e("GHGH","pending");
                                    ipm_title_et_cusname.setText(customer_name);
                                    ipm_title_et_contactperson.setText(contactperson);
                                    ipm_title_et_email.setText(getemail);
                                    ipm_title_et_docu.setText(getreport);
                                    qsrresults.setText(getaddress);
                                    ipm_title_et_phone.setText(getphone);
                                    calendardemo.setText(getdates);
                                    qsr_title_txt_disply2.setText(gettype);
                                    ipm_title_et_retopci_customer.setText(get_pci_cus);

                                    Cursor c18;
                                    c18 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
                                    c18.moveToFirst();

                                    Log.e("DFDF","update value = "+c18.getString(c18.getColumnIndex(db.AUDIT_BY_NAME)));

                                    String getusername = c18.getString(c18.getColumnIndex(db.AUDIT_BY_NAME));
                                    String getbranch = c18.getString(c18.getColumnIndex(db.REP_BRANCH));

                                    ipm_title_et_auditedby.setText(getusername);
                                    ipm_title_et_branch.setText(getbranch);

                                    if(get_pci_pest.equalsIgnoreCase("MONTHLY")){
                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pci_pest.equalsIgnoreCase("Bi-Monthly")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("Six-Monthly")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(2)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("Yearly")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(3)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("As per Requirement")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(4)).setChecked(true);

                                    }

                                    Update_Status="1";


//
//                                    Update_Status="1";
//
                                    cv1.put(db.QSR_COMPLETE_STATUS, "1");
                                    sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv1);
                                    Cursor c6;
                                    c6 = sd.rawQuery("Select * from " + db.QSR_COMPLETE_STATUS_TABLE, null);
                                    c6.moveToFirst();

                                    Log.e("DFDFSDVC","db = "+c6.getString(c6.getColumnIndex(db.QSR_COMPLETE_STATUS)));

                                }
                                else if(get_qsr_status.equalsIgnoreCase("Completed")){
pd.show();


                                    sd.delete(db.QSR_COMPLETE_STATUS_TABLE,null,null);

                                    Log.e("YYYYY","PP1");



                                    Log.e("YYYYY","PP2");
                                    Log.e("GHGH","Completed");
                                    ipm_title_et_cusname.setText(customer_name);
                                    ipm_title_et_contactperson.setText(contactperson);
                                    ipm_title_et_email.setText(getemail);
                                    ipm_title_et_docu.setText(getreport);
                                    qsrresults.setText(getaddress);
                                    ipm_title_et_phone.setText(getphone);
                                    calendardemo.setText(getdates);
                                    qsr_title_txt_disply2.setText(gettype);
                                    ipm_title_et_retopci_customer.setText(get_pci_cus);

                                    Log.e("YYYYY","PP3");
                                    ipm_title_et_cusname.setEnabled(false);
                                    ipm_title_et_contactperson.setEnabled(false);
                                    ipm_title_et_email.setEnabled(false);
                                    ipm_title_et_docu.setEnabled(false);
                                    qsrresults.setEnabled(false);
                                    ipm_title_et_phone.setEnabled(false);
                                    calendardemo.setEnabled(false);
                                    ipm_title_et_retopci_customer.setEnabled(false);
                                    ipm_title_txt_selecRespon2.setEnabled(false);
                                    Log.e("YYYYY","PP4");

                                    qsrtitleradiogroupnew12.getChildAt(0).setEnabled(false);
                                    qsrtitleradiogroupnew12.getChildAt(1).setEnabled(false);
                                    qsrtitleradiogroupnew12.getChildAt(2).setEnabled(false);
                                    qsrtitleradiogroupnew12.getChildAt(3).setEnabled(false);
                                    qsrtitleradiogroupnew12.getChildAt(4).setEnabled(false);
                                    Log.e("YYYYY","PP5");
                                    Cursor c19;
                                    c19 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
                                    c19.moveToFirst();

                                    Log.e("YYYYY","PP6");

                                    Log.e("DFDFCX","update value = "+c19.getString(c19.getColumnIndex(db.AUDIT_BY_NAME)));

                                    String getusername = c19.getString(c19.getColumnIndex(db.AUDIT_BY_NAME));
                                    String getbranch = c19.getString(c19.getColumnIndex(db.REP_BRANCH));

                                    Log.e("YYYYY","PP7");

                                    ipm_title_et_auditedby.setText(getusername);
                                    ipm_title_et_branch.setText(getbranch);

                                    Log.e("YYYYY","PP8");
                                    if(get_pci_pest.equalsIgnoreCase("MONTHLY")){
                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(0)).setChecked(true);


                                    }
                                    else if(get_pci_pest.equalsIgnoreCase("Bi-Monthly")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(1)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("Six-Monthly")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(2)).setChecked(true);

                                    }


                                    else if(get_pci_pest.equalsIgnoreCase("Yearly")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(3)).setChecked(true);

                                    }

                                    else if(get_pci_pest.equalsIgnoreCase("As per Requirement")){

                                        ((RadioButton) qsrtitleradiogroupnew12.getChildAt(4)).setChecked(true);

                                    }



                                    Log.e("YYYYY","PP10");

                                    Update_Status="2";



                                    cv3.put(db.QSR_COMPLETE_STATUS, "2");
                                    sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv3);
                                    Cursor c10;
                                    c10 = sd.rawQuery("Select * from " + db.QSR_COMPLETE_STATUS_TABLE, null);
                                    c10.moveToFirst();


                                    Log.e("DFDFSD","db = "+c10.getString(c10.getColumnIndex(db.QSR_COMPLETE_STATUS)));

                                    pd.dismiss();

                                }

                          }

                           // Toast.makeText( QSR_Title_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("DCDC","err qsr 1"+error.getMessage());
                        //mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }


//    public void getData() {
//
//        class GetDataJSON extends AsyncTask<String, Void, String> {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                loading = new ProgressDialog(QSR_Title_1.this, R.style.MyDialog );
//                loading.show();
//                loading.setTitle( "Please Wait" );
//                loading.setMessage( "Data Loading..." );
//                //   loading.setProgressDrawable( getActivity().getResources().getDrawable( R.drawable.add_white_icon ) );
//                super.onPreExecute();
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//                //  user_profile_date();
//                Log.e("DDDDDD",""+ Category_Type_Activity.User_Login_Mail);
//
//                DefaultHttpClient httpclient = new DefaultHttpClient( new BasicHttpParams() );
//                HttpPost httppost = new HttpPost( "http://rauditor.riflows.com/rauditor/Android/QSR/get_qsr_title_1.php?user_mail=" + Category_Type_Activity.User_Login_Mail +"&main_id="+Main_ID );
//
//                // Depends on your web service
//                httppost.setHeader( "Content-type", "application/json" );
//
//                InputStream inputStream = null;
//                String result = null;
//                try {
//                    HttpResponse response = httpclient.execute( httppost );
//                    HttpEntity entity = response.getEntity();
//                    inputStream = entity.getContent();
//                    // json is UTF-8 by default
//                    BufferedReader reader = new BufferedReader( new InputStreamReader( inputStream, "UTF-8" ), 8 );
//                    StringBuilder sb = new StringBuilder();
//
//                    String line = null;
//                    while ((line = reader.readLine()) != null) {
//                        sb.append( line + "\n" );
//                    }
//                    result = sb.toString();
//                } catch (Exception e) {
//                    // Oops
//                } finally {
//                    try {
//                        if (inputStream != null) inputStream.close();
//                    } catch (Exception squish) {
//                    }
//                }
//                return result;
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//
//                myJSON = result;
//                showList();
//                loading.dismiss();
//
//            }
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute();
//    }
//    protected void showList() {
//        List_Item_Methodes wp;
//        try {
//            Log.e( "JJJJJJ","Profile_Fragment\t"+myJSON );
//            JSONObject jsonObj = new JSONObject( myJSON );
//            json_arr_cus_result = jsonObj.getJSONArray( "result" );
//
//            for (int i = 0; i < json_arr_cus_result.length(); i++) {
//
//
//                JSONObject c = json_arr_cus_result.getJSONObject(i);
//                String id = c.getString( "user_id" );
//                String customer_name = c.getString( "cust_name" );
//                String contactperson = c.getString( "cont_person" );
//                String getreport = c.getString( "report_no" );
//                String getaddress = c.getString( "address" );
//                String getphone = c.getString( "phone" );
//                String getemail = c.getString( "email" );
//                String getdates = c.getString( "cond_date" );
//                String gettype = c.getString( "typ_faci" );
//
//
//                ipm_title_et_cusname.setText(customer_name);
//                ipm_title_et_contactperson.setText(contactperson);
//                ipm_title_et_email.setText(getemail);
//                ipm_title_et_docu.setText(getreport);
//                qsrresults.setText(getaddress);
//                ipm_title_et_phone.setText(getphone);
//                calendardemo.setText(getdates);
//                qsr_title_txt_disply2.setText(gettype);
//
//                Update_Status="1";
//
//            }
//            //    Log.e("InProcess ","bb\t"+arraylist.size());
//
//        } catch (JSONException e) {
//            Log.e( "UUUU", "error iii\t" + e.getMessage() );
//            e.printStackTrace();
//        } catch (NullPointerException e1) {
//            Log.e( "UUUU", "error rrr\t" + e1.getMessage() );
//            e1.printStackTrace();
//        }
//
//    }
//




}
