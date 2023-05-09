package parser;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Film;

public class Json {
    public static ObjectMapper getMapper() {
        return new ObjectMapper();
    }
}
