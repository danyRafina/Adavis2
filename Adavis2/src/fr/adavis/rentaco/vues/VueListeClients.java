package fr.adavis.rentaco.vues;

import java.util.* ;
import java.awt.* ;

import javax.swing.* ;
import javax.swing.JTable.* ;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.modele.ModeleListeClients;
import fr.adavis.rentaco.modele.ModeleLocations;

/** Vue dédiée à l'affichage de la liste des clients
 * 
 * @author xilim
 *
 */
public class VueListeClients extends JPanel {

	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private ModeleLocations modele ;
	
	private ModeleListeClients modeleTableauClients ;
	private JTable tableauClients ;
	
	/** Créer la vue dédiée à l'affichage de la liste des clients
	 * 
	 * @param modele Le modèle
	 * @param controleur Le contrôleur
	 */
	public VueListeClients(ModeleLocations modele, Controleur controleur) {
		super();
		System.out.println("VueListeClients::VueListeClients()") ;
		
		this.modele = modele ;
		this.controleur = controleur ;
		
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxEtiquette = Box.createHorizontalBox() ;
		Box boxTableau = Box.createHorizontalBox() ;

		boxEtiquette.add(new JLabel("Clients :")) ;
		boxEtiquette.add(Box.createHorizontalGlue()) ;
		
		modeleTableauClients = new ModeleListeClients(modele) ;
		tableauClients = new JTable(modeleTableauClients) ;
		tableauClients.setRowHeight(30) ;
		
		JScrollPane spClients = new JScrollPane(tableauClients) ;
		//spClients.setPreferredSize(new Dimension(1290,420)) ;
		spClients.setPreferredSize(new Dimension(1090,420)) ;
		
		boxTableau.add(spClients) ;
		
		boxPrincipal.add(boxEtiquette) ;
		boxPrincipal.add(boxTableau) ;
		
		this.add(boxPrincipal) ;
		
	}
	
	/** Actualiser le modèle du tableau
	 * 
	 */
	public void actualiser(){
		System.out.println("VueListeClients::actualiser()") ;
		modeleTableauClients = new ModeleListeClients(modele) ;
		tableauClients.setModel(modeleTableauClients) ;
	}
	
}
