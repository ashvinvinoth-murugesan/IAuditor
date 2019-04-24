package com.rentokil.pci.rauditor.MSOT_NEW;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.Category_Type_Activity;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.LoginActivity;
import com.rentokil.pci.rauditor.MSOT.DataModel;
import com.rentokil.pci.rauditor.MSOT.List_Adapter;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.SIR_Title_1;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MSOT_LIST_1 extends AppCompatActivity {

   static ArrayList<DataModel> modelArrayList;
    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;
    Boolean isInternetPresent = false;
    ConnectivityManager cManager;
    NetworkInfo nInfo;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;
/*    ArrayList<DataModel> list;*/
    ArrayList<String> activity_list = new ArrayList<String>();
    ImageView img_tool12;

    private ListView lv;

    private List_Adapter list_adapter;
    private Button  btnnext;
    private  String[] animallist = new String[]{"Ladder use",

            "Fly Killer Dispenser",
            "Bait Stations",
            "Fly Traps",
            "Bird Proofing via Abseiling","Driving Service Vehicle","Riding Motorbike for Work"
                ,"Work at the Ceiling/ Roof Void","Entry into Roof Void","Insect Light Trap","Work near / around Electrical Cabinet",
                "Work inside Electrical Room","Fumigation","Air Freshener","Scenting"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msot__list);

        lv = (ListView) findViewById(R.id.lv);

        btnnext = (Button) findViewById(R.id.next);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

        db = new DatabaseHelper(MSOT_LIST_1.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();


        modelArrayList = getModel(false);
        list_adapter = new List_Adapter(this,modelArrayList);
        lv.setAdapter(list_adapter);





        Intent intent1=getIntent();
        String id=intent1.getStringExtra("key_id");
        Log.e("FFFFFF Sir main",""+id);
        if(id!=null){

            Log.e("RRRTT","qsr main ="+MSOT_Main.Main_ID);
            MSOT_Main.Main_ID=id;
            get_LIST_Data(id);
        }else {
            if (!MSOT_Main.Main_ID.equals("0")) {
                // String keyid=null;

                Log.e("RRRTT","qsr else = "+MSOT_Main.Main_ID);
                get_LIST_Data(MSOT_Main.Main_ID);
            }

        }





        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int cout= list_adapter.getIndex().size()-1;
               // sd.delete(db.ACTIVITIES_TABLE,"WHERE MAIN_ID="+db.getLogincount(sd),null);
                sd.execSQL("DELETE FROM " + db.ACTIVITIES_TABLE + " WHERE " + db.MAIN_ID + "='"+db.last_insert_id(sd)+"'");
                Log.e("GGGGGGGG",""+db.getALL_ACTIVITE(sd,db.last_insert_id(sd)).size());
                Log.e("GGGGGGGG lats1",""+db.last_insert_id(sd));
                for (int i = 0; i < list_adapter.getIndex().size(); i++) {


                    DataModel A_m = modelArrayList.get(Integer.parseInt(list_adapter.getIndex().get(i)));
                    Log.e("QQQQQQ OP",""+A_m.getAnimal());
                    cv1.put(db.MAIN_ID,db.last_insert_id(sd));
                    cv1.put(db.POSITION_ID,A_m.getAnimal());
                    Log.e(list_adapter.getIndex().size()+"\tPPPPPPPP",""+A_m.getAnimal());


                    if (check_active(db.last_insert_id(sd),A_m.getAnimal())==0) {


                        activity_list.add(A_m.getAnimal());



                        Log.e("LLLLL in",""+A_m.getAnimal());


//                         cv3.put(db.ACTIVITY_SELECT, A_m.getAnimal());
//                         sd.insert(db.ACTIVITIES_SELECTED_STRING, null, cv3);

                        sd.insert(db.ACTIVITIES_TABLE,null,cv1);
                        cv1.clear();
                    } else {
                        Log.e("LLLLLL",""+A_m.getAnimal());
                        cv1.clear();
                    }
                    cv1.clear();
                    if(cout==i)
                    {

                        submitres();
                        Intent intent = new Intent(MSOT_LIST_1.this, Page_A.class);
                        startActivity(intent);

                    }

                }
                /*Intent intent = new Intent(MSOT_LIST_1.this,MSOT_LIST_2.class);
                startActivity(intent);
       */     }
        });


    }

    @Override
    public void onBackPressed(){

        Intent i = new Intent(MSOT_LIST_1.this,MSOT_Main.class);
        startActivity(i);

    }
    private ArrayList<DataModel> getModel(boolean isSelect){
        ArrayList<DataModel>  list = new ArrayList<>();
        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
        c5.moveToFirst();

        String get_country = c5.getString(c5.getColumnIndex(db.COUNTRY));
        for(int i = 0; i < 15; i++){

            DataModel model = new DataModel();
            model.setSelected(isSelect);


            if(get_country.equalsIgnoreCase("India")){
                if(i==1){

                }else if(i==2){


                }

                else {
                    model.setAnimal(animallist[i]);

                    list.add(model);


                }
            }else {

                if(i==4){

                }
                else {
                    model.setAnimal(animallist[i]);
                    list.add(model);


                }
            }
            Log.e("c",""+animallist[i]);

        }
        return list;
    }
    public int check_active(int cus_id,String active_name){
        int count=0;
        String query="select * from "+db.ACTIVITIES_TABLE +" WHERE MAIN_ID="+cus_id+" AND POSITION_ID='"+active_name+"'";
        Log.e("QQQQQQ",""+query);
        Cursor cursor = sd.rawQuery(query, null);
        cursor.moveToFirst();
        count=cursor.getCount();
        Log.e("QQQQQQ",""+count);
        return count;
    }


    private void submitres() {


        sd.delete(db.MSOT_TABLE,null,null);


        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};

        Log.e("GGGG","sub");
        isInternetPresent = haveNetworkConnection(MSOT_LIST_1.this);
        if (isInternetPresent) {


//            Cursor c5;
//            c5 = sd.rawQuery("Select * from " + db.INSERTED_MSOT_MAIN, null);
//            c5.moveToFirst();
//
//            String get_main_id = c5.getString(c5.getColumnIndex(db.INSERT_MAIN_ID));



            Map<String, String> params = new HashMap<String, String>();
            params.put("count",""+activity_list.size());
            params.put("main_id", MSOT_Main.Main_ID);
            for (int i=0;i<activity_list.size();i++) {


                params.put("activity_list"+i, activity_list.get(i));


                Log.e("SSWWQ", "params" + params);
            }


            VolleyDataRequester.withDefaultHttps( this )
                    .setUrl("https://rauditor.riflows.com/rauditor/Android/MSOT/msot_list_1.php")
                    .setBody( params )
                    .setMethod( VolleyDataRequester.Method.POST )
                    .setStringResponseListener( new VolleyDataRequester.StringResponseListener() {
                        @Override
                        public void onResponse(String response) {

                            try {

                                get_list_data();
                                Log.e("CCSS","e1"+response);

//                                cv1.put(db.STATUS, "99");
//                                sd.insert(db.MSOT_TABLE, null, cv1);


                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } )
                    .setResponseErrorListener( new VolleyDataRequester.ResponseErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("DDDDDD","error  = "+error.getMessage());


                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    } )
                    .requestString();




        }
        else{


            final Dialog dialog = new Dialog(this);
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
                        Toast.makeText(MSOT_LIST_1.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(MSOT_LIST_1.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();


        }



    }

    private void get_list_data(){

        sd.delete(db.MSOT_QUESTION_ID_TABLE,null,null);

        Log.e("FFFDD","MAIN = "+MSOT_Main.Main_ID);

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_methods/get_q_select_list.php?main_id=" + MSOT_Main.Main_ID;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            Log.e("SSSSWW","response = "+response);
                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String question_id = c.getString( "question_id" );

                                Log.e("GGGTR","qus id = "+question_id);



                                Cursor c5;
                                c5 = sd.rawQuery("Select * from " + db.INSERTED_MSOT_MAIN, null);
                                c5.moveToFirst();

                                String get_main_id = c5.getString(c5.getColumnIndex(db.INSERT_MAIN_ID));

                                cv2.put(db.MAIN_ID, get_main_id);
                                cv2.put(db.QUESTION_ID, question_id);
                                if (check_entry(MSOT_Main.Main_ID,question_id)==0) {

                                    Log.e("XXXXS","ques="+question_id);
//                                    Log.e("XXXXS","main = "+get_main_id);
                                    sd.insert(db.MSOT_QUESTION_ID_TABLE, null, cv2);
                                } else {

                                    Log.e("XXXXS","else=="+question_id);
                                }




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

                        Log.e("SSSSXXCA","error  = "+error.getMessage());
                    }
                } )
                .requestJson();
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
    protected  int check_entry(String main_id,String qus_id){

        Cursor c6;
        c6 = sd.rawQuery("Select * from " + db.MSOT_QUESTION_ID_TABLE +" where MAIN_ID = '"+main_id+"' and QUESTION_ID = '"+qus_id+"' ", null);
        c6.moveToFirst();

        return c6.getCount();


    }

    protected  int get_all(){

        Cursor c6;
        c6 = sd.rawQuery("Select * from " + db.MSOT_QUESTION_ID_TABLE, null);
        c6.moveToFirst();
        for(int j=0;j<c6.getCount();j++){
            Log.e("SSSSS\t",c6.getString(c6.getColumnIndex(db.QUESTION_ID)));
            c6.moveToNext();
        }
        return c6.getCount();
    }


    private void get_LIST_Data(String key_id){

        Log.e("XXXXV","3rd ="+key_id);
        Log.e("KKKJJ",key_id);
        if(key_id!=null){
            MSOT_Main.Main_ID=key_id;

            Log.e("LLLLKI",MSOT_Main.Main_ID);
        }

        String  url="https://rauditor.riflows.com/rauditor/Android/MSOT/get_msot_list_select.php?main_id="+MSOT_Main.Main_ID ;

        VolleyDataRequester.withDefaultHttps( this )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            ArrayList<DataModel>  list = new ArrayList<>();
                            DataModel model = new DataModel();
                            Log.e("GGGGG","response = "+response);

                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String gt_main = c.getString( "main_id" );
                                String act_id = c.getString( "act_id" );
                                String act_name = c.getString( "act_name");
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
