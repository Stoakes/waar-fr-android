package fr.waar.android.lib;

import fr.waar.android.activity.WaaRActivity;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

public class NotificationHandler{

	public static int ID_NOTIFICATION ;
			
	public static void createNotify(Context context, WaarNotification n){
		NotificationHandler.createNotify(context, n, true);
    	
    }
 
	public static void cancelNotify(Context context, WaarNotification n){
    	NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    	notificationManager.cancel(n.id_notification);
    }
	
	public static void createNotify(Context context, WaarNotification n, boolean vibrateAndSing){		
    	NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
	   	
    	int icon = fr.waar.android.R.drawable.w_notif_logo;
    	Notification notification = new Notification(icon, Params.NotificationPopUp , System.currentTimeMillis());  
 
    	PendingIntent pendingIntent = null;
    	
    	int pendingIntentCode = PendingIntent.FLAG_UPDATE_CURRENT;
    	
    	Intent intent= null;
        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, WaaRActivity.class), 0);
    	if (!n.url.equals(""))
    	{

    		intent = new Intent(Intent.ACTION_VIEW, Uri.parse(n.url));
    	}
    	else if(n.classCible!= null)
    	{
    		intent =  new Intent(context, n.classCible);
    	}
    	
    	pendingIntent = PendingIntent.getActivity(context, 0, intent, pendingIntentCode);
    	
        String titreNotification = Params.WAAR_SITE_NAME;
        notification.setLatestEventInfo(context, titreNotification, n.getTexte_notification(), pendingIntent);
        //Ici les chiffres correspondent � 0sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration

        //debug
        //vibrateAndSing = false;
        
        if (vibrateAndSing)
        {
            notification.vibrate = new long[] {0,100,50,100};
            notification.audioStreamType = Notification.STREAM_DEFAULT;
            
            notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            
            notification.ledOnMS  = 200;    //Set led blink (On in ms)
            notification.ledOffMS = 200;    //Set led blink (Off in ms)
            notification.ledARGB = Notification.DEFAULT_LIGHTS;   //Set led color
        }
        notification.flags =  Notification.FLAG_AUTO_CANCEL;

//        Logger.log("NOTIFy");
        notificationManager.notify(n.id_notification , notification);
    }
	
}