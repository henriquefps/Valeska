package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import br.ufrpe.clinica_medica.App;
import br.ufrpe.clinica_medica.negocio.beans.Pessoa;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Telas {
	private Stage stage;
	private Stage dialogStage;
	private static Telas instance;
	private App a;
	private ArrayList<Scene> cena;
	private FXMLLoader f;
	private Pessoa logada;
	public Pessoa getLogada() {
		return logada;
	}

	public void setLogada(Pessoa logada) {
		this.logada = logada;
	}

	private Telas() {
		cena = new ArrayList<Scene>();
	}

	public static Telas getInstance() {
		if (instance == null) {
			instance = new Telas();
		}

		return instance;
	}
	
	
	public FXMLLoader getF() {
		return f;
	}

	public void setF(FXMLLoader f) {
		this.f = f;
	}

	public void setA(App a) {
		this.a = a;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Stage getStage() {
		return stage;
	}

	public void setScene(Scene s, Pessoa log) {
		this.cena.add(s);
		this.logada = log;
	}
	
	public void setScene(Scene s) {
		this.cena.add(s);
	}

	public void abrirTelaDialogo() {
		dialogStage.setScene(cena.get(cena.size() - 1));
		dialogStage.showAndWait();
	}

	public void fecharTelaDialogo() {
		this.cena.remove(cena.size() - 1);
		dialogStage.close();
	}

	public void abrirTela() {
		stage.setScene(this.cena.get(cena.size() - 1));
		stage.show();
	}

	public void fecharTela() {
		stage.close();
	}

	public Node carregarFXML(String tela) {
		String r = "gui/grafica/FXML/" + tela + ".fxml";
		Node root = null;
		f = new FXMLLoader(a.getClass().getResource(r));
		try {
			root = f.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return root;
	}

	public void voltarTela() {
		this.cena.remove(cena.size() - 1);
	}
	
	public void abrirTelaLogin(){
		setDialogStage(new Stage());
		setScene(new Scene((Parent) carregarFXML("TelaPrincipal")), logada);
		abrirTelaDialogo();
	}
	
	public void logoff() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirma��o");
		alert.setHeaderText("Logoff");
		alert.setContentText("Deseja fazer logoff?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			fecharTela();
			abrirTelaLogin();
		} else {
		    alert.close();
		}
	}
}
