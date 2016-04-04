package com.example.androidsocketdemo;

import com.example.androidsocketdemo.R;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewDemo extends Activity{

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		WebView webview=(WebView) findViewById(R.id.webView1);
		webview.loadUrl("http://121.201.8.196/mx/css.html");
		webview.getSettings().setSupportZoom(true);
		webview.getSettings().setBuiltInZoomControls(true);
	}
}
