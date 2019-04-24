package com.rentokil.pci.rauditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

public class FirebaseGet extends AppCompatActivity {



    JSONArray jsonArray;
    String [] id;
    String [] title;
    String [] description;
    JSONObject jsonObject;

    ListView lst;

    public static final String HTTPS_JSONARRAY = "https://rauditor.riflows.com/rauditor/Android/Firebase/test.php"; //Free array test.

    private TextView tvResult;

    TextView names,mobile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_get);

        names=(TextView) findViewById(R.id.name);
        mobile=(TextView) findViewById(R.id.mobile);



        jsonRequestGetHttpsExample();
    }

    private void jsonRequestGetHttpsExample(){
        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(HTTPS_JSONARRAY)
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {


                            jsonObject=(JSONObject) response;
                            jsonArray=jsonObject.getJSONArray("result");


                            id=new String[jsonArray.length()];
                            title=new String[jsonArray.length()];
                            description=new String[jsonArray.length()];

                            for(int i=0;i<jsonArray.length();i++){

                                JSONObject ob = jsonArray.getJSONObject(i);

                                id[i] = ob.getString("id");
                                title[i] = ob.getString("name");
                                description[i] = ob.getString("mob");

                                names.setText(title[i]);
                                mobile.setText(description[i]);
                            }

                            Toast.makeText( FirebaseGet.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mobile.setText( error.getMessage() );
                    }
                } )
                .requestJson();
    }


}
