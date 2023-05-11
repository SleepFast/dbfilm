package service;

import xmlChecker.XmlCheck;
import jakarta.persistence.EntityManager;

public class Start {
    public Start(EntityManager em) {

        // Check si dans mon persistence.xml la value est à none ou create pour lancer le remplissage de la db si on est en create
        new XmlCheck(em);

        // Les queries sans scanner mais variables sont modifiable dans la classe 'Queries'
        new Queries(em);
    }
}
