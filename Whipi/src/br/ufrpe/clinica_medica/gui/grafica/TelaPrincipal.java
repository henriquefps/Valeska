package br.ufrpe.clinica_medica.gui.grafica;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class TelaPrincipal implements Initializable {
	
	@FXML private TextField textField;
	@FXML private PasswordField passField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
	
	@FXML
	public void validaLogin() {
		String usuario = textField.getText();
		String senha = passField.getText();
		//TODO Código para passar pra camanda de negocio e fazer as checagens e posteriormente passar pra uma suposta tela.
	}


}
