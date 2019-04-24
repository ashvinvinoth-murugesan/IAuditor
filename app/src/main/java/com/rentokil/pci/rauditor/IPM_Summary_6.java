package com.rentokil.pci.rauditor;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class IPM_Summary_6 extends AppCompatActivity {

    Button ipmsumsub,ipmsumbck;

    Button pestsumsub,pestsumbck;


    private FirebaseAnalytics mFirebaseAnalytics;
    AlertDialog aa;
    AlertDialog.Builder dialogBuilder;
    View d2;

    ImageView Customer_Sign;
    Bitmap bitmap_auditor,bitmap_customer;

    CheckBox terms_conditions;
    String Sign_Check="";
   // Dialog dl;
    LinearLayout hide_layout,mContent;
    signature mSignature;
    Bitmap returnedBitmap_Customer;
    Bitmap returnedBitmap_RIPCI;
    String Post_Cus,Post_Audi,getdesc,sirtitleconductd;
    ImageView Sirsignauditor;

    Button Save_bt,Clear_bt;
    Button Cancel_bt,ipmsumcomple1;
    String Check_Point="";

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    private android.app.AlertDialog pd;

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

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    String Image_1,Image_2;


    EditText ipm_sum_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ipm__summary);

        ipmsumsub =(Button) findViewById(R.id.ipmsumcomple);
        ipmsumbck=(Button) findViewById(R.id.ipmsumbck);
        ipmsumcomple1=(Button) findViewById(R.id.ipmsumcomple1);

        ipmsumcomple1.setVisibility(View.GONE);


        ipmsumcomple1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                pd.show();
                ipm_obser_next();

                Intent i = new Intent(IPM_Summary_6.this,Category_Type_Activity.class);
                startActivity(i);
            }
        });



        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        sirtitleetconducted=(TextView) findViewById(R.id.date_calendar);
        ipm_sum_description=(EditText) findViewById(R.id.ipm_sum_description);

        Customer_Sign=(ImageView)findViewById(R.id.rs_signature_im);
        Sirsignauditor=(ImageView)findViewById(R.id.sirsumauditorsign);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = df.format(c.getTime());
        mLastClickTime = SystemClock.elapsedRealtime();

        date = (TextView) findViewById(R.id.date_calendar);
        date.setText(formattedDate);
        setDateTimeField();

        db = new DatabaseHelper(IPM_Summary_6.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();




        pd = new SpotsDialog(IPM_Summary_6.this, R.style.Custom);

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(IPM_Summary_6.this);
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
                        Toast.makeText(IPM_Summary_6.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(IPM_Summary_6.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){

            Log.e("SDSD","main id 4 = "+QSR_Title_1.Main_ID);
            IPM_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!IPM_Title_1.Main_ID.equals("0")) {

                Log.e("SDSD","else = "+IPM_Title_1.Main_ID);
                getData(IPM_Title_1.Main_ID);
            }

        }

        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );
        //  setDateTimeField();
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                follow = 1;
                fromDatePickerDialog.show();
            }
        });

        Sirsignauditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Sign_Check="RIPCI";
                get_signature();
            }
        });

        Customer_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_Check="Customer";
                get_signature();
            }
        });


        ipmsumsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_obser_next();

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(IPM_Summary_6.this);
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
                                Toast.makeText(IPM_Summary_6.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(IPM_Summary_6.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {

                    ipm_obser_next();
                    post_ipm_summary();
                }else {
                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT);
                }
            }
        });

        ipmsumbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ipm_obser_back();
                Intent i = new Intent(IPM_Summary_6.this,IPM_Obser_5.class);
                startActivity(i);
            }
        });
    }


    @Override

    public void onBackPressed(){

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
public boolean validation(){


    try {
        bitmap_auditor=((BitmapDrawable) Sirsignauditor.getDrawable()).getBitmap();
        bitmap_customer=((BitmapDrawable) Customer_Sign.getDrawable()).getBitmap();

        getdesc=ipm_sum_description.getText().toString();
        sirtitleconductd = sirtitleetconducted.getText().toString();

        Post_Audi=getStringImage(bitmap_auditor);
        Post_Cus=getStringImage(bitmap_customer);

        if(TextUtils.isEmpty(getdesc ) ) {

            if (TextUtils.isEmpty(getdesc)) {
                ipm_sum_description.setError("Required");
            }

            return false;
        }


        if(Post_Audi==null||Post_Cus==null){
            return  false;
        }else {
            return  true;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return  false;
    }
}
    public void get_signature(){

        dialogBuilder = new AlertDialog.Builder(this);

        LayoutInflater inflater = this.getLayoutInflater();
        d2 = inflater.inflate(R.layout.sign, null);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        dialogBuilder.setView(d2);


        dialogBuilder.setCancelable(false);



        Clear_bt= (Button)d2.findViewById(R.id.clear);

        TextView set_date_time=(TextView)d2.findViewById(R.id.textView2);
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
        String DateToStr = format.format(curDate);
        set_date_time.setText(DateToStr);
        System.out.println(DateToStr);
        Cancel_bt=(Button)d2.findViewById(R.id.cancel);
        Save_bt=(Button)d2.findViewById(R.id.save);
        mContent = (LinearLayout) d2.findViewById(R.id.mysignature);
        mSignature = new IPM_Summary_6.signature(this, null);
        mContent.addView(mSignature);
        Save_bt.setOnClickListener(onButtonClick);
        Clear_bt.setOnClickListener(onButtonClick);
        Cancel_bt.setOnClickListener(onButtonClick);
        aa = dialogBuilder.create();
        aa.show();
    }
    Button.OnClickListener onButtonClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            if (v == Clear_bt) {
                mSignature.clear();
                //dl.dismiss();

            } else if (v == Save_bt) {
                mSignature.save();


            }else if(v == Cancel_bt){
                aa.dismiss();

            }
        }
    };

    public class signature extends View {
        static final float STROKE_WIDTH = 5f;
        static final float HALF_STROKE_WIDTH = STROKE_WIDTH / 2;
        Paint paint = new Paint();
        Path path = new Path();
        float lastTouchX;
        float lastTouchY;
        final RectF dirtyRect = new RectF();
        public signature(Context context, AttributeSet attrs) {
            super(context, attrs);
            paint.setAntiAlias(true);
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStrokeWidth(STROKE_WIDTH);
        }
        public void clear() {
            path.reset();
            invalidate();
            Save_bt.setEnabled(false);
        }
        public void save() {

            if(Sign_Check.equals("Customer")){
                returnedBitmap_Customer = Bitmap.createBitmap(mContent.getWidth(),
                        mContent.getHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(returnedBitmap_Customer);

                Drawable bgDrawable = mContent.getBackground();

                if (bgDrawable != null)
                    bgDrawable.draw(canvas);
                else
                    canvas.drawColor(Color.WHITE);
                mContent.draw(canvas);

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                //  Context context;
                returnedBitmap_Customer.compress(Bitmap.CompressFormat.PNG, 50, bs);
            }
            if(Sign_Check.equals("RIPCI")){
                returnedBitmap_RIPCI = Bitmap.createBitmap(mContent.getWidth(),
                        mContent.getHeight(), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(returnedBitmap_RIPCI);

                Drawable bgDrawable = mContent.getBackground();

                if (bgDrawable != null)
                    bgDrawable.draw(canvas);
                else
                    canvas.drawColor(Color.WHITE);
                mContent.draw(canvas);

                ByteArrayOutputStream bs = new ByteArrayOutputStream();
                //  Context context;
                returnedBitmap_RIPCI.compress(Bitmap.CompressFormat.PNG, 50, bs);
            }
            // Bitmap b=BitmapFactory.decodeStream(mContent.getBbs.toByteArray());

            if(Sign_Check.equals("Customer")){
                Customer_Sign.setVisibility(View.VISIBLE);
                Customer_Sign.setImageBitmap(returnedBitmap_Customer);
            }    if(Sign_Check.equals("RIPCI")){

                Sirsignauditor.setVisibility(View.VISIBLE);

                Sirsignauditor.setImageBitmap(returnedBitmap_RIPCI);
            }
            aa.dismiss();

            // finish();
        }
        @Override
        protected void onDraw(Canvas canvas) {
            // TODO Auto-generated method stub
            canvas.drawPath(path, paint);
        }
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float eventX = event.getX();
            float eventY = event.getY();
            Save_bt.setEnabled(true);
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    path.moveTo(eventX, eventY);
                    lastTouchX = eventX;
                    lastTouchY = eventY;
                    return true;
                case MotionEvent.ACTION_MOVE:
                case MotionEvent.ACTION_UP:
                    resetDirtyRect(eventX, eventY);
                    int historySize = event.getHistorySize();
                    for (int i = 0; i < historySize; i++) {
                        float historicalX = event.getHistoricalX(i);
                        float historicalY = event.getHistoricalY(i);
                        path.lineTo(historicalX, historicalY);
                    }
                    path.lineTo(eventX, eventY);
                    break;
            }
            invalidate((int) (dirtyRect.left - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.top - HALF_STROKE_WIDTH),
                    (int) (dirtyRect.right + HALF_STROKE_WIDTH),
                    (int) (dirtyRect.bottom + HALF_STROKE_WIDTH));
            lastTouchX = eventX;
            lastTouchY = eventY;
            return true;
        }
        private void resetDirtyRect(float eventX, float eventY) {
            dirtyRect.left = Math.min(lastTouchX, eventX);
            dirtyRect.right = Math.max(lastTouchX, eventX);
            dirtyRect.top = Math.min(lastTouchY, eventY);
            dirtyRect.bottom = Math.max(lastTouchY, eventY);
        }
    }

    public String getStringImage(Bitmap bmp){
        String encodedImage = null;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp.setPixel(20,100, Color.BLUE);
            byte[] imageBytes = baos.toByteArray();
            encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        return encodedImage;
    }

    private void post_ipm_summary() {

        pd.show();


        Map<String, String> params = new HashMap<String, String>();
        params.put("siraudisign",Post_Audi);
        params.put("sircussign",Post_Cus);
        params.put("sirgetdesc",getdesc);
        params.put("sirdate",sirtitleconductd);
        params.put("main_id",IPM_Title_1.Main_ID);

        Log.e("MMMMM 444","Post_Audi = "+Post_Audi);
        Log.e("MMMMM 444","Post_Cus= "+Post_Cus);
        Log.e("MMMMM 444","getdesc= "+getdesc);
        Log.e("MMMMM 444","conduct date ="+sirtitleconductd);
        Log.e("MMMMM 444","ipm sum main id ="+IPM_Title_1.Main_ID);

        Log.e("TTTTRRR","pos audi"+Post_Audi);
        Log.e("TTTTRRR","pos cus"+Post_Cus);
        Log.e("TTTTRRR","get desc"+getdesc);
        Log.e("TTTTRRR","date"+sirtitleconductd);
        Log.e("TTTTRRR","main id"+IPM_Title_1.Main_ID);


        Log.e("MMMMM 444",""+params);


        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/IPM/in_ipm_sum_6.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            pd.dismiss();
                            Log.e( "JJJJ IPM 66","\t"+response);
                            Intent i=new Intent( IPM_Summary_6.this,Category_Type_Activity.class);
                            startActivity( i );

                            //   Toast.makeText( IPM_Summary_6.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        Log.e("FFFF","error = "+error.getMessage());


                        try {
                            if(true){

                                Intent i = new Intent(IPM_Summary_6.this,Category_Type_Activity.class);
                                startActivity(i);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }


                        //   Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }

    private void getData(String key_id){

//        pd.show();

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            IPM_Title_1.Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/IPM/get_ipm_sum_6.php?main_id="+IPM_Title_1.Main_ID;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            Log.e("FRFR","res pmca 4 = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");

                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                Log.e("FRFR","for =  ");
                                JSONObject c = jsonArray_get.getJSONObject(i);
                                Log.e("GFGFG","enter1");
                                String getimage_ri = c.getString( "sign_audit" );
                                String getimage_cus = c.getString( "sign_cus" );
                                String get_summary = c.getString( "summary" );
                                String get_next_date = c.getString( "next_date" );



                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("DFDFCS","update value = "+c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.IPM_COMPLETE_STATUS));

                                if(get_summary.equalsIgnoreCase("null")&get_next_date.equalsIgnoreCase("null")){
                                    get_pmca_complete="";


                                }

                                if(get_pmca_complete.equalsIgnoreCase("2")){




                                    ipmsumsub.setVisibility(View.GONE);
                                    ipmsumbck.setVisibility(View.GONE);
                                    ipmsumcomple1.setVisibility(View.VISIBLE);

                                    Log.e("c","entry1");

                                    ipm_sum_description.setText(get_summary);
                                    sirtitleetconducted.setText(get_next_date);

                                    ipm_sum_description.setEnabled(false);
                                    sirtitleetconducted.setEnabled(false);

//                                    sircomplete1.setVisibility(View.VISIBLE);
//                                    sirsumcompl.setVisibility(View.GONE);


                                    if(getimage_ri.length()!=0){
                                        Image_1=getimage_ri;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    if(getimage_cus.length()!=0){
                                        Image_2=getimage_cus;
                                        //RS_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(getimage_ri, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Sirsignauditor.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));

                                    byte[] decodedString_r = Base64.decode(getimage_cus, Base64.DEFAULT);
                                    Bitmap decodedByte_r = BitmapFactory.decodeByteArray(decodedString_r, 0, decodedString_r.length);
                                    //imageView2.setImageBitmap(decodedByte_r);
                                    Customer_Sign.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_r, 400, 400, false));

                                    pd.dismiss();
                                }
                                else if(get_pmca_complete.equalsIgnoreCase("1")) {
                                    Log.e("SDEE","entry2 else");

                                    if (getimage_ri.length() != 0) {
                                        Image_1 = getimage_ri;
                                        //Customer_Signature_IM.setImageDrawable(null);
                                    }
                                    if (getimage_cus.length() != 0) {
                                        Image_2 = getimage_cus;
                                        //RS_Signature_IM.setImageDrawable(null);
                                    }

                                    byte[] decodedString_c = Base64.decode(getimage_ri, Base64.DEFAULT);
                                    Bitmap decodedByte_c = BitmapFactory.decodeByteArray(decodedString_c, 0, decodedString_c.length);
                                    //   imageView1.setImageBitmap(decodedByte_c);
                                    Sirsignauditor.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_c, 400, 400, false));

                                    byte[] decodedString_r = Base64.decode(getimage_cus, Base64.DEFAULT);
                                    Bitmap decodedByte_r = BitmapFactory.decodeByteArray(decodedString_r, 0, decodedString_r.length);
                                    //imageView2.setImageBitmap(decodedByte_r);
                                    Customer_Sign.setImageBitmap(Bitmap.createScaledBitmap(decodedByte_r, 400, 400, false));


                                    ipm_sum_description.setText(get_summary);
                                    sirtitleetconducted.setText(get_next_date);
                                }
                            }

//                            Toast.makeText( SIR_Summary_4.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
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
                        Log.e( "DSDS","error res sum4 = = "+error.getMessage() );
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
