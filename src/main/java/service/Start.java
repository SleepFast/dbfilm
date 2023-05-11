package service;

import java.util.List;

import xmlChecker.XmlCheck;
import DAO.ActeurDAO;
import DAO.FilmDAO;
import jakarta.persistence.EntityManager;
import model.Acteur;
import model.Film;

public class Start {
    public Start(EntityManager em) {

        // Check si dans mon persistence.xml la value est à none ou create pour lancer le remplissage de la db si on est en create
        new XmlCheck(em);

		//------------------------------------------------
		//Query pour trouver la filmographie d'un acteur
		//------------------------------------------------
		String acteurRandom = "Zulay Henao";
		
		List<Acteur> listActeurs = ActeurDAO.getActeursByName(acteurRandom, em);
		List<Film> listFilm = ActeurDAO.getFilmsFromActeurs(listActeurs, em);

		System.out.println("\nFILMOGRAPHIE de " + acteurRandom + " : \n");
		System.out.println(listFilm);
		System.out.println("\n------------------------");


		//------------------------------------------------
		//Query pour trouver le casting d'un film
		//------------------------------------------------
		String filmRandom = "Emmet's Holiday Party: A Lego Movie Short";

		List<Film> listFilms = FilmDAO.getFilmByName(filmRandom, em);
		List<Acteur> castingFilms = ActeurDAO.getActeursFromFilm(listFilms, em);

		System.out.println("\nCASTING du film " + filmRandom + " : \n");
		System.out.println(castingFilms);
		System.out.println("\n------------------------");
		

		//------------------------------------------------
		//Query pour trouver un film entre 2 dates données
		//------------------------------------------------
		int randomAnnee = 2017;
		int randomAnnee2 = 2018;

		List<Film> listFilmsDate = FilmDAO.getFilmBetweenDates(randomAnnee, randomAnnee2, em);

		System.out.println("\nFILM SORTIE ENTRE " + randomAnnee + " et " + randomAnnee2 + " : \n");
		System.out.println(listFilmsDate);
		System.out.println("\n------------------------");


		//------------------------------------------------
		//Query pour afficher les films dans lesquels les acteurs données ont joué
		//------------------------------------------------
		String acteurIdentite = "Georgann Johnson";
		String acteurIdentite2 = "Ed Kemmer";

		List<Acteur> listActeurs1 = ActeurDAO.getActeursFromNames(acteurIdentite, acteurIdentite2, em);
		List<Film> listFilmCommuns = FilmDAO.getFilmsSharedActors(listActeurs1, em);

		System.out.println("\nFILM(S) DANS LE(S)QUEL(S) JOUENT " + acteurIdentite + " et " + acteurIdentite2 + " : \n");
		System.out.println(listFilmCommuns);
		System.out.println("\n------------------------");


		//------------------------------------------------
		//Query pour afficher les acteurs communs à 2 films donnés
		//------------------------------------------------
		String filmTitle = "Too Far from Norm";
		String filmTitle2 = "Untitled Super Mario Project";

		List<Film> listFilms2 = FilmDAO.getFilmsByNames(filmTitle, filmTitle2, em);

		List<Acteur> listActeursCommuns = ActeurDAO.getActeursCommunsFromFilms(listFilms2);

		System.out.println("\nACTEURS QUI ONT JOUE DANS " + filmTitle + " ET DANS " + filmTitle2 + " : \n");
		System.out.println(listActeursCommuns);
		System.out.println("\n------------------------");
    }
}
