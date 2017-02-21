package br.ufrpe.clinica_medica;

import java.time.LocalDate;
import java.time.LocalDateTime;

import br.ufrpe.clinica_medica.exceptions.ECException;
import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.gui.grafica.Controller.TelaRecepcionistaController;
import br.ufrpe.clinica_medica.gui.grafica.Controller.Telas;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.GerenciamentoConsulta;
import br.ufrpe.clinica_medica.negocio.GerenciamentoMedico;
import br.ufrpe.clinica_medica.negocio.GerenciamentoPaciente;
import br.ufrpe.clinica_medica.negocio.GerenciamentoRecepcionista;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
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
