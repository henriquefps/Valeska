import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestMedico {

	public static void main(String[] args) {
		Cliente teste = new Cliente("Henrique Farias", "22312245534", LocalDate.of(1998, 6, 2), "Masculino");
		Medico doutor = new Medico("Jorge Medeiros", "14414424431", "Pediatra", "245561");
		doutor.registrarConsulta(new Consulta(teste, LocalDateTime.of(2016, 9, 18, 15, 30), doutor));
		doutor.registrarConsulta(new Consulta(teste, LocalDateTime.of(2016, 10, 18, 16, 30), doutor));
		System.out.println(doutor);
		System.out.println(doutor.consultasDeHoje());
		doutor.realizarConsulta(doutor.getTodasAsConsultas().get(0), "");;
		System.out.println(doutor.consultasDeHoje());
	}
}
