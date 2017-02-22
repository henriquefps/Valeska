package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
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
		Pessoa p = t.getLogada();
		if(p instanceof Medico){
			
			logado = (Medico) p; 
		}
		
	}

	@FXML
	public void alterarDiasDeTrabalho() {

	}

	@FXML
	public void realizarConsulta() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogDiasDeTrabalho")), t.getLogada());
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		t.abrirTelaDialogo();
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

	}
	
	public void sair(){
		t.sairDoSistema();
		
	}
	
	public void logoff(){
		t.logoff();
	}

}
