package br.ufrpe.clinica_medica.gui.grafica;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaPrincipalController implements Initializable {
	
	@FXML private TextField txfUsuario;
	@FXML private PasswordField psfSenha;
	 
	private FachadaClinicaMedica fachada;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = FachadaClinicaMedica.getInstance();
		
	}
	
	@FXML
	public void validaLogin() {
		String usuario = txfUsuario.getText();
		String senha = psfSenha.getText();
		System.out.println("Usuário: " + usuario);
		System.out.println("Senha: " + senha);
		//TODO Código para passar pra camanda de negocio e fazer as checagens e posteriormente passar pra uma suposta tela.
	}
	
	public void sair(){
		System.exit(0);
	}


}
