package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class DialogRecepcionistaController implements Initializable {

	@FXML
	private TextField txfNome;
	@FXML
	private TextField txfCPF;
	@FXML
	private TextField txfRG;
	@FXML
	private TextField txfTelefone;
	@FXML
	private TextField txfCelular;
	@FXML
	private TextField txfEndereco;
	@FXML
	private TextField txfBairro;
	@FXML
	private TextField txfCidade;
	@FXML
	private TextField txfRual;
	@FXML
	private TextField txfComplemento;
	@FXML
	private TextField txfCEP;
	@FXML
	private TextField txfSenha;
	@FXML
	private ComboBox<String> cbxEstado;
	@FXML
	private DatePicker dtpNasc;
	@FXML
	private ToggleGroup tgpSexo;
	@FXML
	private RadioButton rbFeminino;
	@FXML
	private RadioButton rbMasculino;
	@FXML
	private Button btSalvar;
	@FXML
	private Button btSair;
	private FachadaClinicaMedica fachada;
	private Telas t;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		fachada = FachadaClinicaMedica.getInstance();
		t = Telas.getInstance();

		ObservableList<String> estados = FXCollections.observableArrayList(Estados.pegarEstados());
		cbxEstado.getItems().addAll(estados);
	}

	@FXML
	private void salva() {
		cadastrar();
	}

	public void cadastrar() {
		if (isInputValid()) {
			Recepcionista recep = new Recepcionista();
			recep.setNome(txfNome.getText());
			recep.setCpf(txfCPF.getText());
			recep.setRg(txfRG.getText());
			recep.setDataDeNascimento(dtpNasc.getValue());
			recep.setCelular(txfCelular.getText());
			recep.setTelefone(txfTelefone.getText());
			recep.setSenha(txfSenha.getText());
			recep.setEndereco(new Endereco(txfEndereco.getText(), txfCidade.getText(), txfBairro.getText(),
					cbxEstado.getValue(), txfCEP.getText(), txfComplemento.getText()));

			if (tgpSexo.getSelectedToggle().equals(rbMasculino)) {
				recep.setSexo('M');
			} else {
				recep.setSexo('F');
			}
			try {
				fachada.cadastrarRecepcionista(recep.getNome(), recep.getCpf(), recep.getRg(), recep.getTelefone(),
						recep.getCelular(), recep.getSexo(), recep.getEndereco(), recep.getDataDeNascimento(),
						recep.getSenha());
			} catch (PJCException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro de dados!");
				alert.setHeaderText("Cadastro invalido!");
				alert.setContentText(recep.getNome() + " ja cadastrado.");
				alert.showAndWait();
			} finally {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Cadastro Recepcionista");
				alert.setHeaderText(null);
				alert.setContentText(recep.getNome() + " cadastrado com sucesso");
				alert.showAndWait();
				sair();
			}
		}

	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (txfNome.getText() == null || txfNome.getText().length() == 0) {
			errorMessage += "Nome inválido!\n";
		}
		if (txfCPF.getText() == null || txfCPF.getText().length() == 0) {
			errorMessage += "Cpf inválido!\n";
		}
		if (txfRG.getText() == null || txfRG.getText().length() == 0) {
			errorMessage += "Rg inválido!\n";
		}
		if (txfTelefone.getText() == null || txfTelefone.getText().length() == 0) {
			errorMessage += "Telefone inválido!\n";
		}
		if (cbxEstado.getValue() == null) {
			errorMessage += "Estado inválido!\n";
		}
		if (txfCidade.getText() == null || txfCidade.getText().length() == 0) {
			errorMessage += "Cidade inválida!\n";
		}
		if (txfBairro.getText() == null || txfBairro.getText().length() == 0) {
			errorMessage += "Bairro inválido!\n";
		} // ajeitar!!
		if (txfEndereco.getText() == null || txfEndereco.getText().length() == 0) {
			errorMessage += "Rua inválida!\n";
		}
		if (txfCEP.getText() == null || txfCEP.getText().length() == 0) {
			errorMessage += "Cep inválido!\n";
		}
		if (rbMasculino == null || rbFeminino == null) {
			errorMessage += "Sexo inválido!\n";
		}
		LocalDate hoje = LocalDate.now();
		if (dtpNasc.getValue() == null || dtpNasc.getValue().isAfter(hoje)) {
			errorMessage += "Data de nascimento inválida!\n";
		}
		if (txfSenha.getText() == null || txfSenha.getText().length() == 0) {
			errorMessage += "Senha inválida!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Inválidos");
			alert.setHeaderText("Por favor, corrija	os campos inválidos!");
			alert.setContentText(errorMessage);
			alert.showAndWait();

			return false;
		}
	}

	@FXML
	private void sair() {
		t.fecharTelaDialogo();
	}
}
