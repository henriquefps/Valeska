package br.ufrpe.clinica_medica.gui.grafica.Controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class DialogDiasDeTrabalhoController implements Initializable {
	@FXML private CheckBox ckbDomingo;
	@FXML private CheckBox ckbSegunda;
	@FXML private CheckBox ckbTerca;
	@FXML private CheckBox ckbQuarta;
	@FXML private CheckBox ckbQuinta;
	@FXML private CheckBox ckbSexta;
	@FXML private CheckBox ckbSabado;
	
	@FXML private MenuButton menuDomingoInicio;
	@FXML private MenuButton menuDomingoFim;
	@FXML private MenuButton menuSegundaInicio;
	@FXML private MenuButton menuSegundaFim;
	@FXML private MenuButton menuTercaInicio;
	@FXML private MenuButton menuTercaFim;
	@FXML private MenuButton menuQuartaInicio;
	@FXML private MenuButton menuQuartaFim;
	@FXML private MenuButton menuQuintaInicio;
	@FXML private MenuButton menuQuintaFim;
	@FXML private MenuButton menuSextaInicio;
	@FXML private MenuButton menuSextaFim;
	@FXML private MenuButton menuSabadoInicio;
	@FXML private MenuButton menuSabadoFim;
	
	private Telas t;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		t = Telas.getInstance();
	}
	
	@FXML public void salvarAlteracoes(){
		
	}
	
	
	
	@FXML public void fecharTela(){
		t.fecharTelaDialogo();
	}


}
