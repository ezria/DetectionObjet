
package Thread;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import Interface.FenetreBase;
import Objet.Forme;
import Objet.Image;
import Objet.ListeCouleurPixels;
import Objet.ListePixels;
import Objet.Pixel;
import Utilitaire.TraitementImage;

public class ThreadTraitementMultiFormeMultiColor extends Thread{
	private Image image;
	private FenetreBase fenetre;
	private String cheminSauv;
 	
	
	public ThreadTraitementMultiFormeMultiColor(BufferedImage buffImage, FenetreBase fenetreMultiFormeMultiColor) {
		super();
		this.fenetre = fenetreMultiFormeMultiColor;
		//on attribut la BuferedImage � la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreMultiFormeMultiColor.getCheminSauv();



	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en param�tre un objet de la classe FenetreUneForme permet d'afficher les explications de la m�thode
	 */
	public void run (){
		fenetre.setTexteFinTraitement("D�but du traitement, veuillez patienter");
		
		//Cr�ations d�une listePixels � partir de l�image, la fonction r�cupereToutLesPixels est en g�n�ral une fonction interne � chacun des langages de programmation existant, c�est pour cela qu�elle n�est pas expliqu�e ci-dessous.
		ListeCouleurPixels listeCouleurPixels = new ListeCouleurPixels();
		for (Pixel p : image.recupererToutLesPixels()){
			listeCouleurPixels.ajoutePixel(p);
		}

		//Cr�ation d'une liste de Forme permettant de sauvegarder les formes trouv�es.
		ArrayList<Forme> formes = new ArrayList<Forme>();
		ListePixels pixelsCouleur = listeCouleurPixels.recupererListeAutresPixels();
		
		//Cette boucle permet de parcourir tout les pixels de la liste en supprimant toujours le premier pixels.
		while(pixelsCouleur.taille() != 0){
		
			//cr�ation d'une forme accueillant le pixel de couleurs trouv�.
			Forme forme = new Forme();
			forme.ajoutPixel(pixelsCouleur.recuperePixel(0));
			formes.add(forme);
			//Cr�ation d'une pile de pixels permettant de sauvegarder les pixels � v�rifier plus tard (explication plus loin)
			ArrayList<Pixel> pilePixel = new ArrayList<Pixel>();
			
			//on ajoute le premier pixel de couleur noir trouv� � la pile.
			pilePixel.add(pixelsCouleur.recuperePixel(0));
			
			//on supprime le pixel afin que la boucle puisse se terminer.
			pixelsCouleur.supprimePixel(0);

			//boucles permettant de parcourir toute la pile
			while (pilePixel.size() != 0){
				//Cette boucle permet de parcourir tous les pixels entourant le pixel vis�.
				for (int i = -1 ; i < 2 ; i++){
					for (int j = -1 ; j < 2 ; j++){

						//si le pixel est dans la liste, alors on le sauvegarde dans la forme, on le rajoute � la pile et on le supprime de la liste qui est parcouru.
						 if (pixelsCouleur.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j) != null){
							forme.ajoutPixel(pixelsCouleur.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j));
							pilePixel.add(pixelsCouleur.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j));
							pixelsCouleur.supprimePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j);
							
						 }	
					}
				}
				
				//on supprime le pixel de la pile afin de pouvoir terminer la boucle.
				pilePixel.remove(0);
			}
		}
		

		try{
			//creation d'un fichier pour sauvegarder l'image
			for (Forme f : formes){
				File monFichier = new File(cheminSauv +"/formeMultipleMultiColor" + formes.indexOf(f) + ".png");
				//Cette fonction permet de sauvegarder un carr� de pixels � partir d�une image, en d�finissant la position du pixel en haut � gauche ainsi que se taille.
				TraitementImage.saveFile(monFichier, image.creerSousImage(f.getMinX(), f.getMinY(), f.getMaxX() - f.getMinX(), f.getMaxY() - f.getMinY()));
			}
			
			fenetre.setTexteFinTraitement("Traitement termin�, vos images se trouvent dans le dossier " + cheminSauv);
		}
		catch (Exception e){
			e.printStackTrace();
			fenetre.setTexteFinTraitement("Erreur du � la sauvegarde du fichier: " + e.toString());
		}		
	}	
}
