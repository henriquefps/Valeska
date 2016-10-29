package br.ufrpe.clinica_medica.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class DialogMedicoController implements Initializable {
	
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
	private TextField txfComplemento;
	@FXML
	private TextField txfCEP;
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
	private DatePicker dtpNasc;
	@FXML
	private ToggleGroup sexo;
	@FXML
	private RadioButton rbFeminino;
	@FXML
	private RadioButton rbMasculino;
	
	private Stage dialog;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void ok(){
		//Medico m = new Medico(txfNome.getText(), txfCPF.getText(), txfRG.getText(), txfTelefone.getText(), txfCelular.getText(), sexo, endereco, dataDeNascimento, numCRM, consultasPorDia, senha)
	}
	
	@FXML
	private void cancelar(){
		
	}
	

}
