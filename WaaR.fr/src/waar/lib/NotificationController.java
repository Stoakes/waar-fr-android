package waar.lib;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class NotificationController {
	
	
	public static Boolean derniereRoutineOK = true;
	
	/**
	 * Permet de lancer tout la procédure de notification
	 * @return
	 */
	public static boolean execute(Context c)
	{
		String url  = Params.WAAR_SITE + Params.NOTIF_PAGE;
				
		Params.loadAllParams(c);
		
		//Params.logParam();
		
		if (Params.notification_active)
		{			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");
			
			Calendar derniere_routine_OK = null;
			
			if(Params.dernier_check != null)
				derniere_routine_OK=Params.dernier_check;
			else
			{
				derniere_routine_OK = Calendar.getInstance();
				derniere_routine_OK.setTime(new Date(0));
			}
			
			
			derniere_routine_OK.add(Calendar.SECOND, Params.tempo_entre_2_check_sec-2);
			
			Calendar maintenant = Calendar.getInstance();
			String monHeure = sdf.format(derniere_routine_OK.getTime());
			String str_maintenant = sdf.format(maintenant.getTime());
			
			Logger.log("next : " + monHeure);
						
			if (derniere_routine_OK.before(maintenant))
			{
				
				waar.lib.NotificationManager.init_notifications(c);
				
				ArrayList<NameValuePair> paramList = new ArrayList<NameValuePair>();
				
				paramList.add(new BasicNameValuePair("pseudo", Params.pseudo));
				paramList.add(new BasicNameValuePair("pwd", Params.md5Password));
				
				String data = ServerHandler.postData(url, paramList);
				
				if (data == null || data.equals(""))
					NotificationController.derniereRoutineOK = false;
				Logger.log("DATA RECEIVED : " +  data);
				
				boolean res = NotificationController.decodeData(data);
				
				//si il y a des resultats.
				if (res)
				{
					waar.lib.NotificationManager.MaJ_Notifcations(c);
				}
				Params.dernier_check = maintenant;
			}
			else
			{
				Logger.log("Dernier check trop court !");
			}
		}
		
		return true;
				
	}
	
	/**
	 * Permet de transformer les données reçu en catégories de notifications
	 * stocke le tout dans NotificationManager.notifications_actuelles
	 * @param data
	 * @return Hashtable<String, String>
	 */
	private static boolean decodeData(String data)
	{
		data = data.replace("\t", "");
		data = data.replace(" ", "");
		
		String[] dataTable = data.split(";");
		int nb_notifications = 0;
		
		boolean data_analysed = false;
		for (String element : dataTable)
		{
			if (element.contains("JdB") && Params.notification_active_jbd)
			{
				nb_notifications = Integer.parseInt(element.replace("JdB", ""));
				waar.lib.NotificationManager.getNotification("JdB").nombre_notifications = nb_notifications;
				data_analysed = true;
			}
			else if(element.contains("Ally")  && Params.notification_active_ally)
			{
				nb_notifications = Integer.parseInt(element.replace("Ally", ""));
				waar.lib.NotificationManager.getNotification("Ally").nombre_notifications = nb_notifications;
				data_analysed = true;
			}
			else if(element.contains("News") && Params.notification_active_news)
			{
				nb_notifications = Integer.parseInt(element.replace("News", ""));
				waar.lib.NotificationManager.getNotification("News").nombre_notifications = nb_notifications;
				data_analysed = true;
			}
			else if(element.contains("MP")  && Params.notification_active_mp)
			{
				nb_notifications = Integer.parseInt(element.replace("MP", ""));
				waar.lib.NotificationManager.getNotification("MP").nombre_notifications = nb_notifications;
				data_analysed = true;
			}
		}
		
		//gestion des erreurs
		if (data.contains("ERREUR_PARAM"))
		{
			WaarNotification n = waar.lib.NotificationManager.getNotification("erreur_param");
			n.nombre_notifications = 1;
			data_analysed = true;
		}
		else if (data.contains("ERREUR_PSEUDO"))
		{
			waar.lib.NotificationManager.getNotification("erreur_pseudo").nombre_notifications = 1;
			data_analysed = true;
		}
		else if (data.contains("ERREUR_MDP"))
		{
			waar.lib.NotificationManager.getNotification("erreur_pwd").nombre_notifications = 1;;
			data_analysed = true;
		}
		else if (data.contains("ERREUR_SPAM"))
		{
			waar.lib.NotificationManager.getNotification("erreur_spam").nombre_notifications = 1;
			data_analysed = true;
		}
		else if (data.contains("ERREUR_IPBANNIE"))
		{
			waar.lib.NotificationManager.getNotification("erreur_ban").nombre_notifications = 1;
			data_analysed = true;
		}
		else if (data.contains("NO_NOTIF"))
		{
			data_analysed = true;
			Logger.log("pas de notifications");
		}
		/*else if (data.contains("APP_MAJ"))
		{
			waar.lib.NotificationManager.getNotification("application_maj").nombre_notifications = 1;
		}*/
		return data_analysed;
	}

}
