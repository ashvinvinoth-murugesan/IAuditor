package com.rentokil.pci.rauditor;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {


    TextView sgnin;
    // CallbackManager callbackManager;
    TextView txtEmail, txtName;
    //LoginButton loginButton;

    DatabaseHelper db;
    ContentValues cv1,cv2,cv3,cv4,cv_que,cv_act,cv_act_qus;

    SQLiteDatabase sd;



    JSONObject jsonObject_Main,jsonObject_get;
    JSONArray jsonArray_get,jsonArray_get_B,jsonArray_get_act,jsonArray_get_qus,jsonArray_get_act_qus;

    ConnectivityManager cManager;
    NetworkInfo nInfo;

    public static final int RequestPermissionCode = 1;

    //G-SignIN Result Code

    private static final int RC_SIGN_IN = 007;

    private GoogleApiClient mGoogleApiClient;

    RelativeLayout header;

    private SignInButton btnSignIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo = cManager.getActiveNetworkInfo();




        db = new DatabaseHelper(LoginActivity.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();
        cv4 = new ContentValues();
        cv_act = new ContentValues();
        cv_act_qus = new ContentValues();
        cv_que = new ContentValues();
        header = (RelativeLayout) findViewById(R.id.header);

        sgnin = (TextView) findViewById(R.id.signin1);

        btnSignIn = findViewById(R.id.sign_in_button);

        sgnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent i = new Intent(LoginActivity.this, IPM_Title_1.class);
                startActivity(i);
            }
        });
        get_full_mas_detail();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
                if (nInfo[0] != null && nInfo[0].isConnected()) {


                } else {
                    final Dialog dialog = new Dialog(LoginActivity.this);
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
                                Toast.makeText(LoginActivity.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    Toast.makeText(LoginActivity.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
                    dialog.show();
                }


                signIn();
            }
        });
        EnableRuntimePermission();

    }



    public void EnableRuntimePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) && ActivityCompat.shouldShowRequestPermissionRationale(LoginActivity.this,
                Manifest.permission.GET_ACCOUNTS)) {

            Toast.makeText(LoginActivity.this, "STORAGE and CONTACTS permission allows us to Access the app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(LoginActivity.this, new String[]{
                    Manifest.permission.CAMERA, Manifest.permission.GET_ACCOUNTS, Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, RequestPermissionCode);

        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {

        Log.e("AAAAA", "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {

            GoogleSignInAccount acct = result.getSignInAccount();

            String personName = acct.getDisplayName();
            String email = acct.getEmail();

//            Glide.with(getApplicationContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imageView4);




          /*  txtName.setText(personName);
            txtEmail.setText(email);*/
        //  Toast.makeText( getApplicationContext(),personName+"\t\t"+email,Toast.LENGTH_SHORT).show();
        Log.e("AAAAAAc 22", "Count " + db.getLogincount(sd));
        if (email.endsWith("@rentokil-pci.com") || email.endsWith("@rentokil-initial.com")) {


            sd.delete(db.MSOT_MAIN_COUNTRY_TABLE,null,null);

            Log.e("DDDDD", "true");

            get_country_Data(email);
            get_login_details(email);

//            personPhotoUrl = acct.getPhotoUrl().toString();

//            Intent intent = new Intent(LoginActivity.this, Category_Type_Activity.class);
//            startActivity(intent);
//            finish();

        } else {
            Log.e("DDDDD", "false");
            signOut();
        }
        //  btnSignIn.setVisibility(View.GONE);
        // btnSignOut.setVisibility(View.VISIBLE); no use

    } else

    {

        Toast.makeText(getApplicationContext(), "Authentication Fails", Toast.LENGTH_LONG).show();

    }

}

    private void get_login_details(final String mail) {
        sd.delete( db.AUDIT_ACCESS_TB, null, null );

        String urls = "https://rauditor.riflows.com/rauditor/Android/rAuditorlogin.php?user_mail="+mail;

        VolleyDataRequester.withDefaultHttps(this)
                .setUrl(urls.replace(" ", "%20"))
                .setJsonResponseListener(new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            jsonObject_get = (JSONObject) response;
                            jsonArray_get = jsonObject_get.getJSONArray("result");
                            jsonArray_get_B = jsonObject_get.getJSONArray("result_access");
                            Log.e("GGGGG", "e1" + jsonArray_get.length());
                            Log.e("GGGGG", "e1" + jsonArray_get_B.length());
                            if(jsonArray_get_B.length()!=0){
                               // Toast.makeText(getApplicationContext(), "Logged In Successfully", Toast.LENGTH_SHORT).show();

                                for (int p = 0; p < jsonArray_get_B.length(); p++) {

                                    JSONObject c = jsonArray_get_B.getJSONObject(p);
                                    String audit_id = c.getString( "audit_id" );
                                    String audit_name = c.getString( "audit_name" );
                                    Log.e("GGGGG", "e2\t" +audit_name);
                                    cv4.put(db.AUDIT_NAME, audit_name);
                                    sd.insert(db.AUDIT_ACCESS_TB, null, cv4);
                                    cv4.clear();

                                }
                            }

                            if(jsonArray_get.length()==1){
                                Toast.makeText(getApplicationContext(), "Logged In Successfully", Toast.LENGTH_SHORT).show();

                                for (int i = 0; i < jsonArray_get.length(); i++) {

                                    Log.e("GGGGG", "e2");
                                    JSONObject c = jsonArray_get.getJSONObject(i);
                                    String get_usermail = c.getString("user_mail");
                                    String get_username = c.getString("user_name");
                                    String get_msot_access = c.getString("msot_access");
                                    String get_country = c.getString("country");
                                    String get_branch = c.getString("branch");




                                    Log.e("GGGGG", "e3");

                                    cv1.put(db.USER_NAME, get_username);
                                    cv1.put(db.USER_MAIL, get_usermail);
                                    cv1.put(db.COUNT, get_country);
                                    cv1.put(db.BRANCH, get_branch);
                                    cv1.put(db.STATUS, get_msot_access);
                                    sd.insert(db.USER_PROFILE_TABLE, null, cv1);
                                    cv1.clear();
                                    Intent intent = new Intent(LoginActivity.this, Category_Type_Activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                            else{

                                Log.e("GGGGG", "elsepart");

                                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                                        new ResultCallback<Status>() {
                                            @Override
                                            public void onResult(Status status) {
                                                Snackbar snack;
                                                snack = Snackbar.make(header, "Your Mail ID is Not Registered With Us", Snackbar.LENGTH_LONG);
                                                View view = snack.getView();
                                                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) view.getLayoutParams();
                                                params.gravity = Gravity.BOTTOM;
                                                view.setLayoutParams(params);
                                                snack.show();

                                                sd.delete(db.USER_PROFILE_TABLE,null,null);

                                            }
                                        });
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setResponseErrorListener(new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );
                    }
                })
                .requestJson();
    }
    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                Toast.makeText(LoginActivity.this, "Please select Rentokil ID", Toast.LENGTH_SHORT).show();


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
      //  callbackManager.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

}
    // set the facebook credential to textview
    private void setProfileToView(JSONObject jsonObject) {
        try {
            txtName.setText(jsonObject.getString("name"));
            txtEmail.setText(jsonObject.getString("email"));



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {



                } else {

                }
                break;
        }
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText( getApplicationContext(),"yyy"+connectionResult,Toast.LENGTH_SHORT ).show();

    }

    private void get_country_Data(String mail){

        String  url="https://rauditor.riflows.com/rauditor/Android/ia_profile.php?mail_id=" + mail + "" ;

        VolleyDataRequester.withDefaultHttps( this)
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("CCCCSSA","re"+response);
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");
                            //   id=new String[jsonArray_get.length()];
                            for (int i = 0; i < jsonArray_get.length(); i++) {
                                
                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String user_name = c.getString( "user_name" );
                                String branch = c.getString( "branch" );
                                String country = c.getString( "country" );
                                String job_title = c.getString( "job_title" );

                                Log.e("BBBG",""+branch);
                                Log.e("BBBG",""+country);



                                cv2.put(db.BRANCH, branch);
                                cv2.put(db.COUNTRY, country);
                                sd.insert(db.MSOT_MAIN_COUNTRY_TABLE, null, cv2);

                                cv3.put(db.AUDIT_BY_NAME, user_name);
                                cv3.put(db.REP_BRANCH, branch);
                                cv3.put(db.JOB_TITLE, job_title);
                                sd.insert(db.BRANCH_AUDITBY_TABLE, null, cv3);



                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
                                c5.moveToFirst();

                                String get_brancg= c5.getString(c5.getColumnIndex(db.BRANCH));
                                String get_coun = c5.getString(c5.getColumnIndex(db.COUNTRY));


                                Log.e("NNNBV","branch "+get_brancg);
                                Log.e("NNNBV","country "+get_coun);

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
    private void get_full_mas_detail() {
        sd.delete( db.AUDIT_ACCESS_TB, null, null );

        String urls = "https://rauditor.riflows.com/rauditor/Android/get_msot_master.php";

        VolleyDataRequester.withDefaultHttps(this)
                .setUrl(urls.replace(" ", "%20"))
                .setJsonResponseListener(new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            jsonObject_get = (JSONObject) response;
                            jsonArray_get_qus = jsonObject_get.getJSONArray("result_qus");
                            jsonArray_get_act = jsonObject_get.getJSONArray("result_act");
                            jsonArray_get_act_qus = jsonObject_get.getJSONArray("result_act_qus");

                            if(jsonArray_get_qus.length()!=0){
                                for (int p = 0; p < jsonArray_get_qus.length(); p++) {

                                    JSONObject c = jsonArray_get_qus.getJSONObject(p);
                                    String audit_id = c.getString( "id" );
                                    String activity_name = c.getString( "question_id" );
                             //     Log.e("GGGGG", "e2\t" +audit_name);
                                    cv_que.put(db.MAIN_ID, audit_id);
                                    cv_que.put(db.QUESTION_ID, activity_name);
                                    sd.insert(db.MSOT_QUESTION_MAS_DB, null, cv_que);
                                    cv_que.clear();

                                }
                            }

                            if(jsonArray_get_act.length()==1){
                                SimpleDateFormat df = new SimpleDateFormat( "yyyy-mm-dd" );
                                String formatted = df.format( new Date() );
                                for (int i = 0; i < jsonArray_get_act.length(); i++) {


                                    JSONObject c = jsonArray_get_act.getJSONObject(i);
                                    String id = c.getString("id");
                                    String activity_name = c.getString("activity_name");
                                    cv_act.put(db.MAIN_ID, id);
                                    cv_act.put(db.ACTIVITY_NAME, activity_name);
                                    cv_act.put(db.DATE, formatted);
                                    sd.insert(db.MSOT_ACTIVITY_MAS_DB, null, cv_act);
                                    cv_act.clear();

                                }
                            }if(jsonArray_get_act_qus.length()==1){
                                for (int i = 0; i < jsonArray_get_act_qus.length(); i++) {

                                    JSONObject c = jsonArray_get_act.getJSONObject(i);
                                    String id = c.getString("id");
                                    String act_id = c.getString("act_id");
                                    String qus_id = c.getString("qus_id");
                                    cv_act_qus.put(db.MAIN_ID, id);
                                    cv_act_qus.put(db.ACTIVITY_ID, act_id);
                                    cv_act_qus.put(db.QUESTION_ID, qus_id);
                                    sd.insert(db.MSOT_ACT_QUS_DB, null, cv_act_qus);
                                    cv_act_qus.clear();

                                }
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setResponseErrorListener(new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );
                    }
                })
                .requestJson();
    }
}
