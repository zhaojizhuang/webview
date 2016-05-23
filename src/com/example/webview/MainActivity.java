package com.example.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {
	private static final String CLASS_NAME="MainActivity";
	
	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 让进度条显示在标题栏上  
		 requestWindowFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_main);
		initUI();
		settWeb();
		startBrowse();
	}
	private void startBrowse(){
		webView.loadUrl("http://www.baidu.com/"); 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startBrowse();
		}
		return super.onOptionsItemSelected(item);
	}


	private void initUI(){
		webView=(WebView) findViewById(R.id.webview);
	}
	private void settWeb(){
		setWebviewClient();
		webViewSettings();
	}
	private void setWebviewClient(){
		//1 设置webviewclien
		webView.setWebViewClient(new WebViewClient(){
		
			@Override
			public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
				// TODO Auto-generated method stub
				 view.loadUrl(url);// 当打开新链接时，使用当前的 WebView，不会使用系统其他浏览器
		            return true;
			}
			
		});
		//2设置webchromeclient
		webView.setWebChromeClient(new mWebChromeClient());
	}
	private void webViewSettings(){
		WebSettings webSettings=webView.getSettings();
		webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
		webSettings.setAllowFileAccess(true); // 允许访问文件
		webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
		//支持缩放      
		webSettings.setSupportZoom(true); 
		/**
		 * 用WebView显示图片，可使用这个参数 * 设置网页布局类型： * 1、LayoutAlgorithm.NARROW_COLUMNS ： 适应内容大小 * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放 */
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS); 
		webSettings.setDefaultTextEncodingName("utf-8"); //设置文本编码 
		webSettings.setAppCacheEnabled(true); //开启缓存
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//设置缓存模式    
	}
	//webChrome类
	class mWebChromeClient extends WebChromeClient{
		// 设置网页加载的进度条
        public void onProgressChanged(WebView view, int newProgress) {
        	Log.d(CLASS_NAME, "onProgressChanged:"+newProgress);
        	MainActivity.this.setProgress(newProgress*100);
        }

        // 获取网页的标题
        public void onReceivedTitle(WebView view, String title) {
        }

        // JavaScript弹出框
        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        // JavaScript输入框
        @Override
        public boolean onJsPrompt(WebView view, String url, String message,
                String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        // JavaScript确认框
        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }
	}
}
