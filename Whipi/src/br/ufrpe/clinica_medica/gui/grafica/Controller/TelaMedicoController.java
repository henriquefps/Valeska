package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.NCDException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

	private Telas t;
	private FachadaClinicaMedica f;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		lblLogado.setText(t.getLogada().getNome());
		Pessoa p = t.getLogada();
		if(p instanceof Medico){
			logado = (Medico) p; 
		}
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
		
	}
	
	@FXML
	public void sair(){
		t.sairDoSistema();
		
	}
	
	public void preencherTableViewConsultas(){
		ArrayList<Consulta> consultas = null;
		try {
			consultas = f.consultasDoDia(logado);
			colunaNomeDoMedico.setCellValueFactory(new PropertyValueFactory<>("nomeDoMedico"));
			colunaNomeDoPaciente.setCellValueFactory(new PropertyValueFactory<>("nomeDoPaciente"));
			colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
			colunaRealizada.setCellValueFactory(new PropertyValueFactory<>("realizada"));
			tabelaConsultas.setItems(FXCollections.observableArrayList(consultas));
		} catch (NCDException e) {
			e.printStackTrace();
		}
		
	}
	
	@FXML
	public void logoff(){
		t.logoff();
	}

}
