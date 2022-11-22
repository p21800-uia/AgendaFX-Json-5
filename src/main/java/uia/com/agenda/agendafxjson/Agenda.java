package uia.com.agenda.agendafxjson;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Agenda extends InfoAgenda{

    public Agenda() throws IOException {
        super();
    }

    public void serializa() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("miAgenda-2.json"), this);
    }

    public void deserializa() throws IOException {

    }

}
