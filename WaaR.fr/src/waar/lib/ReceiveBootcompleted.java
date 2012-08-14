package waar.lib;

import waar.Services.NotificationService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

public class ReceiveBootcompleted extends BroadcastReceiver {
	 
	@Override
	public void onReceive(Context context, Intent intent) {
    context.startService(new Intent().setComponent(new ComponentName(context.getPackageName(), NotificationService.class.getName())));
    }
}