package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Thread.ThreadTraitementMultiForme;
import Thread.ThreadTraitementMultiFormeTuto;
import Utilitaire.TraitementImage;

@SuppressWarnings("serial")
public class FenetreMultiForme extends FenetreBase {

	private FenetreBase fenetre;
	/**
	 * Create the panel.
	 */
	public FenetreMultiForme() {
		
		super();
		this.getLblTitre().setText("Detection de plusieurs formes noirs dans une image à fond blanc");
		this.getBtnActiverLaRecherche().addActionListener(new actionTraitement());
		this.getBtnActiverLaRechercheTuto().addActionListener(new actionTraitementTuto());
		this.fenetre = this;
		File fichier = new File("ImageBase/ImageFormesSimples.png");
		this.getPanneau().setMonImage(TraitementImage.openFile(fichier));
	}
	class actionTraitement implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setEnabledAllButton(false);
			ThreadTraitementMultiForme  tIF= new ThreadTraitementMultiForme(getPanneau().getMonImage(), fenetre);
			tIF.start();
			getLblWait1().setVisible(true);
			
		}
	}
	
	class actionTraitementTuto implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {

			ThreadTraitementMultiFormeTuto  tIF= new ThreadTraitementMultiFormeTuto(getPanneau().getMonImage(), fenetre);
			tIF.start();
			getLblWait2().setVisible(true);
			
		}
	}
}
