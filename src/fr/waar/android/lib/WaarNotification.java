package fr.waar.android.lib;



public class WaarNotification {
	
	public String categorie;
	public Integer nombre_notifications;
	public Integer id_notification;
	public Class<?> classCible = null;
	public String url=  "";
	
	private String texte_notification;

	public WaarNotification(String categorie, int nombre_notifications,
			int id_notification, String texte_notification, Class<?> className) {
		super();
		this.categorie = categorie;
		this.nombre_notifications = nombre_notifications;
		this.id_notification = id_notification;
		this.texte_notification = texte_notification;
		this.classCible = className;
	}
	
	public WaarNotification(String categorie, int nombre_notifications,
			int id_notification, String texte_notification, String url) {
		super();
		this.categorie = categorie;
		this.nombre_notifications = nombre_notifications;
		this.id_notification = id_notification;
		this.texte_notification = texte_notification;
//		this.intentCible = new WaaRActivity(url);
		this.url = url;
		//this.intentCible = new Intent(WaaRActivity.this,WaaRActivity.class);
	}
	
	public WaarNotification(String categorie, int nombre_notifications,
			int id_notification, String texte_notification) {
		super();
		this.categorie = categorie;
		this.nombre_notifications = nombre_notifications;
		this.id_notification = id_notification;
		this.texte_notification = texte_notification;
	}

	public String getTexte_notification() {
		String txt =  this.texte_notification.replace("%s", this.nombre_notifications.toString());
		if (this.nombre_notifications > 1)
		{
			txt =  txt.replace("(s)", "s");
			txt =  txt.replace("(x)", "x");
		}
		else if(this.nombre_notifications == 1)
		{
			txt =  txt.replace("(s)", "");
			txt =  txt.replace("(x)", "");
		}
		return txt;
	}
}
