package br.ufrpe.clinica_medica.gui.grafica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/*
 * controller da tela principal do recepcionista
 */
public class TelaRecepcionista implements Initializable{
	private Stage s = new Stage();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	@FXML
	void telaRecep(){
		URL url = getClass().getResource("TelaRecepcionista.fxml");
		Parent parent = null;
		try {
			parent = FXMLLoader.load(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		s.setTitle("Recepcionista");
		s.setScene(new Scene(parent));
		s.show();
	}
	
	@FXML
	void telaCadastrar_Recepcionista(){
		URL url = getClass().getResource("TelaRecepcionista_Cadastro.fxml");
		Parent parent = null;
		try {
			parent = FXMLLoader.load(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.setScene(new Scene(parent));
		s.show();
	}
}
