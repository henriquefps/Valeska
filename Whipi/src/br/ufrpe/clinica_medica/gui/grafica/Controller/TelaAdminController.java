package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TelaAdminController implements Initializable {

	@FXML
	private Button cadastroMedico;
	@FXML
	private Button atualizaMedico;
	@FXML
	private Button removeMedico;
	@FXML
	private Button cadastroRecepcionista;
	@FXML
	private Button atualizaRecepcionista;
	@FXML
	private Button removeRecepcionista;
	@FXML
	private Button cadastroPaciente;
	@FXML
	private Button atualizaPaciente;
	@FXML
	private Button removePaciente;
	
	private Telas t;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
	}

	@FXML
	private void telaCadastroMedico() {

	}

	@FXML
	private void telaAtualizaMedico() {

	}

	@FXML
	private void telaRemoveMedico() {

	}

	@FXML
	private void telaCadastroRecepcionista() {

	}

	@FXML
	private void telaAtualizaRecepcionista() {

	}

	@FXML
	private void telaRemoveRecepcionista() {

	}

	@FXML
	private void telaCadastroPaciente() {
		t.fecharTelaDialogo();
		t.setScene(new Scene((Parent) t.carregarFXML("TelaPacienteCadastro")));
		t.abrirTela();
	}

	@FXML
	private void telaAtualizaPaciente() {

	}

	@FXML
	private void telaRemovePaciente() {

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
