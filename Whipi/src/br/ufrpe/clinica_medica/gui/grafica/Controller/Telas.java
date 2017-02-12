package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.io.IOException;

import br.ufrpe.clinica_medica.App;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Telas{
	private Stage stage;
	private Scene scene;
	private static Telas instance;
	
	private Telas(){
	}
	
	public static Telas getInstance(){
		if(instance == null){
			instance = new Telas();
		}
		
		return instance;
	}
	
	public void setStage(Stage s){
		stage = s;
	}
	public Stage getStage(){
		return stage;
	}
	public void setScene(Scene s){
		scene = s;
	}
	public Scene getScene(){
		return scene;
	}
	
	
	public void abrirTela(){
		stage.setScene(scene);	
	}
	public void fecharTela(){
		stage.close();
	}
	
	public Node carregarFXML(String tela){
		String r = "gui/grafica/FXML/" + tela + ".fxml";
		Node root = null;
		App a = new App();
		try {
			root = FXMLLoader.load(a.getClass().getResource(r));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return root;
	}
}
