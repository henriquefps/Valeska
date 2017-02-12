package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TelaAdmin implements Initializable{

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	@FXML
	public void telaAdmin() {
		URL url = getClass().getResource("TelaAdmin.fxml");
		Parent parent = null;
		try {
			parent = FXMLLoader.load(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Stage s = new Stage();
		s.setTitle("Admin");
		s.setScene(new Scene(parent));
		s.show();
	}

}
