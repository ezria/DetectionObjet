package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Thread.ThreadTraitementImageUneForme;
import Thread.ThreadTraitementImageUneFormeTuto;
import Utilitaire.TraitementImage;

@SuppressWarnings("serial")
public class FenetreUneForme extends FenetreBase {

	private FenetreBase fenetre;
	/**
	 * Create the panel.
	 */
	public FenetreUneForme() {
		
		super();
		this.getLblTitre().setText("Detection de plusieurs formes multicouleurs sur fond uniforme");
		this.getBtnActiverLaRecherche().addActionListener(new actionTraitement());
		this.getBtnActiverLaRechercheTuto().addActionListener(new actionTraitementTuto());
		this.fenetre = this;
		File fichier = new File("ImageBase/ImageFormeSimple.png");
		this.getPanneau().setMonImage(TraitementImage.openFile(fichier));
	}

	class actionTraitement implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setEnabledAllButton(false);
			ThreadTraitementImageUneFormeTuto  tIF= new ThreadTraitementImageUneFormeTuto(getPanneau().getMonImage(), fenetre);
			tIF.start();
			getLblWait1().setVisible(true);
			
		}
	}
	
	class actionTraitementTuto implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {

			ThreadTraitementImageUneForme  tIF= new ThreadTraitementImageUneForme(getPanneau().getMonImage(), fenetre);
			tIF.start();
			getLblWait2().setVisible(true);
			
		}
	}

}
