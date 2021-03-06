package simu.model;

import java.util.LinkedList;

import controller.IKontrolleri;
import controller.Kontrolleri;
import eduni.distributions.ContinuousGenerator;
import simu.framework.Kello;
import simu.framework.Tapahtuma;
import simu.framework.Tapahtumalista;

// TODO:
// Palvelupistekohtaiset toiminnallisuudet, laskutoimitukset (+ tarvittavat muuttujat) ja raportointi koodattava
public class Palvelupiste {

	private LinkedList<Asiakas> jono = new LinkedList<Asiakas>(); // Tietorakennetoteutus
	
	private ContinuousGenerator generator;
	private Tapahtumalista tapahtumalista;
	private TapahtumanTyyppi skeduloitavanTapahtumanTyyppi; 
	private double palveluSumma = 0;
	private IKontrolleri kontrolleri;
	
	
	//JonoStartegia strategia; //optio: asiakkaiden järjestys
	
	private boolean varattu = false;


	public Palvelupiste(ContinuousGenerator generator, Tapahtumalista tapahtumalista, TapahtumanTyyppi tyyppi, IKontrolleri kontrolleri){
		this.tapahtumalista = tapahtumalista;
		this.generator = generator;
		this.skeduloitavanTapahtumanTyyppi = tyyppi;
		this.kontrolleri = kontrolleri;
	}


	public void lisaaJonoon(Asiakas a){   // Jonon 1. asiakas aina palvelussa
		jono.add(a);
		System.out.println("Asiakas " + a.getId() + " on lisätty jonoon. " + skeduloitavanTapahtumanTyyppi);
		kontrolleri.lisaaAsiakas(skeduloitavanTapahtumanTyyppi, jono.size());
	}

	public Asiakas otaJonosta(){  // Poistetaan palvelussa ollut		
		varattu = false; 
		kontrolleri.poistaAsiakas(skeduloitavanTapahtumanTyyppi, jono.size());
		System.out.println("Jonon koko:" + jono.size() + "-------------------------------------------------");
		return jono.poll();
		
	}

	public void aloitaPalvelu(){  //Aloitetaan uusi palvelu, asiakas on jonossa palvelun aikana
		varattu = true;
		double palveluaika = generator.sample();
		palveluSumma += palveluaika;
		tapahtumalista.lisaa(new Tapahtuma(skeduloitavanTapahtumanTyyppi,Kello.getInstance().getAika()+palveluaika));
	}


	public boolean onVarattu(){
		return varattu;
	}


	public boolean onJonossa(){
		return jono.size() != 0;
	}
	
	public double getPalveluAika() {
		return palveluSumma;
	}
	
}
