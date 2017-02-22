package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.EMEException;
import br.ufrpe.clinica_medica.exceptions.EMNEException;
import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.EspecialidadeMedico;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaAdminController implements Initializable {

	@FXML
	private Button atualizaMedico;
	@FXML
	private Button removeMedico;
	@FXML
	private Button detalheMedico;
	@FXML
	
	private Button atualizaRecepcionista;
	@FXML
	private Button removeRecepcionista;
	@FXML
	private Button detalheRecepcionista;
	@FXML
	
	private Button atualizaPaciente;
	@FXML
	private Button removePaciente;
	@FXML
	private Button detalhePaciente;
	
	@FXML
	private Button atualizaEspecialidade;
	@FXML
	private Button removeEspecialidade;
	
	@FXML	
	private TableColumn<Paciente, String> colunaPacienteNome;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteCPF;
	@FXML
	private TableView<Paciente> tabelaPaciente;

	@FXML
	private TableColumn<Medico, String> colunaNomeDoMedico;
	@FXML
	private TableView<Medico> tabelaMedico;
	@FXML
	private TableColumn<Medico, String> colunaCpfDoMedico;
	
	@FXML
	private TableColumn<Recepcionista, String> colunaNomeDoRecepcionista;
	@FXML
	private TableView<Recepcionista> tabelaRecepcionista;
	@FXML
	private TableColumn<Recepcionista, String> colunaCpfDoRecepcionista;
	
	@FXML
	private TableView<EspecialidadeMedico> tabelaEspecialidades;
	@FXML
	private TableColumn<EspecialidadeMedico, String> colunaEspecialidade;
	
	@FXML
	private Label lblLogado;
	
	private Telas t;
	private FachadaClinicaMedica f;

	private Paciente pacienteAtual;
	private Medico medicoAtual;
	private Recepcionista recepcionistaAtual;
	private EspecialidadeMedico especialidadeAtual;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		lblLogado.setText(t.getLogada().getNome());
		preencherTableViewPaciente();
		preencherTableViewMedico();
		preencherTableViewRecepcionista();
		preencherTableViewEspecialidade();
		
		
		tabelaEspecialidades.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EspecialidadeMedico>(){

			@Override
			public void changed(ObservableValue<? extends EspecialidadeMedico> observable, EspecialidadeMedico oldValue,
					EspecialidadeMedico newValue) {
				especialidadeAtual = newValue;
				atualizaEspecialidade.setDisable(false);
				removeEspecialidade.setDisable(false);
			}

			
		});
		
	}

	@FXML
	private void telaCadastroMedico() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogMedico")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
		preencherTableViewMedico();
		atualizaMedico.setDisable(true);
		removeMedico.setDisable(true);
		detalheMedico.setDisable(true);
	}

	@FXML
	private void telaAtualizaMedico() {
		if (medicoAtual == null) {
			return;
		}
		t.setScene(new Scene((Parent) t.carregarFXML("DialogMedico")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogMedicoController p = t.getF().getController();
		p.mostrarDetalhes(medicoAtual);
		t.abrirTelaDialogo();
		preencherTableViewMedico();
		this.medicoAtual = null;
		atualizaMedico.setDisable(true);
		removeMedico.setDisable(true);
		detalheMedico.setDisable(true);
	}

	@FXML
	private void telaRemoveMedico() {
		if (medicoAtual == null) {
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remover médico");
		alert.setHeaderText("Deseja remover o médico " + medicoAtual.getNome() + "?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				f.removerMedico(medicoAtual);
				this.medicoAtual = null;
				atualizaMedico.setDisable(true);
				removeMedico.setDisable(true);
				detalheMedico.setDisable(true);
				preencherTableViewMedico();
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Sucesso");
				a.setHeaderText("Remoção realizada com sucesso");
				a.setContentText(null);
				
			} catch (PNEException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Erro");
				a.setHeaderText("Remoção inválida");
				a.setContentText(e.getMessage());
			}
		} else {
			return;
		}
	}

	@FXML
	private void telaDetalheMedico() {
		if (medicoAtual == null) {
			return;
		}
		t.setScene(new Scene((Parent) t.carregarFXML("DialogMedico")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogMedicoController p = t.getF().getController();
		p.verDetalhes(medicoAtual);
		t.abrirTelaDialogo();
	}

	@FXML
	private void telaCadastroRecepcionista() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogRecepcionista")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
		preencherTableViewRecepcionista();
		atualizaRecepcionista.setDisable(true);
		removeRecepcionista.setDisable(true);
		detalheRecepcionista.setDisable(true);
	}

	@FXML
	private void telaAtualizaRecepcionista() {
		if (recepcionistaAtual == null) {
			return;
		}
		t.setScene(new Scene((Parent) t.carregarFXML("DialogRecepcionista")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogRecepcionistaController p = t.getF().getController();
		p.mostrarDetalhes(recepcionistaAtual);
		t.abrirTelaDialogo();
		preencherTableViewRecepcionista();
		this.recepcionistaAtual = null;
		atualizaRecepcionista.setDisable(true);
		removeRecepcionista.setDisable(true);
		detalheRecepcionista.setDisable(true);

	}

	@FXML
	private void telaRemoveRecepcionista() {
		if (recepcionistaAtual == null) {
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remover recepcionista");
		alert.setHeaderText("Deseja remover o recepcionista " + recepcionistaAtual.getNome() + "?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				f.removerRecepcionista(recepcionistaAtual);
				this.recepcionistaAtual = null;
				atualizaRecepcionista.setDisable(true);
				removeRecepcionista.setDisable(true);
				detalheRecepcionista.setDisable(true);
				preencherTableViewRecepcionista();
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Sucesso");
				a.setHeaderText("Remoção realizada com sucesso");
				a.setContentText(null);
				
			} catch (PNEException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Erro");
				a.setHeaderText("Remoção inválida");
				a.setContentText(e.getMessage());
			}
		} else {
			return;
		}
	}

	@FXML
	private void telaDetalheRecepcionista() {
		if (recepcionistaAtual == null) {
			return;
		}
		t.setScene(new Scene((Parent) t.carregarFXML("DialogRecepcionista")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogRecepcionistaController p = t.getF().getController();
		p.verDetalhes(recepcionistaAtual);
		t.abrirTelaDialogo();
	}

	@FXML
	private void telaCadastroPaciente() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogPaciente")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
		preencherTableViewPaciente();
		atualizaPaciente.setDisable(true);
		removePaciente.setDisable(true);
		detalhePaciente.setDisable(true);
	}

	@FXML
	private void telaAtualizaPaciente() {
		if (pacienteAtual == null) {
			return;
		}
		t.setScene(new Scene((Parent) t.carregarFXML("DialogPaciente")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogPacienteController p = t.getF().getController();
		p.mostrarDetalhes(pacienteAtual);
		t.abrirTelaDialogo();
		preencherTableViewPaciente();
		this.pacienteAtual = null;
		atualizaPaciente.setDisable(true);
		removePaciente.setDisable(true);
		detalhePaciente.setDisable(true);
	}

	@FXML
	private void telaRemovePaciente() {
		if (pacienteAtual == null) {
			return;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remover paciente");
		alert.setHeaderText("Deseja remover o paciente " + pacienteAtual.getNome() + "?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				f.removerPaciente(pacienteAtual);
				atualizaPaciente.setDisable(true);
				removePaciente.setDisable(true);
				detalhePaciente.setDisable(true);
				this.pacienteAtual = null;
				preencherTableViewPaciente();
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Sucesso");
				a.setHeaderText("Remoção realizada com sucesso");
				a.setContentText(null);
				
			} catch (PNEException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Erro");
				a.setHeaderText("Remoção inválida");
				a.setContentText(e.getMessage());
			}
		} else {
			return;
		}
	}

	@FXML
	private void telaDetalhePaciente() {
		if (pacienteAtual == null) {
			return;
		}
		t.setScene(new Scene((Parent) t.carregarFXML("DialogPaciente")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogPacienteController p = t.getF().getController();
		p.verDetalhes(pacienteAtual);
		t.abrirTelaDialogo();
	}

	@FXML
	private void logoff() {
		t.logoff();
	}

	@FXML
	private void sair() {
		t.sairDoSistema();

	}

	@FXML
	private void nomePacienteClicado() {
		Paciente clicado = tabelaPaciente.getSelectionModel().getSelectedItem();
		if (clicado != null) {
			atualizaPaciente.setDisable(false);
			removePaciente.setDisable(false);
			detalhePaciente.setDisable(false);
			pacienteAtual = clicado;
		}
	}
	@FXML private void nomeMedicoClicado() {
		Medico clicado = tabelaMedico.getSelectionModel().getSelectedItem();
		if (clicado != null) {
			medicoAtual = clicado;
			atualizaMedico.setDisable(false);
			removeMedico.setDisable(false);
			detalheMedico.setDisable(false);
			medicoAtual = clicado;
		}
	}
	
	@FXML private void nomeRecepcionistaClicado(){
		Recepcionista clicado = tabelaRecepcionista.getSelectionModel().getSelectedItem();
		if(clicado != null){
			recepcionistaAtual = clicado;
			atualizaRecepcionista.setDisable(false);
			removeRecepcionista.setDisable(false);
			detalheRecepcionista.setDisable(false);
			recepcionistaAtual =  clicado;
		}
	}

	public Medico retornaMedico(){
		return this.medicoAtual;
	}
	public Paciente retornaPaciente() {
		return this.pacienteAtual;
	}
	public Recepcionista retornaRecepcionista(){
		return this.recepcionistaAtual;
		
	}
	
	@FXML
	public void clicadoForaDaTabela() {
		atualizaMedico.setDisable(true);
		removeMedico.setDisable(true);
		detalheMedico.setDisable(true);
		atualizaRecepcionista.setDisable(true);
		removeRecepcionista.setDisable(true);
		detalheRecepcionista.setDisable(true);
		atualizaPaciente.setDisable(true);
		removePaciente.setDisable(true);
		detalhePaciente.setDisable(true);
		atualizaEspecialidade.setDisable(true);
		removeEspecialidade.setDisable(true);
		this.medicoAtual = null;
		this.pacienteAtual = null;
		this.recepcionistaAtual = null;
		this.especialidadeAtual = null;
	}

	public void preencherTableViewPaciente() {
		ArrayList<Paciente> pacientes = f.listarPacientes();
		colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaPaciente.setItems(FXCollections.observableArrayList(pacientes));
		tabelaPaciente.getColumns().get(0).setVisible(false);
		tabelaPaciente.getColumns().get(0).setVisible(true);
	}
	
	public void preencherTableViewMedico() {
		ArrayList<Medico> medico = f.listarMedicos();
		colunaNomeDoMedico.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCpfDoMedico.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaMedico.setItems(FXCollections.observableArrayList(medico));
		tabelaMedico.getColumns().get(0).setVisible(false);
		tabelaMedico.getColumns().get(0).setVisible(true);
	}
	
	public void preencherTableViewRecepcionista(){
		ArrayList<Recepcionista> recepcionista = f.listarRecepcionistas();
		colunaNomeDoRecepcionista.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaCpfDoRecepcionista.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaRecepcionista.setItems(FXCollections.observableArrayList(recepcionista));
		tabelaRecepcionista.getColumns().get(0).setVisible(false);
		tabelaRecepcionista.getColumns().get(0).setVisible(true);
	}
	
	public void preencherTableViewEspecialidade(){
		ArrayList<EspecialidadeMedico> especialidade = f.listarTodosEspecialidade();
		colunaEspecialidade.setCellValueFactory(new PropertyValueFactory<>("nome"));
		tabelaEspecialidades.setItems(FXCollections.observableArrayList(especialidade));
		tabelaEspecialidades.getColumns().get(0).setVisible(false);
		tabelaEspecialidades.getColumns().get(0).setVisible(true);
	}
	
	public void telaCadastroEspecialidade(){
		TextInputDialog tid = new TextInputDialog();
		tid.setTitle("Cadastro Especialidade");
		tid.setHeaderText(null);
		tid.setContentText("Digite a especialidade:");
		Optional<String> result = tid.showAndWait();
		if(result.isPresent()){
			try {
				f.cadastrarEspecialidade(result.get());
				atualizaEspecialidade.setDisable(true);
				removeEspecialidade.setDisable(true);
				this.especialidadeAtual = null;
				preencherTableViewEspecialidade();
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Sucesso");
				a.setHeaderText("Cadastro realizado com sucesso");
				a.setContentText(null);
				a.showAndWait();
				
			} catch (EMEException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Cadastro inválido");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		}
	}
	
	public void telaAtualizaEspecialidade(){
		TextInputDialog tid = new TextInputDialog();
		tid.setTitle("Atualizar Especialidade");
		tid.setHeaderText(null);
		tid.setContentText("Digite a atualização:");
		Optional<String> result = tid.showAndWait();
		if(result.isPresent()){
			try {
				f.alterarEspecialidade(especialidadeAtual.getNome(), new EspecialidadeMedico(result.get()));
				atualizaEspecialidade.setDisable(true);
				removeEspecialidade.setDisable(true);
				this.especialidadeAtual = null;
				preencherTableViewEspecialidade();
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Sucesso");
				a.setHeaderText("Alteração realizada com sucesso");
				a.setContentText(null);
				a.showAndWait();
				
			} catch (EMNEException e) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Erro");
				alert.setHeaderText("Alteração inválida");
				alert.setContentText(e.getMessage());
				alert.showAndWait();
			}
		}
	}	
	
	public void telaRemoveEspecialidade(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Remover paciente");
		alert.setHeaderText("Deseja remover a especialidade " + especialidadeAtual.getNome() + "?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				f.removerEspecialidade(especialidadeAtual);
				atualizaEspecialidade.setDisable(true);
				removeEspecialidade.setDisable(true);
				this.especialidadeAtual = null;
				preencherTableViewEspecialidade();
				
				Alert a = new Alert(AlertType.INFORMATION);
				a.setTitle("Sucesso");
				a.setHeaderText("Remoção realizada com sucesso");
				a.setContentText(null);
				a.showAndWait();
				
			} catch (EMNEException e) {
				Alert a = new Alert(AlertType.ERROR);
				a.setTitle("Erro");
				a.setHeaderText("Remoção inválida");
				a.setContentText(e.getMessage());
				a.showAndWait();
			}
		} else {
			return;
		}
	}
	
	
}