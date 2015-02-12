package Objet;

import java.util.ArrayList;

import Objet.Pixel;

public class Forme {
		private ArrayList<Pixel> pixelsForme;
		private int maxY, maxX, minY, minX, taille;
		
		public Forme() {
			super();
			this.pixelsForme = new ArrayList<Pixel>();

		}
		
		public Forme(ArrayList<Pixel> pixelsForme, int maxY, int maxX,
				int minY, int minX, int taille) {
			super();
			this.pixelsForme = pixelsForme;
			this.maxY = maxY;
			this.maxX = maxX;
			this.minY = minY;
			this.minX = minX;
			this.taille = taille;
		}

		public ArrayList<Pixel> getPixelsForme() {
			return pixelsForme;
		}

		public void setPixelsForme(ArrayList<Pixel> pixelsForme) {
			this.pixelsForme = pixelsForme;
		}

		public int getMaxY() {
			return maxY;
		}

		public void setMaxY(int maxY) {
			this.maxY = maxY;
		}

		public int getMaxX() {
			return maxX;
		}

		public void setMaxX(int maxX) {
			this.maxX = maxX;
		}

		public int getMinY() {
			return minY;
		}

		public void setMinY(int minY) {
			this.minY = minY;
		}

		public int getMinX() {
			return minX;
		}

		public void setMinX(int minX) {
			this.minX = minX;
		}

		public int getTaille() {
			return taille;
		}

		public void setTaille(int taille) {
			this.taille = taille;
		}

		public void ajoutPixel(Pixel pixel){
			
			pixelsForme.add(pixel);
			
			if (pixelsForme.size() == 1){
				maxY = pixel.getY();
				maxX = pixel.getX();
				minY = pixel.getY();
				minX = pixel.getX();
				
			}
			
			if (pixel.getY() < minY)
				minY = pixel.getY();
			else if (pixel.getY() > maxY)
				maxY = pixel.getY();
			if (pixel.getX() < minX)
				minX = pixel.getX();
			else if (pixel.getX() > maxX)
				maxX = pixel.getX();
		}

	public int[] RecupererPosPixelHautGauche(){
		int[] positionPixel = new int[2];
		if (maxX -minX > maxY - minY){
			taille = maxX - minX;
			int positionY = minY - (((maxX -minX)-(maxY - minY))/2);
			positionPixel[0] = minX;
			positionPixel[1] = positionY;
		}
		else{ 
			taille = maxY - minY;
			int positionX = minX - (((maxY - minY)-(maxX -minX))/2);
			positionPixel[0] = minY;
			positionPixel[1] = positionX;
		}
		return positionPixel;
	}

}
