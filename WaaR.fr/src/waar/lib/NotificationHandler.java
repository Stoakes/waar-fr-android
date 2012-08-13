package waar.lib;

import waar.Activities.WaaRActivity;
import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;

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
	   	
    	int icon = fr.waar.android.R.drawable.waar_notification_icon;
    	Notification notification = new Notification(icon, Params.NotificationPopUp , System.currentTimeMillis());  
 
    	//Le PendingIntent c'est ce qui va nous permettre d'atteindre notre deuxi�me Activity
    	//ActivityNotification sera donc le nom de notre seconde Activity
    	//TODO remplacer l'intent
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, WaaRActivity.class), 0);
        String titreNotification = Params.WAAR_SITE_NAME;
        notification.setLatestEventInfo(context, titreNotification, n.getTexte_notification(), pendingIntent);
        //Ici les chiffres correspondent � 0sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration
        
        if (vibrateAndSing)
        {
            notification.vibrate = new long[] {0,100,50,100};
            notification.audioStreamType = Notification.STREAM_DEFAULT;
            
            notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            
            notification.ledOnMS  = 200;    //Set led blink (On in ms)
            notification.ledOffMS = 200;    //Set led blink (Off in ms)
            notification.ledARGB = Notification.DEFAULT_LIGHTS;   //Set led color
        }


        notificationManager.notify(n.id_notification , notification);
    }
	
}