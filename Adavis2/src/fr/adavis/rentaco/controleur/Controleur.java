package fr.adavis.rentaco.controleur;

import java.util.* ;

import fr.adavis.rentaco.entites.Client;
import fr.adavis.rentaco.entites.Location;
import fr.adavis.rentaco.entites.Vehicule;
import fr.adavis.rentaco.modele.ModeleLocations;
import fr.adavis.rentaco.vues.GuiRentaco;

/** Contrôleur de l'application
 * 
 * @author xilim
 *
 */
public class Controleur {

	private GuiRentaco vuePrincipale ;
	private ModeleLocations modele ;
	
	/** Créer le contrôleur
	 * 
	 * @param modele Le modèle
	 */
	public Controleur(ModeleLocations modele) {
		super();
		System.out.println("Controleur::Controleur()") ;
		this.modele = modele;
	}
	
	/** Obtenir la vue principale
	 * 
	 * @return La vue principale
	 */
	public GuiRentaco getVuePrincipale() {
		System.out.println("Controleur::getVuePrincipale()") ;
		return vuePrincipale ;
	}

	/** Modifier la vue principale
	 * 
	 * @param vuePrincipale La nouvelle vue principale
	 */
	public void setVuePrincipales(GuiRentaco vuePrincipale) {
		System.out.println("Controleur::setVuePrincipale()") ;
		this.vuePrincipale = vuePrincipale ;
	}
	
	/** Visualiser la liste des clients sous une forme tabulaire
	 * 
	 */
	public void visualiserClients(){
		System.out.println("Controleur::visualiserClients()") ;
		this.vuePrincipale.changerDeVue("Liste clients");
	}
	
	/** Visualiser la liste des locations sous une forme tabulaire
	 * 
	 */
	public void visualiserLocations(){
		System.out.println("Controleur::visualiserLocations()") ;
		this.vuePrincipale.changerDeVue("Liste locations") ;
	}
	
	/** Visualiser la liste des véhicules sous une forme tabulaire
	 * 
	 */
	public void visualiserVehicules(){
		System.out.println("Controleur::visualiserVehicules()") ;
		this.vuePrincipale.changerDeVue("Liste véhicules");
	}
	
	/** Afficher le formulaire de saisie d'un nouveau client
	 * 
	 */
	public void saisirNouveauClient(){
		System.out.println("Controleur::saisirNouveauClient()") ;
		this.vuePrincipale.changerDeVue("Nouveau client");
	}
	
	/** Afficher le formulaire de saisie d'une nouvelle location
	 * 
	 */
	public void saisirNouvelleLocation(){
		System.out.println("Controleur::saisirNouvelleLocation()") ;
		this.vuePrincipale.changerDeVue("Nouvelle location");
	}
	
	/** Enregistrer un nouveau client
	 * 
	 * @param nom Le nom du client
	 * @param prenom Le prénom du client
	 * @param mobile Le numéro de mobile du client
	 */
	public void enregistrerClient(String nom, String prenom, String mobile){
		System.out.println("Controleur::enregistrerClient()") ;
		this.modele.ajouterClient(nom,prenom,mobile) ;
		this.vuePrincipale.changerDeVue("Liste clients") ;
		
	}
	
	/** Annuler l'enregistrement d'un nouveau client
	 * 
	 */
	public void annulerEnregistrerClient(){
		System.out.println("Controleur::annulerEnregistrerClient()") ;
		this.vuePrincipale.changerDeVue("Liste clients") ;
	}
	
	/** Enregistrer une nouvelle location
	 * 
	 * @param immVehicule Le numéro d'immatriculation du véhicule réservé
	 * @param numClient Le numéro du client
	 * @param dateDepart La date de début de location
	 */
	public void enregistrerLocation(String immVehicule,int numClient,GregorianCalendar dateDepart){
		System.out.println("Controleur::enregistrerLocation()") ;
		this.modele.ajouterLocation(immVehicule,numClient,dateDepart) ;
		this.vuePrincipale.changerDeVue("Liste locations") ;
	}
	
	/** Annuler l'enregistrement d'une nouvelle location
	 * 
	 */
	public void annulerEnregistrerLocation(){
		System.out.println("Controleur::annulerEnregistrerLocation()") ;
		this.vuePrincipale.changerDeVue("Liste locations") ;
	}
	
	/** Enregistrer le départ d'un véhicule
	 * Début de la location
	 * @param numeroLocation Le numéro de la location
	 */
	public void enregistrerDepart(int numeroLocation){
		System.out.println("Controleur::enregistrerDepart()") ;
		this.modele.enregistrerDepartVehicule(numeroLocation) ;
		this.vuePrincipale.changerDeVue("Liste locations") ;
	}
	
	/** Enregistrer le retour d'un véhicule
	 * Fin d'une location
	 * @param numeroLocation Le numéro de la location
	 * @param nbKm Le nombre de km relevé
	 */
	public void enregistrerRetour(int numeroLocation, int nbKm){
		System.out.println("Controleur::enregistrerRetour()") ;
		this.modele.enregistrerRetourVehicule(numeroLocation,nbKm) ;
		this.vuePrincipale.changerDeVue("Liste locations") ;
	}
	
	/** Annuler une location
	 * Suppression de la location
	 * @param numeroLocation Le numéro de la location
	 */
	public void annulerLocation(int numeroLocation){
		System.out.println("Controleur::annulerLocation()") ;
		this.modele.supprimerLocation(numeroLocation) ;
		this.vuePrincipale.changerDeVue("Liste locations") ;
	}
	
	/** Quitter l'application
	 * 
	 */
	public void quitterApplication(){
		System.out.println("Controleur::quitterApplication()") ;
		System.exit(0) ;
	}
	
	/** Visualiser les données de la couche "Logique Métier"
	 * 
	 */
	public void visualiserModele(){
		System.out.println("Controleur::visualiserModele()") ;
		System.out.println("") ;
		System.out.println("------------------ Locations ----------------------------") ;
		for(Location location : this.modele.getLocations()){
			System.out.println(location) ;
		}
		System.out.println("------------------- Clients -----------------------------") ;
		for(Client client : this.modele.getClients()){
			System.out.println(client) ;
		}
		System.out.println("------------------ Véhicules ----------------------------") ;
		for(Vehicule vehicule : this.modele.getVehicules()){
			System.out.println(vehicule) ;
		}
		System.out.println("") ;
	}
	
}
