package br.ufrpe.clinica_medica.gui;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
	
	private Stage primary;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primary = primaryStage;
		URL fxml = getClass().getResource("Principal.fxml");
		
		FXMLLoader l = new FXMLLoader();
		l.setLocation(App.class.getResource("Principal.fxml"));
		PrincipalController c = l.getController();
		c.setApp(this);
		
		Parent parent = FXMLLoader.load(fxml);
		primary.setTitle("Clínica Médica");
		primary.setScene(new Scene(parent));
		primary.show();
		
		
	}
	
	public void telaCadatrarMedico() throws Exception{
		URL url = getClass().getResource("TCadastrarMedicoController.fxml");
		Parent parent = FXMLLoader.load(url);
		Stage s = new Stage();
		primary.close();
		s.setTitle("Cadastro Médicos");
		s.setScene(new Scene(parent));
		primary = s;
		primary.show();
	}
	

	public static void main(String[] args) {
		launch(args);
	}
}
