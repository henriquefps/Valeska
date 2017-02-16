package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * controller da tela principal do recepcionista
 */
public class TelaRecepcionistaController implements Initializable{
	
	private Telas t; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		t.getStage().centerOnScreen();
		t.getStage().setMaximized(true);
		t.getStage().setResizable(false);
		t.getStage().setTitle("Ola Recepcionista");
	}
	
	@FXML
	private void telaListarMedico(){
		
	}
	@FXML
	private void telaCadastroPaciente() {
		t.setScene(new Scene((Parent) t.carregarFXML("TelaPacienteCadastro")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
	}
	@FXML
	private void telaAtualizaPaciente(){
		
	}
	@FXML
	private void telaListarPaciente(){
		
	}
	@FXML
	private void telaRemovePaciente(){
		
	}
	@FXML
	private void telaAtualizarRecepcionista(){
		
	}
	@FXML
	private void telaCadastroConsulta(){
		
	}
	@FXML
	private void telaAtualizaConsulta(){
		
	}
	@FXML
	private void telaRemoveConsulta(){
		
	}
	@FXML
	private void telaListaConsulta(){
		
	}
	@FXML
	private void logoff() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Logoff");
		alert.setContentText("Deseja fazer logoff?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			t.voltarTela();
			t.abrirTela();
		} else {
		    alert.close();
		}
	}
	
	@FXML
	private void sair() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Sair");
		alert.setContentText("Deseja fechar o programa?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(0);
		} else {
		    alert.close();
		}
	}

	
	
	
}
