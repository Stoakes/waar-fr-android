package waar.lib;

import java.util.Hashtable;

import android.content.Context;
import android.content.SharedPreferences;
import android.telephony.TelephonyManager;

public class Params {

	public static String pseudo;
	public static String passWord;
	public static String md5Password;
	public static String phoneNumber;
	public static String idPhone;
	
	public static final String PREFS_NAME = "RemoTxtPrefs";
	
	public static final String WAAR_SITE = "http://dev.waar2.fr" ;
	public static final String WAAR_SITE_NAME = "Waar Beta" ;
	public static final String NOTIF_PAGE = "/ajax/get_notifications_mobile.php";
	
	public static final String  NotificationTitle = "Waar Beta";
	public static final String  NotificationPopUp = "Notification(s) Waar !";

	
	public static void loadAllParams(Context c){
		// Ici on permet donc la lecture de notre fichier de pr�f�rence � toutes les applications
		SharedPreferences myPrefs = c.getSharedPreferences(PREFS_NAME, c.MODE_WORLD_READABLE);		
		Params.pseudo = myPrefs.getString("pseudo", "");
		Params.passWord = myPrefs.getString("pwd","" );
		Params.idPhone = myPrefs.getString("idPhone","" );
		
		md5Password = passWord;
		
		 TelephonyManager tMgr =(TelephonyManager)c.getSystemService(Context.TELEPHONY_SERVICE);
		 Params.phoneNumber = tMgr.getLine1Number();
	}
		
	public static void saveAllParams(Context c){
		SharedPreferences myPrefs = c.getSharedPreferences(PREFS_NAME, c.MODE_WORLD_READABLE);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.putString("pseudo", Params.pseudo);
		prefsEditor.putString("pwd", Params.passWord);
		prefsEditor.putString("idPhone", Params.idPhone);
		prefsEditor.commit();
	}
	
	public static void clearAllParams(Context c){
		SharedPreferences myPrefs = c.getSharedPreferences(PREFS_NAME, c.MODE_WORLD_READABLE);
		SharedPreferences.Editor prefsEditor = myPrefs.edit();
		prefsEditor.clear();
		prefsEditor.commit();
	}
}
