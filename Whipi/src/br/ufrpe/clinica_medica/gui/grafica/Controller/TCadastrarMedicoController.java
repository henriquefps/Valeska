package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TCadastrarMedicoController implements Initializable {

	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnRemover;
	@FXML
	private Button btnAlterar;
	@FXML
	private TextField txfPesquisa;
	@FXML
	private ComboBox<String> cbxTiposPesquisa;
	@FXML
	private TableView<Medico> tbMedico;
	@FXML
	private TableColumn<Medico, String> columnNome;
	@FXML
	private TableColumn<Medico, String> columnCRM;

	private FachadaClinicaMedica fachada;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[] obj = { "Nome", "CPF", "CRM" };
		cbxTiposPesquisa.getItems().addAll(obj);
		this.fachada = FachadaClinicaMedica.getInstance();

		// columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		// columnCRM.setCellValueFactory(new PropertyValueFactory<>("numCRM"));
		tbMedico.setItems(FXCollections.observableArrayList(fachada.ListarMedicos()));

	}

	@FXML
	private void cadastrar() throws Exception {
		URL url = getClass().getResource("DialogMedico.fxml");
		Parent parent = FXMLLoader.load(url);
		Stage s = new Stage();
		s.setTitle("Edi��o");
		s.setScene(new Scene(parent));
		s.show();
	}

	@FXML
	private void remover() {

	}

	@FXML
	private void alterar() {

	}

}