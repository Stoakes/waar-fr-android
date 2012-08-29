package fr.waar.android.activity;

import org.apache.http.util.EncodingUtils;



import fr.waar.android.R;
import fr.waar.android.lib.Logger;
import fr.waar.android.lib.Params;
import fr.waar.android.service.NotificationService;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WaaRActivity extends Activity {
    
    private WebView webView;
    
    public String url="";
    
    public WaaRActivity() {
		super();
	}
    

    public WaaRActivity(String url) {
		super();
		this.url = url;
	}

	@Override
    public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            init();   

            //this.loadPage();
            
            String url = Params.WAAR_SITE_DEFAULT_PAGE;
            this.ConnectAndRedirect(url);
            
            onNewIntent(getIntent());   
            Logger.log("WAARACTIVITY CREATED");
            
            startService(new Intent(getApplicationContext(), NotificationService.class));
    }
	
	private void init()
	{
		Params.loadAllParams(getApplicationContext());
		
		webView = (WebView) findViewById(R.id.WebView);
        
        webView.setWebViewClient(new myWebViewClient());
        
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
	}

    
    private void loadPage()
    {    	
         if (this.url.equals(""))
        	 this.url = Params.WAAR_SITE + Params.WAAR_SITE_DEFAULT_PAGE;
         
         webView.loadUrl(this.url);
    }
    
    public void ConnectAndRedirect(String pageToLoad){ 

    	String URL_CONNECTION = Params.WAAR_SITE + Params.WAAR_SITE_CONNECTION_PAGE;
    	
    	String postData = "psd=" +Params.pseudo+"&mdp="+Params.md5Password+"&page=" + pageToLoad;
		
		webView.postUrl(URL_CONNECTION,EncodingUtils.getBytes(postData, "BASE64"));
		
		Logger.log("Page chargée : " + URL_CONNECTION + " - " +postData );

   }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
	  MenuInflater inflater = getMenuInflater();
	  inflater.inflate(R.layout.optionmenu, menu);
	  return true;
	}

	private class myWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
		
		@Override
		public void onPageFinished(WebView view, String url) {
			// TODO Auto-generated method stub
			super.onPageFinished(view, url);
			Logger.log("Finish load page : " +  url);
		}
		
		@Override
		public void onPageStarted(WebView view, String url, Bitmap favicon) {
			// TODO Auto-generated method stub
			super.onPageStarted(view, url, favicon);
			Logger.log("Start load page : " + url);
		}
	}
	
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		Bundle extras = getIntent().getExtras();
		if(extras !=null) {
			this.init();
			String url = extras.getString("url");
			Logger.log("NewIntent url : " + url);
			this.ConnectAndRedirect(url);
		}
	}
	
	/*	
	@Override
	protected void onResume() {
		super.onResume();
		loadPage();
	};
	
	@Override
	protected void onRestart() {
		super.onRestart();
		loadPage();
	};*/
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.refresh:
	        	webView.reload();
	            return true;
	        case R.id.options_menu:
	            //on affiche l'activité options
	        	Intent intent = new Intent(WaaRActivity.this, OptionsActivity.class);
	        	startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}