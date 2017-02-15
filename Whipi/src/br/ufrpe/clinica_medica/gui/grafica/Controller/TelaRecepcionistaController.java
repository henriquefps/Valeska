package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * controller da tela principal do recepcionista
 */
public class TelaRecepcionistaController implements Initializable {
	@FXML
	private BorderPane Right;
	
	private Telas t; 

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		t = Telas.getInstance();
		t.getStage().centerOnScreen();
		t.getStage().setMaximized(true);
		t.getStage().setTitle("Ol� Recepcionista");
		
		DatePickerSkin dt = new DatePickerSkin(new DatePicker(LocalDate.now()));
		Node calen = dt.getPopupContent();
		calen.prefHeight(400);
		Right.setBottom(calen);
		Right.getBottom().prefWidth(200);
		Right.getBottom().prefHeight(400);
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
