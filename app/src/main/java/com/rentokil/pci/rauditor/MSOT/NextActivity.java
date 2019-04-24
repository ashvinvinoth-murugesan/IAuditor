package com.rentokil.pci.rauditor.MSOT;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.GeolocationPermissions;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.rentokil.pci.rauditor.R;

public class NextActivity extends AppCompatActivity {

    private TextView tv;

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);


//        webView = (WebView)findViewById(R.id.webview);
//
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setAppCacheEnabled(true);
//        webView.getSettings().setDatabaseEnabled(true);
//        webView.getSettings().setDomStorageEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//
//        webView.getSettings().setGeolocationEnabled(true);
//        webView.setWebViewClient(new WebViewClient()
//        {
//
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler
//                    handler, SslError error)
//            {
//                handler.proceed();
//            }
//        });
//
//        webView.loadUrl("https://speedops.riflows.com/PORTAL/indexf.php");
//
//        webView.setWebChromeClient(new WebChromeClient()
//        {
//            @Override
//            public void onGeolocationPermissionsShowPrompt(String origin,
//                                                           GeolocationPermissions.Callback callback)
//            {
//                callback.invoke(origin,true,false);
//            }
//        });
    }
}
