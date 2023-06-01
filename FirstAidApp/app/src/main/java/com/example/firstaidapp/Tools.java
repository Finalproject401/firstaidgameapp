package com.example.firstaidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Tools extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);

        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        //webView.getSettings().setJavaScriptEnabled(true);

        // Load the Amazon.com URL
        webView.loadUrl("https://www.amazon.com/first-aid-kit/s?k=first+aid");
    }
    public void onBackPressed(){
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}