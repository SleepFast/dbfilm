package index;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;
import parser.Json;

public class AppMovie {

	public static void main(String[] args) {

		EntityManager em = UtilsMovie.getInstance().getEntityManager();
		em.getTransaction().begin();

		Json.parseJson("filmsTest.json", em);

		em.getTransaction().commit();

		//------------------------------------------------
		//Query pour trouver la filmographie d'un acteur
		//------------------------------------------------
		// TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findByName", Acteur.class);
		// namedQueryActeur.setParameter("name", "%" + "Priyanka" + "%");
		// List<Acteur> listActeurs = namedQueryActeur.getResultList();

		// TypedQuery<Role> namedQuery1Role = em.createNamedQuery("Role.findByActorId", Role.class);
		
		// for(Acteur acteur : listActeurs) {

		// 	System.out.println();
		// 	System.out.println(acteur.getId());
		// 	namedQuery1Role.setParameter("acteur", acteur.getId());
		// 	List<Role> listRole = namedQuery1Role.getResultList();
		// 	for (Role role : listRole) {
		// 		System.out.println(role.getFilm());
		// 	}
		// }


		//------------------------------------------------
		//Query pour trouver la le casting d'un film
		//------------------------------------------------
		// TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByActorId", Film.class);
		// namedQueryFilm.setParameter("film", "%" + "Cowboy" + "%");
		// List<Film> listFilms = namedQueryFilm.getResultList();

		// TypedQuery<Role> namedQueryRole = em.createNamedQuery("Role.findByFilmId", Role.class);

		// for (Film film : listFilms) {
		// 	namedQueryRole.setParameter("film", film.getId());
		// 	List<Role> listRole = namedQueryRole.getResultList();
		// 	for (Role role : listRole) {
		// 		System.out.println(role.getActeur());
		// 	}
		// }

        em.close();
	}
	
}
