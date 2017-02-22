package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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
	@FXML
	private Button bntSave;
	@FXML
	private Label labelTitle;
	@FXML
	private Label lC;
	
	private FachadaClinicaMedica fachada;
	private Telas t;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
				
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucesso");
				alert.setHeaderText(null);
				alert.setContentText("cadastrado realizado com sucesso");
				alert.showAndWait();
				sair();
			} catch (PJCException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Cadastro inválido!");
				alert.setContentText(e.getMessage());
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
			errorMessage += "Data de nascimento inválido!\n";
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

	protected void mostrarDetalhes(Recepcionista m) {
		this.labelTitle.setText("Atualiza Medico");
		if (m != null) {
			txfNome.setText(m.getNome());
			txfCpf.setText(m.getCpf());
			txfCpf.setDisable(true);
			txfRg.setText(m.getRg());
			txfTelefone.setText(m.getTelefone());
			txfCelular.setText(m.getCelular());
			txfRua.setText(m.getEndereco().getRua());
			txfBairro.setText(m.getEndereco().getBairro());
			txfCidade.setText(m.getEndereco().getCidade());
			txfComplemento.setText(m.getEndereco().getComplemento());
			txfCep.setText(m.getEndereco().getCep());
			cbxEstado.setValue(m.getEndereco().getEstado());
			if (m.getSexo() == 'M') {
				tgpSexo.selectToggle(rbtMasculino);
			} else {
				tgpSexo.selectToggle(rbtFeminino);
			}
			dtpNascimento.setValue(m.getDataDeNascimento());
			pswSenha.setText(m.getSenha());
		} else {
			txfNome.setText("");
			txfCpf.setText("");
			txfRg.setText("");
			txfTelefone.setText("");
			txfCelular.setText("");
			txfRua.setText("");
			txfBairro.setText("");
			txfCidade.setText("");
			txfComplemento.setText("");
			txfCep.setText("");
			cbxEstado.setValue("");
			pswSenha.setText("");
		}bntSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent evento) {
		        atualizaRecepcionista(m);
				sair();
			}
		});
	}
	protected void atualizaRecepcionista(Recepcionista m) {
		Recepcionista recep = new Recepcionista();
		recep.setNome(txfNome.getText());
		recep.setCpf(txfCpf.getText());
		recep.setRg(txfRg.getText());
		recep.setCelular(txfCelular.getText());
		recep.setTelefone(txfTelefone.getText());
		if (tgpSexo.getSelectedToggle().equals(rbtMasculino)) {
			recep.setSexo('M');
		} else {
			recep.setSexo('F');
		}
		recep.setDataDeNascimento(dtpNascimento.getValue());
		recep.setEndereco(new Endereco(txfRua.getText(), txfCidade.getText(), txfBairro.getText(),
				cbxEstado.getValue(), txfCep.getText(), txfComplemento.getText()));
		recep.setSenha(pswSenha.getText());
		
		try {
			fachada.alterarRecepcionista(recep.getCpf(), recep);
			
		} catch (PNEException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText("Cadastro inválido!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}
	
	protected void verDetalhes(Recepcionista recepcionista) {
		mostrarDetalhes(recepcionista);
		this.labelTitle.setText("Detalhe Paciente");
		txfNome.setDisable(true);
		txfCpf.setDisable(true);
		txfRg.setDisable(true);
		txfTelefone.setDisable(true);
		txfCelular.setDisable(true);
		txfRua.setDisable(true);
		txfBairro.setDisable(true);
		txfCidade.setDisable(true);
		txfComplemento.setDisable(true);
		txfCep.setDisable(true);
		cbxEstado.setDisable(true);
		rbtMasculino.setDisable(true);
		rbtFeminino.setDisable(true);
		dtpNascimento.setDisable(true);
		pswSenha.setDisable(true);
		pswConfirmarSenha.setVisible(false);
		bntSave.setVisible(false);
		lC.setVisible(false);
	}
}
