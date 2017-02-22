package br.ufrpe.clinica_medica.gui.grafica.Controller;


import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.beans.DiasDeAtendimento;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;

public class DialogDiasDeTrabalhoController implements Initializable {
	@FXML private CheckBox ckbSeg;
	@FXML private CheckBox ckbTer;
	@FXML private CheckBox ckbQuar;
	@FXML private CheckBox ckbQui;
	@FXML private CheckBox ckbSex;
	@FXML private CheckBox ckbSab;
	@FXML private Label labelDia;
	
	private Medico medico;
	
	private Telas t;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		t = Telas.getInstance();
		mostrarDetalhes();
	}
	
	public void setMedico(Medico medico){
		this.medico = medico;
	}
	
	public Medico getMedico(){
		return medico;
	}
	
	@FXML public void salvarAlteracao(){
		Medico medico = ((Medico) t.getLogada());
		medico.setDia(0, ckbSeg.isArmed());
		medico.setDia(1, ckbTer.isArmed());
		medico.setDia(2, ckbQuar.isArmed());
		medico.setDia(3, ckbQui.isArmed());
		medico.setDia(4, ckbSex.isArmed());
		medico.setDia(5, ckbSab.isArmed());
		t.fecharTelaDialogo();
	}
	
	
	
	@FXML public void fecharTela(){
		t.fecharTelaDialogo();
	}
	
	protected void mostrarDetalhes(){
		Medico medico = ((Medico) t.getLogada());
		if (medico.isDia(0)) {
			ckbSeg.arm();
		} else {
			ckbSeg.disarm();
		}
		if(medico.isDia(1)){
			ckbTer.arm();
		} else {
			ckbTer.disarm();
		}
		if (medico.isDia(2)) {
			ckbQuar.arm();
		} else{
			ckbQuar.disarm();
		}
		if (medico.isDia(3)) {
			ckbQui.arm();
		} else {
			ckbQui.disarm();
		}
		if (medico.isDia(4)) {
			ckbSex.arm();
		} else {
			ckbSex.disarm();
		}
		if (medico.isDia(5)) {
			ckbSab.arm();
		} else{
			ckbSab.disarm();
		}
	}
	
	protected void verDetalhes(){
		this.mostrarDetalhes();
		labelDia.setText("Ver dias de trabalho");
		ckbSeg.setDisable(true);
		ckbTer.setDisable(true);
		ckbQuar.setDisable(true);
		ckbQui.setDisable(true);
		ckbSex.setDisable(true);
		ckbSab.setDisable(true);
	}
}
