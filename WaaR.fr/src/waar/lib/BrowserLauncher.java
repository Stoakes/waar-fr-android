package waar.lib;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class BrowserLauncher {

	
	public static void runOnBrowser(String url, Activity a)
	{
		if (!url.equals(""))
			a.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
	}
}
