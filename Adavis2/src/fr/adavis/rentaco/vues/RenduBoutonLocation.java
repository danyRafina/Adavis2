package fr.adavis.rentaco.vues;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import fr.adavis.rentaco.entites.Location;
import fr.adavis.rentaco.modele.ModeleListeLocations;

/** Rendu des boutons du tableau des locations
 * 
 * @author xilim
 *
 */
public class RenduBoutonLocation extends JButton implements TableCellRenderer {

	/* (non-Javadoc)
	 * @see javax.swing.table.TableCellRenderer#getTableCellRendererComponent(javax.swing.JTable, java.lang.Object, boolean, boolean, int, int)
	 */
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		
		//System.out.println("RenduBoutonLocation::getTableCellRendererComponent()") ;
		if(value != null){
			this.setText(value.toString()) ;
			
		}
		else {
			this.setText("") ;
		}
		
		int etat = ((ModeleListeLocations)table.getModel()).getEtat(row) ;
		
		switch(etat){
			case Location.EN_ATTENTE :
				switch(column){
					case 6 :
						this.setEnabled(true) ;
						break ;
					case 7 :
						this.setEnabled(false) ;
						break ;
					case 8 :
						this.setEnabled(true) ;
						break ;
				}
				break ;
			case Location.EN_COURS :
				switch(column){
					case 6 :
						this.setEnabled(false) ;
						break ;
					case 7 :
						this.setEnabled(true) ;
						break ;
					case 8 :
						this.setEnabled(false) ;
						break ;
				}
				break ;
			case Location.TERMINEE :
				switch(column){
					case 6 :
						this.setEnabled(false) ;
						break ;
					case 7 :
						this.setEnabled(false) ;
						break ;
					case 8 :
						this.setEnabled(false) ;
						break ;
				}
				break ;
		}
		
		return this ;
	}
	
	

}
