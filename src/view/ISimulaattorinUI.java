package view;

import javafx.scene.control.CheckBox;

public interface ISimulaattorinUI {
	
	// Kontrolleri tarvitsee syötteitä, jotka se välittää Moottorille
	public double getAika();
	public long getViive();
	
	//Kontrolleri antaa käyttöliittymälle tuloksia, joita Moottori tuottaa 
	public void setLoppuaika(double aika);
	
	// Kontrolleri tarvitsee  
	public IVisualisointi getVisualisointi();
	
	public void setTextArea(String s);
	
	public boolean isTickedK2();
	public boolean isTickedK3();
	public boolean isTickedV2();
	public void saveText();
	public int tiheys();
	public int heitto();
}
