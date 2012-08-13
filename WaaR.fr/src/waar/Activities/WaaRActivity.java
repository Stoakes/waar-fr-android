package waar.Activities;

import waar.Services.NotificationService;
import waar.lib.NotificationHandler;
import waar.lib.NotificationManager;

import fr.waar.android.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WaaRActivity extends Activity {
    /** Called when the activity is first created. */
    
    private WebView webView;
    private final String mimeType = "text/html";
    private final String encoding = "utf-8";
    private final String waarUrl = "http://www.waar.fr";
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
                        
            webView = (WebView) findViewById(R.id.WebView);
            
            //pageWeb = getPage(waarUrl);
            webView.setWebViewClient(new myWebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(waarUrl);
            //webView.loadData(pageWeb, mimeType, encoding);
            //webView.loadUrl(waarUrl);
             
            startService(new Intent(getApplicationContext(), NotificationService.class));
            //NotificationHandler.createNotify(getApplicationContext(), "Waar.fr", "Vous avez un nouveau message privé.");
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