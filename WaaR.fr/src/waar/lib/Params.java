package waar.lib;

import java.util.Hashtable;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;
import android.util.Log;

public class Params {

	public static String pseudo;
	public static String md5Password;
	public static String phoneNumber;
	public static String idPhone;
	
	
	public static boolean notification_active;
	public static boolean notification_active_jbd;
	public static boolean notification_active_news;
	public static boolean notification_active_ally;
	public static boolean notification_active_mp;
	
	public static boolean nb_notification;
	public static boolean notification_jbd;
	public static boolean notification_news;
	public static boolean notification_ally;
	public static boolean notification_mp;
	
	public static String urlToLoad = "";
	
	
	public static final String PREFS_NAME = "WaaRPrefs";
	
	public static final String WAAR_SITE = "http://dev.waar2.fr" ;
	public static final String WAAR_SITE_DEFAULT_PAGE = "/royaume.php" ;
	public static final String WAAR_SITE_CONNECTION_PAGE = "/actions/general/connexion.php" ;
	public static final String WAAR_SITE_NAME = "Waar Beta" ;
	public static final String NOTIF_PAGE = "/ajax/get_notifications_mobile.php";
	
	public static final String  NotificationTitle = "Waar Beta";
	public static final String  NotificationPopUp = "Notification(s) Waar !";
	
	public static void loadAllParams(Context c){
		// Ici on permet donc la lecture de notre fichier de pr�f�rence � toutes les applications
		SharedPreferences myPrefs = c.getSharedPreferences(PREFS_NAME, c.MODE_WORLD_READABLE);		
		Params.pseudo = myPrefs.getString("Waar_pseudo", "");
		Params.md5Password = myPrefs.getString("Waar_pwd","");
		
		Params.notification_active = myPrefs.getBoolean("Waar_activaterNotifications",true);
		Params.notification_active_jbd = myPrefs.getBoolean("Waar_activaterNotifications_JbB",true);
		Params.notification_active_news = myPrefs.getBoolean("Waar_activaterNotifications_News",true);
		Params.notification_active_ally = myPrefs.getBoolean("Waar_activaterNotifications_MP",true);
		Params.notification_active_mp = myPrefs.getBoolean("Waar_activaterNotifications_Ally",true);
	}
		
	public static void saveAllParams(Context c){
		SharedPreferences myPrefs = c.getSharedPreferences(PREFS_NAME, c.MODE_WORLD_READABLE);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.putString("Waar_pseudo", Params.pseudo);
		prefsEditor.putString("Waar_pwd", Params.md5Password);
		
		prefsEditor.putBoolean("Waar_activaterNotifications",Params.notification_active);
		prefsEditor.putBoolean("Waar_activaterNotifications_JbB",Params.notification_active_jbd);
		prefsEditor.putBoolean("Waar_activaterNotifications_News",Params.notification_active_news);
		prefsEditor.putBoolean("Waar_activaterNotifications_MP",Params.notification_active_ally);
		prefsEditor.putBoolean("Waar_activaterNotifications_Ally",Params.notification_active_mp);
		
		prefsEditor.commit();
	}
	
	public static void clearAllParams(Context c){
		SharedPreferences myPrefs = c.getSharedPreferences(PREFS_NAME, c.MODE_WORLD_READABLE);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.clear();
		prefsEditor.commit();
	}
	
	public static void logParam(){
		
		String txt = "Waar_activaterNotifications :" +new Boolean(Params.notification_active).toString() + " - ";
		txt +="Waar_activaterNotifications_JbB :"+new Boolean(Params.notification_active_jbd).toString()+ " - ";
		txt +="Waar_activaterNotifications_News :"+new Boolean(Params.notification_active_news).toString()+ " - ";
		txt +="Waar_activaterNotifications_MP :"+new Boolean(Params.notification_active_ally).toString()+ " - ";
		txt +="Waar_activaterNotifications_Ally :"+new Boolean(Params.notification_active_mp).toString()+ " - ";
		
		Log.e("PARAM", txt);
	}
}
