/*
 * Whipi inc
 * Classe TelaMedicoController
 * Tem todas As funções do da Cena TelaMedico.fxml
 */
package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.NCDException;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Consulta;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Paciente;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.repositorio.RepositorioConsultas;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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

	/*
	 * inicializa a cena
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		lblLogado.setText(t.getLogada().getNome());
		Pessoa p = t.getLogada();
		if (p instanceof Medico) {
			logado = (Medico) p;
		}
		preencherTableViewConsultas();

	}
/*
 * altera os dias liberados para marcar consulta para um medico
 */
	@FXML
	public void alterarDiasDeTrabalho() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogDiasDeTrabalho")), t.getLogada());
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogDiasDeTrabalhoController p = t.getF().getController();
		p.setMedico((Medico) t.getLogada());
		p.mostrarDetalhes();
		t.abrirTelaDialogo();
	}

	/*
	 * Marca uma Consulta do médico como realizada(True)
	 */
	@FXML
	public void realizarConsulta() {
		if (consultaAtual != null) {
			consultaAtual.setRealizada(true);
		}

		preencherTableViewConsultas();
	}
	
/*
 * Abre a tela de Alterar informações pessoais do médico
 */
	@FXML
	public void editarPerfil() {
		t.setScene(new Scene((Parent) t.carregarFXML("DialogMedico")), t.getLogada());
		t.setDialogStage(new Stage());
		t.getDialogStage().initModality(Modality.WINDOW_MODAL);
		t.getDialogStage().initOwner(t.getStage());
		DialogMedicoController p = t.getF().getController();
		p.mostrarDetalhes((Medico) t.getLogada());
		t.abrirTelaDialogo();
	}
/*
 * Cancela uma consulta selecionada
 */
	@FXML
	public void cancelarConsultasDeUmDia() {
		if (consultaAtual != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Remover paciente");
			alert.setHeaderText("Deseja remover a consulta?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				ArrayList<Consulta> aux = f.listarConsultas();
				aux.remove(consultaAtual);
				preencherTableViewConsultas();
				RepositorioConsultas.getInstance().salvarConsultaEmArquivo();
			}
		}
	}

	/*
	 * fecha o programa
	 */
	@FXML
	public void sair() {
		t.sairDoSistema();

	}

	/*
	 * preenche a TableView Consultas
	 */
	public void preencherTableViewConsultas() {
		ArrayList<Consulta> consultas = null;
		try {
			consultas = f.consultasDoDia(logado);
			colunaNomeDoMedico
					.setCellValueFactory(new Callback<CellDataFeatures<Consulta, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Consulta, String> oferta) {
							return new SimpleStringProperty(oferta.getValue().getMedico().getNome());
						}
					});
			colunaNomeDoPaciente
					.setCellValueFactory(new Callback<CellDataFeatures<Consulta, String>, ObservableValue<String>>() {
						@Override
						public ObservableValue<String> call(CellDataFeatures<Consulta, String> oferta) {
							return new SimpleStringProperty(oferta.getValue().getPaciente().getNome());
						}
					});
			colunaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
			colunaRealizada
					.setCellValueFactory(new Callback<CellDataFeatures<Consulta, Boolean>, ObservableValue<Boolean>>() {
						@Override
						public ObservableValue<Boolean> call(CellDataFeatures<Consulta, Boolean> oferta) {
							return new SimpleBooleanProperty(oferta.getValue().foiRealizada());
						}
					});
			tabelaConsultas.setItems(FXCollections.observableArrayList(consultas));
			tabelaConsultas.getColumns().get(0).setVisible(false);
			tabelaConsultas.getColumns().get(0).setVisible(true);
		} catch (NCDException e) {
			Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Sucesso");
			a.setHeaderText(e.getMessage());
			a.setContentText(null);
			a.showAndWait();
		}

	}
/*
 * sai do perfil logado e volta para a tela inicial
 */
	@FXML
	public void logoff() {
		t.logoff();
	}

	/*
	 * pega a consulta clicada na tabela
	 */
	@FXML
	private void consultaClicado() {
		Consulta clicado = tabelaConsultas.getSelectionModel().getSelectedItem();
		if (clicado != null) {
			consultaAtual = clicado;
		}
	}
}
