package br.ufrpe.clinica_medica.gui;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
	private TableColumn<Medico, String> columnSobrenome;
	
	private FachadaClinicaMedica fachada;

	@Override
	public void initialize(URL location, ResourceBundle resources){		
		String[] obj = {"Nome", "CPF", "CRM"};		
		cbxTiposPesquisa.getItems().addAll(obj);		
		this.fachada = FachadaClinicaMedica.getInstance();
		
		ObservableList<Medico> medicos = FXCollections.observableArrayList(fachada.ListarMedicos());
		
		tbMedico.setItems(medicos);
		
	}
	
	@FXML
	private void cadastrar(){
		DialogMedicoController d = new DialogMedicoController();
		d.initialize(null, null);
		
	}
	
	@FXML
	private void remover(){
		
	}
	
	@FXML
	private void alterar(){
		
	}
	
	private void preencheTabela(){
		tbMedico.getItems().setAll(fachada.ListarMedicos());
	}

}