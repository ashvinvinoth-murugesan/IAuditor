package com.rentokil.pci.rauditor.Image;

import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rentokil.pci.rauditor.R;

public class Snackbar_Test extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar__test);

        coordinatorLayout=(CoordinatorLayout) findViewById(R.id.coordinatorLayout);


        Snackbar snackbar = Snackbar.make(coordinatorLayout, "", Snackbar.LENGTH_LONG);

        Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();

        TextView textView = (TextView) layout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setVisibility(View.INVISIBLE);
        textView.setMaxLines(14);


        View snackView = getLayoutInflater().inflate(R.layout.my_snackbar, null);
//
//        TextView textViewTop = (TextView) snackView.findViewById(R.id.text);




        layout.setPadding(0,0,0,0);

        layout.addView(snackView, 0);

        snackbar.show();

    }
}
