package Interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import Utilitaire.ChoixFichier;
import Utilitaire.TraitementImage;

@SuppressWarnings("serial")
public class FenetreBase extends JPanel{
	/**	
	 * 
	 */
	
	private JButton btnActiverLaRecherche;
	private JButton btnActiverLaRechercheTuto;
	private JButton btnParcourir;
	private JButton btnParcourirDossier;
	
	private JPanel panelCadreImage;
	private JPanel panelBas;
	private JPanel panelGauche;
	private JPanel panelDroite;
	private JPanel panelHaut;
	private JPanel panel_1;
	
	private JLabel lblTitre;
	private JLabel lblWait1;
	private JLabel lblWait2;
	private JLabel lblInformation;
	private JLabel lblSelectionnerLeDossier;
	
	private JTextArea txtArea;
	private JTextField txtimagesortie;
	private JTextField textField;

	

	private String cheminSauv;
	private JScrollPane scrollPane;
	private Panneau panneau;
	/**
	 * Create the panel.
	 */
	public FenetreBase() {
		setLayout(new BorderLayout(0, 0));
		cheminSauv = "ImageSortie"; 
		JPanel panel = new JPanel();
		add(panel, BorderLayout.WEST);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{13, 142, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{33, 0, 21, 0, 0, 0, 134, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblSelectionnerSaPropre = new JLabel("Selectionner sa propre image:\r\n");
		GridBagConstraints gbc_lblSelectionnerSaPropre = new GridBagConstraints();
		gbc_lblSelectionnerSaPropre.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectionnerSaPropre.fill = GridBagConstraints.BOTH;
		gbc_lblSelectionnerSaPropre.gridx = 1;
		gbc_lblSelectionnerSaPropre.gridy = 0;
		panel.add(lblSelectionnerSaPropre, gbc_lblSelectionnerSaPropre);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnParcourir = new JButton("Parcourir ...");
		btnParcourir.addActionListener(new actionParcourir());
		GridBagConstraints gbc_btnParcourir = new GridBagConstraints();
		gbc_btnParcourir.anchor = GridBagConstraints.EAST;
		gbc_btnParcourir.insets = new Insets(0, 0, 5, 5);
		gbc_btnParcourir.gridx = 2;
		gbc_btnParcourir.gridy = 1;
		panel.add(btnParcourir, gbc_btnParcourir);
		
		btnActiverLaRecherche = new JButton("Activer la recherche de forme");
		btnActiverLaRecherche.setToolTipText("Ce bouton lancera la recherche sans expliclation");
		
		lblSelectionnerLeDossier = new JLabel("Selectionner le dossier de sortie:\r\n");
		lblSelectionnerLeDossier.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_lblSelectionnerLeDossier = new GridBagConstraints();
		gbc_lblSelectionnerLeDossier.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblSelectionnerLeDossier.insets = new Insets(0, 0, 5, 5);
		gbc_lblSelectionnerLeDossier.gridx = 1;
		gbc_lblSelectionnerLeDossier.gridy = 2;
		panel.add(lblSelectionnerLeDossier, gbc_lblSelectionnerLeDossier);
		
		txtimagesortie = new JTextField();
		txtimagesortie.setText("/ImageSortie");
		txtimagesortie.setEditable(false);
		txtimagesortie.setColumns(10);
		GridBagConstraints gbc_txtimagesortie = new GridBagConstraints();
		gbc_txtimagesortie.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtimagesortie.insets = new Insets(0, 0, 5, 5);
		gbc_txtimagesortie.gridx = 1;
		gbc_txtimagesortie.gridy = 3;
		panel.add(txtimagesortie, gbc_txtimagesortie);
		
		btnParcourirDossier = new JButton("Parcourir ...");
		btnParcourirDossier.addActionListener(new actionParcourirDossier());
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 5, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 3;
		panel.add(btnParcourirDossier, gbc_button);
		GridBagConstraints gbc_btnActiverLaRecherche = new GridBagConstraints();
		gbc_btnActiverLaRecherche.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActiverLaRecherche.insets = new Insets(0, 0, 5, 5);
		gbc_btnActiverLaRecherche.gridx = 1;
		gbc_btnActiverLaRecherche.gridy = 4;
		panel.add(btnActiverLaRecherche, gbc_btnActiverLaRecherche);
		
		btnActiverLaRechercheTuto = new JButton("Activer la recherche de forme avec explication");
		btnActiverLaRechercheTuto.setToolTipText("Ce bouton lancera la recherche sans expliclation");
		
		lblWait1 = new JLabel();
		lblWait1.setIcon(new ImageIcon("ImageBase/wait.gif"));
		lblWait1.setVisible(false);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 4;
		panel.add(lblWait1, gbc_lblNewLabel);
		GridBagConstraints gbc_btnActiverLaRecherche_1 = new GridBagConstraints();
		gbc_btnActiverLaRecherche_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnActiverLaRecherche_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnActiverLaRecherche_1.gridx = 1;
		gbc_btnActiverLaRecherche_1.gridy = 5;
		panel.add(btnActiverLaRechercheTuto, gbc_btnActiverLaRecherche_1);
		
		lblWait2 = new JLabel();
		lblWait2.setIcon(new ImageIcon("ImageBase/wait.gif"));
		lblWait2.setVisible(false);
		GridBagConstraints gbc_lblWait2 = new GridBagConstraints();
		gbc_lblWait2.gridy = 5;
		gbc_lblWait2.insets = new Insets(0, 0, 5, 5);
		gbc_lblWait2.gridx = 2;
		gbc_lblNewLabel.gridy = 4;
		panel.add(lblWait2, gbc_lblWait2);
		
		panelCadreImage = new JPanel();
		add(panelCadreImage, BorderLayout.CENTER);
		panelCadreImage.setLayout(new BorderLayout(0, 0));
		
		panelHaut = new JPanel();
		panelCadreImage.add(panelHaut, BorderLayout.NORTH);
		
		panelBas = new JPanel();
		panelCadreImage.add(panelBas, BorderLayout.SOUTH);
		
		panelGauche = new JPanel();
		panelCadreImage.add(panelGauche, BorderLayout.WEST);
		
		panelDroite = new JPanel();
		panelCadreImage.add(panelDroite, BorderLayout.EAST);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED );
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		panelCadreImage.add(scrollPane, BorderLayout.CENTER);
		
		panneau = new Panneau();
		scrollPane.setViewportView(panneau);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0), 1, true), new LineBorder(new Color(0, 0, 0))));
		add(panel_2, BorderLayout.NORTH);
		
		lblTitre = new JLabel("Titre");
		panel_2.add(lblTitre);

		textField.setText("Image de base du logiciel");
		
		panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 6;
		panel.add(panel_1, gbc_panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		lblInformation = new JLabel("Informations :");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblInformation, BorderLayout.NORTH);
		
		txtArea = new JTextArea();
		txtArea.setLineWrap(true);
		txtArea.setEditable(false);
		txtArea.setColumns(10);
		txtArea.setBackground(SystemColor.control);
		txtArea.setWrapStyleWord(true);
		panel_1.add(txtArea, BorderLayout.CENTER);
	}
	class actionParcourir implements ActionListener{
		// TODO Auto-generated method stub

		@Override
		public void actionPerformed(ActionEvent e) {
			File fichier = ChoixFichier.openDialogFile();
			if (fichier != null){
				textField.setText(fichier.getPath());
				panneau.setMonImage(TraitementImage.openFile(fichier));
			}
		}
	}
	
	class actionParcourirDossier implements ActionListener{
		// TODO Auto-generated method stub
		@Override
		public void actionPerformed(ActionEvent e) {
			File fichier = ChoixFichier.openDialogFolder();
			if (fichier != null){
				cheminSauv = fichier.getPath();
				txtimagesortie.setText(cheminSauv);
			}
		}
	}
	
	public void setTexteExplication(String s){
		txtArea.setText(s);		
	}
	
	public void setTexteFinTraitement(String s){
		txtArea.setText(s);	
		lblWait1.setVisible(false);
		lblWait2.setVisible(false);
		btnActiverLaRecherche.setEnabled(true);
		btnActiverLaRechercheTuto.setEnabled(true);
		btnParcourir.setEnabled(true);
		btnParcourirDossier.setEnabled(true);
	}
	public Panneau getPanneau(){
		return panneau;
	}
	
	public String getCheminSauv() {
		return cheminSauv;
	}
	
	public void setCheminSauv(String cheminSauv) {
		this.cheminSauv = cheminSauv;
	}
	
	public JButton getBtnActiverLaRecherche() {
		return btnActiverLaRecherche;
	}
	
	public void setBtnActiverLaRecherche(JButton btnActiverLaRecherche) {
		this.btnActiverLaRecherche = btnActiverLaRecherche;
	}
	
	public JButton getBtnActiverLaRechercheTuto() {
		return btnActiverLaRechercheTuto;
	}
	
	public void setBtnActiverLaRechercheTuto(JButton btnActiverLaRechercheTuto) {
		this.btnActiverLaRechercheTuto = btnActiverLaRechercheTuto;
	}

	public JLabel getLblTitre() {
		return lblTitre;
	}

	public void setLblTitre(JLabel lblTitre) {
		this.lblTitre = lblTitre;
	}
	public void setEnabledAllButton(boolean b){
		btnActiverLaRecherche.setEnabled(b);
		btnActiverLaRechercheTuto.setEnabled(b);
		btnParcourir.setEnabled(b);
		btnParcourirDossier.setEnabled(b);
	}

	public void setPanneau(Panneau panneau) {
		this.panneau = panneau;
	}

	public JLabel getLblWait1() {
		return lblWait1;
	}

	public void setLblWait1(JLabel lblWait1) {
		this.lblWait1 = lblWait1;
	}

	public JLabel getLblWait2() {
		return lblWait2;
	}

	public void setLblWait2(JLabel lblWait2) {
		this.lblWait2 = lblWait2;
	}
}
