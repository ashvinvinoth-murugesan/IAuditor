package com.rentokil.pci.rauditor.Image;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NextActivity extends AppCompatActivity {


    String[] fruits = {"Apple","Ashvin","Arjun","Aravinth","Abinash", "Banana", "Cherry", "Date", "Grape", "Kiwi", "Mango", "Pear"};

    AutoCompleteTextView autoCompleteTextView;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    EditText et_designation;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    List<String> responseList = new ArrayList<String>();
    List<String> responseList1 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        autoCompleteTextView=(AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        et_designation=(EditText) findViewById(R.id.et_designation);

        db = new DatabaseHelper(this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();



        et_designation.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                String get_names=autoCompleteTextView.getText().toString();
                Log.e("BBBB","nam="+get_names);
                get_tech_designation(get_names);
                return false;
            }


        });








        insert();

//
//
//        NextActivity.this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);




    }

    private void insert(){


        cv1.put(db.BRANCH, 72);
        cv1.put(db.COUNTRY, 4);
        sd.insert(db.USER_PROFILE_TABLE, null, cv1);

        get_tech_list();
    }

    private void get_tech_list(){


        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
        c5.moveToFirst();

        String get_branch = c5.getString(c5.getColumnIndex(db.BRANCH));
        String get_country = c5.getString(c5.getColumnIndex(db.COUNTRY));

        Log.e("FFQQ","Branch"+get_branch);
        Log.e("FFQQ","country"+get_country);


        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/tech_list.php?get_branch="+get_branch+"&get_country="+get_country ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("EEEGF","res="+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String service = c.getString( "employee_name" );
                                String type_work = c.getString( "employee_code" );
                                String msot_name = c.getString( "branch" );
                                String job_title = c.getString( "country" );
                                String time = c.getString( "occupation_desc" );

                                responseList.add(service);




                                ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                        (getApplicationContext(), android.R.layout.select_dialog_item,responseList );

                                //Getting the instance of AutoCompleteTextView

                                autoCompleteTextView.setThreshold(1);//will start working from first character
                                autoCompleteTextView.setAdapter(adapter);//setting the adapter data into the AutoCompleteTextView
                                autoCompleteTextView.setTextColor(Color.RED);
                                autoCompleteTextView.setDropDownWidth(getResources().getDisplayMetrics().widthPixels);



                                autoCompleteTextView.addTextChangedListener(new TextWatcher() {

                                    @Override
                                    public void afterTextChanged(Editable s) {}

                                    @Override
                                    public void beforeTextChanged(CharSequence s, int start,
                                                                  int count, int after) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence s, int start,
                                                              int before, int count) {


                                    }
                                });



                            }



//                            Toast.makeText( MSOT_Main.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e("FFFFF err HH",e.getMessage());
                        }


                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );


                        Log.e("FFFFF err",error.getMessage());
                    }
                } )
                .requestJson();
    }


    private void get_tech_designation(String name){





        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/tech_design.php?get_name="+name ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("EEEGFSSQS","res="+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );

                                String type_work = c.getString( "employee_code" );
                                String occup_des = c.getString( "occupation_desc" );

                              et_designation.setText(occup_des);

                            }

//                            Toast.makeText( MSOT_Main.this, "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();
                        }catch (Exception e){
                            e.printStackTrace();
                            Log.e("FFFFF err HH",e.getMessage());
                        }
                    }
                } )
                .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mobile.setText( error.getMessage() );


                        Log.e("FFFFF err",error.getMessage());
                    }
                } )
                .requestJson();
    }





}
