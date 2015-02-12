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
		//on attribut la BuferedImage � la classe
		this.image = new Image(buffImage);
		cheminSauv = fenetreUneForme.getCheminSauv();
	}
	
	/**
	 * 
	 * @param fenetreUneForme passer en param�tre un objet de la classe FenetreUneForme permet d'afficher les explications de la m�thode
	 */
	@SuppressWarnings("static-access")
	public void run (){
		//Cr�ations d�une liste de pixels � partir de l�image, la fonctions r�cupererToutLesPixels est en g�n�ral une fonction interne � chacun des langages de programmation existant, c�est pour cela qu�elle n�est pas exliqu� ci dessous.
		ArrayList<Pixel> pixelsImage = image.recupererToutLesPixels();

		//cr�ation de la forme accueillant les pixels de couleurs noir trouv�.
		Forme forme = new Forme();
		fenetreUneForme.setTexteExplication("Nous allons pacourir chacun des pixels de l'image un � un. Nous v�rifirons la couleur de ce pixel, si il est noir alors on le place dans la forme, sinon on passe au suivant");	
		
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
				fenetreUneForme.setTexteExplication("Nous traitons actuellement le pixel que se trouve � la position x = " + p.getX() + " et y = " + p.getY());	
				
				try {
					this.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				
				}
			}
			if (pixelsImage.indexOf(p) ==  10){
				fenetreUneForme.setTexteExplication("Et on continue de v�rifier les pixels");	
			}
						
			//si le pixel et de couleur noir alors on le sauvegarde dans la forme.
			if (p.getCouleurPixel().equalRgb(new int []{0,0,0})){
				if (forme.getPixelsForme().size() == 0){
					fenetreUneForme.setTexteExplication("Le pixel qui se trouve � la position X = " + p.getX() + " Y = " + p.getY() +"  est de la couleur noir, nous l'ajoutons � la forme, pour plus de clart� nous laiss� les pixels d'une forme en rouge");	
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
			
			//on remet le buff image de d�part
			BufferedImage buffModif1 = image.getBuffImage();
			//boucle qui remet les couleurs de d�part des images
			for (Pixel p : forme.getPixelsForme()){
				buffModif1.setRGB(p.getX(), p.getY(), Color.black.getRGB());
			}
			fenetreUneForme.getPanneau().setMonImage(buffModif1);
			// cette fonction permet de sauvergarder un carr� de pixels � partir d�une image, en d�finissant la position du pixel en haut � gauche ainsi que se taille.
			TraitementImage.saveFile(monFichier, image.creerSousImage(forme.RecupererPosPixelHautGauche(), forme.getTaille()));
			fenetreUneForme.setTexteFinTraitement("Traitement termin�, votre image se trouve dans le dossier " + cheminSauv);		
		}
		catch (Exception e){
			e.printStackTrace();
			fenetreUneForme.setTexteFinTraitement("Erreur du � la sauvegarde du fichier: " + e.toString());
		}	
			
	}
}
