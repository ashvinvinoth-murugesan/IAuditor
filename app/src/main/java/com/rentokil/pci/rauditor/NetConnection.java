package com.rentokil.pci.rauditor;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NetConnection extends AppCompatActivity {

    Button btnnet,btnref;
    ConnectivityManager cManager;
    NetworkInfo nInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_connection);

        btnnet =(Button) findViewById(R.id.button2);
        btnref=(Button) findViewById(R.id.btnrefresh);

        cManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        nInfo=cManager.getActiveNetworkInfo();


    }
    public void open(View view) {

        final ConnectivityManager cManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        final NetworkInfo[] nInfo = {cManager.getActiveNetworkInfo()};
        if (nInfo[0] != null && nInfo[0].isConnected()) {

            Toast.makeText(NetConnection.this, "Net Connected Successfully", Toast.LENGTH_SHORT).show();


        } else {
            final Dialog dialog = new Dialog(NetConnection.this);
            dialog.setContentView(R.layout.alertbox);
            dialog.setTitle("Ashvin");
            dialog.setCancelable(true);
            Button btnreff =(Button) dialog.findViewById(R.id.btnrefresh);
            btnreff.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nInfo[0] =cManager.getActiveNetworkInfo();
                    if (nInfo[0] != null && nInfo[0].isConnected()){

                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(NetConnection.this, "Please Connect Internet", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            Toast.makeText(NetConnection.this, "Internet Disconnected", Toast.LENGTH_LONG).show();
            dialog.show();
        }
    }
}
