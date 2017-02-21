package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

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
	private TableView<LocalTime> tabelaConsulta;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteNome;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteCPF;
	@FXML
	private TableColumn<Medico, String> colunaMedicoNome;
	@FXML
	private TableColumn<Medico, String> colunaMedicoCRM;
	@FXML
	private TableColumn<LocalTime, String> colunaConsultaHorario;
	@FXML
	private TextField txfPesquisaPaciente;
	@FXML
	private TextField txfPesquisaMedico;
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
	private int click;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = FachadaClinicaMedica.getInstance();
		t = Telas.getInstance();
		preencherTableViewPaciente(fachada.listarPacientes());
		click = 1;
		tabelaPaciente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Paciente>(){
			@Override
			public void changed(ObservableValue<? extends Paciente> arg0, Paciente arg1, Paciente arg2) {
				labelPaciente.setVisible(true);
				if(arg2 != null)
					labelPaciente.setText(arg2.getNome());
				pacienteAtual = arg2;
				btnProximo.setDisable(false);
			}
			
		});
		
		tabelaMedico.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Medico>(){
			@Override
			public void changed(ObservableValue<? extends Medico> arg0, Medico arg1, Medico arg2) {
				labelMedico.setVisible(true);
				if(arg2 != null)
					labelMedico.setText(arg2.getNome());
				medicoAtual = arg2;
				btnProximo.setDisable(false);
			}
		});
		
		tabelaConsulta.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<LocalTime>(){
			@Override
			public void changed(ObservableValue<? extends LocalTime> arg0, LocalTime arg1, LocalTime arg2) {
				if (arg2 != null) {
					btnSave.setDisable(false);
				} else {
					btnSave.setDisable(true);
				}		
			}
			
		});
		
		/*dtpConsulta.valueProperty().addListener(new ChangeListener<LocalDate>(){
			@Override
			public void changed(ObservableValue<? extends LocalDate> arg0, LocalDate arg1, LocalDate arg2) {
				if (arg2 != null) {
					btnSave.setDisable(false);
				} else {
					btnSave.setDisable(true);
				}		
			}
			
		});*/
		
		
		txfPesquisaPaciente.lengthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if(arg2.intValue() == 0)
					preencherTableViewPaciente(fachada.listarPacientes());
				else{
					ArrayList<Paciente> pacientes = fachada.pesquisarPacienteNome(txfPesquisaPaciente.getText());
					preencherTableViewPaciente(pacientes);
				}
					
			}
			
		});
		
		txfPesquisaMedico.lengthProperty().addListener(new ChangeListener<Number>(){
			@Override
			public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
				if(arg2.intValue() == 0)
					preencherTableViewMedico(fachada.listarMedicos());
				else{
					ArrayList<Medico> medicos = fachada.pesquisarMedicoNome(txfPesquisaMedico.getText());
					preencherTableViewMedico(medicos);
				}
					
			}
			
		});
		
		
	}
	
	public void handleProximo(){
		click++;
		escolherTabela();
		btnProximo.setDisable(true);
		btnAnterior.setDisable(false);

	}
	
	public void handleAnterior(){
		if(click == 3)
			dtpConsulta.setValue(null);
		click--;
		escolherTabela();
	}
	
	private void escolherTabela(){
		switch(click){
		case 1:
			preencherTableViewPaciente(fachada.listarPacientes());
			labelMedico.setText("");
			dtpConsulta.setVisible(false);
			
			tabelaPaciente.setVisible(true);
			tabelaMedico.setVisible(false);
			tabelaConsulta.setVisible(false);
			
			btnAnterior.setDisable(true);
			txfPesquisaPaciente.setVisible(true);
			txfPesquisaMedico.setVisible(false);
			break;
		case 2:
			preencherTableViewMedico(fachada.listarMedicos());
			tabelaMedico.getSelectionModel().clearSelection();
			dtpConsulta.setVisible(false);
			
			tabelaPaciente.setVisible(false);
			tabelaMedico.setVisible(true);
			tabelaConsulta.setVisible(false);
			
			txfPesquisaMedico.setVisible(true);
			txfPesquisaPaciente.setVisible(false);
			break;
		case 3:
			tabelaConsulta.getSelectionModel().clearSelection();
			dtpConsulta.setVisible(true);
			
			tabelaMedico.setVisible(false);
			tabelaConsulta.setVisible(true);
			tabelaPaciente.setVisible(false);
			
			btnProximo.setDisable(true);
			break;
		}
	}
	
	@FXML
	private void pesquisaPaciente(KeyEvent e) {
		
	}
	
	public void preencherTableViewPaciente(ArrayList<Paciente> pacientes) {
		colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaPaciente.setItems(FXCollections.observableArrayList(pacientes));
	}
	
	@FXML
	private void pesquisaMedico() {
	}
	
	public void preencherTableViewMedico(ArrayList<Medico> medicos) {
		colunaMedicoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaMedicoCRM.setCellValueFactory(new PropertyValueFactory<>("crm"));
		tabelaMedico.setItems(FXCollections.observableArrayList(medicos));
	}
	
	@FXML
	private void fecharTela() {
		t.fecharTelaDialogo();
	}
	
	@FXML
	private void salvar(){
		
	}
	
	

}
