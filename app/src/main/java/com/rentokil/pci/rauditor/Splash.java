package com.rentokil.pci.rauditor;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.PixelFormat;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

public class Splash extends AppCompatActivity {
        DatabaseHelper db;
        SQLiteDatabase sd;
        ContentValues cv3,cv4;
        Context mContext;
        JSONObject jsonObject_get,jsonObject_get_A;
        JSONArray jsonArray_get,jsonArray_get_A,jsonArray_get_B;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        db = new DatabaseHelper(Splash.this);
        sd = db.getReadableDatabase();
        mContext = this;
        cv3=new ContentValues(  );
        cv4=new ContentValues(  );
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity

                Log.e("AAAAAA 11", "Count " + db.getLogincount(sd));
                if (db.getLogincount(sd) == 0) {
                    Intent i = new Intent(Splash.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                } else {

                    Intent i = new Intent(Splash.this, Category_Type_Activity.class);
                    startActivity(i);
                    finish();
                }
            }
        },1000);

        try {
            if (db.getLogincount(sd) != 0) {
                if (isNetworkConnected()) {
                    get_audit_access(get_user_id());
                }
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }
    public String get_user_id(){
        Cursor c1;
        String User_id="";

        try {
            c1 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
            c1.moveToFirst();
            User_id = c1.getString(c1.getColumnIndex(db.USER_MAIL));
        } catch (Exception e) {

        }
        return User_id;
    }
    private void get_audit_access(String mail){

        sd.delete( db.AUDIT_ACCESS_TB, null, null );
        sd.delete( db.DASHBOARD_TB, null, null );

        Log.e("DDDDDD mail",mail+"");
        String  url="https://rauditor.riflows.com/rauditor/Android/ia_audit_access.php?user_mail="+mail;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            jsonObject_get_A=(JSONObject) response;
                            jsonArray_get_A=jsonObject_get_A.getJSONArray("result");
                            jsonArray_get_B=jsonObject_get_A.getJSONArray("result_dash");


                            //   id=new String[jsonArray_get.length()];

                            Log.e("DDDDDD Count A",jsonArray_get_A.length()+"");
                            Log.e("DDDDDD Count B",jsonArray_get_B+"");
                            Log.e("DDDDDD Count A",jsonArray_get_A+"");
                            if (jsonArray_get_A!=null) {
                                if (jsonArray_get_A.length()!=0) {
                                    for (int i = 0; i < jsonArray_get_A.length(); i++) {


                                        JSONObject c = jsonArray_get_A.getJSONObject(i);
                                        String audit_id = c.getString( "audit_id" );
                                        String audit_name = c.getString( "audit_name" );
                                        cv3.put(db.AUDIT_NAME, audit_name);
                                        //  Log.e("DDDDDD audit_name",audit_name+"\n");
                                        sd.insert(db.AUDIT_ACCESS_TB, null, cv3);
                                        cv3.clear();

                                    }
                                }
                            } if (jsonArray_get_B!=null) {
                                if (jsonArray_get_B.length()!=0) {
                                    for (int l = 0; l < jsonArray_get_B.length(); l++) {


                                        JSONObject c = jsonArray_get_B.getJSONObject(l);
                                        String Completed_Count = c.getString( "Completed_Count");
                                        String InCompleted_Count = c.getString( "InCompleted_Count");
                                        String audit_name = c.getString( "Audit_Type" );
                                        String Type = c.getString( "Type" );
                                        cv4.put(db.AUDIT_NAME, audit_name);
                                        cv4.put(db.STATE, Type);
                                        cv4.put(db.COMPLETED, Completed_Count);
                                        cv4.put(db.IN_PROGRESS, InCompleted_Count);
                                        //  Log.e("DDDDDD audit_name",audit_name+"\n");
                                        sd.insert(db.DASHBOARD_TB, null, cv4);
                                        cv4.clear();

                                    }
                                }
                            }

                            //  Toast.makeText( AIB_Title_1.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            Log.e("DDDDDD error",""+e.getMessage());
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText( Splash.this, error.getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                } )
                .requestJson();
    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService( Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
