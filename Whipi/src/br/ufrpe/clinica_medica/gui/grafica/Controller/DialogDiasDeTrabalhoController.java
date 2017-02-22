package br.ufrpe.clinica_medica.gui.grafica.Controller;


import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.exceptions.PNEException;
import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import br.ufrpe.clinica_medica.negocio.beans.Medico;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class DialogDiasDeTrabalhoController implements Initializable {
	@FXML private CheckBox ckbSeg;
	@FXML private CheckBox ckbTer;
	@FXML private CheckBox ckbQuar;
	@FXML private CheckBox ckbQui;
	@FXML private CheckBox ckbSex;
	@FXML private CheckBox ckbSab;
	@FXML private Label labelDia;
	@FXML private Button btnSalvar;
	@FXML private Button btnSair;
	
	private Medico medico;
	private FachadaClinicaMedica f;
	
	private Telas t;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		t = Telas.getInstance();
		f = FachadaClinicaMedica.getInstance();
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
		medico.setDia(0, ckbSeg.isSelected());
		medico.setDia(1, ckbTer.isSelected());
		medico.setDia(2, ckbQuar.isSelected());
		medico.setDia(3, ckbQui.isSelected());
		medico.setDia(4, ckbSex.isSelected());
		medico.setDia(5, ckbSab.isSelected());
		try {
			f.alterarMedico(medico.getCpf(), medico);
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Sucesso!");
			alert.setHeaderText("Cadastro!");
			alert.setContentText("Alteração realizada com sucesso!");
			alert.showAndWait();
			
		} catch (PNEException e) {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText("Alteração inválida!");
			alert.setContentText(e.getMessage());
			alert.showAndWait();
		}

		t.fecharTelaDialogo();
	}
	
	
	
	@FXML public void fecharTela(){
		t.fecharTelaDialogo();
	}
	
	protected void mostrarDetalhes(){
		Medico medico = ((Medico) t.getLogada());
		
		if (medico.isDia(0)) {
			ckbSeg.setSelected(true);
		}
		if(medico.isDia(1)){
			ckbTer.setSelected(true);
		}
		if (medico.isDia(2)) {
			ckbQuar.setSelected(true);
		}
		if (medico.isDia(3)) {
			ckbQui.setSelected(true);
		}
		if (medico.isDia(4)) {
			ckbSex.setSelected(true);
		}
		if (medico.isDia(5)) {
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
		btnSalvar.setVisible(false);
		btnSair.setLayoutX(174);
	}
}
