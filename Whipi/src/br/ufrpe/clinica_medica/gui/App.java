package br.ufrpe.clinica_medica.gui;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		URL fxml = getClass().getResource("./Principal.fxml");
		Parent parent = FXMLLoader.load(fxml);
		primaryStage.setTitle("Clínica Médica");
		primaryStage.setScene(new Scene(parent));
	}

	public static void main(String[] args) {
		launch(args);
	}
}
