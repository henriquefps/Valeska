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
	
	private App main;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void setApp(App main){
		this.main = main;
	}
	
	@FXML
	private void cadatrarMedico() throws Exception{
		main.telaCadatrarMedico();
	}
	

}
