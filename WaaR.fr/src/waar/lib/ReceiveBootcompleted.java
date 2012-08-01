package waar.lib;

import waar.Services.NotificationService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiveBootcompleted extends BroadcastReceiver {
	 
@Override
public void onReceive(Context context, Intent intent) {
 
    Log.i("ReceiveBootCompleted","****** Boot terminé ********************");
    Log.i("ReceiveBootCompleted"," ***** lancement du service **************");
    context.startService(new Intent().setComponent(new ComponentName(context.getPackageName(), NotificationService.class.getName())));
        }
}