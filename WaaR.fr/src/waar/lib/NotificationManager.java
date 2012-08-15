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
		notifications.add(new WaarNotification("JdB", 0, 42001, "Journal de Bord : %s nouvelle(s) entrée(s).", Params.WAAR_SITE + "/royaume.php"));
		notifications.add(new WaarNotification("News", 0, 42002, "News : %s nouvelle(s) news " + Params.WAAR_SITE_NAME + " !",Params.WAAR_SITE + "/news.php"));
		notifications.add(new WaarNotification("Ally", 0, 42003, "Alliance : %s nouveau(x) message(s) d'alliance",Params.WAAR_SITE + "/alliance.php"));
		notifications.add(new WaarNotification("MP", 0, 42004, "Messages Privés : %s nouveau(x) message(s)", Params.WAAR_SITE + "/messages.php"));
		
		notifications.add(new WaarNotification("erreur_param", 0, 42011, "Paramètres de connection incorrects", Params.WAAR_SITE + "/royaume.php"));
		notifications.add(new WaarNotification("erreur_pseudo", 0, 42012, "Pseudo incorrect", Params.WAAR_SITE + "/royaume.php"));
		notifications.add(new WaarNotification("erreur_pwd", 0, 42013, "Mot de passe incorrect", Params.WAAR_SITE + "/royaume.php"));
		notifications.add(new WaarNotification("erreur_spam", 0, 42014, "Que fais-tu, ptit malin?", Params.WAAR_SITE + "/royaume.php"));
		notifications.add(new WaarNotification("erreur_ban", 0, 42015, "Votre IP a été bannie.", Params.WAAR_SITE + "/royaume.php"));
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
			for (WaarNotification n : NotificationManager.notifications_avant)
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
					//nouvelle notification ou nombre de notificatiosn augmenté
					NotificationHandler.createNotify(c, n);
				}
				else if(n_avant.nombre_notifications == n.nombre_notifications)
				{
					//on ne fait rien !
				}
				else
				{
					//on a une notification, mais son nombre a diminué.
					NotificationHandler.createNotify(c, n, false);
				}
			}
			else
			{
				NotificationHandler.cancelNotify(c,n);
			}
		}
	}
	
	
}
