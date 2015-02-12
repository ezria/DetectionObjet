package Objet;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.Raster;
import java.util.ArrayList;

public class Image {
	private BufferedImage buffImage;
	private Raster r;
	private ColorModel cm;
	private ArrayList<Pixel> pixels = null;
	
	public Image (BufferedImage buffImage){
		this.buffImage = buffImage;
		r = this.buffImage.getData();
		cm = this.buffImage.getColorModel(); 
	}

	public BufferedImage getBuffImage() {
		return buffImage;
	}

	public void setBuffImage(BufferedImage buffImage) {
		this.buffImage = buffImage;
	}

	public Raster getR() {
		return r;
	}

	public void setR(Raster r) {
		this.r = r;
	}

	public ColorModel getCm() {
		return cm;
	}

	public void setCm(ColorModel cm) {
		this.cm = cm;
	}
	public ArrayList<Pixel> recupererToutLesPixels(){
		if (pixels == null){
			pixels = new ArrayList<Pixel>();
		
			for (int x = 0 ; x < r.getWidth() ; x++){ 
				for (int y = 0 ; y < r.getHeight() ; y++){ 
					Object objCouleur   = r.getDataElements(x, y, null);
					pixels.add(new Pixel(x, y, new Couleur(cm.getRed(objCouleur), cm.getGreen(objCouleur), cm.getBlue(objCouleur))));
				}	
			}
		}
		return pixels;
	}
	
	public BufferedImage creerSousImage (int[] xY, int taille){
		BufferedImage bi = buffImage.getSubimage(xY[0]-2, xY[1]-2, taille+4, taille+4);
		return bi;
	}
	public BufferedImage creerSousImage (int x, int y, int tailleX, int tailleY){
		BufferedImage bi = buffImage.getSubimage(x-2, y-2, tailleX+4, tailleY+4);
		return bi;
	}
	
}
