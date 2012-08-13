package waar.Activities;

import waar.Services.NotificationService;
import waar.lib.NotificationHandler;
import waar.lib.NotificationManager;
import waar.lib.Params;

import fr.waar.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class WaaRActivity extends Activity {
    /** Called when the activity is first created. */
    
    private WebView webView;
    private final String mimeType = "text/html";
    private final String encoding = "utf-8";
    
    private String url="";
    
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
            
            startService(new Intent(getApplicationContext(), NotificationService.class));
                       
            loadPage();
    }
	
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
    
    private void loadPage()
    {
    	 webView = (WebView) findViewById(R.id.WebView);
         
         webView.setWebViewClient(new myWebViewClient());
         webView.getSettings().setJavaScriptEnabled(true);
         
         if (this.url.equals(""))
        	 this.url = Params.WAAR_SITE;
         
         webView.loadUrl(this.url);
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
	}
}