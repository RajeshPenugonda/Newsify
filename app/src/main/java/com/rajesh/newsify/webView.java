package com.rajesh.newsify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.material.button.MaterialButton;

public class webView extends AppCompatActivity {

    Toolbar toolbar;
    WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        toolbar = findViewById(R.id.toolbar);
        webView = findViewById(R.id.webView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        MaterialButton button = findViewById(R.id.share_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(android.content.Intent.ACTION_SEND);
                intent1.putExtra(android.content.Intent.EXTRA_TEXT, url);
                startActivity(Intent.createChooser(intent, "Share using..."));
            }
        });
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);

    }
}