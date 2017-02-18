package br.ufrpe.clinica_medica.gui.grafica.Controller;


import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
	@FXML
	private Label labelTitle;
    @FXML
    private Button btnSave;
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
			errorMessage += "Nome invï¿½lido!\n";
		}
		if (txfCPF.getText() == null || txfCPF.getText().length() == 0) {
			errorMessage += "Cpf invï¿½lido!\n";
		}
		if (txfRG.getText() == null || txfRG.getText().length() == 0) {
			errorMessage += "Rg invï¿½lido!\n";
		}
		if (txfTelefone.getText() == null || txfTelefone.getText().length() == 0) {
			errorMessage += "Telefone invï¿½lido!\n";
		}
		if (cbxEstados.getValue() == null) {
			errorMessage += "Estado invï¿½lido!\n";
		}
		if (txfCidade.getText() == null || txfCidade.getText().length() == 0) {
			errorMessage += "Cidade invï¿½lida!\n";
		}
		if (txfBairro.getText() == null || txfBairro.getText().length() == 0) {
			errorMessage += "Bairro invï¿½lido!\n";
		}
		if (txfRua.getText() == null || txfRua.getText().length() == 0) {
			errorMessage += "Rua invï¿½lida!\n";
		}
		if (txfCEP.getText() == null || txfCEP.getText().length() == 0) {
			errorMessage += "Cep invï¿½lido!\n";
		}
		if (sexo.getSelectedToggle() == null) {
			errorMessage += "Sexo invï¿½lido!\n";
		}
		LocalDate hoje = LocalDate.now();
		if (dtpNascimento.getValue() == null || dtpNascimento.getValue().isAfter(hoje)) {
			errorMessage += "Data de nascimento invï¿½lida!\n";
		}
		if(txfSenha.getText() == null || txfSenha.getText().length() == 0){
			errorMessage += "Senha inválida!\n";
		}
		if (errorMessage.length() == 0) {
			return true;
		} else {

			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Campos Invï¿½lidos");
			alert.setHeaderText("Por favor, corrija	os campos invï¿½lidos!");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
		}
	}

	protected void mostrarDetalhes(Medico m) {
		this.labelTitle.setText("Atualiza Medico");
		if (m != null) {
			txfNome.setText(m.getNome());
			txfCPF.setText(m.getCpf());
			txfRG.setText(m.getRg());
			txfTelefone.setText(m.getTelefone());
			txfCelular.setText(m.getCelular());
			txfRua.setText(m.getEndereco().getRua());
			txfBairro.setText(m.getEndereco().getBairro());
			txfCidade.setText(m.getEndereco().getCidade());
			txfComplemento.setText(m.getEndereco().getComplemento());
			txfCEP.setText(m.getEndereco().getCep());
			cbxEstados.setValue(m.getEndereco().getEstado());
			if (m.getSexo() == 'M') {
				sexo.selectToggle(rbMasculino);
			} else {
				sexo.selectToggle(rbFeminino);
			}
			dtpNascimento.setValue(m.getDataDeNascimento());
			txfSenha.setText(m.getSenha());
			txfCRM.setText(Integer.toString(m.getNumCRM()));
			txfConsultas.setText(Integer.toString(m.getConsultasPorDia()));
		} else {
			txfNome.setText("");
			txfCPF.setText("");
			txfRG.setText("");
			txfTelefone.setText("");
			txfCelular.setText("");
			txfRua.setText("");
			txfBairro.setText("");
			txfCidade.setText("");
			txfComplemento.setText("");
			txfCEP.setText("");
			cbxEstados.setValue("");
			txfSenha.setText("");
			txfCRM.setText("");
			txfConsultas.setText("");
		}btnSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evento) {
				atualizaMedico(m);
				fecharTela();
			}
		});
	}
	protected void atualizaMedico(Medico m) {
		Medico medico = new Medico();
		medico.setNome(txfNome.getText());
		medico.setCpf(txfCPF.getText());
		medico.setRg(txfRG.getText());
		medico.setCelular(txfCelular.getText());
		medico.setTelefone(txfTelefone.getText());
		if (sexo.getSelectedToggle().equals(rbMasculino)) {
			medico.setSexo('M');
		} else {
			medico.setSexo('F');
		}
		medico.setDataDeNascimento(dtpNascimento.getValue());
		medico.setEndereco(new Endereco(txfRua.getText(), txfCidade.getText(), txfBairro.getText(),
				cbxEstados.getValue(), txfCEP.getText(), txfComplemento.getText()));
		medico.setConsultasPorDia(txfConsultas.getAnchor());
		medico.setNumCRM(txfCRM.getAnchor());
		medico.setSenha(txfSenha.getText());
		try {
			fachada.removerMedico(m);
			try {
				fachada.cadastrarMedico(medico.getNome(), medico.getCpf(), medico.getRg(), medico.getTelefone(), medico.getCelular(), medico.getSexo(),
				medico.getEndereco(), medico.getDataDeNascimento(), medico.getNumCRM(), 
				medico.getConsultasPorDia(), medico.getSenha());
			} catch (PJCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PNEException e) {
			e.printStackTrace();
		}
	}


	@FXML
	private void sair() {
		Telas.getInstance().voltarTela();
		Telas.getInstance().fecharTelaDialogo();
	}

}
