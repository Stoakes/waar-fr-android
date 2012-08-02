package waar.lib;

import waar.Activities.WaaRActivity;
import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class NotificationHandler{

	public static int ID_NOTIFICATION ;
		
	//TODO faire singleton !
	
	public static void createNotify(Context context, String titre, String texte){
		
    	//On cr�er un "gestionnaire de notification"
    	NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);        
 
    	
    	int icon = fr.waar.android.R.drawable.waar_notification_icon;
    	
    	//On cr�er la notification
    	//Avec son ic�ne et son texte d�filant (optionel si l'on veut pas de texte d�filant on met cet argument � null)
    	Notification notification = new Notification(icon, "Waar : 1 MP !", System.currentTimeMillis());  
 
    	//Le PendingIntent c'est ce qui va nous permettre d'atteindre notre deuxi�me Activity
    	//ActivityNotification sera donc le nom de notre seconde Activity
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, WaaRActivity.class), 0);
        //On d�finit le titre de la notif
        String titreNotification = titre;

 
        //On configure notre notification avec tous les param�tres que l'on vient de cr�er
        notification.setLatestEventInfo(context, titreNotification, texte, pendingIntent);
        //On ajoute un style de vibration � notre notification
        //L'utilisateur est donc �galement averti par les vibrations de son t�l�phone
        //Ici les chiffres correspondent � 0sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration, 0.1sec de pause, 0.2sec de vibration
        //Vous pouvez bien entendu modifier ces valeurs � votre convenance
        notification.vibrate = new long[] {0,100,200,300};

 
        //Enfin on ajoute notre notification et son ID � notre gestionnaire de notification
        notificationManager.notify(ID_NOTIFICATION, notification);
    }
 
    //M�thode pour supprimer de la liste de notification la notification que l'on vient de cr�er
	private void cancelNotify(Context context){
    	//On cr�� notre gestionnaire de notfication
    	NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
    	//on supprime la notification gr�ce � son ID
    	notificationManager.cancel(ID_NOTIFICATION);
    }
}
