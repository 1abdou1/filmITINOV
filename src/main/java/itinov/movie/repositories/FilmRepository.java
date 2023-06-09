package itinov.movie.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import itinov.movie.models.Film;

public interface FilmRepository extends JpaRepository<Film, Long>{


	List<Film> findAllByOrderByDateSortieFilmDesc();

	List<Film> findAllByOrderByNoteDesc();

	List<Film> findBydejaVuTrue();

	List<Film> findBydejaVuFalse();
}
