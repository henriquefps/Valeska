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
			errorMessage += "Nome inv�lido!\n";
		}
		if (txfCpf.getText() == null || txfCpf.getText().length() == 0) {
			errorMessage += "Cpf inv�lido!\n";
		}
		if (txfRg.getText() == null || txfRg.getText().length() == 0) {
			errorMessage += "Rg inv�lido!\n";
		}
		if (txfTelefone.getText() == null || txfTelefone.getText().length() == 0) {
			errorMessage += "Telefone inv�lido!\n";
		}
		if (cbxEstado.getValue() == null) {
			errorMessage += "Estado inv�lido!\n";
		}
		if (txfCidade.getText() == null || txfCidade.getText().length() == 0) {
			errorMessage += "Cidade inv�lida!\n";
		}
		if (txfBairro.getText() == null || txfBairro.getText().length() == 0) {
			errorMessage += "Bairro inv�lido!\n";
		} // ajeitar!!
		if (txfRua.getText() == null || txfRua.getText().length() == 0) {
			errorMessage += "Rua inv�lida!\n";
		}
		if (txfCep.getText() == null || txfCep.getText().length() == 0) {
			errorMessage += "Cep inv�lido!\n";
		}
		if (rbtMasculino == null || rbtFeminino == null) {
			errorMessage += "Sexo inv�lido!\n";
		}
		LocalDate hoje = LocalDate.now();
		if (dtpNascimento.getValue() == null || dtpNascimento.getValue().isAfter(hoje)) {
			errorMessage += "Data de nascimento inv�lida!\n";
		}
		if (pswSenha.getText() == null || pswSenha.getText().length() == 0) {
			errorMessage += "Senha inv�lida!\n";
		}
		if (pswConfirmarSenha.getText() == null || pswConfirmarSenha.getText().length() == 0) {
			errorMessage += "Confirma��o de Senha inv�lida!\n";
		}
		if(!pswSenha.getText().equals(pswConfirmarSenha.getText())){
			errorMessage += "Senhas n�o coincidem!\n";
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

	@FXML
	private void sair() {
		t.fecharTelaDialogo();
	}

	protected void mostrarDetalhes(Recepcionista m) {
		this.labelTitle.setText("Atualiza Recepcionista");
		if (m != null) {
			txfNome.setText(m.getNome());
			txfCpf.setText(m.getCpf());
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
			fachada.removerRecepcionista(m);
			try {
				fachada.cadastrarRecepcionista(recep.getNome(), recep.getCpf(), recep.getRg(), recep.getTelefone(),
						recep.getCelular(), recep.getSexo(), recep.getEndereco(), 
						recep.getDataDeNascimento(), recep.getSenha());
			} catch (PJCException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (PNEException e) {
			e.printStackTrace();
		}
	}
}
