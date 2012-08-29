package fr.waar.android.activity;



import fr.waar.android.R;
import fr.waar.android.lib.BrowserLauncher;
import fr.waar.android.lib.Params;
import fr.waar.android.service.NotificationService;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class OptionsActivity extends Activity {
    
    private EditText champPseudo;
    private EditText champPwd;
    private CheckBox CkboxNotif;
    
    private CheckBox CkboxNotif_Jbd;
    private CheckBox CkboxNotif_News;
    private CheckBox CkboxNotif_MP;
    private CheckBox CkboxNotif_Ally;
    
    private Button boutton_OK;
    private Button boutton_Annuler;
    private Button boutton_Lancer_Waar;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
            setContentView(R.layout.options);
            
            String app_name = getString(R.string.app_name);
            String app_version = getString(R.string.app_version);
            setTitle(app_name + " V" + app_version);
            
            champPseudo = (EditText) findViewById(R.id.txtFieldPseudo);
            champPwd = (EditText) findViewById(R.id.txtFieldMdP);
            
            CkboxNotif =  (CheckBox) findViewById(R.id.chkbox_activernotifications);
            
            CkboxNotif_Jbd =  (CheckBox) findViewById(R.id.chkbox_activer_Jbd);
            CkboxNotif_News =  (CheckBox) findViewById(R.id.chkbox_activer_News);
            CkboxNotif_MP =  (CheckBox) findViewById(R.id.chkbox_activer_MP);
            CkboxNotif_Ally =  (CheckBox) findViewById(R.id.chkbox_activer_Ally);
            
            boutton_OK = (Button) findViewById(R.id.btnOK);
            boutton_Annuler= (Button) findViewById(R.id.btnAnnuler);
            boutton_Lancer_Waar= (Button) findViewById(R.id.boutton_lancer_waar);
            
            boutton_OK.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				valider();
    			}
    		});				
            boutton_Annuler.setOnClickListener(new OnClickListener() {
    			public void onClick(View v) {
    				annuler();
    			}
    		});
            
            boutton_Lancer_Waar.setOnClickListener( new OnClickListener() {
    			public void onClick(View v) {
    				runWaar();
    			}
    		});
            
            CkboxNotif.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						MaJActivationCheckboxes(isChecked);
				}
			});
       
            startService(new Intent(getApplicationContext(), NotificationService.class));
            
            remplirFormulaire();
	}
	
	
	private void runWaar()
	{
		BrowserLauncher.runOnBrowser(Params.WAAR_SITE + Params.WAAR_SITE_DEFAULT_PAGE, this);
	}
	
	private void MaJActivationCheckboxes(boolean activer)
	{
		CkboxNotif_Jbd.setEnabled(activer);
        CkboxNotif_News.setEnabled(activer);
        CkboxNotif_MP.setEnabled(activer);
        CkboxNotif_Ally.setEnabled(activer);
	}
	
    private void valider() {
    	
    	boolean valide = true;
    	
    	if (champPseudo.getText().toString().equals(""))
    	{
    		Toast.makeText(getApplicationContext(), "Le pseudo ne peut pas être vide !", Toast.LENGTH_LONG).show();
    		valide = false;
    	}
    	else if(champPwd.getText().toString().equals(""))
    	{
    		Toast.makeText(getApplicationContext(), "Le mot de passe ne peut pas être vide !", Toast.LENGTH_LONG).show();
    		valide = false;
    	}
		
    	if (valide)
    	{
    		//le formulaire est valide, on enregistre les données les données
    		sauvegarderDonnees();
    		Toast.makeText(getApplicationContext(), "Paramètres sauvegardés", Toast.LENGTH_LONG).show();
    		this.finish();
    	}
	}
    
	private void annuler() {
		remplirFormulaire();
		this.finish();
	}
	
	private void remplirFormulaire()
	{
		Params.loadAllParams(getApplicationContext());
		this.champPseudo.setText(Params.pseudo);
		this.champPwd.setText(Params.md5Password);
		this.CkboxNotif.setChecked(Params.notification_active);
		this.CkboxNotif_Ally.setChecked(Params.notification_active_ally);
		this.CkboxNotif_Jbd.setChecked(Params.notification_active_jbd);
		this.CkboxNotif_MP.setChecked(Params.notification_active_mp);
		this.CkboxNotif_News.setChecked(Params.notification_active_news);
	}
	
	private void sauvegarderDonnees()
	{
		Params.pseudo = this.champPseudo.getText().toString();
		Params.md5Password = this.champPwd.getText().toString();
		Params.notification_active = this.CkboxNotif.isChecked();
		Params.notification_active_ally = this.CkboxNotif_Ally.isChecked();
		Params.notification_active_jbd = this.CkboxNotif_Jbd.isChecked();
		Params.notification_active_mp = this.CkboxNotif_MP.isChecked();
		Params.notification_active_news = this.CkboxNotif_News.isChecked();
		Params.saveAllParams(getApplicationContext());
	}
	
	

}