package itinov.movie.models;

import lombok.*;

import java.time.LocalDate;

import org.hibernate.annotations.Where;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "deleted=false")
public class Film {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/* une sorte de matricule a un film 
	 * ex:ACT001,DRM001,HUM001
	 * Action,Drama,Humour ...ect
	 */
    private String filmReference;
    
    private LocalDate dateSortieFilm;
    private String titre;
    private Integer note;
    private boolean dejaVu;
    private boolean deleted;    
    }

