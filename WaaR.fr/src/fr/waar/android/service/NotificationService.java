package fr.waar.android.service;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import fr.waar.android.lib.NotificationController;
import fr.waar.android.lib.Params;


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
//	
//	private void maj_lastcheck(Calendar val)
//	{
//		if (val!=null)
//			Params.dernier_check = val;
//	}
	
	@Override
	public void onStart(Intent intent, int startId)
	{	
		
	    t.scheduleAtFixedRate(new TimerTask() {

	    	@Override
	        public void run() {
//	    		Looper.prepare();
	    		NotificationController.execute(getApplicationContext());
//	    		maj_lastcheck(lastcheck);
	
//	    		Looper.loop();
	        } 
	    }, 0, Params.tempo_entre_2_check_sec*1000);
	}
		
}
