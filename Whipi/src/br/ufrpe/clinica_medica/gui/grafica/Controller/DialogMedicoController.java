package br.ufrpe.clinica_medica.gui.grafica.Controller;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
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
	private TextField txfCPF;
	@FXML
	private TextField txfRG;
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
	private TextField txfCEP;
	@FXML
	private TextField txfCRM;
	@FXML
	private TextField txfConsultas;
	@FXML
	private TextField txfSenha;
	@FXML
	private TextField txfConfirmaSenha;
	@FXML
	private ComboBox<String> cbxEstados;
	@FXML
	private DatePicker dtpNascimento;
	@FXML
	private ToggleGroup sexo;
	@FXML
	private RadioButton rbFeminino;
	@FXML
	private RadioButton rbMasculino;
	
//	@FXML private Button btnSalvar;
//	@FXML private Button btnSair;

	private Stage dialog;
	private FachadaClinicaMedica fachada;
    private Telas t;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		fachada = FachadaClinicaMedica.getInstance();
		t = Telas.getInstance();
		ObservableList<String> estados = FXCollections.observableArrayList(Estados.pegarEstados());
		cbxEstados.getItems().addAll(estados);
	}

	@FXML
	private void salvarMedico() {
		cadastrar();
	}

	public void cadastrar() {
		Medico m = new Medico();
		if (isInputValid()) {
			m.setNome(txfNome.getText());
			m.setCpf(txfCPF.getText());
			m.setRg(txfRG.getText());
			m.setDataDeNascimento(dtpNascimento.getValue());
			m.setCelular(txfCelular.getText());
			m.setTelefone(txfTelefone.getText());
			m.setConsultasPorDia(txfConsultas.getAnchor());
			m.setSenha(txfSenha.getText());
			m.setEndereco(new Endereco(txfRua.getText(), txfCidade.getText(), txfBairro.getText(),
					cbxEstados.getValue(), txfCEP.getText(), txfComplemento.getText()));
			m.setNumCRM(txfCRM.getAnchor());
			m.setSenha(txfSenha.getText());
			if(sexo.getSelectedToggle().equals(rbMasculino)){
			   m.setSexo('M');
			}else{
				m.setSexo('F');
			}
			
			try{
				fachada.cadastrarMedico(m.getNome(), m.getCpf(), m.getRg(), m.getTelefone(), m.getCelular(), m.getSexo(),
						m.getEndereco(), m.getDataDeNascimento(), m.getNumCRM(), 
						m.getConsultasPorDia(), m.getSenha());
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucesso!");
				alert.setHeaderText("Cadastro!");
				alert.setContentText("Medico cadastrado com sucesso!");
				alert.showAndWait();
				fecharTela();
				
			} catch (PJCException e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText("Cadastro invalido!");
				alert.setContentText("Medico ja cadastrado!");
				alert.showAndWait();
			}
		}
	}

	private void fecharTela(){
		t.fecharTelaDialogo();
	}
	private boolean isInputValid() {
		String errorMessage = "";

		if (txfNome.getText() == null || txfNome.getText().length() == 0) {
			errorMessage += "Nome inv�lido!\n";
		}
		if (txfCPF.getText() == null || txfCPF.getText().length() == 0) {
			errorMessage += "Cpf inv�lido!\n";
		}
		if (txfRG.getText() == null || txfRG.getText().length() == 0) {
			errorMessage += "Rg inv�lido!\n";
		}
		if (txfTelefone.getText() == null || txfTelefone.getText().length() == 0) {
			errorMessage += "Telefone inv�lido!\n";
		}
		if (cbxEstados.getValue() == null) {
			errorMessage += "Estado inv�lido!\n";
		}
		if (txfCidade.getText() == null || txfCidade.getText().length() == 0) {
			errorMessage += "Cidade inv�lida!\n";
		}
		if (txfBairro.getText() == null || txfBairro.getText().length() == 0) {
			errorMessage += "Bairro inv�lido!\n";
		}
		if (txfRua.getText() == null || txfRua.getText().length() == 0) {
			errorMessage += "Rua inv�lida!\n";
		}
		if (txfCEP.getText() == null || txfCEP.getText().length() == 0) {
			errorMessage += "Cep inv�lido!\n";
		}
		if (sexo.getSelectedToggle() == null) {
			errorMessage += "Sexo inv�lido!\n";
		}
		LocalDate hoje = LocalDate.now();
		if (dtpNascimento.getValue() == null || dtpNascimento.getValue().isAfter(hoje)) {
			errorMessage += "Data de nascimento inv�lida!\n";
		}
		if(txfSenha.getText() == null || txfSenha.getText().length() == 0){
			errorMessage += "Senha inv�lida!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Inv�lidos");
			alert.setHeaderText("Por favor, corrija	os campos inv�lidos!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

	public void mostrarDetalhes(Medico log) {
		txfBairro.setText(log.getEndereco().getBairro());
		txfCelular.setText(log.getCelular());
		txfCEP.setText(log.getEndereco().getCep());
		txfCidade.setText(log.getEndereco().getCidade());
		txfComplemento.setText(log.getEndereco().getComplemento());
		txfConfirmaSenha.setText(log.getSenha());
		txfCPF.setText(log.getCpf());
		txfCRM.setText(String.valueOf(log.getNumCRM()));
		txfNome.setText(log.getNome());
		txfConsultas.setText(String.valueOf(log.getConsultasPorDia()));
		txfRG.setText(log.getRg());
		txfRua.setText(log.getEndereco().getRua());
		txfSenha.setText(log.getSenha());
		txfTelefone.setText(log.getTelefone());
		if (log.getSexo() == 'M') {
			sexo.selectToggle(rbMasculino);
		} else {
			sexo.selectToggle(rbFeminino);
		}
		dtpNascimento.setValue(log.getDataDeNascimento());

	}

	@FXML
	private void sair() {
		Telas.getInstance().voltarTela();
		Telas.getInstance().fecharTelaDialogo();
	}

}
