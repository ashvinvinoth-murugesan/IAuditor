package com.rentokil.pci.rauditor.MSOT_NEW;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.rentokil.pci.rauditor.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Pdf_Viewer extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf__viewer);

        Intent intent1=getIntent();
        String id=intent1.getStringExtra("main_id");

        Log.e("KKKRRR","id = "+id);


        String pdfUrl = "https://rauditor.riflows.com/rauditor/Android/downloads/msot/msot_"+id+".pdf"; //your pdf url
        String url = "http://docs.google.com/gview?embedded=true&url=" + pdfUrl;

        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);

        finish();

    }



}