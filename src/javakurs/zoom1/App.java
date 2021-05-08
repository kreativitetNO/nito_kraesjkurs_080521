package javakurs.zoom1;

import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

public class App extends Application
{
	private static final String[] romTyper = { "Auditorium", "Seminarrom", "Lab", "Grupperom" };
	Rom[] romTabell;
	TextArea romListe;
	ComboBox<String> romTypeCombo;
	
	@Override
	public void start(Stage primaryStage)
	{
		romTabell = Rom.lesFraFil("romZoom.txt");
		
		primaryStage.setTitle("Zoom kurs");
		
		var romTypeFilterLbl = new Text("Velg romtype");
		romTypeCombo = new ComboBox<String>();
		romTypeCombo.getItems().addAll(romTyper);
		romTypeCombo.setValue(romTyper[0]);
		var romTypeRad = new HBox(romTypeFilterLbl, romTypeCombo);
		romTypeRad.setAlignment(Pos.CENTER_LEFT);
		romTypeRad.setSpacing(5);

		romListe = new TextArea(finnRom(romTabell, romTyper[0]));
				
		var romNummerLbl = new Label("Romnummer");
		var romNummerFld = new TextField();
		var romTypeLbl = new Label("RomType");
		var romTypeFld = new TextField();
		var plasserLbl = new Label("Plasser");
		var plasserFld = new TextField();
		var redigerBtn = new Button("Rediger");
		var avbrytBtn = new Button("Avbryt");
		
		var redigeringsTabell = new GridPane();
		redigeringsTabell.add(romNummerLbl, 0, 0);
		redigeringsTabell.add(romNummerFld, 1, 0);
		redigeringsTabell.add(romTypeLbl, 0, 1);
		redigeringsTabell.add(romTypeFld, 1, 1);
		redigeringsTabell.add(plasserLbl, 0, 2);
		redigeringsTabell.add(plasserFld, 1, 2);
		redigeringsTabell.add(redigerBtn, 2, 0, 1, 1);
		redigeringsTabell.add(avbrytBtn, 2, 1, 1, 2);
		
		var vbox = new VBox(romTypeRad, romListe, redigeringsTabell);
		
		
		
		romTypeCombo.setOnAction((event) -> behandleHendelse(event));
		
		avbrytBtn.setOnAction((event) -> {
			romNummerFld.clear();
			romTypeFld.clear();
			plasserFld.clear();
		});

		primaryStage.setScene(new Scene(vbox));
		primaryStage.show();
	}
	
	private void behandleHendelse(ActionEvent e)
	{
		if (e.getSource() == romTypeCombo)
		{
			oppdaterListe();
		}
	}
	
	private void oppdaterListe()
	{
		String valg = romTypeCombo.getValue();
		romListe.setText(finnRom(romTabell, valg));
	}
	
	private String finnRom(Rom[] romTabell, String romType)
	{
		String s = "";
		for (var rom : romTabell)
		{
			if (rom.getRomType().equals(romType))
			{
				s += rom.getRomType();
				s += '\t';
				s += rom.getRomNummer();
				s += '\t';
				s += rom.getPlasser();
				s += '\n';
			}
		}
		return s;
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
