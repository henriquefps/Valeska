package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.io.IOException;
import java.util.ArrayList;

import br.ufrpe.clinica_medica.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas {
	private Stage stage;
	private Stage dialogStage;
	private static Telas instance;
	private App a;
	private ArrayList<Scene> cena;

	private Telas() {
		cena = new ArrayList<Scene>();
	}

	public static Telas getInstance() {
		if (instance == null) {
			instance = new Telas();
		}

		return instance;
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

	public void setScene(Scene s) {
		this.cena.add(s);
	}

	public void abrirTelaDialogo() {
		dialogStage.setScene(cena.get(cena.size() - 1));
		dialogStage.showAndWait();
	}

	public void fecharTelaDialogo() {
		//this.cena.remove(cena.size() - 1);
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
		try {
			root = FXMLLoader.load(a.getClass().getResource(r));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return root;
	}

	public void voltarTela() {
		this.cena.remove(cena.size() - 1);
	}
}
