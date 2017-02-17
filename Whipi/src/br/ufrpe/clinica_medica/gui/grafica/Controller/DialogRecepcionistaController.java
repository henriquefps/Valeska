package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DialogRecepcionistaController implements Initializable {

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
	private TextField txfBairro;
	@FXML
	private TextField txfCidade;
	@FXML
	private TextField txfRua;
	@FXML
	private TextField txfComplemento;
	@FXML
	private TextField txfCep;
	@FXML
	private PasswordField pswSenha;
	@FXML
	private PasswordField pswConfirmarSenha;
	@FXML
	private ComboBox<String> cbxEstado;
	@FXML
	private DatePicker dtpNascimento;
	@FXML
	private ToggleGroup tgpSexo;
	@FXML
	private RadioButton rbtFeminino;
	@FXML
	private RadioButton rbtMasculino;
	
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
	private void salvar() {
		cadastrar();
	}

	public void cadastrar() {
		if (isInputValid()) {
			Recepcionista recep = new Recepcionista();
			recep.setNome(txfNome.getText());
			recep.setCpf(txfCpf.getText());
			recep.setRg(txfRg.getText());
			recep.setDataDeNascimento(dtpNascimento.getValue());
			recep.setCelular(txfCelular.getText());
			recep.setTelefone(txfTelefone.getText());
			recep.setSenha(pswSenha.getText());
			recep.setEndereco(new Endereco(txfRua.getText(), txfCidade.getText(), txfBairro.getText(),
					cbxEstado.getValue(), txfCep.getText(), txfComplemento.getText()));

			if (tgpSexo.getSelectedToggle().equals(rbtMasculino)) {
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
		} // ajeitar!!
		if (txfRua.getText() == null || txfRua.getText().length() == 0) {
			errorMessage += "Rua inválida!\n";
		}
		if (txfCep.getText() == null || txfCep.getText().length() == 0) {
			errorMessage += "Cep inválido!\n";
		}
		if (rbtMasculino == null || rbtFeminino == null) {
			errorMessage += "Sexo inválido!\n";
		}
		LocalDate hoje = LocalDate.now();
		if (dtpNascimento.getValue() == null || dtpNascimento.getValue().isAfter(hoje)) {
			errorMessage += "Data de nascimento inválida!\n";
		}
		if (pswSenha.getText() == null || pswSenha.getText().length() == 0) {
			errorMessage += "Senha inválida!\n";
		}
		if (pswConfirmarSenha.getText() == null || pswConfirmarSenha.getText().length() == 0) {
			errorMessage += "Confirmação de Senha inválida!\n";
		}
		if(!pswSenha.getText().equals(pswConfirmarSenha.getText())){
			errorMessage += "Senhas não coincidem!\n";
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
