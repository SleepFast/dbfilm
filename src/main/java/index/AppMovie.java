package index;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
		film.setSortie(new Date());
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
		acteur.setNaissance(new Date());
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

		em.getTransaction().begin();
		em.persist(genre);
		em.persist(genre1);
		em.persist(real);
		em.persist(real1);
		em.persist(real2);
		em.persist(pays);
		em.persist(lieu);
		em.persist(film);
		em.persist(acteur);
		em.persist(role);
		em.persist(role1);
		em.getTransaction().commit();


		em.close();
	}

}
