package index;

import java.util.ArrayList;
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

		// Json.parseJson("films.json", em);

		em.getTransaction().commit();


		//------------------------------------------------
		//Query pour trouver la filmographie d'un acteur
		//------------------------------------------------
		TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findByName", Acteur.class);
		namedQueryActeur.setParameter("name",  "Zulay Henao");
		List<Acteur> listActeurs = namedQueryActeur.getResultList();

		TypedQuery<Role> namedQuery1Role = em.createNamedQuery("Role.findByActorId", Role.class);
		

		for(Acteur acteur : listActeurs) {

			System.out.println("\nFILMOGRAPHIE de " + acteur.getIdentite() + " : \n");

			namedQuery1Role.setParameter("acteur", acteur.getId());
			List<Role> listRole = namedQuery1Role.getResultList();
			for (Role role : listRole) {
				
				System.out.println(role.getFilm());
			}
		}

		System.out.println("\n------------------------");


		//------------------------------------------------
		//Query pour trouver le casting d'un film
		//------------------------------------------------
		TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByFilmName", Film.class);
		namedQueryFilm.setParameter("film", "Emmet's Holiday Party: A Lego Movie Short");
		List<Film> listFilms = namedQueryFilm.getResultList();

		TypedQuery<Role> namedQueryRole = em.createNamedQuery("Role.findByFilmId", Role.class);

		for (Film film : listFilms) {

			System.out.println("\nCASTING du film " + film.getNom() + " : \n");
			namedQueryRole.setParameter("film", film.getId());

			List<Role> listRole = namedQueryRole.getResultList();
			for (Role role : listRole) {
				System.out.println(role.getActeur());
			}
		}

		System.out.println("\n------------------------");
		

		//------------------------------------------------
		//Query pour trouver un film entre 2 dates données
		//------------------------------------------------
		TypedQuery<Film> namedQueryFilmDate = em.createNamedQuery("Film.findByDate", Film.class);
		namedQueryFilmDate.setParameter("before", 2019);
		namedQueryFilmDate.setParameter("after", 2020);
		List<Film> listFilmsDate = namedQueryFilmDate.getResultList();

		System.out.println("\nFILM SORTIE ENTRE " + namedQueryFilmDate.getParameterValue("before") + " et " + namedQueryFilmDate.getParameterValue("after") + " : \n");

		for (Film film : listFilmsDate) {
			System.out.println(film);
		}

		System.out.println("\n------------------------");


		//------------------------------------------------
		//Query pour afficher les films dans lesquels les acteurs données ont joué
		//------------------------------------------------
		TypedQuery<Acteur> namedQueryFilmCommun = em.createNamedQuery("Acteur.findById2", Acteur.class);
		namedQueryFilmCommun.setParameter("name", "Georgann Johnson");
		namedQueryFilmCommun.setParameter("name2", "Ed Kemmer");
		List<Acteur> listActeurs1 = namedQueryFilmCommun.getResultList();

		TypedQuery<Role> namedQueryFilmCommunRole = em.createNamedQuery("Role.findByActorId", Role.class);

		List<String> listIdFilm = new ArrayList<>();
		boolean isFirst = true;

		System.out.println("\nFILM(S) DANS LE(S)QUEL(S) JOUENT " + namedQueryFilmCommun.getParameterValue("name") + " et " + namedQueryFilmCommun.getParameterValue("name2") + " : \n");

		for (int i = 0; i < listActeurs1.size(); i++) {
			namedQueryFilmCommunRole.setParameter("acteur", listActeurs1.get(i).getId());
			List<Role> listRole = namedQueryFilmCommunRole.getResultList();
			for (int j = 0; j < listRole.size(); j++) {
				if (isFirst) {
					listIdFilm.add(listRole.get(j).getFilm().getId());
				} else {
					for (int k = 0; k < listIdFilm.size(); k++) {
						if (listRole.get(j).getFilm().getId() == listIdFilm.get(k)) {
							System.out.println(listRole.get(j).getFilm());
						}
					}
				}
			}
			isFirst = false;
		}

		System.out.println("\n------------------------");


		//------------------------------------------------
		//Query pour afficher les acteurs communs à 2 films donnés
		//------------------------------------------------
		TypedQuery<Film> namedQueryActeursCommuns = em.createNamedQuery("Film.findByFilmName2", Film.class);
		namedQueryActeursCommuns.setParameter("film", "Too Far from Norm");
		namedQueryActeursCommuns.setParameter("film2", "Untitled Super Mario Project");
		List<Film> listFilms2 = namedQueryActeursCommuns.getResultList();

		List<Role> listRoleCheck = new ArrayList<>();
		boolean isFirst2 = true;

		System.out.println("\nACTEURS QUI ONT JOUE DANS " + namedQueryActeursCommuns.getParameterValue("film") + " ET DANS " + namedQueryActeursCommuns.getParameterValue("film2") + " : \n");

		for(Film film : listFilms2) {
			List<Role> listRole = film.getRoles();
			for (Role role : listRole) {
				if (isFirst2) {
					listRoleCheck.add(role);
				} else {
					for (Role roleCheck : listRoleCheck) {
						if (roleCheck.getActeur().getId() == role.getActeur().getId()) {
							System.out.println(roleCheck.getActeur());
						}
					}
				}
			}
			isFirst2 = false;
		}

		System.out.println("\n------------------------");


		//------------------------------------------------
		//Query pour afficher les films sorties entre 2 années données et qui ont un acteur donné en commun
		//------------------------------------------------
		// TypedQuery<Film> namedQueryFilmsBetween = em.createNamedQuery("Film.findByDate", Film.class);
		// namedQueryFilmsBetween.setParameter("before", 2015);
		// namedQueryFilmsBetween.setParameter("after", 2016);
		// List<Film> listFilms3 = namedQueryFilmsBetween.getResultList();

		// List<Acteur> listActeurCheck = new ArrayList<>();
		// boolean isFirst3 = true;

		// System.out.println("\nFILM(S) QUI EST(SONT) SORTI(S) ENTRE" + namedQueryFilmsBetween.getParameterValue("before") + " et " + namedQueryFilmsBetween.getParameterValue("after") + " : \n");

		// for (Film film : listFilms3) {
		// 	// System.out.println(film.getRoles());
		// 	List<Role> listRoles = film.getRoles();

		// 	for (Role role : listRoles) {
		// 		role.getActeur();
		// 	}
		// }

        em.close();
	}
	
}
