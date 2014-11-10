package fr.adavis.rentaco.vues;

import java.awt.FlowLayout;
import java.util.List;
import java.util.Locale;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fr.adavis.rentaco.controleur.Controleur;
import fr.adavis.rentaco.entites.Client;
import fr.adavis.rentaco.entites.Vehicule;
import fr.adavis.rentaco.modele.ModeleLocations;
import fr.adavis.rentaco.utilitaires.Dates;

/** Vue dédiée à la saisie des informations d'une nouvelle location
 * 
 * @author xilim
 *
 */
public class VueNouvelleLocation extends JPanel implements ActionListener, DocumentListener {

	private static final long serialVersionUID = 1L;
	private Controleur controleur ;
	private ModeleLocations modele ;
	
	private JComboBox cbClients = new JComboBox() ;
	private JComboBox cbVehicules = new JComboBox() ;
	
	private JTextField tfDateDepart = new JTextField(10) ;
	
	private JButton bEnregistrer = new JButton("Enregistrer") ;
	private JButton bAnnuler = new JButton("Annuler") ;
	private Date date = new Date();
	private List<Integer> numClient = new ArrayList<Integer>();
	
	/** Créer la vue de saisie d'une nouvelle location
	 * 
	 * @param modele Le modele
	 * @param controleur Le contrôleur
	 */
	public VueNouvelleLocation(ModeleLocations modele, Controleur controleur) {
		super();
		System.out.println("VueNouvelleLocation::VueNouvelleLocation()") ;
		this.modele = modele ;
		this.controleur = controleur ;
		
		this.tfDateDepart.getDocument().addDocumentListener(this) ;
		
		this.bEnregistrer.setEnabled(false) ;
		this.bEnregistrer.addActionListener(this) ;
		this.bAnnuler.addActionListener(this) ;

		for(Client client : this.modele.getClients()){
			this.numClient.add(client.getNumero());
			this.cbClients.addItem(client.getNom() + " " + client.getPrenom() + " (" + client.getNumero() + ")") ;
			//System.out.println(client.getNom() + " " + client.getPrenom() + " (" + client.getNumero() + ")");
		}
		
		for(Vehicule vehicule : this.modele.getVehicules()){
			this.cbVehicules.addItem(vehicule.getImmatriculation()) ;
		}
		
		Box boxPrincipal = Box.createVerticalBox();
		Box boxClient= Box.createHorizontalBox() ;
		Box boxDateDepart = Box.createHorizontalBox() ;
		Box boxVehicule = Box.createHorizontalBox() ;
		Box boxBouttons = Box.createHorizontalBox() ;
		
		boxClient.add(new JLabel("Client : "));
		boxClient.add(this.cbClients);
		boxDateDepart.add(new JLabel("Date Départ : "));
		boxDateDepart.add(this.tfDateDepart);
		boxVehicule.add(new JLabel("Vehicule : "));
		boxVehicule.add(this.cbVehicules);
		boxBouttons.add(this.bEnregistrer);
		boxBouttons.add(this.bAnnuler);
		
		boxPrincipal.add(boxClient);
		boxPrincipal.add(boxDateDepart);
		boxPrincipal.add(boxVehicule);
		boxPrincipal.add(boxBouttons);
		
		this.add(boxPrincipal);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) {
		System.out.println("VueNouvelleLocation::actionPerformed()") ;
		Object sourceEvt = evt.getSource() ;
		if(sourceEvt == this.bEnregistrer){
			int numeroClient = numClient.get(this.cbClients.getSelectedIndex());
			String uneImmVehicule = (String)this.cbVehicules.getSelectedItem();
			//System.out.println(numeroClient) ;
			this.controleur.enregistrerLocation(uneImmVehicule,numeroClient,Dates.parseString(this.tfDateDepart.getText())) ;
			this.actualiser();
		}
		else if(sourceEvt == this.bAnnuler) {
			System.out.println("VueNouvelleLocation::actionPerformed()::else if") ;
			this.controleur.annulerEnregistrerLocation();
			this.actualiser();
			
		}
	}
	/** Actualiser le champs de la date
	 * 
	 */
	public void actualiser(){
		System.out.println("VueNouvelleLocation::actualiser()") ;
		this.tfDateDepart.setText("");
		
		
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#changedUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void changedUpdate(DocumentEvent evt) {
		System.out.println("VueNouvelleLocation::changedUpdate()") ;
		this.repercuterSaisieDate() ;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#insertUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void insertUpdate(DocumentEvent evt) {
		System.out.println("VueNouvelleLocation::insertUpdate()") ;
		this.repercuterSaisieDate() ;
	}

	/* (non-Javadoc)
	 * @see javax.swing.event.DocumentListener#removeUpdate(javax.swing.event.DocumentEvent)
	 */
	@Override
	public void removeUpdate(DocumentEvent evt) {
		System.out.println("VueNouvelleLocation::actionPerformed()") ;
		this.repercuterSaisieDate() ;
	}
	
	/** Modifier l'état du bouton "Valider" en fonction de la valeur saisie dans le champ associé à la date de départ
	 * @throws ParseException 
	 * 
	 */
	private void repercuterSaisieDate(){
		System.out.println("VueNouvelleLocation::repercuterSaisieDate()") ;
		if(Dates.estDate(this.tfDateDepart.getText())){
			this.bEnregistrer.setEnabled(true) ;
			
			
		}
		else {
			this.bEnregistrer.setEnabled(false) ;
		}
	}


}
