package parser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import index.UtilsMovie;
import jakarta.persistence.EntityManager;
import model.Film;
import model.Role;

public class Json {
    private static ObjectMapper MAPPER = getMapper();

    private static ObjectMapper getMapper() {
        return new ObjectMapper();
    }

    public static void parseJson(String src, EntityManager em) {
        try {
            Film[] films = MAPPER.readValue(new File(src), Film[].class);
            for (Film film : films) {
                for (Role rolePouet : film.getRoles()) {
                    rolePouet.setFilm(film);
                }
                em.merge(film);     
            }
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
