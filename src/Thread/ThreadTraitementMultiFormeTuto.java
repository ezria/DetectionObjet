package Thread;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import Interface.FenetreBase;
import Objet.Forme;
import Objet.Image;
import Objet.ListePixels;
import Objet.Pixel;
import Utilitaire.TraitementImage;

public class ThreadTraitementMultiFormeTuto extends Thread{
	private Image image;
	private FenetreBase fenetreMultiForme;
	private String cheminSauv;
 	
	
	public ThreadTraitementMultiFormeTuto(BufferedImage buffImage, FenetreBase fenetreMultiForme) {
		super();
		this.fenetreMultiForme = fenetreMultiForme;
		//on attribut la BuferedImage à la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreMultiForme.getCheminSauv();
	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en paramètre un objet de la classe FenetreUneForme permet d'afficher les explications de la méthode
	 */
	@SuppressWarnings("static-access")
	public void run (){
		//Créations d’une listePixels à partir de l’image, la fonction récupereToutLesPixels est en général une fonction interne à chacun des langages de programmation existant, c’est pour cela qu’elle n’est pas expliquée ci-dessous.

		ListePixels pixelsImage = new ListePixels(image.recupererToutLesPixels());

		//Création d'une liste de Forme permettant de sauvegarder les formes trouvées.
		ArrayList<Forme> formes = new ArrayList<Forme>();
		BufferedImage buffModif;
		int rgbBase;
		int nbIteration = 0;
		//Cette boucle permet de parcourir tout les pixels de la liste en supprimant toujours le premier pixels.
		while(pixelsImage.taille() != 0){
			
			
			//Partie permettant l'explication de l'algorithme
			
			buffModif = image.getBuffImage();
			rgbBase = buffModif.getRGB(pixelsImage.recuperePixel(0).getX(), pixelsImage.recuperePixel(0).getY());
			buffModif.setRGB(pixelsImage.recuperePixel(0).getX(), pixelsImage.recuperePixel(0).getY(), Color.red.getRGB());
			fenetreMultiForme.getPanneau().setMonImage(buffModif);
			if (nbIteration < 10){
				fenetreMultiForme.setTexteExplication("Nous traitons actuellement le pixel que se trouve à la position x = " + pixelsImage.recuperePixel(0).getX() + " et y = " + pixelsImage.recuperePixel(0).getY());	

				try {
					this.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			}
			else if ((nbIteration == 10))
				fenetreMultiForme.setTexteExplication("On continue de parcourir les pixels");	

			
		// Si le pixel est de couleur noir alors on fait un traitement spécifique permettant de détecter une nouvelle forme.
			if (pixelsImage.recuperePixel(0).getCouleurPixel().equalRgb(new int[]{0,0,0})){
				
				//création d'une forme accueillant le pixel de couleurs trouvé.
				Forme forme = new Forme();
				if (forme.getPixelsForme().size() == 0){
					fenetreMultiForme.setTexteExplication("Le pixel qui se trouve à la position X = " + pixelsImage.recuperePixel(0).getX() + " Y = " + pixelsImage.recuperePixel(0).getY() +"  est de la couleur noir. Une nouvelle forme est detecter, nous l'ajoutons a la liste de forme et gardons le pixel en rouge pour plus de clarté");	
					try {
						this.sleep(4000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				forme.ajoutPixel(pixelsImage.recuperePixel(0));
				formes.add(forme);
				//Création d'une pile de pixels permettant de sauvegarder les pixels à vérifier plus tard (explication plus loin)
				ArrayList<Pixel> pilePixel = new ArrayList<Pixel>();
				
				//on ajoute le premier pixel de couleur noir trouvé à la pile.
				pilePixel.add(pixelsImage.recuperePixel(0));
				
				//on supprime le pixel afin que la boucle puisse se terminer.
				pixelsImage.supprimePixel(0);
				
				//Explication
				fenetreMultiForme.setTexteExplication("Suite à la detection de cette nouvelle forme nous allons parcourir tout les pixels qui entour ce pixel. Si il est de couleur noir, alors nous l'ajoutons à la pile de pixel. "
						+ "Cette pile permettra d'ajouter tout les pixels adjacents");	
				try {
					this.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//boucles permettant de parcourir toute la pile
				while (pilePixel.size() != 0){
					fenetreMultiForme.setTexteExplication("La pile contient " + pilePixel.size() + " éléments a parcourir");	
					try {
						this.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Cette boucle permet de parcourir tous les pixels entourant le pixel visé.
					for (int i = -1 ; i < 2 ; i++){
						for (int j = -1 ; j < 2 ; j++){
							if ((pilePixel.get(0).getX() + i) >= 0 && pilePixel.get(0).getY() + j >= 0){
								Pixel pixel = pixelsImage.recuperePixel(pilePixel.get(0).getX() + i, pilePixel.get(0).getY() + j);
								//si le pixel est de couleur noir, alors on le sauvegarde dans la forme, on le rajoute à la pile et on le supprime de la liste qui est parcouru.
								if (pixel != null && pixel.getCouleurPixel().equalRgb(new int[]{0,0,0})){

									buffModif = image.getBuffImage();
									rgbBase = buffModif.getRGB(pixel.getX(), pixel.getY());
									buffModif.setRGB(pixel.getX(), pixel.getY(), Color.red.getRGB());
								
									fenetreMultiForme.getPanneau().setMonImage(buffModif);
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
				fenetreMultiForme.setTexteExplication("On continue de parcourir les pixels");	
			}
			//si le pixel n'est pas noir alors, on le supprime de la liste.
			else{
				buffModif.setRGB(pixelsImage.recuperePixel(0).getX(), pixelsImage.recuperePixel(0).getY(), rgbBase);
				pixelsImage.supprimePixel(0);
				
			}
			nbIteration++;
		}
		
		//creation d'un fichier pour sauvegarder l'image
		

		try{
			BufferedImage buffModif1 = image.getBuffImage();
			//boucle qui remet les couleurs de départ des images
			for (Forme f : formes){
				for (Pixel p : f.getPixelsForme()){
					;
					buffModif1.setRGB(p.getX(), p.getY(), Color.black.getRGB());
				}
				image.setBuffImage(buffModif1);
				//creation d'un fichier pour sauvegarder l'image
				File monFichier = new File(cheminSauv +"/formeMultipleNoir" + formes.indexOf(f) + ".png");
				//Cette fonction permet de sauvegarder un carré de pixels à partir d’une image, en définissant la position du pixel en haut à gauche ainsi que se taille.
				TraitementImage.saveFile(monFichier, image.creerSousImage(f.getMinX(), f.getMinY(), f.getMaxX() - f.getMinX(), f.getMaxY() - f.getMinY()));
			}
			fenetreMultiForme.getPanneau().setMonImage(buffModif1);
			fenetreMultiForme.setTexteFinTraitement("Traitement terminé, vos images se trouvent dans le dossier " + cheminSauv);
		}
		catch (Exception e){
			e.printStackTrace();
			fenetreMultiForme.setTexteFinTraitement("Erreur du à la sauvegarde du fichier: " + e.toString());
		}	
	}	
}
