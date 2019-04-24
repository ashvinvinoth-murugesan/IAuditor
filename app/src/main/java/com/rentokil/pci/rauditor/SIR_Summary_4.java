package com.rentokil.pci.rauditor;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class SIR_Summary_4 extends AppCompatActivity {

     Button sirsumcompl,sirsumbck,sircomplete1;

    ImageView Customer_Sign;
    Bitmap  bitmap_auditor,bitmap_customer;

    CheckBox terms_conditions;
    String Sign_Check="";
    AlertDialog.Builder dialogBuilder;
    LinearLayout hide_layout,mContent;
    View d2;
    AlertDialog aa;
    signature  mSignature;
    Bitmap returnedBitmap_Customer;
    Bitmap returnedBitmap_RIPCI;
   String Post_Cus,Post_Audi;
    ImageView Sirsignauditor;

    String myJSON;
    JSONArray json_arr_cus_result;

    private FirebaseAnalytics mFirebaseAnalytics;

    Button Save_bt,Clear_bt;
    Button Cancel_bt;
    String Check_Point="";
    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    String Image_1,Image_2;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;

    public String Update_Status="0";

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    private android.app.AlertDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sir_summary);

        Customer_Sign=(ImageView)findViewById(R.id.rs_signature_im);
        Sirsignauditor=(ImageView)findViewById(R.id.sirsumauditorsign);
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();
        db = new DatabaseHelper(SIR_Summary_4.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(SIR_Summary_4.this);
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
                        Toast.makeText(SIR_Summary_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(SIR_Summary_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }

//        getData();
        pd = new SpotsDialog(SIR_Summary_4.this, R.style.Custom);
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

        sirsumcompl =(Button) findViewById(R.id.sircomplete);
        sirsumbck=(Button) findViewById(R.id.sirsumbck);
        sircomplete1=(Button) findViewById(R.id.sircomplete1);

        sircomplete1.setVisibility(View.GONE);

        sircomplete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(SIR_Summary_4.this,Category_Type_Activity.class);
                startActivity(i);

            }
        });


        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);

        if(id!=null){

            Log.e("SDSD","main id 4 = "+SIR_Title_1.Main_ID);
            SIR_Title_1.Main_ID=id;
            getData(id);
        }else {
            if (!SIR_Title_1.Main_ID.equals("0")) {
                // String keyid=null;

                Log.e("SDSD","else = "+SIR_Title_1.Main_ID);
                getData(SIR_Title_1.Main_ID);
            }

        }


        this .getWindow().setSoftInputMode(WindowManager.LayoutParams. SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        sirsumcompl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {




                } else {
                    final Dialog dialog = new Dialog(SIR_Summary_4.this);
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
                                Toast.makeText(SIR_Summary_4.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(SIR_Summary_4.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }


                if(Sirsignauditor.getDrawable() == null & Customer_Sign.getDrawable() == null){

                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }
                else if (Sirsignauditor.getDrawable() == null){

                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }
                else if (Customer_Sign.getDrawable() == null){

                    Toast.makeText(getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT).show();
                }

                else{
                    Toast.makeText(getApplicationContext(),"SIR Audit Submitted",Toast.LENGTH_SHORT).show();
                    sirsignsum();
                    sir_sum_button_next();

                }





//                if (validation()) {
//
//                } else {
//                    Toast.makeText( getApplicationContext(),"Please fill all mandatory fields",Toast.LENGTH_SHORT ).show();
//                }

            }
        });

        sirsumbck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sir_sum_button_back();
                Intent i = new Intent(SIR_Summary_4.this,SIR_Obser_3.class);
                startActivity(i);
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
        mSignature = new signature(this, null);
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


    private void sir_sum_button_next(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_3");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_4_submit");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

    private void sir_sum_button_back(){


        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "sir_4");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "click me");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "sir_4_back");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

    }

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
            }
            if(Sign_Check.equals("RIPCI")){

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

    private void sirsignsum() {

        pd.show();

        bitmap_auditor=((BitmapDrawable) Sirsignauditor.getDrawable()).getBitmap();
        bitmap_customer=((BitmapDrawable) Customer_Sign.getDrawable()).getBitmap();

        Post_Audi=getStringImage(bitmap_auditor);
        Post_Cus=getStringImage(bitmap_customer);

        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();
        params.put("siraudisign",Post_Audi);
        params.put("sircussign",Post_Cus);
        params.put("main_id",SIR_Title_1.Main_ID);

  Log.e("VFVFVF","params = "+params);




        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/SIR/in_sir_sum_4.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.e( "JJJJJ","Res SIR 4"+response);
                        try {

                            pd.dismiss();
                            Intent i = new Intent(SIR_Summary_4.this,Category_Type_Activity.class);
                            startActivity(i);



//                            Toast.makeText( SIR_Summary_4.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        pd.dismiss();
                        try {
                            Intent i = new Intent(SIR_Summary_4.this,Category_Type_Activity.class);
                            startActivity(i);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.e("DDDDDD","error obser3  = "+error.getMessage());


//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } )
                .requestString();




    }

    @Override
    public void onBackPressed(){

    }

    public boolean validation(){



        bitmap_auditor=((BitmapDrawable) Sirsignauditor.getDrawable()).getBitmap();
        bitmap_customer=((BitmapDrawable) Customer_Sign.getDrawable()).getBitmap();

        Post_Audi=getStringImage(bitmap_auditor);
        Post_Cus=getStringImage(bitmap_customer);


        if(Post_Audi==null||Post_Cus==null){
            return false;
        }else {
            return true;
        }
    }

    private void getData(String key_id){
        pd.show();

        Log.e("FFFFF key id",key_id);
        if(key_id!=null){
            SIR_Title_1.Main_ID=key_id;
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/SIR/get_sir_sum_4.php?main_id="+SIR_Title_1.Main_ID;

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

                                String getimage_ri = c.getString( "image_ri" );
                                String getimage_cus = c.getString( "image_cus" );


                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.USER_COMPLETE_STATUS, null);
                                c5.moveToFirst();

                                Log.e("HHNNNNN","update value = "+c5.getString(c5.getColumnIndex(db.COMPLETE_STATUS)));

                                String getsircuscomp = c5.getString(c5.getColumnIndex(db.COMPLETE_STATUS));
                                if(getsircuscomp.equalsIgnoreCase("2")){

                                    Log.e("MMMMML","entry1");



                                    sirsumcompl.setVisibility(View.GONE);
                                    sirsumbck.setVisibility(View.GONE);
                                    sircomplete1.setVisibility(View.VISIBLE);



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
                                else if(getsircuscomp.equalsIgnoreCase("1")) {
                                    Log.e("MMMMML","entry2 else");

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


}
