package itinov.movie.services.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itinov.movie.exceptions.FilmNotFoundException;
import itinov.movie.models.Film;
import itinov.movie.repositories.FilmRepository;
import itinov.movie.services.service.FilmService;

@Service
public class FilmServiceImpl implements FilmService{

	private final FilmRepository filmRepository;

    @Autowired
    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    @Override
    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }

    @Override
    public Film updateFilm(long filmId, Film film) {
        Film existingFilm = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film", "id", filmId));

        existingFilm.setTitre(film.getTitre());
        existingFilm.setDateSortieFilm(film.getDateSortieFilm());
        existingFilm.setNote(film.getNote());
        existingFilm.setDejaVu(film.isDejaVu());

        return filmRepository.save(existingFilm);
    }
    
    @Override
    public Film getFilm(long filmId) {
        return filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film", "id", filmId));
    }

    @Override
    public Film deleteFilm(long filmId) {
        Film film = filmRepository.findById(filmId)
                .orElseThrow(() -> new FilmNotFoundException("Film", "id", filmId));
        film.setDeleted(true);
        filmRepository.save(film);
        
        return film;
    }

    @Override
    public List<Film> findFavorisByOrderByDateSortie() {
        return filmRepository.findAllByOrderByDateSortieFilmDesc();
    }
 

	@Override
	public List<Film> findFavorisByOrderByNote() {
        return filmRepository.findAllByOrderByNoteDesc();
	}

	@Override
	public List<Film> findDejaVu() {
		return filmRepository.findBydejaVuTrue();
	}

	@Override
	public List<Film> findNonVu() {
		return filmRepository.findBydejaVuFalse();
	}
}