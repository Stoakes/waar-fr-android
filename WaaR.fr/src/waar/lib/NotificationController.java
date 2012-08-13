package waar.lib;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

public class NotificationController {
	
	/**
	 * Permet de lancer tout la procédure de notification
	 * @return
	 */
	public static boolean execute(Context c)
	{
		String url  = Params.WAAR_SITE + Params.NOTIF_PAGE;
				
		Params.loadAllParams(c);
		
		waar.lib.NotificationManager.init_notifications(c);
		
		ArrayList<NameValuePair> paramList = new ArrayList<NameValuePair>();
		
		Params.pseudo = "Guizmo";
		Params.md5Password = "testtest";
		
		paramList.add(new BasicNameValuePair("pseudo", Params.pseudo));
		paramList.add(new BasicNameValuePair("pwd", Params.md5Password));
		
		String data = ServerHandler.postData(url, paramList);
		Log.e("DATA RECEIVED", data);
		
		NotificationController.decodeData(data);
		
		waar.lib.NotificationManager.MaJ_Notifcations(c);

		return true;
	}
	
	/**
	 * Permet de transformer les données reçu en catégories de notifications
	 * stocke le tout dans NotificationManager.notifications_actuelles
	 * @param data
	 * @return Hashtable<String, String>
	 */
	private static void decodeData(String data)
	{
		String[] dataTable = data.split(";");
		int nb_notifications = 0;
		
		for (String element : dataTable)
		{
			if (element.contains("JdB"))
			{
				
				nb_notifications = Integer.parseInt(element.replace(" JdB", ""));
				waar.lib.NotificationManager.getNotification("JdB").nombre_notifications = nb_notifications;
			}
			else if(element.contains("Ally"))
			{
				nb_notifications = Integer.parseInt(element.replace(" Ally", ""));
				waar.lib.NotificationManager.getNotification("Ally").nombre_notifications = nb_notifications;
			}
			else if(element.contains("News"))
			{
				nb_notifications = Integer.parseInt(element.replace(" News", ""));
				waar.lib.NotificationManager.getNotification("News").nombre_notifications = nb_notifications;
			}
			else if(element.contains("MP"))
			{
				nb_notifications = Integer.parseInt(element.replace(" MP", ""));
				waar.lib.NotificationManager.getNotification("MP").nombre_notifications = nb_notifications;
			}
		}
	}

}
