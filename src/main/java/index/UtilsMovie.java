package index;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class UtilsMovie {
	private static UtilsMovie INSTANCE = new UtilsMovie();
	
	private UtilsMovie() {}
	
	public static UtilsMovie getInstance() {
		return INSTANCE;
	}
	
	private final static EntityManagerFactory EMF = Persistence.createEntityManagerFactory("dbFilm");

	private final static EntityManager EM = EMF.createEntityManager();

	public EntityManager getEntityManager() {
		return EM;
	}
}
