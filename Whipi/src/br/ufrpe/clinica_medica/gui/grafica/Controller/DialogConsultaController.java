package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DialogConsultaController implements Initializable {

	@FXML
	private Button btnAnterior;
	@FXML
	private Button btnProximo;
	@FXML
	private Button btnSave;
	@FXML
	private TableView<Paciente> tabelaPaciente;
	@FXML
	private TableView<Medico> tabelaMedico;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteNome;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteCPF;
	@FXML
	private TableColumn<Medico, String> colunaMedicoNome;
	@FXML
	private TableColumn<Medico, String> colunaMedicoCRM;
	@FXML
	private TextField txfPesquisaPaciente;
	@FXML
	private TextField txfPesquisaMedico;
	@FXML
	private TextField txfHorario;
	@FXML
	private Label labelPaciente;
	@FXML
	private Label labelMedico;
	@FXML
	private DatePicker dtpConsulta;
	
	private Paciente pacienteAtual;
	
	private Medico medicoAtual;
	
	private FachadaClinicaMedica fachada;
	
	private Telas t;
	
	@FXML
	private void pesquisaPaciente() {
		preencherTableViewPaciente(this.txfPesquisaPaciente.getText());
	}
	
	@FXML
	private void verificaSave() {
		if (dtpConsulta.getValue() != null && txfHorario.getText().length() != 0) {
			btnSave.setDisable(false);
		} else {
			btnSave.setDisable(true);
		}
	}
	
	public void preencherTableViewPaciente(String inicial) {
		ArrayList<Paciente> pacientes = fachada.listarPacientes(inicial);
		colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaPaciente.setItems(FXCollections.observableArrayList(pacientes));
	}
	
	@FXML
	private void pesquisaMedico() {
		preencherTableViewMedico(this.txfPesquisaMedico.getText());
	}
	
	public void preencherTableViewMedico(String inicial) {
		ArrayList<Medico> medicos = fachada.listarMedicos(inicial);
		colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("crm"));
		tabelaMedico.setItems(FXCollections.observableArrayList(medicos));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = FachadaClinicaMedica.getInstance();
		t = Telas.getInstance();
	}
	
	@FXML
	private void nomePacienteClicado() {
		Paciente clicado = tabelaPaciente.getSelectionModel().getSelectedItem();
		if (clicado != null) {
			pacienteAtual = clicado;
			btnProximo.setDisable(false);
			btnProximo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evento) {
					tabelaPaciente.setVisible(false);
					tabelaPaciente.setDisable(true);
					btnAnterior.setDisable(false);
					tabelaMedico.setVisible(true);
					tabelaMedico.setDisable(false);
					labelPaciente.setVisible(true);
					labelPaciente.setText(pacienteAtual.getNome());
					txfPesquisaPaciente.setVisible(false);
					txfPesquisaPaciente.setDisable(true);
					txfPesquisaMedico.setVisible(true);
					txfPesquisaMedico.setDisable(false);
				}
			});
		}
	}
	
	@FXML
	private void nomeMedicoClicado() {
		Medico clicado = tabelaMedico.getSelectionModel().getSelectedItem();
		if (clicado != null) {
			medicoAtual = clicado;
			btnProximo.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent evento) {
					tabelaMedico.setDisable(true);
					labelMedico.setVisible(true);
					labelMedico.setText(medicoAtual.getNome());
					txfPesquisaMedico.setDisable(true);
					dtpConsulta.setVisible(true);
					dtpConsulta.setDisable(false);
					txfHorario.setVisible(true);
					txfHorario.setDisable(false);
					btnProximo.setDisable(true);
				}
			});
		}
	}
	
	@FXML
	private void fecharTela() {
		
	}

}
