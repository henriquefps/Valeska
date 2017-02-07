package br.ufrpe.clinica_medica.gui.grafica;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaPrincipalController implements Initializable {
	
	@FXML private TextField txfUsuario;
	@FXML private PasswordField psfSenha;
	 
	private FachadaClinicaMedica fachada;
	private TelaAdmin admin;
	private TelaRecepcionista recep;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = FachadaClinicaMedica.getInstance();
		
	}
	
	@FXML
	public void validaLogin() {
		String usuario = txfUsuario.getText();
		String senha = psfSenha.getText();
		System.out.println("Usu�rio: " + usuario);
		System.out.println("Senha: " + senha);
		Pessoa resultado = fachada.efetuarLogin(usuario, senha);
		if (resultado instanceof Recepcionista) {
			recep = new TelaRecepcionista();
			recep.telaRecep();
		} else if (resultado instanceof Medico) {
			//TODO Tela de m�dico
		} else if (resultado instanceof Pessoa) {
			//Inicia a tela admin, mas n�o fecha a tela principal
			//Pergunta: onde a tela principal est� sendo invocada??
			//TODO Fechar a tela principal ao invocar outra tela
			admin = new TelaAdmin();
			admin.telaAdmin();
		} else {
			this.dadosInvalidos();
		}
	}
	
	public void sair(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirma��o");
		alert.setHeaderText("Sair");
		alert.setContentText("Deseja fechar o programa?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			System.exit(0);
		} else {
		    alert.close();
		}
	}
	
	public void dadosInvalidos() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Erro de dados!");
		alert.setHeaderText("Dados inv�lidos!");
		alert.setContentText("Usu�rio ou senha inv�lidos.");

		alert.showAndWait();
	}

}
