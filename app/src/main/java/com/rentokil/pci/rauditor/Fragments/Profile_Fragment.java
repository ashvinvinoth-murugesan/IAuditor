package com.rentokil.pci.rauditor.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.Category_Type_Activity;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.LoginActivity;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import org.json.JSONArray;
import org.json.JSONObject;

public class Profile_Fragment extends Fragment {

    private View view;
    TextView Emp_Code;
    String myJSON;
    JSONArray json_arr_cus_result;
    TextView Emp_Name,Branch,Country,Divition,Email,country_txt;
    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ImageView banar1;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    private GoogleApiClient mGoogleApiClient;
    Button button123;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.activity_profile, container, false);
        Emp_Name=(TextView)view.findViewById( R.id.name_et);
        Emp_Code=(TextView) view.findViewById( R.id.emp_code_et);

        Email=(TextView)view.findViewById( R.id.email_et);
        Branch=(TextView)view.findViewById( R.id.branch_et);
        country_txt=(TextView)view.findViewById( R.id.country_txt);
        button123=(Button) view.findViewById(R.id.button123);
        banar1=(ImageView) view.findViewById(R.id.banar1);




//        Glide.with(getActivity()).load(LoginActivity.personPhotoUrl)
//                .thumbnail(0.5f)
//                .crossFade()
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(banar1);


        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();





        button123.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alert_nav_logout();

            }
        });

        cManager = (ConnectivityManager) getActivity().getSystemService(context.CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        if (nInfo != null && nInfo.isConnected()) {


        } else {

            final Dialog dialog = new Dialog(getActivity());
            dialog.setContentView(R.layout.alertbox);
            dialog.setCancelable(false);


            Button btnreff =(Button) dialog.findViewById(R.id.btnrefresh);

            btnreff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    nInfo=cManager.getActiveNetworkInfo();
                    if (nInfo != null && nInfo.isConnected()) {

                        Intent intent = getActivity().getIntent();
                        getActivity().finish();
                        startActivity(intent);

                    }
                    else{
                        Toast.makeText(getActivity(), "No Internet Access ", Toast.LENGTH_SHORT).show();
                    }

                }
            });



            //Toast.makeText(getActivity(), "No Internet Access ", Toast.LENGTH_LONG).show();
            dialog.show();
        }

        // Country=(MyTextView)view.findViewById( R.id.branch_et);
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }



    public void alert_nav_logout() {
        Button yes, no;
        final Dialog dl = new Dialog(getActivity());

        dl.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dl.setContentView(R.layout.logout);
        yes = (Button) dl.findViewById(R.id.yes_edit);
        no = (Button) dl.findViewById(R.id.no_edit);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                // ...


                                sd.delete(db.MSOT_MAIN_COUNTRY_TABLE,null,null);
                                db.deleteAll();


                                Toast.makeText(getActivity(),"Logged Out",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getActivity(), LoginActivity.class);
                                startActivity(i);
                                getActivity().finish();


                            }
                        });
        }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dl.dismiss();

            }
        });
        dl.show();

    }

    @Override
    public void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }


    private void getData(){

        String  url="https://rauditor.riflows.com/rauditor/Android/ia_profile.php?mail_id=" + Category_Type_Activity.User_Login_Mail + "" ;

        VolleyDataRequester.withDefaultHttps( getActivity() )
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
                                String country = c.getString( "country" );



                                Emp_Name.setText(user_name);
                                Branch.setText( branch );
                                country_txt.setText( country );
                                Email.setText(Category_Type_Activity.User_Login_Mail);

//
//                                cv1.put(db.BRANCH, branch);
//                                cv1.put(db.COUNTRY, country);
//                                sd.insert(db.USER_PROFILE_TABLE, null, cv1);


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







//    public void getData() {
//
//        class GetDataJSON extends AsyncTask<String, Void, String> {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                loading = new ProgressDialog( getActivity(), R.style.MyDialog );
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
//                HttpPost httppost = new HttpPost( "http://rauditor.riflows.com/rauditor/Android/ia_profile.php?mail_id=" + Category_Type_Activity.User_Login_Mail + "" );
//
//                // Depends on your web service
//                httppost.setHeader( "Content-type", "application/json" );
//
//                InputStream inputStream = null;
//                String result = null;
//                try {
//                    HttpResponse response = httpclient.execute( httppost );
//                    HttpEntity entity = response.getEntity();
//
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
//                JSONObject c = json_arr_cus_result.getJSONObject( i );
//                String id = c.getString( "id" );
//                String user_name = c.getString( "user_name" );
//                String branch = c.getString( "branch" );
//                String manager_mail = c.getString( "manager_mail" );
//
//                Line_Name.setText(manager_mail);
//                Emp_Name.setText(user_name);
//                Branch.setText( branch );
//                Email.setText(Category_Type_Activity.User_Login_Mail);
//
//
//
//
//              }
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
}
