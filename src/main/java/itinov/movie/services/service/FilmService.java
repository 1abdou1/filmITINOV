package itinov.movie.services.service;

import java.util.List;

import itinov.movie.models.Film;


public interface FilmService {

		List<Film> getFilms();

	    Film createFilm(Film film);

	    Film updateFilm(long filmId, Film film);

	    Film getFilm(long filmId);

	    Film deleteFilm(long filmId);


		List<Film> findFavorisByOrderByDateSortie();

		List<Film> findFavorisByOrderByNote();

		List<Film> findDejaVu();

		List<Film> findNonVu();
}
