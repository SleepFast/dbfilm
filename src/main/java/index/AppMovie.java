package index;

import jakarta.persistence.EntityManager;
import service.Start;

public class AppMovie {

	public static void main(String[] args) {

		EntityManager em = UtilsMovie.getInstance().getEntityManager();

		new Start(em);

        em.close();
	}
	
}
