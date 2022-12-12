import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import inputClasses.InputData;
import tvClasses.TV;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {

        File out = new File(args[1]);
        action(args[0], args[1]);
    }

    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputData inputData = objectMapper.readValue(new File(filePath1),
                InputData.class);

        ArrayNode output = objectMapper.createArrayNode();
        TV tv = new TV(inputData, output);
        tv.initialize();
        tv.mainLoop();

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(filePath2), output);
    }
}
