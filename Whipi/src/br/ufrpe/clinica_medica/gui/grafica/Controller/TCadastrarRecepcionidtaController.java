package br.ufrpe.clinica_medica.gui.grafica.Controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.ufrpe.clinica_medica.negocio.FachadaClinicaMedica;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TCadastrarRecepcionidtaController implements Initializable {
    

	@FXML
	private Button btRecepcionista;
    @FXML
	private Button btConsulta;
    @FXML
	private Button btPaciente;
    @FXML
	private Button btMedico;
    @FXML
   	private Button btSair;
    
    private Telas t;
    
    private FachadaClinicaMedica f;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		f =  FachadaClinicaMedica.getInstance();
		t = Telas.getInstance();
	}
	
	@FXML
	private void cadastrarRecepcionista() throws Exception{
		URL url = getClass().getResource("DialogRecepcionista.fxml");
		Parent parent = FXMLLoader.load(url);
		Stage s = new Stage();
		s.setTitle("Cadastrar Recepcionista");
		s.setScene(new Scene(parent));
		s.show();
	}
    
	@FXML
	private void cadastrarMedico() throws Exception{
		URL url = getClass().getResource("DialogMedico.fxml");
		Parent parent = FXMLLoader.load(url);
		Stage s = new Stage();
		s.setTitle("Cadastrar Medico");
		s.setScene(new Scene(parent));
		s.show();
	}
	
	/*@FXML
	private void cadastrarPaciente() throws Exception{
		URL url = getClass().getResource("DialogPaciente.fxml");
		Parent parent = FXMLLoader.load(url);
		Stage s = new Stage();
		s.setTitle("Cadastrar Paciente");
		s.setScene(new Scene(parent));
		s.show();
	}*/

	/*@FXML
	private void cadastrarConsultas() throws Exception{
		URL url = getClass().getResource("DialogConsulta.fxml");
		Parent parent = FXMLLoader.load(url);
		Stage s = new Stage();
		s.setTitle("Cadastrar Consulta");
		s.setScene(new Scene(parent));
		s.show();
	}*/
}
