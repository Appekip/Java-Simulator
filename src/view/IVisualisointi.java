package view;

import simu.model.TapahtumanTyyppi;

public interface IVisualisointi {

	public void tyhjennaNaytto();
	
	public void uusiMyymala();

	public void poistaAsiakas(TapahtumanTyyppi palvelupiste, int size);
	
	public void lisääAsiakas(TapahtumanTyyppi palvelupiste, int size);
	
		
}

