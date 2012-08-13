package waar.Services;

import java.util.Timer;
import java.util.TimerTask;

import waar.lib.NotificationController;
import waar.lib.NotificationHandler;
import waar.lib.NotificationManager;
import waar.lib.Params;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class NotificationService extends Service {

	Timer t = new Timer();
	
	@Override
	public IBinder onBind(Intent intent) {
		
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
        //chargement des parametres
        Params.loadAllParams(getApplicationContext());
        
		t = new Timer();
	}
	
	@Override
	public void onStart(Intent intent, int startId)
	{		
	    t.scheduleAtFixedRate(new TimerTask() {

	    	@Override
	        public void run() {
	    		//NotificationHandler.createNotify(getApplicationContext(), "Waar.fr", "Vous avez un nouveau message privé.");
	    		NotificationController.execute(getApplicationContext());
	        } 
	    }, 0, 10000); 
	}

		
}
