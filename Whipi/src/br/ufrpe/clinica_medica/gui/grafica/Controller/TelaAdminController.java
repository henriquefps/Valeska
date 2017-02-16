package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.beans.property.StringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaAdminController implements Initializable {

	@FXML
	private Button cadastroMedico;
	@FXML
	private Button atualizaMedico;
	@FXML
	private Button removeMedico;
	@FXML
	private Button detalheMedico;
	@FXML
	private Button cadastroRecepcionista;
	@FXML
	private Button atualizaRecepcionista;
	@FXML
	private Button removeRecepcionista;
	@FXML
	private Button detalheRecepcionista;
	@FXML
	private Button cadastroPaciente;
	@FXML
	private Button atualizaPaciente;
	@FXML
	private Button removePaciente;
	@FXML
	private Button detalhePaciente;
	@FXML
	private AnchorPane anchorAdm;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteNome;
	@FXML
	private TableColumn<Paciente, String> colunaPacienteCPF;
	@FXML
	private TableView<Paciente> tabelaPaciente;
	
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
	private void telaCadastroMedico() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogMedico")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
	}

	@FXML
	private void telaAtualizaMedico() {

	}

	@FXML
	private void telaRemoveMedico() {

	}
	
	@FXML
	private void telaDetalheMedico() {
		
	}

	@FXML
	private void telaCadastroRecepcionista() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogRecepcionista")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
	}

	@FXML
	private void telaAtualizaRecepcionista() {
		TextInputDialog dialog = new TextInputDialog("CPF");
		dialog.setTitle("Atualizar Recepcionista");
		dialog.setHeaderText("Atualizar Recepcionista");
		dialog.setContentText("Digite seu CPF:");

		// Traditional way to get the response value.
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()){
		    System.out.println("Your name: " + result.get());
		}

	}

	@FXML
	private void telaRemoveRecepcionista() {

	}
	
	@FXML
	private void telaDetalheRecepcionista() {
		
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
		t.setScene(new Scene((Parent) t.carregarFXML("DialogPaciente")));
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogPacienteController p = t.getF().getController();
		p.mostrarDetalhes(pacienteAtual);
		t.abrirTelaDialogo();
	}

	@FXML
	private void telaRemovePaciente() {

	}
	
	@FXML
	private void telaDetalhePaciente() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(pacienteAtual.getNome());
		alert.setContentText(pacienteAtual.toString());
	}
	
	@FXML
	private void logoff() {
		t.logoff();
	}
	
	@FXML
	private void sair() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirma��o");
		alert.setHeaderText("Sair");
		alert.setContentText("Deseja fechar o programa?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(0);
		} else {
		    alert.close();
		}
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
	
	public Paciente retornaPaciente() {
		return this.pacienteAtual;
	}
	
	public void preencherTableView(){	
		ArrayList<Paciente> pacientes = f.ListarPacientes();
		colunaPacienteNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaPacienteCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));
		tabelaPaciente.setItems(FXCollections.observableArrayList(pacientes));
	}
}
