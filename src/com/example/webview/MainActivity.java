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
		// �ý�������ʾ�ڱ�������  
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
		//1 ����webviewclien
		webView.setWebViewClient(new WebViewClient(){
		
			@Override
			public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
				// TODO Auto-generated method stub
				 view.loadUrl(url);// ����������ʱ��ʹ�õ�ǰ�� WebView������ʹ��ϵͳ���������
		            return true;
			}
			
		});
		//2����webchromeclient
		webView.setWebChromeClient(new mWebChromeClient());
	}
	private void webViewSettings(){
		WebSettings webSettings=webView.getSettings();
		webSettings.setJavaScriptEnabled(true); // ����֧��javascript�ű�
		webSettings.setAllowFileAccess(true); // ��������ļ�
		webSettings.setBuiltInZoomControls(true); // ������ʾ���Ű�ť
		//֧������      
		webSettings.setSupportZoom(true); 
		/**
		 * ��WebView��ʾͼƬ����ʹ��������� * ������ҳ�������ͣ� * 1��LayoutAlgorithm.NARROW_COLUMNS �� ��Ӧ���ݴ�С * 2��LayoutAlgorithm.SINGLE_COLUMN:��Ӧ��Ļ�����ݽ��Զ����� */
		webSettings.setLayoutAlgorithm(LayoutAlgorithm.NARROW_COLUMNS); 
		webSettings.setDefaultTextEncodingName("utf-8"); //�����ı����� 
		webSettings.setAppCacheEnabled(true); //��������
		webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//���û���ģʽ    
	}
	//webChrome��
	class mWebChromeClient extends WebChromeClient{
		// ������ҳ���صĽ�����
        public void onProgressChanged(WebView view, int newProgress) {
        	Log.d(CLASS_NAME, "onProgressChanged:"+newProgress);
        	MainActivity.this.setProgress(newProgress*100);
        }

        // ��ȡ��ҳ�ı���
        public void onReceivedTitle(WebView view, String title) {
        }

        // JavaScript������
        @Override
        public boolean onJsAlert(WebView view, String url, String message,
                JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        // JavaScript�����
        @Override
        public boolean onJsPrompt(WebView view, String url, String message,
                String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        // JavaScriptȷ�Ͽ�
        @Override
        public boolean onJsConfirm(WebView view, String url, String message,
                JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }
	}
}
