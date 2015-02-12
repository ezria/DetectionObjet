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
		//on attribut la BuferedImage à la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreUneForme.getCheminSauv();



	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en paramètre un objet de la classe FenetreUneForme permet d'afficher les explications de la méthode
	 */
	public void run (){
		fenetre.setTexteFinTraitement("Début du traitement, veuillez patienter");
		//Créations d’une liste de pixels à partir de l’image, la fonctions récupererToutLesPixels est en général une fonction interne à chacun des langages de programmation existant, c’est pour cela qu’elle n’est pas exliqué ci dessous.
		ArrayList<Pixel> pixelsImage = image.recupererToutLesPixels();

		//création de la forme accueillant les pixels de couleurs noir trouvé.
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
			
			// cette fonction permet de sauvergarder un carré de pixels à partir d’une image, en définissant la position du pixel en haut à gauche ainsi que se taille.
			TraitementImage.saveFile(monFichier, image.creerSousImage(forme.RecupererPosPixelHautGauche(), forme.getTaille()));
			fenetre.setTexteFinTraitement("Traitement terminé, votre image se trouve dans le dossier " + cheminSauv);		
		}
		catch (Exception e){
			e.printStackTrace();
			fenetre.setTexteFinTraitement("Erreur du à la sauvegarde du fichier: " + e.toString());
		}	
		

		
	}
}
