package itinov.movie.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import itinov.movie.exceptions.FilmNotFoundException;
import itinov.movie.models.Film;
import itinov.movie.services.service.FilmService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("/ajoutFavoris")
    public Film ajouterFilmFavoris(@RequestBody Film film) {
        return filmService.createFilm(film);
    }

    @DeleteMapping("/retirerFavoris/{id}")
    public ResponseEntity<String> retirerFilmFavoris(@PathVariable String id) {       
        try {
            Long filmId = Long.parseLong(id);
            filmService.deleteFilm(filmId);
            return ResponseEntity.ok("Film retiré des favoris avec succès");
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id de film invalid");
        } catch (FilmNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    

    @GetMapping("/favoris/listerParDateSortie")
    public List<Film> listerFilmFavorisParDateSortie() {
        return filmService.findFavorisByOrderByDateSortie();
    }

    @GetMapping("/favoris/listerParNote")
    public List<Film> listerFilmFavorisParNote() {
        return filmService.findFavorisByOrderByNote();
    }

    /*ici aussi je devais gerer l'exception comme deleteMapping
    en cas ou la variable n'est pas de type long */
    @PostMapping("/dejaVu/{id}")
    public Film marquerDejaVu(@PathVariable Long id) {
        Film film = filmService.getFilm(id);               
        film.setDejaVu(true);
        return filmService.updateFilm(id,film);
    }

    @GetMapping("/dejaVu")
    public List<Film> listerDejaVu() {
        return filmService.findDejaVu();
    }

    @GetMapping("/nonVu")
    public List<Film> listerNonVu() {
    	return filmService.findNonVu();
    }

    @ExceptionHandler(FilmNotFoundException.class)
    public void handleFilmNotFoundException(FilmNotFoundException exception) {
        log.error(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception exception) {
        log.error("An error occurred: ", exception);
    }

   
}
