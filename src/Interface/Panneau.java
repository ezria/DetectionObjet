package Interface;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Panneau extends JPanel{
	private BufferedImage monImage;
	
	public Panneau(){
		super();
	}
	
	public Panneau (BufferedImage monImage){
		super();
		this.monImage = monImage;
		Graphics2D g = monImage.createGraphics();
		g.drawImage(monImage, 0, 0,monImage.getWidth(),monImage.getHeight(), null); 
	}
	
	public BufferedImage getMonImage() {

		return monImage;
	}

	public void setMonImage(BufferedImage monImage) {
		this.monImage = monImage;
		Graphics2D g = monImage.createGraphics();
		g.drawImage(monImage, 0, 0,monImage.getWidth(),monImage.getHeight(), null); 
		this.repaint();
		
	}
	

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(monImage != null)
			g.drawImage(monImage, 0, 0, null);		
	} 
}
