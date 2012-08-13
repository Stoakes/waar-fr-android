package waar.lib;

import java.util.ArrayList;

import waar.Activities.WaaRActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class NotificationManager {
	
	public static ArrayList<WaarNotification> notifications = null;
	public static ArrayList<WaarNotification> notifications_avant = null;

	@SuppressWarnings("unchecked")
	public static void init_notifications(Context c)
	{
		if (notifications!=null)
			notifications_avant = (ArrayList<WaarNotification> ) notifications.clone();
		
		notifications = new ArrayList<WaarNotification>();
		notifications.add(new WaarNotification("JdB", 0, 42001, "Vous avez %s nouvelle(s) entrée(s) dans votre Journal de Bord."));
		notifications.add(new WaarNotification("News", 0, 42002, "%s nouvelle(s) news est diponible sur " + Params.WAAR_SITE_NAME + " !",WaaRActivity.class));
		notifications.add(new WaarNotification("Ally", 0, 42003, "Vous avez %s nouveau(x) message(s) d'alliance",WaaRActivity.class));
		notifications.add(new WaarNotification("MP", 0, 42004, "Vous avez %s nouveau(x) message(s) privé",WaaRActivity.class));
	}
	
	/**
	 * Retourne la notification actuelle en fonction de categorie
	 * @param categorie
	 */
	public static WaarNotification getNotification(String categorie)
	{
		for (WaarNotification n : notifications)
		{
			if (n.categorie.equals(categorie))
				return n;
		}
		return null;
	}
	
	/**
	 * Retourne la notification actuelle en fonction de categorie
	 * @param categorie
	 */
	public static WaarNotification getNotificationAvant(String categorie)
	{
		if (notifications_avant != null)
		{
			for (WaarNotification n : NotificationManager.notifications)
			{
				if (n.categorie.equals(categorie))
					return n;
			}
		}
		return null;
	}
	
	/**
	 * Retourne la liste de toute les catégories possibles
	 */
	public static ArrayList<String> getCategories()
	{
		ArrayList<String> categories = new ArrayList<String>();
		for (WaarNotification n : NotificationManager.notifications)
		{
			categories.add(n.categorie);
		}
		return categories;
	}
	
	/**
	 * Met a jour les notification systèmes d'Android en fonction de ce qu il y a dans notifications_actuelles
	 */
	public static void MaJ_Notifcations(Context c)
	{
		for (WaarNotification n : NotificationManager.notifications)
		{
			WaarNotification n_avant = getNotificationAvant(n.categorie);
			
			if (n.nombre_notifications>0)
			{
				if (n_avant==null || n_avant.nombre_notifications < n.nombre_notifications)
				{
					NotificationHandler.createNotify(c, n);
				}
				else
				{
					
					//NotificationHandler.cancelNotify(c,n);
				}
			}
			else
			{
				NotificationHandler.cancelNotify(c,n);
			}
		}
	}
	
	
}
