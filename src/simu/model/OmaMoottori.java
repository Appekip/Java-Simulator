package simu.model;

import controller.IKontrolleri;
import eduni.distributions.Negexp;
import eduni.distributions.Normal;
import simu.framework.Kello;
import simu.framework.Moottori;
import simu.framework.Saapumisprosessi;
import simu.framework.Tapahtuma;
import simu.framework.Trace;
import controller.Kontrolleri;
import simu.framework.IMoottori;


public class OmaMoottori extends Moottori{
	
	private Saapumisprosessi saapumisprosessi;
	
	//private Kontrolleri kontrolleri;
	
	private double asiakasMaara = 0;
	private double konversioMaara = 0;
	private double varastoCount = 0;
	private double kassaCount = 0;
	private double hyllyCount = 0;
	private double TechPisteCount = 0;
	private double TelePisteCount = 0;
	private double KodinkonePisteCount = 0;
	private double AWPisteCount = 0;
	private double poistuneet = 0;
	private double kokonaisAjat = 0;
	private double kassa2Count = 0;
	private double kassa3Count = 0;
	private double varasto2Count = 0;
	private double teleAika;
	private double homeAika;
	private double awAika;
	private double techAika;
	private double kassaAika;
	private double varaAika;
	private double hyllyAika; 
	
	
	public OmaMoottori(IKontrolleri kontrolleri){ // UUSI
		
		super(kontrolleri); //UUSI
		
		palvelupisteet = new Palvelupiste[11];
	
		
		palvelupisteet[0]=new Palvelupiste(new Normal(8,5), tapahtumalista, TapahtumanTyyppi.HYLLY, kontrolleri);
		palvelupisteet[1]=new Palvelupiste(new Normal(35,10), tapahtumalista, TapahtumanTyyppi.TELEPISTE1, kontrolleri);
		palvelupisteet[2]=new Palvelupiste(new Normal(65,10), tapahtumalista, TapahtumanTyyppi.KODINKONEPISTE1, kontrolleri);
		palvelupisteet[3]=new Palvelupiste(new Normal(45,10), tapahtumalista, TapahtumanTyyppi.AWPISTE1, kontrolleri);
		palvelupisteet[4]=new Palvelupiste(new Normal(55,10), tapahtumalista, TapahtumanTyyppi.TECHPISTE1, kontrolleri);
		palvelupisteet[5]=new Palvelupiste(new Normal(7,5), tapahtumalista, TapahtumanTyyppi.KASSA, kontrolleri);
		palvelupisteet[6]=new Palvelupiste(new Normal(12,10), tapahtumalista, TapahtumanTyyppi.VARASTO, kontrolleri);
		palvelupisteet[7]=new Palvelupiste(new Normal(10,5), tapahtumalista, TapahtumanTyyppi.POISTUMINEN, kontrolleri);
		palvelupisteet[8]=new Palvelupiste(new Normal(7,5), tapahtumalista, TapahtumanTyyppi.KASSA2, kontrolleri);
		palvelupisteet[9]=new Palvelupiste(new Normal(7,5), tapahtumalista, TapahtumanTyyppi.KASSA3, kontrolleri);
		palvelupisteet[10]=new Palvelupiste(new Normal(12,10), tapahtumalista, TapahtumanTyyppi.VARASTO2, kontrolleri);
		
		
		saapumisprosessi = new Saapumisprosessi(new Normal(kontrolleri.getTiheys(),kontrolleri.getHeitto()), tapahtumalista, TapahtumanTyyppi.SAAPUMINEN);

	}

	
	@Override
	protected void alustukset() {
		saapumisprosessi.generoiSeuraava(); // Ensimm??inen saapuminen j??rjestelm????n
	}
	
	@Override
	protected void suoritaTapahtuma(Tapahtuma t){  // B-vaiheen tapahtumaa
		Asiakas a;
		kontrolleri.luoMyym??l??();
		
		switch (t.getTyyppi()){
		
			case SAAPUMINEN: palvelupisteet[0].lisaaJonoon(new Asiakas());	
				saapumisprosessi.generoiSeuraava();			      
		       break;

			case HYLLY:	a = palvelupisteet[0].otaJonosta();
					asiakasMaara++;
					hyllyAika += palvelupisteet[0].getPalveluAika();
					
					switch (a.getTuoteryhma()) {
					
					case 1:	
						
						hyllyCount++;
						if (a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
							if (palvelupisteet[5].onJonossa() == true) {
								if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
									palvelupisteet[8].lisaaJonoon(a);
								}
									
								if(kontrolleri.tickedK3() == 1) {
									palvelupisteet[9].lisaaJonoon(a);
								}
							}
							palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
						}
						else {//Asiakas ei osta, joten poistuu myym??l??st??
							palvelupisteet[7].lisaaJonoon(a);
							poistuneet++;
						}
						break;
						
					case 2:
						hyllyCount++;
						if (a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
							if (palvelupisteet[5].onJonossa() == true) {
								if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
									palvelupisteet[8].lisaaJonoon(a);
								}
									
								if(kontrolleri.tickedK3() == 1) {
									palvelupisteet[9].lisaaJonoon(a);
								}
							}
							palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
						}
						else {//Asiakas ei osta, joten poistuu myym??l??st??
							palvelupisteet[7].lisaaJonoon(a);
							poistuneet++;
						}
						break;
						
					case 3:
						hyllyCount++;
						if (a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
							if (palvelupisteet[5].onJonossa() == true) {
								if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
									palvelupisteet[8].lisaaJonoon(a);
								}
									
								if(kontrolleri.tickedK3() == 1) {
									palvelupisteet[9].lisaaJonoon(a);
								}

							}
							palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
						}
						else {//Asiakas ei osta, joten poistuu myym??l??st??
							palvelupisteet[7].lisaaJonoon(a);
							poistuneet++;
						}
						break;
						
					case 4:
						hyllyCount++;
						if (a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
							if (palvelupisteet[5].onJonossa() == true) {
								if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
									palvelupisteet[8].lisaaJonoon(a);
								}
									
								if(kontrolleri.tickedK3() == 1) {
									palvelupisteet[9].lisaaJonoon(a);
								}
							}
							palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
						} 
						else {//Asiakas ei osta, joten poistuu myym??l??st??
							palvelupisteet[7].lisaaJonoon(a);
							poistuneet++;
						}
						break;
						
					case 5:
						hyllyCount++;
						if (a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
							if (palvelupisteet[5].onJonossa() == true) {
								if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
									palvelupisteet[8].lisaaJonoon(a);
								}
									
								if(kontrolleri.tickedK3() == 1) {
									palvelupisteet[9].lisaaJonoon(a);
								}
								
							}
							palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
						}
						else {//Asiakas ei osta, joten poistuu myym??l??st??
							palvelupisteet[7].lisaaJonoon(a);
							poistuneet++;
						}
						break;
						
					case 6://Tuoteryhm?? on 6, joten asiakas siirtyy puhelinpisteen jonoon
							palvelupisteet[1].lisaaJonoon(a);
							
						break;
						
					case 7://Tuoteryhm?? on 7, joten asiakas siirtyy tehcpisteen jonoon
							palvelupisteet[4].lisaaJonoon(a);
						break;
						
					case 8://Tuoteryhm?? on 7, joten asiakas siirtyy awpisteen jonoon
							palvelupisteet[3].lisaaJonoon(a);
							
						break;
						
					case 9://Tuoteryhm?? on 9, joten asiakas siirtyy kodinkonepisteen jonoon
							palvelupisteet[2].lisaaJonoon(a);	
						break;
				
				}
					
			break;
			
			case TELEPISTE1: a = palvelupisteet[1].otaJonosta();
				TelePisteCount++;
				teleAika += palvelupisteet[1].getPalveluAika();
				System.out.println("Palveluaika on: " + palvelupisteet[1].getPalveluAika());
				if(a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
					if (palvelupisteet[5].onJonossa() == true) {
						if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
							palvelupisteet[8].lisaaJonoon(a);
						}
							
						if(kontrolleri.tickedK3() == 1) {
							palvelupisteet[9].lisaaJonoon(a);
						}

					}
					palvelupisteet[5].lisaaJonoon(a);
				}
				
				else {//Asiakas ei osta, joten poistuu myym??l??st??
					palvelupisteet[7].lisaaJonoon(a);
					poistuneet++;
				}	
				
				break;
				
			case AWPISTE1: a = palvelupisteet[3].otaJonosta();
				AWPisteCount++;
				awAika += palvelupisteet[3].getPalveluAika();
				System.out.println("Palveluaika on: " + palvelupisteet[3].getPalveluAika());
				if(a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
					if (palvelupisteet[5].onJonossa() == true) {
						if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
							palvelupisteet[8].lisaaJonoon(a);
						}
							
						if(kontrolleri.tickedK3() == 1) {
							palvelupisteet[9].lisaaJonoon(a);
						}
						
					}
					palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
				}
				else {//Asiakas ei osta, joten poistuu myym??l??st??
					palvelupisteet[7].lisaaJonoon(a);
					poistuneet++;
				}
				break;
				
			case KODINKONEPISTE1: a = palvelupisteet[2].otaJonosta();
				KodinkonePisteCount++;
				homeAika += palvelupisteet[2].getPalveluAika();
				System.out.println("Palveluaika on: " + palvelupisteet[2].getPalveluAika());
				if(a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
					if (palvelupisteet[5].onJonossa() == true) {
						if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
							palvelupisteet[8].lisaaJonoon(a);
						}
						if(kontrolleri.tickedK3() == 1) {
							palvelupisteet[9].lisaaJonoon(a);
						}
					}
					palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
				}
				else {//Asiakas ei osta, joten poistuu myym??l??st??
					palvelupisteet[7].lisaaJonoon(a);
					poistuneet++;
				}
				break;
				
			case TECHPISTE1: a = palvelupisteet[4].otaJonosta();
				TechPisteCount++;	
				techAika += palvelupisteet[4].getPalveluAika();
				System.out.println("Palveluaika on: " + palvelupisteet[4].getPalveluAika());
				if(a.getKonversio() < 6) {//Tarkistetaan ostaako asiakas
					
					if (palvelupisteet[5].onJonossa() == true) {
						if(kontrolleri.tickedK2() == 1 && palvelupisteet[8].onJonossa() == false) {
							palvelupisteet[8].lisaaJonoon(a);
						}
							
						if(kontrolleri.tickedK3() == 1) {
							palvelupisteet[9].lisaaJonoon(a);
						}

					}
					palvelupisteet[5].lisaaJonoon(a);//Lis??t????n kassan 1 jonoon 
				}
				else {//Asiakas ei osta, joten poistuu myym??l??st??
					palvelupisteet[7].lisaaJonoon(a);
					poistuneet++;
				}
				break;
			
			case KASSA: a = palvelupisteet[5].otaJonosta();
			kassaAika += palvelupisteet[5].getPalveluAika();
			System.out.println("Palveluaika on: " + palvelupisteet[5].getPalveluAika());
								kassaCount++;
								konversioMaara++;
							if (a.getTuoteryhma() < 6) {//Tarkistetaan tuote 
								palvelupisteet[7].lisaaJonoon(a);//Asiakkaan ei tarvitse menn?? varastoon, joten poistuu oston j??lkeen
								poistuneet++;
							}
							
							else if (palvelupisteet[6].onJonossa() == true && kontrolleri.tickedV2() == 1){
								palvelupisteet[10].lisaaJonoon(a);
								}
							
							else {
								palvelupisteet[6].lisaaJonoon(a);//Asiakas menee varastolle noutamaan tuotetta
								}
	
			break;
			
			case KASSA2: a = palvelupisteet[8].otaJonosta();
			kassaAika += palvelupisteet[8].getPalveluAika();
			System.out.println("Palveluaika on: " + palvelupisteet[8].getPalveluAika());
						kassa2Count++;
						konversioMaara++;
						if (a.getTuoteryhma() < 6) {//Tarkistetaan tuote 
							palvelupisteet[7].lisaaJonoon(a);//Asiakkaan ei tarvitse menn?? varastoon, joten poistuu oston j??lkeen
							poistuneet++;
						}
						
						else if (palvelupisteet[6].onJonossa() == true && kontrolleri.tickedV2() == 1){
							palvelupisteet[10].lisaaJonoon(a);
							}
						
						else {
							
							
							palvelupisteet[6].lisaaJonoon(a);//Asiakas menee varastolle noutamaan tuotetta
						}
				
				break;
				
			case KASSA3: a = palvelupisteet[9].otaJonosta();
					kassa3Count++;
					konversioMaara++;
					kassaAika += palvelupisteet[9].getPalveluAika();
					System.out.println("Palveluaika on: " + palvelupisteet[9].getPalveluAika());
					if (a.getTuoteryhma() < 6) {//Tarkistetaan tuote 
						palvelupisteet[7].lisaaJonoon(a);//Asiakkaan ei tarvitse menn?? varastoon, joten poistuu oston j??lkeen
						poistuneet++;
					}
					
					else if (palvelupisteet[6].onJonossa() == true && kontrolleri.tickedV2() == 1){
						palvelupisteet[10].lisaaJonoon(a);
						}
					
					else {
						palvelupisteet[6].lisaaJonoon(a);//Asiakas menee varastolle noutamaan tuotetta
					}
				break;
			
			case VARASTO:a = palvelupisteet[6].otaJonosta();
			varaAika += palvelupisteet[6].getPalveluAika();
			System.out.println("Palveluaika on: " + palvelupisteet[6].getPalveluAika());
			      		  palvelupisteet[7].lisaaJonoon(a); 
			      		  varastoCount++;
			      		  poistuneet++;
			break;
			
			case VARASTO2:a = palvelupisteet[10].otaJonosta();
			varaAika += palvelupisteet[10].getPalveluAika();
			System.out.println("Palveluaika on: " + palvelupisteet[10].getPalveluAika());
		    		  palvelupisteet[7].lisaaJonoon(a); 
		    		  varasto2Count++;
		    		  poistuneet++;	  
				break;
			
			case POISTUMINEN: a = palvelupisteet[7].otaJonosta();
					  a.setPoistumisaika(Kello.getInstance().getAika());
					  kokonaisAjat = kokonaisAjat + a.kokonaisAika();
			}
	}

	
	@Override
	public void tulokset() {
		System.out.println("Asiakkaita oli " + asiakasMaara + " Ostaneita oli " + konversioMaara + ". Konversio prosentti oli: " + (konversioMaara/asiakasMaara) * 100 + "%");
		System.out.println("Hyllyill?? oli " + hyllyCount + " asiakasta.");
		System.out.println("Telepisteell?? pisteill?? oli " + TelePisteCount + " asiakasta.");
		System.out.println("Tech pisteill?? oli " + TechPisteCount + " asiakasta.");
		System.out.println("Kodinkone pisteill?? oli " + KodinkonePisteCount + " asiakasta.");
		System.out.println("AW pisteill?? oli " + AWPisteCount + " asiakasta.");
		System.out.println("Kassalla oli " + kassaCount + " asiakasta.");
		System.out.println("Varastolla oli " + varastoCount + " asiakasta.");
		System.out.println("Myym??l??st?? poistui " + poistuneet + " asiakasta.");	
		System.out.println("Teleaika  " + teleAika);	
		System.out.println("Kokonaisaika  " + kokonaisAjat);	
	}
	
	public String textAreaTulokset() {
		
		int am = (int) asiakasMaara;
		int km = (int) konversioMaara;
		int hc = (int) hyllyCount;
		int tc = (int) TelePisteCount;
		int tec = (int) TechPisteCount;
		int kc = (int) KodinkonePisteCount;
		int ac = (int) AWPisteCount;
		int kass = (int) kassaCount;
		int kass2 = (int) kassa2Count;
		int kass3 = (int) kassa3Count;
		int var = (int) varastoCount;
		int pois = (int) poistuneet;
		double konversioProsentti = (konversioMaara/asiakasMaara) * 100;
		int kp = (int) konversioProsentti;
		double keskiarvo = kokonaisAjat / asiakasMaara;
		int kaa = (int) keskiarvo;
		int var2 = (int) varasto2Count;
		double teleKA = teleAika / TelePisteCount;
		double techKA = techAika / TechPisteCount;
		double homeKA = homeAika / KodinkonePisteCount;
		double awKA = awAika / AWPisteCount;
		double kassaKA = kassaAika / kassaCount;
		double varaKA = varaAika / varastoCount;
		double hyllyKA = hyllyAika / asiakasMaara;
		int hyka = (int) hyllyKA;
		int tlka = (int) teleKA;
		int tcka = (int) techKA;
		int hka = (int) homeKA;
		int awka = (int) awKA;
		int kka = (int) kassaKA;
		int vka = (int) varaKA;
		double teleRasi = (teleAika / kokonaisAjat) 	;
		double techRasi = (techAika / kokonaisAjat) * 100;
		double homeRasi =  (homeAika / kokonaisAjat ) * 100 ;
		double awRasi = (awAika / kokonaisAjat) * 100 ;
		double kassaRasi =  (kassaAika / kokonaisAjat) * 100;
		double varastoRasi = (varaAika / kokonaisAjat) * 100;
		int ta = (int) teleAika;
		int tca = (int) techAika;
		int ha = (int) homeAika;
		int awa = (int) awAika;
		int ka = (int) kassaAika;
		int va = (int) varaAika;
		int tlr = (int) teleRasi;
		int tcr = (int) techRasi;
		int hmr = (int) homeRasi;
		int awr = (int) awRasi;
		int kar = (int) kassaRasi;
		int vra = (int) varastoRasi;
		 
		
		
		return "Asiakkaita oli " + am +  " Ostaneita oli " + km + ". " + "\n" 
		+ "Ostaneiden asiakkaiden prosentti oli: " + kp + "%" +  "\n"
		+ "Hyllyillt?? osti " + hc + " asiakasta." + "\n"
		+ "Telell?? oli " + tc + " asiakasta." + " Keskim????r??inen palveluaika oli: " + tlka + " ajan yksikk????" +"\n"
		+ "Kokonaispalveluaika oli " + ta + ", k??ytt??aste oli " + tlr + "%" + "\n"
		+ "Techill?? oli " + tec + " asiakasta." + " Keskim????r??inen palveluaika oli: " + tcka + " ajan yksikk????" + "\n"
		+ "Kokonaispalveluaika oli " + tca + ", k??ytt??aste oli " + tcr + "%" +   "\n"
		+ "Home pisteill?? oli " + kc + " asiakasta." + " Keskim????r??inen palveluaika oli: " + hka  + " ajan yksikk????" +"\n"
		+ "Kokonaispalveluaika oli " + ha + ", k??ytt??aste oli " + hmr + "%" + "\n"
		+ "AW pisteill?? oli " + ac + " asiakasta." + " Keskim????r??inen palveluaika oli: " + awka + " ajan yksikk????" + "\n"
		+ "Kokonaispalveluaika oli " + awa + ", k??ytt??aste oli " + awr + "%" + "\n"
		+ "Kassa 1:ll?? oli " + kass + " asiakasta. Kassa 2:lla oli "+ kass2 + " asiakasta." + "\n" 
		+ "Kassa 3:lla oli "+ kass3 + " asiakasta." + "\n"
		+ "Kassojen keskim????rinen palveluaika oli: " + kka + " ajan yksikk????" +"\n"
		+ "Kokonaispalveluaika oli " + ka + ", k??ytt??aste oli " + kar + "%" + "\n"
		+ "Varasto 1:ll?? oli " + var + " asiakasta. Varasto 2:lla oli " + var2 +  " asiakasta. " +  "\n"
		+ "Varaston keskim????r??inen palveluaika oli: " + vka + " ajan yksikk????" +"\n" 
		+ "Kokonaispalveluaika oli " + va + ", k??ytt??aste oli " + vra + "%" + "\n"
		+ "Keskim????r??inen l??pimeno aika oli " +  kaa + " ajan yksikk????." ;
	}




	
}
