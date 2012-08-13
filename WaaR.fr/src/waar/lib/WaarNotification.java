package waar.lib;

import waar.Activities.WaaRActivity;


public class WaarNotification {
	
	public String categorie;
	public Integer nombre_notifications;
	public Integer id_notification;
	public WaaRActivity intentCible;
	
	private String texte_notification;

	public WaarNotification(String categorie, int nombre_notifications,
			int id_notification, String texte_notification, WaaRActivity class1) {
		super();
		this.categorie = categorie;
		this.nombre_notifications = nombre_notifications;
		this.id_notification = id_notification;
		this.texte_notification = texte_notification;
		this.intentCible = class1;
	}
	
	public WaarNotification(String categorie, int nombre_notifications,
			int id_notification, String texte_notification, String url) {
		super();
		this.categorie = categorie;
		this.nombre_notifications = nombre_notifications;
		this.id_notification = id_notification;
		this.texte_notification = texte_notification;
		this.intentCible = new WaaRActivity(url);
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
		return this.texte_notification.replace("%s", this.nombre_notifications.toString());
	}
	
	
	
}
