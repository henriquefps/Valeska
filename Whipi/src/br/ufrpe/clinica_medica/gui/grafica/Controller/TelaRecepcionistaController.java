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
	private Scene cadastro;
	private Scene editar;
	private Scene remover;
	private Scene voltar;
	private Scene atual;
	private Telas t; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		t.getStage().centerOnScreen();
		t.getStage().setMaximized(true);
		t.getStage().setResizable(false);
		t.getStage().setTitle("Ol� Recepcionista");
	}

	@FXML
	public void showTelaRecepcionista_Cadastro(){
		t.setDialogStage(new Stage());	 
		t.setScene(new Scene((Parent) t.carregarFXML("TelaPacienteCadastro")));
		t.abrirTelaDialogo();
	}

	@FXML
	public void showTelaRecepcionista_Editar(){
		t.setScene(new Scene((Parent) t.carregarFXML("TelaRecepcionista_Editar")));
		t.abrirTela();
	}
	
}
