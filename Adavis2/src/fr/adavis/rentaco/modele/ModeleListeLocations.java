package fr.adavis.rentaco.modele ;

import java.util.* ;
import java.awt.event.* ;

import javax.swing.* ;
import javax.swing.table.* ;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.entites.Location;
import fr.adavis.rentaco.entites.Vehicule;
import fr.adavis.rentaco.utilitaires.Dates;

/** Modèle du tableau des locations
 * 
 * @author xilim
 *
 */
public class ModeleListeLocations extends AbstractTableModel {
	private java.util.List<Location> locations ;
	
	private final String[] entetes = {"Numéro","Enregistrement","Date départ","Date retour","Client","Véhicule","Heure enregistrement","Départ","Retour","Annulation"} ;
	
	private ModeleLocations modele ;
	private Controleur controleur ;
	
	/** Créer le modèle de la liste des locations
	 * 
	 * @param modele Le modèle de l'application
	 * @param controleur Le contrôleur de l'application
	 */
	public ModeleListeLocations(ModeleLocations modele, Controleur controleur){
		super() ; 
		System.out.println("ModeleListeLocations::ModeleListeLocations()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		locations = modele.getLocations() ;
	}
	
	/** Obtenir le contrôleur
	 * 
	 * @return Le contrôleur
	 */
	public Controleur getControleur(){
		//System.out.println("ModeleListeLocations::getControleur()") ;
		return this.controleur ;
	}
	
	/** Obtenir le modèle de l'application
	 * 
	 * @return Le modèle de l'application
	 */
	public ModeleLocations getModele(){
		//System.out.println("ModeleListeLocations::getModele()") ;
		return this.modele ;
	}
	
	/** Obtenir le numéro de la location
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @return Le numéro de de la location
	 */
	public int getNumeroLocation(int indiceLigne){
		//System.out.println("ModeleListeLocations::getNumeroLocation()") ;
		return locations.get(indiceLigne).getNumero() ;
	}
	
	/** Obtenir le véhicule
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @return Le véhicule
	 */
	public Vehicule getVehicule(int indiceLigne){
		//System.out.println("ModeleListeLocations::getVehicule()") ;
		return locations.get(indiceLigne).getVehicule() ;
	}
	
	/** Obtenir le nombre de lignes
	 * 
	 * @return Le nombre de lignes
	 */
	@Override
	public int getRowCount(){
		//System.out.println("ModeleListeLocations::getRowCount()") ;
		return locations.size() ;
	}

	/** Obtenir le nombre de colonnes
	 * 
	 * @return Le nombre de colonnes
	 */
	@Override
	public int getColumnCount(){
		//System.out.println("ModeleListeLocations::getColumnCount()") ;
		return entetes.length ;
	}
	
	/** Obtenir le nom d'une colonne
	 * 
	 * @param indiceColonne L'indice de la colonne
	 * @return Le nom de la colonne
	 */
	@Override
	public String getColumnName(int indiceColonne){
		//System.out.println("ModeleListeLocations::getColumnName()") ;
		return entetes[indiceColonne] ;
	}
	
	/** Obtenir la classe d'une colonne
	 * 
	 * @param indiceColonne Le numéro de la colonne
	 * @return La classe de la colonne
	 */
	@Override
	public Class getColumnClass(int indiceColonne){
		//System.out.println("ModeleListeLocations::getColumnClass()") ;
		switch(indiceColonne){
			case 0 :
				return Integer.class ;
			case 1 :
				return String.class ;
			case 2 : 
				return String.class ;
			case 3 :
				return String.class ;
			case 4 :
				return String.class ;
			case 5 :
				return String.class ;
			case 6 :
				return String.class ;
			case 7 :
				return JButton.class ;
			case 8 :
				return JButton.class ;
			case 9 :
				return JButton.class ;
			default :
				return Object.class ;
		}
	}
	
	/** Obtenir la valeur d'une cellule
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @param indiceColonne L'indice de la colonne
	 * @return La valeur de la cellule
	 */
	@Override
	public Object getValueAt(int indiceLigne, int indiceColonne){
		//System.out.println("ModeleListeLocations::getControleur()") ;
		switch(indiceColonne){
			case 0 : 
				return new Integer(locations.get(indiceLigne).getNumero()) ;
			case 1 : 
				return Dates.parseDate(locations.get(indiceLigne).getDateEnreg()) ;
			case 2 :
				return Dates.parseDate(locations.get(indiceLigne).getDateDepart()) ;
			case 3 : 
				if(locations.get(indiceLigne).getDateRetour() == null){
					return "" ;
				}
				else {
					return Dates.parseDate(locations.get(indiceLigne).getDateRetour()) ;
				}
			case 4 : 
				return locations.get(indiceLigne).getClient().getNom() + " " 
						+ locations.get(indiceLigne).getClient().getPrenom() + " ("
						+ locations.get(indiceLigne).getClient().getNumero() + ")" ;
			case 5 :
				return locations.get(indiceLigne).getVehicule().getImmatriculation() ;
			case 6 :
				return locations.get(indiceLigne).getHeureEng() + ":"+locations.get(indiceLigne).getMinutesEng();			
			case 7 :
				return "Enreg." ;
			case 8 :
				return "Enreg." ;
			case 9 :
				return "Annuler" ;
			default :
				return null ;
		}
	}
	
	/** Spécifier si une cellule est éditable
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @param indiceColonne L'indice de la colonne
	 * @return true si la cellule est éditable, et false dans le cas contraire
	 */
	@Override
	public boolean isCellEditable(int indiceLigne, int indiceColonne){
		//System.out.println("ModeleListeLocations::isCellEditable()") ;
		int etat = locations.get(indiceLigne).getEtat() ;
		
		switch(indiceColonne){
			case 6 :
				switch(etat){
					case Location.EN_ATTENTE :
						return true ;
					case Location.EN_COURS :
						return false ;
					case Location.TERMINEE :
						return false ;
				}
				break ;
			case 7 :
				switch(etat){
					case Location.EN_ATTENTE :
						return false ;
					case Location.EN_COURS :
						return true ;
					case Location.TERMINEE :
						return false ;
				}
				break ;
			case 8 :
				switch(etat){
					case Location.EN_ATTENTE :
						return true ;
					case Location.EN_COURS :
						return false ;
					case Location.TERMINEE :
						return false ;
				}
				break ;
		}
		return false ;
	}
	
	/** Obtenir l'état de la location
	 * 
	 * @param indiceLigne L'indice de la ligne 
	 * @return L'état de la location
	 */
	public int getEtat(int indiceLigne){
		//System.out.println("ModeleListeLocations::getEtat()") ;
		return locations.get(indiceLigne).getEtat() ;
	}
	
}
