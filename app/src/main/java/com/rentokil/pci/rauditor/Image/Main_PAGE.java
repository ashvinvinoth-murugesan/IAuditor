package com.rentokil.pci.rauditor.Image;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import com.rentokil.pci.rauditor.R;

public class Main_PAGE extends AppCompatActivity implements BSImagePicker.OnSingleImageSelectedListener {

    private GridView gvGallery;
    private GalleryAdapter galleryAdapter;
    ArrayList<Uri> mArrayUri;
    TextView tv_single_selection;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        mArrayUri = new ArrayList<Uri>();
        gvGallery = (GridView)findViewById(R.id.gv);
        tv_single_selection = (TextView)findViewById(R.id.tv_single_selection);

        tv_single_selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BSImagePicker pickerDialog = new BSImagePicker.Builder("com.rentokil.pci.rauditor.fileprovider")
                        .setMinimumMultiSelectCount(3)
                        .setMaximumMultiSelectCount(5)
                        .build();
                pickerDialog.show(getSupportFragmentManager(), "picker");
            }
        });

    }

    @Override
    public void onSingleImageSelected(Uri uri, String tag) {
        //   Glide.with(Main_PAGE.this).load(uri).into(ivImage2);
        Log.e("JJJJJ",""+mArrayUri.size());
        if (mArrayUri.size()<4) {
            mArrayUri.add(uri);
            galleryAdapter = new GalleryAdapter(getApplicationContext(),mArrayUri);
            gvGallery.setAdapter(galleryAdapter);
            galleryAdapter.notifyDataSetChanged();
        } else {
            Toast.makeText(getApplicationContext(),"Reached Maximum Limit", Toast.LENGTH_SHORT).show();
        }
    }
}
