package index;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import parser.Json;
import jakarta.persistence.EntityManager;
import model.Acteur;
import model.Film;
import model.Genre;
import model.Lieu;
import model.Pays;
import model.Realisateur;
import model.Role;

public class AppMovie {

	public static void main(String[] args) {
		EntityManager em = UtilsMovie.getInstance().getEntityManager();
		Film film = new Film();
		film.setId("pouet123");
		film.setLangue("Fr");
		film.setPlot("lalalalal");
		film.setNom("thor");
		film.setSortie("2012");
		film.setUrl("titi/tata/pouet");

		Realisateur real = new Realisateur();
		real.setIdentite("mongol");
		real.setUrl("titi/tata/pouet");
		Realisateur real1 = new Realisateur();
		real1.setIdentite("pouet");
		real1.setUrl("titi/tata/pouet");
		Realisateur real2 = new Realisateur();
		real2.setIdentite("pit");
		real2.setUrl("titi/tata/pouet");

		Pays pays = new Pays();
		pays.setNom("France");
		pays.setUrl("tata/titi/pat");

		Lieu lieu = new Lieu();
		lieu.setEtatDept("herault");
		lieu.setPays("France");
		lieu.setVille("montpellier");

		Acteur acteur = new Acteur();
		acteur.setId("qskpgnjlmqsdng");
		acteur.setIdentite("pouet pit");
		acteur.setLieuNaissance("pat");
		acteur.setNaissance("25/06/1997");
		acteur.setUrl("pouet/pit/part.com");

		Role role = new Role();
		role.setNom("monpapy");
		role.setFilm(film);
		role.setActeur(acteur);
		Role role1 = new Role();
		role1.setNom("thor");
		role1.setFilm(film);
		role1.setActeur(acteur);

		Genre genre = new Genre();
		genre.setNom("achier");
		Genre genre1 = new Genre();
		genre1.setNom("action");
		
		film.setLieu(lieu);
		film.setPays(pays);
		
		List<Realisateur> listReal = new ArrayList<>(Arrays.asList(real, real2));
		film.setRealisateurs(listReal);
		List<Genre> listGenre = new ArrayList<>(Arrays.asList(genre, genre1));
		film.setGenre(listGenre);
		
		ObjectMapper mapper = Json.getMapper();
		
		em.getTransaction().begin();
		try {
			Film[] mesFilms = mapper.readValue(new File("filmsTest.json"), Film[].class);
			for (Film monfilm : mesFilms) {
				System.out.println(monfilm);
				em.merge(monfilm);
			}
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// em.persist(film);
		em.getTransaction().commit();
		

		em.close();
		
		
	}
	
}
