package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
		t.setScene(new Scene((Parent) t.carregarFXML("TelaPacienteCadastro")));
		t.abrirTelaDialogo();
	}

	@FXML
	private void telaAtualizaPaciente() {

	}

	@FXML
	private void telaRemovePaciente() {

	}
}
