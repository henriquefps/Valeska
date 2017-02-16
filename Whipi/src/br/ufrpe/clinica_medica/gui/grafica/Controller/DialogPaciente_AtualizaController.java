package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DialogPaciente_AtualizaController implements Initializable {

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
	public void initialize(URL arg0, ResourceBundle arg1) {
		t = Telas.getInstance();
		fachada = FachadaClinicaMedica.getInstance();
		ObservableList<String> estados = FXCollections.observableArrayList(Estados.pegarEstados());
		cbxEstado.getItems().addAll(estados);
		TelaAdminController a = new TelaAdminController();
		pegarPaciente(a.retornaPaciente());
	}
	
	public void pegarPaciente (Paciente a) {
		this.txfNome.setText(a.getNome());
		this.txfCpf.setText(a.getCpf());
		this.txfRg.setText(a.getRg());
		this.txfTelefone.setText(a.getTelefone());
		this.txfCelular.setText(a.getCelular());
		this.cbxEstado.setPromptText(a.getEndereco().getEstado());
		this.txfCidade.setText(a.getEndereco().getCidade());
		this.txfBairro.setText(a.getEndereco().getBairro());
		this.txfRua.setText(a.getEndereco().getRua());
		this.txfCep.setText(a.getEndereco().getCep());
		this.txfComplemento.setText(a.getEndereco().getComplemento());
		if (a.getSexo() == 'M') {
			this.rbtMasculino.arm();
		} else {
			this.rbtFeminino.arm();
		}
		this.dtpNascimento.setPromptText(a.getDataDeNascimento().toString());
	}
	
}
