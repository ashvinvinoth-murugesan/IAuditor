package com.rentokil.pci.rauditor.MSOT_NEW;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BulletSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.VolleyError;
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
import com.rentokil.pci.rauditor.Category_Type_Activity;

import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.GPSTracker;
import com.rentokil.pci.rauditor.IPM_Cus_Pest_3;
import com.rentokil.pci.rauditor.IPM_Title_1;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import com.rentokil.pci.rauditor.R;

import dmax.dialog.SpotsDialog;

public class MSOT_Main extends AppCompatActivity {
    String db_user_name,db_user_mail,db_branch,db_country;

   public static String Main_ID ="0" ;
    Button msot1_sub,msot1_bck,msot1_sub2;

    EditText msot_main_edt1,msot_main_edt2,msot_main_edt3,msot_main_edt4,msot_main_edt7;

    String get_msot_edt1,get_msot_edt2,get_msot_edt3,get_msot_edt4,get_msot_edt5,get_msot_edt6="",get_msot_edt7="",get_current_Date;

    Boolean isInternetPresent = false;

    TextView getlocations;
    String lati,longi;
    String myJSON;
    JSONArray json_arr_cus_result;
    Geocoder geocoder;
    List<Address> addressList;
    GPSTracker gps;

    SpannableStringBuilder ssb;
    Context mContext;



    private android.app.AlertDialog pd;

    protected static final String TAG = "LocationOnOff";
    private GoogleApiClient googleApiClient;
    final static int REQUEST_LOCATION = 199;
    double latitude,longitude;

    TextView msot_main_edt5,current_Date;

    android.support.v7.widget.CardView name_layout,title_layout,card_v1,card_v2,lin_branch_co,lin_branch_manager;


    String get_branch_Co,get_branch_manager;
    ConnectivityManager cManager;
    NetworkInfo nInfo;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv2,cv3,cv4,cv5,cv_off;



    DatePickerDialog picker;
    TimePickerDialog timePickerDialog;

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

    List<String> responseList = new ArrayList<String>();
    AutoCompleteTextView msot_main_edt6;

    String post_type;

    EditText msot_main_edt8,msot_main_edt9;
    private String get_branch_type;


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msot__main);

        this.setFinishOnTouchOutside(true);

        // Todo Location Already on  ... start
        final LocationManager manager = (LocationManager) MSOT_Main.this.getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(MSOT_Main.this)) {
//            Toast.makeText(MSOT_Main.this,"Gps already enabled",Toast.LENGTH_SHORT).show();

        }
        // Todo Location Already on  ... end

        if(!hasGPSDevice(MSOT_Main.this)){
            Toast.makeText(MSOT_Main.this,"Gps not Supported",Toast.LENGTH_SHORT).show();
        }

        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER) && hasGPSDevice(MSOT_Main.this)) {
            Log.e("keshav","Gps already enabled");
            Toast.makeText(MSOT_Main.this,"Gps not enabled",Toast.LENGTH_SHORT).show();
            enableLoc();
        }else{
            Log.e("keshav","Gps already enabled");
//            Toast.makeText(MSOT_Main.this,"Gps already enabled",Toast.LENGTH_SHORT).show();
        }

        msot_main_edt6=(AutoCompleteTextView) findViewById(R.id.msot_main_edt6);



        msot_main_edt7=(EditText) findViewById(R.id.msot_main_edt7);
        pd = new SpotsDialog(MSOT_Main.this, R.style.Custom);




        msot_main_edt8=(EditText) findViewById(R.id.msot_main_edt8);
        msot_main_edt9=(EditText) findViewById(R.id.msot_main_edt9);


        name_layout=(android.support.v7.widget.CardView) findViewById(R.id.name_layout);
        title_layout=(android.support.v7.widget.CardView) findViewById(R.id.title_layout);
        card_v1=(android.support.v7.widget.CardView) findViewById(R.id.card_v1);
        card_v2=(android.support.v7.widget.CardView) findViewById(R.id.card_v2);
        lin_branch_co=(android.support.v7.widget.CardView) findViewById(R.id.lin_branch_co);
        lin_branch_manager=(android.support.v7.widget.CardView) findViewById(R.id.lin_branch_manager);



        msot_main_edt7.setFocusable(false);



        isInternetPresent = haveNetworkConnection(MSOT_Main.this);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

        msot1_bck=(Button) findViewById(R.id.msot1_bck);
        msot1_sub=(Button) findViewById(R.id.msot1_sub);
        msot1_sub2=(Button) findViewById(R.id.msot1_sub2);

        msot1_sub2.setVisibility(View.GONE);

        db = new DatabaseHelper(this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();
        cv4 = new ContentValues();
        cv5 = new ContentValues();
        cv_off = new ContentValues();


           Toast.makeText(getApplicationContext(),""+db.get_msot_main_count(sd),Toast.LENGTH_SHORT).show();


        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate = mdformat.format(calendar.getTime());



        msot_main_edt1=(EditText) findViewById(R.id.msot_main_edt1);
        msot_main_edt2=(EditText) findViewById(R.id.msot_main_edt2);
        msot_main_edt3=(EditText) findViewById(R.id.msot_main_edt3);
        msot_main_edt4=(EditText) findViewById(R.id.msot_main_edt4);
        msot_main_edt5=(TextView) findViewById(R.id.msot_main_edt5);

        msot_main_edt3.setEnabled(false);
        msot_main_edt4.setText(strDate);
        msot_main_edt5.setText(strDate);

        current_Date =(TextView) findViewById(R.id.date_calendar);

        Log.e("PPPOU","KKKK = "+Main_ID);
        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        String check=intent1.getStringExtra("check");


        if (!Main_ID.equals("0")) {
            Log.e("DDDDV","1st ="+Main_ID);
            // String keyid=null;

            lin_branch_co.setVisibility(View.VISIBLE);
            lin_branch_manager.setVisibility(View.VISIBLE);

            msot1_sub.setVisibility(View.GONE);
            msot1_sub2.setVisibility(View.VISIBLE);
            getData(Main_ID);
            get_LIST_Data(Main_ID);
        }

        else{

            if (check==null) {
                alert_instruction();
            }


        }


        Log.e("VVVVVV",""+id);

        if(id!=null){

            msot1_sub.setVisibility(View.GONE);
            msot1_sub2.setVisibility(View.VISIBLE);


            Log.e("DDDDV","2nd ="+id);

            getData(id);
            get_LIST_Data(id);
        }


        msot1_sub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c5;
                c5 = sd.rawQuery("Select * from " + db.MSOT_TYPE_TABLE, null);
                c5.moveToFirst();

                String get_branch_tech = c5.getString(c5.getColumnIndex(db.BRANCH_AND_TECH_MSOT));


                if (get_branch_tech.equalsIgnoreCase("2) Branch Section")) {

                    Intent i = new Intent(MSOT_Main.this, Page_K.class);
                    startActivity(i);
                } else if (get_branch_tech.equalsIgnoreCase("1) Technician Section")) {

                    Intent i = new Intent(MSOT_Main.this, MSOT_LIST_1.class);
                    startActivity(i);

                }
            }
        });



        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        mLastClickTime = SystemClock.elapsedRealtime();
        current_Date.setText(formattedDate);
        setDateTimeField();

        current_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(MSOT_Main.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                current_Date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                                current_Date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);


                                int month= monthOfYear+1;
                                String fm=""+month;
                                String fd=""+dayOfMonth;
                                if(month<10){
                                    fm ="0"+month;
                                }

                                if (dayOfMonth<10){
                                    fd="0"+dayOfMonth;
                                }

                                String date= ""+year+"-"+fm+"-"+fd;

                                current_Date.setText(date);
                            }
                        }, year, month, day);
                picker.show();
            }
        });



        mContext = this;

        msot_main_edt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                // Create a new instance of TimePickerDialog
                timePickerDialog = new TimePickerDialog(MSOT_Main.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        msot_main_edt5.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                timePickerDialog.setTitle("Select Time");
                timePickerDialog.show();
                msot_main_edt5.setFocusable(false);
            }
        });


        insert();






        msot1_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.show();

                if (validation()) {
                    Log.e("GGGG", "validation");
                  /*  if (isInternetPresent) {


                        //submitres();
                    } else {
                        pd.dismiss();
                        Toast.makeText(MSOT_Main.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
*/
                  insert_main_off();
                } else {

                    Log.e("GGGG", "else");
                         pd.dismiss();
                    Toast.makeText(MSOT_Main.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }



            }
        });

        msot1_bck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MSOT_Main.this, Category_Type_Activity.class);
                startActivity(i);

            }
        });


        getlocations = (TextView) findViewById(R.id.sirtitlegetLocation);

        getlocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                geocoder = new Geocoder(MSOT_Main.this, Locale.getDefault());





                try {

                    if (ContextCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_FINE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED

                            && ActivityCompat.checkSelfPermission(mContext,
                            Manifest.permission.ACCESS_COARSE_LOCATION)

                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(MSOT_Main.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

                    } else {

                        gps = new GPSTracker(mContext, MSOT_Main.this);

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

                        msot_main_edt1.setText(fullAddress);
                    } catch (Exception e) {

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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void alert(){


        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
        c5.moveToFirst();

        String get_country = c5.getString(c5.getColumnIndex(db.COUNTRY));

        if(get_country.equalsIgnoreCase("India")) {

            sd.delete(db.MSOT_TYPE_TABLE,null,null);

            withItems();
        }
    }

    private void alert_instruction(){


        final Dialog dialog = new Dialog(MSOT_Main.this);
        dialog.setContentView(R.layout.instruction_page);
        dialog.getWindow().setLayout(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

       TextView txt_msg = (TextView) dialog.findViewById(R.id.welcome_messgae);
        Button got_it = (Button) dialog.findViewById(R.id.got_it);
        Button ins_back = (Button) dialog.findViewById(R.id.ins_back);

        ins_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MSOT_Main.this,Category_Type_Activity.class);
                startActivity(i);
            }
        });

        got_it.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                  dialog.dismiss();
                alert();

            }
        });
        String tick="✓";

        String longDescription = "Select  \u2713 , P , X  or NA in response to each question.\n\n" +
                "\u2713 Safe Condition / Safe Behavior / Yes \n\n" +
                "P Partial Compliance / Partial Understanding\n\n" +
                "X  Unsafe Condition / Unsafe Behavior/ No\n\n" +
                "NA  Not Applicable / Not Inspected during MSOT\n\n\t" +
                "(i) An “info” sign may appear at some questions. Click on the sign for reference to the compliance standards/ answers/ guidelines.\n\n\t" +
                "Upon completion, please email the MSOT e-report to the Country SHE Manager/ Executive, and the relevant line managers.\n\n\t";


        String arr[] = longDescription.split("\n\t");

        int bulletGap = (int) dp(10);

        ssb  = new SpannableStringBuilder();
        for (int i = 0; i < arr.length; i++) {
            String line = arr[i];
            SpannableString ss = new SpannableString(line);
            ss.setSpan(new BulletSpan(bulletGap), 0, line.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            ssb.append(ss);

            //avoid last "\n"
            if(i+1<arr.length)
                ssb.append("\n");

        }

        txt_msg.setText(ssb);


        dialog.show();





    }

    private float dp(int dp) {
        return getResources().getDisplayMetrics().density * dp;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)

    private void withItems() {

        final String[] items = {"1) Technician Section", "2) Branch Section"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                                cv4.put(db.BRANCH_AND_TECH_MSOT, items[which]);
                                sd.insert(db.MSOT_TYPE_TABLE, null, cv4);


                        lin_branch_co.setVisibility(View.VISIBLE);
                        lin_branch_manager.setVisibility(View.VISIBLE);

                    }
                });


        TextView tv = new TextView(this);
        tv.setText("Select MSOT Section");
        tv.setPadding(40, 40, 40, 40);
        tv.setGravity(Gravity.CENTER);
        tv.setTypeface(tv.getTypeface(), Typeface.BOLD);
        tv.setTextColor(Color.parseColor("#0A7293"));
        tv.setTextSize(17);

        TextView tv1 = new TextView(this);
        tv1.setText("*Both Sections must be completed for MSOT");
        tv1.setTypeface(null, Typeface.ITALIC);
        tv1.setTextSize(12);
        tv1.setTextColor(Color.parseColor("#8C0000"));

        LinearLayout layout = new LinearLayout(this);
        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(parms);

        LinearLayout.LayoutParams tv1Params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1Params.bottomMargin = 5;
        tv1Params.leftMargin=40;
        layout.addView(tv1,tv1Params);

        builder.setView(layout);
        builder.setCustomTitle(tv);


        android.support.v7.app.AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

    }

    public boolean validation() {

        boolean check_point=false;

        try {
            get_msot_edt1=msot_main_edt1.getText().toString();
            get_msot_edt2=msot_main_edt2.getText().toString();
            get_msot_edt3=msot_main_edt3.getText().toString();

            get_msot_edt5=msot_main_edt5.getText().toString();
            get_msot_edt6=msot_main_edt6.getText().toString();
            get_msot_edt7=msot_main_edt7.getText().toString();
            get_current_Date=current_Date.getText().toString();


            Cursor c5;
            c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
            c5.moveToFirst();

            String get_country = c5.getString(c5.getColumnIndex(db.COUNTRY));

            if(get_country.equalsIgnoreCase("India")) {

                Cursor c6;
                c6 = sd.rawQuery("Select * from " + db.MSOT_TYPE_TABLE, null);
                c6.moveToFirst();

                String get_branch_type = c6.getString(c6.getColumnIndex(db.BRANCH_AND_TECH_MSOT));

                if(get_branch_type.equalsIgnoreCase("Branch Section")){


                    if (get_msot_edt1.length()!=0&&get_msot_edt2.length()!=0&&get_msot_edt3.length()!=0&&get_msot_edt5.length()!=0 &&get_current_Date.length()!=0){
                        check_point=true;
                    }else {
                        check_point=false;
                    }


                }
                else{

                    if (get_msot_edt1.length()!=0&&get_msot_edt2.length()!=0&&get_msot_edt3.length()!=0&&get_msot_edt5.length()!=0
                            &&get_msot_edt6.length()!=0&&get_msot_edt7.length()!=0&&get_current_Date.length()!=0){
                        check_point=true;
                    }else {
                        check_point=false;
                    }


                }
            }
            else {


                if (get_msot_edt1.length() != 0 && get_msot_edt2.length() != 0 && get_msot_edt3.length() != 0 && get_msot_edt5.length() != 0
                        && get_msot_edt6.length() != 0 && get_msot_edt7.length() != 0 && get_current_Date.length() != 0) {
                    check_point = true;
                } else {
                    check_point = false;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return check_point;

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
                            current_Date.setText

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
                            current_Date.setText


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

    private void insert(){


        Cursor c14;
        c14 = sd.rawQuery("Select * from " + db.BRANCH_AUDITBY_TABLE, null);
        c14.moveToFirst();


        String getusername = c14.getString(c14.getColumnIndex(db.AUDIT_BY_NAME));
        String get_job_title = c14.getString(c14.getColumnIndex(db.JOB_TITLE));
        msot_main_edt3.setText(getusername);
        msot_main_edt4.setText(get_job_title);

        Log.e("SSSKM","title = "+get_job_title);
        Log.e("SSSKM","name = "+getusername);
        name_layout.setVisibility(View.GONE);
        title_layout.setVisibility(View.GONE);
        get_tech_list();
    }

    @Override
    public void onBackPressed(){

    }


    private void submitres() {




        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

        Log.e("GGGG","sub");
        isInternetPresent = haveNetworkConnection(MSOT_Main.this);
        if (isInternetPresent) {

            get_msot_edt1=msot_main_edt1.getText().toString();
            get_msot_edt2=msot_main_edt2.getText().toString();
            get_msot_edt3=msot_main_edt3.getText().toString();

            get_msot_edt5=msot_main_edt5.getText().toString();
            get_msot_edt6=msot_main_edt6.getText().toString();
            get_msot_edt7=msot_main_edt7.getText().toString();
            get_current_Date=current_Date.getText().toString();

            get_msot_edt4=msot_main_edt4.getText().toString();




            Cursor c6;
            c6 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
            c6.moveToFirst();

            String get_country = c6.getString(c6.getColumnIndex(db.COUNTRY));

                if(get_country.equalsIgnoreCase("India")) {


                   Cursor c5;
                   c5 = sd.rawQuery("Select * from " + db.MSOT_TYPE_TABLE, null);
                   c5.moveToFirst();

                    post_type= c5.getString(c5.getColumnIndex(db.BRANCH_AND_TECH_MSOT));

            }
            else {

                    post_type="";

            }


            if(msot_main_edt6!=null){

                get_msot_edt6=msot_main_edt6.getText().toString();
            }
            else{

                get_msot_edt6="";
            }

            if(msot_main_edt8!=null){

                get_branch_Co=msot_main_edt8.getText().toString();
            }
            else{

                get_branch_Co="";
            }


            if(msot_main_edt9!=null){

                get_branch_manager=msot_main_edt9.getText().toString();
            }
            else{

                get_branch_manager="";
            }


            Map<String, String> params = new HashMap<String, String>();
            params.put("pos_et1",get_msot_edt1);
            params.put("pos_et2",get_msot_edt2);
            params.put("pos_et3",get_msot_edt3);
            params.put("pos_et4",get_msot_edt4);
            params.put("pos_et5",get_msot_edt5);
            params.put("pos_et6",get_msot_edt6);
            params.put("pos_et7",get_msot_edt7);
            params.put("pos_date",get_current_Date);
            params.put("audit_type",post_type);
            params.put("country",get_country);
            params.put("get_branch_co",get_branch_Co);
            params.put("get_branch_mangr",get_branch_manager);
            params.put("country",get_country);
            params.put("user_mail",Category_Type_Activity.User_Login_Mail);
            params.put("main_id",Main_ID);
            Log.e("GGGHH","params"+params);



            VolleyDataRequester.withDefaultHttps( this )
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/most_main.php")
                    .setBody( params )
                    .setMethod( VolleyDataRequester.Method.POST )
                    .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                pd.dismiss();
                                Log.e("GGGG","e1"+response);

                                cv1.put(db.STATUS, "99");
                                sd.insert(db.MSOT_TABLE, null, cv1);


                                JSONObject jsonObject = new JSONObject(response);

                                Main_ID=jsonObject.getString("id");


                                cv2.put(db.INSERT_MAIN_ID, Main_ID);
                                sd.insert(db.INSERTED_MSOT_MAIN, null, cv2);
                                Log.e("YYYY","mainid="+Main_ID);


                                Cursor c6;
                                c6 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
                                c6.moveToFirst();

                                String get_country = c6.getString(c6.getColumnIndex(db.COUNTRY));

                                if(get_country.equalsIgnoreCase("India")) {


                                    Cursor c5;
                                    c5 = sd.rawQuery("Select * from " + db.MSOT_TYPE_TABLE, null);
                                    c5.moveToFirst();

                                    String get_branch_tech = c5.getString(c5.getColumnIndex(db.BRANCH_AND_TECH_MSOT));


                                    if (get_branch_tech.equalsIgnoreCase("2) Branch Section")) {

                                        Intent i = new Intent(MSOT_Main.this, Page_K.class);
                                        startActivity(i);
                                    } else if (get_branch_tech.equalsIgnoreCase("1) Technician Section")) {

                                        Intent i = new Intent(MSOT_Main.this, MSOT_LIST_1.class);
                                        startActivity(i);

                                    }

                                }
                                else {

                                    Intent i = new Intent(MSOT_Main.this, MSOT_LIST_1.class);
                                    startActivity(i);

                                }





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

                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } )
                    .requestString();




        }
        else{


            final Dialog dialog = new Dialog(MSOT_Main.this);
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
                        Toast.makeText(MSOT_Main.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(MSOT_Main.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();


        }



    }

    private boolean haveNetworkConnection(Context context) {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            Log.e("Available ", "" + ni.getTypeName());
            if (ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if (ni.isConnected()) {
                    haveConnectedWifi = true;
                    Log.i("WIFI CONNECTION ", "AVAILABLE");
                } else {
                    Log.i("WIFI CONNECTION ", "NOT AVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("MOBILE NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("MOBILE NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("USBNET")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("USBNET NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("USBNET NET CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile_internet")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile_net CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile_net CONNECTION", "NOTAVAILABLE");
                }
            }
            if (ni.getTypeName().equalsIgnoreCase("mobile")) {
                if (ni.isConnected()) {
                    haveConnectedMobile = true;
                    Log.i("mobile NET CONNECTION", "AVAILABLE");
                } else {
                    Log.i("mobile NET CONNECTION", "NOTAVAILABLE");
                }
            }
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void getData(String key_id){



        Cursor c6;
        c6 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
        c6.moveToFirst();

        String get_country = c6.getString(c6.getColumnIndex(db.COUNTRY));

        if(get_country.equalsIgnoreCase("India")) {



            card_v1.setVisibility(View.VISIBLE);
            card_v2.setVisibility(View.VISIBLE);
            lin_branch_co.setVisibility(View.VISIBLE);
            lin_branch_manager.setVisibility(View.VISIBLE);

        }



        Log.e("DDDDV","3rd ="+key_id);
        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_main.php?main_id="+Main_ID ;

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
                                String id = c.getString( "user_id" );
                                String service = c.getString( "service" );
                                String type_work = c.getString( "type_work" );
                                String msot_name = c.getString( "msot_name" );

                                String time = c.getString( "time" );
                                String date = c.getString( "date" );
                                String name_tech = c.getString( "name_tech" );
                                String status = c.getString( "status" );
                                String job_staff = c.getString( "job_staff" );
                                String branch_co_name = c.getString( "branch_co_name" );
                                String branch_managr_name = c.getString( "branch_managr_name" );


                                if(status.equalsIgnoreCase("Pending")){

                                    Log.e("DDD","wel 1 ");


                                    msot_main_edt1.setText(service);
                                    msot_main_edt2.setText(type_work);
                                    msot_main_edt3.setText(msot_name);

                                    msot_main_edt5.setText(time);
                                    msot_main_edt6.setText(name_tech);
                                    msot_main_edt7.setText(job_staff);
                                    current_Date.setText(date);
                                    msot_main_edt8.setText(branch_co_name);
                                    msot_main_edt9.setText(branch_managr_name);



                                }


                            }

//                            Toast.makeText( MSOT_Main.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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


                        Log.e("FFFFF err",error.getMessage());
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
            googleApiClient = new GoogleApiClient.Builder(MSOT_Main.this)
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
                                status.startResolutionForResult(MSOT_Main.this, REQUEST_LOCATION);

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

    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(MSOT_Main.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(MSOT_Main.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(MSOT_Main.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(MSOT_Main.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(MSOT_Main.this, new String[]{
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
                    gps = new GPSTracker(mContext, MSOT_Main.this);

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


    private void get_tech_list(){


        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
        c5.moveToFirst();

        String get_branch = c5.getString(c5.getColumnIndex(db.BRANCH));
        String get_country = c5.getString(c5.getColumnIndex(db.COUNTRY));

        Log.e("FFQQ","Branch"+get_branch);
        Log.e("FFQQ","country"+get_country);


        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/tech_list.php?get_branch="+get_branch+"&get_country="+get_country ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("EEEGF","res="+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String service = c.getString( "employee_name" );
                                String type_work = c.getString( "employee_code" );
                                String msot_name = c.getString( "branch" );
                                String job_title = c.getString( "country" );
                                String time = c.getString( "occupation_desc" );

                                responseList.add(service);



/*
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                        (getApplicationContext(), android.R.layout.select_dialog_item,responseList );*/
                                ArrayAdapter<String> adapter = new ArrayAdapter<String>(MSOT_Main.this,R.layout.dropdown, responseList);
                                //Getting the instance of AutoCompleteTextView

                                msot_main_edt6.setThreshold(1);//will start working from first character
                                msot_main_edt6.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                msot_main_edt6.setTextColor(Color.RED);
                                msot_main_edt6.setDropDownWidth(getResources().getDisplayMetrics().widthPixels);



                                msot_main_edt6.addTextChangedListener(new TextWatcher() {

                                    @Override
                                    public void afterTextChanged(Editable s) {


                                        get_tech_designation();
                                    }



                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start,
                                                                  int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start,
                                                              int before, int count) {


                                    }
                                });



                            }



//                            Toast.makeText( MSOT_Main.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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


                        Log.e("FFFFF err",error.getMessage());
                    }
                } )
                .requestJson();
    }


    private void get_tech_designation(){



        String get_names;

        if(msot_main_edt6 !=null){

            get_names=msot_main_edt6.getText().toString();

        }
        else{

            get_names="";

        }

        Log.e("BBBB","nam="+get_names);

        if(get_names.equalsIgnoreCase("")){

            msot_main_edt7.getText().clear();
        }


        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/tech_design.php?get_name="+get_names ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("EEEGFSSQS","res="+response);



                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );

                                String type_work = c.getString( "employee_code" );
                                String occup_des = c.getString( "occupation_desc" );

                                msot_main_edt7.setText(occup_des);


                            }

//                            Toast.makeText( MSOT_Main.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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


                        Log.e("FFFFF err",error.getMessage());
                    }
                } )
                .requestJson();
    }

    private void get_LIST_Data(String key_id){

        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("LLLLKI",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_methods/get_msot_list.php?main_id="+MSOT_Main.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("GGGGG","response = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String act_id = c.getString( "act_id" );
                                String act_name = c.getString( "act_name" );

                                Log.e("GGGGGSSD","act_name = "+act_name);
}
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


                        Log.e("FFFFF err",error.getMessage());
                    }
                } )
                .requestJson();
    }



  public void insert_main_off(){
      sd.delete(db.INSERTED_MSOT_MAIN,null,null);
      sd.delete(db.MSOT_TABLE,null,null);

      get_proflie_db();

      cv_off.put(db.USER_MAIL, ""+db_user_mail);
      cv_off.put(db.SERVICE_ADDRESS, ""+get_msot_edt1);
      cv_off.put(db.TYPE_WORK, ""+get_msot_edt2);
      cv_off.put(db.MSOT_NAME, ""+db_user_name);
      cv_off.put(db.TIME_MS, ""+get_msot_edt5);
      cv_off.put(db.DATE, ""+get_current_Date);
      cv_off.put(db.TECH_NAME, ""+get_msot_edt6);
      cv_off.put(db.TECH_ID, "111");
      cv_off.put(db.JOB_STAFF, ""+get_msot_edt4);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
      String currentDateandTime = sdf.format(new Date());
     // share
      if(post_type.equalsIgnoreCase("Branch Section")){
          cv_off.put(db.AUDIT_BRANCH, ""+post_type);
          cv_off.put(db.BRANCH_START_DATE, ""+currentDateandTime);

      }else
      {
          cv_off.put(db.AUDIT_TECH, ""+post_type);
          cv_off.put(db.TECH_START_DATE, ""+currentDateandTime);
      }

      cv_off.put(db.BRANCH, ""+db_branch);
      cv_off.put(db.COUNTRY, ""+db_country);
      cv_off.put(db.BRANCH_CO_NAME, ""+get_branch_Co);
      cv_off.put(db.BRANCH_MANAGE, ""+get_branch_manager);
      cv_off.put(db.STATUS, "Pending");
      sd.insert(db.MSOT_MAIN_DB, null, cv_off);
      if(post_type.startsWith("Branch")){
          Intent intent=new Intent(getApplicationContext(),Page_K.class);
          intent.putExtra("key_id",""+get_last_id());
          startActivity(intent);
      }else {
          Intent intent=new Intent(getApplicationContext(),MSOT_LIST_1.class);
          intent.putExtra("key_id",""+get_last_id());
          startActivity(intent);

      }


  }
public void get_proflie_db(){
    Cursor c5;
    c5 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
    c5.moveToFirst();

    if(c5!=null)
    {
        db_user_name=c5.getString(c5.getColumnIndex(db.USER_NAME));
        db_user_mail=c5.getString(c5.getColumnIndex(db.USER_MAIL));
        db_branch=c5.getString(c5.getColumnIndex(db.BRANCH));
        db_country=c5.getString(c5.getColumnIndex(db.COUNTRY));
    }
    c5.close();

}
public int get_last_id(){
    Cursor c5;
    c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_DB, null);
    c5.moveToLast();

    return c5.getInt(c5.getColumnIndex(db.KEY_ID));

}
}
