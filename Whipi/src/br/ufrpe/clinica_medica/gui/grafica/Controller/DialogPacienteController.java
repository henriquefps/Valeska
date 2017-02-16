package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DialogPacienteController implements Initializable {

	@FXML
	private TextField txfNome;
	@FXML
	private TextField txfCpf;
	@FXML
	private TextField txfRg;
	@FXML
	private TextField txfTelefone;
	@FXML
	private TextField txfCelular;
	@FXML
	private ComboBox<String> cbxEstado;
	@FXML
	private TextField txfCidade;
	@FXML
	private TextField txfBairro;
	@FXML
	private TextField txfRua;
	@FXML
	private TextField txfCep;
	@FXML
	private TextField txfComplemento;
	@FXML
	private ToggleGroup tgpSexo;
	@FXML
	private RadioButton rbtMasculino;
	@FXML
	private RadioButton rbtFeminino;
	@FXML
	private DatePicker dtpNascimento;

	private FachadaClinicaMedica fachada;

	private Telas t;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		
		fachada = FachadaClinicaMedica.getInstance();
		
		ObservableList<String> estados = FXCollections.observableArrayList(Estados.pegarEstados());
		cbxEstado.getItems().addAll(estados);
	}

	@FXML
	private void salvarPaciente() {
		cadastrar();
	}

	public void cadastrar() {
		if (isInputValid()) {
			Paciente paciente = new Paciente();
			paciente.setNome(txfNome.getText());
			paciente.setCpf(txfCpf.getText());
			paciente.setRg(txfRg.getText());
			paciente.setCelular(txfCelular.getText());
			paciente.setTelefone(txfTelefone.getText());
			if (tgpSexo.getSelectedToggle().equals(rbtMasculino)) {
				paciente.setSexo('M');
			} else {
				paciente.setSexo('F');
			}
			paciente.setDataDeNascimento(dtpNascimento.getValue());
			paciente.setEndereco(new Endereco(txfRua.getText(), txfCidade.getText(), txfBairro.getText(),
					cbxEstado.getValue(), txfCep.getText(), txfComplemento.getText()));

			try {
				fachada.cadastrarPaciente(paciente.getNome(), paciente.getCpf(), paciente.getRg(),
						paciente.getTelefone(), paciente.getCelular(), paciente.getSexo(), paciente.getEndereco(),
						paciente.getDataDeNascimento());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucesso!");
				alert.setHeaderText("Cadastro!");
				alert.setContentText("Paciente cadastrado com sucesso!");
				alert.showAndWait();
				fecharTela();
			} catch (PJCException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText("Cadastro inválido!");
				alert.setContentText("Paciente já cadastrado!");
				alert.showAndWait();
			}

		}
	}

	private boolean isInputValid() {
		String errorMessage = "";

		if (txfNome.getText() == null || txfNome.getText().length() == 0) {
			errorMessage += "Nome inválido!\n";
		}
		if (txfCpf.getText() == null || txfCpf.getText().length() == 0) {
			errorMessage += "Cpf inválido!\n";
		}
		if (txfRg.getText() == null || txfRg.getText().length() == 0) {
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
		}
		if (txfRua.getText() == null || txfRua.getText().length() == 0) {
			errorMessage += "Rua inválida!\n";
		}
		if (txfCep.getText() == null || txfCep.getText().length() == 0) {
			errorMessage += "Cep inválido!\n";
		}
		if (tgpSexo.getSelectedToggle() == null) {
			errorMessage += "Sexo inválido!\n";
		}
		LocalDate hoje = LocalDate.now();
		if (dtpNascimento.getValue() == null || dtpNascimento.getValue().isAfter(hoje)) {
			errorMessage += "Data de nascimento inválida!\n";
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
	private void fecharTela() {
		t.voltarTela();
		t.fecharTelaDialogo();
	}

}
