package ma.ensa.hopital.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity

@Data // Cette annotation génère automatiquement les méthodes toString(), equals(), hashCode()
@NoArgsConstructor // génère un constructeur par défaut (sans arguments)
@AllArgsConstructor //Cette annotation génère un constructeur avec tous les arguments pour la classe.
@Builder // Cette annotation génère un builder pour la classe,

public class Patient {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;

	@NotEmpty
	@Size(min = 4 , max = 20)
	private String nom ;

	@Temporal(TemporalType.DATE)
	private Date datenaissance ;

	private boolean malade ;

	@Min(100)
	private  int score ;


}
