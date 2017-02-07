package br.ufrpe.clinica_medica.gui.grafica;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * controller da tela principal do recepcionista
 */
public class TelaRecepcionistaController implements Initializable {
	private Stage s;
	private Scene cadastro;
	private Scene editar;
	private Scene remover;
	private Scene voltar;
	private Scene atual;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public TelaRecepcionistaController() {
		s = new Stage();
		s.centerOnScreen();
		s.setResizable(false);
		s.setTitle("Olá Recepcionista");
		
	}

	public Stage getStageRecepcionista() {
		return s;
	}

	@FXML
	public void telaRecep() {
		try {
			this.atual = new Scene(FXMLLoader.load(getClass().getResource("TelaRecepcionista.fxml")));
			this.cadastro = new Scene(FXMLLoader.load(getClass().getResource("TelaRecepcionista_Cadastro.fxml")));
			this.editar = new Scene(FXMLLoader.load(getClass().getResource("TelaRecepcionista_Editar.fxml")));
		} catch (IOException a) {
			a.printStackTrace();
		}
		showTelaRecepcionista();
	}
	public void showTelaRecepcionista(){
		this.s.setScene(this.atual);
		this.s.show();
	}
	@FXML
	public void showTelaRecepcionista_Cadastro(){
		s.setScene(this.cadastro);
		s.show();
	}

	@FXML
	public void showTelaRecepcionista_Editar(){
		s.setScene(this.editar);
		s.show();
	}
	
}
