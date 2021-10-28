package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import simu.model.TapahtumanTyyppi;

public class Visualisointi extends Canvas implements IVisualisointi{

	private GraphicsContext gc;
	
	private double sisäänkäyntiX = 50;
	private double sisäänkäyntiY = 200;
	
	private double hyllyX = 350;
	private double hyllyY = 240;
	
	private double teleX = 100;
	private double teleY = 60;
	
	private double techX = 350;
	private double techY = 60;
	
	private double homeX = 570;
	private double homeY = 160;
	
	private double awX = 570;
	private double awY = 60;
	
	private double k1X = 120;
	private double k1Y = 450;
	
	private double k2X = 240;
	private double k2Y = 450;
	
	private double k3X = 360;
	private double k3Y = 450;
	
	private double v1X = 480;
	private double v1Y = 450;
	
	private double v2X = 600;
	private double v2Y = 450;
	

	

	public Visualisointi(int w, int h) {
		super(w, h);
		gc = this.getGraphicsContext2D();
		tyhjennaNaytto();
	}
	

	public void tyhjennaNaytto() {
		gc.setFill(Color.DARKBLUE);
		gc.fillRect(25, 0, this.getWidth(), this.getHeight());
	}
	
	
	
	public void uusiMyymala() {
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Kassa 1" , 100, 440);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Kassa 2" , 220, 440);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Kassa 3" , 340, 440);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Varasto" , 460, 440);
		
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Varasto 2" , 580, 440);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Telepiste" , 70, 50);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Techpiste" , 320, 50);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Awpiste" , 550, 50);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Homepiste " , 540, 150);
		
		gc.setFill(Color.LAWNGREEN);
		gc.setFont(new Font(20));
		gc.fillText("Hylly" , 330, 230);
		


	}


	@Override
	public void poistaAsiakas(TapahtumanTyyppi palvelupiste, int size) {
		switch (palvelupiste){
		case SAAPUMINEN:
			gc.setFill(Color.RED);
			gc.fillOval(sisäänkäyntiX, sisäänkäyntiY, 10, 10);
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(sisäänkäyntiX, sisäänkäyntiY, 10, 10);
			break;
			
		case HYLLY:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(hyllyX, hyllyY, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(hyllyX + 15, hyllyY + -5, 150, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, hyllyX + 25, hyllyY + 12);
			break;
			
		case TELEPISTE1:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(teleX, teleY, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(teleX + 15, teleY + -5, 150, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, teleX + 25, teleY + 12);
			break;
		
		case AWPISTE1:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(awX, awY, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(awX + 15, awY + -5, 150, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, awX + 25, awY + 12);
			break;
			
		case KODINKONEPISTE1:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(homeX, homeY, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(homeX + 15, homeY + -5, 150, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, homeX + 25, homeY + 12);
			break;
		
		case TECHPISTE1:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(techX, techY, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(techX + 15, techY + -5, 150, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, techX + 25, techY + 12);
			break;
		
		case KASSA:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(k1X, k1Y, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(k1X + -35, k1Y + -50, 100, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, k1X + -32, k1Y + -35);
			break;
			
		case KASSA2:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(k2X, k2Y, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(k2X + -35, k2Y + -50, 100, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, k2X + -32, k2Y + -35);
			break;
			
		case KASSA3: 
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(k3X, k3Y, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(k3X + -35, k3Y + -50, 100, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, k3X + -32, k3Y + -35);
			break;
			
		case VARASTO:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(v1X, v1Y, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(v1X + -35, v1Y + -50, 100, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, v1X + -32, v1Y + -35);
			break;
			
		case VARASTO2:
			gc.setFill(Color.LAWNGREEN);
			gc.fillOval(v2X, v2Y, 10, 10);
			gc.setFill(Color.DARKBLUE);
			gc.fillRect(v2X + -35, v2Y + -50, 100, 20);
			gc.setFill(Color.LAWNGREEN);
			gc.fillText("Jonossa: " + size, v2X + -32, v2Y + -35);
			break;
		
		}
	}


	@Override
	public void lisääAsiakas(TapahtumanTyyppi palvelupiste, int size) {
		
		switch (palvelupiste){
				
		case HYLLY:
			gc.setFill(Color.RED);
			gc.fillOval(hyllyX, hyllyY, 10, 10);
			break;
			
		case TELEPISTE1:
			gc.setFill(Color.RED);
			gc.fillOval(teleX, teleY, 10, 10);
			break;
		
		case AWPISTE1:
			gc.setFill(Color.RED);
			gc.fillOval(awX, awY, 10, 10);
			break;
			
		case KODINKONEPISTE1:
			gc.setFill(Color.RED);
			gc.fillOval(homeX, homeY, 10, 10);
			break;
		
		case TECHPISTE1:
			gc.setFill(Color.RED);
			gc.fillOval(techX, techY, 10, 10);
			break;
		
		case KASSA:
			gc.setFill(Color.RED);
			gc.fillOval(k1X, k1Y, 10, 10);
			break;
			
		case KASSA2:
			gc.setFill(Color.RED);
			gc.fillOval(k2X, k2Y, 10, 10);
			break;
			
		case KASSA3: 
			gc.setFill(Color.RED);
			gc.fillOval(k1X, k1Y, 10, 10);
			break;
			
		case VARASTO:
			gc.setFill(Color.RED);
			gc.fillOval(v1X, v1Y, 10, 10);
			break;
			
		case VARASTO2:
			gc.setFill(Color.RED);
			gc.fillOval(v2X, v2Y, 10, 10);
			break;
		
		}
				
		
	}





		
	}
	

