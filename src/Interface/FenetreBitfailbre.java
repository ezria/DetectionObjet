package Interface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import Thread.ThreadTraitementImageBitFaible;
import Thread.ThreadTraitementImageUneForme;
import Thread.ThreadTraitementImageUneFormeTuto;
import Utilitaire.TraitementImage;

@SuppressWarnings("serial")
public class FenetreBitfailbre extends FenetreBase {

	private FenetreBase fenetre;
	/**
	 * Create the panel.
	 */
	public FenetreBitfailbre() {
		
		super();
		this.getLblTitre().setText("Detection de plusieurs formes multicouleurs sur fond uniforme");
		this.getBtnActiverLaRecherche().addActionListener(new actionTraitement());
		this.fenetre = this;
		File fichier = new File("ImageBase/ImageFormeSimple.png");
		this.getPanneau().setMonImage(TraitementImage.openFile(fichier));
	}

	class actionTraitement implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			setEnabledAllButton(false);
			ThreadTraitementImageBitFaible  tIF= new ThreadTraitementImageBitFaible(getPanneau().getMonImage(), fenetre);
			tIF.start();
			getLblWait1().setVisible(true);
			
		}
	}
}
