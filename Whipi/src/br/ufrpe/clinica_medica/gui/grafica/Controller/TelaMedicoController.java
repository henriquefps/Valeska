package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.awt.Button;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.text.TabableView;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TelaMedicoController implements Initializable {
	@FXML
	private TableView<Consulta> tabelaConsultas;

	@FXML
	private TableColumn<Medico, String> colunaNomeDoMedico;

	@FXML
	private TableColumn<Paciente, String> colunaNomeDoPaciente;

	@FXML
	private TableColumn<Consulta, LocalDateTime> colunaHorario;

	@FXML
	private TableColumn<Consulta, Boolean> colunaRealizada;

	private Medico logado;
	// @FXML private Button botaoAlterarDiasDeTrabalho;
	//
	// @FXML private Button botaoEditarPerfil;
	//
	// @FXML private Button botaoRealizarConsulta;
	//
	// @FXML private Button botaoCancelarDiaDeTrabalho;

	private Telas t;
	private FachadaClinicaMedica f;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		ArrayList<Consulta> consultas = f.listarConsultas();
		colunaNomeDoMedico.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaNomeDoPaciente.setCellValueFactory(new PropertyValueFactory<>("nome"));
		colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
		colunaRealizada.setCellValueFactory(new PropertyValueFactory<>("realizada"));
		tabelaConsultas.setItems(FXCollections.observableArrayList(consultas));
		logado = (Medico) t.getLogada();
	}

	@FXML
	public void alterarDiasDeTrabalho() {

	}

	@FXML
	public void realizarConsulta() {

	}

	@FXML
	public void editarPerfil() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogMedico")), t.getLogada());
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
	}

	@FXML
	public void cancelarConsultasDeUmDia() {

	}

}
