package br.ufrpe.clinica_medica;

import br.ufrpe.clinica_medica.gui.grafica.Controller.Telas;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) {

		Telas t = Telas.getInstance();
		t.setStage(primaryStage);
		t.setA(new App());

		t.setDialogStage(new Stage());
		t.setScene(new Scene((Parent) t.carregarFXML("TelaPrincipal")));
		t.abrirTelaDialogo();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
