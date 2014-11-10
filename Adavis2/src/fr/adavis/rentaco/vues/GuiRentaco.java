package fr.adavis.rentaco.vues;

import java.awt.CardLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.modele.ModeleLocations;

/** Vue principale 
 * 
 * @author xilim
 *
 */
public class GuiRentaco extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private ModeleLocations modele ;
	private Controleur controleur ;
		
	private JMenuItem itemQuitter ;
	private JMenuItem itemVisualiserLocations ;
	private JMenuItem itemEnregistrerLocation ;
	private JMenuItem itemVisualiserClients ;
	private JMenuItem itemEnregistrerClient ;
	private JMenuItem itemVisualiserVehicules ;
	
	private VueListeLocations vueVisualiserLocations ;
	private VueListeClients vueVisualiserClients ;
	private VueListeVehicules vueVisualiserVehicules ;
	private VueNouveauClient vueSaisirClient ;
	private VueNouvelleLocation vueSaisirLocation ;
	
	private CardLayout vues ;
	private Container conteneur ;

	/** Construire la vue principale de l'application
	 * 
	 * @param modele Le modèle
	 * @param controleur Le contrôleur
	 */
	public GuiRentaco(ModeleLocations modele, Controleur controleur) {
		super();
		System.out.println("GuiRentaco::GuiRentaco()") ;
		this.modele = modele;
		this.controleur = controleur ;
		this.controleur.setVuePrincipales(this);
		
		this.setTitle("Rentaco") ;
		//this.setSize(1300,500) ; 
		this.setSize(1120,520) ;
		this.setLocationRelativeTo(null) ;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE) ;
		
		this.vues = new CardLayout(2,2) ;
		this.conteneur = this.getContentPane() ;
		this.conteneur.setLayout(this.vues) ;
		
		vueVisualiserVehicules = new VueListeVehicules(modele,controleur) ;
		vueVisualiserClients = new VueListeClients(modele,controleur) ;
		vueVisualiserLocations = new VueListeLocations(modele,controleur) ;
		vueSaisirClient = new VueNouveauClient(modele,controleur) ;
		vueSaisirLocation = new VueNouvelleLocation(modele,controleur) ;
		
		this.conteneur.add(vueVisualiserVehicules,"Liste véhicules") ;
		this.conteneur.add(vueVisualiserClients,"Liste clients") ;
		this.conteneur.add(vueVisualiserLocations,"Liste locations") ;
		this.conteneur.add(vueSaisirClient,"Nouveau client") ;
		this.conteneur.add(vueSaisirLocation,"Nouvelle location") ;
		
		this.vues.show(this.conteneur, "Liste locations");
		
		this.creerBarreMenus() ;
		this.setVisible(true) ;
		
	}
	
	/** Obtenir le contrôleur
	 * 
	 * @return Le contrôleur
	 */
	public Controleur getControleur() {
		System.out.println("GuiRentaco::getControleur()") ;
		return this.controleur;
	}

	/** Modifier le contrôleur
	 * 
	 * @param controleur Le nouveau contrôleur
	 */
	public void setControleur(Controleur controleur) {
		System.out.println("GuiRentaco::setControleur()") ;
		this.controleur = controleur;
	}

	/** Obtenir le modèle
	 * 
	 * @return Le modèle
	 */
	public ModeleLocations getModele() {
		System.out.println("GuiRentaco::getModele()") ;
		return this.modele;
	}

	/** Modifier le modèle
	 * 
	 * @param modele Le nouveau modèle
	 */
	public void setModele(ModeleLocations modele) {
		System.out.println("GuiRentaco::setModele()") ;
		this.modele = modele;
	}

	/** Créer la barre de menus
	 * 
	 */
	private void creerBarreMenus(){
		System.out.println("GuiRentaco::creerBarreMenus()") ;
		JMenuBar barreMenus = new JMenuBar() ;
		
		JMenu menuFichier = new JMenu("Fichier") ;
		this.itemQuitter = new JMenuItem("Quitter") ;
		this.itemQuitter.addActionListener(this) ;
		menuFichier.add(this.itemQuitter) ;
		
		JMenu menuLocations = new JMenu("Locations") ;
		this.itemVisualiserLocations = new JMenuItem("Liste des locations") ;
		this.itemVisualiserLocations.addActionListener(this) ;
		menuLocations.add(this.itemVisualiserLocations) ;
		this.itemEnregistrerLocation = new JMenuItem("Nouvelle location") ;
		this.itemEnregistrerLocation.addActionListener(this) ;
		menuLocations.add(this.itemEnregistrerLocation) ;
		
		JMenu menuClients = new JMenu("Clients") ;
		this.itemVisualiserClients = new JMenuItem("Liste des clients") ;
		this.itemVisualiserClients.addActionListener(this) ;
		menuClients.add(this.itemVisualiserClients) ;
		this.itemEnregistrerClient = new JMenuItem("nouveau client") ;
		this.itemEnregistrerClient.addActionListener(this) ;
		menuClients.add(this.itemEnregistrerClient) ;
		
		JMenu menuVehicules = new JMenu("Véhicules") ;
		this.itemVisualiserVehicules = new JMenuItem("Liste des véhicules") ;
		this.itemVisualiserVehicules.addActionListener(this) ;
		menuVehicules.add(this.itemVisualiserVehicules) ;
		
		barreMenus.add(menuFichier) ;
		barreMenus.add(menuLocations) ;
		barreMenus.add(menuClients) ;
		barreMenus.add(menuVehicules) ;
		
		this.setJMenuBar(barreMenus) ;
	}
	
	/** Basculer sur l'une des vues
	 * 
	 * @param nomVue La vue qui doit passer au premier plan
	 */
	public void changerDeVue(String nomVue){
		System.out.println("GuiRentaco::changerVue()") ;
		if(nomVue.equals("Liste véhicules")){
			this.vueVisualiserVehicules.actualiser() ;
		}
		else if(nomVue.equals("Liste clients")){
			this.vueVisualiserClients.actualiser() ;
		}
		else if(nomVue.equals("Liste locations")){
			this.vueVisualiserLocations.actualiser() ;
		}
		this.vues.show(this.conteneur,nomVue) ;
	}

	/** Gérer les actions de l'utilisateur
	 * 
	 * @param evenement L'événement utilisateur
	 */
	@Override
	public void actionPerformed(ActionEvent evenement) {
		System.out.println("----------------------------------------") ;
		System.out.println("GuiRentaco::actionPerformed()") ;
		Object sourceEvt = evenement.getSource() ;
		
		if(sourceEvt == this.itemQuitter){
			this.controleur.quitterApplication() ;
		}
		else if(sourceEvt == this.itemEnregistrerClient){
			this.controleur.saisirNouveauClient() ;
		}
		else if(sourceEvt == this.itemEnregistrerLocation){
			this.controleur.saisirNouvelleLocation() ;
		}
		else if(sourceEvt == this.itemVisualiserClients){
			this.controleur.visualiserClients() ;
		}
		else if(sourceEvt == this.itemVisualiserLocations){
			this.controleur.visualiserLocations() ;
		}
		else if(sourceEvt == this.itemVisualiserVehicules){
			this.controleur.visualiserVehicules() ;
		}
		
	}
	
}
