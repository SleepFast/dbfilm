package DAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

public class FilmDAO {
    public static List<Film> getFilmByName(String name, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByFilmName", Film.class);
        namedQueryFilm.setParameter("film", name);
        return namedQueryFilm.getResultList();
    }

    public static List<Film> getFilmBetweenDates(int annee, int annee2, EntityManager em) {
        TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByDate", Film.class);
		namedQueryFilm.setParameter("before", annee);
		namedQueryFilm.setParameter("after", annee2);
		return namedQueryFilm.getResultList();
    }

    public static List<Film> getFilmsSharedActors(List<Acteur> listActeurs1, EntityManager em) {

        List<String> listIdFilm = new ArrayList<>();
        List<Film> listFilm = new ArrayList<>();
		boolean isFirst = true;
        
        for (int i = 0; i < listActeurs1.size(); i++) {
			List<Role> listRole = RoleDAO.getRolesByActeurId(listActeurs1.get(i).getId(), em);

			for (int j = 0; j < listRole.size(); j++) {

				if (isFirst) {
					listIdFilm.add(listRole.get(j).getFilm().getId());
				} else {
					for (int k = 0; k < listIdFilm.size(); k++) {
						if (listRole.get(j).getFilm().getId() == listIdFilm.get(k)) {
                            listFilm.add(listRole.get(j).getFilm());
						}
					}
				}
			}
			isFirst = false;
		}
        return listFilm;
    }

    public static List<Film> getFilmsByNames(String name, String name2, EntityManager em) {
		TypedQuery<Film> namedQueryFilm = em.createNamedQuery("Film.findByFilmName2", Film.class);
		namedQueryFilm.setParameter("film", "Too Far from Norm");
		namedQueryFilm.setParameter("film2", "Untitled Super Mario Project");
        return namedQueryFilm.getResultList();
    }
}
