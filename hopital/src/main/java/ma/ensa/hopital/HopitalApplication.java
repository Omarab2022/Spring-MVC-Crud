package ma.ensa.hopital;

import ma.ensa.hopital.entities.Patient;
import ma.ensa.hopital.repository.Patientrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class HopitalApplication  implements CommandLineRunner {


	@Autowired
	private Patientrepository patientrepository ;


	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}




	@Override
	public void run(String... args) throws Exception {

		//methode 1

		Patient patient1 = new Patient(null,"yassin",new Date(),false,123);

		//methode 2

		Patient patient2 = new Patient();
		patient2.setId(null);
		patient2.setNom("omar");
		patient2.setScore(333);
		patient2.setMalade(true);
		patient2.setDatenaissance(new Date());

		//methode 3 : using Builder

		Patient patient3 =Patient.builder()
				.id(null)
				.nom("anas")
				.score(444)
				.datenaissance(new Date())
				.malade(false)
				.build();


		patientrepository.save(patient1);
		patientrepository.save(patient2);
		patientrepository.save(patient3);


	}
}
