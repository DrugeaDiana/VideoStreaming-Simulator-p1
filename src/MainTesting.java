import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import inputClasses.InputData;
import tvClasses.TV;
import tvClasses.databasesClasses.MovieDatabase;
import tvClasses.databasesClasses.UsersDatabase;

import java.io.File;
import java.io.IOException;

public class MainTesting {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        InputData inputData = objectMapper.readValue(new File("checker/resources/in/basic_1.json"),
                InputData.class);


        ArrayNode output = objectMapper.createArrayNode();
        ObjectNode node = objectMapper.createObjectNode();

        TV tv = new TV();
        tv.initialize(inputData);

        node.set("Movies!", objectMapper.convertValue(MovieDatabase.getInstance().getMovies(), JsonNode.class));
        node.set("Users!!", objectMapper.convertValue(UsersDatabase.getInstance().getUsers(), JsonNode.class));

        output.add(node);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File("testing.json"), output);
    }
}
