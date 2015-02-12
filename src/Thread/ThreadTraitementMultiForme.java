package Thread;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import Interface.FenetreBase;
import Objet.Forme;
import Objet.Image;
import Objet.ListePixels;
import Objet.Pixel;
import Utilitaire.TraitementImage;

public class ThreadTraitementMultiForme extends Thread{
	private Image image;
	private FenetreBase fenetre;
	private String cheminSauv;
 	
	
	public ThreadTraitementMultiForme(BufferedImage buffImage, FenetreBase fenetreMultiForme) {
		super();
		this.fenetre = fenetreMultiForme;
		//on attribut la BuferedImage � la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreMultiForme.getCheminSauv();



	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en param�tre un objet de la classe FenetreUneForme permet d'afficher les explications de la m�thode
	 */
	public void run (){
		fenetre.setTexteFinTraitement("D�but du traitement, veuillez patienter");
		//Cr�ations d�une listePixels � partir de l�image, la fonction r�cupereToutLesPixels est en g�n�ral une fonction interne � chacun des langages de programmation existant, c�est pour cela qu�elle n�est pas expliqu�e ci-dessous.

		ListePixels pixelsImage = new ListePixels(image.recupererToutLesPixels());

		//Cr�ation d'une liste de Forme permettant de sauvegarder les formes trouv�es.
		ArrayList<Forme> formes = new ArrayList<Forme>();

		//Cette boucle permet de parcourir tout les pixels de la liste en supprimant toujours le premier pixels.
		while(pixelsImage.taille() != 0){


		// Si le pixel est de couleur noir alors on fait un traitement sp�cifique permettant de d�tecter une nouvelle forme.
			if (pixelsImage.recuperePixel(0).getCouleurPixel().equalRgb(new int[]{0,0,0})){

				//cr�ation d'une forme accueillant le pixel de couleurs trouv�.
				Forme forme = new Forme();
				forme.ajoutPixel(pixelsImage.recuperePixel(0));
				formes.add(forme);
				//Cr�ation d'une pile de pixels permettant de sauvegarder les pixels � v�rifier plus tard (explication plus loin)
				ArrayList<Pixel> pilePixel = new ArrayList<Pixel>();
				
				//on ajoute le premier pixel de couleur noir trouv� � la pile.
				pilePixel.add(pixelsImage.recuperePixel(0));
				
				//on supprime le pixel afin que la boucle puisse se terminer.
				pixelsImage.supprimePixel(0);
	
				//boucles permettant de parcourir toute la pile
				while (pilePixel.size() != 0){
					//Cette boucle permet de parcourir tous les pixels entourant le pixel vis�.
					for (int i = -1 ; i < 2 ; i++){
						for (int j = -1 ; j < 2 ; j++){
							if ((pilePixel.get(0).getX() + i) >= 0 && pilePixel.get(0).getY() + j >= 0){
								Pixel pixel = pixelsImage.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j);
								//si le pixel est de couleur noir, alors on le sauvegarde dans la forme, on le rajoute � la pile et on le supprime de la liste qui est parcouru.
								if (pixel != null && pixel.getCouleurPixel().equalRgb(new int[]{0,0,0})){
									forme.ajoutPixel(pixelsImage.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j));
									pilePixel.add(pixelsImage.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j));
									pixelsImage.supprimePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j);
								}	
							}
						}
					}
					//on supprime le pixel de la pile afin de pouvoir terminer la boucle.
					pilePixel.remove(0);
				}
			}
			//si le pixel n'est pas noir alors, on le supprime de la liste.
			else{
				pixelsImage.supprimePixel(0);
			}
		}
		try{
			//creation d'un fichier pour sauvegarder l'image
			for (Forme f : formes){
				File monFichier = new File(cheminSauv +"/formeMultipleNoir" + formes.indexOf(f) + ".png");
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
