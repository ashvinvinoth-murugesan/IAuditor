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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class AIB_Summary_7 extends AppCompatActivity {

    Button aibsumsub,aibsumbck,aibsumcomplete1;

    private android.app.AlertDialog pd;
    String Image_1,Image_2;
    ImageView Customer_Sign;
    Bitmap bitmap_auditor,bitmap_customer;

    CheckBox terms_conditions;
    String Sign_Check="";
  //  Dialog dl;
    LinearLayout hide_layout,mContent;
    signature mSignature;
    Bitmap returnedBitmap_Customer;
    Bitmap returnedBitmap_RIPCI;
    String Post_Cus,Post_Audi;
    ImageView Sirsignauditor;

    Button Save_bt,Clear_bt;
    Button Cancel_bt;
    String Check_Point="";

    private FirebaseAnalytics mFirebaseAnalytics;



    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    EditText des;

    String getdes,getbtn1,getdate;

    RadioGroup aib_sum_radiogroup1;

    RadioButton rdbtn1;

    ConnectivityManager cManager;
    NetworkInfo nInfo;


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

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    AlertDialog aa;
    AlertDialog.Builder dialogBuilder;
    View d2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aib__summary);

        db = new DatabaseHelper(AIB_Summary_7.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();


        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        aibsumsub=(Button) findViewById(R.id.aibsumcomplete);
        aibsumbck=(Button) findViewById(R.id.aibsumbck);
        aibsumcomplete1=(Button) findViewById(R.id.aibsumcomplete1);

        aibsumcomplete1.setVisibility(View.GONE);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};


        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(AIB_Summary_7.this);
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
                        Toast.makeText(AIB_Summary_7.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(AIB_Summary_7.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        aibsumcomplete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_summ_button_next();


                Intent i = new Intent(AIB_Summary_7.this,Category_Type_Activity.class);
                startActivity(i);

            }
        });


        des=(EditText) findViewById(R.id.aib_sum_description);


        pd = new SpotsDialog(AIB_Summary_7.this, R.style.Custom);

        Customer_Sign=(ImageView)findViewById(R.id.rs_signature_im);
        Sirsignauditor=(ImageView)findViewById(R.id.sirsumauditorsign);

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

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){

            Log.e("SDSD","main id 4 = "+AIB_Title_1.Main_ID);
            AIB_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!AIB_Title_1.Main_ID.equals("0")) {

                Log.e("SDSD","else = "+AIB_Title_1.Main_ID);
                getData(AIB_Title_1.Main_ID);
            }

        }





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







        aibsumsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_summ_button_next();

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(AIB_Summary_7.this);
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
                                Toast.makeText(AIB_Summary_7.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(AIB_Summary_7.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }




                if (validation()) {

                    aib_summ_button_next();
                    post_aib_sum();
                } else {
                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }



            }
        });

        aibsumbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                aib_summ_button_back();

                Intent i = new Intent(AIB_Summary_7.this,AIB_Interl_Obser_6.class);
                startActivity(i);


            }
        });
    }

    public  boolean validation(){


        try {
            getdes=des.getText().toString();
            getdate=date.getText().toString();

            bitmap_auditor=((BitmapDrawable) Sirsignauditor.getDrawable()).getBitmap();
            bitmap_customer=((BitmapDrawable) Customer_Sign.getDrawable()).getBitmap();

            Post_Audi=getStringImage(bitmap_auditor);
            Post_Cus=getStringImage(bitmap_customer);

            if(TextUtils.isEmpty(getdes ) ) {

                if (TextUtils.isEmpty(getdes)) {
                    des.setError("Required");
                }

                return false;
            }


            if ((getdes.length()==0||getdate.length()==0||Post_Audi.length()==0||Post_Cus.length()==0)){
                return  false;
            }else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }


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
        mSignature = new AIB_Summary_7.signature(this, null);
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

    private void post_aib_sum() {

        pd.show();
        if (Post_Audi!=null) {

            Post_Audi=getStringImage(bitmap_auditor);
        }
        else{

            Post_Audi="";
        }

        if (Post_Cus!=null) {

            Post_Cus=getStringImage(bitmap_customer);
        }
        else{

            Post_Cus="";
        }

        if (getdate!=null) {

            getdate=date.getText().toString();

        }
        else{

            getdate="";
        }

        if (getdes!=null) {

            getdes=des.getText().toString();

        }
        else{

            getdes="";
        }


        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();

        params.put("siraudisign",Post_Audi);
        params.put("sircussign",Post_Cus);
        params.put("datei",getdate);
        params.put("des1",getdes);




        params.put("main_id",AIB_Title_1.Main_ID);

        Log.e("BNBNM","params = "+params);




        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/AIB/aib_sum.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            pd.dismiss();
                            Log.e("BBBBB","res1 = "+response);
                            Intent i = new Intent(AIB_Summary_7.this,Category_Type_Activity.class);
                            startActivity(i);
                            //    Toast.makeText( AIB_Summary_7.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();

                        Log.e("BBBBB","error2  = "+error.getMessage());

                        try {
                            if(true){

                                Intent i = new Intent(AIB_Summary_7.this,Category_Type_Activity.class);
                                startActivity(i);
                            }
                            else if(error.getMessage().equalsIgnoreCase("java.lang.NullPointerException: Attempt to invoke virtual method 'int java.lang.String.length()' on a null object reference"))
                            {
                                Intent i = new Intent(AIB_Summary_7.this,Category_Type_Activity.class);
                                startActivity(i);

                            }
                            //  Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                } )
                .requestString();




    }

    private void getData(String key_id){



        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            AIB_Title_1.Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/AIB/get_aib_sum_7.php?main_id="+AIB_Title_1.Main_ID;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            pd.dismiss();

                            Log.e("YYYYYYY","aib sum = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");

                            for (int i = 0; i < jsonArray_get.length(); i++) {

                                Log.e("RRRRRRR","aib sum en1  ");
                                JSONObject c = jsonArray_get.getJSONObject(i);
                                Log.e("GFGFG","enter1");
                                String getimage_ri = c.getString( "sign_audit" );
                                String getimage_cus = c.getString( "sign_cus" );
                                String get_summary = c.getString( "summary" );
                                String get_next_date = c.getString( "next_date" );


                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                c5.moveToFirst();

                                Log.e("KLOIP","update value = "+c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS)));

                                String get_pmca_complete = c5.getString(c5.getColumnIndex(db.AIB_COMPLETE_STATUS));

                                if(get_summary.equalsIgnoreCase("null")&get_next_date.equalsIgnoreCase("null")){
                                    get_pmca_complete="";


                                }

                                if(get_pmca_complete.equalsIgnoreCase("2")){


                                    aibsumsub.setVisibility(View.GONE);
                                    aibsumbck.setVisibility(View.GONE);
                                    aibsumcomplete1.setVisibility(View.VISIBLE);

                                    des.setText(get_summary);

                                    date.setText(get_next_date);

                                    des.setEnabled(false);


                                    date.setEnabled(false);

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


                                    des.setText(get_summary);
                                    date.setText(get_next_date);
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

                        pd.dismiss();


                        Log.e( "DSDS","error res sum4 = = "+error.getMessage() );
                    }
                } )
                .requestJson();
    }


    private void aib_summ_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


    }

    private void aib_summ_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "aib_1");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "aib_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }


}
