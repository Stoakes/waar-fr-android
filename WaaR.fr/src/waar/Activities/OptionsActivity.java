package waar.Activities;

import waar.lib.Params;

import fr.waar.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
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
    
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
    		super.onCreate(savedInstanceState);
            setContentView(R.layout.options);
            
            champPseudo = (EditText) findViewById(R.id.txtFieldPseudo);
            champPwd = (EditText) findViewById(R.id.txtFieldMdP);
            
            CkboxNotif =  (CheckBox) findViewById(R.id.chkbox_activernotifications);
            
            CkboxNotif_Jbd =  (CheckBox) findViewById(R.id.chkbox_activer_Jbd);
            CkboxNotif_News =  (CheckBox) findViewById(R.id.chkbox_activer_News);
            CkboxNotif_MP =  (CheckBox) findViewById(R.id.chkbox_activer_MP);
            CkboxNotif_Ally =  (CheckBox) findViewById(R.id.chkbox_activer_Ally);
            
            boutton_OK = (Button) findViewById(R.id.btnOK);
            boutton_Annuler= (Button) findViewById(R.id.btnAnnuler);
            
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
       
	}
	
    private void valider() {
		
		
	}
	private void annuler() {
		
		
	}

}