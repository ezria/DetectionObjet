package Utilitaire;


import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

/**
 * 
 * @author Corentin, Arthur, Aymeric et Jules, dans le cadre d'un exercice de système expert à l'ESAIP - St Barthélémy d'Anjou - France
 * @category Boite de dialogue classique, permettant de selectionner un fichier.
 *
 **/
public abstract class ChoixFichier {

	public static File openDialogFile(){
		File repertoireCourant = null;
        try {
            repertoireCourant = new File(".").getCanonicalFile();
        } catch(IOException e) {}
        JFileChooser dialogue = new JFileChooser(repertoireCourant);
         
        dialogue.showOpenDialog(null);
        return dialogue.getSelectedFile();
	}
	public static File openDialogFolder(){
		File repertoireCourant = null;
        try {
            repertoireCourant = new File(".").getCanonicalFile();
         } catch(IOException e) {
        	 System.err.println(e.getMessage());
         }
         
        JFileChooser dialogue = new JFileChooser(repertoireCourant);
        dialogue.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        dialogue.showOpenDialog (null);
        return dialogue.getSelectedFile();
	}
}
