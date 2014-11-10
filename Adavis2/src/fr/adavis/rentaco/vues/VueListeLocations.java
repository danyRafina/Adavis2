package fr.adavis.rentaco.vues;

import java.awt.* ;

import javax.swing.* ;
import javax.swing.JTable.* ;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.modele.ModeleListeLocations;
import fr.adavis.rentaco.modele.ModeleLocations;

/** Vue dédiée à l'affichage de la liste des locations
 * 
 * @author xilim
 *
 */
public class VueListeLocations extends JPanel {

	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private ModeleLocations modele ;
	
	private ModeleListeLocations modeleTableauLocations ;
	private JTable tableauLocations ;
	
	/** Création de la vue de la liste des locations
	 * @param modele Modèle Locations
	 * @param controleur Controleur de l'application
	 */
	public VueListeLocations(ModeleLocations modele, Controleur controleur) {
		super();
		System.out.println("VueListeLocations::VueListeLocations()") ;
		this.modele = modele;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;
				
		boxEtiquette.add(new JLabel("Locations :")) ;
		boxEtiquette.add(Box.createHorizontalGlue()) ;
	
		modeleTableauLocations = new ModeleListeLocations(modele,controleur) ;
		tableauLocations = new JTable(modeleTableauLocations) ;
		tableauLocations.setRowHeight(30) ;
		
		this.appliquerRendu() ;

		JScrollPane spLocations = new JScrollPane(tableauLocations) ;
		//spLocations.setPreferredSize(new Dimension(1290,420)) ;
		spLocations.setPreferredSize(new Dimension(1090,420)) ;

		boxTableau.add(spLocations) ;
		
		boxPrincipal.add(boxEtiquette) ;
		boxPrincipal.add(boxTableau) ;
		
		this.add(boxPrincipal) ;
	}
	
	/** Actualiser le modèle du tableau
	 * 
	 */
	public void actualiser(){
		System.out.println("VueListeLocations::actualiser()") ;
		modeleTableauLocations = new ModeleListeLocations(modele,controleur) ;
		tableauLocations.setModel(modeleTableauLocations);
		this.appliquerRendu();
	}
	
	/** Appliquer le "rendu" au tableau
	 * 
	 */
	private void appliquerRendu(){
		System.out.println("VueListeLocations::appliquerRendu()") ;
		this.tableauLocations.getColumn("Numéro").setCellRenderer(new RenduCelluleLocation()) ;
		this.tableauLocations.getColumn("Enregistrement").setCellRenderer(new RenduCelluleLocation()) ;
		this.tableauLocations.getColumn("Date départ").setCellRenderer(new RenduCelluleLocation()) ;
		this.tableauLocations.getColumn("Date retour").setCellRenderer(new RenduCelluleLocation()) ;
		this.tableauLocations.getColumn("Client").setCellRenderer(new RenduCelluleLocation()) ;
		this.tableauLocations.getColumn("Véhicule").setCellRenderer(new RenduCelluleLocation()) ;
		this.tableauLocations.getColumn("Heure enregistrement").setCellRenderer(new RenduCelluleLocation()) ;
		this.tableauLocations.getColumn("Départ").setCellRenderer(new RenduBoutonLocation()) ;
		this.tableauLocations.getColumn("Retour").setCellRenderer(new RenduBoutonLocation()) ;
		this.tableauLocations.getColumn("Annulation").setCellRenderer(new RenduBoutonLocation()) ;
		
		this.tableauLocations.getColumn("Départ").setCellEditor(new EditeurBoutonLocation(new JCheckBox())) ; 
		this.tableauLocations.getColumn("Retour").setCellEditor(new EditeurBoutonLocation(new JCheckBox())) ; 
		this.tableauLocations.getColumn("Annulation").setCellEditor(new EditeurBoutonLocation(new JCheckBox())) ; 
	}
	
}
