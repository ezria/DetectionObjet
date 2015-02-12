package Thread;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import Interface.FenetreBase;
import Objet.Forme;
import Objet.Image;
import Objet.Pixel;
import Utilitaire.TraitementImage;

public class ThreadTraitementImageUneForme extends Thread {
	
	private Image image;
	private FenetreBase fenetre;
	private String cheminSauv;
 	
	
	public ThreadTraitementImageUneForme(BufferedImage buffImage, FenetreBase fenetreUneForme) {
		super();
		this.fenetre = fenetreUneForme;
		//on attribut la BuferedImage � la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreUneForme.getCheminSauv();



	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en param�tre un objet de la classe FenetreUneForme permet d'afficher les explications de la m�thode
	 */
	public void run (){
		fenetre.setTexteFinTraitement("D�but du traitement, veuillez patienter");
		//Cr�ations d�une liste de pixels � partir de l�image, la fonctions r�cupererToutLesPixels est en g�n�ral une fonction interne � chacun des langages de programmation existant, c�est pour cela qu�elle n�est pas exliqu� ci dessous.
		ArrayList<Pixel> pixelsImage = image.recupererToutLesPixels();

		//cr�ation de la forme accueillant les pixels de couleurs noir trouv�.
		Forme forme = new Forme();

		//Cette boucle permet de parcourir tout les pixels de la liste.
		for (Pixel p : pixelsImage){ 
			
			//si le pixel et de couleur noir alors on le sauvegarde dans la forme.
			if (p.getCouleurPixel().equalRgb(new int []{0,0,0}))
				forme.ajoutPixel(p);
		}
		
		try{
			//creation d'un fichier pour sauvegarder l'image
			File monFichier = new File(cheminSauv +"/formeUnique.png");
			
			// cette fonction permet de sauvergarder un carr� de pixels � partir d�une image, en d�finissant la position du pixel en haut � gauche ainsi que se taille.
			TraitementImage.saveFile(monFichier, image.creerSousImage(forme.RecupererPosPixelHautGauche(), forme.getTaille()));
			fenetre.setTexteFinTraitement("Traitement termin�, votre image se trouve dans le dossier " + cheminSauv);		
		}
		catch (Exception e){
			e.printStackTrace();
			fenetre.setTexteFinTraitement("Erreur du � la sauvegarde du fichier: " + e.toString());
		}	
		

		
	}
}
