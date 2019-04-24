package com.rentokil.pci.rauditor;
import android.Manifest;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.Fragments.About_Us_Fragment;
import com.rentokil.pci.rauditor.Fragments.Completed_Fragment;
import com.rentokil.pci.rauditor.Fragments.Dashboard;
import com.rentokil.pci.rauditor.Fragments.Profile_Fragment;
import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_Main;
import com.rentokil.pci.rauditor.Fragments.In_Progress_Fragment;
import com.rentokil.pci.rauditor.volley.VolleyDataRequester;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.rentokil.pci.rauditor.MSOT_NEW.MSOT_Main.Main_ID;


public class Category_Type_Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;

    //   private DragTopLayout dragLayout;


    JSONObject jsonObject_get,jsonObject_get_A;
    JSONArray jsonArray_get,jsonArray_get_A;

    private ViewPager viewPager;
    Typeface mTypeface;
    NavigationView navigationView;
    SQLiteDatabase sd;
    public static String User_Login_Mail="";
    DatabaseHelper db;
    ContentValues cv;
    ContentValues cv2;
    ContentValues cv3;
    ContentValues cv4;
    ContentValues cv5;
    ContentValues cv6;
    ContentValues cv7;

    ConnectivityManager cManager;
    NetworkInfo nInfo;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setToolbar();

      tabLayout = (TabLayout)findViewById(R.id.tab_layout);
        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        SIR_Title_1.Main_ID="0";
        PMCA_Title_1.Main_ID="0";
        QSR_Title_1.Main_ID="0";
        IPM_Title_1.Main_ID="0";
        AIB_Title_1.Main_ID="0";
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {




        } else {
            final Dialog dialog = new Dialog(Category_Type_Activity.this);
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
                        Toast.makeText(Category_Type_Activity.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(Category_Type_Activity.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }




        db=new DatabaseHelper( Category_Type_Activity.this );
         cv=new ContentValues(  );
         cv2=new ContentValues(  );
         cv3=new ContentValues(  );
         cv4=new ContentValues(  );
         cv5=new ContentValues(  );
         cv6=new ContentValues(  );
         cv7=new ContentValues(  );
         sd=db.getReadableDatabase();
         if(db.getLogincount( sd )==0){
             cv2.put(db.STATUS, "99");
             sd.insert(db.MSOT_TABLE, null, cv2);

             cv4.put(db.STATUS, "100");
             sd.insert(db.CHECK_STATUS_TABLE, null, cv4);
         }else {
             Log.e("UUU","VCVC else");
             User_Login_Mail=get_mail();

             cv2.put(db.STATUS, "99");
             sd.insert(db.MSOT_TABLE, null, cv2);

             cv4.put(db.STATUS, "100");
             sd.insert(db.CHECK_STATUS_TABLE, null, cv4);
         //    get_audit_access(get_user_id());
         }

        viewPager = (ViewPager) findViewById(R.id.pager);
        try {
            new Load_Layout().onPreExecute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Main_ID="0";
            sd.delete(db.INSERTED_MSOT_MAIN,null,null);


        } catch (Exception e) {
            e.printStackTrace();
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(viewPager);

        if (ContextCompat.checkSelfPermission(Category_Type_Activity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)

                != PackageManager.PERMISSION_GRANTED

                && ActivityCompat.checkSelfPermission(Category_Type_Activity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)

                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Category_Type_Activity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        }else {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

         navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(Category_Type_Activity.this);
        Menu menu =navigationView.getMenu();

        if (db.get_check_audit(sd,"MSOT")==0) {
            MenuItem target = menu.findItem(R.id.msot_menu);

            target.setVisible(false);
        }if (db.get_check_audit(sd,"SIR")==0) {
            MenuItem target = menu.findItem(R.id.sir_menu);

            target.setVisible(false);
        }if (db.get_check_audit(sd,"AIB")==0) {
            MenuItem target = menu.findItem(R.id.aib_menu);

            target.setVisible(false);
        }if (db.get_check_audit(sd,"IPM")==0) {
            MenuItem target = menu.findItem(R.id.ipm_menu);

            target.setVisible(false);
        }if (db.get_check_audit(sd,"PMCA")==0) {
            MenuItem target = menu.findItem(R.id.pmca_menu);

            target.setVisible(false);
        }if (db.get_check_audit(sd,"QSR")==0) {
            MenuItem target = menu.findItem(R.id.qsr_menu);

            target.setVisible(false);
        }
    }

    @Override
    public void onBackPressed(){

    }

    private class Load_Layout extends AsyncTask<String, Void, String> {
        protected String doInBackground(String... urls) {

            return "call";
        }



        protected void onPostExecute() {
           // showDialog("Downloaded " + result + " bytes");
        }
        protected void onPreExecute() {
            setupViewPager(viewPager);
            // showDialog("Downloaded " + result + " bytes");
        }
    }


    private void setToolbar(){

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(false);

        actionBar.setTitle("");

    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Dashboard(), "Dashboard");
        adapter.addFrag(new Recyclerview(), "Inspection");


        adapter.addFrag(new Completed_Fragment(), "Complete");
        adapter.addFrag(new In_Progress_Fragment(), "INComplete");
        adapter.addFrag(new Profile_Fragment(), "Profile");
        adapter.addFrag(new About_Us_Fragment(), "About Us");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
     public String get_mail(){
         Cursor c1;
         String Mail_Name="";
         Log.e("UUU","entry12");
         try {
             c1 = sd.rawQuery("Select * from " + db.USER_PROFILE_TABLE, null);
             c1.moveToFirst();
             Mail_Name = c1.getString(c1.getColumnIndex(db.USER_MAIL));

             Log.e("UUU","mail = "+c1.getString(c1.getColumnIndex(db.USER_MAIL)));
         } catch (Exception e) {

         }
         return Mail_Name;
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

    @Override
    protected void onResume() {


        Main_ID="0";
        sd.delete(db.INSERTED_MSOT_MAIN,null,null);
        super.onResume();
    }    @Override
    protected void onRestart() {
        Main_ID="0";
        sd.delete(db.INSERTED_MSOT_MAIN,null,null);
        super.onRestart();
    }
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.sir_menu) {
            // Handle the camera action
            Intent i = new Intent(getApplicationContext(),SIR_Title_1.class);
            startActivity(i);
        } else if (id == R.id.pmca_menu) {
            Intent i = new Intent(getApplicationContext(),PMCA_Title_1.class);
            startActivity(i);
        } else if (id == R.id.aib_menu) {
            Intent i = new Intent(getApplicationContext(),AIB_Title_1.class);
            startActivity(i);
        } else if (id == R.id.qsr_menu) {
            Intent i = new Intent(getApplicationContext(),QSR_Title_1.class);
            startActivity(i);
        } else if (id == R.id.ipm_menu) {
            Intent i = new Intent(getApplicationContext(),IPM_Title_1.class);
            startActivity(i);
        }else if (id == R.id.msot_menu) {
            Intent i = new Intent(getApplicationContext(),MSOT_Main.class);
            startActivity(i);
        }else if (id == R.id.sync_menu) {
            Toast.makeText(getApplicationContext(),"Please wait...",Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void get_audit_access(String mail){

//        sd.delete( db.AUDIT_ACCESS_TB, null, null );

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


                            //   id=new String[jsonArray_get.length()];

                             Log.e("DDDDDD Count",jsonArray_get_A.length()+"");
                             Log.e("DDDDDD Count",jsonArray_get_A+"");
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
                        Toast.makeText( Category_Type_Activity.this, error.getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                } )
                .requestJson();
    }


}
