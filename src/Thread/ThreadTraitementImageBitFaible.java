package Thread;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

import Interface.FenetreBase;
import Objet.Forme;
import Objet.Image;
import Objet.Pixel;
import Utilitaire.TraitementImage;

public class ThreadTraitementImageBitFaible extends Thread {
	
	private Image image;
	private FenetreBase fenetre;
	private String cheminSauv;
 	
	
	public ThreadTraitementImageBitFaible(BufferedImage buffImage, FenetreBase fenetreUneForme) {
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
		String red= "", green = "", blue ="", rgb="";
		
		for (Pixel p : pixelsImage){
			rgb += (p.getCouleurPixel().getR()%2)
					+""+(p.getCouleurPixel().getG()%2)
					+""+(p.getCouleurPixel().getB()%2);
					
		}
		
		//Cette boucle permet de parcourir tout les pixels de la liste.
		/*for (int i=0; i< pixelsImage.size() -3; i+=3){
			rgb += ((((pixelsImage.get(i).getCouleurPixel().getR())%2==0)?1:0) +""+ (((pixelsImage.get(i).getCouleurPixel().getG())%2 == 0)? 1:0) +""+(((pixelsImage.get(i).getCouleurPixel().getB())%2 == 0)? 1:0)+""+
					(((pixelsImage.get(i+1).getCouleurPixel().getR())%2 == 0)?1:0) +""+ (((pixelsImage.get(i+1).getCouleurPixel().getG())%2 == 0)? 1:0) +""+(((pixelsImage.get(i+1).getCouleurPixel().getB())%2 == 0)? 1:0)+""+
					(((pixelsImage.get(i+2).getCouleurPixel().getR())%2 == 0)? 1:0)+""+ (((pixelsImage.get(i+2).getCouleurPixel().getG())%2 == 0)? 1:0));
		}
		*/
		
		
		
		
		
		
		/*char nextChar;
		String rgbAscii ="";
		for (int i=0;i<=rgb.length()-8;i+=9){
			nextChar = (char)Integer.parseInt(rgb.substring(i, i+7), 2);
			rgbAscii += nextChar;
		}*/
		
		try{
			//creation d'un fichier pour sauvegarder l'image
			File monFichier = new File(cheminSauv +"/formeUnique.txt");
	            // Creation du fichier
			monFichier .createNewFile();
	            // creation d'un writer (un écrivain)
	            final FileWriter writer = new FileWriter(monFichier);
	            try {
	                writer.write("rgb =" + rgb + "\n");
	            } finally {
	                // quoiqu'il arrive, on ferme le fichier
	                writer.close();
	            }
	            fenetre.setTexteFinTraitement("c'est fait");
		}
		catch (Exception e){
			e.printStackTrace();
			fenetre.setTexteFinTraitement("Erreur du à la sauvegarde du fichier: " + e.toString());
		}	
		

		
	}
}
