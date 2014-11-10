package fr.adavis.rentaco.modele ;

import java.util.* ;

import javax.swing.table.* ;

import fr.adavis.rentaco.entites.Client;
import fr.adavis.rentaco.utilitaires.NumeroMobile;

/** Modèle du tableau des clients
 * 
 * @author xilim
 *
 */
public class ModeleListeClients extends AbstractTableModel {
	
	private List<Client> clients ;
	private final String[] entetes = {"Numéro","Nom","Prénom","Mobile"} ;
	
	private ModeleLocations modele ;
	
	/** Créer le modèle de la liste des clients
	 * 
	 * @param modele Le modèle de l'application
	 */
	public ModeleListeClients(ModeleLocations modele){
		super() ; 
		System.out.println("ModeleListeClients::ModeleListeClients()") ;
		this.modele = modele ;
		clients = modele.getClients() ;
	}
	
	/** Obtenir le nombre de lignes
	 * 
	 * @return Le nombre de lignes
	 */
	public int getRowCount(){
		//System.out.println("ModeleListeClients::getRowCount()") ;
		return clients.size() ;
	}

	/** Obtenir le nombre de colonnes
	 * 
	 * @return Le nombre de colonnes
	 */
	public int getColumnCount(){
		//System.out.println("ModeleListeClients::getColumnCount()") ;
		return entetes.length ;
	}
	
	/** Obtenir le nom d'une colonne
	 * 
	 * @param indiceColonne L'indice de la colonne
	 * @return Le nom de la colonne
	 */
	public String getColumnName(int indiceColonne){
		//System.out.println("ModeleListeClients::getColumnName()") ;
		return entetes[indiceColonne] ;
	}
	
	/** Obtenir la valeur d'une cellule
	 * 
	 * @param indiceLigne L'indice de la ligne
	 * @param indiceColonne L'indice de la colonne
	 * @return La valeur de la cellule
	 */
	public Object getValueAt(int indiceLigne, int indiceColonne){
		//System.out.println("ModeleListeClients::getValueAt()") ;
		switch(indiceColonne){
			case 0 : 
				return new Integer(clients.get(indiceLigne).getNumero()) ;
			case 1 : 
				return clients.get(indiceLigne).getNom() ;
			case 2 :
				return clients.get(indiceLigne).getPrenom() ;
			case 3 : 
				return NumeroMobile.formater(clients.get(indiceLigne).getMobile()) ;
			default :
				return null ;
		}
	}
	
}
