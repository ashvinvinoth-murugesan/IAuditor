package com.rentokil.pci.rauditor.MSOT_NEW;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.MSOT.Movie;
import com.rentokil.pci.rauditor.MSOT.MoviesAdapter;
import com.rentokil.pci.rauditor.MSOT.RecyclerTouchListener;
import com.rentokil.pci.rauditor.R;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MSOT_LIST_2 extends AppCompatActivity {

    static public List<Movie> movieList = new ArrayList<>();
    public static RecyclerView recyclerView;
    public static MoviesAdapter mAdapter;

    JSONObject jsonObject_get;
    JSONArray jsonArray_get;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msot_2);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);


        mAdapter = new MoviesAdapter(this, movieList);

        recyclerView.setHasFixedSize(true);

        db = new DatabaseHelper(MSOT_LIST_2.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();


        // vertical RecyclerView
        // keep movie_list_row.xml width to `match_parent`
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());


        recyclerView.setLayoutManager(mLayoutManager);

        // adding inbuilt divider line
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));


        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Movie movie = movieList.get(position);
//                Toast.makeText(getApplicationContext(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        prepareMovieData();


    }

    @Override

    public void onBackPressed() {

        Intent i = new Intent(MSOT_LIST_2.this, MSOT_LIST_1.class);
        startActivity(i);

    }

    @Override
    public void onStart() {
        super.onStart();
        Intent intent = new Intent(MSOT_LIST_2.this, Page_A.class);
        startActivity(intent);
    }


    /**
     * Prepares sample data to provide data set to adapter
     */
    private void prepareMovieData() {


        movieList.clear();


        Movie movie = new Movie("INCIDENT REPORTING", "(IR)", R.drawable.pcinew_opt);
        movieList.add(movie);

        movie = new Movie("SINA CARD", "(Success Is No Accident)", R.drawable.pcinew_opt);
        movieList.add(movie);

        movie = new Movie("ZERO TOLERANCE POLICY", "(ZTP)", R.drawable.pcinew_opt);
        movieList.add(movie);

        movie = new Movie("SHE GOLDEN RULES", "(SGR)", R.drawable.pcinew_opt);
        movieList.add(movie);

        Log.e("GGGGGGGG lats12", "" + db.getALL_ACTIVITE(sd, db.last_insert_id(sd)).size());
        for (int i = 0; i < db.getALL_ACTIVITE(sd, db.last_insert_id(sd)).size(); i++) {
            Log.e("HHHHHHSPPP", "" + db.getALL_ACTIVITE(sd, db.last_insert_id(sd)).get(i));
            switch (db.getALL_ACTIVITE(sd, db.last_insert_id(sd)).get(i)) {

                case "Ladder use":
//                 movie = new Movie("4.SHE GOLDEN RULES(Ladder)", "(SGR)",R.drawable.pcinew_opt);
//                 movieList.add(movie);


                    movie = new Movie("LADDER USE", "(LU)", R.drawable.pcinew_opt);
                    movieList.add(movie);

                    break;


                case "Fly Killer Dispenser installation / servicing":


                    movie = new Movie("Fly Killer Dispenser installation / servicing", "(FKDIS)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;


                case "Bait Stations installation / servicing":


                    movie = new Movie("Bait Stations Installation / Servicing", "(BSIS)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;


                case "Fly Traps installation / servicing":

                    movie = new Movie("Fly Traps Installation / Servicing", "(FTIS)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;
                case "Bird Proofing via abseiling":


                    movie = new Movie("Bird Proofing Via Abseiling", "(BPVA)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;

                case "Driving service vehicle":

                    movie = new Movie("Driving Service Vehicle", "(BPVA)", R.drawable.pcinew_opt);
                    movieList.add(movie);


                    break;
                case "Riding motorbike for work":
                    movie = new Movie("Riding Motorbike For Work", "(RMW)", R.drawable.pcinew_opt);
                    movieList.add(movie);

                    break;
                case "Work at the Ceiling/ Roof Void":
                    movie = new Movie("Work At The Ceiling/ Roof Void", "(WCRV)", R.drawable.pcinew_opt);
                    movieList.add(movie);

                    break;
                case "Entry into Roof Void":
                    movie = new Movie("Entry into Roof Void", "(ERV)", R.drawable.pcinew_opt);
                    movieList.add(movie);

                    break;
                case "Insect Light Trap":

                    movie = new Movie("Insect Light Trap", "(ILT)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;
                case "Work near / around Electrical Cabinet":

                    movie = new Movie("Work Near / Around Electrical Cabinet", "(WNEC)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;
                case "Work inside Electrical Room":

                    movie = new Movie("Work Inside Electrical Room", "(WIER)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;
                case "Fumigation":

                    movie = new Movie("Fumigation", "(FS)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;
                case "Air Freshener (AF) installation / servicing":

                    movie = new Movie("Air Freshener (AF) Installation / Servicing", "(FS)", R.drawable.pcinew_opt);
                    movieList.add(movie);
                    break;
                case "Scenting":
                    movie = new Movie("Scenting", "(SGR)", R.drawable.pcinew_opt);
                    movieList.add(movie);

                    break;
                case "Others, please specify below":
                    break;


            }

        }

        Cursor c5;
        c5 = sd.rawQuery("Select * from " + db.MSOT_MAIN_COUNTRY_TABLE, null);
        c5.moveToFirst();

        String get_country = c5.getString(c5.getColumnIndex(db.COUNTRY));

        if (get_country.equalsIgnoreCase("India")) {

            movie = new Movie("COMPLIANCE TO BASIC SAFETY RULES", "(CBSR)", R.drawable.pcinew_opt);
            movieList.add(movie);

            movie = new Movie("SHE GOLDEN RULES TRAINING AT THE BRANCH", "(SGRTB)", R.drawable.pcinew_opt);
            movieList.add(movie);

            movie = new Movie("SRA IMPLEMENTATION AT THE BRANCH", "(SIAB)", R.drawable.pcinew_opt);
            movieList.add(movie);

            movie = new Movie("PERSONAL PROTECTIVE EQUIPMENT", "(PPE)", R.drawable.pcinew_opt);
            movieList.add(movie);

            movie = new Movie("DRIVING & MOTORBIKE RIDING SAFETY", "(DMRS)", R.drawable.pcinew_opt);
            movieList.add(movie);

        } else {


        }

        mAdapter.notifyDataSetChanged();
    }
}