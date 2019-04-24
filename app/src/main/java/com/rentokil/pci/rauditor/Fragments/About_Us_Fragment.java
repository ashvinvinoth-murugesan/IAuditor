package com.rentokil.pci.rauditor.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.rentokil.pci.rauditor.BuildConfig;
import com.rentokil.pci.rauditor.Adapter.List_Item_Methodes;
import com.rentokil.pci.rauditor.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class About_Us_Fragment extends Fragment {

    public View view;

    Activity context;
    ConnectivityManager cManager;
    NetworkInfo nInfo;
    String myJSON;
    JSONArray json_arr_cus_result;
    TextView version_update;
    String versionName = BuildConfig.VERSION_NAME;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_aboutus, container, false);


        version_update=(TextView) view.findViewById(R.id.version_update);
        version_update.setText(versionName);

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
            dialog.show();
        }


        return view;

    }

}
