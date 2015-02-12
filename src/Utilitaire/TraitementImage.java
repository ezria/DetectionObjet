package Utilitaire;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class TraitementImage {
	
	public static BufferedImage openFile(File monFichier){
		BufferedImage  monImage = null;
		try {
			monImage = ImageIO.read(monFichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return monImage;
	}
	public static boolean saveFile(File monFichier, BufferedImage buffImage){
		
		String format ="PNG";
		try {
			ImageIO.write(buffImage, format, monFichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}  
		return true;
	}
	
}
