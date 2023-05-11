package DAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.Acteur;
import model.Film;
import model.Role;

public class ActeurDAO {
    public static List<Acteur> getActeursFromFilm(List<Film> listFilms, EntityManager em) {
        List<Acteur> acteurList = new ArrayList<>();

		for (Film film : listFilms) {   

			List<Role> listRole = RoleDAO.getRolesFromFilmId(film.getId(), em);
			for (Role role : listRole) {
				acteurList.add(role.getActeur());
			}
		}
        return acteurList;
    }

    public static List<Acteur> getActeursByName(String name, EntityManager em) {
        TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findByName", Acteur.class);
		namedQueryActeur.setParameter("name",  name);
        return namedQueryActeur.getResultList();
    }

    public static List<Film> getFilmsFromActeurs(List<Acteur> listActeurs, EntityManager em) {
        List<Film> listFilms = new ArrayList<>();
        for(Acteur acteur : listActeurs) {

			List<Role> listRole = RoleDAO.getRolesByActeurId(acteur.getId(), em);
			for (Role role : listRole) {
				listFilms.add(role.getFilm());
			}
		}
        return listFilms;
    }

    public static List<Acteur> getActeursFromNames(String identite, String identite2, EntityManager em) {
        TypedQuery<Acteur> namedQueryActeur = em.createNamedQuery("Acteur.findById2", Acteur.class);
		namedQueryActeur.setParameter("name", identite);
		namedQueryActeur.setParameter("name2", identite2);
        return namedQueryActeur.getResultList();
    }

    public static List<Acteur> getActeursCommunsFromFilms(List<Film> listFilms) {
        List<Acteur> listActeurs = new ArrayList<>();
        List<Role> listRoleCheck = new ArrayList<>();
		boolean isFirst = true;

        for(Film film : listFilms) {
			List<Role> listRole = film.getRoles();
			for (Role role : listRole) {
				if (isFirst) {
					listRoleCheck.add(role);
				} else {
					for (Role roleCheck : listRoleCheck) {
						if (roleCheck.getActeur().getId() == role.getActeur().getId()) {
							listActeurs.add(roleCheck.getActeur());
						}
					}
				}
			}
			isFirst = false;
		}
        return listActeurs;
    }
}
