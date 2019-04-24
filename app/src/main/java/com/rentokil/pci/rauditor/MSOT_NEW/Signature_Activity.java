package com.rentokil.pci.rauditor.MSOT_NEW;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.AIB_Summary_7;
import com.rentokil.pci.rauditor.AIB_Title_1;
import com.rentokil.pci.rauditor.Category_Type_Activity;

import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class Signature_Activity extends AppCompatActivity  implements View.OnClickListener {


    Button supervisor_signature, tech_signature, msot_signature_button,staff_signature;
    Dialog dl;
    String Sign_Check = "";
    ImageView S_signature, T_signature, msot_signature,staff_imageView;
    signature mSignature;
    Button Clear_bt, Cancel_bt, Save_bt, Set_Developmnet_plan;
    TextView Text_name, Text_date;

    String Post_coord,Post_branch,Post_msot,Post_staff;

    Boolean isInternetPresent = false;
    RelativeLayout mContent;

    Button go_home;
    String MAIN_ID="";

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1,cv_off,cv_off_img;
    JSONObject jsonObject_get;

    JSONArray jsonArray_get;

    String Image_1,Image_2,Image_3,Image_4;
    String ASS_sign,Co_sign,Staff_sign,Manager_sign;


    private android.app.AlertDialog pd;

    Bitmap bitmap_coordinator,bitmap_branch_manager,bitmap_msot,bitmap_staff;

//    ImageView back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_);


        db = new DatabaseHelper(this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv_off = new ContentValues();
        cv_off_img = new ContentValues();


        supervisor_signature = (Button) findViewById(R.id.supervisor_signature_button);


        supervisor_signature.setOnClickListener(onButtonClick);

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");

        Log.e("GGGGRR Main id",""+id);
        if(id!=null){

            Log.e("LLLLPP","main id = "+MSOT_Main.Main_ID);
            MSOT_Main.Main_ID=id;
            get_Sign(MSOT_Main.Main_ID);
        }else {
            if (!MSOT_Main.Main_ID.equals("0")) {
                // String keyid=null;
                Log.e("EEEESSS","else ="+MSOT_Main.Main_ID);
                get_Sign(MSOT_Main.Main_ID);
            }

        }


        tech_signature = (Button) findViewById(R.id.tech_signature_button);
        tech_signature.setOnClickListener(onButtonClick);


        staff_signature = (Button) findViewById(R.id.staff_signature_button);
        staff_signature.setOnClickListener(onButtonClick);


        msot_signature_button = (Button) findViewById(R.id.msot_signature_button);
        msot_signature_button.setOnClickListener(onButtonClick);

        T_signature = (ImageView) findViewById(R.id.tech_imageView);
        S_signature = (ImageView) findViewById(R.id.supervisor_imageView);
        msot_signature = (ImageView) findViewById(R.id.msot_imageView);
        staff_imageView = (ImageView) findViewById(R.id.staff_imageView);

        S_signature = (ImageView) findViewById(R.id.supervisor_imageView);

        pd = new SpotsDialog(Signature_Activity.this, R.style.Custom);
        go_home = (Button) findViewById(R.id.go_home);


        go_home.setOnClickListener(this);

    }

    @Override
    public void onBackPressed(){

        Intent i= new Intent(Signature_Activity.this,Page_O.class);
        startActivity(i);
    }

    public void get_signature(String name_author) {
        dl = new Dialog(Signature_Activity.this);
        dl.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dl.setContentView(R.layout.capturesignature);
        dl.show();
        dl.setCancelable(false);
        Clear_bt = (Button) dl.findViewById(R.id.clear);
        Text_name = (TextView) dl.findViewById(R.id.sign_name);
//        Text_name.setVisibility(View.GONE);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Text_date = (TextView) dl.findViewById(R.id.sign_date);
        Text_date.setText(currentDateTimeString);





        if (name_author.equals("Man")) {

            Text_name.setText(Manager_sign);

        } else if(name_author.equals("msot")) {

            Text_name.setText(ASS_sign);
        }
        else if(name_author.equals("Co")) {

            Text_name.setText(Co_sign);
        }
        else if(name_author.equals("staff")) {

            Text_name.setText(Staff_sign);
        }

        Cancel_bt = (Button) dl.findViewById(R.id.cancel);
        Save_bt = (Button) dl.findViewById(R.id.save);
        mContent = (RelativeLayout) dl.findViewById(R.id.mysignature);
        mSignature = new signature(this, null);
        mContent.addView(mSignature);
        Save_bt.setOnClickListener(onButtonClick);
        Clear_bt.setOnClickListener(onButtonClick);
        Cancel_bt.setOnClickListener(onButtonClick);
      /*  Cancel_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl.dismiss();
            }
        });*/

    }

    Button.OnClickListener onButtonClick = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            if (v == Clear_bt) {
                mSignature.clear();
                //  dl.dismiss();

            } else if (v == Save_bt) {
                mSignature.save();

            } else if (v == Cancel_bt) {
               /* Bundle b = new Bundle();
                b.putString("byteArray", "cancel");
                Intent intent = new Intent();
                intent.putExtras(b);
                setResult(3, intent);*/
                // finish();
                mSignature.clear();
                dl.dismiss();

            } else if (v == tech_signature) {
                Sign_Check = "Man";
                get_signature(Sign_Check);

            }
            else if (v == staff_signature) {
                Sign_Check = "staff";
                get_signature(Sign_Check);

            }


            else if (v == supervisor_signature) {

                Sign_Check = "Co";
                get_signature(Sign_Check);

            }

            else if (v == msot_signature_button) {

                Sign_Check = "msot";
                get_signature(Sign_Check);

            }
        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.go_home){


            isInternetPresent = haveNetworkConnection(Signature_Activity.this);
            if (isInternetPresent) {

                pd.show();

                if (validation()) {
                    Log.e("GGGG","validation");
                    if (isInternetPresent) {

                        post_sum();
                    }
                    else{

                        pd.dismiss();
                        Toast.makeText(Signature_Activity.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    pd.dismiss();
                    Log.e("GGGG","else");

                    Toast.makeText(Signature_Activity.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                pd.dismiss();
                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

                final Dialog dialog = new Dialog(Signature_Activity.this);
                dialog.setContentView(R.layout.alertbox);
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
                            Toast.makeText(Signature_Activity.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Toast.makeText(Signature_Activity.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                dialog.show();


            }

        }


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
            String Man1 = "Man";
            String Co1 = "Co";
            String Msot1 = "msot";
            String Staff1 = "staff";
            Bitmap returnedBitmap = Bitmap.createBitmap(mContent.getWidth(),
                    mContent.getHeight(), Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(returnedBitmap);

            Drawable bgDrawable = mContent.getBackground();

            if (bgDrawable != null)
                bgDrawable.draw(canvas);
            else
                canvas.drawColor(Color.WHITE);
            mContent.draw(canvas);

            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            //  Context context;
            returnedBitmap.compress(Bitmap.CompressFormat.PNG, 50, bs);
            // Bitmap b=BitmapFactory.decodeStream(mContent.getBbs.toByteArray());
            if (Sign_Check.equals("Man")) {
                T_signature.setVisibility(View.VISIBLE);

                T_signature.setImageBitmap(returnedBitmap);
            }
            if (Sign_Check.equals("Co")) {
                S_signature.setVisibility(View.VISIBLE);
                S_signature.setImageBitmap(returnedBitmap);
            }

            if (Sign_Check.equals("staff")) {
                staff_imageView.setVisibility(View.VISIBLE);
                staff_imageView.setImageBitmap(returnedBitmap);
            }

            if (Sign_Check.equals("msot")) {
                msot_signature.setVisibility(View.VISIBLE);
                msot_signature.setImageBitmap(returnedBitmap);
            }
            dl.dismiss();

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

    public void check_development_plan() {
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



    private void post_sum() {
        Post_coord=getStringImage(bitmap_coordinator);
        Post_branch=getStringImage(bitmap_branch_manager);
        Post_msot=getStringImage(bitmap_msot);
        Post_staff=getStringImage(bitmap_staff);
        if (bitmap_coordinator!=null) {
            Post_coord=getStringImage(bitmap_coordinator);
        }else {
            Post_coord="";
        }
        if (bitmap_branch_manager!=null) {
            Post_branch=getStringImage(bitmap_branch_manager);
        }else {
            Post_branch="";
        }

        if (bitmap_msot!=null) {
            Post_msot=getStringImage(bitmap_msot);
        }else {
            Post_msot="";
        }

        if (bitmap_staff!=null) {
            Post_staff=getStringImage(bitmap_staff);
        }else {
            Post_staff="";
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("sign_1",Post_coord);
        params.put("sign_2",Post_branch);
        params.put("sign_3",Post_msot);
        params.put("sign_4",Post_staff);
        params.put("main_id", MSOT_Main.Main_ID);

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_16_sign.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            pd.dismiss();
                            Log.e("RRRRRT","response = "+response);

                            Toast.makeText(getApplicationContext(),"Completed",Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(Signature_Activity.this, Category_Type_Activity.class);
                            startActivity(intent);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("DDDDDE","eror = "+error.getMessage());
                        pd.dismiss();
                        try {
                            if(error.getMessage().equalsIgnoreCase("null")){

                                Intent intent=new Intent(Signature_Activity.this, Category_Type_Activity.class);
                                startActivity(intent);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                            Intent intent=new Intent(Signature_Activity.this, Category_Type_Activity.class);
                            startActivity(intent);
                        }


                    }
                } )
                .requestString();
    }

    public boolean validation() {
        boolean check_point=false;
        try {
            bitmap_coordinator=((BitmapDrawable) S_signature.getDrawable()).getBitmap();
            bitmap_branch_manager=((BitmapDrawable) T_signature.getDrawable()).getBitmap();
            bitmap_msot=((BitmapDrawable) msot_signature.getDrawable()).getBitmap();
            bitmap_staff=((BitmapDrawable) staff_imageView.getDrawable()).getBitmap();
            Post_coord=getStringImage(bitmap_coordinator);
            Post_branch=getStringImage(bitmap_branch_manager);
            Post_msot=getStringImage(bitmap_msot);
            Post_staff=getStringImage(bitmap_staff);
            try {
                if (Post_coord.length() != 0 && Post_branch.length() != 0 && Post_msot.length() != 0 && Post_staff.length() != 0) {
                    check_point = true;
                } else {
                    check_point = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return check_point;

    }


    private void get_Sign(String key_id){

        Log.e("RRRRS","PPP"+key_id);
        Log.e("KKKJJY",key_id);
        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("SSSQQW",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_page_16.php?main_id="+MSOT_Main.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("BBBVVV","response = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);

                                String id = c.getString( "id" );
                                Staff_sign = c.getString( "staff_name" );
                                ASS_sign = c.getString( "access_name" );
                                Co_sign = c.getString( "co_name" );
                               Manager_sign = c.getString( "manager_name" );
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
                        Log.e("EEEEERRRSS","err = "+error.getMessage());
                    }
                } )
                .requestJson();
    }
    public void insert_off_sign(){

        cv_off.put(db.MAIN_ID,MAIN_ID);
        cv_off.put(db.STAFF_SIGN,Post_staff);
        cv_off.put(db.MAN_SIGN,Post_branch);
        cv_off.put(db.ACC_SIGN,Post_msot);
        cv_off.put(db.CO_SIGN,Post_coord);

        sd.insert(db.MSOT_SIGN_DB,null,cv_off);

    }

}
