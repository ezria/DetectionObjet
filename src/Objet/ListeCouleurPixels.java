package Objet;

import java.util.ArrayList;

public class ListeCouleurPixels {
	private ArrayList<Couleur> couleurs;
	private ArrayList<ArrayList<Pixel>> listePixels;
	private ArrayList<Pixel> plusGrandeListePixel;
	private ListePixels listeAutresPixels;
		
	public ListeCouleurPixels(){
		couleurs = new ArrayList<Couleur>();
		listePixels = new ArrayList<ArrayList<Pixel>>();
	}
	public void ajoutePixel(Pixel p){
		int i = 0;
		boolean couleurTrouve = false;
		while (i < couleurs.size() && !couleurTrouve){
	
			if (couleurs.get(i).equalRgb(p.getCouleurPixel())){
				couleurTrouve = true;
				listePixels.get(i).add(p);
			}
			i++;
		}
		if (!couleurTrouve){

			couleurs.add(p.getCouleurPixel());
			ArrayList<Pixel> nouvelleListePixels = new ArrayList<Pixel>();
			nouvelleListePixels.add(p);
			listePixels.add(nouvelleListePixels);
		}
	}
	
	public ArrayList<Pixel> recupererPlusGrandeListe(){
		if (plusGrandeListePixel == null){
			int plusGrandeTaille = listePixels.get(0).size();
			plusGrandeListePixel = listePixels.get(0);
			for(int i = 0 ; i < listePixels.size() ; i++){
				if (listePixels.get(i).size() > plusGrandeTaille){
					plusGrandeTaille = listePixels.get(i).size();
					plusGrandeListePixel = listePixels.get(i);
				}
			}
			listeAutresPixels = new ListePixels();
			for( ArrayList<Pixel> listeTempPixels : listePixels){
				if (listeTempPixels != plusGrandeListePixel){
					for (Pixel p : listeTempPixels)
						listeAutresPixels.ajoutePixel(p);
				}
			}
		}
		return plusGrandeListePixel;
	}
	public ListePixels recupererListeAutresPixels (){
		if (listeAutresPixels == null) {
			int plusGrandeTaille = listePixels.get(0).size();
			plusGrandeListePixel = listePixels.get(0);
			for(int i = 0 ; i < listePixels.size() ; i++){
				if (listePixels.get(i).size() > plusGrandeTaille){
					plusGrandeTaille = listePixels.get(i).size();
					plusGrandeListePixel = listePixels.get(i);
				}
			}
			listeAutresPixels = new ListePixels();
			for( ArrayList<Pixel> listeTempPixels : listePixels){
				if (listeTempPixels != plusGrandeListePixel){
					for (Pixel p : listeTempPixels)
						listeAutresPixels.ajoutePixel(p);
				}
			}
		}
		return listeAutresPixels ;
	}
}
