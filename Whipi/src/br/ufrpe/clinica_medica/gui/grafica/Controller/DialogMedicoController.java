 package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DialogMedicoController implements Initializable {
	
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
	private TextField txfRua;
	@FXML
	private TextField txfBairro;
	@FXML
	private TextField txfCidade;
	@FXML
	private TextField txfComplemento;
	@FXML
	private TextField txfCep;
	@FXML
	private TextField txfCRM;
	@FXML
	private TextField txfNumConsultas;
	@FXML
	private TextField txfSenha;
	@FXML
	private TextField txfConfirmaSenha;
	@FXML
	private ComboBox<String> cbxEstado;
	@FXML
	private DatePicker dtpNascimento;
	@FXML
	private ToggleGroup sexo;
	@FXML
	private RadioButton rbFeminino;
	@FXML
	private RadioButton rbMasculino;
	
	private Stage dialog;
	private FachadaClinicaMedica fachada;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fachada = FachadaClinicaMedica.getInstance();
		ObservableList<String> estados = FXCollections.observableArrayList(Estados.pegarEstados());
		cbxEstado.getItems().addAll(estados);
	}
	@FXML
	private void salvarMedico(){
		
	}
	
	public void cadastrar(){
		Medico m = new Medico();
		if(isInputValid()){
			m.setNome(txfNome.getText());
			m.setCpf(txfCpf.getText());
			m.setDataDeNascimento(dtpNascimento.getValue());
			m.setCelular(txfCelular.getText());
		//Parei Aqui...
			
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
		if (sexo.getSelectedToggle() == null) {
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
	

}
