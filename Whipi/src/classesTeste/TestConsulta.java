package classesTeste;

import java.time.LocalDate;
import java.time.LocalDateTime;

import negocio.beans.*;

public class TestConsulta {
	public static void main(String[] args){
		Consulta teste = new Consulta(new Cliente("Pikachu", "1231234", LocalDate.of(1990, 8, 14), "Indefinido"), LocalDateTime.now(), new Medico("Enfermeira Joyce", "4555555", "Geral", "4312356"));
		System.out.println(teste);
		System.out.println(teste.getMedico());
	}
}
