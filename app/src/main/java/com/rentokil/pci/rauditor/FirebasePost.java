package com.rentokil.pci.rauditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import java.util.HashMap;
import java.util.Map;

public class FirebasePost extends AppCompatActivity {

    public static final String HTTPS_Posts = "https://rauditor.riflows.com/rauditor/Android/Firebase/posttest.php";

    Button sub;

    TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_post);
        sub=(Button) findViewById(R.id.subbtn);

        txtview=(TextView) findViewById(R.id.txtview);



        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                jsonRequestPostHttpsExample();
            }
        });





    }


    private void jsonRequestPostHttpsExample(){

        Map<String, String> params = new HashMap<String, String>();
        params.put("namei", "vinoth");
        params.put("passi", "1234");




        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(HTTPS_Posts)
                .setBody( params )
                .setMethod( VolleyDataRequester.Method.POST )
                .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            Log.e("DDDDDD",""+response);
//                            String data = response.getString( "message" );
                            txtview.setText( response);

                            Toast.makeText( FirebasePost.this, "HTTPS/POST"+response, Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtview.setText( error.getMessage() );
                    }
                } )
                .requestString();
    }
}
