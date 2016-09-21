package classesTeste;

import java.time.LocalDate;
import java.time.LocalDateTime;

import negocio.beans.*;

public class TestRecepcionista {

	public static void main(String[] args) {
		Recepcionista atendente = new Recepcionista("Valeska", "69696969696", "322345");
		Medico doutor = new Medico("Jorge Medeiros", "14414424431", "Pediatra", "245561");
		atendente.cadastrarCliente("Henrique Farias", "22312245534", "Masculino", LocalDate.of(1998, 6, 2));
		atendente.marcarConsulta(doutor, atendente.getCliente("22312245534"), LocalDateTime.now());
		System.out.println(doutor.getTodasAsConsultas());
		System.out.println(atendente);
		System.out.println(Recepcionista.getTodosOsClientes());
		Cliente j = new Cliente("Joao", "123123", LocalDate.now(), "Masculino");
		atendente.marcarConsulta(doutor, j, LocalDateTime.now());
		System.out.println(Recepcionista.getTodasAsConsultas());
		atendente.desmarcarConsulta(atendente.getConsulta("123123"));
		System.out.println(Recepcionista.getTodasAsConsultas());
	}

}
