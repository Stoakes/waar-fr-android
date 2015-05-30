package fr.waar.android.service;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import fr.waar.android.lib.NotificationController;
import fr.waar.android.lib.Params;


import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

/*Patern singleton*/
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
	    		NotificationController.execute(getApplicationContext());
	        } 
	    	
	    }, 0, Params.tempo_entre_2_check_sec*1000);
	}
		
}
