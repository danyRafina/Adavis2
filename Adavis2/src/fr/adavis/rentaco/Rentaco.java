package fr.adavis.rentaco;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.modele.ModeleLocations;
import fr.adavis.rentaco.vues.GuiRentaco;

/** Classe principale de l'application
 * 
 * @author xilim
 *
 */
public class Rentaco {

	/** Point de départ de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Rentaco::main()") ;
		
		System.out.println(" [Création du modèle]") ;
		ModeleLocations modele = new ModeleLocations() ;
		
		System.out.println(" [Création du contrôleur]") ;
		Controleur controleur = new Controleur(modele) ;
		
		System.out.println(" [Création de la vue principale]") ;
		new GuiRentaco(modele,controleur) ;
	}

}
