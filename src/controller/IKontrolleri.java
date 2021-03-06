package controller;

import javafx.scene.control.CheckBox;
import simu.model.TapahtumanTyyppi;

public interface IKontrolleri {
	
		// Rajapinta, joka tarjotaan  käyttöliittymälle:
	
		/**
		 * Käynnistetään simulointi
		 */
		public void kaynnistaSimulointi();
		public void nopeuta();
		public void hidasta();
		public void checkbox();
		
		// Rajapinta, joka tarjotaan moottorille:
		
		public void naytaLoppuaika(double aika);
		//public void visualisoiAsiakas();
		public void textSet(String string);
		public int tickedK2();
		public int tickedK3();
		public int tickedV2();
		public void lisaaAsiakas(TapahtumanTyyppi skeduloitavanTapahtumanTyyppi, int size);
		public void poistaAsiakas(TapahtumanTyyppi skeduloitavanTapahtumanTyyppi, int size);
		public void luoMyymälä();
		public int getTiheys();
		public int getHeitto();
		

}
