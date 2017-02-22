package br.ufrpe.clinica_medica.gui.grafica.Controller;


import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PJCException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.Estados;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.DiasDeAtendimento;
import br.ufrpe.clinica_medica.negocio.beans.Endereco;
import br.ufrpe.clinica_medica.negocio.beans.EspecialidadeMedico;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
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
    @FXML
    private GridPane gridEsp;
    @FXML
    private Button btnMaisEsp;
    @FXML
    private Button btnMenosEsp;
    
    ArrayList<ComboBox<EspecialidadeMedico>> esp;
    private int cont;

	private FachadaClinicaMedica fachada;
    private Telas t;
    
    public DialogMedicoController(){
    	esp = new ArrayList<>();
    	cont = 0;
    }
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = FachadaClinicaMedica.getInstance();
		t = Telas.getInstance();
		cbxEstados.getItems().addAll(FXCollections.observableArrayList(Estados.pegarEstados()));
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
			
			ArrayList<EspecialidadeMedico> especialidades = new ArrayList<>();
			for (ComboBox<EspecialidadeMedico> comboBox : esp) {
				especialidades.add(comboBox.getValue());
			}
			
			try{
				fachada.cadastrarMedico(m.getNome(), m.getCpf(), m.getRg(), m.getTelefone(), m.getCelular(), m.getSexo(),
						m.getEndereco(), m.getDataDeNascimento(), m.getNumCRM(), 
						m.getConsultasPorDia(), m.getSenha(), especialidades);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Sucesso!");
				alert.setHeaderText("Cadastro!");
				alert.setContentText("Médico cadastrado com sucesso!");
				alert.showAndWait();
				fecharTela();
				
			} catch (PJCException e){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro!");
				alert.setHeaderText("Cadastro inválido!");
				alert.setContentText(e.getMessage());
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
			errorMessage += "Nome inválido!\n";
		}
		if (txfCPF.getText() == null || txfCPF.getText().length() == 0) {
			errorMessage += "Cpf inválido!\n";
		}
		if (txfRG.getText() == null || txfRG.getText().length() == 0) {
			errorMessage += "Rg inválido!\n";
		}
		if (txfTelefone.getText() == null || txfTelefone.getText().length() == 0) {
			errorMessage += "Telefone inválido!\n";
		}
		if (cbxEstados.getValue() == null) {
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
		if (txfCEP.getText() == null || txfCEP.getText().length() == 0) {
			errorMessage += "Cep inválido!\n";
		}
		if (sexo.getSelectedToggle() == null) {
			errorMessage += "Sexo inválido!\n";
		}
		LocalDate hoje = LocalDate.now();
		if (dtpNascimento.getValue() == null || dtpNascimento.getValue().isAfter(hoje)) {
			errorMessage += "Data de nascimento inválida!\n";
		}
		if(txfSenha.getText() == null || txfSenha.getText().length() == 0){
			errorMessage += "Senha inválida!\n";
		}
		if(esp.isEmpty()){
			errorMessage += "Nenhuma especialidade selecionada!\n";
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

	protected void mostrarDetalhes(Medico m) {
		this.labelTitle.setText("Atualiza Medico");
		if (m != null) {
			txfNome.setText(m.getNome());
			txfCPF.setText(m.getCpf());
			txfCPF.setDisable(true);
			txfRG.setText(m.getRg());
			txfTelefone.setText(m.getTelefone());
			txfCelular.setText(m.getCelular());
			txfRua.setText(m.getEndereco().getRua());
			txfBairro.setText(m.getEndereco().getBairro());
			txfCidade.setText(m.getEndereco().getCidade());
			txfComplemento.setText(m.getEndereco().getComplemento());
			txfCEP.setText(m.getEndereco().getCep());
			cbxEstados.setValue(m.getEndereco().getEstado());
			ArrayList<EspecialidadeMedico> especialidades = m.getEspecialidade();
			mostrarEspecialidades(especialidades);
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
		ArrayList<EspecialidadeMedico> especialidades = new ArrayList<>();
		for (ComboBox<EspecialidadeMedico> comboBox : esp) {
			especialidades.add(comboBox.getValue());
		}
		
		medico.setEspecialidade(especialidades);
		try {
			fachada.alterarMedico(medico.getCpf(), medico);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso!");
			alert.setHeaderText("Cadastro!");
			alert.setContentText("Médico atualizado com sucesso!");
			alert.showAndWait();
			
		} catch (PNEException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText("Cadastro inválido!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
	}

	protected void verDetalhes(Medico m) {
		mostrarDetalhes(m);
		this.labelTitle.setText("Detalhe Medico");
		txfNome.setDisable(true);
		txfCPF.setDisable(true);
		txfRG.setDisable(true);
		txfTelefone.setDisable(true);
		txfCelular.setDisable(true);
		txfRua.setDisable(true);
		txfBairro.setDisable(true);
		txfCidade.setDisable(true);
		txfComplemento.setDisable(true);
		txfCEP.setDisable(true);
		cbxEstados.setDisable(true);
		rbMasculino.setDisable(true);
		rbFeminino.setDisable(true);
		dtpNascimento.setDisable(true);
		btnSave.setVisible(false);
		txfConsultas.setDisable(true);
		txfCRM.setDisable(true);
		txfSenha.setDisable(true);
		btnMaisEsp.setVisible(false);
		btnMenosEsp.setVisible(false);
		gridEsp.setDisable(true);
	}

	@FXML
	private void sair() {
		Telas.getInstance().fecharTelaDialogo();
	}
	
	public void maisEspecialidades(){
		ComboBox<EspecialidadeMedico> c = new ComboBox<>(FXCollections.observableArrayList(fachada.listarTodosEspecialidade()));
		esp.add(c);
		if(cont >= 6){
			gridEsp.addRow(cont, esp.get(cont));
			cont++;
		}
		else {
			gridEsp.add(esp.get(cont), 0, cont);
			cont++;
		}
		
	}
	
	public void menosEspecialidades(){
		if(!gridEsp.getChildren().isEmpty()){
			cont--;
			gridEsp.getChildren().remove(esp.get(cont));
			
			esp.remove(cont);
			
		}
	}
	
	public void mostrarEspecialidades(ArrayList<EspecialidadeMedico> especialidades){
		for (EspecialidadeMedico especialidadeMedico : especialidades) {
			ComboBox<EspecialidadeMedico> c = new ComboBox<>(FXCollections.observableArrayList(fachada.listarTodosEspecialidade()));
			c.setValue(especialidadeMedico);
			esp.add(c);
		}
		
		int i = 0;
		for (ComboBox<EspecialidadeMedico> c : esp) {
			gridEsp.add(c, 0, i);
			i++;
		}
		cont = i;
	}
}
