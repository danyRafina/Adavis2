package fr.adavis.rentaco.vues;

import java.awt.* ;
import java.awt.event.* ;

import javax.swing.* ;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.modele.ModeleLocations;

/** Vue dédiée à la saisie des informations d'un nouveau client
 * 
 * @author xilim
 *
 */
public class VueNouveauClient extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private ModeleLocations modele ;
	
	private JTextField tfNom = new JTextField() ;
	private JTextField tfPrenom = new JTextField() ;
	private JTextField tfMobile = new JTextField() ;
	
	private JButton bEnregistrer = new JButton("Enregistrer") ;
	private JButton bAnnuler = new JButton("Annuler") ;
	
	
	/** Créer la vue de saisie d'un nouveau client
	 * 
	 * @param modele Le modèle
	 * @param controleur Le contrôleur
	 */
	public VueNouveauClient(ModeleLocations modele, Controleur controleur) {
		super();
		System.out.println("VueNouveauClient::VueNouveauClient()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		
		this.bEnregistrer.addActionListener(this) ;
		this.bAnnuler.addActionListener(this) ;
		Box boxPrincipal = Box.createVerticalBox() ;
		Box boxNom= Box.createHorizontalBox() ;
		Box boxPrenom = Box.createHorizontalBox() ;
		Box boxMobile = Box.createHorizontalBox() ;
		Box boxBouttons = Box.createHorizontalBox() ;
		
		boxNom.add(new JLabel("Nom : "));
		boxNom.add(this.tfNom);
		boxPrenom.add(new JLabel("Prénom : "));
		boxPrenom.add(this.tfPrenom);
		boxMobile.add(new JLabel("Mobile : "));
		boxMobile.add(this.tfMobile);
		boxBouttons.add(this.bEnregistrer);
		boxBouttons.add(this.bAnnuler);
		
		boxPrincipal.add(boxNom);
		boxPrincipal.add(boxPrenom);
		boxPrincipal.add(boxMobile);
		boxPrincipal.add(boxBouttons);
		
		this.add(boxPrincipal);


		
		
	}
	
	/** Gérer les actions de l'utilisateur
	 * 
	 * @param evenement L'action de l'utilisateur
	 */
	@Override
	public void actionPerformed(ActionEvent evenement) {
		System.out.println("VueNouveauClient::actionPerformed()") ;
		Object sourceEvt = evenement.getSource() ;
		if(sourceEvt == this.bEnregistrer){
			this.controleur.enregistrerClient(this.tfNom.getText().toUpperCase(),this.tfPrenom.getText(),this.tfMobile.getText()) ;
			this.actualiser();
			
		}
		if(sourceEvt == this.bAnnuler){
			this.controleur.annulerEnregistrerClient();
			this.actualiser();
		}
		
	}
	/** Actualiser le modèle du tableau
	 * 
	 */
	public void actualiser(){
		System.out.println("VueNouveauClient::actualiser()") ;
		this.tfNom.setText("");
		this.tfPrenom.setText("");
		this.tfMobile.setText("");
	}
	
}
