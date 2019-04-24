package com.rentokil.pci.rauditor.MSOT_NEW;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.AIB_Summary_7;
import com.rentokil.pci.rauditor.AIB_Title_1;
import com.rentokil.pci.rauditor.Category_Type_Activity;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import dmax.dialog.SpotsDialog;

public class Signature2 extends AppCompatActivity  implements View.OnClickListener {


    Button supervisor_signature, tech_signature, msot_signature_button;
    Dialog dl;
    String Sign_Check = "";
    ImageView S_signature, T_signature, msot_signature;
    signature mSignature;
    Button Clear_bt, Cancel_bt, Save_bt, Set_Developmnet_plan;
    TextView Text_name, Text_date;

    String Post_coord,Post_branch,Post_msot;

    Boolean isInternetPresent = false;
    RelativeLayout mContent;
    private android.app.AlertDialog pd;
    Button go_home;

    Bitmap bitmap_coordinator,bitmap_branch_manager,bitmap_msot;

    ImageView back1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature2);

        supervisor_signature = (Button) findViewById(R.id.supervisor_signature_button);


        back1=(ImageView) findViewById(R.id.back_page);

        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Signature2.this,Page_J.class);
                startActivity(i);
            }
        });


        supervisor_signature.setOnClickListener(onButtonClick);


        tech_signature = (Button) findViewById(R.id.tech_signature_button);
        tech_signature.setOnClickListener(onButtonClick);


        pd = new SpotsDialog(Signature2.this, R.style.Custom);

        T_signature = (ImageView) findViewById(R.id.tech_imageView);
        S_signature = (ImageView) findViewById(R.id.supervisor_imageView);


        S_signature = (ImageView) findViewById(R.id.supervisor_imageView);


        go_home = (Button) findViewById(R.id.go_home);

        go_home.setOnClickListener(this);

    }

    public void get_signature() {
        dl = new Dialog(Signature2.this);
        dl.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dl.setContentView(R.layout.capturesignature);
        dl.show();
        dl.setCancelable(false);
        Clear_bt = (Button) dl.findViewById(R.id.clear);
        Text_name = (TextView) dl.findViewById(R.id.sign_name);
        Text_name.setVisibility(View.GONE);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Text_date = (TextView) dl.findViewById(R.id.sign_date);
        Text_date.setText(currentDateTimeString);

        if (Sign_Check.equals("Tech")) {
//            Text_name.setText("Ashvin");

        } else if(Sign_Check.equals("msot")) {
//            Text_name.setText("Murugan");
        }
        else if(Sign_Check.equals("Sup")) {
//            Text_name.setText("Hari");
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
                Sign_Check = "Tech";
                get_signature();

            } else if (v == supervisor_signature) {

                Sign_Check = "Sup";
                get_signature();

            }


        }
    };

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.go_home){


            isInternetPresent = haveNetworkConnection(Signature2.this);
            if (isInternetPresent) {

pd.show();
                if (validation()) {
                    Log.e("GGGG","validation");
                    if (isInternetPresent) {


                        post_sum();
                    }
                    else{

                        pd.dismiss();

                        Toast.makeText(Signature2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }


                }
                else{
                    pd.dismiss();
                    Log.e("GGGG","else");

                    Toast.makeText(Signature2.this, "Please Fill All Details", Toast.LENGTH_SHORT).show();
                }
            }
            else {
                pd.dismiss();
                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

                final Dialog dialog = new Dialog(Signature2.this);
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
                            Toast.makeText(Signature2.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Toast.makeText(Signature2.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
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
            String Tech1 = "Tech";
            String Super1 = "Super";
            String Msot1 = "msot";
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
            if (Sign_Check.equals("Tech")) {
                T_signature.setVisibility(View.VISIBLE);

                T_signature.setImageBitmap(returnedBitmap);
            }
            if (Sign_Check.equals("Sup")) {
                S_signature.setVisibility(View.VISIBLE);
                S_signature.setImageBitmap(returnedBitmap);
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




        Log.e("FFFFF","getParams");
        Map<String, String> params = new HashMap<String, String>();

        params.put("sign_1",Post_coord);
        params.put("sign_2",Post_branch);
        params.put("main_id", MSOT_Main.Main_ID);

        Log.e("BNBNM","params = "+params);
        Log.e("VVVCC","params = "+Post_coord);
        Log.e("LLOOP","params = "+Post_branch);
        Log.e("QQWSS","params = "+MSOT_Main.Main_ID);



        VolleyDataRequester.withDefaultHttps( this )
                .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/page_sign2.php")
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("RRRRRT","response = "+response);
                            pd.dismiss();
                            Toast.makeText(getApplicationContext(),"Completed",Toast.LENGTH_SHORT).show();

                            Intent intent=new Intent(Signature2.this, Category_Type_Activity.class);
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

                                Intent intent=new Intent(Signature2.this, Category_Type_Activity.class);
                                startActivity(intent);

                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                            Intent intent=new Intent(Signature2.this, Category_Type_Activity.class);
                            startActivity(intent);
                        }


                    }
                } )
                .requestString();
    }

    @Override
    public void onBackPressed(){

        Intent i= new Intent(Signature2.this,Page_J.class);
        startActivity(i);

    }

    public boolean validation() {

        boolean check_point=false;

        try {
            bitmap_coordinator=((BitmapDrawable) S_signature.getDrawable()).getBitmap();
            bitmap_branch_manager=((BitmapDrawable) T_signature.getDrawable()).getBitmap();



            Post_coord=getStringImage(bitmap_coordinator);
            Post_branch=getStringImage(bitmap_branch_manager);




            try {

                if (Post_coord.length() != 0 && Post_branch.length() != 0) {
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

}
