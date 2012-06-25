package waar;

import fr.waar.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

public class WaaRActivity extends Activity {
    /** Called when the activity is first created. */
    
    private WebView webView;
    private final String mimeType = "text/html";
    private final String encoding = "utf-8";
    private final String waarUrl = "www.waar.fr";
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	try
    	{
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main);
            
            webView = (WebView) findViewById(R.id.WebView);
           
            webView.loadData(waarUrl, mimeType, encoding);
    	}
    	catch (Exception e)
    	{
    		Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG);
    	}

    }
}