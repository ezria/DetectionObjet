package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Thread.ThreadTraitementMultiFormeMultiColor;
import Thread.ThreadTraitementMultiFormeMultiColorTuto;
import Utilitaire.TraitementImage;

@SuppressWarnings("serial")
public class FenetreMultiFormeMultiColor extends FenetreBase {

	private FenetreBase fenetre;
	/**
	 * Create the panel.
	 */
	public FenetreMultiFormeMultiColor() {
		
		super();
		this.getLblTitre().setText("Detection de plusieurs formes multicolores sur fond uniforme");
		this.getBtnActiverLaRecherche().addActionListener(new actionTraitement());
		this.getBtnActiverLaRechercheTuto().addActionListener(new actionTraitementTuto());
		this.fenetre = this;
		File fichier = new File("ImageBase/ImageFormesMulticolores.png");
		this.getPanneau().setMonImage(TraitementImage.openFile(fichier));
	}

	class actionTraitement implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setEnabledAllButton(false);
			ThreadTraitementMultiFormeMultiColor  tIF= new ThreadTraitementMultiFormeMultiColor(getPanneau().getMonImage(), fenetre);
			tIF.start();
			getLblWait1().setVisible(true);
			
		}
	}
	
	class actionTraitementTuto implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {

			ThreadTraitementMultiFormeMultiColorTuto  tIF= new ThreadTraitementMultiFormeMultiColorTuto(getPanneau().getMonImage(), fenetre);
			tIF.start();
			getLblWait2().setVisible(true);
			
		}
	}
}