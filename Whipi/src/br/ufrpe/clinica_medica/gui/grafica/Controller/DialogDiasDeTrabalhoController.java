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
		DiasDeAtendimento v[] = ((Medico) t.getLogada()).getDiasDeTrabalho();
		v[1].setDia(ckbSeg.isArmed());
		v[2].setDia(ckbTer.isArmed());
		v[3].setDia(ckbQuar.isArmed());
		v[4].setDia(ckbQui.isArmed());
		v[5].setDia(ckbSex.isArmed());
		v[6].setDia(ckbSab.isArmed());
		t.fecharTelaDialogo();
	}
	
	
	
	@FXML public void fecharTela(){
		t.fecharTelaDialogo();
	}
	
	protected void mostrarDetalhes(){
		DiasDeAtendimento v[] = ((Medico) t.getLogada()).getDiasDeTrabalho();
		if (v[0].isDia()) {
			ckbSeg.arm();
		} else {
			ckbSeg.disarm();
		}
		if(v[1].isDia()){
			ckbTer.arm();
		} else {
			ckbTer.disarm();
		}
		if (v[2].isDia()) {
			ckbQuar.arm();
		} else{
			ckbQuar.disarm();
		}
		if (v[3].isDia()) {
			ckbQui.arm();
		} else {
			ckbQui.disarm();
		}
		if (v[4].isDia()) {
			ckbSex.arm();
		} else {
			ckbSex.disarm();
		}
		if (v[5].isDia()) {
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
