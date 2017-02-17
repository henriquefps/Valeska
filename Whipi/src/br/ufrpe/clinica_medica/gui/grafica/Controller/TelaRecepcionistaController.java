package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * controller da tela principal do recepcionista
 */
public class TelaRecepcionistaController implements Initializable {

	// @FXML private Button btnCadastroConsulta;
	// @FXML private Button btnListarMedico;
	// @FXML private Button btnListarPaciente;
	// @FXML private Button btnAtualizaPaciente;
	// @FXML private Button btnRemoveConsulta;
	// @FXML private Button btnRemovePaciente;
	// @FXML private Button btnListarConsulta;
	// @FXML private Button btnCadastroPaciente;
	// @FXML private Button btnAtualizaConsulta;
	// @FXML private Button btnAtualizaRecepcionista;
	//
	// @FXML private Button botaoLogoff;
	//
	// @FXML private Button botaoSair;

	@FXML
	private TableColumn<Paciente, String> colunaPacienteNome;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteCPF;
	@FXML
	private TableView<Paciente> tabelaPaciente;

	@FXML
	private TableView<Consulta> tabelaConsultas;
	@FXML
	private TableColumn<Consulta, Medico> colunaConsultaMedico;
	@FXML
	private TableColumn<Consulta, Paciente> colunaConsultaPaciente;
	@FXML
	private TableColumn<Consulta, LocalDateTime> colunaConsultaHorario;
	@FXML
	private TableColumn<Consulta, Boolean> colunaConsultaRealizada;

	private Telas t;
	private FachadaClinicaMedica f;
	private Paciente pacienteAtual;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		preencherTableView();
	}

	@FXML
	private void telaListarMedico() {

	}

	@FXML
	private void telaCadastroPaciente() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogPaciente")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
		preencherTableView();
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
		preencherTableView();
	}

	@FXML
	private void telaRemovePaciente() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmacao");
		alert.setHeaderText("Remover Paciente");
		alert.setContentText("Deseja remover o paciente " + pacienteAtual.getNome() + "?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				f.removerPaciente(pacienteAtual);
			} catch (PNEException e) {
				e.printStackTrace();
			}
			preencherTableView();
			alert.close();
		} else {
			alert.close();
		}
	}

	@FXML
	private void telaAtualizaRecepcionista() {

	}

	@FXML
	private void telaCadastroConsulta() {

	}

	@FXML
	private void telaAtualizaConsulta() {

	}

	@FXML
	private void telaRemoveConsulta() {

	}

	@FXML
	private void telaListarConsulta() {

	}

	@FXML
	private void logoff() {
		// Alert alert = new Alert(AlertType.CONFIRMATION);
		// alert.setTitle("Confirmacao");
		// alert.setHeaderText("Logoff");
		// alert.setContentText("Deseja fazer logoff?");
		//
		// Optional<ButtonType> result = alert.showAndWait();
		// if (result.get() == ButtonType.OK){
		// t.voltarTela();
		// t.abrirTela();
		// } else {
		// alert.close();
		// }
		t.logoff();
	}

	@FXML
	private void sair() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirma��o");
		alert.setHeaderText("Sair");
		alert.setContentText("Deseja fechar o programa?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			System.exit(0);
		} else {
			alert.close();
		}
	}

	public void preencherTableView() {
		ArrayList<Paciente> pacientes = f.ListarPacientes();
		colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaPaciente.setItems(FXCollections.observableArrayList(pacientes));
	}

	@FXML
	private void nomePacienteClicado() {
		Paciente clicado = tabelaPaciente.getSelectionModel().getSelectedItem();
		if (clicado != null) {
			pacienteAtual = clicado;
		}
	}

	public void preencherTableViewConsultas() {
		ArrayList<Consulta> consultas = f.listarConsultas();
		colunaConsultaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		colunaConsultaMedico.setCellValueFactory(new PropertyValueFactory<>("medico"));
		colunaConsultaPaciente.setCellValueFactory(new PropertyValueFactory<>("paciente"));
		colunaConsultaRealizada.setCellValueFactory(new PropertyValueFactory<>("realizada"));
		tabelaConsultas.setItems(FXCollections.observableArrayList(consultas));
	}
}
