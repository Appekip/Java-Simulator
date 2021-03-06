package simu.model;

import java.util.Random;

import simu.framework.Kello;
import simu.framework.Trace;


// TODO:
// Asiakas koodataan simulointimallin edellyttämällä tavalla (data!)
public class Asiakas {
	private double saapumisaika;
	private double poistumisaika;
	private int id;
	private static int i = 1;
	private static long sum = 0;
	private int konversio;//Ostaako asiakas
	private int tuoteryhma ;//Mitä tuotetta asiakas tuli hakemaan
	Random randomi = new Random();
	private double kokonaisAika;
	
	public Asiakas(){
	    id = i++;
	    tuoteryhma = randomi.nextInt(9); //Tuoteryhmän randomisointi, 1 - 5 ovat hyllytuotteita, 6 = Puhelin, 7 = Tietokone, 8 TV, 9 = Kodinkone
	    tuoteryhma = tuoteryhma + 1;
	    konversio = randomi.nextInt(10);	//Konversion randomisointi
	    konversio = konversio + 1;
		saapumisaika = Kello.getInstance().getAika();
		Trace.out(Trace.Level.INFO, "Uusi asiakas:" + id + " : "+ "Saapui: " + saapumisaika + " Konversio: " + konversio + " Tuoteryhma: " + tuoteryhma);
	}

	public double getPoistumisaika() {
		return poistumisaika;
	}

	public void setPoistumisaika(double poistumisaika) {
		this.poistumisaika = poistumisaika;
	}

	public double getSaapumisaika() {
		return saapumisaika;
	}

	public void setSaapumisaika(double saapumisaika) {
		this.saapumisaika = saapumisaika;
	}
	
	public void raportti(){
		Trace.out(Trace.Level.INFO, "Asiakas "+id+ " saapui:" +saapumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " poistui:" +poistumisaika);
		Trace.out(Trace.Level.INFO,"Asiakas "+id+ " viipyi:" +(poistumisaika-saapumisaika));
		sum += (poistumisaika-saapumisaika);
		double keskiarvo = sum/id;
		System.out.println("Asiakkaiden läpimenoaikojen keskiarvo "+ keskiarvo);
	}

	public int getKonversio() {
		return konversio;
	}

	
	public int getTuoteryhma() {
		return tuoteryhma;
	}
	
	public int getId() {
		return id;
	}
	
	public double kokonaisAika() {
		kokonaisAika = poistumisaika - saapumisaika;
		
		return kokonaisAika;
	}

}
