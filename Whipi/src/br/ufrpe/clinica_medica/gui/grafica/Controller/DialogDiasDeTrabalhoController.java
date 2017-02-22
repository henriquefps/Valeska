package br.ufrpe.clinica_medica.gui.grafica.Controller;


import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.DiasDeAtendimento;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;

public class DialogDiasDeTrabalhoController implements Initializable {
	@FXML private CheckBox ckbSeg;
	@FXML private CheckBox ckbTer;
	@FXML private CheckBox ckbQuar;
	@FXML private CheckBox ckbQui;
	@FXML private CheckBox ckbSex;
	@FXML private CheckBox ckbSab;
	@FXML private Label labelDia;
	
	private Telas t;
	
	private FachadaClinicaMedica f; 
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
		mostrarDetalhes();
	}
	
	@FXML public void salvarAlteracao(){
		DiasDeAtendimento v[] = ((Medico) t.getLogada()).getDiasDeTrabalho();
		v[0].setDia(ckbSeg.isSelected());
		v[1].setDia(ckbTer.isSelected());
		v[2].setDia(ckbQuar.isSelected());
		v[3].setDia(ckbQui.isSelected());
		v[4].setDia(ckbSex.isSelected());
		v[5].setDia(ckbSab.isSelected());
		Medico m = ((Medico) t.getLogada());
		m.setDiasDeTrabalho(v);
		try {
			f.alterarMedico(m.getCpf(), m);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso!");
			alert.setHeaderText("Cadastro!");
			alert.setContentText("Médico atualizado com sucesso!");
			alert.showAndWait();
			
		} catch (PNEException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText("Cadastro inválido!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}
		t.fecharTelaDialogo();
	}
	
	
	
	@FXML public void fecharTela(){
		t.fecharTelaDialogo();
	}
	
	protected void mostrarDetalhes(){
		DiasDeAtendimento v[] = ((Medico) t.getLogada()).getDiasDeTrabalho();
		if (v[0].isDia()) {
			ckbSeg.setSelected(true);
		}
		if(v[1].isDia()){
			ckbTer.setSelected(true);
		}
		if (v[2].isDia()) {
			ckbQuar.setSelected(true);
		}
		if (v[3].isDia()) {
			ckbQui.setSelected(true);
		}
		if (v[4].isDia()) {
			ckbSex.setSelected(true);
		}
		if (v[5].isDia()) {
			ckbSab.setSelected(true);
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
