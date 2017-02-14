package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class TelaCadastrarPacienteController implements Initializable {

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
	private TextField txfEstado;
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
	private CheckBox choiceMasculino;
	@FXML
	private CheckBox choiceFeminino;
	@FXML
	private Button btnSalvar;
	@FXML
	private Button btnSair;
	@FXML
	private DatePicker dtpNascimento;

	private FachadaClinicaMedica fachada;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fachada = FachadaClinicaMedica.getInstance();
	}

	@FXML
	private void salvarPaciente() {
		char sexo;
		if (choiceMasculino.isPressed() && !choiceFeminino.isPressed()) {
			sexo = 'M';
		} else if (!choiceMasculino.isPressed() && choiceFeminino.isPressed()) {
			sexo = 'F';
		} else {
			boolean masc = choiceMasculino.isPressed();
			boolean fem = choiceFeminino.isPressed();
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro de dados!");
			alert.setHeaderText("Sexo invalido!");
			if (masc && fem) {
				alert.setContentText("Dois sexos escolhidos!");
			}
			if (!masc && !fem) {
				alert.setContentText("Nenhum sexo escolhido!");
			}
			alert.showAndWait();
			return;
		}
		String nome = txfNome.getText();
		String cpf = txfCpf.getText();
		String rg = txfRg.getText();
		String telefone = txfTelefone.getText();
		String celular = txfCelular.getText();
		Endereco endereco = new Endereco(txfRua.getText(), txfCidade.getText(), txfBairro.getText(),
				txfEstado.getText(), txfCep.getText(), txfComplemento.getText());
		LocalDate dataDeNascimento = dtpNascimento.getValue();
		try {
			fachada.cadastrarPaciente(nome, cpf, rg, telefone, celular, sexo, endereco, dataDeNascimento);
		} catch (PJCException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro de dados!");
			alert.setHeaderText("Cadastro invalido!");
			alert.setContentText("Paciente ja cadastrado.");
			alert.showAndWait();
		} finally {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Cadastro Paciente");
			alert.setHeaderText(null);
			alert.setContentText("Cadastro realizado com sucesso!");
			alert.showAndWait();
			voltarTela();
		}
	}

	@FXML
	private void voltarTela() {
		
	}

}
