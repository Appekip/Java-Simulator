package controller;

import java.io.Console;

import javax.swing.JOptionPane;

import javafx.application.Platform;
import javafx.scene.control.CheckBox;
import javafx.stage.Window;
import simu.framework.IMoottori;
import simu.framework.Kello;
import simu.model.OmaMoottori;
import simu.model.TapahtumanTyyppi;
import view.ISimulaattorinUI;

public class Kontrolleri implements IKontrolleri{   // UUSI
	
	private IMoottori moottori; 
	private ISimulaattorinUI gui;

	
	public Kontrolleri(ISimulaattorinUI gui) {
		this.gui = gui;
	}

	
	// Moottorin ohjausta:
		
	@Override
	public void kaynnistaSimulointi() {
		
		moottori = new OmaMoottori(this); // luodaan uusi moottorisäie jokaista simulointia varten
		moottori.nollaus();
		moottori.setSimulointiaika(gui.getAika());
		moottori.setViive(gui.getViive());
		gui.getVisualisointi().tyhjennaNaytto();
		((Thread)moottori).start();
		gui.setTextArea("Simulointi on käynnissä...");
		 //	moottori.tulokset();
	}
	
	@Override
	public void hidasta() { // hidastetaan moottorisäiettä
		moottori.setViive((long)(moottori.getViive()*1.10));
	}

	@Override
	public void nopeuta() { // nopeutetaan moottorisäiettä
		if (moottori.getViive() > 5) {
			moottori.setViive((long)(moottori.getViive()*0.9));
		}
		else {
		JOptionPane.showMessageDialog(null, "Viive ei voi olla pienempi kuin 5!");
		}
	}
	
	
	
	// Simulointitulosten välittämistä käyttöliittymään.
	// Koska gui:n päivitykset tulevat moottorisäikeestä, ne pitää ohjata JavaFX-säikeeseen:
		
	@Override
	public void naytaLoppuaika(double aika) {
		Platform.runLater(()->gui.setLoppuaika(aika)); 
	}

	@Override
	public void checkbox() {
		// TODO Auto-generated method stub
		
	}
	public void textSet(String s) {
		gui.setTextArea(s);
	}
	


	public int tickedK2() {
		if (gui.isTickedK2() == true) {
			return 1;
		}
		return 0;
	}
	
	public int tickedK3() {
		if (gui.isTickedK3() == true) {
			return 1;
		}
		return 0;
	}
	
	public int tickedV2() {
		if (gui.isTickedV2() == true) {
			return 1;
		}
		return 0;
	}


	@Override
	public void lisaaAsiakas(TapahtumanTyyppi palvelupiste, int size) {
		Platform.runLater(new Runnable(){ 
			public void run(){
				gui.getVisualisointi().lisääAsiakas(palvelupiste, size);
			}
		});
	}
		


	@Override
	public void poistaAsiakas(TapahtumanTyyppi palvelupiste, int size) {		
			Platform.runLater(new Runnable(){ 
				public void run(){
					gui.getVisualisointi().poistaAsiakas(palvelupiste, size);
				}
			});
		}


	@Override
	public void luoMyymälä() {
		gui.getVisualisointi().uusiMyymala();
	}

	public int getTiheys() {
		return gui.tiheys();
	}
	
	public int getHeitto() {
		return gui.heitto();
	}
		
		
	}


	



