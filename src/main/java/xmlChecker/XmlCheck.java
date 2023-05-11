package xmlChecker;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import jakarta.persistence.EntityManager;
import parser.Json;

import java.io.File;

public class XmlCheck {
    public XmlCheck(EntityManager em) {
        try {
            // creating a constructor of file class and parsing an XML file
            File file = new File("src/main/resources/META-INF/persistence.xml");
            // an instance of factory that gives a document builder
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            // an instance of builder to parse the specified xml file
            
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("property");

            if (!nodeList.item(4).getAttributes().getNamedItem("value").getNodeValue().equals("none")) {
                System.out.println("\nCREATION ET REMPLISSAGE DE LA BDD AVEC LE JSON");
                em.getTransaction().begin();
                
                new Json("films.json", em);
                
                em.getTransaction().commit();
                System.out.println("\nDONE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
