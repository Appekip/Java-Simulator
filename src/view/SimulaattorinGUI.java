package view;


import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import controller.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import simu.framework.Trace;
import simu.framework.Trace.Level;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.Scene;
import javafx.scene.paint.Color;



public class SimulaattorinGUI extends Application implements ISimulaattorinUI{
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   LocalDateTime now = LocalDateTime.now();  

	//Kontrollerin esittely (tarvitaan käyttöliittymässä)
	private IKontrolleri kontrolleri;

	// Käyttöliittymäkomponentit:
	private TextField aika;
	private Label aika1;
	private TextField viive;
	private Label viive1;
	private TextField tulos;
	private TextArea console;
	private Button kaynnistaButton;
	private Button hidastaButton;
	private Button nopeutaButton;
	private CheckBox Varasto2;
	private CheckBox Kassa2;
	private CheckBox Kassa3;
	private Button clear;
	private Button Save;
	private Button Load;
	private Button Reset;
	private TextField asiakkaat;
	private TextField tiheys;
	private Label as;
	private Label tiheystext;
	
	
	private final Border border = new Border( new BorderStroke( Color.BLACK, BorderStrokeStyle.SOLID,new CornerRadii (5.0),new BorderWidths(2)  ) );
		
	private IVisualisointi naytto;
	//private IVisualisointi naytto1;
	


	@Override
	public void init(){
		
		Trace.setTraceLevel(Level.INFO);
		kontrolleri = new Kontrolleri(this);
		
		
	}


	@Override
	public void start(Stage primaryStage) throws IOException {
		// Käyttöliittymän rakentaminen
		try {
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);		
			        
			    }
			});
						
			
			primaryStage.setTitle("Simulaattori");	
			
			
			 primaryStage.getIcons().add(new Image("view/Gigantti.png"));
			 
			
			kaynnistaButton = new Button();
			kaynnistaButton.setText("Käynnistä simulointi");
			kaynnistaButton.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
			kaynnistaButton.setBorder(border);
			kaynnistaButton.setTextFill(Color.BLACK);
			kaynnistaButton.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                kontrolleri.kaynnistaSimulointi();
	            }
	        });
			
			
			console = new TextArea("");
			console.setFont(Font.font("Tahoma", FontWeight.NORMAL, 11));
			console.setPrefWidth(200);
			console.setPrefHeight(200);
			//console.setText(text);
			console.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (9.0), new Insets(10, 10, 10, 10))));
			console.setBorder(border);					   
			   			
			
			Varasto2 = new CheckBox();
			Varasto2.setText("Varasto 2");
			Varasto2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
			//CheckBox1.setOnAction(e -> kontrolleri.hidasta());
			Varasto2.setTextFill(Color.LAWNGREEN);
			
			
						
			Kassa3 = new CheckBox();
			Kassa3.setText("Kassa 3");
			Kassa3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
			//CheckBox.setOnAction(e -> kontrolleri.hidasta());
			Kassa3.setTextFill(Color.LAWNGREEN);
			
			Kassa2 = new CheckBox();
			Kassa2.setText("Kassa 2");
			Kassa2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
			//CheckBox.setOnAction(e -> kontrolleri.hidasta());
			Kassa2.setTextFill(Color.LAWNGREEN);
			

			hidastaButton = new Button();
			hidastaButton.setText("Hidasta");
			hidastaButton.setOnAction(e -> kontrolleri.hidasta());
			hidastaButton.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
			hidastaButton.setBorder(border);

			nopeutaButton = new Button();
			nopeutaButton.setText("Nopeuta");
			nopeutaButton.setOnAction(e -> kontrolleri.nopeuta());
			nopeutaButton.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
			nopeutaButton.setBorder(border);
			
			Save = new Button();
			Save.setText("Save");
			Save.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
			Save.setBorder(border);
			Save.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                saveText();
	                console.appendText("\n" + "Saved to a file called: Simulation_data.txt");
	            }
			});
			
			Load = new Button();
			Load.setText("Load");
			Load.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
			Load.setBorder(border);
			Load.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	loadText();
	            }
			});
			
			Reset = new Button();
			Reset.setText("Reset file");
			Reset.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
			Reset.setBorder(border);
			Reset.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	            	resetFile();
	            }
			});
			
			clear = new Button();
			clear.setText("Clear");
			clear.setBackground(new Background(new BackgroundFill(Color.LAWNGREEN,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
			clear.setBorder(border);
			clear.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                setTextArea("");
	            }
	        });
				    	        	      
	        aika = new TextField("8000");
	        aika.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
	        aika.setPrefWidth(150);
	        aika.setBorder(border);	        	        		
	        aika.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
	        
	        asiakkaat = new TextField("8");
	        asiakkaat.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
	        asiakkaat.setPrefWidth(150);
	        asiakkaat.setBorder(border);	        	        		
	        asiakkaat.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
	        
	        tiheys = new TextField("3");
	        tiheys.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
	        tiheys.setPrefWidth(150);
	        tiheys.setBorder(border);	        	        		
	        tiheys.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
	        
	        
	        aika1 = new Label("Simulointiaika");
	        aika1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        aika1.setTextFill(Color.LAWNGREEN);
	        aika1.underlineProperty();
	        
	        viive1 = new Label("Viive");
	        viive1.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        viive1.setTextFill(Color.LAWNGREEN);
	        viive1.underlineProperty();
	        
	        as = new Label("Saapumis tiheys");
	        as.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        as.setTextFill(Color.LAWNGREEN);
	        as.underlineProperty();
	        
	        tiheystext = new Label("Tiheyden heitto");
	        tiheystext.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
	        tiheystext.setTextFill(Color.LAWNGREEN);
	        tiheystext.underlineProperty();

	        viive = new TextField("10");
	        viive.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
	        viive.setPrefWidth(150);
	        viive.setBorder(border);
	        viive.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));
	                	        
	       // tulos = new TextField("SSS");
	      //  tulos.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
	     //   tulos.setPrefWidth(150);
	     //   tulos.setBorder(border);
	     //  tulos.setBackground(new Background(new BackgroundFill(Color.WHITE,new CornerRadii (7.0), new Insets(0, 0, 0, 0))));

	        HBox hBox = new HBox();
	        hBox.setPadding(new Insets(15, 15, 15, 15)); // marginaalit ylä, oikea, ala, vasen
	        hBox.setSpacing(10);   // noodien välimatka 10 pikseliä
	        hBox.setBackground(new Background(new BackgroundFill(Color.ROYALBLUE, CornerRadii.EMPTY, new Insets(-20, -20, -20, -40))));
	    
	        
	        GridPane grid = new GridPane();
	        grid.setAlignment(Pos.TOP_CENTER);
	        grid.setVgap(10);
	        grid.setHgap(10);	        	           
	        grid.add(aika, 0, 1);
	        grid.add(viive1, 1, 0);// sarake, rivi
	        grid.add(viive, 1, 1);               // sarake, rivi
	        grid.add(kaynnistaButton,0, 4);     // sarake, rivi
	        grid.add(Varasto2, 1, 6);            // sarake, rivi
	        grid.add(nopeutaButton, 0, 5);               // sarake, rivi
	       grid.add(hidastaButton, 0, 6);  
	        grid.add(Kassa2, 1, 4);// sarake, rivi
	        grid.add(Kassa3, 1, 5);	        	        
	        grid.add(console, 0, 7, 3, 7);
	        grid.add(aika1, 0, 0);
	        grid.add(Save, 2, 3);
	        grid.add(clear, 2, 4);
	        grid.add(Reset, 2, 6);
	        grid.add(Load, 2, 5);
	        grid.add(asiakkaat, 0, 3);
	        grid.add(tiheys, 1, 3);
	        grid.add(tiheystext, 1, 2);
	        grid.add(as, 0, 2);
	        
	        naytto = new Visualisointi(700,480);
	        	        
	        grid.setBackground(
	    			new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, new Insets(-20, -20, -20, -40))));
	      //  naytto1 = new Visualisointi(600, 400);

	        // Täytetään boxi:
	        hBox.getChildren().addAll(grid, (Canvas)naytto);
	        
	        Scene scene = new Scene(hBox);
	        primaryStage.setScene(scene);
	        primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}


	//Käyttöliittymän rajapintametodit (kutsutaan kontrollerista)

	@Override
	public double getAika(){
		int i;
		try {  
			   i = Integer.parseInt(aika.getText()); 
			    return i;
			  } catch(NumberFormatException e){  
				  JOptionPane.showMessageDialog(null, "Syötä numeerinen arvo! Ajaksi asetettu 8000.");
				  return 8000;
			  }  
	}

	@Override
	public long getViive(){
		int i;
		try {  
			   i = Integer.parseInt(viive.getText()); 
			    return i;
			  } catch(NumberFormatException e){  
				  JOptionPane.showMessageDialog(null, "Syötä numeerinen arvo! Viiveeksi asetettu 10.");
				  return 8000;
			  }  
	}
	

	@Override
	public void setLoppuaika(double aika){
		 DecimalFormat formatter = new DecimalFormat("#0.00");
		 this.tulos.setText(formatter.format(aika));
	}


	@Override
	public IVisualisointi getVisualisointi() {
		 return naytto ;
		 
	}
	
	public void setTextArea(String s) {
		console.setText(s);
	}
	
	public boolean isTickedK2() {
		
		if (Kassa2.isSelected() == true) {
			return true;
		}
		return false;
		
	}
	
public boolean isTickedK3() {
		
		if (Kassa3.isSelected() == true) {
			return true;
		}
		return false;
		
	}

public boolean isTickedV2() {
	
	if (Varasto2.isSelected() == true) {
		return true;
	}
	return false;
	
}
public void saveText() {
	String s = console.getText();
	try(FileWriter fw = new FileWriter("Simulation_data.txt", true);
		    BufferedWriter bw = new BufferedWriter(fw);
		    PrintWriter out = new PrintWriter(bw))
		{
		   
			out.println("Simulaatio ajettiin: " + dtf.format(now));
		    out.println(s);
		    out.println("----------------------------");

		} catch (IOException e) {
		
		}
}

public void loadText() {
	 try {
	      File myObj = new File("Simulation_data.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        console.appendText(data + "\n");
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
		
		}
}

public void resetFile() {
	
	try (PrintWriter out = new PrintWriter("Simulation_data.txt")) {
		setTextArea("File has been reset.");
	    out.println("");
	} catch (IOException e) {
		
		}
}


	public int tiheys() {
		int i;
		try {  
			   i = Integer.parseInt(asiakkaat.getText()); 
			    return i;
			  } catch(NumberFormatException e){  
				  JOptionPane.showMessageDialog(null, "Syötä numeerinen arvo! Heitoksi asetettu 8.");
				  return 3;
			  } 
		} 
		
	
	public int heitto() {
		int i;
		try {  
			   i = Integer.parseInt(tiheys.getText()); 
			    return i;
			  } catch(NumberFormatException e){  
				  JOptionPane.showMessageDialog(null, "Syötä numeerinen arvo! Tiheydeksi asetettu 3.");
				  return 8;
			  }  
	}
	
	
	
	
	// JavaFX-sovelluksen (käyttöliittymän) käynnistäminen

	public static void main(String[] args) {
		launch(args);
	}
	
}
