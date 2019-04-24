package com.rentokil.pci.rauditor.Image;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rentokil.pci.rauditor.Database.DatabaseHelper;
import com.rentokil.pci.rauditor.MSOT_NEW.MSOT_LIST_2;
import com.rentokil.pci.rauditor.R;

public class MSOT_TEST extends AppCompatActivity {

    CheckBox cb1,cb2;

    DatabaseHelper db;
    SQLiteDatabase sd;
    ContentValues cv1;
    ContentValues cv2;

    Button act_sub;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msot__test);

        cb1=(CheckBox) findViewById(R.id.cb1);
        cb2=(CheckBox) findViewById(R.id.cb2);
        db = new DatabaseHelper(MSOT_TEST.this);
        sd = db.getReadableDatabase();
        cv1 = new ContentValues();
        cv2 = new ContentValues();

        act_sub=(Button) findViewById(R.id.act_sub);

        act_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MSOT_TEST.this, MSOT_LIST_2.class);
                startActivity(i);
            }
        });
        cb1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            sd.delete(db.ACTIVITIES_PAGE_1,null,null);

            boolean checked = ((CheckBox) view).isChecked();

            if (checked){

                cv1.put(db.STATUS_1, "cb1");
                sd.insert(db.ACTIVITIES_PAGE_1, null, cv1);

                Toast.makeText(getApplicationContext(),"CHECKED",Toast.LENGTH_SHORT).show();


            }
            else{

                cv1.put(db.STATUS_1, "undocb1");
                sd.insert(db.ACTIVITIES_PAGE_1, null, cv1);

                Toast.makeText(getApplicationContext(),"Checkbox UNCHECKED",Toast.LENGTH_SHORT).show();
                // Do your coding
            }
        }
    });

        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sd.delete(db.ACTIVITIES_PAGE_2,null,null);

                boolean checked = ((CheckBox) view).isChecked();

                if (checked){

                    cv2.put(db.STATUS_2, "cb2");
                    sd.insert(db.ACTIVITIES_PAGE_2, null, cv2);

                    Toast.makeText(getApplicationContext(),"CHECKED",Toast.LENGTH_SHORT).show();

                }
                else{

                    cv2.put(db.STATUS_2, "undocb2");
                    sd.insert(db.ACTIVITIES_PAGE_2, null, cv2);

                    Toast.makeText(getApplicationContext(),"Checkbox UNCHECKED",Toast.LENGTH_SHORT).show();


                }
            }
        });
    }
}
