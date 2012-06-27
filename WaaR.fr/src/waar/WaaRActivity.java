package waar;

import fr.waar.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
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

    }
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private class myWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			view.loadUrl(url);
			return true;
		}
	}
}