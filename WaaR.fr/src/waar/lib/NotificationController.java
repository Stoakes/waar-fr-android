package waar.lib;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.util.Log;

public class NotificationController {
	
	/**
	 * Permet de lancer tout la procédure de notification
	 * @return
	 */
	public static boolean execute(Context c)
	{
		Log.e("WAAR_NOTIF_LOG", "NotificationController");
		
		String url  = Params.WAAR_SITE + Params.NOTIF_PAGE;
				
		Params.loadAllParams(c);
		
		ArrayList<NameValuePair> paramList = new ArrayList<NameValuePair>();
		
		paramList.add(new BasicNameValuePair("pseudo", Params.pseudo));
		paramList.add(new BasicNameValuePair("pwd", Params.md5Password));
	
		String data = ServerHandler.postData(url, paramList);
		
		
		
		return true;
	}
	
	/**
	 * Permet de transformer les données reçu en catégories de notifications
	 * @param data
	 * @return Hashtable<String, String>
	 */
	private Hashtable<String, String> decodeData(String data)
	{
		Hashtable<String, String> categories  = new Hashtable<String, String>();
		
		String[] dataTable = data.split(";");
		String nb_notifications = "";
		
		for (String element : dataTable)
		{
			if (element.contains("JdB"))
			{
				nb_notifications = element.replace(" JdB", "");
				categories.put("JdB", nb_notifications);
			}
			else if(element.contains("Ally"))
			{
				nb_notifications = element.replace(" Ally", "");
				categories.put("Ally", nb_notifications);
			}
			else if(element.contains("News"))
			{
				nb_notifications = element.replace(" News", "");
				categories.put("News", nb_notifications);
			}
			else if(element.contains("MP"))
			{
				nb_notifications = element.replace(" MP", "");
				categories.put("MP", nb_notifications);
			}
		}
 		
		return categories;

	}

}
