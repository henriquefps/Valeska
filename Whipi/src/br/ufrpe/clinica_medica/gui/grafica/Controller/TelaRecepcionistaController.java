package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
	private Telas t; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
	}

	public TelaRecepcionistaController() {
		s = new Stage();
		s.centerOnScreen();
		s.setResizable(false);
		s.setTitle("Olá Recepcionista");
		
	}
	
	public void setStage(Stage s){
		this.s = s;
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
		//showTelaRecepcionista();
	}
	public void showTelaRecepcionista(){
		//t.setScene(atual);
		this.s.setScene(this.atual);
		this.s.show();
	}
	@FXML
	public void showTelaRecepcionista_Cadastro(){
		telaRecep();
		Parent p = (Parent) t.carregarFXML("TelaRecepcionista_Cadastro");
		t.setScene(new Scene(p));
		t.abrirTela();
		//s.setScene(this.cadastro);
		//s.show();
	}

	@FXML
	public void showTelaRecepcionista_Editar(){
		s.setScene(this.editar);
		s.show();
	}
	
}
