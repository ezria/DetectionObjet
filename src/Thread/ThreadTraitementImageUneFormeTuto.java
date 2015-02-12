package Thread;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import Interface.FenetreBase;
import Objet.Forme;
import Objet.Image;
import Objet.Pixel;
import Utilitaire.TraitementImage;

public class ThreadTraitementImageUneFormeTuto extends Thread {
	
	private Image image;
	private FenetreBase fenetreUneForme;

	private String cheminSauv; 	
	
	public ThreadTraitementImageUneFormeTuto(BufferedImage buffImage, FenetreBase fenetreUneForme) {
		super();
		this.fenetreUneForme = fenetreUneForme;
		//on attribut la BuferedImage à la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreUneForme.getCheminSauv();
	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en paramètre un objet de la classe FenetreUneForme permet d'afficher les explications de la méthode
	 */
	@SuppressWarnings("static-access")
	public void run (){
		//Créations d’une liste de pixels à partir de l’image, la fonctions récupererToutLesPixels est en général une fonction interne à chacun des langages de programmation existant, c’est pour cela qu’elle n’est pas exliqué ci dessous.
		ArrayList<Pixel> pixelsImage = image.recupererToutLesPixels();

		//création de la forme accueillant les pixels de couleurs noir trouvé.
		Forme forme = new Forme();
		fenetreUneForme.setTexteExplication("Nous allons pacourir chacun des pixels de l'image un à un. Nous vérifirons la couleur de ce pixel, si il est noir alors on le place dans la forme, sinon on passe au suivant");	
		
		try {
			this.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		//Cette boucle permet de parcourir tout les pixels de la liste.
		BufferedImage buffModif;
		int rgbBase;
		for (Pixel p : pixelsImage){ 
			
			buffModif = image.getBuffImage();
			rgbBase = buffModif.getRGB(p.getX(), p.getY());
			buffModif.setRGB(p.getX(), p.getY(), Color.red.getRGB());
			fenetreUneForme.getPanneau().setMonImage(buffModif);
			if (pixelsImage.indexOf(p)< 10){
				fenetreUneForme.setTexteExplication("Nous traitons actuellement le pixel que se trouve à la position x = " + p.getX() + " et y = " + p.getY());	
				
				try {
					this.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			}
			if (pixelsImage.indexOf(p) ==  10){
				fenetreUneForme.setTexteExplication("Et on continue de vérifier les pixels");	
			}
						
			//si le pixel et de couleur noir alors on le sauvegarde dans la forme.
			if (p.getCouleurPixel().equalRgb(new int []{0,0,0})){
				if (forme.getPixelsForme().size() == 0){
					fenetreUneForme.setTexteExplication("Le pixel qui se trouve à la position X = " + p.getX() + " Y = " + p.getY() +"  est de la couleur noir, nous l'ajoutons à la forme, pour plus de clarté nous laissé les pixels d'une forme en rouge");	
					try {
						this.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					fenetreUneForme.setTexteExplication("Puis on continu de parcourir les pixels en placant les noirs dans la forme et en ne touchant pas aux autres");	
					try {
						this.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				forme.ajoutPixel(p);
			}
			else{
				
				buffModif.setRGB(p.getX(), p.getY(), rgbBase);
			}
			
		}
		try{
			//creation d'un fichier pour sauvegarder l'image
			File monFichier = new File(cheminSauv +"/formeUnique.png");
			
			//on remet le buff image de départ
			BufferedImage buffModif1 = image.getBuffImage();
			//boucle qui remet les couleurs de départ des images
			for (Pixel p : forme.getPixelsForme()){
				buffModif1.setRGB(p.getX(), p.getY(), Color.black.getRGB());
			}
			fenetreUneForme.getPanneau().setMonImage(buffModif1);
			// cette fonction permet de sauvergarder un carré de pixels à partir d’une image, en définissant la position du pixel en haut à gauche ainsi que se taille.
			TraitementImage.saveFile(monFichier, image.creerSousImage(forme.RecupererPosPixelHautGauche(), forme.getTaille()));
			fenetreUneForme.setTexteFinTraitement("Traitement terminé, votre image se trouve dans le dossier " + cheminSauv);		
		}
		catch (Exception e){
			e.printStackTrace();
			fenetreUneForme.setTexteFinTraitement("Erreur du à la sauvegarde du fichier: " + e.toString());
		}	
			
	}
}
