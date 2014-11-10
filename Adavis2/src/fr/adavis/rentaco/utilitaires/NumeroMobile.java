package fr.adavis.rentaco.utilitaires;

/** Manipuler les numéros de mobiles
 * 
 * @author xilim
 *
 */
public class NumeroMobile {

	/** Formater la chaîne représentant un numéro de mobile
	 * 
	 * @param source Le numéro de mobile sans espaces
	 * @return Le numéro de mobile avec des espaces
	 */
	public static String formater(String source){
		String destination = source.substring(0, 2) ;
		for(int i = 1 ; i < 5 ; i++){
			destination = destination + " " + source.substring(2 * i, 2 * ( i + 1)) ;
		}
		return destination ;
	}
	
	/** Vérifier qu'une chaîne de caractères représente un numéro de mobile
	 * 
	 * @param source La chaîne de caractères
	 * @return true si la chaîne de caractères représente un numéro de mobile, et false dans le cas contraire
	 */
	public static boolean estNumeroMobile(String source){
		if(source.length() == 10 && ( source.substring(0,2).equals("06") || source.substring(0,2).equals("07"))){
			try {
				Integer.parseInt(source) ;
				return true ;
			}
			catch(NumberFormatException e){
				return false ;
			}
		}
		else {
			return false ;
		}
	}
}
