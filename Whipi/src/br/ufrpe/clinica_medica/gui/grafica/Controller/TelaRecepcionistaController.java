package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * controller da tela principal do recepcionista
 */
public class TelaRecepcionistaController implements Initializable{
	
	@FXML
	private TableColumn<Paciente, String> colunaPacienteNome;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteCPF;
	@FXML
	private TableView<Paciente> tabelaPaciente;
	private Telas t; 
	private FachadaClinicaMedica f;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		ArrayList<Paciente> pacientes = f.ListarPacientes();
		colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaPaciente.setItems(FXCollections.observableArrayList(pacientes));
		tabelaPaciente.getColumns().addAll(colunaPacienteNome, colunaPacienteCPF);
	}
	
	@FXML
	private void telaListarMedico(){
		
	}
	@FXML
	private void telaCadastroPaciente() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogCadastro")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
	}
	@FXML
	private void telaAtualizaPaciente(){
		
	}
	@FXML
	private void telaListarPaciente(){
		
	}
	@FXML
	private void telaRemovePaciente(){
		
	}
	@FXML
	private void telaAtualizarRecepcionista(){
		
	}
	@FXML
	private void telaCadastroConsulta(){
		
	}
	@FXML
	private void telaAtualizaConsulta(){
		
	}
	@FXML
	private void telaRemoveConsulta(){
		
	}
	@FXML
	private void telaListaConsulta(){
		
	}
	@FXML
	private void logoff() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Logoff");
		alert.setContentText("Deseja fazer logoff?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			t.voltarTela();
			t.abrirTela();
		} else {
		    alert.close();
		}
	}
	
	@FXML
	private void sair() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText("Sair");
		alert.setContentText("Deseja fechar o programa?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(0);
		} else {
		    alert.close();
		}
	}
}
