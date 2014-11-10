package fr.adavis.rentaco.entites;

import java.util.GregorianCalendar;
import java.util.Calendar;

import fr.adavis.rentaco.utilitaires.Dates;

/** Représenter une location
 * 
 * @author xilim
 *
 */
public class Location {
	
	public static final int EN_ATTENTE = 1 ;
	public static final int EN_COURS = 2 ;
	public static final int TERMINEE = 3 ;
	
	private int numero ;
	private GregorianCalendar dateEnreg ;
	private GregorianCalendar dateDepart ;
	private GregorianCalendar dateRetour ;
	private int etat = EN_ATTENTE ;
	
	private Vehicule vehicule ;
	private Client client ;
	
	/** Créer une location
	 * 
	 * @param numero Le numéro de la location
	 * @param vehicule Le véhicule
	 * @param client Le client
	 * @param dateDepart La date départ
	 */
	public Location(int numero, Vehicule vehicule, Client client, GregorianCalendar dateDepart) {
		super();
		this.numero = numero;
		this.dateEnreg = new GregorianCalendar() ;
		this.dateDepart = dateDepart;
		this.vehicule = vehicule ;
		this.client = client ;
	}
	
	/** Créer une location
	 * Départ ce jour
	 * @param numero Le numéro de la location
	 * @param vehicule Le véhicule
	 * @param client Le client
	 */
	public Location(int numero, Vehicule vehicule, Client client) {
		super();
		this.numero = numero;
		this.dateEnreg = new GregorianCalendar() ;
		this.dateDepart = new GregorianCalendar() ;
		this.vehicule = vehicule ;
		this.client = client ;
	}

	/** Obtenir le numéro de la location
	 * 
	 * @return Le numéro de la location
	 */
	public int getNumero() {
		return numero;
	}

	/** Modifier le numéro de la location
	 * 
	 * @param numero Le numéro de la location
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/** Obtenir la date d'enregistrement de la location
	 * 
	 * @return La date d'enregistrement de la location
	 */
	public GregorianCalendar getDateEnreg() {
		return dateEnreg;
	}

	/** Modifier la date d'enregistrement de la location
	 * 
	 * @param dateEnreg La date d'enregistrement de la location
	 */
	public void setDateEnreg(GregorianCalendar dateEnreg) {
		this.dateEnreg = dateEnreg;
	}

	/** Obtenir la date de départ
	 * 
	 * @return La date de départ
	 */
	public GregorianCalendar getDateDepart() {
		return dateDepart;
	}

	/** Modifier la date de départ
	 * 
	 * @param dateDepart La date de départ
	 */
	public void setDateDepart(GregorianCalendar dateDepart) {
		this.dateDepart = dateDepart;
	}

	/** Obtenir la date de retour
	 * 
	 * @return La date de retour
	 */
	public GregorianCalendar getDateRetour() {
		return dateRetour;
	}

	/** Modifier la date de retour
	 * 
	 * @param dateRetour La date de retour
	 */
	public void setDateRetour(GregorianCalendar dateRetour) {
		this.dateRetour = dateRetour;
	}

	/** Obtenir l'état de la location
	 * EN_ATTENTE : Location en attente de départ,
	 * EN_COURS : Location en cours,
	 * TERMINEE : Location terminée
	 * @return L'état de la location
	 */
	public int getEtat() {
		return etat;
	}

	/** Modifier l'état de la location
	 * EN_ATTENTE : Location en attente de départ,
	 * EN_COURS : Location en cours,
	 * TERMINEE : Location terminée
	 * @param etat L'état de la location 
	 */
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	/** Obtenir le véhicule
	 * 
	 * @return Le véhicule
	 */
	public Vehicule getVehicule() {
		return vehicule;
	}

	/** Modifier le véhicule
	 * 
	 * @param vehicule Le véhicule
	 */
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	/** Obtenir le client
	 * 
	 * @return Le client
	 */
	public Client getClient() {
		return client;
	}

	/** Modifier le client
	 * 
	 * @param client Le client
	 */
	public void setClient(Client client) {
		this.client = client;
	}
	
	/** Indique si la location est en attente
	 * 
	 * @return true si la location est en attente et false dans le cas contraire
	 */
	public boolean estEnAttente(){
		if(this.etat == EN_ATTENTE){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	/** Indique si la location est en cours
	 * 
	 * @return true si la location est en cour et false dans le cas contraire
	 */
	public boolean estEnCours(){
		if(this.etat == EN_COURS){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	/** Récupérer la partie heure d'enregistrement
	 * @return heure Partie heure 
	 */
	public int getHeureEng(){
		int heure = this.dateEnreg.get(Calendar.HOUR);;
		if(this.dateEnreg.get(Calendar.AM_PM) == Calendar.PM){
			heure +=12;
		}
		return heure;
	}
	
	/** Récupérer la partie minute d'une heure d'enregistrement
	 * @return minute Minute de l'heure d'enregistrement
	 */
	public int getMinutesEng(){
		int minutes = this.dateEnreg.get(Calendar.MINUTE);
		return minutes;
	}
	
	/** Indique si la location est terminée
	 * 
	 * @return true si la location est terminée et false dans le cas contraire
	 */
	public boolean estTerminee(){
		if(this.etat == TERMINEE){
			return true ;
		}
		else {
			return false ;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String depart, retour ;
		if(this.dateDepart != null){
			depart = Dates.parseDate(this.dateDepart) ;
		}
		else {
			depart = "" ;
		}
		if(this.dateRetour != null){
			retour = Dates.parseDate(this.dateRetour) ;
		}
		else {
			retour = "" ;
		}
		String sonEtat ;
		if(this.etat == Location.EN_ATTENTE){
			sonEtat = "En attente" ;
		}
		else if(this.etat == Location.EN_COURS){
			sonEtat = "En cours" ;
		}
		else {
			sonEtat = "Terminée" ;
		}
		
		return "Location [numero=" + numero + ", dateEnreg=" + Dates.parseDate(dateEnreg)
				+ ", dateDepart=" + depart + ", dateRetour=" + retour
				+ ", etat=" + sonEtat +  ", Véhicule="+ this.getVehicule().getImmatriculation() 
				+ ", Client=" + this.getClient().getNom() + " " + this.getClient().getPrenom() + "]";
	}

}
