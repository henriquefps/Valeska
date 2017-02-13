package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import br.ufrpe.clinica_medica.negocio.beans.Recepcionista;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class TelaPrincipalController implements Initializable {
	
	@FXML private TextField txfUsuario;
	@FXML private PasswordField psfSenha;
	private FachadaClinicaMedica fachada;
	private Telas t;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fachada = FachadaClinicaMedica.getInstance();
		t = Telas.getInstance();
	}
	
	@FXML
	public void validaLogin() {
		String usuario = txfUsuario.getText();
		String senha = psfSenha.getText();
		System.out.println("Usuário: " + usuario);
		System.out.println("Senha: " + senha);
		Pessoa resultado = fachada.efetuarLogin(usuario, senha);
		if (resultado instanceof Recepcionista) {
			t.setScene(new Scene((Parent) t.carregarFXML("TelaRecepcionista")));
			t.abrirTela();
		} else if (resultado instanceof Medico) {
			//TODO Tela de mï¿½dico
		} else if (resultado instanceof Pessoa) {
			//Inicia a tela admin, mas nï¿½o fecha a tela principal
			//Pergunta: onde a tela principal estï¿½ sendo invocada??
			//TODO Fechar a tela principal ao invocar outra tela
			t.setScene(new Scene((Parent) t.carregarFXML("TelaAdmin")));
			t.abrirTela();
		} else {
			this.dadosInvalidos();
		}
	}
	
	public void sair(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
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
		alert.setHeaderText("Dados invï¿½lidos!");
		alert.setContentText("Usuï¿½rio ou senha invï¿½lidos.");

		alert.showAndWait();
	}

}
