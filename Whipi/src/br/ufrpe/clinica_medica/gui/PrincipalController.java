package br.ufrpe.clinica_medica.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PrincipalController implements Initializable {
	
	@FXML
	private Button btnMedico;
	@FXML
	private Button btnPaciente;
	@FXML
	private Button btnRecepcionista;
	@FXML
	private Button btnConsulta;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	@FXML
	private void cadatrarMedico() throws Exception{
		URL url = getClass().getResource("TCadastrarMedico.fxml");
		Parent parent = FXMLLoader.load(url);
		Stage s = new Stage();
		s.setTitle("Cadastro Médicos");
		s.setScene(new Scene(parent));
		s.show();
	}
	

}
