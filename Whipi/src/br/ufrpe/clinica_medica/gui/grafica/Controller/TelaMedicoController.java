package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.NCDException;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class TelaMedicoController implements Initializable {
	@FXML
	private TableView<Consulta> tabelaConsultas;
	@FXML
	private TableColumn<Consulta, String> colunaNomeDoMedico;
	@FXML
	private TableColumn<Consulta, String> colunaNomeDoPaciente;
	@FXML
	private TableColumn<Consulta, LocalDateTime> colunaHorario;
	@FXML
	private TableColumn<Consulta, Boolean> colunaRealizada;
	@FXML
	private Label lblLogado;

	private Medico logado;
	private Consulta consultaAtual;
	private Telas t;
	private FachadaClinicaMedica f;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		lblLogado.setText(t.getLogada().getNome());
		preencherTableViewConsultas();
		
	}

	@FXML
	public void alterarDiasDeTrabalho() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogDiasDeTrabalho")), t.getLogada());
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogDiasDeTrabalhoController p = t.getF().getController();
		p.mostrarDetalhes();
		t.abrirTelaDialogo();
	}

	@FXML
	public void realizarConsulta() {
		if (consultaAtual != null) {
			consultaAtual.setRealizada(true);
		}
		
		preencherTableViewConsultas();
	}

	@FXML
	public void editarPerfil() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogMedico")), t.getLogada());
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogMedicoController p = t.getF().getController();
		p.mostrarDetalhes((Medico)t.getLogada());
		t.abrirTelaDialogo();
	}

	@FXML
	public void cancelarConsultasDeUmDia() {
		if (consultaAtual != null) {
			ArrayList<Consulta> aux = f.listarConsultas();
			aux.remove(consultaAtual);
		}
		preencherTableViewConsultas();
	}
	
	
	@FXML
	public void sair(){
		t.sairDoSistema();
		
	}
	
	public void preencherTableViewConsultas(){
		ArrayList<Consulta> consultas = null;
		try {
			consultas = f.consultasDoDia(logado);
			colunaNomeDoMedico.setCellValueFactory(new Callback<CellDataFeatures<Consulta, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Consulta, String> c) {
					
					return new SimpleStringProperty(c.getValue().getMedico().getNome());
				}
			});
			
			colunaNomeDoPaciente.setCellValueFactory(new Callback<CellDataFeatures<Consulta, String>, ObservableValue<String>>() {

				@Override
				public ObservableValue<String> call(CellDataFeatures<Consulta, String> c) {
					
					return new SimpleStringProperty(c.getValue().getPaciente().getNome());
				}
				
			});
			colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
			colunaRealizada.setCellValueFactory(new PropertyValueFactory<>("realizada"));
			tabelaConsultas.setItems(FXCollections.observableArrayList(consultas));
		} catch (NCDException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informação");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		
	}
	
	@FXML
	public void logoff(){
		t.logoff();
	}
	
	@FXML private void consultaClicado() {
		Consulta clicado = tabelaConsultas.getSelectionModel().getSelectedItem();
		if (clicado != null) {
			consultaAtual = clicado;
		}
	}
}
