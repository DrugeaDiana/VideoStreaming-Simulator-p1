import User.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.InputData;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        InputData inputData = objectMapper.readValue(new File("checker/resources/in/basic_1.json"),
                InputData.class);


        ArrayNode output = objectMapper.createArrayNode();
        ObjectNode node = objectMapper.createObjectNode();
        node.set("inputData", objectMapper.convertValue(inputData, JsonNode.class));
        User user = new User();
        user.setName(inputData.getUsers().get(0).getCredentials().getName());
        node.put("name", user.getName());
        System.out.println(user.getName());
        output.add(node);
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File("testing.json"), output);


    }
}
