package com.rentokil.pci.rauditor.Fragments;


import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.AIB_Title_1;
import com.rentokil.pci.rauditor.Adapter.ListViewAdapter_Completed;
import com.rentokil.pci.rauditor.Adapter.List_Item_Methodes;
import com.rentokil.pci.rauditor.Category_Type_Activity;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.IPM_Title_1;
import com.rentokil.pci.rauditor.PMCA_Title_1;
import com.rentokil.pci.rauditor.QSR_Title_1;
import com.rentokil.pci.rauditor.R;
import com.rentokil.pci.rauditor.SIR_Title_1;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;




public class Completed_Fragment extends Fragment {


    private View view;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    String myJSON;
    JSONArray json_arr_cus_result = null;
    ArrayList<List_Item_Methodes> arraylist;
    ListViewAdapter_Completed adapter;
    ListView List_View;
    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;



    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;
    ContentValues cv3;
    ContentValues cv4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //   super.onCreate(savedInstanceState);
        //  setContentView(R.layout.activity_expandable_list_view);
        //  return inflater.inflate(R.layout.activity_expandable_list_view, container, false);
        view = inflater.inflate(R.layout.content_main, container, false);
        List_View = (ListView) view.findViewById( R.id.listview_main );

        db = new DatabaseHelper(getActivity());
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();
        cv3 = new ContentValues();
        cv4 = new ContentValues();





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


        arraylist = new ArrayList<List_Item_Methodes>();
        //if(isNetworkConnected()){
        try {
            getData();
        } catch (Exception e) {
            Log.e("JJJJK Erro",""+e.getMessage());
            e.printStackTrace();
        }
        //}
        return view;

    }
/*    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager)getActivity().getSystemService( Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetwork() != null;
    }*/

    private void getData(){



        String  url="https://rauditor.riflows.com/rauditor/Android/ia_completed.php?mail_id=" + Category_Type_Activity.User_Login_Mail + "" ;

        VolleyDataRequester.withDefaultHttps( getActivity() )
                .setUrl(url.replace(" ","%20"))
                .setJsonResponseListener( new VolleyDataRequester.JsonResponseListener() {
                    @Override
                    public void onResponse(JSONObject response) {

                        List_Item_Methodes wp;

                        try {


                            jsonObject_get=(JSONObject) response;
                            jsonArray_get=jsonObject_get.getJSONArray("result");


                            //   id=new String[jsonArray_get.length()];


                            for (int i = 0; i < jsonArray_get.length(); i++) {


                                JSONObject c = jsonArray_get.getJSONObject(i);
                                String id = c.getString( "id" );
                                String cus_name = c.getString( "cus_name" );
                                String audi_name = c.getString( "audi_name" );
                                String audi_date = c.getString( "audi_date" );
                                String doc_no = c.getString( "doc_no" );
                                String status_view = c.getString( "status" );
                                int id_1=Integer.parseInt( id );

                                wp = new List_Item_Methodes( id_1, audi_name, cus_name ,audi_date,doc_no,status_view);
                                arraylist.add( wp );
                            }
                            //    Log.e("InProcess ","bb\t"+arraylist.size());
                            adapter = new ListViewAdapter_Completed( getActivity(), arraylist );
                            // Binds the Adapter to the ListView
                            List_View.setAdapter( adapter );
//                            Toast.makeText( getActivity(), "HTTPS/GET, JsonRequest successfully.", Toast.LENGTH_SHORT ).show();

                            List_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedItem = (String) parent.getItemAtPosition(position);
                                    List_Item_Methodes methods=arraylist.get(position);
                                    //  Toast.makeText(getContext(),"Success"+methodes.getCus_name(),Toast.LENGTH_SHORT).show();
                                    Log.e("AAAAAA","Audi name\t"+methods.getAudi_name());
                                    String Auditor_Tite=methods.getAudi_name();
                                    String get_document=methods.getDoc_no();

                                    if(Auditor_Tite.equalsIgnoreCase("SIR")){

                                        sd.delete(db.USER_COMPLETE_STATUS,null,null);

                                        Intent intent=null;
                                        if(methods.getStatus().equalsIgnoreCase("Completed")&get_document.equalsIgnoreCase(methods.getDoc_no())){


                                            cv3.put(db.COMPLETE_STATUS, "2");
                                            sd.insert(db.USER_COMPLETE_STATUS, null, cv3);
                                            Cursor c10;
                                            c10 = sd.rawQuery("Select * from " + db.USER_COMPLETE_STATUS, null);
                                            c10.moveToFirst();


                                            intent=new Intent(getActivity(),SIR_Title_1.class);
                                        }

                                        Log.e("GGGG","TTTY = "+methods.getId());
                                        intent.putExtra("key_id",""+""+methods.getId());
                                        startActivity(intent);


                                    }
                                    if(Auditor_Tite.equalsIgnoreCase("PMCA")){

                                        sd.delete(db.PMCA_COMPLETE_STATUS_TABLE,null,null);
                                        Intent intent=null;
                                        if(methods.getStatus().equalsIgnoreCase("Completed")&get_document.equalsIgnoreCase(methods.getDoc_no())){

                                            cv3.put(db.PMCA_COMPLETE_STATUS, "2");
                                            sd.insert(db.PMCA_COMPLETE_STATUS_TABLE, null, cv3);
                                            Cursor c10;
                                            c10 = sd.rawQuery("Select * from " + db.PMCA_COMPLETE_STATUS_TABLE, null);
                                            c10.moveToFirst();


                                            intent=new Intent(getActivity(),PMCA_Title_1.class);
                                        }
                                        intent.putExtra("key_id",""+""+methods.getId());
                                        startActivity(intent);


                                    }

                                    if(Auditor_Tite.equalsIgnoreCase("QSR")){

                                        sd.delete(db.QSR_COMPLETE_STATUS_TABLE,null,null);
                                        sd.delete(db.CHECK_STATUS_TABLE,null,null);

                                        Intent intent=null;
                                        if(methods.getStatus().equalsIgnoreCase("Completed")&get_document.equalsIgnoreCase(methods.getDoc_no())){
                                            cv3.put(db.QSR_COMPLETE_STATUS, "2");
                                            sd.insert(db.QSR_COMPLETE_STATUS_TABLE, null, cv3);


                                            cv4.put(db.STATUS, "101");
                                            sd.insert(db.CHECK_STATUS_TABLE, null, cv4);

                                            intent=new Intent(getActivity(),QSR_Title_1.class);
                                        }
                                        intent.putExtra("key_id",""+""+methods.getId());
                                        startActivity(intent);




                                    }

                                    if(Auditor_Tite.equalsIgnoreCase("IPM")){

                                        sd.delete(db.IPM_COMPLETE_STATUS_TABLE,null,null);
                                        Intent intent=null;
                                        if(methods.getStatus().equalsIgnoreCase("Completed")&get_document.equalsIgnoreCase(methods.getDoc_no())){


                                            cv3.put(db.IPM_COMPLETE_STATUS, "2");
                                            sd.insert(db.IPM_COMPLETE_STATUS_TABLE, null, cv3);
                                            Cursor c10;
                                            c10 = sd.rawQuery("Select * from " + db.IPM_COMPLETE_STATUS_TABLE, null);
                                            c10.moveToFirst();

                                            intent=new Intent(getActivity(),IPM_Title_1.class);
                                        }
                                        intent.putExtra("key_id",""+""+methods.getId());
                                        startActivity(intent);


                                    }

                                    if(Auditor_Tite.equalsIgnoreCase("AIB")){

                                        sd.delete(db.AIB_COMPLETE_STATUS_TABLE,null,null);
                                        Intent intent=null;

                                        if(methods.getStatus().equalsIgnoreCase("Completed")&get_document.equalsIgnoreCase(methods.getDoc_no())){

                                            cv3.put(db.AIB_COMPLETE_STATUS, "2");
                                            sd.insert(db.AIB_COMPLETE_STATUS_TABLE, null, cv3);
                                            Cursor c10;
                                            c10 = sd.rawQuery("Select * from " + db.AIB_COMPLETE_STATUS_TABLE, null);
                                            c10.moveToFirst();

                                            Log.e("DFDFSD2","db = "+c10.getString(c10.getColumnIndex(db.AIB_COMPLETE_STATUS)));


                                            intent=new Intent(getActivity(),AIB_Title_1.class);
                                        }
                                        intent.putExtra("key_id",""+""+methods.getId());
                                        startActivity(intent);


                                    }
                                }
                            });
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
//
//
//                loading = new ProgressDialog( getActivity(), R.style.MyDialog );
//                loading.show();
//                loading.setTitle( "Please Wait" );
//                loading.setMessage( "Data Loading..." );
//             //   loading.setProgressDrawable( getActivity().getResources().getDrawable( R.drawable.add_white_icon ) );
//                super.onPreExecute();
//            }
//
//            @Override
//            protected String doInBackground(String... params) {
//              //  user_profile_date();
//                Log.e("TTTTTT",""+ Category_Type_Activity.User_Login_Mail);
//
//                DefaultHttpClient httpclient = new DefaultHttpClient( new BasicHttpParams() );
//                HttpPost httppost = new HttpPost( "http://rauditor.riflows.com/rauditor/Android/ia_completed.php?mail_id=" + Category_Type_Activity.User_Login_Mail + "" );
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
//             Log.e( "JJJJJJ","Compl\t"+myJSON );
//            JSONObject jsonObj = new JSONObject( myJSON );
//            json_arr_cus_result = jsonObj.getJSONArray( "result" );
//
//            for (int i = 0; i < json_arr_cus_result.length(); i++) {
//                JSONObject c = json_arr_cus_result.getJSONObject( i );
//                String id = c.getString( "id" );
//                String cus_name = c.getString( "cus_name" );
//                String audi_name = c.getString( "audi_name" );
//                String audi_date = c.getString( "audi_date" );
//                String doc_no = c.getString( "doc_no" );
//                int id_1=Integer.parseInt( id );
//
//                wp = new List_Item_Methodes( id_1, audi_name, cus_name ,audi_date,doc_no);
//                arraylist.add( wp );
//            }
//            //    Log.e("InProcess ","bb\t"+arraylist.size());
//            adapter = new ListViewAdapter_Completed( getActivity(), arraylist );
//            // Binds the Adapter to the ListView
//            List_View.setAdapter( adapter );
//        } catch (JSONException e) {
//            Log.e( "UUUU", "error iii\t" + e.getMessage() );
//            e.printStackTrace();
//        } catch (NullPointerException e1) {
//            Log.e( "UUUU", "error rrr\t" + e1.getMessage() );
//            e1.printStackTrace();
//        }

//    }
}
