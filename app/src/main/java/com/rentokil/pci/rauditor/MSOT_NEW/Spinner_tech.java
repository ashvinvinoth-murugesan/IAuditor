package com.rentokil.pci.rauditor.MSOT_NEW;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Spinner_tech extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] options;


    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
    List<String> responseList = new ArrayList<String>();
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_tech);

        db = new DatabaseHelper(this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();



         spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        get_tech_list();
        // Creating ArrayAdapter using the string array and default spinner layout
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.Animals, android.R.layout.simple_spinner_item);
//        // Specify layout to be used when list of choices appears
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Applying the adapter to our spinner
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(this);
//        options = Spinner_tech.this.getResources().getStringArray(R.array.Animals);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void get_tech_list(){


        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
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

                                spinner.setPrompt("Select Technician");
                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinner.setAdapter(adapter);





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
