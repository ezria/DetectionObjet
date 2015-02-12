
package Thread;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.io.File;
import java.util.ArrayList;

import Interface.FenetreBase;
import Objet.Couleur;
import Objet.Forme;
import Objet.Image;
import Objet.ListeCouleurPixels;
import Objet.ListePixels;
import Objet.Pixel;
import Utilitaire.TraitementImage;

public class ThreadTraitementMultiFormeMultiColorTuto extends Thread{
	private Image image;
	private FenetreBase fenetreMultiFormeMultiColor;
	private String cheminSauv;
	
	public ThreadTraitementMultiFormeMultiColorTuto(BufferedImage buffImage, FenetreBase fenetreMultiFormeMultiColor) {
		super();
		this.fenetreMultiFormeMultiColor = fenetreMultiFormeMultiColor;
		//on attribut la BuferedImage à la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreMultiFormeMultiColor.getCheminSauv();
	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en paramètre un objet de la classe FenetreUneForme permet d'afficher les explications de la méthode
	 */
	@SuppressWarnings("static-access")
	public void run (){
		//Créations d’une listePixels à partir de l’image, la fonction récupereToutLesPixels est en général une fonction interne à chacun des langages de programmation existant, c’est pour cela qu’elle n’est pas expliquée ci-dessous.
		ListeCouleurPixels listeCouleurPixels = new ListeCouleurPixels();
		
		//Partie permettant les explications
		fenetreMultiFormeMultiColor.setTexteExplication("Dans un premier temps nous parcourons tous les pixels afin de déterminer "
				+ "la couleur dominante de l'image. Cette couleur sera la couleur de fond");	
		try {
			this.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Pixel p : image.recupererToutLesPixels()){
			listeCouleurPixels.ajoutePixel(p);
		}

		//Création d'une liste de Forme permettant de sauvegarder les formes trouvées.
		ArrayList<Forme> formes = new ArrayList<Forme>();
		ListePixels pixelsCouleur = listeCouleurPixels.recupererListeAutresPixels();
		
		//Partie permettant les explications
		Couleur couleurFond = listeCouleurPixels.recupererPlusGrandeListe().get(0).getCouleurPixel();
		fenetreMultiFormeMultiColor.setTexteExplication("Tous les pixels ont été parcouru. "
				+ "La couleur de fond est composé de " + couleurFond + ". "
				+ "Nous allons maintenant parcourir tous les pixels qui ne sont pas de cette couleur.");	
		
		try {
			this.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BufferedImage buffModif = image.getBuffImage().getSubimage(0, 0, image.getBuffImage().getWidth(), image.getBuffImage().getHeight());
		ColorModel cm = buffModif.getColorModel();
		Raster r;
		//Cette boucle permet de parcourir tout les pixels de la liste en supprimant toujours le premier pixels.
		while(pixelsCouleur.taille() != 0){
			
			int x = pixelsCouleur.recuperePixel(0).getX();
			int y = pixelsCouleur.recuperePixel(0).getY();
			
			r = buffModif.getData();
			Object objCouleur   = r.getDataElements(x, y, null);
			Color couleurInv = new Color(255 - cm.getRed(objCouleur), 255 - cm.getGreen(objCouleur), 255 - cm.getBlue(objCouleur));
			buffModif.setRGB(pixelsCouleur.recuperePixel(0).getX(), pixelsCouleur.recuperePixel(0).getY(), couleurInv.getRGB());
			fenetreMultiFormeMultiColor.getPanneau().setMonImage(buffModif);
			
			
			//création d'une forme accueillant le pixel de couleurs trouvé.
			Forme forme = new Forme();
			forme.ajoutPixel(pixelsCouleur.recuperePixel(0));
			formes.add(forme);
			if (forme.getPixelsForme().size() == 0){
				fenetreMultiFormeMultiColor.setTexteExplication("Le pixel qui se trouve à la position X = " 
						+ pixelsCouleur.recuperePixel(0).getX() + " Y = " + pixelsCouleur.recuperePixel(0).getY() 
						+ " fait partie d'une forme. En parcourant tous les pixels qui l'entoure, et en réitérant le traitement sur ces pixels, "
						+ "nous alons pouvoir définir tout les pixels qui compose la forme. La forme sera d'une couleur uniforme pour simplifier"
						+ " la visualisation du traitement");	
				try {
					this.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Création d'une pile de pixels permettant de sauvegarder les pixels à vérifier plus tard (explication plus loin)
			ArrayList<Pixel> pilePixel = new ArrayList<Pixel>();
			
			//on ajoute le premier pixel de couleur noir trouvé à la pile.
			pilePixel.add(pixelsCouleur.recuperePixel(0));
			
			//on supprime le pixel afin que la boucle puisse se terminer.
			pixelsCouleur.supprimePixel(0);
			
			//boucles permettant de parcourir toute la pile
			while (pilePixel.size() != 0){
				
				fenetreMultiFormeMultiColor.setTexteExplication("La pile contient " + pilePixel.size() + " éléments a parcourir");
				try {
					this.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//Cette boucle permet de parcourir tous les pixels entourant le pixel visé.
				for (int i = -1 ; i < 2 ; i++){
					for (int j = -1 ; j < 2 ; j++){
						
						//si le pixel est dans la liste, alors on le sauvegarde dans la forme, on le rajoute à la pile et on le supprime de la liste qui est parcouru.
						 if (pixelsCouleur.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j) != null){
							 
							buffModif.setRGB(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j, couleurInv.getRGB());
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
		
			BufferedImage buffModif1 = image.getBuffImage();
			//boucle qui remet les couleurs de départ des imagesq
			for (Forme f : formes){
				for (Pixel p : f.getPixelsForme()){
					;
					buffModif1.setRGB(p.getX(), p.getY(), Color.black.getRGB());
				}
				image.setBuffImage(buffModif1);
				//creation d'un fichier pour sauvegarder l'image
				File monFichier = new File(cheminSauv +"/formeMultipleMultiColor" + formes.indexOf(f) + ".png");
				//Cette fonction permet de sauvegarder un carré de pixels à partir d’une image, en définissant la position du pixel en haut à gauche ainsi que se taille.
				TraitementImage.saveFile(monFichier, image.creerSousImage(f.getMinX(), f.getMinY(), f.getMaxX() - f.getMinX(), f.getMaxY() - f.getMinY()));
			}
			
			fenetreMultiFormeMultiColor.setTexteFinTraitement("Traitement terminé, vos images se trouvent dans le dossier " + cheminSauv);
		}
		catch (Exception e){
			e.printStackTrace();
			fenetreMultiFormeMultiColor.setTexteFinTraitement("Erreur du à la sauvegarde du fichier: " + e.toString());
		}			
	}	
}
